package com.yc.service.impl;

import com.yc.dao.UserDAO;
import com.yc.model.User;
import com.yc.service.UserService;
import java.util.List;
import javax.annotation.Resource;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;

	public UserDAO getUserDao() {
		return this.userDAO;
	}

	@Resource
	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean register(User user) {
		if (checkUserName(user.getName())) {
			return false;
		}
		return this.userDAO.doCreate(user);
	}

	public boolean remove(String[] ids) {
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			flag = this.userDAO.doDelete(Integer.parseInt(ids[i]));
		}
		return flag;
	}

	public boolean update(User user) {
		return this.userDAO.doUpdate(user);
	}

	public List<User> getUsers() {
		return this.userDAO.findAll();
	}

	public User getUser(String username) {
		return this.userDAO.findByUserName(username);
	}

	public List<User> getUsersPage(int start, int end) {
		return this.userDAO.findByPage(start, end);
	}

	public boolean login(String name, String password) {
		return this.userDAO.login(name, password);
	}

	public boolean checkUserName(String username) {
		return this.userDAO.checkUserName(username);
	}

	public int getCount() {
		return getUsers().size();
	}
}