<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست درخواست استخدام</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>شماره پرسنلی</th>
        <th>تحصیلات</th>
        <th>رشته تحصیلی</th>
        <th>محل تحصیل</th>
        <th>سابقه کاری</th>
        <th>شفل سابق</th>
        <th>علت خروج از آخرین شغل</th>
        <th>نشانی محل کار سابق</th>
        <th>شماره تماس محل کار سابق</th>
        <th>شیفت کاری</th>
        <th>حقوق پیشنهادی</th>
        <th>قبول</th>
        <th>عدم پذیرش</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="recruitment" items="${sessionScope.recruitmentList}">
    <tr class="active-row">
        <td>${Recruitment.id}</td>
        <td>${Recruitment.person}</td>
        <td>${Recruitment.education}</td>
        <td>${Recruitment.fieldOfStudy}</td>
        <td>${Recruitment.educationPlace}</td>
        <td>${Recruitment.workExperience}</td>
        <td>${Recruitment.lastJob}</td>
        <td>${Recruitment.lastJobExitReason}</td>
        <td>${Recruitment.lastJobAdders}</td>
        <td>${Recruitment.lastJobNo}</td>
        <td>${Recruitment.shiftWork}</td>
        <td>${Recruitment.requestedSalary}</td>
        <td class="Active"><div class="input1"><input type="checkbox"></div></td>
        <td class="InActive"><div class="input2"><input type="checkbox"></div></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
