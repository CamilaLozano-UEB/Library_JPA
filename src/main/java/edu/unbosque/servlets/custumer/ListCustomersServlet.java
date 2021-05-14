package edu.unbosque.servlets.custumer;

import com.google.gson.Gson;
import edu.unbosque.services.CustomerService;
import edu.unbosque.servlets.pojos.CustomerPOJO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "listAuthorsServlet", value = "/list-customer")
public class ListCustomersServlet extends HttpServlet {
    /**
     * Manages the list operation on the service, get a list of CustomerPOJO and print the information to the .jsp on JSON
     * format
     * @param request, the request of the client
     * @param response the response of the client
     * @throws IOException input and output exceptions
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CustomerService customerService = new CustomerService();
        List<CustomerPOJO> customers = customerService.listCustomer();

        String customersJsonString = new Gson().toJson(customers);

        PrintWriter out = response.getWriter();
        out.print(customersJsonString);
        out.flush();

    }

}
