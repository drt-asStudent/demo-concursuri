package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.PozeBean;
import org.concursuri.ejb.UsersBean;
import org.concursuri.entities.Participari;
import org.concursuri.entities.Poze;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "Notare", value = "/Notare")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,    // 1MB
        maxFileSize = 10L * 1024 * 1024,    // 10MB
        maxRequestSize = 12L * 1024 * 1024  // 12MB
)
public class Notare extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Inject
    ParticipariBean participariBean;

    @Inject
    PozeBean pozeBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri = concursBean.findConcursuriOverdueWithParticipari();
        Map<Integer, List<Participari>> participariByConcurs = new LinkedHashMap<>();
        for (ConcursDto concurs : concursuri) {
            participariByConcurs.put(concurs.getId(), participariBean.findParticipariByConcursId(concurs.getId()));
        }

        Set<Integer> userIds = new LinkedHashSet<>();
        for (List<Participari> participari : participariByConcurs.values()) {
            for (Participari p : participari) {
                if (p.getIdu() != null) {
                    userIds.add(p.getIdu());
                }
            }
        }
        Map<Integer, String> userNamesById = usersBean.findUserNamesByIds(List.copyOf(userIds));

        request.setAttribute("concursuri", concursuri);
        request.setAttribute("participariByConcurs", participariByConcurs);
        request.setAttribute("userNamesById", userNamesById);
        request.getRequestDispatcher("/WEB-INF/pages/notarea.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("close".equals(action)) {
            String idcParam = request.getParameter("idc");
            try {
                Integer idc = Integer.valueOf(idcParam);
                concursBean.markConcursOver(idc);
            } catch (NumberFormatException ignored) {
            }
            response.sendRedirect(request.getContextPath() + "/Notare");
            return;
        }

        if ("upload".equals(action)) {
            String idcParam = request.getParameter("idc");
            Integer idc = null;
            try {
                idc = Integer.valueOf(idcParam);
            } catch (NumberFormatException ignored) {
            }

            if (idc != null) {
                for (Part part : request.getParts()) {
                    if (!"poze".equals(part.getName()) || part.getSize() == 0) {
                        continue;
                    }
                    String contentType = part.getContentType();
                    if (contentType != null && contentType.startsWith("image/")) {
                        String submittedName = part.getSubmittedFileName();
                        try (InputStream in = part.getInputStream()) {
                            byte[] bytes = in.readAllBytes();
                            pozeBean.createPozaForConcurs(
                                    idc,
                                    submittedName,
                                    contentType,
                                    bytes,
                                    Poze.POZE_DUPA
                            );
                        }
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/Notare");
            return;
        }

        String iduParam = request.getParameter("idu");
        String idcParam = request.getParameter("idc");
        String lucrare = request.getParameter("lucrare");
        String notaParam = request.getParameter("nota");

        if (iduParam == null || idcParam == null || lucrare == null || lucrare.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/Notare?error=1");
            return;
        }

        Integer idu;
        Integer idc;
        Integer nota;
        try {
            idu = Integer.valueOf(iduParam);
            idc = Integer.valueOf(idcParam);
            nota = notaParam == null || notaParam.isBlank() ? null : Integer.valueOf(notaParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/Notare?error=1");
            return;
        }

        boolean updated = participariBean.updateNota(idu, idc, lucrare, nota);
        if (!updated) {
            response.sendRedirect(request.getContextPath() + "/Notare?notFound=1");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/Notare?saved=1");
    }
}
