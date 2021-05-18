package eyogo.jdbc;

import eyogo.business.Check;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CheckDAO implements DAO <Check, Integer> {
    @Override
    public boolean create(Check model) {
        String sqlCreate = "INSERT INTO checkt VALUES (?, ?)";
        try (PreparedStatement statement = RestaurantDB.getConnection().prepareStatement(sqlCreate)) {
            statement.setInt(1, model.getId());
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+3:00"));
            statement.setTimestamp(2, model.getTimestamp(), calendar);
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
