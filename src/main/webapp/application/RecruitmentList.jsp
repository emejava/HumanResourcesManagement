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
            <table class="content-table" dir="rtl">
                <thead>
                <tr>
                    <th>رتبه</th>
                    <th>شماره پرسنلی</th>
                    <th>تحصیلات</th>
                    <th>رشته تحصیلی</th>
                    <th>محل تحصیل</th>
                    <th>سابقه کاری</th>
                    <th>شفل سابق</th>
                    <th>علت خروج از آخرین شغل</th>
                    <th>نشانی محل کار سابق</th>
                    <th>شماره تماس محل کار سابق</th>
                    <th>شیفت کاری</th>
                    <th>حقوق پیشنهادی</th>
                    <th>قبول</th>
                    <th>عدم پذیرش</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="Recruitment" items="${sessionScope.RecruitmentList}">
                <tr class="active-row">
                    <td>${Recruitment.id}</td>
                    <td>${Recruitment.person}</td>
                    <td>${Recruitment.education}</td>
                    <td>${Recruitment.fieldOfStudy}</td>
                    <td>${Recruitment.educationPlace}</td>
                    <td>${Recruitment.workExperience}</td>
                    <td>${Recruitment.lastJob}</td>
                    <td>${Recruitment.lastJobExitReason}</td>
                    <td>${Recruitment.lastJobAdders}</td>
                    <td>${Recruitment.lastJobNo}</td>
                    <td>${Recruitment.shiftWork}</td>
                    <td>${Recruitment.requestedSalary}</td>
                    <td class="Active"><div class="input1"><input type="checkbox"></div></td>
                    <td class="InActive"><div class="input2"><input type="checkbox"></div></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


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
