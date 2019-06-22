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
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/sms/static/css/sms.css">
</head>
<body>
    <div class="login">
        <div class="header"></div>
        <div class="main">
            <form method="post" action="LoginServlet">
                <div>
                    <label for="username">用户名:</label>
                    <input type="text" id="username" name="username">
                </div>
                <div>
                    <label for="password">密&nbsp;&nbsp;&nbsp;码:</label>
                    <input type="password" id="password" name="password">
                </div>
                <div>
                    <input type="submit" value=" ">
                    <input type="button" onclick="to_reg()" value=" ">
                </div>

            </form>
        </div>
    </div>
</body>
<script type="text/javascript">
    function to_reg() {
        window.location.href = 'register.jsp';
    }
</script>
</html>
