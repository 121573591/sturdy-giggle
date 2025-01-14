package cn.hutool.log;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.level.Level;
import java.io.Serializable;

public abstract class AbstractLog implements Log, Serializable {
  private static final long serialVersionUID = -3211115409504005616L;
  
  private static final String FQCN = AbstractLog.class.getName();
  
  public boolean isEnabled(Level level) {
    switch (level) {
      case TRACE:
        return isTraceEnabled();
      case DEBUG:
        return isDebugEnabled();
      case INFO:
        return isInfoEnabled();
      case WARN:
        return isWarnEnabled();
      case ERROR:
        return isErrorEnabled();
    } 
    throw new Error(StrUtil.format("Can not identify level: {}", new Object[] { level }));
  }
  
  public void trace(Throwable t) {
    trace(t, ExceptionUtil.getSimpleMessage(t), new Object[0]);
  }
  
  public void trace(String format, Object... arguments) {
    trace((Throwable)null, format, arguments);
  }
  
  public void trace(Throwable t, String format, Object... arguments) {
    trace(FQCN, t, format, arguments);
  }
  
  public void debug(Throwable t) {
    debug(t, ExceptionUtil.getSimpleMessage(t), new Object[0]);
  }
  
  public void debug(String format, Object... arguments) {
    if (null != arguments && 1 == arguments.length && arguments[0] instanceof Throwable) {
      debug((Throwable)arguments[0], format, new Object[0]);
    } else {
      debug((Throwable)null, format, arguments);
    } 
  }
  
  public void debug(Throwable t, String format, Object... arguments) {
    debug(FQCN, t, format, arguments);
  }
  
  public void info(Throwable t) {
    info(t, ExceptionUtil.getSimpleMessage(t), new Object[0]);
  }
  
  public void info(String format, Object... arguments) {
    if (null != arguments && 1 == arguments.length && arguments[0] instanceof Throwable) {
      info((Throwable)arguments[0], format, new Object[0]);
    } else {
      info((Throwable)null, format, arguments);
    } 
  }
  
  public void info(Throwable t, String format, Object... arguments) {
    info(FQCN, t, format, arguments);
  }
  
  public void warn(Throwable t) {
    warn(t, ExceptionUtil.getSimpleMessage(t), new Object[0]);
  }
  
  public void warn(String format, Object... arguments) {
    if (null != arguments && 1 == arguments.length && arguments[0] instanceof Throwable) {
      warn((Throwable)arguments[0], format, new Object[0]);
    } else {
      warn((Throwable)null, format, arguments);
    } 
  }
  
  public void warn(Throwable t, String format, Object... arguments) {
    warn(FQCN, t, format, arguments);
  }
  
  public void error(Throwable t) {
    error(t, ExceptionUtil.getSimpleMessage(t), new Object[0]);
  }
  
  public void error(String format, Object... arguments) {
    if (null != arguments && 1 == arguments.length && arguments[0] instanceof Throwable) {
      error((Throwable)arguments[0], format, new Object[0]);
    } else {
      error((Throwable)null, format, arguments);
    } 
  }
  
  public void error(Throwable t, String format, Object... arguments) {
    error(FQCN, t, format, arguments);
  }
  
  public void log(Level level, String format, Object... arguments) {
    if (null != arguments && 1 == arguments.length && arguments[0] instanceof Throwable) {
      log(level, (Throwable)arguments[0], format, new Object[0]);
    } else {
      log(level, (Throwable)null, format, arguments);
    } 
  }
  
  public void log(Level level, Throwable t, String format, Object... arguments) {
    log(FQCN, level, t, format, arguments);
  }
}
