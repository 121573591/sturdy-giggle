package org.openjdk.nashorn.internal.objects;

import java.util.Objects;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class DataPropertyDescriptor extends ScriptObject implements PropertyDescriptor {
  public Object configurable;
  
  public Object enumerable;
  
  public Object writable;
  
  public Object value;
  
  private static PropertyMap $nasgenmap$;
  
  DataPropertyDescriptor(boolean configurable, boolean enumerable, boolean writable, Object value, Global global) {
    super(global.getObjectPrototype(), $nasgenmap$);
    this.configurable = Boolean.valueOf(configurable);
    this.enumerable = Boolean.valueOf(enumerable);
    this.writable = Boolean.valueOf(writable);
    this.value = value;
  }
  
  public boolean isConfigurable() {
    return JSType.toBoolean(this.configurable);
  }
  
  public boolean isEnumerable() {
    return JSType.toBoolean(this.enumerable);
  }
  
  public boolean isWritable() {
    return JSType.toBoolean(this.writable);
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public ScriptFunction getGetter() {
    throw new UnsupportedOperationException("getter");
  }
  
  public ScriptFunction getSetter() {
    throw new UnsupportedOperationException("setter");
  }
  
  public void setConfigurable(boolean flag) {
    this.configurable = Boolean.valueOf(flag);
  }
  
  public void setEnumerable(boolean flag) {
    this.enumerable = Boolean.valueOf(flag);
  }
  
  public void setWritable(boolean flag) {
    this.writable = Boolean.valueOf(flag);
  }
  
  public void setValue(Object value) {
    this.value = value;
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
    if (sobj.has("writable")) {
      this.writable = Boolean.valueOf(JSType.toBoolean(sobj.get("writable")));
    } else {
      delete("writable", false);
    } 
    if (sobj.has("value")) {
      this.value = sobj.get("value");
    } else {
      delete("value", false);
    } 
    return this;
  }
  
  public int type() {
    return 1;
  }
  
  public boolean hasAndEquals(PropertyDescriptor otherDesc) {
    if (!(otherDesc instanceof DataPropertyDescriptor))
      return false; 
    DataPropertyDescriptor other = (DataPropertyDescriptor)otherDesc;
    return ((!has("configurable") || ScriptRuntime.sameValue(this.configurable, other.configurable)) && (
      !has("enumerable") || ScriptRuntime.sameValue(this.enumerable, other.enumerable)) && (
      !has("writable") || ScriptRuntime.sameValue(this.writable, other.writable)) && (
      !has("value") || ScriptRuntime.sameValue(this.value, other.value)));
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof DataPropertyDescriptor))
      return false; 
    DataPropertyDescriptor other = (DataPropertyDescriptor)obj;
    return (ScriptRuntime.sameValue(this.configurable, other.configurable) && 
      ScriptRuntime.sameValue(this.enumerable, other.enumerable) && 
      ScriptRuntime.sameValue(this.writable, other.writable) && 
      ScriptRuntime.sameValue(this.value, other.value));
  }
  
  public String toString() {
    return "[" + getClass().getSimpleName() + " {configurable=" + this.configurable + " enumerable=" + this.enumerable + " writable=" + this.writable + " value=" + this.value + "}]";
  }
  
  public int hashCode() {
    int hash = 5;
    hash = 43 * hash + Objects.hashCode(this.configurable);
    hash = 43 * hash + Objects.hashCode(this.enumerable);
    hash = 43 * hash + Objects.hashCode(this.writable);
    hash = 43 * hash + Objects.hashCode(this.value);
    return hash;
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_4
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
    //   42: dup
    //   43: ldc 'writable'
    //   45: iconst_0
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: dup
    //   60: ldc 'value'
    //   62: iconst_0
    //   63: ldc
    //   65: ldc
    //   67: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   70: invokeinterface add : (Ljava/lang/Object;)Z
    //   75: pop
    //   76: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   79: putstatic org/openjdk/nashorn/internal/objects/DataPropertyDescriptor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   82: return
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
  
  public Object G$writable() {
    return this.writable;
  }
  
  public void S$writable(Object paramObject) {
    this.writable = paramObject;
  }
  
  public Object G$value() {
    return this.value;
  }
  
  public void S$value(Object paramObject) {
    this.value = paramObject;
  }
}
