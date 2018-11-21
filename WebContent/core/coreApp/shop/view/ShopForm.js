/** 公告form视图类 */
Ext.define("core.shop.view.ShopForm", {
	extend : "Ext.form.Panel",
	alias : "widget.shopform",
	layout : {
		type : "table",
		columns : 2
	},
	align : "left",
	defaults : {
		margin : "10 0 0 15",
		selectOnFocus : true,
		width : 300,
		msgTarget : "side" // 提示信息现在的位置
	},
	tbar : [ {
		xtype : "button",
		ref : "save",
		iconCls : "table_save",
		text : "保存"
	}, {
		xtype : "button",
		ref : "return",
		iconCls : "return",
		text : "返回"
	} ],
	items : [ {
		xtype : "textfield",
		fieldLabel : "主键",
		name : "id",
		hidden : true
	}, {
		xtype : "textfield",
		fieldLabel : "商铺名称",
		name : "username",
		allowBlank : false,// 不允许为空
		blankText : '商铺名称不能为空',// 错误提示内容
		readOnly : false
	}, {
		xtype : "textfield",
		fieldLabel : "密码",
		inputType : 'password',
		name : "password",
		blankText : "密码不能为空",
		allowBlank : false,// 不允许为空
		regexText : '请输入同时含有数字和字母，且长度要在6-16位之间的密码！',// 错误提示内容
		readOnly : false,
		regex : /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,16}$/
	}],
	initComponent : function() {
		this.callParent(arguments);
	}
});