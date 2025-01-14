package cn.hutool.core.date.format;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

public interface DateParser extends DateBasic {
  Date parse(String paramString) throws ParseException;
  
  Date parse(String paramString, ParsePosition paramParsePosition);
  
  boolean parse(String paramString, ParsePosition paramParsePosition, Calendar paramCalendar);
  
  default Object parseObject(String source) throws ParseException {
    return parse(source);
  }
  
  default Object parseObject(String source, ParsePosition pos) {
    return parse(source, pos);
  }
}
