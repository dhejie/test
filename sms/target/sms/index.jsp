<%--
  Created by IntelliJ IDEA.
  User: 何进结
  Date: 2019/6/10
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/1.8.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/sms/static/css/sms.css">
    <title>异步加载</title>
</head>
<body>

<ul>
    <li id="aaa"></li>
</ul>

<button onclick="adddata()">点击</button>
<script>
    function adddata(){

        $.getJSON("MessageServlet",{action:"query"},function(json){
           $.each(json,function(idx,obj){
               console.log(idx)
           })
        });
    }



</script>
</body>
</html>