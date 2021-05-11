package edu.unbosque.servlets.Edition;

import edu.unbosque.services.BookService;
import edu.unbosque.services.EditionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "CreateEditionServlet", value = "/create-edition")
public class CreateEditionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String description = request.getParameter("description");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            Date releaseYear = format.parse(request.getParameter("releaseYear"));
            EditionService editionService = new EditionService();

            editionService.saveEdition(bookId, description, releaseYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("./form-edition.jsp");
    }
}
