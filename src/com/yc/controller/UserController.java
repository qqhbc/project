package com.yc.controller;

import com.yc.model.User;
import com.yc.service.UserService;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	public UserService getUserService() {
		return this.userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request, PrintWriter writer) {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
		user.setQQ(request.getParameter("QQ"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		user.setSex(Boolean.parseBoolean(request.getParameter("sex")));
		user.setIp(request.getRemoteAddr());
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		if (this.userService.register(user)) {
			writer.write("{success:true,msg:'新增成功！'}");
		} else {
			writer.write("{success:false,msg:'新增失败！'}");
		}
	}

	@RequestMapping(value = "/remove_user", method = RequestMethod.POST)
	public void removeUser(HttpServletRequest request, PrintWriter writer) {
		String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");
		if (this.userService.remove(ids)) {
			writer.write("{success:true,msg:'删除成功！'}");
		} else {
			writer.write("{success:false,msg:'删除失败！'}");
		}
	}

	@RequestMapping(value = "/update_user", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request, User user, PrintWriter writer) {
		if (this.userService.update(user)) {
			writer.write("{success:true,msg:'修改成功！'}");
		} else {
			writer.write("{success:false,msg:'修改失败!'}");
		}
	}

	@RequestMapping(value = "/list_user", method = RequestMethod.GET)
	public void listUser(HttpServletRequest request, PrintWriter writer) {
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		List<User> users = this.userService.getUsersPage(Integer.parseInt(startStr), Integer.parseInt(limitStr));
		int len = users.size();
		String preStr = "{totalCount:" + this.userService.getCount() + ",rows:[";
		String cenStr = "";
		int i = 0;
		String comma = ",";
		for (User user : users) {
			i++;
			cenStr = cenStr + "{id:'" + user.getId() + "', " + "name:'" + user.getName() + "'," + "password:'"
					+ user.getPassword() + "'," + "sex:'" + user.isSex() + "'," + "phone:'" + user.getPhone() + "',"
					+ "QQ:'" + user.getQQ() + "'," + "email:'" + user.getEmail() + "'," + "address:'"
					+ user.getAddress() + "'," + "regDate:'" + user.getRegDate() + "'," + "ip:'" + user.getIp() + "'"
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