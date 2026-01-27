package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.FirmeBean;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddConcurs", value = "/AddConcurs")
public class AddConcurs extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Inject
    UsersBean usersBean;

    @Inject
    FirmeBean firmeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri = concursBean.findAllConcursuri();
        request.setAttribute("concursuri", concursuri);
        Integer iduOrganizator = null;
        if (request.getUserPrincipal() != null) {
            Integer userId = usersBean.findUserIdByUsername(request.getUserPrincipal().getName());
            if (userId != null) {
                iduOrganizator = firmeBean.findFirmaIdByUserId(userId);
            }
        }
        request.setAttribute("iduOrganizator", iduOrganizator);
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

        // Handle the 1/0 as booleans
        Boolean isSoftware = "1".equals(request.getParameter("isSoftware"));
        Boolean isHardware = "1".equals(request.getParameter("isHardware"));

        //Integer isSoftware = Integer.parseInt(request.getParameter("isSoftware"));
        //Integer isHardware = Integer.parseInt(request.getParameter("isHardware"));

        String nivel = request.getParameter("nivel");

        Integer iduOrganizator = null;
        String iduOrganizatorParam = request.getParameter("iduOrganizator");
        if (iduOrganizatorParam != null && !iduOrganizatorParam.isBlank()) {
            iduOrganizator = Integer.valueOf(iduOrganizatorParam);
        }
        if (iduOrganizator == null && request.getUserPrincipal() != null) {
            Integer userId = usersBean.findUserIdByUsername(request.getUserPrincipal().getName());
            if (userId != null) {
                iduOrganizator = firmeBean.findFirmaIdByUserId(userId);
            }
        }
        concursBean.createConcurs(nume, dataDesfasurare, startInscrieri, stopInscrieri, isSoftware, isHardware, nivel, iduOrganizator);
        if (request.isUserInRole("student") || request.isUserInRole("elev")) {
            response.sendRedirect(request.getContextPath() + "/Concursuri");
        } else {
            response.sendRedirect(request.getContextPath() + "/Notare");
        }
    }
}
