package repository.impl;

import mapper.EmployeeMapper;
import model.entity.Employee;
import util.JdbcConnection;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee, Integer> {

    public EmployeeRepository() {
        super(new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_EMPLOYEES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Employee employee = getMapper().map(result);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        // TODO: Implement a method which checks if an employee with the given id exists in the employees table
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!(resultSet == null)) {
                return resultSet.next();
            } else {
                return false;
            }
        } catch (SQLException exception) {
            System.out.println("This id : " + id + " does not exists in db");
        }
        return null;
    }

    @Override
    public Boolean save(Employee employee) {
        /*
         * TODO: Implement a method which adds an employee to the employees table
         *  If the employee exists then the method should instead update the employee
         */

        if (exists(employee.getId())) {
            try(Connection connection = JdbcConnection.connect();
                PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_EMPLOYEE)){
                statement.setString(1, employee.getLastName());
                statement.setString(2, employee.getFirstName());
                statement.setInt(3, employee.getId());
                int resultSet = statement.executeUpdate();
                if (resultSet > 0) {
                    System.out.println("Rows updated : " + resultSet);
                    System.out.println("Updated  First Name : " + employee.getFirstName() + " Last Name : " + employee.getLastName() + " for ID : " + employee.getId());
                    return true;
                }
            }
            catch (SQLException exception) {
                System.out.println("No rows updated on the db.");
            }

        } else if (!exists(employee.getId())){
            try(Connection connection = JdbcConnection.connect();
                PreparedStatement statement = connection.prepareStatement(Queries.INSERT_EMPLOYEE)) {
                statement.setString(1, employee.getLastName());
                statement.setString(2, employee.getFirstName());
                int resultSet = statement.executeUpdate();
                if (resultSet>0) {
                    System.out.println("Inserted  First Name : " + employee.getFirstName() + " Last Name : " + employee.getLastName());
                    return true;
                }
            }
            catch (SQLException exception) {
                System.out.println("No employee inserted in the db");
            }
        }

        return null;
    }

    @Override
    public Integer update(Employee employee) {
        /*
         * TODO: Implement a method which updates an employee with the given Employee instance
         *  The method should then return the number of updated records
         */
        return null;
    }

}
