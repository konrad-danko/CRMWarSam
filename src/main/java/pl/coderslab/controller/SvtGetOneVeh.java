package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svtGetOneVeh")
public class SvtGetOneVeh extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassVehicle(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassVehicle(request, response);
    }

    private static void getIdAndPassVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

        Vehicle vehicle = VehicleDao.getOneVehicle(vehicleId);
        request.setAttribute("vehicle", vehicle);

        List<Order> vehOrderList = OrderDao.getVehOrders(vehicleId);
        request.setAttribute("vehOrderList", vehOrderList);

        request.getRequestDispatcher("/views/showOneVehicle.jsp").forward(request, response);
    }
}
