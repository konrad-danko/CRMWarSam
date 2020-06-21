<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nowy klient</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Wprowadź dane nowego klienta</h2>
<form action="${pageContext.request.contextPath}/svtCreateCustomer", method="post">
    <table>
        <tr>
            <td>Imię:</td>
            <td><input type="text" name="firstName" autofocus required></td>
        </tr>
        <tr>
            <td>Nazwisko:</td>
            <td><input type="text" name="lastName" required></td>
        </tr>
        <tr>
            <td>Data urodzenia:</td>
            <td><input type="date" name="birthDate" required></td>
        </tr>
        <tr>
            <td>Telefon:</td>
            <td><input type="tel" name="phone" placeholder="format: +48 22 123 4567" required></td>
        </tr>
        <tr>
            <td>Adres mailowy:</td>
            <td><input type="email" name="email" required></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Zatwierdź" style="width:170px; height:25px;">
    <input type="reset"  value="Wyczyść formularz" style="width:170px; height:25px;">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>
