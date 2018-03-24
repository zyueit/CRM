$(function () {
    var systemLogDatagrid = $("#systemLog_datagrid");

    //数据表格初始化
    systemLogDatagrid.datagrid({
        url: '/systemLog_query',
        fit: true,
        rownumbers: true,
        pagination: true,
        fitColumns: true,
        loadMsg: '正在努力加载...',
        singleSelect: true,
        pageList: [1, 5, 10, 20],
        remoteSort: false,
        toolbar: '#systemLog_datagrid_btn',
        columns: [[
            {
                title: '用户名',
                field: 'opUser',
                align: 'center',
                width: 20, formatter: function (value) {
                    if (value) {
                        return value.username;
                    } else {
                        return "-";
                    }
                }
            }, {
                title: 'IP地址',
                field: 'opIp',
                align: 'center',
                width: 20
            }, {
                title: '使用时间',
                field: 'opTime',
                align: 'center',
                width: 20
            }, {
                title: '函数名',
                field: 'function',
                align: 'center',
                width: 20, formatter: function (value) {
                    if (value) {
                        return value.substring(value.lastIndexOf(".") + 1);
                    } else {
                        return "-";
                    }
                }
            }, {
                title: '参数',
                field: 'params',
                align: 'center',
                width: 20, formatter: function (value) {
                    if (value) {
                        return value.substring(1, value.length - 1);
                    }
                }
            }
        ]]
    });
    //操作方法集中管理
    var cmdObj = {
        /** 刷新 */
        refresh: function () {
            systemLogDatagrid.datagrid("reload");
        }
    }
    //为所有按钮绑定单击事件
    $("a").on('click', function () {
        var cmd = $(this).data('cmd');
        if (cmd) {
            cmdObj[cmd]();
        }
    });
});
