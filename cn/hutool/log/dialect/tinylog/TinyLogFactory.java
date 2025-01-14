package cn.hutool.log.dialect.tinylog;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.pmw.tinylog.Logger;

public class TinyLogFactory extends LogFactory {
  public TinyLogFactory() {
    super("TinyLog");
    checkLogExist(Logger.class);
  }
  
  public Log createLog(String name) {
    return (Log)new TinyLog(name);
  }
  
  public Log createLog(Class<?> clazz) {
    return (Log)new TinyLog(clazz);
  }
}
