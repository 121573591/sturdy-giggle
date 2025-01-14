package cn.hutool.core.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

public class PairConverter extends AbstractConverter<Pair<?, ?>> {
  private final Type pairType;
  
  private final Type keyType;
  
  private final Type valueType;
  
  public PairConverter(Type pairType) {
    this(pairType, null, null);
  }
  
  public PairConverter(Type pairType, Type keyType, Type valueType) {
    this.pairType = pairType;
    this.keyType = keyType;
    this.valueType = valueType;
  }
  
  protected Pair<?, ?> convertInternal(Object value) {
    Map<CharSequence, CharSequence> map = null;
    if (value instanceof Map.Entry) {
      Map.Entry entry = (Map.Entry)value;
      map = MapUtil.of(entry.getKey(), entry.getValue());
    } else if (value instanceof Map) {
      map = (Map)value;
    } else if (value instanceof CharSequence) {
      CharSequence str = (CharSequence)value;
      map = strToMap(str);
    } else if (BeanUtil.isReadableBean(value.getClass())) {
      map = BeanUtil.beanToMap(value, new String[0]);
    } 
    if (null != map)
      return mapToPair(this.pairType, this.keyType, this.valueType, map); 
    throw new ConvertException("Unsupported to map from [{}] of type: {}", new Object[] { value, value.getClass().getName() });
  }
  
  private static Map<CharSequence, CharSequence> strToMap(CharSequence str) {
    int index = StrUtil.indexOf(str, '=', 0, str.length());
    if (index > -1)
      return MapUtil.of(str.subSequence(0, index + 1), str.subSequence(index, str.length())); 
    return null;
  }
  
  private static Pair<?, ?> mapToPair(Type targetType, Type keyType, Type valueType, Map map) {
    Object key = null;
    Object value = null;
    if (1 == map.size()) {
      Map.Entry entry = map.entrySet().iterator().next();
      key = entry.getKey();
      value = entry.getValue();
    } else if (2 == map.size()) {
      key = map.get("key");
      value = map.get("value");
    } 
    ConverterRegistry convert = ConverterRegistry.getInstance();
    return (Pair<?, ?>)ReflectUtil.newInstance(TypeUtil.getClass(targetType), new Object[] { TypeUtil.isUnknown(keyType) ? key : convert.convert(keyType, key), 
          TypeUtil.isUnknown(valueType) ? value : convert.convert(valueType, value) });
  }
}
