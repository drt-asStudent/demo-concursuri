package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.security.Principal;

import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.UsersBean;

@WebServlet(name = "Participare", value = "/Participare")
public class Participare extends HttpServlet {
    @Inject
    ParticipariBean participariBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idcParam = request.getParameter("idc");
        if (idcParam == null || idcParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/Concursuri");
            return;
        }
        request.setAttribute("idc", idcParam);
        request.getRequestDispatcher("/WEB-INF/pages/participare.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idcParam = request.getParameter("idc");
        if (idcParam == null || idcParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/Concursuri");
            return;
        }

        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        Integer idu = usersBean.findUserIdByUsername(principal.getName());
        if (idu == null) {
            request.setAttribute("message", "User not found.");
            request.setAttribute("idc", idcParam);
            request.getRequestDispatcher("/WEB-INF/pages/participare.jsp").forward(request, response);
            return;
        }

        String lucrare = request.getParameter("lucrare");
        String descriere = request.getParameter("descriere");
        String profesorCoordonator = request.getParameter("profesorCoordonator");
        Integer idc = Integer.valueOf(idcParam);

        participariBean.createParticipare(lucrare, descriere, profesorCoordonator, idu, idc);
        response.sendRedirect(request.getContextPath() + "/Concursuri");
    }
}
