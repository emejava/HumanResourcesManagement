<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست تائید مرخصی</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>شماره پرسنلی</th>
        <th>از تاریخ</th>
        <th>تا تاریخ</th>
        <th>تعداد روز</th>
        <th>درخواست</th>
        <th>جزئیات</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="leaveDays" items="${sessionScope.leaveDaysList}">
    <tr class="active-row">
        <td>${leaveDays.id}</td>
        <td>${leaveDays.person}</td>
        <td>${leaveDays.from}</td>
        <td>${leaveDays.till}</td>
        <td>${leaveDays.daysCount}</td>
        <td>${leaveDays.request}</td>
        <td class="Details"><div class="input3"><input type="submit" value="جزییات"></div></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
