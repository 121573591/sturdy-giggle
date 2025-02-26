package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class BuilderBasedDeserializer extends BeanDeserializerBase {
  private static final long serialVersionUID = 1L;
  
  protected final AnnotatedMethod _buildMethod;
  
  protected final JavaType _targetType;
  
  public BuilderBasedDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, JavaType targetType, BeanPropertyMap properties, Map<String, SettableBeanProperty> backRefs, Set<String> ignorableProps, boolean ignoreAllUnknown, boolean hasViews) {
    this(builder, beanDesc, targetType, properties, backRefs, ignorableProps, ignoreAllUnknown, (Set<String>)null, hasViews);
  }
  
  public BuilderBasedDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, JavaType targetType, BeanPropertyMap properties, Map<String, SettableBeanProperty> backRefs, Set<String> ignorableProps, boolean ignoreAllUnknown, Set<String> includableProps, boolean hasViews) {
    super(builder, beanDesc, properties, backRefs, ignorableProps, ignoreAllUnknown, includableProps, hasViews);
    this._targetType = targetType;
    this._buildMethod = builder.getBuildMethod();
    if (this._objectIdReader != null)
      throw new IllegalArgumentException("Cannot use Object Id with Builder-based deserialization (type " + beanDesc
          .getType() + ")"); 
  }
  
  @Deprecated
  public BuilderBasedDeserializer(BeanDeserializerBuilder builder, BeanDescription beanDesc, BeanPropertyMap properties, Map<String, SettableBeanProperty> backRefs, Set<String> ignorableProps, boolean ignoreAllUnknown, boolean hasViews) {
    this(builder, beanDesc, beanDesc
        .getType(), properties, backRefs, ignorableProps, ignoreAllUnknown, hasViews);
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer src) {
    this(src, src._ignoreAllUnknown);
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer src, boolean ignoreAllUnknown) {
    super(src, ignoreAllUnknown);
    this._buildMethod = src._buildMethod;
    this._targetType = src._targetType;
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer src, NameTransformer unwrapper) {
    super(src, unwrapper);
    this._buildMethod = src._buildMethod;
    this._targetType = src._targetType;
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer src, ObjectIdReader oir) {
    super(src, oir);
    this._buildMethod = src._buildMethod;
    this._targetType = src._targetType;
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer src, Set<String> ignorableProps) {
    this(src, ignorableProps, src._includableProps);
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer src, Set<String> ignorableProps, Set<String> includableProps) {
    super(src, ignorableProps, includableProps);
    this._buildMethod = src._buildMethod;
    this._targetType = src._targetType;
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer src, BeanPropertyMap props) {
    super(src, props);
    this._buildMethod = src._buildMethod;
    this._targetType = src._targetType;
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer unwrapper) {
    return (JsonDeserializer<Object>)new BuilderBasedDeserializer(this, unwrapper);
  }
  
  public BeanDeserializerBase withObjectIdReader(ObjectIdReader oir) {
    return new BuilderBasedDeserializer(this, oir);
  }
  
  public BeanDeserializerBase withByNameInclusion(Set<String> ignorableProps, Set<String> includableProps) {
    return new BuilderBasedDeserializer(this, ignorableProps, includableProps);
  }
  
  public BeanDeserializerBase withIgnoreAllUnknown(boolean ignoreUnknown) {
    return new BuilderBasedDeserializer(this, ignoreUnknown);
  }
  
  public BeanDeserializerBase withBeanProperties(BeanPropertyMap props) {
    return new BuilderBasedDeserializer(this, props);
  }
  
  protected BeanDeserializerBase asArrayDeserializer() {
    SettableBeanProperty[] props = this._beanProperties.getPropertiesInInsertionOrder();
    return (BeanDeserializerBase)new BeanAsArrayBuilderDeserializer(this, this._targetType, props, this._buildMethod);
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return Boolean.FALSE;
  }
  
  protected Object finishBuild(DeserializationContext ctxt, Object builder) throws IOException {
    if (null == this._buildMethod)
      return builder; 
    try {
      return this._buildMethod.getMember().invoke(builder, (Object[])null);
    } catch (Exception e) {
      return wrapInstantiationProblem(e, ctxt);
    } 
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.isExpectedStartObjectToken()) {
      JsonToken t = p.nextToken();
      if (this._vanillaProcessing)
        return finishBuild(ctxt, vanillaDeserialize(p, ctxt, t)); 
      return finishBuild(ctxt, deserializeFromObject(p, ctxt));
    } 
    switch (p.currentTokenId()) {
      case 6:
        return finishBuild(ctxt, deserializeFromString(p, ctxt));
      case 7:
        return finishBuild(ctxt, deserializeFromNumber(p, ctxt));
      case 8:
        return finishBuild(ctxt, deserializeFromDouble(p, ctxt));
      case 12:
        return p.getEmbeddedObject();
      case 9:
      case 10:
        return finishBuild(ctxt, deserializeFromBoolean(p, ctxt));
      case 3:
        return _deserializeFromArray(p, ctxt);
      case 2:
      case 5:
        return finishBuild(ctxt, deserializeFromObject(p, ctxt));
    } 
    return ctxt.handleUnexpectedToken(getValueType(ctxt), p);
  }
  
  public Object deserialize(JsonParser p, DeserializationContext ctxt, Object value) throws IOException {
    JavaType valueType = this._targetType;
    Class<?> builderRawType = handledType();
    Class<?> instRawType = value.getClass();
    if (builderRawType.isAssignableFrom(instRawType))
      return ctxt.reportBadDefinition(valueType, String.format("Deserialization of %s by passing existing Builder (%s) instance not supported", new Object[] { valueType, builderRawType
              
              .getName() })); 
    return ctxt.reportBadDefinition(valueType, String.format("Deserialization of %s by passing existing instance (of %s) not supported", new Object[] { valueType, instRawType
            
            .getName() }));
  }
  
  private final Object vanillaDeserialize(JsonParser p, DeserializationContext ctxt, JsonToken t) throws IOException {
    Object bean = this._valueInstantiator.createUsingDefault(ctxt);
    for (; p.currentToken() == JsonToken.FIELD_NAME; p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty prop = this._beanProperties.find(propName);
      if (prop != null) {
        try {
          bean = prop.deserializeSetAndReturn(p, ctxt, bean);
        } catch (Exception e) {
          wrapAndThrow(e, bean, propName, ctxt);
        } 
      } else {
        handleUnknownVanilla(p, ctxt, bean, propName);
      } 
    } 
    return bean;
  }
  
  public Object deserializeFromObject(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (this._nonStandardCreation) {
      if (this._unwrappedPropertyHandler != null)
        return deserializeWithUnwrapped(p, ctxt); 
      if (this._externalTypeIdHandler != null)
        return deserializeWithExternalTypeId(p, ctxt); 
      return deserializeFromObjectUsingNonDefault(p, ctxt);
    } 
    Object bean = this._valueInstantiator.createUsingDefault(ctxt);
    if (this._injectables != null)
      injectValues(ctxt, bean); 
    if (this._needViewProcesing) {
      Class<?> view = ctxt.getActiveView();
      if (view != null)
        return deserializeWithView(p, ctxt, bean, view); 
    } 
    for (; p.currentToken() == JsonToken.FIELD_NAME; p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty prop = this._beanProperties.find(propName);
      if (prop != null) {
        try {
          bean = prop.deserializeSetAndReturn(p, ctxt, bean);
        } catch (Exception e) {
          wrapAndThrow(e, bean, propName, ctxt);
        } 
      } else {
        handleUnknownVanilla(p, ctxt, bean, propName);
      } 
    } 
    return bean;
  }
  
  protected Object _deserializeUsingPropertyBased(JsonParser p, DeserializationContext ctxt) throws IOException {
    Object builder;
    PropertyBasedCreator creator = this._propertyBasedCreator;
    PropertyValueBuffer buffer = creator.startBuilding(p, ctxt, this._objectIdReader);
    Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
    TokenBuffer unknown = null;
    JsonToken t = p.currentToken();
    for (; t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
      if (!buffer.readIdProperty(propName) || creatorProp != null)
        if (creatorProp != null) {
          if (activeView != null && !creatorProp.visibleInView(activeView)) {
            p.skipChildren();
          } else if (buffer.assignParameter(creatorProp, creatorProp.deserialize(p, ctxt))) {
            Object object;
            p.nextToken();
            try {
              object = creator.build(ctxt, buffer);
            } catch (Exception e) {
              wrapAndThrow(e, this._beanType.getRawClass(), propName, ctxt);
            } 
            if (object.getClass() != this._beanType.getRawClass())
              return handlePolymorphic(p, ctxt, p.streamReadConstraints(), object, unknown); 
            if (unknown != null)
              object = handleUnknownProperties(ctxt, object, unknown); 
            return _deserialize(p, ctxt, object);
          } 
        } else {
          SettableBeanProperty prop = this._beanProperties.find(propName);
          if (prop != null) {
            buffer.bufferProperty(prop, prop.deserialize(p, ctxt));
          } else if (IgnorePropertiesUtil.shouldIgnore(propName, this._ignorableProps, this._includableProps)) {
            handleIgnoredProperty(p, ctxt, handledType(), propName);
          } else if (this._anySetter != null) {
            buffer.bufferAnyProperty(this._anySetter, propName, this._anySetter.deserialize(p, ctxt));
          } else {
            if (unknown == null)
              unknown = ctxt.bufferForInputBuffering(p); 
            unknown.writeFieldName(propName);
            unknown.copyCurrentStructure(p);
          } 
        }  
    } 
    try {
      builder = creator.build(ctxt, buffer);
    } catch (Exception e) {
      builder = wrapInstantiationProblem(e, ctxt);
    } 
    if (unknown != null) {
      if (builder.getClass() != this._beanType.getRawClass())
        return handlePolymorphic((JsonParser)null, ctxt, p.streamReadConstraints(), builder, unknown); 
      return handleUnknownProperties(ctxt, builder, unknown);
    } 
    return builder;
  }
  
  protected final Object _deserialize(JsonParser p, DeserializationContext ctxt, Object builder) throws IOException {
    if (this._injectables != null)
      injectValues(ctxt, builder); 
    if (this._unwrappedPropertyHandler != null) {
      if (p.hasToken(JsonToken.START_OBJECT))
        p.nextToken(); 
      TokenBuffer tokens = ctxt.bufferForInputBuffering(p);
      tokens.writeStartObject();
      return deserializeWithUnwrapped(p, ctxt, builder, tokens);
    } 
    if (this._externalTypeIdHandler != null)
      return deserializeWithExternalTypeId(p, ctxt, builder); 
    if (this._needViewProcesing) {
      Class<?> view = ctxt.getActiveView();
      if (view != null)
        return deserializeWithView(p, ctxt, builder, view); 
    } 
    JsonToken t = p.currentToken();
    if (t == JsonToken.START_OBJECT)
      t = p.nextToken(); 
    for (; t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty prop = this._beanProperties.find(propName);
      if (prop != null) {
        try {
          builder = prop.deserializeSetAndReturn(p, ctxt, builder);
        } catch (Exception e) {
          wrapAndThrow(e, builder, propName, ctxt);
        } 
      } else {
        handleUnknownVanilla(p, ctxt, builder, propName);
      } 
    } 
    return builder;
  }
  
  protected Object _deserializeFromArray(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonDeserializer<Object> delegateDeser = this._arrayDelegateDeserializer;
    if (delegateDeser != null || (delegateDeser = this._delegateDeserializer) != null) {
      Object builder = this._valueInstantiator.createUsingArrayDelegate(ctxt, delegateDeser
          .deserialize(p, ctxt));
      if (this._injectables != null)
        injectValues(ctxt, builder); 
      return finishBuild(ctxt, builder);
    } 
    CoercionAction act = _findCoercionFromEmptyArray(ctxt);
    boolean unwrap = ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
    if (unwrap || act != CoercionAction.Fail) {
      JsonToken t = p.nextToken();
      if (t == JsonToken.END_ARRAY) {
        switch (act) {
          case AsEmpty:
            return getEmptyValue(ctxt);
          case AsNull:
          case TryConvert:
            return getNullValue(ctxt);
        } 
        return ctxt.handleUnexpectedToken(getValueType(ctxt), JsonToken.START_ARRAY, p, null, new Object[0]);
      } 
      if (unwrap) {
        Object value = deserialize(p, ctxt);
        if (p.nextToken() != JsonToken.END_ARRAY)
          handleMissingEndArrayForSingle(p, ctxt); 
        return value;
      } 
    } 
    return ctxt.handleUnexpectedToken(getValueType(ctxt), p);
  }
  
  protected final Object deserializeWithView(JsonParser p, DeserializationContext ctxt, Object bean, Class<?> activeView) throws IOException {
    JsonToken t = p.currentToken();
    for (; t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty prop = this._beanProperties.find(propName);
      if (prop != null) {
        if (!prop.visibleInView(activeView)) {
          p.skipChildren();
        } else {
          try {
            bean = prop.deserializeSetAndReturn(p, ctxt, bean);
          } catch (Exception e) {
            wrapAndThrow(e, bean, propName, ctxt);
          } 
        } 
      } else {
        handleUnknownVanilla(p, ctxt, bean, propName);
      } 
    } 
    return bean;
  }
  
  protected Object deserializeWithUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (this._delegateDeserializer != null)
      return this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p, ctxt)); 
    if (this._propertyBasedCreator != null)
      return deserializeUsingPropertyBasedWithUnwrapped(p, ctxt); 
    TokenBuffer tokens = ctxt.bufferForInputBuffering(p);
    tokens.writeStartObject();
    Object bean = this._valueInstantiator.createUsingDefault(ctxt);
    if (this._injectables != null)
      injectValues(ctxt, bean); 
    Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
    for (; p.currentToken() == JsonToken.FIELD_NAME; p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty prop = this._beanProperties.find(propName);
      if (prop != null) {
        if (activeView != null && !prop.visibleInView(activeView)) {
          p.skipChildren();
        } else {
          try {
            bean = prop.deserializeSetAndReturn(p, ctxt, bean);
          } catch (Exception e) {
            wrapAndThrow(e, bean, propName, ctxt);
          } 
        } 
      } else if (IgnorePropertiesUtil.shouldIgnore(propName, this._ignorableProps, this._includableProps)) {
        handleIgnoredProperty(p, ctxt, bean, propName);
      } else {
        tokens.writeFieldName(propName);
        tokens.copyCurrentStructure(p);
        if (this._anySetter != null)
          try {
            this._anySetter.deserializeAndSet(p, ctxt, bean, propName);
          } catch (Exception e) {
            wrapAndThrow(e, bean, propName, ctxt);
          }  
      } 
    } 
    tokens.writeEndObject();
    return this._unwrappedPropertyHandler.processUnwrapped(p, ctxt, bean, tokens);
  }
  
  protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
    PropertyBasedCreator creator = this._propertyBasedCreator;
    PropertyValueBuffer buffer = creator.startBuilding(p, ctxt, this._objectIdReader);
    TokenBuffer tokens = ctxt.bufferForInputBuffering(p);
    tokens.writeStartObject();
    Object builder = null;
    JsonToken t = p.currentToken();
    for (; t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String propName = p.currentName();
      p.nextToken();
      SettableBeanProperty creatorProp = creator.findCreatorProperty(propName);
      if (!buffer.readIdProperty(propName) || creatorProp != null)
        if (creatorProp != null) {
          if (buffer.assignParameter(creatorProp, creatorProp.deserialize(p, ctxt))) {
            t = p.nextToken();
            try {
              builder = creator.build(ctxt, buffer);
            } catch (Exception e) {
              wrapAndThrow(e, this._beanType.getRawClass(), propName, ctxt);
            } 
            if (builder.getClass() != this._beanType.getRawClass())
              return handlePolymorphic(p, ctxt, p.streamReadConstraints(), builder, tokens); 
            return deserializeWithUnwrapped(p, ctxt, builder, tokens);
          } 
        } else {
          SettableBeanProperty prop = this._beanProperties.find(propName);
          if (prop != null) {
            buffer.bufferProperty(prop, prop.deserialize(p, ctxt));
          } else if (IgnorePropertiesUtil.shouldIgnore(propName, this._ignorableProps, this._includableProps)) {
            handleIgnoredProperty(p, ctxt, handledType(), propName);
          } else {
            tokens.writeFieldName(propName);
            tokens.copyCurrentStructure(p);
            if (this._anySetter != null)
              buffer.bufferAnyProperty(this._anySetter, propName, this._anySetter.deserialize(p, ctxt)); 
          } 
        }  
    } 
    tokens.writeEndObject();
    if (builder == null)
      try {
        builder = creator.build(ctxt, buffer);
      } catch (Exception e) {
        return wrapInstantiationProblem(e, ctxt);
      }  
    return this._unwrappedPropertyHandler.processUnwrapped(p, ctxt, builder, tokens);
  }
  
  protected Object deserializeWithUnwrapped(JsonParser p, DeserializationContext ctxt, Object builder, TokenBuffer tokens) throws IOException {
    Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
    for (JsonToken t = p.currentToken(); t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String propName = p.currentName();
      SettableBeanProperty prop = this._beanProperties.find(propName);
      p.nextToken();
      if (prop != null) {
        if (activeView != null && !prop.visibleInView(activeView)) {
          p.skipChildren();
        } else {
          try {
            builder = prop.deserializeSetAndReturn(p, ctxt, builder);
          } catch (Exception e) {
            wrapAndThrow(e, builder, propName, ctxt);
          } 
        } 
      } else if (IgnorePropertiesUtil.shouldIgnore(propName, this._ignorableProps, this._includableProps)) {
        handleIgnoredProperty(p, ctxt, builder, propName);
      } else {
        tokens.writeFieldName(propName);
        tokens.copyCurrentStructure(p);
        if (this._anySetter != null)
          this._anySetter.deserializeAndSet(p, ctxt, builder, propName); 
      } 
    } 
    tokens.writeEndObject();
    return this._unwrappedPropertyHandler.processUnwrapped(p, ctxt, builder, tokens);
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (this._propertyBasedCreator != null)
      return deserializeUsingPropertyBasedWithExternalTypeId(p, ctxt); 
    return deserializeWithExternalTypeId(p, ctxt, this._valueInstantiator.createUsingDefault(ctxt));
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser p, DeserializationContext ctxt, Object bean) throws IOException {
    Class<?> activeView = this._needViewProcesing ? ctxt.getActiveView() : null;
    ExternalTypeHandler ext = this._externalTypeIdHandler.start();
    for (JsonToken t = p.currentToken(); t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String propName = p.currentName();
      t = p.nextToken();
      SettableBeanProperty prop = this._beanProperties.find(propName);
      if (prop != null) {
        if (t.isScalarValue())
          ext.handleTypePropertyValue(p, ctxt, propName, bean); 
        if (activeView != null && !prop.visibleInView(activeView)) {
          p.skipChildren();
        } else {
          try {
            bean = prop.deserializeSetAndReturn(p, ctxt, bean);
          } catch (Exception e) {
            wrapAndThrow(e, bean, propName, ctxt);
          } 
        } 
      } else if (IgnorePropertiesUtil.shouldIgnore(propName, this._ignorableProps, this._includableProps)) {
        handleIgnoredProperty(p, ctxt, bean, propName);
      } else if (!ext.handlePropertyValue(p, ctxt, propName, bean)) {
        if (this._anySetter != null) {
          try {
            this._anySetter.deserializeAndSet(p, ctxt, bean, propName);
          } catch (Exception e) {
            wrapAndThrow(e, bean, propName, ctxt);
          } 
        } else {
          handleUnknownProperty(p, ctxt, bean, propName);
        } 
      } 
    } 
    return ext.complete(p, ctxt, bean);
  }
  
  protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser p, DeserializationContext ctxt) throws IOException {
    JavaType t = this._targetType;
    return ctxt.reportBadDefinition(t, String.format("Deserialization (of %s) with Builder, External type id, @JsonCreator not yet implemented", new Object[] { t }));
  }
}
