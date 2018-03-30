<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/3/28
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考勤</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/attence.js"></script>
</head>
<body>
<%--数据表--%>
<table id="datagrid"></table>
<div id="button" align="right">
    <form id="form" action="/attence_down" method="post">
        <input type="hidden" name="data"/>
        <%--<input type="submit">--%>
    </form>
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="produce">导出</a>
</div>
</body>
</html>
