package org.concursuri.servlets;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet(name = "Home", value = "")
public class Home extends HttpServlet {

    @EJB
    ConcursBean concursBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Get the data
        List<ConcursDto> concursuri = concursBean.findAllConcursuri();

        // 2. Put it in the request so index.jsp can see it
        request.setAttribute("concursuri", concursuri);

        // 3. Forward to index.jsp
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}