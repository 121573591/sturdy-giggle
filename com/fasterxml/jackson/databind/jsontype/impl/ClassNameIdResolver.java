package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;

public class ClassNameIdResolver extends TypeIdResolverBase {
  private static final String JAVA_UTIL_PKG = "java.util.";
  
  protected final PolymorphicTypeValidator _subTypeValidator;
  
  @Deprecated
  protected ClassNameIdResolver(JavaType baseType, TypeFactory typeFactory) {
    this(baseType, typeFactory, (PolymorphicTypeValidator)LaissezFaireSubTypeValidator.instance);
  }
  
  public ClassNameIdResolver(JavaType baseType, TypeFactory typeFactory, PolymorphicTypeValidator ptv) {
    super(baseType, typeFactory);
    this._subTypeValidator = ptv;
  }
  
  public static ClassNameIdResolver construct(JavaType baseType, MapperConfig<?> config, PolymorphicTypeValidator ptv) {
    return new ClassNameIdResolver(baseType, config.getTypeFactory(), ptv);
  }
  
  public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CLASS;
  }
  
  public void registerSubtype(Class<?> type, String name) {}
  
  public String idFromValue(Object value) {
    return _idFrom(value, value.getClass(), this._typeFactory);
  }
  
  public String idFromValueAndType(Object value, Class<?> type) {
    return _idFrom(value, type, this._typeFactory);
  }
  
  public JavaType typeFromId(DatabindContext context, String id) throws IOException {
    return _typeFromId(id, context);
  }
  
  protected JavaType _typeFromId(String id, DatabindContext ctxt) throws IOException {
    JavaType t = ctxt.resolveAndValidateSubType(this._baseType, id, this._subTypeValidator);
    if (t == null && 
      ctxt instanceof DeserializationContext)
      return ((DeserializationContext)ctxt).handleUnknownTypeId(this._baseType, id, this, "no such class found"); 
    return t;
  }
  
  protected String _idFrom(Object value, Class<?> cls, TypeFactory typeFactory) {
    if (ClassUtil.isEnumType(cls))
      if (!cls.isEnum())
        cls = cls.getSuperclass();  
    String str = cls.getName();
    if (str.startsWith("java.util.")) {
      if (value instanceof EnumSet) {
        Class<?> enumClass = ClassUtil.findEnumType((EnumSet)value);
        str = typeFactory.constructCollectionType(EnumSet.class, enumClass).toCanonical();
      } else if (value instanceof EnumMap) {
        Class<?> enumClass = ClassUtil.findEnumType((EnumMap)value);
        Class<?> valueClass = Object.class;
        str = typeFactory.constructMapType(EnumMap.class, enumClass, valueClass).toCanonical();
      } 
    } else if (str.indexOf('$') >= 0) {
      Class<?> outer = ClassUtil.getOuterClass(cls);
      if (outer != null) {
        Class<?> staticType = this._baseType.getRawClass();
        if (ClassUtil.getOuterClass(staticType) == null) {
          cls = this._baseType.getRawClass();
          str = cls.getName();
        } 
      } 
    } 
    return str;
  }
  
  public String getDescForKnownTypeIds() {
    return "class name used as type id";
  }
}
