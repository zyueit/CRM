$(function () {
    $("#menuTree").tree({
        url: '/menu',
        onClick: function (node) {
            console.log(node.attributes);
            // 把数据库中attributes:{"url":"xxxxx"}请求的json字符串转换为json对象
            node.attributes = $.parseJSON(node.attributes);
            //在选项中添加新面板
            var myTab = $("#myTabs");
            //在选项卡中是否已经有该节点的面板.
            if (myTab.tabs("exists", node.text)) {
                //选中面板
                myTab.tabs("select", node.text);
            } else {
                myTab.tabs("add", {
                    title: node.text,
                    closable: true,
                    content: "<iframe src='" + node.attributes.url + "' style='width:100%;height:100%' frameborder=0></iframe>"
                });
            }
        }
    });
});