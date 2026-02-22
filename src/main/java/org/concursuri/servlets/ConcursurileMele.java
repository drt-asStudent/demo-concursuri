package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@WebServlet(name = "ConcursurileMele", value = "/ConcursurileMele")
public class ConcursurileMele extends HttpServlet {
    @Inject
    ConcursBean concursBean;

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

        request.setAttribute("activePage", "ConcursurileMele");
        request.setAttribute("concursuri", concursuri);
        request.getRequestDispatcher("/WEB-INF/pages/concursurileMele.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
