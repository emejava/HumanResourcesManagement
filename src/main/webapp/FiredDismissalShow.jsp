<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست عدم نیاز</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>شماره پرسنلی</th>
        <th>علت</th>
        <th>تاریخ</th>
        <th>آخرین پرداختی</th>
        <th>پیوست</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="dismissal" items="${sessionScope.dismissalList}">
    <tr class="active-row">
        <td>${dismissal.id}</td>
        <td>${dismissal.person}</td>
        <td>${dismissal.reason}</td>
        <td>${dismissal.date}</td>
        <td>${dismissal.lastPayment}</td>
        <td>${dismissal.attachment}</td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
