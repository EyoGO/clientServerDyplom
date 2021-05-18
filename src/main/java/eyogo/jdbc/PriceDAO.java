package eyogo.jdbc;

import eyogo.business.Price;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Read operation searches most up to date price for specified Integer dishID
public class PriceDAO implements DAO <Price, Integer>{
    @Override
    public boolean create(Price model) {
        System.err.println("Not allowed operation through program. Set prices in MySQL Workbench.");
        return false;
    }

    @Override
    public Price read(Integer integer) {
        Price result = null;
        String sqlRead = "SELECT * FROM price WHERE dish_id=? ORDER BY price_date DESC LIMIT 1";
        try (PreparedStatement statement = RestaurantDB.getConnection().prepareStatement(sqlRead)) {
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int dishID = resultSet.getInt(1);
                Date name = resultSet.getDate(2);
                double value = resultSet.getDouble(3);
                result = new Price(dishID, name, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Price price) {
        System.err.println("Not allowed operation.");
        return false;
    }

    @Override
    public boolean delete(Price price) {
        System.err.println("Not allowed operation.");
        return false;
    }
}
