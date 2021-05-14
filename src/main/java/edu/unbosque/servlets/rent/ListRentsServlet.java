package edu.unbosque.servlets.rent;

import com.google.gson.Gson;
import edu.unbosque.services.RentService;
import edu.unbosque.servlets.pojos.RentPOJO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "listRentsServlet", value = "/find-rentbydays")
public class ListRentsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String rentsJsonString="";
        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date  renting_date1 = format.parse(request.getParameter("date1"));
            Date renting_date2 = format.parse(request.getParameter("date2"));
            RentService rentService = new RentService();
            List<RentPOJO>rent =  rentService.listRents( renting_date1, renting_date2,email);
            rentsJsonString = new Gson().toJson(rent);
        }catch( ParseException e){
        }

        PrintWriter out = response.getWriter();
        out.print(rentsJsonString);
        out.flush();

    }

}
