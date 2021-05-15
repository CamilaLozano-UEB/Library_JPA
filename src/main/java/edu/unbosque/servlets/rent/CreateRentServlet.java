package edu.unbosque.servlets.rent;

import edu.unbosque.services.RentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "createRentServlet", value = "create-rent")
public class CreateRentServlet extends HttpServlet {
    /**
     *  Manages the create operation on the service
     *
     * @param request the request of the client
     * @param response the response to the client
     * @throws IOException input and output exception
     * @throws ServletException a servlet Exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");

        String email = request.getParameter("email");
        String message;
        try{
            Integer editionId = Integer.parseInt(request.getParameter("editionId"));
            // Create a date format
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            // Format the date received
            Date releaseYear = format.parse(request.getParameter("releaseYear"));
            RentService rentService=new RentService();
            // get the create message of the method saveRent
            message= rentService.saveRent(email,editionId,releaseYear);

        }catch(NumberFormatException |ParseException e){
            message="Es necesario que el id de edicion sea numerica";
        }
        // Create an attribute with the message
        request.setAttribute("createRentMessage", message);
        // redirect to the form-rent.jsp
        request.getRequestDispatcher("/form-rent.jsp").forward(request, response);
    }

}
