package edu.unbosque.servlets.library;

import com.google.gson.Gson;
import edu.unbosque.services.LibraryService;
import edu.unbosque.servlets.pojos.AssociationPOJO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListAssociationsServlet", value = "/listAssociationsServlet")
public class ListAssociationsServlet extends HttpServlet {
    /**
     * Manages the list operation on the service, get a list of LibraryPOJO and print the information to the .jsp on JSON
     * format
     *
     * @param request, the request of the client
     * @param response the response of the client
     * @throws IOException input and output exceptions
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        LibraryService libraryService = new LibraryService();
        List<AssociationPOJO> associations = libraryService.listAssociations();

        String associationsJSON = new Gson().toJson(associations);

        PrintWriter out = response.getWriter();
        out.print(associationsJSON);
        out.flush();
    }
}
