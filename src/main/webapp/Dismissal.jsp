<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleOne.css">
    <!--                    Css for select                     -->
    <link rel="stylesheet" href="asetes/css/styleSelect.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Dismissal Form</title>
</head>
<body dir="rtl">
<div class="container">
    <header> فرم عدم نیاز</header>
    <form action="/dismissal.do" method="post">
        <div class="form first">
            <div class="details personal">
                <div class="fields">
                    <div class="input-field">
                        <div class="input-field">
                            <label>شماره پرسنلی</label>
                            <input type="number" name="Person" placeholder="شماره پرسنلی خود را وارد کنید"  required>
                        </div>
                        <div class="input-field">
                            <label>علت</label>
                            <input type="text" name="Reason"  placeholder="علت مرخصی را وارد کنید"  required>
                        </div>
                        <label>تاریخ</label>
                        <input type="date" name="date"  required>
                    </div>
                    <div class="input-field">
                        <label> تاریخ آخرین پرداختی</label>
                        <input type="date" name="LastPayment"  required>
                    </div>
                    <div class="input-field">
                        <label>پیوست</label>
                        <input type="text" name="Attachment" placeholder="پیوست مربوطه را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <select class="select" name="personalId">
                            <c:forEach var="person" items="${sessionScope.person}">
                                <option value="personalOfDismissal">
                                    Personal Of Dismissal
                                </option>
                                <option value="personalOfDismissal">
                                        ${person.firstName}${" "}${person.lastName}${" "}${person.id}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <button class="leaveBTN">
                <i class="uil-navigator"></i>
                <span class="btnText" >ارسال</span>
                <!--                <ion-icon name = "paper-plane"></ion-icon>-->
            </button>
        </div>
    </form>
</div>
</body>
</html>
