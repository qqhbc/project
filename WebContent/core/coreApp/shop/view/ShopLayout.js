/** 公告管理视图布局类 */
Ext.define("core.shop.view.ShopLayout", {
	extend : "Ext.panel.Panel",
	alias : "widget.shoplayout",
	title : "<center height=40>商户基本信息</center>",
	defaults : {
		bodyStyle : "padding:1px"
	},
	layout : "fit",
	items : [ {
		xtype : "shopgrid"
	}, {
		xtype : "shopform",
		hidden : true
	} ]
});