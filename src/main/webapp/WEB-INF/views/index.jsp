<%@ page import="eon.util.UserContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户关系管理系统</title>
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script> <!-- jQuery核心库 -->
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>  <!-- EasyUI核心库 -->
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <link rel="stylesheet" href="/css/public.css">
    <script type="text/javascript" src="/js/views/index.js"></script>
    <%--<script type="text/javascript" src="/js/views/welcome.js"></script>--%>
    <style type="text/css">
        .btn-logout {
            margin-left: 10px;
            background: #ff9900 none repeat scroll 0 0;
            border-radius: 6px;
            color: #fefefe;
            display: inline;
            padding: 6px;
            text-decoration: none;
        }

        a:hover {
            margin-left: 10px;
            background: #08b72a none repeat scroll 0 0;
            border-radius: 6px;
            color: #3bd7ff;
            display: inline;
            padding: 6px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div data-options="region:'north'"
         style="height:100px;background: url('/img/bg.jpg') no-repeat;background-size:cover;">
        <h1 style="position: relative;top: 30px;">客户关系管理系统</h1>
        <div id="top">
            <div id="top_links">
                <div style="float: right;width: 200px;margin-top: 10px;">
                    <p>
                        <span style="font-size: 14px;color: #ff9900;margin-right: 10px">当前用户:</span>
                        ${sessionScope[UserContext.USER_IN_SESSION].username}
                        <a href="/user/logout" class="btn-logout">安全退出</a>
                    </p>
                </div>

            </div>
        </div>
    </div>
    <div data-options="region:'west'" style="width:180px;">
        <div class="easyui-accordion" fit="true">
            <div title="菜单">
                <!-- 使用树组件来定义菜单 -->
                <ul id="menuTree"></ul>
            </div>
            <div title="帮助"></div>
            <div title="简介"></div>
        </div>
    </div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <!-- 正文内容 -->
        <div id="myTabs" class="easyui-tabs" fit="true">
            <div title="欢迎页" closable="true">
                <div style="text-align: center;"><h1>欢迎登陆系统</h1></div>
            </div>
        </div>
    </div>
    <div data-options="region:'south'"
         style="height:30px;  background: url('/img/bg.jpg') no-repeat; background-size: cover">
        <div style="text-align: center;">Copyright ©2015-2016 XXX科技有限公司 (<span style="color: #0000FF;">版权所有,侵权必究</span>)
        </div>
    </div>
</div>
</body>
</html>
