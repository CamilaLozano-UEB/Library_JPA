package edu.unbosque.servlets.book;

import edu.unbosque.services.AuthorService;
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

        bookService.deleteBook(bookId);

        response.sendRedirect("./form-book.jsp");
    }

}
