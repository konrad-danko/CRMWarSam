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

@WebServlet("/svtDeleteEmployee")
public class SvtDeleteEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Employee employee = EmployeeDao.getOneEmployee(employeeId);
        if (!EmployeeDao.deleteEmployee(employeeId)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Niestety usunięcie danych pracownika (" + employee.getFirstName() + " " +employee.getLastName()+ ") nie powiodło się<br>");
            String path = "//svtGetOneEmpl?employeeId="+employeeId;
            writer.println("<a href=\""+path+"\">Powrót</a>");
        } else response.sendRedirect(request.getContextPath() + "/svtGetAllEmpl");
    }
}
