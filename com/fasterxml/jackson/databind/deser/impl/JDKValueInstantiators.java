package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class JDKValueInstantiators {
  public static ValueInstantiator findStdValueInstantiator(DeserializationConfig config, Class<?> raw) {
    if (raw == JsonLocation.class)
      return (ValueInstantiator)new JsonLocationInstantiator(); 
    if (Collection.class.isAssignableFrom(raw)) {
      if (raw == ArrayList.class)
        return (ValueInstantiator)ArrayListInstantiator.INSTANCE; 
      if (Collections.EMPTY_SET.getClass() == raw)
        return (ValueInstantiator)new ConstantValueInstantiator(Collections.EMPTY_SET); 
      if (Collections.EMPTY_LIST.getClass() == raw)
        return (ValueInstantiator)new ConstantValueInstantiator(Collections.EMPTY_LIST); 
    } else if (Map.class.isAssignableFrom(raw)) {
      if (raw == LinkedHashMap.class)
        return (ValueInstantiator)LinkedHashMapInstantiator.INSTANCE; 
      if (raw == HashMap.class)
        return (ValueInstantiator)HashMapInstantiator.INSTANCE; 
      if (Collections.EMPTY_MAP.getClass() == raw)
        return (ValueInstantiator)new ConstantValueInstantiator(Collections.EMPTY_MAP); 
    } 
    return null;
  }
  
  private static class ArrayListInstantiator extends ValueInstantiator.Base implements Serializable {
    private static final long serialVersionUID = 2L;
    
    public static final ArrayListInstantiator INSTANCE = new ArrayListInstantiator();
    
    public ArrayListInstantiator() {
      super(ArrayList.class);
    }
    
    public boolean canInstantiate() {
      return true;
    }
    
    public boolean canCreateUsingDefault() {
      return true;
    }
    
    public Object createUsingDefault(DeserializationContext ctxt) throws IOException {
      return new ArrayList();
    }
  }
  
  private static class HashMapInstantiator extends ValueInstantiator.Base implements Serializable {
    private static final long serialVersionUID = 2L;
    
    public static final HashMapInstantiator INSTANCE = new HashMapInstantiator();
    
    public HashMapInstantiator() {
      super(HashMap.class);
    }
    
    public boolean canInstantiate() {
      return true;
    }
    
    public boolean canCreateUsingDefault() {
      return true;
    }
    
    public Object createUsingDefault(DeserializationContext ctxt) throws IOException {
      return new HashMap<>();
    }
  }
  
  private static class LinkedHashMapInstantiator extends ValueInstantiator.Base implements Serializable {
    private static final long serialVersionUID = 2L;
    
    public static final LinkedHashMapInstantiator INSTANCE = new LinkedHashMapInstantiator();
    
    public LinkedHashMapInstantiator() {
      super(LinkedHashMap.class);
    }
    
    public boolean canInstantiate() {
      return true;
    }
    
    public boolean canCreateUsingDefault() {
      return true;
    }
    
    public Object createUsingDefault(DeserializationContext ctxt) throws IOException {
      return new LinkedHashMap<>();
    }
  }
  
  private static class ConstantValueInstantiator extends ValueInstantiator.Base implements Serializable {
    private static final long serialVersionUID = 2L;
    
    protected final Object _value;
    
    public ConstantValueInstantiator(Object value) {
      super(value.getClass());
      this._value = value;
    }
    
    public boolean canInstantiate() {
      return true;
    }
    
    public boolean canCreateUsingDefault() {
      return true;
    }
    
    public Object createUsingDefault(DeserializationContext ctxt) throws IOException {
      return this._value;
    }
  }
}
