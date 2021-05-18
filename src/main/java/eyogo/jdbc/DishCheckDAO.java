package eyogo.jdbc;

import eyogo.business.DishCheck;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DishCheckDAO implements DAO <DishCheck, Integer> {
    @Override
    public boolean create(DishCheck model) {
        String sqlCreate = "INSERT INTO dish_checkt VALUES (?, ?, ?)";
        try (PreparedStatement statement = RestaurantDB.getConnection().prepareStatement(sqlCreate)) {
            statement.setInt(1, model.getDishID());
            statement.setInt(2, model.getCheckID());
            statement.setInt(3, model.getCount());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public DishCheck read(Integer integer) {
        System.err.println("Not allowed operation through program. Read info of the check in MySQL Workbench.");
        return null;
    }

    @Override
    public boolean update(DishCheck dishCheck) {
        System.err.println("Not allowed operation.");
        return false;
    }

    @Override
    public boolean delete(DishCheck dishCheck) {
        System.err.println("Not allowed operation.");
        return false;
    }
}
