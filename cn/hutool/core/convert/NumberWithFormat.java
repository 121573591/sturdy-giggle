package cn.hutool.core.convert;

import cn.hutool.core.convert.impl.DateConverter;
import cn.hutool.core.convert.impl.TemporalAccessorConverter;
import java.lang.reflect.Type;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class NumberWithFormat extends Number implements TypeConverter {
  private static final long serialVersionUID = 1L;
  
  private final Number number;
  
  private final String format;
  
  public NumberWithFormat(Number number, String format) {
    this.number = number;
    this.format = format;
  }
  
  public Object convert(Type targetType, Object value) {
    if (null != this.format && targetType instanceof Class) {
      Class<?> clazz = (Class)targetType;
      if (Date.class.isAssignableFrom(clazz))
        return (new DateConverter(clazz, this.format)).convert(this.number, null); 
      if (TemporalAccessor.class.isAssignableFrom(clazz))
        return (new TemporalAccessorConverter(clazz, this.format)).convert(this.number, null); 
      if (String.class == clazz)
        return toString(); 
    } 
    return Convert.convertWithCheck(targetType, this.number, null, false);
  }
  
  public int intValue() {
    return this.number.intValue();
  }
  
  public long longValue() {
    return this.number.longValue();
  }
  
  public float floatValue() {
    return this.number.floatValue();
  }
  
  public double doubleValue() {
    return this.number.doubleValue();
  }
  
  public String toString() {
    return this.number.toString();
  }
}
