package org.openjdk.nashorn.internal.objects;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;

public class NativeWeakSet extends ScriptObject {
  private final Map<Object, Boolean> map = new WeakHashMap<>();
  
  private static PropertyMap $nasgenmap$;
  
  private NativeWeakSet(ScriptObject proto, PropertyMap map) {
    super(proto, map);
  }
  
  public static Object construct(boolean isNew, Object self, Object arg) {
    if (!isNew)
      throw ECMAErrors.typeError("constructor.requires.new", new String[] { "WeakSet" }); 
    Global global = Global.instance();
    NativeWeakSet weakSet = new NativeWeakSet(global.getWeakSetPrototype(), $nasgenmap$);
    populateWeakSet(weakSet.map, arg, global);
    return weakSet;
  }
  
  public static Object add(Object self, Object value) {
    NativeWeakSet set = getSet(self);
    set.map.put(NativeWeakMap.checkKey(value), Boolean.TRUE);
    return self;
  }
  
  public static boolean has(Object self, Object value) {
    NativeWeakSet set = getSet(self);
    return (!JSType.isPrimitive(value) && set.map.containsKey(value));
  }
  
  public static boolean delete(Object self, Object value) {
    Map<Object, Boolean> map = (getSet(self)).map;
    if (JSType.isPrimitive(value))
      return false; 
    boolean returnValue = map.containsKey(value);
    map.remove(value);
    return returnValue;
  }
  
  public String getClassName() {
    return "WeakSet";
  }
  
  static void populateWeakSet(Map<Object, Boolean> set, Object arg, Global global) {
    if (arg != null && arg != Undefined.getUndefined())
      AbstractIterator.iterate(arg, global, value -> set.put(NativeWeakMap.checkKey(value), Boolean.TRUE)); 
  }
  
  private static NativeWeakSet getSet(Object self) {
    if (self instanceof NativeWeakSet)
      return (NativeWeakSet)self; 
    throw ECMAErrors.typeError("not.a.weak.set", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
