package com.yc.service.impl;

import com.yc.dao.SalesOrderDAO;
import com.yc.model.SalesOrder;
import com.yc.service.SalesOrderService;
import java.util.List;
import javax.annotation.Resource;

public class SalesOrderServiceImpl
  implements SalesOrderService
{
  private SalesOrderDAO salesOrderDAO;
  
  public SalesOrderDAO getSalesOrderDAO()
  {
    return this.salesOrderDAO;
  }
  
  @Resource
  public void setSalesOrderDAO(SalesOrderDAO salesOrderDAO)
  {
    this.salesOrderDAO = salesOrderDAO;
  }
  
  public boolean add(SalesOrder salesOrder)
  {
    return this.salesOrderDAO.doCreate(salesOrder);
  }
  
  public boolean update(SalesOrder salesOrder)
  {
    return this.salesOrderDAO.doUpdate(salesOrder);
  }
  
  public List<SalesOrder> getSalesOrders()
  {
    return this.salesOrderDAO.findAll();
  }
  
  public List<SalesOrder> getSalesOrdersPage(int start, int end)
  {
    return this.salesOrderDAO.findAll(start, end);
  }
  
  public List<SalesOrder> getById(int id)
  {
    return this.salesOrderDAO.findById(id);
  }
  
  public List<SalesOrder> getSalesOrderByOrderCode(String orderCode)
  {
    return this.salesOrderDAO.findByOrderCode(orderCode);
  }
  
  public List<SalesOrder> getSalesOrderByUsername(String username)
  {
    return this.salesOrderDAO.findByUsername(username);
  }
  
  public boolean verify(int id)
  {
    return this.salesOrderDAO.verify(id);
  }
  
  public long getCount()
  {
    return getSalesOrders().size();
  }
}
