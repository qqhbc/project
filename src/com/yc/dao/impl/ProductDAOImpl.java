package com.yc.dao.impl;

import com.yc.dao.ProductDAO;
import com.yc.model.Product;
import com.yc.util.HibernateUtil;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDAOImpl implements ProductDAO {
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return this.hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean doCreate(Product product) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			session.save(product);

			transaction.commit();
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			this.hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.hibernateUtil.closeSession(session);
		}
		return flag;
	}

	public boolean doDelete(int id) {
		String hql = "delete from Product where id=" + id;
		return this.hibernateUtil.executedDelete(hql);
	}

	public boolean doUpdate(Product product) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Product pro = (Product) session.load(Product.class, Integer.valueOf(product.getId()));
			if (product.getDescription() != null) {
				pro.setDescription(product.getDescription());
			}
			if (product.getCategory() != null) {
				pro.setCategory(product.getCategory());
			}
			if (product.getName() != null) {
				pro.setName(product.getName());
			}
			if (product.getMaketPrice() != 0) {
				pro.setMaketPrice(product.getMaketPrice());
			}
			if (product.getMemberPrice() != 0) {
				pro.setMemberPrice(product.getMemberPrice());
			}
			if (product.getPdate() != null) {
				pro.setPdate(product.getPdate());
			}
			if (product.getPhoto() != null) {
				pro.setPhoto(product.getPhoto());
			}
			transaction.commit();
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			this.hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.hibernateUtil.closeSession(session);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		String hql = "from Product";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAllPage(int start, int end) {
		String hql = "from Product";
		return this.hibernateUtil.executedQueryPage(hql, start, end);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAllByKeyword(String keyword) {
		String hql = null;
		if (keyword != null) {
			hql = "from Product where name like '%" + keyword + "%' or description like '%" + keyword + "%'";
		}
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryId(String categoryId) {
		String hql = null;
		if (categoryId != null) {
			hql = "from Product where category ='" + categoryId + "'";
		}
		return this.hibernateUtil.executedQuery(hql);
	}
}