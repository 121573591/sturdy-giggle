package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.util.Collection;

public abstract class SubtypeResolver {
  public SubtypeResolver copy() {
    return this;
  }
  
  public abstract void registerSubtypes(NamedType... paramVarArgs);
  
  public abstract void registerSubtypes(Class<?>... paramVarArgs);
  
  public abstract void registerSubtypes(Collection<Class<?>> paramCollection);
  
  public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> config, AnnotatedMember property, JavaType baseType) {
    return collectAndResolveSubtypes(property, config, config
        .getAnnotationIntrospector(), baseType);
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> config, AnnotatedClass baseType) {
    return collectAndResolveSubtypes(baseType, config, config.getAnnotationIntrospector());
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> config, AnnotatedMember property, JavaType baseType) {
    return collectAndResolveSubtypes(property, config, config
        .getAnnotationIntrospector(), baseType);
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> config, AnnotatedClass baseType) {
    return collectAndResolveSubtypes(baseType, config, config.getAnnotationIntrospector());
  }
  
  @Deprecated
  public Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember property, MapperConfig<?> config, AnnotationIntrospector ai, JavaType baseType) {
    return collectAndResolveSubtypesByClass(config, property, baseType);
  }
  
  @Deprecated
  public Collection<NamedType> collectAndResolveSubtypes(AnnotatedClass baseType, MapperConfig<?> config, AnnotationIntrospector ai) {
    return collectAndResolveSubtypesByClass(config, baseType);
  }
}
