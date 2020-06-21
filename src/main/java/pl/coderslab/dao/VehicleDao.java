package pl.coderslab.dao;

import pl.coderslab.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private static final String CREATE_VEHICLE_QUERY =
            "INSERT INTO VEHICLES (MARK, MODEL, PROD_YEAR, REG_NUMBER, NEXT_INSP_DATE, CUSTOMER_ID) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_VEHICLE_QUERY =
            "UPDATE VEHICLES SET MARK=?, MODEL=?, PROD_YEAR=?, REG_NUMBER=?, NEXT_INSP_DATE=?, CUSTOMER_ID=? WHERE VEHICLE_ID=?";
    private static final String DELETE_VEHICLE_QUERY =
            "DELETE FROM VEHICLES WHERE VEHICLE_ID=?";
    private static final String GET_ONE_VEHICLE_QUERY =
            "SELECT VEHICLE_ID, MARK, MODEL, PROD_YEAR, REG_NUMBER, NEXT_INSP_DATE, CUSTOMERS.CUSTOMER_ID, CONCAT(FIRSTNAME, ' ', LASTNAME) AS CUST_NAME\n" +
                    "FROM VEHICLES INNER JOIN CUSTOMERS ON VEHICLES.CUSTOMER_ID = CUSTOMERS.CUSTOMER_ID\n" +
                    "WHERE VEHICLE_ID=?";
    private static final String GET_CUST_VEHICLES_QUERY =
            "SELECT * FROM VEHICLES WHERE CUSTOMER_ID = ? ORDER BY MARK, MODEL, REG_NUMBER";
    private static final String GET_ALL_VEHICLES_QUERY =
            "SELECT VEHICLE_ID, MARK, MODEL, PROD_YEAR, REG_NUMBER, NEXT_INSP_DATE, CUSTOMERS.CUSTOMER_ID, CONCAT(FIRSTNAME, ' ', LASTNAME) AS CUST_NAME\n" +
                    "FROM VEHICLES INNER JOIN CUSTOMERS\n" +
                    "ON VEHICLES.CUSTOMER_ID = CUSTOMERS.CUSTOMER_ID\n" +
                    "ORDER BY MARK, MODEL, REG_NUMBER";

    public static Vehicle createVehicle(Vehicle vehicle) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat =
                     conn.prepareStatement(CREATE_VEHICLE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepStat.setString(1, vehicle.getMark());
            prepStat.setString(2, vehicle.getModel());
            prepStat.setInt(3, vehicle.getProdYear());
            prepStat.setString(4, vehicle.getRegNumber());
            prepStat.setString(5, vehicle.getNextInspDate().toString());
            prepStat.setInt(6, vehicle.getCustomerId());
            prepStat.executeUpdate();
            try (ResultSet resSet = prepStat.getGeneratedKeys()) {
                if (resSet.next()) {
                    vehicle.setVehicleId(resSet.getInt(1));
                }
                return vehicle;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean updateVehicle (Vehicle vehicle) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(UPDATE_VEHICLE_QUERY)) {
            prepStat.setString(1, vehicle.getMark());
            prepStat.setString(2, vehicle.getModel());
            prepStat.setInt(3, vehicle.getProdYear());
            prepStat.setString(4, vehicle.getRegNumber());
            prepStat.setString(5, vehicle.getNextInspDate().toString());
            prepStat.setInt(6, vehicle.getCustomerId());
            prepStat.setInt(7, vehicle.getVehicleId());
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteVehicle (int vehicleId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(DELETE_VEHICLE_QUERY)) {
            prepStat.setInt(1, vehicleId);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Vehicle getOneVehicle (int vehicleId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_ONE_VEHICLE_QUERY)) {
            prepStat.setInt(1, vehicleId);
            try (ResultSet resSet = prepStat.executeQuery()) {
                if (resSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(vehicleId); //VEHICLE_ID
                    vehicle.setMark(resSet.getString(2));//MARK,
                    vehicle.setModel(resSet.getString(3));//MODEL,
                    vehicle.setProdYear(resSet.getInt(4));//PROD_YEAR,
                    vehicle.setRegNumber(resSet.getString(5));//REG_NUMBER,
                    vehicle.setNextInspDate(resSet.getDate(6));//NEXT_INSP_DATE,
                    vehicle.setCustomerId(resSet.getInt(7));//CUSTOMER_ID,
                    vehicle.setCustName(resSet.getString(8));//CUST_NAME
                    return vehicle;
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

    public static List<Vehicle> getCustVehicles(int customerId) {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_CUST_VEHICLES_QUERY)) {
            prepStat.setInt(1, customerId);
            try (ResultSet resSet = prepStat.executeQuery()){
                while (resSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(resSet.getInt(1));
                    vehicle.setMark(resSet.getString(2));
                    vehicle.setModel(resSet.getString(3));
                    vehicle.setProdYear(resSet.getInt(4));
                    vehicle.setRegNumber(resSet.getString(5));
                    vehicle.setNextInspDate(resSet.getDate(6));
                    vehicle.setCustomerId(resSet.getInt(7));
                    list.add(vehicle);
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

    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
        PreparedStatement prepStat = conn.prepareStatement(GET_ALL_VEHICLES_QUERY);
        ResultSet resSet = prepStat.executeQuery()) {
            while (resSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(resSet.getInt(1));
                vehicle.setMark(resSet.getString(2));
                vehicle.setModel(resSet.getString(3));
                vehicle.setProdYear(resSet.getInt(4));
                vehicle.setRegNumber(resSet.getString(5));
                vehicle.setNextInspDate(resSet.getDate(6));
                vehicle.setCustomerId(resSet.getInt(7));
                vehicle.setCustName(resSet.getString(8));
                list.add(vehicle);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        }
    }
}
