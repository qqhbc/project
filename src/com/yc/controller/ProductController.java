package com.yc.controller;

import com.yc.model.Category;
import com.yc.model.Product;
import com.yc.service.ProductService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/product")
public class ProductController {
	private ProductService productService;

	public ProductService getProductService() {
		return this.productService;
	}

	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/listall_product", method = RequestMethod.GET)
	public void listallProduct(HttpServletRequest request, PrintWriter writer) {
		List<Product> products = this.productService.getProducts();
		int len = products.size();
		String preStr = "{totalCount:" + this.productService.getCount() + ",rows:[";
		String cenStr = "";
		int i = 0;
		String comma = ",";
		for (Product product : products) {
			i++;
			cenStr =

					cenStr + "{id:'" + product.getId() + "'," + "name:'" + product.getName() + "'," + "description:'"
							+ product.getDescription() + "'," + "maketPrice:'" + product.getMaketPrice() + "',"
							+ "memberPrice:'" + product.getMemberPrice() + "'," + "pdate:'" + product.getPdate() + "',"
							+ "photo:'" + product.getPhoto() + "'," + "category:'" + product.getCategory().getText()
							+ "'" + "}";
			if (i < len) {
				cenStr = cenStr + comma;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}

	@RequestMapping(value = "/list_product" , method = RequestMethod.GET)
	public void listProduct(HttpServletRequest request, PrintWriter writer) {
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		List<Product> products = this.productService.getProductsPage(Integer.parseInt(startStr),
				Integer.parseInt(limitStr));
		int len = products.size();
		String preStr = "{totalCount:" + this.productService.getCount() + ",rows:[";
		String cenStr = "";
		int i = 0;
		String comma = ",";
		for (Product product : products) {
			i++;
			cenStr =

					cenStr + "{id:'" + product.getId() + "'," + "name:'" + product.getName() + "'," + "description:'"
							+ product.getDescription() + "'," + "maketPrice:'" + product.getMaketPrice() + "',"
							+ "memberPrice:'" + product.getMemberPrice() + "'," + "pdate:'" + product.getPdate() + "',"
							+ "photo:'" + product.getPhoto() + "'," + "category:'" + product.getCategory().getText()
							+ "'" + "}";
			if (i < len) {
				cenStr = cenStr + comma;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}

		@RequestMapping(value = "/add_product" , method = RequestMethod.POST )
		public void addProduct(@RequestParam MultipartFile photos, HttpServletRequest request, HttpServletResponse response,
				PrintWriter writer) throws IOException {
			String name = request.getParameter("name");
			String categoryId = request.getParameter("categoryId");
			String maketPrice = request.getParameter("maketPrice");
			String memberPrice = request.getParameter("memberPrice");
			String description = request.getParameter("description");
			Product product = new Product();
			product.setName(name);
			Category category = new Category();
			category.setId(Integer.parseInt(categoryId));
			product.setCategory(category);
			product.setDescription(description);
			product.setMaketPrice(Math.round(Float.parseFloat(maketPrice) * 100.0F) / 100.0D);
			product.setMemberPrice(Math.round(Float.parseFloat(memberPrice) * 100.0F) / 100.0D);
			product.setPdate(new Timestamp(System.currentTimeMillis()));
			if (photos.isEmpty()) {
				System.out.println("文件未上传！");
				product.setPhoto("default.jpg");
			} else {
				product.setPhoto(photos.getOriginalFilename());
				String realPath = request.getSession().getServletContext().getRealPath("/images/product");
				//实现文件上传
				FileUtils.copyInputStreamToFile(photos.getInputStream(), new File(realPath, photos.getOriginalFilename()));
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;  charset=UTF-8");
			if (this.productService.add(product)) {
				writer.write("{success:true, msg:'商品新增成功!'}");
			} else {
				writer.write("{success:false, msg:'商品新增失败!'}");
			}
		}

	@RequestMapping(value = "/remove_product" , method = RequestMethod.POST )
	public void removeProduct(HttpServletRequest request, PrintWriter writer) {
		String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");
		if (this.productService.remove(ids)) {
			writer.write("{success:true,msg:'商品删除成功！'}");
		} else {
			writer.write("{success:true,msg:'商品删除失败！'}");
		}
	}

	@RequestMapping(value = "/update_product", method = RequestMethod.POST )
	public void updateProduct(@RequestParam MultipartFile photos, HttpServletRequest request,
			HttpServletResponse response, PrintWriter writer) throws IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String categoryId = request.getParameter("categoryId");
		String memberPrice = request.getParameter("memberPrice");
		String maketPrice = request.getParameter("maketPrice");
		String description = request.getParameter("description");
		Product product = new Product();
		product.setId(Integer.parseInt(id));
		product.setName(name);
		product.setMemberPrice(Math.round(Float.parseFloat(memberPrice) * 100.0F) / 100.0D);
		product.setMaketPrice(Math.round(Float.parseFloat(maketPrice) * 100.0F) / 100.0D);
		product.setDescription(description);
		Category category = new Category();
		category.setId(Integer.parseInt(categoryId));
		product.setCategory(category);
		if (!photos.isEmpty()) {
			product.setPhoto(photos.getOriginalFilename());
			String realPath = request.getSession().getServletContext().getRealPath("/images/product");
			FileUtils.copyInputStreamToFile(photos.getInputStream(), new File(realPath, photos.getOriginalFilename()));
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;  charset=UTF-8");
		if (this.productService.update(product)) {
			writer.write("{success:true, msg:'商品修改成功!',photo:'" + photos.getOriginalFilename() + "'}");
		} else {
			writer.write("{success:false, msg:'商品修改失败!'}");
		}
	}
}
