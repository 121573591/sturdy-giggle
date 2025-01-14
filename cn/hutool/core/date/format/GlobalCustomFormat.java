package cn.hutool.core.date.format;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.SafeConcurrentHashMap;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class GlobalCustomFormat {
  public static final String FORMAT_SECONDS = "#sss";
  
  public static final String FORMAT_MILLISECONDS = "#SSS";
  
  private static final Map<CharSequence, Function<Date, String>> formatterMap = (Map<CharSequence, Function<Date, String>>)new SafeConcurrentHashMap();
  
  private static final Map<CharSequence, Function<CharSequence, Date>> parserMap = (Map<CharSequence, Function<CharSequence, Date>>)new SafeConcurrentHashMap();
  
  static {
    putFormatter("#sss", date -> String.valueOf(Math.floorDiv(date.getTime(), 1000L)));
    putParser("#sss", dateStr -> DateUtil.date(Math.multiplyExact(Long.parseLong(dateStr.toString()), 1000L)));
    putFormatter("#SSS", date -> String.valueOf(date.getTime()));
    putParser("#SSS", dateStr -> DateUtil.date(Long.parseLong(dateStr.toString())));
  }
  
  public static void putFormatter(String format, Function<Date, String> func) {
    Assert.notNull(format, "Format must be not null !", new Object[0]);
    Assert.notNull(func, "Function must be not null !", new Object[0]);
    formatterMap.put(format, func);
  }
  
  public static void putParser(String format, Function<CharSequence, Date> func) {
    Assert.notNull(format, "Format must be not null !", new Object[0]);
    Assert.notNull(func, "Function must be not null !", new Object[0]);
    parserMap.put(format, func);
  }
  
  public static boolean isCustomFormat(String format) {
    return formatterMap.containsKey(format);
  }
  
  public static String format(Date date, CharSequence format) {
    if (null != formatterMap) {
      Function<Date, String> func = formatterMap.get(format);
      if (null != func)
        return func.apply(date); 
    } 
    return null;
  }
  
  public static String format(TemporalAccessor temporalAccessor, CharSequence format) {
    return format((Date)DateUtil.date(temporalAccessor), format);
  }
  
  public static Date parse(CharSequence dateStr, String format) {
    if (null != parserMap) {
      Function<CharSequence, Date> func = parserMap.get(format);
      if (null != func)
        return func.apply(dateStr); 
    } 
    return null;
  }
}
