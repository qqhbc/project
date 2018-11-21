package com.yc.dao;

import com.yc.model.Category;
import com.yc.util.YcException;
import java.util.List;

public abstract interface CategoryDAO
{
  public abstract boolean doCreate(Category paramCategory);
  
  public abstract boolean doCreateChildCategory(int paramInt, Category paramCategory);
  
  public abstract boolean doDelete(int paramInt)
    throws YcException;
  
  public abstract boolean doUpdate(Category paramCategory);
  
  public abstract List<Category> findAll();
  
  public abstract boolean doUpateCategoryToLeaf(int paramInt)
    throws YcException;
  
  public abstract List<Category> findById(int paramInt);
  
  public abstract List<Category> findByParentId(int paramInt);
  
  public abstract List<Category> findRootAll();
}
