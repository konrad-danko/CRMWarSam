package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/svtDeleteOrder")
public class SvtDeleteOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDao.getOneOrder(orderId);
        if (!OrderDao.deleteOrder(orderId)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety usunięcie zlecenia nr '" + orderId +"' nie powiodło się<br>");
            String path = "//svtGetOneOrdr?orderId="+orderId;
            writer.println("<a href=\""+path+"\">Powrót</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneVeh?vehicleId="+order.getVehicleId());
    }
}
