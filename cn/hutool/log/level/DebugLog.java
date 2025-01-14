package cn.hutool.log.level;

public interface DebugLog {
  boolean isDebugEnabled();
  
  void debug(Throwable paramThrowable);
  
  void debug(String paramString, Object... paramVarArgs);
  
  void debug(Throwable paramThrowable, String paramString, Object... paramVarArgs);
  
  void debug(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
}
