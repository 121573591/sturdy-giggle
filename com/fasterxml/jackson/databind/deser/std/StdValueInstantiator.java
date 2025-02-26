package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class StdValueInstantiator extends ValueInstantiator implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final String _valueTypeDesc;
  
  protected final Class<?> _valueClass;
  
  protected AnnotatedWithParams _defaultCreator;
  
  protected AnnotatedWithParams _withArgsCreator;
  
  protected SettableBeanProperty[] _constructorArguments;
  
  protected JavaType _delegateType;
  
  protected AnnotatedWithParams _delegateCreator;
  
  protected SettableBeanProperty[] _delegateArguments;
  
  protected JavaType _arrayDelegateType;
  
  protected AnnotatedWithParams _arrayDelegateCreator;
  
  protected SettableBeanProperty[] _arrayDelegateArguments;
  
  protected AnnotatedWithParams _fromStringCreator;
  
  protected AnnotatedWithParams _fromIntCreator;
  
  protected AnnotatedWithParams _fromLongCreator;
  
  protected AnnotatedWithParams _fromBigIntegerCreator;
  
  protected AnnotatedWithParams _fromDoubleCreator;
  
  protected AnnotatedWithParams _fromBigDecimalCreator;
  
  protected AnnotatedWithParams _fromBooleanCreator;
  
  @Deprecated
  public StdValueInstantiator(DeserializationConfig config, Class<?> valueType) {
    this._valueTypeDesc = ClassUtil.nameOf(valueType);
    this._valueClass = (valueType == null) ? Object.class : valueType;
  }
  
  public StdValueInstantiator(DeserializationConfig config, JavaType valueType) {
    this._valueTypeDesc = (valueType == null) ? "UNKNOWN TYPE" : valueType.toString();
    this._valueClass = (valueType == null) ? Object.class : valueType.getRawClass();
  }
  
  protected StdValueInstantiator(StdValueInstantiator src) {
    this._valueTypeDesc = src._valueTypeDesc;
    this._valueClass = src._valueClass;
    this._defaultCreator = src._defaultCreator;
    this._constructorArguments = src._constructorArguments;
    this._withArgsCreator = src._withArgsCreator;
    this._delegateType = src._delegateType;
    this._delegateCreator = src._delegateCreator;
    this._delegateArguments = src._delegateArguments;
    this._arrayDelegateType = src._arrayDelegateType;
    this._arrayDelegateCreator = src._arrayDelegateCreator;
    this._arrayDelegateArguments = src._arrayDelegateArguments;
    this._fromStringCreator = src._fromStringCreator;
    this._fromIntCreator = src._fromIntCreator;
    this._fromLongCreator = src._fromLongCreator;
    this._fromBigIntegerCreator = src._fromBigIntegerCreator;
    this._fromDoubleCreator = src._fromDoubleCreator;
    this._fromBigDecimalCreator = src._fromBigDecimalCreator;
    this._fromBooleanCreator = src._fromBooleanCreator;
  }
  
  public void configureFromObjectSettings(AnnotatedWithParams defaultCreator, AnnotatedWithParams delegateCreator, JavaType delegateType, SettableBeanProperty[] delegateArgs, AnnotatedWithParams withArgsCreator, SettableBeanProperty[] constructorArgs) {
    this._defaultCreator = defaultCreator;
    this._delegateCreator = delegateCreator;
    this._delegateType = delegateType;
    this._delegateArguments = delegateArgs;
    this._withArgsCreator = withArgsCreator;
    this._constructorArguments = constructorArgs;
  }
  
  public void configureFromArraySettings(AnnotatedWithParams arrayDelegateCreator, JavaType arrayDelegateType, SettableBeanProperty[] arrayDelegateArgs) {
    this._arrayDelegateCreator = arrayDelegateCreator;
    this._arrayDelegateType = arrayDelegateType;
    this._arrayDelegateArguments = arrayDelegateArgs;
  }
  
  public void configureFromStringCreator(AnnotatedWithParams creator) {
    this._fromStringCreator = creator;
  }
  
  public void configureFromIntCreator(AnnotatedWithParams creator) {
    this._fromIntCreator = creator;
  }
  
  public void configureFromLongCreator(AnnotatedWithParams creator) {
    this._fromLongCreator = creator;
  }
  
  public void configureFromBigIntegerCreator(AnnotatedWithParams creator) {
    this._fromBigIntegerCreator = creator;
  }
  
  public void configureFromDoubleCreator(AnnotatedWithParams creator) {
    this._fromDoubleCreator = creator;
  }
  
  public void configureFromBigDecimalCreator(AnnotatedWithParams creator) {
    this._fromBigDecimalCreator = creator;
  }
  
  public void configureFromBooleanCreator(AnnotatedWithParams creator) {
    this._fromBooleanCreator = creator;
  }
  
  public String getValueTypeDesc() {
    return this._valueTypeDesc;
  }
  
  public Class<?> getValueClass() {
    return this._valueClass;
  }
  
  public boolean canCreateFromString() {
    return (this._fromStringCreator != null);
  }
  
  public boolean canCreateFromInt() {
    return (this._fromIntCreator != null);
  }
  
  public boolean canCreateFromLong() {
    return (this._fromLongCreator != null);
  }
  
  public boolean canCreateFromBigInteger() {
    return (this._fromBigIntegerCreator != null);
  }
  
  public boolean canCreateFromDouble() {
    return (this._fromDoubleCreator != null);
  }
  
  public boolean canCreateFromBigDecimal() {
    return (this._fromBigDecimalCreator != null);
  }
  
  public boolean canCreateFromBoolean() {
    return (this._fromBooleanCreator != null);
  }
  
  public boolean canCreateUsingDefault() {
    return (this._defaultCreator != null);
  }
  
  public boolean canCreateUsingDelegate() {
    return (this._delegateType != null);
  }
  
  public boolean canCreateUsingArrayDelegate() {
    return (this._arrayDelegateType != null);
  }
  
  public boolean canCreateFromObjectWith() {
    return (this._withArgsCreator != null);
  }
  
  public boolean canInstantiate() {
    return (canCreateUsingDefault() || 
      canCreateUsingDelegate() || canCreateUsingArrayDelegate() || 
      canCreateFromObjectWith() || canCreateFromString() || 
      canCreateFromInt() || canCreateFromLong() || 
      canCreateFromDouble() || canCreateFromBoolean());
  }
  
  public JavaType getDelegateType(DeserializationConfig config) {
    return this._delegateType;
  }
  
  public JavaType getArrayDelegateType(DeserializationConfig config) {
    return this._arrayDelegateType;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig config) {
    return this._constructorArguments;
  }
  
  public Object createUsingDefault(DeserializationContext ctxt) throws IOException {
    if (this._defaultCreator == null)
      return super.createUsingDefault(ctxt); 
    try {
      return this._defaultCreator.call();
    } catch (Exception e) {
      return ctxt.handleInstantiationProblem(this._valueClass, null, (Throwable)rewrapCtorProblem(ctxt, e));
    } 
  }
  
  public Object createFromObjectWith(DeserializationContext ctxt, Object[] args) throws IOException {
    if (this._withArgsCreator == null)
      return super.createFromObjectWith(ctxt, args); 
    try {
      return this._withArgsCreator.call(args);
    } catch (Exception e) {
      return ctxt.handleInstantiationProblem(this._valueClass, args, (Throwable)rewrapCtorProblem(ctxt, e));
    } 
  }
  
  public Object createUsingDefaultOrWithoutArguments(DeserializationContext ctxt) throws IOException {
    if (this._defaultCreator != null)
      return createUsingDefault(ctxt); 
    if (this._withArgsCreator != null)
      return createFromObjectWith(ctxt, new Object[this._constructorArguments.length]); 
    return super.createUsingDefaultOrWithoutArguments(ctxt);
  }
  
  public Object createUsingDelegate(DeserializationContext ctxt, Object delegate) throws IOException {
    if (this._delegateCreator == null && 
      this._arrayDelegateCreator != null)
      return _createUsingDelegate(this._arrayDelegateCreator, this._arrayDelegateArguments, ctxt, delegate); 
    return _createUsingDelegate(this._delegateCreator, this._delegateArguments, ctxt, delegate);
  }
  
  public Object createUsingArrayDelegate(DeserializationContext ctxt, Object delegate) throws IOException {
    if (this._arrayDelegateCreator == null && 
      this._delegateCreator != null)
      return createUsingDelegate(ctxt, delegate); 
    return _createUsingDelegate(this._arrayDelegateCreator, this._arrayDelegateArguments, ctxt, delegate);
  }
  
  public Object createFromString(DeserializationContext ctxt, String value) throws IOException {
    if (this._fromStringCreator != null)
      try {
        return this._fromStringCreator.call1(value);
      } catch (Exception t) {
        return ctxt.handleInstantiationProblem(this._fromStringCreator.getDeclaringClass(), value, (Throwable)
            rewrapCtorProblem(ctxt, t));
      }  
    return super.createFromString(ctxt, value);
  }
  
  public Object createFromInt(DeserializationContext ctxt, int value) throws IOException {
    if (this._fromIntCreator != null) {
      Object arg = Integer.valueOf(value);
      try {
        return this._fromIntCreator.call1(arg);
      } catch (Exception t0) {
        return ctxt.handleInstantiationProblem(this._fromIntCreator.getDeclaringClass(), arg, (Throwable)
            rewrapCtorProblem(ctxt, t0));
      } 
    } 
    if (this._fromLongCreator != null) {
      Object arg = Long.valueOf(value);
      try {
        return this._fromLongCreator.call1(arg);
      } catch (Exception t0) {
        return ctxt.handleInstantiationProblem(this._fromLongCreator.getDeclaringClass(), arg, (Throwable)
            rewrapCtorProblem(ctxt, t0));
      } 
    } 
    if (this._fromBigIntegerCreator != null) {
      Object arg = BigInteger.valueOf(value);
      try {
        return this._fromBigIntegerCreator.call1(arg);
      } catch (Exception t0) {
        return ctxt.handleInstantiationProblem(this._fromBigIntegerCreator.getDeclaringClass(), arg, (Throwable)
            rewrapCtorProblem(ctxt, t0));
      } 
    } 
    return super.createFromInt(ctxt, value);
  }
  
  public Object createFromLong(DeserializationContext ctxt, long value) throws IOException {
    if (this._fromLongCreator != null) {
      Long arg = Long.valueOf(value);
      try {
        return this._fromLongCreator.call1(arg);
      } catch (Exception t0) {
        return ctxt.handleInstantiationProblem(this._fromLongCreator.getDeclaringClass(), arg, (Throwable)
            rewrapCtorProblem(ctxt, t0));
      } 
    } 
    if (this._fromBigIntegerCreator != null) {
      BigInteger arg = BigInteger.valueOf(value);
      try {
        return this._fromBigIntegerCreator.call1(arg);
      } catch (Exception t0) {
        return ctxt.handleInstantiationProblem(this._fromBigIntegerCreator.getDeclaringClass(), arg, (Throwable)
            rewrapCtorProblem(ctxt, t0));
      } 
    } 
    return super.createFromLong(ctxt, value);
  }
  
  public Object createFromBigInteger(DeserializationContext ctxt, BigInteger value) throws IOException {
    if (this._fromBigIntegerCreator != null)
      try {
        return this._fromBigIntegerCreator.call1(value);
      } catch (Exception t) {
        return ctxt.handleInstantiationProblem(this._fromBigIntegerCreator.getDeclaringClass(), value, (Throwable)
            rewrapCtorProblem(ctxt, t));
      }  
    return super.createFromBigInteger(ctxt, value);
  }
  
  public Object createFromDouble(DeserializationContext ctxt, double value) throws IOException {
    if (this._fromDoubleCreator != null) {
      Double arg = Double.valueOf(value);
      try {
        return this._fromDoubleCreator.call1(arg);
      } catch (Exception t0) {
        return ctxt.handleInstantiationProblem(this._fromDoubleCreator.getDeclaringClass(), arg, (Throwable)
            rewrapCtorProblem(ctxt, t0));
      } 
    } 
    if (this._fromBigDecimalCreator != null) {
      BigDecimal arg = BigDecimal.valueOf(value);
      try {
        return this._fromBigDecimalCreator.call1(arg);
      } catch (Exception t0) {
        return ctxt.handleInstantiationProblem(this._fromBigDecimalCreator.getDeclaringClass(), arg, (Throwable)
            rewrapCtorProblem(ctxt, t0));
      } 
    } 
    return super.createFromDouble(ctxt, value);
  }
  
  public Object createFromBigDecimal(DeserializationContext ctxt, BigDecimal value) throws IOException {
    if (this._fromBigDecimalCreator != null)
      try {
        return this._fromBigDecimalCreator.call1(value);
      } catch (Exception t) {
        return ctxt.handleInstantiationProblem(this._fromBigDecimalCreator.getDeclaringClass(), value, (Throwable)
            rewrapCtorProblem(ctxt, t));
      }  
    if (this._fromDoubleCreator != null) {
      Double dbl = tryConvertToDouble(value);
      if (dbl != null)
        try {
          return this._fromDoubleCreator.call1(dbl);
        } catch (Exception t0) {
          return ctxt.handleInstantiationProblem(this._fromDoubleCreator.getDeclaringClass(), dbl, (Throwable)
              rewrapCtorProblem(ctxt, t0));
        }  
    } 
    return super.createFromBigDecimal(ctxt, value);
  }
  
  static Double tryConvertToDouble(BigDecimal value) {
    double doubleValue = value.doubleValue();
    return Double.isInfinite(doubleValue) ? null : Double.valueOf(doubleValue);
  }
  
  public Object createFromBoolean(DeserializationContext ctxt, boolean value) throws IOException {
    if (this._fromBooleanCreator == null)
      return super.createFromBoolean(ctxt, value); 
    Boolean arg = Boolean.valueOf(value);
    try {
      return this._fromBooleanCreator.call1(arg);
    } catch (Exception t0) {
      return ctxt.handleInstantiationProblem(this._fromBooleanCreator.getDeclaringClass(), arg, (Throwable)
          rewrapCtorProblem(ctxt, t0));
    } 
  }
  
  public AnnotatedWithParams getDelegateCreator() {
    return this._delegateCreator;
  }
  
  public AnnotatedWithParams getArrayDelegateCreator() {
    return this._arrayDelegateCreator;
  }
  
  public AnnotatedWithParams getDefaultCreator() {
    return this._defaultCreator;
  }
  
  public AnnotatedWithParams getWithArgsCreator() {
    return this._withArgsCreator;
  }
  
  @Deprecated
  protected JsonMappingException wrapException(Throwable t) {
    for (Throwable curr = t; curr != null; curr = curr.getCause()) {
      if (curr instanceof JsonMappingException)
        return (JsonMappingException)curr; 
    } 
    return new JsonMappingException(null, "Instantiation of " + 
        getValueTypeDesc() + " value failed: " + ClassUtil.exceptionMessage(t), t);
  }
  
  @Deprecated
  protected JsonMappingException unwrapAndWrapException(DeserializationContext ctxt, Throwable t) {
    for (Throwable curr = t; curr != null; curr = curr.getCause()) {
      if (curr instanceof JsonMappingException)
        return (JsonMappingException)curr; 
    } 
    return ctxt.instantiationException(getValueClass(), t);
  }
  
  protected JsonMappingException wrapAsJsonMappingException(DeserializationContext ctxt, Throwable t) {
    if (t instanceof JsonMappingException)
      return (JsonMappingException)t; 
    return ctxt.instantiationException(getValueClass(), t);
  }
  
  protected JsonMappingException rewrapCtorProblem(DeserializationContext ctxt, Throwable t) {
    if (t instanceof ExceptionInInitializerError || t instanceof java.lang.reflect.InvocationTargetException) {
      Throwable cause = t.getCause();
      if (cause != null)
        t = cause; 
    } 
    return wrapAsJsonMappingException(ctxt, t);
  }
  
  private Object _createUsingDelegate(AnnotatedWithParams delegateCreator, SettableBeanProperty[] delegateArguments, DeserializationContext ctxt, Object delegate) throws IOException {
    if (delegateCreator == null)
      throw new IllegalStateException("No delegate constructor for " + getValueTypeDesc()); 
    try {
      if (delegateArguments == null)
        return delegateCreator.call1(delegate); 
      int len = delegateArguments.length;
      Object[] args = new Object[len];
      for (int i = 0; i < len; i++) {
        SettableBeanProperty prop = delegateArguments[i];
        if (prop == null) {
          args[i] = delegate;
        } else {
          args[i] = ctxt.findInjectableValue(prop.getInjectableValueId(), (BeanProperty)prop, null);
        } 
      } 
      return delegateCreator.call(args);
    } catch (Exception t) {
      throw rewrapCtorProblem(ctxt, t);
    } 
  }
}
