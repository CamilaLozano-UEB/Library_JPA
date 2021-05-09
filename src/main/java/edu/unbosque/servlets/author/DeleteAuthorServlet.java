package edu.unbosque.servlets.author;

import edu.unbosque.services.AuthorService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveAuthorServlet", value = "/delete-author")
public class DeleteAuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer authorId = Integer.parseInt(request.getParameter("authorId"));

        AuthorService authorService = new AuthorService();
        authorService.deleteAuthor(authorId);

        response.sendRedirect("./form-author.jsp");
    }
}