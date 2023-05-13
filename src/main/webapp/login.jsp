<%--
  Created by IntelliJ IDEA.
  User: mohammedhossyn
  Date: 5/11/2023
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ورود</title>
    <link rel="stylesheet" href="HCJ/CSS/particles1.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="HCJ/CSS/font-awesome/css/font-awesome.min.css">
</head>
<body>
<div id="particles-js">
    <div id="login" dir="rtl">
        <form action="/login.do" method="post">
                <i class="fa fa-user"></i><label>نام کاربری</label>
                <br>
                <input  type="text" name="username" id="username" placeholder="username" required>
            <br>
                <i class="fa fa-key"></i><label >رمز عبور</label>
                <br>
                <input type="password" id="password" name="password" placeholder="password" required>
            <br>
                <i class="fa fa-cloud" ></i><label> مرا بخاطر بسپار</label>
                <input type="checkbox" name="rememberMe" value="true">
            <br>
            <button type="submit">ورود</button>

        </form>
    </div>
</div>
<script type="application/javascript" src="particlesJs/particles.js"></script>
<script type="application/javascript" src="particlesJs/app.js"></script>
</body>
</html>
