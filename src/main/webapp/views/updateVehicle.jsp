<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edycja pojazdu</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Edytuj dane pojazdu</h2>
<form action="${pageContext.request.contextPath}/svtUpdateVehicle", method="post">
    <input hidden type="number" name="vehicleId" value="${vehicle.getVehicleId()}">
    <table>
        <tr>
            <td>Klient:</td>
            <td>
                <select name="customerId">
                    <option value="${vehicle.getCustomerId()}">${vehicle.getCustName()}</option>
                    <c:forEach items="${customerList}" var="item">
                        <option value=${item.getCustomerId()}>${item.getLastName()} ${item.getFirstName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Marka:</td>
            <td><input type="text" name="mark" value="${vehicle.getMark()}" autofocus required></td>
        </tr>
        <tr>
            <td>Model:</td>
            <td><input type="text" name="model" value="${vehicle.getModel()}" required></td>
        </tr>
        <tr>
            <td>Nr rejestracyjny:</td>
            <td><input type="text" name="regNumber" value="${vehicle.getRegNumber()}" required></td>
        </tr>
        <tr>
            <td>Rok produkcji:</td>
            <td><input type="number" name="prodYear" value="${vehicle.getProdYear()}" required></td>
        </tr>
        <tr>
            <td>Kolejny przegląd:</td>
            <td><input type="date" name="nextInspDate" value="${vehicle.getNextInspDate()}" required></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Zatwierdź" style="width:170px; height:25px;">
    <input type="submit" value="Anuluj zmiany" formaction="/svtGetOneVeh" style="width:170px; height:25px">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>
