package cn.hutool.core.date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class TemporalUtil {
  public static Duration between(Temporal startTimeInclude, Temporal endTimeExclude) {
    return Duration.between(startTimeInclude, endTimeExclude);
  }
  
  public static long between(Temporal startTimeInclude, Temporal endTimeExclude, ChronoUnit unit) {
    return unit.between(startTimeInclude, endTimeExclude);
  }
  
  public static ChronoUnit toChronoUnit(TimeUnit unit) throws IllegalArgumentException {
    if (null == unit)
      return null; 
    switch (unit) {
      case NANOS:
        return ChronoUnit.NANOS;
      case MICROS:
        return ChronoUnit.MICROS;
      case MILLIS:
        return ChronoUnit.MILLIS;
      case SECONDS:
        return ChronoUnit.SECONDS;
      case MINUTES:
        return ChronoUnit.MINUTES;
      case HOURS:
        return ChronoUnit.HOURS;
      case DAYS:
        return ChronoUnit.DAYS;
    } 
    throw new IllegalArgumentException("Unknown TimeUnit constant");
  }
  
  public static TimeUnit toTimeUnit(ChronoUnit unit) throws IllegalArgumentException {
    if (null == unit)
      return null; 
    switch (unit) {
      case NANOS:
        return TimeUnit.NANOSECONDS;
      case MICROS:
        return TimeUnit.MICROSECONDS;
      case MILLIS:
        return TimeUnit.MILLISECONDS;
      case SECONDS:
        return TimeUnit.SECONDS;
      case MINUTES:
        return TimeUnit.MINUTES;
      case HOURS:
        return TimeUnit.HOURS;
      case DAYS:
        return TimeUnit.DAYS;
    } 
    throw new IllegalArgumentException("ChronoUnit cannot be converted to TimeUnit: " + unit);
  }
  
  public static <T extends Temporal> T offset(T time, long number, TemporalUnit field) {
    if (null == time)
      return null; 
    return (T)time.plus(number, field);
  }
  
  public <T extends Temporal> T offset(T temporal, DayOfWeek dayOfWeek, boolean isPrevious) {
    return (T)temporal.with(isPrevious ? TemporalAdjusters.previous(dayOfWeek) : TemporalAdjusters.next(dayOfWeek));
  }
}
