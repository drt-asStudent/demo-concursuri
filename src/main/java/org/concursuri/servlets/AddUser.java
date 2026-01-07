package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.common.UserDto;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUser extends HttpServlet {
    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> concursuri = usersBean.findAllUsers();
        request.setAttribute("users", concursuri);
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String eMail = request.getParameter("eMail");
        Integer telefon = request.getParameter("telefon") == null ? null : Integer.parseInt(request.getParameter("telefon"));
        Integer varsta = Integer.parseInt(request.getParameter("varsta"));
        String rol = request.getParameter("rol");

        usersBean.createUser(id, nume, prenume, eMail, telefon, rol, varsta);
        response.sendRedirect(request.getContextPath() + "/AddUser");
    }
}