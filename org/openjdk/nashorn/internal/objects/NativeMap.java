package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.util.Collections;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;

public class NativeMap extends ScriptObject {
  private final LinkedMap map = new LinkedMap();
  
  private static final Object FOREACH_INVOKER_KEY = new Object();
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private NativeMap(ScriptObject proto, PropertyMap map) {
    super(proto, map);
  }
  
  public static Object construct(boolean isNew, Object self, Object arg) {
    if (!isNew)
      throw ECMAErrors.typeError("constructor.requires.new", new String[] { "Map" }); 
    Global global = Global.instance();
    NativeMap map = new NativeMap(global.getMapPrototype(), $nasgenmap$);
    populateMap(map.getJavaMap(), arg, global);
    return map;
  }
  
  public static void clear(Object self) {
    (getNativeMap(self)).map.clear();
  }
  
  public static boolean delete(Object self, Object key) {
    return (getNativeMap(self)).map.delete(convertKey(key));
  }
  
  public static boolean has(Object self, Object key) {
    return (getNativeMap(self)).map.has(convertKey(key));
  }
  
  public static Object set(Object self, Object key, Object value) {
    (getNativeMap(self)).map.set(convertKey(key), value);
    return self;
  }
  
  public static Object get(Object self, Object key) {
    return (getNativeMap(self)).map.get(convertKey(key));
  }
  
  public static int size(Object self) {
    return (getNativeMap(self)).map.size();
  }
  
  public static Object entries(Object self) {
    return new MapIterator(getNativeMap(self), AbstractIterator.IterationKind.KEY_VALUE, Global.instance());
  }
  
  public static Object keys(Object self) {
    return new MapIterator(getNativeMap(self), AbstractIterator.IterationKind.KEY, Global.instance());
  }
  
  public static Object values(Object self) {
    return new MapIterator(getNativeMap(self), AbstractIterator.IterationKind.VALUE, Global.instance());
  }
  
  public static Object getIterator(Object self) {
    return new MapIterator(getNativeMap(self), AbstractIterator.IterationKind.KEY_VALUE, Global.instance());
  }
  
  public static void forEach(Object self, Object callbackFn, Object thisArg) {
    NativeMap map = getNativeMap(self);
    if (!Bootstrap.isCallable(callbackFn))
      throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(callbackFn) }); 
    MethodHandle invoker = Global.instance().getDynamicInvoker(FOREACH_INVOKER_KEY, () -> Bootstrap.createDynamicCallInvoker(Object.class, new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class }));
    LinkedMap.LinkedMapIterator iterator = map.getJavaMap().getIterator();
    while (true) {
      LinkedMap.Node node = iterator.next();
      if (node == null)
        break; 
      try {
        Object object = invoker.invokeExact(callbackFn, thisArg, node.getValue(), node.getKey(), self);
      } catch (RuntimeException|Error e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      } 
    } 
  }
  
  public String getClassName() {
    return "Map";
  }
  
  static void populateMap(LinkedMap map, Object arg, Global global) {
    if (arg != null && arg != Undefined.getUndefined())
      AbstractIterator.iterate(arg, global, value -> {
            if (JSType.isPrimitive(value))
              throw ECMAErrors.typeError(global, "not.an.object", new String[] { ScriptRuntime.safeToString(value) }); 
            if (value instanceof ScriptObject) {
              ScriptObject sobj = (ScriptObject)value;
              map.set(convertKey(sobj.get(0)), sobj.get(1));
            } 
          }); 
  }
  
  static Object convertKey(Object key) {
    if (key instanceof org.openjdk.nashorn.internal.runtime.ConsString)
      return key.toString(); 
    if (key instanceof Double) {
      Double d = (Double)key;
      if (JSType.isRepresentableAsInt(d.doubleValue()))
        return Integer.valueOf(d.intValue()); 
    } 
    return key;
  }
  
  LinkedMap getJavaMap() {
    return this.map;
  }
  
  private static NativeMap getNativeMap(Object self) {
    if (self instanceof NativeMap)
      return (NativeMap)self; 
    throw ECMAErrors.typeError("not.a.map", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
