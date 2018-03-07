$(function () {
    var roleDatagrid = $("#role_datagrid");
    var roleEdit = $("#roleEdit");
    var roleRemove = $("#roleRemove");
    var roleDialog = $("#role_dialog");
    var dialogForm = $("#dialog_form");
    var selfPermission = $("#selfPermission");
    var allPermission = $("#allPermission");

    //数据表格初始化
    roleDatagrid.datagrid({
        title: '角色管理',
        url: '/role_selectByCondition',
        fit: true,
        rownumbers: true,
        pagination: true,
        fitColumns: true,
        loadMsg: '正在努力加载中...',
        singleSelect: true,
        pageList: [1, 5, 10, 20],
        remoteSort: false,
        toolbar: '#role_datagrid_btn',
        onClickRow: function (rowIndex, rowData) {
            //角色的编辑和禁用按钮控制
            if (rowData.state) {
                roleEdit.linkbutton("enable");
                roleRemove.linkbutton("enable");
            } else {
                roleEdit.linkbutton("disable");
                roleRemove.linkbutton("disable");
            }
        },
        columns: [[
            {
                title: '角色编码',
                field: 'sn',
                align: 'center',
                width: 1
            }, {
                title: '角色名称',
                field: 'name',
                align: 'center',
                width: 1
            }, {
                title: '权限',
                field: 'permissionsName',
                align: 'center',
                width: 1
            }, {
                title: '状态',
                field: 'state',
                formatter: function (value) {
                    if (value) {
                        return '<span style="color: green;">正常</span>';
                    } else {
                        return '<span style="color: red;">禁用</span>';
                    }
                },
                align: 'center',
                width: 1
            }
        ]]
    });
    //新增/编辑对话框初始化
    roleDialog.dialog({
        width: 600,
        height: 400,
        top: 200,
        modal: true,
        closed: true,
        doSize: true,
        buttons: '#role_dialog_btn'
    });
    //拥有的权限的数据表格初始化
    selfPermission.datagrid({
        title: '已分配权限',
        width: 220,
        height: 250,
        rownumbers: true,
        fitColumns: true,
        singleSelect: true,
        onDblClickRow: function (rowIndex) {
            selfPermission.datagrid("deleteRow", rowIndex);
        },
        columns: [[
            {
                title: '权限名称',
                field: 'name',
                align: 'center',
                width: 1
            }
        ]]
    });
    //所有的权限的数据表格初始化
    allPermission.datagrid({
        title: '所有权限',
        width: 220,
        height: 250,
        url: '/permission_selectByCondition',
        rownumbers: true,
        pagination: true,
        fitColumns: true,
        loadMsg: '正在努力加载中...',
        singleSelect: true,
        onDblClickRow: function (rowIndex, rowData) {
            var rows = selfPermission.datagrid("getRows");
            var eq = false;
            for (var index = 0; index < rows.length; index++) {
                if (rowData.id == rows[index].id) {
                    eq = true;
                    break;
                }
            }
            if (eq) {
                selfPermission.datagrid("selectRow", index);
            } else {
                selfPermission.datagrid("appendRow", rowData);
            }
        },
        columns: [[
            {
                title: '权限名称',
                field: 'name',
                align: 'center',
                width: 1
            }
        ]]
    });
    //设置所有权限表格的分页条的部分信息不显示
    allPermission.datagrid("getPager").pagination({
        showPageList: false,
        showRefresh: false,
        displayMsg: ''
    });
    //操作方法集中管理
    var cmdObj = {
        /** 角色新增 */
        add: function () {
            //dialogForm.form("clear");
            $("[name='sn'],[name='name'],[name='id']").val("");
            selfPermission.datagrid("loadData", {rows: []});
            allPermission.datagrid("unselectAll");
            roleDialog.dialog("open");
            roleDialog.dialog("setTitle", "新增角色");
        },
        /** 角色编辑 */
        edit: function () {
            var select = roleDatagrid.datagrid("getSelected");
            if (select) {
                //dialogForm.form("clear");
                $("[name='sn'],[name='name'],[name='id']").val("");
                selfPermission.datagrid("loadData", {rows: []});
                allPermission.datagrid("unselectAll");
                roleDialog.dialog("open");
                roleDialog.dialog("setTitle", "编辑角色");
                if (select.permissions.length) {
                    select.rows = select.permissions;
                    selfPermission.datagrid("loadData", select)
                }
                dialogForm.form("load", select);
            } else {
                $.messager.alert("温馨提示", "请选择要编辑的角色！");
            }
        },
        /** 禁用角色 */
        del: function () {
            var select = roleDatagrid.datagrid("getSelected");
            if (select) {
                $.messager.confirm("温馨提示", "您确定要禁用该角色吗？", function (bool) {
                    if (bool) {
                        $.post("/role_disable", {id: select.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg);
                                roleDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg);
                            }
                        });
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择要禁用的角色！");
            }
        },
        /** 刷新 */
        refresh: function () {
            roleDatagrid.datagrid("reload");
        },
        /** 保存或更新 */
        save: function () {
            var url;
            var eid = $("#dialog_form :input[name=id]").val();
            if (eid) {
                url = "/role_update";
            } else {
                url = "/role_save";
            }
            dialogForm.form("submit", {
                url: url,
                onSubmit: function (param) {
                    var rows = selfPermission.datagrid("getRows");
                    for (var index = 0; index < rows.length; index++) {
                        param["permissions[" + index + "].id"] = rows[index].id;
                    }
                },
                success: function (dataStr) {
                    var data = $.parseJSON(dataStr);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        roleDialog.dialog("close");
                        roleDatagrid.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        /** 取消 */
        cancel: function () {
            roleDialog.dialog("close");
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
