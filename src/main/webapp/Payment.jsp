<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--                    Css                      -->
    <link rel="stylesheet" href="asetes/css/styleSignUp.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Payment Form</title>
</head>
<body dir="rtl">
<div class="container">
    <header> فرم پرداخت</header>
    <form action="/Payment" method="post">
        <div class="form first">
            <div class="details personal">

                <div class="fields">
                    <div class="input-field">
                        <label>سال</label>
                        <input type="text" name="year" required>
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
                        <label>شماره پرسنلی</label>
                        <input type="number" name="PersonnelCode" placeholder="شماره پرسنلی خود را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>حقوق پایه</label>
                        <input type="number" name="BasicSalary" placeholder="حقوق پایه خود را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>اضافه کاری</label>
                        <input type="datetime-local" name="OverTime" required>
                    </div>

                </div>
            </div>
            <div class="details ID">
                <span class="title">جزییات غیبت</span>

                <div class="fields">
                    <div class="input-field">
                        <label>شمارش حقوق اضافه کاری</label>
                        <input type="number" name="OverTimePayCount" placeholder="تعداد را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>زمان کارکرد</label>
                        <input type="datetime-local" name="OperationTime" required>
                    </div>
                    <div class="input-field">
                        <label>غیبت</label>
                        <input type="datetime-local" name="AbsenceTime" required>
                    </div>
                    <div class="input-field">
                        <label>مسکن</label>
                        <input type="number" name="Housing" placeholder="مسکن خود را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>شمارش پرداخت کارکرد</label>
                        <input type="number" name="OperationPayCount" placeholder="تعداد را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>شمارش حقوق حین غیبت</label>
                        <input type="number" name="AbsencepayCount" placeholder="تعداد را وارد نمایید" required>
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
                        <label> بهره وری</label>
                        <input type="text" name="Benefits" placeholder=" بهره وری وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>کسر حقوق روز های مرخصی</label>
                        <input type="number" name="LeaveDaysPayDeduction" placeholder="Please Enter Your LeaveDays Pay Deduction" required>
                    </div>
                    <div class="input-field">
                        <label>مرخصی</label>
                        <input type="list" name="LeaveDays" placeholder="مرخصی را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>حق سنوات</label>
                        <input type="number" name="SeverancePay" placeholder="سنوات خود را وراد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>هزینه کودک</label>
                        <input type="number" name="Children Pay" placeholder="Pهزینه را وراد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>پاداش مدیریت</label>
                        <input type="number" name="ManagementBonus" placeholder="پاداش مدیریت را وراد نمایید" required>
                    </div>
                </div>
            </div>
            <div class="details Family">
                <span class="title">جزئیات پرداخت</span>

                <div class="fields">
                    <div class="input-field">
                        <label>بدهی</label>
                        <input type="number" name="Debt" placeholder="بدهی در یک سال را وراد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>مالیات</label>
                        <input type="number" name="Tax" placeholder="مالیات را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>بیمه</label>
                        <input type="number" name="Insurance" placeholder="بیمه را وارد نمایید" required>
                    </div>
                    <div class="input-field">
                        <label>مجموع پرداختی</label>
                        <input type="number" name="TotalPayment" placeholder="مجموع پرداختی را وارد کنید" required>
                    </div>
                    <div class="input-field">
                        <label>شماره تراکنش</label>
                        <input type="number" name="Transaction Number" placeholder="شماره تراکنش را وراد نمایید" required>
                    </div>
                    <!--                    <div class="input-field">-->
                    <!--                        <label>رنگ وضعیت</label>-->
                    <!--                        <input type="color"  required>-->
                    <!--                    </div>-->
                </div>
                <div class="bottom">
                    <button class="nextBTN">
                        <i class="uil-navigator"></i>
                        <span class="btnText">تائید</span>
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
