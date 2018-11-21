/** 公告form视图类 */
Ext.define("core.notice.view.NoticeForm", {
	extend : "Ext.form.Panel",
	alias : "widget.noticeform",
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
		fieldLabel : "公告名",
		name : "name",
		allowBlank : false,// 不允许为空
		blankText : '公告名不能为空',// 错误提示内容
		readOnly : false
	}, {
		xtype : "textareafield",
		fieldLabel : "公告内容",
		name : "description",
		blankText : "公告内容不能为空",
		allowBlank : false,// 不允许为空
		readOnly : false
	}, {
		xtype : "textfield",
		fieldLabel : "开始时间",
		name : "startDate",
		allowBlank : false,// 不允许为空
		blankText : "活动开始时间不能为空",
		regexText : '请输入正确的时间格式',// 错误提示内容
		readOnly : false,
		regex : /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/
	}, {
		xtype : "textfield",
		fieldLabel : "结束时间",
		name : "endDate",
		allowBlank : false,// 不允许为空
		blankText : "活动结束时间不能为空",
		regexText : '请输入正确的时间格式',// 错误提示内容
		readOnly : false,
		regex : /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});