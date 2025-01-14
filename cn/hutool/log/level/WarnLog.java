package cn.hutool.log.level;

public interface WarnLog {
  boolean isWarnEnabled();
  
  void warn(Throwable paramThrowable);
  
  void warn(String paramString, Object... paramVarArgs);
  
  void warn(Throwable paramThrowable, String paramString, Object... paramVarArgs);
  
  void warn(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
}
