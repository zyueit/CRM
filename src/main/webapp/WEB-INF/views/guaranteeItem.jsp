<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/3/16
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>保修单明细</title>

    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript">
        $(function () {
            var url = document.URL;
            var id = url.substr(url.length - 2, url.length);

            var datagird = $("#datagird");
            datagird.datagrid({
                title: "保修单明细",
                fitColumns: true,
                fit: true,
                columns: [[
                    {field: "id", title: "编号", align: "center", width: 10},
                    {field: "content", title: "保修内容", align: "center", width: 10},
                    {field: "guaranteeTime", title: "保修时间", align: "center", width: 10},
                    {field: "a", title: "保修费用", align: "center", width: 10},
                    {field: "remark", title: "备注", align: "center", width: 10},
                    {field: "status", title: "保修状态", align: "center", width: 10},
                ]]
            })

            $.get("guaranteeItem?id=" + id, function (data) {
                if (data.length == 0) {
                    $.messager.alert("温馨提示", "此保修单没有保修明细！", "warning")
                } else {
                    datagird.datagrid({"data": data});
                }
            }, "json");
        });
    </script>
</head>
<body>
<table id="datagird" headerCls="Calendar-title"></table>
</table>
</body>
</html>
