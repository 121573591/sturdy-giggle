package cn.hutool.log;

import cn.hutool.core.lang.caller.CallerUtil;
import cn.hutool.log.level.DebugLog;
import cn.hutool.log.level.ErrorLog;
import cn.hutool.log.level.InfoLog;
import cn.hutool.log.level.Level;
import cn.hutool.log.level.TraceLog;
import cn.hutool.log.level.WarnLog;

public interface Log extends TraceLog, DebugLog, InfoLog, WarnLog, ErrorLog {
  static Log get(Class<?> clazz) {
    return LogFactory.get(clazz);
  }
  
  static Log get(String name) {
    return LogFactory.get(name);
  }
  
  static Log get() {
    return LogFactory.get(CallerUtil.getCallerCaller());
  }
  
  String getName();
  
  boolean isEnabled(Level paramLevel);
  
  void log(Level paramLevel, String paramString, Object... paramVarArgs);
  
  void log(Level paramLevel, Throwable paramThrowable, String paramString, Object... paramVarArgs);
  
  void log(String paramString1, Level paramLevel, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
}
