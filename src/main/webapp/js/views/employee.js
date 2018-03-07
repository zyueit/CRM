$(function () {
    var employeeDatagrid = $("#employee_datagrid");
    var employeeDialog = $("#employee_dialog");
    var employeeDept = $("#employee_dept");
    var employeeRoles = $("#employee_roles");
    var employeeEdit = $("#employeeEdit");
    var employeeRemove = $("#employeeRemove");
    var dialogForm = $("#dialog_form");
    var searchForm = $("#search_form");

    //数据表格初始化
    employeeDatagrid.datagrid({
        title: '员工管理',
        url: '/employee_selectByCondition',
        fit: true,
        rownumbers: true,
        pagination: true,
        fitColumns: true,
        loadMsg: '正在努力加载...',
        singleSelect: true,
        pageList: [1, 5, 10, 20],
        remoteSort: false,
        onClickRow: function (rowIndex, rowData) {
            //员工信息的编辑和离职按钮控制
            if (rowData.state) {
                employeeEdit.linkbutton("enable");
                employeeRemove.linkbutton("enable");
            } else {
                employeeEdit.linkbutton("disable");
                employeeRemove.linkbutton("disable");
            }
        },
        toolbar: '#employee_datagrid_btn',
        columns: [[
            {
                title: '用户名',
                field: 'username',
                align: 'center',
                width: 1
            }, {
                title: '真实名字',
                field: 'realName',
                align: 'center',
                width: 1
            }, {
                title: '联系电话',
                field: 'tel',
                align: 'center',
                width: 1
            }, {
                title: '邮箱',
                field: 'email',
                align: 'center',
                width: 1
            }, {
                title: '部门',
                field: 'dept',
                align: 'center',
                width: 1,
                formatter: deptFormatter
            }, {
                title: '角色',
                field: 'rolesName',
                align: 'center',
                width: 1,
            }, {
                title: '入职时间',
                field: 'inputTime',
                align: 'center',
                width: 1
            }, {
                title: '状态',
                field: 'state',
                align: 'center',
                width: 1,
                formatter: stateFormatter
            }
        ]]
    });
    //新增/编辑对话框初始化
    employeeDialog.dialog({
        width: 300,
        height: 300,
        top: 200,
        modal: true,
        closed: true,
        doSize: true,
        buttons: '#employee_dialog_btn'
    });
    //部门下拉框初始化
    employeeDept.combobox({
        url: '/listForEmp',
        method: 'get',
        valueField: 'id',
        textField: 'name'
    });
    //角色分配下拉框初始化
    employeeRoles.combobox({
        url: '/role_list',
        method: 'get',
        valueField: 'id',
        textField: 'name',
        multiple: true
    });
    //操作方法集中管理
    var cmdObj = {
        /** 员工新增 */
        add: function () {
            dialogForm.form("clear");
            employeeDialog.dialog("open");
            employeeDialog.dialog("setTitle", "新增员工");
        },
        /** 员工编辑 */
        edit: function () {
            var select = employeeDatagrid.datagrid("getSelected");
            if (select) {
                dialogForm.form("clear");
                employeeDialog.dialog("open");
                employeeDialog.dialog("setTitle", "编辑员工");
                if (select.dept) {
                    select['dept.id'] = select.dept.id;
                }
                if (select.roles.length) {
                    var ids = $.map(select.roles, function (arr) {
                        return arr.id;
                    });
                    employeeRoles.combobox("setValues", ids);
                }
                dialogForm.form("load", select);
            } else {
                $.messager.alert("温馨提示", "请选择要编辑的员工信息！");
            }
        },
        /** 员工离职 */
        del: function () {
            var select = employeeDatagrid.datagrid("getSelected");
            if (select) {
                $.messager.confirm("温馨提示", "您确定要离职该员工吗？", function (bool) {
                    if (bool) {
                        $.post("/employee_dimission", {id: select.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg);
                                employeeDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg);
                            }
                        });
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择要离职的员工！");
            }
        },
        /** 刷新 */
        refresh: function () {
            employeeDatagrid.datagrid("reload");
        },
        /** 保存或更新 */
        save: function () {
            var url;
            var eid = $("#dialog_form :input[name=id]").val();
            if (eid) {
                url = "/employee_update";
            } else {
                url = "/employee_save";
            }
            dialogForm.form("submit", {
                url: url,
                onSubmit: function (params) {
                    var ids = employeeRoles.combobox("getValues");
                    for (var index = 0; index < ids.length; index++) {
                        params["roles[" + index + "].id"] = ids[index];
                    }
                },
                success: function (dataStr) {
                    var data = $.parseJSON(dataStr);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        employeeDialog.dialog("close");
                        employeeDatagrid.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        /** 取消 */
        cancel: function () {
            employeeDialog.dialog("close");
        },
        /** 查询 */
        querySearch: function () {
            var paramArr = searchForm.serializeArray();
            var param = {};
            for (var index = 0; index < paramArr.length; index++) {
                param[paramArr[index].name] = paramArr[index].value;
            }
            employeeDatagrid.datagrid("load", param);
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

/** 员工状态格式化 */
function stateFormatter(value) {
    if (value) {
        return '<span style="color:green;">在职</span>';
    } else {
        return '<span style="color:red;">离职</span>';
    }
}

/** 员工部门格式化 */
function deptFormatter(value) {
    if (value) {
        return value.name;
    } else {
        return '—';
    }
}