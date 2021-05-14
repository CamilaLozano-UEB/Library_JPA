package edu.unbosque.servlets.Edition;

import edu.unbosque.services.EditionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteEditionServlet", value = "/delete-edition")
public class DeleteEditionServlet extends HttpServlet {
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
        response.setContentType("text/html");

        String message ="";
        try {
            Integer editionId = Integer.parseInt(request.getParameter("editionId"));
            EditionService editionService = new EditionService();
            // get the delete message of the method deleteEdition
            message = editionService.deleteEdition(editionId);
        }catch(NumberFormatException e){
            message = "El id ingresado es incorrecto!";
        }
        // Create an attribute with the message
        request.setAttribute("DeleteEditionMessage", message);
        // redirect to the form-edition.jsp
        request.getRequestDispatcher("/form-edition.jsp").forward(request, response);
    }
}
