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

@WebServlet("/svtUpdateEmployee")
public class SvtUpdateEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        BigDecimal hourlyWage = new BigDecimal(request.getParameter("hourlyWage")) ;

        Employee employee = new Employee(employeeId, firstName, lastName, address, phone, note, hourlyWage);
        if (!EmployeeDao.updateEmployee(employee)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety zapisanie danych pracownika (" + firstName + " " +lastName + ") nie powiodło się<br>");
            String path = "/svtUpdateEmployee?employeeId="+employeeId;
            writer.println("<a href=\""+path+"\">Spróbuj ponownie</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetOneEmpl?employeeId="+employeeId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Employee employee = EmployeeDao.getOneEmployee(employeeId);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/views/updateEmployee.jsp").forward(request, response);
    }
}
