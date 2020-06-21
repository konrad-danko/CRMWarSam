<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Pojazdy</title>
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
<h2 style="text-align:center;">Lista pojazdów</h2>
<table>
    <tr>
        <th>Marka</th>
        <th>Model</th>
        <th>Rok produkcji</th>
        <th>Nr rejestracyjny</th>
        <th>Następny przegląd</th>
        <th>Klient</th>
    </tr>
    <c:forEach items="${vehicleList}" var="item">
        <tr>
            <td> ${item.getMark()} </td>
            <td> ${item.getModel()} </td>
            <td> ${item.getProdYear()} </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneVeh?vehicleId=${item.getVehicleId()}"/>'>${item.getRegNumber()}</a> </td>
            <td> ${item.getNextInspDate()} </td>
            <td> ${item.getCustName()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>

