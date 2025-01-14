package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.EnumNamingStrategy;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.EnumFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class EnumValues implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Class<Enum<?>> _enumClass;
  
  private final Enum<?>[] _values;
  
  private final SerializableString[] _textual;
  
  private transient EnumMap<?, SerializableString> _asMap;
  
  private EnumValues(Class<Enum<?>> enumClass, SerializableString[] textual) {
    this._enumClass = enumClass;
    this._values = enumClass.getEnumConstants();
    this._textual = textual;
  }
  
  public static EnumValues construct(SerializationConfig config, AnnotatedClass annotatedClass) {
    if (config.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING))
      return constructFromToString((MapperConfig<?>)config, annotatedClass); 
    return constructFromName((MapperConfig<?>)config, annotatedClass);
  }
  
  @Deprecated
  public static EnumValues constructFromName(MapperConfig<?> config, Class<Enum<?>> enumClass) {
    Class<? extends Enum<?>> enumCls = ClassUtil.findEnumType(enumClass);
    Enum[] arrayOfEnum = (Enum[])enumCls.getEnumConstants();
    if (arrayOfEnum == null)
      throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName()); 
    String[] names = config.getAnnotationIntrospector().findEnumValues(enumCls, arrayOfEnum, new String[arrayOfEnum.length]);
    SerializableString[] textual = new SerializableString[arrayOfEnum.length];
    for (int i = 0, len = arrayOfEnum.length; i < len; i++) {
      Enum<?> en = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = en.name(); 
      if (config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE))
        name = name.toLowerCase(); 
      textual[en.ordinal()] = config.compileString(name);
    } 
    return construct(enumClass, textual);
  }
  
  public static EnumValues constructFromName(MapperConfig<?> config, AnnotatedClass annotatedClass) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean useLowerCase = config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    String[] names = ai.findEnumValues(config, annotatedClass, arrayOfEnum, new String[arrayOfEnum.length]);
    SerializableString[] textual = new SerializableString[arrayOfEnum.length];
    for (int i = 0, len = arrayOfEnum.length; i < len; i++) {
      Enum<?> enumValue = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = enumValue.name(); 
      if (useLowerCase)
        name = name.toLowerCase(); 
      textual[enumValue.ordinal()] = config.compileString(name);
    } 
    return construct(enumCls, textual);
  }
  
  public static EnumValues constructFromToString(MapperConfig<?> config, AnnotatedClass annotatedClass) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean useLowerCase = config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    String[] names = new String[arrayOfEnum.length];
    if (ai != null)
      ai.findEnumValues(config, annotatedClass, arrayOfEnum, names); 
    SerializableString[] textual = new SerializableString[arrayOfEnum.length];
    for (int i = 0; i < arrayOfEnum.length; i++) {
      String name = names[i];
      if (name == null) {
        Enum<?> en = arrayOfEnum[i];
        name = en.toString();
      } 
      if (useLowerCase)
        name = name.toLowerCase(); 
      textual[i] = config.compileString(name);
    } 
    return construct(enumCls, textual);
  }
  
  @Deprecated
  public static EnumValues constructFromToString(MapperConfig<?> config, Class<Enum<?>> enumClass) {
    Class<? extends Enum<?>> cls = ClassUtil.findEnumType(enumClass);
    Enum[] arrayOfEnum = (Enum[])cls.getEnumConstants();
    if (arrayOfEnum == null)
      throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName()); 
    ArrayList<String> external = new ArrayList<>(arrayOfEnum.length);
    for (Enum<?> en : arrayOfEnum)
      external.add(en.toString()); 
    return construct(config, enumClass, external);
  }
  
  public static EnumValues constructUsingEnumNamingStrategy(MapperConfig<?> config, AnnotatedClass annotatedClass, EnumNamingStrategy namingStrategy) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean useLowerCase = config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    String[] names = new String[arrayOfEnum.length];
    if (ai != null)
      ai.findEnumValues(config, annotatedClass, arrayOfEnum, names); 
    SerializableString[] textual = new SerializableString[arrayOfEnum.length];
    for (int i = 0, len = arrayOfEnum.length; i < len; i++) {
      Enum<?> enumValue = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = namingStrategy.convertEnumToExternalName(enumValue.name()); 
      if (useLowerCase)
        name = name.toLowerCase(); 
      textual[i] = config.compileString(name);
    } 
    return construct(enumCls, textual);
  }
  
  @Deprecated
  public static EnumValues constructUsingEnumNamingStrategy(MapperConfig<?> config, Class<Enum<?>> enumClass, EnumNamingStrategy namingStrategy) {
    Class<? extends Enum<?>> cls = ClassUtil.findEnumType(enumClass);
    Enum[] arrayOfEnum = (Enum[])cls.getEnumConstants();
    if (arrayOfEnum == null)
      throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName()); 
    ArrayList<String> external = new ArrayList<>(arrayOfEnum.length);
    for (Enum<?> en : arrayOfEnum)
      external.add(namingStrategy.convertEnumToExternalName(en.name())); 
    return construct(config, enumClass, external);
  }
  
  public static EnumValues construct(MapperConfig<?> config, Class<Enum<?>> enumClass, List<String> externalValues) {
    int len = externalValues.size();
    SerializableString[] textual = new SerializableString[len];
    for (int i = 0; i < len; i++)
      textual[i] = config.compileString(externalValues.get(i)); 
    return construct(enumClass, textual);
  }
  
  public static EnumValues construct(Class<Enum<?>> enumClass, SerializableString[] externalValues) {
    return new EnumValues(enumClass, externalValues);
  }
  
  protected static Class<Enum<?>> _enumClass(Class<?> enumCls0) {
    return (Class)enumCls0;
  }
  
  protected static Enum<?>[] _enumConstants(Class<?> enumCls) {
    Enum[] arrayOfEnum = (Enum[])ClassUtil.findEnumType(enumCls).getEnumConstants();
    if (arrayOfEnum == null)
      throw new IllegalArgumentException("No enum constants for class " + enumCls.getName()); 
    return (Enum<?>[])arrayOfEnum;
  }
  
  public SerializableString serializedValueFor(Enum<?> key) {
    return this._textual[key.ordinal()];
  }
  
  public Collection<SerializableString> values() {
    return Arrays.asList(this._textual);
  }
  
  public List<Enum<?>> enums() {
    return Arrays.asList(this._values);
  }
  
  public EnumMap<?, SerializableString> internalMap() {
    EnumMap<?, SerializableString> result = this._asMap;
    if (result == null) {
      Map<Enum<?>, SerializableString> map = new LinkedHashMap<>();
      for (Enum<?> en : this._values)
        map.put(en, this._textual[en.ordinal()]); 
      this._asMap = result = new EnumMap<>(map);
    } 
    return result;
  }
  
  public Class<Enum<?>> getEnumClass() {
    return this._enumClass;
  }
}
