package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.UsersBean;

import java.io.IOException;
import java.security.Principal;

@WebServlet(name = "CancelConcurs", value = "/CancelConcurs")
public class CancelConcurs extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Inject
    UsersBean usersBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        if (!request.isUserInRole("organizator")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String idcParam = request.getParameter("idc");
        Integer idc;
        try {
            idc = Integer.valueOf(idcParam);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Integer idOrganizator = usersBean.findUserIdByUsername(principal.getName());
        if (idOrganizator == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        boolean canceled = concursBean.cancelConcursForOrganizator(idc, idOrganizator);
        if (!canceled) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/ConcursurileMele");
    }
}
