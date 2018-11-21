/** 公告数据集类 */
Ext.define("core.notice.store.NoticeStore", {
	extend : "Ext.data.Store",
	model : "core.notice.model.NoticeModel",
	pageSize : 50, // 每页显示50条记录
	// autoSync:true, //与服务器同步
	proxy : {
		type : "ajax",
		url : "notice/list_notice.htm",
		reader : {
			type : "json",
			root : "rows",
			totalProperty : "totalCount"
		},
		writer : {
			type : "json"
		}
	},
	autoLoad : true
});