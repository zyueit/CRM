<%@ page import="eon.util.UserContext" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客户关系管理系统</title>
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/views/login.js"></script>
</head>
<body>
<%
    Object user = session.getAttribute(UserContext.USER_IN_SESSION);
    if (user != null) {
        response.sendRedirect("/index");
    }
%>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form method="post">
            <p><input type="text" name="username" value="" placeholder="账号"></p>
            <p><input type="password" name="password" value="" placeholder="密码"></p>
            <p class="submit">
                <input type="button" value="登录" onclick="subForm()">
                <input type="button" value="重置" onclick="resetForm()">
            </p>
        </form>
    </div>
</section>
<div style="text-align:center;" class="login-help">
    <p>Copyright ©2017 XXX科技有限公司</p>
</div>
</body>
</html>