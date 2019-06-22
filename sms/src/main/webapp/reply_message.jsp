<%--
  Created by IntelliJ IDEA.
  User: 何进结
  Date: 2019/6/6
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/sms/static/css/sms.css">

    <title>回消息页面</title>
</head>
<body class="main_body">
<div class="main">
    <div class="header">
        <span>当前用户: ${userName } </span>

        <a href="LogoutServlet">退出</a>
    </div>
    <div class="content">
        <form method="post" action="MessageServlet?action=send" enctype="multipart/form-data">
<%--            <input type="hidden" name="action" value="send">--%>
            <div>
                发送给：
                <select name="to_id">
                    <option value="${user.id}"> ${user.username}</option>
                </select>

                标题：
                <input type="text" name="subject" />
            </div>
            <div>
                <textarea style="width: 400px; height: 300px;" name="content">

                </textarea>
            </div>
            请选择附件: <input type="file" name="file">
            <div>
                <input type="submit" value="发送">
            </div>
        </form>
    </div>
</div>
</body>
</html>
