package edu.unbosque.servlets.author;

import com.google.gson.Gson;
import edu.unbosque.services.AuthorService;
import edu.unbosque.servlets.pojos.AuthorPOJO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "listAuthorsServlet", value = "/list-authors")
public class ListAuthorsServlet extends HttpServlet {

    /**
     * Manages the list operation on the service, get a list of authorPOJO and print the information to the .jsp on JSON
     * format
     *
     * @param request  the request of the client
     * @param response the response to the client
     * @throws IOException input and output exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        AuthorService authorService = new AuthorService();
        List<AuthorPOJO> authors = authorService.listAuthors();

        String authorsJsonString = new Gson().toJson(authors);

        PrintWriter out = response.getWriter();
        out.print(authorsJsonString);
        out.flush();

    }

}
