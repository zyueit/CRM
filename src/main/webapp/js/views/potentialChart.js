$(function () {
    var datagrid = $("#datagrid");
    var search_form = $("#search_form");

    datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/potentialChart_query',
        loadMsg: '正在努力加载...',
        singleSelect: true,
        columns: [[
            {field: "username", title: "銷售人員", align: "center", width: 10},
            {field: "inputTime", title: "录入时间", align: "center", width: 10},
            {field: "customerCount", title: "新增潜在客户人数", align: "center", width: 10},
        ]],
        toolbar: "#datagrid_btn"
    })


    $("a[data-cmd=querySearch]").on("click", function () {
        var a = {};
        var data = search_form.serializeArray();
        $.each(data, function (i, e) {
            a[data[i]['name']] = data[i]['value'];
        })
        datagrid.datagrid("load", a);
    });
    // var a = {};

    $("a[data-cmd=produce]").on("click", function () {
        var rows = datagrid.datagrid("getData").rows;
        var info = null;
        $.post("/potentialChart_parse", {rows: JSON.stringify(rows)}, function (data) {
            info = JSON.stringify(data);
            $("input[name=array]").val(info);
        });
        window.open("potentialCharthight.jsp", "newwindow", "height=500,width=900");
    });
});