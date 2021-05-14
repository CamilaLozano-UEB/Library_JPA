package edu.unbosque.servlets.library;

import edu.unbosque.services.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteLibraryServlet", value = "/delete-library")
public class DeleteLibraryServlet extends HttpServlet {
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
        response.setContentType("text/html");
        // captured data in the forms
        Integer libraryId = Integer.parseInt(request.getParameter("libraryId"));
        LibraryService libraryService = new LibraryService();
        // Save the parameters to send them to services
        String message = libraryService.deleteLibrary(libraryId);
        request.setAttribute("deleteLibraryMessage", message);
        request.getRequestDispatcher("/form-library.jsp").forward(request, response);
    }
}
