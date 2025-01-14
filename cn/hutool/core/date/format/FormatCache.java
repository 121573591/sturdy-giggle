package cn.hutool.core.date.format;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Tuple;
import cn.hutool.core.map.SafeConcurrentHashMap;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentMap;

abstract class FormatCache<F extends Format> {
  static final int NONE = -1;
  
  private final ConcurrentMap<Tuple, F> cInstanceCache = (ConcurrentMap<Tuple, F>)new SafeConcurrentHashMap(7);
  
  private static final ConcurrentMap<Tuple, String> C_DATE_TIME_INSTANCE_CACHE = (ConcurrentMap<Tuple, String>)new SafeConcurrentHashMap(7);
  
  public F getInstance() {
    return getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), null, null);
  }
  
  public F getInstance(String pattern, TimeZone timeZone, Locale locale) {
    Assert.notBlank(pattern, "pattern must not be blank", new Object[0]);
    if (timeZone == null)
      timeZone = TimeZone.getDefault(); 
    if (locale == null)
      locale = Locale.getDefault(); 
    Tuple key = new Tuple(new Object[] { pattern, timeZone, locale });
    Format format = (Format)this.cInstanceCache.get(key);
    if (format == null) {
      format = (Format)createInstance(pattern, timeZone, locale);
      Format format1 = (Format)this.cInstanceCache.putIfAbsent(key, (F)format);
      if (format1 != null)
        format = format1; 
    } 
    return (F)format;
  }
  
  protected abstract F createInstance(String paramString, TimeZone paramTimeZone, Locale paramLocale);
  
  F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale) {
    if (locale == null)
      locale = Locale.getDefault(); 
    String pattern = getPatternForStyle(dateStyle, timeStyle, locale);
    return getInstance(pattern, timeZone, locale);
  }
  
  F getDateInstance(int dateStyle, TimeZone timeZone, Locale locale) {
    return getDateTimeInstance(Integer.valueOf(dateStyle), null, timeZone, locale);
  }
  
  F getTimeInstance(int timeStyle, TimeZone timeZone, Locale locale) {
    return getDateTimeInstance(null, Integer.valueOf(timeStyle), timeZone, locale);
  }
  
  static String getPatternForStyle(Integer dateStyle, Integer timeStyle, Locale locale) {
    Tuple key = new Tuple(new Object[] { dateStyle, timeStyle, locale });
    String pattern = C_DATE_TIME_INSTANCE_CACHE.get(key);
    if (pattern == null)
      try {
        DateFormat formatter;
        if (dateStyle == null) {
          formatter = DateFormat.getTimeInstance(timeStyle.intValue(), locale);
        } else if (timeStyle == null) {
          formatter = DateFormat.getDateInstance(dateStyle.intValue(), locale);
        } else {
          formatter = DateFormat.getDateTimeInstance(dateStyle.intValue(), timeStyle.intValue(), locale);
        } 
        pattern = ((SimpleDateFormat)formatter).toPattern();
        String previous = C_DATE_TIME_INSTANCE_CACHE.putIfAbsent(key, pattern);
        if (previous != null)
          pattern = previous; 
      } catch (ClassCastException ex) {
        throw new IllegalArgumentException("No date time pattern for locale: " + locale);
      }  
    return pattern;
  }
}
