<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/3/26
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>测试</title>
    <script src="/js/plugins/jquery-easyui/jquery.min.js"></script>
    <script src="/js/plugins/jquery-easyui/highcharts.js"></script>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        $(function () {
            var mydata = $(window.opener.document.getElementsByName("array")).val()
            mydata = JSON.parse(mydata);
            Highcharts.chart('container', {
                chart: {
                    type: 'column'
                },
                title: {
                    text: '潜在客户新增量'
                },
                subtitle: {
                    text: 'Source: WorldClimate.com'
                },
                xAxis: {
                    //x轴   分类的字段参数,按月分类    按月份
                    categories: mydata.y,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '员工增加量'
                    }
                },
                tooltip: {
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: mydata.data
            });
        });
    </script>
</head>
<body>
<div id="container"></div>
</body>
</html>

