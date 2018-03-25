$(function () {
    var datagird = $("#datagird");
    var button = $("#button");

    /*数据表初始化*/
    datagird.datagrid({
        url: "/customerTransfer_query",
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
            {field: 'customer', title: '客户名称', align: 'center', width: 10, formatter: customerFormatter},
            {field: 'transTime', title: '移交时间', align: 'center', width: 10},
            {
                field: 'oldSeller', title: '原市场专员', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {
                field: 'newSeller', title: '新市场专员', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {
                field: 'inputUser', title: '操作人', align: 'center', width: 10,
                formatter: employeeFormatter
            }]],
        toolbar: button
    })


    var cmdObj = {
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
        return value.username;
    } else {
        return "-";
    }
}

function customerFormatter(value) {
    if (value) {
        return value.name;
    } else {
        return "-";
    }
}