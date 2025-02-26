package cn.hutool.core.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MapConverter extends AbstractConverter<Map<?, ?>> {
  private static final long serialVersionUID = 1L;
  
  private final Type mapType;
  
  private final Type keyType;
  
  private final Type valueType;
  
  public MapConverter(Type mapType) {
    this(mapType, TypeUtil.getTypeArgument(mapType, 0), TypeUtil.getTypeArgument(mapType, 1));
  }
  
  public MapConverter(Type mapType, Type keyType, Type valueType) {
    this.mapType = mapType;
    this.keyType = keyType;
    this.valueType = valueType;
  }
  
  protected Map<?, ?> convertInternal(Object value) {
    Map<?, ?> map;
    if (value instanceof Map) {
      Class<?> valueClass = value.getClass();
      if (valueClass.equals(this.mapType)) {
        Type[] typeArguments = TypeUtil.getTypeArguments(valueClass);
        if (null != typeArguments && 2 == typeArguments.length && 
          
          Objects.equals(this.keyType, typeArguments[0]) && 
          Objects.equals(this.valueType, typeArguments[1]))
          return (Map<?, ?>)value; 
      } 
      Class<?> mapClass = TypeUtil.getClass(this.mapType);
      if (null == mapClass || mapClass.isAssignableFrom(AbstractMap.class)) {
        map = new LinkedHashMap<>();
      } else {
        map = MapUtil.createMap(mapClass);
      } 
      convertMapToMap((Map<?, ?>)value, (Map)map);
    } else if (BeanUtil.isBean(value.getClass())) {
      map = BeanUtil.beanToMap(value, new String[0]);
      map = convertInternal(map);
    } else {
      throw new UnsupportedOperationException(StrUtil.format("Unsupported toMap value type: {}", new Object[] { value.getClass().getName() }));
    } 
    return map;
  }
  
  private void convertMapToMap(Map<?, ?> srcMap, Map<Object, Object> targetMap) {
    ConverterRegistry convert = ConverterRegistry.getInstance();
    srcMap.forEach((key, value) -> {
          key = TypeUtil.isUnknown(this.keyType) ? key : convert.convert(this.keyType, key);
          value = TypeUtil.isUnknown(this.valueType) ? value : convert.convert(this.valueType, value);
          targetMap.put(key, value);
        });
  }
  
  public Class<Map<?, ?>> getTargetType() {
    return TypeUtil.getClass(this.mapType);
  }
}
