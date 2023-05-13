<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" dir="rtl">
    <!--                    Css Sidbar                         -->
    <meta http-equiv="X=UA-Compatible" content="ie=edge">
    <meta name="viewport " content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../HCJ/CSS/dashboardTable.css">
    <title>Title</title>
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

            </li>
            <li>
                <a href="#">
                    <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
                    <span class="title">خروج</span>
                </a>
            </li>
        </ul>
    </div>

    <!--    main   -->
    <div class="main">
        <div class="topbar">
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
        <div>
            <table class="content-table">
                <thead>
                <tr>
                    <th>رتبه</th>
                    <th>شماره پرسنلی</th>
                    <th>کد پرسنلی</th>
                    <th>تاریخ</th>
                    <th>تاریخ آخرین پراختی</th>
                    <th>پیوست</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="Retirement" items="${sessionScope.RetiredPersonnelList}">
                <tr class="active-row">
                    <td>${Retirement.id}</td>
                    <td>${Retirement.persin}</td>
                    <td>${Retirement.personalCode}</td>
                    <td>${Retirement.date}</td>
                    <td>${Retirement.lastPayment}</td>
                    <td>${Retirement.attachment}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>


</div>



<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script>

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