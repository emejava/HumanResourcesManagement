<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleNews.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>News Form</title>
</head>
<body dir="rtl">
<div class="container">
    <header>گزارش اخبار</header>
    <form action="/News" method="post">
        <div class="form">
            <div class="details personal">

                <div class="fields">
                    <div class="input-field1">
                        <label>مبحث</label>
                        <input type="text" name="Subject" placeholder="مبحث را وارد کنید" required>
                    </div>
                    <div class="input-field2">
                        <label>عنوان</label>
                        <input type="text" name="Title" placeholder="عنوان را وارد کنید" required>
                    </div>
                    <div class="input-field3">
                        <label>گزارش اخبار</label>
                        <input type="text" name="NewsReport" placeholder="گزارش اخبار خود را وارد کنید" required>
                    </div>

                </div>
            </div>
            <button class="submitBTN">
                <i class="uil-navigator"></i>
                <span class="btnText" >ارسال</span>
                <!--                <ion-icon name = "paper-plane"></ion-icon>-->
            </button>
        </div>
    </form>
</div>
</body>
</html>
