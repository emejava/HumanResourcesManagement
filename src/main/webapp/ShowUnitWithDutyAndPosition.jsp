<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست نمایش واحد و وضایف و موقعیت</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>نام واحد</th>
        <th>واحد های مرتبط</th>
        <th>شهر</th>
        <th>وضایف</th>
        <th>موقغیت</th>
        <th>توضیح وظایف</th>
        <th>جزئیات</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="show" items="${sessionScope.showList}">
    <tr class="active-row">
        <td>${Unit.id}</td>
        <td>${Unit.name}</td>
        <td class="Details"><div class="input3"><input type="submit" value="واحد های مرتبط"></div></td>
        <td>${Unit.city}</td>
        <td>${Unit.duties}</td>
        <td>${Duty.Position}</td>
        <td>${Duty.dutyExplanation}</td>
        <td class="Details"><div class="input3"><input type="submit" value="جزئیات"></div></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
