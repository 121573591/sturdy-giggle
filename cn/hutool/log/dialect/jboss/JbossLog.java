package cn.hutool.log.dialect.jboss;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.AbstractLog;
import cn.hutool.log.level.Level;
import org.jboss.logging.Logger;

public class JbossLog extends AbstractLog {
  private static final long serialVersionUID = -6843151523380063975L;
  
  private final transient Logger logger;
  
  public JbossLog(Logger logger) {
    this.logger = logger;
  }
  
  public JbossLog(Class<?> clazz) {
    this((null == clazz) ? "null" : clazz.getName());
  }
  
  public JbossLog(String name) {
    this(Logger.getLogger(name));
  }
  
  public String getName() {
    return this.logger.getName();
  }
  
  public boolean isTraceEnabled() {
    return this.logger.isTraceEnabled();
  }
  
  public void trace(String fqcn, Throwable t, String format, Object... arguments) {
    if (isTraceEnabled())
      this.logger.trace(fqcn, StrUtil.format(format, arguments), t); 
  }
  
  public boolean isDebugEnabled() {
    return this.logger.isDebugEnabled();
  }
  
  public void debug(String fqcn, Throwable t, String format, Object... arguments) {
    if (isDebugEnabled())
      this.logger.debug(fqcn, StrUtil.format(format, arguments), t); 
  }
  
  public boolean isInfoEnabled() {
    return this.logger.isInfoEnabled();
  }
  
  public void info(String fqcn, Throwable t, String format, Object... arguments) {
    if (isInfoEnabled())
      this.logger.info(fqcn, StrUtil.format(format, arguments), t); 
  }
  
  public boolean isWarnEnabled() {
    return this.logger.isEnabled(Logger.Level.WARN);
  }
  
  public void warn(String fqcn, Throwable t, String format, Object... arguments) {
    if (isWarnEnabled())
      this.logger.warn(fqcn, StrUtil.format(format, arguments), t); 
  }
  
  public boolean isErrorEnabled() {
    return this.logger.isEnabled(Logger.Level.ERROR);
  }
  
  public void error(String fqcn, Throwable t, String format, Object... arguments) {
    if (isErrorEnabled())
      this.logger.error(fqcn, StrUtil.format(format, arguments), t); 
  }
  
  public void log(String fqcn, Level level, Throwable t, String format, Object... arguments) {
    switch (level) {
      case TRACE:
        trace(fqcn, t, format, arguments);
        return;
      case DEBUG:
        debug(fqcn, t, format, arguments);
        return;
      case INFO:
        info(fqcn, t, format, arguments);
        return;
      case WARN:
        warn(fqcn, t, format, arguments);
        return;
      case ERROR:
        error(fqcn, t, format, arguments);
        return;
    } 
    throw new Error(StrUtil.format("Can not identify level: {}", new Object[] { level }));
  }
}
