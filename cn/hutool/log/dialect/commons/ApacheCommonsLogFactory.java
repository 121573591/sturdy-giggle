package cn.hutool.log.dialect.commons;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.commons.logging.LogFactory;

public class ApacheCommonsLogFactory extends LogFactory {
  public ApacheCommonsLogFactory() {
    super("Apache Common Logging");
    checkLogExist(LogFactory.class);
  }
  
  public Log createLog(String name) {
    try {
      return (Log)new ApacheCommonsLog4JLog(name);
    } catch (Exception e) {
      return (Log)new ApacheCommonsLog(name);
    } 
  }
  
  public Log createLog(Class<?> clazz) {
    try {
      return (Log)new ApacheCommonsLog4JLog(clazz);
    } catch (Exception e) {
      return (Log)new ApacheCommonsLog(clazz);
    } 
  }
  
  protected void checkLogExist(Class<?> logClassName) {
    super.checkLogExist(logClassName);
    getLog(ApacheCommonsLogFactory.class);
  }
}
