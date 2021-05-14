package edu.unbosque.servlets.library;


import edu.unbosque.services.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createLibraryServlet", value = "/create-library")
public class CreateLibraryServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");

        LibraryService libraryService = new LibraryService();
        String message = libraryService.saveLibrary(name);
        request.setAttribute("createLibraryMessage", message);
        request.getRequestDispatcher("/form-library.jsp").forward(request, response);
    }

}
