package edu.unbosque.servlets.book;

import edu.unbosque.services.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModifyBookServlet", value = "/modify-book")
public class ModifyBookServlet extends HttpServlet {
    /**
     * Manages the modify operation on the DB
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
            Integer authorId = Integer.parseInt(request.getParameter("authorId"));
            String title = request.getParameter("title");
            String isbn = request.getParameter("isbn");
            String genre = request.getParameter("genre");

            BookService bookService = new BookService();
            // get the modify message of the method modifyBook
            message = bookService.modifyBook(bookId, authorId, title, isbn, genre);
        } catch (NumberFormatException e) {
            message = "El id ingresado es incorrecto!";
        }
        // Create an attribute with the message
        request.setAttribute("createBookMessage", message);
        // redirect to the form-book.jsp
        request.getRequestDispatcher("/form-book.jsp").forward(request, response);
    }

}
