$(function () {
    var datagird = $("#datagird");
    /*数据表初始化*/
    datagird.datagrid({
        url: "/contractOrder_query",
        title: '合同管理',
        singleSelect: true,
        fit: true,
        fitColumns: true,
        rownumbers: true,
        columns: [[
            {field: 'sn', title: '编号', align: 'center', width: 10},
            {field: 'customer', title: '现金客户', align: 'center', width: 10},
            {field: 'signTime', title: '签订时间', align: 'center', width: 10},
            {field: 'seller', title: '销售人员', align: 'center', width: 10},
            {field: 'sum', title: '合同金额', align: 'center', width: 10},
            {field: 'money', title: '付款金额', align: 'center', width: 10},
            {field: 'intro', title: '摘要', align: 'center', width: 10},
            {field: 'file', title: '附件', align: 'center', width: 10},
            {field: 'modifyUser', title: '最近修改人', align: 'center', width: 10},
            {field: 'modifyTime', title: '最近修改时间', align: 'center', width: 10},
            {field:  'status', title: '状态', align: 'center', width: 10},
        ]]
    })
});