package com.yc.dao;

import com.yc.model.Notice;
import java.util.List;

public abstract interface NoticeDAO {
	
	public abstract boolean doCreate(Notice notice);

	public abstract boolean doDelete(int id);

	public abstract boolean doUpdate(Notice notice);

	public abstract List<Notice> findAll();

	public abstract Notice findByNoticeName(String name);

	public abstract List<Notice> findByPage(int page, int pageSize);

}
