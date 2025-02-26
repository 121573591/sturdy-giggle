package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ViewMatcher;
import java.io.IOException;
import java.io.Serializable;

public abstract class SettableBeanProperty extends ConcreteBeanPropertyBase implements Serializable {
  protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER = (JsonDeserializer<Object>)new FailingDeserializer("No _valueDeserializer assigned");
  
  protected final PropertyName _propName;
  
  protected final JavaType _type;
  
  protected final PropertyName _wrapperName;
  
  protected final transient Annotations _contextAnnotations;
  
  protected final JsonDeserializer<Object> _valueDeserializer;
  
  protected final TypeDeserializer _valueTypeDeserializer;
  
  protected final NullValueProvider _nullProvider;
  
  protected String _managedReferenceName;
  
  protected ObjectIdInfo _objectIdInfo;
  
  protected ViewMatcher _viewMatcher;
  
  protected int _propertyIndex;
  
  protected SettableBeanProperty(BeanPropertyDefinition propDef, JavaType type, TypeDeserializer typeDeser, Annotations contextAnnotations) {
    this(propDef.getFullName(), type, propDef.getWrapperName(), typeDeser, contextAnnotations, propDef
        .getMetadata());
  }
  
  protected SettableBeanProperty(PropertyName propName, JavaType type, PropertyName wrapper, TypeDeserializer typeDeser, Annotations contextAnnotations, PropertyMetadata metadata) {
    super(metadata);
    this._propertyIndex = -1;
    if (propName == null) {
      this._propName = PropertyName.NO_NAME;
    } else {
      this._propName = propName.internSimpleName();
    } 
    this._type = type;
    this._wrapperName = wrapper;
    this._contextAnnotations = contextAnnotations;
    this._viewMatcher = null;
    if (typeDeser != null)
      typeDeser = typeDeser.forProperty((BeanProperty)this); 
    this._valueTypeDeserializer = typeDeser;
    this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
    this._nullProvider = (NullValueProvider)MISSING_VALUE_DESERIALIZER;
  }
  
  protected SettableBeanProperty(PropertyName propName, JavaType type, PropertyMetadata metadata, JsonDeserializer<Object> valueDeser) {
    super(metadata);
    this._propertyIndex = -1;
    if (propName == null) {
      this._propName = PropertyName.NO_NAME;
    } else {
      this._propName = propName.internSimpleName();
    } 
    this._type = type;
    this._wrapperName = null;
    this._contextAnnotations = null;
    this._viewMatcher = null;
    this._valueTypeDeserializer = null;
    this._valueDeserializer = valueDeser;
    this._nullProvider = (NullValueProvider)valueDeser;
  }
  
  protected SettableBeanProperty(SettableBeanProperty src) {
    super(src);
    this._propertyIndex = -1;
    this._propName = src._propName;
    this._type = src._type;
    this._wrapperName = src._wrapperName;
    this._contextAnnotations = src._contextAnnotations;
    this._valueDeserializer = src._valueDeserializer;
    this._valueTypeDeserializer = src._valueTypeDeserializer;
    this._managedReferenceName = src._managedReferenceName;
    this._propertyIndex = src._propertyIndex;
    this._viewMatcher = src._viewMatcher;
    this._objectIdInfo = src._objectIdInfo;
    this._nullProvider = src._nullProvider;
  }
  
  protected SettableBeanProperty(SettableBeanProperty src, JsonDeserializer<?> deser, NullValueProvider nuller) {
    super(src);
    JsonDeserializer<Object> jsonDeserializer;
    this._propertyIndex = -1;
    this._propName = src._propName;
    this._type = src._type;
    this._wrapperName = src._wrapperName;
    this._contextAnnotations = src._contextAnnotations;
    this._valueTypeDeserializer = src._valueTypeDeserializer;
    this._managedReferenceName = src._managedReferenceName;
    this._propertyIndex = src._propertyIndex;
    if (deser == null) {
      this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
    } else {
      this._valueDeserializer = (JsonDeserializer)deser;
    } 
    this._viewMatcher = src._viewMatcher;
    this._objectIdInfo = src._objectIdInfo;
    if (nuller == MISSING_VALUE_DESERIALIZER)
      jsonDeserializer = this._valueDeserializer; 
    this._nullProvider = (NullValueProvider)jsonDeserializer;
  }
  
  protected SettableBeanProperty(SettableBeanProperty src, PropertyName newName) {
    super(src);
    this._propertyIndex = -1;
    this._propName = newName;
    this._type = src._type;
    this._wrapperName = src._wrapperName;
    this._contextAnnotations = src._contextAnnotations;
    this._valueDeserializer = src._valueDeserializer;
    this._valueTypeDeserializer = src._valueTypeDeserializer;
    this._managedReferenceName = src._managedReferenceName;
    this._propertyIndex = src._propertyIndex;
    this._viewMatcher = src._viewMatcher;
    this._objectIdInfo = src._objectIdInfo;
    this._nullProvider = src._nullProvider;
  }
  
  public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> paramJsonDeserializer);
  
  public abstract SettableBeanProperty withName(PropertyName paramPropertyName);
  
  public SettableBeanProperty withSimpleName(String simpleName) {
    PropertyName n = (this._propName == null) ? new PropertyName(simpleName) : this._propName.withSimpleName(simpleName);
    return (n == this._propName) ? this : withName(n);
  }
  
  public abstract SettableBeanProperty withNullProvider(NullValueProvider paramNullValueProvider);
  
  public void setManagedReferenceName(String n) {
    this._managedReferenceName = n;
  }
  
  public void setObjectIdInfo(ObjectIdInfo objectIdInfo) {
    this._objectIdInfo = objectIdInfo;
  }
  
  public void setViews(Class<?>[] views) {
    if (views == null) {
      this._viewMatcher = null;
    } else {
      this._viewMatcher = ViewMatcher.construct(views);
    } 
  }
  
  public void assignIndex(int index) {
    if (this._propertyIndex != -1)
      throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + index); 
    this._propertyIndex = index;
  }
  
  public void fixAccess(DeserializationConfig config) {}
  
  public void markAsIgnorable() {}
  
  public boolean isIgnorable() {
    return false;
  }
  
  public final String getName() {
    return this._propName.getSimpleName();
  }
  
  public PropertyName getFullName() {
    return this._propName;
  }
  
  public JavaType getType() {
    return this._type;
  }
  
  public PropertyName getWrapperName() {
    return this._wrapperName;
  }
  
  public abstract AnnotatedMember getMember();
  
  public abstract <A extends java.lang.annotation.Annotation> A getAnnotation(Class<A> paramClass);
  
  public <A extends java.lang.annotation.Annotation> A getContextAnnotation(Class<A> acls) {
    return (A)this._contextAnnotations.get(acls);
  }
  
  public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
    if (isRequired()) {
      objectVisitor.property((BeanProperty)this);
    } else {
      objectVisitor.optionalProperty((BeanProperty)this);
    } 
  }
  
  protected Class<?> getDeclaringClass() {
    return getMember().getDeclaringClass();
  }
  
  public String getManagedReferenceName() {
    return this._managedReferenceName;
  }
  
  public ObjectIdInfo getObjectIdInfo() {
    return this._objectIdInfo;
  }
  
  public boolean hasValueDeserializer() {
    return (this._valueDeserializer != null && this._valueDeserializer != MISSING_VALUE_DESERIALIZER);
  }
  
  public boolean hasValueTypeDeserializer() {
    return (this._valueTypeDeserializer != null);
  }
  
  public JsonDeserializer<Object> getValueDeserializer() {
    JsonDeserializer<Object> deser = this._valueDeserializer;
    if (deser == MISSING_VALUE_DESERIALIZER)
      return null; 
    return deser;
  }
  
  public TypeDeserializer getValueTypeDeserializer() {
    return this._valueTypeDeserializer;
  }
  
  public NullValueProvider getNullValueProvider() {
    return this._nullProvider;
  }
  
  public boolean visibleInView(Class<?> activeView) {
    return (this._viewMatcher == null || this._viewMatcher.isVisibleForView(activeView));
  }
  
  public boolean hasViews() {
    return (this._viewMatcher != null);
  }
  
  public int getPropertyIndex() {
    return this._propertyIndex;
  }
  
  public int getCreatorIndex() {
    throw new IllegalStateException(String.format("Internal error: no creator index for property '%s' (of type %s)", new Object[] { getName(), getClass().getName() }));
  }
  
  public Object getInjectableValueId() {
    return null;
  }
  
  public boolean isInjectionOnly() {
    return false;
  }
  
  public abstract void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) throws IOException;
  
  public abstract Object deserializeSetAndReturn(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) throws IOException;
  
  public abstract void set(Object paramObject1, Object paramObject2) throws IOException;
  
  public abstract Object setAndReturn(Object paramObject1, Object paramObject2) throws IOException;
  
  public final Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.hasToken(JsonToken.VALUE_NULL))
      return this._nullProvider.getNullValue(ctxt); 
    if (this._valueTypeDeserializer != null)
      return this._valueDeserializer.deserializeWithType(p, ctxt, this._valueTypeDeserializer); 
    Object value = this._valueDeserializer.deserialize(p, ctxt);
    if (value == null)
      value = this._nullProvider.getNullValue(ctxt); 
    return value;
  }
  
  public final Object deserializeWith(JsonParser p, DeserializationContext ctxt, Object toUpdate) throws IOException {
    if (p.hasToken(JsonToken.VALUE_NULL)) {
      if (NullsConstantProvider.isSkipper(this._nullProvider))
        return toUpdate; 
      return this._nullProvider.getNullValue(ctxt);
    } 
    if (this._valueTypeDeserializer != null) {
      JavaType subType = ctxt.getTypeFactory().constructType(toUpdate.getClass());
      JsonDeserializer<Object> subTypeValueDeserializer = ctxt.findContextualValueDeserializer(subType, (BeanProperty)this);
      return subTypeValueDeserializer.deserialize(p, ctxt, toUpdate);
    } 
    Object value = this._valueDeserializer.deserialize(p, ctxt, toUpdate);
    if (value == null) {
      if (NullsConstantProvider.isSkipper(this._nullProvider))
        return toUpdate; 
      value = this._nullProvider.getNullValue(ctxt);
    } 
    return value;
  }
  
  protected void _throwAsIOE(JsonParser p, Exception e, Object value) throws IOException {
    if (e instanceof IllegalArgumentException) {
      String actType = ClassUtil.classNameOf(value);
      StringBuilder msg = (new StringBuilder("Problem deserializing property '")).append(getName()).append("' (expected type: ").append(getType()).append("; actual type: ").append(actType).append(")");
      String origMsg = ClassUtil.exceptionMessage(e);
      if (origMsg != null) {
        msg.append(", problem: ")
          .append(origMsg);
      } else {
        msg.append(" (no error message provided)");
      } 
      throw JsonMappingException.from(p, msg.toString(), e);
    } 
    _throwAsIOE(p, e);
  }
  
  protected IOException _throwAsIOE(JsonParser p, Exception e) throws IOException {
    ClassUtil.throwIfIOE(e);
    ClassUtil.throwIfRTE(e);
    Throwable th = ClassUtil.getRootCause(e);
    throw JsonMappingException.from(p, ClassUtil.exceptionMessage(th), th);
  }
  
  @Deprecated
  protected IOException _throwAsIOE(Exception e) throws IOException {
    return _throwAsIOE((JsonParser)null, e);
  }
  
  protected void _throwAsIOE(Exception e, Object value) throws IOException {
    _throwAsIOE((JsonParser)null, e, value);
  }
  
  public String toString() {
    return "[property '" + getName() + "']";
  }
  
  public static abstract class Delegating extends SettableBeanProperty {
    protected final SettableBeanProperty delegate;
    
    protected Delegating(SettableBeanProperty d) {
      super(d);
      this.delegate = d;
    }
    
    protected abstract SettableBeanProperty withDelegate(SettableBeanProperty param1SettableBeanProperty);
    
    protected SettableBeanProperty _with(SettableBeanProperty newDelegate) {
      if (newDelegate == this.delegate)
        return this; 
      return withDelegate(newDelegate);
    }
    
    public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
      return _with(this.delegate.withValueDeserializer(deser));
    }
    
    public SettableBeanProperty withName(PropertyName newName) {
      return _with(this.delegate.withName(newName));
    }
    
    public SettableBeanProperty withNullProvider(NullValueProvider nva) {
      return _with(this.delegate.withNullProvider(nva));
    }
    
    public void assignIndex(int index) {
      this.delegate.assignIndex(index);
    }
    
    public void fixAccess(DeserializationConfig config) {
      this.delegate.fixAccess(config);
    }
    
    protected Class<?> getDeclaringClass() {
      return this.delegate.getDeclaringClass();
    }
    
    public String getManagedReferenceName() {
      return this.delegate.getManagedReferenceName();
    }
    
    public ObjectIdInfo getObjectIdInfo() {
      return this.delegate.getObjectIdInfo();
    }
    
    public boolean hasValueDeserializer() {
      return this.delegate.hasValueDeserializer();
    }
    
    public boolean hasValueTypeDeserializer() {
      return this.delegate.hasValueTypeDeserializer();
    }
    
    public JsonDeserializer<Object> getValueDeserializer() {
      return this.delegate.getValueDeserializer();
    }
    
    public TypeDeserializer getValueTypeDeserializer() {
      return this.delegate.getValueTypeDeserializer();
    }
    
    public boolean visibleInView(Class<?> activeView) {
      return this.delegate.visibleInView(activeView);
    }
    
    public boolean hasViews() {
      return this.delegate.hasViews();
    }
    
    public int getPropertyIndex() {
      return this.delegate.getPropertyIndex();
    }
    
    public int getCreatorIndex() {
      return this.delegate.getCreatorIndex();
    }
    
    public Object getInjectableValueId() {
      return this.delegate.getInjectableValueId();
    }
    
    public boolean isInjectionOnly() {
      return this.delegate.isInjectionOnly();
    }
    
    public AnnotatedMember getMember() {
      return this.delegate.getMember();
    }
    
    public <A extends java.lang.annotation.Annotation> A getAnnotation(Class<A> acls) {
      return this.delegate.getAnnotation(acls);
    }
    
    public SettableBeanProperty getDelegate() {
      return this.delegate;
    }
    
    public void deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
      this.delegate.deserializeAndSet(p, ctxt, instance);
    }
    
    public Object deserializeSetAndReturn(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
      return this.delegate.deserializeSetAndReturn(p, ctxt, instance);
    }
    
    public void set(Object instance, Object value) throws IOException {
      this.delegate.set(instance, value);
    }
    
    public Object setAndReturn(Object instance, Object value) throws IOException {
      return this.delegate.setAndReturn(instance, value);
    }
  }
}
