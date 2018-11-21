package com.yc.dao.impl;

import com.yc.dao.NoticeDAO;
import com.yc.model.Notice;
import com.yc.util.HibernateUtil;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NoticeDAOImpl implements NoticeDAO {
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return this.hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean doCreate(Notice Notice) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			session.save(Notice);

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
		String hql = "delete from  Notice where id=" + id;
		return this.hibernateUtil.executedDelete(hql);
	}

	public boolean doUpdate(Notice notice) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			Notice n = (Notice) session.load(Notice.class, Integer.valueOf(notice.getId()));
			if (notice.getName() != null) {
				n.setName(notice.getName());
			}
			
			if (notice.getDescription() != null) {
				n.setDescription(notice.getDescription());
			}
			if (notice.getStartDate() != null) {
				n.setStartDate(notice.getStartDate());
			}
			if (notice.getEndDate() != null) {
				n.setEndDate(notice.getEndDate());
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
	public List<Notice> findAll() {
		String hql = "from Notice";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Notice> findByPage(int start, int end) {
		String hql = "from Notice";
		return this.hibernateUtil.executedQueryPage(hql, start, end);
	}

	public Notice findByNoticeName(String Noticename) {
		String hql = "from Notice where name='" + Noticename + "'";
		return (Notice) this.hibernateUtil.executedQuery(hql).get(0);
	}

}