package com.yc.dao.impl;

import com.yc.dao.CategoryDAO;
import com.yc.model.Category;
import com.yc.util.HibernateUtil;
import com.yc.util.YcException;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoryDAOImpl implements CategoryDAO {
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return this.hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean doCreate(Category category) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			session.save(category);

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

	public boolean doCreateChildCategory(int parentId, Category category) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Category cate = (Category) session.get(Category.class, Integer.valueOf(parentId));
			cate.setLeaf(false);
			category.setParentId(parentId);

			flag = doCreate(category);
			transaction.commit();
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

	public boolean doDelete(int id) throws YcException {
		String hql = "delete from Category where id=" + id;
		return this.hibernateUtil.executedDelete(hql);
	}

	public boolean doUpdate(Category category) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Category cate = (Category) session.load(Category.class, Integer.valueOf(category.getId()));
			cate.setText(category.getText());
			cate.setDescription(category.getDescription());
			cate.setParentId(category.getParentId());
			cate.setLeaf(category.isLeaf());
			cate.setProducts(category.getProducts());

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

	public boolean doUpateCategoryToLeaf(int id) throws YcException {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Category category = (Category) session.load(Category.class, Integer.valueOf(id));
			category.setLeaf(true);
			session.saveOrUpdate(category);

			transaction.commit();
			flag = true;
		} catch (ObjectNotFoundException oe) {
			throw new YcException("所有记录全部删除完毕");
		} catch (HibernateException he) {
			he.printStackTrace();
			this.hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		String hql = "from Category";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Category> findByParentId(int pid) {
		String hql = "from Category where parentId=" + pid;
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Category> findRootAll() {
		String hql = "from Category where parentId=0";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Category> findById(int id) {
		String hql = "from Category where id=" + id;
		return this.hibernateUtil.executedQuery(hql);
	}
}
