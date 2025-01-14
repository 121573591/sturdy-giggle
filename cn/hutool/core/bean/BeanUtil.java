package cn.hutool.core.bean;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.bean.copier.ValueProvider;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BeanUtil {
  public static boolean isReadableBean(Class<?> clazz) {
    return (hasGetter(clazz) || hasPublicField(clazz));
  }
  
  public static boolean isBean(Class<?> clazz) {
    return (hasSetter(clazz) || hasPublicField(clazz));
  }
  
  public static boolean hasSetter(Class<?> clazz) {
    if (ClassUtil.isNormalClass(clazz))
      for (Method method : clazz.getMethods()) {
        if (method.getParameterCount() == 1 && method.getName().startsWith("set"))
          return true; 
      }  
    return false;
  }
  
  public static boolean hasGetter(Class<?> clazz) {
    if (ClassUtil.isNormalClass(clazz))
      for (Method method : clazz.getMethods()) {
        if (method.getParameterCount() == 0) {
          String name = method.getName();
          if ((name.startsWith("get") || name.startsWith("is")) && false == 
            "getClass".equals(name))
            return true; 
        } 
      }  
    return false;
  }
  
  public static boolean hasPublicField(Class<?> clazz) {
    if (ClassUtil.isNormalClass(clazz))
      for (Field field : clazz.getFields()) {
        if (ModifierUtil.isPublic(field) && false == ModifierUtil.isStatic(field))
          return true; 
      }  
    return false;
  }
  
  public static DynaBean createDynaBean(Object bean) {
    return new DynaBean(bean);
  }
  
  public static PropertyEditor findEditor(Class<?> type) {
    return PropertyEditorManager.findEditor(type);
  }
  
  public static BeanDesc getBeanDesc(Class<?> clazz) {
    return BeanDescCache.INSTANCE.getBeanDesc(clazz, () -> new BeanDesc(clazz));
  }
  
  public static void descForEach(Class<?> clazz, Consumer<? super PropDesc> action) {
    getBeanDesc(clazz).getProps().forEach(action);
  }
  
  public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws BeanException {
    BeanInfo beanInfo;
    try {
      beanInfo = Introspector.getBeanInfo(clazz);
    } catch (IntrospectionException e) {
      throw new BeanException(e);
    } 
    return (PropertyDescriptor[])ArrayUtil.filter((Object[])beanInfo.getPropertyDescriptors(), t -> (false == "class".equals(t.getName())));
  }
  
  public static Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
    return BeanInfoCache.INSTANCE.getPropertyDescriptorMap(clazz, ignoreCase, () -> internalGetPropertyDescriptorMap(clazz, ignoreCase));
  }
  
  private static Map<String, PropertyDescriptor> internalGetPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
    PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
    Map<String, PropertyDescriptor> map = ignoreCase ? (Map<String, PropertyDescriptor>)new CaseInsensitiveMap(propertyDescriptors.length, 1.0F) : new HashMap<>(propertyDescriptors.length, 1.0F);
    for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
      map.put(propertyDescriptor.getName(), propertyDescriptor); 
    return map;
  }
  
  public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String fieldName) throws BeanException {
    return getPropertyDescriptor(clazz, fieldName, false);
  }
  
  public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String fieldName, boolean ignoreCase) throws BeanException {
    Map<String, PropertyDescriptor> map = getPropertyDescriptorMap(clazz, ignoreCase);
    return (null == map) ? null : map.get(fieldName);
  }
  
  public static Object getFieldValue(Object bean, String fieldNameOrIndex) {
    if (null == bean || null == fieldNameOrIndex)
      return null; 
    if (bean instanceof Map)
      return ((Map)bean).get(fieldNameOrIndex); 
    if (bean instanceof Collection)
      try {
        return CollUtil.get((Collection)bean, Integer.parseInt(fieldNameOrIndex));
      } catch (NumberFormatException e) {
        return CollUtil.map((Collection)bean, beanEle -> getFieldValue(beanEle, fieldNameOrIndex), false);
      }  
    if (ArrayUtil.isArray(bean))
      try {
        return ArrayUtil.get(bean, Integer.parseInt(fieldNameOrIndex));
      } catch (NumberFormatException e) {
        return ArrayUtil.map(bean, Object.class, beanEle -> getFieldValue(beanEle, fieldNameOrIndex));
      }  
    return ReflectUtil.getFieldValue(bean, fieldNameOrIndex);
  }
  
  public static Object setFieldValue(Object bean, String fieldNameOrIndex, Object value) {
    if (bean instanceof Map) {
      ((Map<String, Object>)bean).put(fieldNameOrIndex, value);
    } else if (bean instanceof List) {
      ListUtil.setOrPadding((List)bean, Convert.toInt(fieldNameOrIndex).intValue(), value);
    } else {
      if (ArrayUtil.isArray(bean))
        return ArrayUtil.setOrAppend(bean, Convert.toInt(fieldNameOrIndex).intValue(), value); 
      ReflectUtil.setFieldValue(bean, fieldNameOrIndex, value);
    } 
    return bean;
  }
  
  public static <T> T getProperty(Object bean, String expression) {
    if (null == bean || StrUtil.isBlank(expression))
      return null; 
    return (T)BeanPath.create(expression).get(bean);
  }
  
  public static void setProperty(Object bean, String expression, Object value) {
    BeanPath.create(expression).set(bean, value);
  }
  
  @Deprecated
  public static <T> T mapToBean(Map<?, ?> map, Class<T> beanClass, boolean isIgnoreError) {
    return fillBeanWithMap(map, (T)ReflectUtil.newInstanceIfPossible(beanClass), isIgnoreError);
  }
  
  @Deprecated
  public static <T> T mapToBeanIgnoreCase(Map<?, ?> map, Class<T> beanClass, boolean isIgnoreError) {
    return fillBeanWithMapIgnoreCase(map, (T)ReflectUtil.newInstanceIfPossible(beanClass), isIgnoreError);
  }
  
  @Deprecated
  public static <T> T mapToBean(Map<?, ?> map, Class<T> beanClass, CopyOptions copyOptions) {
    return fillBeanWithMap(map, (T)ReflectUtil.newInstanceIfPossible(beanClass), copyOptions);
  }
  
  @Deprecated
  public static <T> T mapToBean(Map<?, ?> map, Class<T> beanClass, boolean isToCamelCase, CopyOptions copyOptions) {
    return fillBeanWithMap(map, (T)ReflectUtil.newInstanceIfPossible(beanClass), isToCamelCase, copyOptions);
  }
  
  public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isIgnoreError) {
    return fillBeanWithMap(map, bean, false, isIgnoreError);
  }
  
  @Deprecated
  public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase, boolean isIgnoreError) {
    return fillBeanWithMap(map, bean, isToCamelCase, CopyOptions.create().setIgnoreError(isIgnoreError));
  }
  
  public static <T> T fillBeanWithMapIgnoreCase(Map<?, ?> map, T bean, boolean isIgnoreError) {
    return fillBeanWithMap(map, bean, CopyOptions.create().setIgnoreCase(true).setIgnoreError(isIgnoreError));
  }
  
  public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, CopyOptions copyOptions) {
    if (MapUtil.isEmpty(map))
      return bean; 
    copyProperties(map, bean, copyOptions);
    return bean;
  }
  
  @Deprecated
  public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase, CopyOptions copyOptions) {
    if (MapUtil.isEmpty(map))
      return bean; 
    copyProperties(map, bean, copyOptions);
    return bean;
  }
  
  public static <T> T toBean(Object source, Class<T> clazz) {
    return toBean(source, clazz, (CopyOptions)null);
  }
  
  public static <T> T toBeanIgnoreError(Object source, Class<T> clazz) {
    return toBean(source, clazz, CopyOptions.create().setIgnoreError(true));
  }
  
  public static <T> T toBeanIgnoreCase(Object source, Class<T> clazz, boolean ignoreError) {
    return toBean(source, clazz, 
        CopyOptions.create()
        .setIgnoreCase(true)
        .setIgnoreError(ignoreError));
  }
  
  public static <T> T toBean(Object source, Class<T> clazz, CopyOptions options) {
    return toBean(source, () -> ReflectUtil.newInstanceIfPossible(clazz), options);
  }
  
  public static <T> T toBean(Object source, Supplier<T> targetSupplier, CopyOptions options) {
    if (null == source || null == targetSupplier)
      return null; 
    T target = targetSupplier.get();
    copyProperties(source, target, options);
    return target;
  }
  
  public static <T> T toBean(Class<T> beanClass, ValueProvider<String> valueProvider, CopyOptions copyOptions) {
    if (null == beanClass || null == valueProvider)
      return null; 
    return fillBean((T)ReflectUtil.newInstanceIfPossible(beanClass), valueProvider, copyOptions);
  }
  
  public static <T> T fillBean(T bean, ValueProvider<String> valueProvider, CopyOptions copyOptions) {
    if (null == valueProvider)
      return bean; 
    return (T)BeanCopier.create(valueProvider, bean, copyOptions).copy();
  }
  
  public static Map<String, Object> beanToMap(Object bean, String... properties) {
    int mapSize = 16;
    Editor<String> keyEditor = null;
    if (ArrayUtil.isNotEmpty((Object[])properties)) {
      mapSize = properties.length;
      Set<String> propertiesSet = CollUtil.set(false, (Object[])properties);
      keyEditor = (property -> propertiesSet.contains(property) ? property : null);
    } 
    return beanToMap(bean, new LinkedHashMap<>(mapSize, 1.0F), false, keyEditor);
  }
  
  public static Map<String, Object> beanToMap(Object bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
    if (null == bean)
      return null; 
    return beanToMap(bean, new LinkedHashMap<>(), isToUnderlineCase, ignoreNullValue);
  }
  
  public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, boolean isToUnderlineCase, boolean ignoreNullValue) {
    if (null == bean)
      return null; 
    return beanToMap(bean, targetMap, ignoreNullValue, key -> isToUnderlineCase ? StrUtil.toUnderlineCase(key) : key);
  }
  
  public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, boolean ignoreNullValue, Editor<String> keyEditor) {
    if (null == bean)
      return null; 
    return (Map<String, Object>)BeanCopier.create(bean, targetMap, 
        CopyOptions.create()
        .setIgnoreNullValue(ignoreNullValue)
        .setFieldNameEditor(keyEditor))
      .copy();
  }
  
  public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, CopyOptions copyOptions) {
    if (null == bean)
      return null; 
    return (Map<String, Object>)BeanCopier.create(bean, targetMap, copyOptions).copy();
  }
  
  public static <T> T copyProperties(Object source, Class<T> tClass, String... ignoreProperties) {
    if (null == source)
      return null; 
    T target = (T)ReflectUtil.newInstanceIfPossible(tClass);
    copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
    return target;
  }
  
  public static void copyProperties(Object source, Object target, String... ignoreProperties) {
    copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
  }
  
  public static void copyProperties(Object source, Object target, boolean ignoreCase) {
    BeanCopier.create(source, target, CopyOptions.create().setIgnoreCase(ignoreCase)).copy();
  }
  
  public static void copyProperties(Object source, Object target, CopyOptions copyOptions) {
    if (null == source)
      return; 
    BeanCopier.create(source, target, (CopyOptions)ObjectUtil.defaultIfNull(copyOptions, CopyOptions::create)).copy();
  }
  
  public static <T> List<T> copyToList(Collection<?> collection, Class<T> targetType, CopyOptions copyOptions) {
    if (null == collection)
      return null; 
    if (collection.isEmpty())
      return new ArrayList<>(0); 
    if (ClassUtil.isBasicType(targetType) || String.class == targetType)
      return Convert.toList(targetType, collection); 
    return (List<T>)collection.stream().map(source -> {
          T target = (T)ReflectUtil.newInstanceIfPossible(targetType);
          copyProperties(source, target, copyOptions);
          return (Function)target;
        }).collect(Collectors.toList());
  }
  
  public static <T> List<T> copyToList(Collection<?> collection, Class<T> targetType) {
    return copyToList(collection, targetType, CopyOptions.create());
  }
  
  public static boolean isMatchName(Object bean, String beanClassName, boolean isSimple) {
    if (null == bean || StrUtil.isBlank(beanClassName))
      return false; 
    return ClassUtil.getClassName(bean, isSimple).equals(isSimple ? StrUtil.upperFirst(beanClassName) : beanClassName);
  }
  
  public static <T> T edit(T bean, Editor<Field> editor) {
    if (bean == null)
      return null; 
    Field[] fields = ReflectUtil.getFields(bean.getClass());
    for (Field field : fields) {
      if (!ModifierUtil.isStatic(field))
        editor.edit(field); 
    } 
    return bean;
  }
  
  public static <T> T trimStrFields(T bean, String... ignoreFields) {
    return edit(bean, field -> {
          if (ignoreFields != null && ArrayUtil.containsIgnoreCase((CharSequence[])ignoreFields, field.getName()))
            return field; 
          if (String.class.equals(field.getType())) {
            String val = (String)ReflectUtil.getFieldValue(bean, field);
            if (null != val) {
              String trimVal = StrUtil.trim(val);
              if (false == val.equals(trimVal))
                ReflectUtil.setFieldValue(bean, field, trimVal); 
            } 
          } 
          return field;
        });
  }
  
  public static boolean isNotEmpty(Object bean, String... ignoreFieldNames) {
    return (false == isEmpty(bean, ignoreFieldNames));
  }
  
  public static boolean isEmpty(Object bean, String... ignoreFieldNames) {
    if (null != bean)
      for (Field field : ReflectUtil.getFields(bean.getClass())) {
        if (!ModifierUtil.isStatic(field))
          if (false == ArrayUtil.contains((Object[])ignoreFieldNames, field.getName()) && null != 
            ReflectUtil.getFieldValue(bean, field))
            return false;  
      }  
    return true;
  }
  
  public static boolean hasNullField(Object bean, String... ignoreFieldNames) {
    if (null == bean)
      return true; 
    for (Field field : ReflectUtil.getFields(bean.getClass())) {
      if (!ModifierUtil.isStatic(field))
        if (false == ArrayUtil.contains((Object[])ignoreFieldNames, field.getName()) && null == 
          ReflectUtil.getFieldValue(bean, field))
          return true;  
    } 
    return false;
  }
  
  public static String getFieldName(String getterOrSetterName) {
    if (getterOrSetterName.startsWith("get") || getterOrSetterName.startsWith("set"))
      return StrUtil.removePreAndLowerFirst(getterOrSetterName, 3); 
    if (getterOrSetterName.startsWith("is"))
      return StrUtil.removePreAndLowerFirst(getterOrSetterName, 2); 
    throw new IllegalArgumentException("Invalid Getter or Setter name: " + getterOrSetterName);
  }
  
  public static boolean isCommonFieldsEqual(Object source, Object target, String... ignoreProperties) {
    if (null == source && null == target)
      return true; 
    if (null == source || null == target)
      return false; 
    Map<String, Object> sourceFieldsMap = beanToMap(source, new String[0]);
    Map<String, Object> targetFieldsMap = beanToMap(target, new String[0]);
    Set<String> sourceFields = sourceFieldsMap.keySet();
    sourceFields.removeAll(Arrays.asList((Object[])ignoreProperties));
    for (String field : sourceFields) {
      if (sourceFieldsMap.containsKey(field) && targetFieldsMap.containsKey(field) && 
        ObjectUtil.notEqual(sourceFieldsMap.get(field), targetFieldsMap.get(field)))
        return false; 
    } 
    return true;
  }
}
