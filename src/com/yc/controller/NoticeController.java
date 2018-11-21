package com.yc.controller;

import com.yc.model.Notice;
import com.yc.service.NoticeService;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	private NoticeService noticeService;

	public NoticeService getNoticeService() {
		return this.noticeService;
	}

	@Resource
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
		
	}
	
	@RequestMapping(value = "/add_notice", method = RequestMethod.POST)
	public void addNotice(HttpServletRequest request, PrintWriter writer) throws Exception {
		Notice notice = new Notice();
		notice.setName(request.getParameter("name"));
		notice.setDescription(request.getParameter("description"));
		notice.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
		notice.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")));
		if (this.noticeService.add(notice)) {
			writer.write("{success:true,msg:'新增成功！'}");
		} else {
			writer.write("{success:false,msg:'新增失败！'}");
		}
	}

	@RequestMapping(value = "/remove_notice", method = RequestMethod.POST)
	public void removeNotice(HttpServletRequest request, PrintWriter writer) {
		String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");
		if (this.noticeService.remove(ids)) {
			writer.write("{success:true,msg:'删除成功！'}");
		} else {
			writer.write("{success:false,msg:'删除失败！'}");
		}
	}

	@RequestMapping(value = "/update_notice", method = RequestMethod.POST)
	public void updateNotice(HttpServletRequest request, PrintWriter writer) throws Exception{
		Notice notice = new Notice();
		notice.setName(request.getParameter("name"));
		notice.setDescription(request.getParameter("description"));
		notice.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
		notice.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")));
		if (this.noticeService.update(notice)) {
			writer.write("{success:true,msg:'修改成功！'}");
		} else {
			writer.write("{success:false,msg:'修改失败!'}");
		}
	}
	@RequestMapping(value = "/list_notice", method = RequestMethod.GET)
	public void listNotice(HttpServletRequest request, PrintWriter writer) {
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		List<Notice> Notices = this.noticeService.getNoticesPage(Integer.parseInt(startStr), Integer.parseInt(limitStr));
		int len = Notices.size();
		String preStr = "{totalCount:" + this.noticeService.getCount() + ",rows:[";
		String cenStr = "";
		int i = 0;
		String comma = ",";
		for (Notice notice : Notices) {
			i++;
			cenStr = cenStr + "{id:'" + notice.getId() + "', " + "name:'" + notice.getName() + "'," + "description:'"
					+ notice.getDescription() + "'," + "startDate:'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(notice.getStartDate()) + "'," + "endDate:'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(notice.getEndDate()) 
					 + "'"
					+ "}";
			if (i < len) {
				cenStr = cenStr + comma;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}
}