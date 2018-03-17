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
    <script type="text/javascript" src="/js/views/guaranteeItem.js"></script>
</head>
<body>
<table id="datagird" headerCls="Calendar-title"></table>

<!-- datagrid工具栏按钮 -->
<div id="datagird_btn">
    <div style="display: inline-block">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
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
            <%--<tr style="line-height: 50px">--%>
                <%--<td>产品名称</td>--%>
                <%--<td><input name="productName"></td>--%>
            <%--</tr>--%>
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
</table>
</body>
</html>
