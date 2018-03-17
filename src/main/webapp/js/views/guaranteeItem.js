$(function () {
    var URL = document.URL;
    var datagird_btn = $("#datagird_btn");
    var dialog = $("#dialog");
    var dialog_form = $("#dialog_form");

    var id = URL.substr(URL.length - 2, URL.length);

    var datagird = $("#datagird");

    datagird.datagrid({
        title: "保修单明细",
        fitColumns: true,
        fit: true,
        singleSelect: true,
        columns: [[
            {field: "id", title: "编号", align: "center", width: 10},
            {field: "content", title: "保修内容", align: "center", width: 10},
            {field: "guaranteeTime", title: "保修时间", align: "center", width: 10},
            {field: "a", title: "保修费用", align: "center", width: 10},
            {field: "remark", title: "备注", align: "center", width: 10},
            {
                field: "status", title: "保修状态", align: "center", width: 10, formatter: function (value) {
                    if (value) {
                        return "<span style='color: green'>完成</span>";
                    } else {
                        return "<span style='color: red'>进行</span>";
                    }
                }
            },
        ]],
        toolbar: datagird_btn
    })

    $.get("guaranteeItem?id=" + id, function (data) {
        if (data.length == 0) {
            $.messager.alert("温馨提示", "此保修单没有保修明细！", "warning")
        } else {
            datagird.datagrid({"data": data});
        }
    }, "json");


    //对话框初始化
    dialog.dialog({
        closed:
            true,
        buttons:
            "#dialog_btn",
        width:
            300,
        height:
            200
    })


    //方法集中管理
    var btn = {
        add: function () {
            dialog_form.form("clear");
            dialog.dialog("setTitle", "增加明细");
            dialog.dialog("open");
        },
        edit: function () {
            dialog_form.form("clear");
            var select = datagird.datagrid("getSelected");
            if (select) {
                dialog.dialog("setTitle", "编辑明细");
                dialog_form.form("load", select);
                dialog.dialog("open");
            } else {
                $.messager.alert("温馨提示", "请选择要编辑的数据!");
            }
        },
        refresh: function () {
            datagird.datagrid("reload");
        },
        save: function () {
            var id = $("input[name=id]").val();
            var url;
            if (id) {
                //编辑
                url = "/guaranteeItem_update";
            } else {
                url = "/guaranteeItem_save";
                $("input[name=id]").val(URL.substr(URL.length - 2, URL.length));
            }
            dialog_form.form("submit", {
                url: url, success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        dialog.dialog("close");
                        location.reload();
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        del: function () {
            var select = datagird.datagrid("getSelected");
            if (select) {
                $.messager.confirm("温馨提示", "是否删除？", function (bool) {
                    if (bool) {
                        $.post("/guaranteeItem_delete", {id: select.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg);
                                location.reload();
                            } else {
                                $.messager.alert("温馨提示", data.msg);
                            }
                        });
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择要删除的明细！");
            }
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

})
;