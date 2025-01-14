package cn.hutool.json.serialize;

import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.json.JSON;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalSerializeMapping {
  private static Map<Type, JSONSerializer<? extends JSON, ?>> serializerMap = (Map<Type, JSONSerializer<? extends JSON, ?>>)new SafeConcurrentHashMap();
  
  private static Map<Type, JSONDeserializer<?>> deserializerMap = (Map<Type, JSONDeserializer<?>>)new SafeConcurrentHashMap();
  
  static {
    TemporalAccessorSerializer localDateSerializer = new TemporalAccessorSerializer((Class)LocalDate.class);
    serializerMap.put(LocalDate.class, localDateSerializer);
    deserializerMap.put(LocalDate.class, localDateSerializer);
    TemporalAccessorSerializer localDateTimeSerializer = new TemporalAccessorSerializer((Class)LocalDateTime.class);
    serializerMap.put(LocalDateTime.class, localDateTimeSerializer);
    deserializerMap.put(LocalDateTime.class, localDateTimeSerializer);
    TemporalAccessorSerializer localTimeSerializer = new TemporalAccessorSerializer((Class)LocalTime.class);
    serializerMap.put(LocalTime.class, localTimeSerializer);
    deserializerMap.put(LocalTime.class, localTimeSerializer);
  }
  
  public static void put(Type type, JSONArraySerializer<?> serializer) {
    putInternal(type, (JSONSerializer)serializer);
  }
  
  public static void put(Type type, JSONObjectSerializer<?> serializer) {
    putInternal(type, (JSONSerializer)serializer);
  }
  
  private static synchronized void putInternal(Type type, JSONSerializer<? extends JSON, ?> serializer) {
    if (null == serializerMap)
      serializerMap = (Map<Type, JSONSerializer<? extends JSON, ?>>)new SafeConcurrentHashMap(); 
    serializerMap.put(type, serializer);
  }
  
  public static synchronized void put(Type type, JSONDeserializer<?> deserializer) {
    if (null == deserializerMap)
      deserializerMap = new ConcurrentHashMap<>(); 
    deserializerMap.put(type, deserializer);
  }
  
  public static JSONSerializer<? extends JSON, ?> getSerializer(Type type) {
    if (null == serializerMap)
      return null; 
    return serializerMap.get(type);
  }
  
  public static JSONDeserializer<?> getDeserializer(Type type) {
    if (null == deserializerMap)
      return null; 
    return deserializerMap.get(type);
  }
}
