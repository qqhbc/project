package com.yc.util;

@SuppressWarnings("serial")
public class YcException
  extends Exception
{
  private String resultMsg;
  
  public YcException(String resultMsg)
  {
    this.resultMsg = resultMsg;
  }
  
  public String toString()
  {
    return this.resultMsg;
  }
}
