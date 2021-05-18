package eyogo.servlet;

import com.mysql.cj.protocol.result.AbstractResultsetRow;
import eyogo.business.Dish;
import eyogo.business.DishCounter;
import eyogo.jdbc.DishDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RecognizeServlet extends HttpServlet {

    private static Map<String, ArrayList<DishCounter>> terminals = new HashMap<>();
    private static DishDAO dishDAO = new DishDAO();

    /**
     * handles HTTP GET request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            request.getRequestDispatcher("/recognize.jsp").forward(request, response);
            String sessionID = request.getSession().getId();
            if (!terminals.containsKey(sessionID)) {
                terminals.put(sessionID, new ArrayList<>());
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles HTTP POST request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int dishID = new Random().nextInt(3) + 1;
        String sessionID = request.getSession().getId();
        ArrayList<DishCounter> currentDishes = terminals.get(sessionID);
        Dish dish = dishDAO.read(dishID);
        boolean found = false;
        for (int i = 0; i < currentDishes.size(); i++) {
            DishCounter dishCounterExample = currentDishes.get(i);
            if (dishCounterExample.getDish().equals(dish)) {
                dishCounterExample.setCount(dishCounterExample.getCount()+1);
                found = true;
                break;
            }
        }
        if (!found) {
            currentDishes.add(new DishCounter(dish, 1));
        }
        request.setAttribute("dishes", terminals.get(sessionID));

        request.getRequestDispatcher("/recognize.jsp").forward(request, response);
    }

    public static Map<String, ArrayList<DishCounter>> getTerminals() {
        return terminals;
    }
}
