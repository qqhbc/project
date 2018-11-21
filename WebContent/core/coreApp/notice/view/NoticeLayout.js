/** 公告管理视图布局类 */
Ext.define("core.notice.view.NoticeLayout", {
	extend : "Ext.panel.Panel",
	alias : "widget.noticelayout",
	title : "<center height=40>公告基本信息</center>",
	defaults : {
		bodyStyle : "padding:1px"
	},
	layout : "fit",
	items : [ {
		xtype : "noticegrid"
	}, {
		xtype : "noticeform",
		hidden : true
	} ]
});