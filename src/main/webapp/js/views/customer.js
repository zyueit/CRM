$(function () {
    var Datagrid = $("#datagrid");
    var deliver_form = $("#deliver_form");
    var deliverDialog = $("#deliverDialog");
    var job = $("#job");
    var salaryLevel = $("#salaryLevel");
    var Dialog = $("#dialog");
    var dialogForm = $("#dialog_form");
    var deliver = $("#deliver");
    var search_form = $("#search_form");


    //移交对话框初始化
    deliverDialog.dialog({
        width: 400,
        height: 300,
        top: 50,
        modal: true,
        closed: true,
        doSize: true,
        buttons: '#deliver_btn'
    });


    //数据表格初始化
    Datagrid.datagrid({
        url: '/customer_query',
        fit: true,
        fitColumns: true,
        loadMsg: '正在努力加载中...',
        singleSelect: true,
        remoteSort: false, pageList: [1, 5, 10, 20],
        toolbar: '#datagrid_btn',
        pagination: true,
        onClickRow: function (rowIndex, rowData) {
            // //信息的编辑和离职按钮控制
            // if (rowData.state) {
            //     Edit.linkbutton("enable");
            //     Remove.linkbutton("enable");
            // } else {
            //     Edit.linkbutton("disable");
            //     Remove.linkbutton("disable");
            // }
        },
        columns: [[
            {
                title: '编号',
                field: 'id',
                align: 'center',
                width: 20
            }, {
                title: '客户名称',
                field: 'name',
                align: 'center',
                width: 20
            }, {
                title: '年龄',
                field: 'age',
                align: 'center',
                width: 20,
            }, {
                title: '性别',
                field: 'gender',
                align: 'center',
                width: 20,
                formatter: function (value) {
                    if (value == "0") {
                        return "男";
                    } else if (value == "1") {
                        return "女";
                    }
                }
            }, {
                title: '电话号码',
                field: 'tel',
                align: 'center',
                width: 20,
            }
            , {
                title: '邮箱 ',
                field: 'email',
                align: 'center',
                width: 20,
            }
            , {
                title: 'QQ',
                field: 'qq',
                align: 'center',
                width: 20,
            }
            , {
                title: '微信',
                field: 'wechat',
                align: 'center',
                width: 20,
            }
            , {
                title: '职业',
                field: 'job',
                align: 'center',
                width: 20,
                formatter: function (value) {
                    if (value) {
                        return value.name;
                    } else {
                        return "";
                    }
                }
            }
            , {
                title: '收入水平',
                field: 'salaryLevel',
                align: 'center',
                width: 20,
                formatter: function (value) {
                    if (value) {
                        return value.name;
                    } else {
                        return "";
                    }
                }
            }
            , {
                title: '客户来源',
                field: 'customerSource',
                align: 'center',
                width: 20,
            }
            , {
                title: '负责人',
                field: 'inChargeUser',
                align: 'center',
                width: 20,
                formatter: function (value) {
                    if (value) {
                        return value.username;
                    } else {
                        return "-";
                    }
                }
            }
            , {
                title: '录入人',
                field: 'inputUser',
                align: 'center',
                width: 20,
                formatter: function (value) {
                    if (value) {
                        return value.username;
                    } else {
                        return "-";
                    }
                }
            }
            , {
                title: '录入时间',
                field: 'inputTime',
                align: 'center',
                width: 20,
            }
            , {
                title: '状态',
                field: 'state',
                align: 'center',
                width: 20,
                formatter: function (value) {
                    if (value == "-1") {
                        return "<span style='color: green'>开发失败</span>";
                    } else if (value == "0") {
                        return "<span style='color: red'>潜在客户</span>";
                    } else if (value == "1") {
                        return "<span style='color: blue'>正式客户</span>";
                    } else if (value == "2") {
                        return "<span style='color:darkblue'>资源池客户</span>";
                    } else if (value == "-2") {
                        return "<span style='color:red'>流失客户</span>";
                    }
                }
            }
        ]]
    });
    //新增/编辑对话框初始化
    Dialog.dialog({
        width: 500,
        height: 400,
        top: 50,
        modal: true,
        closed: true,
        doSize: true,
        buttons: '#dialog_btn'
    });
    //职业的下拉框初始化
    job.combobox({
        url: '/systemDictionary_listItem?id=1',
        method: 'get',
        valueField: 'id',
        textField: 'name'
    });
    //收入來源的下拉框初始化
    salaryLevel.combobox({
        url: '/systemDictionary_listItem?id=2',
        method: 'get',
        valueField: 'id',
        textField: 'name'
    });

    //移交人下拉框的初始化
    deliver.combobox({
        url: '/listForDept',
        method: 'get',
        valueField: 'id',
        textField: 'username'
    });

    //操作方法集中管理
    var cmdObj = {
        /** 信息新增 */
        add: function () {
            job.combobox("reload");//解决添加或编辑信息后没刷新问题
            salaryLevel.combobox("reload");//解决添加或编辑信息后没刷新问题
            dialogForm.form("clear");
            Dialog.dialog("open");
            Dialog.dialog("setTitle", "新增信息");
        },
        /** 信息编辑 */
        edit: function () {
            var select = Datagrid.datagrid("getSelected");
            if (select) {
                if (select.state != "-2") {
                    job.combobox("reload");//解决添加或编辑信息后没刷新问题
                    salaryLevel.combobox("reload");//解决添加或编辑信息后没刷新问题
                    dialogForm.form("clear");
                    Dialog.dialog("open");
                    Dialog.dialog("setTitle", "编辑信息");
                    /**回显职业*/
                    if (select.job) {
                        select['job.id'] = select.job.id;
                    }
                    /**回显收入水平*/
                    if (select.salaryLevel) {
                        select['salaryLevel.id'] = select.salaryLevel.id;
                    }
                    dialogForm.form("load", select);
                }
                else {
                    $.messager.alert("温馨提示", "此客戶已流失！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要编辑的信息！");
            }
        },
        /** 客户移交 */
        deliver: function () {
            var select = Datagrid.datagrid("getSelected");
            if (select) {
                if (select.state != -2) {
                    deliver_form.form("clear");
                    deliverDialog.dialog("setTitle", "编辑信息");
                    select['inChargeUser'] = select.inChargeUser.username;
                    deliver_form.form("load", select);
                    deliverDialog.dialog("open");
                    $("input[name=cmd]").val("deliver");
                } else {
                    $.messager.alert("温馨提示", "此客戶已流失！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要移交的客户！");
            }
        },
        /** 客户共享 */
        share: function () {
            var select = Datagrid.datagrid("getSelected");
            if (select) {
                if (select.state != -2) {
                    deliver_form.form("clear");
                    deliverDialog.dialog("setTitle", "编辑信息");
                    select['inChargeUser'] = select.inChargeUser.username;
                    deliver_form.form("load", select);
                    deliverDialog.dialog("open");
                    $("input[name=cmd]").val("share");
                } else {
                    $.messager.alert("温馨提示", "此客戶已流失！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要共享的客户！");
            }
        },
        /** 客戶流失 */
        away: function () {
            var select = Datagrid.datagrid("getSelected");
            if (select) {
                if (select.state != -2) {
                    $.messager.confirm("温馨提示", "您确定要这样做吗？", function (bool) {
                        if (bool) {
                            $.post("/customer_approve?state=-2", {id: select.id}, function (data) {
                                if (data.success) {
                                    $.messager.alert("温馨提示", data.msg);
                                    Datagrid.datagrid("reload");
                                } else {
                                    $.messager.alert("温馨提示", data.msg);
                                }
                            });
                        }
                    });
                } else {
                    $.messager.alert("温馨提示", "此客戶已流失！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要删除的信息！");
            }
        },
        /** 刷新 */
        refresh: function () {
            Datagrid.datagrid("reload");
        },
        /** 保存或更新 */
        save: function () {
            var url;
            var eid = $("#dialog_form :input[name=id]").val();
            if (eid) {
                url = "/customer_update";
            } else {
                url = "/customer_save";
            }
            dialogForm.form("submit", {
                url: url,
                success: function (dataStr) {
                    var data = $.parseJSON(dataStr);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        Dialog.dialog("close");
                        Datagrid.datagrid("reload");

                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        /** 取消 */
        cancel: function () {
            Dialog.dialog("close");
        },
        //移交对话框的提交
        submit: function () {
            var select = Datagrid.datagrid("getSelected");
            if (select) {
                var data = deliver_form.serializeArray()
                var sid = data[3]['value'];
                if (sid) {
                    $.get("/customer_" + data[0]['value'] + "?inChargeUser.id=" + sid + "&id=" + select.id, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg);
                            deliverDialog.dialog("close");
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    }, "json");
                    Datagrid.datagrid("reload");
                } else {
                    $.messager.alert("温馨提示", "请选择接收人!");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要移交的客户！");
            }
        },
        //高级查询
        querySearch: function () {
            var paramArr = search_form.serializeArray();
            var param = {};
            for (var index = 0; index < paramArr.length; index++) {
                param[paramArr[index].name] = paramArr[index].value;
            }
            Datagrid.datagrid("load", param);
        },
        /** 取消 */
        cancel2: function () {
            deliverDialog.dialog("close");
        },
        /**审核*/
        approve: function () {
            var select = Datagrid.datagrid("getSelected");
            if (select) {
                if (select.state == "0") {
                    $.post("/customer_approve?state=1", {id: select.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg);
                            Dialog.dialog("close");
                            Datagrid.datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    }, "json");
                } else {
                    $.messager.alert("温馨提示", "此信息已审核！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要审核的信息！");
            }
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
