package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Calendar;
import java.util.Date;

public class CalendarConverter extends AbstractConverter<Calendar> {
  private static final long serialVersionUID = 1L;
  
  private String format;
  
  public String getFormat() {
    return this.format;
  }
  
  public void setFormat(String format) {
    this.format = format;
  }
  
  protected Calendar convertInternal(Object value) {
    if (value instanceof Date)
      return DateUtil.calendar((Date)value); 
    if (value instanceof Long)
      return DateUtil.calendar(((Long)value).longValue()); 
    String valueStr = convertToStr(value);
    return DateUtil.calendar(StrUtil.isBlank(this.format) ? (Date)DateUtil.parse(valueStr) : (Date)DateUtil.parse(valueStr, this.format));
  }
}
