package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddConcurs", value = "/AddConcurs")
public class AddConcurs extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri = concursBean.findAllConcursuri();
        request.setAttribute("concursuri", concursuri);
        request.getRequestDispatcher("/WEB-INF/pages/addConcurs.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Integer id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        //Date dataDesfasurare = new Date(request.getParameter("dataDesfasurare"));
        //Date startInscrieri = new Date(request.getParameter("startInscrieri"));
        //Date stopInscrieri = new Date(request.getParameter("stopInscrieri"));

        // Use java.sql.Date to parse YYYY-MM-DD strings from HTML5 inputs
        java.sql.Date dataDesfasurare = java.sql.Date.valueOf(request.getParameter("dataDesfasurare"));
        java.sql.Date startInscrieri = java.sql.Date.valueOf(request.getParameter("startInscrieri"));
        java.sql.Date stopInscrieri = java.sql.Date.valueOf(request.getParameter("stopInscrieri"));

        String competitionType = request.getParameter("competitionType");
        String nivel = request.getParameter("nivel");

        int maxPart = Integer.parseInt(request.getParameter("maxPart"));

        concursBean.createConcurs(nume, dataDesfasurare, startInscrieri, stopInscrieri, competitionType, nivel, maxPart);

        if (request.isUserInRole("student") || request.isUserInRole("external")) {
            response.sendRedirect(request.getContextPath() + "/Concursuri");
        } else {
            response.sendRedirect(request.getContextPath() + "/Notare");
        }
    }
}

