package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class DateConverter extends AbstractConverter<Date> {
  private static final long serialVersionUID = 1L;
  
  private final Class<? extends Date> targetType;
  
  private String format;
  
  public DateConverter(Class<? extends Date> targetType) {
    this.targetType = targetType;
  }
  
  public DateConverter(Class<? extends Date> targetType, String format) {
    this.targetType = targetType;
    this.format = format;
  }
  
  public String getFormat() {
    return this.format;
  }
  
  public void setFormat(String format) {
    this.format = format;
  }
  
  protected Date convertInternal(Object value) {
    if (value == null || (value instanceof CharSequence && StrUtil.isBlank(value.toString())))
      return null; 
    if (value instanceof TemporalAccessor)
      return wrap(DateUtil.date((TemporalAccessor)value)); 
    if (value instanceof Calendar)
      return wrap(DateUtil.date((Calendar)value)); 
    if (value instanceof Number)
      return wrap(((Number)value).longValue()); 
    String valueStr = convertToStr(value);
    DateTime dateTime = StrUtil.isBlank(this.format) ? DateUtil.parse(valueStr) : DateUtil.parse(valueStr, this.format);
    if (null != dateTime)
      return wrap(dateTime); 
    throw new ConvertException("Can not convert {}:[{}] to {}", new Object[] { value.getClass().getName(), value, this.targetType.getName() });
  }
  
  private Date wrap(DateTime date) {
    if (Date.class == this.targetType)
      return date.toJdkDate(); 
    if (DateTime.class == this.targetType)
      return (Date)date; 
    if (Date.class == this.targetType)
      return date.toSqlDate(); 
    if (Time.class == this.targetType)
      return new Time(date.getTime()); 
    if (Timestamp.class == this.targetType)
      return date.toTimestamp(); 
    throw new UnsupportedOperationException(StrUtil.format("Unsupported target Date type: {}", new Object[] { this.targetType.getName() }));
  }
  
  private Date wrap(long mills) {
    if ("#sss".equals(this.format))
      return (Date)DateUtil.date(mills * 1000L); 
    if (Date.class == this.targetType)
      return new Date(mills); 
    if (DateTime.class == this.targetType)
      return (Date)DateUtil.date(mills); 
    if (Date.class == this.targetType)
      return new Date(mills); 
    if (Time.class == this.targetType)
      return new Time(mills); 
    if (Timestamp.class == this.targetType)
      return new Timestamp(mills); 
    throw new UnsupportedOperationException(StrUtil.format("Unsupported target Date type: {}", new Object[] { this.targetType.getName() }));
  }
  
  public Class<Date> getTargetType() {
    return (Class)this.targetType;
  }
}
