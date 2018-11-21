/** 用户数据集类 */
Ext.define("core.user.store.UserStore", {
	extend : "Ext.data.Store",
	model : "core.user.model.UserModel",
	pageSize : 50, // 每页显示50条记录
	// autoSync:true, //与服务器同步
	proxy : {
		type : "ajax",
		url : "user/list_user.htm",
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