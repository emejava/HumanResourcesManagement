<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleOne.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Recruitment Form</title>
</head>
<body dir="rtl">
<div class="container">
    <header>فرم ثبت نام برای استخدام</header>
    <form action="/recruitment.do" method="post">
        <div class="form first">
            <div class="details personal">
                <span class="title">اطلاعات شخصی</span>

                <div class="fields">
                    <div class="input-field">
                        <label>محل تحصیل</label>
                        <input type="text" name="EducationPlace" placeholder="محل تحصیل خود را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>تحصیلات</label>
                        <input type="text" name="Education" placeholder="تحصیلات خود را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>رشته تحصیلی</label>
                        <input type="text" name="FieldOfStudy" placeholder="رشته تحصیلی خود را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>سابقه کاری</label>
                        <input type="text" name="WorkExperience" placeholder="سابقه کاری تان را برایمان بنویسید" required>
                    </div>
                    <div class="input-field">
                        <label>شغل قبلی</label>
                        <input type="text" name="LastJob" placeholder="عنوان آخرین شغل تان را بنویسید" required>
                    </div>
                    <div class="input-field">
                        <label>علت خروج از محل کار قبلی</label>
                        <input type="text" name="LastJobExitReason" placeholder="علت را بنویسید" required>
                    </div>
                    <div class="input-field">
                        <label>آدرس محل کار قبلی</label>
                        <input type="text" name="LastJobAdders" placeholder="آدرس را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>شماره تماس محل کار قبلی</label>
                        <input type="text" name="LastJobNo" placeholder="شماره تماس را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>شیفت کاری</label>
                        <input type="" name="ShiftWork" placeholder="شیفت خود را انتخاب کنید" required>
                    </div>
                    <div class="input-field">
                        <label>حقوق درخواستی</label>
                        <input type="text" name="RequestedSalary" placeholder="حقوق درخواستی را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <select class="select" name="personalId">
                            <c:forEach var="person" items="${sessionScope.person}">
                                <option value="personalOfDismissal">
                                    Personal Of Recruitment
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
                <span class="btnText" >ارسال</span>
                <!--                <ion-icon name = "paper-plane"></ion-icon>-->
            </button>
        </div>
    </form>
</div>
</body>
</html>
