package cn.hutool.core.exceptions;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionUtil {
  public static String getMessage(Throwable e) {
    if (null == e)
      return "null"; 
    return StrUtil.format("{}: {}", new Object[] { e.getClass().getSimpleName(), e.getMessage() });
  }
  
  public static String getSimpleMessage(Throwable e) {
    return (null == e) ? "null" : e.getMessage();
  }
  
  public static RuntimeException wrapRuntime(Throwable throwable) {
    if (throwable instanceof RuntimeException)
      return (RuntimeException)throwable; 
    return new RuntimeException(throwable);
  }
  
  public static RuntimeException wrapRuntime(String message) {
    return new RuntimeException(message);
  }
  
  public static <T extends Throwable> T wrap(Throwable throwable, Class<T> wrapThrowable) {
    if (wrapThrowable.isInstance(throwable))
      return (T)throwable; 
    return (T)ReflectUtil.newInstance(wrapThrowable, new Object[] { throwable });
  }
  
  public static void wrapAndThrow(Throwable throwable) {
    if (throwable instanceof RuntimeException)
      throw (RuntimeException)throwable; 
    if (throwable instanceof Error)
      throw (Error)throwable; 
    throw new UndeclaredThrowableException(throwable);
  }
  
  public static void wrapRuntimeAndThrow(String message) {
    throw new RuntimeException(message);
  }
  
  public static Throwable unwrap(Throwable wrapped) {
    Throwable unwrapped = wrapped;
    while (true) {
      while (unwrapped instanceof InvocationTargetException)
        unwrapped = ((InvocationTargetException)unwrapped).getTargetException(); 
      if (unwrapped instanceof UndeclaredThrowableException) {
        unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
        continue;
      } 
      break;
    } 
    return unwrapped;
  }
  
  public static StackTraceElement[] getStackElements() {
    return Thread.currentThread().getStackTrace();
  }
  
  public static StackTraceElement getStackElement(int i) {
    return Thread.currentThread().getStackTrace()[i];
  }
  
  public static StackTraceElement getStackElement(String fqcn, int i) {
    StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();
    int index = ArrayUtil.matchIndex(ele -> StrUtil.equals(fqcn, ele.getClassName()), (Object[])stackTraceArray);
    if (index > 0)
      return stackTraceArray[index + i]; 
    return null;
  }
  
  public static StackTraceElement getRootStackElement() {
    StackTraceElement[] stackElements = Thread.currentThread().getStackTrace();
    return Thread.currentThread().getStackTrace()[stackElements.length - 1];
  }
  
  public static String stacktraceToOneLineString(Throwable throwable) {
    return stacktraceToOneLineString(throwable, 3000);
  }
  
  public static String stacktraceToOneLineString(Throwable throwable, int limit) {
    Map<Character, String> replaceCharToStrMap = new HashMap<>();
    replaceCharToStrMap.put(Character.valueOf('\r'), " ");
    replaceCharToStrMap.put(Character.valueOf('\n'), " ");
    replaceCharToStrMap.put(Character.valueOf('\t'), " ");
    return stacktraceToString(throwable, limit, replaceCharToStrMap);
  }
  
  public static String stacktraceToString(Throwable throwable) {
    return stacktraceToString(throwable, 3000);
  }
  
  public static String stacktraceToString(Throwable throwable, int limit) {
    return stacktraceToString(throwable, limit, null);
  }
  
  public static String stacktraceToString(Throwable throwable, int limit, Map<Character, String> replaceCharToStrMap) {
    FastByteArrayOutputStream baos = new FastByteArrayOutputStream();
    throwable.printStackTrace(new PrintStream((OutputStream)baos));
    String exceptionStr = baos.toString();
    int length = exceptionStr.length();
    if (limit < 0 || limit > length)
      limit = length; 
    if (MapUtil.isNotEmpty(replaceCharToStrMap)) {
      StringBuilder sb = StrUtil.builder();
      for (int i = 0; i < limit; i++) {
        char c = exceptionStr.charAt(i);
        String value = replaceCharToStrMap.get(Character.valueOf(c));
        if (null != value) {
          sb.append(value);
        } else {
          sb.append(c);
        } 
      } 
      return sb.toString();
    } 
    if (limit == length)
      return exceptionStr; 
    return StrUtil.subPre(exceptionStr, limit);
  }
  
  public static boolean isCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
    return (null != getCausedBy(throwable, causeClasses));
  }
  
  public static Throwable getCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
    Throwable cause = throwable;
    while (cause != null) {
      for (Class<? extends Exception> causeClass : causeClasses) {
        if (causeClass.isInstance(cause))
          return cause; 
      } 
      cause = cause.getCause();
    } 
    return null;
  }
  
  public static boolean isFromOrSuppressedThrowable(Throwable throwable, Class<? extends Throwable> exceptionClass) {
    return (convertFromOrSuppressedThrowable(throwable, exceptionClass, true) != null);
  }
  
  public static boolean isFromOrSuppressedThrowable(Throwable throwable, Class<? extends Throwable> exceptionClass, boolean checkCause) {
    return (convertFromOrSuppressedThrowable(throwable, exceptionClass, checkCause) != null);
  }
  
  public static <T extends Throwable> T convertFromOrSuppressedThrowable(Throwable throwable, Class<T> exceptionClass) {
    return convertFromOrSuppressedThrowable(throwable, exceptionClass, true);
  }
  
  public static <T extends Throwable> T convertFromOrSuppressedThrowable(Throwable throwable, Class<T> exceptionClass, boolean checkCause) {
    if (throwable == null || exceptionClass == null)
      return null; 
    if (exceptionClass.isAssignableFrom(throwable.getClass()))
      return (T)throwable; 
    if (checkCause) {
      Throwable cause = throwable.getCause();
      if (cause != null && exceptionClass.isAssignableFrom(cause.getClass()))
        return (T)cause; 
    } 
    Throwable[] throwables = throwable.getSuppressed();
    if (ArrayUtil.isNotEmpty((Object[])throwables))
      for (Throwable throwable1 : throwables) {
        if (exceptionClass.isAssignableFrom(throwable1.getClass()))
          return (T)throwable1; 
      }  
    return null;
  }
  
  public static List<Throwable> getThrowableList(Throwable throwable) {
    List<Throwable> list = new ArrayList<>();
    while (throwable != null && false == list.contains(throwable)) {
      list.add(throwable);
      throwable = throwable.getCause();
    } 
    return list;
  }
  
  public static Throwable getRootCause(Throwable throwable) {
    List<Throwable> list = getThrowableList(throwable);
    return (list.size() < 1) ? null : list.get(list.size() - 1);
  }
  
  public static String getRootCauseMessage(Throwable th) {
    return getMessage(getRootCause(th));
  }
}
