/** 公告实体类 */
Ext.define("core.shop.model.ShopModel", {
	extend : "Ext.data.Model",
	fields : [ {
		name : "id",
		type : "int",
		srotable : true
	}, {
		name : "username",
		type : "string",
		srotable : true
	}, {
		name : "password",
		type : "string",
		srotable : true
	} ]
})