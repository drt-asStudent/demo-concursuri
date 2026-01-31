package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.security.Principal;

@WebServlet(name = "InscrierileMele", value = "/InscrierileMele")
public class InscrierileMele extends HttpServlet {

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

        Integer idu = usersBean.findUserIdByUsername(principal.getName());
        if (idu == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        request.setAttribute("activePage", "InscrierileMele");
        request.setAttribute("inscrieri", participariBean.findInscrieriForUser(idu));
        request.getRequestDispatcher("/WEB-INF/pages/inscrierileMele.jsp").forward(request, response);
    }
}