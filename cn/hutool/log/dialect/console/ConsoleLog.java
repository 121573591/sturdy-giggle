package cn.hutool.log.dialect.console;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.AbstractLog;
import cn.hutool.log.level.Level;
import java.util.Map;

public class ConsoleLog extends AbstractLog {
  private static final long serialVersionUID = -6843151523380063975L;
  
  private static final String logFormat = "[{date}] [{level}] {name}: {msg}";
  
  private static Level currentLevel = Level.DEBUG;
  
  private final String name;
  
  public ConsoleLog(Class<?> clazz) {
    this.name = (null == clazz) ? "null" : clazz.getName();
  }
  
  public ConsoleLog(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static void setLevel(Level customLevel) {
    Assert.notNull(customLevel);
    currentLevel = customLevel;
  }
  
  public boolean isTraceEnabled() {
    return isEnabled(Level.TRACE);
  }
  
  public void trace(String fqcn, Throwable t, String format, Object... arguments) {
    log(fqcn, Level.TRACE, t, format, arguments);
  }
  
  public boolean isDebugEnabled() {
    return isEnabled(Level.DEBUG);
  }
  
  public void debug(String fqcn, Throwable t, String format, Object... arguments) {
    log(fqcn, Level.DEBUG, t, format, arguments);
  }
  
  public boolean isInfoEnabled() {
    return isEnabled(Level.INFO);
  }
  
  public void info(String fqcn, Throwable t, String format, Object... arguments) {
    log(fqcn, Level.INFO, t, format, arguments);
  }
  
  public boolean isWarnEnabled() {
    return isEnabled(Level.WARN);
  }
  
  public void warn(String fqcn, Throwable t, String format, Object... arguments) {
    log(fqcn, Level.WARN, t, format, arguments);
  }
  
  public boolean isErrorEnabled() {
    return isEnabled(Level.ERROR);
  }
  
  public void error(String fqcn, Throwable t, String format, Object... arguments) {
    log(fqcn, Level.ERROR, t, format, arguments);
  }
  
  public void log(String fqcn, Level level, Throwable t, String format, Object... arguments) {
    if (false == isEnabled(level))
      return; 
    Dict dict = Dict.create().set("date", DateUtil.now()).set("level", level.toString()).set("name", this.name).set("msg", StrUtil.format(format, arguments));
    String logMsg = StrUtil.format("[{date}] [{level}] {name}: {msg}", (Map)dict);
    if (level.ordinal() >= Level.WARN.ordinal()) {
      Console.error(t, logMsg, new Object[0]);
    } else {
      Console.log(t, logMsg, new Object[0]);
    } 
  }
  
  public boolean isEnabled(Level level) {
    return (currentLevel.compareTo((Enum)level) <= 0);
  }
}
