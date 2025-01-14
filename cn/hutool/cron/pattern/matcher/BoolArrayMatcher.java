package cn.hutool.cron.pattern.matcher;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BoolArrayMatcher implements PartMatcher {
  private final int minValue;
  
  private final boolean[] bValues;
  
  public BoolArrayMatcher(List<Integer> intValueList) {
    Assert.isTrue(CollUtil.isNotEmpty(intValueList), "Values must be not empty!", new Object[0]);
    this.bValues = new boolean[((Integer)Collections.max((Collection)intValueList)).intValue() + 1];
    int min = Integer.MAX_VALUE;
    for (Integer value : intValueList) {
      min = Math.min(min, value.intValue());
      this.bValues[value.intValue()] = true;
    } 
    this.minValue = min;
  }
  
  public boolean match(Integer value) {
    if (null == value || value.intValue() >= this.bValues.length)
      return false; 
    return this.bValues[value.intValue()];
  }
  
  public int nextAfter(int value) {
    if (value > this.minValue)
      while (value < this.bValues.length) {
        if (this.bValues[value])
          return value; 
        value++;
      }  
    return this.minValue;
  }
  
  public int getMinValue() {
    return this.minValue;
  }
  
  public String toString() {
    return StrUtil.format("Matcher:{}", new Object[] { this.bValues });
  }
}
