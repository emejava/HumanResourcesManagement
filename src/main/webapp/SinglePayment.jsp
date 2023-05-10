<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleTable.css">
    <title>لیست پرداختی های مستقل </title>
</head>
<body dir="rtl">
<table class="content-table">
    <thead>
    <tr>
        <th>رتبه</th>
        <th>کد پرسنلی</th>
        <th>سال</th>
        <th>از تاریخ</th>
        <th>تا تاریخ</th>
        <th>حقوق پایه</th>
        <th>اضافه کاری</th>
        <th>زمان غیبت</th>
        <th>زمان عملی</th>
        <th>تعداد پرداختی اضافه کاری</th>
        <th>تعداد پرداختی غیبت</th>
        <th>تعداد پرداختی عملی</th>
        <th>مسکن</th>
        <th>لیست مرخصی</th>
        <th>کسر حقوق روز های مرخصی</th>
        <th>بهره</th>
        <th>پاداش</th>
        <th>پرداختی فرزندان</th>
        <th>پرداختی جدایی</th>
        <th>بیمه</th>
        <th>مالیات</th>
        <th>وام</th>
        <th>مجموع پرداختی</th>
        <th>شماره تراکنش</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="singlePayment" items="${sessionScope.singlePaymentList}">
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
        <td class="Details"><div class="input3"><input type="submit" value="لیست مرخصی"></div></td>
        <td>${Payment.leaveDaysPayDeduction}</td>
        <td>${Payment.benefits}</td>
        <td>${Payment.managementBonus}</td>
        <td>${Payment.childrenPay}</td>
        <td>${Payment.severancePay}</td>
        <td>${Payment.insurance}</td>
        <td>${Payment.tax}</td>
        <td>${Payment.debt}</td>
        <td>${Payment.totalPayment}</td>
        <td>${Payment.transactionNumber}</td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
