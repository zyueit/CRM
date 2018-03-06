$(function () {
    var departmentDatagrid = $("#department_datagrid");
    var departmentEdit = $("#departmentEdit");
    var departmentRemove = $("#departmentRemove");
    var departmentManager = $("#department_manager");
    var departmentParent = $("#department_parent");
    var departmentDialog = $("#department_dialog");
    var dialogForm = $("#dialog_form");

    //数据表格初始化
    departmentDatagrid.datagrid({
        title: '部门管理',
        url: '/department_list',
        fit: true,
        rownumbers: true,
        fitColumns: true,
        loadMsg: '正在努力加载中...',
        singleSelect: true,
        remoteSort: false,
        toolbar: '#department_datagrid_btn',
        onClickRow: function (rowIndex, rowData) {
            //员工信息的编辑和离职按钮控制
            if (rowData.state) {
                departmentEdit.linkbutton("enable");
                departmentRemove.linkbutton("enable");
            } else {
                departmentEdit.linkbutton("disable");
                departmentRemove.linkbutton("disable");
            }
        },
        columns: [[
            {
                title: '部门编码',
                field: 'sn',
                align: 'center',
                width: 1
            }, {
                title: '部门名称',
                field: 'name',
                align: 'center',
                width: 1
            }, {
                title: '部门经理',
                field: 'manager',
                align: 'center',
                width: 1,
                formatter: function (value) {
                    if (value) {
                        return value.realName;
                    } else {
                        return '—';
                    }
                }
            }, {
                title: '上级部门',
                field: 'parent',
                align: 'center',
                width: 1,
                formatter: function (value) {
                    if (value) {
                        return value.name;
                    } else {
                        return '—';
                    }
                }
            }, {
                title: '状态',
                field: 'state',
                align: 'center',
                width: 1,
                formatter: function (value) {
                    if (value) {
                        return '<span style="color: green;">正常</span>';
                    } else {
                        return '<span style="color: red;">停用</span>';
                    }
                }
            }
        ]]
    });
    //新增/编辑对话框初始化
    departmentDialog.dialog({
        width: 300,
        height: 250,
        top: 200,
        modal: true,
        closed: true,
        doSize: true,
        buttons: '#department_dialog_btn'
    });
    //添加部门经理的下拉框初始化
    departmentManager.combobox({
        url: '/listForDept',
        method: 'get',
        valueField: 'id',
        textField: 'realName'
    });
    //添加上级部门的下拉框初始化
    departmentParent.combobox({
        url: '/listForEmp',
        method: 'get',
        valueField: 'id',
        textField: 'name'
    });
    //操作方法集中管理
    var cmdObj = {
        /** 部门新增 */
        add: function () {
            dialogForm.form("clear");
            departmentDialog.dialog("open");
            departmentDialog.dialog("setTitle", "新增部门");
        },
        /** 部门编辑 */
        edit: function () {
            var select = departmentDatagrid.datagrid("getSelected");
            if (select) {
                dialogForm.form("clear");
                departmentDialog.dialog("open");
                departmentDialog.dialog("setTitle", "编辑部门");
                if (select.manager) {
                    select['manager.id'] = select.manager.id;
                }
                if (select.parent) {
                    select['parent.id'] = select.parent.id;
                }
                dialogForm.form("load", select);
            } else {
                $.messager.alert("温馨提示", "请选择要编辑的部门！");
            }
        },
        /** 停用部门 */
        del: function () {
            var select = departmentDatagrid.datagrid("getSelected");
            if (select) {
                $.messager.confirm("温馨提示", "您确定要停用该部门吗？", function (bool) {
                    if (bool) {
                        $.post("/department_disable", {id: select.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg);
                                departmentDatagrid.datagrid("reload");
                                departmentParent.combobox("reload");//解决停用部门后没刷新问题
                            } else {
                                $.messager.alert("温馨提示", data.msg);
                            }
                        });
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择要停用的部门！");
            }
        },
        /** 刷新 */
        refresh: function () {
            departmentDatagrid.datagrid("reload");
        },
        /** 保存或更新 */
        save: function () {
            var url;
            var eid = $("#dialog_form :input[name=id]").val();
            if (eid) {
                url = "/department_update";
            } else {
                url = "/department_save";
            }
            dialogForm.form("submit", {
                url: url,
                success: function (dataStr) {
                    var data = $.parseJSON(dataStr);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        departmentDialog.dialog("close");
                        departmentDatagrid.datagrid("reload");
                        departmentParent.combobox("reload");//解决添加或编辑部门后没刷新问题
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        /** 取消 */
        cancel: function () {
            departmentDialog.dialog("close");
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
