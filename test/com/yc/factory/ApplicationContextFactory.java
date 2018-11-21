package com.yc.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextFactory
{
  public static ApplicationContext getApplicationContext()
  {
    return new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
  }
}

