package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ValueInstantiator {
  public ValueInstantiator createContextual(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
    return this;
  }
  
  public Class<?> getValueClass() {
    return Object.class;
  }
  
  public String getValueTypeDesc() {
    Class<?> cls = getValueClass();
    if (cls == null)
      return "UNKNOWN"; 
    return cls.getName();
  }
  
  public boolean canInstantiate() {
    return (canCreateUsingDefault() || 
      canCreateUsingDelegate() || canCreateUsingArrayDelegate() || 
      canCreateFromObjectWith() || canCreateFromString() || 
      canCreateFromInt() || canCreateFromLong() || 
      canCreateFromDouble() || canCreateFromBoolean());
  }
  
  public boolean canCreateFromString() {
    return false;
  }
  
  public boolean canCreateFromInt() {
    return false;
  }
  
  public boolean canCreateFromLong() {
    return false;
  }
  
  public boolean canCreateFromBigInteger() {
    return false;
  }
  
  public boolean canCreateFromDouble() {
    return false;
  }
  
  public boolean canCreateFromBigDecimal() {
    return false;
  }
  
  public boolean canCreateFromBoolean() {
    return false;
  }
  
  public boolean canCreateUsingDefault() {
    return (getDefaultCreator() != null);
  }
  
  public boolean canCreateUsingDelegate() {
    return false;
  }
  
  public boolean canCreateUsingArrayDelegate() {
    return false;
  }
  
  public boolean canCreateFromObjectWith() {
    return false;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig config) {
    return null;
  }
  
  public JavaType getDelegateType(DeserializationConfig config) {
    return null;
  }
  
  public JavaType getArrayDelegateType(DeserializationConfig config) {
    return null;
  }
  
  public Object createUsingDefault(DeserializationContext ctxt) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no default no-arguments constructor found", new Object[0]);
  }
  
  public Object createFromObjectWith(DeserializationContext ctxt, Object[] args) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no creator with arguments specified", new Object[0]);
  }
  
  public Object createUsingDefaultOrWithoutArguments(DeserializationContext ctxt) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "neither default (no-arguments) nor with-arguments Creator found", new Object[0]);
  }
  
  public Object createFromObjectWith(DeserializationContext ctxt, SettableBeanProperty[] props, PropertyValueBuffer buffer) throws IOException {
    return createFromObjectWith(ctxt, buffer.getParameters(props));
  }
  
  public Object createUsingDelegate(DeserializationContext ctxt, Object delegate) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no delegate creator specified", new Object[0]);
  }
  
  public Object createUsingArrayDelegate(DeserializationContext ctxt, Object delegate) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no array delegate creator specified", new Object[0]);
  }
  
  public Object createFromString(DeserializationContext ctxt, String value) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, ctxt.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", new Object[] { value });
  }
  
  public Object createFromInt(DeserializationContext ctxt, int value) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no int/Int-argument constructor/factory method to deserialize from Number value (%s)", new Object[] { Integer.valueOf(value) });
  }
  
  public Object createFromLong(DeserializationContext ctxt, long value) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no long/Long-argument constructor/factory method to deserialize from Number value (%s)", new Object[] { Long.valueOf(value) });
  }
  
  public Object createFromBigInteger(DeserializationContext ctxt, BigInteger value) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no BigInteger-argument constructor/factory method to deserialize from Number value (%s)", new Object[] { value });
  }
  
  public Object createFromDouble(DeserializationContext ctxt, double value) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no double/Double-argument constructor/factory method to deserialize from Number value (%s)", new Object[] { Double.valueOf(value) });
  }
  
  public Object createFromBigDecimal(DeserializationContext ctxt, BigDecimal value) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no BigDecimal/double/Double-argument constructor/factory method to deserialize from Number value (%s)", new Object[] { value });
  }
  
  public Object createFromBoolean(DeserializationContext ctxt, boolean value) throws IOException {
    return ctxt.handleMissingInstantiator(getValueClass(), this, null, "no boolean/Boolean-argument constructor/factory method to deserialize from boolean value (%s)", new Object[] { Boolean.valueOf(value) });
  }
  
  public AnnotatedWithParams getDefaultCreator() {
    return null;
  }
  
  public AnnotatedWithParams getDelegateCreator() {
    return null;
  }
  
  public AnnotatedWithParams getArrayDelegateCreator() {
    return null;
  }
  
  public AnnotatedWithParams getWithArgsCreator() {
    return null;
  }
  
  @Deprecated
  protected Object _createFromStringFallbacks(DeserializationContext ctxt, String value) throws IOException {
    if (value.isEmpty() && 
      ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT))
      return null; 
    if (canCreateFromBoolean())
      if (ctxt.findCoercionAction(LogicalType.Boolean, Boolean.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
        String str = value.trim();
        if ("true".equals(str))
          return createFromBoolean(ctxt, true); 
        if ("false".equals(str))
          return createFromBoolean(ctxt, false); 
      }  
    return ctxt.handleMissingInstantiator(getValueClass(), this, ctxt.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", new Object[] { value });
  }
  
  public static interface Gettable {
    ValueInstantiator getValueInstantiator();
  }
  
  public static class Base extends ValueInstantiator implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected final Class<?> _valueType;
    
    public Base(Class<?> type) {
      this._valueType = type;
    }
    
    public Base(JavaType type) {
      this._valueType = type.getRawClass();
    }
    
    public String getValueTypeDesc() {
      return this._valueType.getName();
    }
    
    public Class<?> getValueClass() {
      return this._valueType;
    }
  }
  
  public static class Delegating extends ValueInstantiator implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected final ValueInstantiator _delegate;
    
    protected Delegating(ValueInstantiator delegate) {
      this._delegate = delegate;
    }
    
    public ValueInstantiator createContextual(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
      ValueInstantiator d = this._delegate.createContextual(ctxt, beanDesc);
      return (d == this._delegate) ? this : new Delegating(d);
    }
    
    protected ValueInstantiator delegate() {
      return this._delegate;
    }
    
    public Class<?> getValueClass() {
      return delegate().getValueClass();
    }
    
    public String getValueTypeDesc() {
      return delegate().getValueTypeDesc();
    }
    
    public boolean canInstantiate() {
      return delegate().canInstantiate();
    }
    
    public boolean canCreateFromString() {
      return delegate().canCreateFromString();
    }
    
    public boolean canCreateFromInt() {
      return delegate().canCreateFromInt();
    }
    
    public boolean canCreateFromLong() {
      return delegate().canCreateFromLong();
    }
    
    public boolean canCreateFromDouble() {
      return delegate().canCreateFromDouble();
    }
    
    public boolean canCreateFromBoolean() {
      return delegate().canCreateFromBoolean();
    }
    
    public boolean canCreateUsingDefault() {
      return delegate().canCreateUsingDefault();
    }
    
    public boolean canCreateUsingDelegate() {
      return delegate().canCreateUsingDelegate();
    }
    
    public boolean canCreateUsingArrayDelegate() {
      return delegate().canCreateUsingArrayDelegate();
    }
    
    public boolean canCreateFromObjectWith() {
      return delegate().canCreateFromObjectWith();
    }
    
    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig config) {
      return delegate().getFromObjectArguments(config);
    }
    
    public JavaType getDelegateType(DeserializationConfig config) {
      return delegate().getDelegateType(config);
    }
    
    public JavaType getArrayDelegateType(DeserializationConfig config) {
      return delegate().getArrayDelegateType(config);
    }
    
    public Object createUsingDefault(DeserializationContext ctxt) throws IOException {
      return delegate().createUsingDefault(ctxt);
    }
    
    public Object createFromObjectWith(DeserializationContext ctxt, Object[] args) throws IOException {
      return delegate().createFromObjectWith(ctxt, args);
    }
    
    public Object createFromObjectWith(DeserializationContext ctxt, SettableBeanProperty[] props, PropertyValueBuffer buffer) throws IOException {
      return delegate().createFromObjectWith(ctxt, props, buffer);
    }
    
    public Object createUsingDelegate(DeserializationContext ctxt, Object delegate) throws IOException {
      return delegate().createUsingDelegate(ctxt, delegate);
    }
    
    public Object createUsingArrayDelegate(DeserializationContext ctxt, Object delegate) throws IOException {
      return delegate().createUsingArrayDelegate(ctxt, delegate);
    }
    
    public Object createFromString(DeserializationContext ctxt, String value) throws IOException {
      return delegate().createFromString(ctxt, value);
    }
    
    public Object createFromInt(DeserializationContext ctxt, int value) throws IOException {
      return delegate().createFromInt(ctxt, value);
    }
    
    public Object createFromLong(DeserializationContext ctxt, long value) throws IOException {
      return delegate().createFromLong(ctxt, value);
    }
    
    public Object createFromBigInteger(DeserializationContext ctxt, BigInteger value) throws IOException {
      return delegate().createFromBigInteger(ctxt, value);
    }
    
    public Object createFromDouble(DeserializationContext ctxt, double value) throws IOException {
      return delegate().createFromDouble(ctxt, value);
    }
    
    public Object createFromBigDecimal(DeserializationContext ctxt, BigDecimal value) throws IOException {
      return delegate().createFromBigDecimal(ctxt, value);
    }
    
    public Object createFromBoolean(DeserializationContext ctxt, boolean value) throws IOException {
      return delegate().createFromBoolean(ctxt, value);
    }
    
    public AnnotatedWithParams getDefaultCreator() {
      return delegate().getDefaultCreator();
    }
    
    public AnnotatedWithParams getDelegateCreator() {
      return delegate().getDelegateCreator();
    }
    
    public AnnotatedWithParams getArrayDelegateCreator() {
      return delegate().getArrayDelegateCreator();
    }
    
    public AnnotatedWithParams getWithArgsCreator() {
      return delegate().getWithArgsCreator();
    }
  }
}
