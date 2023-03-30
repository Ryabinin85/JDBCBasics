package dao;

import Config.Configuration;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    CityDAOImpl cityDAO = new CityDAOImpl();

    @Override
    public void createEmployee(Employee employee) throws SQLException {
        int cityIDByName = cityDAO.getCityIDByName(employee.getCityName());
        String sqlQuery = "INSERT INTO employee (first_name, last_name, gender, age, city_id)\n" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Configuration.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, cityIDByName);
            statement.executeUpdate();
        }
    }

    @Override
    public Employee getEmployeeByID(int id) throws SQLException {
        try (final Connection connection = Configuration.connectToDB();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return new Employee(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("gender"),
                    resultSet.getInt("age"),
                    cityDAO.getCityByID(resultSet.getInt("city_id")).getCityName());
        }
    }

    @Override
    public List<Employee> readAll() throws SQLException {

        List<Employee> employeeList = new ArrayList<>();

        try (final Connection connection = Configuration.connectToDB();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                employeeList.add(new Employee(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        cityDAO.getCityByID(resultSet.getInt("city_id")).getCityName()));
            }
        }
        return employeeList;
    }

    @Override
    public void updateEmployeeById(Employee employee, int id) throws SQLException {

        int cityIDByName = cityDAO.getCityIDByName(employee.getCityName());
        String sqlQuery = "UPDATE employee SET first_name = (?), last_name = (?), gender = (?), age = (?), city_id = (?) " +
                "WHERE id= (?);";

        try (final Connection connection = Configuration.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(6, id);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, cityIDByName);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteEmployeeById(int id) throws SQLException {

        try (final Connection connection = Configuration.connectToDB();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
