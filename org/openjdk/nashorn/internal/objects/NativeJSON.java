package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSONFunctions;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.linker.InvokeByName;

public final class NativeJSON extends ScriptObject {
  private static final Object TO_JSON = new Object();
  
  private static InvokeByName getTO_JSON() {
    return Global.instance().getInvokeByName(TO_JSON, () -> new InvokeByName("toJSON", ScriptObject.class, Object.class, new Class[] { Object.class }));
  }
  
  private static final Object JSOBJECT_INVOKER = new Object();
  
  private static MethodHandle getJSOBJECT_INVOKER() {
    return Global.instance().getDynamicInvoker(JSOBJECT_INVOKER, () -> Bootstrap.createDynamicCallInvoker(Object.class, new Class[] { Object.class, Object.class }));
  }
  
  private static final Object REPLACER_INVOKER = new Object();
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private static MethodHandle getREPLACER_INVOKER() {
    return Global.instance().getDynamicInvoker(REPLACER_INVOKER, () -> Bootstrap.createDynamicCallInvoker(Object.class, new Class[] { Object.class, Object.class, Object.class, Object.class }));
  }
  
  private NativeJSON() {
    throw new UnsupportedOperationException();
  }
  
  public static Object parse(Object self, Object text, Object reviver) {
    return JSONFunctions.parse(text, reviver);
  }
  
  public static Object stringify(Object self, Object value, Object replacer, Object space) {
    String gap;
    StringifyState state = new StringifyState();
    if (Bootstrap.isCallable(replacer)) {
      state.replacerFunction = replacer;
    } else if (isArray(replacer) || 
      isJSObjectArray(replacer) || replacer instanceof Iterable || (replacer != null && replacer
      
      .getClass().isArray())) {
      state.propertyList = new ArrayList<>();
      ArrayLikeIterator arrayLikeIterator = ArrayLikeIterator.arrayLikeIterator(replacer);
      while (arrayLikeIterator.hasNext()) {
        String item = null;
        Object v = arrayLikeIterator.next();
        if (v instanceof String) {
          item = (String)v;
        } else if (v instanceof org.openjdk.nashorn.internal.runtime.ConsString) {
          item = v.toString();
        } else if (v instanceof Number || v instanceof NativeNumber || v instanceof NativeString) {
          item = JSType.toString(v);
        } 
        if (item != null)
          state.propertyList.add(item); 
      } 
    } 
    Object modSpace = space;
    if (modSpace instanceof NativeNumber) {
      modSpace = Double.valueOf(JSType.toNumber(JSType.toPrimitive(modSpace, Number.class)));
    } else if (modSpace instanceof NativeString) {
      modSpace = JSType.toString(JSType.toPrimitive(modSpace, String.class));
    } 
    if (modSpace instanceof Number) {
      int indent = Math.min(10, JSType.toInteger(modSpace));
      if (indent < 1) {
        gap = "";
      } else {
        gap = " ".repeat(indent);
      } 
    } else if (JSType.isString(modSpace)) {
      String str = modSpace.toString();
      gap = str.substring(0, Math.min(10, str.length()));
    } else {
      gap = "";
    } 
    state.gap = gap;
    ScriptObject wrapper = Global.newEmptyInstance();
    wrapper.set("", value, 0);
    return str("", wrapper, state);
  }
  
  private static class StringifyState {
    final Map<Object, Object> stack = new IdentityHashMap<>();
    
    StringBuilder indent = new StringBuilder();
    
    String gap = "";
    
    List<String> propertyList = null;
    
    Object replacerFunction = null;
  }
  
  private static Object str(Object key, Object holder, StringifyState state) {
    assert holder instanceof ScriptObject || holder instanceof JSObject;
    Object value = getProperty(holder, key);
    try {
      if (value instanceof ScriptObject) {
        InvokeByName toJSONInvoker = getTO_JSON();
        ScriptObject svalue = (ScriptObject)value;
        Object toJSON = toJSONInvoker.getGetter().invokeExact(svalue);
        if (Bootstrap.isCallable(toJSON))
          value = toJSONInvoker.getInvoker().invokeExact(toJSON, svalue, key); 
      } else if (value instanceof JSObject) {
        JSObject jsObj = (JSObject)value;
        Object toJSON = jsObj.getMember("toJSON");
        if (Bootstrap.isCallable(toJSON))
          value = getJSOBJECT_INVOKER().invokeExact(toJSON, value); 
      } 
      if (state.replacerFunction != null)
        value = getREPLACER_INVOKER().invokeExact(state.replacerFunction, holder, key, value); 
    } catch (Error|RuntimeException t) {
      throw t;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
    boolean isObj = value instanceof ScriptObject;
    if (isObj)
      if (value instanceof NativeNumber) {
        value = Double.valueOf(JSType.toNumber(value));
      } else if (value instanceof NativeString) {
        value = JSType.toString(value);
      } else if (value instanceof NativeBoolean) {
        value = Boolean.valueOf(((NativeBoolean)value).booleanValue());
      }  
    if (value == null)
      return "null"; 
    if (Boolean.TRUE.equals(value))
      return "true"; 
    if (Boolean.FALSE.equals(value))
      return "false"; 
    if (value instanceof String)
      return JSONFunctions.quote((String)value); 
    if (value instanceof org.openjdk.nashorn.internal.runtime.ConsString)
      return JSONFunctions.quote(value.toString()); 
    if (value instanceof Number)
      return JSType.isFinite(((Number)value).doubleValue()) ? JSType.toString(value) : "null"; 
    JSType type = JSType.of(value);
    if (type == JSType.OBJECT) {
      if (isArray(value) || isJSObjectArray(value))
        return JA(value, state); 
      if (value instanceof ScriptObject || value instanceof JSObject)
        return JO(value, state); 
    } 
    return ScriptRuntime.UNDEFINED;
  }
  
  private static String JO(Object value, StringifyState state) {
    assert value instanceof ScriptObject || value instanceof JSObject;
    if (state.stack.containsKey(value))
      throw ECMAErrors.typeError("JSON.stringify.cyclic", new String[0]); 
    state.stack.put(value, value);
    StringBuilder stepback = new StringBuilder(state.indent.toString());
    state.indent.append(state.gap);
    StringBuilder finalStr = new StringBuilder();
    List<Object> partial = new ArrayList();
    List<String> k = (state.propertyList == null) ? Arrays.<String>asList(getOwnKeys(value)) : state.propertyList;
    for (String p : k) {
      Object strP = str(p, value, state);
      if (strP != ScriptRuntime.UNDEFINED) {
        StringBuilder member = new StringBuilder();
        member.append(JSONFunctions.quote(p.toString())).append(':');
        if (!state.gap.isEmpty())
          member.append(' '); 
        member.append(strP);
        partial.add(member);
      } 
    } 
    if (partial.isEmpty()) {
      finalStr.append("{}");
    } else {
      int size = partial.size();
      int index = 0;
      if (state.gap.isEmpty()) {
        finalStr.append('{');
        for (Object str : partial) {
          finalStr.append(str);
          if (index < size - 1)
            finalStr.append(','); 
          index++;
        } 
      } else {
        finalStr.append("{\n");
        finalStr.append(state.indent);
        for (Object str : partial) {
          finalStr.append(str);
          if (index < size - 1) {
            finalStr.append(",\n");
            finalStr.append(state.indent);
          } 
          index++;
        } 
        finalStr.append('\n');
        finalStr.append(stepback);
      } 
      finalStr.append('}');
    } 
    state.stack.remove(value);
    state.indent = stepback;
    return finalStr.toString();
  }
  
  private static Object JA(Object value, StringifyState state) {
    assert value instanceof ScriptObject || value instanceof JSObject;
    if (state.stack.containsKey(value))
      throw ECMAErrors.typeError("JSON.stringify.cyclic", new String[0]); 
    state.stack.put(value, value);
    StringBuilder stepback = new StringBuilder(state.indent.toString());
    state.indent.append(state.gap);
    List<Object> partial = new ArrayList();
    int length = JSType.toInteger(getLength(value));
    int index = 0;
    while (index < length) {
      Object strP = str(Integer.valueOf(index), value, state);
      if (strP == ScriptRuntime.UNDEFINED)
        strP = "null"; 
      partial.add(strP);
      index++;
    } 
    StringBuilder finalStr = new StringBuilder();
    if (partial.isEmpty()) {
      finalStr.append("[]");
    } else if (state.gap.isEmpty()) {
      int size = partial.size();
      index = 0;
      finalStr.append('[');
      for (Object str : partial) {
        finalStr.append(str);
        if (index < size - 1)
          finalStr.append(','); 
        index++;
      } 
      finalStr.append(']');
    } else {
      int size = partial.size();
      index = 0;
      finalStr.append("[\n");
      finalStr.append(state.indent);
      for (Object str : partial) {
        finalStr.append(str);
        if (index < size - 1) {
          finalStr.append(",\n");
          finalStr.append(state.indent);
        } 
        index++;
      } 
      finalStr.append('\n');
      finalStr.append(stepback);
      finalStr.append(']');
    } 
    state.stack.remove(value);
    state.indent = stepback;
    return finalStr.toString();
  }
  
  private static String[] getOwnKeys(Object obj) {
    if (obj instanceof ScriptObject)
      return ((ScriptObject)obj).getOwnKeys(false); 
    if (obj instanceof ScriptObjectMirror)
      return ((ScriptObjectMirror)obj).getOwnKeys(false); 
    if (obj instanceof JSObject)
      return (String[])((JSObject)obj).keySet().toArray((Object[])new String[0]); 
    throw new AssertionError("should not reach here");
  }
  
  private static Object getLength(Object obj) {
    if (obj instanceof ScriptObject)
      return ((ScriptObject)obj).getLength(); 
    if (obj instanceof JSObject)
      return ((JSObject)obj).getMember("length"); 
    throw new AssertionError("should not reach here");
  }
  
  private static boolean isJSObjectArray(Object obj) {
    return (obj instanceof JSObject && ((JSObject)obj).isArray());
  }
  
  private static Object getProperty(Object holder, Object key) {
    if (holder instanceof ScriptObject)
      return ((ScriptObject)holder).get(key); 
    if (holder instanceof JSObject) {
      JSObject jsObj = (JSObject)holder;
      if (key instanceof Integer)
        return jsObj.getSlot(((Integer)key).intValue()); 
      return jsObj.getMember(Objects.toString(key));
    } 
    return new AssertionError("should not reach here");
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
