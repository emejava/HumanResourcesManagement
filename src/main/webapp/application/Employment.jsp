<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" dir="rtl">
    <!--                    Css for select                     -->
    <link rel="stylesheet" href="../HCJ/CSS/styleSelect.css">
    <!--                    Css Icon                 -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <!--                    Css Sidbar                         -->
    <meta http-equiv="X=UA-Compatible" content="ie=edge">
    <meta name="viewport " content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../HCJ/CSS/dashboardForm.css">
    <title>Employment Form</title>
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

        <div class="container1"  dir="rtl">
            <header>فرم استخدام</header>
            <form action="application/employmentForm.do" method="post">
                <div class="form first">
                    <div class="personal">
                        <div class="fields">
                            <div class="input-field">
                                <label>واحد</label>
                                <input type="number" name="Unit" placeholder="واحد خود را وارد کنید" required>
                            </div>
                            <div class="input-field">
                                <label>نوع استخدام</label>
                                <input type="text" name="EploymentType" placeholder="نوع استخدام و انتخاب کنید" required>
                            </div>
                            <div class="input-field">
                                <label>شماره پرسنلی</label>
                                <input type="number" name="Person" placeholder="شماره پرسنلی خود را وارد کنید" required>
                            </div>

                            <div class="input-field">
                                <label>تاریخ شروع به کار</label>
                                <input type="datetime-local" name="StartWorkingDate"  required>
                            </div>
                            <div class="input-field">
                                <label>موقعیت شغلی</label>
                                <input type="number" name="Position" placeholder="موقعیت شغلی خود را وارد کنید"  required>
                            </div>
                            <div class="input-field">
                                <label>وظیفه</label>
                                <input type="number" name="Duty" placeholder="وظیفه خود را وارد کنید"  required>

                            </div>

                            <div class="input-field">
                                <label>شیفت</label>
                                <input type="text" name="ShiftWork" placeholder="شیفت خود را انتخماب کنید"  required>
                            </div>
                            <div class="input-field">
                                <select class="select" name="PersonId">
                                    <c:forEach var="person" items="${sessionScope.personList}">
                                        <option value="personalOfEmployment">
                                            Personal Of Employment
                                        </option>
                                        <option value="personalOfEmployment">
                                                ${person.firstName}${" "}${person.lastName}${" "}${person.id}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field">
                                <select class="select" name="unit">
                                    <c:forEach var="unit" items="${sessionScope.unitList}">
                                        <option value="unit">
                                            unit Of Employment
                                        </option>
                                        <option value="unit">
                                                ${unit.id}${" "}${unit.name}${" "}${unit.duties}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field">
                                <select class="select" name="duty">
                                    <c:forEach var="duty" items="${sessionScope.dutyList}">
                                        <option value="duty">
                                            Duty Of Employment
                                        </option>
                                        <option value="duty">
                                                ${duty.id}${" "}${duty.position}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field">
                                <select class="select" name="position">
                                    <c:forEach var="position" items="${sessionScope.positionList}">
                                        <option value="position">
                                            position Of Employment
                                        </option>
                                        <option value="position">
                                                ${position.id}${" "}${position.name}
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