package com.yc.dao;

import java.util.List;

import com.yc.model.Admin;

public interface AdminDAO {
	/** 根据登录名查询管理员数据*/
	Admin findAdminByUsername(String username);
	
	public abstract boolean doCreate(Admin admin);

	public abstract boolean doDelete(int id);

	public abstract boolean doUpdate(Admin admin);

	public abstract List<Admin> findAll();

	public abstract List<Admin> findByPage(int page, int pageSize);
}
