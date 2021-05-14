package edu.unbosque.servlets.library;

import edu.unbosque.services.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AssociateEditionLibraryServlet", value = "/associate-library")
public class AssociateEditionLibraryServlet extends HttpServlet {

    /**
     * The data of the forms is captured and after being validated they are redirected to the services
     *
     * @param request, the request of the client
     * @param response the response of the client
     * @throws IOException      input and output exceptions
     * @throws ServletException servlet exceptions
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // captured data in the forms
        Integer libraryId = Integer.parseInt(request.getParameter("libraryId"));
        Integer editionId = Integer.parseInt(request.getParameter("editionId"));
        // Save the parameters to send them to services
        LibraryService libraryService = new LibraryService();
        String message = libraryService.associateEdition(libraryId, editionId);
        request.setAttribute("associateLibraryMessage", message);
        request.getRequestDispatcher("/form-library.jsp").forward(request, response);
    }
}
