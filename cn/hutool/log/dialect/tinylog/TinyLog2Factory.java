package cn.hutool.log.dialect.tinylog;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.tinylog.Logger;

public class TinyLog2Factory extends LogFactory {
  public TinyLog2Factory() {
    super("TinyLog");
    checkLogExist(Logger.class);
  }
  
  public Log createLog(String name) {
    return (Log)new TinyLog2(name);
  }
  
  public Log createLog(Class<?> clazz) {
    return (Log)new TinyLog2(clazz);
  }
}
