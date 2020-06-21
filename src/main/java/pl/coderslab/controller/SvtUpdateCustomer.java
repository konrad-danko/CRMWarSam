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
import java.sql.Date;

@WebServlet("/svtUpdateCustomer")
public class SvtUpdateCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Customer customer = new Customer(customerId,firstName,lastName,birthDate,phone,email);
        if (!CustomerDao.updateCustomer(customer)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety zapisanie danych klienta (" + firstName + " " +lastName + ") nie powiodło się<br>");
            String path = "/svtUpdateCustomer?customerId="+customerId;
            writer.println("<a href=\""+path+"\">Spróbuj ponownie</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneCust?customerId="+customerId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = CustomerDao.getOneCustomer(customerId);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/views/updateCustomer.jsp").forward(request, response);
    }
}
