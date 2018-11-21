package com.yc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TimeStamp
{
  public String getTimeStamp()
  {
    StringBuffer sb = new StringBuffer();
    sb.append(getTime());
    sb.append(getRand());
    return sb.toString();
  }
  
  private String getRand()
  {
    StringBuffer sb = new StringBuffer();
    Random r = new Random();
    for (int i = 0; i < 3; i++) {
      sb.append(r.nextInt(10));
    }
    return sb.toString();
  }
  
  private String getTime()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    return sdf.format(new Date());
  }
}
