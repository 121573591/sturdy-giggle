package cn.hutool.core.lang;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Scanner;

public class Console {
  private static final String TEMPLATE_VAR = "{}";
  
  public static void log() {
    System.out.println();
  }
  
  public static void log(Object obj) {
    if (obj instanceof Throwable) {
      Throwable e = (Throwable)obj;
      log(e, e.getMessage(), new Object[0]);
    } else {
      log("{}", new Object[] { obj });
    } 
  }
  
  public static void log(Object obj1, Object... otherObjs) {
    if (ArrayUtil.isEmpty(otherObjs)) {
      log(obj1);
    } else {
      log(buildTemplateSplitBySpace(otherObjs.length + 1), ArrayUtil.insert(otherObjs, 0, new Object[] { obj1 }));
    } 
  }
  
  public static void log(String template, Object... values) {
    if (ArrayUtil.isEmpty(values) || StrUtil.contains(template, "{}")) {
      logInternal(template, values);
    } else {
      logInternal(buildTemplateSplitBySpace(values.length + 1), ArrayUtil.insert(values, 0, new Object[] { template }));
    } 
  }
  
  public static void log(Throwable t, String template, Object... values) {
    System.out.println(StrUtil.format(template, values));
    if (null != t) {
      t.printStackTrace(System.out);
      System.out.flush();
    } 
  }
  
  private static void logInternal(String template, Object... values) {
    log(null, template, values);
  }
  
  public static void table(ConsoleTable consoleTable) {
    print(consoleTable.toString());
  }
  
  public static void print(Object obj) {
    print("{}", new Object[] { obj });
  }
  
  public static void print(Object obj1, Object... otherObjs) {
    if (ArrayUtil.isEmpty(otherObjs)) {
      print(obj1);
    } else {
      print(buildTemplateSplitBySpace(otherObjs.length + 1), ArrayUtil.insert(otherObjs, 0, new Object[] { obj1 }));
    } 
  }
  
  public static void print(String template, Object... values) {
    if (ArrayUtil.isEmpty(values) || StrUtil.contains(template, "{}")) {
      printInternal(template, values);
    } else {
      printInternal(buildTemplateSplitBySpace(values.length + 1), ArrayUtil.insert(values, 0, new Object[] { template }));
    } 
  }
  
  public static void printProgress(char showChar, int len) {
    print("{}{}", new Object[] { Character.valueOf('\r'), StrUtil.repeat(showChar, len) });
  }
  
  public static void printProgress(char showChar, int totalLen, double rate) {
    Assert.isTrue((rate >= 0.0D && rate <= 1.0D), "Rate must between 0 and 1 (both include)", new Object[0]);
    printProgress(showChar, (int)(totalLen * rate));
  }
  
  private static void printInternal(String template, Object... values) {
    System.out.print(StrUtil.format(template, values));
  }
  
  public static void error() {
    System.err.println();
  }
  
  public static void error(Object obj) {
    if (obj instanceof Throwable) {
      Throwable e = (Throwable)obj;
      error(e, e.getMessage(), new Object[0]);
    } else {
      error("{}", new Object[] { obj });
    } 
  }
  
  public static void error(Object obj1, Object... otherObjs) {
    if (ArrayUtil.isEmpty(otherObjs)) {
      error(obj1);
    } else {
      error(buildTemplateSplitBySpace(otherObjs.length + 1), ArrayUtil.insert(otherObjs, 0, new Object[] { obj1 }));
    } 
  }
  
  public static void error(String template, Object... values) {
    if (ArrayUtil.isEmpty(values) || StrUtil.contains(template, "{}")) {
      errorInternal(template, values);
    } else {
      errorInternal(buildTemplateSplitBySpace(values.length + 1), ArrayUtil.insert(values, 0, new Object[] { template }));
    } 
  }
  
  public static void error(Throwable t, String template, Object... values) {
    System.err.println(StrUtil.format(template, values));
    if (null != t) {
      t.printStackTrace(System.err);
      System.err.flush();
    } 
  }
  
  private static void errorInternal(String template, Object... values) {
    error(null, template, values);
  }
  
  public static Scanner scanner() {
    return new Scanner(System.in);
  }
  
  public static String input() {
    return scanner().nextLine();
  }
  
  public static String where() {
    StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
    String className = stackTraceElement.getClassName();
    String methodName = stackTraceElement.getMethodName();
    String fileName = stackTraceElement.getFileName();
    Integer lineNumber = Integer.valueOf(stackTraceElement.getLineNumber());
    return String.format("%s.%s(%s:%s)", new Object[] { className, methodName, fileName, lineNumber });
  }
  
  public static Integer lineNumber() {
    return Integer.valueOf((new Throwable()).getStackTrace()[1].getLineNumber());
  }
  
  private static String buildTemplateSplitBySpace(int count) {
    return StrUtil.repeatAndJoin("{}", count, " ");
  }
}
