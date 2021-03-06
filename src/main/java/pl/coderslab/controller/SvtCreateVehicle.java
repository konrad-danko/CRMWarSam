package pl.coderslab.controller;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Customer;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/svtCreateVehicle")
public class SvtCreateVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String mark = request.getParameter("mark");
        String model = request.getParameter("model");
        int prodYear = Integer.parseInt(request.getParameter("prodYear"));
        String regNumber = request.getParameter("regNumber");
        Date nextInspDate = Date.valueOf(request.getParameter("nextInspDate"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        Vehicle vehicle = new Vehicle(mark,model,prodYear,regNumber,nextInspDate,customerId);
        VehicleDao.createVehicle(vehicle);
        int vehicleId = vehicle.getVehicleId();
        if (vehicleId==0) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety utworzenie pojazdu (" + mark + " " +model + " " +regNumber + ") nie powiodło się<br><br>");
            writer.println("<a href=\"/svtGetOneCust?customerId="+customerId+"\">Powrót do widoku klienta</a><br>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneCust?customerId="+customerId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = CustomerDao.getOneCustomer(customerId);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/views/createVehicle.jsp").forward(request, response);
    }
}
