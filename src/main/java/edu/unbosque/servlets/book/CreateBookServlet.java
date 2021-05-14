package edu.unbosque.servlets.book;


import edu.unbosque.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "createBookServlet", value = "/create-book")
public class CreateBookServlet extends HttpServlet {

    /**
     * Manages the create operation on the service
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException      input and output exception
     * @throws ServletException a servlet Exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");

        String title = request.getParameter("title");
        String isbn = request.getParameter("isbn");
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");

        // Create a date format
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String message = "";
        try {
            // Format the date received
            Date releaseYear = format.parse(request.getParameter("releaseYear"));
            Integer authorId = Integer.parseInt(request.getParameter("authorId"));
            BookService bookService = new BookService();
            // get the create message of the method createBook
            message = bookService.saveBook(title, isbn, authorId, genre, description, releaseYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Create an attribute with the message
        request.setAttribute("createBookMessage", message);
        // redirect to the form-book.jsp
        request.getRequestDispatcher("/form-book.jsp").forward(request, response);
    }

}
