package edu.unbosque.servlets.library;

import edu.unbosque.services.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteLibraryServlet", value = "/DeleteLibraryServlet")
public class DeleteLibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Integer libraryId = Integer.parseInt(request.getParameter("libraryId"));
        LibraryService libraryService = new LibraryService();
        libraryService.deleteLibrary(libraryId);

        response.sendRedirect("./form-library.jsp");
    }
}
