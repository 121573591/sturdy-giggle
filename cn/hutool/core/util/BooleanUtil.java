package cn.hutool.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import java.util.Set;

public class BooleanUtil {
  private static final Set<String> TRUE_SET = CollUtil.newHashSet((Object[])new String[] { 
        "true", "yes", "y", "t", "ok", "1", "on", "是", "对", "真", 
        "對", "√" });
  
  private static final Set<String> FALSE_SET = CollUtil.newHashSet((Object[])new String[] { 
        "false", "no", "n", "f", "0", "off", "否", "错", "假", "錯", 
        "×" });
  
  public static Boolean negate(Boolean bool) {
    if (bool == null)
      return null; 
    return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
  }
  
  public static boolean isTrue(Boolean bool) {
    return Boolean.TRUE.equals(bool);
  }
  
  public static boolean isFalse(Boolean bool) {
    return Boolean.FALSE.equals(bool);
  }
  
  public static boolean negate(boolean bool) {
    return !bool;
  }
  
  public static boolean toBoolean(String valueStr) {
    if (StrUtil.isNotBlank(valueStr)) {
      valueStr = valueStr.trim().toLowerCase();
      return TRUE_SET.contains(valueStr);
    } 
    return false;
  }
  
  public static Boolean toBooleanObject(String valueStr) {
    if (StrUtil.isNotBlank(valueStr)) {
      valueStr = valueStr.trim().toLowerCase();
      if (TRUE_SET.contains(valueStr))
        return Boolean.valueOf(true); 
      if (FALSE_SET.contains(valueStr))
        return Boolean.valueOf(false); 
    } 
    return null;
  }
  
  public static int toInt(boolean value) {
    return value ? 1 : 0;
  }
  
  public static Integer toInteger(boolean value) {
    return Integer.valueOf(toInt(value));
  }
  
  public static char toChar(boolean value) {
    return (char)toInt(value);
  }
  
  public static Character toCharacter(boolean value) {
    return Character.valueOf(toChar(value));
  }
  
  public static byte toByte(boolean value) {
    return (byte)toInt(value);
  }
  
  public static Byte toByteObj(boolean value) {
    return Byte.valueOf(toByte(value));
  }
  
  public static long toLong(boolean value) {
    return toInt(value);
  }
  
  public static Long toLongObj(boolean value) {
    return Long.valueOf(toLong(value));
  }
  
  public static short toShort(boolean value) {
    return (short)toInt(value);
  }
  
  public static Short toShortObj(boolean value) {
    return Short.valueOf(toShort(value));
  }
  
  public static float toFloat(boolean value) {
    return toInt(value);
  }
  
  public static Float toFloatObj(boolean value) {
    return Float.valueOf(toFloat(value));
  }
  
  public static double toDouble(boolean value) {
    return toInt(value);
  }
  
  public static Double toDoubleObj(boolean value) {
    return Double.valueOf(toDouble(value));
  }
  
  public static String toStringTrueFalse(boolean bool) {
    return toString(bool, "true", "false");
  }
  
  public static String toStringOnOff(boolean bool) {
    return toString(bool, "on", "off");
  }
  
  public static String toStringYesNo(boolean bool) {
    return toString(bool, "yes", "no");
  }
  
  public static String toString(boolean bool, String trueString, String falseString) {
    return bool ? trueString : falseString;
  }
  
  public static String toString(Boolean bool, String trueString, String falseString, String nullString) {
    if (bool == null)
      return nullString; 
    return bool.booleanValue() ? trueString : falseString;
  }
  
  public static boolean and(boolean... array) {
    if (ArrayUtil.isEmpty(array))
      throw new IllegalArgumentException("The Array must not be empty !"); 
    for (boolean element : array) {
      if (false == element)
        return false; 
    } 
    return true;
  }
  
  public static Boolean andOfWrap(Boolean... array) {
    if (ArrayUtil.isEmpty(array))
      throw new IllegalArgumentException("The Array must not be empty !"); 
    for (Boolean b : array) {
      if (isFalse(b))
        return Boolean.valueOf(false); 
    } 
    return Boolean.valueOf(true);
  }
  
  public static boolean or(boolean... array) {
    if (ArrayUtil.isEmpty(array))
      throw new IllegalArgumentException("The Array must not be empty !"); 
    for (boolean element : array) {
      if (element)
        return true; 
    } 
    return false;
  }
  
  public static Boolean orOfWrap(Boolean... array) {
    if (ArrayUtil.isEmpty(array))
      throw new IllegalArgumentException("The Array must not be empty !"); 
    for (Boolean b : array) {
      if (isTrue(b))
        return Boolean.valueOf(true); 
    } 
    return Boolean.valueOf(false);
  }
  
  public static boolean xor(boolean... array) {
    if (ArrayUtil.isEmpty(array))
      throw new IllegalArgumentException("The Array must not be empty"); 
    boolean result = false;
    for (boolean element : array)
      result ^= element; 
    return result;
  }
  
  public static Boolean xorOfWrap(Boolean... array) {
    if (ArrayUtil.isEmpty(array))
      throw new IllegalArgumentException("The Array must not be empty !"); 
    boolean[] primitive = (boolean[])Convert.convert(boolean[].class, array);
    return Boolean.valueOf(xor(primitive));
  }
  
  public static boolean isBoolean(Class<?> clazz) {
    return (clazz == Boolean.class || clazz == boolean.class);
  }
}
