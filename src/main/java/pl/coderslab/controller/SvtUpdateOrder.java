package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/svtUpdateOrder")
public class SvtUpdateOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String problemDescr = request.getParameter("problemDescr");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Employee employee = EmployeeDao.getOneEmployee(employeeId);
        Date ordrCreatDate = Date.valueOf(request.getParameter("ordrCreatDate"));
        Date planRepDate = Date.valueOf(request.getParameter("planRepDate"));
        Date actuRepDate = Date.valueOf(request.getParameter("actuRepDate"));
        BigDecimal manHours = new BigDecimal(request.getParameter("manHours"));
        BigDecimal hourlyWage = employee==null ? BigDecimal.valueOf(0) : employee.getHourlyWage();
        BigDecimal partsCost = new BigDecimal(request.getParameter("partsCost"));
        BigDecimal repairCost = manHours.multiply(hourlyWage).add(partsCost);
        String repairDescr = request.getParameter("repairDescr")==null ? null : request.getParameter("repairDescr");
        int statId = Integer.parseInt(request.getParameter("statId"));

        Order order = new Order(orderId, vehicleId, problemDescr, employeeId, ordrCreatDate, planRepDate, actuRepDate, manHours, hourlyWage, partsCost, repairCost, repairDescr, statId);
        if (!OrderDao.updateOrder(order)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety zapisanie zlecenia ("+") nie powiodło się<br><br>");
            String path = "/svtUpdateOrder?orderId="+orderId;
            writer.println("<a href=\""+path+"\">Spróbuj ponownie</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneOrdr?orderId="+orderId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Employee> employeeList = EmployeeDao.getAllEmployees();
        request.setAttribute("employeeList", employeeList);

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDao.getOneOrder(orderId);
        request.setAttribute("order",order );

        request.getRequestDispatcher("/views/updateOrder.jsp").forward(request, response);
    }
}
