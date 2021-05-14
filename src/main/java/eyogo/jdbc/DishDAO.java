package eyogo.jdbc;

import eyogo.business.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishDAO implements DAO <Dish, Integer>{

    @Override
    public boolean create(Dish model) {
        System.err.println("Not allowed operation through program. Add dishes in MySQL Workbench.");
        return false;
    }

    @Override
    public Dish read(Integer integer) {
        Dish result = null;
        String sqlRead = "SELECT * FROM dish WHERE id=?";
        try (PreparedStatement statement = RestaurantDB.getConnection().prepareStatement(sqlRead)) {
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                result = new Dish(id, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Dish dish) {
        System.err.println("Not allowed operation through program. Update dishes in MySQL Workbench.");
        return false;
    }

    @Override
    public boolean delete(Dish dish) {
        System.err.println("Not allowed operation through program. Delete dishes in MySQL Workbench.");
        return false;
    }
}
