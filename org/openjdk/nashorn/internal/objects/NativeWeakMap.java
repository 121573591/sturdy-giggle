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

public class NativeWeakMap extends ScriptObject {
  private final Map<Object, Object> jmap = new WeakHashMap<>();
  
  private static PropertyMap $nasgenmap$;
  
  private NativeWeakMap(ScriptObject proto, PropertyMap map) {
    super(proto, map);
  }
  
  public static Object construct(boolean isNew, Object self, Object arg) {
    if (!isNew)
      throw ECMAErrors.typeError("constructor.requires.new", new String[] { "WeakMap" }); 
    Global global = Global.instance();
    NativeWeakMap weakMap = new NativeWeakMap(global.getWeakMapPrototype(), $nasgenmap$);
    populateMap(weakMap.jmap, arg, global);
    return weakMap;
  }
  
  public static Object set(Object self, Object key, Object value) {
    NativeWeakMap map = getMap(self);
    map.jmap.put(checkKey(key), value);
    return self;
  }
  
  public static Object get(Object self, Object key) {
    NativeWeakMap map = getMap(self);
    if (JSType.isPrimitive(key))
      return Undefined.getUndefined(); 
    return map.jmap.get(key);
  }
  
  public static boolean delete(Object self, Object key) {
    Map<Object, Object> map = (getMap(self)).jmap;
    if (JSType.isPrimitive(key))
      return false; 
    boolean returnValue = map.containsKey(key);
    map.remove(key);
    return returnValue;
  }
  
  public static boolean has(Object self, Object key) {
    NativeWeakMap map = getMap(self);
    return (!JSType.isPrimitive(key) && map.jmap.containsKey(key));
  }
  
  public String getClassName() {
    return "WeakMap";
  }
  
  static Object checkKey(Object key) {
    if (JSType.isPrimitive(key))
      throw ECMAErrors.typeError("invalid.weak.key", new String[] { ScriptRuntime.safeToString(key) }); 
    return key;
  }
  
  static void populateMap(Map<Object, Object> map, Object arg, Global global) {
    if (arg != null && arg != Undefined.getUndefined())
      AbstractIterator.iterate(arg, global, value -> {
            if (JSType.isPrimitive(value))
              throw ECMAErrors.typeError(global, "not.an.object", new String[] { ScriptRuntime.safeToString(value) }); 
            if (value instanceof ScriptObject) {
              ScriptObject sobj = (ScriptObject)value;
              map.put(checkKey(sobj.get(0)), sobj.get(1));
            } 
          }); 
  }
  
  private static NativeWeakMap getMap(Object self) {
    if (self instanceof NativeWeakMap)
      return (NativeWeakMap)self; 
    throw ECMAErrors.typeError("not.a.weak.map", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
