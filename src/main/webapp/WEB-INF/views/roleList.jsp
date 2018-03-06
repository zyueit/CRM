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
    <title>角色管理</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="role_datagrid" headerCls="calendar-title"></table>
<!-- datagrid工具栏按钮 -->
<div id="role_datagrid_btn">
    <c:if test="${myFn:checkPermission('eon.web.controller.RoleController:save')}">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    </c:if>
    <c:if test="${myFn:checkPermission('eon.web.controller.RoleController:update')}">
        <a class="easyui-linkbutton" id="roleEdit" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </c:if>
    <c:if test="${myFn:checkPermission('eon.web.controller.RoleController:disable')}">
        <a class="easyui-linkbutton" id="roleRemove" iconCls="icon-remove" plain="true" data-cmd="del">禁用</a>
    </c:if>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
</div>
<!-- 定义添加/编辑对话框 -->
<div id="role_dialog">
    <form id="dialog_form" method="post">
        <input type="hidden" name="id">
        <table style="margin-top:20px;padding-left: 40px;">
            <tr>
                <td>角色名称<input type="text" name="name"></td>
                <td>角色编码<input type="text" name="sn"></td>
            </tr>
            <tr>
                <td>
                    <table id="selfPermission" headerCls="calendar-title"></table>
                </td>
                <td>
                    <table id="allPermission" headerCls="calendar-title"></table>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框的底部按钮 -->
<div id="role_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
