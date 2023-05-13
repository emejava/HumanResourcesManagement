<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X=UA-Compatible" content="ie=edge">
    <meta name="viewport " content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../HCJ/CSS/dashboard1.css">
    <title>central recruitment</title>
</head>
<body>
<div class="container" dir="rtl">
    <div class="navigation">
        <ul>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="earth-outline"></ion-icon></span>
                    <span class="title">نام برند</span>
                </a>
            </li>
            <li>
                <a href="/application/Dashboard.jsp">
                    <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                    <span class="title">داشبورد</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
                    <span class="title">پروفایل</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="chatbubbles-outline"></ion-icon></span>
                    <span class="title">گزارشات</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="help-circle-outline"></ion-icon></span>
                    <span class="title">اخبار</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="cog-outline"></ion-icon></span>
                    <span class="title">ورود</span>
                </a>
            </li>

            <li>
                <a href="/application/SignUp.jsp">
                    <span class="icon"><ion-icon name="cog-outline"></ion-icon></span>
                    <span class="title">ثبت نام</span>
                </a>
            </li>

            <li>
                <a href="/application/CentralRecruitment.jsp">
                    <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                    <span class="title">کارگزینی مرکزی</span>
                </a>
                <!--          list to list (not compelete)-->

            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
                    <span class="title">خروج</span>
                </a>
            </li>
        </ul>
    </div>
</div>

<!--    main   -->
<div class="main">
    <div class="topbar" dir="rtl">
        <div class="toggle">
            <ion-icon name="menu-outline"></ion-icon>
        </div>


        <div class="search">
            <label>
                <input type="text" placeholder="اینجا جست و جو کنید">
                <ion-icon name="search-outline"></ion-icon>
            </label>
        </div>

        <!--            userImg-->

        <div class="user">
            <img src="../user.jpg">
        </div>
    </div>
</div>
<!--     inner menu  -->
<nav>
    <div>
        <ul dir="rtl">
            <li><a href="#"><i class="fa fa-chevron-left" aria-hidden="true">پرونده ها</i>
            </a>
                <ul>
                    <li><a href="/application/EmploymentList.jsp">استخدامی ها</a></li>
                    <li><a href="/application/FiredPersonnel.jsp">اخراجی ها</a></li>
                    <li><a href="/application/ResignConfirmationList.jsp">استعفا ها</a></li>
                    <li><a href="/application/RetiredPersonalsList.jsp">بازنشتگان</a></li>
                    <li><a href="/application/LeaveDaysConfirmationList.jsp">مرخصیات</a></li>
                    <li><a href="/application/PaySlips.jsp">فیشهای حقوقی</a></li>
                    <li><a href="/application/ShowUnitWithDutyAndPosition.jsp">واحدات و وظایف</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="fa fa-chevron-left" aria-hidden="true">فرم ها</i>
            </a>
                <ul>
                    <li><a href="/application/Dismissal.jsp">اخراج</a></li>
                    <li><a href="/application/Resignation.jsp">استعفا</a></li>
                    <li><a href="/application/Employment.jsp">استخدام</a></li>
                    <li><a href="/application/Retirement.jsp">بازنشتگی</a></li>
                    <li><a href="/application/Payment.jsp">حقوق و مزایا</a></li>
                    <li><a href="/application/LeaveDays.jsp">مرخصی ساعتی</a></li>
                    <li><a href="/application/UnitAndDuty.jsp">واحدات و سمت</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="fa fa-chevron-left" aria-hidden="true">تاییده ها</i>
            </a>
                <ul>
                    <li><a href="#">تاییدات مرخصی</a></li>
                    <li><a href="#">تاییدات استعفا</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>




<script dir="rtl" type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script dir="rtl" nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script dir="rtl">

    <!--    MenuToggle-->
    let toggle = document.querySelector('.toggle');
    let navigation = document.querySelector('.navigation');
    let main = document.querySelector('.main');


    toggle.onclick = function (){
        navigation.classList.toggle('active');
        main.classList.toggle('active');
    }
    <!--    add hovered class in selected list item-->
    let list = document.querySelectorAll('.navigation li');
    function activeLink(){
        list.forEach((item) =>
            item.classList.remove('hovered'));
        this.classList.add('hovered');
    }
    list.forEach((item) =>
        item.addEventListener('mouseover', activeLink));
</script>
</body>
</html>
