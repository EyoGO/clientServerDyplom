package eyogo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class WelcomeServlet extends HttpServlet {
 
    /**
     * handles HTTP GET request
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * handles HTTP POST request
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String paramWidth = request.getParameter("width");
        int width = Integer.parseInt(paramWidth);
 
        String paramHeight = request.getParameter("height");
        int height = Integer.parseInt(paramHeight);
 
        long area = width * height;
 
        PrintWriter writer = response.getWriter();
        writer.println("<html>Area of the rectangle is: " + area + "</html>");
        writer.flush();
 
    }
}