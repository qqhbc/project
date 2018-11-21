package com.yc.modeldto;

import com.yc.model.SaleItem;
import java.util.List;

public class OrderFormDto
{
  private List<SaleItem> saleitems;
  
  public List<SaleItem> getSaleitems()
  {
    return this.saleitems;
  }
  
  public void setSaleitems(List<SaleItem> saleitems)
  {
    this.saleitems = saleitems;
  }
}
