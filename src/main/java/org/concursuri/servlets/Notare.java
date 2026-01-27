package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.FirmeBean;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.UsersBean;
import org.concursuri.entities.Participari;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Notare", value = "/Notare")
public class Notare extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Inject
    ParticipariBean participariBean;

    @Inject
    UsersBean usersBean;

    @Inject
    FirmeBean firmeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();
        Integer iduOrganizator = null;
        if (principal != null) {
            Integer userId = usersBean.findUserIdByUsername(principal.getName());
            if (userId != null) {
                iduOrganizator = firmeBean.findFirmaIdByUserId(userId);
            }
        }
        List<ConcursDto> concursuri = iduOrganizator == null
                ? List.of()
                : concursBean.findConcursuriByOrganizator(iduOrganizator);
        Map<Integer, List<Participari>> participariByConcurs = new LinkedHashMap<>();
        for (ConcursDto concurs : concursuri) {
            participariByConcurs.put(concurs.getId(), participariBean.findParticipariByConcursId(concurs.getId()));
        }
        request.setAttribute("concursuri", concursuri);
        request.setAttribute("participariByConcurs", participariByConcurs);
        request.getRequestDispatcher("/WEB-INF/pages/notarea.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
