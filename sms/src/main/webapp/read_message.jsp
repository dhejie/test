<%--
  Created by IntelliJ IDEA.
  User: 何进结
  Date: 2019/6/5
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看消息</title>
    <link rel="stylesheet" type="text/css" href="/sms/static/css/sms.css">
</head>
<body class="read_body">
<div class="read_main">
    <div class="header">
        <span>当前用户: ${userName } </span>


        <a href="LogoutServlet">退出</a>
    </div>
    <div class="content">
        <div>
            发送人： ${user.username}

            标题：
            ${message.subject }
        </div>
        <div>
                <textarea style="width: 400px; height: 300px;" name="content">
                    ${message.content}
                </textarea>
            <br>
            <a href=${message.attachment}>附件</a>
        </div>
        <div>
            <a href="MessageServlet?action=list">返回</a>
        </div>
    </div>
</div>
</body><body>

</body>
</html>
