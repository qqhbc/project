package com.yc.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_product")
public class Product
{
  private int id;
  private String name;
  private String description;
  private double maketPrice;
  private double memberPrice;
  private Timestamp pdate;
  private String photo;
  private Category category;
  
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
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public double getMaketPrice()
  {
    return this.maketPrice;
  }
  
  public void setMaketPrice(double maketPrice)
  {
    this.maketPrice = maketPrice;
  }
  
  public double getMemberPrice()
  {
    return this.memberPrice;
  }
  
  public void setMemberPrice(double memberPrice)
  {
    this.memberPrice = memberPrice;
  }
  
  public Timestamp getPdate()
  {
    return this.pdate;
  }
  
  public void setPdate(Timestamp pdate)
  {
    this.pdate = pdate;
  }
  
  public String getPhoto()
  {
    return this.photo;
  }
  
  public void setPhoto(String photo)
  {
    this.photo = photo;
  }
  
  @ManyToOne
  public Category getCategory()
  {
    return this.category;
  }
  
  public void setCategory(Category category)
  {
    this.category = category;
  }
}