package com.yc.service.impl;

import com.yc.dao.NoticeDAO;
import com.yc.model.Notice;
import com.yc.service.NoticeService;
import java.util.List;
import javax.annotation.Resource;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDAO noticeDAO;

	public NoticeDAO getNoticeDao() {
		return this.noticeDAO;
	}

	@Resource
	public void setNoticeDao(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	public boolean remove(String[] ids) {
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			flag = this.noticeDAO.doDelete(Integer.parseInt(ids[i]));
		}
		return flag;
	}

	public boolean update(Notice notice) {
		return this.noticeDAO.doUpdate(notice);
	}

	public List<Notice> getNotices() {
		return this.noticeDAO.findAll();
	}

	public Notice getNoticeByName(String name) {
		return this.noticeDAO.findByNoticeName(name);
	}

	public List<Notice> getNoticesPage(int start, int end) {
		return this.noticeDAO.findByPage(start, end);
	}

	public int getCount() {
		return getNotices().size();
	}

	@Override
	public boolean add(Notice notice) {
		return noticeDAO.doCreate(notice);
	}

}