package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.util.function.Function;

public class PrimitiveConverter extends AbstractConverter<Object> {
  private static final long serialVersionUID = 1L;
  
  private final Class<?> targetType;
  
  public PrimitiveConverter(Class<?> clazz) {
    if (null == clazz)
      throw new NullPointerException("PrimitiveConverter not allow null target type!"); 
    if (false == clazz.isPrimitive())
      throw new IllegalArgumentException("[" + clazz + "] is not a primitive class!"); 
    this.targetType = clazz;
  }
  
  protected Object convertInternal(Object value) {
    return convert(value, this.targetType, this::convertToStr);
  }
  
  protected String convertToStr(Object value) {
    return StrUtil.trim(super.convertToStr(value));
  }
  
  public Class<Object> getTargetType() {
    return (Class)this.targetType;
  }
  
  protected static Object convert(Object value, Class<?> primitiveClass, Function<Object, String> toStringFunc) {
    if (byte.class == primitiveClass)
      return ObjectUtil.defaultIfNull(NumberConverter.convert(value, (Class)Byte.class, toStringFunc), Integer.valueOf(0)); 
    if (short.class == primitiveClass)
      return ObjectUtil.defaultIfNull(NumberConverter.convert(value, (Class)Short.class, toStringFunc), Integer.valueOf(0)); 
    if (int.class == primitiveClass)
      return ObjectUtil.defaultIfNull(NumberConverter.convert(value, (Class)Integer.class, toStringFunc), Integer.valueOf(0)); 
    if (long.class == primitiveClass)
      return ObjectUtil.defaultIfNull(NumberConverter.convert(value, (Class)Long.class, toStringFunc), Integer.valueOf(0)); 
    if (float.class == primitiveClass)
      return ObjectUtil.defaultIfNull(NumberConverter.convert(value, (Class)Float.class, toStringFunc), Integer.valueOf(0)); 
    if (double.class == primitiveClass)
      return ObjectUtil.defaultIfNull(NumberConverter.convert(value, (Class)Double.class, toStringFunc), Integer.valueOf(0)); 
    if (char.class == primitiveClass)
      return Convert.convert(Character.class, value); 
    if (boolean.class == primitiveClass)
      return Convert.convert(Boolean.class, value); 
    throw new ConvertException("Unsupported target type: {}", new Object[] { primitiveClass });
  }
}
