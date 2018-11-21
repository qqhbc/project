package com.yc.util;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUtil {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	public void close() {
		try {
			this.sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeSession(Session session) {
		try {
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rollbackTransaction(Transaction transaction) {
		try {
			if (transaction != null) {
				transaction.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public List executedQuery(String hql) {
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			list = session.createQuery(hql).list();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List executedQueryPage(String hql, int start, int end) {
		List list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			list = session.createQuery(hql).setFirstResult(start).setMaxResults(end).list();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return list;
	}

	public boolean executedDelete(String hql) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			session.createQuery(hql).executeUpdate();

			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return flag;
	}
}
