$(function () {
    var datagird = $("#datagird");
    var datagird_item = $("#datagird_item");
    var datagird_btn = $("#datagird_btn");
    var dialog = $("#dialog");
    var dialog_form = $("#dialog_form");
    //保修单数据表格初始化
    datagird.datagrid({
        fit: true,
        fitColumns: true,
        url: '/guarantee_list',
        pagination: true,
        loadMsg: '正在努力加载...',
        singleSelect: true,
        pageList: [1, 5, 10, 20],
        rownumbers: true,
        columns: [[
            {field: "id", title: "编号", align: "center", width: 10},
            {field: "sn", title: "合同编号", align: "center", width: 10},
            {field: "productName", title: "产品名称", align: "center", width: 10},
            {field: "dueTime", title: "保修到期时间", align: "center", width: 10},
            {field: "remark", title: "备注", align: "center", width: 10},
        ]],
        toolbar: datagird_btn
    })

    //保修单明细初始化
    datagird_item.datagrid({
        fit: true,
        fitColumns: true,
        title: '保修单明细',
        pagination: true,
        loadMsg: '正在努力加载...',
        singleSelect: true,
        pageList: [1, 5, 10, 20],
        rownumbers: true,
        columns: [[
            {field: "id", title: "编号", align: "center", width: 10},
            {field: "sn", title: "合同编号", align: "center", width: 10},
            {field: "productName", title: "产品名称", align: "center", width: 10},
            {field: "dueTime", title: "保修到期时间", align: "center", width: 10},
            {field: "remark", title: "备注", align: "center", width: 10},
        ]]
    });

    //对话框初始化
    dialog.dialog({
        title: "保修单编辑",
        closed:
            true,
        buttons:
            "#dialog_btn",
        width:
            300,
        height:
            200
    });


    var btn = {
        edit: function () {
            dialog_form.form("clear");
            var select = datagird.datagrid("getSelected");
            if (select) {
                dialog_form.form("load", select);
                dialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择要编辑的数据!");
            }
        },
        refresh: function () {

        },
        search_items: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                window.open("guaranteeItem_list?id=" + select.id, 'new', 'height=500, width=800');
            } else {
                $.messager.alert("温馨提示", "请选择要查询的保修单!");
            }
        },
        save: function () {
            dialog_form.form("submit", {
                url: "/guarantee_update", success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        dialog.dialog("close");
                        datagird.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        cancel: function () {
            dialog.dialog("close")
        }
    };


    $("a").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            btn[cmd]();
        }
    });
});