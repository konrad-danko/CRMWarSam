<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edycja pracownika</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Edytuj dane pracownika</h2>
<form action="${pageContext.request.contextPath}/svtUpdateEmployee", method="post">
    <input hidden type="number" name="employeeId" value="${employee.getEmployeeId()}">
    <table>
        <tr>
            <td> Imię: </td>
            <td><input type="text" name="firstName" value="${employee.getFirstName()}" autofocus required></td>
        </tr>
        <tr>
            <td> Nazwisko: </td>
            <td><input type="text" name="lastName" value="${employee.getLastName()}" required></td>
        </tr>
        <tr>
            <td> Adres: </td>
            <td><input type="text" name="address" value="${employee.getAddress()}" required size="50"></td>
        </tr>
        <tr>
            <td> Telefon: </td>
            <td><input type="tel" name="phone" value="${employee.getPhone()}" required></td>
        </tr>
        <tr>
            <td> Notatka: </td>
            <td><input type="text" name="note" value="${employee.getNote()}" required></td>
        </tr>
        <tr>
            <td> Stawka godzinowa (PLN): </td>
            <td><input type="number" name="hourlyWage" value="${employee.getHourlyWage()}" min="0.00" step="0.01" required></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Zatwierdź" style="width:170px; height:25px;">
    <input type="submit" value="Anuluj zmiany" formaction="/svtGetOneEmpl" style="width:170px; height:25px">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>
