package pl.coderslab.dao;

import pl.coderslab.model.Customer;
import pl.coderslab.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static final String CREATE_CUSTOMER_QUERY =
            "INSERT INTO CUSTOMERS (FIRSTNAME, LASTNAME, BIRTH_DATE, PHONE, EMAIL) VALUES (?,?,?,?,?)";
    private static final String UPDATE_CUSTOMER_QUERY =
            "UPDATE CUSTOMERS SET FIRSTNAME=?, LASTNAME=?, BIRTH_DATE=?, PHONE=?, EMAIL=? WHERE CUSTOMER_ID=?";
    private static final String DELETE_CUSTOMER_QUERY =
            "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID=?";
    private static final String GET_ONE_CUSTOMER_QUERY =
            "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID=?";
    private static final String GET_SOME_CUSTOMERS_QUERY =
            "SELECT * FROM CUSTOMERS\n" +
                    "WHERE FIRSTNAME LIKE ?\n" +
                    "AND LASTNAME LIKE ?\n" +
                    "ORDER BY LASTNAME, FIRSTNAME, BIRTH_DATE";
    private static final String GET_ALL_CUSTOMERS_QUERY =
            "SELECT * FROM CUSTOMERS ORDER BY LASTNAME, FIRSTNAME, BIRTH_DATE";


    public static Customer createCustomer(Customer customer) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat =
                     conn.prepareStatement(CREATE_CUSTOMER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepStat.setString(1, customer.getFirstName());
            prepStat.setString(2, customer.getLastName());
            prepStat.setString(3, customer.getBirthDate().toString());
            prepStat.setString(4, customer.getPhone());
            prepStat.setString(5, customer.getEmail());
            prepStat.executeUpdate();
            try (ResultSet resSet = prepStat.getGeneratedKeys()) {
                if (resSet.next()) {
                    customer.setCustomerId(resSet.getInt(1));
                }
                return customer;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean updateCustomer (Customer customer) {
        try (Connection conn = DbUtil.getConn();
        PreparedStatement prepStat = conn.prepareStatement(UPDATE_CUSTOMER_QUERY)) {
            prepStat.setString(1, customer.getFirstName());
            prepStat.setString(2, customer.getLastName());
            prepStat.setString(3, customer.getBirthDate().toString());
            prepStat.setString(4, customer.getPhone());
            prepStat.setString(5, customer.getEmail());
            prepStat.setInt(6, customer.getCustomerId());
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCustomer (int customerId) {
        try (Connection conn = DbUtil.getConn();
        PreparedStatement prepStat = conn.prepareStatement(DELETE_CUSTOMER_QUERY)) {
            prepStat.setInt(1, customerId);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Customer getOneCustomer (int customerId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_ONE_CUSTOMER_QUERY)) {
            prepStat.setInt(1, customerId);
            try (ResultSet resSet = prepStat.executeQuery()) {
                if (resSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);
                    customer.setFirstName(resSet.getString(2));
                    customer.setLastName(resSet.getString(3));
                    customer.setBirthDate(resSet.getDate(4));
                    customer.setPhone(resSet.getString(5));
                    customer.setEmail(resSet.getString(6));
                    return customer;
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

    public static List<Customer> getSomeCustomers(String firstName, String lastName) {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_SOME_CUSTOMERS_QUERY)) {
            prepStat.setString(1, "%"+firstName+"%");
            prepStat.setString(2, "%"+lastName+"%");
            try (ResultSet resSet = prepStat.executeQuery()){
                while (resSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(resSet.getInt(1));
                    customer.setFirstName(resSet.getString(2));
                    customer.setLastName(resSet.getString(3));
                    customer.setBirthDate(resSet.getDate(4));
                    customer.setPhone(resSet.getString(5));
                    customer.setEmail(resSet.getString(6));
                    list.add(customer);
                }
                return list;
            } catch (SQLException ex){
                ex.printStackTrace();
                return list;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        }
    }

    public static List<Customer> getAllCustomers () {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_ALL_CUSTOMERS_QUERY);
             ResultSet resSet = prepStat.executeQuery()) {
            while (resSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resSet.getInt(1));
                customer.setFirstName(resSet.getString(2));
                customer.setLastName(resSet.getString(3));
                customer.setBirthDate(resSet.getDate(4));
                customer.setPhone(resSet.getString(5));
                customer.setEmail(resSet.getString(6));
                list.add(customer);
            }
            return list;
        } catch (SQLException ex){
            ex.printStackTrace();
            return list;
        }
    }
}
