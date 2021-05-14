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

    /**
     * Manages the create operation on the service
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException      input and output exception
     * @throws ServletException a servlet Exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String country = request.getParameter("country");

        AuthorService authorService = new AuthorService();

        // get the delete message of the method deleteAuthor
        String message = authorService.saveAuthor(name, country);
        // Create an attribute with the message
        request.setAttribute("createAuthorMessage", message);
        // redirect to the form-author.jsp
        request.getRequestDispatcher("/form-author.jsp").forward(request, response);
    }

}
