<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nagłówek</title>
</head>
<body>
<form>
    <input type="submit" formaction="/svtHomePage" value="Strona główna" style="width:200px; height:30px;">
    <input type="submit" formaction="/svtGetAllEmpl" value="Pracownicy" style="width:200px; height:30px;">
    <input type="submit" formaction="/svtGetAllCust" value="Klienci" style="width:200px; height:30px;">
    <input type="submit" formaction="/svtGetAllVeh" value="Pojazdy" style="width:200px; height:30px;">
    <input type="submit" formaction="/svtGetAllOrdr" value="Zlecenia" style="width:200px; height:30px;">
    <input type="submit" formaction="/views/reports.jsp" value="Raport" style="width:200px; height:30px;">
</form>
<hr>
</body>
</html>
