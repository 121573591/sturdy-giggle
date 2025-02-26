package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;

public class CharacterConverter extends AbstractConverter<Character> {
  private static final long serialVersionUID = 1L;
  
  protected Character convertInternal(Object value) {
    if (value instanceof Boolean)
      return BooleanUtil.toCharacter(((Boolean)value).booleanValue()); 
    String valueStr = convertToStr(value);
    if (StrUtil.isNotBlank(valueStr))
      return Character.valueOf(valueStr.charAt(0)); 
    return null;
  }
}
