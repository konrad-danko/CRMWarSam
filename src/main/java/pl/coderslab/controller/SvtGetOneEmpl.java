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
import java.util.List;

@WebServlet("/svtGetOneEmpl")
public class SvtGetOneEmpl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassEmployee(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassEmployee(request, response);
    }

    private static void getIdAndPassEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        Employee employee = EmployeeDao.getOneEmployee(employeeId);
        request.setAttribute("employee", employee);
        
        List<Order> emplOrderList = OrderDao.getEmplOrders(employeeId);
        request.setAttribute("emplOrderList", emplOrderList);

        request.getRequestDispatcher("/views/showOneEmployee.jsp").forward(request, response);
    }
}
