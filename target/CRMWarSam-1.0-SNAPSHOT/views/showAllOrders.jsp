<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Zlecenia</title>
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
<h2 style="text-align:center;">Lista zleceń</h2>
<table>
    <tr>
        <th>Nr</th>
        <th>Pojazd</th>
        <th>Opis zlecenia</th>
        <th>Pracownik</th>
        <th style="text-align: center">Data zlecenia</th>
        <th style="text-align: center">Data naprawy</th>
        <th style="text-align: right">Koszt (PLN)</th>
        <th>Status</th>
    </tr>
    <c:forEach items="${orderList}" var="item">
        <tr>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneOrdr?orderId=${item.getOrderId()}"/>'>${item.getOrderId()}</a> </td>
            <td> ${item.getVehicleName()} </td>
            <td> ${item.getProblemDescr()} </td>
            <td> ${item.getEmployeeName()} </td>
            <td style="text-align: center"> ${item.getOrdrCreatDate()} </td>
            <td style="text-align: center"> ${item.getActuRepDate()} </td>
            <td style="text-align: right"> ${item.getRepairCost()} </td>
            <td> ${item.getStatName()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>
