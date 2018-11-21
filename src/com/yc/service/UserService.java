package com.yc.service;

import com.yc.model.User;
import java.util.List;

public abstract interface UserService
{
  public abstract boolean register(User paramUser);
  
  public abstract boolean remove(String[] paramArrayOfString);
  
  public abstract boolean update(User paramUser);
  
  public abstract List<User> getUsers();
  
  public abstract List<User> getUsersPage(int paramInt1, int paramInt2);
  
  public abstract User getUser(String paramString);
  
  public abstract boolean login(String paramString1, String paramString2);
  
  public abstract boolean checkUserName(String paramString);
  
  public abstract int getCount();
}
