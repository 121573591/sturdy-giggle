package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class BasicClassIntrospector extends ClassIntrospector implements Serializable {
  private static final long serialVersionUID = 2L;
  
  private static final Class<?> CLS_OBJECT = Object.class;
  
  private static final Class<?> CLS_STRING = String.class;
  
  private static final Class<?> CLS_JSON_NODE = JsonNode.class;
  
  protected static final BasicBeanDescription STRING_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(String.class), 
      AnnotatedClassResolver.createPrimordial(CLS_STRING));
  
  protected static final BasicBeanDescription BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(boolean.class), 
      AnnotatedClassResolver.createPrimordial(boolean.class));
  
  protected static final BasicBeanDescription INT_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(int.class), 
      AnnotatedClassResolver.createPrimordial(int.class));
  
  protected static final BasicBeanDescription LONG_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(long.class), 
      AnnotatedClassResolver.createPrimordial(long.class));
  
  protected static final BasicBeanDescription OBJECT_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(Object.class), 
      AnnotatedClassResolver.createPrimordial(CLS_OBJECT));
  
  public ClassIntrospector copy() {
    return new BasicClassIntrospector();
  }
  
  public BasicBeanDescription forSerialization(SerializationConfig config, JavaType type, ClassIntrospector.MixInResolver r) {
    BasicBeanDescription desc = _findStdTypeDesc((MapperConfig<?>)config, type);
    if (desc == null) {
      desc = _findStdJdkCollectionDesc((MapperConfig<?>)config, type);
      if (desc == null)
        desc = BasicBeanDescription.forSerialization(collectProperties((MapperConfig<?>)config, type, r, true)); 
    } 
    return desc;
  }
  
  public BasicBeanDescription forDeserialization(DeserializationConfig config, JavaType type, ClassIntrospector.MixInResolver r) {
    BasicBeanDescription desc = _findStdTypeDesc((MapperConfig<?>)config, type);
    if (desc == null) {
      desc = _findStdJdkCollectionDesc((MapperConfig<?>)config, type);
      if (desc == null)
        desc = BasicBeanDescription.forDeserialization(collectProperties((MapperConfig<?>)config, type, r, false)); 
    } 
    return desc;
  }
  
  public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig config, JavaType builderType, ClassIntrospector.MixInResolver r, BeanDescription valueTypeDesc) {
    return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder((MapperConfig<?>)config, builderType, r, valueTypeDesc, false));
  }
  
  @Deprecated
  public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig config, JavaType type, ClassIntrospector.MixInResolver r) {
    return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder((MapperConfig<?>)config, type, r, null, false));
  }
  
  public BasicBeanDescription forCreation(DeserializationConfig config, JavaType type, ClassIntrospector.MixInResolver r) {
    BasicBeanDescription desc = _findStdTypeDesc((MapperConfig<?>)config, type);
    if (desc == null) {
      desc = _findStdJdkCollectionDesc((MapperConfig<?>)config, type);
      if (desc == null)
        desc = BasicBeanDescription.forDeserialization(
            collectProperties((MapperConfig<?>)config, type, r, false)); 
    } 
    return desc;
  }
  
  public BasicBeanDescription forClassAnnotations(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r) {
    BasicBeanDescription desc = _findStdTypeDesc(config, type);
    if (desc == null)
      desc = BasicBeanDescription.forOtherUse(config, type, 
          _resolveAnnotatedClass(config, type, r)); 
    return desc;
  }
  
  public BasicBeanDescription forDirectClassAnnotations(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r) {
    BasicBeanDescription desc = _findStdTypeDesc(config, type);
    if (desc == null)
      desc = BasicBeanDescription.forOtherUse(config, type, 
          _resolveAnnotatedWithoutSuperTypes(config, type, r)); 
    return desc;
  }
  
  protected POJOPropertiesCollector collectProperties(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r, boolean forSerialization) {
    AnnotatedClass classDef = _resolveAnnotatedClass(config, type, r);
    AccessorNamingStrategy accNaming = type.isRecordType() ? config.getAccessorNaming().forRecord(config, classDef) : config.getAccessorNaming().forPOJO(config, classDef);
    return constructPropertyCollector(config, classDef, type, forSerialization, accNaming);
  }
  
  @Deprecated
  protected POJOPropertiesCollector collectProperties(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r, boolean forSerialization, String mutatorPrefix) {
    AnnotatedClass classDef = _resolveAnnotatedClass(config, type, r);
    AccessorNamingStrategy accNaming = (new DefaultAccessorNamingStrategy.Provider()).withSetterPrefix(mutatorPrefix).forPOJO(config, classDef);
    return constructPropertyCollector(config, classDef, type, forSerialization, accNaming);
  }
  
  protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r, BeanDescription valueTypeDesc, boolean forSerialization) {
    AnnotatedClass builderClassDef = _resolveAnnotatedClass(config, type, r);
    AccessorNamingStrategy accNaming = config.getAccessorNaming().forBuilder(config, builderClassDef, valueTypeDesc);
    return constructPropertyCollector(config, builderClassDef, type, forSerialization, accNaming);
  }
  
  @Deprecated
  protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r, boolean forSerialization) {
    return collectPropertiesWithBuilder(config, type, r, null, forSerialization);
  }
  
  protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> config, AnnotatedClass classDef, JavaType type, boolean forSerialization, AccessorNamingStrategy accNaming) {
    return new POJOPropertiesCollector(config, forSerialization, type, classDef, accNaming);
  }
  
  @Deprecated
  protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> config, AnnotatedClass ac, JavaType type, boolean forSerialization, String mutatorPrefix) {
    return new POJOPropertiesCollector(config, forSerialization, type, ac, mutatorPrefix);
  }
  
  protected BasicBeanDescription _findStdTypeDesc(MapperConfig<?> config, JavaType type) {
    Class<?> cls = type.getRawClass();
    if (cls.isPrimitive()) {
      if (cls == int.class)
        return INT_DESC; 
      if (cls == long.class)
        return LONG_DESC; 
      if (cls == boolean.class)
        return BOOLEAN_DESC; 
    } else if (ClassUtil.isJDKClass(cls)) {
      if (cls == CLS_OBJECT)
        return OBJECT_DESC; 
      if (cls == CLS_STRING)
        return STRING_DESC; 
      if (cls == Integer.class)
        return INT_DESC; 
      if (cls == Long.class)
        return LONG_DESC; 
      if (cls == Boolean.class)
        return BOOLEAN_DESC; 
    } else if (CLS_JSON_NODE.isAssignableFrom(cls)) {
      return BasicBeanDescription.forOtherUse(config, type, 
          AnnotatedClassResolver.createPrimordial(cls));
    } 
    return null;
  }
  
  protected boolean _isStdJDKCollection(JavaType type) {
    if (!type.isContainerType() || type.isArrayType())
      return false; 
    Class<?> raw = type.getRawClass();
    if (ClassUtil.isJDKClass(raw))
      if (Collection.class.isAssignableFrom(raw) || Map.class
        .isAssignableFrom(raw))
        return true;  
    return false;
  }
  
  protected BasicBeanDescription _findStdJdkCollectionDesc(MapperConfig<?> cfg, JavaType type) {
    if (_isStdJDKCollection(type))
      return BasicBeanDescription.forOtherUse(cfg, type, 
          _resolveAnnotatedClass(cfg, type, (ClassIntrospector.MixInResolver)cfg)); 
    return null;
  }
  
  protected AnnotatedClass _resolveAnnotatedClass(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r) {
    return AnnotatedClassResolver.resolve(config, type, r);
  }
  
  protected AnnotatedClass _resolveAnnotatedWithoutSuperTypes(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r) {
    return AnnotatedClassResolver.resolveWithoutSuperTypes(config, type, r);
  }
}
