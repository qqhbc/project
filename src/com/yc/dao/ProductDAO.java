package com.yc.dao;

import com.yc.model.Product;
import java.util.List;

public abstract interface ProductDAO
{
  public abstract boolean doCreate(Product paramProduct);
  
  public abstract boolean doDelete(int paramInt);
  
  public abstract boolean doUpdate(Product paramProduct);
  
  public abstract List<Product> findAll();
  
  public abstract List<Product> findAllPage(int paramInt1, int paramInt2);
  
  public abstract List<Product> findAllByKeyword(String paramString);
  
  public abstract List<Product> findByCategoryId(String paramString);
}
