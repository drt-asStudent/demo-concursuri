package org.concursuri.servlets;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.UsersBean;
import org.concursuri.common.UserDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Concursuri", value = "/Concursuri")
public class Concursuri extends HttpServlet {
    @EJB
    ConcursBean concursBean;

    @Inject
    UsersBean usersBean;

    @Inject
    ParticipariBean participariBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri;
        UserDto currentUser = null;
        if (request.getUserPrincipal() != null) {
            String username = request.getUserPrincipal().getName();
            currentUser = usersBean.findUserByUsername(username);
            Integer idu = usersBean.findUserIdByUsername(username);
            if (idu != null) {
                concursuri = concursBean.findConcursuriNotParticipated(idu);
            } else {
                concursuri = concursBean.findAllConcursuri();
            }
        } else {
            concursuri = concursBean.findAllConcursuri();
        }
        for (ConcursDto c : concursuri) {
            if (c.getId() == null) {
                continue;
            }
            int registered = participariBean.countParticipariByConcursId(c.getId());
            c.setRegisteredCount(registered);
        }

        boolean shouldFilterByEmail = currentUser != null
                && currentUser.getRol() != null
                && ("student".equalsIgnoreCase(currentUser.getRol())
                    || "external".equalsIgnoreCase(currentUser.getRol()));
        if (shouldFilterByEmail) {
            String email = currentUser.geteMail();
            boolean isUlbs = email != null && email.toLowerCase().endsWith("@ulbsibiu.ro");
            String requiredNivel = isUlbs ? "ULBSibiu" : "external";
            LocalDate today = LocalDate.now();
            List<ConcursDto> filtered = new ArrayList<>();
            for (ConcursDto c : concursuri) {
                if (c.getNivel() == null) {
                    continue;
                }
                String nivel = c.getNivel();
                boolean nivelAllowed = requiredNivel.equalsIgnoreCase(nivel) || "deschis".equalsIgnoreCase(nivel);
                if (!nivelAllowed) {
                    continue;
                }
                if (c.getStartInscrieri() == null || c.getStopInscrieri() == null) {
                    continue;
                }
                LocalDate startDate = c.getStartInscrieri().toLocalDate();
                LocalDate stopDate = c.getStopInscrieri().toLocalDate();
                if (today.isBefore(startDate) || today.isAfter(stopDate)) {
                    continue;
                }
                if (c.getRegisteredCount() != null && c.getRegisteredCount() >= c.getNumarLocuri()) {
                    continue;
                }
                filtered.add(c);
            }
            concursuri = filtered;
        }

        request.setAttribute("concursuri", concursuri);
        request.getRequestDispatcher("/WEB-INF/pages/concursuri.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
