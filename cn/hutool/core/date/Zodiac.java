package cn.hutool.core.date;

import cn.hutool.core.lang.Assert;
import java.util.Calendar;
import java.util.Date;

public class Zodiac {
  private static final int[] DAY_ARR = new int[] { 
      20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 
      23, 22 };
  
  private static final String[] ZODIACS = new String[] { 
      "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", 
      "天蝎座", "射手座", "摩羯座" };
  
  private static final String[] CHINESE_ZODIACS = new String[] { 
      "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", 
      "狗", "猪" };
  
  public static String getZodiac(Date date) {
    return getZodiac(DateUtil.calendar(date));
  }
  
  public static String getZodiac(Calendar calendar) {
    if (null == calendar)
      return null; 
    return getZodiac(calendar.get(2), calendar.get(5));
  }
  
  public static String getZodiac(Month month, int day) {
    return getZodiac(month.getValue(), day);
  }
  
  public static String getZodiac(int month, int day) {
    Assert.checkBetween(month, Month.JANUARY
        .getValue(), Month.DECEMBER
        .getValue(), "Unsupported month value, must be [0,12]", new Object[0]);
    return (day < DAY_ARR[month]) ? ZODIACS[month] : ZODIACS[month + 1];
  }
  
  public static String getChineseZodiac(Date date) {
    return getChineseZodiac(DateUtil.calendar(date));
  }
  
  public static String getChineseZodiac(Calendar calendar) {
    if (null == calendar)
      return null; 
    return getChineseZodiac(calendar.get(1));
  }
  
  public static String getChineseZodiac(int year) {
    if (year < 1900)
      return null; 
    return CHINESE_ZODIACS[(year - 1900) % CHINESE_ZODIACS.length];
  }
}
