package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.concursuri.common.ConcursDto;
import org.concursuri.common.PozeDto;
import org.concursuri.ejb.ConcursBean;
import org.concursuri.ejb.ParticipariBean;
import org.concursuri.ejb.PozeBean;
import org.concursuri.entities.Participari;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ArhivaRezultate", value = "/ArhivaRezultate")
public class ArhivaRezultate extends HttpServlet {
    @Inject
    ConcursBean concursBean;

    @Inject
    ParticipariBean participariBean;

    @Inject
    PozeBean pozeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConcursDto> concursuri = concursBean.findConcursuriClosed();
        Map<Integer, List<Participari>> participariByConcurs = new LinkedHashMap<>();
        Map<Integer, List<PozeDto>> pozeByConcurs = new LinkedHashMap<>();

        for (ConcursDto concurs : concursuri) {
            participariByConcurs.put(
                    concurs.getId(),
                    participariBean.findParticipariByConcursIdOrderByNotaDesc(concurs.getId())
            );
            pozeByConcurs.put(concurs.getId(), pozeBean.findPozeByConcurs(concurs.getId()));
        }

        request.setAttribute("concursuri", concursuri);
        request.setAttribute("participariByConcurs", participariByConcurs);
        request.setAttribute("pozeByConcurs", pozeByConcurs);
        request.getRequestDispatcher("/WEB-INF/pages/arhivaRezultate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
