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
    <script type="text/javascript" src="/js/views/systemLog.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="systemLog_datagrid" headerCls="calendar-title"></table>
<!-- datagrid工具栏按钮 -->
<div id="systemLog_datagrid_btn">
    <div style="display: inline-block">
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
    </div>
    <div style="height: 5px;"></div>
</div>
</body>
</html>
