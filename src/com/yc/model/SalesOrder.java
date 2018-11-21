package com.yc.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="t_salesorder")
public class SalesOrder
{
  private int id;
  private String orderCode;
  private String username;
  private String phone;
  private String QQ;
  private String address;
  private Timestamp odate;
  private boolean status;
  private boolean nullify;
  private String remark;
  private Set<SaleItem> saleItems = new HashSet<SaleItem>();
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getOrderCode()
  {
    return this.orderCode;
  }
  
  public void setOrderCode(String orderCode)
  {
    this.orderCode = orderCode;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getQQ()
  {
    return this.QQ;
  }
  
  public void setQQ(String qQ)
  {
    this.QQ = qQ;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public Timestamp getOdate()
  {
    return this.odate;
  }
  
  public void setOdate(Timestamp odate)
  {
    this.odate = odate;
  }
  
  public boolean isStatus()
  {
    return this.status;
  }
  
  public void setStatus(boolean status)
  {
    this.status = status;
  }
  
  public boolean isNullify()
  {
    return this.nullify;
  }
  
  public void setNullify(boolean nullify)
  {
    this.nullify = nullify;
  }
  
  public String getRemark()
  {
    return this.remark;
  }
  
  public void setRemark(String remark)
  {
    this.remark = remark;
  }
  
  @OneToMany(fetch=FetchType.EAGER)
  @Cascade({org.hibernate.annotations.CascadeType.ALL})
  @JoinColumn(name="salesOrderId")
  public Set<SaleItem> getSaleItems()
  {
    return this.saleItems;
  }
  
  public void setSaleItems(Set<SaleItem> saleItems)
  {
    this.saleItems = saleItems;
  }
}
