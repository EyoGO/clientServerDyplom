package eyogo.servlet;

import eyogo.business.*;
import eyogo.jdbc.CheckDAO;
import eyogo.jdbc.DishCheckDAO;
import eyogo.jdbc.PriceDAO;
import eyogo.jdbc.RestaurantDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SubmitServlet extends HttpServlet {

    private static int nextCheckID = 0;
    static {
        Statement statement = null;
        try {
            statement = RestaurantDB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT max(id) FROM checkt");
            if (rs.next()) nextCheckID = rs.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * handles HTTP GET request
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (RecognizeServlet.getTerminals().get(request.getSession().getId()).isEmpty()) {
                response.sendRedirect("/dyplom/recognize");
                return;
            }
            PriceDAO priceDAO = new PriceDAO();
            ArrayList<DishCounter> terminalDishCounters = RecognizeServlet.getTerminals().get(request.getSession().getId());
            ArrayList<CheckModel> checkModels = new ArrayList<>();
            for (DishCounter dishCounter: terminalDishCounters) {
                checkModels.add(new CheckModel(dishCounter, priceDAO.read(dishCounter.getDish().getId())));
            }
            request.setAttribute("checks", checkModels);
            request.getRequestDispatcher("/submit.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * handles HTTP POST request
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String res = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (res.equals("Підтвердити покупку")) {
            Connection connection = RestaurantDB.getConnection();
            try {
                connection.setAutoCommit(false);
                PriceDAO priceDAO = new PriceDAO();
                CheckDAO checkDAO = new CheckDAO();
                DishCheckDAO dishCheckDAO = new DishCheckDAO();
                ArrayList<DishCounter> terminalDishCounters = RecognizeServlet.getTerminals().get(request.getSession().getId());
                ArrayList<CheckModel> checkModels = new ArrayList<>();
                for (DishCounter dishCounter: terminalDishCounters) {
                    checkModels.add(new CheckModel(dishCounter, priceDAO.read(dishCounter.getDish().getId())));
                }
                checkDAO.create(new Check(nextCheckID, new Timestamp(System.currentTimeMillis())));
                for (CheckModel check: checkModels) {
                    DishCounter dishCounter = check.getDishCounter();
                    Dish dish = dishCounter.getDish();
                    dishCheckDAO.create(new DishCheck(dish.getId(), nextCheckID, dishCounter.getCount()));
                }
                connection.commit();
                nextCheckID++;
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RecognizeServlet.getTerminals().put(request.getSession().getId(), new ArrayList<>());
        response.sendRedirect("/dyplom/welcome");
    }
}