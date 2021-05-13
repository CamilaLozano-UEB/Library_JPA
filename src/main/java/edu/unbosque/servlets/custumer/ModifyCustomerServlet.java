package edu.unbosque.servlets.custumer;

import edu.unbosque.services.CustomerService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyCustomerServlet", value = "/modify-customer")
public class ModifyCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String ageString = request.getParameter("age");
        int age= Integer.parseInt(ageString);

        CustomerService customerService = new CustomerService();
        if (customerService.findCustomer(email)){
            customerService.modifyCustomer(email,first_name,last_name,gender,age);
        }

        response.sendRedirect("./form-customer.jsp");
    }
}
