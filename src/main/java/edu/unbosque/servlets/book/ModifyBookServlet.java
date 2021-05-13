package edu.unbosque.servlets.book;

import edu.unbosque.services.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModifyBookServlet", value = "/modify-book")
public class ModifyBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Integer authorId = Integer.parseInt(request.getParameter("authorId"));
        String title = request.getParameter("title");
        String isbn = request.getParameter("isbn");
        String genre = request.getParameter("genre");

        BookService bookService = new BookService();
        bookService.modifyBook(bookId, authorId, title, isbn, genre);

        response.sendRedirect("./form-book.jsp");
    }

}
