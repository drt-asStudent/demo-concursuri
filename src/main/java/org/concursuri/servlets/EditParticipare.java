package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.UsersBean;
import org.concursuri.entities.Participari;

import java.io.IOException;
import java.security.Principal;

@WebServlet(name = "EditParticipare", value = "/EditParticipare")
public class EditParticipare extends HttpServlet {

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

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/InscrierileMele");
            return;
        }

        Integer idu = usersBean.findUserIdByUsername(principal.getName());
        if (idu == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        Integer participareId;
        try {
            participareId = Integer.valueOf(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/InscrierileMele");
            return;
        }

        Participari participare = participariBean.findParticipareByIdForUser(participareId, idu);
        if (participare == null) {
            response.sendRedirect(request.getContextPath() + "/InscrierileMele");
            return;
        }

        request.setAttribute("participare", participare);
        request.getRequestDispatcher("/WEB-INF/pages/editParticipare.jsp").forward(request, response);
    }

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

        String lucrare = request.getParameter("lucrare");
        String descriere = request.getParameter("descriere");
        String profesorCoordonator = request.getParameter("profesorCoordonator");

        boolean updated = participariBean.updateParticipareFields(
                participareId,
                idu,
                lucrare,
                descriere,
                profesorCoordonator
        );

        if (!updated) {
            request.setAttribute("message", "Nu ai permisiunea să editezi această înscriere (sau nu există).");
            Participari participare = participariBean.findParticipareByIdForUser(participareId, idu);
            request.setAttribute("participare", participare);
            request.getRequestDispatcher("/WEB-INF/pages/editParticipare.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/InscrierileMele");
    }
}