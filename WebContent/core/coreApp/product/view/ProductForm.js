/** 图书form视图类 */
Ext.define("core.product.view.ProductForm", {
	extend : "Ext.form.Panel",
	alias : "widget.productform",
	// height: 380,
	// width: 300,
	bodyStyle : "padding:5px 5px 5px 5px",
	border : 0,
	frame : true,
	enctype : "multipart/form-data",// 把文件以二进制流的方式传递到服务器
	fileUpload : true,
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
		bodyStyle : "background:transparent",// 设置为透明,不妨碍更换主题了
		border : 0,
		layout : "column",
		items : [ {
			columnWidth : 0.5,
			xtype : "fieldset",
			title : "图书基本信息",
			items : [ {
				xtype : "textfield",
				name : "id",
				fieldLabel : "id",
				labelWidth : 60,
				allowBlank : false,
				hidden : true
			}, {
				xtype : "textfield",
				name : "name",
				fieldLabel : "图书名称",
				allowBlank : false,
				blankText : "图书名称不能为空",
				labelWidth : 60,
			}, {
				xtype : "combobox",
				fieldLabel : "图书类别",
				allowBlank : false,
				name : "categoryId",
				// editable: false,
				store : Ext.create("core.product.store.CategoryStore", {}),
				forceSelection : true,
				queryMode : "remote", // 远程
				displayField : "text",
				valueField : "categoryId",
				labelWidth : 60
			}, {
				xtype : "numberfield",
				name : "maketPrice",
				fieldLabel : "市场价",
				allowBlank : false,
				blankText : "市场价不能为空",
				labelWidth : 60
			}, {
				xtype : "numberfield",
				name : "memberPrice",
				fieldLabel : "会员价",
				allowBlank : false,
				blankText : "会员价不能为空",
				labelWidth : 60
			}, {
				xtype : "textareafield",
				name : "description",
				fieldLabel : "图书描述",
				labelWidth : 60
			}, {
				height : 153,
				bodyStyle : "background:transparent",// 设置为透明,不妨碍更换主题了
				border : 0
			} ]
		}, {
			width : 5,
			bodyStyle : "background:transparent",
			border : 0
		}, {
			columnWidth : 0.5,
			xtype : "fieldset",
			title : "照片上传",
			items : [ {
				xtype : "filefield",
				name : "photos",
				labelWidth : 60,
				msgTarget : "side",
				anchor : "100%",
				buttonText : "选择照片",
				fieldLabel : "上传图片"
			}, {
				xtype : "image",
				width : 400,
				height : 300
			} ]
		} ]

	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});