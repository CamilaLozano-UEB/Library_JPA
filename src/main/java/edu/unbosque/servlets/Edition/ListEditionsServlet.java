package edu.unbosque.servlets.Edition;

import com.google.gson.Gson;
import edu.unbosque.services.BookService;
import edu.unbosque.services.EditionService;
import edu.unbosque.servlets.pojos.BookPOJO;
import edu.unbosque.servlets.pojos.EditionPOJO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListEditionsServlet", value = "/list-edition")
public class ListEditionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        EditionService editionService = new EditionService();
        List<EditionPOJO> editionPOJOS = editionService.listEditions();

        String booksJsonString = new Gson().toJson(editionPOJOS);

        PrintWriter out = response.getWriter();
        out.print(booksJsonString);
        out.flush();
    }
}
