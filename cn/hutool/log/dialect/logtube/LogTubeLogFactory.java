package cn.hutool.log.dialect.logtube;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.github.logtube.Logtube;

public class LogTubeLogFactory extends LogFactory {
  public LogTubeLogFactory() {
    super("LogTube");
    checkLogExist(Logtube.class);
  }
  
  public Log createLog(String name) {
    return (Log)new LogTubeLog(name);
  }
  
  public Log createLog(Class<?> clazz) {
    return (Log)new LogTubeLog(clazz);
  }
}
