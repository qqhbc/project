package com.yc.controller;

import com.yc.model.SaleItem;
import com.yc.model.SalesOrder;
import com.yc.service.SalesOrderService;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/salesorder")
public class SalesOrderController {
	private SalesOrderService salesOrderService;

	public SalesOrderService getSalesOrderService() {
		return this.salesOrderService;
	}

	@Resource
	public void setSalesOrderService(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	@RequestMapping(value = "/list_salesorder", method = RequestMethod.GET)
	public void listSalesorder(HttpServletRequest request, PrintWriter writer) {
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		List<SalesOrder> orders = this.salesOrderService.getSalesOrdersPage(Integer.parseInt(startStr),
				Integer.parseInt(limitStr));
		int len = orders.size();
		String preStr = "{totalCount:" + this.salesOrderService.getCount() + ",rows:[";
		String cenStr = "";
		int i = 0;
		String comma = ",";
		for (SalesOrder order : orders) {
			double totalPrice = 0.0D;
			Set<SaleItem> items = order.getSaleItems();
			for (SaleItem item : items) {
				totalPrice += item.getPrice() * item.getCount();
			}
			i++;
			if (!order.isNullify()) {
				cenStr = cenStr + "{username:'" + order.getUsername() + "', " + "id:'" + order.getId() + "',"
						+ "orderCode:'" + order.getOrderCode() + "'," + "status:'" + order.isStatus() + "'," + "odate:'"
						+ order.getOdate() + "'," + "phone:'" + order.getPhone() + "'," + "QQ:'" + order.getQQ() + "',"
						+ "nullify:'" + order.isNullify() + "'," + "address:'" + order.getAddress() + "',"
						+ "totalmoney:'" + Math.round(totalPrice * 100.0D) / 100.0D + "'," + "remark:'"
						+ order.getRemark() + "'" + "}";
				if (i < len) {
					cenStr = cenStr + comma;
				}
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}

	@RequestMapping(value = "/show_salesorderdetail", method = RequestMethod.GET)
	public void showSalesOrderDetail(HttpServletRequest request, PrintWriter writer) {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		SalesOrder salesOrder = (SalesOrder) this.salesOrderService.getById(id).get(0);
		int len = salesOrder.getSaleItems().size();
		Set<SaleItem> items = salesOrder.getSaleItems();
		String preStr = "{totalCount:" + items.size() + ",rows:[";
		String cenStr = "";
		int i = 1;
		String comma = ",";
		for (SaleItem item : items) {
			cenStr = cenStr + "{id:'" + item.getId() + "', " + "name:'" + item.getProductName() + "'," + "productId:'"
					+ item.getProductId() + "', " + "count:'" + item.getCount() + "'," + "price:'" + item.getPrice()
					+ "'," + "sum:'" + Math.round(item.getCount() * item.getPrice() * 100.0D) / 100.0D + "'" + "}";
			if (i < len) {
				cenStr = cenStr + comma;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}

	@RequestMapping(value = "/check_salesorder", method = RequestMethod.POST)
	public void checkSalesOrderDetail(HttpServletRequest request, PrintWriter writer) {
		String id = request.getParameter("id");
		if (this.salesOrderService.verify(Integer.parseInt(id))) {
			writer.write("{success:true, msg:'ÉóºË³É¹¦!'}");
		} else {
			writer.write("{success:false, msg:'ÉóºËÊ§°Ü!'}");
		}
	}

	@RequestMapping(value = "/nullify_salesorder", method = RequestMethod.POST)
	public void nullifySalesOrderDetail(HttpServletRequest request, PrintWriter writer) {
		String idStr = request.getParameter("id");
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(Integer.parseInt(idStr));
		if (this.salesOrderService.update(salesOrder)) {
			writer.write("{success:true, msg:'×÷·Ï³É¹¦!'}");
		} else {
			writer.write("{success:false, msg:'×÷·ÏÊ§°Ü!'}");
		}
	}
}