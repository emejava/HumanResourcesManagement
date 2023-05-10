<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست پرداختی</title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>شماره پرسنلی</th>
        <th>سال</th>
        <th>از تاریخ</th>
        <th>تا تاریخ</th>
        <th>حقوق پایه</th>
        <th>اضافه کاری</th>
        <th>غیبت</th>
        <th>زمان عملی</th>
        <th>تعداد حقوق اضافه کاری</th>
        <th>تعداد حقوق غیبت</th>
        <th>تعداد حقوق عملی</th>
        <th>مسکن</th>
        <th>بهره</th>
        <th>پاداش</th>
        <th>پرداختی فرزندان</th>
        <th>پرداختی جدایی</th>
        <th>بیمه</th>
        <th>مالیات</th>
        <th>وام</th>
        <th>مجموع پرداختی</th>
        <th>شماره تراکنش</th>
        <th>جزئیات</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="payment" items="${sessionScope.paymentList}">
    <tr class="active-row">
        <td>${Payment.id}</td>
        <td>${Payment.person}</td>
        <td>${Payment.year}</td>
        <td>${Payment.from}</td>
        <td>${Payment.till}</td>
        <td>${Payment.basicSalary}</td>
        <td>${Payment.overTime}</td>
        <td>${Payment.absenceTime}</td>
        <td>${Payment.operationTime}</td>
        <td>${Payment.overTimePayCount}</td>
        <td>${Payment.absencePayCount}</td>
        <td>${Payment.operationPayCount}</td>
        <td>${Payment.housing}</td>
        <td>${Payment.benefits}</td>
        <td>${Payment.managementBonus}</td>
        <td>${Payment.childrenPay}</td>
        <td>${Payment.severancePay}</td>
        <td>${Payment.insurance}</td>
        <td>${Payment.tax}</td>
        <td>${Payment.debt}</td>
        <td>${Payment.totalPayment}</td>
        <td>${Payment.transactionNumber}</td>
        <td class="Details"><div class="input3"><input type="submit" value="جزئیات"></div></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
