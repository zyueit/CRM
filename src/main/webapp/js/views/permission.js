$(function () {
    var permissionDatagrid = $("#permission_datagrid");

    //数据表格初始化
    permissionDatagrid.datagrid({
        title: '权限列表',
        url: '/permission_selectByCondition',
        fit: true,
        rownumbers: true,
        pagination: true,
        fitColumns: true,
        loadMsg: '正在努力加载中...',
        singleSelect: true,
        pageList: [1, 5, 10, 20],
        remoteSort: false,
        toolbar: '#permission_datagrid_btn',
        columns: [[
            {
                title: '权限名称',
                field: 'name',
                align: 'center',
                width: 1
            }, {
                title: '权限表达式',
                field: 'resource',
                align: 'center',
                width: 1
            }
        ]]
    });
    //操作方法集中管理
    var cmdObj = {
        /** 权限加载 */
        load: function () {
            $.messager.confirm("温馨提示", "加载权限需要花费大量时间，您确定要重新加载吗？", function (bool) {
                if (bool) {
                    $.post("/permission_load", function (data) {
                        if (data.success) {
                            permissionDatagrid.datagrid("reload");
                        }
                        $.messager.alert("温馨提示", data.msg);
                    });
                }
            });
        },
        /** 权限删除 */
        del: function () {
            var select = permissionDatagrid.datagrid("getSelected");
            if (select) {
                $.messager.confirm("温馨提示", "删除该权限后，所有人将都可以进行该操作（除了all），您确定要删除吗？", function (bool) {
                    if (bool) {
                        $.post("/permission_delete", {id: select.id}, function (data) {
                            if (data.success) {
                                permissionDatagrid.datagrid("reload");
                            }
                            $.messager.alert("温馨提示", data.msg);
                        });
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择要删除的权限！");
            }
        },
        /** 刷新 */
        refresh: function () {
            permissionDatagrid.datagrid("reload");
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
