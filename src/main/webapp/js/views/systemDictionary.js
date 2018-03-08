$(function () {
    var systemDictionaryDatagrid = $("#systemDictionary_datagrid");
    var systemDictionaryItemDatagrid = $("#systemDictionaryItem_datagrid");
    var systemDictionaryItemDialog = $("#systemDictionaryItem_dialog");
    var dialogForm = $("#dialog_form");

    //字典目录数据表格初始化
    systemDictionaryDatagrid.datagrid({
        fit: true,
        url: '/systemDictionary_list',
        method: 'get',
        rownumbers: true,
        fitColumns: true,
        singleSelect: true,
        onClickRow: function () {
            var parent = systemDictionaryDatagrid.datagrid("getSelected");
            var option = systemDictionaryItemDatagrid.datagrid("options");
            option.url = '/systemDictionary_listItem';
            systemDictionaryItemDatagrid.datagrid("load", {'id': parent.id});
        },
        columns: [[
            {
                title: '字典编码',
                field: 'sn',
                width: 1,
                align: 'center'
            }, {
                title: '字典名称',
                field: 'name',
                width: 1,
                align: 'center'
            }, {
                title: '字典简介',
                field: 'intro',
                width: 1,
                align: 'center'
            }
        ]]
    });
    //明细数据表格初始化
    systemDictionaryItemDatagrid.datagrid({
        title: '字典明细',
        fit: true,
        method: 'get',
        rownumbers: true,
        fitColumns: true,
        singleSelect: true,
        toolbar: '#systemDictionaryItem_btn',
        onClickRow: function () {

        },
        columns: [[
            {
                title: '字典明细名称',
                field: 'name',
                width: 1,
                align: 'center'
            }, {
                title: '字典明细简介',
                field: 'intro',
                width: 1,
                align: 'center'
            }
        ]]
    });
    //新增/编辑对话框的初始化
    systemDictionaryItemDialog.dialog({
        width: 350,
        height: 180,
        top: 200,
        modal: true,
        closed: true,
        doSize: true,
        buttons: '#systemDictionaryItem_dialog_btn'
    });
    //操作方法集中管理
    var cmdObj = {
        /** 字典明细新增 */
        add: function () {
            dialogForm.form("clear");
            var parent = systemDictionaryDatagrid.datagrid("getSelected");
            if (parent) {
                dialogForm.find("[name='parent.id']").val(parent.id);
                systemDictionaryItemDialog.dialog("open");
                systemDictionaryItemDialog.dialog("setTitle", "新增字典明细");
            } else {
                $.messager.alert("温馨提示", "请先在左边选择一个字典目录！");
            }
        },
        /** 字典明细编辑 */
        edit: function () {
            var select = systemDictionaryItemDatagrid.datagrid("getSelected");
            if (select) {
                dialogForm.form("clear");
                systemDictionaryItemDialog.dialog("open");
                systemDictionaryItemDialog.dialog("setTitle", "编辑字典明细");
                dialogForm.form("load", select);
            } else {
                $.messager.alert("温馨提示", "请选择要编辑的字典明细！");
            }
        },
        /** 刷新 */
        refresh: function () {
            systemDictionaryItemDatagrid.datagrid("reload");
        },
        /** 保存或更新 */
        save: function () {
            var url;
            var eid = $("#dialog_form :input[name=id]").val();
            if (eid) {
                url = "/systemDictionary_updateItem";
            } else {
                url = "/systemDictionary_saveItem";
            }
            dialogForm.form("submit", {
                url: url,
                success: function (dataStr) {
                    var data = $.parseJSON(dataStr);
                    if (data.success) {
                        systemDictionaryItemDialog.dialog("close");
                        systemDictionaryItemDatagrid.datagrid("reload");
                    }
                    $.messager.alert("温馨提示", data.msg);
                }
            });
        },
        /** 取消 */
        cancel: function () {
            systemDictionaryItemDialog.dialog("close");
        }
    };
    //为所有按钮绑定单击事件
    $("a").on('click', function () {
        var cmd = $(this).data('cmd');
        if (cmd) {
            cmdObj[cmd]();
        }
    });
});