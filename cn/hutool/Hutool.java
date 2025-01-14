package cn.hutool;

import cn.hutool.core.lang.ConsoleTable;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Set;

public class Hutool {
  public static final String AUTHOR = "Looly";
  
  public static Set<Class<?>> getAllUtils() {
    return ClassUtil.scanPackage("cn.hutool", clazz -> 
        (false == clazz.isInterface() && StrUtil.endWith(clazz.getSimpleName(), "Util")));
  }
  
  public static void printAllUtils() {
    Set<Class<?>> allUtils = getAllUtils();
    ConsoleTable consoleTable = ConsoleTable.create().addHeader(new String[] { "工具类名", "所在包" });
    for (Class<?> clazz : allUtils) {
      consoleTable.addBody(new String[] { clazz.getSimpleName(), clazz.getPackage().getName() });
    } 
    consoleTable.print();
  }
}
