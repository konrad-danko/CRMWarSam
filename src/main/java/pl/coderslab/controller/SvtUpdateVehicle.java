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
import java.util.List;

@WebServlet("/svtUpdateVehicle")
public class SvtUpdateVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String mark = request.getParameter("mark");
        String model = request.getParameter("model");
        String regNumber = request.getParameter("regNumber");
        int prodYear = Integer.parseInt(request.getParameter("prodYear"));
        Date nextInspDate = Date.valueOf(request.getParameter("nextInspDate"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        
        Vehicle vehicle = new Vehicle(vehicleId, mark, model, prodYear, regNumber, nextInspDate, customerId);
        if (!VehicleDao.updateVehicle(vehicle)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety zapisanie danych pojazdu (" + mark + " " + model + ", " + regNumber +") nie powiodło się<br>");
            String path = "/svtUpdateVehicle?vehicleId="+vehicleId;
            writer.println("<a href=\""+path+"\">Spróbuj ponownie</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneVeh?vehicleId="+vehicleId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Customer> customerList = CustomerDao.getAllCustomers();
        request.setAttribute("customerList", customerList);

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle = VehicleDao.getOneVehicle(vehicleId);
        request.setAttribute("vehicle", vehicle);
        
        request.getRequestDispatcher("/views/updateVehicle.jsp").forward(request, response);
    }
}
