<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleOne.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Employment Form</title>
</head>
<body dir="rtl">
<div class="container">
    <header>فرم استخدام</header>
    <form action="/employmentForm.do" method="post">
        <div class="form first">
            <div class="details personal">

                <div class="fields">
                    <div class="input-field">
                        <label>شماره پرسنلی</label>
                        <input type="number" name="Person" placeholder="شماره پرسنلی خود را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>نوع استخدام</label>
                        <input type="text" name="EploymentType" placeholder="نوع استخدام و انتخاب کنید" required>
                    </div>
                    <div class="input-field">
                        <label>واحد</label>
                        <input type="number" name="Unit" placeholder="واحد خود را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>وظیفه</label>
                        <input type="number" name="Duty" placeholder="وظیفه خود را وارد کنید"  required>
                    </div>
                    <div class="input-field">
                        <label>موقعیت شغلی</label>
                        <input type="number" name="Position" placeholder="موقعیت شغلی خود را وارد کنید"  required>
                    </div>
                    <div class="input-field">
                        <label>تاریخ شروع به کار</label>
                        <input type="datetime-local" name="StartWorkingDate"  required>
                    </div>

                    <div class="input-field">
                        <label>شیفت</label>
                        <input type="text" name="ShiftWork" placeholder="شیفت خود را انتخماب کنید"  required>
                    </div>
                    <div class="input-field">
                        <select class="select" name="personalId">
                            <c:forEach var="person" items="${sessionScope.person}">
                                <option value="personalOfDismissal">
                                    Personal Of Employment
                                </option>
                                <option value="personalOfDismissal">
                                        ${person.firstName}${" "}${person.lastName}${" "}${person.id}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <button class="submitBTN">
                <i class="uil-navigator"></i>
                <span class="btnText" >ثبت</span>
                <!--                <ion-icon name = "paper-plane"></ion-icon>-->
            </button>
        </div>
    </form>
</div>
</body>
</html>
