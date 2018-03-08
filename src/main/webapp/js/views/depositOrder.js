$(function () {
    var datagird = $("#depositOrder_datagrid");
    var button = $("#depositOrder_button");
    var dialog = $("#depositOrder_dialog");
    var dialog_form = $("#dialog_form");
    var sellerCombox = $("input[name='seller.id']");
    var search_form = $("#search_form");

    datagird.datagrid({
        url: '/depositOrder_query',
        title: '定金订单管理',
        singleSelect: true,
        rownumbers: true,
        fit: true,
        fitColumns: true,
        pagination: true,
        loadMsg: '正在努力加载...',
        pageList: [1, 5, 10, 20],
        onClickRow: function (row, data) {
            var edit = $("a").get(1);
            var mydelete = $("a").get(2);

            if (data.status != "0") {
                $(edit).linkbutton("disable");
                $(mydelete).linkbutton("disable");
            } else {
                $(edit).linkbutton("enable");
                $(mydelete).linkbutton("enable");
            }

            // /*控制财务审批按钮*/
            // var num = data.statusOfFinance;
            // if (num == 0 || num == 2) {
            //     $(approveByFinance).linkbutton("disable");
            // }
            // else if (num == 1) {
            //     $(approveByFinance).linkbutton("enable");
            // }

        },
        columns: [[
            {
                title: '定金客户',
                field: 'customer',
                align: 'center',
                width: 10
            },
            {
                title: '签订时间',
                field: 'signTime',
                align: 'center',
                width: 10
            },
            {
                title: '营销人员',
                field: 'seller',
                align: 'center',
                width: 10,
                formatter: employeeFormatter
            },
            {
                title: '总金额',
                field:
                    'totalSum',
                align:
                    'center',
                width:
                    10
            }
            ,
            {
                title: '定金金额',
                field:
                    'sum',
                align:
                    'center',
                width:
                    10
            }
            ,
            {
                title: '摘要',
                field:
                    'intro',
                align:
                    'center',
                width:
                    10
            }
            ,
            {
                title: '附件',
                field:
                    'file',
                align:
                    'center',
                width:
                    10
            }
            ,
            {
                title: '最近修改人',
                field:
                    'modifyUser',
                align:
                    'center',
                width:
                    10,
                formatter: employeeFormatter
            }
            ,
            {
                title: '最近修改时间',
                field:
                    'modifyTime',
                align:
                    'center',
                width:
                    10
            }
            ,
            {
                title: '订单状态',
                field:
                    'status',
                align:
                    'center',
                width:
                    10,
                formatter: statusFormatter
            }
            // ,
            // {
            //     title: '定金状态',
            //     field:
            //         'statusOfFinance',
            //     align:
            //         'center',
            //     width:
            //         10,
            //     formatter: statusOfFinanceFormatter
            // }
            // ,
        ]],
        toolbar: button
    });


    dialog.dialog({
        //新增/编辑对话框初始化
        width: 300,
        height: 300,
        top: 200,
        modal: true,
        closed: true,
        doSize: true,
        buttons: '#depositOrder_dialog_button'
    });

    // sellerCombox.combobox({
    //     url: "/employee_getEmployee",
    //     valueField: 'id',
    //     textField: 'realName'
    // });


//    方法集中管理
    var cdmObj = {
        /*定金订单新增*/
        save: function () {
            dialog_form.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "新增定金订单");
        },
        /*定金订单编辑*/
        edit: function () {
            dialog_form.form("clear");
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.seller.id) {
                    select['seller.id'] = select.seller.id;
                }
                dialog_form.form("load", select);
                dialog.dialog("open");
                dialog.dialog("setTitle", "编辑定金订单");
            }
            else {
                $.messager.alert("温馨提示", "请选择要编辑的信息！");
            }
        },
        /*定金订单审批*/
        approve: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.status == "0") {
                    $.messager.confirm("温馨提示", "是否通过审批？", function (ok) {
                        if (ok) {
                            $.ajax({
                                url: "/depositOrder_approve?id=" + select.id,
                                type: "get",
                                dataType: "json",
                                success: function (data) {
                                    if (data.success) {
                                        $.messager.alert("温馨提示", data.msg)
                                        datagird.datagrid("reload");
                                    } else {
                                        $.messager.alert(data.msg)
                                    }
                                }
                            });
                        }
                    })
                } else if (select.status == "2") {
                    $.messager.alert("温馨提示", "此订单已退款！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要审批的订单！");
            }
        },
        /*定金订单部门审批*/
        approveDept: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.status == "1") {
                    $.messager.confirm("温馨提示", "是否通过审批？", function (ok) {
                        if (ok) {
                            $.ajax({
                                url: "/depositOrder_approveByDept?id=" + select.id,
                                type: "get",
                                dataType: "json",
                                success: function (data) {
                                    if (data.success) {
                                        $.messager.alert("温馨提示", data.msg)
                                        datagird.datagrid("reload");
                                    } else {
                                        $.messager.alert(data.msg)
                                    }
                                }
                            });
                        }
                    })
                } else if (select.status == "2") {
                    $.messager.alert("温馨提示", "此订单已退款！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要审批的订单！");
            }
        },
        /*定金订单财务审批*/
        approveByFinance: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                if (select.statusOfFinance == "1") {
                    $.messager.alert("温馨提示", "此功能还未开放！");
                    // $.messager.confirm("温馨提示", "是否通过审批？", function (ok) {
                    //     if (ok) {
                    //         $.ajax({
                    //             url: "/depositOrder_approveByFinance?id=" + select.id,
                    //             type: "get",
                    //             dataType: "json",
                    //             success: function (data) {
                    //                 if (data.success) {
                    //                     $.messager.alert("温馨提示", data.msg)
                    //                     datagird.datagrid("reload");
                    //                 } else {
                    //                     $.messager.alert(data.msg)
                    //                 }
                    //             }
                    //         });
                    //     }
                    // })
                }
                else if (select.status == "2") {
                    $.messager.alert("温馨提示", "此订单已退款！");
                }
            } else {
                $.messager.alert("温馨提示", "请选择要审批的订单！");
            }
        },
        /*定金订单刷新*/
        refresh: function () {
            datagird.datagrid("reload");
        },
        /*定金订单删除*/
        delete: function () {
            var select = datagird.datagrid("getSelected");
            $.messager.confirm("温馨提示", "是否要删除该订单记录？", function (ok) {
                if (ok) {
                    $.ajax({
                        url: "/depositOrder_delete?id=" + select.id,
                        type: "get",
                        dataType: "json",
                        success: function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg)
                                datagird.datagrid("reload");
                            } else {
                                $.messager.alert(data.msg)
                            }
                        }
                    });
                }
            });
        },
        /*对话框提交*/
        commit: function () {
            var url;
            var did = $("#dialog_form :input[name=id]").val();
            if (did) {
                url = "/depositOrder_update";
            } else {
                url = "/depositOrder_save";
            }
            dialog_form.form("submit", {
                url: url,
                success: function (data) {
                    var data = $.parseJSON(data);
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
        /*对话框取消*/
        cancel: function () {
            dialog.dialog({
                closed: true
            })
        },
        /*因为使用submit方法会使page与rows丢失,要对高级查询表单的处理*/
        querySearch: function () {
            var paramArr = search_form.serializeArray();
            var param = {};
            for (var index = 0; index < paramArr.length; index++) {
                param[paramArr[index].name] = paramArr[index].value;
            }
            datagird.datagrid("load", param);
        }
    }


//    给所有标签绑定同一事件
    $("a").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cdmObj[cmd]();
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

//有关状态显示的格式器
function statusFormatter(value) {
    if (value == "1") {
        return '<span style="color: blue">部门主管未审批</span>';
    } else if (value == "0") {
        return '<span style="color:  red">初始录入</span>';
    } else if (value == "2") {
        return '已退款';
    } else if (value == "3") {
        return '<span style="color:  green">已出合同</span>';
    }
}

function statusOfFinanceFormatter(value) {
    if (value == "2") {
        return '<span style="color: green">已到账</span>';
    } else if (value == "1") {
        return '<span style="color:  red">未到账</span>';
    } else if (value == "0") {
        return "-";
    }

}


