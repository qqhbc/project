/** 系统主程序界面布局类 */
Ext.define("core.adminApp.view.MainViewLayout", {
	extend : "Ext.panel.Panel",
	border : 0,
	layout : "border",
	alias : "widget.mainviewlayout", // 别名
	width : 10,
	height : 10,
	items : [ {
		region : "north",
		xtype : "topview"
	}, {
		xtype : "westview",
		region : "west"

	}, {
		xtype : "panel",
		region : "center",
		layout : "fit", // 布满
		margin : "2 0 0 0", // 上右下左
		border : 0,
		items : [ {
			// 事件提醒的girdpanel
			xtype : "centerview",
			border : 0
		} ]
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});