package cn.hutool.log.level;

public interface InfoLog {
  boolean isInfoEnabled();
  
  void info(Throwable paramThrowable);
  
  void info(String paramString, Object... paramVarArgs);
  
  void info(Throwable paramThrowable, String paramString, Object... paramVarArgs);
  
  void info(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
}
