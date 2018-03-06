<%--
  Created by IntelliJ IDEA.
  User: ming19
  Date: 2018/1/31
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://zyueit.com/java/crm" prefix="myFn" %>
<html>
<head>
    <title>权限列表</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/permission.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="permission_datagrid" headerCls="calendar-title"></table>
<!-- datagrid工具栏按钮 -->
<div id="permission_datagrid_btn">
    <c:if test="${myFn:checkPermission('eon.web.controller.PermissionController:load')}">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="load">加载权限</a>
    </c:if>
    <c:if test="${myFn:checkPermission('eon.web.controller.PermissionController:delete')}">
        <a class="easyui-linkbutton" id="permissionRemove" iconCls="icon-remove" plain="true" data-cmd="del">删除</a>
    </c:if>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
</div>
</body>
</html>
