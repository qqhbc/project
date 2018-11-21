package com.yc.service;

import com.yc.model.Notice;
import java.util.List;

public abstract interface NoticeService {
	
	public abstract boolean add(Notice notice);

	public abstract boolean remove(String[] ids);

	public abstract boolean update(Notice notice);

	public abstract List<Notice> getNotices();

	public abstract List<Notice> getNoticesPage(int start, int end);

	public abstract Notice getNoticeByName(String name);


	public abstract int getCount();
}
