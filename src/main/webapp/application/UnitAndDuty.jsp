<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" dir="rtl">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <!--                    Css Sidbar                         -->
    <meta http-equiv="X=UA-Compatible" content="ie=edge">
    <meta name="viewport " content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../HCJ/CSS/dashboardForm.css">
    <title>Unit/Duty</title>
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

        <div class="container1" dir="rtl">
            <header>بخش/وظیفه</header>
            <form action="application/UnitAndDutySubmit.do" method="post">
                <div class="form first">
                    <div class="personal">
                        <span class="title">بخش</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>شهر</label>
                                <input type="text" name="City" placeholder="شهر را وارد نمایید" required>
                            </div>
                            <div class="input-field">
                                <label>بخش های مرتبط</label>
                                <input type="" name="RelatedUnits" placeholder="تمامی بخش های مرتبط را وارد نمایید" required>
                            </div>
                            <div class="input-field">
                                <label> نام بخش</label>
                                <input type="text" name="Name" placeholder="نام بخش خود را وارد نمایید" required>
                            </div>
                            <div class="input-field">
                                <label>وظایف</label>
                                <input type="" name="Duties" placeholder="تمامی وظایف را وارد نمایید" required>
                            </div>
                        </div>
                    </div>
                    <div class="personal">
                        <span class="title">وظیفه</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>موقعیت</label>
                                <input type="number" name="Position" placeholder="موقعیت را وارد نمایید" required>
                            </div>
                            <div class="input-field">
                                <label>توضیح وظیفه</label>
                                <input type="text" name="DutyExplanation" placeholder="توضیح را وارد نمایید" required>
                            </div>
                        </div>
                    </div>
                    <button class="leaveBTN">
                        <i class="uil-navigator"></i>
                        <span class="btnText" >تائید</span>
                        <!--                <ion-icon name = "paper-plane"></ion-icon>-->
                    </button>
                </div>
            </form>
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
