<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Pracownicy</title>
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
<form>
    <input type="submit" formaction="/svtCreateEmployee" value="Dodaj nowego pracownika" style="width:170px; height:25px;">
</form>
<h2 style="text-align:center;">Lista pracowników</h2>
<table>
    <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Adres</th>
        <th style="text-align: center">Tel. kontaktowy</th>
        <th>Notatka</th>
        <th>Stawka godzinowa</th>
    </tr>
    <c:forEach items="${employeeList}" var="item">
        <tr>
            <td> ${item.getFirstName()} </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneEmpl?employeeId=${item.getEmployeeId()}"/>'>${item.getLastName()}</a> </td>
            <td> ${item.getAddress()} </td>
            <td style="text-align: center"> ${item.getPhone()} </td>
            <td> ${item.getNote()} </td>
            <td style="text-align: right"> ${item.getHourlyWage()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>

