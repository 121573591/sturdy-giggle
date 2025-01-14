package cn.hutool.core.lang;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;

public interface Segment<T extends Number> {
  T getStartIndex();
  
  T getEndIndex();
  
  default T length() {
    Number number1 = Assert.<Number>notNull((Number)getStartIndex(), "Start index must be not null!", new Object[0]);
    Number number2 = Assert.<Number>notNull((Number)getEndIndex(), "End index must be not null!", new Object[0]);
    return (T)Convert.convert(number1.getClass(), NumberUtil.sub(number2, number1).abs());
  }
}
