package cn.hutool.json.serialize;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAccessor;

public class TemporalAccessorSerializer implements JSONObjectSerializer<TemporalAccessor>, JSONDeserializer<TemporalAccessor> {
  private static final String YEAR_KEY = "year";
  
  private static final String MONTH_KEY = "month";
  
  private static final String DAY_KEY = "day";
  
  private static final String HOUR_KEY = "hour";
  
  private static final String MINUTE_KEY = "minute";
  
  private static final String SECOND_KEY = "second";
  
  private static final String NANO_KEY = "nano";
  
  private final Class<? extends TemporalAccessor> temporalAccessorClass;
  
  public TemporalAccessorSerializer(Class<? extends TemporalAccessor> temporalAccessorClass) {
    this.temporalAccessorClass = temporalAccessorClass;
  }
  
  public void serialize(JSONObject json, TemporalAccessor bean) {
    if (bean instanceof LocalDate) {
      LocalDate localDate = (LocalDate)bean;
      json.set("year", Integer.valueOf(localDate.getYear()));
      json.set("month", Integer.valueOf(localDate.getMonthValue()));
      json.set("day", Integer.valueOf(localDate.getDayOfMonth()));
    } else if (bean instanceof LocalDateTime) {
      LocalDateTime localDateTime = (LocalDateTime)bean;
      json.set("year", Integer.valueOf(localDateTime.getYear()));
      json.set("month", Integer.valueOf(localDateTime.getMonthValue()));
      json.set("day", Integer.valueOf(localDateTime.getDayOfMonth()));
      json.set("hour", Integer.valueOf(localDateTime.getHour()));
      json.set("minute", Integer.valueOf(localDateTime.getMinute()));
      json.set("second", Integer.valueOf(localDateTime.getSecond()));
      json.set("nano", Integer.valueOf(localDateTime.getNano()));
    } else if (bean instanceof LocalTime) {
      LocalTime localTime = (LocalTime)bean;
      json.set("hour", Integer.valueOf(localTime.getHour()));
      json.set("minute", Integer.valueOf(localTime.getMinute()));
      json.set("second", Integer.valueOf(localTime.getSecond()));
      json.set("nano", Integer.valueOf(localTime.getNano()));
    } else {
      throw new JSONException("Unsupported type to JSON: {}", new Object[] { bean.getClass().getName() });
    } 
  }
  
  public TemporalAccessor deserialize(JSON json) {
    JSONObject jsonObject = (JSONObject)json;
    if (LocalDate.class.equals(this.temporalAccessorClass) || LocalDateTime.class.equals(this.temporalAccessorClass)) {
      Integer year = jsonObject.getInt("year");
      Assert.notNull(year, "Field 'year' must be not null", new Object[0]);
      Integer month = jsonObject.getInt("month");
      if (null == month) {
        Month monthEnum = Month.valueOf(jsonObject.getStr("month"));
        Assert.notNull(monthEnum, "Field 'month' must be not null", new Object[0]);
        month = Integer.valueOf(monthEnum.getValue());
      } 
      Integer day = jsonObject.getInt("day");
      if (null == day) {
        day = jsonObject.getInt("dayOfMonth");
        Assert.notNull(day, "Field 'day' or 'dayOfMonth' must be not null", new Object[0]);
      } 
      LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), day.intValue());
      if (LocalDate.class.equals(this.temporalAccessorClass))
        return localDate; 
      LocalTime localTime = LocalTime.of(jsonObject
          .getInt("hour", Integer.valueOf(0)).intValue(), jsonObject
          .getInt("minute", Integer.valueOf(0)).intValue(), jsonObject
          .getInt("second", Integer.valueOf(0)).intValue(), jsonObject
          .getInt("nano", Integer.valueOf(0)).intValue());
      return LocalDateTime.of(localDate, localTime);
    } 
    if (LocalTime.class.equals(this.temporalAccessorClass))
      return LocalTime.of(jsonObject.getInt("hour").intValue(), jsonObject.getInt("minute").intValue(), jsonObject.getInt("second").intValue(), jsonObject.getInt("nano").intValue()); 
    throw new JSONException("Unsupported type from JSON: {}", new Object[] { this.temporalAccessorClass });
  }
}
