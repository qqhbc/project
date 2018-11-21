package com.yc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.yc.dao.AdminDAO;
import com.yc.model.Admin;
import com.yc.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDAO adminDAO;
	

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	@Resource
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}


	@Override
	public Admin findAdminByUsername(String username) {
		return adminDAO.findAdminByUsername(username);
	}
	
	public boolean remove(String[] ids) {
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			flag = this.adminDAO.doDelete(Integer.parseInt(ids[i]));
		}
		return flag;
	}

	public boolean update(Admin Admin) {
		return this.adminDAO.doUpdate(Admin);
	}

	public List<Admin> getAdmins() {
		return this.adminDAO.findAll();
	}

	public List<Admin> getAdminsPage(int start, int end) {
		return this.adminDAO.findByPage(start, end);
	}

	public int getCount() {
		return getAdmins().size();
	}

	@Override
	public boolean add(Admin Admin) {
		return adminDAO.doCreate(Admin);
	}


}
