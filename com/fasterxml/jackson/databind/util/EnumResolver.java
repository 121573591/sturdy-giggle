package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.EnumNamingStrategy;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumResolver implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final Class<Enum<?>> _enumClass;
  
  protected final Enum<?>[] _enums;
  
  protected final HashMap<String, Enum<?>> _enumsById;
  
  protected final Enum<?> _defaultValue;
  
  protected final boolean _isIgnoreCase;
  
  protected final boolean _isFromIntValue;
  
  protected EnumResolver(Class<Enum<?>> enumClass, Enum<?>[] enums, HashMap<String, Enum<?>> map, Enum<?> defaultValue, boolean isIgnoreCase, boolean isFromIntValue) {
    this._enumClass = enumClass;
    this._enums = enums;
    this._enumsById = map;
    this._defaultValue = defaultValue;
    this._isIgnoreCase = isIgnoreCase;
    this._isFromIntValue = isFromIntValue;
  }
  
  public static EnumResolver constructFor(DeserializationConfig config, AnnotatedClass annotatedClass) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    String[] names = ai.findEnumValues((MapperConfig)config, annotatedClass, arrayOfEnum, new String[arrayOfEnum.length]);
    String[][] allAliases = new String[names.length][];
    ai.findEnumAliases((MapperConfig)config, annotatedClass, arrayOfEnum, allAliases);
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = 0, len = arrayOfEnum.length; i < len; i++) {
      Enum<?> enumValue = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = enumValue.name(); 
      map.put(name, enumValue);
      String[] aliases = allAliases[i];
      if (aliases != null)
        for (String alias : aliases)
          map.putIfAbsent(alias, enumValue);  
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, annotatedClass, (Enum<?>[])arrayOfEnum), isIgnoreCase, false);
  }
  
  @Deprecated
  public static EnumResolver constructFor(DeserializationConfig config, Class<?> enumCls0) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    String[] names = ai.findEnumValues(enumCls, arrayOfEnum, new String[arrayOfEnum.length]);
    String[][] allAliases = new String[names.length][];
    ai.findEnumAliases(enumCls, arrayOfEnum, allAliases);
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = 0, len = arrayOfEnum.length; i < len; i++) {
      Enum<?> enumValue = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = enumValue.name(); 
      map.put(name, enumValue);
      String[] aliases = allAliases[i];
      if (aliases != null)
        for (String alias : aliases)
          map.putIfAbsent(alias, enumValue);  
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, enumCls), isIgnoreCase, false);
  }
  
  public static EnumResolver constructUsingToString(DeserializationConfig config, AnnotatedClass annotatedClass) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    String[] names = new String[arrayOfEnum.length];
    String[][] allAliases = new String[arrayOfEnum.length][];
    if (ai != null) {
      ai.findEnumValues((MapperConfig)config, annotatedClass, arrayOfEnum, names);
      ai.findEnumAliases((MapperConfig)config, annotatedClass, arrayOfEnum, allAliases);
    } 
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> enumValue = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = enumValue.toString(); 
      map.put(name, enumValue);
      String[] aliases = allAliases[i];
      if (aliases != null)
        for (String alias : aliases)
          map.putIfAbsent(alias, enumValue);  
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, annotatedClass, (Enum<?>[])arrayOfEnum), isIgnoreCase, false);
  }
  
  @Deprecated
  public static EnumResolver constructUsingToString(DeserializationConfig config, Class<?> enumCls0) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    HashMap<String, Enum<?>> map = new HashMap<>();
    String[][] allAliases = new String[arrayOfEnum.length][];
    if (ai != null)
      ai.findEnumAliases(enumCls, arrayOfEnum, allAliases); 
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> enumValue = arrayOfEnum[i];
      map.put(enumValue.toString(), enumValue);
      String[] aliases = allAliases[i];
      if (aliases != null)
        for (String alias : aliases)
          map.putIfAbsent(alias, enumValue);  
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, enumCls), isIgnoreCase, false);
  }
  
  public static EnumResolver constructUsingIndex(DeserializationConfig config, AnnotatedClass annotatedClass) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> enumValue = arrayOfEnum[i];
      map.put(String.valueOf(i), enumValue);
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, annotatedClass, (Enum<?>[])arrayOfEnum), isIgnoreCase, false);
  }
  
  @Deprecated
  public static EnumResolver constructUsingIndex(DeserializationConfig config, Class<Enum<?>> enumCls0) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> enumValue = arrayOfEnum[i];
      map.put(String.valueOf(i), enumValue);
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, enumCls), isIgnoreCase, false);
  }
  
  @Deprecated
  public static EnumResolver constructUsingEnumNamingStrategy(DeserializationConfig config, Class<?> enumCls0, EnumNamingStrategy enumNamingStrategy) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    HashMap<String, Enum<?>> map = new HashMap<>();
    String[] names = new String[arrayOfEnum.length];
    String[][] allAliases = new String[arrayOfEnum.length][];
    if (ai != null) {
      ai.findEnumValues(enumCls, arrayOfEnum, names);
      ai.findEnumAliases(enumCls, arrayOfEnum, allAliases);
    } 
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> anEnum = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = enumNamingStrategy.convertEnumToExternalName(anEnum.name()); 
      map.put(name, anEnum);
      String[] aliases = allAliases[i];
      if (aliases != null)
        for (String alias : aliases)
          map.putIfAbsent(alias, anEnum);  
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, enumCls), isIgnoreCase, false);
  }
  
  public static EnumResolver constructUsingEnumNamingStrategy(DeserializationConfig config, AnnotatedClass annotatedClass, EnumNamingStrategy enumNamingStrategy) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    String[] names = new String[arrayOfEnum.length];
    String[][] allAliases = new String[arrayOfEnum.length][];
    if (ai != null) {
      ai.findEnumValues((MapperConfig)config, annotatedClass, arrayOfEnum, names);
      ai.findEnumAliases((MapperConfig)config, annotatedClass, arrayOfEnum, allAliases);
    } 
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> anEnum = arrayOfEnum[i];
      String name = names[i];
      if (name == null)
        name = enumNamingStrategy.convertEnumToExternalName(anEnum.name()); 
      map.put(name, anEnum);
      String[] aliases = allAliases[i];
      if (aliases != null)
        for (String alias : aliases)
          map.putIfAbsent(alias, anEnum);  
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, annotatedClass, (Enum<?>[])arrayOfEnum), isIgnoreCase, false);
  }
  
  @Deprecated
  public static EnumResolver constructUsingMethod(DeserializationConfig config, Class<?> enumCls0, AnnotatedMember accessor) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> en = arrayOfEnum[i];
      try {
        Object o = accessor.getValue(en);
        if (o != null)
          map.put(o.toString(), en); 
      } catch (Exception e) {
        throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + en + ": " + e.getMessage());
      } 
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, enumCls), isIgnoreCase, 
        
        _isIntType(accessor.getRawType()));
  }
  
  public static EnumResolver constructUsingMethod(DeserializationConfig config, AnnotatedClass annotatedClass, AnnotatedMember accessor) {
    AnnotationIntrospector ai = config.getAnnotationIntrospector();
    boolean isIgnoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    Class<?> enumCls0 = annotatedClass.getRawType();
    Class<Enum<?>> enumCls = _enumClass(enumCls0);
    Enum[] arrayOfEnum = (Enum[])_enumConstants(enumCls0);
    HashMap<String, Enum<?>> map = new HashMap<>();
    for (int i = arrayOfEnum.length; --i >= 0; ) {
      Enum<?> en = arrayOfEnum[i];
      try {
        Object o = accessor.getValue(en);
        if (o != null)
          map.put(o.toString(), en); 
      } catch (Exception e) {
        throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + en + ": " + e.getMessage());
      } 
    } 
    return new EnumResolver(enumCls, (Enum<?>[])arrayOfEnum, map, 
        _enumDefault(ai, annotatedClass, (Enum<?>[])arrayOfEnum), isIgnoreCase, 
        
        _isIntType(accessor.getRawType()));
  }
  
  public CompactStringObjectMap constructLookup() {
    return CompactStringObjectMap.construct(this._enumsById);
  }
  
  protected static Class<Enum<?>> _enumClass(Class<?> enumCls0) {
    return (Class)enumCls0;
  }
  
  protected static Enum<?>[] _enumConstants(Class<?> enumCls) {
    Enum[] arrayOfEnum = (Enum[])_enumClass(enumCls).getEnumConstants();
    if (arrayOfEnum == null)
      throw new IllegalArgumentException("No enum constants for class " + enumCls.getName()); 
    return (Enum<?>[])arrayOfEnum;
  }
  
  protected static Enum<?> _enumDefault(AnnotationIntrospector intr, AnnotatedClass annotatedClass, Enum<?>[] enums) {
    return (intr != null) ? intr.findDefaultEnumValue(annotatedClass, (Enum[])enums) : null;
  }
  
  @Deprecated
  protected static Enum<?> _enumDefault(AnnotationIntrospector intr, Class<?> enumCls) {
    return (intr != null) ? intr.findDefaultEnumValue(_enumClass(enumCls)) : null;
  }
  
  protected static boolean _isIntType(Class<?> erasedType) {
    if (erasedType.isPrimitive())
      erasedType = ClassUtil.wrapperType(erasedType); 
    return (erasedType == Long.class || erasedType == Integer.class || erasedType == Short.class || erasedType == Byte.class);
  }
  
  public Enum<?> findEnum(String key) {
    Enum<?> en = this._enumsById.get(key);
    if (en == null && 
      this._isIgnoreCase)
      return _findEnumCaseInsensitive(key); 
    return en;
  }
  
  protected Enum<?> _findEnumCaseInsensitive(String key) {
    for (Map.Entry<String, Enum<?>> entry : this._enumsById.entrySet()) {
      if (key.equalsIgnoreCase(entry.getKey()))
        return entry.getValue(); 
    } 
    return null;
  }
  
  public Enum<?> getEnum(int index) {
    if (index < 0 || index >= this._enums.length)
      return null; 
    return this._enums[index];
  }
  
  public Enum<?> getDefaultValue() {
    return this._defaultValue;
  }
  
  public Enum<?>[] getRawEnums() {
    return this._enums;
  }
  
  public List<Enum<?>> getEnums() {
    ArrayList<Enum<?>> enums = new ArrayList<>(this._enums.length);
    for (Enum<?> e : this._enums)
      enums.add(e); 
    return enums;
  }
  
  public Collection<String> getEnumIds() {
    return this._enumsById.keySet();
  }
  
  public Class<Enum<?>> getEnumClass() {
    return this._enumClass;
  }
  
  public int lastValidIndex() {
    return this._enums.length - 1;
  }
  
  public boolean isFromIntValue() {
    return this._isFromIntValue;
  }
}
