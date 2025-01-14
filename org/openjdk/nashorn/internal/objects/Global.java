package org.openjdk.nashorn.internal.objects;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.api.scripting.ClassFilter;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.FindProperty;
import org.openjdk.nashorn.internal.runtime.GlobalConstants;
import org.openjdk.nashorn.internal.runtime.GlobalFunctions;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.NativeJavaPackage;
import org.openjdk.nashorn.internal.runtime.Property;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.Scope;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.ScriptingFunctions;
import org.openjdk.nashorn.internal.runtime.Specialization;
import org.openjdk.nashorn.internal.runtime.Symbol;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.linker.InvokeByName;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.regexp.RegExpResult;
import org.openjdk.nashorn.internal.scripts.JD;
import org.openjdk.nashorn.internal.scripts.JO;
import org.openjdk.nashorn.tools.ShellFunctions;

public final class Global extends Scope {
  private static final Object LAZY_SENTINEL = new Object();
  
  private static final Object LOCATION_PLACEHOLDER = new Object();
  
  private static final String PACKAGE_PREFIX = "org.openjdk.nashorn.internal.objects.";
  
  private InvokeByName TO_STRING;
  
  private InvokeByName VALUE_OF;
  
  public Object arguments;
  
  public Object parseInt;
  
  public Object parseFloat;
  
  public Object isNaN;
  
  public Object isFinite;
  
  public Object encodeURI;
  
  public Object encodeURIComponent;
  
  public Object decodeURI;
  
  public Object decodeURIComponent;
  
  public Object escape;
  
  public Object unescape;
  
  public Object print;
  
  public Object load;
  
  public Object loadWithNewGlobal;
  
  public Object exit;
  
  public Object quit;
  
  public static final double NaN = NaND;
  
  public static final double Infinity = InfinityD;
  
  public static final Object undefined = ScriptRuntime.UNDEFINED;
  
  public Object eval;
  
  public volatile Object object;
  
  public volatile Object function;
  
  public volatile Object array;
  
  public volatile Object string;
  
  public volatile Object _boolean;
  
  public volatile Object number;
  
  public static Object getDate(Object self) {
    Global global = instanceFrom(self);
    if (global.date == LAZY_SENTINEL)
      global.date = global.getBuiltinDate(); 
    return global.date;
  }
  
  public static void setDate(Object self, Object value) {
    Global global = instanceFrom(self);
    global.date = value;
  }
  
  private volatile Object date = LAZY_SENTINEL;
  
  public static Object getRegExp(Object self) {
    Global global = instanceFrom(self);
    if (global.regexp == LAZY_SENTINEL)
      global.regexp = global.getBuiltinRegExp(); 
    return global.regexp;
  }
  
  public static void setRegExp(Object self, Object value) {
    Global global = instanceFrom(self);
    global.regexp = value;
  }
  
  private volatile Object regexp = LAZY_SENTINEL;
  
  public static Object getJSON(Object self) {
    Global global = instanceFrom(self);
    if (global.json == LAZY_SENTINEL)
      global.json = global.getBuiltinJSON(); 
    return global.json;
  }
  
  public static void setJSON(Object self, Object value) {
    Global global = instanceFrom(self);
    global.json = value;
  }
  
  private volatile Object json = LAZY_SENTINEL;
  
  public static Object getJSAdapter(Object self) {
    Global global = instanceFrom(self);
    if (global.jsadapter == LAZY_SENTINEL)
      global.jsadapter = global.getBuiltinJSAdapter(); 
    return global.jsadapter;
  }
  
  public static void setJSAdapter(Object self, Object value) {
    Global global = instanceFrom(self);
    global.jsadapter = value;
  }
  
  private volatile Object jsadapter = LAZY_SENTINEL;
  
  public volatile Object math;
  
  public volatile Object error;
  
  public static Object getEvalError(Object self) {
    Global global = instanceFrom(self);
    if (global.evalError == LAZY_SENTINEL)
      global.evalError = global.getBuiltinEvalError(); 
    return global.evalError;
  }
  
  public static void setEvalError(Object self, Object value) {
    Global global = instanceFrom(self);
    global.evalError = value;
  }
  
  private volatile Object evalError = LAZY_SENTINEL;
  
  public static Object getRangeError(Object self) {
    Global global = instanceFrom(self);
    if (global.rangeError == LAZY_SENTINEL)
      global.rangeError = global.getBuiltinRangeError(); 
    return global.rangeError;
  }
  
  public static void setRangeError(Object self, Object value) {
    Global global = instanceFrom(self);
    global.rangeError = value;
  }
  
  private volatile Object rangeError = LAZY_SENTINEL;
  
  public volatile Object referenceError;
  
  public volatile Object syntaxError;
  
  public volatile Object typeError;
  
  public static Object getURIError(Object self) {
    Global global = instanceFrom(self);
    if (global.uriError == LAZY_SENTINEL)
      global.uriError = global.getBuiltinURIError(); 
    return global.uriError;
  }
  
  public static void setURIError(Object self, Object value) {
    Global global = instanceFrom(self);
    global.uriError = value;
  }
  
  private volatile Object uriError = LAZY_SENTINEL;
  
  private volatile Object arrayBuffer;
  
  private volatile Object dataView;
  
  private volatile Object int8Array;
  
  private volatile Object uint8Array;
  
  private volatile Object uint8ClampedArray;
  
  private volatile Object int16Array;
  
  private volatile Object uint16Array;
  
  private volatile Object int32Array;
  
  private volatile Object uint32Array;
  
  private volatile Object float32Array;
  
  private volatile Object float64Array;
  
  private volatile Object symbol;
  
  private volatile Object map;
  
  private volatile Object weakMap;
  
  private volatile Object set;
  
  private volatile Object weakSet;
  
  public volatile Object packages;
  
  public volatile Object com;
  
  public volatile Object edu;
  
  public volatile Object java;
  
  public volatile Object javafx;
  
  public volatile Object javax;
  
  public volatile Object org;
  
  private volatile Object javaImporter;
  
  private volatile Object javaApi;
  
  public static Object getArrayBuffer(Object self) {
    Global global = instanceFrom(self);
    if (global.arrayBuffer == LAZY_SENTINEL)
      global.arrayBuffer = global.getBuiltinArrayBuffer(); 
    return global.arrayBuffer;
  }
  
  public static void setArrayBuffer(Object self, Object value) {
    Global global = instanceFrom(self);
    global.arrayBuffer = value;
  }
  
  public static Object getDataView(Object self) {
    Global global = instanceFrom(self);
    if (global.dataView == LAZY_SENTINEL)
      global.dataView = global.getBuiltinDataView(); 
    return global.dataView;
  }
  
  public static void setDataView(Object self, Object value) {
    Global global = instanceFrom(self);
    global.dataView = value;
  }
  
  public static Object getInt8Array(Object self) {
    Global global = instanceFrom(self);
    if (global.int8Array == LAZY_SENTINEL)
      global.int8Array = global.getBuiltinInt8Array(); 
    return global.int8Array;
  }
  
  public static void setInt8Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.int8Array = value;
  }
  
  public static Object getUint8Array(Object self) {
    Global global = instanceFrom(self);
    if (global.uint8Array == LAZY_SENTINEL)
      global.uint8Array = global.getBuiltinUint8Array(); 
    return global.uint8Array;
  }
  
  public static void setUint8Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.uint8Array = value;
  }
  
  public static Object getUint8ClampedArray(Object self) {
    Global global = instanceFrom(self);
    if (global.uint8ClampedArray == LAZY_SENTINEL)
      global.uint8ClampedArray = global.getBuiltinUint8ClampedArray(); 
    return global.uint8ClampedArray;
  }
  
  public static void setUint8ClampedArray(Object self, Object value) {
    Global global = instanceFrom(self);
    global.uint8ClampedArray = value;
  }
  
  public static Object getInt16Array(Object self) {
    Global global = instanceFrom(self);
    if (global.int16Array == LAZY_SENTINEL)
      global.int16Array = global.getBuiltinInt16Array(); 
    return global.int16Array;
  }
  
  public static void setInt16Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.int16Array = value;
  }
  
  public static Object getUint16Array(Object self) {
    Global global = instanceFrom(self);
    if (global.uint16Array == LAZY_SENTINEL)
      global.uint16Array = global.getBuiltinUint16Array(); 
    return global.uint16Array;
  }
  
  public static void setUint16Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.uint16Array = value;
  }
  
  public static Object getInt32Array(Object self) {
    Global global = instanceFrom(self);
    if (global.int32Array == LAZY_SENTINEL)
      global.int32Array = global.getBuiltinInt32Array(); 
    return global.int32Array;
  }
  
  public static void setInt32Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.int32Array = value;
  }
  
  public static Object getUint32Array(Object self) {
    Global global = instanceFrom(self);
    if (global.uint32Array == LAZY_SENTINEL)
      global.uint32Array = global.getBuiltinUint32Array(); 
    return global.uint32Array;
  }
  
  public static void setUint32Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.uint32Array = value;
  }
  
  public static Object getFloat32Array(Object self) {
    Global global = instanceFrom(self);
    if (global.float32Array == LAZY_SENTINEL)
      global.float32Array = global.getBuiltinFloat32Array(); 
    return global.float32Array;
  }
  
  public static void setFloat32Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.float32Array = value;
  }
  
  public static Object getFloat64Array(Object self) {
    Global global = instanceFrom(self);
    if (global.float64Array == LAZY_SENTINEL)
      global.float64Array = global.getBuiltinFloat64Array(); 
    return global.float64Array;
  }
  
  public static void setFloat64Array(Object self, Object value) {
    Global global = instanceFrom(self);
    global.float64Array = value;
  }
  
  public static Object getSymbol(Object self) {
    Global global = instanceFrom(self);
    if (global.symbol == LAZY_SENTINEL)
      global.symbol = global.getBuiltinSymbol(); 
    return global.symbol;
  }
  
  public static void setSymbol(Object self, Object value) {
    (instanceFrom(self)).symbol = value;
  }
  
  public static Object getMap(Object self) {
    Global global = instanceFrom(self);
    if (global.map == LAZY_SENTINEL)
      global.map = global.getBuiltinMap(); 
    return global.map;
  }
  
  public static void setMap(Object self, Object value) {
    (instanceFrom(self)).map = value;
  }
  
  public static Object getWeakMap(Object self) {
    Global global = instanceFrom(self);
    if (global.weakMap == LAZY_SENTINEL)
      global.weakMap = global.getBuiltinWeakMap(); 
    return global.weakMap;
  }
  
  public static void setWeakMap(Object self, Object value) {
    (instanceFrom(self)).weakMap = value;
  }
  
  public static Object getSet(Object self) {
    Global global = instanceFrom(self);
    if (global.set == LAZY_SENTINEL)
      global.set = global.getBuiltinSet(); 
    return global.set;
  }
  
  public static void setSet(Object self, Object value) {
    (instanceFrom(self)).set = value;
  }
  
  public static Object getWeakSet(Object self) {
    Global global = instanceFrom(self);
    if (global.weakSet == LAZY_SENTINEL)
      global.weakSet = global.getBuiltinWeakSet(); 
    return global.weakSet;
  }
  
  public static void setWeakSet(Object self, Object value) {
    (instanceFrom(self)).weakSet = value;
  }
  
  public static Object getJavaImporter(Object self) {
    Global global = instanceFrom(self);
    if (global.javaImporter == LAZY_SENTINEL)
      global.javaImporter = global.getBuiltinJavaImporter(); 
    return global.javaImporter;
  }
  
  public static void setJavaImporter(Object self, Object value) {
    Global global = instanceFrom(self);
    global.javaImporter = value;
  }
  
  public static Object getJavaApi(Object self) {
    Global global = instanceFrom(self);
    if (global.javaApi == LAZY_SENTINEL)
      global.javaApi = global.getBuiltinJavaApi(); 
    return global.javaApi;
  }
  
  public static void setJavaApi(Object self, Object value) {
    Global global = instanceFrom(self);
    global.javaApi = value;
  }
  
  public static final Object __FILE__ = LOCATION_PLACEHOLDER;
  
  public static final Object __DIR__ = LOCATION_PLACEHOLDER;
  
  public static final Object __LINE__ = LOCATION_PLACEHOLDER;
  
  private volatile NativeDate DEFAULT_DATE;
  
  private volatile NativeRegExp DEFAULT_REGEXP;
  
  private ScriptFunction builtinFunction;
  
  private ScriptFunction builtinObject;
  
  private ScriptFunction builtinArray;
  
  private ScriptFunction builtinBoolean;
  
  private ScriptFunction builtinDate;
  
  private ScriptObject builtinJSON;
  
  private ScriptFunction builtinJSAdapter;
  
  private ScriptObject builtinMath;
  
  private ScriptFunction builtinNumber;
  
  private ScriptFunction builtinRegExp;
  
  private ScriptFunction builtinString;
  
  private ScriptFunction builtinError;
  
  private ScriptFunction builtinEval;
  
  private ScriptFunction builtinEvalError;
  
  private ScriptFunction builtinRangeError;
  
  private ScriptFunction builtinReferenceError;
  
  private ScriptFunction builtinSyntaxError;
  
  private ScriptFunction builtinTypeError;
  
  private ScriptFunction builtinURIError;
  
  private ScriptObject builtinPackages;
  
  private ScriptObject builtinCom;
  
  private ScriptObject builtinEdu;
  
  private ScriptObject builtinJava;
  
  private ScriptObject builtinJavafx;
  
  private ScriptObject builtinJavax;
  
  private ScriptObject builtinOrg;
  
  private ScriptFunction builtinJavaImporter;
  
  private ScriptObject builtinJavaApi;
  
  private ScriptFunction builtinArrayBuffer;
  
  private ScriptFunction builtinDataView;
  
  private ScriptFunction builtinInt8Array;
  
  private ScriptFunction builtinUint8Array;
  
  private ScriptFunction builtinUint8ClampedArray;
  
  private ScriptFunction builtinInt16Array;
  
  private ScriptFunction builtinUint16Array;
  
  private ScriptFunction builtinInt32Array;
  
  private ScriptFunction builtinUint32Array;
  
  private ScriptFunction builtinFloat32Array;
  
  private ScriptFunction builtinFloat64Array;
  
  private ScriptFunction builtinSymbol;
  
  private ScriptFunction builtinMap;
  
  private ScriptFunction builtinWeakMap;
  
  private ScriptFunction builtinSet;
  
  private ScriptFunction builtinWeakSet;
  
  private ScriptObject builtinIteratorPrototype;
  
  private ScriptObject builtinMapIteratorPrototype;
  
  private ScriptObject builtinSetIteratorPrototype;
  
  private ScriptObject builtinArrayIteratorPrototype;
  
  private ScriptObject builtinStringIteratorPrototype;
  
  private ScriptFunction builtInJavaExtend;
  
  private ScriptFunction builtInJavaTo;
  
  private ScriptFunction typeErrorThrower;
  
  private RegExpResult lastRegExpResult;
  
  NativeDate getDefaultDate() {
    return this.DEFAULT_DATE;
  }
  
  NativeRegExp getDefaultRegExp() {
    return this.DEFAULT_REGEXP;
  }
  
  private static final MethodHandle EVAL = findOwnMH_S("eval", Object.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle NO_SUCH_PROPERTY = findOwnMH_S("__noSuchProperty__", Object.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle PRINT = findOwnMH_S("print", Object.class, new Class[] { Object.class, Object[].class });
  
  private static final MethodHandle PRINTLN = findOwnMH_S("println", Object.class, new Class[] { Object.class, Object[].class });
  
  private static final MethodHandle LOAD = findOwnMH_S("load", Object.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle LOAD_WITH_NEW_GLOBAL = findOwnMH_S("loadWithNewGlobal", Object.class, new Class[] { Object.class, Object[].class });
  
  private static final MethodHandle EXIT = findOwnMH_S("exit", Object.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle LEXICAL_SCOPE_FILTER = findOwnMH_S("lexicalScopeFilter", Object.class, new Class[] { Object.class });
  
  private static PropertyMap $nasgenmap$;
  
  private final Context context;
  
  private ThreadLocal<ScriptContext> scontext;
  
  private ScriptEngine engine;
  
  private volatile ScriptContext initscontext;
  
  private final LexicalScope lexicalScope;
  
  private SwitchPoint lexicalScopeSwitchPoint;
  
  private final Map<Object, InvokeByName> namedInvokers;
  
  private final Map<Object, MethodHandle> dynamicInvokers;
  
  static {
    $clinit$();
  }
  
  public void setScriptContext(ScriptContext ctxt) {
    assert this.scontext != null;
    this.scontext.set(ctxt);
  }
  
  public ScriptContext getScriptContext() {
    assert this.scontext != null;
    return this.scontext.get();
  }
  
  public void setInitScriptContext(ScriptContext ctxt) {
    this.initscontext = ctxt;
  }
  
  private ScriptContext currentContext() {
    ScriptContext sc = (this.scontext != null) ? this.scontext.get() : null;
    if (sc != null)
      return sc; 
    if (this.initscontext != null)
      return this.initscontext; 
    return (this.engine != null) ? this.engine.getContext() : null;
  }
  
  protected Context getContext() {
    return this.context;
  }
  
  protected boolean useDualFields() {
    return this.context.useDualFields();
  }
  
  private static PropertyMap checkAndGetMap(Context context) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("nashorn.createGlobal")); 
    Objects.requireNonNull(context);
    return $nasgenmap$;
  }
  
  public Global(Context context) {
    super(checkAndGetMap(context));
    this.namedInvokers = new ConcurrentHashMap<>();
    this.dynamicInvokers = new ConcurrentHashMap<>();
    this.context = context;
    this.lexicalScope = isES6() ? new LexicalScope(this) : null;
  }
  
  public static Global instance() {
    return Objects.<Global>requireNonNull(Context.getGlobal());
  }
  
  private static Global instanceFrom(Object self) {
    return (self instanceof Global) ? (Global)self : instance();
  }
  
  public static boolean hasInstance() {
    return (Context.getGlobal() != null);
  }
  
  static ScriptEnvironment getEnv() {
    return instance().getContext().getEnv();
  }
  
  static Context getThisContext() {
    return instance().getContext();
  }
  
  public ClassFilter getClassFilter() {
    return this.context.getClassFilter();
  }
  
  public boolean isOfContext(Context ctxt) {
    return (this.context == ctxt);
  }
  
  public boolean isStrictContext() {
    return (this.context.getEnv())._strict;
  }
  
  public void initBuiltinObjects(ScriptEngine eng) {
    if (this.builtinObject != null)
      return; 
    this.TO_STRING = new InvokeByName("toString", ScriptObject.class);
    this.VALUE_OF = new InvokeByName("valueOf", ScriptObject.class);
    this.engine = eng;
    if (this.engine != null)
      this.scontext = new ThreadLocal<>(); 
    init(eng);
  }
  
  public Object wrapAsObject(Object obj) {
    if (obj instanceof Boolean)
      return new NativeBoolean(((Boolean)obj).booleanValue(), this); 
    if (obj instanceof Number)
      return new NativeNumber(((Number)obj).doubleValue(), this); 
    if (JSType.isString(obj))
      return new NativeString((CharSequence)obj, this); 
    if (obj instanceof Object[])
      return new NativeArray(ArrayData.allocate((Object[])obj), this); 
    if (obj instanceof double[])
      return new NativeArray(ArrayData.allocate((double[])obj), this); 
    if (obj instanceof int[])
      return new NativeArray(ArrayData.allocate((int[])obj), this); 
    if (obj instanceof ArrayData)
      return new NativeArray((ArrayData)obj, this); 
    if (obj instanceof Symbol)
      return new NativeSymbol((Symbol)obj, this); 
    return obj;
  }
  
  public static GuardedInvocation primitiveLookup(LinkRequest request, Object self) {
    if (JSType.isString(self))
      return NativeString.lookupPrimitive(request, self); 
    if (self instanceof Number)
      return NativeNumber.lookupPrimitive(request, self); 
    if (self instanceof Boolean)
      return NativeBoolean.lookupPrimitive(request, self); 
    if (self instanceof Symbol)
      return NativeSymbol.lookupPrimitive(request, self); 
    throw new IllegalArgumentException("Unsupported primitive: " + self);
  }
  
  public static MethodHandle getPrimitiveWrapFilter(Object self) {
    if (JSType.isString(self))
      return NativeString.WRAPFILTER; 
    if (self instanceof Number)
      return NativeNumber.WRAPFILTER; 
    if (self instanceof Boolean)
      return NativeBoolean.WRAPFILTER; 
    throw new IllegalArgumentException("Unsupported primitive: " + self);
  }
  
  public ScriptObject newObject() {
    return useDualFields() ? (ScriptObject)new JD(getObjectPrototype()) : (ScriptObject)new JO(getObjectPrototype());
  }
  
  public Object getDefaultValue(ScriptObject sobj, Class<?> typeHint) {
    Class<?> hint = typeHint;
    if (hint == null)
      hint = Number.class; 
    try {
      if (hint == String.class) {
        Object toString = this.TO_STRING.getGetter().invokeExact(sobj);
        if (Bootstrap.isCallable(toString)) {
          Object value = this.TO_STRING.getInvoker().invokeExact(toString, sobj);
          if (JSType.isPrimitive(value))
            return value; 
        } 
        Object valueOf = this.VALUE_OF.getGetter().invokeExact(sobj);
        if (Bootstrap.isCallable(valueOf)) {
          Object value = this.VALUE_OF.getInvoker().invokeExact(valueOf, sobj);
          if (JSType.isPrimitive(value))
            return value; 
        } 
        throw ECMAErrors.typeError(this, "cannot.get.default.string", new String[0]);
      } 
      if (hint == Number.class) {
        Object valueOf = this.VALUE_OF.getGetter().invokeExact(sobj);
        if (Bootstrap.isCallable(valueOf)) {
          Object value = this.VALUE_OF.getInvoker().invokeExact(valueOf, sobj);
          if (JSType.isPrimitive(value))
            return value; 
        } 
        Object toString = this.TO_STRING.getGetter().invokeExact(sobj);
        if (Bootstrap.isCallable(toString)) {
          Object value = this.TO_STRING.getInvoker().invokeExact(toString, sobj);
          if (JSType.isPrimitive(value))
            return value; 
        } 
        throw ECMAErrors.typeError(this, "cannot.get.default.number", new String[0]);
      } 
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
    return ScriptRuntime.UNDEFINED;
  }
  
  public boolean isError(ScriptObject sobj) {
    ScriptObject errorProto = getErrorPrototype();
    ScriptObject proto = sobj.getProto();
    while (proto != null) {
      if (proto == errorProto)
        return true; 
      proto = proto.getProto();
    } 
    return false;
  }
  
  public ScriptObject newError(String msg) {
    return new NativeError(msg, this);
  }
  
  public ScriptObject newEvalError(String msg) {
    return new NativeEvalError(msg, this);
  }
  
  public ScriptObject newRangeError(String msg) {
    return new NativeRangeError(msg, this);
  }
  
  public ScriptObject newReferenceError(String msg) {
    return new NativeReferenceError(msg, this);
  }
  
  public ScriptObject newSyntaxError(String msg) {
    return new NativeSyntaxError(msg, this);
  }
  
  public ScriptObject newTypeError(String msg) {
    return new NativeTypeError(msg, this);
  }
  
  public ScriptObject newURIError(String msg) {
    return new NativeURIError(msg, this);
  }
  
  public PropertyDescriptor newGenericDescriptor(boolean configurable, boolean enumerable) {
    return new GenericPropertyDescriptor(configurable, enumerable, this);
  }
  
  public PropertyDescriptor newDataDescriptor(Object value, boolean configurable, boolean enumerable, boolean writable) {
    return new DataPropertyDescriptor(configurable, enumerable, writable, value, this);
  }
  
  public PropertyDescriptor newAccessorDescriptor(Object get, Object set, boolean configurable, boolean enumerable) {
    AccessorPropertyDescriptor desc = new AccessorPropertyDescriptor(configurable, enumerable, (get == null) ? ScriptRuntime.UNDEFINED : get, (set == null) ? ScriptRuntime.UNDEFINED : set, this);
    if (get == null)
      desc.delete("get", false); 
    if (set == null)
      desc.delete("set", false); 
    return desc;
  }
  
  private <T> T getLazilyCreatedValue(Object key, Supplier<T> creator, Map<Object, T> map) {
    T obj = map.get(key);
    if (obj != null)
      return obj; 
    Global oldGlobal = Context.getGlobal();
    boolean differentGlobal = (oldGlobal != this);
    try {
      if (differentGlobal)
        Context.setGlobal(this); 
      T newObj = creator.get();
      T existingObj = map.putIfAbsent(key, newObj);
      return (existingObj != null) ? existingObj : newObj;
    } finally {
      if (differentGlobal)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  public InvokeByName getInvokeByName(Object key, Supplier<InvokeByName> creator) {
    return getLazilyCreatedValue(key, creator, this.namedInvokers);
  }
  
  public MethodHandle getDynamicInvoker(Object key, Supplier<MethodHandle> creator) {
    return getLazilyCreatedValue(key, creator, this.dynamicInvokers);
  }
  
  public static Object __noSuchProperty__(Object self, Object name) {
    Global global = instance();
    ScriptContext sctxt = global.currentContext();
    String nameStr = name.toString();
    if (sctxt != null) {
      int scope = sctxt.getAttributesScope(nameStr);
      if (scope != -1)
        return ScriptObjectMirror.unwrap(sctxt.getAttribute(nameStr, scope), global); 
    } 
    if ("context".equals(nameStr))
      return sctxt; 
    if ("engine".equals(nameStr))
      if (System.getSecurityManager() == null || global.getClassFilter() == null)
        return global.engine;  
    if (self == ScriptRuntime.UNDEFINED)
      throw ECMAErrors.referenceError(global, "not.defined", new String[] { nameStr }); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static Object eval(Object self, Object str) {
    return directEval(self, str, instanceFrom(self), ScriptRuntime.UNDEFINED, false);
  }
  
  public static Object directEval(Object self, Object str, Object callThis, Object location, boolean strict) {
    if (!JSType.isString(str))
      return str; 
    Global global = instanceFrom(self);
    ScriptObject scope = (self instanceof ScriptObject && ((ScriptObject)self).isScope()) ? (ScriptObject)self : (ScriptObject)global;
    return global.getContext().eval(scope, str.toString(), callThis, location, strict, true);
  }
  
  public static Object print(Object self, Object... objects) {
    return instanceFrom(self).printImpl(false, objects);
  }
  
  public static Object println(Object self, Object... objects) {
    return instanceFrom(self).printImpl(true, objects);
  }
  
  public static Object load(Object self, Object source) throws IOException {
    Global global = instanceFrom(self);
    return global.getContext().load(self, source);
  }
  
  public static Object loadWithNewGlobal(Object self, Object... args) throws IOException {
    Global global = instanceFrom(self);
    int length = args.length;
    boolean hasArgs = (0 < length);
    Object from = hasArgs ? args[0] : ScriptRuntime.UNDEFINED;
    Object[] arguments = hasArgs ? Arrays.<Object>copyOfRange(args, 1, length) : args;
    return global.getContext().loadWithNewGlobal(from, arguments);
  }
  
  public static Object exit(Object self, Object code) {
    System.exit(JSType.toInt32(code));
    return ScriptRuntime.UNDEFINED;
  }
  
  public ScriptObject getObjectPrototype() {
    return ScriptFunction.getPrototype(this.builtinObject);
  }
  
  public ScriptObject getFunctionPrototype() {
    return ScriptFunction.getPrototype(this.builtinFunction);
  }
  
  public ScriptObject getArrayPrototype() {
    return ScriptFunction.getPrototype(this.builtinArray);
  }
  
  ScriptObject getBooleanPrototype() {
    return ScriptFunction.getPrototype(this.builtinBoolean);
  }
  
  ScriptObject getNumberPrototype() {
    return ScriptFunction.getPrototype(this.builtinNumber);
  }
  
  ScriptObject getDatePrototype() {
    return ScriptFunction.getPrototype(getBuiltinDate());
  }
  
  ScriptObject getRegExpPrototype() {
    return ScriptFunction.getPrototype(getBuiltinRegExp());
  }
  
  ScriptObject getStringPrototype() {
    return ScriptFunction.getPrototype(this.builtinString);
  }
  
  ScriptObject getErrorPrototype() {
    return ScriptFunction.getPrototype(this.builtinError);
  }
  
  ScriptObject getEvalErrorPrototype() {
    return ScriptFunction.getPrototype(getBuiltinEvalError());
  }
  
  ScriptObject getRangeErrorPrototype() {
    return ScriptFunction.getPrototype(getBuiltinRangeError());
  }
  
  ScriptObject getReferenceErrorPrototype() {
    return ScriptFunction.getPrototype(this.builtinReferenceError);
  }
  
  ScriptObject getSyntaxErrorPrototype() {
    return ScriptFunction.getPrototype(this.builtinSyntaxError);
  }
  
  ScriptObject getTypeErrorPrototype() {
    return ScriptFunction.getPrototype(this.builtinTypeError);
  }
  
  ScriptObject getURIErrorPrototype() {
    return ScriptFunction.getPrototype(getBuiltinURIError());
  }
  
  ScriptObject getJavaImporterPrototype() {
    return ScriptFunction.getPrototype(getBuiltinJavaImporter());
  }
  
  ScriptObject getJSAdapterPrototype() {
    return ScriptFunction.getPrototype(getBuiltinJSAdapter());
  }
  
  ScriptObject getSymbolPrototype() {
    return ScriptFunction.getPrototype(getBuiltinSymbol());
  }
  
  ScriptObject getMapPrototype() {
    return ScriptFunction.getPrototype(getBuiltinMap());
  }
  
  ScriptObject getWeakMapPrototype() {
    return ScriptFunction.getPrototype(getBuiltinWeakMap());
  }
  
  ScriptObject getSetPrototype() {
    return ScriptFunction.getPrototype(getBuiltinSet());
  }
  
  ScriptObject getWeakSetPrototype() {
    return ScriptFunction.getPrototype(getBuiltinWeakSet());
  }
  
  ScriptObject getIteratorPrototype() {
    if (this.builtinIteratorPrototype == null)
      this.builtinIteratorPrototype = initPrototype("AbstractIterator", getObjectPrototype()); 
    return this.builtinIteratorPrototype;
  }
  
  ScriptObject getMapIteratorPrototype() {
    if (this.builtinMapIteratorPrototype == null)
      this.builtinMapIteratorPrototype = initPrototype("MapIterator", getIteratorPrototype()); 
    return this.builtinMapIteratorPrototype;
  }
  
  ScriptObject getSetIteratorPrototype() {
    if (this.builtinSetIteratorPrototype == null)
      this.builtinSetIteratorPrototype = initPrototype("SetIterator", getIteratorPrototype()); 
    return this.builtinSetIteratorPrototype;
  }
  
  ScriptObject getArrayIteratorPrototype() {
    if (this.builtinArrayIteratorPrototype == null)
      this.builtinArrayIteratorPrototype = initPrototype("ArrayIterator", getIteratorPrototype()); 
    return this.builtinArrayIteratorPrototype;
  }
  
  ScriptObject getStringIteratorPrototype() {
    if (this.builtinStringIteratorPrototype == null)
      this.builtinStringIteratorPrototype = initPrototype("StringIterator", getIteratorPrototype()); 
    return this.builtinStringIteratorPrototype;
  }
  
  private synchronized ScriptFunction getBuiltinArrayBuffer() {
    if (this.builtinArrayBuffer == null)
      this.builtinArrayBuffer = initConstructorAndSwitchPoint("ArrayBuffer", ScriptFunction.class); 
    return this.builtinArrayBuffer;
  }
  
  ScriptObject getArrayBufferPrototype() {
    return ScriptFunction.getPrototype(getBuiltinArrayBuffer());
  }
  
  private synchronized ScriptFunction getBuiltinDataView() {
    if (this.builtinDataView == null)
      this.builtinDataView = initConstructorAndSwitchPoint("DataView", ScriptFunction.class); 
    return this.builtinDataView;
  }
  
  ScriptObject getDataViewPrototype() {
    return ScriptFunction.getPrototype(getBuiltinDataView());
  }
  
  private synchronized ScriptFunction getBuiltinInt8Array() {
    if (this.builtinInt8Array == null)
      this.builtinInt8Array = initConstructorAndSwitchPoint("Int8Array", ScriptFunction.class); 
    return this.builtinInt8Array;
  }
  
  ScriptObject getInt8ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinInt8Array());
  }
  
  private synchronized ScriptFunction getBuiltinUint8Array() {
    if (this.builtinUint8Array == null)
      this.builtinUint8Array = initConstructorAndSwitchPoint("Uint8Array", ScriptFunction.class); 
    return this.builtinUint8Array;
  }
  
  ScriptObject getUint8ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinUint8Array());
  }
  
  private synchronized ScriptFunction getBuiltinUint8ClampedArray() {
    if (this.builtinUint8ClampedArray == null)
      this.builtinUint8ClampedArray = initConstructorAndSwitchPoint("Uint8ClampedArray", ScriptFunction.class); 
    return this.builtinUint8ClampedArray;
  }
  
  ScriptObject getUint8ClampedArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinUint8ClampedArray());
  }
  
  private synchronized ScriptFunction getBuiltinInt16Array() {
    if (this.builtinInt16Array == null)
      this.builtinInt16Array = initConstructorAndSwitchPoint("Int16Array", ScriptFunction.class); 
    return this.builtinInt16Array;
  }
  
  ScriptObject getInt16ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinInt16Array());
  }
  
  private synchronized ScriptFunction getBuiltinUint16Array() {
    if (this.builtinUint16Array == null)
      this.builtinUint16Array = initConstructorAndSwitchPoint("Uint16Array", ScriptFunction.class); 
    return this.builtinUint16Array;
  }
  
  ScriptObject getUint16ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinUint16Array());
  }
  
  private synchronized ScriptFunction getBuiltinInt32Array() {
    if (this.builtinInt32Array == null)
      this.builtinInt32Array = initConstructorAndSwitchPoint("Int32Array", ScriptFunction.class); 
    return this.builtinInt32Array;
  }
  
  ScriptObject getInt32ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinInt32Array());
  }
  
  private synchronized ScriptFunction getBuiltinUint32Array() {
    if (this.builtinUint32Array == null)
      this.builtinUint32Array = initConstructorAndSwitchPoint("Uint32Array", ScriptFunction.class); 
    return this.builtinUint32Array;
  }
  
  ScriptObject getUint32ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinUint32Array());
  }
  
  private synchronized ScriptFunction getBuiltinFloat32Array() {
    if (this.builtinFloat32Array == null)
      this.builtinFloat32Array = initConstructorAndSwitchPoint("Float32Array", ScriptFunction.class); 
    return this.builtinFloat32Array;
  }
  
  ScriptObject getFloat32ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinFloat32Array());
  }
  
  private synchronized ScriptFunction getBuiltinFloat64Array() {
    if (this.builtinFloat64Array == null)
      this.builtinFloat64Array = initConstructorAndSwitchPoint("Float64Array", ScriptFunction.class); 
    return this.builtinFloat64Array;
  }
  
  ScriptObject getFloat64ArrayPrototype() {
    return ScriptFunction.getPrototype(getBuiltinFloat64Array());
  }
  
  public ScriptFunction getTypeErrorThrower() {
    return this.typeErrorThrower;
  }
  
  private synchronized ScriptFunction getBuiltinDate() {
    if (this.builtinDate == null) {
      this.builtinDate = initConstructorAndSwitchPoint("Date", ScriptFunction.class);
      ScriptObject dateProto = ScriptFunction.getPrototype(this.builtinDate);
      this.DEFAULT_DATE = new NativeDate(Double.NaN, dateProto);
    } 
    return this.builtinDate;
  }
  
  private synchronized ScriptFunction getBuiltinEvalError() {
    if (this.builtinEvalError == null)
      this.builtinEvalError = initErrorSubtype("EvalError", getErrorPrototype()); 
    return this.builtinEvalError;
  }
  
  private ScriptFunction getBuiltinFunction() {
    return this.builtinFunction;
  }
  
  public static SwitchPoint getBuiltinFunctionApplySwitchPoint() {
    return ScriptFunction.getPrototype(instance().getBuiltinFunction()).getProperty("apply").getBuiltinSwitchPoint();
  }
  
  private static boolean isBuiltinFunctionProperty(String name) {
    Global instance = instance();
    ScriptFunction builtinFunction = instance.getBuiltinFunction();
    if (builtinFunction == null)
      return false; 
    boolean isBuiltinFunction = (instance.function == builtinFunction);
    return (isBuiltinFunction && ScriptFunction.getPrototype(builtinFunction).getProperty(name).isBuiltin());
  }
  
  public static boolean isBuiltinFunctionPrototypeApply() {
    return isBuiltinFunctionProperty("apply");
  }
  
  public static boolean isBuiltinFunctionPrototypeCall() {
    return isBuiltinFunctionProperty("call");
  }
  
  private synchronized ScriptFunction getBuiltinJSAdapter() {
    if (this.builtinJSAdapter == null)
      this.builtinJSAdapter = initConstructorAndSwitchPoint("JSAdapter", ScriptFunction.class); 
    return this.builtinJSAdapter;
  }
  
  private synchronized ScriptObject getBuiltinJSON() {
    if (this.builtinJSON == null)
      this.builtinJSON = initConstructorAndSwitchPoint("JSON", ScriptObject.class); 
    return this.builtinJSON;
  }
  
  private synchronized ScriptFunction getBuiltinJavaImporter() {
    if ((getContext().getEnv())._no_java)
      throw new IllegalStateException(); 
    if (this.builtinJavaImporter == null)
      this.builtinJavaImporter = initConstructor("JavaImporter", ScriptFunction.class); 
    return this.builtinJavaImporter;
  }
  
  private synchronized ScriptObject getBuiltinJavaApi() {
    if ((getContext().getEnv())._no_java)
      throw new IllegalStateException(); 
    if (this.builtinJavaApi == null) {
      this.builtinJavaApi = initConstructor("Java", ScriptObject.class);
      this.builtInJavaExtend = (ScriptFunction)this.builtinJavaApi.get("extend");
      this.builtInJavaTo = (ScriptFunction)this.builtinJavaApi.get("to");
    } 
    return this.builtinJavaApi;
  }
  
  public static boolean isBuiltInJavaExtend(ScriptFunction fn) {
    if (!"extend".equals(fn.getName()))
      return false; 
    return (fn == (Context.getGlobal()).builtInJavaExtend);
  }
  
  public static boolean isBuiltInJavaTo(ScriptFunction fn) {
    if (!"to".equals(fn.getName()))
      return false; 
    return (fn == (Context.getGlobal()).builtInJavaTo);
  }
  
  private synchronized ScriptFunction getBuiltinRangeError() {
    if (this.builtinRangeError == null)
      this.builtinRangeError = initErrorSubtype("RangeError", getErrorPrototype()); 
    return this.builtinRangeError;
  }
  
  private synchronized ScriptFunction getBuiltinRegExp() {
    if (this.builtinRegExp == null) {
      this.builtinRegExp = initConstructorAndSwitchPoint("RegExp", ScriptFunction.class);
      ScriptObject regExpProto = ScriptFunction.getPrototype(this.builtinRegExp);
      this.DEFAULT_REGEXP = new NativeRegExp("(?:)", "", this, regExpProto);
      regExpProto.addBoundProperties(this.DEFAULT_REGEXP);
    } 
    return this.builtinRegExp;
  }
  
  private synchronized ScriptFunction getBuiltinURIError() {
    if (this.builtinURIError == null)
      this.builtinURIError = initErrorSubtype("URIError", getErrorPrototype()); 
    return this.builtinURIError;
  }
  
  private synchronized ScriptFunction getBuiltinSymbol() {
    if (this.builtinSymbol == null)
      this.builtinSymbol = initConstructorAndSwitchPoint("Symbol", ScriptFunction.class); 
    return this.builtinSymbol;
  }
  
  private synchronized ScriptFunction getBuiltinMap() {
    if (this.builtinMap == null)
      this.builtinMap = initConstructorAndSwitchPoint("Map", ScriptFunction.class); 
    return this.builtinMap;
  }
  
  private synchronized ScriptFunction getBuiltinWeakMap() {
    if (this.builtinWeakMap == null)
      this.builtinWeakMap = initConstructorAndSwitchPoint("WeakMap", ScriptFunction.class); 
    return this.builtinWeakMap;
  }
  
  private synchronized ScriptFunction getBuiltinSet() {
    if (this.builtinSet == null)
      this.builtinSet = initConstructorAndSwitchPoint("Set", ScriptFunction.class); 
    return this.builtinSet;
  }
  
  private synchronized ScriptFunction getBuiltinWeakSet() {
    if (this.builtinWeakSet == null)
      this.builtinWeakSet = initConstructorAndSwitchPoint("WeakSet", ScriptFunction.class); 
    return this.builtinWeakSet;
  }
  
  public String getClassName() {
    return "global";
  }
  
  public static Object regExpCopy(Object regexp) {
    return new NativeRegExp((NativeRegExp)regexp);
  }
  
  public static NativeRegExp toRegExp(Object obj) {
    if (obj instanceof NativeRegExp)
      return (NativeRegExp)obj; 
    return new NativeRegExp(JSType.toString(obj));
  }
  
  public static Object toObject(Object obj) {
    if (obj == null || obj == ScriptRuntime.UNDEFINED)
      throw ECMAErrors.typeError("not.an.object", new String[] { ScriptRuntime.safeToString(obj) }); 
    if (obj instanceof ScriptObject)
      return obj; 
    return instance().wrapAsObject(obj);
  }
  
  public static NativeArray allocate(Object[] initial) {
    ArrayData arrayData = ArrayData.allocate(initial);
    for (int index = 0; index < initial.length; index++) {
      Object value = initial[index];
      if (value == ScriptRuntime.EMPTY)
        arrayData = arrayData.delete(index); 
    } 
    return new NativeArray(arrayData);
  }
  
  public static NativeArray allocate(double[] initial) {
    return new NativeArray(ArrayData.allocate(initial));
  }
  
  public static NativeArray allocate(int[] initial) {
    return new NativeArray(ArrayData.allocate(initial));
  }
  
  public static ScriptObject allocateArguments(Object[] arguments, Object callee, int numParams) {
    return NativeArguments.allocate(arguments, (ScriptFunction)callee, numParams);
  }
  
  public static boolean isEval(Object fn) {
    return (fn == (instance()).builtinEval);
  }
  
  public static Object replaceLocationPropertyPlaceholder(Object placeholder, Object locationProperty) {
    return isLocationPropertyPlaceholder(placeholder) ? locationProperty : placeholder;
  }
  
  public static boolean isLocationPropertyPlaceholder(Object placeholder) {
    return (placeholder == LOCATION_PLACEHOLDER);
  }
  
  public static Object newRegExp(String expression, String options) {
    if (options == null)
      return new NativeRegExp(expression); 
    return new NativeRegExp(expression, options);
  }
  
  public static ScriptObject objectPrototype() {
    return instance().getObjectPrototype();
  }
  
  public static ScriptObject newEmptyInstance() {
    return instance().newObject();
  }
  
  public static ScriptObject checkObject(Object obj) {
    if (!(obj instanceof ScriptObject))
      throw ECMAErrors.typeError("not.an.object", new String[] { ScriptRuntime.safeToString(obj) }); 
    return (ScriptObject)obj;
  }
  
  public static void checkObjectCoercible(Object obj) {
    if (obj == null || obj == ScriptRuntime.UNDEFINED)
      throw ECMAErrors.typeError("not.an.object", new String[] { ScriptRuntime.safeToString(obj) }); 
  }
  
  public boolean isES6() {
    return (this.context.getEnv())._es6;
  }
  
  public final ScriptObject getLexicalScope() {
    assert isES6();
    return this.lexicalScope;
  }
  
  public void addBoundProperties(ScriptObject source, Property[] properties) {
    PropertyMap ownMap = getMap();
    LexicalScope lexScope = null;
    PropertyMap lexicalMap = null;
    boolean hasLexicalDefinitions = false;
    if (isES6()) {
      lexScope = (LexicalScope)getLexicalScope();
      lexicalMap = lexScope.getMap();
      for (Property property : properties) {
        if (property.isLexicalBinding())
          hasLexicalDefinitions = true; 
        Property globalProperty = ownMap.findProperty(property.getKey());
        if (globalProperty != null && !globalProperty.isConfigurable() && property.isLexicalBinding())
          throw ECMAErrors.syntaxError("redeclare.variable", new String[] { property.getKey().toString() }); 
        Property lexicalProperty = lexicalMap.findProperty(property.getKey());
        if (lexicalProperty != null && !property.isConfigurable())
          throw ECMAErrors.syntaxError("redeclare.variable", new String[] { property.getKey().toString() }); 
      } 
    } 
    boolean extensible = isExtensible();
    for (Property property : properties) {
      if (property.isLexicalBinding()) {
        assert lexScope != null;
        lexicalMap = lexScope.addBoundProperty(lexicalMap, source, property, true);
        if (ownMap.findProperty(property.getKey()) != null)
          invalidateGlobalConstant(property.getKey()); 
      } else {
        ownMap = addBoundProperty(ownMap, source, property, extensible);
      } 
    } 
    setMap(ownMap);
    if (hasLexicalDefinitions) {
      assert lexScope != null;
      lexScope.setMap(lexicalMap);
      invalidateLexicalSwitchPoint();
    } 
  }
  
  public GuardedInvocation findGetMethod(CallSiteDescriptor desc, LinkRequest request) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    boolean isScope = NashornCallSiteDescriptor.isScope(desc);
    if (this.lexicalScope != null && isScope && !NashornCallSiteDescriptor.isApplyToCall(desc) && 
      this.lexicalScope.hasOwnProperty(name))
      return this.lexicalScope.findGetMethod(desc, request); 
    GuardedInvocation invocation = super.findGetMethod(desc, request);
    if (isScope && isES6() && (invocation.getSwitchPoints() == null || !hasOwnProperty(name)))
      return invocation.addSwitchPoint(getLexicalScopeSwitchPoint()); 
    return invocation;
  }
  
  protected FindProperty findProperty(Object key, boolean deep, boolean isScope, ScriptObject start) {
    if (this.lexicalScope != null && isScope) {
      FindProperty find = this.lexicalScope.findProperty(key, false);
      if (find != null)
        return find; 
    } 
    return super.findProperty(key, deep, isScope, start);
  }
  
  public GuardedInvocation findSetMethod(CallSiteDescriptor desc, LinkRequest request) {
    boolean isScope = NashornCallSiteDescriptor.isScope(desc);
    if (this.lexicalScope != null && isScope) {
      String name = NashornCallSiteDescriptor.getOperand(desc);
      if (this.lexicalScope.hasOwnProperty(name))
        return this.lexicalScope.findSetMethod(desc, request); 
    } 
    GuardedInvocation invocation = super.findSetMethod(desc, request);
    if (isScope && isES6())
      return invocation.addSwitchPoint(getLexicalScopeSwitchPoint()); 
    return invocation;
  }
  
  public void addShellBuiltins() {
    Object value = ScriptFunction.createBuiltin("input", ShellFunctions.INPUT);
    addOwnProperty("input", 2, value);
    value = ScriptFunction.createBuiltin("evalinput", ShellFunctions.EVALINPUT);
    addOwnProperty("evalinput", 2, value);
  }
  
  private synchronized SwitchPoint getLexicalScopeSwitchPoint() {
    SwitchPoint switchPoint = this.lexicalScopeSwitchPoint;
    if (switchPoint == null || switchPoint.hasBeenInvalidated())
      switchPoint = this.lexicalScopeSwitchPoint = new SwitchPoint(); 
    return switchPoint;
  }
  
  private synchronized void invalidateLexicalSwitchPoint() {
    if (this.lexicalScopeSwitchPoint != null) {
      this.context.getLogger(GlobalConstants.class).info("Invalidating non-constant globals on lexical scope update");
      SwitchPoint.invalidateAll(new SwitchPoint[] { this.lexicalScopeSwitchPoint });
    } 
  }
  
  private static Object lexicalScopeFilter(Object self) {
    if (self instanceof Global)
      return ((Global)self).getLexicalScope(); 
    return self;
  }
  
  private <T extends ScriptObject> T initConstructorAndSwitchPoint(String name, Class<T> clazz) {
    T func = initConstructor(name, clazz);
    tagBuiltinProperties(name, (ScriptObject)func);
    return func;
  }
  
  private void init(ScriptEngine eng) {
    assert Context.getGlobal() == this : "this global is not set as current";
    ScriptEnvironment env = getContext().getEnv();
    initFunctionAndObject();
    setInitialProto(getObjectPrototype());
    this.eval = this.builtinEval = ScriptFunction.createBuiltin("eval", EVAL);
    this.parseInt = ScriptFunction.createBuiltin("parseInt", GlobalFunctions.PARSEINT, new Specialization[] { new Specialization(GlobalFunctions.PARSEINT_Z), new Specialization(GlobalFunctions.PARSEINT_I), new Specialization(GlobalFunctions.PARSEINT_OI), new Specialization(GlobalFunctions.PARSEINT_O) });
    this.parseFloat = ScriptFunction.createBuiltin("parseFloat", GlobalFunctions.PARSEFLOAT);
    this.isNaN = ScriptFunction.createBuiltin("isNaN", GlobalFunctions.IS_NAN, new Specialization[] { new Specialization(GlobalFunctions.IS_NAN_I), new Specialization(GlobalFunctions.IS_NAN_J), new Specialization(GlobalFunctions.IS_NAN_D) });
    this.parseFloat = ScriptFunction.createBuiltin("parseFloat", GlobalFunctions.PARSEFLOAT);
    this.isNaN = ScriptFunction.createBuiltin("isNaN", GlobalFunctions.IS_NAN);
    this.isFinite = ScriptFunction.createBuiltin("isFinite", GlobalFunctions.IS_FINITE);
    this.encodeURI = ScriptFunction.createBuiltin("encodeURI", GlobalFunctions.ENCODE_URI);
    this.encodeURIComponent = ScriptFunction.createBuiltin("encodeURIComponent", GlobalFunctions.ENCODE_URICOMPONENT);
    this.decodeURI = ScriptFunction.createBuiltin("decodeURI", GlobalFunctions.DECODE_URI);
    this.decodeURIComponent = ScriptFunction.createBuiltin("decodeURIComponent", GlobalFunctions.DECODE_URICOMPONENT);
    this.escape = ScriptFunction.createBuiltin("escape", GlobalFunctions.ESCAPE);
    this.unescape = ScriptFunction.createBuiltin("unescape", GlobalFunctions.UNESCAPE);
    this.print = ScriptFunction.createBuiltin("print", env._print_no_newline ? PRINT : PRINTLN);
    this.load = ScriptFunction.createBuiltin("load", LOAD);
    this.loadWithNewGlobal = ScriptFunction.createBuiltin("loadWithNewGlobal", LOAD_WITH_NEW_GLOBAL);
    this.exit = ScriptFunction.createBuiltin("exit", EXIT);
    this.quit = ScriptFunction.createBuiltin("quit", EXIT);
    this.builtinArray = initConstructorAndSwitchPoint("Array", ScriptFunction.class);
    this.builtinBoolean = initConstructorAndSwitchPoint("Boolean", ScriptFunction.class);
    this.builtinNumber = initConstructorAndSwitchPoint("Number", ScriptFunction.class);
    this.builtinString = initConstructorAndSwitchPoint("String", ScriptFunction.class);
    this.builtinMath = initConstructorAndSwitchPoint("Math", ScriptObject.class);
    ScriptObject stringPrototype = getStringPrototype();
    stringPrototype.addOwnProperty("length", 7, Double.valueOf(0.0D));
    ScriptObject arrayPrototype = getArrayPrototype();
    arrayPrototype.setIsArray();
    if (env._es6) {
      this.symbol = LAZY_SENTINEL;
      this.map = LAZY_SENTINEL;
      this.weakMap = LAZY_SENTINEL;
      this.set = LAZY_SENTINEL;
      this.weakSet = LAZY_SENTINEL;
    } else {
      delete("Symbol", false);
      delete("Map", false);
      delete("WeakMap", false);
      delete("Set", false);
      delete("WeakSet", false);
      this.builtinObject.delete("getOwnPropertySymbols", false);
      arrayPrototype.delete("entries", false);
      arrayPrototype.delete("keys", false);
      arrayPrototype.delete("values", false);
    } 
    initErrorObjects();
    if (!env._no_java) {
      this.javaApi = LAZY_SENTINEL;
      this.javaImporter = LAZY_SENTINEL;
      initJavaAccess();
    } else {
      delete("Java", false);
      delete("JavaImporter", false);
      delete("Packages", false);
      delete("com", false);
      delete("edu", false);
      delete("java", false);
      delete("javafx", false);
      delete("javax", false);
      delete("org", false);
    } 
    if (!env._no_typed_arrays) {
      this.arrayBuffer = LAZY_SENTINEL;
      this.dataView = LAZY_SENTINEL;
      this.int8Array = LAZY_SENTINEL;
      this.uint8Array = LAZY_SENTINEL;
      this.uint8ClampedArray = LAZY_SENTINEL;
      this.int16Array = LAZY_SENTINEL;
      this.uint16Array = LAZY_SENTINEL;
      this.int32Array = LAZY_SENTINEL;
      this.uint32Array = LAZY_SENTINEL;
      this.float32Array = LAZY_SENTINEL;
      this.float64Array = LAZY_SENTINEL;
    } 
    if (env._scripting)
      initScripting(env); 
    if (Context.DEBUG) {
      boolean debugOkay;
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        try {
          sm.checkPermission(new RuntimePermission("nashorn.debugMode"));
          debugOkay = true;
        } catch (SecurityException ignored) {
          debugOkay = false;
        } 
      } else {
        debugOkay = true;
      } 
      if (debugOkay)
        initDebug(); 
    } 
    copyBuiltins();
    this.arguments = wrapAsObject(env.getArguments().toArray());
    if (env._scripting)
      addOwnProperty("$ARG", 2, this.arguments); 
    if (eng != null) {
      addOwnProperty("javax.script.filename", 2, null);
      ScriptFunction noSuchProp = ScriptFunction.createStrictBuiltin("__noSuchProperty__", NO_SUCH_PROPERTY);
      addOwnProperty("__noSuchProperty__", 2, noSuchProp);
    } 
  }
  
  private void initErrorObjects() {
    this.builtinError = initConstructor("Error", ScriptFunction.class);
    ScriptObject errorProto = getErrorPrototype();
    ScriptFunction getStack = ScriptFunction.createBuiltin("getStack", NativeError.GET_STACK);
    ScriptFunction setStack = ScriptFunction.createBuiltin("setStack", NativeError.SET_STACK);
    errorProto.addOwnProperty("stack", 2, getStack, setStack);
    ScriptFunction getLineNumber = ScriptFunction.createBuiltin("getLineNumber", NativeError.GET_LINENUMBER);
    ScriptFunction setLineNumber = ScriptFunction.createBuiltin("setLineNumber", NativeError.SET_LINENUMBER);
    errorProto.addOwnProperty("lineNumber", 2, getLineNumber, setLineNumber);
    ScriptFunction getColumnNumber = ScriptFunction.createBuiltin("getColumnNumber", NativeError.GET_COLUMNNUMBER);
    ScriptFunction setColumnNumber = ScriptFunction.createBuiltin("setColumnNumber", NativeError.SET_COLUMNNUMBER);
    errorProto.addOwnProperty("columnNumber", 2, getColumnNumber, setColumnNumber);
    ScriptFunction getFileName = ScriptFunction.createBuiltin("getFileName", NativeError.GET_FILENAME);
    ScriptFunction setFileName = ScriptFunction.createBuiltin("setFileName", NativeError.SET_FILENAME);
    errorProto.addOwnProperty("fileName", 2, getFileName, setFileName);
    errorProto.set("name", "Error", 0);
    errorProto.set("message", "", 0);
    tagBuiltinProperties("Error", (ScriptObject)this.builtinError);
    this.builtinReferenceError = initErrorSubtype("ReferenceError", errorProto);
    this.builtinSyntaxError = initErrorSubtype("SyntaxError", errorProto);
    this.builtinTypeError = initErrorSubtype("TypeError", errorProto);
  }
  
  private ScriptFunction initErrorSubtype(String name, ScriptObject errorProto) {
    ScriptFunction cons = initConstructor(name, ScriptFunction.class);
    ScriptObject prototype = ScriptFunction.getPrototype(cons);
    prototype.set("name", name, 0);
    prototype.set("message", "", 0);
    prototype.setInitialProto(errorProto);
    tagBuiltinProperties(name, (ScriptObject)cons);
    return cons;
  }
  
  private void initJavaAccess() {
    ScriptObject objectProto = getObjectPrototype();
    this.builtinPackages = (ScriptObject)new NativeJavaPackage("", objectProto);
    this.builtinCom = (ScriptObject)new NativeJavaPackage("com", objectProto);
    this.builtinEdu = (ScriptObject)new NativeJavaPackage("edu", objectProto);
    this.builtinJava = (ScriptObject)new NativeJavaPackage("java", objectProto);
    this.builtinJavafx = (ScriptObject)new NativeJavaPackage("javafx", objectProto);
    this.builtinJavax = (ScriptObject)new NativeJavaPackage("javax", objectProto);
    this.builtinOrg = (ScriptObject)new NativeJavaPackage("org", objectProto);
  }
  
  private void initScripting(ScriptEnvironment scriptEnv) {
    ScriptFunction scriptFunction = ScriptFunction.createBuiltin("readLine", ScriptingFunctions.READLINE);
    addOwnProperty("readLine", 2, scriptFunction);
    scriptFunction = ScriptFunction.createBuiltin("readFully", ScriptingFunctions.READFULLY);
    addOwnProperty("readFully", 2, scriptFunction);
    String execName = "$EXEC";
    scriptFunction = ScriptFunction.createBuiltin("$EXEC", ScriptingFunctions.EXEC);
    addOwnProperty("$EXEC", 2, scriptFunction);
    ScriptObject scriptObject1 = (ScriptObject)get("print");
    addOwnProperty("echo", 2, scriptObject1);
    ScriptObject options = newObject();
    copyOptions(options, scriptEnv);
    addOwnProperty("$OPTIONS", 2, options);
    ScriptObject env = newObject();
    if (System.getSecurityManager() == null) {
      env.putAll(System.getenv(), scriptEnv._strict);
      env.put("PWD", System.getProperty("user.dir"), scriptEnv._strict);
    } 
    addOwnProperty("$ENV", 2, env);
    addOwnProperty("$OUT", 2, ScriptRuntime.UNDEFINED);
    addOwnProperty("$ERR", 2, ScriptRuntime.UNDEFINED);
    addOwnProperty("$EXIT", 2, ScriptRuntime.UNDEFINED);
  }
  
  private static void copyOptions(ScriptObject options, ScriptEnvironment scriptEnv) {
    for (Field f : scriptEnv.getClass().getFields()) {
      try {
        options.set(f.getName(), f.get(scriptEnv), 0);
      } catch (IllegalArgumentException|IllegalAccessException exp) {
        throw new RuntimeException(exp);
      } 
    } 
  }
  
  private void copyBuiltins() {
    this.array = this.builtinArray;
    this._boolean = this.builtinBoolean;
    this.error = this.builtinError;
    this.function = this.builtinFunction;
    this.com = this.builtinCom;
    this.edu = this.builtinEdu;
    this.java = this.builtinJava;
    this.javafx = this.builtinJavafx;
    this.javax = this.builtinJavax;
    this.org = this.builtinOrg;
    this.math = this.builtinMath;
    this.number = this.builtinNumber;
    this.object = this.builtinObject;
    this.packages = this.builtinPackages;
    this.referenceError = this.builtinReferenceError;
    this.string = this.builtinString;
    this.syntaxError = this.builtinSyntaxError;
    this.typeError = this.builtinTypeError;
  }
  
  private void initDebug() {
    addOwnProperty("Debug", 2, initConstructor("Debug", ScriptObject.class));
  }
  
  private Object printImpl(boolean newLine, Object... objects) {
    ScriptContext sc = currentContext();
    PrintWriter out = (sc != null) ? new PrintWriter(sc.getWriter()) : getContext().getEnv().getOut();
    StringBuilder sb = new StringBuilder();
    for (Object obj : objects) {
      if (sb.length() != 0)
        sb.append(' '); 
      sb.append(JSType.toString(obj));
    } 
    if (newLine) {
      out.println(sb.toString());
    } else {
      out.print(sb.toString());
    } 
    out.flush();
    return ScriptRuntime.UNDEFINED;
  }
  
  private <T extends ScriptObject> T initConstructor(String name, Class<T> clazz) {
    try {
      StringBuilder sb = new StringBuilder("org.openjdk.nashorn.internal.objects.");
      sb.append("Native");
      sb.append(name);
      sb.append("$Constructor");
      Class<?> funcClass = Class.forName(sb.toString());
      ScriptObject scriptObject = (ScriptObject)clazz.cast(funcClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
      if (scriptObject instanceof ScriptFunction) {
        ScriptFunction func = (ScriptFunction)scriptObject;
        func.modifyOwnProperty(func.getProperty("prototype"), 7);
      } 
      if (scriptObject.getProto() == null)
        scriptObject.setInitialProto(getObjectPrototype()); 
      scriptObject.setIsBuiltin();
      return (T)scriptObject;
    } catch (Exception e) {
      if (e instanceof RuntimeException)
        throw (RuntimeException)e; 
      throw new RuntimeException(e);
    } 
  }
  
  private ScriptObject initPrototype(String name, ScriptObject prototype) {
    try {
      String className = "org.openjdk.nashorn.internal.objects." + name + "$Prototype";
      Class<?> funcClass = Class.forName(className);
      ScriptObject res = funcClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      res.setIsBuiltin();
      res.setInitialProto(prototype);
      return res;
    } catch (Exception e) {
      if (e instanceof RuntimeException)
        throw (RuntimeException)e; 
      throw new RuntimeException(e);
    } 
  }
  
  private List<Property> extractBuiltinProperties(String name, ScriptObject func) {
    List<Property> list = new ArrayList<>();
    list.addAll(Arrays.asList(func.getMap().getProperties()));
    if (func instanceof ScriptFunction) {
      ScriptObject proto = ScriptFunction.getPrototype((ScriptFunction)func);
      if (proto != null)
        list.addAll(Arrays.asList(proto.getMap().getProperties())); 
    } 
    Property prop = getProperty(name);
    if (prop != null)
      list.add(prop); 
    return list;
  }
  
  private void tagBuiltinProperties(String name, ScriptObject func) {
    SwitchPoint sp = this.context.getBuiltinSwitchPoint(name);
    if (sp == null)
      sp = this.context.newBuiltinSwitchPoint(name); 
    for (Property prop : extractBuiltinProperties(name, func))
      prop.setBuiltinSwitchPoint(sp); 
  }
  
  private void initFunctionAndObject() {
    this.builtinFunction = initConstructor("Function", ScriptFunction.class);
    ScriptFunction anon = ScriptFunction.createAnonymous();
    anon.addBoundProperties(getFunctionPrototype());
    this.builtinFunction.setInitialProto((ScriptObject)anon);
    this.builtinFunction.setPrototype(anon);
    anon.set("constructor", this.builtinFunction, 0);
    anon.deleteOwnProperty(anon.getMap().findProperty("prototype"));
    this.typeErrorThrower = ScriptFunction.createBuiltin("TypeErrorThrower", Lookup.TYPE_ERROR_THROWER);
    this.typeErrorThrower.preventExtensions();
    this.builtinObject = initConstructor("Object", ScriptFunction.class);
    ScriptObject ObjectPrototype = getObjectPrototype();
    anon.setInitialProto(ObjectPrototype);
    ScriptFunction getProto = ScriptFunction.createBuiltin("getProto", NativeObject.GET__PROTO__);
    ScriptFunction setProto = ScriptFunction.createBuiltin("setProto", NativeObject.SET__PROTO__);
    ObjectPrototype.addOwnProperty("__proto__", 2, getProto, setProto);
    Property[] properties = getFunctionPrototype().getMap().getProperties();
    for (Property property : properties) {
      Object key = property.getKey();
      Object value = this.builtinFunction.get(key);
      if (value instanceof ScriptFunction && value != anon) {
        ScriptFunction func = (ScriptFunction)value;
        func.setInitialProto(getFunctionPrototype());
        ScriptObject prototype = ScriptFunction.getPrototype(func);
        if (prototype != null)
          prototype.setInitialProto(ObjectPrototype); 
      } 
    } 
    for (Property property : this.builtinObject.getMap().getProperties()) {
      Object key = property.getKey();
      Object value = this.builtinObject.get(key);
      if (value instanceof ScriptFunction) {
        ScriptFunction func = (ScriptFunction)value;
        ScriptObject prototype = ScriptFunction.getPrototype(func);
        if (prototype != null)
          prototype.setInitialProto(ObjectPrototype); 
      } 
    } 
    properties = getObjectPrototype().getMap().getProperties();
    for (Property property : properties) {
      Object key = property.getKey();
      if (!key.equals("constructor")) {
        Object value = ObjectPrototype.get(key);
        if (value instanceof ScriptFunction) {
          ScriptFunction func = (ScriptFunction)value;
          ScriptObject prototype = ScriptFunction.getPrototype(func);
          if (prototype != null)
            prototype.setInitialProto(ObjectPrototype); 
        } 
      } 
    } 
    tagBuiltinProperties("Object", (ScriptObject)this.builtinObject);
    tagBuiltinProperties("Function", (ScriptObject)this.builtinFunction);
    tagBuiltinProperties("Function", (ScriptObject)anon);
  }
  
  private static MethodHandle findOwnMH_S(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), Global.class, name, Lookup.MH.type(rtype, types));
  }
  
  RegExpResult getLastRegExpResult() {
    return this.lastRegExpResult;
  }
  
  void setLastRegExpResult(RegExpResult regExpResult) {
    this.lastRegExpResult = regExpResult;
  }
  
  protected boolean isGlobal() {
    return true;
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: bipush #66
    //   6: invokespecial <init> : (I)V
    //   9: dup
    //   10: ldc_w 'arguments'
    //   13: bipush #6
    //   15: ldc_w
    //   18: ldc_w
    //   21: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   24: invokeinterface add : (Ljava/lang/Object;)Z
    //   29: pop
    //   30: dup
    //   31: ldc_w 'parseInt'
    //   34: iconst_2
    //   35: ldc_w
    //   38: ldc_w
    //   41: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   44: invokeinterface add : (Ljava/lang/Object;)Z
    //   49: pop
    //   50: dup
    //   51: ldc_w 'parseFloat'
    //   54: iconst_2
    //   55: ldc_w
    //   58: ldc_w
    //   61: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   64: invokeinterface add : (Ljava/lang/Object;)Z
    //   69: pop
    //   70: dup
    //   71: ldc_w 'isNaN'
    //   74: iconst_2
    //   75: ldc_w
    //   78: ldc_w
    //   81: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   84: invokeinterface add : (Ljava/lang/Object;)Z
    //   89: pop
    //   90: dup
    //   91: ldc_w 'isFinite'
    //   94: iconst_2
    //   95: ldc_w
    //   98: ldc_w
    //   101: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   104: invokeinterface add : (Ljava/lang/Object;)Z
    //   109: pop
    //   110: dup
    //   111: ldc_w 'encodeURI'
    //   114: iconst_2
    //   115: ldc_w
    //   118: ldc_w
    //   121: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   124: invokeinterface add : (Ljava/lang/Object;)Z
    //   129: pop
    //   130: dup
    //   131: ldc_w 'encodeURIComponent'
    //   134: iconst_2
    //   135: ldc_w
    //   138: ldc_w
    //   141: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   144: invokeinterface add : (Ljava/lang/Object;)Z
    //   149: pop
    //   150: dup
    //   151: ldc_w 'decodeURI'
    //   154: iconst_2
    //   155: ldc_w
    //   158: ldc_w
    //   161: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   164: invokeinterface add : (Ljava/lang/Object;)Z
    //   169: pop
    //   170: dup
    //   171: ldc_w 'decodeURIComponent'
    //   174: iconst_2
    //   175: ldc_w
    //   178: ldc_w
    //   181: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   184: invokeinterface add : (Ljava/lang/Object;)Z
    //   189: pop
    //   190: dup
    //   191: ldc_w 'escape'
    //   194: iconst_2
    //   195: ldc_w
    //   198: ldc_w
    //   201: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   204: invokeinterface add : (Ljava/lang/Object;)Z
    //   209: pop
    //   210: dup
    //   211: ldc_w 'unescape'
    //   214: iconst_2
    //   215: ldc_w
    //   218: ldc_w
    //   221: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   224: invokeinterface add : (Ljava/lang/Object;)Z
    //   229: pop
    //   230: dup
    //   231: ldc_w 'print'
    //   234: iconst_2
    //   235: ldc_w
    //   238: ldc_w
    //   241: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   244: invokeinterface add : (Ljava/lang/Object;)Z
    //   249: pop
    //   250: dup
    //   251: ldc_w 'load'
    //   254: iconst_2
    //   255: ldc_w
    //   258: ldc_w
    //   261: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   264: invokeinterface add : (Ljava/lang/Object;)Z
    //   269: pop
    //   270: dup
    //   271: ldc_w 'loadWithNewGlobal'
    //   274: iconst_2
    //   275: ldc_w
    //   278: ldc_w
    //   281: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   284: invokeinterface add : (Ljava/lang/Object;)Z
    //   289: pop
    //   290: dup
    //   291: ldc_w 'exit'
    //   294: iconst_2
    //   295: ldc_w
    //   298: ldc_w
    //   301: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   304: invokeinterface add : (Ljava/lang/Object;)Z
    //   309: pop
    //   310: dup
    //   311: ldc_w 'quit'
    //   314: iconst_2
    //   315: ldc_w
    //   318: ldc_w
    //   321: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   324: invokeinterface add : (Ljava/lang/Object;)Z
    //   329: pop
    //   330: dup
    //   331: ldc_w 'NaN'
    //   334: bipush #7
    //   336: ldc_w
    //   339: aconst_null
    //   340: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   343: invokeinterface add : (Ljava/lang/Object;)Z
    //   348: pop
    //   349: dup
    //   350: ldc_w 'Infinity'
    //   353: bipush #7
    //   355: ldc_w
    //   358: aconst_null
    //   359: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   362: invokeinterface add : (Ljava/lang/Object;)Z
    //   367: pop
    //   368: dup
    //   369: ldc_w 'undefined'
    //   372: bipush #7
    //   374: ldc_w
    //   377: aconst_null
    //   378: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   381: invokeinterface add : (Ljava/lang/Object;)Z
    //   386: pop
    //   387: dup
    //   388: ldc_w 'eval'
    //   391: iconst_2
    //   392: ldc_w
    //   395: ldc_w
    //   398: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   401: invokeinterface add : (Ljava/lang/Object;)Z
    //   406: pop
    //   407: dup
    //   408: ldc_w 'Object'
    //   411: iconst_2
    //   412: ldc_w
    //   415: ldc_w
    //   418: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   421: invokeinterface add : (Ljava/lang/Object;)Z
    //   426: pop
    //   427: dup
    //   428: ldc_w 'Function'
    //   431: iconst_2
    //   432: ldc_w
    //   435: ldc_w
    //   438: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   441: invokeinterface add : (Ljava/lang/Object;)Z
    //   446: pop
    //   447: dup
    //   448: ldc_w 'Array'
    //   451: iconst_2
    //   452: ldc_w
    //   455: ldc_w
    //   458: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   461: invokeinterface add : (Ljava/lang/Object;)Z
    //   466: pop
    //   467: dup
    //   468: ldc_w 'String'
    //   471: iconst_2
    //   472: ldc_w
    //   475: ldc_w
    //   478: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   481: invokeinterface add : (Ljava/lang/Object;)Z
    //   486: pop
    //   487: dup
    //   488: ldc_w 'Boolean'
    //   491: iconst_2
    //   492: ldc_w
    //   495: ldc_w
    //   498: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   501: invokeinterface add : (Ljava/lang/Object;)Z
    //   506: pop
    //   507: dup
    //   508: ldc_w 'Number'
    //   511: iconst_2
    //   512: ldc_w
    //   515: ldc_w
    //   518: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   521: invokeinterface add : (Ljava/lang/Object;)Z
    //   526: pop
    //   527: dup
    //   528: ldc_w 'Math'
    //   531: iconst_2
    //   532: ldc_w
    //   535: ldc_w
    //   538: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   541: invokeinterface add : (Ljava/lang/Object;)Z
    //   546: pop
    //   547: dup
    //   548: ldc_w 'Error'
    //   551: iconst_2
    //   552: ldc_w
    //   555: ldc_w
    //   558: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   561: invokeinterface add : (Ljava/lang/Object;)Z
    //   566: pop
    //   567: dup
    //   568: ldc_w 'ReferenceError'
    //   571: iconst_2
    //   572: ldc_w
    //   575: ldc_w
    //   578: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   581: invokeinterface add : (Ljava/lang/Object;)Z
    //   586: pop
    //   587: dup
    //   588: ldc_w 'SyntaxError'
    //   591: iconst_2
    //   592: ldc_w
    //   595: ldc_w
    //   598: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   601: invokeinterface add : (Ljava/lang/Object;)Z
    //   606: pop
    //   607: dup
    //   608: ldc_w 'TypeError'
    //   611: iconst_2
    //   612: ldc_w
    //   615: ldc_w
    //   618: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   621: invokeinterface add : (Ljava/lang/Object;)Z
    //   626: pop
    //   627: dup
    //   628: ldc_w 'Packages'
    //   631: iconst_2
    //   632: ldc_w
    //   635: ldc_w
    //   638: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   641: invokeinterface add : (Ljava/lang/Object;)Z
    //   646: pop
    //   647: dup
    //   648: ldc_w 'com'
    //   651: iconst_2
    //   652: ldc_w
    //   655: ldc_w
    //   658: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   661: invokeinterface add : (Ljava/lang/Object;)Z
    //   666: pop
    //   667: dup
    //   668: ldc_w 'edu'
    //   671: iconst_2
    //   672: ldc_w
    //   675: ldc_w
    //   678: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   681: invokeinterface add : (Ljava/lang/Object;)Z
    //   686: pop
    //   687: dup
    //   688: ldc_w 'java'
    //   691: iconst_2
    //   692: ldc_w
    //   695: ldc_w
    //   698: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   701: invokeinterface add : (Ljava/lang/Object;)Z
    //   706: pop
    //   707: dup
    //   708: ldc_w 'javafx'
    //   711: iconst_2
    //   712: ldc_w
    //   715: ldc_w
    //   718: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   721: invokeinterface add : (Ljava/lang/Object;)Z
    //   726: pop
    //   727: dup
    //   728: ldc_w 'javax'
    //   731: iconst_2
    //   732: ldc_w
    //   735: ldc_w
    //   738: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   741: invokeinterface add : (Ljava/lang/Object;)Z
    //   746: pop
    //   747: dup
    //   748: ldc_w 'org'
    //   751: iconst_2
    //   752: ldc_w
    //   755: ldc_w
    //   758: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   761: invokeinterface add : (Ljava/lang/Object;)Z
    //   766: pop
    //   767: dup
    //   768: ldc_w '__FILE__'
    //   771: bipush #7
    //   773: ldc_w
    //   776: aconst_null
    //   777: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   780: invokeinterface add : (Ljava/lang/Object;)Z
    //   785: pop
    //   786: dup
    //   787: ldc_w '__DIR__'
    //   790: bipush #7
    //   792: ldc_w
    //   795: aconst_null
    //   796: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   799: invokeinterface add : (Ljava/lang/Object;)Z
    //   804: pop
    //   805: dup
    //   806: ldc_w '__LINE__'
    //   809: bipush #7
    //   811: ldc_w
    //   814: aconst_null
    //   815: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   818: invokeinterface add : (Ljava/lang/Object;)Z
    //   823: pop
    //   824: dup
    //   825: ldc_w 'Date'
    //   828: iconst_2
    //   829: ldc_w
    //   832: ldc_w
    //   835: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   838: invokeinterface add : (Ljava/lang/Object;)Z
    //   843: pop
    //   844: dup
    //   845: ldc_w 'RegExp'
    //   848: iconst_2
    //   849: ldc_w
    //   852: ldc_w
    //   855: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   858: invokeinterface add : (Ljava/lang/Object;)Z
    //   863: pop
    //   864: dup
    //   865: ldc_w 'JSON'
    //   868: iconst_2
    //   869: ldc_w
    //   872: ldc_w
    //   875: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   878: invokeinterface add : (Ljava/lang/Object;)Z
    //   883: pop
    //   884: dup
    //   885: ldc_w 'JSAdapter'
    //   888: iconst_2
    //   889: ldc_w
    //   892: ldc_w
    //   895: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   898: invokeinterface add : (Ljava/lang/Object;)Z
    //   903: pop
    //   904: dup
    //   905: ldc_w 'EvalError'
    //   908: iconst_2
    //   909: ldc_w
    //   912: ldc_w
    //   915: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   918: invokeinterface add : (Ljava/lang/Object;)Z
    //   923: pop
    //   924: dup
    //   925: ldc_w 'RangeError'
    //   928: iconst_2
    //   929: ldc_w
    //   932: ldc_w
    //   935: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   938: invokeinterface add : (Ljava/lang/Object;)Z
    //   943: pop
    //   944: dup
    //   945: ldc_w 'URIError'
    //   948: iconst_2
    //   949: ldc_w
    //   952: ldc_w
    //   955: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   958: invokeinterface add : (Ljava/lang/Object;)Z
    //   963: pop
    //   964: dup
    //   965: ldc_w 'ArrayBuffer'
    //   968: iconst_2
    //   969: ldc_w
    //   972: ldc_w
    //   975: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   978: invokeinterface add : (Ljava/lang/Object;)Z
    //   983: pop
    //   984: dup
    //   985: ldc_w 'DataView'
    //   988: iconst_2
    //   989: ldc_w
    //   992: ldc_w
    //   995: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   998: invokeinterface add : (Ljava/lang/Object;)Z
    //   1003: pop
    //   1004: dup
    //   1005: ldc_w 'Int8Array'
    //   1008: iconst_2
    //   1009: ldc_w
    //   1012: ldc_w
    //   1015: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1018: invokeinterface add : (Ljava/lang/Object;)Z
    //   1023: pop
    //   1024: dup
    //   1025: ldc_w 'Uint8Array'
    //   1028: iconst_2
    //   1029: ldc_w
    //   1032: ldc_w
    //   1035: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1038: invokeinterface add : (Ljava/lang/Object;)Z
    //   1043: pop
    //   1044: dup
    //   1045: ldc_w 'Uint8ClampedArray'
    //   1048: iconst_2
    //   1049: ldc_w
    //   1052: ldc_w
    //   1055: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1058: invokeinterface add : (Ljava/lang/Object;)Z
    //   1063: pop
    //   1064: dup
    //   1065: ldc_w 'Int16Array'
    //   1068: iconst_2
    //   1069: ldc_w
    //   1072: ldc_w
    //   1075: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1078: invokeinterface add : (Ljava/lang/Object;)Z
    //   1083: pop
    //   1084: dup
    //   1085: ldc_w 'Uint16Array'
    //   1088: iconst_2
    //   1089: ldc_w
    //   1092: ldc_w
    //   1095: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1098: invokeinterface add : (Ljava/lang/Object;)Z
    //   1103: pop
    //   1104: dup
    //   1105: ldc_w 'Int32Array'
    //   1108: iconst_2
    //   1109: ldc_w
    //   1112: ldc_w
    //   1115: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1118: invokeinterface add : (Ljava/lang/Object;)Z
    //   1123: pop
    //   1124: dup
    //   1125: ldc_w 'Uint32Array'
    //   1128: iconst_2
    //   1129: ldc_w
    //   1132: ldc_w
    //   1135: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1138: invokeinterface add : (Ljava/lang/Object;)Z
    //   1143: pop
    //   1144: dup
    //   1145: ldc_w 'Float32Array'
    //   1148: iconst_2
    //   1149: ldc_w
    //   1152: ldc_w
    //   1155: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1158: invokeinterface add : (Ljava/lang/Object;)Z
    //   1163: pop
    //   1164: dup
    //   1165: ldc_w 'Float64Array'
    //   1168: iconst_2
    //   1169: ldc_w
    //   1172: ldc_w
    //   1175: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1178: invokeinterface add : (Ljava/lang/Object;)Z
    //   1183: pop
    //   1184: dup
    //   1185: ldc_w 'Symbol'
    //   1188: iconst_2
    //   1189: ldc_w
    //   1192: ldc_w
    //   1195: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1198: invokeinterface add : (Ljava/lang/Object;)Z
    //   1203: pop
    //   1204: dup
    //   1205: ldc_w 'Map'
    //   1208: iconst_2
    //   1209: ldc_w
    //   1212: ldc_w
    //   1215: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1218: invokeinterface add : (Ljava/lang/Object;)Z
    //   1223: pop
    //   1224: dup
    //   1225: ldc_w 'WeakMap'
    //   1228: iconst_2
    //   1229: ldc_w
    //   1232: ldc_w
    //   1235: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1238: invokeinterface add : (Ljava/lang/Object;)Z
    //   1243: pop
    //   1244: dup
    //   1245: ldc_w 'Set'
    //   1248: iconst_2
    //   1249: ldc_w
    //   1252: ldc_w
    //   1255: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1258: invokeinterface add : (Ljava/lang/Object;)Z
    //   1263: pop
    //   1264: dup
    //   1265: ldc_w 'WeakSet'
    //   1268: iconst_2
    //   1269: ldc_w
    //   1272: ldc_w
    //   1275: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1278: invokeinterface add : (Ljava/lang/Object;)Z
    //   1283: pop
    //   1284: dup
    //   1285: ldc_w 'JavaImporter'
    //   1288: iconst_2
    //   1289: ldc_w
    //   1292: ldc_w
    //   1295: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1298: invokeinterface add : (Ljava/lang/Object;)Z
    //   1303: pop
    //   1304: dup
    //   1305: ldc_w 'Java'
    //   1308: iconst_2
    //   1309: ldc_w
    //   1312: ldc_w
    //   1315: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   1318: invokeinterface add : (Ljava/lang/Object;)Z
    //   1323: pop
    //   1324: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   1327: putstatic org/openjdk/nashorn/internal/objects/Global.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   1330: return
  }
  
  public Object G$arguments() {
    return this.arguments;
  }
  
  public void S$arguments(Object paramObject) {
    this.arguments = paramObject;
  }
  
  public Object G$parseInt() {
    return this.parseInt;
  }
  
  public void S$parseInt(Object paramObject) {
    this.parseInt = paramObject;
  }
  
  public Object G$parseFloat() {
    return this.parseFloat;
  }
  
  public void S$parseFloat(Object paramObject) {
    this.parseFloat = paramObject;
  }
  
  public Object G$isNaN() {
    return this.isNaN;
  }
  
  public void S$isNaN(Object paramObject) {
    this.isNaN = paramObject;
  }
  
  public Object G$isFinite() {
    return this.isFinite;
  }
  
  public void S$isFinite(Object paramObject) {
    this.isFinite = paramObject;
  }
  
  public Object G$encodeURI() {
    return this.encodeURI;
  }
  
  public void S$encodeURI(Object paramObject) {
    this.encodeURI = paramObject;
  }
  
  public Object G$encodeURIComponent() {
    return this.encodeURIComponent;
  }
  
  public void S$encodeURIComponent(Object paramObject) {
    this.encodeURIComponent = paramObject;
  }
  
  public Object G$decodeURI() {
    return this.decodeURI;
  }
  
  public void S$decodeURI(Object paramObject) {
    this.decodeURI = paramObject;
  }
  
  public Object G$decodeURIComponent() {
    return this.decodeURIComponent;
  }
  
  public void S$decodeURIComponent(Object paramObject) {
    this.decodeURIComponent = paramObject;
  }
  
  public Object G$escape() {
    return this.escape;
  }
  
  public void S$escape(Object paramObject) {
    this.escape = paramObject;
  }
  
  public Object G$unescape() {
    return this.unescape;
  }
  
  public void S$unescape(Object paramObject) {
    this.unescape = paramObject;
  }
  
  public Object G$print() {
    return this.print;
  }
  
  public void S$print(Object paramObject) {
    this.print = paramObject;
  }
  
  public Object G$load() {
    return this.load;
  }
  
  public void S$load(Object paramObject) {
    this.load = paramObject;
  }
  
  public Object G$loadWithNewGlobal() {
    return this.loadWithNewGlobal;
  }
  
  public void S$loadWithNewGlobal(Object paramObject) {
    this.loadWithNewGlobal = paramObject;
  }
  
  public Object G$exit() {
    return this.exit;
  }
  
  public void S$exit(Object paramObject) {
    this.exit = paramObject;
  }
  
  public Object G$quit() {
    return this.quit;
  }
  
  public void S$quit(Object paramObject) {
    this.quit = paramObject;
  }
  
  public double G$NaN() {
    return NaN;
  }
  
  public double G$Infinity() {
    return Infinity;
  }
  
  public Object G$undefined() {
    return undefined;
  }
  
  public Object G$eval() {
    return this.eval;
  }
  
  public void S$eval(Object paramObject) {
    this.eval = paramObject;
  }
  
  public Object G$object() {
    return this.object;
  }
  
  public void S$object(Object paramObject) {
    this.object = paramObject;
  }
  
  public Object G$function() {
    return this.function;
  }
  
  public void S$function(Object paramObject) {
    this.function = paramObject;
  }
  
  public Object G$array() {
    return this.array;
  }
  
  public void S$array(Object paramObject) {
    this.array = paramObject;
  }
  
  public Object G$string() {
    return this.string;
  }
  
  public void S$string(Object paramObject) {
    this.string = paramObject;
  }
  
  public Object G$_boolean() {
    return this._boolean;
  }
  
  public void S$_boolean(Object paramObject) {
    this._boolean = paramObject;
  }
  
  public Object G$number() {
    return this.number;
  }
  
  public void S$number(Object paramObject) {
    this.number = paramObject;
  }
  
  public Object G$math() {
    return this.math;
  }
  
  public void S$math(Object paramObject) {
    this.math = paramObject;
  }
  
  public Object G$error() {
    return this.error;
  }
  
  public void S$error(Object paramObject) {
    this.error = paramObject;
  }
  
  public Object G$referenceError() {
    return this.referenceError;
  }
  
  public void S$referenceError(Object paramObject) {
    this.referenceError = paramObject;
  }
  
  public Object G$syntaxError() {
    return this.syntaxError;
  }
  
  public void S$syntaxError(Object paramObject) {
    this.syntaxError = paramObject;
  }
  
  public Object G$typeError() {
    return this.typeError;
  }
  
  public void S$typeError(Object paramObject) {
    this.typeError = paramObject;
  }
  
  public Object G$packages() {
    return this.packages;
  }
  
  public void S$packages(Object paramObject) {
    this.packages = paramObject;
  }
  
  public Object G$com() {
    return this.com;
  }
  
  public void S$com(Object paramObject) {
    this.com = paramObject;
  }
  
  public Object G$edu() {
    return this.edu;
  }
  
  public void S$edu(Object paramObject) {
    this.edu = paramObject;
  }
  
  public Object G$java() {
    return this.java;
  }
  
  public void S$java(Object paramObject) {
    this.java = paramObject;
  }
  
  public Object G$javafx() {
    return this.javafx;
  }
  
  public void S$javafx(Object paramObject) {
    this.javafx = paramObject;
  }
  
  public Object G$javax() {
    return this.javax;
  }
  
  public void S$javax(Object paramObject) {
    this.javax = paramObject;
  }
  
  public Object G$org() {
    return this.org;
  }
  
  public void S$org(Object paramObject) {
    this.org = paramObject;
  }
  
  public Object G$__FILE__() {
    return __FILE__;
  }
  
  public Object G$__DIR__() {
    return __DIR__;
  }
  
  public Object G$__LINE__() {
    return __LINE__;
  }
  
  private static class LexicalScope extends ScriptObject {
    LexicalScope(Global global) {
      super((ScriptObject)global, PropertyMap.newMap());
      setIsInternal();
    }
    
    protected GuardedInvocation findGetMethod(CallSiteDescriptor desc, LinkRequest request) {
      return filterInvocation(super.findGetMethod(desc, request));
    }
    
    protected GuardedInvocation findSetMethod(CallSiteDescriptor desc, LinkRequest request) {
      return filterInvocation(super.findSetMethod(desc, request));
    }
    
    protected PropertyMap addBoundProperty(PropertyMap propMap, ScriptObject source, Property property, boolean extensible) {
      return super.addBoundProperty(propMap, source, property, extensible);
    }
    
    private static GuardedInvocation filterInvocation(GuardedInvocation invocation) {
      MethodType type = invocation.getInvocation().type();
      return invocation.asType(type.changeParameterType(0, Object.class)).filterArguments(0, new MethodHandle[] { Global.LEXICAL_SCOPE_FILTER });
    }
  }
}
