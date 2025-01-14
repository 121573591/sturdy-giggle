package org.openjdk.nashorn.api.scripting;

import java.nio.ByteBuffer;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import javax.script.Bindings;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ECMAException;
import org.openjdk.nashorn.internal.runtime.JSONListAdapter;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;

public final class ScriptObjectMirror extends AbstractJSObject implements Bindings {
  private static AccessControlContext getContextAccCtxt() {
    Permissions perms = new Permissions();
    perms.add(new RuntimePermission("nashorn.getContext"));
    return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, perms) });
  }
  
  private static final AccessControlContext GET_CONTEXT_ACC_CTXT = getContextAccCtxt();
  
  private final ScriptObject sobj;
  
  private final Global global;
  
  private final boolean strict;
  
  private final boolean jsonCompatible;
  
  public boolean equals(Object other) {
    if (other instanceof ScriptObjectMirror)
      return this.sobj.equals(((ScriptObjectMirror)other).sobj); 
    return false;
  }
  
  public int hashCode() {
    return this.sobj.hashCode();
  }
  
  public String toString() {
    return inGlobal(() -> ScriptRuntime.safeToString(this.sobj));
  }
  
  public Object call(Object thiz, Object... args) {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != this.global);
    try {
      if (globalChanged)
        Context.setGlobal(this.global); 
      if (this.sobj instanceof ScriptFunction) {
        Object[] modArgs = globalChanged ? wrapArrayLikeMe(args, oldGlobal) : args;
        Object self = globalChanged ? wrapLikeMe(thiz, oldGlobal) : thiz;
        return wrapLikeMe(ScriptRuntime.apply((ScriptFunction)this.sobj, unwrap(self, this.global), unwrapArray(modArgs, this.global)));
      } 
      throw new RuntimeException("not a function: " + toString());
    } catch (NashornException ne) {
      throw ne.initEcmaError(this.global);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  public Object newObject(Object... args) {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != this.global);
    try {
      if (globalChanged)
        Context.setGlobal(this.global); 
      if (this.sobj instanceof ScriptFunction) {
        Object[] modArgs = globalChanged ? wrapArrayLikeMe(args, oldGlobal) : args;
        return wrapLikeMe(ScriptRuntime.construct((ScriptFunction)this.sobj, unwrapArray(modArgs, this.global)));
      } 
      throw new RuntimeException("not a constructor: " + toString());
    } catch (NashornException ne) {
      throw ne.initEcmaError(this.global);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  public Object eval(String s) {
    return inGlobal(() -> {
          Context context = AccessController.<Context>doPrivileged(Context::getContext, GET_CONTEXT_ACC_CTXT);
          return wrapLikeMe(context.eval((ScriptObject)this.global, s, this.sobj, null));
        });
  }
  
  public Object callMember(String functionName, Object... args) {
    Objects.requireNonNull(functionName);
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != this.global);
    try {
      if (globalChanged)
        Context.setGlobal(this.global); 
      Object val = this.sobj.get(functionName);
      if (val instanceof ScriptFunction) {
        Object[] modArgs = globalChanged ? wrapArrayLikeMe(args, oldGlobal) : args;
        return wrapLikeMe(ScriptRuntime.apply((ScriptFunction)val, this.sobj, unwrapArray(modArgs, this.global)));
      } 
      if (val instanceof JSObject && ((JSObject)val).isFunction())
        return ((JSObject)val).call(this.sobj, args); 
      throw new NoSuchMethodException("No such function " + functionName);
    } catch (NashornException ne) {
      throw ne.initEcmaError(this.global);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  public Object getMember(String name) {
    Objects.requireNonNull(name);
    return inGlobal(() -> wrapLikeMe(this.sobj.get(name)));
  }
  
  public Object getSlot(int index) {
    return inGlobal(() -> wrapLikeMe(this.sobj.get(index)));
  }
  
  public boolean hasMember(String name) {
    Objects.requireNonNull(name);
    return ((Boolean)inGlobal(() -> Boolean.valueOf(this.sobj.has(name)))).booleanValue();
  }
  
  public boolean hasSlot(int slot) {
    return ((Boolean)inGlobal(() -> Boolean.valueOf(this.sobj.has(slot)))).booleanValue();
  }
  
  public void removeMember(String name) {
    remove(Objects.requireNonNull(name));
  }
  
  public void setMember(String name, Object value) {
    put(Objects.<String>requireNonNull(name), value);
  }
  
  public void setSlot(int index, Object value) {
    inGlobal(() -> this.sobj.set(index, unwrap(value, this.global), getCallSiteFlags()));
  }
  
  public void setIndexedPropertiesToExternalArrayData(ByteBuffer buf) {
    inGlobal(() -> this.sobj.setArray(ArrayData.allocate(buf)));
  }
  
  public boolean isInstance(Object instance) {
    if (!(instance instanceof ScriptObjectMirror))
      return false; 
    ScriptObjectMirror mirror = (ScriptObjectMirror)instance;
    if (this.global != mirror.global)
      return false; 
    return ((Boolean)inGlobal(() -> Boolean.valueOf(this.sobj.isInstance(mirror.sobj)))).booleanValue();
  }
  
  public String getClassName() {
    return this.sobj.getClassName();
  }
  
  public boolean isFunction() {
    return this.sobj instanceof ScriptFunction;
  }
  
  public boolean isStrictFunction() {
    return (isFunction() && ((ScriptFunction)this.sobj).isStrict());
  }
  
  public boolean isArray() {
    return this.sobj.isArray();
  }
  
  public void clear() {
    inGlobal(() -> this.sobj.clear(this.strict));
  }
  
  public boolean containsKey(Object key) {
    checkKey(key);
    return ((Boolean)inGlobal(() -> Boolean.valueOf(this.sobj.containsKey(key)))).booleanValue();
  }
  
  public boolean containsValue(Object value) {
    return ((Boolean)inGlobal(() -> Boolean.valueOf(this.sobj.containsValue(unwrap(value, this.global))))).booleanValue();
  }
  
  public Set<Map.Entry<String, Object>> entrySet() {
    return inGlobal(() -> {
          Iterator<String> iter = this.sobj.propertyIterator();
          Set<Map.Entry<String, Object>> entries = new LinkedHashSet<>();
          while (iter.hasNext()) {
            String key = iter.next();
            Object value = translateUndefined(wrapLikeMe(this.sobj.get(key)));
            entries.add(new AbstractMap.SimpleImmutableEntry<>(key, value));
          } 
          return Collections.unmodifiableSet(entries);
        });
  }
  
  public Object get(Object key) {
    checkKey(key);
    return inGlobal(() -> translateUndefined(wrapLikeMe(this.sobj.get(key))));
  }
  
  public boolean isEmpty() {
    Objects.requireNonNull(this.sobj);
    return ((Boolean)inGlobal(this.sobj::isEmpty)).booleanValue();
  }
  
  public Set<String> keySet() {
    return inGlobal(() -> {
          Iterator<String> iter = this.sobj.propertyIterator();
          Set<String> keySet = new LinkedHashSet<>();
          while (iter.hasNext())
            keySet.add(iter.next()); 
          return Collections.unmodifiableSet(keySet);
        });
  }
  
  public Object put(String key, Object value) {
    checkKey(key);
    Global global = Context.getGlobal();
    boolean globalChanged = (global != this.global);
    return inGlobal(() -> {
          Object modValue = globalChanged ? wrapLikeMe(value, oldGlobal) : value;
          return translateUndefined(wrapLikeMe(this.sobj.put(key, unwrap(modValue, this.global), this.strict)));
        });
  }
  
  public void putAll(Map<? extends String, ?> map) {
    Objects.requireNonNull(map);
    Global global = Context.getGlobal();
    boolean globalChanged = (global != this.global);
    inGlobal(() -> {
          for (Map.Entry<? extends String, ?> entry : (Iterable<Map.Entry<? extends String, ?>>)map.entrySet()) {
            Object value = entry.getValue();
            Object modValue = globalChanged ? wrapLikeMe(value, oldGlobal) : value;
            String key = entry.getKey();
            checkKey(key);
            this.sobj.set(key, unwrap(modValue, this.global), getCallSiteFlags());
          } 
        });
  }
  
  public Object remove(Object key) {
    checkKey(key);
    return inGlobal(() -> translateUndefined(wrapLikeMe(this.sobj.remove(key, this.strict))));
  }
  
  public boolean delete(Object key) {
    return ((Boolean)inGlobal(() -> Boolean.valueOf(this.sobj.delete(unwrap(key, this.global), this.strict)))).booleanValue();
  }
  
  public int size() {
    Objects.requireNonNull(this.sobj);
    return ((Integer)inGlobal(this.sobj::size)).intValue();
  }
  
  public Collection<Object> values() {
    return inGlobal(() -> {
          List<Object> values = new ArrayList(size());
          Iterator<Object> iter = this.sobj.valueIterator();
          while (iter.hasNext())
            values.add(translateUndefined(wrapLikeMe(iter.next()))); 
          return Collections.unmodifiableList(values);
        });
  }
  
  public Object getProto() {
    return inGlobal(() -> wrapLikeMe(this.sobj.getProto()));
  }
  
  public void setProto(Object proto) {
    inGlobal(() -> this.sobj.setPrototypeOf(unwrap(proto, this.global)));
  }
  
  public Object getOwnPropertyDescriptor(String key) {
    return inGlobal(() -> wrapLikeMe(this.sobj.getOwnPropertyDescriptor(key)));
  }
  
  public String[] getOwnKeys(boolean all) {
    return inGlobal(() -> this.sobj.getOwnKeys(all));
  }
  
  public ScriptObjectMirror preventExtensions() {
    return inGlobal(() -> {
          this.sobj.preventExtensions();
          return this;
        });
  }
  
  public boolean isExtensible() {
    Objects.requireNonNull(this.sobj);
    return ((Boolean)inGlobal(this.sobj::isExtensible)).booleanValue();
  }
  
  public ScriptObjectMirror seal() {
    return inGlobal(() -> {
          this.sobj.seal();
          return this;
        });
  }
  
  public boolean isSealed() {
    Objects.requireNonNull(this.sobj);
    return ((Boolean)inGlobal(this.sobj::isSealed)).booleanValue();
  }
  
  public ScriptObjectMirror freeze() {
    return inGlobal(() -> {
          this.sobj.freeze();
          return this;
        });
  }
  
  public boolean isFrozen() {
    Objects.requireNonNull(this.sobj);
    return ((Boolean)inGlobal(this.sobj::isFrozen)).booleanValue();
  }
  
  public static boolean isUndefined(Object obj) {
    return (obj == ScriptRuntime.UNDEFINED);
  }
  
  public <T> T to(Class<T> type) {
    return inGlobal(() -> type.cast(ScriptUtils.convert(this.sobj, type)));
  }
  
  public static Object wrap(Object obj, Object homeGlobal) {
    return wrap(obj, homeGlobal, false);
  }
  
  public static Object wrapAsJSONCompatible(Object obj, Object homeGlobal) {
    return wrap(obj, homeGlobal, true);
  }
  
  private static Object wrap(Object obj, Object homeGlobal, boolean jsonCompatible) {
    if (obj instanceof ScriptObject) {
      if (!(homeGlobal instanceof Global))
        return obj; 
      ScriptObject sobj = (ScriptObject)obj;
      Global global = (Global)homeGlobal;
      ScriptObjectMirror mirror = new ScriptObjectMirror(sobj, global, jsonCompatible);
      if (jsonCompatible && sobj.isArray())
        return new JSONListAdapter(mirror, global); 
      return mirror;
    } 
    if (obj instanceof org.openjdk.nashorn.internal.runtime.ConsString)
      return obj.toString(); 
    if (jsonCompatible && obj instanceof ScriptObjectMirror)
      return ((ScriptObjectMirror)obj).asJSONCompatible(); 
    return obj;
  }
  
  private Object wrapLikeMe(Object obj, Object homeGlobal) {
    return wrap(obj, homeGlobal, this.jsonCompatible);
  }
  
  private Object wrapLikeMe(Object obj) {
    return wrapLikeMe(obj, this.global);
  }
  
  public static Object unwrap(Object obj, Object homeGlobal) {
    if (obj instanceof ScriptObjectMirror) {
      ScriptObjectMirror mirror = (ScriptObjectMirror)obj;
      return (mirror.global == homeGlobal) ? mirror.sobj : obj;
    } 
    if (obj instanceof JSONListAdapter)
      return ((JSONListAdapter)obj).unwrap(homeGlobal); 
    return obj;
  }
  
  public static Object[] wrapArray(Object[] args, Object homeGlobal) {
    return wrapArray(args, homeGlobal, false);
  }
  
  private static Object[] wrapArray(Object[] args, Object homeGlobal, boolean jsonCompatible) {
    if (args == null || args.length == 0)
      return args; 
    Object[] newArgs = new Object[args.length];
    int index = 0;
    for (Object obj : args) {
      newArgs[index] = wrap(obj, homeGlobal, jsonCompatible);
      index++;
    } 
    return newArgs;
  }
  
  private Object[] wrapArrayLikeMe(Object[] args, Object homeGlobal) {
    return wrapArray(args, homeGlobal, this.jsonCompatible);
  }
  
  public static Object[] unwrapArray(Object[] args, Object homeGlobal) {
    if (args == null || args.length == 0)
      return args; 
    Object[] newArgs = new Object[args.length];
    int index = 0;
    for (Object obj : args) {
      newArgs[index] = unwrap(obj, homeGlobal);
      index++;
    } 
    return newArgs;
  }
  
  public static boolean identical(Object obj1, Object obj2) {
    Object o1 = (obj1 instanceof ScriptObjectMirror) ? ((ScriptObjectMirror)obj1).sobj : obj1;
    Object o2 = (obj2 instanceof ScriptObjectMirror) ? ((ScriptObjectMirror)obj2).sobj : obj2;
    return (o1 == o2);
  }
  
  ScriptObjectMirror(ScriptObject sobj, Global global) {
    this(sobj, global, false);
  }
  
  private ScriptObjectMirror(ScriptObject sobj, Global global, boolean jsonCompatible) {
    assert sobj != null : "ScriptObjectMirror on null!";
    assert global != null : "home Global is null";
    this.sobj = sobj;
    this.global = global;
    this.strict = global.isStrictContext();
    this.jsonCompatible = jsonCompatible;
  }
  
  ScriptObject getScriptObject() {
    return this.sobj;
  }
  
  Global getHomeGlobal() {
    return this.global;
  }
  
  static Object translateUndefined(Object obj) {
    return (obj == ScriptRuntime.UNDEFINED) ? null : obj;
  }
  
  private int getCallSiteFlags() {
    return this.strict ? 32 : 0;
  }
  
  private void inGlobal(Runnable r) {
    inGlobal(() -> {
          r.run();
          return null;
        });
  }
  
  private <V> V inGlobal(Supplier<V> s) {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != this.global);
    if (globalChanged)
      Context.setGlobal(this.global); 
    try {
      return s.get();
    } catch (NashornException ne) {
      throw ne.initEcmaError(this.global);
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  private static void checkKey(Object key) {
    Objects.requireNonNull(key, "key can not be null");
    if (!(key instanceof String))
      throw new ClassCastException("key should be a String. It is " + key.getClass().getName() + " instead."); 
    if (((String)key).length() == 0)
      throw new IllegalArgumentException("key can not be empty"); 
  }
  
  @Deprecated
  public double toNumber() {
    return ((Double)inGlobal(() -> Double.valueOf(JSType.toNumber(this.sobj)))).doubleValue();
  }
  
  public Object getDefaultValue(Class<?> hint) {
    return inGlobal(() -> {
          try {
            return this.sobj.getDefaultValue(hint);
          } catch (ECMAException e) {
            throw new UnsupportedOperationException(e.getMessage(), e);
          } 
        });
  }
  
  private ScriptObjectMirror asJSONCompatible() {
    if (this.jsonCompatible)
      return this; 
    return new ScriptObjectMirror(this.sobj, this.global, true);
  }
}
