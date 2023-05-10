<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleOne.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Resignation Form</title>
</head>
<body dir="rtl">
<div class="container">
    <header>فرم استعفا</header>
    <form action="/resignSubmit.do" method="post">
        <div class="form first">
            <div class="details personal">

                <div class="fields">
                    <div class="input-field">
                        <label>شماره پرسنلی</label>
                        <input type="number" name="person"  placeholder="علت را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>تاریخ</label>
                        <input type="date" name="date" required>
                    </div>
                    <div class="input-field">
                        <label>علت</label>
                        <input type="text" name="reason" placeholder="شماره پرسنلی خود را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>پیوست</label>
                        <input type="text" name="Attachment" placeholder="پیوست مربوطه را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <select class="select" name="personalId">
                            <c:forEach var="person" items="${sessionScope.person}">
                                <option value="personalOfDismissal">
                                    Personal Of Resignation
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
                <span class="btnText" >ثبت</span>
                <!--                <ion-icon name = "paper-plane"></ion-icon>-->
            </button>
        </div>
    </form>
</div>
</body>
</html>
