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

@WebServlet(name = "AddAcceptUsers", value = "/AddAcceptUsers")
public class AddAcceptUsers extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = usersBean.findApplying();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/addForAccept.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("user_id");
        if (userIdStr != null) {
            Integer userId = Integer.parseInt(userIdStr);
            usersBean.acceptUser(userId);
        }
        response.sendRedirect(request.getContextPath() + "/AddAcceptUsers");
    }
}