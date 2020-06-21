package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/svtGetOneOrdr")
public class SvtGetOneOrdr extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassOrder(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getIdAndPassOrder(request, response);
    }

    private static void getIdAndPassOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        Order order = OrderDao.getOneOrder(orderId);
        request.setAttribute("order", order);

        request.getRequestDispatcher("/views/showOneOrder.jsp").forward(request, response);
    }
}
