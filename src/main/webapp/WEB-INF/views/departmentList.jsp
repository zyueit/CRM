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
    <title>部门管理</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/department.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="department_datagrid" headerCls="calendar-title"></table>
<!-- datagrid工具栏按钮 -->
<div id="department_datagrid_btn">
    <c:if test="${myFn:checkPermission('eon.web.controller.DepartmentController:save')}">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    </c:if>
    <c:if test="${myFn:checkPermission('eon.web.controller.DepartmentController:update')}">
        <a class="easyui-linkbutton" id="departmentEdit" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </c:if>
    <c:if test="${myFn:checkPermission('eon.web.controller.DepartmentController:disable')}">
        <a class="easyui-linkbutton" id="departmentRemove" iconCls="icon-remove" plain="true" data-cmd="del">停用</a>
    </c:if>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
</div>
<!-- 定义添加/编辑对话框 -->
<div id="department_dialog">
    <form id="dialog_form" method="post">
        <input type="hidden" name="id">
        <table style="margin-top:20px;padding-left: 30px;">
            <tr>
                <td>部门名称</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>部门编码</td>
                <td><input type="text" name="sn"></td>
            </tr>
            <tr>
                <td>部门经理</td>
                <td><input id="department_manager" name="manager.id"></td>
            </tr>
            <tr>
                <td>上级部门</td>
                <td><input id="department_parent" name="parent.id"></td>
            </tr>

        </table>
    </form>
</div>
<!-- 对话框的底部按钮 -->
<div id="department_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
