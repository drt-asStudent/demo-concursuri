package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.UsersBean;
import org.concursuri.entities.Participari;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AcceptareLucrari", value = "/AcceptareLucrari")
public class AcceptareLucrari extends HttpServlet {

    @Inject
    ConcursBean concursBean;

    @Inject
    ParticipariBean participariBean;

    @Inject
    UsersBean usersBean;

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

        Integer idOrganizator = usersBean.findUserIdByUsername(principal.getName());
        if (idOrganizator == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        List<ConcursDto> concursuri = concursBean.findConcursuriByOrganizator(idOrganizator);

        Map<Integer, List<Participari>> participariByConcurs = new LinkedHashMap<>();
        for (ConcursDto concurs : concursuri) {
            participariByConcurs.put(concurs.getId(), participariBean.findParticipariByConcursId(concurs.getId()));
        }

        request.setAttribute("activePage", "AcceptareLucrari");
        request.setAttribute("concursuri", concursuri);
        request.setAttribute("participariByConcurs", participariByConcurs);

        request.getRequestDispatcher("/WEB-INF/pages/acceptareLucrari.jsp").forward(request, response);
    }
}