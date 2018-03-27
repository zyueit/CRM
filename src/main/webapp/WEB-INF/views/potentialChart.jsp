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
    <script type="text/javascript" src="/js/views/potentialChart.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="datagrid"></table>
<!-- datagrid工具栏按钮 -->
<div id="datagrid_btn">
    <div style="display: inline-block">
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
    </div>
    <div style="display: inline-block;float: right;margin-right: 20px;">
        <form id="search_form">
            销售人员<input name="keyword">
            时间<input name="beginTime" class="easyui-datebox">~<input name="endTime" class="easyui-datebox">
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="querySearch">查询</a>
            分组信息
            <select name="groupBy">
                <option value="月份">月份</option>
                <option value="年份">年份</option>
            </select>
            <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="produce">生成报表</a>
        </form>
    </div>
</div>
<input type="hidden" name="array">
</body>
</html>
