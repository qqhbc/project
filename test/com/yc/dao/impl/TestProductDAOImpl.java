package com.yc.dao.impl;

import com.yc.dao.ProductDAO;
import com.yc.factory.ApplicationContextFactory;
import com.yc.model.Category;
import com.yc.model.Product;

import java.sql.Timestamp;
import java.util.List;
import org.junit.Test;

public class TestProductDAOImpl {
	private ProductDAO getProductDAO() {
		return (ProductDAO) ApplicationContextFactory.getApplicationContext().getBean("productDAO");
	}

	@Test
	public void testDoCreate() {
		Product product = new Product();
		for (int i = 0; i < 1; i++) {
			product.setName("ţ��" + i);
			product.setDescription("�óԵ�ţ��");
			product.setMaketPrice(22.21);
			product.setMemberPrice(11.10);
			product.setPdate(new Timestamp(System.currentTimeMillis()));
			Category category = new Category();
			category.setId(1);
			product.setCategory(category);
			product.setPhoto("bazym.jpg");
			System.out.println("���" + getProductDAO().doCreate(product));
		}
	}

	@Test
	public void testDoDelete() {
		System.out.println("ɾ��" + getProductDAO().doDelete(32));
	}

	@Test
	public void testDoUpdate() {
		Product product = new Product();
		product.setId(1);
		product.setDescription("�ǳ�����һ����");
		product.setName("java���˼��");
		System.out.println("�޸�" + getProductDAO().doUpdate(product));
	}

	@Test
	public void testFindAll() {
		List<Product> list = getProductDAO().findAll();
		for (Product p : list) {
			System.out.println("��Ʒ����" + p.getName() + "�г��ۣ�" + p.getMaketPrice());
		}
	}

	@Test
	public void testFindPage() {
		List<Product> list = getProductDAO().findAllPage(1, 5);
		for (Product p : list) {
			System.out.println("��Ʒ����" + p.getName() + "�г��ۣ�" + p.getMaketPrice());
		}
	}

	@Test
	public void testFindByKeyword() {
		String keyword = "��";
		List<Product> list = getProductDAO().findAllByKeyword(keyword);
		for (Product p : list) {
			System.out.println("��Ʒ����" + p.getName() + "�г��ۣ�" + p.getMaketPrice());
		}
	}

	@Test
	public void testFindByCategoryId() {
		String categoryId = "1";
		List<Product> list = getProductDAO().findByCategoryId(categoryId);
		for (Product p : list) {
			System.out.println("��Ʒ����" + p.getName());
		}
	}
}
