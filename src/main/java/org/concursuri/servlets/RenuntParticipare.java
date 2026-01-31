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

@WebServlet(name = "RenuntParticipare", value = "/RenuntParticipare")
public class RenuntParticipare extends HttpServlet {

    @Inject
    ParticipariBean participariBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/InscrierileMele");
            return;
        }

        Integer participareId;
        try {
            participareId = Integer.valueOf(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/InscrierileMele");
            return;
        }

        participariBean.deleteParticipareByIdForUser(participareId, idu);
        response.sendRedirect(request.getContextPath() + "/InscrierileMele");
    }
}