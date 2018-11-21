package com.yc.dao.impl;

import com.yc.dao.UserDAO;
import com.yc.model.User;
import com.yc.util.HibernateUtil;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO {
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return this.hibernateUtil;
	}

	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean doCreate(User user) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			session.save(user);

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
		String hql = "delete from  User where id=" + id;
		return this.hibernateUtil.executedDelete(hql);
	}

	public boolean doUpdate(User user) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.hibernateUtil.getSession();
			transaction = session.beginTransaction();

			User u = (User) session.load(User.class, Integer.valueOf(user.getId()));
			if (user.getName() != null) {
				u.setName(user.getName());
			}
			
			if (user.getPassword() != null) {
				u.setPassword(user.getPassword());
			}
			if (user.getAddress() != null) {
				u.setAddress(user.getAddress());
			}
			if (user.getEmail() != null) {
				u.setEmail(user.getEmail());
			}
			if (user.getQQ() != null) {
				u.setQQ(user.getQQ());
			}
			if (user.getPhone() != null) {
				u.setPhone(user.getPhone());
			}
			u.setMember(user.isMember());
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
	public List<User> findAll() {
		String hql = "from User";
		return this.hibernateUtil.executedQuery(hql);
	}

	@SuppressWarnings("unchecked")
	public List<User> findByPage(int start, int end) {
		String hql = "from User";
		return this.hibernateUtil.executedQueryPage(hql, start, end);
	}

	public User findByUserName(String username) {
		String hql = "from User where name='" + username + "'";
		return (User) this.hibernateUtil.executedQuery(hql).get(0);
	}

	public boolean login(String name, String password) {
		boolean flag = false;
		String hql = "from User where name='" + name + "' and password='" + password + "'";
		if (this.hibernateUtil.executedQuery(hql).size() > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean checkUserName(String username) {
		boolean flag = false;
		String hql = "from User where name='" + username + "'";
		if (this.hibernateUtil.executedQuery(hql).size() > 0) {
			flag = true;
		}
		return flag;
	}
}