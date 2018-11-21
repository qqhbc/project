/** 类别管理布局类 */
Ext.define("core.category.view.CategoryLayout", {
	extend : "Ext.panel.Panel",
	alias : "widget.categorylayout",
	title : "<center height=40>类别基本信息</center>",
	defaults : {
		split : true, // 折叠效果
		collapsible : true, // 可以被折叠
		bodyStyle : "padding:1px"
	},
	layout : 'border',
	items : [ {
		title : "类别管理",
		region : "west",
		xtype : "categorytree",
		margins : "5 2 5 5",
		width : 150
	}, {
		xtype : "panel",
		region : "center",
		border : 0,
		header : false,
		layout : "border",
		items : [ {
			region : "north",
			margins : "5 0 5 0",
			height : 160,
			title : "类别信息",
			collapsible : true,// 可以被折叠
			xtype : "categoryform"
		}, {
			collapsible : true, // 可以被折叠
			region : "center",
			height : 500,
			margins : "5 0 5 0",
			xtype : "product_grid",
			bbar : [],
			store : Ext.create("core.category.store.ProductStore", {}),
			tbar : [ {
				xtype : "button",
				text : "删除",
				ref : "delete",
				iconCls : "table_remove"
			}, "->", "按名称查询", {
				xtype : "triggerfield",
				triggerCls : Ext.baseCSSPrefix + "form-search-trigger",
				listeners : {
					"change" : function(_this, _new, _old, _opt) {
						var _store = _this.ownerCt.ownerCt.getStore();
						_store.clearFilter(false);
						_store.filter("name", _new);
					}
				},
				onTriggerClick : function() {
					var _store = this.ownerCt.ownerCt.getStore();
					_store.clearFilter(false);
					_store.filter("name", this.getValue());
				}
			}, {
				xtype : "panel",
				width : 10,
				border : 0
			}, "按编号查询:", {
				xtype : "triggerfield",
				triggerCls : Ext.baseCSSPrefix + "form-search-trigger",
				listeners : {
					"change" : function(_this, _new, _old, _opt) {
						var _store = _this.ownerCt.ownerCt.getStore();
						_store.clearFilter(false);
						_store.filter("id", _new);
					}
				},
				onTriggerClick : function() {
					var _store = this.ownerCt.ownerCt.getStore();
					_store.clearFilter(false);
					_store.filter("id", this.getValue());
				}
			} ],
			title:"类别信息"
		} ]
	} ]
});