package cn.hutool.log.dialect.log4j;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.log4j.Logger;

public class Log4jLogFactory extends LogFactory {
  public Log4jLogFactory() {
    super("Log4j");
    checkLogExist(Logger.class);
  }
  
  public Log createLog(String name) {
    return (Log)new Log4jLog(name);
  }
  
  public Log createLog(Class<?> clazz) {
    return (Log)new Log4jLog(clazz);
  }
}
