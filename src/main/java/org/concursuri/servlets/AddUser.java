package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.common.UserDto;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUser extends HttpServlet {
    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users );
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String eMail = request.getParameter("eMail");
        String telefon = request.getParameter("telefon");
        Integer varsta = Integer.parseInt(request.getParameter("varsta"));
        String rol = request.getParameter("rol");
        //Date bday = (bdayString != null && !bdayString.isEmpty()) ? Date.valueOf(bdayString) : null;
        java.sql.Date bday = java.sql.Date.valueOf(request.getParameter("bday"));

        usersBean.createUser(id, nume, prenume, eMail, telefon, rol, varsta, bday, true);
        response.sendRedirect(request.getContextPath() + "/Users");
    }
}