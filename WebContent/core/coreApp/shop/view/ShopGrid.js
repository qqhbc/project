/** 公告流程挂接视图类 */
Ext.define("core.shop.view.ShopGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.shopgrid",
	store : "core.shop.store.ShopStore",
	selModel : {
		selType : "checkboxmodel"
	},
	border : 0,
	multiSelect : true,
	frame : true,
	tbar : [ {
		xtype : "button",
		text : "添加商铺",
		ref : "addShop",
		iconCls : "table_add"
	}, {
		xtype : "button",
		text : "修改商铺",
		ref : "updateShop",
		iconCls : "table_edit"
	}, {
		xtype : "button",
		text : "删除商铺",
		ref : "removeShop",
		iconCls : "table_remove"
	}, "->", "按商铺名称模糊查询：", {
		xtype : "triggerfield",
		triggerCls : Ext.baseCSSPrefix + "form-search-trigger",
		listeners : {                             //监听(自动过滤)不需要点击出发时间
			"change" : function(_this, _new, _old, _opt) {
				var _store = _this.ownerCt.ownerCt.getStore();
				_store.clearFilter(false);
				_store.filter("username", _new);
			}
		},
		onTriggerClick : function() {               //点击触发事件
			var _store = this.ownerCt.ownerCt.getStore();
			_store.clearFilter(false);
			_store.filter("username", this.getValue());
		}
	} ],
	// 底部样式
	bbar : {
		xtype : "pagingtoolbar",
		store : "core.shop.store.ShopStore",
		dock : "bottom",
		displayInfo : true
	},
	enableKeyNav : true, // 可以使用键盘控制上下
	columnLines : true, // 展示竖线
	columns : [ {
		xtype : "rownumberer"
	}, {
		text : "商户名称",
		dataIndex : "username",
		width : 100,
		border : 50
	}, {
		text : "商户密码",
		dataIndex : "password",
		width : 100
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});