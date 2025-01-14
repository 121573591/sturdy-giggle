package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;

public final class UserAccessorProperty extends SpillProperty {
  private static final long serialVersionUID = -5928687246526840321L;
  
  static final class Accessors {
    Object getter;
    
    Object setter;
    
    Accessors(Object getter, Object setter) {
      set(getter, setter);
    }
    
    final void set(Object getter, Object setter) {
      this.getter = getter;
      this.setter = setter;
    }
    
    public String toString() {
      return "[getter=" + this.getter + " setter=" + this.setter + "]";
    }
  }
  
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private static final MethodHandle INVOKE_OBJECT_GETTER = findOwnMH_S("invokeObjectGetter", Object.class, new Class[] { Accessors.class, MethodHandle.class, Object.class });
  
  private static final MethodHandle INVOKE_INT_GETTER = findOwnMH_S("invokeIntGetter", int.class, new Class[] { Accessors.class, MethodHandle.class, int.class, Object.class });
  
  private static final MethodHandle INVOKE_NUMBER_GETTER = findOwnMH_S("invokeNumberGetter", double.class, new Class[] { Accessors.class, MethodHandle.class, int.class, Object.class });
  
  private static final MethodHandle INVOKE_OBJECT_SETTER = findOwnMH_S("invokeObjectSetter", void.class, new Class[] { Accessors.class, MethodHandle.class, String.class, Object.class, Object.class });
  
  private static final MethodHandle INVOKE_INT_SETTER = findOwnMH_S("invokeIntSetter", void.class, new Class[] { Accessors.class, MethodHandle.class, String.class, Object.class, int.class });
  
  private static final MethodHandle INVOKE_NUMBER_SETTER = findOwnMH_S("invokeNumberSetter", void.class, new Class[] { Accessors.class, MethodHandle.class, String.class, Object.class, double.class });
  
  private static final Object OBJECT_GETTER_INVOKER_KEY = new Object();
  
  private static MethodHandle getObjectGetterInvoker() {
    return Context.getGlobal().getDynamicInvoker(OBJECT_GETTER_INVOKER_KEY, () -> getINVOKE_UA_GETTER(Object.class, -1));
  }
  
  static MethodHandle getINVOKE_UA_GETTER(Class<?> returnType, int programPoint) {
    if (UnwarrantedOptimismException.isValid(programPoint)) {
      int flags = 0x88 | programPoint << 15;
      return Bootstrap.createDynamicInvoker("", flags, returnType, new Class[] { Object.class, Object.class });
    } 
    return Bootstrap.createDynamicCallInvoker(Object.class, new Class[] { Object.class, Object.class });
  }
  
  private static final Object OBJECT_SETTER_INVOKER_KEY = new Object();
  
  private static MethodHandle getObjectSetterInvoker() {
    return Context.getGlobal().getDynamicInvoker(OBJECT_SETTER_INVOKER_KEY, () -> getINVOKE_UA_SETTER(Object.class));
  }
  
  static MethodHandle getINVOKE_UA_SETTER(Class<?> valueType) {
    return Bootstrap.createDynamicCallInvoker(void.class, new Class[] { Object.class, Object.class, valueType });
  }
  
  UserAccessorProperty(Object key, int flags, int slot) {
    super(key, flags | 0x1000, slot);
  }
  
  private UserAccessorProperty(UserAccessorProperty property) {
    super(property);
  }
  
  private UserAccessorProperty(UserAccessorProperty property, Class<?> newType) {
    super(property, newType);
  }
  
  public Property copy() {
    return new UserAccessorProperty(this);
  }
  
  public Property copy(Class<?> newType) {
    return new UserAccessorProperty(this, newType);
  }
  
  void setAccessors(ScriptObject sobj, PropertyMap map, Accessors gs) {
    try {
      super.getSetter(Object.class, map).invokeExact(sobj, gs);
    } catch (Error|RuntimeException t) {
      throw t;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  Accessors getAccessors(ScriptObject sobj) {
    try {
      Object gs = super.getGetter(Object.class).invokeExact(sobj);
      return (Accessors)gs;
    } catch (Error|RuntimeException t) {
      throw t;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  protected Class<?> getLocalType() {
    return Object.class;
  }
  
  public boolean hasGetterFunction(ScriptObject sobj) {
    return ((getAccessors(sobj)).getter != null);
  }
  
  public boolean hasSetterFunction(ScriptObject sobj) {
    return ((getAccessors(sobj)).setter != null);
  }
  
  public int getIntValue(ScriptObject self, ScriptObject owner) {
    return ((Integer)getObjectValue(self, owner)).intValue();
  }
  
  public double getDoubleValue(ScriptObject self, ScriptObject owner) {
    return ((Double)getObjectValue(self, owner)).doubleValue();
  }
  
  public Object getObjectValue(ScriptObject self, ScriptObject owner) {
    try {
      return invokeObjectGetter(getAccessors((owner != null) ? owner : self), getObjectGetterInvoker(), self);
    } catch (Error|RuntimeException t) {
      throw t;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public void setValue(ScriptObject self, ScriptObject owner, int value, boolean strict) {
    setValue(self, owner, Integer.valueOf(value), strict);
  }
  
  public void setValue(ScriptObject self, ScriptObject owner, double value, boolean strict) {
    setValue(self, owner, Double.valueOf(value), strict);
  }
  
  public void setValue(ScriptObject self, ScriptObject owner, Object value, boolean strict) {
    try {
      invokeObjectSetter(getAccessors((owner != null) ? owner : self), getObjectSetterInvoker(), strict ? getKey().toString() : null, self, value);
    } catch (Error|RuntimeException t) {
      throw t;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public MethodHandle getGetter(Class<?> type) {
    return Lookup.filterReturnType(INVOKE_OBJECT_GETTER, type);
  }
  
  public MethodHandle getOptimisticGetter(Class<?> type, int programPoint) {
    if (type == int.class)
      return INVOKE_INT_GETTER; 
    if (type == double.class)
      return INVOKE_NUMBER_GETTER; 
    assert type == Object.class;
    return INVOKE_OBJECT_GETTER;
  }
  
  void initMethodHandles(Class<?> structure) {
    throw new UnsupportedOperationException();
  }
  
  public ScriptFunction getGetterFunction(ScriptObject sobj) {
    Object value = (getAccessors(sobj)).getter;
    return (value instanceof ScriptFunction) ? (ScriptFunction)value : null;
  }
  
  public MethodHandle getSetter(Class<?> type, PropertyMap currentMap) {
    if (type == int.class)
      return INVOKE_INT_SETTER; 
    if (type == double.class)
      return INVOKE_NUMBER_SETTER; 
    assert type == Object.class;
    return INVOKE_OBJECT_SETTER;
  }
  
  public ScriptFunction getSetterFunction(ScriptObject sobj) {
    Object value = (getAccessors(sobj)).setter;
    return (value instanceof ScriptFunction) ? (ScriptFunction)value : null;
  }
  
  MethodHandle getAccessorsGetter() {
    return super.getGetter(Object.class).asType(MethodType.methodType(Accessors.class, Object.class));
  }
  
  private static Object invokeObjectGetter(Accessors gs, MethodHandle invoker, Object self) throws Throwable {
    Object func = gs.getter;
    if (func instanceof ScriptFunction)
      return invoker.invokeExact(func, self); 
    return ScriptRuntime.UNDEFINED;
  }
  
  private static int invokeIntGetter(Accessors gs, MethodHandle invoker, int programPoint, Object self) throws Throwable {
    Object func = gs.getter;
    if (func instanceof ScriptFunction)
      return invoker.invokeExact(func, self); 
    throw new UnwarrantedOptimismException(ScriptRuntime.UNDEFINED, programPoint);
  }
  
  private static double invokeNumberGetter(Accessors gs, MethodHandle invoker, int programPoint, Object self) throws Throwable {
    Object func = gs.getter;
    if (func instanceof ScriptFunction)
      return invoker.invokeExact(func, self); 
    throw new UnwarrantedOptimismException(ScriptRuntime.UNDEFINED, programPoint);
  }
  
  private static void invokeObjectSetter(Accessors gs, MethodHandle invoker, String name, Object self, Object value) throws Throwable {
    Object func = gs.setter;
    if (func instanceof ScriptFunction) {
      invoker.invokeExact(func, self, value);
    } else if (name != null) {
      throw ECMAErrors.typeError("property.has.no.setter", new String[] { name, ScriptRuntime.safeToString(self) });
    } 
  }
  
  private static void invokeIntSetter(Accessors gs, MethodHandle invoker, String name, Object self, int value) throws Throwable {
    Object func = gs.setter;
    if (func instanceof ScriptFunction) {
      invoker.invokeExact(func, self, value);
    } else if (name != null) {
      throw ECMAErrors.typeError("property.has.no.setter", new String[] { name, ScriptRuntime.safeToString(self) });
    } 
  }
  
  private static void invokeNumberSetter(Accessors gs, MethodHandle invoker, String name, Object self, double value) throws Throwable {
    Object func = gs.setter;
    if (func instanceof ScriptFunction) {
      invoker.invokeExact(func, self, value);
    } else if (name != null) {
      throw ECMAErrors.typeError("property.has.no.setter", new String[] { name, ScriptRuntime.safeToString(self) });
    } 
  }
  
  private static MethodHandle findOwnMH_S(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(LOOKUP, UserAccessorProperty.class, name, Lookup.MH.type(rtype, types));
  }
}
