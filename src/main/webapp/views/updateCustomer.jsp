<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edycja klienta</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Edytuj dane klienta</h2>
<form action="${pageContext.request.contextPath}/svtUpdateCustomer", method="post">
    <input hidden type="number" name="customerId" value="${customer.getCustomerId()}">
    <table>
        <tr>
            <td>Imię:</td>
            <td><input type="text" name="firstName" value="${customer.getFirstName()}" autofocus required></td>
        </tr>
        <tr>
            <td>Nazwisko:</td>
            <td><input type="text" name="lastName" value="${customer.getLastName()}" required></td>
        </tr>
        <tr>
            <td>Data urodzenia:</td>
            <td><input type="date" name="birthDate" value="${customer.getBirthDate()}" required></td>
        </tr>
        <tr>
            <td>Telefon:</td>
            <td><input type="tel" name="phone" value="${customer.getPhone()}" required></td>
        </tr>
        <tr>
            <td>Adres mailowy:</td>
            <td><input type="email" name="email" value="${customer.getEmail()}" required></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Zatwierdź" style="width:170px; height:25px;">
    <input type="submit" value="Anuluj zmiany" formaction="/svtGetOneCust" style="width:170px; height:25px">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>
