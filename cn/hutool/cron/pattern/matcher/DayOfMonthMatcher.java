package cn.hutool.cron.pattern.matcher;

import cn.hutool.core.date.Month;
import java.util.List;

public class DayOfMonthMatcher extends BoolArrayMatcher {
  public DayOfMonthMatcher(List<Integer> intValueList) {
    super(intValueList);
  }
  
  public boolean match(int value, int month, boolean isLeapYear) {
    return (match(Integer.valueOf(value)) || (value > 27 && 
      
      match(Integer.valueOf(31)) && isLastDayOfMonth(value, month, isLeapYear)));
  }
  
  private static boolean isLastDayOfMonth(int value, int month, boolean isLeapYear) {
    return (value == Month.getLastDay(month - 1, isLeapYear));
  }
}
