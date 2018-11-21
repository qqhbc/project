package com.yc.service.impl;

import com.yc.factory.ApplicationContextFactory;
import com.yc.model.User;
import com.yc.service.UserService;

import java.sql.Timestamp;
import org.junit.Test;

public class TestUserServiceImpl {
	private UserService getUserService() {
		return (UserService) ApplicationContextFactory.getApplicationContext().getBean("userService");
	}

	@Test
	public void testRegister() {
		User user = new User();
		user.setName("…Ú¡÷");
		user.setPassword("335466");
		user.setIp("127.0.0.1");
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		System.out.println("◊¢≤·" + getUserService().register(user));
	}
}
