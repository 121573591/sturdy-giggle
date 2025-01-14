package cn.hutool.log.level;

public interface ErrorLog {
  boolean isErrorEnabled();
  
  void error(Throwable paramThrowable);
  
  void error(String paramString, Object... paramVarArgs);
  
  void error(Throwable paramThrowable, String paramString, Object... paramVarArgs);
  
  void error(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
}
