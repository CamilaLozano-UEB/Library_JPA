package edu.unbosque.servlets.rent;

import com.google.gson.Gson;
import edu.unbosque.services.RentService;
import edu.unbosque.servlets.pojos.RentPOJO;

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
import java.util.List;
import java.util.Locale;

@WebServlet(name = "listRentsServlet", value = "/find-rentbydays")
public class ListRentsServlet extends HttpServlet {
    /**
     * Manages the list operation on the service, get a list of RentPOJO
     *
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException input and output exception
     * @throws ServletException a servlet Exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String rentsJsonString="";

        try{
            // Create a date format
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            // Format the date received
            Date  renting_date1 = format.parse(request.getParameter("date1"));
            Date renting_date2 = format.parse(request.getParameter("date2"));
            RentService rentService = new RentService();

            List<RentPOJO>rent =  rentService.listRents( renting_date1, renting_date2,email);
            rentsJsonString = new Gson().toJson(rent);
            // Create an attribute with the information to the .jsp on JSON format
            request.setAttribute("rentsJsonString", rentsJsonString);
            // redirect to the form-rent.jsp
            request.getRequestDispatcher("/form-rent.jsp").forward(request, response);
        }catch( ParseException e){
            request.getRequestDispatcher("/form-rent.jsp").forward(request, response);
        }





    }

}
