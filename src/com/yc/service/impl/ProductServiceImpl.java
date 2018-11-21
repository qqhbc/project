package com.yc.service.impl;

import com.yc.dao.ProductDAO;
import com.yc.model.Product;
import com.yc.service.ProductService;
import java.util.List;
import javax.annotation.Resource;

public class ProductServiceImpl
  implements ProductService
{
  private ProductDAO productDAO;
  
  public ProductDAO getProductDAO()
  {
    return this.productDAO;
  }
  
  @Resource
  public void setProductDAO(ProductDAO productDAO)
  {
    this.productDAO = productDAO;
  }
  
  public boolean add(Product product)
  {
    return this.productDAO.doCreate(product);
  }
  
  public boolean remove(String[] ids)
  {
    boolean flag = false;
    for (int i = 0; i < ids.length; i++) {
      flag = this.productDAO.doDelete(Integer.parseInt(ids[i]));
    }
    return flag;
  }
  
  public boolean update(Product product)
  {
    return this.productDAO.doUpdate(product);
  }
  
  public List<Product> getProducts()
  {
    return this.productDAO.findAll();
  }
  
  public List<Product> getProductsPage(int start, int end)
  {
    return this.productDAO.findAllPage(start, end);
  }
  
  public List<Product> getProductsByKeyword(String keyword)
  {
    return this.productDAO.findAllByKeyword(keyword);
  }
  
  public List<Product> getProductByCategoryId(String categoryId)
  {
    return this.productDAO.findByCategoryId(categoryId);
  }
  
  public long getCount()
  {
    return getProducts().size();
  }
}