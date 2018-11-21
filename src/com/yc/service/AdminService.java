package com.yc.service;

import java.util.List;

import com.yc.model.Admin;

public interface AdminService {
	/** ���ݵ�¼����ѯ����Ա����*/
	Admin findAdminByUsername(String username);
	
	public abstract boolean add(Admin admin);

	public abstract boolean remove(String[] ids);

	public abstract boolean update(Admin admin);

	public abstract List<Admin> getAdmins();

	public abstract List<Admin> getAdminsPage(int start, int end);

	public abstract int getCount();
}
