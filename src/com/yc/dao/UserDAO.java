package com.yc.dao;

import com.yc.model.User;
import java.util.List;

public abstract interface UserDAO
{
  public abstract boolean doCreate(User paramUser);
  
  public abstract boolean doDelete(int paramInt);
  
  public abstract boolean doUpdate(User paramUser);
  
  public abstract List<User> findAll();
  
  public abstract User findByUserName(String paramString);
  
  public abstract List<User> findByPage(int paramInt1, int paramInt2);
  
  public abstract boolean login(String paramString1, String paramString2);
  
  public abstract boolean checkUserName(String paramString);
}
