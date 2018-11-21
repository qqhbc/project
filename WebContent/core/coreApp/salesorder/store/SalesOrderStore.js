/** 订单数据集类 */
Ext.define("core.salesorder.store.SalesOrderStore", {
	extend : "Ext.data.Store",
	model : "core.salesorder.model.SalesOrderModel",
	pageSize : 50, // 每页显示50条记录
	// autoSync:true, //与服务器同步
	proxy : {
		type : "ajax",
		url : "salesorder/list_salesorder.htm",
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