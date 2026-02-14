package org.concursuri.servlets;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.ParticipariBean;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "Home", value = "")
public class Home extends HttpServlet {

    @EJB
    ConcursBean concursBean;

    @Inject
    ParticipariBean participariBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri = concursBean.findAllConcursuri();

        LocalDate today = LocalDate.now();

        for (ConcursDto c : concursuri) {
            if (c.getId() == null || c.getStartInscrieri() == null) {
                continue;
            }

            LocalDate start = c.getStartInscrieri().toLocalDate();

            // Ongoing or ended registrations: start <= today
            if (!start.isAfter(today)) {
                int registered = participariBean.countParticipariByConcursId(c.getId());
                c.setRegisteredCount(registered);
            }
        }

        request.setAttribute("concursuri", concursuri);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}