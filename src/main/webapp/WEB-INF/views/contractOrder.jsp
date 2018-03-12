<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/3/9
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>合同管理</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/contractOrder.js"></script>
</head>
<body>
<%--数据表--%>
<table id="datagird" headerCls="calendar-title"></table>

<%--工具栏--%>
<div id="button">
    <div style="display: inline-block">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="save">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="approve">审核</a>
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="approveByDept">部门审核</a>
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

<div id="dialog">
    <form id="dialog_form" method="post" enctype="multipart/form-data">
        <table style="margin-top: 20px" align="center">
            <%--用于区分跟新操作和保存操作--%>
            <input type="hidden" name="id">
            <%--传递营销人员id--%>
            <input type="hidden" name="seller.id">
            <tr>
                <td>现金客户</td>
                <td><input name="" readonly="true"></td>
            </tr>
            <tr>
                <td>合同金额</td>
                <td><input name="sum"></td>
            </tr>
            <tr>
                <td>付款金额</td>
                <td><input name="money"></td>
            </tr>
            <tr>
                <td>摘&emsp;&emsp;要</td>
                <td><input name="intro"></td>
            </tr>
            <tr>
                <td>附&emsp;&emsp;件</td>
                <td><input id="filebox" class="easyui-filebox" name="myfile" buttontext="浏览"></td>
            </tr>
        </table>
    </form>
</div>

<div id="dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="submit">提交</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

</body>
</html>
