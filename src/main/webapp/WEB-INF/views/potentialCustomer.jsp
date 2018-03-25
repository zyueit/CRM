<%@ page import="eon.util.UserContext" %>
<%@ page import="eon.domain.Employee" %><%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/3/21
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>潜在客户管理</title>
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/potentialCustomer.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="datagrid" headerCls="calendar-title"></table>
<!-- datagrid工具栏按钮 -->
<div id="datagrid_btn">
    <div style="display: inline-block">
        <%--<c:if test="${myFn:checkPermission('eon.web.controller.Controller:save')}">--%>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <%--</c:if>--%>
        <%--<c:if test="${myFn:checkPermission('eon.web.controller.Controller:update')}">--%>
        <a class="easyui-linkbutton" id="Edit" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" id="Edit" iconCls="icon-tip" plain="true" data-cmd="share">共享</a>
        <a class="easyui-linkbutton" id="Edit" iconCls="icon-tip" plain="true" data-cmd="deliver">移交</a>
        <%--</c:if>--%>
        <%--<c:if test="${myFn:checkPermission('eon.web.controller.Controller:disable')}">--%>
        <a class="easyui-linkbutton" id="Remove" iconCls="icon-remove" plain="true" data-cmd="del">开发失败</a>
        <%--</c:if>--%>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="approve">转正</a>
    </div>
    <div style="display: inline-block;float: right;margin-right: 20px;">
        <form id="search_form">
            关键字<input name="keyword">
            状态
            <select name="state">
                <option value="-5">全部</option>
                <option value="0">潜在客户</option>
                <option value="-1">开发失败</option>
            </select>
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="querySearch">查询</a>
        </form>
    </div>
</div>
<!-- 定义添加/编辑对话框 -->
<div id="dialog">
    <form id="dialog_form" method="post">
        <input type="hidden" name="id">
        <table style="margin-top:30px;padding-left: 150px;">
            <tr>
                <td>客户名称</td>
                <td><input type="text" class="easyui-textbox" name="name"></td>
            </tr>
            <tr>
                <td>年龄</td>
                <td><input type="text" class="easyui-numberbox" name="age"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td><input type="radio" name="gender" value="0">男<input type="radio" name="gender" value="1">女</td>
            </tr>
            <tr>
                <td>电话号码</td>
                <td><input class="easyui-textbox" name="tel"></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input class="easyui-textbox" name="email"></td>
            </tr>
            <tr>
                <td>QQ</td>
                <td><input class="easyui-textbox" name="qq"></td>
            </tr>
            <tr>
                <td>微信</td>
                <td><input class="easyui-textbox" name="wechat"></td>
            </tr>
            <tr>
                <td>职业</td>
                <td><input id="job" name="job.id"></td>
            </tr>
            <tr>
                <td>收入水平</td>
                <td><input id="salaryLevel" name="salaryLevel.id"></td>
            </tr>
            <%--<tr  >--%>
            <%--<td>客户来源</td>--%>
            <%--<td><input id="customerSource" name="customerSource"></td>--%>
            <%--</tr>--%>
        </table>
    </form>
</div>
<!-- 对话框的底部按钮 -->
<div id="dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>


<div id="deliverDialog">
    <form id="deliver_form" method="post">
        <table style="margin-top:30px;padding-left: 125px;">
            <input type="hidden" name="cmd">
            <tr>
                <td>当前潜在客户:</td>
            </tr>
            <tr>
                <td><input type="text" name="name" readonly="true" style="background-color: #c0c0c0"></td>

            </tr>
            <tr>
                <td>当前潜在客户负责人:</td>
            </tr>
            <tr>
                <td><input type="text" name="inChargeUser" readonly="true" style="background-color: #c0c0c0"></td>

            </tr>
            <tr>
                <td>移交给:</td>
            </tr>
            <tr>
                <td><input id="deliver" name="inChargeUser.id"></td>

            </tr>
        </table>
    </form>
</div>

<!-- 对话框的底部按钮 -->
<div id="deliver_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="submit">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel2">取消</a>
</div>
</body>
</html>
