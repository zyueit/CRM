$(function () {
    $('#pp').portal({
        border: false,
        fit: true
    });

    // create the panel
    var p1 = $('<div></div>').appendTo('body');
    var p2 = $('<div></div>').appendTo('body');
    var p3 = $('<div></div>').appendTo('body');
    var p4 = $('<div></div>').appendTo('body');

    p1.panel({
        title: '日程安排',
        height: 200,
        closable: true,
        collapsible: true,

    });

    p1.calendar({
        current: new Date(),
        onSelect: function () {
            $("#myTabs").tabs("add", {
                title: "日常安排",
                closable: true,
                content: "<iframe src='" + "/calendar" + "' style='width:100%;height:100%' frameborder=0></iframe>"
            });
        }
    })

    p2.panel({
        content: "<iframe  src='/task'  style='width:100%;height:100%;'  frameborder='0' ></iframe>",
        title: '在线搜索',
         height:470,
        fit:true,
        height:470,
        closable: true,
        collapsible: true
    });


    p3.panel({
    	   	content: "<iframe  src='/fastCheckIn'  style='width:100%;height:100%;'  frameborder='0' ></iframe>",
        title: '快速签到',
        height: 150,
        closable: true,
        collapsible: true
    });

    $('#pp').portal('add', {
        panel: p1,
        columnIndex: 2
    });


// add the panel to portal
    $('#pp').portal('add', {
        panel: p2,
        columnIndex: 1
    });

    p4.panel({
    	 content: "<iframe  src='/weather'  style='width:100%;height:100%;'  frameborder='0' ></iframe>",
        title: '天气查询',
        height: 400,
        closable: true,
        collapsible: true
    });

    $('#pp').portal('add', {
        panel: p3,
        columnIndex: 0
    });
    $('#pp').portal('add', {
        panel: p4,
        columnIndex: 0
    });

})