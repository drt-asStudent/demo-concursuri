package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.common.ConcursDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.entities.Participari;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetaliiConcurs", value = "/DetaliiConcurs")
public class DetaliiConcurs extends HttpServlet {

    @Inject
    ConcursBean concursBean;

    @Inject
    ParticipariBean participariBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idcParam = request.getParameter("idc");
        if (idcParam == null || idcParam.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter: idc");
            return;
        }

        final Integer idc;
        try {
            idc = Integer.valueOf(idcParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter idc: must be an integer");
            return;
        }

        ConcursDto concurs = concursBean.findConcursById(idc);
        if (concurs == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Concurs not found for idc=" + idc);
            return;
        }

        int registeredCount = participariBean.countParticipariByConcursId(idc);
        List<Participari> participari = participariBean.findParticipariByConcursId(idc);

        request.setAttribute("concurs", concurs);
        request.setAttribute("registeredCount", registeredCount);
        request.setAttribute("participari", participari);

        request.getRequestDispatcher("/WEB-INF/pages/detaliiConcurs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}