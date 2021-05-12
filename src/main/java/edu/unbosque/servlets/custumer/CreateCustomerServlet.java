package edu.unbosque.servlets.custumer;

import edu.unbosque.services.CustomerService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "createCustomerServlet", value = "/create-customer")
public class CreateCustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String ageString = request.getParameter("age");
        int age= Integer.parseInt(ageString);

        CustomerService customerService = new CustomerService();
        if (!customerService.findCustomer(email)) {
            customerService.saveCustomer(email, first_name, last_name, gender, age);
        }


        response.sendRedirect("./index.html");
    }

}
