<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست تائید استعفا</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>شماره پرسنلی</th>
        <th>تاریخ</th>
        <th>تاریخ آخرین پرداختی</th>
        <th>علت استعفا</th>
        <th>جزئیات</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="resignation" items="${sessionScope.resignationList}">
    <tr class="active-row">
        <td>${Resignation.id}</td>
        <td>${Resignation.person}</td>
        <td>${Resignation.date}</td>
        <td>${Resignation.lastPayment}</td>
        <td>${Resignation.reason}</td>
        <td class="Details"><div class="input3"><input type="submit" value="جزئیات"></div></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
