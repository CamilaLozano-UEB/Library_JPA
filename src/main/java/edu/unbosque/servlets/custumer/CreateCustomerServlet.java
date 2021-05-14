package edu.unbosque.servlets.custumer;

import edu.unbosque.services.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createCustomerServlet", value = "/create-customer")
public class CreateCustomerServlet extends HttpServlet {
    /**
     * The data of the forms is captured and after being validated they are redirected to the services
     *
     * @param request, the request of the client
     * @param response the response of the client
     * @throws IOException      input and output exceptions
     * @throws ServletException servlet exceptions
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // captured data in the forms
        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String stringAge = request.getParameter("age");
        int age;
        // validation that age is a number
        try {
            age = Integer.parseInt(stringAge);
            CustomerService customerService = new CustomerService();
            // validate that the email is not the same and save the parameters to send them to services
            if (!customerService.findCustomer(email)) {
                String message = customerService.saveCustomer(email, first_name, last_name, gender, age);
                request.setAttribute("createCustomerMessage", message);
            } else {
                request.setAttribute("createCustomerMessage", "Ya existe un cliente con el mismo Email");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("createCustomerMessage", "Error en la edad, porfavor ingrese un numero valido");
        }
        request.getRequestDispatcher("/form-customer.jsp").forward(request, response);
    }
}
