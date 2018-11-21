package com.yc.service.impl;

import com.yc.dao.CategoryDAO;
import com.yc.model.Category;
import com.yc.service.CategoryService;
import com.yc.util.YcException;
import java.util.List;
import javax.annotation.Resource;

public class CategoryServiceImpl
  implements CategoryService
{
  private CategoryDAO categoryDAO;
  
  public CategoryDAO getCategoryDAO()
  {
    return this.categoryDAO;
  }
  
  @Resource
  public void setCategoryDAO(CategoryDAO categoryDAO)
  {
    this.categoryDAO = categoryDAO;
  }
  
  public boolean addChildCategory(int parentId, Category category)
  {
    return this.categoryDAO.doCreateChildCategory(parentId, category);
  }
  
  public boolean addRootCategory(String text, String description)
  {
    Category category = new Category();
    category.setText(text);
    category.setDescription(description);
    category.setParentId(0);
    category.setLeaf(true);
    return this.categoryDAO.doCreate(category);
  }
  
  public boolean removeById(int id, int pid)
    throws YcException
  {
    List<Category> list = getCategorysByParentId(id);
    if (list.size() > 0)
    {
      for (Category c : list) {
        removeById(c.getId(), c.getParentId());
      }
    }
    else
    {
      System.out.println(getCategorysByParentId(pid).size());
      if (getCategorysByParentId(pid).size() == 1) {
        try
        {
          setCategoryToLeaf(pid);
        }
        catch (YcException e)
        {
          e.printStackTrace();
        }
      }
    }
    return this.categoryDAO.doDelete(id);
  }
  
  public boolean update(Category category)
  {
    return this.categoryDAO.doUpdate(category);
  }
  
  public List<Category> getCategorys()
  {
    return this.categoryDAO.findAll();
  }
  
  public List<Category> getRootCategorys()
  {
    return this.categoryDAO.findRootAll();
  }
  
  public List<Category> getCategoryById(int id)
  {
    return this.categoryDAO.findById(id);
  }
  
  public List<Category> getCategorysByParentId(int pid)
  {
    return this.categoryDAO.findByParentId(pid);
  }
  
  private boolean setCategoryToLeaf(int pid)
    throws YcException
  {
    return this.categoryDAO.doUpateCategoryToLeaf(pid);
  }
}