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

@WebServlet("/svtCreateCustomer")
public class SvtCreateCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Customer customer = new Customer(firstName,lastName,birthDate,phone,email);
        CustomerDao.createCustomer(customer);
        int customerId  = customer.getCustomerId();
        if (customerId==0) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety utworzenie klienta (" + firstName + " " +lastName + ") nie powiodło się<br>");
            writer.println("<a href=\"/svtCreateCustomer\">Spróbuj ponownie</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneCust?customerId="+customerId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/createCustomer.jsp").forward(request, response);
    }
}
