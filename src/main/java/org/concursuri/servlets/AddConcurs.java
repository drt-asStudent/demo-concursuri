package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@WebServlet(name = "AddConcurs", value = "/AddConcurs")
public class AddConcurs extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri = concursBean.findAllConcursuri();
        request.setAttribute("concursuri", concursuri);
        request.getRequestDispatcher("/WEB-INF/pages/addConcurs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        Integer idOrganizator = usersBean.findUserIdByUsername(principal.getName());
        if (idOrganizator == null) {
            request.setAttribute("error", "Organizer user not found.");
            request.getRequestDispatcher("/WEB-INF/pages/addConcurs.jsp").forward(request, response);
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
            request.setAttribute("error", "Invalid participants limits: minPart must be <= maxPart.");
            request.getRequestDispatcher("/WEB-INF/pages/addConcurs.jsp").forward(request, response);
            return;
        }

        concursBean.createConcurs(
                nume,
                detaliiConcurs,
                dataDesfasurare,
                startInscrieri,
                stopInscrieri,
                competitionType,
                nivel,
                minPart,
                maxPart,
                idOrganizator
        );

        if (request.isUserInRole("student") || request.isUserInRole("external")) {
            response.sendRedirect(request.getContextPath() + "/Concursuri");
        } else {
            response.sendRedirect(request.getContextPath() + "/Notare");
        }
    }
}

