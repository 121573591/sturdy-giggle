package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.util.Collections;
import java.util.function.Consumer;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.linker.InvokeByName;

public abstract class AbstractIterator extends ScriptObject {
  private static PropertyMap $nasgenmap$;
  
  private static final Object ITERATOR_INVOKER_KEY = new Object();
  
  private static final Object NEXT_INVOKER_KEY = new Object();
  
  private static final Object DONE_INVOKER_KEY = new Object();
  
  private static final Object VALUE_INVOKER_KEY = new Object();
  
  static {
    $clinit$();
  }
  
  enum IterationKind {
    KEY, VALUE, KEY_VALUE;
  }
  
  protected AbstractIterator(ScriptObject prototype, PropertyMap map) {
    super(prototype, map);
  }
  
  public static Object getIterator(Object self) {
    return self;
  }
  
  public String getClassName() {
    return "Iterator";
  }
  
  protected IteratorResult makeResult(Object value, Boolean done, Global global) {
    return new IteratorResult(value, done, global);
  }
  
  static MethodHandle getIteratorInvoker(Global global) {
    return global.getDynamicInvoker(ITERATOR_INVOKER_KEY, () -> Bootstrap.createDynamicCallInvoker(Object.class, new Class[] { Object.class, Object.class }));
  }
  
  public static InvokeByName getNextInvoker(Global global) {
    return global.getInvokeByName(NEXT_INVOKER_KEY, () -> new InvokeByName("next", Object.class, Object.class, new Class[] { Object.class }));
  }
  
  public static MethodHandle getDoneInvoker(Global global) {
    return global.getDynamicInvoker(DONE_INVOKER_KEY, () -> Bootstrap.createDynamicInvoker("done", 0, Object.class, new Class[] { Object.class }));
  }
  
  public static MethodHandle getValueInvoker(Global global) {
    return global.getDynamicInvoker(VALUE_INVOKER_KEY, () -> Bootstrap.createDynamicInvoker("value", 0, Object.class, new Class[] { Object.class }));
  }
  
  public static Object getIterator(Object iterable, Global global) {
    Object object = Global.toObject(iterable);
    if (object instanceof ScriptObject) {
      Object getter = ((ScriptObject)object).get(NativeSymbol.iterator);
      if (Bootstrap.isCallable(getter))
        try {
          MethodHandle invoker = getIteratorInvoker(global);
          Object value = invoker.invokeExact(getter, iterable);
          if (JSType.isPrimitive(value))
            throw ECMAErrors.typeError("not.an.object", new String[] { ScriptRuntime.safeToString(value) }); 
          return value;
        } catch (Throwable t) {
          throw new RuntimeException(t);
        }  
      throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(getter) });
    } 
    throw ECMAErrors.typeError("cannot.get.iterator", new String[] { ScriptRuntime.safeToString(iterable) });
  }
  
  public static void iterate(Object iterable, Global global, Consumer<Object> consumer) {
    Object iterator = getIterator(Global.toObject(iterable), global);
    InvokeByName nextInvoker = getNextInvoker(global);
    MethodHandle doneInvoker = getDoneInvoker(global);
    MethodHandle valueInvoker = getValueInvoker(global);
    try {
      while (true) {
        Object next = nextInvoker.getGetter().invokeExact(iterator);
        if (!Bootstrap.isCallable(next))
          break; 
        Object result = nextInvoker.getInvoker().invokeExact(next, iterator, null);
        if (!(result instanceof ScriptObject))
          break; 
        Object done = doneInvoker.invokeExact(result);
        if (JSType.toBoolean(done))
          break; 
        consumer.accept(valueInvoker.invokeExact(result));
      } 
    } catch (RuntimeException r) {
      throw r;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  protected abstract IteratorResult next(Object paramObject);
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
