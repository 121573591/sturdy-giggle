package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import java.time.Period;
import java.time.temporal.TemporalAmount;

public class PeriodConverter extends AbstractConverter<Period> {
  private static final long serialVersionUID = 1L;
  
  protected Period convertInternal(Object value) {
    if (value instanceof TemporalAmount)
      return Period.from((TemporalAmount)value); 
    if (value instanceof Integer)
      return Period.ofDays(((Integer)value).intValue()); 
    return Period.parse(convertToStr(value));
  }
}
