package com.yc.dao.impl;

import com.yc.dao.UserDAO;
import com.yc.factory.ApplicationContextFactory;
import com.yc.model.User;

import java.sql.Timestamp;
import java.util.List;
import org.junit.Test;

public class TestUserDAOImpl {
	private UserDAO getUserDAO() {
		return (UserDAO) ApplicationContextFactory.getApplicationContext().getBean("userDAO");
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setName("ÁõÎÄ½Ü");
		user.setPassword("123456");
		user.setIp("127.0.0.1");
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		System.out.println(getUserDAO().doCreate(user));
	}

	@Test
	public void testCheckUserName() {
		System.out.println(getUserDAO().checkUserName("ºúÌìÒ«"));
	}

	@Test
	public void testDelete() {
		System.out.println(getUserDAO().doDelete(1));
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testFindAll() {
		List list = getUserDAO().findAll();
		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);
			System.out.println("ÐÕÃû:" + user.getName());
		}
	}

	@Test
	public void testLogin() {
		System.out.println("µÇÂ¼" + getUserDAO().login("ºúÌìÒ«", "123456"));
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setId(2);
		user.setName("ÍõÅô2Ùã");
		System.out.println("ÐÞ¸Ä" + getUserDAO().doUpdate(user));
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testFindAllByPage() {
		int start = 1;
		int end = 10;
		List list = getUserDAO().findByPage(start, end);
		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);
			System.out.println("ÐÕÃû£º" + user.getName());
		}
	}
}
