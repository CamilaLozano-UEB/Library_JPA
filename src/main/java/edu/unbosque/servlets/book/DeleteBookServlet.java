package edu.unbosque.servlets.book;

import edu.unbosque.services.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteBookServlet", value = "/delete-book")
public class DeleteBookServlet extends HttpServlet {
    /**
     * Manages the delete operation on the service
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException      input and output exception
     * @throws ServletException a servlet Exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        try {
            Integer bookId = Integer.parseInt(request.getParameter("bookId"));

            BookService bookService = new BookService();
            // get the delete message of the method deleteBook
            message = bookService.deleteBook(bookId);
        } catch (NumberFormatException e) {
            message = "El id ingresado es incorrecto!";
        }
        // Create an attribute with the message
        request.setAttribute("deleteBookMessage", message);
        // redirect to the form-book.jsp
        request.getRequestDispatcher("/form-book.jsp").forward(request, response);
    }

}
