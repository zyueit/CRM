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
    <title>保修单管理</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/guarantee.js"></script>
</head>
<body>
<input type="hidden" name="var">
<%-- 布局 --%>
<div class="easyui-layout" fit="true">
    <div data-options="region:'center'">
        <div title="保修单" data-options="region:'north'" style="height: 100%">
            <table id="datagird" headerCls="calendar-title"></table>
        </div>
        <%--<div data-options="region:'south'" title="保修单明细" style="height: 50%">--%>
        <%--<table id="datagird_item" headerCls="calendar-title"></table>--%>
        <%--</div>--%>
    </div>
</div>

<!-- datagrid工具栏按钮 -->
<div id="datagird_btn">
    <div style="display: inline-block">
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="search_items">查询明细</a>
    </div>
    <%--<div style="display: inline-block;float: right;margin-right: 20px;">--%>
    <%--<form id="search_form">--%>
    <%--关键字<input name="keyword">--%>
    <%--入职时间<input name="beginTime" class="easyui-datebox">~<input name="endTime" class="easyui-datebox">--%>
    <%--状态--%>
    <%--<select name="state">--%>
    <%--<option value="">全部</option>--%>
    <%--<option value="1">在职</option>--%>
    <%--<option value="0">离职</option>--%>
    <%--</select>--%>
    <%--<a class="easyui-linkbutton" iconCls="icon-search" data-cmd="querySearch">查询</a>--%>
    <%--</form>--%>
    <%--</div>--%>
    <%--<div style="height: 5px;"></div>--%>
</div>

<!-- 定义添加/编辑对话框 -->
<div id="dialog">
    <form id="dialog_form" method="post">
        <input type="hidden" name="id">
        <table style="margin-top:20px;padding-left: 30px;">
                <tr style="line-height: 50px">
                    <td>产品名称</td>
                    <td><input name="productName"></td>
                </tr>
                <tr>
                    <td>备&emsp;&emsp;注</td>
                    <td><input name="remark"></td>
                </tr>
        </table>
    </form>
</div>

<!-- 对话框的底部按钮 -->
<div id="dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
