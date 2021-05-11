package edu.unbosque.servlets.Edition;

import edu.unbosque.services.EditionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteEditionServlet", value = "/delete-edition")
public class DeleteEditionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Integer editionId = Integer.parseInt(request.getParameter("editionId"));
        EditionService editionService = new EditionService();
        editionService.deleteEdition(editionId);

        response.sendRedirect("./form-edition.jsp");
    }
}
