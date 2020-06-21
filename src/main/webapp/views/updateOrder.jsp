<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edycja zlecenia</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Edytuj zlecenie nr ${order.getOrderId()}</h2>
<form action="${pageContext.request.contextPath}/svtUpdateOrder", method="post">
    <input hidden type="number" name="orderId" value="${order.getOrderId()}">
    <input hidden type="number" name="vehicleId" value="${order.getVehicleId()}">
    <input hidden type="date" name="ordrCreatDate" value="${order.getOrdrCreatDate()}">
    <table>
        <tr style="color:midnightblue; font-weight:bold">
            <td>Pojazd:</td>
            <td>${order.getVehicleName()}</td>
        </tr>
        <tr>
            <td>Nr zlecenia:</td>
            <td>${order.getOrderId()}</td>
        </tr>
        <tr>
            <td>Opis zlecenia:</td>
            <td><input type="text" name="problemDescr" value="${order.getProblemDescr()}" required size="100"></td>
        </tr>
        <tr>
            <td>Pracownik:</td>
            <td>
                <select name="employeeId">
                    <option value="${order.getEmployeeId()}">${order.getEmployeeName()}</option>
                    <c:forEach items="${employeeList}" var="item">
                        <option value=${item.getEmployeeId()}>${item.getLastName()} ${item.getFirstName()} (${item.getNote()}), ${item.getHourlyWage()} zł/h</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Data zlecenia</td>
            <td>${order.getOrdrCreatDate()}</td>
        </tr>
        <tr>
            <td>Data plan. naprawy:</td>
            <td><input type="date" name="planRepDate" value="${order.getPlanRepDate()}" required></td>
        </tr>
        <tr>
            <td>Data naprawy:</td>
            <td><input type="date" name="actuRepDate" value="${order.getActuRepDate()}" required></td>
        </tr>
        <tr>
            <td>Ilość rob.godz.:</td>
            <td><input type="number" name="manHours" step="0.01" min="0.00" value="${order.getManHours()}" required></td>
        </tr>
        <tr>
            <td>Stawka godzinowa</td>
            <td>${order.getHourlyWage()}</td>
        </tr>
        <tr>
            <td>Koszt części:</td>
            <td><input type="number" name="partsCost" step="0.01" min="0.00" value="${order.getPartsCost()}" required></td>
        </tr>
        <tr>
            <td>Koszt (PLN):</td>
            <td>${order.getRepairCost()}</td>
        </tr>
        <tr>
            <td>Opis naprawy:</td>
            <td><input type="text" name="repairDescr" value="${order.getRepairDescr()}" size="100"></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td>
                <select name="statId">
                    <option value="${order.getStatId()}">${order.getStatName()}</option>
                    <option value="1">Przyjęty</option>
                    <option value="2">Zatwierdzone koszty naprawy</option>
                    <option value="3">W naprawie</option>
                    <option value="4">Gotowy do odbioru</option>
                    <option value="5">Rezygnacja</option>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Zatwierdź" style="width:170px; height:25px;">
    <input type="submit" value="Anuluj zmiany" formaction="/svtGetOneOrdr" style="width:170px; height:25px">
</form>
<br><br>
<%@ include file="footer.jsp" %>
</body>
</html>
