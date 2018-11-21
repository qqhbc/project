package com.yc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yc.dao.AdminDAO;
import com.yc.model.Admin;
import com.yc.util.HibernateUtil;

public class AdminDAOImpl implements AdminDAO{
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return this.hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
	@Override
	public Admin findAdminByUsername(String username) {
		String hql = "from Admin where username='"+username+"'";
		return (Admin)this.hibernateUtil.getSession().createQuery(hql).uniqueResult();
	}
	public boolean doCreate(Admin admin) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			session.save(admin);

			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			this.hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.hibernateUtil.closeSession(session);
		}
		return flag;
	}

	public boolean doDelete(int id) {
		String hql = "delete from  Admin where id=" + id;
		return this.hibernateUtil.executedDelete(hql);
	}

	public boolean doUpdate(Admin admin) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Admin a = (Admin) session.load(Admin.class, 3);
			if (admin.getUsername() != null) {
				a.setUsername(admin.getUsername());
			}
			
			if (admin.getPassword() != null) {
				a.setPassword((admin.getPassword()));
			}
			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			this.hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.hibernateUtil.closeSession(session);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<Admin> findAll() {
		String hql = "from Admin";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Admin> findByPage(int start, int end) {
		String hql = "from Admin where status = 2";
		return this.hibernateUtil.executedQueryPage(hql, start, end);
	}

}
