package edu.unbosque.servlets.author;

import edu.unbosque.services.AuthorService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModifyAuthorServlet", value = "/modify-author")
public class ModifyAuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String country = request.getParameter("country");
        Integer authorId = Integer.parseInt(request.getParameter("authorId"));

        AuthorService authorService = new AuthorService();
        authorService.modifyAuthor(authorId, name, country);

        response.sendRedirect("./form-author.jsp");
    }
}
