package pl.coderslab.controller;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/svtDeleteVehicle")
public class SvtDeleteVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle = VehicleDao.getOneVehicle(vehicleId);
        if (!VehicleDao.deleteVehicle(vehicleId)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety usunięcie danych pojazdu (" + vehicle.getMark() + " " +vehicle.getModel()+ ", " +vehicle.getRegNumber() +") nie powiodło się<br>");
            String path = "//svtGetOneVeh?vehicleId="+vehicleId;
            writer.println("<a href=\""+path+"\">Powrót</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneCust?customerId="+vehicle.getCustomerId());
    }
}
