package cn.hutool.json;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.convert.impl.ArrayConverter;
import cn.hutool.core.convert.impl.BeanConverter;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import cn.hutool.json.serialize.GlobalSerializeMapping;
import cn.hutool.json.serialize.JSONDeserializer;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JSONConverter implements Converter<JSON> {
  static {
    ConverterRegistry registry = ConverterRegistry.getInstance();
    registry.putCustom(JSON.class, JSONConverter.class);
    registry.putCustom(JSONObject.class, JSONConverter.class);
    registry.putCustom(JSONArray.class, JSONConverter.class);
  }
  
  protected static Object toArray(JSONArray jsonArray, Class<?> arrayClass) {
    return (new ArrayConverter(arrayClass)).convert(jsonArray, null);
  }
  
  protected static <T> List<T> toList(JSONArray jsonArray, Class<T> elementType) {
    return Convert.toList(elementType, jsonArray);
  }
  
  protected static <T> T jsonConvert(Type targetType, Object value, JSONConfig jsonConfig) throws ConvertException {
    if (JSONUtil.isNull(value))
      return null; 
    if (targetType instanceof Class) {
      Class<?> clazz = (Class)targetType;
      if (JSONBeanParser.class.isAssignableFrom(clazz)) {
        JSONBeanParser<Object> target = (JSONBeanParser)ReflectUtil.newInstanceIfPossible(clazz);
        if (null == target)
          throw new ConvertException("Can not instance [{}]", new Object[] { targetType }); 
        target.parse(value);
        return (T)target;
      } 
      if (targetType == byte[].class && value instanceof CharSequence)
        return (T)Base64.decode((CharSequence)value); 
    } 
    return jsonToBean(targetType, value, jsonConfig.isIgnoreError());
  }
  
  protected static <T> T jsonToBean(Type targetType, Object value, boolean ignoreError) throws ConvertException {
    if (JSONUtil.isNull(value))
      return null; 
    if (value instanceof JSON) {
      JSONDeserializer<?> deserializer = GlobalSerializeMapping.getDeserializer(targetType);
      if (null != deserializer)
        return (T)deserializer.deserialize((JSON)value); 
      if (value instanceof JSONGetter && targetType instanceof Class && false == Map.Entry.class
        
        .isAssignableFrom((Class)targetType) && 
        BeanUtil.hasSetter((Class)targetType)) {
        JSONConfig config = ((JSONGetter)value).getConfig();
        BeanConverter beanConverter = new BeanConverter(targetType, InternalJSONUtil.toCopyOptions(config).setIgnoreError(ignoreError));
        return (T)beanConverter.convertWithCheck(value, null, ignoreError);
      } 
    } 
    T targetValue = (T)Convert.convertWithCheck(targetType, value, null, ignoreError);
    if (null == targetValue && false == ignoreError) {
      if (StrUtil.isBlankIfStr(value))
        return null; 
      throw new ConvertException("Can not convert {} to type {}", new Object[] { value, ObjectUtil.defaultIfNull(TypeUtil.getClass(targetType), targetType) });
    } 
    return targetValue;
  }
  
  public JSON convert(Object value, JSON defaultValue) throws IllegalArgumentException {
    return JSONUtil.parse(value);
  }
}
