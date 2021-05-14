package edu.unbosque.servlets.library;

import com.google.gson.Gson;
import edu.unbosque.services.LibraryService;
import edu.unbosque.servlets.pojos.LibraryPOJO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "listLibrariesServlet", value = "/list-libraries")
public class ListLibrariesServlet extends HttpServlet {
    /**
     * The data of the forms is captured and after being validated they are redirected to the services
     *
     * @param request, the request of the client
     * @param response the response of the client
     * @throws IOException input and output exceptions
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        LibraryService libraryService = new LibraryService();
        List<LibraryPOJO> libraries = libraryService.listLibraries();

        String librariesJsonString = new Gson().toJson(libraries);

        PrintWriter out = response.getWriter();
        out.print(librariesJsonString);
        out.flush();

    }

}
