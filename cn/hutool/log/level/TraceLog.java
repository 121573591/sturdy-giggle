package cn.hutool.log.level;

public interface TraceLog {
  boolean isTraceEnabled();
  
  void trace(Throwable paramThrowable);
  
  void trace(String paramString, Object... paramVarArgs);
  
  void trace(Throwable paramThrowable, String paramString, Object... paramVarArgs);
  
  void trace(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
}
