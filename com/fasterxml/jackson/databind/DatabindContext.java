package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DatabindContext {
  private static final int MAX_ERROR_STR_LEN = 500;
  
  public abstract MapperConfig<?> getConfig();
  
  public abstract AnnotationIntrospector getAnnotationIntrospector();
  
  public abstract boolean isEnabled(MapperFeature paramMapperFeature);
  
  public abstract boolean isEnabled(DatatypeFeature paramDatatypeFeature);
  
  public abstract DatatypeFeatures getDatatypeFeatures();
  
  public abstract boolean canOverrideAccessModifiers();
  
  public abstract Class<?> getActiveView();
  
  public abstract Locale getLocale();
  
  public abstract TimeZone getTimeZone();
  
  public abstract JsonFormat.Value getDefaultPropertyFormat(Class<?> paramClass);
  
  public abstract Object getAttribute(Object paramObject);
  
  public abstract DatabindContext setAttribute(Object paramObject1, Object paramObject2);
  
  public JavaType constructType(Type type) {
    if (type == null)
      return null; 
    return getTypeFactory().constructType(type);
  }
  
  public abstract JavaType constructSpecializedType(JavaType paramJavaType, Class<?> paramClass);
  
  public JavaType resolveSubType(JavaType baseType, String subClassName) throws JsonMappingException {
    if (subClassName.indexOf('<') > 0) {
      JavaType t = getTypeFactory().constructFromCanonical(subClassName);
      if (t.isTypeOrSubTypeOf(baseType.getRawClass()))
        return t; 
    } else {
      Class<?> cls;
      try {
        cls = getTypeFactory().findClass(subClassName);
      } catch (ClassNotFoundException e) {
        return null;
      } catch (Exception e) {
        throw invalidTypeIdException(baseType, subClassName, String.format("problem: (%s) %s", new Object[] { e
                
                .getClass().getName(), 
                ClassUtil.exceptionMessage(e) }));
      } 
      if (baseType.isTypeOrSuperTypeOf(cls))
        return getTypeFactory().constructSpecializedType(baseType, cls); 
    } 
    throw invalidTypeIdException(baseType, subClassName, "Not a subtype");
  }
  
  public JavaType resolveAndValidateSubType(JavaType baseType, String subClass, PolymorphicTypeValidator ptv) throws JsonMappingException {
    Class<?> cls;
    int ltIndex = subClass.indexOf('<');
    if (ltIndex > 0)
      return _resolveAndValidateGeneric(baseType, subClass, ptv, ltIndex); 
    MapperConfig<?> config = getConfig();
    PolymorphicTypeValidator.Validity vld = ptv.validateSubClassName(config, baseType, subClass);
    if (vld == PolymorphicTypeValidator.Validity.DENIED)
      return _throwSubtypeNameNotAllowed(baseType, subClass, ptv); 
    try {
      cls = getTypeFactory().findClass(subClass);
    } catch (ClassNotFoundException e) {
      return null;
    } catch (Exception e) {
      throw invalidTypeIdException(baseType, subClass, String.format("problem: (%s) %s", new Object[] { e
              
              .getClass().getName(), 
              ClassUtil.exceptionMessage(e) }));
    } 
    if (!baseType.isTypeOrSuperTypeOf(cls))
      return _throwNotASubtype(baseType, subClass); 
    JavaType subType = config.getTypeFactory().constructSpecializedType(baseType, cls);
    if (vld == PolymorphicTypeValidator.Validity.INDETERMINATE) {
      vld = ptv.validateSubType(config, baseType, subType);
      if (vld != PolymorphicTypeValidator.Validity.ALLOWED)
        return _throwSubtypeClassNotAllowed(baseType, subClass, ptv); 
    } 
    return subType;
  }
  
  private JavaType _resolveAndValidateGeneric(JavaType baseType, String subClass, PolymorphicTypeValidator ptv, int ltIndex) throws JsonMappingException {
    MapperConfig<?> config = getConfig();
    PolymorphicTypeValidator.Validity vld = ptv.validateSubClassName(config, baseType, subClass.substring(0, ltIndex));
    if (vld == PolymorphicTypeValidator.Validity.DENIED)
      return _throwSubtypeNameNotAllowed(baseType, subClass, ptv); 
    JavaType subType = getTypeFactory().constructFromCanonical(subClass);
    if (!subType.isTypeOrSubTypeOf(baseType.getRawClass()))
      return _throwNotASubtype(baseType, subClass); 
    if (vld != PolymorphicTypeValidator.Validity.ALLOWED && 
      ptv.validateSubType(config, baseType, subType) != PolymorphicTypeValidator.Validity.ALLOWED)
      return _throwSubtypeClassNotAllowed(baseType, subClass, ptv); 
    return subType;
  }
  
  protected <T> T _throwNotASubtype(JavaType baseType, String subType) throws JsonMappingException {
    throw invalidTypeIdException(baseType, subType, "Not a subtype");
  }
  
  protected <T> T _throwSubtypeNameNotAllowed(JavaType baseType, String subType, PolymorphicTypeValidator ptv) throws JsonMappingException {
    throw invalidTypeIdException(baseType, subType, "Configured `PolymorphicTypeValidator` (of type " + 
        ClassUtil.classNameOf(ptv) + ") denied resolution");
  }
  
  protected <T> T _throwSubtypeClassNotAllowed(JavaType baseType, String subType, PolymorphicTypeValidator ptv) throws JsonMappingException {
    throw invalidTypeIdException(baseType, subType, "Configured `PolymorphicTypeValidator` (of type " + 
        ClassUtil.classNameOf(ptv) + ") denied resolution");
  }
  
  protected abstract JsonMappingException invalidTypeIdException(JavaType paramJavaType, String paramString1, String paramString2);
  
  public abstract TypeFactory getTypeFactory();
  
  public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectIdInfo) throws JsonMappingException {
    Class<?> implClass = objectIdInfo.getGeneratorType();
    MapperConfig<?> config = getConfig();
    HandlerInstantiator hi = config.getHandlerInstantiator();
    ObjectIdGenerator<?> gen = (hi == null) ? null : hi.objectIdGeneratorInstance(config, annotated, implClass);
    if (gen == null)
      gen = (ObjectIdGenerator)ClassUtil.createInstance(implClass, config
          .canOverrideAccessModifiers()); 
    return gen.forScope(objectIdInfo.getScope());
  }
  
  public ObjectIdResolver objectIdResolverInstance(Annotated annotated, ObjectIdInfo objectIdInfo) {
    Class<? extends ObjectIdResolver> implClass = objectIdInfo.getResolverType();
    MapperConfig<?> config = getConfig();
    HandlerInstantiator hi = config.getHandlerInstantiator();
    ObjectIdResolver resolver = (hi == null) ? null : hi.resolverIdGeneratorInstance(config, annotated, implClass);
    if (resolver == null)
      resolver = (ObjectIdResolver)ClassUtil.createInstance(implClass, config.canOverrideAccessModifiers()); 
    return resolver;
  }
  
  public Converter<Object, Object> converterInstance(Annotated annotated, Object converterDef) throws JsonMappingException {
    if (converterDef == null)
      return null; 
    if (converterDef instanceof Converter)
      return (Converter<Object, Object>)converterDef; 
    if (!(converterDef instanceof Class))
      throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + converterDef
          .getClass().getName() + "; expected type Converter or Class<Converter> instead"); 
    Class<?> converterClass = (Class)converterDef;
    if (converterClass == Converter.None.class || ClassUtil.isBogusClass(converterClass))
      return null; 
    if (!Converter.class.isAssignableFrom(converterClass))
      throw new IllegalStateException("AnnotationIntrospector returned Class " + converterClass
          .getName() + "; expected Class<Converter>"); 
    MapperConfig<?> config = getConfig();
    HandlerInstantiator hi = config.getHandlerInstantiator();
    Converter<?, ?> conv = (hi == null) ? null : hi.converterInstance(config, annotated, converterClass);
    if (conv == null)
      conv = (Converter<?, ?>)ClassUtil.createInstance(converterClass, config
          .canOverrideAccessModifiers()); 
    return (Converter)conv;
  }
  
  public abstract <T> T reportBadDefinition(JavaType paramJavaType, String paramString) throws JsonMappingException;
  
  public <T> T reportBadDefinition(Class<?> type, String msg) throws JsonMappingException {
    return reportBadDefinition(constructType(type), msg);
  }
  
  protected final String _format(String msg, Object... msgArgs) {
    if (msgArgs.length > 0)
      return String.format(msg, msgArgs); 
    return msg;
  }
  
  protected final String _truncate(String desc) {
    if (desc == null)
      return ""; 
    if (desc.length() <= 500)
      return desc; 
    return desc.substring(0, 500) + "]...[" + desc.substring(desc.length() - 500);
  }
  
  protected String _quotedString(String desc) {
    if (desc == null)
      return "[N/A]"; 
    return String.format("\"%s\"", new Object[] { _truncate(desc) });
  }
  
  protected String _colonConcat(String msgBase, String extra) {
    if (extra == null)
      return msgBase; 
    return msgBase + ": " + extra;
  }
  
  protected String _desc(String desc) {
    if (desc == null)
      return "[N/A]"; 
    return _truncate(desc);
  }
}
