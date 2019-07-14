<%--
  Created by IntelliJ IDEA.
  User: 何进结
  Date: 2019/6/5
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/1.8.1/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/sms/static/css/sms.css">
    <title>主页面</title>
</head>
<% //response.setHeader("refresh","3"
//);%>
<body class="main_boby">
<div class="main">
    <div class="main_title"></div>
    <div class="main_header">
        当前用户：${userName }
        <a href="MessageServlet?action=to_send">发消息</a>
        <a href="LogoutServlet">退出</a>
    </div>
       <div class="content">

<%--           <h:if test="${message.status!=3}">--%>


        <ul>
           <h:forEach var="message" items="${messageList}">
<%--               <li>--%>

<%--                   <div class="main_img_span">--%>
<%--                   <h:if test="${message.status==2}">--%>
<%--                       <img src="/sms/static/images/sms_unReaded.png">--%>
<%--                   </h:if>--%>
<%--                   <h:if test="${message.status==1}">--%>
<%--                       <img src="/sms/static/images/sms_readed.png">--%>
<%--                   </h:if>--%>

<%--                   <span onclick="readMsg('${message.id}')" style="cursor: pointer;">${message.subject}</span>--%>
<%--                   </div>--%>
<%--                   <a href="MessageServlet?action=delete&id=${message.id}">删除</a>--%>
<%--                   <a href="MessageServlet?action=reply&id=${message.id}">回信</a>--%>
<%--                   ${message.createtime}--%>
<%--                  <div class="main_div_hr"> <hr class="main_hr"></div>--%>
<%--               </li>--%>
<%--           </h:if>--%>

               <li id="updata">

           </li>
           </ul>

<%--               </h:if>--%>
       </div>
    </div>
</body>
<script type="text/javascript">
    function readMsg(id) {
        window.location.href = 'MessageServlet?action=read&id='+id;
    }
    // setTimeout("location.href='MessageServlet?action=list'",10000)
    //全局刷新
    setInterval(dd(),5000);
    function dd(){
        $.ajax({
            type: 'get',
            async: 'false',
            url: 'updataServlet',
            action:'list',
            dataType: 'json',
            contentType: 'application/json',
            "success":callack

        })

    }
    function callack(json) {
        $("#updata").html("<li>"+""+"</li>");
        $.each(json, function(x,y) {
            if(y.status!=3){
                if(y.status==1) {


                $("#updata").append("<img src='/sms/static/images/sms_readed.png'>" + "<li>" +
                    "<span onclick='readMsg(${message.id})' style='cursor: pointer;'>"+ y.subject +"</span>"+ y.createtime +
                 "<a href='MessageServlet?action=delete&id=${message.id}'> "+"删除"+"</a>"+
                    "<a href='MessageServlet?action=reply&id=${message.id}'>"+"回信"+"</a>"+"</li>");

                }
                if(y.status==2) {


                $("#updata").append("<img src='/sms/static/images/sms_unReaded.png'>" + "<li>" +
                    "<span onclick='readMsg(${message.id})' style='cursor: pointer;'>"+ y.subject+"</span>" + y.createtime +
                    "<a href='MessageServlet?action=delete&id=${message.id}'> "+"删除"+"</a>"+
                    "<a href='MessageServlet?action=reply&id=${message.id}'>"+"回信"+"</a>"+"</li>");

                }
            }

        });
    }

</script>
</h:forEach>
</html>
