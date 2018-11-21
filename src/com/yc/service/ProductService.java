package com.yc.service;

import com.yc.model.Product;
import java.util.List;

public abstract interface ProductService
{
  public abstract boolean add(Product paramProduct);
  
  public abstract boolean remove(String[] paramArrayOfString);
  
  public abstract boolean update(Product paramProduct);
  
  public abstract List<Product> getProducts();
  
  public abstract List<Product> getProductsPage(int paramInt1, int paramInt2);
  
  public abstract List<Product> getProductsByKeyword(String paramString);
  
  public abstract List<Product> getProductByCategoryId(String paramString);
  
  public abstract long getCount();
}
