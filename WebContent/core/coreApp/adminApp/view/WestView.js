/** 西部菜单功能类 */
Ext.define("core.adminApp.view.WestView", {
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
		title : "商户管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "shopmanager",
					text : "商户基本信息",
					leaf : true
				} ]
			}
		} ]
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});

