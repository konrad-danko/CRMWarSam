package pl.coderslab.dao;

import pl.coderslab.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static final String CREATE_ORDER_QUERY =
            "INSERT INTO ORDERS (VEHICLE_ID, PROBLEM_DESCR, EMPLOYEE_ID, ORDR_CREAT_DATE, PLAN_REP_DATE,\n" +
            "ACTU_REP_DATE, MAN_HOURS, HOURLY_WAGE, PARTS_COST, REPAIR_COST, REPAIR_DESCR) \n" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ORDER_QUERY =
            "UPDATE ORDERS SET VEHICLE_ID=?, PROBLEM_DESCR=?, EMPLOYEE_ID=?, ORDR_CREAT_DATE=?, PLAN_REP_DATE=?,\n" +
            "ACTU_REP_DATE=?, MAN_HOURS=?, HOURLY_WAGE=?, PARTS_COST=?, REPAIR_COST=?, REPAIR_DESCR=?, STAT_ID=?\n" +
            "WHERE ORDER_ID=?";
    private static final String DELETE_ORDER_QUERY =
            "DELETE FROM ORDERS WHERE ORDER_ID=?";
    private static final String GET_ONE_ORDER_QUERY =
            "SELECT ORDER_ID, VEHICLES.VEHICLE_ID, CONCAT(MARK,' ',MODEL,', ',REG_NUMBER) AS VEHICLE_NAME,\n" +
            "PROBLEM_DESCR, EMPLOYEES.EMPLOYEE_ID, CONCAT(FIRSTNAME,' ', LASTNAME) AS EMPLOYEE_NAME, ORDR_CREAT_DATE,\n" +
            "PLAN_REP_DATE, ACTU_REP_DATE, MAN_HOURS, ORDERS.HOURLY_WAGE, PARTS_COST, REPAIR_COST, REPAIR_DESCR,\n" +
            "STATUSES.STAT_ID, STATUS AS STAT_NAME\n" +
            "FROM ORDERS\n" +
            "INNER JOIN EMPLOYEES ON ORDERS.EMPLOYEE_ID = EMPLOYEES.EMPLOYEE_ID\n" +
            "INNER JOIN STATUSES ON ORDERS.STAT_ID = STATUSES.STAT_ID\n" +
            "INNER JOIN VEHICLES ON ORDERS.VEHICLE_ID = VEHICLES.VEHICLE_ID\n" +
            "WHERE ORDER_ID=?";
    private static final String GET_VEH_ORDERS_QUERY =
            "SELECT ORDER_ID, VEHICLES.VEHICLE_ID, CONCAT(MARK,' ',MODEL,', ',REG_NUMBER) AS VEHICLE_NAME,\n" +
            "PROBLEM_DESCR, EMPLOYEES.EMPLOYEE_ID, CONCAT(FIRSTNAME,' ', LASTNAME) AS EMPLOYEE_NAME, ORDR_CREAT_DATE,\n" +
            "PLAN_REP_DATE, ACTU_REP_DATE, MAN_HOURS, ORDERS.HOURLY_WAGE, PARTS_COST, REPAIR_COST, REPAIR_DESCR,\n" +
            "STATUSES.STAT_ID, STATUS AS STAT_NAME\n" +
            "FROM ORDERS\n" +
            "INNER JOIN EMPLOYEES ON ORDERS.EMPLOYEE_ID = EMPLOYEES.EMPLOYEE_ID\n" +
            "INNER JOIN STATUSES ON ORDERS.STAT_ID = STATUSES.STAT_ID\n" +
            "INNER JOIN VEHICLES ON ORDERS.VEHICLE_ID = VEHICLES.VEHICLE_ID\n" +
            "WHERE VEHICLES.VEHICLE_ID=?\n" +
            "ORDER BY ACTU_REP_DATE DESC";
    private static final String GET_EMPL_ORDERS_QUERY =
            "SELECT ORDER_ID, VEHICLES.VEHICLE_ID, CONCAT(MARK,' ',MODEL,' ',REG_NUMBER) AS VEHICLE_NAME,\n" +
            "PROBLEM_DESCR, EMPLOYEES.EMPLOYEE_ID, CONCAT(FIRSTNAME,' ', LASTNAME) AS EMPLOYEE_NAME, ORDR_CREAT_DATE,\n" +
            "PLAN_REP_DATE, ACTU_REP_DATE, MAN_HOURS, ORDERS.HOURLY_WAGE, PARTS_COST, REPAIR_COST, REPAIR_DESCR,\n" +
            "STATUSES.STAT_ID, STATUS AS STAT_NAME\n" +
            "FROM ORDERS\n" +
            "INNER JOIN EMPLOYEES ON ORDERS.EMPLOYEE_ID = EMPLOYEES.EMPLOYEE_ID\n" +
            "INNER JOIN STATUSES ON ORDERS.STAT_ID = STATUSES.STAT_ID\n" +
            "INNER JOIN VEHICLES ON ORDERS.VEHICLE_ID = VEHICLES.VEHICLE_ID\n" +
            "WHERE ORDERS.EMPLOYEE_ID = ? AND ORDERS.STAT_ID = 3\n" +
            "ORDER BY ORDR_CREAT_DATE DESC";
    private static final String GET_CURR_ORDERS_QUERY =
            "SELECT ORDER_ID, VEHICLES.VEHICLE_ID, CONCAT(MARK,' ',MODEL,' ',REG_NUMBER) AS VEHICLE_NAME,\n" +
            "PROBLEM_DESCR, EMPLOYEES.EMPLOYEE_ID, CONCAT(FIRSTNAME,' ', LASTNAME) AS EMPLOYEE_NAME, ORDR_CREAT_DATE,\n" +
            "PLAN_REP_DATE, ACTU_REP_DATE, MAN_HOURS, ORDERS.HOURLY_WAGE, PARTS_COST, REPAIR_COST, REPAIR_DESCR,\n" +
            "STATUSES.STAT_ID, STATUS AS STAT_NAME\n" +
            "FROM ORDERS\n" +
            "INNER JOIN EMPLOYEES ON ORDERS.EMPLOYEE_ID = EMPLOYEES.EMPLOYEE_ID\n" +
            "INNER JOIN STATUSES ON ORDERS.STAT_ID = STATUSES.STAT_ID\n" +
            "INNER JOIN VEHICLES ON ORDERS.VEHICLE_ID = VEHICLES.VEHICLE_ID\n" +
            "WHERE ORDERS.STAT_ID = 3\n" +
            "ORDER BY EMPLOYEES.LASTNAME, ACTU_REP_DATE DESC";
    private static final String GET_ALL_ORDERS_QUERY =
            "SELECT ORDER_ID, VEHICLES.VEHICLE_ID, CONCAT(MARK,' ',MODEL,', ',REG_NUMBER) AS VEHICLE_NAME,\n" +
            "PROBLEM_DESCR, EMPLOYEES.EMPLOYEE_ID, CONCAT(FIRSTNAME,' ', LASTNAME) AS EMPLOYEE_NAME, ORDR_CREAT_DATE,\n" +
            "PLAN_REP_DATE, ACTU_REP_DATE, MAN_HOURS, ORDERS.HOURLY_WAGE, PARTS_COST, REPAIR_COST, REPAIR_DESCR,\n" +
            "STATUSES.STAT_ID, STATUS AS STAT_NAME\n" +
            "FROM ORDERS\n" +
            "INNER JOIN EMPLOYEES ON ORDERS.EMPLOYEE_ID = EMPLOYEES.EMPLOYEE_ID\n" +
            "INNER JOIN STATUSES ON ORDERS.STAT_ID = STATUSES.STAT_ID\n" +
            "INNER JOIN VEHICLES ON ORDERS.VEHICLE_ID = VEHICLES.VEHICLE_ID\n" +
            "ORDER BY ACTU_REP_DATE DESC";

    public static Order createOrder (Order order) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat =
                     conn.prepareStatement(CREATE_ORDER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepStat.setInt(1, order.getVehicleId()); //VEHICLE_ID,
            prepStat.setString(2, order.getProblemDescr());//PROBLEM_DESCR,
            prepStat.setInt(3, order.getEmployeeId());//EMPLOYEE_ID,
            prepStat.setString(4, order.getOrdrCreatDate().toString());//ORDR_CREAT_DATE,
            prepStat.setString(5, order.getPlanRepDate().toString());//PLAN_REP_DATE,
            prepStat.setString(6, order.getActuRepDate().toString());//ACTU_REP_DATE,
            prepStat.setBigDecimal(7, order.getManHours());//MAN_HOURS,
            prepStat.setBigDecimal(8, order.getHourlyWage());//HOURLY_WAGE,
            prepStat.setBigDecimal(9, order.getPartsCost());//PARTS_COST,
            prepStat.setBigDecimal(10, order.getRepairCost());//REPAIR_COST,
            prepStat.setString(11, order.getRepairDescr());//REPAIR_DESCR
            prepStat.executeUpdate();
            try (ResultSet resSet = prepStat.getGeneratedKeys()) {
                if (resSet.next()) {
                    order.setOrderId(resSet.getInt(1));
                }
                return order;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean updateOrder (Order order) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(UPDATE_ORDER_QUERY)) {
            prepStat.setInt(1, order.getVehicleId());//VEHICLE_ID=1,
            prepStat.setString(2, order.getProblemDescr());//PROBLEM_DESCR=2
            prepStat.setInt(3, order.getEmployeeId());//EMPLOYEE_ID=3
            prepStat.setString(4, order.getOrdrCreatDate().toString());//ORDR_CREAT_DATE=4
            prepStat.setString(5, order.getPlanRepDate().toString());//PLAN_REP_DATE=5
            prepStat.setString(6, order.getActuRepDate().toString());//ACTU_REP_DATE=6
            prepStat.setBigDecimal(7, order.getManHours());//MAN_HOURS=7
            prepStat.setBigDecimal(8, order.getHourlyWage());//HOURLY_WAGE=8
            prepStat.setBigDecimal(9, order.getPartsCost());//PARTS_COST=9
            prepStat.setBigDecimal(10, order.getRepairCost());//REPAIR_COST=10
            prepStat.setString(11, order.getRepairDescr());//REPAIR_DESCR=11
            prepStat.setInt(12, order.getStatId());//STAT_ID=12
            prepStat.setInt(13, order.getOrderId());//WHERE ORDER_ID=13
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteOrder (int orderId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(DELETE_ORDER_QUERY)) {
            prepStat.setInt(1, orderId);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Order getOneOrder (int orderId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_ONE_ORDER_QUERY)) {
            prepStat.setInt(1, orderId);    //WHERE ORDER_ID=?
            try (ResultSet resSet = prepStat.executeQuery()) {
                if (resSet.next()) {
                    Order order = new Order();
                    order.setOrderId(orderId); //ORDER_ID,
                    order.setVehicleId(resSet.getInt(2));//VEHICLES.VEHICLE_ID,
                    order.setVehicleName(resSet.getString(3));//VEHICLE_NAME,
                    order.setProblemDescr(resSet.getString(4));//PROBLEM_DESCR,
                    order.setEmployeeId(resSet.getInt(5));//EMPLOYEES.EMPLOYEE_ID,
                    order.setEmployeeName(resSet.getString(6));//EMPLOYEE_NAME,
                    order.setOrdrCreatDate(resSet.getDate(7));//ORDR_CREAT_DATE,
                    order.setPlanRepDate(resSet.getDate(8));//PLAN_REP_DATE,
                    order.setActuRepDate(resSet.getDate(9));//ACTU_REP_DATE,
                    order.setManHours(resSet.getBigDecimal(10));//MAN_HOURS,
                    order.setHourlyWage(resSet.getBigDecimal(11));//ORDERS.HOURLY_WAGE,
                    order.setPartsCost(resSet.getBigDecimal(12));//PARTS_COST,
                    order.setRepairCost(resSet.getBigDecimal(13));//REPAIR_COST,
                    order.setRepairDescr(resSet.getString(14));//REPAIR_DESCR,
                    order.setStatId(resSet.getInt(15));//STATUSES.STAT_ID,
                    order.setStatName(resSet.getString(16));//STAT_NAME
                    return order;
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

    public static List<Order> getVehOrders (int vehicleId) {
        List<Order> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_VEH_ORDERS_QUERY)) {
            prepStat.setInt(1, vehicleId); //WHERE VEHICLE_ID=?
            try (ResultSet resSet = prepStat.executeQuery()){
                while (resSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resSet.getInt(1));//ORDER_ID
                    order.setVehicleId(vehicleId);//VEHICLES.VEHICLE_ID,
                    order.setVehicleName(resSet.getString(3));//VEHICLE_NAME,
                    order.setProblemDescr(resSet.getString(4));//PROBLEM_DESCR,
                    order.setEmployeeId(resSet.getInt(5));//EMPLOYEES.EMPLOYEE_ID,
                    order.setEmployeeName(resSet.getString(6));//EMPLOYEE_NAME,
                    order.setOrdrCreatDate(resSet.getDate(7));//ORDR_CREAT_DATE,
                    order.setPlanRepDate(resSet.getDate(8));//PLAN_REP_DATE,
                    order.setActuRepDate(resSet.getDate(9));//ACTU_REP_DATE,
                    order.setManHours(resSet.getBigDecimal(10));//MAN_HOURS,
                    order.setHourlyWage(resSet.getBigDecimal(11));//ORDERS.HOURLY_WAGE,
                    order.setPartsCost(resSet.getBigDecimal(12));//PARTS_COST,
                    order.setRepairCost(resSet.getBigDecimal(13));//REPAIR_COST,
                    order.setRepairDescr(resSet.getString(14));//REPAIR_DESCR,
                    order.setStatId(resSet.getInt(15));//STATUSES.STAT_ID,
                    order.setStatName(resSet.getString(16));//STAT_NAME
                    list.add(order);
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

    public static List<Order> getEmplOrders (int employeeId) {
        List<Order> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_EMPL_ORDERS_QUERY)) {
            prepStat.setInt(1, employeeId); // WHERE ORDERS.EMPLOYEE_ID = ?
            try (ResultSet resSet = prepStat.executeQuery()){
                while (resSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resSet.getInt(1)); // 1 ORDER_ID,
                    order.setVehicleId(resSet.getInt(2)); // 2 VEHICLE_ID,
                    order.setVehicleName(resSet.getString(3)); // 3 VEHICLE_NAME,
                    order.setProblemDescr(resSet.getString(4)); // 4 PROBLEM_DESCR,
                    order.setEmployeeId(resSet.getInt(5)); // 5 EMPLOYEE_ID,
                    order.setEmployeeName(resSet.getString(6)); // 6 EMPLOYEE_NAME,
                    order.setOrdrCreatDate(resSet.getDate(7)); // 7 ORDR_CREAT_DATE,
                    order.setPlanRepDate(resSet.getDate(8)); // 8 PLAN_REP_DATE,
                    order.setActuRepDate(resSet.getDate(9));// 9 ACTU_REP_DATE,
                    order.setManHours(resSet.getBigDecimal(10)); // 10 MAN_HOURS,
                    order.setHourlyWage(resSet.getBigDecimal(11)); // 11 HOURLY_WAGE,
                    order.setPartsCost(resSet.getBigDecimal(12)); // 12 PARTS_COST,
                    order.setRepairCost(resSet.getBigDecimal(13)); // 13 REPAIR_COST,
                    order.setRepairDescr(resSet.getString(14));  // 14 REPAIR_DESCR,
                    order.setStatId(resSet.getInt(15)); // 15 STAT_ID,
                    order.setStatName(resSet.getString(16)); // 16 STAT_NAME
                    list.add(order);
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

    public static List<Order> getCurrOrders() {
        List<Order> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_CURR_ORDERS_QUERY);
             ResultSet resSet = prepStat.executeQuery()) {
            while (resSet.next()) {
                Order order = new Order();
                order.setOrderId(resSet.getInt(1));//ORDER_ID
                order.setVehicleId(resSet.getInt(2));//VEHICLES.VEHICLE_ID,
                order.setVehicleName(resSet.getString(3));//VEHICLE_NAME,
                order.setProblemDescr(resSet.getString(4));//PROBLEM_DESCR,
                order.setEmployeeId(resSet.getInt(5));//EMPLOYEES.EMPLOYEE_ID,
                order.setEmployeeName(resSet.getString(6));//EMPLOYEE_NAME,
                order.setOrdrCreatDate(resSet.getDate(7));//ORDR_CREAT_DATE,
                order.setPlanRepDate(resSet.getDate(8));//PLAN_REP_DATE,
                order.setActuRepDate(resSet.getDate(9));//ACTU_REP_DATE,
                order.setManHours(resSet.getBigDecimal(10));//MAN_HOURS,
                order.setHourlyWage(resSet.getBigDecimal(11));//ORDERS.HOURLY_WAGE,
                order.setPartsCost(resSet.getBigDecimal(12));//PARTS_COST,
                order.setRepairCost(resSet.getBigDecimal(13));//REPAIR_COST,
                order.setRepairDescr(resSet.getString(14));//REPAIR_DESCR,
                order.setStatId(resSet.getInt(15));//STATUSES.STAT_ID,
                order.setStatName(resSet.getString(16));//STAT_NAME
                list.add(order);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        }
    }

    public static List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_ALL_ORDERS_QUERY);
             ResultSet resSet = prepStat.executeQuery()) {
            while (resSet.next()) {
                Order order = new Order();
                order.setOrderId(resSet.getInt(1));//ORDER_ID
                order.setVehicleId(resSet.getInt(2));//VEHICLES.VEHICLE_ID,
                order.setVehicleName(resSet.getString(3));//VEHICLE_NAME,
                order.setProblemDescr(resSet.getString(4));//PROBLEM_DESCR,
                order.setEmployeeId(resSet.getInt(5));//EMPLOYEES.EMPLOYEE_ID,
                order.setEmployeeName(resSet.getString(6));//EMPLOYEE_NAME,
                order.setOrdrCreatDate(resSet.getDate(7));//ORDR_CREAT_DATE,
                order.setPlanRepDate(resSet.getDate(8));//PLAN_REP_DATE,
                order.setActuRepDate(resSet.getDate(9));//ACTU_REP_DATE,
                order.setManHours(resSet.getBigDecimal(10));//MAN_HOURS,
                order.setHourlyWage(resSet.getBigDecimal(11));//ORDERS.HOURLY_WAGE,
                order.setPartsCost(resSet.getBigDecimal(12));//PARTS_COST,
                order.setRepairCost(resSet.getBigDecimal(13));//REPAIR_COST,
                order.setRepairDescr(resSet.getString(14));//REPAIR_DESCR,
                order.setStatId(resSet.getInt(15));//STATUSES.STAT_ID,
                order.setStatName(resSet.getString(16));//STAT_NAME
                list.add(order);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        }
    }
}



