package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/svtCreateOrder")
public class SvtCreateOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String problemDescr = request.getParameter("problemDescr");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Employee employee = EmployeeDao.getOneEmployee(employeeId);
        Date ordrCreatDate = Date.valueOf(LocalDate.now());
        Date planRepDate = Date.valueOf(request.getParameter("planRepDate"));
        Date actuRepDate = Date.valueOf(request.getParameter("planRepDate"));
        BigDecimal manHours = new BigDecimal(request.getParameter("manHours"));
        BigDecimal hourlyWage = employee==null ? BigDecimal.valueOf(0) : employee.getHourlyWage();
        BigDecimal partsCost = new BigDecimal(request.getParameter("partsCost"));
        BigDecimal repairCost = manHours.multiply(hourlyWage).add(partsCost);
        String repairDescr = request.getParameter("repairDescr")==null ? null : request.getParameter("repairDescr");
        
        Order order = new Order(vehicleId, problemDescr, employeeId, ordrCreatDate, planRepDate, actuRepDate, manHours, hourlyWage, partsCost, repairCost, repairDescr, 1);
        OrderDao.createOrder(order);
        int orderId = order.getOrderId();
        if (orderId==0) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety zapisanie zlecenia ("+") nie powiodło się<br><br>");
            writer.println("<a href=\"/svtGetOneVeh?vehicleId="+vehicleId+"\">Powrót do widoku pojazdu</a><br>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneVeh?vehicleId="+vehicleId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Employee> employeeList = EmployeeDao.getAllEmployees();
        request.setAttribute("employeeList",employeeList );

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle = VehicleDao.getOneVehicle(vehicleId);
        request.setAttribute("vehicle", vehicle);

        request.getRequestDispatcher("/views/createOrder.jsp").forward(request, response);
    }
}
