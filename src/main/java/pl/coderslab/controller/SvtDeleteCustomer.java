package pl.coderslab.controller;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/svtDeleteCustomer")
public class SvtDeleteCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = CustomerDao.getOneCustomer(customerId);
        if (!CustomerDao.deleteCustomer(customerId)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety usunięcie danych klienta (" + customer.getFirstName() + " " +customer.getLastName()+ ") nie powiodło się<br>");
            String path = "//svtGetOneCust?customerId="+customerId;
            writer.println("<a href=\""+path+"\">Powrót</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetAllCust");
    }
}
