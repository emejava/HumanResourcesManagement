<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" dir="rtl">
    <meta http-equiv="X=UA-Compatible" content="ie=edge">
    <meta name="viewport " content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../HCJ/CSS/dashboard1.css">
    <title>Dashboard1</title>
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


        <!--        cards-->
        <div class="cardBox">
            <div class="card">
                <div>
                    <div class="numbers">1,504</div>
                    <div class="cardName">بازدید روزانه</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="eye-outline"></ion-icon>
                </div>
            </div>
            <div class="card">
                <div>
                    <div class="numbers">%49</div>
                    <div class="cardName">درصد تحقق هدف</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="infinite-outline"></ion-icon>
                </div>
            </div>
            <div class="card">
                <div>
                    <div class="numbers">284</div>
                    <div class="cardName">استخدامی ها</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="chatbubble-ellipses-outline"></ion-icon>
                </div>
            </div>

            <div class="card">
                <div>
                    <div class="numbers">7,842</div>
                    <div class="cardName">کارمندان</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="people-outline"></ion-icon>
                </div>
            </div>

        </div>


        <div class="details">
            <!--order details list-->
            <div class="recentOrders">
                    <h2 style="font-family: Shabnam">اخبار</h2>
                <table>
                    <c:forEach var="news" items="${sessionScope.newsList}">
                    <tbody>
                    <tr>
                        <td>${news.subject}</td>
                        <td>${news.title}</td>
                        <td><span class="">بیشتر</span><p style="display: none">${news.id}</p></td>
                    </tr>
                    </tbody>
                    </c:forEach>

                </table>
            </div>

            <!--     new customer    -->
<%--            <div class="recentCustomers">--%>
<%--                <div class="cardHeader">--%>
<%--                    <h2>Recent Customers</h2>--%>
<%--                </div>--%>
<%--                <table>--%>
<%--                    <tr>--%>
<%--                        <td width="60px"><div class="imgBx"><img src="../customer1.jpg"></div></td>--%>
<%--                        <td><h4>Emily<br><span>Blunt</span></h4></td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td width="60px"><div class="imgBx"><img src="../customer2.png"></div></td>--%>
<%--                        <td><h4>Jason<br><span>Mcclure</span></h4></td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td width="60px"><div class="imgBx"><img src="../customer3.png"></div></td>--%>
<%--                        <td><h4>Ashley<br><span>Price</span></h4></td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td width="60px"><div class="imgBx"><img src="../customer4.jpg"></div></td>--%>
<%--                        <td><h4>Christian<br><span>Mendoza</span></h4></td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td width="60px"><div class="imgBx"><img src="../customer5.png"></div></td>--%>
<%--                        <td><h4>Megan<br><span>George</span></h4></td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td width="60px"><div class="imgBx"><img src="../customer6.png"></div></td>--%>
<%--                        <td><h4>Amy<br><span>Rogers</span></h4></td>--%>
<%--                    </tr>--%>
<%--                </table>--%>
<%--            </div>--%>







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