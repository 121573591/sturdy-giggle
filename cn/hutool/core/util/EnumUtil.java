package cn.hutool.core.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.map.MapUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class EnumUtil {
  public static boolean isEnum(Class<?> clazz) {
    Assert.notNull(clazz);
    return clazz.isEnum();
  }
  
  public static boolean isEnum(Object obj) {
    Assert.notNull(obj);
    return obj.getClass().isEnum();
  }
  
  public static String toString(Enum<?> e) {
    return (null != e) ? e.name() : null;
  }
  
  public static <E extends Enum<E>> E getEnumAt(Class<E> enumClass, int index) {
    Enum[] arrayOfEnum = (Enum[])enumClass.getEnumConstants();
    return (index >= 0 && index < arrayOfEnum.length) ? (E)arrayOfEnum[index] : null;
  }
  
  public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value) {
    return Enum.valueOf(enumClass, value);
  }
  
  public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value, E defaultValue) {
    return (E)ObjectUtil.<Enum>defaultIfNull(fromStringQuietly(enumClass, value), (Enum)defaultValue);
  }
  
  public static <E extends Enum<E>> E fromStringQuietly(Class<E> enumClass, String value) {
    if (null == enumClass || StrUtil.isBlank(value))
      return null; 
    try {
      return fromString(enumClass, value);
    } catch (IllegalArgumentException e) {
      return null;
    } 
  }
  
  public static <E extends Enum<E>> E likeValueOf(Class<E> enumClass, Object value) {
    if (value instanceof CharSequence)
      value = value.toString().trim(); 
    Field[] fields = ReflectUtil.getFields(enumClass);
    Enum[] arrayOfEnum = (Enum[])enumClass.getEnumConstants();
    for (Field field : fields) {
      String fieldName = field.getName();
      if (!field.getType().isEnum() && !"ENUM$VALUES".equals(fieldName) && !"ordinal".equals(fieldName))
        for (Enum<?> enumObj : arrayOfEnum) {
          if (ObjectUtil.equal(value, ReflectUtil.getFieldValue(enumObj, field)))
            return (E)enumObj; 
        }  
    } 
    return null;
  }
  
  public static List<String> getNames(Class<? extends Enum<?>> clazz) {
    Enum[] arrayOfEnum = (Enum[])clazz.getEnumConstants();
    if (null == arrayOfEnum)
      return null; 
    List<String> list = new ArrayList<>(arrayOfEnum.length);
    for (Enum<?> e : arrayOfEnum)
      list.add(e.name()); 
    return list;
  }
  
  public static List<Object> getFieldValues(Class<? extends Enum<?>> clazz, String fieldName) {
    Enum[] arrayOfEnum = (Enum[])clazz.getEnumConstants();
    if (null == arrayOfEnum)
      return null; 
    List<Object> list = new ArrayList(arrayOfEnum.length);
    for (Enum<?> e : arrayOfEnum)
      list.add(ReflectUtil.getFieldValue(e, fieldName)); 
    return list;
  }
  
  public static List<String> getFieldNames(Class<? extends Enum<?>> clazz) {
    List<String> names = new ArrayList<>();
    Field[] fields = ReflectUtil.getFields(clazz);
    for (Field field : fields) {
      String name = field.getName();
      if (!field.getType().isEnum() && !name.contains("$VALUES") && !"ordinal".equals(name))
        if (false == names.contains(name))
          names.add(name);  
    } 
    return names;
  }
  
  public static <E extends Enum<E>> E getBy(Class<E> enumClass, Predicate<? super E> predicate) {
    return (E)Arrays.<Enum>stream((Enum[])enumClass.getEnumConstants())
      .filter(predicate).findFirst().orElse(null);
  }
  
  public static <E extends Enum<E>, C> E getBy(Func1<E, C> condition, C value) {
    Class<E> implClass = LambdaUtil.getRealClass(condition);
    if (Enum.class.equals(implClass))
      implClass = LambdaUtil.getRealClass(condition); 
    return (E)Arrays.<Enum>stream((Enum[])implClass.getEnumConstants()).filter(e -> condition.callWithRuntimeException(e).equals(value)).findAny().orElse(null);
  }
  
  public static <E extends Enum<E>, C> E getBy(Func1<E, C> condition, C value, E defaultEnum) {
    return (E)ObjectUtil.<Enum>defaultIfNull(getBy(condition, value), (Enum)defaultEnum);
  }
  
  public static <E extends Enum<E>, F, C> F getFieldBy(Func1<E, F> field, Function<E, C> condition, C value) {
    Class<E> implClass = LambdaUtil.getRealClass(field);
    if (Enum.class.equals(implClass))
      implClass = LambdaUtil.getRealClass(field); 
    return Arrays.stream((Object[])implClass.getEnumConstants())
      
      .filter(e -> condition.apply(e).equals(value))
      
      .findFirst().map(field::callWithRuntimeException).orElse(null);
  }
  
  public static <E extends Enum<E>> LinkedHashMap<String, E> getEnumMap(Class<E> enumClass) {
    LinkedHashMap<String, E> map = new LinkedHashMap<>();
    for (Enum enum_ : (Enum[])enumClass.getEnumConstants())
      map.put(enum_.name(), (E)enum_); 
    return map;
  }
  
  public static Map<String, Object> getNameFieldMap(Class<? extends Enum<?>> clazz, String fieldName) {
    Enum[] arrayOfEnum = (Enum[])clazz.getEnumConstants();
    if (null == arrayOfEnum)
      return null; 
    Map<String, Object> map = MapUtil.newHashMap(arrayOfEnum.length, true);
    for (Enum<?> e : arrayOfEnum)
      map.put(e.name(), ReflectUtil.getFieldValue(e, fieldName)); 
    return map;
  }
  
  public static <E extends Enum<E>> boolean contains(Class<E> enumClass, String val) {
    return getEnumMap(enumClass).containsKey(val);
  }
  
  public static <E extends Enum<E>> boolean notContains(Class<E> enumClass, String val) {
    return (false == contains(enumClass, val));
  }
  
  public static boolean equalsIgnoreCase(Enum<?> e, String val) {
    return StrUtil.equalsIgnoreCase(toString(e), val);
  }
  
  public static boolean equals(Enum<?> e, String val) {
    return StrUtil.equals(toString(e), val);
  }
}
