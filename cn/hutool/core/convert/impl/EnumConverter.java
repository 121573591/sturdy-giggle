package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.lang.EnumItem;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.map.WeakConcurrentMap;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.ReflectUtil;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumConverter extends AbstractConverter<Object> {
  private static final long serialVersionUID = 1L;
  
  private static final WeakConcurrentMap<Class<?>, Map<Class<?>, Method>> VALUE_OF_METHOD_CACHE = new WeakConcurrentMap();
  
  private final Class enumClass;
  
  public EnumConverter(Class enumClass) {
    this.enumClass = enumClass;
  }
  
  protected Object convertInternal(Object value) {
    Enum enumValue = tryConvertEnum(value, this.enumClass);
    if (null == enumValue && false == value instanceof String)
      enumValue = Enum.valueOf(this.enumClass, convertToStr(value)); 
    if (null != enumValue)
      return enumValue; 
    throw new ConvertException("Can not convert {} to {}", new Object[] { value, this.enumClass });
  }
  
  public Class getTargetType() {
    return this.enumClass;
  }
  
  protected static Enum tryConvertEnum(Object value, Class<?> enumClass) {
    if (value == null)
      return null; 
    if (EnumItem.class.isAssignableFrom(enumClass)) {
      EnumItem first = (EnumItem)EnumUtil.getEnumAt(enumClass, 0);
      if (null != first) {
        if (value instanceof Integer)
          return (Enum)first.fromInt((Integer)value); 
        if (value instanceof String)
          return (Enum)first.fromStr(value.toString()); 
      } 
    } 
    try {
      Map<Class<?>, Method> methodMap = getMethodMap(enumClass);
      if (MapUtil.isNotEmpty(methodMap)) {
        Class<?> valueClass = value.getClass();
        for (Map.Entry<Class<?>, Method> entry : methodMap.entrySet()) {
          if (ClassUtil.isAssignable(entry.getKey(), valueClass))
            return (Enum)ReflectUtil.invokeStatic(entry.getValue(), new Object[] { value }); 
        } 
      } 
    } catch (Exception exception) {}
    Enum enumResult = null;
    if (value instanceof Integer) {
      enumResult = EnumUtil.getEnumAt(enumClass, ((Integer)value).intValue());
    } else if (value instanceof String) {
      try {
        enumResult = Enum.valueOf(enumClass, (String)value);
      } catch (IllegalArgumentException illegalArgumentException) {}
    } 
    return enumResult;
  }
  
  private static Map<Class<?>, Method> getMethodMap(Class<?> enumClass) {
    return (Map<Class<?>, Method>)VALUE_OF_METHOD_CACHE.computeIfAbsent(enumClass, key -> (Map)Arrays.<Method>stream(enumClass.getMethods()).filter(ModifierUtil::isStatic).filter(()).filter(()).filter(()).collect(Collectors.toMap((), (), ())));
  }
}
