package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.common.ConcursDto;
import org.concursuri.common.PozeDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.PozeBean;
import org.concursuri.entities.Participari;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetaliiConcurs", value = "/DetaliiConcurs")
public class DetaliiConcurs extends HttpServlet {

    @Inject
    ConcursBean concursBean;

    @Inject
    ParticipariBean participariBean;

    @Inject
    PozeBean pozeBean;

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
        List<Participari> participari = participariBean.findAcceptedParticipariByConcursId(idc);

        PozeDto pozaPrezentare = pozeBean.findPrezentareByConcurs(idc);

        request.setAttribute("concurs", concurs);
        request.setAttribute("registeredCount", registeredCount);
        request.setAttribute("participari", participari);
        request.setAttribute("pozaPrezentare", pozaPrezentare);

        request.getRequestDispatcher("/WEB-INF/pages/detaliiConcurs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
