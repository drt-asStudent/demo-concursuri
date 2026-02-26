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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        Set<Integer> userIds = new LinkedHashSet<>();
        for (List<Participari> participari : participariByConcurs.values()) {
            for (Participari p : participari) {
                if (p.getIdu() != null) {
                    userIds.add(p.getIdu());
                }
            }
        }
        Map<Integer, String> userNamesById = usersBean.findUserNamesByIds(List.copyOf(userIds));

        request.setAttribute("activePage", "AcceptareLucrari");
        request.setAttribute("concursuri", concursuri);
        request.setAttribute("participariByConcurs", participariByConcurs);
        request.setAttribute("userNamesById", userNamesById);

        request.getRequestDispatcher("/WEB-INF/pages/acceptareLucrari.jsp").forward(request, response);
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

        Integer idOrganizator = usersBean.findUserIdByUsername(principal.getName());
        if (idOrganizator == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String pidParam = request.getParameter("pid");
        String accepted = request.getParameter("accepted");
        if (pidParam != null && accepted != null && !accepted.isBlank()) {
            try {
                Integer pid = Integer.valueOf(pidParam);
                if ("YES".equals(accepted) || "REJECT".equals(accepted)) {
                    participariBean.updateAcceptedForOrganizator(pid, idOrganizator, accepted);
                }
            } catch (NumberFormatException ignored) {
            }
        }

        response.sendRedirect(request.getContextPath() + "/AcceptareLucrari");
    }
}
