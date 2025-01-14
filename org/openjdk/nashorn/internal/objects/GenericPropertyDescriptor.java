package org.openjdk.nashorn.internal.objects;

import java.util.Objects;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class GenericPropertyDescriptor extends ScriptObject implements PropertyDescriptor {
  public Object configurable;
  
  public Object enumerable;
  
  private static PropertyMap $nasgenmap$;
  
  GenericPropertyDescriptor(boolean configurable, boolean enumerable, Global global) {
    super(global.getObjectPrototype(), $nasgenmap$);
    this.configurable = Boolean.valueOf(configurable);
    this.enumerable = Boolean.valueOf(enumerable);
  }
  
  public boolean isConfigurable() {
    return JSType.toBoolean(this.configurable);
  }
  
  public boolean isEnumerable() {
    return JSType.toBoolean(this.enumerable);
  }
  
  public boolean isWritable() {
    return false;
  }
  
  public Object getValue() {
    throw new UnsupportedOperationException("value");
  }
  
  public ScriptFunction getGetter() {
    throw new UnsupportedOperationException("get");
  }
  
  public ScriptFunction getSetter() {
    throw new UnsupportedOperationException("set");
  }
  
  public void setConfigurable(boolean flag) {
    this.configurable = Boolean.valueOf(flag);
  }
  
  public void setEnumerable(boolean flag) {
    this.enumerable = Boolean.valueOf(flag);
  }
  
  public void setWritable(boolean flag) {
    throw new UnsupportedOperationException("set writable");
  }
  
  public void setValue(Object value) {
    throw new UnsupportedOperationException("set value");
  }
  
  public void setGetter(Object getter) {
    throw new UnsupportedOperationException("set getter");
  }
  
  public void setSetter(Object setter) {
    throw new UnsupportedOperationException("set setter");
  }
  
  public PropertyDescriptor fillFrom(ScriptObject sobj) {
    if (sobj.has("configurable")) {
      this.configurable = Boolean.valueOf(JSType.toBoolean(sobj.get("configurable")));
    } else {
      delete("configurable", false);
    } 
    if (sobj.has("enumerable")) {
      this.enumerable = Boolean.valueOf(JSType.toBoolean(sobj.get("enumerable")));
    } else {
      delete("enumerable", false);
    } 
    return this;
  }
  
  public int type() {
    return 0;
  }
  
  public boolean hasAndEquals(PropertyDescriptor other) {
    if (has("configurable") && other.has("configurable") && 
      isConfigurable() != other.isConfigurable())
      return false; 
    if (has("enumerable") && other.has("enumerable"))
      return (isEnumerable() == other.isEnumerable()); 
    return true;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof GenericPropertyDescriptor))
      return false; 
    GenericPropertyDescriptor other = (GenericPropertyDescriptor)obj;
    return (ScriptRuntime.sameValue(this.configurable, other.configurable) && 
      ScriptRuntime.sameValue(this.enumerable, other.enumerable));
  }
  
  public String toString() {
    return "[" + getClass().getSimpleName() + " {configurable=" + this.configurable + " enumerable=" + this.enumerable + "}]";
  }
  
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.configurable);
    hash = 97 * hash + Objects.hashCode(this.enumerable);
    return hash;
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_2
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'configurable'
    //   11: iconst_0
    //   12: ldc
    //   14: ldc
    //   16: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: dup
    //   26: ldc 'enumerable'
    //   28: iconst_0
    //   29: ldc
    //   31: ldc
    //   33: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   36: invokeinterface add : (Ljava/lang/Object;)Z
    //   41: pop
    //   42: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   45: putstatic org/openjdk/nashorn/internal/objects/GenericPropertyDescriptor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   48: return
  }
  
  public Object G$configurable() {
    return this.configurable;
  }
  
  public void S$configurable(Object paramObject) {
    this.configurable = paramObject;
  }
  
  public Object G$enumerable() {
    return this.enumerable;
  }
  
  public void S$enumerable(Object paramObject) {
    this.enumerable = paramObject;
  }
}
