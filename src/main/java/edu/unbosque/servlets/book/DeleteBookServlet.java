package edu.unbosque.servlets.book;

import edu.unbosque.services.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteBookServlet", value = "/delete-book")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));

        BookService bookService = new BookService();

        String message = bookService.deleteBook(bookId);

        request.setAttribute("deleteBookMessage", message);
        request.getRequestDispatcher("/form-book.jsp").forward(request, response);
    }

}
