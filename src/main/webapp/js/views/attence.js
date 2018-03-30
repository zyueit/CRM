$(function () {
    var datagrid = $("#datagrid");
    datagrid.datagrid({
        url: "/attence_query",
        singleSelect: true,
        fit: true,
        fitColumns: true,
        columns: [[
            {field: 'ip', title: 'IP', align: 'center', width: 10},
            {field: 'state', title: '状态', align: 'center', width: 10},
            {field: 'signInTime', title: '签到时间', align: 'center', width: 10},
            {
                field: 'addSignUser', title: '补签人员', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {
                field: 'user', title: '签到人', align: 'center', width: 10,
                formatter: employeeFormatter
            },
            {field: 'signOutTime', title: '签退时间', align: 'center', width: 10},
            {field: 'addSignTime', title: '补签时间', align: 'center', width: 10}
        ]],
        toolbar: button
    });

    $("a[data-cmd=produce]").on("click", function () {
        var data = datagrid.datagrid("getData");
        data = JSON.stringify(data.rows)
        $("input[name=data]").val(data);
        $("#form").submit();
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
