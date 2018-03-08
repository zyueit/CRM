<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/3/7
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单管理</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/depositOrder.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="depositOrder_datagrid" headerCls="calendar-title"></table>

<%--工具栏--%>
<div id="depositOrder_button">
    <div style="display: inline-block">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="save">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="approve">审核</a>
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="approveDept">部门审核</a>
        <%--<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="approveByFinance">财务审核</a>--%>
    </div>
    <div style="display: inline-block;margin-right: 2px;float: right">
        <form id="search_form" method="post">
            关键字<input name="keyword" placeholder="营销人员或定金客户">
            签订时间<input name="beginTime" class="easyui-datebox">~<input name="endTime" class="easyui-datebox">
            订单状态
            <select name="status">
                <option value="-1">全部</option>
                <option value="0">初始录入</option>
                <option value="1">已审核</option>
                <option value="3">已出合同</option>
            </select>
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="querySearch">查询</a>
        </form>
    </div>
</div>

<div id="depositOrder_dialog">
    <form id="dialog_form" method="post">
        <table style="margin-top:20px;padding-left: 30px;">
            <%--用于区分跟新操作和保存操作--%>
            <input type="hidden" name="id">
            <%--传递营销人员id--%>
            <input type="hidden" name="seller.id">
            <tr>
                <td>定金客户</td>
                <td><input name="" readonly="true"></td>
            </tr>
            <tr>
                <td>总金额</td>
                <td><input name="totalSum"></td>
            </tr>
            <tr>
                <td>定金金额</td>
                <td><input name="sum"></td>
            </tr>
            <tr>
                <td>摘&emsp;&emsp;要</td>
                <td><input name="intro"></td>
            </tr>
            <tr>
                <td>附&emsp;&emsp;件</td>
                <td><input name="file"></td>
            </tr>
        </table>
    </form>
</div>

<%--消息框按钮--%>
<div id="depositOrder_dialog_button">
    <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="commit">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
</div>

</body>
</html>
