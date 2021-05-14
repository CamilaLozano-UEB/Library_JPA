package edu.unbosque.servlets.Edition;

import edu.unbosque.services.EditionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "ModifyEditionServlet", value = "/modify-edition")
public class ModifyEditionServlet extends HttpServlet {
    /**
     * Manages the modify operation on the DB
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException      input and output exception
     * @throws ServletException a servlet Exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "";
        try {
            Integer editionId = Integer.parseInt(request.getParameter("editionId"));
            Integer bookId = Integer.parseInt(request.getParameter("bookId"));
            String description = request.getParameter("description");
            // Create a date format
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            // Format the date received
            Date releaseYear = format.parse(request.getParameter("releaseYear"));
            EditionService editionService = new EditionService();

            // get the create message of the method modifyEdition
            message = editionService.modifyEdition(editionId, bookId, description, releaseYear);
        } catch (ParseException | NumberFormatException e) {
            message = "El id ingresado es incorrecto!";
        }
        // Create an attribute with the message
        request.setAttribute("modifyEditionMessage", message);
        // redirect to the form-edition.jsp
        request.getRequestDispatcher("/form-edition.jsp").forward(request, response);
    }
}
