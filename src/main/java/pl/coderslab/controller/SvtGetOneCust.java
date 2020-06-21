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
import java.util.List;

@WebServlet("/svtGetOneCust")
public class SvtGetOneCust extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassCustomer(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassCustomer(request, response);
    }

    private static void getIdAndPassCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int customerId = Integer.parseInt(request.getParameter("customerId"));

        Customer customer = CustomerDao.getOneCustomer(customerId);
        request.setAttribute("customer", customer);

        List<Vehicle> custVehicleList = VehicleDao.getCustVehicles(customerId);
        request.setAttribute("custVehicleList", custVehicleList);

        request.getRequestDispatcher("/views/showOneCustomer.jsp").forward(request, response);
    }
}
