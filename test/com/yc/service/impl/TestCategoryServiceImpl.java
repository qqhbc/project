package com.yc.service.impl;

import com.yc.factory.ApplicationContextFactory;
import com.yc.model.Category;
import com.yc.model.Product;
import com.yc.service.CategoryService;
import com.yc.util.YcException;

import java.util.List;
import java.util.Set;
import org.junit.Test;

public class TestCategoryServiceImpl {
	private CategoryService getCategoryService() {
		return (CategoryService) ApplicationContextFactory.getApplicationContext().getBean("categoryService");
	}

	@Test
	public void testAddRootCategory() {
		String text = "所有分类";
		String description = "分类描述";
		getCategoryService().addRootCategory(text, description);
	}

	@Test
	public void testAddChildCategory() {
		Category category = new Category();
		category.setText("小说");
		category.setDescription("魔幻世界");
		getCategoryService().addChildCategory(2, category);
	}

	@Test
	public void testRemoveById() {
		try {
			getCategoryService().removeById(1, 1);
		} catch (YcException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCategorys() {
		List<Category> list = getCategoryService().getCategorys();
		for (Category c : list) {
			System.out.println(c.getText());
		}
	}

	@Test
	public void testGetRootCategorys() {
		List<Category> list = getCategoryService().getRootCategorys();
		for (Category c : list) {
			System.out.println(c.getText());
		}
	}

	@Test
	public void testUpdate() {
		Category category = new Category();
		category.setId(4);
		category.setText("计算机");
		category.setDescription("博大精深");
		System.out.println("修改" + getCategoryService().update(category));
	}

	@Test
	public void testGetCategoryById() {
		int id = 1;
		List<Category> list = getCategoryService().getCategoryById(id);
		Set<Product> products = ((Category) list.get(0)).getProducts();
		for (Product p : products) {
			System.out.println("商品名字：" + p.getName());
		}
	}
}