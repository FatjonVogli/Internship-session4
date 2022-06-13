package util;

import model.entity.Employee;

public final class Queries extends Employee {

    private Queries() {}

    public static final String FIND_ALL_EMPLOYEES = "SELECT * FROM employees;";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";


    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET lastName=?, firstName=? WHERE employeeNumber=?;";


    public static final String INSERT_EMPLOYEE = "INSERT INTO employees (lastName,firstName) VALUES (?,?);";


}
