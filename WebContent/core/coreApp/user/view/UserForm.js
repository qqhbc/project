/**用户form视图类*/
Ext.define("core.user.view.UserForm", {
	extend : "Ext.form.Panel",
	alias : "widget.userform",
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
		fieldLabel : "用户名",
		name : "name",
		allowBlank : false,// 不允许为空
		blankText : '用户名不能为空',// 错误提示内容
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
	}, {
		xtype : "numberfield",
		fieldLabel : "电话",
		name : "phone",
		allowBlank : false,// 不允许为空
		blankText : "手机号码不能为空",
		regexText : '请输入正确的手机号码',// 错误提示内容
		readOnly : false,
		regex : /^0?1[3|4|5|8][0-9]\d{8}$/
	}, {
		xtype : "numberfield",
		fieldLabel : "QQ",
		name : "QQ",
		readOnly : false,
		regexText : "请输入正确的QQ号码(可以为空)",
		regex : /^[1-9][0-9]{4,11}$/
	}, {
		xtype : 'fieldcontainer',
		fieldLabel : '性别',
		defaultType : 'radiofield', // 定义为radiofield
		name : "sex",
		defaults : {
			flex : 1
		},
		layout : 'hbox',
		items : [ {
			boxLabel : '男',
			name : 'sex',
			inputValue : 'true',
			checked : true
		}, {
			boxLabel : '女',
			name : 'sex',
			inputValue : 'false'
		} ]
	}, {
		xtype : "textfield",
		fieldLabel : "Email",
		name : "email",
		vtype : "email",
		readOnly : false
	}, {
		xtype : "textareafield",
		fieldLabel : "地址",
		name : "address",
		readOnly : false,
		blankText : "地址不能为空",
		allowBlank : false,// 不允许为空
		regexText : "地址只能有汉字和数字，字母(不能全为数字或字母)",
		regex : /^(?=.*?[\u4E00-\u9FA5])[\w\u4E00-\u9FA5]+$/
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});