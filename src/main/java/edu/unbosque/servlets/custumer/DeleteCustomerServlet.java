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

    /**
     * The data of the forms is captured and after being validated they are redirected to the services
     *
     * @param request, the request of the client
     * @param response the response of the client
     * @throws IOException      input and output exceptions
     * @throws ServletException servlet exceptions
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // captured data in the forms
        String email = request.getParameter("email");
        CustomerService customerService = new CustomerService();
        String message = customerService.deleteCustomer(email);
        request.setAttribute("deleteCustomerMessage", message);
        request.getRequestDispatcher("/form-customer.jsp").forward(request, response);
    }
}
