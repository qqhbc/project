/** 用户实体类 */
Ext.define("core.user.model.UserModel", {
	extend : "Ext.data.Model",
	fields : [ {
		name : "id",
		type : "int",
		srotable : true
	}, {
		name : "name",
		type : "string",
		srotable : true
	}, {
		name : "password",
		type : "string",
		srotable : true
	}, {
		name : "sex",
		type : "string",
		srotable : true
	}, {
		name : "phone",
		type : "string",
		srotable : true
	}, {
		name : "QQ",
		type : "string",
		srotable : true
	}, {
		name : "email",
		type : "string",
		srotable : true
	}, {
		name : "address",
		type : "string",
		srotable : true
	}, {
		name : "regDate",
		type : "string",
		srotable : true
	}, {
		name : "ip",
		type : "string",
		srotable : true
	} ]
})