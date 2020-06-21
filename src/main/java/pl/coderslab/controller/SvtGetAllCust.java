package pl.coderslab.controller;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svtGetAllCust")
public class SvtGetAllCust extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName")==null ? null : request.getParameter("firstName");
        String lastName = request.getParameter("lastName")==null ? null : request.getParameter("lastName");
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);

        List<Customer> customerList = CustomerDao.getSomeCustomers(firstName, lastName);
        request.setAttribute("customerList", customerList);

        request.getRequestDispatcher("/views/showAllCustomers.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Customer> customerList = CustomerDao.getAllCustomers();
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("/views/showAllCustomers.jsp").forward(request, response);
    }
}
