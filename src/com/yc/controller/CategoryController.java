package com.yc.controller;

import com.yc.model.Category;
import com.yc.model.Product;
import com.yc.service.CategoryService;
import com.yc.service.ProductService;
import com.yc.util.YcException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
@Scope("prototype")
public class CategoryController {
	private CategoryService categoryService;
	private ProductService productService;
	private String str = "";

	public CategoryService getCategoryService() {
		return this.categoryService;
	}

	@Resource
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public ProductService getProductService() {
		return this.productService;
	}

	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/list_category", method = RequestMethod.GET)
	public void listCategory(HttpServletRequest request, PrintWriter writer) {
		writer.write("[" + outTreeJson(0) + "]");
	}

	private String outTreeJson(int pid) {
		List<Category> categorys = this.categoryService.getCategorysByParentId(pid);
		for (Category category : categorys) {
			this.str += "{";
			if (!category.isLeaf()) {
				this.str = (this.str + "id:'" + category.getId() + "'," + "text:'" + category.getText() + "',"
						+ "leaf:'" + category.isLeaf() + "'," + "expandable:true," + "description:'"
						+ category.getDescription() + "',");
				if (pid == 0) {
					this.str += "parentId:'root'";
				} else {
					this.str = (this.str + "parentId:'" + category.getParentId() + "'");
				}
				this.str += ",children:[";

				outTreeJson(category.getId());
			} else {
				this.str = (this.str + "id:'" + category.getId() + "'," + "text:'" + category.getText() + "',"
						+ "leaf:'" + category.isLeaf() + "'," + "expandable:true," + "description:'"
						+ category.getDescription() + "',");
				if (pid == 0) {
					this.str += "parentId:'root'";
				} else {
					this.str = (this.str + "parentId:'" + category.getParentId() + "'");
				}
				this.str += "},";
			}
		}
		if (pid != 0) {
			this.str = this.str.substring(0, this.str.length() - 1);
			this.str += "]},";
		} else {
			this.str = this.str.substring(0, this.str.length() - 1);
		}
		return this.str;
	}

	@RequestMapping(value = "/add_category", method = RequestMethod.POST)
	public void addCategory(HttpServletRequest request, PrintWriter writer) {
		String text = request.getParameter("text");
		String description = request.getParameter("description");
		String parentId = request.getParameter("parentId");
		if (("".equals(parentId)) || (parentId == null)) {
			writer.write("{success:false,msg:'请点击对应的类别进行添加！'}");
		} else if (("".equals(text)) || (text == null)) {
			writer.write("{success:false,msg:'类别名称不能为空！'}");
		} else if (parentId.equals("0")) {
			if (this.categoryService.addRootCategory(text, description)) {
				writer.write("{success:true, msg:'根节点新增成功!'}");
			} else {
				writer.write("{success:false, msg:'根节点新增失败!'}");
			}
		} else {
			Category category = new Category();
			category.setText(text);
			category.setDescription(description);
			if (this.categoryService.addChildCategory(Integer.parseInt(parentId), category)) {
				writer.write("{success:true, msg:'子节点新增成功!'}");
			} else {
				writer.write("{success:true, msg:'子节点新增失败!'}");
			}
		}
	}

	@RequestMapping(value = "/delete_category", method = RequestMethod.POST)
	public void deleteCategory(HttpServletRequest request, PrintWriter writer) {
		String id = request.getParameter("id");
		List<Product> products = this.productService.getProductByCategoryId(id);

		String pid = request.getParameter("pid");
		if ("root".equals(pid)) {
			pid = "0";
		}
		try {
			if (("".equals(id)) || (id == null)) {
				writer.write("{success:false,msg:'不能删除未存在类别！'}");
			} else if (products.size() > 0) {
				writer.write("{success:false,msg:'不能删除已存在商品的类别！'}");
			} else if (this.categoryService.removeById(Integer.parseInt(id), Integer.parseInt(pid))) {
				writer.write("{success:true,msg:'类别删除成功！'}");
			} else {
				writer.write("{success:true,msg:'类别删除失败！'}");
			}
		} catch (YcException e) {
			writer.write("{success:true, msg:'" + e + "'}");
		}
	}

	@RequestMapping(value = "/update_category", method = RequestMethod.POST)
	public void updateCategory(HttpServletRequest request, PrintWriter writer) {
		String id = request.getParameter("id");
		String parentId = request.getParameter("parentId");
		String leaf = request.getParameter("leaf");
		String text = request.getParameter("text");
		String description = request.getParameter("description");
		if ("root".equals(parentId)) {
			parentId = "0";
		}
		Category category = new Category();
		category.setId(Integer.parseInt(id));
		category.setLeaf(Boolean.parseBoolean(leaf));
		category.setParentId(Integer.parseInt(parentId));
		category.setDescription(description);
		category.setText(text);
		if (this.categoryService.update(category)) {
			writer.write("{success:true,msg:'修改类别成功！'}");
		} else {
			writer.write("{success:true,msg:'修改类别失败！'}");
		}
	}

	@RequestMapping(value = "combo_category", method = RequestMethod.GET)
	public void comboCategoryTocombobox(HttpServletRequest request, PrintWriter writer) {
		List<Category> categorys = this.categoryService.getCategorys();
		int len = categorys.size();
		String preStr = "{totalCount:" + len + ",rows:[";
		String cenStr = "";
		int i = 0;
		String comma = ",";
		for (Category category : categorys) {
			i++;
			cenStr = cenStr + "{categoryId:'" + category.getId() + "'," + "text:'" + category.getText() + "'" + "}";
			if (i < len) {
				cenStr = cenStr + comma;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}
}
