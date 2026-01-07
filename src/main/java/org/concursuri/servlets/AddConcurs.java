package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;

import java.io.IOException;
import java.util.Date;
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        //Date dataDesfasurare = new Date(request.getParameter("dataDesfasurare"));
        //Date startInscrieri = new Date(request.getParameter("startInscrieri"));
        //Date stopInscrieri = new Date(request.getParameter("stopInscrieri"));

        // Use java.sql.Date to parse YYYY-MM-DD strings from HTML5 inputs
        java.sql.Date dataDesfasurare = java.sql.Date.valueOf(request.getParameter("dataDesfasurare"));
        java.sql.Date startInscrieri = java.sql.Date.valueOf(request.getParameter("startInscrieri"));
        java.sql.Date stopInscrieri = java.sql.Date.valueOf(request.getParameter("stopInscrieri"));

        // Handle the 1/0 as booleans
        Boolean isSoftware = "1".equals(request.getParameter("isSoftware"));
        Boolean isHardware = "1".equals(request.getParameter("isHardware"));

        //Integer isSoftware = Integer.parseInt(request.getParameter("isSoftware"));
        //Integer isHardware = Integer.parseInt(request.getParameter("isHardware"));

        String nivel = request.getParameter("nivel");

        concursBean.createConcurs(id, nume, dataDesfasurare, startInscrieri, stopInscrieri, isSoftware, isHardware, nivel);
        response.sendRedirect(request.getContextPath() + "/Concursuri");
    }
}