package cn.hutool.core.convert;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.util.Map;

public abstract class AbstractConverter<T> implements Converter<T>, Serializable {
  private static final long serialVersionUID = 1L;
  
  public T convertQuietly(Object value, T defaultValue) {
    try {
      return convert(value, defaultValue);
    } catch (Exception e) {
      return defaultValue;
    } 
  }
  
  public T convert(Object value, T defaultValue) {
    Class<T> targetType = getTargetType();
    if (null == targetType && null == defaultValue)
      throw new NullPointerException(StrUtil.format("[type] and [defaultValue] are both null for Converter [{}], we can not know what type to convert !", new Object[] { getClass().getName() })); 
    if (null == targetType)
      targetType = (Class)defaultValue.getClass(); 
    if (null == value)
      return defaultValue; 
    if (null == defaultValue || targetType.isInstance(defaultValue)) {
      if (targetType.isInstance(value) && false == Map.class.isAssignableFrom(targetType))
        return targetType.cast(value); 
      T result = convertInternal(value);
      return (null == result) ? defaultValue : result;
    } 
    throw new IllegalArgumentException(
        StrUtil.format("Default value [{}]({}) is not the instance of [{}]", new Object[] { defaultValue, defaultValue.getClass(), targetType }));
  }
  
  protected abstract T convertInternal(Object paramObject);
  
  protected String convertToStr(Object value) {
    if (null == value)
      return null; 
    if (value instanceof CharSequence)
      return value.toString(); 
    if (ArrayUtil.isArray(value))
      return ArrayUtil.toString(value); 
    if (CharUtil.isChar(value))
      return CharUtil.toString(((Character)value).charValue()); 
    return value.toString();
  }
  
  public Class<T> getTargetType() {
    return ClassUtil.getTypeArgument(getClass());
  }
}
