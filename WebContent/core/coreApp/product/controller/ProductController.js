/**图书控制器类*/
Ext.define("core.product.controller.ProductController", {
	extend : "Ext.app.Controller",
	init : function() {
		var self = this;    //提高效率 
		this.control({
			
			/**添加图书按钮*/
			"product_grid button[ref=add]" : {
				click : function(_btn){
					var form = _btn.up("productlayout").down("productform");
					form.getForm().reset();
					form.down("image").setSrc("images/product/default.jpg");
					var grid = form.up("productlayout").down("product_grid");
					form.show();
					grid.hide();
				}
			},
			/** 修改图书,这个功能在保存按钮中完成， 要修改图书，请双击记录 */
			"product_grid button[ref=update]" : {
				click : function(btn) {
					Ext.Msg.alert("友情提示", "请双击需要修改的记录进行修改！");
				}
			},
			/**添加图书form保存按钮*/
			"productform button[ref=save]" : {
				click : function(_btn){
					//1获得form
					var _form = _btn.up("productform");
					var _grid = _btn.up("productlayout").down("product_grid");
					var id = _form.getForm().findField("id").getValue();
					var url = "";
					// 根据id值来做判断，如果id为null说明是做添加操作，否则就是做修改操作
					if(id == "" || null == id){
						url = "product/add_product.htm";
					}else{
						url = "product/update_product.htm";
					}
					//2.把数据保存到数据库中去
					_form.submit({
						clientValidation : false,
						waitMsg : '正在进行处理,请稍后...', 
						url : url,
						method : 'POST',
						success : function(form, action) {
							var resObj = Ext.decode(action.response.responseText);
							if (resObj.success) {
								
								if(url != "product/update_product.htm"){
									_form.getForm().reset();
								}else{
									_form.down("image").setSrc("images/product/"+resObj.photo);
								}
								//3.把这条数据加到grid中
								_grid.getStore().load();
								Ext.Msg.alert("提示", resObj.msg);
							} else {
								Ext.Msg.alert("提示", resObj.msg);
							}
						},
						failure : function(form, action) {
							Ext.Msg.alert("提示","请求处理失败！");
						}
					});
				}
			},

			/** 双击进入form，修改信息 */
			"product_grid" : {
				itemdblclick : function(_grid, record, item, index, e, eOpts) {
					var form = _grid.up("productlayout").down("productform");
					var grid = form.up("productlayout").down("product_grid");
					//把选择的数据加载到form中去
					var _record = grid.getSelectionModel().getSelection();
					form.loadRecord(_record[0]);
					grid.hide();
					form.down("image").setSrc("images/product/"+_record[0].get("photo"));
					form.show();
				}
			},
			/** 添加图书form的返回按钮 */
			"productform button[ref=return]" : {
				click : function(btn) {
					var form = btn.up("productform");
					var grid = form.up("productlayout").down("product_grid");
					form.hide();
					grid.show();
				}
			},
			/**删除图书按钮*/
			"product_grid button[ref=del]" : {
				click : function(btn){
					var grid = btn.up("product_grid");
					var store = grid.getStore();
					//你选择的将要删除的记录
					var records = grid.getSelectionModel().getSelection();
					if (!records || records.length <= 0) {
						Ext.Msg.alert("提示", "请选择需要删除的数据!");
						return;
					}
					// 根据id删除多条记录
					var data = [];
					Ext.Array.each(records, function(model) {
						data.push(Ext.JSON.encode(model.get('id')));
					});
					Ext.MessageBox.confirm("重要提示",
					"是否要删除吗？",
					function(e){
						if(e == 'yes'){
							Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...', 
								url : "product/remove_product.htm",
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

			
			
		});
	},
	views : ["core.product.view.ProductLayout","core.product.view.ProductGrid","core.product.view.ProductForm"],
	stores : ["core.product.store.ProductStore"],
	models : ["core.product.model.ProductModel"]
});