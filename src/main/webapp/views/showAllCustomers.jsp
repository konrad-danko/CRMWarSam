<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Klienci</title>
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
    <table style="width: 20%">
        <tr>
            <td>Imię:</td>
            <td><input type="text" name="firstName" value="${firstName}"></td>
        </tr>
        <tr>
            <td>Nazwisko:</td>
            <td><input type="text" name="lastName" value="${lastName}"></td>
        </tr>
    </table><br>
    <input type="submit" formmethod="get" formaction="/svtGetAllCust" value="Wyczyść formularz" style="width:170px; height:25px;">
    <input type="submit" formmethod="post" formaction="/svtGetAllCust" value="Wyszukaj klienta" style="width:170px; height:25px;">
    <input type="submit" formaction="/svtCreateCustomer" value="Dodaj nowego klienta" style="width:170px; height:25px;">
</form>
<h2 style="text-align:center;">Lista klientów</h2>
<table>
    <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th style="text-align: center">Data urodzenia</th>
        <th style="text-align: center">Tel. kontaktowy</th>
        <th>Adres email</th>
    </tr>
    <c:forEach items="${customerList}" var="item">
        <tr>
            <td> ${item.getFirstName()} </td>
            <td> <a href='<c:url value="${pageContext.request.contextPath}/svtGetOneCust?customerId=${item.getCustomerId()}"/>'>${item.getLastName()}</a> </td>
            <td style="text-align: center"> ${item.getBirthDate()} </td>
            <td style="text-align: center"> ${item.getPhone()} </td>
            <td> ${item.getEmail()} </td>
        </tr>
    </c:forEach>
</table>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>

