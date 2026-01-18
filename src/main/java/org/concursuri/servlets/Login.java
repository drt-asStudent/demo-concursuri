package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import org.concursuri.common.UserDto;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDto user = usersBean.verifyUser(username, password);

        if (user != null) {
            // Success! Store user info in the session
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Failure
            request.setAttribute("message", "Username or password incorrect");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}