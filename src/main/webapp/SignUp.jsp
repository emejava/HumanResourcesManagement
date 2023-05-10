<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleSignUp.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Sign Up</title>
</head>
<body dir="rtl">
<div class="container">
    <header>ثبت نام</header>
    <form action="signUp.do" method="post">
        <div class="form first">
            <div class="details personal">

                <div class="fields">
                    <div class="input-field">
                        <label>نام</label>
                        <input type="text" name="FirstName" placeholder="نام را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>نام خانوادگی</label>
                        <input type="text" name="LastName" placeholder="نام خانوادگی را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>کد ملی</label>
                        <input type="text" name="NationalCode" placeholder="کد ملی را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>شماره شسناسنامه</label>
                        <input type="text" name="BirthCertificateCode" placeholder="شماره شناسنامه را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>تولد</label>
                        <input type="date" name="Birthday" placeholder="تاریخ تولد را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>سن</label>
                        <input type="number" name="Age" placeholder="سن را وارد کنید" required>
                    </div>
                </div>
            </div>
            <div class="details ID">
                <span class="title">اصالت</span>

                <div class="fields">
                    <div class="input-field">
                        <label>نام پدر</label>
                        <input type="text" name="FatherName" placeholder="نام پدر را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>استان</label>
                        <input type="text" name="State" placeholder="استان را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>شهر</label>
                        <input type="text" name="City" placeholder="شهر را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>کد پستی</label>
                        <input type="text" name="PostCode" placeholder="کد پستی را وارد کنید" required>
                    </div>
                    <div class="two">
                        <span class="title">جنسیت</span>
                        <div class="gender-category">
                            <input type="radio" name="gender" id="male" >
                            <label for="male">خانم</label>
                            <input type="radio" name="gender" id="female" >
                            <label for="female">آقا</label>
                        </div>
                    </div>
                    <div class="one">
                        <span class="title">وضعیت تاهل</span>
                        <div class="maritalStatus-category">
                            <input type="radio" name="maritalStatus" id="Single" >
                            <label for="Single">مطلقه</label>
                            <input type="radio" name="maritalStatus" id="Married" >
                            <label for="Married">متاهل</label>
                            <input type="radio" name="maritalStatus" id="Divorced" >
                            <label for="Divorced">مجرد</label>
                        </div>
                    </div>
                </div>
                <button class="nextBTN">
                    <i class="uil-navigator"></i>
                    <span class="btnText">بعدی</span>
                </button>
            </div>
        </div>
        <!--    ////////////////////////////////////////////////////////////////////////////////////////////////-->
        <div class="form second">
            <div class="details address">

                <div class="fields">
                    <div class="input-field">
                        <label>شماره موبایل</label>
                        <input type="text" name="PhoneNo" placeholder="شماره موبایل را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>شماره تماس محل سکونت</label>
                        <input type="number" name="LandLineNo" placeholder="شماره تماس را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>آدرس</label>
                        <input type="text" name="Address" placeholder="آدرس محل سکونت ر ا وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>تصویر کارت ملی</label>
                        <input type="file" name="NationalCardPicture" placeholder="تصویر را بارگذاری کنید" required>
                    </div>
                    <div class="input-field">
                        <label>تعداد فرزندان</label>
                        <input type="number" name="ChildrenCount" placeholder="تعداد فرزندان را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>پست الکترونیک</label>
                        <input type="email" name="Email" placeholder="آدرس پست الکترونیک را وارد کنید" required>
                    </div>
                </div>
            </div>
            <div class="details Family">

                <div class="fields">
                    <div class="input-field">
                        <label>عکس پرسنلی</label>
                        <input type="file" name="PersonnelPicture" placeholder="عکس پرسنلی را بارگذاری کنید" required>
                    </div>
                    <div class="input-field">
                        <label>نام کاربری</label>
                        <input type="text" name="Username" placeholder="نام کاربری را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>رمز عبور</label>
                        <input type="password" name="Password" placeholder="رمز عبور را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>نام وظیفه</label>
                        <input type="text" name="Role Name" placeholder="نام وظیفه را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>سطح دسترسی</label>
                        <input type="number" name="Access Level" placeholder="از 1 تا 4 انتخاب کنید" required>
                    </div>
                    <div class="input-field">
                        <label>شخص</label>
                        <input type="text" name="Person" placeholder="نام را وارد کنید" required>
                    </div>
                </div>
                <div class="bottom">
                    <button class="nextBTN">
                        <i class="uil-navigator"></i>
                        <span class="btnText">ثبت</span>
                    </button>
                    <div class="backBTN">
                        <span class="btnText">بازگشت</span>
                        <i class="uil-navigator"></i>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="asetes/js/nextBTN.js"></script>
</body>
</html>
