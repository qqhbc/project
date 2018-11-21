/**分类数据集类*/
Ext.define("core.category.store.CategoryStore",{
	extend:"Ext.data.TreeStore",
	defaultRootId:"root",
	//autoSync:true,//与服务器同步
	proxy:{
		api:{
		},
		type:"ajax",
		url:"category/list_category.htm",
		reader:{
			type:"json"
		},
		writer:{
			type:"json"
		}
	},
	autoLoad:true
});