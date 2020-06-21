package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/svtCreateEmployee")
public class SvtCreateEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        BigDecimal hourlyWage = new BigDecimal(request.getParameter("hourlyWage")) ;

        Employee employee = new Employee(firstName, lastName, address, phone, note, hourlyWage);
        EmployeeDao.createEmployee(employee);
        int employeeId  = employee.getEmployeeId();
        if (employeeId==0) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety utworzenie pracownika (" + firstName + " " +lastName + ") nie powiodło się<br>");
            writer.println("<a href=\"/svtCreateEmployee\">Spróbuj ponownie</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneEmpl?employeeId="+employeeId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/createEmployee.jsp").forward(request, response);
    }
}
