package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.linker.PrimitiveLookup;

public final class NativeBoolean extends ScriptObject {
  private final boolean value;
  
  static final MethodHandle WRAPFILTER = findOwnMH("wrapFilter", Lookup.MH.type(NativeBoolean.class, new Class[] { Object.class }));
  
  private static final MethodHandle PROTOFILTER = findOwnMH("protoFilter", Lookup.MH.type(Object.class, new Class[] { Object.class }));
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private NativeBoolean(boolean value, ScriptObject proto, PropertyMap map) {
    super(proto, map);
    this.value = value;
  }
  
  NativeBoolean(boolean flag, Global global) {
    this(flag, global.getBooleanPrototype(), $nasgenmap$);
  }
  
  NativeBoolean(boolean flag) {
    this(flag, Global.instance());
  }
  
  public String safeToString() {
    return "[Boolean " + toString() + "]";
  }
  
  public String toString() {
    return Boolean.toString(getValue());
  }
  
  public boolean getValue() {
    return booleanValue();
  }
  
  public boolean booleanValue() {
    return this.value;
  }
  
  public String getClassName() {
    return "Boolean";
  }
  
  public static String toString(Object self) {
    return getBoolean(self).toString();
  }
  
  public static boolean valueOf(Object self) {
    return getBoolean(self).booleanValue();
  }
  
  public static Object constructor(boolean newObj, Object self, Object value) {
    boolean flag = JSType.toBoolean(value);
    if (newObj)
      return new NativeBoolean(flag); 
    return Boolean.valueOf(flag);
  }
  
  private static Boolean getBoolean(Object self) {
    if (self instanceof Boolean)
      return (Boolean)self; 
    if (self instanceof NativeBoolean)
      return Boolean.valueOf(((NativeBoolean)self).getValue()); 
    if (self != null && self == Global.instance().getBooleanPrototype())
      return Boolean.valueOf(false); 
    throw ECMAErrors.typeError("not.a.boolean", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  public static GuardedInvocation lookupPrimitive(LinkRequest request, Object receiver) {
    return PrimitiveLookup.lookupPrimitive(request, Boolean.class, new NativeBoolean(((Boolean)receiver).booleanValue()), WRAPFILTER, PROTOFILTER);
  }
  
  private static NativeBoolean wrapFilter(Object receiver) {
    return new NativeBoolean(((Boolean)receiver).booleanValue());
  }
  
  private static Object protoFilter(Object object) {
    return Global.instance().getBooleanPrototype();
  }
  
  private static MethodHandle findOwnMH(String name, MethodType type) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), NativeBoolean.class, name, type);
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
