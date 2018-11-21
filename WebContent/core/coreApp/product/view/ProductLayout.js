/** 图书管理视图布局类 */
Ext.define("core.product.view.ProductLayout", {
			extend : "Ext.panel.Panel",
			alias : "widget.productlayout",
			title : "<center height=40>图书基本信息</center>",
			defaults : {
				bodyStyle : "padding:1px"
			},
			layout : "fit",
			items:[{
				xtype:"product_grid"
			},{
				xtype:"productform",
				hidden:true
			}]
});