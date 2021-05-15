package edu.unbosque.servlets.rent;


import edu.unbosque.services.BookService;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");

        String email = request.getParameter("email");
        String message;
        try{
            Integer editionId = Integer.parseInt(request.getParameter("editionId"));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date releaseYear = format.parse(request.getParameter("releaseYear"));
            RentService rentService=new RentService();
            message= rentService.saveRent(email,editionId,releaseYear);

        }catch(NumberFormatException |ParseException e){
            message="Es necesario que el id de edicion sea numerica";
        }
        request.setAttribute("createRentMessage", message);


        request.getRequestDispatcher("/form-rent.jsp").forward(request, response);
    }

}
