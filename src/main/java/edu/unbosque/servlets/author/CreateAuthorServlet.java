package edu.unbosque.servlets.author;

import edu.unbosque.services.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createAuthorServlet", value = "/create-author")
public class CreateAuthorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String country = request.getParameter("country");

        AuthorService authorService = new AuthorService();
        authorService.saveAuthor(name, country);

        String message = "hello";
        request.setAttribute("message", message);

        response.sendRedirect("./form-author.jsp");
    }

}
