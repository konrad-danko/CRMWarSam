<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Pojazd</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 4px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Dane pojazdu</h2>
<table style="width: 40%">
    <tr style="color:midnightblue; font-weight:bold">
        <td>Klient:</td>
        <td><a href='<c:url value="${pageContext.request.contextPath}/svtGetOneCust?customerId=${vehicle.getCustomerId()}"/>'>${vehicle.getCustName()}</a></td>
    </tr>
    <tr>
        <td>Marka:</td>
        <td>${vehicle.getMark()}</td>
    </tr>
    <tr>
        <td>Model:</td>
        <td>${vehicle.getModel()}</td>
    </tr>
    <tr>
        <td>Nr rejestracyjny:</td>
        <td>${vehicle.getRegNumber()}</td>
    </tr>
    <tr>
        <td>Rok produkcji:</td>
        <td>${vehicle.getProdYear()}</td>
    </tr>
    <tr>
        <td>Kolejny przegląd:</td>
        <td>${vehicle.getNextInspDate()}</td>
    </tr>
</table>
    <br>
<form method="get">
    <input hidden type="number" name="vehicleId" value="${vehicle.getVehicleId()}">
    <input type="submit" formaction="/svtUpdateVehicle" value="Edytuj dane pojazdu" style="width:170px; height:25px;">
    <input type="submit" formaction="/svtDeleteVehicle" value="Usuń dane pojazdu" style="width:170px; height:25px;">
    <input type="submit" formaction="/svtCreateOrder" value="Dodaj nowe zlecenie" style="width:170px; height:25px;">
</form>
<h4>Zlecenia pojazdu</h4>
<table>
    <tr>
        <th>Nr</th>
        <th>Opis zlecenia</th>
        <th>Pracownik</th>
        <th style="text-align: center">Data zlecenia</th>
        <th style="text-align: center">Data naprawy</th>
        <th style="text-align: right">Ilość roboczogodzin</th>
        <th style="text-align: right">Koszt (PLN)</th>
        <th>Status</th>
    </tr>
    <c:forEach items="${vehOrderList}" var="item">
        <tr>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneOrdr?orderId=${item.getOrderId()}"/>'>${item.getOrderId()}</a> </td>
            <td> ${item.getProblemDescr()} </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneEmpl?employeeId=${item.getEmployeeId()}"/>'>${item.getEmployeeName()}</a> </td>
            <td style="text-align: center"> ${item.getOrdrCreatDate()} </td>
            <td style="text-align: center"> ${item.getActuRepDate()} </td>
            <td style="text-align: right"> ${item.getManHours()} </td>
            <td style="text-align: right"> ${item.getRepairCost()} </td>
            <td> ${item.getStatName()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>
