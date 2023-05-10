<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleOne.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>LeaveDays Form</title>
</head>
<body dir="rtl">
<div class="container">
    <header> فرم مرخصی</header>
    <form action="/leaveDays.do" method="post">
        <div class="form first">
            <div class="details personal">

                <div class="fields">
                    <div class="input-field">
                        <label>شماره پرسنلی</label>
                        <input type="number" name="PersonnelCode" placeholder="شماره پرسنلی خود را واد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>از تاریخ</label>
                        <input type="datetime-local" name="From" required>
                    </div>
                    <div class="input-field">
                        <label>تا تاریخ</label>
                        <input type="datetime-local" name="Till" required>
                    </div>
                    <div class="input-field">
                        <label>تعداد روز</label>
                        <input type="number" name="DaysCount" placeholder="تعداد روز را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>درخواست</label>
                        <input type="text" name="Request" placeholder="درخواست خود را وارد کنید" required>
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
