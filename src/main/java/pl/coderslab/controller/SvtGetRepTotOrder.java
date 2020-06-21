package pl.coderslab.controller;

import pl.coderslab.dao.ReportDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Report;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/svtRepRevenue")
public class SvtGetRepTotOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Date dateFrom = Date.valueOf(request.getParameter("dateFrom"));
        Date dateTo = Date.valueOf(request.getParameter("dateTo"));
        request.setAttribute("dateFrom", dateFrom);
        request.setAttribute("dateTo", dateTo);

        List<Report> reportList = ReportDao.getTotOrders(dateFrom, dateTo);
        request.setAttribute("reportList", reportList);

        BigDecimal sumManHours = BigDecimal.valueOf(0);
        BigDecimal sumRepairCost = BigDecimal.valueOf(0);
        for (Report report: reportList) {
            sumManHours = sumManHours.add(report.getManHours());
            sumRepairCost = sumRepairCost.add(report.getRepairCost());
        }
        request.setAttribute("sumManHours", sumManHours);
        request.setAttribute("sumRepairCost", sumRepairCost);

        request.getRequestDispatcher("/views/showReport.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
