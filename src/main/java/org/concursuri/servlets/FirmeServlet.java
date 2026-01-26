package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

import org.concursuri.ejb.FirmeBean;
import org.concursuri.entities.Firme;

@WebServlet(name = "Firme", value = "/Firme")
public class FirmeServlet extends HttpServlet {
    @Inject
    FirmeBean firmeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Firme> firme = firmeBean.findAllFirmeWithReprezentanti();
        request.setAttribute("firme", firme);
        request.getRequestDispatcher("/WEB-INF/pages/firme.jsp").forward(request, response);
    }
}
