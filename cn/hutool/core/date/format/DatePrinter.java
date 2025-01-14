package cn.hutool.core.date.format;

import java.util.Calendar;
import java.util.Date;

public interface DatePrinter extends DateBasic {
  String format(long paramLong);
  
  String format(Date paramDate);
  
  String format(Calendar paramCalendar);
  
  <B extends Appendable> B format(long paramLong, B paramB);
  
  <B extends Appendable> B format(Date paramDate, B paramB);
  
  <B extends Appendable> B format(Calendar paramCalendar, B paramB);
}
