/** 公告实体类 */
Ext.define("core.notice.model.NoticeModel", {
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
		name : "description",
		type : "string",
		srotable : true
	}, {
		name : "startDate",
		type : "string",
		srotable : true
	}, {
		name : "endDate",
		type : "string",
		srotable : true
	} ]
})