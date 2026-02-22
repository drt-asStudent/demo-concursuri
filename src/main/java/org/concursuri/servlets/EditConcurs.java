package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.PozeBean;
import org.concursuri.ejb.UsersBean;
import org.concursuri.entities.Poze;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@WebServlet(name = "EditConcurs", value = "/EditConcurs")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 10L * 1024 * 1024,
        maxRequestSize = 12L * 1024 * 1024
)
public class EditConcurs extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Inject
    UsersBean usersBean;

    @Inject
    PozeBean pozeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        if (!request.isUserInRole("organizator")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String idcParam = request.getParameter("idc");
        Integer idc;
        try {
            idc = Integer.valueOf(idcParam);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Integer idOrganizator = usersBean.findUserIdByUsername(principal.getName());
        if (idOrganizator == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        ConcursDto concurs = concursBean.findConcursByIdForOrganizator(idc, idOrganizator);
        if (concurs == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // expose existing prezentare image id (if any) so JSP can show preview
        try {
            org.concursuri.common.PozeDto p = pozeBean.findPrezentareByConcurs(idc);
            if (p != null) {
                request.setAttribute("pozaPrezentareId", p.id());
            }
        } catch (Exception ignored) {
            // ignore DB errors; page can still render without preview
        }

        request.setAttribute("activePage", "ConcursurileMele");
        request.setAttribute("concurs", concurs);
        request.getRequestDispatcher("/WEB-INF/pages/editConcurs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        if (!request.isUserInRole("organizator")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String idcParam = request.getParameter("idc");
        Integer idc;
        try {
            idc = Integer.valueOf(idcParam);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Integer idOrganizator = usersBean.findUserIdByUsername(principal.getName());
        if (idOrganizator == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String nume = request.getParameter("nume");
        String detaliiConcurs = request.getParameter("detaliiConcurs");

        java.sql.Date dataDesfasurare = java.sql.Date.valueOf(request.getParameter("dataDesfasurare"));
        java.sql.Date startInscrieri = java.sql.Date.valueOf(request.getParameter("startInscrieri"));
        java.sql.Date stopInscrieri = java.sql.Date.valueOf(request.getParameter("stopInscrieri"));

        String competitionType = request.getParameter("competitionType");
        String nivel = request.getParameter("nivel");

        int minPart = Integer.parseInt(request.getParameter("minPart"));
        int maxPart = Integer.parseInt(request.getParameter("maxPart"));

        if (minPart > maxPart) {
            ConcursDto concurs = new ConcursDto(
                    idc,
                    nume,
                    detaliiConcurs,
                    dataDesfasurare,
                    startInscrieri,
                    stopInscrieri,
                    competitionType,
                    nivel,
                    minPart,
                    maxPart,
                    0
            );
            request.setAttribute("error", "Invalid participants limits: minPart must be <= maxPart.");
            request.setAttribute("concurs", concurs);
            request.getRequestDispatcher("/WEB-INF/pages/editConcurs.jsp").forward(request, response);
            return;
        }

        boolean updated = concursBean.updateConcursForOrganizator(
                idc,
                idOrganizator,
                nume,
                detaliiConcurs,
                dataDesfasurare,
                startInscrieri,
                stopInscrieri,
                competitionType,
                nivel,
                minPart,
                maxPart
        );

        if (!updated) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Part pozaPart = request.getPart("poza");
        if (pozaPart != null && pozaPart.getSize() > 0) {
            String contentType = pozaPart.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                String submittedName = pozaPart.getSubmittedFileName();
                try (InputStream in = pozaPart.getInputStream()) {
                    byte[] bytes = in.readAllBytes();
                    pozeBean.createPozaForConcurs(
                            idc,
                            submittedName,
                            contentType,
                            bytes,
                            Poze.POZE_INAINTE
                    );
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/ConcursurileMele");
    }
}
