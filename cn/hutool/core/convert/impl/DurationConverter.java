package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class DurationConverter extends AbstractConverter<Duration> {
  private static final long serialVersionUID = 1L;
  
  protected Duration convertInternal(Object value) {
    if (value instanceof TemporalAmount)
      return Duration.from((TemporalAmount)value); 
    if (value instanceof Long)
      return Duration.ofMillis(((Long)value).longValue()); 
    return Duration.parse(convertToStr(value));
  }
}
