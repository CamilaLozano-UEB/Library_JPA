package edu.unbosque.servlets.author;

import edu.unbosque.services.AuthorService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveAuthorServlet", value = "/delete-author")
public class DeleteAuthorServlet extends HttpServlet {
    /**
     * Manages the delete operation on the service
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException      input and output exception
     * @throws ServletException a servlet Exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        try {
            Integer authorId = Integer.parseInt(request.getParameter("authorId"));

            AuthorService authorService = new AuthorService();
            // get the delete message of the method deleteAuthor
            message = authorService.deleteAuthor(authorId);
        } catch (NumberFormatException e) {
            message = "El id ingresado es incorrecto!";
        }
        // Create an attribute with the message
        request.setAttribute("deleteAuthorMessage", message);
        // redirect to the form-author.jsp
        request.getRequestDispatcher("/form-author.jsp").forward(request, response);
    }
}
