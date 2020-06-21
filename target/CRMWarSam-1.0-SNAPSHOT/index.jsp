<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Strona główna</title>
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
<%@ include file="views/header.jsp" %>
<h2>Lista bieżących zleceń</h2>
<table>
    <tr>
        <th>Pracownik</th>
        <th>Nr</th>
        <th>Pojazd</th>
        <th>Opis zlecenia</th>
        <th style="text-align: center">Data zlecenia</th>
        <th style="text-align: center">Data naprawy</th>
        <th style="text-align: right">Koszt (PLN)</th>
    </tr>
    <c:forEach items="${currOrderList}" var="item">
        <tr>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneEmpl?employeeId=${item.getEmployeeId()}"/>'>${item.getEmployeeName()}</a> </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneOrdr?orderId=${item.getOrderId()}"/>'>${item.getOrderId()}</a> </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneVeh?vehicleId=${item.getVehicleId()}"/>'>${item.getVehicleName()}</a> </td>
            <td> ${item.getProblemDescr()} </td>
            <td style="text-align: center"> ${item.getOrdrCreatDate()} </td>
            <td style="text-align: center"> ${item.getActuRepDate()} </td>
            <td style="text-align: right"> ${item.getRepairCost()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="views/footer.jsp" %>
</body>
</html>
