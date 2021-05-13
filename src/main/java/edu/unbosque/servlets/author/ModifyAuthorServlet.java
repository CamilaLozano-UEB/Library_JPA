package edu.unbosque.servlets.author;

import edu.unbosque.services.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModifyAuthorServlet", value = "/modify-author")
public class ModifyAuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("name");
        String country = request.getParameter("country");
        Integer authorId = Integer.parseInt(request.getParameter("authorId"));

        AuthorService authorService = new AuthorService();

        String message = authorService.modifyAuthor(authorId, name, country);
        request.setAttribute("modifyAuthorMessage", message);

        request.getRequestDispatcher("/form-author.jsp").forward(request, response);
    }
}
