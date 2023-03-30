import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Employee;

import java.sql.*;

public class Application {

    private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public static void main(String[] args) throws SQLException {
        employeeDAO.createEmployee(new Employee("user", "user", "M", 20, "Moscow"));
        System.out.println(employeeDAO.getEmployeeByID(29));

        employeeDAO.updateEmployeeById(new Employee("new_user", "new_user", "F", 30, "Samara"), 28);
        System.out.println(employeeDAO.readAll());

        employeeDAO.deleteEmployeeById(29);
        System.out.println(employeeDAO.readAll());

    }
}
