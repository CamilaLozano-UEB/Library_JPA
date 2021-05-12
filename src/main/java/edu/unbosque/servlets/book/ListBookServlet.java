package edu.unbosque.servlets.book;

import com.google.gson.Gson;
import edu.unbosque.services.BookService;
import edu.unbosque.servlets.pojos.BookPOJO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListBookServlet", value = "/list-books")
public class ListBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BookService bookService = new BookService();
        List<BookPOJO> books = bookService.listBooks();

        String booksJsonString = new Gson().toJson(books);

        PrintWriter out = response.getWriter();
        out.print(booksJsonString);
        out.flush();
    }
}
