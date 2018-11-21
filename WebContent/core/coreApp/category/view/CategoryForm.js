/**分类form视图类*/
Ext.define("core.category.view.CategoryForm",{
	extend:"Ext.form.Panel",
	alias:"widget.categoryform",
	layout:"auto",
	align:"left",
	frame:true,
	defaults:{
		margin:"7 0 0 15",
		selectOnFocus:true
	},
	tbar:[{
		xtype:"button",
		ref:"save",
		iconCls:"table_save",
		text:"保存"
	}],
	items:[{
		xtype:"textfield",
		fieldLabel:"类别ID",
		name:"id",
		hidden:true
	},{
		xtype:"textfield",
		fieldLabel:"类别名称",
		allowBlank : false,// 不允许为空
		blankText : '类别名称不能为空',// 错误提示内容
		name:"text"
	},{
		xtype:"textareafield",
		fieldLabel:"类别描述",
		name:"description",
		height:40
	},{
		xtype:"textfield",
		fieldLabel:"父类ID",
		name:"parentId",
		hidden:true
	},{
		xtype:"textfield",
		fieldLabel:"是否为叶子",
		name:"leaf",
		hidden:true
		
	}]
});