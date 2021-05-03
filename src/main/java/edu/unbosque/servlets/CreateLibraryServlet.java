package edu.unbosque.servlets;


import edu.unbosque.services.LibraryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createLibraryServlet", value = "/create-library")
public class CreateLibraryServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");

        LibraryService libraryService = new LibraryService();
        libraryService.saveLibrary(name);

        response.sendRedirect("./index.jsp");
    }

}
