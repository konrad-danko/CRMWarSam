<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Zlecenie</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 50%;
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
<h2>Dane zlecenia</h2>
<table>
    <tr style="color:midnightblue; font-weight:bold">
        <td>Pojazd:</td>
        <td><a href='<c:url value="${pageContext.request.contextPath}/svtGetOneVeh?vehicleId=${order.getVehicleId()}"/>'>${order.getVehicleName()}</a></td>
    </tr>
    <tr>
        <td>Nr zlecenia:</td>
        <td>${order.getOrderId()}</td>
    </tr>
    <tr>
        <td>Opis zlecenia:</td>
        <td>${order.getProblemDescr()}</td>
    </tr>
    <tr>
        <td>Pracownik:</td>
        <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneEmpl?employeeId=${order.getEmployeeId()}"/>'>${order.getEmployeeName()}</a> </td>
    </tr>
    <tr>
        <td>Data zlecenia:</td>
        <td>${order.getOrdrCreatDate()}</td>
    </tr>
    <tr>
        <td>Data plan. naprawy:</td>
        <td>${order.getPlanRepDate()}</td>
    </tr>
    <tr>
        <td>Data naprawy:</td>
        <td>${order.getActuRepDate()}</td>
    </tr>
    <tr>
        <td>Ilość rob.godz.:</td>
        <td>${order.getManHours()}</td>
    </tr>
    <tr>
        <td>Stawka godzinowa:</td>
        <td>${order.getHourlyWage()}</td>
    </tr>
    <tr>
        <td>Koszt części:</td>
        <td>${order.getPartsCost()}</td>
    </tr>
    <tr>
        <td>Koszt (PLN):</td>
        <td>${order.getRepairCost()}</td>
    </tr>
    <tr>
        <td>Opis naprawy:</td>
        <td>${order.getRepairDescr()}</td>
    </tr>
    <tr>
        <td>Status:</td>
        <td>${order.getStatName()}</td>
    </tr>
</table>
    <br>
<form method="get">
    <input hidden type="number" name="orderId" value="${order.getOrderId()}">
    <input type="submit" formaction="/svtUpdateOrder" value="Edytuj zlecenie" style="width:170px; height:25px;">
    <input type="submit" formaction="/svtDeleteOrder" value="Usuń zlecenie" style="width:170px; height:25px;">
</form>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>
