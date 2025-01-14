package cn.hutool.log.dialect.jdk;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import java.io.InputStream;
import java.util.logging.LogManager;

public class JdkLogFactory extends LogFactory {
  public JdkLogFactory() {
    super("JDK Logging");
    readConfig();
  }
  
  public Log createLog(String name) {
    return (Log)new JdkLog(name);
  }
  
  public Log createLog(Class<?> clazz) {
    return (Log)new JdkLog(clazz);
  }
  
  private void readConfig() {
    InputStream in = ResourceUtil.getStreamSafe("logging.properties");
    if (null == in) {
      System.err.println("[WARN] Can not find [logging.properties], use [%JRE_HOME%/lib/logging.properties] as default!");
      return;
    } 
    try {
      LogManager.getLogManager().readConfiguration(in);
    } catch (Exception e) {
      Console.error(e, "Read [logging.properties] from classpath error!", new Object[0]);
      try {
        LogManager.getLogManager().readConfiguration();
      } catch (Exception e1) {
        Console.error(e, "Read [logging.properties] from [%JRE_HOME%/lib/logging.properties] error!", new Object[0]);
      } 
    } finally {
      IoUtil.close(in);
    } 
  }
}
