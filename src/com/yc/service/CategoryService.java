package com.yc.service;

import com.yc.model.Category;
import com.yc.util.YcException;
import java.util.List;

public abstract interface CategoryService
{
  public abstract boolean addChildCategory(int paramInt, Category paramCategory);
  
  public abstract boolean addRootCategory(String paramString1, String paramString2);
  
  public abstract boolean removeById(int paramInt1, int paramInt2)
    throws YcException;
  
  public abstract boolean update(Category paramCategory);
  
  public abstract List<Category> getCategorys();
  
  public abstract List<Category> getRootCategorys();
  
  public abstract List<Category> getCategorysByParentId(int paramInt);
  
  public abstract List<Category> getCategoryById(int paramInt);
}
