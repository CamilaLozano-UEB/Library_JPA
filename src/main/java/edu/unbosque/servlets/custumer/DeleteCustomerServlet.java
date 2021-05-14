package edu.unbosque.servlets.custumer;

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");

        CustomerService customerService = new CustomerService();
        if (customerService.findCustomer(email)){
            customerService.deleteCustomer(email);
            request.setAttribute("deleteCustomerMessage", " ");
        }else{
            request.setAttribute("deleteCustomerMessage", "No existe un cliente con ese Email");

        }

        request.getRequestDispatcher("/form-customer.jsp").forward(request, response);
    }
}
