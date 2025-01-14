package cn.hutool.cron.pattern.parser;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.CronException;
import cn.hutool.cron.pattern.Part;
import cn.hutool.cron.pattern.matcher.AlwaysTrueMatcher;
import cn.hutool.cron.pattern.matcher.BoolArrayMatcher;
import cn.hutool.cron.pattern.matcher.DayOfMonthMatcher;
import cn.hutool.cron.pattern.matcher.PartMatcher;
import cn.hutool.cron.pattern.matcher.YearValueMatcher;
import java.util.ArrayList;
import java.util.List;

public class PartParser {
  private final Part part;
  
  public static PartParser of(Part part) {
    return new PartParser(part);
  }
  
  public PartParser(Part part) {
    this.part = part;
  }
  
  public PartMatcher parse(String value) {
    if (isMatchAllStr(value))
      return (PartMatcher)new AlwaysTrueMatcher(); 
    List<Integer> values = parseArray(value);
    if (values.isEmpty())
      throw new CronException("Invalid part value: [{}]", new Object[] { value }); 
    switch (this.part) {
      case DAY_OF_MONTH:
        return (PartMatcher)new DayOfMonthMatcher(values);
      case YEAR:
        return (PartMatcher)new YearValueMatcher(values);
    } 
    return (PartMatcher)new BoolArrayMatcher(values);
  }
  
  private List<Integer> parseArray(String value) {
    List<Integer> values = new ArrayList<>();
    List<String> parts = StrUtil.split(value, ',');
    for (String part : parts)
      CollUtil.addAllIfNotContains(values, parseStep(part)); 
    return values;
  }
  
  private List<Integer> parseStep(String value) {
    List<Integer> results;
    List<String> parts = StrUtil.split(value, '/');
    int size = parts.size();
    if (size == 1) {
      results = parseRange(value, -1);
    } else if (size == 2) {
      int step = parseNumber(parts.get(1), false);
      if (step < 1)
        throw new CronException("Non positive divisor for field: [{}]", new Object[] { value }); 
      results = parseRange(parts.get(0), step);
    } else {
      throw new CronException("Invalid syntax of field: [{}]", new Object[] { value });
    } 
    return results;
  }
  
  private List<Integer> parseRange(String value, int step) {
    List<Integer> results = new ArrayList<>();
    if (value.length() <= 2) {
      int minValue = this.part.getMin();
      if (false == isMatchAllStr(value)) {
        minValue = Math.max(minValue, parseNumber(value, true));
      } else if (step < 1) {
        step = 1;
      } 
      if (step > 0) {
        int maxValue = this.part.getMax();
        if (minValue > maxValue)
          throw new CronException("Invalid value {} > {}", new Object[] { Integer.valueOf(minValue), Integer.valueOf(maxValue) }); 
        int i;
        for (i = minValue; i <= maxValue; i += step)
          results.add(Integer.valueOf(i)); 
      } else {
        results.add(Integer.valueOf(minValue));
      } 
      return results;
    } 
    List<String> parts = StrUtil.split(value, '-');
    int size = parts.size();
    if (size == 1) {
      int v1 = parseNumber(value, true);
      if (step > 0) {
        NumberUtil.appendRange(v1, this.part.getMax(), step, results);
      } else {
        results.add(Integer.valueOf(v1));
      } 
    } else if (size == 2) {
      int v1 = parseNumber(parts.get(0), true);
      int v2 = parseNumber(parts.get(1), true);
      if (step < 1)
        step = 1; 
      if (v1 <= v2) {
        NumberUtil.appendRange(v1, v2, step, results);
      } else {
        NumberUtil.appendRange(v1, this.part.getMax(), step, results);
        NumberUtil.appendRange(this.part.getMin(), v2, step, results);
      } 
    } else {
      throw new CronException("Invalid syntax of field: [{}]", new Object[] { value });
    } 
    return results;
  }
  
  private static boolean isMatchAllStr(String value) {
    return (1 == value.length() && ("*".equals(value) || "?".equals(value)));
  }
  
  private int parseNumber(String value, boolean checkValue) throws CronException {
    int i;
    try {
      i = Integer.parseInt(value);
    } catch (NumberFormatException ignore) {
      i = parseAlias(value);
    } 
    if (i < 0)
      i += this.part.getMax(); 
    if (Part.DAY_OF_WEEK.equals(this.part) && Week.SUNDAY.getIso8601Value() == i)
      i = Week.SUNDAY.ordinal(); 
    return checkValue ? this.part.checkValue(i) : i;
  }
  
  private int parseAlias(String name) throws CronException {
    if ("L".equalsIgnoreCase(name))
      return this.part.getMax(); 
    switch (this.part) {
      case MONTH:
        return Month.of(name).getValueBaseOne();
      case DAY_OF_WEEK:
        return Week.of(name).ordinal();
    } 
    throw new CronException("Invalid alias value: [{}]", new Object[] { name });
  }
}
