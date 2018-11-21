package com.yc.dao;

import com.yc.model.SalesOrder;
import java.util.List;

public abstract interface SalesOrderDAO
{
  public abstract boolean doCreate(SalesOrder paramSalesOrder);
  
  public abstract boolean doUpdate(SalesOrder paramSalesOrder);
  
  public abstract List<SalesOrder> findAll();
  
  public abstract List<SalesOrder> findAll(int paramInt1, int paramInt2);
  
  public abstract List<SalesOrder> findById(int paramInt);
  
  public abstract List<SalesOrder> findByOrderCode(String paramString);
  
  public abstract List<SalesOrder> findByUsername(String paramString);
  
  public abstract boolean verify(int paramInt);
}
