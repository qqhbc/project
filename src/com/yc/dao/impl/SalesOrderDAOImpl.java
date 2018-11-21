package com.yc.dao.impl;

import com.yc.dao.SalesOrderDAO;
import com.yc.model.SaleItem;
import com.yc.model.SalesOrder;
import com.yc.util.HibernateUtil;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SalesOrderDAOImpl implements SalesOrderDAO {
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return this.hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean doCreate(SalesOrder salesOrder) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			session.save(salesOrder);
			Set<SaleItem> set = salesOrder.getSaleItems();
			for (SaleItem si : set) {
				session.save(si);
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

	public boolean doUpdate(SalesOrder salesOrder) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			SalesOrder order = (SalesOrder) session.load(SalesOrder.class, Integer.valueOf(salesOrder.getId()));
			order.setNullify(true);

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
	public List<SalesOrder> findAll() {
		String hql = "from SalesOrder";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrder> findAll(int start, int end) {
		String hql = "from SalesOrder";
		return this.hibernateUtil.executedQueryPage(hql, start, end);
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrder> findById(int id) {
		String hql = "from SalesOrder where id=" + id;
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrder> findByOrderCode(String orderCode) {
		String hql = "from SalesOrder where orderCode='" + orderCode + "'";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrder> findByUsername(String username) {
		String hql = "from SalesOrder where username='" + username + "'";
		return this.hibernateUtil.executedQuery(hql);
	}

	public boolean verify(int id) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			SalesOrder order = (SalesOrder) session.load(SalesOrder.class, Integer.valueOf(id));
			order.setStatus(true);

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
}