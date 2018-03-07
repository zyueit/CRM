<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://zyueit.com/java/crm" prefix="myFn" %>
<html>
<head>
    <title>客户关系管理系统</title>
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.min.js"></script> <!-- jQuery核心库 -->
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>  <!-- EasyUI核心库 -->
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/js/views/systemDictionary.js"></script>
</head>
<body>
<%-- 布局 --%>
<div class="easyui-layout" fit="true">
    <div title="字典目录" data-options="region:'west'" style="width:50%;">
        <%-- 字典目录数据表格 --%>
        <table id="systemDictionary_datagrid"></table>
    </div>
    <div data-options="region:'center'" style="padding-left:5px;background:#eee;">
        <%-- 字典明细数据表格 --%>
        <table id="systemDictionaryItem_datagrid"></table>
    </div>
</div>
<%--明细数据表格的菜单按钮--%>
<div id="systemDictionaryItem_btn">
    <c:if test="${myFn:checkPermission('eon.web.controller.SystemDictionaryController:saveItem')}">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    </c:if>
    <c:if test="${myFn:checkPermission('eon.web.controller.SystemDictionaryController:updateItem')}">
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </c:if>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
</div>
<%--添加/编辑对话框--%>
<div id="systemDictionaryItem_dialog">
    <form id="dialog_form" method="post">
        <input type="hidden" name="id">
        <input type="hidden" name="parent.id">
        <table style="margin-top:20px;padding-left: 40px;">
            <tr>
                <td>字典明细名称</td>
                <td><input name="name"></td>
            </tr>
            <tr>
                <td>字典明细简介</td>
                <td><input name="intro"></td>
            </tr>
        </table>
    </form>
</div>
<%--对话框底部按钮--%>
<div id="systemDictionaryItem_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
