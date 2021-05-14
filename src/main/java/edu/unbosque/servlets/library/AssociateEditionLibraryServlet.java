package edu.unbosque.servlets.library;

import edu.unbosque.services.LibraryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AssociateEditionLibraryServlet", value = "/associate-library")
public class AssociateEditionLibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Integer libraryId = Integer.parseInt(request.getParameter("libraryId"));
        Integer editionId = Integer.parseInt(request.getParameter("editionId"));

        LibraryService libraryService = new LibraryService();
        String message = libraryService.associateEdition(libraryId, editionId);
        request.setAttribute("associateLibraryMessage", message);
        request.getRequestDispatcher("/form-library.jsp").forward(request, response);
    }
}
