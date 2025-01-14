package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import org.openjdk.nashorn.api.scripting.NashornException;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ECMAException;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class NativeError extends ScriptObject {
  static final MethodHandle GET_COLUMNNUMBER = findOwnMH("getColumnNumber", Object.class, new Class[] { Object.class });
  
  static final MethodHandle SET_COLUMNNUMBER = findOwnMH("setColumnNumber", Object.class, new Class[] { Object.class, Object.class });
  
  static final MethodHandle GET_LINENUMBER = findOwnMH("getLineNumber", Object.class, new Class[] { Object.class });
  
  static final MethodHandle SET_LINENUMBER = findOwnMH("setLineNumber", Object.class, new Class[] { Object.class, Object.class });
  
  static final MethodHandle GET_FILENAME = findOwnMH("getFileName", Object.class, new Class[] { Object.class });
  
  static final MethodHandle SET_FILENAME = findOwnMH("setFileName", Object.class, new Class[] { Object.class, Object.class });
  
  static final MethodHandle GET_STACK = findOwnMH("getStack", Object.class, new Class[] { Object.class });
  
  static final MethodHandle SET_STACK = findOwnMH("setStack", Object.class, new Class[] { Object.class, Object.class });
  
  static final String MESSAGE = "message";
  
  static final String NAME = "name";
  
  static final String STACK = "__stack__";
  
  static final String LINENUMBER = "__lineNumber__";
  
  static final String COLUMNNUMBER = "__columnNumber__";
  
  static final String FILENAME = "__fileName__";
  
  public Object instMessage;
  
  public Object nashornException;
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private NativeError(Object msg, ScriptObject proto, PropertyMap map) {
    super(proto, map);
    if (msg != ScriptRuntime.UNDEFINED) {
      this.instMessage = JSType.toString(msg);
    } else {
      delete("message", false);
    } 
    initException(this);
  }
  
  NativeError(Object msg, Global global) {
    this(msg, global.getErrorPrototype(), $nasgenmap$);
  }
  
  private NativeError(Object msg) {
    this(msg, Global.instance());
  }
  
  public String getClassName() {
    return "Error";
  }
  
  public static NativeError constructor(boolean newObj, Object self, Object msg) {
    return new NativeError(msg);
  }
  
  static void initException(ScriptObject self) {
    new ECMAException(self, null);
  }
  
  public static Object captureStackTrace(Object self, Object errorObj) {
    ScriptObject sobj = Global.checkObject(errorObj);
    initException(sobj);
    sobj.delete("__stack__", false);
    if (!sobj.has("stack")) {
      ScriptFunction getStack = ScriptFunction.createBuiltin("getStack", GET_STACK);
      ScriptFunction setStack = ScriptFunction.createBuiltin("setStack", SET_STACK);
      sobj.addOwnProperty("stack", 2, getStack, setStack);
    } 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static Object dumpStack(Object self) {
    Thread.dumpStack();
    return ScriptRuntime.UNDEFINED;
  }
  
  public static Object printStackTrace(Object self) {
    return ECMAException.printStackTrace(Global.checkObject(self));
  }
  
  public static Object getStackTrace(Object self) {
    Object[] res;
    ScriptObject sobj = Global.checkObject(self);
    Object exception = ECMAException.getException(sobj);
    if (exception instanceof Throwable) {
      StackTraceElement[] arrayOfStackTraceElement = NashornException.getScriptFrames((Throwable)exception);
    } else {
      res = ScriptRuntime.EMPTY_ARRAY;
    } 
    return new NativeArray(res);
  }
  
  public static Object getLineNumber(Object self) {
    ScriptObject sobj = Global.checkObject(self);
    return sobj.has("__lineNumber__") ? sobj.get("__lineNumber__") : ECMAException.getLineNumber(sobj);
  }
  
  public static Object setLineNumber(Object self, Object value) {
    ScriptObject sobj = Global.checkObject(self);
    if (sobj.hasOwnProperty("__lineNumber__")) {
      sobj.put("__lineNumber__", value, false);
    } else {
      sobj.addOwnProperty("__lineNumber__", 2, value);
    } 
    return value;
  }
  
  public static Object getColumnNumber(Object self) {
    ScriptObject sobj = Global.checkObject(self);
    return sobj.has("__columnNumber__") ? sobj.get("__columnNumber__") : ECMAException.getColumnNumber((ScriptObject)self);
  }
  
  public static Object setColumnNumber(Object self, Object value) {
    ScriptObject sobj = Global.checkObject(self);
    if (sobj.hasOwnProperty("__columnNumber__")) {
      sobj.put("__columnNumber__", value, false);
    } else {
      sobj.addOwnProperty("__columnNumber__", 2, value);
    } 
    return value;
  }
  
  public static Object getFileName(Object self) {
    ScriptObject sobj = Global.checkObject(self);
    return sobj.has("__fileName__") ? sobj.get("__fileName__") : ECMAException.getFileName((ScriptObject)self);
  }
  
  public static Object setFileName(Object self, Object value) {
    ScriptObject sobj = Global.checkObject(self);
    if (sobj.hasOwnProperty("__fileName__")) {
      sobj.put("__fileName__", value, false);
    } else {
      sobj.addOwnProperty("__fileName__", 2, value);
    } 
    return value;
  }
  
  public static Object getStack(Object self) {
    ScriptObject sobj = Global.checkObject(self);
    if (sobj.has("__stack__"))
      return sobj.get("__stack__"); 
    Object exception = ECMAException.getException(sobj);
    if (exception instanceof Throwable) {
      Object value = getScriptStackString(sobj, (Throwable)exception);
      if (sobj.hasOwnProperty("__stack__")) {
        sobj.put("__stack__", value, false);
      } else {
        sobj.addOwnProperty("__stack__", 2, value);
      } 
      return value;
    } 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static Object setStack(Object self, Object value) {
    ScriptObject sobj = Global.checkObject(self);
    if (sobj.hasOwnProperty("__stack__")) {
      sobj.put("__stack__", value, false);
    } else {
      sobj.addOwnProperty("__stack__", 2, value);
    } 
    return value;
  }
  
  public static Object toString(Object self) {
    ScriptObject sobj = Global.checkObject(self);
    Object name = sobj.get("name");
    if (name == ScriptRuntime.UNDEFINED) {
      name = "Error";
    } else {
      name = JSType.toString(name);
    } 
    Object msg = sobj.get("message");
    if (msg == ScriptRuntime.UNDEFINED) {
      msg = "";
    } else {
      msg = JSType.toString(msg);
    } 
    if (((String)name).isEmpty())
      return msg; 
    if (((String)msg).isEmpty())
      return name; 
    return "" + name + ": " + name;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), NativeError.class, name, Lookup.MH.type(rtype, types));
  }
  
  private static String getScriptStackString(ScriptObject sobj, Throwable exp) {
    return JSType.toString(sobj) + "\n" + JSType.toString(sobj);
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_2
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'message'
    //   11: iconst_2
    //   12: ldc_w
    //   15: ldc_w
    //   18: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   21: invokeinterface add : (Ljava/lang/Object;)Z
    //   26: pop
    //   27: dup
    //   28: ldc_w 'nashornException'
    //   31: iconst_2
    //   32: ldc_w
    //   35: ldc_w
    //   38: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   41: invokeinterface add : (Ljava/lang/Object;)Z
    //   46: pop
    //   47: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   50: putstatic org/openjdk/nashorn/internal/objects/NativeError.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   53: return
  }
  
  public Object G$instMessage() {
    return this.instMessage;
  }
  
  public void S$instMessage(Object paramObject) {
    this.instMessage = paramObject;
  }
  
  public Object G$nashornException() {
    return this.nashornException;
  }
  
  public void S$nashornException(Object paramObject) {
    this.nashornException = paramObject;
  }
}
