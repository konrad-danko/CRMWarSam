<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nowe zlecenie</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Wprowadź dane nowego zlecenia</h2>
<form action="${pageContext.request.contextPath}/svtCreateOrder", method="post">
    <input hidden type="number" name="vehicleId" value="${vehicle.getVehicleId()}">
    <table>
        <tr style="color:midnightblue; font-weight:bold">
            <td>Pojazd:</td>
            <td>${vehicle.getMark()} ${vehicle.getModel()}, ${vehicle.getRegNumber()}</td>
        </tr>
        <tr>
            <td>Opis zlecenia:</td>
            <td><input type="text" name="problemDescr" autofocus required size="100"></td>
        </tr>
        <tr>
            <td>Pracownik:</td>
            <td>
                <select name="employeeId">
                    <option value="0">???</option>
                    <c:forEach items="${employeeList}" var="item">
                        <option value=${item.getEmployeeId()}>${item.getLastName()} ${item.getFirstName()} (${item.getNote()}), ${item.getHourlyWage()} zł/h</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Data plan. naprawy:</td>
            <td><input type="date" name="planRepDate" required></td>
        </tr>
        <tr>
            <td>Ilość rob.godz.:</td>
            <td><input type="number" name="manHours" step="0.01" min="0.00" value="0" required></td>
        </tr>
        <tr>
            <td>Koszt części:</td>
            <td><input type="number" name="partsCost" step="0.01" min="0.00" value="0" required></td>
        </tr>
        <tr>
            <td>Opis naprawy:</td>
            <td><input type="text" name="repairDescr" size="100"></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Zatwierdź" style="width:170px; height:25px;">
    <input type="reset"  value="Wyczyść formularz" style="width:170px; height:25px;">
    <input type="submit" formnovalidate value="Powrót" formaction="${pageContext.request.contextPath}/svtGetOneVeh" style="width:170px; height:25px;">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>



