package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;

public class CreatorProperty extends SettableBeanProperty {
  private static final long serialVersionUID = 1L;
  
  protected final AnnotatedParameter _annotated;
  
  protected final JacksonInject.Value _injectableValue;
  
  protected SettableBeanProperty _fallbackSetter;
  
  protected final int _creatorIndex;
  
  protected boolean _ignorable;
  
  protected CreatorProperty(PropertyName name, JavaType type, PropertyName wrapperName, TypeDeserializer typeDeser, Annotations contextAnnotations, AnnotatedParameter param, int index, JacksonInject.Value injectable, PropertyMetadata metadata) {
    super(name, type, wrapperName, typeDeser, contextAnnotations, metadata);
    this._annotated = param;
    this._creatorIndex = index;
    this._injectableValue = injectable;
    this._fallbackSetter = null;
  }
  
  @Deprecated
  public CreatorProperty(PropertyName name, JavaType type, PropertyName wrapperName, TypeDeserializer typeDeser, Annotations contextAnnotations, AnnotatedParameter param, int index, Object injectableValueId, PropertyMetadata metadata) {
    this(name, type, wrapperName, typeDeser, contextAnnotations, param, index, (injectableValueId == null) ? null : 
        
        JacksonInject.Value.construct(injectableValueId, null), metadata);
  }
  
  public static CreatorProperty construct(PropertyName name, JavaType type, PropertyName wrapperName, TypeDeserializer typeDeser, Annotations contextAnnotations, AnnotatedParameter param, int index, JacksonInject.Value injectable, PropertyMetadata metadata) {
    return new CreatorProperty(name, type, wrapperName, typeDeser, contextAnnotations, param, index, injectable, metadata);
  }
  
  protected CreatorProperty(CreatorProperty src, PropertyName newName) {
    super(src, newName);
    this._annotated = src._annotated;
    this._injectableValue = src._injectableValue;
    this._fallbackSetter = src._fallbackSetter;
    this._creatorIndex = src._creatorIndex;
    this._ignorable = src._ignorable;
  }
  
  protected CreatorProperty(CreatorProperty src, JsonDeserializer<?> deser, NullValueProvider nva) {
    super(src, deser, nva);
    this._annotated = src._annotated;
    this._injectableValue = src._injectableValue;
    this._fallbackSetter = src._fallbackSetter;
    this._creatorIndex = src._creatorIndex;
    this._ignorable = src._ignorable;
  }
  
  public SettableBeanProperty withName(PropertyName newName) {
    return new CreatorProperty(this, newName);
  }
  
  public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
    if (this._valueDeserializer == deser)
      return this; 
    NullValueProvider nvp = (this._valueDeserializer == this._nullProvider) ? (NullValueProvider)deser : this._nullProvider;
    return new CreatorProperty(this, deser, nvp);
  }
  
  public SettableBeanProperty withNullProvider(NullValueProvider nva) {
    return new CreatorProperty(this, this._valueDeserializer, nva);
  }
  
  public void fixAccess(DeserializationConfig config) {
    if (this._fallbackSetter != null)
      this._fallbackSetter.fixAccess(config); 
  }
  
  public void setFallbackSetter(SettableBeanProperty fallbackSetter) {
    this._fallbackSetter = fallbackSetter;
  }
  
  public void markAsIgnorable() {
    this._ignorable = true;
  }
  
  public boolean isIgnorable() {
    return this._ignorable;
  }
  
  @Deprecated
  public Object findInjectableValue(DeserializationContext context, Object beanInstance) throws JsonMappingException {
    if (this._injectableValue == null)
      context.reportBadDefinition(ClassUtil.classOf(beanInstance), 
          String.format("Property %s (type %s) has no injectable value id configured", new Object[] { ClassUtil.name(getName()), ClassUtil.classNameOf(this) })); 
    return context.findInjectableValue(this._injectableValue.getId(), (BeanProperty)this, beanInstance);
  }
  
  @Deprecated
  public void inject(DeserializationContext context, Object beanInstance) throws IOException {
    set(beanInstance, findInjectableValue(context, beanInstance));
  }
  
  public <A extends java.lang.annotation.Annotation> A getAnnotation(Class<A> acls) {
    if (this._annotated == null)
      return null; 
    return (A)this._annotated.getAnnotation(acls);
  }
  
  public AnnotatedMember getMember() {
    return (AnnotatedMember)this._annotated;
  }
  
  public int getCreatorIndex() {
    return this._creatorIndex;
  }
  
  public void deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
    _verifySetter();
    this._fallbackSetter.set(instance, deserialize(p, ctxt));
  }
  
  public Object deserializeSetAndReturn(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
    _verifySetter();
    return this._fallbackSetter.setAndReturn(instance, deserialize(p, ctxt));
  }
  
  public void set(Object instance, Object value) throws IOException {
    _verifySetter();
    this._fallbackSetter.set(instance, value);
  }
  
  public Object setAndReturn(Object instance, Object value) throws IOException {
    _verifySetter();
    return this._fallbackSetter.setAndReturn(instance, value);
  }
  
  public PropertyMetadata getMetadata() {
    PropertyMetadata md = super.getMetadata();
    if (this._fallbackSetter != null)
      return md.withMergeInfo(this._fallbackSetter.getMetadata().getMergeInfo()); 
    return md;
  }
  
  public Object getInjectableValueId() {
    return (this._injectableValue == null) ? null : this._injectableValue.getId();
  }
  
  public boolean isInjectionOnly() {
    return (this._injectableValue != null && !this._injectableValue.willUseInput(true));
  }
  
  public String toString() {
    return "[creator property, name " + ClassUtil.name(getName()) + "; inject id '" + getInjectableValueId() + "']";
  }
  
  private final void _verifySetter() throws IOException {
    if (this._fallbackSetter == null)
      _reportMissingSetter((JsonParser)null, (DeserializationContext)null); 
  }
  
  private void _reportMissingSetter(JsonParser p, DeserializationContext ctxt) throws IOException {
    String msg = "No fallback setter/field defined for creator property " + ClassUtil.name(getName());
    if (ctxt != null) {
      ctxt.reportBadDefinition(getType(), msg);
    } else {
      throw InvalidDefinitionException.from(p, msg, getType());
    } 
  }
}
