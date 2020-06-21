package pl.coderslab.dao;

import pl.coderslab.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final String CREATE_EMPLOYEE_QUERY =
            "INSERT INTO EMPLOYEES (FIRSTNAME, LASTNAME, ADDRESS, PHONE, NOTE, HOURLY_WAGE) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE_QUERY =
            "UPDATE EMPLOYEES SET FIRSTNAME=?,LASTNAME=?,ADDRESS=?,PHONE=?,NOTE=?,HOURLY_WAGE=? WHERE EMPLOYEE_ID=?";
    private static final String DELETE_EMPLOYEE_QUERY =
            "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID=?";
    private static final String GET_ONE_EMPLOYEE_QUERY =
            "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID=?";
    private static final String GET_ALL_EMPLOYEES_QUERY =
            "SELECT * FROM EMPLOYEES ORDER BY LASTNAME, FIRSTNAME";

    public static Employee createEmployee(Employee employee) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat =
                     conn.prepareStatement(CREATE_EMPLOYEE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepStat.setString(1, employee.getFirstName());
            prepStat.setString(2, employee.getLastName());
            prepStat.setString(3, employee.getAddress());
            prepStat.setString(4, employee.getPhone());
            prepStat.setString(5, employee.getNote());
            prepStat.setBigDecimal(6, employee.getHourlyWage());
            prepStat.executeUpdate();
            try (ResultSet resSet = prepStat.getGeneratedKeys()) {
                if (resSet.next()) {
                    employee.setEmployeeId(resSet.getInt(1));
                }
                return employee;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean updateEmployee (Employee employee) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(UPDATE_EMPLOYEE_QUERY)) {
            prepStat.setString(1, employee.getFirstName());
            prepStat.setString(2, employee.getLastName());
            prepStat.setString(3, employee.getAddress());
            prepStat.setString(4, employee.getPhone());
            prepStat.setString(5, employee.getNote());
            prepStat.setBigDecimal(6, employee.getHourlyWage());
            prepStat.setInt(7, employee.getEmployeeId());
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEmployee (int employeeId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
            prepStat.setInt(1, employeeId);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Employee getOneEmployee (int employeeId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_ONE_EMPLOYEE_QUERY)) {
            prepStat.setInt(1, employeeId);
            try (ResultSet resSet = prepStat.executeQuery()) {
                if (resSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeId);
                    employee.setFirstName(resSet.getString(2));
                    employee.setLastName(resSet.getString(3));
                    employee.setAddress(resSet.getString(4));
                    employee.setPhone(resSet.getString(5));
                    employee.setNote(resSet.getString(6));
                    employee.setHourlyWage(resSet.getBigDecimal(7));
                    return employee;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public static List<Employee> getAllEmployees () {
        List<Employee> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
        PreparedStatement prepStat = conn.prepareStatement(GET_ALL_EMPLOYEES_QUERY);
        ResultSet resSet = prepStat.executeQuery()) {
            while (resSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resSet.getInt(1));
                employee.setFirstName(resSet.getString(2));
                employee.setLastName(resSet.getString(3));
                employee.setAddress(resSet.getString(4));
                employee.setPhone(resSet.getString(5));
                employee.setNote(resSet.getString(6));
                employee.setHourlyWage(resSet.getBigDecimal(7));
                list.add(employee);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        }
    }
}


