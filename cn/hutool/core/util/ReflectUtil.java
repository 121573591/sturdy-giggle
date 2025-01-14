package cn.hutool.core.util;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.UniqueKeySet;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.InvocationTargetRuntimeException;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.reflect.MethodHandleUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.map.WeakConcurrentMap;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectUtil {
  private static final WeakConcurrentMap<Class<?>, Constructor<?>[]> CONSTRUCTORS_CACHE = new WeakConcurrentMap();
  
  private static final WeakConcurrentMap<Class<?>, Field[]> FIELDS_CACHE = new WeakConcurrentMap();
  
  private static final WeakConcurrentMap<Class<?>, Method[]> METHODS_CACHE = new WeakConcurrentMap();
  
  public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
    if (null == clazz)
      return null; 
    Constructor[] arrayOfConstructor = (Constructor[])getConstructors(clazz);
    for (Constructor<?> constructor : arrayOfConstructor) {
      Class<?>[] pts = constructor.getParameterTypes();
      if (ClassUtil.isAllAssignableFrom(pts, parameterTypes)) {
        setAccessible(constructor);
        return (Constructor)constructor;
      } 
    } 
    return null;
  }
  
  public static <T> Constructor<T>[] getConstructors(Class<T> beanClass) throws SecurityException {
    Assert.notNull(beanClass);
    return (Constructor<T>[])CONSTRUCTORS_CACHE.computeIfAbsent(beanClass, () -> getConstructorsDirectly(beanClass));
  }
  
  public static Constructor<?>[] getConstructorsDirectly(Class<?> beanClass) throws SecurityException {
    return beanClass.getDeclaredConstructors();
  }
  
  public static boolean hasField(Class<?> beanClass, String name) throws SecurityException {
    return (null != getField(beanClass, name));
  }
  
  public static String getFieldName(Field field) {
    if (null == field)
      return null; 
    Alias alias = field.<Alias>getAnnotation(Alias.class);
    if (null != alias)
      return alias.value(); 
    return field.getName();
  }
  
  public static Field getField(Class<?> beanClass, String name) throws SecurityException {
    Field[] fields = getFields(beanClass);
    return ArrayUtil.<Field>firstMatch(field -> name.equals(getFieldName(field)), fields);
  }
  
  public static Map<String, Field> getFieldMap(Class<?> beanClass) {
    Field[] fields = getFields(beanClass);
    HashMap<String, Field> map = MapUtil.newHashMap(fields.length, true);
    for (Field field : fields)
      map.put(field.getName(), field); 
    return map;
  }
  
  public static Field[] getFields(Class<?> beanClass) throws SecurityException {
    Assert.notNull(beanClass);
    return (Field[])FIELDS_CACHE.computeIfAbsent(beanClass, () -> getFieldsDirectly(beanClass, true));
  }
  
  public static Field[] getFields(Class<?> beanClass, Filter<Field> fieldFilter) throws SecurityException {
    return ArrayUtil.<Field>filter(getFields(beanClass), fieldFilter);
  }
  
  public static Field[] getFieldsDirectly(Class<?> beanClass, boolean withSuperClassFields) throws SecurityException {
    Assert.notNull(beanClass);
    Field[] allFields = null;
    Class<?> searchType = beanClass;
    while (searchType != null) {
      Field[] declaredFields = searchType.getDeclaredFields();
      if (null == allFields) {
        allFields = declaredFields;
      } else {
        allFields = ArrayUtil.<Field>append(allFields, declaredFields);
      } 
      searchType = withSuperClassFields ? searchType.getSuperclass() : null;
    } 
    return allFields;
  }
  
  public static Object getFieldValue(Object obj, String fieldName) throws UtilException {
    if (null == obj || StrUtil.isBlank(fieldName))
      return null; 
    return getFieldValue(obj, getField((obj instanceof Class) ? (Class)obj : obj.getClass(), fieldName));
  }
  
  public static Object getStaticFieldValue(Field field) throws UtilException {
    return getFieldValue((Object)null, field);
  }
  
  public static Object getFieldValue(Object obj, Field field) throws UtilException {
    Object result;
    if (null == field)
      return null; 
    if (obj instanceof Class)
      obj = null; 
    setAccessible(field);
    try {
      result = field.get(obj);
    } catch (IllegalAccessException e) {
      throw new UtilException(e, "IllegalAccess for {}.{}", new Object[] { field.getDeclaringClass(), field.getName() });
    } 
    return result;
  }
  
  public static Object[] getFieldsValue(Object obj) {
    return getFieldsValue(obj, null);
  }
  
  public static Object[] getFieldsValue(Object obj, Filter<Field> filter) {
    if (null != obj) {
      Field[] fields = getFields((obj instanceof Class) ? (Class)obj : obj.getClass(), filter);
      if (null != fields)
        return ArrayUtil.map(fields, Object.class, field -> getFieldValue(obj, field)); 
    } 
    return null;
  }
  
  public static void setFieldValue(Object obj, String fieldName, Object value) throws UtilException {
    Assert.notNull(obj);
    Assert.notBlank(fieldName);
    Field field = getField((obj instanceof Class) ? (Class)obj : obj.getClass(), fieldName);
    Assert.notNull(field, "Field [{}] is not exist in [{}]", new Object[] { fieldName, obj.getClass().getName() });
    setFieldValue(obj, field, value);
  }
  
  public static void setFieldValue(Object obj, Field field, Object value) throws UtilException {
    Assert.notNull(field, "Field in [{}] not exist !", new Object[] { obj });
    Class<?> fieldType = field.getType();
    if (null != value) {
      if (false == fieldType.isAssignableFrom(value.getClass())) {
        Object targetValue = Convert.convert(fieldType, value);
        if (null != targetValue)
          value = targetValue; 
      } 
    } else {
      value = ClassUtil.getDefaultValue(fieldType);
    } 
    setAccessible(field);
    try {
      field.set((obj instanceof Class) ? null : obj, value);
    } catch (IllegalAccessException e) {
      throw new UtilException(e, "IllegalAccess for {}.{}", new Object[] { obj, field.getName() });
    } 
  }
  
  public static boolean isOuterClassField(Field field) {
    return "this$0".equals(field.getName());
  }
  
  public static Set<String> getPublicMethodNames(Class<?> clazz) {
    HashSet<String> methodSet = new HashSet<>();
    Method[] methodArray = getPublicMethods(clazz);
    if (ArrayUtil.isNotEmpty(methodArray))
      for (Method method : methodArray)
        methodSet.add(method.getName());  
    return methodSet;
  }
  
  public static Method[] getPublicMethods(Class<?> clazz) {
    return (null == clazz) ? null : clazz.getMethods();
  }
  
  public static List<Method> getPublicMethods(Class<?> clazz, Filter<Method> filter) {
    List<Method> methodList;
    if (null == clazz)
      return null; 
    Method[] methods = getPublicMethods(clazz);
    if (null != filter) {
      methodList = new ArrayList<>();
      for (Method method : methods) {
        if (filter.accept(method))
          methodList.add(method); 
      } 
    } else {
      methodList = CollUtil.newArrayList((Object[])methods);
    } 
    return methodList;
  }
  
  public static List<Method> getPublicMethods(Class<?> clazz, Method... excludeMethods) {
    HashSet<Method> excludeMethodSet = CollUtil.newHashSet((Object[])excludeMethods);
    return getPublicMethods(clazz, method -> (false == excludeMethodSet.contains(method)));
  }
  
  public static List<Method> getPublicMethods(Class<?> clazz, String... excludeMethodNames) {
    HashSet<String> excludeMethodNameSet = CollUtil.newHashSet((Object[])excludeMethodNames);
    return getPublicMethods(clazz, method -> (false == excludeMethodNameSet.contains(method.getName())));
  }
  
  public static Method getPublicMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) throws SecurityException {
    try {
      return clazz.getMethod(methodName, paramTypes);
    } catch (NoSuchMethodException ex) {
      return null;
    } 
  }
  
  public static Method getMethodOfObj(Object obj, String methodName, Object... args) throws SecurityException {
    if (null == obj || StrUtil.isBlank(methodName))
      return null; 
    return getMethod(obj.getClass(), methodName, ClassUtil.getClasses(args));
  }
  
  public static Method getMethodIgnoreCase(Class<?> clazz, String methodName, Class<?>... paramTypes) throws SecurityException {
    return getMethod(clazz, true, methodName, paramTypes);
  }
  
  public static Method getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) throws SecurityException {
    return getMethod(clazz, false, methodName, paramTypes);
  }
  
  public static Method getMethod(Class<?> clazz, boolean ignoreCase, String methodName, Class<?>... paramTypes) throws SecurityException {
    if (null == clazz || StrUtil.isBlank(methodName))
      return null; 
    Method res = null;
    Method[] methods = getMethods(clazz);
    if (ArrayUtil.isNotEmpty(methods))
      for (Method method : methods) {
        if (StrUtil.equals(methodName, method.getName(), ignoreCase) && 
          ClassUtil.isAllAssignableFrom(method.getParameterTypes(), paramTypes) && (res == null || res
          
          .getReturnType().isAssignableFrom(method.getReturnType())))
          res = method; 
      }  
    return res;
  }
  
  public static Method getMethodByName(Class<?> clazz, String methodName) throws SecurityException {
    return getMethodByName(clazz, false, methodName);
  }
  
  public static Method getMethodByNameIgnoreCase(Class<?> clazz, String methodName) throws SecurityException {
    return getMethodByName(clazz, true, methodName);
  }
  
  public static Method getMethodByName(Class<?> clazz, boolean ignoreCase, String methodName) throws SecurityException {
    if (null == clazz || StrUtil.isBlank(methodName))
      return null; 
    Method res = null;
    Method[] methods = getMethods(clazz);
    if (ArrayUtil.isNotEmpty(methods))
      for (Method method : methods) {
        if (StrUtil.equals(methodName, method.getName(), ignoreCase) && (res == null || res
          
          .getReturnType().isAssignableFrom(method.getReturnType())))
          res = method; 
      }  
    return res;
  }
  
  public static Set<String> getMethodNames(Class<?> clazz) throws SecurityException {
    HashSet<String> methodSet = new HashSet<>();
    Method[] methods = getMethods(clazz);
    for (Method method : methods)
      methodSet.add(method.getName()); 
    return methodSet;
  }
  
  public static Method[] getMethods(Class<?> clazz, Filter<Method> filter) throws SecurityException {
    if (null == clazz)
      return null; 
    return ArrayUtil.<Method>filter(getMethods(clazz), filter);
  }
  
  public static Method[] getMethods(Class<?> beanClass) throws SecurityException {
    Assert.notNull(beanClass);
    return (Method[])METHODS_CACHE.computeIfAbsent(beanClass, () -> getMethodsDirectly(beanClass, true, true));
  }
  
  public static Method[] getMethodsDirectly(Class<?> beanClass, boolean withSupers, boolean withMethodFromObject) throws SecurityException {
    Assert.notNull(beanClass);
    if (beanClass.isInterface())
      return withSupers ? beanClass.getMethods() : beanClass.getDeclaredMethods(); 
    UniqueKeySet<String, Method> result = new UniqueKeySet(true, ReflectUtil::getUniqueKey);
    Class<?> searchType = beanClass;
    while (searchType != null && (false != 
      withMethodFromObject || Object.class != searchType)) {
      result.addAllIfAbsent(Arrays.asList(searchType.getDeclaredMethods()));
      result.addAllIfAbsent(getDefaultMethodsFromInterface(searchType));
      searchType = (withSupers && false == searchType.isInterface()) ? searchType.getSuperclass() : null;
    } 
    return (Method[])result.toArray((Object[])new Method[0]);
  }
  
  public static boolean isEqualsMethod(Method method) {
    if (method == null || 1 != method
      .getParameterCount() || false == "equals"
      .equals(method.getName()))
      return false; 
    return (method.getParameterTypes()[0] == Object.class);
  }
  
  public static boolean isHashCodeMethod(Method method) {
    return (method != null && "hashCode"
      .equals(method.getName()) && 
      isEmptyParam(method));
  }
  
  public static boolean isToStringMethod(Method method) {
    return (method != null && "toString"
      .equals(method.getName()) && 
      isEmptyParam(method));
  }
  
  public static boolean isEmptyParam(Method method) {
    return (method.getParameterCount() == 0);
  }
  
  public static boolean isGetterOrSetterIgnoreCase(Method method) {
    return isGetterOrSetter(method, true);
  }
  
  public static boolean isGetterOrSetter(Method method, boolean ignoreCase) {
    if (null == method)
      return false; 
    int parameterCount = method.getParameterCount();
    if (parameterCount > 1)
      return false; 
    String name = method.getName();
    if ("getClass".equals(name))
      return false; 
    if (ignoreCase)
      name = name.toLowerCase(); 
    switch (parameterCount) {
      case 0:
        return (name.startsWith("get") || name.startsWith("is"));
      case 1:
        return name.startsWith("set");
    } 
    return false;
  }
  
  public static <T> T newInstance(String clazz) throws UtilException {
    try {
      return (T)Class.forName(clazz).newInstance();
    } catch (Exception e) {
      throw new UtilException(e, "Instance class [{}] error!", new Object[] { clazz });
    } 
  }
  
  public static <T> T newInstance(Class<T> clazz, Object... params) throws UtilException {
    if (ArrayUtil.isEmpty(params)) {
      Constructor<T> constructor1 = getConstructor(clazz, new Class[0]);
      if (null == constructor1)
        throw new UtilException("No constructor for [{}]", new Object[] { clazz }); 
      try {
        return constructor1.newInstance(new Object[0]);
      } catch (Exception e) {
        throw new UtilException(e, "Instance class [{}] error!", new Object[] { clazz });
      } 
    } 
    Class<?>[] paramTypes = ClassUtil.getClasses(params);
    Constructor<T> constructor = getConstructor(clazz, paramTypes);
    if (null == constructor)
      throw new UtilException("No Constructor matched for parameter types: [{}]", new Object[] { paramTypes }); 
    try {
      return constructor.newInstance(params);
    } catch (Exception e) {
      throw new UtilException(e, "Instance class [{}] error!", new Object[] { clazz });
    } 
  }
  
  public static <T> T newInstanceIfPossible(Class<T> type) {
    Class<HashMap> clazz1;
    Class<HashSet> clazz;
    Assert.notNull(type);
    if (type.isPrimitive())
      return (T)ClassUtil.getPrimitiveDefaultValue(type); 
    if (type.isAssignableFrom(AbstractMap.class)) {
      clazz1 = HashMap.class;
    } else {
      Class<ArrayList> clazz2;
      if (clazz1.isAssignableFrom(List.class)) {
        clazz2 = ArrayList.class;
      } else if (clazz2.isAssignableFrom(Set.class)) {
        clazz = HashSet.class;
      } 
    } 
    try {
      return newInstance((Class)clazz, new Object[0]);
    } catch (Exception exception) {
      if (clazz.isEnum())
        return clazz.getEnumConstants()[0]; 
      if (clazz.isArray())
        return (T)Array.newInstance(clazz.getComponentType(), 0); 
      Constructor[] arrayOfConstructor = (Constructor[])getConstructors(clazz);
      for (Constructor<T> constructor : arrayOfConstructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        if (0 != parameterTypes.length) {
          setAccessible(constructor);
          try {
            return constructor.newInstance(ClassUtil.getDefaultValues(parameterTypes));
          } catch (Exception exception1) {}
        } 
      } 
      return null;
    } 
  }
  
  public static <T> T invokeStatic(Method method, Object... args) throws UtilException {
    return invoke((Object)null, method, args);
  }
  
  public static <T> T invokeWithCheck(Object obj, Method method, Object... args) throws UtilException {
    Class<?>[] types = method.getParameterTypes();
    if (null != args) {
      Assert.isTrue((args.length == types.length), "Params length [{}] is not fit for param length [{}] of method !", new Object[] { Integer.valueOf(args.length), Integer.valueOf(types.length) });
      for (int i = 0; i < args.length; i++) {
        Class<?> type = types[i];
        if (type.isPrimitive() && null == args[i])
          args[i] = ClassUtil.getDefaultValue(type); 
      } 
    } 
    return invoke(obj, method, args);
  }
  
  public static <T> T invoke(Object obj, Method method, Object... args) throws InvocationTargetRuntimeException, UtilException {
    try {
      return invokeRaw(obj, method, args);
    } catch (InvocationTargetException e) {
      throw new InvocationTargetRuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new UtilException(e);
    } 
  }
  
  public static <T> T invokeRaw(Object obj, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
    setAccessible(method);
    Class<?>[] parameterTypes = method.getParameterTypes();
    Object[] actualArgs = new Object[parameterTypes.length];
    if (null != args)
      for (int i = 0; i < actualArgs.length; i++) {
        if (i >= args.length || null == args[i]) {
          actualArgs[i] = ClassUtil.getDefaultValue(parameterTypes[i]);
        } else if (args[i] instanceof cn.hutool.core.bean.NullWrapperBean) {
          actualArgs[i] = null;
        } else if (false == parameterTypes[i].isAssignableFrom(args[i].getClass())) {
          Object targetValue = Convert.convertWithCheck(parameterTypes[i], args[i], null, true);
          if (null != targetValue) {
            actualArgs[i] = targetValue;
          } else {
            actualArgs[i] = args[i];
          } 
        } else {
          actualArgs[i] = args[i];
        } 
      }  
    if (method.isDefault())
      return (T)MethodHandleUtil.invokeSpecial(obj, method, args); 
    return (T)method.invoke(ClassUtil.isStatic(method) ? null : obj, actualArgs);
  }
  
  public static <T> T invoke(Object obj, String methodName, Object... args) throws UtilException {
    Assert.notNull(obj, "Object to get method must be not null!", new Object[0]);
    Assert.notBlank(methodName, "Method name must be not blank!", new Object[0]);
    Method method = getMethodOfObj(obj, methodName, args);
    if (null == method)
      throw new UtilException("No such method: [{}] from [{}]", new Object[] { methodName, obj.getClass() }); 
    return invoke(obj, method, args);
  }
  
  public static <T extends java.lang.reflect.AccessibleObject> T setAccessible(T accessibleObject) {
    if (null != accessibleObject && false == accessibleObject.isAccessible())
      accessibleObject.setAccessible(true); 
    return accessibleObject;
  }
  
  public static void removeFinalModify(Field field) {
    ModifierUtil.removeFinalModify(field);
  }
  
  private static String getUniqueKey(Method method) {
    StringBuilder sb = new StringBuilder();
    sb.append(method.getReturnType().getName()).append('#');
    sb.append(method.getName());
    Class<?>[] parameters = method.getParameterTypes();
    for (int i = 0; i < parameters.length; i++) {
      if (i == 0) {
        sb.append(':');
      } else {
        sb.append(',');
      } 
      sb.append(parameters[i].getName());
    } 
    return sb.toString();
  }
  
  private static List<Method> getDefaultMethodsFromInterface(Class<?> clazz) {
    List<Method> result = new ArrayList<>();
    for (Class<?> ifc : clazz.getInterfaces()) {
      for (Method m : ifc.getMethods()) {
        if (false == ModifierUtil.isAbstract(m))
          result.add(m); 
      } 
    } 
    return result;
  }
}
