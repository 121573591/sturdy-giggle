package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.util.BooleanUtil;

public class BooleanConverter extends AbstractConverter<Boolean> {
  private static final long serialVersionUID = 1L;
  
  protected Boolean convertInternal(Object value) {
    if (value instanceof Number)
      return Boolean.valueOf((0.0D != ((Number)value).doubleValue())); 
    return Boolean.valueOf(BooleanUtil.toBoolean(convertToStr(value)));
  }
}
