$(function () {
    var datagird = $("#datagird");
    var button = $("#button");
    var dialog = $("#dialog");
    var dialog_form = $("#dialog_form");
    var search_form = $("#search_form");

    /*数据表初始化*/
    datagird.datagrid({
        url: "/contractOrder_query",
        title: '合同管理',
        singleSelect: true,
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        loadMsg: '正在努力加载...',
        pageList: [1, 5, 10, 20],
        onClickRow: function (index, value) {
            var edit = $("a").get("1");
            var mydelete = $("a").get("2");
            if (value.status != "0") {
                $(edit).linkbutton("disable")
                $(mydelete).linkbutton("disable")
            } else {
                $(edit).linkbutton("enable")
                $(mydelete).linkbutton("enable")
            }
        },
        columns: [[
            {field: 'sn', title: '编号', align: 'center', width: 10},
            {field: 'customer', title: '现金客户', align: 'center', width: 10},
            {field: 'signTime', title: '签订时间', align: 'center', width: 10},
            {
                field: 'seller', title: '销售人员', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {field: 'sum', title: '合同金额', align: 'center', width: 10},
            {field: 'money', title: '付款金额', align: 'center', width: 10},
            {field: 'intro', title: '摘要', align: 'center', width: 10},
            {field: 'file', title: '附件', align: 'center', width: 10},
            {
                field: 'modifyUser', title: '最近修改人', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {field: 'modifyTime', title: '最近修改时间', align: 'center', width: 10},
            {field: 'status', title: '状态', align: 'center', width: 10, formatter: statusFormatter},
        ]],
        toolbar: button
    })


    //编辑框的初始化
    dialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: "#dialog_btn"
    })


    var cmdObj = {
        //保存订单
        save: function () {
            dialog_form.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "合同新增");
        },
        //关闭对话框
        cancel: function () {
            dialog.dialog("close");
        },
        //编辑订单
        edit: function () {
            dialog_form.form("clear");
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.seller.id) {
                    select['seller.id'] = select.seller.id;
                }
                // if (select.file) {
                //     filebox.filebox("setValue", select.file)
                // }
                dialog_form.form("load", select);
                dialog.dialog("open");
                dialog.dialog("setTitle", "编辑合同订单");
            }
            else {
                $.messager.alert("温馨提示", "请选择要编辑的信息！");
            }
        },
        //提交
        submit: function () {
            var id = $("input[name=id]").val();
            var url;
            if (id) {
                //编辑
                url = "/contractOrder_update";
            } else {
                //保存
                url = "/contractOrder_save";
            }
            dialog_form.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        dialog.dialog("close");
                        datagird.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            })
        },
        //高级查询搜索
        querySearch: function () {
            var form = search_form.serializeArray();
            var data = {};
            $.each(form, function (i, v) {
                data[v.name] = v.value;
            });
            datagird.datagrid("load", data);
        },
        //刷新
        refresh: function () {
            datagird.datagrid("reload");
        },
        //删除
        delete: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.status == "0") {
                    $.messager.confirm("温馨提示", "是否要删除该订单?", function (ok) {
                        if (ok) {
                            $.get("/contractOrder_delete?id=" + select.id, function (data) {
                                if (data.success) {
                                    $.messager.alert("温馨提示", data.msg);
                                    dialog.dialog("close");
                                    datagird.datagrid("reload");
                                } else {
                                    $.messager.alert("温馨提示", data.msg);
                                }
                            }, "json");
                        }
                    })
                } else {
                    $.messager.alert("温馨提示", "此订单已被审核!");
                }
            }
            else {
                $.messager.alert("温馨提示", "请选择要编辑的信息！");
            }
        },
        //审核
        approve: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.status == "0") {
                    $.get("/contractOrder_approve?id=" + select.id, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg)
                            datagird.datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示", data.msg)
                        }
                    }, "json");
                }
                if (select.status == "1") {
                    $.messager.alert("温馨提示", "已通过审核!")
                }
            } else {
                $.messager.alert("温馨提示", "请选择要审核的信息！");
            }
        },
        //部门审核
        approveByDept: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.status == "1") {
                    $.get("/contractOrder_approveByDept?id=" + select.id, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg)
                            datagird.datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示", data.msg)
                        }
                    }, "json");
                }
                if (select.status == "0") {
                    $.messager.alert("温馨提示", "请先通过销售人员审核！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要审核的信息！");
            }
        }

    };

    $("a").on("click", function () {
        var a = $(this);
        var cmd = a.data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
});

//有关员工信息显示的格式器
function employeeFormatter(value) {
    if (value) {
        return value.realName;
    } else {
        return "-";
    }
}


function statusFormatter(value) {
    if (value == "0") {
        return "<span style='color: red'>初始录入</span>";
    } else if (value == "1") {
        return "<span style='color: blue'>未通过部门审核</span>";
    } else if (value == "2") {
        // return "<span style='color: black'>审核拒绝</span>";
    } else if (value == "3") {
        return "<span style='color: green'>已出合同</span>";
    }
}