package com.yc.service;

import com.yc.model.SalesOrder;
import java.util.List;

public abstract interface SalesOrderService
{
  public abstract boolean add(SalesOrder paramSalesOrder);
  
  public abstract boolean update(SalesOrder paramSalesOrder);
  
  public abstract List<SalesOrder> getSalesOrders();
  
  public abstract List<SalesOrder> getSalesOrdersPage(int paramInt1, int paramInt2);
  
  public abstract List<SalesOrder> getById(int paramInt);
  
  public abstract List<SalesOrder> getSalesOrderByOrderCode(String paramString);
  
  public abstract List<SalesOrder> getSalesOrderByUsername(String paramString);
  
  public abstract boolean verify(int paramInt);
  
  public abstract long getCount();
}
