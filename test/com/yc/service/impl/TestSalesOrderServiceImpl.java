package com.yc.service.impl;

import com.yc.factory.ApplicationContextFactory;
import com.yc.model.SaleItem;
import com.yc.model.SalesOrder;
import com.yc.service.SalesOrderService;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class TestSalesOrderServiceImpl {
	private SalesOrderService getSalesOrderService() {
		return (SalesOrderService) ApplicationContextFactory.getApplicationContext().getBean("salesOrderService");
	}

	@Test
	public void testAdd() {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setOrderCode("dd0001");
		salesOrder.setUsername("����ҫ");
		salesOrder.setAddress("����");
		salesOrder.setPhone("18230526557");
		salesOrder.setOdate(new Timestamp(System.currentTimeMillis()));

		Set<SaleItem> saleItems = new HashSet<SaleItem>();
		SaleItem si1 = new SaleItem();
		si1.setProductName("ţ��1");
		si1.setPrice(515.2);
		si1.setCount(12);
		SaleItem si2 = new SaleItem();
		si2.setProductName("ţ��2");
		si2.setPrice(55.2);
		si2.setCount(22);
		saleItems.add(si1);
		saleItems.add(si2);
		salesOrder.setSaleItems(saleItems);
		System.out.println("��Ӷ���" + getSalesOrderService().add(salesOrder));
	}

	@Test
	public void testFindAll() {
		List<SalesOrder> list = getSalesOrderService().getSalesOrders();
		for (SalesOrder order : list) {
			System.out.println("�����ţ�" + order.getOrderCode());
			Set<SaleItem> items = order.getSaleItems();
			for (SaleItem item : items) {
				System.out.println("��������Ӧ��Ʒ���ƣ�" + item.getProductName());
			}
		}
	}

	@Test
	public void testUpdate() {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(1);
		System.out.println("�Ƿ�������" + getSalesOrderService().update(salesOrder));
	}

	@Test
	public void testVerify() {
		int id = 1;
		System.out.println("���" + getSalesOrderService().verify(id));
	}

	@Test
	public void testFindAllPage() {
		List<SalesOrder> list = getSalesOrderService().getSalesOrdersPage(1, 3);
		for (SalesOrder order : list) {
			System.out.println("�����ţ�" + order.getOrderCode());
			Set<SaleItem> items = order.getSaleItems();
			for (SaleItem item : items) {
				System.out.println("��������Ӧ��Ʒ���ƣ�" + item.getProductName());
			}
		}
	}

	@Test
	public void testFindByOrderCode() {
		String orderCode = "dd0003";
		List<SalesOrder> list = getSalesOrderService().getSalesOrderByOrderCode(orderCode);
		for (SalesOrder order : list) {
			System.out.println("�����ţ�" + order.getOrderCode());
			Set<SaleItem> items = order.getSaleItems();
			for (SaleItem item : items) {
				System.out.println("��������Ӧ��Ʒ���ƣ�" + item.getProductName());
			}
		}
	}

	@Test
	public void testFindByUsername() {
		String username = "����";
		List<SalesOrder> list = getSalesOrderService().getSalesOrderByUsername(username);
		for (SalesOrder order : list) {
			System.out.println("�����ţ�" + order.getOrderCode());
			Set<SaleItem> items = order.getSaleItems();
			for (SaleItem item : items) {
				System.out.println("��������Ӧ��Ʒ���ƣ�" + item.getProductName());
			}
		}
	}
}
