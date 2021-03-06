/** 用户控制器类 */
Ext.define("core.shop.controller.ShopController", {
	extend : "Ext.app.Controller",
	init : function() {
		var self = this;
		this.control({
			/** 增添公告按钮 */
			"shopgrid button[ref=addShop]" : {
				click : function(btn) {
					var form = btn.up("shoplayout").down("shopform");
					// 清空数据
					form.getForm().reset();
					var grid = form.up("shoplayout").down("shopgrid");
					grid.hide();
					form.show();
				}
			},
			/** 修改用户,这个功能在保存按钮中完成， 要修改用户，请双击记录 */
			"shopgrid button[ref=updateShop]" : {
				click : function(btn) {
					Ext.Msg.alert("友情提示", "请双击需要修改的记录进行修改！");
				}
			},
			/** 添加用户form的保存按钮 */
			"shopform button[ref=save]" : {
				click : function(btn) {
					// 1.获得form
					var form = btn.up("shopform");
					var grid = form.up("shoplayout").down("shopgrid");
					var id = form.getForm().findField("id").getValue();
					var url = "";
					// 根据id值来做判断，如果id为null说明是做添加操作，否则就是做修改操作
					if (id == "" || id == null) {
						url = "shop/add_shop.htm";
					} else {
						url = "shop/update_shop.htm";
					}
					// 2.把数据保存到数据库中去
					form.submit({
						clientValidation : true,
						waitMsg : "正在进行处理，请稍后...",
						url : url,
						method : "POST",
						success : function(form, action) {
							var resObj = Ext
									.decode(action.response.responseText);
							if (resObj.success) {
								form.reset();
								// 3.把这条数据加到grid中
								grid.getStore().load();
								Ext.Msg.alert("提示", resObj.msg);
							} else {
								Ext.Msg.alert("提示", resObj.msg);
							}
						},
						failure : function(form, action) {
							Ext.Msg.alert("提示", "请求处理失败！");
						}
					});
				}
			},
			/** 添加用户form的返回按钮 */
			"shopform button[ref=return]" : {
				click : function(btn) {
					var form = btn.up("shopform");
					var grid = form.up("shoplayout").down("shopgrid");
					form.hide();
					grid.show();
				}
			},
			/** 删除用户 */
			"shopgrid button[ref=removeShop]" : {
				click : function(btn) {
					var grid = btn.up("shopgrid");
					var store = grid.getStore();
					// 你选择的将要删除记录
					var records = grid.getSelectionModel().getSelection();
					if (!records || records.length <= 0) {
						Ext.Msg.alert("提示", "请选择需要删除的数据！");
						return;
					}
					// 根据id删除多条记录
					var data = [];
					Ext.Array.each(records, function(model) {
						data.push(Ext.JSON.encode(model.get("id")));
					});
					Ext.MessageBox.confirm("重要提示",
							"是否要删除吗？",
							function(e){
								if(e == 'yes'){
					Ext.Ajax.request({
						waitMsg : "正在进行处理，请稍后...",
						url : "shop/remove_shop.htm",
						params : {
							ids : data.join(",")
						},// 根据id删除
						method : "POST",
						timeout : 4000,
						success : function(response, opts) {
							var resObj = Ext.decode(response.responseText);
							if (resObj.success) {
								// 不用查询，从grid中去掉对应的记录就OK了
								store.load();
								Ext.Msg.alert("提示", resObj.msg);
							} else {
								Ext.Msg.alert("提示", resObj.msg);
							}
						}
					});
								}
					});
				}
			},
			/** 双击进入form，修改信息 */
			"shopgrid" : {
				itemdblclick : function(_grid, record, item, index, e, eOpts) {
					var form = _grid.up("shoplayout").down("shopform");
					var grid = form.up("shoplayout").down("shopgrid");
					// 把选择的数据加载到form中
					var _record = grid.getSelectionModel().getSelection();
					form.loadRecord(_record[0]);
					grid.hide();
					form.show();
				}
			},
			// 任务节点事件添加
			"taskevengrid button[ref=addEvent]" : {
				click : function(btn) {
				}
			}
		});
	},
	views : [ "core.shop.view.ShopLayout", "core.shop.view.ShopGrid",
			"core.shop.view.ShopForm" ],
	stores : [ "core.shop.store.ShopStore" ],
	models : [ "core.shop.model.ShopModel" ]
});