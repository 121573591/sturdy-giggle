package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.CompactStringObjectMap;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@JacksonStdImpl
public class EnumDeserializer extends StdScalarDeserializer<Object> implements ContextualDeserializer {
  private static final long serialVersionUID = 1L;
  
  protected Object[] _enumsByIndex;
  
  private final Enum<?> _enumDefaultValue;
  
  protected final CompactStringObjectMap _lookupByName;
  
  protected volatile CompactStringObjectMap _lookupByToString;
  
  protected final Boolean _caseInsensitive;
  
  private Boolean _useDefaultValueForUnknownEnum;
  
  private Boolean _useNullForUnknownEnum;
  
  protected final boolean _isFromIntValue;
  
  protected final CompactStringObjectMap _lookupByEnumNaming;
  
  @Deprecated
  public EnumDeserializer(EnumResolver byNameResolver, Boolean caseInsensitive) {
    this(byNameResolver, caseInsensitive.booleanValue(), (EnumResolver)null, (EnumResolver)null);
  }
  
  @Deprecated
  public EnumDeserializer(EnumResolver byNameResolver, boolean caseInsensitive, EnumResolver byEnumNamingResolver) {
    super(byNameResolver.getEnumClass());
    this._lookupByName = byNameResolver.constructLookup();
    this._enumsByIndex = (Object[])byNameResolver.getRawEnums();
    this._enumDefaultValue = byNameResolver.getDefaultValue();
    this._caseInsensitive = Boolean.valueOf(caseInsensitive);
    this._isFromIntValue = byNameResolver.isFromIntValue();
    this._lookupByEnumNaming = (byEnumNamingResolver == null) ? null : byEnumNamingResolver.constructLookup();
    this._lookupByToString = null;
  }
  
  public EnumDeserializer(EnumResolver byNameResolver, boolean caseInsensitive, EnumResolver byEnumNamingResolver, EnumResolver toStringResolver) {
    super(byNameResolver.getEnumClass());
    this._lookupByName = byNameResolver.constructLookup();
    this._enumsByIndex = (Object[])byNameResolver.getRawEnums();
    this._enumDefaultValue = byNameResolver.getDefaultValue();
    this._caseInsensitive = Boolean.valueOf(caseInsensitive);
    this._isFromIntValue = byNameResolver.isFromIntValue();
    this._lookupByEnumNaming = (byEnumNamingResolver == null) ? null : byEnumNamingResolver.constructLookup();
    this._lookupByToString = (toStringResolver == null) ? null : toStringResolver.constructLookup();
  }
  
  protected EnumDeserializer(EnumDeserializer base, Boolean caseInsensitive, Boolean useDefaultValueForUnknownEnum, Boolean useNullForUnknownEnum) {
    super(base);
    this._lookupByName = base._lookupByName;
    this._enumsByIndex = base._enumsByIndex;
    this._enumDefaultValue = base._enumDefaultValue;
    this._caseInsensitive = caseInsensitive;
    this._isFromIntValue = base._isFromIntValue;
    this._useDefaultValueForUnknownEnum = useDefaultValueForUnknownEnum;
    this._useNullForUnknownEnum = useNullForUnknownEnum;
    this._lookupByEnumNaming = base._lookupByEnumNaming;
    this._lookupByToString = base._lookupByToString;
  }
  
  @Deprecated
  protected EnumDeserializer(EnumDeserializer base, Boolean caseInsensitive) {
    this(base, caseInsensitive, (Boolean)null, (Boolean)null);
  }
  
  @Deprecated
  public EnumDeserializer(EnumResolver byNameResolver) {
    this(byNameResolver, (Boolean)null);
  }
  
  @Deprecated
  public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig config, Class<?> enumClass, AnnotatedMethod factory) {
    return deserializerForCreator(config, enumClass, factory, (ValueInstantiator)null, (SettableBeanProperty[])null);
  }
  
  public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig config, Class<?> enumClass, AnnotatedMethod factory, ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps) {
    if (config.canOverrideAccessModifiers())
      ClassUtil.checkAndFixAccess(factory.getMember(), config
          .isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
    return new FactoryBasedEnumDeserializer(enumClass, factory, factory
        .getParameterType(0), valueInstantiator, creatorProps);
  }
  
  public static JsonDeserializer<?> deserializerForNoArgsCreator(DeserializationConfig config, Class<?> enumClass, AnnotatedMethod factory) {
    if (config.canOverrideAccessModifiers())
      ClassUtil.checkAndFixAccess(factory.getMember(), config
          .isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS)); 
    return new FactoryBasedEnumDeserializer(enumClass, factory);
  }
  
  public EnumDeserializer withResolved(Boolean caseInsensitive, Boolean useDefaultValueForUnknownEnum, Boolean useNullForUnknownEnum) {
    if (Objects.equals(this._caseInsensitive, caseInsensitive) && 
      Objects.equals(this._useDefaultValueForUnknownEnum, useDefaultValueForUnknownEnum) && 
      Objects.equals(this._useNullForUnknownEnum, useNullForUnknownEnum))
      return this; 
    return new EnumDeserializer(this, caseInsensitive, useDefaultValueForUnknownEnum, useNullForUnknownEnum);
  }
  
  @Deprecated
  public EnumDeserializer withResolved(Boolean caseInsensitive) {
    return withResolved(caseInsensitive, this._useDefaultValueForUnknownEnum, this._useNullForUnknownEnum);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    Boolean caseInsensitive = Optional.<Boolean>ofNullable(findFormatFeature(ctxt, property, handledType(), JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)).orElse(this._caseInsensitive);
    Boolean useDefaultValueForUnknownEnum = Optional.<Boolean>ofNullable(findFormatFeature(ctxt, property, handledType(), JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)).orElse(this._useDefaultValueForUnknownEnum);
    Boolean useNullForUnknownEnum = Optional.<Boolean>ofNullable(findFormatFeature(ctxt, property, handledType(), JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)).orElse(this._useNullForUnknownEnum);
    return withResolved(caseInsensitive, useDefaultValueForUnknownEnum, useNullForUnknownEnum);
  }
  
  public boolean isCachable() {
    return true;
  }
  
  public LogicalType logicalType() {
    return LogicalType.Enum;
  }
  
  public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
    return this._enumDefaultValue;
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.hasToken(JsonToken.VALUE_STRING))
      return _fromString(p, ctxt, p.getText()); 
    if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
      if (this._isFromIntValue)
        return _fromString(p, ctxt, p.getText()); 
      return _fromInteger(p, ctxt, p.getIntValue());
    } 
    if (p.isExpectedStartObjectToken())
      return _fromString(p, ctxt, ctxt
          .extractScalarFromObject(p, this, this._valueClass)); 
    return _deserializeOther(p, ctxt);
  }
  
  protected Object _fromString(JsonParser p, DeserializationContext ctxt, String text) throws IOException {
    CompactStringObjectMap lookup = _resolveCurrentLookup(ctxt);
    Object result = lookup.find(text);
    if (result == null) {
      String trimmed = text.trim();
      if (trimmed == text || (result = lookup.find(trimmed)) == null)
        return _deserializeAltString(p, ctxt, lookup, trimmed); 
    } 
    return result;
  }
  
  private CompactStringObjectMap _resolveCurrentLookup(DeserializationContext ctxt) {
    if (this._lookupByEnumNaming != null)
      return this._lookupByEnumNaming; 
    return ctxt.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING) ? 
      _getToStringLookup(ctxt) : this._lookupByName;
  }
  
  protected Object _fromInteger(JsonParser p, DeserializationContext ctxt, int index) throws IOException {
    CoercionAction act = ctxt.findCoercionAction(logicalType(), handledType(), CoercionInputShape.Integer);
    if (act == CoercionAction.Fail) {
      if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS))
        return ctxt.handleWeirdNumberValue(_enumClass(), Integer.valueOf(index), "not allowed to deserialize Enum value out of number: disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow", new Object[0]); 
      _checkCoercionFail(ctxt, act, handledType(), Integer.valueOf(index), "Integer value (" + index + ")");
    } 
    switch (act) {
      case AsNull:
        return null;
      case AsEmpty:
        return getEmptyValue(ctxt);
    } 
    if (index >= 0 && index < this._enumsByIndex.length)
      return this._enumsByIndex[index]; 
    if (useDefaultValueForUnknownEnum(ctxt))
      return this._enumDefaultValue; 
    if (!useNullForUnknownEnum(ctxt))
      return ctxt.handleWeirdNumberValue(_enumClass(), Integer.valueOf(index), "index value outside legal index range [0..%s]", new Object[] { Integer.valueOf(this._enumsByIndex.length - 1) }); 
    return null;
  }
  
  private final Object _deserializeAltString(JsonParser p, DeserializationContext ctxt, CompactStringObjectMap lookup, String nameOrig) throws IOException {
    String name = nameOrig.trim();
    if (name.isEmpty()) {
      CoercionAction act;
      if (useDefaultValueForUnknownEnum(ctxt))
        return this._enumDefaultValue; 
      if (useNullForUnknownEnum(ctxt))
        return null; 
      if (nameOrig.isEmpty()) {
        act = _findCoercionFromEmptyString(ctxt);
        act = _checkCoercionFail(ctxt, act, handledType(), nameOrig, "empty String (\"\")");
      } else {
        act = _findCoercionFromBlankString(ctxt);
        act = _checkCoercionFail(ctxt, act, handledType(), nameOrig, "blank String (all whitespace)");
      } 
      switch (act) {
        case AsEmpty:
        case TryConvert:
          return getEmptyValue(ctxt);
      } 
      return null;
    } 
    if (Boolean.TRUE.equals(this._caseInsensitive)) {
      Object match = lookup.findCaseInsensitive(name);
      if (match != null)
        return match; 
    } 
    if (!ctxt.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS) && !this._isFromIntValue) {
      char c = name.charAt(0);
      if (c >= '0' && c <= '9')
        try {
          int index = Integer.parseInt(name);
          if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS))
            return ctxt.handleWeirdStringValue(_enumClass(), name, "value looks like quoted Enum index, but `MapperFeature.ALLOW_COERCION_OF_SCALARS` prevents use", new Object[0]); 
          if (index >= 0 && index < this._enumsByIndex.length)
            return this._enumsByIndex[index]; 
        } catch (NumberFormatException numberFormatException) {} 
    } 
    if (useDefaultValueForUnknownEnum(ctxt))
      return this._enumDefaultValue; 
    if (useNullForUnknownEnum(ctxt))
      return null; 
    return ctxt.handleWeirdStringValue(_enumClass(), name, "not one of the values accepted for Enum class: %s", new Object[] { lookup
          .keys() });
  }
  
  protected Object _deserializeOther(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.hasToken(JsonToken.START_ARRAY))
      return _deserializeFromArray(p, ctxt); 
    return ctxt.handleUnexpectedToken(_enumClass(), p);
  }
  
  protected Class<?> _enumClass() {
    return handledType();
  }
  
  @Deprecated
  protected CompactStringObjectMap _getToStringLookup(DeserializationContext ctxt) {
    CompactStringObjectMap lookup = this._lookupByToString;
    if (lookup == null)
      synchronized (this) {
        lookup = this._lookupByToString;
        if (lookup == null) {
          lookup = EnumResolver.constructUsingToString(ctxt.getConfig(), _enumClass()).constructLookup();
          this._lookupByToString = lookup;
        } 
      }  
    return lookup;
  }
  
  protected boolean useNullForUnknownEnum(DeserializationContext ctxt) {
    return (Boolean.TRUE.equals(this._useNullForUnknownEnum) || ctxt
      .isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL));
  }
  
  protected boolean useDefaultValueForUnknownEnum(DeserializationContext ctxt) {
    return (this._enumDefaultValue != null && (Boolean.TRUE
      .equals(this._useDefaultValueForUnknownEnum) || ctxt
      .isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)));
  }
}
