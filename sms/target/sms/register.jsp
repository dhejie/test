<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2019/6/4
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/1.8.1/jquery.min.js"></script>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="/sms/static/css/sms.css">
</head>
<body>
    <div class="register">
        <div class="header"></div>
        <div class="main">
            <form method="post" action="UserServlet?action=register" onsubmit="return check();">
<%--                <input type="hidden" value="register" name="action" >--%>
                <div>
                    <label for="account">账号:</label>
                    <input type="text" id="account" name="account" onchange="den()">
                </div>


                <div>
                    <label for="username">用户名:</label>
                    <input type="text" id="username" name="username">
                </div>
                <div>
                    <label for="password">密码:</label>
                    <input type="password" id="password" name="password">
                </div>
                <div>
                    <label for="password">确认密码:</label>
                    <input type="password" id="re_pwd">
                </div>
                <div>
                    <label for="password">邮箱:</label>
                    <input type="email" id="email" name="email">
                </div>
                <div>
                    <input type="submit" value=" ">
                    <input type="button" value=" ">
                </div>
                <div>
                    <a href="login.jsp">回到首页</a>
                </div>
            </form>
        </div>
    </div>
</body>
<script type="text/javascript">
    function checkForm() {
        var password = document.getElementById("password").value;
        var re_pwd = document.getElementById("re_pwd").value;
        if(password != re_pwd) {
            alert('密码有误！');
            return false;
        }
        return true;
    }
    //判断用户名是否存在  如果存在提示下并且清空输入内容
    function den() {
        var name=document.getElementById("account").val;
        console.log(name)
        $.getJSON("UserServlet",{name:name},function(json){
        if(json==false){
        alert("用户已存在！")
        $("#account").val("");

    }
        });

    }
</script>
</html>
