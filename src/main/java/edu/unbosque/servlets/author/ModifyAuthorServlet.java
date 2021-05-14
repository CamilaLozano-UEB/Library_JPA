package edu.unbosque.servlets.author;

import edu.unbosque.services.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModifyAuthorServlet", value = "/modify-author")
public class ModifyAuthorServlet extends HttpServlet {

    /**
     * Manages the modify operation on the DB
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException      input and output exception
     * @throws ServletException a servlet Exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message = "";

        try {
            Integer authorId = Integer.parseInt(request.getParameter("authorId"));
            String name = request.getParameter("name");
            String country = request.getParameter("country");

            AuthorService authorService = new AuthorService();
            // get the modify message of the method modifyAuthor
            message = authorService.modifyAuthor(authorId, name, country);
        } catch (NumberFormatException e) {
            message = "El id ingresado es incorrecto";
        }
        // Create an attribute with the message
        request.setAttribute("modifyAuthorMessage", message);
        // redirect to the form-author.jsp
        request.getRequestDispatcher("/form-author.jsp").forward(request, response);
    }
}
