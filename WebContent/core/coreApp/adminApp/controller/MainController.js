/** 主控制器类 */
Ext.define("core.adminApp.controller.MainController", {
	extend : "Ext.app.Controller",
	init : function() {
		var self = this;
		/** 动态加载controller并渲染它的主窗体 */
		this.addFunItem=function(funInfo){
			if(funInfo){
				var mainView = funInfo.mainView;
				var funPanel = mainView.down(funInfo.funViewXtype);
				if(!funPanel){
					self.application.getController(funInfo.funController).init();
					funPanel = Ext.create(funInfo.funViewName);
					mainView.add(funPanel);
				}
					mainView.setActiveTab(funPanel);
			}
		},
		/** 下在是控制部分 */
		this.control({
			"westview treepanel":{
				itemclick:function(tree,record,item,index,e,eOpts){
					var mainView = tree.up("mainviewlayout").down("centerview");
					/** 商户管理 */
					if(record.data["id"]=="shopmanager"){
						self.addFunItem({
							mainView:mainView,
							funViewXtype:"shoplayout",
							funController:"core.shop.controller.ShopController",
							funViewName:"core.shop.view.ShopLayout"
						});
					}
					
					
					
				}
		
			}// westview treepanel end
		});
		
	},
	views : [ "core.adminApp.view.TopView", "core.adminApp.view.WestView",
			"core.adminApp.view.CenterView", "core.adminApp.view.MainViewLayout" ],
	store : [],
	model : []
});