package cn.hutool.cron.pattern.matcher;

import java.util.Collection;
import java.util.LinkedHashSet;

public class YearValueMatcher implements PartMatcher {
  private final LinkedHashSet<Integer> valueList;
  
  public YearValueMatcher(Collection<Integer> intValueList) {
    this.valueList = new LinkedHashSet<>(intValueList);
  }
  
  public boolean match(Integer t) {
    return this.valueList.contains(t);
  }
  
  public int nextAfter(int value) {
    for (Integer year : this.valueList) {
      if (year.intValue() >= value)
        return year.intValue(); 
    } 
    return -1;
  }
}
