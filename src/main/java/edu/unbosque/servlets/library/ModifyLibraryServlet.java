package edu.unbosque.servlets.library;

import edu.unbosque.services.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ModifyLibraryServlet", value = "/modify-library")

public class ModifyLibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer libraryId = Integer.parseInt(request.getParameter("libraryId"));
        String name = request.getParameter("name");
        LibraryService libraryService = new LibraryService();
        libraryService.modifyLibrary(libraryId, name);

        response.sendRedirect("./form-library.jsp");
    }
}
