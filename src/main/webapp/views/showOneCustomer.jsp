<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Klient</title>
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
<h2>Dane klienta</h2>
<table style="width: 35%">
    <tr>
        <td>Imię:</td>
        <td>${customer.getFirstName()}</td>
    </tr>
    <tr>
        <td>Nazwisko:</td>
        <td>${customer.getLastName()}</td>
    </tr>
    <tr>
        <td>Data urodzenia:</td>
        <td>${customer.getBirthDate()}</td>
    </tr>
    <tr>
        <td>Telefon:</td>
        <td>${customer.getPhone()}</td>
    </tr>
    <tr>
        <td>Adres mailowy:</td>
        <td>${customer.getEmail()}</td>
    </tr>
</table>
    <br>
<form method="get">
    <input hidden type="number" name="customerId" value="${customer.getCustomerId()}">
    <input type="submit" formaction="/svtUpdateCustomer" value="Edytuj dane klienta" style="width:170px; height:25px;">
    <input type="submit" formaction="/svtDeleteCustomer" value="Usuń dane klienta" style="width:170px; height:25px;">
    <input type="submit" formaction="/svtCreateVehicle" value="Dodaj nowy pojazd" style="width:170px; height:25px;">
</form>
<h4>Pojazdy klienta</h4>
<table>
    <tr>
        <th>Marka</th>
        <th>Model</th>
        <th>Nr rejestracyjny</th>
        <th>Rok produkcji</th>
        <th>Następny przegląd</th>
    </tr>
    <c:forEach items="${custVehicleList}" var="item">
        <tr>
            <td> ${item.getMark()} </td>
            <td> ${item.getModel()} </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneVeh?vehicleId=${item.getVehicleId()}"/>'>${item.getRegNumber()}</a> </td>
            <td> ${item.getProdYear()} </td>
            <td> ${item.getNextInspDate()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>
