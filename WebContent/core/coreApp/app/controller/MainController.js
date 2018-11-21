/** 主控制器类 */
Ext.define("core.app.controller.MainController", {
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
					/** 用户管理 */
					if(record.data["id"]=="usermanager"){
						self.addFunItem({
							mainView:mainView,
							funViewXtype:"userlayout",
							funController:"core.user.controller.UserController",
							funViewName:"core.user.view.UserLayout"
						});
					}
						/** 类别管理 */
					else if(record.data["id"]=="category-product"){
							self.addFunItem({
								mainView:mainView,
								funViewXtype:"categorylayout",
								funController:"core.category.controller.CategoryController",
								funViewName:"core.category.view.CategoryLayout"
							});
						}
					/** 图书管理 */
					else if(record.data["id"]=="productmanager"){
							self.addFunItem({
								mainView:mainView,
								funViewXtype:"productlayout",
								funController:"core.product.controller.ProductController",
								funViewName:"core.product.view.ProductLayout"
							});
						}
					/** 订单管理 */
					else if(record.data["id"]=="salesorderment"){
							self.addFunItem({
								mainView:mainView,
								funViewXtype:"salesorderlayout",
								funController:"core.salesorder.controller.SalesOrderController",
								funViewName:"core.salesorder.view.SalesOrderLayout"
							});
						}
					/** 公告管理 */
					else if(record.data["id"]=="noticemanager"){
							self.addFunItem({
								mainView:mainView,
								funViewXtype:"noticelayout",
								funController:"core.notice.controller.NoticeController",
								funViewName:"core.notice.view.NoticeLayout"
							});
						}
				}
			}// westview treepanel end
		});
		
	},
	views : [ "core.app.view.TopView", "core.app.view.WestView",
			"core.app.view.CenterView", "core.app.view.MainViewLayout" ],
	store : [],
	model : []
});