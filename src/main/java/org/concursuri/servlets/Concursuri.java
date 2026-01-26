package org.concursuri.servlets;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Concursuri", value = "/Concursuri")
public class Concursuri extends HttpServlet {
    @EJB
    ConcursBean concursBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri;
        if (request.getUserPrincipal() != null) {
            Integer idu = usersBean.findUserIdByUsername(request.getUserPrincipal().getName());
            if (idu != null) {
                concursuri = concursBean.findConcursuriNotParticipated(idu);
            } else {
                concursuri = concursBean.findAllConcursuri();
            }
        } else {
            concursuri = concursBean.findAllConcursuri();
        }
        request.setAttribute("concursuri", concursuri);
        request.getRequestDispatcher("/WEB-INF/pages/concursuri.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
