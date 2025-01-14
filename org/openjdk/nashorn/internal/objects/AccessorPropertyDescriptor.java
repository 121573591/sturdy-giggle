package org.openjdk.nashorn.internal.objects;

import java.util.Objects;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class AccessorPropertyDescriptor extends ScriptObject implements PropertyDescriptor {
  public Object configurable;
  
  public Object enumerable;
  
  public Object get;
  
  public Object set;
  
  private static PropertyMap $nasgenmap$;
  
  AccessorPropertyDescriptor(boolean configurable, boolean enumerable, Object get, Object set, Global global) {
    super(global.getObjectPrototype(), $nasgenmap$);
    this.configurable = Boolean.valueOf(configurable);
    this.enumerable = Boolean.valueOf(enumerable);
    this.get = get;
    this.set = set;
  }
  
  public boolean isConfigurable() {
    return JSType.toBoolean(this.configurable);
  }
  
  public boolean isEnumerable() {
    return JSType.toBoolean(this.enumerable);
  }
  
  public boolean isWritable() {
    return true;
  }
  
  public Object getValue() {
    throw new UnsupportedOperationException("value");
  }
  
  public ScriptFunction getGetter() {
    return (this.get instanceof ScriptFunction) ? (ScriptFunction)this.get : null;
  }
  
  public ScriptFunction getSetter() {
    return (this.set instanceof ScriptFunction) ? (ScriptFunction)this.set : null;
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
    this.get = getter;
  }
  
  public void setSetter(Object setter) {
    this.set = setter;
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
    if (sobj.has("get")) {
      Object getter = sobj.get("get");
      if (getter == ScriptRuntime.UNDEFINED || getter instanceof ScriptFunction) {
        this.get = getter;
      } else {
        throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(getter) });
      } 
    } else {
      delete("get", false);
    } 
    if (sobj.has("set")) {
      Object setter = sobj.get("set");
      if (setter == ScriptRuntime.UNDEFINED || setter instanceof ScriptFunction) {
        this.set = setter;
      } else {
        throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(setter) });
      } 
    } else {
      delete("set", false);
    } 
    return this;
  }
  
  public int type() {
    return 2;
  }
  
  public boolean hasAndEquals(PropertyDescriptor otherDesc) {
    if (!(otherDesc instanceof AccessorPropertyDescriptor))
      return false; 
    AccessorPropertyDescriptor other = (AccessorPropertyDescriptor)otherDesc;
    return ((!has("configurable") || ScriptRuntime.sameValue(this.configurable, other.configurable)) && (
      !has("enumerable") || ScriptRuntime.sameValue(this.enumerable, other.enumerable)) && (
      !has("get") || ScriptRuntime.sameValue(this.get, other.get)) && (
      !has("set") || ScriptRuntime.sameValue(this.set, other.set)));
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof AccessorPropertyDescriptor))
      return false; 
    AccessorPropertyDescriptor other = (AccessorPropertyDescriptor)obj;
    return (ScriptRuntime.sameValue(this.configurable, other.configurable) && 
      ScriptRuntime.sameValue(this.enumerable, other.enumerable) && 
      ScriptRuntime.sameValue(this.get, other.get) && 
      ScriptRuntime.sameValue(this.set, other.set));
  }
  
  public String toString() {
    return "[" + getClass().getSimpleName() + " {configurable=" + this.configurable + " enumerable=" + this.enumerable + " getter=" + this.get + " setter=" + this.set + "}]";
  }
  
  public int hashCode() {
    int hash = 7;
    hash = 41 * hash + Objects.hashCode(this.configurable);
    hash = 41 * hash + Objects.hashCode(this.enumerable);
    hash = 41 * hash + Objects.hashCode(this.get);
    hash = 41 * hash + Objects.hashCode(this.set);
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
    //   43: ldc 'get'
    //   45: iconst_0
    //   46: ldc
    //   48: ldc
    //   50: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   53: invokeinterface add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: dup
    //   60: ldc 'set'
    //   62: iconst_0
    //   63: ldc
    //   65: ldc
    //   67: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   70: invokeinterface add : (Ljava/lang/Object;)Z
    //   75: pop
    //   76: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   79: putstatic org/openjdk/nashorn/internal/objects/AccessorPropertyDescriptor.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
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
  
  public Object G$get() {
    return this.get;
  }
  
  public void S$get(Object paramObject) {
    this.get = paramObject;
  }
  
  public Object G$set() {
    return this.set;
  }
  
  public void S$set(Object paramObject) {
    this.set = paramObject;
  }
}
