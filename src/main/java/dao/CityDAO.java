package dao;

import model.City;

import java.sql.SQLException;

public interface CityDAO {
    City getCityByID(int id) throws SQLException;

    int getCityIDByName(String name) throws SQLException;
}
