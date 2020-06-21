package pl.coderslab.dao;

import pl.coderslab.model.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao {

    private static final String GET_TOTAL_ORDERS_QUERY =
            "SELECT CONCAT(FIRSTNAME,' ', LASTNAME) AS EMPLOYEE_NAME, SUM(MAN_HOURS), SUM(REPAIR_COST)\n" +
                    "FROM ORDERS INNER JOIN EMPLOYEES ON ORDERS.EMPLOYEE_ID = EMPLOYEES.EMPLOYEE_ID\n" +
                    "WHERE\n" +
                    "STAT_ID=4 AND\n" +
                    "ACTU_REP_DATE>=? AND\n" +
                    "ACTU_REP_DATE<=?\n" +
                    "GROUP BY EMPLOYEE_NAME";

    public static List<Report> getTotOrders (Date dateFrom, Date dateTo) {
        List<Report> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn();
             PreparedStatement prepStat = conn.prepareStatement(GET_TOTAL_ORDERS_QUERY)) {
            prepStat.setString(1, dateFrom.toString());
            prepStat.setString(2, dateTo.toString());
            try (ResultSet resSet = prepStat.executeQuery()){
                while (resSet.next()) {
                    Report report = new Report();
                    report.setEmployeeName(resSet.getString(1));
                    report.setManHours(resSet.getBigDecimal(2));
                    report.setRepairCost(resSet.getBigDecimal(3));
                    list.add(report);
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
}
