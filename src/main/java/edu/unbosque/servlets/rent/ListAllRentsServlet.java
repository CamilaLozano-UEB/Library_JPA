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
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "listAllRentsServlet", value = "/find-rentList")
public class ListAllRentsServlet extends HttpServlet {
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

        RentService rentService = new RentService();
        List<RentPOJO> rents = rentService.listAllRents();

        String booksJsonString = new Gson().toJson(rents);

        PrintWriter out = response.getWriter();
        out.print(booksJsonString);
        out.flush();
    }

}
