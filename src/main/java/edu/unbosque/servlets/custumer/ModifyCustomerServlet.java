package edu.unbosque.servlets.custumer;

import edu.unbosque.services.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyCustomerServlet", value = "/modify-customer")
public class ModifyCustomerServlet extends HttpServlet {
    /**
     * The data of the forms is captured and after being validated they are redirected to the services
     *
     * @param request, the request of the client
     * @param response the response of the client
     * @throws IOException      input and output exceptions
     * @throws ServletException servlet exceptions
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // captured data in the forms
        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String stringAge = request.getParameter("age");

        // validation that age is a number
        int age;
        try {
            age = Integer.parseInt(stringAge);
            CustomerService customerService = new CustomerService();
            String message = customerService.modifyCustomer(email, first_name, last_name, gender, age);
            request.setAttribute("modifyCustomerMessage", message);
            System.out.println("Puto");

        } catch (NumberFormatException e) {
            request.setAttribute("modifyCustomerMessage", "Error en la edad, porfavor ingrese un numero valido");
        }
        request.getRequestDispatcher("/form-customer.jsp").forward(request, response);
    }
}
