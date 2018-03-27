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
    <title>员工管理</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="employee_datagrid" headerCls="calendar-title"></table>
<!-- datagrid工具栏按钮 -->
<div id="employee_datagrid_btn">
    <div style="display: inline-block">
        <c:if test="${myFn:checkPermission('eon.web.controller.EmployeeController:save')}">
            <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        </c:if>
        <c:if test="${myFn:checkPermission('eon.web.controller.EmployeeController:update')}">
            <a class="easyui-linkbutton" id="employeeEdit" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        </c:if>
        <c:if test="${myFn:checkPermission('eon.web.controller.EmployeeController:dimission')}">
            <a class="easyui-linkbutton" id="employeeRemove" iconCls="icon-remove" plain="true" data-cmd="del">离职</a>
        </c:if>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
    </div>

        <form id="search_form">
            关键字<input name="keyword">
            入职时间<input name="beginTime" class="easyui-datebox">~<input name="endTime" class="easyui-datebox">
            状态
            <select name="state">
                <option value="">全部</option>
                <option value="1">在职</option>
                <option value="0">离职</option>
            </select>
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="querySearch">查询</a>
        </form>
    </div>
    <div style="height: 5px;"></div>
</div>

<!-- 定义添加/编辑对话框 -->
<div id="employee_dialog">
    <form id="dialog_form" method="post">
        <input type="hidden" name="id">
        <table style="margin-top:20px;padding-left: 30px;">
            <tr>
                <td>用&ensp;户&ensp;名</td>
                <td><input name="username"></td>
            </tr>
            <tr>
                <td>真实名字</td>
                <td><input name="realName"></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input name="tel"></td>
            </tr>
            <tr>
                <td>邮&emsp;&emsp;箱</td>
                <td><input name="email"></td>
            </tr>
            <tr>
                <td>部&emsp;&emsp;门</td>
                <td><input id="employee_dept" name="dept.id"></td>
            </tr>
            <tr>
                <td>分配角色</td>
                <td><input id="employee_roles"></td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框的底部按钮 -->
<div id="employee_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
