package edu.unbosque.servlets.custumer;

import edu.unbosque.services.AuthorService;
import edu.unbosque.services.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveCustomerServlet", value = "/delete-customer")
public class DeleteCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");

        CustomerService customerService = new CustomerService();
        customerService.deletecustomer(email);

        response.sendRedirect("./form-customer.jsp");
    }
}
