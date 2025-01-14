package cn.hutool.core.lang.reflect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.WeakConcurrentMap;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class ActualTypeMapperPool {
  private static final WeakConcurrentMap<Type, Map<Type, Type>> CACHE = new WeakConcurrentMap();
  
  public static Map<Type, Type> get(Type type) {
    return (Map<Type, Type>)CACHE.computeIfAbsent(type, key -> createTypeMap(type));
  }
  
  public static Map<String, Type> getStrKeyMap(Type type) {
    return Convert.toMap(String.class, Type.class, get(type));
  }
  
  public static Type getActualType(Type type, TypeVariable<?> typeVariable) {
    Map<Type, Type> typeTypeMap = get(type);
    Type result = typeTypeMap.get(typeVariable);
    while (result instanceof TypeVariable)
      result = typeTypeMap.get(result); 
    return result;
  }
  
  public static Type[] getActualTypes(Type type, Type... typeVariables) {
    Type[] result = new Type[typeVariables.length];
    for (int i = 0; i < typeVariables.length; i++)
      result[i] = (typeVariables[i] instanceof TypeVariable) ? 
        getActualType(type, (TypeVariable)typeVariables[i]) : typeVariables[i]; 
    return result;
  }
  
  private static Map<Type, Type> createTypeMap(Type<?> type) {
    Map<Type, Type> typeMap = new HashMap<>();
    while (null != type) {
      ParameterizedType parameterizedType = TypeUtil.toParameterizedType(type);
      if (null == parameterizedType)
        break; 
      Type[] typeArguments = parameterizedType.getActualTypeArguments();
      Class<?> rawType = (Class)parameterizedType.getRawType();
      TypeVariable[] arrayOfTypeVariable = (TypeVariable[])rawType.getTypeParameters();
      for (int i = 0; i < arrayOfTypeVariable.length; i++) {
        Type value = typeArguments[i];
        if (false == value instanceof TypeVariable)
          typeMap.put(arrayOfTypeVariable[i], value); 
      } 
      type = rawType;
    } 
    return typeMap;
  }
}
