<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست بازنشستگی</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>شماره پرسنلی</th>
        <th>کد پرسنلی</th>
        <th>تاریخ</th>
        <th>تاریخ آخرین پراختی</th>
        <th>پیوست</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="retirement" items="${sessionScope.retirementList}">
    <tr class="active-row">
        <td>${Retirement.id}</td>
        <td>${Retirement.persin}</td>
        <td>${Retirement.personalCode}</td>
        <td>${Retirement.date}</td>
        <td>${Retirement.lastPayment}</td>
        <td>${Retirement.attachment}</td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
