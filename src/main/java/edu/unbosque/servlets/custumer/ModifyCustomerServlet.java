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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String ageString = request.getParameter("age");
        int age;

        try{
            age= Integer.parseInt(ageString);
            CustomerService customerService = new CustomerService();
            if (customerService.findCustomer(email)){
                customerService.modifyCustomer(email,first_name,last_name,gender,age);
                request.setAttribute("modifyCustomerMessage", " ");
            }else{
                request.setAttribute("modifyCustomerMessage", "No existe un cliente con ese Email");

            }
        }catch (NumberFormatException e){
            request.setAttribute("modifyCustomerMessage", "Error en la edad, porfavor ingrese un numero valido");
        }


        request.getRequestDispatcher("/form-customer.jsp").forward(request, response);
    }
}
