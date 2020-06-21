<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Pracownik</title>
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
<h2>Dane pracownika</h2>
<table style="width: 40%">
    <tr>
        <td>Imię:</td>
        <td>${employee.getFirstName()}</td>
    </tr>
    <tr>
        <td>Nazwisko:</td>
        <td>${employee.getLastName()}</td>
    </tr>
    <tr>
        <td>Adres:</td>
        <td>${employee.getAddress()}</td>
    </tr>
    <tr>
        <td>Telefon:</td>
        <td>${employee.getPhone()}</td>
    </tr>
    <tr>
        <td>Notatka:</td>
        <td>${employee.getNote()}</td>
    </tr>
    <tr>
        <td>Stawka godzinowa (PLN):</td>
        <td>${employee.getHourlyWage()}</td>
    </tr>
</table>
    <br>
<form method="get">
    <input hidden type="number" name="employeeId" value="${employee.getEmployeeId()}">
    <input type="submit" formaction="/svtUpdateEmployee" value="Edytuj dane pracownika" style="width:170px; height:25px;">
    <input type="submit" formaction="/svtDeleteEmployee" value="Usuń dane pracownika" style="width:170px; height:25px;">
</form>
<h4>Bieżące zlecenia w naprawie</h4>
<table>
    <tr>
        <th>Nr</th>
        <th>Pojazd</th>
        <th>Opis zlecenia</th>
        <th style="text-align: center">Data zlecenia</th>
        <th style="text-align: center">Data naprawy</th>
        <th>Opis naprawy</th>
        <th style="text-align: right">Koszt (PLN)</th>
    </tr>
    <c:forEach items="${emplOrderList}" var="item">
        <tr>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneOrdr?orderId=${item.getOrderId()}"/>'>${item.getOrderId()}</a> </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneVeh?vehicleId=${item.getVehicleId()}"/>'>${item.getVehicleName()}</a> </td>
            <td> ${item.getProblemDescr()} </td>
            <td style="text-align: center"> ${item.getOrdrCreatDate()} </td>
            <td style="text-align: center"> ${item.getActuRepDate()} </td>
            <td> ${item.getRepairDescr()} </td>
            <td style="text-align: right"> ${item.getRepairCost()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>
