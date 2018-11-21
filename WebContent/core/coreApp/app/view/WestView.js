/** 西部菜单功能类 */
Ext.define("core.app.view.WestView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.westview',
	collapsible : true,
	split : true,
	defaults : {
		bodyStyle : 'padding:2px'
	},
	border : 1,
	margins : '2 2 0 0',
	width : 225,
	minSize : 100,
	maxSize : 250,
	title : "功能模块导航",
	layout : 'accordion',
	layoutConfig : {
		titleCollapse : false,
		animate : true,
		activeOnTop : true
	},
	items : [ {
		title : "用户管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "usermanager",
					text : "用户基本信息",
					leaf : true
				} ]
			}
		} ]
	}, {
		title : "图书类别管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					text : "图书类别基本信息",
					id : "category-product",
					leaf : true
				} ]
			}
		} ]
	}, {
		title : "图书管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "productmanager",
					text : "图书基本信息",
					leaf : true
				} ]
			}
		} ]
	}, {
		title : "订单管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "salesorderment",
					text : "订单基本信息",
					leaf : true
				} ]
			}
		} ]
	}, {
		title : "公告管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "noticemanager",
					text : "公告基本信息",
					leaf : true
				} ]
			}
		} ]
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});
