<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nowy pojazd</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Wprowadź dane nowego pojazdu</h2>
<form action="${pageContext.request.contextPath}/svtCreateVehicle", method="post">
    <input hidden type="number" name="customerId" value="${customer.getCustomerId()}">
    <table>
        <tr style="color:midnightblue; font-weight:bold">
            <td>Klient:</td>
            <td>${customer.getFirstName()} ${customer.getLastName()}</td>
        </tr>
        <tr>
            <td>Marka:</td>
            <td><input type="text" name="mark" autofocus required></td>
        </tr>
        <tr>
            <td>Model:</td>
            <td><input type="text" name="model" required></td>
        </tr>
        <tr>
            <td>Rok produkcji:</td>
            <td><input type="number" name="prodYear" min="1900" required></td>
        </tr>
        <tr>
            <td>Nr rejestracyjny:</td>
            <td><input type="text" name="regNumber" required></td>
        </tr>
        <tr>
            <td>Data następnego przeglądu:</td>
            <td><input type="date" name="nextInspDate" required></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Zatwierdź" style="width:170px; height:25px;">
    <input type="reset"  value="Wyczyść formularz" style="width:170px; height:25px;">
    <input type="submit" formnovalidate value="Powrót" formaction="${pageContext.request.contextPath}/svtGetOneCust" style="width:170px; height:25px;">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>



