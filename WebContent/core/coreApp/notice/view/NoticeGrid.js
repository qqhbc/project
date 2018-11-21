/** 公告流程挂接视图类 */
Ext.define("core.notice.view.NoticeGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.noticegrid",
	store : "core.notice.store.NoticeStore",
	selModel : {
		selType : "checkboxmodel"
	},
	border : 0,
	multiSelect : true,
	frame : true,
	tbar : [ {
		xtype : "button",
		text : "发布公告",
		ref : "addNotice",
		iconCls : "table_add"
	}, {
		xtype : "button",
		text : "修改公告",
		ref : "updateNotice",
		iconCls : "table_edit"
	}, {
		xtype : "button",
		text : "删除公告",
		ref : "removeNotice",
		iconCls : "table_remove"
	}, "->", "按公告名模糊查询：", {
		xtype : "triggerfield",
		triggerCls : Ext.baseCSSPrefix + "form-search-trigger",
		listeners : {                             //监听(自动过滤)不需要点击出发时间
			"change" : function(_this, _new, _old, _opt) {
				var _store = _this.ownerCt.ownerCt.getStore();
				_store.clearFilter(false);
				_store.filter("name", _new);
			}
		},
		onTriggerClick : function() {               //点击触发事件
			var _store = this.ownerCt.ownerCt.getStore();
			_store.clearFilter(false);
			_store.filter("name", this.getValue());
		}
	} ],
	// 底部样式
	bbar : {
		xtype : "pagingtoolbar",
		store : "core.notice.store.NoticeStore",
		dock : "bottom",
		displayInfo : true
	},
	enableKeyNav : true, // 可以使用键盘控制上下
	columnLines : true, // 展示竖线
	columns : [ {
		xtype : "rownumberer"
	}, {
		text : "公告名称",
		dataIndex : "name",
		width : 100,
		border : 50
	}, {
		text : "公告内容",
		dataIndex : "description",
		width : 100
	}, {
		text : "开始时间",
		dataIndex : "startDate",
		width : 100,
	}, {
		text : "结束时间",
		dataIndex : "endDate",
		width : 100
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});