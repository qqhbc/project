/**北部菜单功能类*/
Ext.define("core.app.view.TopView", {
	extend:"Ext.panel.Panel",
	alias : 'widget.topview',
	id:"topview",
	height : 50,
	bodyStyle : {
		background : '#7598E0',
		padding : '80px'
	},
	layout : "absolute",
	items : [{
				x : 0,
				y : 0,
				width:230,
				border:0,
				html : "<img src='core/css/images/logo/logo.png'/>"
			},{
				x : 230,
				y : 0,
				width:450,
				bodyStyle : {
					background : '#7598E0',
					border:0,
					padding : '10px'
				},
				html : "<h1><font color=white size=5>&nbsp;&nbsp;&nbsp;&nbsp;网上图书商城后台管理系统</font></h1>"
			},{
				x : 800,
				y : 20,
				ref : "logininfo",
				xtype : "displayfield",
				id:"displaylogin",
				value : "<font color=white><b>欢迎你，城院商铺</b></font>"
			}, {
				x : 960,
				y : 20,
				xtype : "button",
				ref : "exit",
				text : "退出系统"
			}]
});
