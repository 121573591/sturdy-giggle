package cn.hutool.core.date;

import cn.hutool.core.util.ArrayUtil;
import java.util.Calendar;

public class DateModifier {
  private static final int[] IGNORE_FIELDS = new int[] { 11, 9, 8, 6, 4, 3 };
  
  public static Calendar modify(Calendar calendar, int dateField, ModifyType modifyType) {
    return modify(calendar, dateField, modifyType, false);
  }
  
  public static Calendar modify(Calendar calendar, int dateField, ModifyType modifyType, boolean truncateMillisecond) {
    if (9 == dateField) {
      int min, max, href, value;
      boolean isAM = DateUtil.isAM(calendar);
      switch (modifyType) {
        case TRUNCATE:
          calendar.set(11, isAM ? 0 : 12);
          break;
        case CEILING:
          calendar.set(11, isAM ? 11 : 23);
          break;
        case ROUND:
          min = isAM ? 0 : 12;
          max = isAM ? 11 : 23;
          href = (max - min) / 2 + 1;
          value = calendar.get(11);
          calendar.set(11, (value < href) ? min : max);
          break;
      } 
      return modify(calendar, dateField + 1, modifyType);
    } 
    int endField = truncateMillisecond ? 13 : 14;
    for (int i = dateField + 1; i <= endField; i++) {
      if (!ArrayUtil.contains(IGNORE_FIELDS, i))
        if ((3 == dateField) ? (
          5 == i) : (
          
          7 == i))
          modifyField(calendar, i, modifyType);  
    } 
    if (truncateMillisecond)
      calendar.set(14, 0); 
    return calendar;
  }
  
  private static void modifyField(Calendar calendar, int field, ModifyType modifyType) {
    int min;
    int max;
    int href;
    int value;
    if (10 == field)
      field = 11; 
    switch (modifyType) {
      case TRUNCATE:
        calendar.set(field, DateUtil.getBeginValue(calendar, field));
        break;
      case CEILING:
        calendar.set(field, DateUtil.getEndValue(calendar, field));
        break;
      case ROUND:
        min = DateUtil.getBeginValue(calendar, field);
        max = DateUtil.getEndValue(calendar, field);
        if (7 == field) {
          href = (min + 3) % 7;
        } else {
          href = (max - min) / 2 + 1;
        } 
        value = calendar.get(field);
        calendar.set(field, (value < href) ? min : max);
        break;
    } 
  }
  
  public enum ModifyType {
    TRUNCATE, ROUND, CEILING;
  }
}
