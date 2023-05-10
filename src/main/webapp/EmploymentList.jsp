<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست استخدام</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>شماره پرسنلی</th>
        <th>نوع استخدام</th>
        <th>واحد</th>
        <th>وظیفه</th>
        <th>موقعیت</th>
        <th>تاریخ شروع کار</th>
        <th>شیفت کاری</th>
        <th>جزییات</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="employment" items="${sessionScope.employmentList}">
    <tr class="active-row">
    <td>${employment.id}</td>
    <td>${employment.person}</td>
    <td>${employment.eploymentType}</td>
    <td>${employment.unit}</td>
    <td>${employment.duty}</td>
    <td>${employment.position}</td>
    <td>${employment.startWorkingDate}</td>
    <td>${employment.shiftWork}</td>
    <td class="Details"><div class="input3"><input type="submit" value="جزییات"></div></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
