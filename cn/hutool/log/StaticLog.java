package cn.hutool.log;

import cn.hutool.core.lang.caller.CallerUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.level.Level;

public final class StaticLog {
  private static final String FQCN = StaticLog.class.getName();
  
  public static void trace(String format, Object... arguments) {
    trace(LogFactory.get(CallerUtil.getCallerCaller()), format, arguments);
  }
  
  public static void trace(Log log, String format, Object... arguments) {
    log.trace(FQCN, null, format, arguments);
  }
  
  public static void debug(String format, Object... arguments) {
    debug(LogFactory.get(CallerUtil.getCallerCaller()), format, arguments);
  }
  
  public static void debug(Log log, String format, Object... arguments) {
    log.debug(FQCN, null, format, arguments);
  }
  
  public static void info(String format, Object... arguments) {
    info(LogFactory.get(CallerUtil.getCallerCaller()), format, arguments);
  }
  
  public static void info(Log log, String format, Object... arguments) {
    log.info(FQCN, null, format, arguments);
  }
  
  public static void warn(String format, Object... arguments) {
    warn(LogFactory.get(CallerUtil.getCallerCaller()), format, arguments);
  }
  
  public static void warn(Throwable e, String format, Object... arguments) {
    warn(LogFactory.get(CallerUtil.getCallerCaller()), e, StrUtil.format(format, arguments), new Object[0]);
  }
  
  public static void warn(Log log, String format, Object... arguments) {
    warn(log, null, format, arguments);
  }
  
  public static void warn(Log log, Throwable e, String format, Object... arguments) {
    log.warn(FQCN, e, format, arguments);
  }
  
  public static void error(Throwable e) {
    error(LogFactory.get(CallerUtil.getCallerCaller()), e);
  }
  
  public static void error(String format, Object... arguments) {
    error(LogFactory.get(CallerUtil.getCallerCaller()), format, arguments);
  }
  
  public static void error(Throwable e, String format, Object... arguments) {
    error(LogFactory.get(CallerUtil.getCallerCaller()), e, format, arguments);
  }
  
  public static void error(Log log, Throwable e) {
    error(log, e, e.getMessage(), new Object[0]);
  }
  
  public static void error(Log log, String format, Object... arguments) {
    error(log, null, format, arguments);
  }
  
  public static void error(Log log, Throwable e, String format, Object... arguments) {
    log.error(FQCN, e, format, arguments);
  }
  
  public static void log(Level level, Throwable t, String format, Object... arguments) {
    LogFactory.get(CallerUtil.getCallerCaller()).log(FQCN, level, t, format, arguments);
  }
  
  @Deprecated
  public static Log get(Class<?> clazz) {
    return LogFactory.get(clazz);
  }
  
  @Deprecated
  public static Log get(String name) {
    return LogFactory.get(name);
  }
  
  @Deprecated
  public static Log get() {
    return LogFactory.get(CallerUtil.getCallerCaller());
  }
}
