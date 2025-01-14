package cn.hutool.core.util;

import cn.hutool.core.lang.ParameterizedTypeImpl;
import cn.hutool.core.lang.reflect.ActualTypeMapperPool;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeUtil {
  public static Class<?> getClass(Type type) {
    if (null != type) {
      if (type instanceof Class)
        return (Class)type; 
      if (type instanceof ParameterizedType)
        return (Class)((ParameterizedType)type).getRawType(); 
      if (type instanceof TypeVariable) {
        Type[] bounds = ((TypeVariable)type).getBounds();
        if (bounds.length == 1)
          return getClass(bounds[0]); 
      } else if (type instanceof WildcardType) {
        Type[] upperBounds = ((WildcardType)type).getUpperBounds();
        if (upperBounds.length == 1)
          return getClass(upperBounds[0]); 
      } 
    } 
    return null;
  }
  
  public static Type getType(Field field) {
    if (null == field)
      return null; 
    return field.getGenericType();
  }
  
  public static Type getFieldType(Class<?> clazz, String fieldName) {
    return getType(ReflectUtil.getField(clazz, fieldName));
  }
  
  public static Class<?> getClass(Field field) {
    return (null == field) ? null : field.getType();
  }
  
  public static Type getFirstParamType(Method method) {
    return getParamType(method, 0);
  }
  
  public static Class<?> getFirstParamClass(Method method) {
    return getParamClass(method, 0);
  }
  
  public static Type getParamType(Method method, int index) {
    Type[] types = getParamTypes(method);
    if (null != types && types.length > index)
      return types[index]; 
    return null;
  }
  
  public static Class<?> getParamClass(Method method, int index) {
    Class<?>[] classes = getParamClasses(method);
    if (null != classes && classes.length > index)
      return classes[index]; 
    return null;
  }
  
  public static Type[] getParamTypes(Method method) {
    return (null == method) ? null : method.getGenericParameterTypes();
  }
  
  public static Class<?>[] getParamClasses(Method method) {
    return (null == method) ? null : method.getParameterTypes();
  }
  
  public static Type getReturnType(Method method) {
    return (null == method) ? null : method.getGenericReturnType();
  }
  
  public static Class<?> getReturnClass(Method method) {
    return (null == method) ? null : method.getReturnType();
  }
  
  public static Type getTypeArgument(Type type) {
    return getTypeArgument(type, 0);
  }
  
  public static Type getTypeArgument(Type type, int index) {
    Type[] typeArguments = getTypeArguments(type);
    if (null != typeArguments && typeArguments.length > index)
      return typeArguments[index]; 
    return null;
  }
  
  public static Type[] getTypeArguments(Type type) {
    if (null == type)
      return null; 
    ParameterizedType parameterizedType = toParameterizedType(type);
    return (null == parameterizedType) ? null : parameterizedType.getActualTypeArguments();
  }
  
  public static ParameterizedType toParameterizedType(Type type) {
    return toParameterizedType(type, 0);
  }
  
  public static ParameterizedType toParameterizedType(Type type, int interfaceIndex) {
    if (type instanceof ParameterizedType)
      return (ParameterizedType)type; 
    if (type instanceof Class) {
      ParameterizedType[] generics = getGenerics((Class)type);
      if (generics.length > interfaceIndex)
        return generics[interfaceIndex]; 
    } 
    return null;
  }
  
  public static ParameterizedType[] getGenerics(Class<?> clazz) {
    List<ParameterizedType> result = new ArrayList<>();
    Type genericSuper = clazz.getGenericSuperclass();
    if (null != genericSuper && !Object.class.equals(genericSuper)) {
      ParameterizedType parameterizedType = toParameterizedType(genericSuper);
      if (null != parameterizedType)
        result.add(parameterizedType); 
    } 
    Type[] genericInterfaces = clazz.getGenericInterfaces();
    if (ArrayUtil.isNotEmpty(genericInterfaces))
      for (Type genericInterface : genericInterfaces) {
        if (genericInterface instanceof ParameterizedType)
          result.add((ParameterizedType)genericInterface); 
      }  
    return result.<ParameterizedType>toArray(new ParameterizedType[0]);
  }
  
  public static boolean isUnknown(Type type) {
    return (null == type || type instanceof TypeVariable);
  }
  
  public static boolean hasTypeVariable(Type... types) {
    for (Type type : types) {
      if (type instanceof TypeVariable)
        return true; 
    } 
    return false;
  }
  
  public static Map<Type, Type> getTypeMap(Class<?> clazz) {
    return ActualTypeMapperPool.get(clazz);
  }
  
  public static Type getActualType(Type type, Field field) {
    if (null == field)
      return null; 
    return getActualType(ObjectUtil.<Type>defaultIfNull(type, field.getDeclaringClass()), field.getGenericType());
  }
  
  public static Type getActualType(Type type, Type typeVariable) {
    if (typeVariable instanceof ParameterizedType)
      return getActualType(type, (ParameterizedType)typeVariable); 
    if (typeVariable instanceof TypeVariable)
      return ActualTypeMapperPool.getActualType(type, (TypeVariable)typeVariable); 
    return typeVariable;
  }
  
  public static Type getActualType(Type type, ParameterizedType parameterizedType) {
    ParameterizedTypeImpl parameterizedTypeImpl;
    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
    if (hasTypeVariable(actualTypeArguments)) {
      actualTypeArguments = getActualTypes(type, parameterizedType.getActualTypeArguments());
      if (ArrayUtil.isNotEmpty(actualTypeArguments))
        parameterizedTypeImpl = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType()); 
    } 
    return (Type)parameterizedTypeImpl;
  }
  
  public static Type[] getActualTypes(Type type, Type... typeVariables) {
    return ActualTypeMapperPool.getActualTypes(type, typeVariables);
  }
}
