package com.yc.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yc.model.Admin;
import com.yc.service.AdminService;

@Controller
public class AdminController {
	private AdminService adminService;

	public AdminService getAdminService() {
		return adminService;
	}

	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/adminLogin.htm")
	public String toLogin() {
		return "adminLogin";
	}

	@RequestMapping(value = "/admin/login.htm")
	public String login(String username, HttpServletRequest request) {
		Admin admin = adminService.findAdminByUsername(username);
		if (admin == null) {
			request.setAttribute("error", "�̻��������ڣ�");
			return "adminLogin";
		}
		String password = request.getParameter("password");
		if (!password.equals(admin.getPassword())) {
			request.setAttribute("error", "�������");
			return "adminLogin";
		}
		if (admin.getStatus() == 2) {
			return "redirect:/shopIndex.jsp";
		}else{
			return "redirect:/adminIndex.jsp";
		}
	}
	@RequestMapping(value = "shop/add_shop", method = RequestMethod.POST)
	public void addadmin(HttpServletRequest request, PrintWriter writer) throws Exception {
		Admin admin = new Admin();
		admin.setUsername(request.getParameter("username"));
		admin.setPassword(request.getParameter("password"));
		admin.setStatus(2);
		if (this.adminService.add(admin)) {
			writer.write("{success:true,msg:'�����ɹ���'}");
		} else {
			writer.write("{success:false,msg:'����ʧ�ܣ�'}");
		}
	}

	@RequestMapping(value = "shop/remove_shop", method = RequestMethod.POST)
	public void removeadmin(HttpServletRequest request, PrintWriter writer) {
		String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");
		if (this.adminService.remove(ids)) {
			writer.write("{success:true,msg:'ɾ���ɹ���'}");
		} else {
			writer.write("{success:false,msg:'ɾ��ʧ�ܣ�'}");
		}
	}

	@RequestMapping(value = "shop/update_shop", method = RequestMethod.POST)
	public void updateadmin(HttpServletRequest request, PrintWriter writer) throws Exception{
		Admin admin = new Admin();
		admin.setUsername(request.getParameter("username"));
		admin.setPassword(request.getParameter("password"));
		if (this.adminService.update(admin)) {
			writer.write("{success:true,msg:'�޸ĳɹ���'}");
		} else {
			writer.write("{success:false,msg:'�޸�ʧ��!'}");
		}
	}
	@RequestMapping(value = "shop/list_shop", method = RequestMethod.GET)
	public void listadmin(HttpServletRequest request, PrintWriter writer) {
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		List<Admin> admins = this.adminService.getAdminsPage(Integer.parseInt(startStr), Integer.parseInt(limitStr));
		int len = admins.size();
		String preStr = "{totalCount:" + this.adminService.getCount() + ",rows:[";
		String cenStr = "";
		int i = 0;
		String comma = ",";
		for (Admin admin : admins) {
			i++;
			cenStr = cenStr + "{id:'" + admin.getId() + "', " + "username:'" + admin.getUsername() + "'," + "password:'"
					+ admin.getPassword() 
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
