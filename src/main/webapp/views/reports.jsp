<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Raporty</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Raport przychodów</h2>
<form method="post">
    <table>
        <tr>
            <td>Data od: </td>
            <td><input type="date" name="dateFrom" required></td>
        </tr>
        <tr>
            <td>Data do: </td>
            <td><input type="date" name="dateTo" required></td>
        </tr>
    </table>
    <br><br>
    <input type="submit" formaction="/svtRepRevenue" value="Pokaż raport" style="width:200px; height:30px;">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>
