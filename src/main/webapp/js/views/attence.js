$(function () {
    var datagird = $("#datagird");
    datagird.datagrid({
        url: "/attence_query",
        singleSelect: true,
        fit: true,
        fitColumns: true,
        columns: [[
            {field: 'ip', title: '编号', align: 'center', width: 10},
            {field: 'state', title: '现金客户', align: 'center', width: 10},
            {field: 'signInTime', title: '签订时间', align: 'center', width: 10},
            {
                field: 'addSignUser', title: '销售人员', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {
                field: 'user', title: '最近修改人', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {field: 'signOutTime', title: '最近修改时间', align: 'center', width: 10},
            {field: 'AddSignTime', title: '状态', align: 'center', width: 10}
        ]],
        toolbar: button
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

$(function () {
    $("a[data-cmd=produce]").on("click",function () {
    });
});