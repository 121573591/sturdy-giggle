package org.openjdk.nashorn.api.scripting;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractJSObject implements JSObject {
  public Object call(Object thiz, Object... args) {
    throw new UnsupportedOperationException("call");
  }
  
  public Object newObject(Object... args) {
    throw new UnsupportedOperationException("newObject");
  }
  
  public Object eval(String s) {
    throw new UnsupportedOperationException("eval");
  }
  
  public Object getMember(String name) {
    Objects.requireNonNull(name);
    return null;
  }
  
  public Object getSlot(int index) {
    return null;
  }
  
  public boolean hasMember(String name) {
    Objects.requireNonNull(name);
    return false;
  }
  
  public boolean hasSlot(int slot) {
    return false;
  }
  
  public void removeMember(String name) {
    Objects.requireNonNull(name);
  }
  
  public void setMember(String name, Object value) {
    Objects.requireNonNull(name);
  }
  
  public void setSlot(int index, Object value) {}
  
  public Set<String> keySet() {
    return Collections.emptySet();
  }
  
  public Collection<Object> values() {
    return Collections.emptySet();
  }
  
  public boolean isInstance(Object instance) {
    return false;
  }
  
  public boolean isInstanceOf(Object clazz) {
    if (clazz instanceof JSObject)
      return ((JSObject)clazz).isInstance(this); 
    return false;
  }
  
  public String getClassName() {
    return getClass().getName();
  }
  
  public boolean isFunction() {
    return false;
  }
  
  public boolean isStrictFunction() {
    return false;
  }
  
  public boolean isArray() {
    return false;
  }
  
  @Deprecated
  public double toNumber() {
    return Double.NaN;
  }
  
  @Deprecated
  public static Object getDefaultValue(JSObject jsobj, Class<?> hint) {
    return jsobj.getDefaultValue(hint);
  }
}
