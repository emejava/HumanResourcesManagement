<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleOne.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Unit/Duty</title>
</head>
<body dir="rtl">
<div class="container">
    <header>بخش/وظیفه</header>
    <form action="" method="post">
        <div class="form first">
            <div class="details personal">
                <span class="title">بخش</span>

                <div class="fields">
                    <div class="input-field">
                        <label> نام بخش</label>
                        <input type="text" name="NameUnit" placeholder="نام بخش خود را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>بخش های مرتبط</label>
                        <input type="" name="RelatedUnits" placeholder="تمامی بخش های مرتبط را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>شهر</label>
                        <input type="text" name="City" placeholder="شهر را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>وظایف</label>
                        <input type="" name="Duties" placeholder="تمامی وظایف را وارد نمایید" required>
                    </div>
                </div>
            </div>
            <div class="details personal">
                <span class="title">وظیفه</span>

                <div class="fields">
                    <div class="input-field">
                        <label>توضیح وظیفه</label>
                        <input type="text" name="DutyExplanation" placeholder="توضیح را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>موقعیت</label>
                        <input type="number" name="Position" placeholder="موقعیت را وارد نمایید" required>
                    </div>
                </div>
            </div>
            <button class="leaveBTN">
                <i class="uil-navigator"></i>
                <span class="btnText" >ثبت</span>
                <!--                <ion-icon name = "paper-plane"></ion-icon>-->
            </button>
        </div>
    </form>
</div>
</body>
</html>
