package org.openjdk.nashorn.internal.runtime;

public interface PropertyDescriptor {
  public static final int GENERIC = 0;
  
  public static final int DATA = 1;
  
  public static final int ACCESSOR = 2;
  
  public static final String CONFIGURABLE = "configurable";
  
  public static final String ENUMERABLE = "enumerable";
  
  public static final String WRITABLE = "writable";
  
  public static final String VALUE = "value";
  
  public static final String GET = "get";
  
  public static final String SET = "set";
  
  boolean isConfigurable();
  
  boolean isEnumerable();
  
  boolean isWritable();
  
  Object getValue();
  
  ScriptFunction getGetter();
  
  ScriptFunction getSetter();
  
  void setConfigurable(boolean paramBoolean);
  
  void setEnumerable(boolean paramBoolean);
  
  void setWritable(boolean paramBoolean);
  
  void setValue(Object paramObject);
  
  void setGetter(Object paramObject);
  
  void setSetter(Object paramObject);
  
  PropertyDescriptor fillFrom(ScriptObject paramScriptObject);
  
  int type();
  
  boolean has(Object paramObject);
  
  boolean hasAndEquals(PropertyDescriptor paramPropertyDescriptor);
}
