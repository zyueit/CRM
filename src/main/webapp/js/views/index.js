$(function () {
    $("#menuTree").tree({
        url: '/menu',
        onClick: function (node) {
            //在选项中添加新面板
            var myTab = $("#myTabs");
            var url;
            //在选项卡中是否已经有该节点的面板.
            if (myTab.tabs("exists", node.text)) {
                //选中面板
                myTab.tabs("select", node.text);
            } else {
                // 把数据库中attributes:{"url":"xxxxx"}请求的json字符串转换为json对象
                url = $.parseJSON(node.attributes);
                myTab.tabs("add", {
                    title: node.text,
                    closable: true,
                    content: "<iframe src='" + url.url + "' style='width:100%;height:100%' frameborder=0></iframe>"
                });
            }
        }
    });
});