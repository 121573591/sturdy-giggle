package cn.hutool.log.dialect.slf4j;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.AbstractLog;
import cn.hutool.log.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

public class Slf4jLog extends AbstractLog {
  private static final long serialVersionUID = -6843151523380063975L;
  
  private final transient Logger logger;
  
  private final boolean isLocationAwareLogger;
  
  public Slf4jLog(Logger logger) {
    this.logger = logger;
    this.isLocationAwareLogger = logger instanceof LocationAwareLogger;
  }
  
  public Slf4jLog(Class<?> clazz) {
    this(getSlf4jLogger(clazz));
  }
  
  public Slf4jLog(String name) {
    this(LoggerFactory.getLogger(name));
  }
  
  public String getName() {
    return this.logger.getName();
  }
  
  public boolean isTraceEnabled() {
    return this.logger.isTraceEnabled();
  }
  
  public void trace(String fqcn, Throwable t, String format, Object... arguments) {
    if (isTraceEnabled())
      if (this.isLocationAwareLogger) {
        locationAwareLog((LocationAwareLogger)this.logger, fqcn, 0, t, format, arguments);
      } else {
        this.logger.trace(StrUtil.format(format, arguments), t);
      }  
  }
  
  public boolean isDebugEnabled() {
    return this.logger.isDebugEnabled();
  }
  
  public void debug(String fqcn, Throwable t, String format, Object... arguments) {
    if (isDebugEnabled())
      if (this.isLocationAwareLogger) {
        locationAwareLog((LocationAwareLogger)this.logger, fqcn, 10, t, format, arguments);
      } else {
        this.logger.debug(StrUtil.format(format, arguments), t);
      }  
  }
  
  public boolean isInfoEnabled() {
    return this.logger.isInfoEnabled();
  }
  
  public void info(String fqcn, Throwable t, String format, Object... arguments) {
    if (isInfoEnabled())
      if (this.isLocationAwareLogger) {
        locationAwareLog((LocationAwareLogger)this.logger, fqcn, 20, t, format, arguments);
      } else {
        this.logger.info(StrUtil.format(format, arguments), t);
      }  
  }
  
  public boolean isWarnEnabled() {
    return this.logger.isWarnEnabled();
  }
  
  public void warn(String fqcn, Throwable t, String format, Object... arguments) {
    if (isWarnEnabled())
      if (this.isLocationAwareLogger) {
        locationAwareLog((LocationAwareLogger)this.logger, fqcn, 30, t, format, arguments);
      } else {
        this.logger.warn(StrUtil.format(format, arguments), t);
      }  
  }
  
  public boolean isErrorEnabled() {
    return this.logger.isErrorEnabled();
  }
  
  public void error(String fqcn, Throwable t, String format, Object... arguments) {
    if (isErrorEnabled())
      if (this.isLocationAwareLogger) {
        locationAwareLog((LocationAwareLogger)this.logger, fqcn, 40, t, format, arguments);
      } else {
        this.logger.error(StrUtil.format(format, arguments), t);
      }  
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
  
  private void locationAwareLog(LocationAwareLogger logger, String fqcn, int level_int, Throwable t, String msgTemplate, Object[] arguments) {
    logger.log(null, fqcn, level_int, StrUtil.format(msgTemplate, arguments), null, t);
  }
  
  private static Logger getSlf4jLogger(Class<?> clazz) {
    return (null == clazz) ? LoggerFactory.getLogger("") : LoggerFactory.getLogger(clazz);
  }
}
