/** 公告数据集类 */
Ext.define("core.shop.store.ShopStore", {
	extend : "Ext.data.Store",
	model : "core.shop.model.ShopModel",
	pageSize : 50, // 每页显示50条记录
	// autoSync:true, //与服务器同步
	proxy : {
		type : "ajax",
		url : "shop/list_shop.htm",
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