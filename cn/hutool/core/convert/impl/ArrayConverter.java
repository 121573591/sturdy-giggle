package cn.hutool.core.convert.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ArrayConverter extends AbstractConverter<Object> {
  private static final long serialVersionUID = 1L;
  
  private final Class<?> targetType;
  
  private final Class<?> targetComponentType;
  
  private boolean ignoreElementError;
  
  public ArrayConverter(Class<?> targetType) {
    this(targetType, false);
  }
  
  public ArrayConverter(Class<?> targetType, boolean ignoreElementError) {
    if (null == targetType)
      targetType = Object[].class; 
    if (targetType.isArray()) {
      this.targetType = targetType;
      this.targetComponentType = targetType.getComponentType();
    } else {
      this.targetComponentType = targetType;
      this.targetType = ArrayUtil.getArrayType(targetType);
    } 
    this.ignoreElementError = ignoreElementError;
  }
  
  protected Object convertInternal(Object value) {
    return value.getClass().isArray() ? convertArrayToArray(value) : convertObjectToArray(value);
  }
  
  public Class getTargetType() {
    return this.targetType;
  }
  
  public void setIgnoreElementError(boolean ignoreElementError) {
    this.ignoreElementError = ignoreElementError;
  }
  
  private Object convertArrayToArray(Object array) {
    Class<?> valueComponentType = ArrayUtil.getComponentType(array);
    if (valueComponentType == this.targetComponentType)
      return array; 
    int len = ArrayUtil.length(array);
    Object result = Array.newInstance(this.targetComponentType, len);
    for (int i = 0; i < len; i++)
      Array.set(result, i, convertComponentType(Array.get(array, i))); 
    return result;
  }
  
  private Object convertObjectToArray(Object value) {
    Object result;
    if (value instanceof CharSequence) {
      if (this.targetComponentType == char.class || this.targetComponentType == Character.class)
        return convertArrayToArray(value.toString().toCharArray()); 
      if (this.targetComponentType == byte.class) {
        String str = value.toString();
        if (Base64.isBase64(str))
          return Base64.decode(value.toString()); 
        return str.getBytes();
      } 
      String[] strings = StrUtil.splitToArray(value.toString(), ',');
      return convertArrayToArray(strings);
    } 
    if (value instanceof List) {
      List<?> list = (List)value;
      result = Array.newInstance(this.targetComponentType, list.size());
      for (int i = 0; i < list.size(); i++)
        Array.set(result, i, convertComponentType(list.get(i))); 
    } else if (value instanceof Collection) {
      Collection<?> collection = (Collection)value;
      result = Array.newInstance(this.targetComponentType, collection.size());
      int i = 0;
      for (Object element : collection) {
        Array.set(result, i, convertComponentType(element));
        i++;
      } 
    } else if (value instanceof Iterable) {
      List<?> list = IterUtil.toList((Iterable)value);
      result = Array.newInstance(this.targetComponentType, list.size());
      for (int i = 0; i < list.size(); i++)
        Array.set(result, i, convertComponentType(list.get(i))); 
    } else if (value instanceof Iterator) {
      List<?> list = IterUtil.toList((Iterator)value);
      result = Array.newInstance(this.targetComponentType, list.size());
      for (int i = 0; i < list.size(); i++)
        Array.set(result, i, convertComponentType(list.get(i))); 
    } else if (value instanceof Number && byte.class == this.targetComponentType) {
      result = ByteUtil.numberToBytes((Number)value);
    } else if (value instanceof java.io.Serializable && byte.class == this.targetComponentType) {
      result = ObjectUtil.serialize(value);
    } else {
      result = convertToSingleElementArray(value);
    } 
    return result;
  }
  
  private Object[] convertToSingleElementArray(Object value) {
    Object[] singleElementArray = ArrayUtil.newArray(this.targetComponentType, 1);
    singleElementArray[0] = convertComponentType(value);
    return singleElementArray;
  }
  
  private Object convertComponentType(Object value) {
    return Convert.convertWithCheck(this.targetComponentType, value, null, this.ignoreElementError);
  }
}
