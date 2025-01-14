package cn.hutool.core.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import java.util.Properties;

public class SystemPropsUtil {
  public static String HUTOOL_DATE_LENIENT = "hutool.date.lenient";
  
  public static String get(String name, String defaultValue) {
    return StrUtil.nullToDefault(get(name, false), defaultValue);
  }
  
  public static String get(String name, boolean quiet) {
    String value = null;
    try {
      value = System.getProperty(name);
    } catch (SecurityException e) {
      if (false == quiet)
        Console.error("Caught a SecurityException reading the system property '{}'; the SystemUtil property value will default to null.", new Object[] { name }); 
    } 
    if (null == value)
      try {
        value = System.getenv(name);
      } catch (SecurityException e) {
        if (false == quiet)
          Console.error("Caught a SecurityException reading the system env '{}'; the SystemUtil env value will default to null.", new Object[] { name }); 
      }  
    return value;
  }
  
  public static String get(String key) {
    return get(key, (String)null);
  }
  
  public static boolean getBoolean(String key, boolean defaultValue) {
    String value = get(key);
    if (value == null)
      return defaultValue; 
    return BooleanUtil.toBoolean(value);
  }
  
  public static int getInt(String key, int defaultValue) {
    return Convert.toInt(get(key), Integer.valueOf(defaultValue)).intValue();
  }
  
  public static long getLong(String key, long defaultValue) {
    return Convert.toLong(get(key), Long.valueOf(defaultValue)).longValue();
  }
  
  public static Properties getProps() {
    return System.getProperties();
  }
  
  public static void set(String key, String value) {
    if (null == value) {
      System.clearProperty(key);
    } else {
      System.setProperty(key, value);
    } 
  }
}
