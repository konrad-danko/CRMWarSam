<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Przychody</title>
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
<h2 style="text-align:left;">Raport przychodów w okresie od ${dateFrom} do ${dateTo}</h2>
<table>
    <tr>
        <th>Pracownik</th>
        <th style="text-align: right">Roboczogodziny</th>
        <th style="text-align: right">Przychody</th>
    </tr>
    <c:forEach items="${reportList}" var="item">
        <tr>
            <td> ${item.getEmployeeName()} </td>
            <td style="text-align: right"> ${item.getManHours()} </td>
            <td style="text-align: right"> ${item.getRepairCost()} </td>
        </tr>
    </c:forEach>
    <tr>
        <td style="text-align: right; font-weight: bold">Łącznie:</td>
        <td style="text-align: right; font-weight: bold">${sumManHours}</td>
        <td style="text-align: right; font-weight: bold">${sumRepairCost}</td>
    </tr>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>

