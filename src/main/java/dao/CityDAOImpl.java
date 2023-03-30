package dao;

import Config.Configuration;
import model.City;

import java.sql.*;

public class CityDAOImpl implements CityDAO {

    @Override
    public City getCityByID(int id) throws SQLException {
        try (final Connection connection = Configuration.connectToDB();

             PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE city_id = (?)")) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return new City(id, resultSet.getString("city_name"));
        }
    }

    @Override
    public int getCityIDByName(String name) throws SQLException {
        try (final Connection connection = Configuration.connectToDB();

             PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE city_name = (?)")) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return  resultSet.getInt("city_id");
        }
    }
}
