package eyogo.jdbc;

import eyogo.business.Check;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckDAO implements DAO <Check, Integer> {
    @Override
    public boolean create(Check model) {
        String sqlCreate = "INSERT INTO check VALUES (?, ?)";
        try (PreparedStatement statement = RestaurantDB.getConnection().prepareStatement(sqlCreate)) {
            statement.setInt(1, model.getId());
            statement.setDate(2, model.getDate());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Check read(Integer integer) {
        System.err.println("Not allowed operation through program. Read checks in MySQL Workbench.");
        return null;
    }

    @Override
    public boolean update(Check check) {
        System.err.println("Not allowed operation.");
        return false;
    }

    @Override
    public boolean delete(Check check) {
        System.err.println("Not allowed operation.");
        return false;
    }
}
