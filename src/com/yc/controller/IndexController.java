package com.yc.controller;

import com.yc.model.Admin;
import com.yc.model.Cart;
import com.yc.model.CartItem;
import com.yc.model.Category;
import com.yc.model.Product;
import com.yc.model.SaleItem;
import com.yc.model.SalesOrder;
import com.yc.model.User;
import com.yc.modeldto.OrderFormDto;
import com.yc.service.AdminService;
import com.yc.service.CategoryService;
import com.yc.service.ProductService;
import com.yc.service.SalesOrderService;
import com.yc.service.UserService;
import com.yc.util.TimeStamp;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	private CategoryService categoryService;
	private UserService userService;
	private AdminService adminService;
	private ProductService productService;
	private SalesOrderService salesOrderService;
	private List<Product> productList;
	private List<Product> productList25Last;
	private List<Category> categoryList;

	public CategoryService getCategoryService() {
		return this.categoryService;
	}

	@Resource
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public UserService getUserService() {
		return this.userService;
	}

	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public AdminService getAdminService() {
		return this.adminService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProductService getProductService() {
		return this.productService;
	}

	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public SalesOrderService getSalesOrderService() {
		return this.salesOrderService;
	}

	@Resource
	public void setSalesOrderService(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ModelAndView yc(ModelAndView mav) {
		this.productList = this.productService.getProducts();

		this.productList25Last = new ArrayList();
		Collections.reverse(this.productList);
		for (int i = 0; i < this.productList.size(); i++) {
			if (i < 25) {
				this.productList25Last.add((Product) this.productList.get(i));
			}
		}
		this.categoryList = this.categoryService.getCategorys();
		mav.setViewName("index");
		mav.addObject("page", Integer.valueOf(1));
		mav.addObject("goPage", Integer.valueOf(1));
		mav.addObject("categoryList", this.categoryList);
		mav.addObject("productList", this.productList);
		mav.addObject("productList25Last", this.productList25Last);
		return mav;
	}
	//浏览图书
	@RequestMapping("/index" )
	public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		yc(mav);
		Cart cart = (Cart) session.getAttribute("cart");
		List<CartItem> cartList = null;
		if (cart != null) {
			cartList = cart.getList();
			mav.addObject("size", Integer.valueOf(cartList.size()));
		}
		return mav;
	}
  //用户登录
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		if ((username != null) && (!"".equals(username)) && (password != null) && (!"".equals(password))) {
			if (this.userService.login(username, password)) {
				user = this.userService.getUser(username);
				session.setAttribute("user", user);
				yc(mav);
			} else {
				mav.setViewName("login");
				mav.addObject("error", "登录失败！");
			}
		}
		return mav;
	}
	//退出用户
	@RequestMapping("/logout" )
	public ModelAndView logout(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		//注销用户
		session.removeAttribute("user");
		yc(mav);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping( "/buy" )
	public ModelAndView buy(HttpServletRequest request, CartItem item, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		User user = (User) session.getAttribute("user");
		if (user == null) {
			mav.setViewName("login");
			return mav;
		}
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		CartItem cartItem = new CartItem();
		cartItem.setProductId(item.getProductId());
		cartItem.setProductName(item.getProductName());
		cartItem.setCount(1);
		cartItem.setPrice(item.getPrice());
		cart.add(cartItem);
		List<CartItem> cartList = cart.getList();
		mav.setViewName("cart");
		mav.addObject("cartList", cartList);
		mav.addObject("size", Integer.valueOf(cartList.size()));
		mav.addObject("totalPrice", Double.valueOf(cart.getTotalPrice()));
		return mav;
	}

	@RequestMapping( "/buy_update" )
	public ModelAndView buyUpdate(HttpServletRequest request, CartItem cartItem, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Cart cart = (Cart) session.getAttribute("cart");
		List<CartItem> cartList = cart.getList();
		for (int i = 0; i < cartList.size(); i++) {
			CartItem ci = (CartItem) cartList.get(i);
			String strCount = request.getParameter("p" + ci.getProductId());
			if ((strCount != null) && (!"".equals(strCount.trim())) && (Integer.parseInt(strCount) != 0)) {
				ci.setCount(Integer.parseInt(strCount));
			}
		}
		TimeStamp its = new TimeStamp();
		String orderCode = (String) session.getAttribute("orderCode");
		if ((orderCode == null) || ("".equals(orderCode))) {
			orderCode = its.getTimeStamp();
			request.setAttribute("orderCode", orderCode);
		}
		String confirm = request.getParameter("confirm");
		if ((confirm != null) && (!"".equals(confirm)) && (cartList.size() != 0)) {
			User user = (User) session.getAttribute("user");
			mav.setViewName("confirm");
			mav.addObject("msg", "修改成功！");
			mav.addObject("user", user);
			mav.addObject("cartList", cartList);
			mav.addObject("size", Integer.valueOf(cartList.size()));
			mav.addObject("orderCode", orderCode);
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mav.addObject("orderDate", sft.format(new Date(System.currentTimeMillis())));
			mav.addObject("totalPrice", Double.valueOf(cart.getTotalPrice()));
			return mav;
		}
		mav.setViewName("cart");
		mav.addObject("msg", "修改成功！");
		mav.addObject("cartList", cartList);
		mav.addObject("size", Integer.valueOf(cartList.size()));
		mav.addObject("totalPrice", Double.valueOf(cart.getTotalPrice()));
		return mav;
	}

	@RequestMapping( "/buy_delete" )
	public ModelAndView buyDelete(HttpServletRequest request, CartItem cartItem, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Cart cart = (Cart) session.getAttribute("cart");
		List<CartItem> cartList = cart.getList();
		for (int i = 0; i < cartList.size(); i++) {
			CartItem ci = (CartItem) cartList.get(i);
			String productId = request.getParameter("productId");
			if (ci.getProductId() == Integer.parseInt(productId)) {
				cartList.remove(i);
			}
		}
		mav.addObject("msg", "修改成功！");
		mav.addObject("cartList", cartList);
		mav.addObject("size", Integer.valueOf(cartList.size()));
		mav.addObject("totalPrice", Double.valueOf(cart.getTotalPrice()));
		if (cartList.size() == 0) {
			yc(mav);
			mav.setViewName("index");
		} else {
			mav.setViewName("cart");
		}
		return mav;
	}
	@RequestMapping("/paySuccess")
	public ModelAndView paySuccess(HttpServletRequest request, OrderFormDto orderFormDto, HttpSession session)throws Exception {
		ModelAndView mav = new ModelAndView();
		SalesOrder order = new SalesOrder();
		order.setUsername(request.getParameter("username"));
		order.setOrderCode(request.getParameter("orderCode"));
		order.setOdate(new Timestamp(System.currentTimeMillis()));
		order.setPhone(request.getParameter("phone"));
		order.setQQ(request.getParameter("qq"));
		order.setAddress(request.getParameter("address"));
		order.setRemark(request.getParameter("remark"));
		Set<SaleItem> saleItems = new HashSet<SaleItem>();
		List<SaleItem> items = orderFormDto.getSaleitems();
		for (SaleItem item : items) {
			saleItems.add(item);
		}
		order.setSaleItems(saleItems);
		if (this.salesOrderService.add(order)) {
			mav.setViewName("orderend");

			session.removeAttribute("cart");
		} else {
			mav.setViewName("confirm");
			mav.addObject("ordermsg", "定单没有提交成功！请检查你的网络再试");
		}
		return mav;
	}

	@RequestMapping("/confirm_order" )
	public ModelAndView confirmOrder(HttpServletRequest request, OrderFormDto orderFormDto, HttpSession session)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		SalesOrder order = new SalesOrder();
		order.setUsername(request.getParameter("username"));
		order.setOrderCode(request.getParameter("orderCode"));
		order.setOdate(new Timestamp(System.currentTimeMillis()));
		order.setPhone(request.getParameter("phone"));
		order.setQQ(request.getParameter("qq"));
		order.setAddress(request.getParameter("address"));
		order.setRemark(request.getParameter("remark"));
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		mav.addObject("prize", cart.getTotalPrice());
		mav.addObject("cartList", cart.getList());
		mav.addObject("order", order);
		mav.setViewName("pay");
		return mav;
	}
    //图书名称搜索和类别搜索
	@RequestMapping("/searchKeyword" )
	public ModelAndView searchKeyword(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String keyword = request.getParameter("keyword");
		List<Product> productList25Last = null;
		if ((keyword == "请输入您所要查找的商品名称") || ("请输入您所要查找的商品名称".equals(keyword))) {
			int goPage = 1;
			int rows = 25;
			String pageStr = request.getParameter("goPage");
			if ((pageStr != null) && (!"".equals(pageStr))) {
				goPage = Integer.parseInt(pageStr);
			}
			this.productList = this.productService.getProducts();
			int total = this.productList.size();
			int page = (total + rows - 1) / rows;
			productList25Last = this.productService.getProductsPage((goPage - 1) * rows, rows);
			mav.addObject("goPage", Integer.valueOf(goPage));
			mav.addObject("page", Integer.valueOf(page));
		} else {
			productList25Last = this.productService.getProductsByKeyword(keyword);
			if (productList25Last.size() == 0) {
				mav.addObject("Msg", keyword + "未找到！！你可能喜欢：");
				productList25Last = this.productService.getProductsByKeyword("java");
			}
		}
		this.categoryList = this.categoryService.getCategorys();

		this.productList = this.productService.getProducts();
		mav.setViewName("index");
		mav.addObject("categoryList", this.categoryList);
		mav.addObject("productList", this.productList);
		mav.addObject("productList25Last", productList25Last);
		return mav;
	}

	@RequestMapping("/searchCategory" )
	public ModelAndView searchCategory(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String categoryId = request.getParameter("categoryId");
		this.productList25Last.clear();

		this.categoryList = this.categoryService.getCategorys();

		this.productList = this.productService.getProducts();
		for (Product p : this.productList) {
			if (p.getCategory().getId() == Integer.parseInt(categoryId)) {
				this.productList25Last.add(p);
				if (this.productList25Last.size() > 25) {
					this.productList25Last.remove(p);
				}
			}
		}
		mav.setViewName("index");
		mav.addObject("page", Integer.valueOf(1));
		mav.addObject("goPage", Integer.valueOf(1));
		mav.addObject("categoryList", this.categoryList);
		mav.addObject("productList", this.productList);
		mav.addObject("productList25Last", this.productList25Last);
		return mav;
	}

	@RequestMapping( "/usernameValidate" )
	public void usernameValidate(HttpServletResponse response, HttpServletRequest request, HttpSession session)
			throws IOException {
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-store");
		response.setDateHeader("Expires", 0L);

		String username = request.getParameter("username");
		if (this.userService.checkUserName(username)) {
			response.getWriter().write("<msg>true</msg>");
		} else {
			response.getWriter().write("<msg>false</msg>");
		}
	}

	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping("/registerShop")
	public ModelAndView registerShop(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registerShop");
		return mav;
	}

	@RequestMapping({ "/agreement" })
	public ModelAndView agreement(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("agreement");
		return mav;
	}
	
	@RequestMapping({ "/agreementShop" })
	public ModelAndView agreementShop(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("agreementShop");
		return mav;
	}
	
	//用户注册
	@RequestMapping("/user_register")
	public ModelAndView userRegister(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
		user.setAddress(request.getParameter("address"));
		user.setIp(request.getRemoteAddr());
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		if (!"".equals(request.getParameter("QQ"))) {
			user.setQQ(request.getParameter("QQ"));
		}
		if (!"".equals(request.getParameter("email"))) {
			user.setEmail(request.getParameter("email"));
		}
		user.setSex(Boolean.parseBoolean(request.getParameter("sex")));
		if (this.userService.register(user)) {
			mav.setViewName("redirect:/index.htm");
			session.setAttribute("user", user);
		} else {
			mav.addObject("msg", "注册失败");
			mav.setViewName("register");
		}
		return mav;
	}
	
	@RequestMapping("/shop_register")
	public ModelAndView shopRegister(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Admin admin = new Admin();
		admin.setUsername(request.getParameter("username"));
		admin.setPassword(request.getParameter("password"));
		admin.setStatus(2);
		if (this.adminService.add(admin)) {
			mav.setViewName("adminLogin");
		} else {
			mav.addObject("msg", "注册失败");
			mav.setViewName("registerShop");
		}
		return mav;
	}


	@RequestMapping("/mycart" )
	public ModelAndView mycart(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Cart cart = (Cart) session.getAttribute("cart");
		List<CartItem> carList = null;
		if (cart != null) {
			carList = cart.getList();
			mav.addObject("cartList", carList);
			mav.addObject("size", Integer.valueOf(carList.size()));
			mav.setViewName("cart");
		} else {
			mav.setViewName("cart");
			mav.addObject("size", Integer.valueOf(0));
		}
		return mav;
	}

	@RequestMapping("/searchOrderByusername")
	public ModelAndView searchOrderByUsername(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		Cart cart = (Cart) session.getAttribute("cart");
		List<CartItem> cartList = null;
		if (cart != null) {
			cartList = cart.getList();
			mav.addObject("size", Integer.valueOf(cartList.size()));
		}
		String username = request.getParameter("username");
		List<SalesOrder> orders = this.salesOrderService.getSalesOrderByUsername(username);
		if (orders.size() != 0) {
			SalesOrder order = (SalesOrder) orders.get(0);

			Set<SaleItem> items = order.getSaleItems();

			double totalPrice = 0.0D;
			for (SaleItem item : items) {
				totalPrice += item.getCount() * item.getPrice();
				mav.addObject("totalPrice", Double.valueOf(totalPrice));
			}
		}
		mav.addObject("orders", orders);
		mav.setViewName("order");
		return mav;
	}

	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		Cart cart = (Cart) session.getAttribute("cart");
		List<CartItem> cartList = null;
		if (cart != null) {
			cartList = cart.getList();
			mav.addObject("size", Integer.valueOf(cartList.size()));
		}
		String orderCode = request.getParameter("orderCode");
		List<SalesOrder> orders = this.salesOrderService.getSalesOrderByOrderCode(orderCode);

		SalesOrder order = (SalesOrder) orders.get(0);
		Set<SaleItem> items = order.getSaleItems();
		double totalPrice = 0.0D;
		for (SaleItem item : items) {
			totalPrice += item.getCount() * item.getPrice();
		}
		if (orders != null) {
			mav.addObject("order", order);
			mav.addObject("items", items);
			mav.addObject("totalPrice", Double.valueOf(totalPrice));
			mav.setViewName("orderdetail");
		}
		return mav;
	}
	@RequestMapping("/selectByUsername")
	public ModelAndView updateByUserId(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("updateUser");
		return mav;
	}
	//个人信息查看和修改
	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User) session.getAttribute("user");
		user.setName(request.getParameter("name"));
		user.setPhone(request.getParameter("phone"));
		user.setQQ(request.getParameter("QQ"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		if(userService.update(user)){
		mav.setViewName("updateUser");
		}
		return mav;
	}
	//升级会员
	@RequestMapping("/member")
	public ModelAndView member(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User) session.getAttribute("user");
		//false表示普通用户，true表示会员
		user.setMember(true);
		if(userService.update(user)){
		mav.setViewName("updateUser");
		}
		return mav;
	}
}