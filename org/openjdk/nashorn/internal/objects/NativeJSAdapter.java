package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.FindProperty;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.scripts.JO;

public final class NativeJSAdapter extends ScriptObject {
  public static final String __get__ = "__get__";
  
  public static final String __put__ = "__put__";
  
  public static final String __call__ = "__call__";
  
  public static final String __new__ = "__new__";
  
  public static final String __getIds__ = "__getIds__";
  
  public static final String __getKeys__ = "__getKeys__";
  
  public static final String __getValues__ = "__getValues__";
  
  public static final String __has__ = "__has__";
  
  public static final String __delete__ = "__delete__";
  
  public static final String __preventExtensions__ = "__preventExtensions__";
  
  public static final String __isExtensible__ = "__isExtensible__";
  
  public static final String __seal__ = "__seal__";
  
  public static final String __isSealed__ = "__isSealed__";
  
  public static final String __freeze__ = "__freeze__";
  
  public static final String __isFrozen__ = "__isFrozen__";
  
  private final ScriptObject adaptee;
  
  private final boolean overrides;
  
  private static final MethodHandle IS_JSADAPTER = findOwnMH("isJSAdapter", boolean.class, new Class[] { Object.class, Object.class, MethodHandle.class, Object.class, ScriptFunction.class });
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  NativeJSAdapter(Object overrides, ScriptObject adaptee, ScriptObject proto, PropertyMap map) {
    super(proto, map);
    this.adaptee = wrapAdaptee(adaptee);
    if (overrides instanceof ScriptObject) {
      this.overrides = true;
      ScriptObject sobj = (ScriptObject)overrides;
      addBoundProperties(sobj);
    } else {
      this.overrides = false;
    } 
  }
  
  private static ScriptObject wrapAdaptee(ScriptObject adaptee) {
    return (ScriptObject)new JO(adaptee);
  }
  
  public String getClassName() {
    return "JSAdapter";
  }
  
  public int getInt(Object key, int programPoint) {
    return (this.overrides && hasOwnProperty(key)) ? super.getInt(key, programPoint) : callAdapteeInt(programPoint, "__get__", new Object[] { key });
  }
  
  public int getInt(double key, int programPoint) {
    return (this.overrides && hasOwnProperty(key)) ? super.getInt(key, programPoint) : callAdapteeInt(programPoint, "__get__", new Object[] { Double.valueOf(key) });
  }
  
  public int getInt(int key, int programPoint) {
    return (this.overrides && hasOwnProperty(key)) ? super.getInt(key, programPoint) : callAdapteeInt(programPoint, "__get__", new Object[] { Integer.valueOf(key) });
  }
  
  public double getDouble(Object key, int programPoint) {
    return (this.overrides && hasOwnProperty(key)) ? super.getDouble(key, programPoint) : callAdapteeDouble(programPoint, "__get__", new Object[] { key });
  }
  
  public double getDouble(double key, int programPoint) {
    return (this.overrides && hasOwnProperty(key)) ? super.getDouble(key, programPoint) : callAdapteeDouble(programPoint, "__get__", new Object[] { Double.valueOf(key) });
  }
  
  public double getDouble(int key, int programPoint) {
    return (this.overrides && hasOwnProperty(key)) ? super.getDouble(key, programPoint) : callAdapteeDouble(programPoint, "__get__", new Object[] { Integer.valueOf(key) });
  }
  
  public Object get(Object key) {
    return (this.overrides && hasOwnProperty(key)) ? super.get(key) : callAdaptee("__get__", new Object[] { key });
  }
  
  public Object get(double key) {
    return (this.overrides && hasOwnProperty(key)) ? super.get(key) : callAdaptee("__get__", new Object[] { Double.valueOf(key) });
  }
  
  public Object get(int key) {
    return (this.overrides && hasOwnProperty(key)) ? super.get(key) : callAdaptee("__get__", new Object[] { Integer.valueOf(key) });
  }
  
  public void set(Object key, int value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { key, Integer.valueOf(value), Integer.valueOf(flags) });
    } 
  }
  
  public void set(Object key, double value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { key, Double.valueOf(value), Integer.valueOf(flags) });
    } 
  }
  
  public void set(Object key, Object value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { key, value, Integer.valueOf(flags) });
    } 
  }
  
  public void set(double key, int value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { Double.valueOf(key), Integer.valueOf(value), Integer.valueOf(flags) });
    } 
  }
  
  public void set(double key, double value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { Double.valueOf(key), Double.valueOf(value), Integer.valueOf(flags) });
    } 
  }
  
  public void set(double key, Object value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { Double.valueOf(key), value, Integer.valueOf(flags) });
    } 
  }
  
  public void set(int key, int value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { Integer.valueOf(key), Integer.valueOf(value), Integer.valueOf(flags) });
    } 
  }
  
  public void set(int key, double value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { Integer.valueOf(key), Double.valueOf(value), Integer.valueOf(flags) });
    } 
  }
  
  public void set(int key, Object value, int flags) {
    if (this.overrides && hasOwnProperty(key)) {
      super.set(key, value, flags);
    } else {
      callAdaptee("__put__", new Object[] { Integer.valueOf(key), value, Integer.valueOf(flags) });
    } 
  }
  
  public boolean has(Object key) {
    if (this.overrides && hasOwnProperty(key))
      return true; 
    return JSType.toBoolean(callAdaptee(Boolean.FALSE, "__has__", new Object[] { key }));
  }
  
  public boolean has(int key) {
    if (this.overrides && hasOwnProperty(key))
      return true; 
    return JSType.toBoolean(callAdaptee(Boolean.FALSE, "__has__", new Object[] { Integer.valueOf(key) }));
  }
  
  public boolean has(double key) {
    if (this.overrides && hasOwnProperty(key))
      return true; 
    return JSType.toBoolean(callAdaptee(Boolean.FALSE, "__has__", new Object[] { Double.valueOf(key) }));
  }
  
  public boolean delete(int key, boolean strict) {
    if (this.overrides && hasOwnProperty(key))
      return super.delete(key, strict); 
    return JSType.toBoolean(callAdaptee(Boolean.TRUE, "__delete__", new Object[] { Integer.valueOf(key), Boolean.valueOf(strict) }));
  }
  
  public boolean delete(double key, boolean strict) {
    if (this.overrides && hasOwnProperty(key))
      return super.delete(key, strict); 
    return JSType.toBoolean(callAdaptee(Boolean.TRUE, "__delete__", new Object[] { Double.valueOf(key), Boolean.valueOf(strict) }));
  }
  
  public boolean delete(Object key, boolean strict) {
    if (this.overrides && hasOwnProperty(key))
      return super.delete(key, strict); 
    return JSType.toBoolean(callAdaptee(Boolean.TRUE, "__delete__", new Object[] { key, Boolean.valueOf(strict) }));
  }
  
  public Iterator<String> propertyIterator() {
    Object obj, func = this.adaptee.get("__getIds__");
    if (!(func instanceof ScriptFunction))
      func = this.adaptee.get("__getKeys__"); 
    if (func instanceof ScriptFunction) {
      obj = ScriptRuntime.apply((ScriptFunction)func, this, new Object[0]);
    } else {
      obj = new NativeArray(0L);
    } 
    List<String> array = new ArrayList<>();
    for (ArrayLikeIterator<String> arrayLikeIterator = ArrayLikeIterator.arrayLikeIterator(obj); arrayLikeIterator.hasNext();)
      array.add(arrayLikeIterator.next()); 
    return array.iterator();
  }
  
  public Iterator<Object> valueIterator() {
    Object obj = callAdaptee(new NativeArray(0L), "__getValues__", new Object[0]);
    return (Iterator<Object>)ArrayLikeIterator.arrayLikeIterator(obj);
  }
  
  public ScriptObject preventExtensions() {
    callAdaptee("__preventExtensions__", new Object[0]);
    return this;
  }
  
  public boolean isExtensible() {
    return JSType.toBoolean(callAdaptee(Boolean.TRUE, "__isExtensible__", new Object[0]));
  }
  
  public ScriptObject seal() {
    callAdaptee("__seal__", new Object[0]);
    return this;
  }
  
  public boolean isSealed() {
    return JSType.toBoolean(callAdaptee(Boolean.FALSE, "__isSealed__", new Object[0]));
  }
  
  public ScriptObject freeze() {
    callAdaptee("__freeze__", new Object[0]);
    return this;
  }
  
  public boolean isFrozen() {
    return JSType.toBoolean(callAdaptee(Boolean.FALSE, "__isFrozen__", new Object[0]));
  }
  
  public static NativeJSAdapter construct(boolean isNew, Object self, Object... args) {
    Object adaptee, proto = ScriptRuntime.UNDEFINED;
    Object overrides = ScriptRuntime.UNDEFINED;
    if (args == null || args.length == 0)
      throw ECMAErrors.typeError("not.an.object", new String[] { "null" }); 
    switch (args.length) {
      case 1:
        adaptee = args[0];
        break;
      case 2:
        overrides = args[0];
        adaptee = args[1];
        break;
      default:
        proto = args[0];
        overrides = args[1];
        adaptee = args[2];
        break;
    } 
    if (!(adaptee instanceof ScriptObject))
      throw ECMAErrors.typeError("not.an.object", new String[] { ScriptRuntime.safeToString(adaptee) }); 
    Global global = Global.instance();
    if (proto != null && !(proto instanceof ScriptObject))
      proto = global.getJSAdapterPrototype(); 
    return new NativeJSAdapter(overrides, (ScriptObject)adaptee, (ScriptObject)proto, $nasgenmap$);
  }
  
  protected GuardedInvocation findNewMethod(CallSiteDescriptor desc, LinkRequest request) {
    return findHook(desc, "__new__", false);
  }
  
  protected GuardedInvocation findGetMethod(CallSiteDescriptor desc, LinkRequest request) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    if (this.overrides && hasOwnProperty(name))
      try {
        GuardedInvocation inv = super.findGetMethod(desc, request);
        if (inv != null)
          return inv; 
      } catch (Exception exception) {} 
    if (!NashornCallSiteDescriptor.isMethodFirstOperation(desc))
      return findHook(desc, "__get__"); 
    FindProperty find = this.adaptee.findProperty("__call__", true);
    if (find != null) {
      Object value = find.getObjectValue();
      if (value instanceof ScriptFunction) {
        ScriptFunction func = (ScriptFunction)value;
        return new GuardedInvocation(Lookup.MH.dropArguments(Lookup.MH.constant(Object.class, func
                .createBound(this, new Object[] { name })), 0, new Class[] { Object.class }), testJSAdapter(this.adaptee, (MethodHandle)null, (Object)null, (ScriptFunction)null), this.adaptee
            .getProtoSwitchPoints("__call__", find.getOwner()), null);
      } 
    } 
    throw ECMAErrors.typeError("no.such.function", new String[] { name, ScriptRuntime.safeToString(this) });
  }
  
  protected GuardedInvocation findSetMethod(CallSiteDescriptor desc, LinkRequest request) {
    if (this.overrides && hasOwnProperty(NashornCallSiteDescriptor.getOperand(desc)))
      try {
        GuardedInvocation inv = super.findSetMethod(desc, request);
        if (inv != null)
          return inv; 
      } catch (Exception exception) {} 
    return findHook(desc, "__put__");
  }
  
  private Object callAdaptee(String name, Object... args) {
    return callAdaptee(ScriptRuntime.UNDEFINED, name, args);
  }
  
  private double callAdapteeDouble(int programPoint, String name, Object... args) {
    return JSType.toNumberMaybeOptimistic(callAdaptee(name, args), programPoint);
  }
  
  private int callAdapteeInt(int programPoint, String name, Object... args) {
    return JSType.toInt32MaybeOptimistic(callAdaptee(name, args), programPoint);
  }
  
  private Object callAdaptee(Object retValue, String name, Object... args) {
    Object func = this.adaptee.get(name);
    if (func instanceof ScriptFunction)
      return ScriptRuntime.apply((ScriptFunction)func, this, args); 
    return retValue;
  }
  
  private GuardedInvocation findHook(CallSiteDescriptor desc, String hook) {
    return findHook(desc, hook, true);
  }
  
  private GuardedInvocation findHook(CallSiteDescriptor desc, String hook, boolean useName) {
    FindProperty findData = this.adaptee.findProperty(hook, true);
    MethodType type = desc.getMethodType();
    if (findData != null) {
      String name = NashornCallSiteDescriptor.getOperand(desc);
      Object value = findData.getObjectValue();
      if (value instanceof ScriptFunction) {
        ScriptFunction func = (ScriptFunction)value;
        MethodHandle methodHandle1 = getCallMethodHandle(findData, type, 
            useName ? name : null);
        if (methodHandle1 != null)
          return new GuardedInvocation(methodHandle1, 
              
              testJSAdapter(this.adaptee, findData.getGetter(Object.class, -1, null), findData.getOwner(), func), this.adaptee
              .getProtoSwitchPoints(hook, findData.getOwner()), null); 
      } 
    } 
    if ("__call__".equals(hook))
      throw ECMAErrors.typeError("no.such.function", new String[] { NashornCallSiteDescriptor.getOperand(desc), ScriptRuntime.safeToString(this) }); 
    MethodHandle methodHandle = hook.equals("__put__") ? Lookup.MH.asType(Lookup.EMPTY_SETTER, type) : Lookup.emptyGetter(type.returnType());
    return new GuardedInvocation(methodHandle, testJSAdapter(this.adaptee, (MethodHandle)null, (Object)null, (ScriptFunction)null), this.adaptee.getProtoSwitchPoints(hook, null), null);
  }
  
  private static MethodHandle testJSAdapter(Object adaptee, MethodHandle getter, Object where, ScriptFunction func) {
    return Lookup.MH.insertArguments(IS_JSADAPTER, 1, new Object[] { adaptee, getter, where, func });
  }
  
  private static boolean isJSAdapter(Object self, Object adaptee, MethodHandle getter, Object where, ScriptFunction func) {
    boolean res = (self instanceof NativeJSAdapter && ((NativeJSAdapter)self).getAdaptee() == adaptee);
    if (res && getter != null)
      try {
        return (getter.invokeExact(where) == func);
      } catch (RuntimeException|Error e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      }  
    return res;
  }
  
  public ScriptObject getAdaptee() {
    return this.adaptee;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), NativeJSAdapter.class, name, Lookup.MH.type(rtype, types));
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
