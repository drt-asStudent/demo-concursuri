package org.concursuri.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.concursuri.common.PozeDto;
import org.concursuri.ejb.PozeBean;

import java.io.IOException;

@WebServlet(name = "ImageServlet", value = "/Image")
public class ImageServlet extends HttpServlet {

    @Inject
    PozeBean pozeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        Integer id;
        try {
            id = Integer.valueOf(idParam);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        PozeDto poza = pozeBean.findPozaById(id);
        if (poza == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        byte[] data = poza.fileContent();
        String contentType = poza.fileType();
        if (contentType == null || contentType.isEmpty()) {
            contentType = "application/octet-stream";
        }
        response.setContentType(contentType);
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
    }
}
