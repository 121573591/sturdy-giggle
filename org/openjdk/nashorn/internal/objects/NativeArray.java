package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.Debug;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.OptimisticBuiltins;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.openjdk.nashorn.internal.runtime.arrays.ContinuousArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.linker.InvokeByName;

public final class NativeArray extends ScriptObject implements OptimisticBuiltins {
  private static final Object JOIN = new Object();
  
  private static final Object EVERY_CALLBACK_INVOKER = new Object();
  
  private static final Object SOME_CALLBACK_INVOKER = new Object();
  
  private static final Object FOREACH_CALLBACK_INVOKER = new Object();
  
  private static final Object MAP_CALLBACK_INVOKER = new Object();
  
  private static final Object FILTER_CALLBACK_INVOKER = new Object();
  
  private static final Object REDUCE_CALLBACK_INVOKER = new Object();
  
  private static final Object CALL_CMP = new Object();
  
  private static final Object TO_LOCALE_STRING = new Object();
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  NativeArray() {
    this(ArrayData.initialArray());
  }
  
  NativeArray(long length) {
    this(ArrayData.allocate(length));
  }
  
  NativeArray(int[] array) {
    this(ArrayData.allocate(array));
  }
  
  NativeArray(double[] array) {
    this(ArrayData.allocate(array));
  }
  
  NativeArray(long[] array) {
    this(ArrayData.allocate(array.length));
    ArrayData arrayData = getArray();
    Class<?> widest = int.class;
    for (int index = 0; index < array.length; index++) {
      long value = array[index];
      if (widest == int.class && JSType.isRepresentableAsInt(value)) {
        arrayData = arrayData.set(index, (int)value, false);
      } else if (widest != Object.class && JSType.isRepresentableAsDouble(value)) {
        arrayData = arrayData.set(index, value, false);
        widest = double.class;
      } else {
        arrayData = arrayData.set(index, Long.valueOf(value), false);
        widest = Object.class;
      } 
    } 
    setArray(arrayData);
  }
  
  NativeArray(Object[] array) {
    this(ArrayData.allocate(array.length));
    ArrayData arrayData = getArray();
    for (int index = 0; index < array.length; index++) {
      Object value = array[index];
      if (value == ScriptRuntime.EMPTY) {
        arrayData = arrayData.delete(index);
      } else {
        arrayData = arrayData.set(index, value, false);
      } 
    } 
    setArray(arrayData);
  }
  
  NativeArray(ArrayData arrayData) {
    this(arrayData, Global.instance());
  }
  
  NativeArray(ArrayData arrayData, Global global) {
    super(global.getArrayPrototype(), $nasgenmap$);
    setArray(arrayData);
    setIsArray();
  }
  
  protected GuardedInvocation findGetIndexMethod(CallSiteDescriptor desc, LinkRequest request) {
    GuardedInvocation inv = getArray().findFastGetIndexMethod(getArray().getClass(), desc, request);
    if (inv != null)
      return inv; 
    return super.findGetIndexMethod(desc, request);
  }
  
  protected GuardedInvocation findSetIndexMethod(CallSiteDescriptor desc, LinkRequest request) {
    GuardedInvocation inv = getArray().findFastSetIndexMethod(getArray().getClass(), desc, request);
    if (inv != null)
      return inv; 
    return super.findSetIndexMethod(desc, request);
  }
  
  private static InvokeByName getJOIN() {
    return Global.instance().getInvokeByName(JOIN, () -> new InvokeByName("join", ScriptObject.class));
  }
  
  private static MethodHandle createIteratorCallbackInvoker(Object key, Class<?> rtype) {
    return Global.instance().getDynamicInvoker(key, () -> Bootstrap.createDynamicCallInvoker(rtype, new Class[] { Object.class, Object.class, Object.class, double.class, Object.class }));
  }
  
  private static MethodHandle getEVERY_CALLBACK_INVOKER() {
    return createIteratorCallbackInvoker(EVERY_CALLBACK_INVOKER, boolean.class);
  }
  
  private static MethodHandle getSOME_CALLBACK_INVOKER() {
    return createIteratorCallbackInvoker(SOME_CALLBACK_INVOKER, boolean.class);
  }
  
  private static MethodHandle getFOREACH_CALLBACK_INVOKER() {
    return createIteratorCallbackInvoker(FOREACH_CALLBACK_INVOKER, void.class);
  }
  
  private static MethodHandle getMAP_CALLBACK_INVOKER() {
    return createIteratorCallbackInvoker(MAP_CALLBACK_INVOKER, Object.class);
  }
  
  private static MethodHandle getFILTER_CALLBACK_INVOKER() {
    return createIteratorCallbackInvoker(FILTER_CALLBACK_INVOKER, boolean.class);
  }
  
  private static MethodHandle getREDUCE_CALLBACK_INVOKER() {
    return Global.instance().getDynamicInvoker(REDUCE_CALLBACK_INVOKER, () -> Bootstrap.createDynamicCallInvoker(Object.class, new Class[] { Object.class, Undefined.class, Object.class, Object.class, double.class, Object.class }));
  }
  
  private static MethodHandle getCALL_CMP() {
    return Global.instance().getDynamicInvoker(CALL_CMP, () -> Bootstrap.createDynamicCallInvoker(double.class, new Class[] { Object.class, Object.class, Object.class, Object.class }));
  }
  
  private static InvokeByName getTO_LOCALE_STRING() {
    return Global.instance().getInvokeByName(TO_LOCALE_STRING, () -> new InvokeByName("toLocaleString", ScriptObject.class, String.class, new Class[0]));
  }
  
  public String getClassName() {
    return "Array";
  }
  
  public Object getLength() {
    long length = getArray().length();
    assert length >= 0L;
    if (length <= 2147483647L)
      return Integer.valueOf((int)length); 
    return Long.valueOf(length);
  }
  
  private boolean defineLength(long oldLen, PropertyDescriptor oldLenDesc, PropertyDescriptor desc, boolean reject) {
    if (!desc.has("value"))
      return super.defineOwnProperty("length", desc, reject); 
    long newLen = validLength(desc.getValue());
    desc.setValue(JSType.toNarrowestNumber(newLen));
    if (newLen >= oldLen)
      return super.defineOwnProperty("length", desc, reject); 
    if (!oldLenDesc.isWritable()) {
      if (reject)
        throw ECMAErrors.typeError("property.not.writable", new String[] { "length", ScriptRuntime.safeToString(this) }); 
      return false;
    } 
    boolean newWritable = (!desc.has("writable") || desc.isWritable());
    if (!newWritable)
      desc.setWritable(true); 
    boolean succeeded = super.defineOwnProperty("length", desc, reject);
    if (!succeeded)
      return false; 
    long o = oldLen;
    while (newLen < o) {
      o--;
      boolean deleteSucceeded = delete(o, false);
      if (!deleteSucceeded) {
        desc.setValue(Long.valueOf(o + 1L));
        if (!newWritable)
          desc.setWritable(false); 
        super.defineOwnProperty("length", desc, false);
        if (reject)
          throw ECMAErrors.typeError("property.not.writable", new String[] { "length", ScriptRuntime.safeToString(this) }); 
        return false;
      } 
    } 
    if (!newWritable) {
      ScriptObject newDesc = Global.newEmptyInstance();
      newDesc.set("writable", Boolean.valueOf(false), 0);
      return super.defineOwnProperty("length", newDesc, false);
    } 
    return true;
  }
  
  public boolean defineOwnProperty(Object key, Object propertyDesc, boolean reject) {
    PropertyDescriptor desc = toPropertyDescriptor(Global.instance(), propertyDesc);
    PropertyDescriptor oldLenDesc = (PropertyDescriptor)getOwnPropertyDescriptor("length");
    long oldLen = JSType.toUint32(oldLenDesc.getValue());
    if ("length".equals(key)) {
      boolean result = defineLength(oldLen, oldLenDesc, desc, reject);
      if (desc.has("writable") && !desc.isWritable())
        setIsLengthNotWritable(); 
      return result;
    } 
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      long longIndex = ArrayIndex.toLongIndex(index);
      if (longIndex >= oldLen && !oldLenDesc.isWritable()) {
        if (reject)
          throw ECMAErrors.typeError("property.not.writable", new String[] { Long.toString(longIndex), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
      boolean succeeded = super.defineOwnProperty(key, desc, false);
      if (!succeeded) {
        if (reject)
          throw ECMAErrors.typeError("cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
      if (longIndex >= oldLen) {
        oldLenDesc.setValue(Long.valueOf(longIndex + 1L));
        super.defineOwnProperty("length", oldLenDesc, false);
      } 
      return true;
    } 
    return super.defineOwnProperty(key, desc, reject);
  }
  
  public final void defineOwnProperty(int index, Object value) {
    assert ArrayIndex.isValidArrayIndex(index) : "invalid array index";
    long longIndex = ArrayIndex.toLongIndex(index);
    if (longIndex >= getArray().length())
      setArray(getArray().ensure(longIndex)); 
    setArray(getArray().set(index, value, false));
  }
  
  public Object[] asObjectArray() {
    return getArray().asObjectArray();
  }
  
  public void setIsLengthNotWritable() {
    super.setIsLengthNotWritable();
    setArray(ArrayData.setIsLengthNotWritable(getArray()));
  }
  
  public static boolean isArray(Object self, Object arg) {
    return (isArray(arg) || (arg instanceof JSObject && ((JSObject)arg).isArray()));
  }
  
  public static Object length(Object self) {
    if (isArray(self)) {
      long length = ((ScriptObject)self).getArray().length();
      assert length >= 0L;
      if (length <= 2147483647L)
        return Integer.valueOf((int)length); 
      return Double.valueOf(length);
    } 
    return Integer.valueOf(0);
  }
  
  public static void length(Object self, Object length) {
    if (isArray(self))
      ((ScriptObject)self).setLength(validLength(length)); 
  }
  
  public static Object getProtoLength(Object self) {
    return length(self);
  }
  
  public static void setProtoLength(Object self, Object length) {
    length(self, length);
  }
  
  static long validLength(Object length) {
    double doubleLength = JSType.toNumber(length);
    if (doubleLength != JSType.toUint32(length))
      throw ECMAErrors.rangeError("inappropriate.array.length", new String[] { ScriptRuntime.safeToString(length) }); 
    return (long)doubleLength;
  }
  
  public static Object toString(Object self) {
    Object obj = Global.toObject(self);
    if (obj instanceof ScriptObject) {
      InvokeByName joinInvoker = getJOIN();
      ScriptObject sobj = (ScriptObject)obj;
      try {
        Object join = joinInvoker.getGetter().invokeExact(sobj);
        if (Bootstrap.isCallable(join))
          return joinInvoker.getInvoker().invokeExact(join, sobj); 
      } catch (RuntimeException|Error e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      } 
    } 
    return ScriptRuntime.builtinObjectToString(self);
  }
  
  public static Object assertNumeric(Object self) {
    if (!(self instanceof NativeArray) || !((NativeArray)self).getArray().getOptimisticType().isNumeric())
      throw ECMAErrors.typeError("not.a.numeric.array", new String[] { ScriptRuntime.safeToString(self) }); 
    return Boolean.TRUE;
  }
  
  public static String toLocaleString(Object self) {
    StringBuilder sb = new StringBuilder();
    ArrayLikeIterator arrayLikeIterator = ArrayLikeIterator.arrayLikeIterator(self, true);
    while (arrayLikeIterator.hasNext()) {
      Object obj = arrayLikeIterator.next();
      if (obj != null && obj != ScriptRuntime.UNDEFINED) {
        Object val = JSType.toScriptObject(obj);
        try {
          if (val instanceof ScriptObject) {
            InvokeByName localeInvoker = getTO_LOCALE_STRING();
            ScriptObject sobj = (ScriptObject)val;
            Object toLocaleString = localeInvoker.getGetter().invokeExact(sobj);
            if (Bootstrap.isCallable(toLocaleString)) {
              sb.append(localeInvoker.getInvoker().invokeExact(toLocaleString, sobj));
            } else {
              throw ECMAErrors.typeError("not.a.function", new String[] { "toLocaleString" });
            } 
          } 
        } catch (Error|RuntimeException t) {
          throw t;
        } catch (Throwable t) {
          throw new RuntimeException(t);
        } 
      } 
      if (arrayLikeIterator.hasNext())
        sb.append(","); 
    } 
    return sb.toString();
  }
  
  public static NativeArray construct(boolean newObj, Object self, Object... args) {
    Object len;
    switch (args.length) {
      case 0:
        return new NativeArray(0L);
      case 1:
        len = args[0];
        if (len instanceof Number) {
          if (len instanceof Integer || len instanceof Long) {
            long l = ((Number)len).longValue();
            if (l >= 0L && l < 4294967295L)
              return new NativeArray(l); 
          } 
          long length = JSType.toUint32(len);
          double numberLength = ((Number)len).doubleValue();
          if (length != numberLength)
            throw ECMAErrors.rangeError("inappropriate.array.length", new String[] { JSType.toString(numberLength) }); 
          return new NativeArray(length);
        } 
        return new NativeArray(new Object[] { args[0] });
    } 
    return new NativeArray(args);
  }
  
  public static NativeArray construct(boolean newObj, Object self) {
    return new NativeArray(0L);
  }
  
  public static Object construct(boolean newObj, Object self, boolean element) {
    return new NativeArray(new Object[] { Boolean.valueOf(element) });
  }
  
  public static NativeArray construct(boolean newObj, Object self, int length) {
    if (length >= 0)
      return new NativeArray(length); 
    return construct(newObj, self, new Object[] { Integer.valueOf(length) });
  }
  
  public static NativeArray construct(boolean newObj, Object self, long length) {
    if (length >= 0L && length <= 4294967295L)
      return new NativeArray(length); 
    return construct(newObj, self, new Object[] { Long.valueOf(length) });
  }
  
  public static NativeArray construct(boolean newObj, Object self, double length) {
    long uint32length = JSType.toUint32(length);
    if (uint32length == length)
      return new NativeArray(uint32length); 
    return construct(newObj, self, new Object[] { Double.valueOf(length) });
  }
  
  public static NativeArray concat(Object self, int arg) {
    ContinuousArrayData newData = getContinuousArrayDataCCE(self, Integer.class).copy();
    newData.fastPush(arg);
    return new NativeArray((ArrayData)newData);
  }
  
  public static NativeArray concat(Object self, double arg) {
    ContinuousArrayData newData = getContinuousArrayDataCCE(self, Double.class).copy();
    newData.fastPush(arg);
    return new NativeArray((ArrayData)newData);
  }
  
  public static NativeArray concat(Object self, Object arg) {
    ContinuousArrayData newData, selfData = getContinuousArrayDataCCE(self);
    if (arg instanceof NativeArray) {
      ContinuousArrayData argData = (ContinuousArrayData)((NativeArray)arg).getArray();
      if (argData.isEmpty()) {
        newData = selfData.copy();
      } else if (selfData.isEmpty()) {
        newData = argData.copy();
      } else {
        Class<?> widestElementType = selfData.widest(argData).getBoxedElementType();
        newData = ((ContinuousArrayData)selfData.convert(widestElementType)).fastConcat((ContinuousArrayData)argData.convert(widestElementType));
      } 
    } else {
      newData = getContinuousArrayDataCCE(self, Object.class).copy();
      newData.fastPush(arg);
    } 
    return new NativeArray((ArrayData)newData);
  }
  
  public static NativeArray concat(Object self, Object... args) {
    ArrayList<Object> list = new ArrayList();
    concatToList(list, Global.toObject(self));
    for (Object obj : args)
      concatToList(list, obj); 
    return new NativeArray(list.toArray());
  }
  
  private static void concatToList(ArrayList<Object> list, Object obj) {
    boolean isScriptArray = isArray(obj);
    boolean isScriptObject = (isScriptArray || obj instanceof ScriptObject);
    if (isScriptArray || obj instanceof Iterable || obj instanceof JSObject || (obj != null && obj.getClass().isArray())) {
      ArrayLikeIterator arrayLikeIterator = ArrayLikeIterator.arrayLikeIterator(obj, true);
      if (arrayLikeIterator.hasNext()) {
        for (int i = 0; arrayLikeIterator.hasNext(); i++) {
          Object value = arrayLikeIterator.next();
          if (value == ScriptRuntime.UNDEFINED && isScriptObject && !((ScriptObject)obj).has(i)) {
            list.add(ScriptRuntime.EMPTY);
          } else {
            list.add(value);
          } 
        } 
      } else if (!isScriptArray) {
        list.add(obj);
      } 
    } else {
      list.add(obj);
    } 
  }
  
  public static String join(Object self, Object separator) {
    StringBuilder sb = new StringBuilder();
    ArrayLikeIterator arrayLikeIterator = ArrayLikeIterator.arrayLikeIterator(self, true);
    String sep = (separator == ScriptRuntime.UNDEFINED) ? "," : JSType.toString(separator);
    while (arrayLikeIterator.hasNext()) {
      Object obj = arrayLikeIterator.next();
      if (obj != null && obj != ScriptRuntime.UNDEFINED)
        sb.append(JSType.toString(obj)); 
      if (arrayLikeIterator.hasNext())
        sb.append(sep); 
    } 
    return sb.toString();
  }
  
  public static int popInt(Object self) {
    return getContinuousNonEmptyArrayDataCCE(self).fastPopInt();
  }
  
  public static double popDouble(Object self) {
    return getContinuousNonEmptyArrayDataCCE(self).fastPopDouble();
  }
  
  public static Object popObject(Object self) {
    return getContinuousArrayDataCCE(self, (Class<?>)null).fastPopObject();
  }
  
  public static Object pop(Object self) {
    Object obj = Global.toObject(self);
    if (!(obj instanceof ScriptObject))
      return ScriptRuntime.UNDEFINED; 
    ScriptObject sobj = (ScriptObject)obj;
    if (bulkable(sobj))
      return sobj.getArray().pop(); 
    long len = JSType.toUint32(sobj.getLength());
    if (len == 0L) {
      sobj.set("length", 0, 32);
      return ScriptRuntime.UNDEFINED;
    } 
    long index = len - 1L;
    Object element = sobj.get(index);
    sobj.delete(index, true);
    sobj.set("length", index, 32);
    return element;
  }
  
  public static double push(Object self, int arg) {
    return getContinuousArrayDataCCE(self, Integer.class).fastPush(arg);
  }
  
  public static double push(Object self, double arg) {
    return getContinuousArrayDataCCE(self, Double.class).fastPush(arg);
  }
  
  public static double pushObject(Object self, Object arg) {
    return getContinuousArrayDataCCE(self, Object.class).fastPush(arg);
  }
  
  public static Object push(Object self, Object... args) {
    try {
      Object obj = Global.toObject(self);
      if (!(obj instanceof ScriptObject))
        return Integer.valueOf(args.length); 
      ScriptObject sobj = (ScriptObject)obj;
      if (bulkable(sobj) && sobj.getArray().length() + args.length <= 4294967295L) {
        ArrayData newData = sobj.getArray().push(true, args);
        sobj.setArray(newData);
        return JSType.toNarrowestNumber(newData.length());
      } 
      long len = JSType.toUint32(sobj.getLength());
      for (Object element : args)
        sobj.set(len++, element, 32); 
      sobj.set("length", len, 32);
      return JSType.toNarrowestNumber(len);
    } catch (ClassCastException|NullPointerException e) {
      throw ECMAErrors.typeError(Context.getGlobal(), e, "not.an.object", new String[] { ScriptRuntime.safeToString(self) });
    } 
  }
  
  public static double push(Object self, Object arg) {
    Object obj = Global.toObject(self);
    if (!(obj instanceof ScriptObject))
      return 1.0D; 
    ScriptObject sobj = (ScriptObject)obj;
    ArrayData arrayData = sobj.getArray();
    long length = arrayData.length();
    if (bulkable(sobj) && length < 4294967295L) {
      sobj.setArray(arrayData.push(true, arg));
      return (length + 1L);
    } 
    long len = JSType.toUint32(sobj.getLength());
    sobj.set(len++, arg, 32);
    sobj.set("length", len, 32);
    return len;
  }
  
  public static Object reverse(Object self) {
    Object obj = Global.toObject(self);
    if (!(obj instanceof ScriptObject))
      return obj; 
    ScriptObject sobj = (ScriptObject)obj;
    long len = JSType.toUint32(sobj.getLength());
    long middle = len / 2L;
    long lower;
    for (lower = 0L; lower != middle; lower++) {
      long upper = len - lower - 1L;
      Object lowerValue = sobj.get(lower);
      Object upperValue = sobj.get(upper);
      boolean lowerExists = sobj.has(lower);
      boolean upperExists = sobj.has(upper);
      if (lowerExists && upperExists) {
        sobj.set(lower, upperValue, 32);
        sobj.set(upper, lowerValue, 32);
      } else if (!lowerExists && upperExists) {
        sobj.set(lower, upperValue, 32);
        sobj.delete(upper, true);
      } else if (lowerExists) {
        sobj.delete(lower, true);
        sobj.set(upper, lowerValue, 32);
      } 
    } 
    return sobj;
  }
  
  public static Object shift(Object self) {
    Object obj = Global.toObject(self);
    Object first = ScriptRuntime.UNDEFINED;
    if (!(obj instanceof ScriptObject))
      return first; 
    ScriptObject sobj = (ScriptObject)obj;
    long len = JSType.toUint32(sobj.getLength());
    first = sobj.get(0);
    if (bulkable(sobj)) {
      sobj.getArray().shiftLeft(1);
    } else {
      boolean hasPrevious = true;
      long k;
      for (k = 1L; k < len; k++) {
        boolean hasCurrent = sobj.has(k);
        if (hasCurrent) {
          sobj.set((k - 1L), sobj.get(k), 32);
        } else if (hasPrevious) {
          sobj.delete((k - 1L), true);
        } 
        hasPrevious = hasCurrent;
      } 
    } 
    sobj.delete(--len, true);
    len = 0L;
    sobj.set("length", len, 32);
    return first;
  }
  
  public static Object slice(Object self, Object start, Object end) {
    Object obj = Global.toObject(self);
    if (!(obj instanceof ScriptObject))
      return ScriptRuntime.UNDEFINED; 
    ScriptObject sobj = (ScriptObject)obj;
    long len = JSType.toUint32(sobj.getLength());
    long relativeStart = JSType.toLong(start);
    long relativeEnd = (end == ScriptRuntime.UNDEFINED) ? len : JSType.toLong(end);
    long k = (relativeStart < 0L) ? Math.max(len + relativeStart, 0L) : Math.min(relativeStart, len);
    long finale = (relativeEnd < 0L) ? Math.max(len + relativeEnd, 0L) : Math.min(relativeEnd, len);
    if (k >= finale)
      return new NativeArray(0L); 
    if (bulkable(sobj))
      return new NativeArray(sobj.getArray().slice(k, finale)); 
    NativeArray copy = new NativeArray(finale - k);
    for (long n = 0L; k < finale; n++, k++) {
      if (sobj.has(k))
        copy.defineOwnProperty(ArrayIndex.getArrayIndex(n), sobj.get(k)); 
    } 
    return copy;
  }
  
  private static Object compareFunction(Object comparefn) {
    if (comparefn == ScriptRuntime.UNDEFINED)
      return null; 
    if (!Bootstrap.isCallable(comparefn))
      throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(comparefn) }); 
    return comparefn;
  }
  
  private static Object[] sort(Object[] array, Object comparefn) {
    final Object cmp = compareFunction(comparefn);
    List<Object> list = Arrays.asList(array);
    final Object cmpThis = (cmp == null || Bootstrap.isStrictCallable(cmp)) ? ScriptRuntime.UNDEFINED : Global.instance();
    try {
      list.sort(new Comparator() {
            private final MethodHandle call_cmp = NativeArray.getCALL_CMP();
            
            public int compare(Object x, Object y) {
              if (x == ScriptRuntime.UNDEFINED && y == ScriptRuntime.UNDEFINED)
                return 0; 
              if (x == ScriptRuntime.UNDEFINED)
                return 1; 
              if (y == ScriptRuntime.UNDEFINED)
                return -1; 
              if (cmp != null)
                try {
                  return (int)Math.signum(this.call_cmp.invokeExact(cmp, cmpThis, x, y));
                } catch (RuntimeException|Error e) {
                  throw e;
                } catch (Throwable t) {
                  throw new RuntimeException(t);
                }  
              return JSType.toString(x).compareTo(JSType.toString(y));
            }
          });
    } catch (IllegalArgumentException illegalArgumentException) {}
    return list.toArray(new Object[0]);
  }
  
  public static ScriptObject sort(Object self, Object comparefn) {
    try {
      ScriptObject sobj = (ScriptObject)self;
      long len = JSType.toUint32(sobj.getLength());
      ArrayData array = sobj.getArray();
      if (len > 1L) {
        ArrayList<Object> src = new ArrayList();
        for (Iterator<Long> iter = array.indexIterator(); iter.hasNext(); ) {
          long index = ((Long)iter.next()).longValue();
          if (index >= len)
            break; 
          src.add(array.getObject((int)index));
        } 
        Object[] sorted = sort(src.toArray(), comparefn);
        for (int i = 0; i < sorted.length; i++)
          array = array.set(i, sorted[i], true); 
        if (sorted.length != len)
          array = array.delete(sorted.length, len - 1L); 
        sobj.setArray(array);
      } 
      return sobj;
    } catch (ClassCastException|NullPointerException e) {
      throw ECMAErrors.typeError("not.an.object", new String[] { ScriptRuntime.safeToString(self) });
    } 
  }
  
  public static Object splice(Object self, Object... args) {
    long actualDeleteCount;
    NativeArray returnValue;
    Object obj = Global.toObject(self);
    if (!(obj instanceof ScriptObject))
      return ScriptRuntime.UNDEFINED; 
    ScriptObject sobj = (ScriptObject)obj;
    long len = JSType.toUint32(sobj.getLength());
    long relativeStart = JSType.toLong((args.length > 0) ? args[0] : ScriptRuntime.UNDEFINED);
    long actualStart = (relativeStart < 0L) ? Math.max(len + relativeStart, 0L) : Math.min(relativeStart, len);
    Object[] items = ScriptRuntime.EMPTY_ARRAY;
    if (args.length == 0) {
      actualDeleteCount = 0L;
    } else if (args.length == 1) {
      actualDeleteCount = len - actualStart;
    } else {
      actualDeleteCount = Math.min(Math.max(JSType.toLong(args[1]), 0L), len - actualStart);
      if (args.length > 2) {
        items = new Object[args.length - 2];
        System.arraycopy(args, 2, items, 0, items.length);
      } 
    } 
    if (actualStart <= 2147483647L && actualDeleteCount <= 2147483647L && bulkable(sobj)) {
      try {
        returnValue = new NativeArray(sobj.getArray().fastSplice((int)actualStart, (int)actualDeleteCount, items.length));
        int k = (int)actualStart;
        for (int i = 0; i < items.length; i++, k++)
          sobj.defineOwnProperty(k, items[i]); 
      } catch (UnsupportedOperationException uoe) {
        returnValue = slowSplice(sobj, actualStart, actualDeleteCount, items, len);
      } 
    } else {
      returnValue = slowSplice(sobj, actualStart, actualDeleteCount, items, len);
    } 
    return returnValue;
  }
  
  private static NativeArray slowSplice(ScriptObject sobj, long start, long deleteCount, Object[] items, long len) {
    NativeArray array = new NativeArray(deleteCount);
    long k;
    for (k = 0L; k < deleteCount; k++) {
      long from = start + k;
      if (sobj.has(from))
        array.defineOwnProperty(ArrayIndex.getArrayIndex(k), sobj.get(from)); 
    } 
    if (items.length < deleteCount) {
      for (k = start; k < len - deleteCount; k++) {
        long from = k + deleteCount;
        long to = k + items.length;
        if (sobj.has(from)) {
          sobj.set(to, sobj.get(from), 32);
        } else {
          sobj.delete(to, true);
        } 
      } 
      for (k = len; k > len - deleteCount + items.length; k--)
        sobj.delete((k - 1L), true); 
    } else if (items.length > deleteCount) {
      for (k = len - deleteCount; k > start; k--) {
        long from = k + deleteCount - 1L;
        long to = k + items.length - 1L;
        if (sobj.has(from)) {
          Object fromValue = sobj.get(from);
          sobj.set(to, fromValue, 32);
        } else {
          sobj.delete(to, true);
        } 
      } 
    } 
    k = start;
    for (int i = 0; i < items.length; i++, k++)
      sobj.set(k, items[i], 32); 
    long newLength = len - deleteCount + items.length;
    sobj.set("length", newLength, 32);
    return array;
  }
  
  public static Object unshift(Object self, Object... items) {
    Object obj = Global.toObject(self);
    if (!(obj instanceof ScriptObject))
      return ScriptRuntime.UNDEFINED; 
    ScriptObject sobj = (ScriptObject)obj;
    long len = JSType.toUint32(sobj.getLength());
    if (items == null)
      return ScriptRuntime.UNDEFINED; 
    if (bulkable(sobj)) {
      sobj.getArray().shiftRight(items.length);
      for (int j = 0; j < items.length; j++)
        sobj.setArray(sobj.getArray().set(j, items[j], true)); 
    } else {
      long k;
      for (k = len; k > 0L; k--) {
        long from = k - 1L;
        long to = k + items.length - 1L;
        if (sobj.has(from)) {
          Object fromValue = sobj.get(from);
          sobj.set(to, fromValue, 32);
        } else {
          sobj.delete(to, true);
        } 
      } 
      for (int j = 0; j < items.length; j++)
        sobj.set(j, items[j], 32); 
    } 
    long newLength = len + items.length;
    sobj.set("length", newLength, 32);
    return JSType.toNarrowestNumber(newLength);
  }
  
  public static double indexOf(Object self, Object searchElement, Object fromIndex) {
    try {
      ScriptObject sobj = (ScriptObject)Global.toObject(self);
      long len = JSType.toUint32(sobj.getLength());
      if (len == 0L)
        return -1.0D; 
      long n = JSType.toLong(fromIndex);
      if (n >= len)
        return -1.0D; 
      long k;
      for (k = Math.max(0L, (n < 0L) ? (len - Math.abs(n)) : n); k < len; k++) {
        if (sobj.has(k) && 
          ScriptRuntime.EQ_STRICT(sobj.get(k), searchElement))
          return k; 
      } 
    } catch (ClassCastException|NullPointerException classCastException) {}
    return -1.0D;
  }
  
  public static double lastIndexOf(Object self, Object... args) {
    try {
      ScriptObject sobj = (ScriptObject)Global.toObject(self);
      long len = JSType.toUint32(sobj.getLength());
      if (len == 0L)
        return -1.0D; 
      Object searchElement = (args.length > 0) ? args[0] : ScriptRuntime.UNDEFINED;
      long n = (args.length > 1) ? JSType.toLong(args[1]) : (len - 1L);
      long k;
      for (k = (n < 0L) ? (len - Math.abs(n)) : Math.min(n, len - 1L); k >= 0L; k--) {
        if (sobj.has(k) && 
          ScriptRuntime.EQ_STRICT(sobj.get(k), searchElement))
          return k; 
      } 
    } catch (ClassCastException|NullPointerException e) {
      throw ECMAErrors.typeError("not.an.object", new String[] { ScriptRuntime.safeToString(self) });
    } 
    return -1.0D;
  }
  
  public static boolean every(Object self, Object callbackfn, Object thisArg) {
    return applyEvery(Global.toObject(self), callbackfn, thisArg);
  }
  
  private static boolean applyEvery(Object self, Object callbackfn, Object thisArg) {
    return ((Boolean)(new IteratorAction<Boolean>(Global.toObject(self), callbackfn, thisArg, Boolean.valueOf(true)) {
        private final MethodHandle everyInvoker = NativeArray.getEVERY_CALLBACK_INVOKER();
        
        protected boolean forEach(Object val, double i) throws Throwable {
          return ((Boolean)(this.result = Boolean.valueOf(this.everyInvoker.invokeExact(this.callbackfn, this.thisArg, val, i, this.self)))).booleanValue();
        }
      }).apply()).booleanValue();
  }
  
  public static boolean some(Object self, Object callbackfn, Object thisArg) {
    return ((Boolean)(new IteratorAction<Boolean>(Global.toObject(self), callbackfn, thisArg, Boolean.valueOf(false)) {
        private final MethodHandle someInvoker = NativeArray.getSOME_CALLBACK_INVOKER();
        
        protected boolean forEach(Object val, double i) throws Throwable {
          return !((Boolean)(this.result = Boolean.valueOf(this.someInvoker.invokeExact(this.callbackfn, this.thisArg, val, i, this.self)))).booleanValue();
        }
      }).apply()).booleanValue();
  }
  
  public static Object forEach(Object self, Object callbackfn, Object thisArg) {
    return (new IteratorAction<Object>(Global.toObject(self), callbackfn, thisArg, ScriptRuntime.UNDEFINED) {
        private final MethodHandle forEachInvoker = NativeArray.getFOREACH_CALLBACK_INVOKER();
        
        protected boolean forEach(Object val, double i) throws Throwable {
          this.forEachInvoker.invokeExact(this.callbackfn, this.thisArg, val, i, this.self);
          return true;
        }
      }).apply();
  }
  
  public static NativeArray map(Object self, Object callbackfn, Object thisArg) {
    return (NativeArray)(new IteratorAction<NativeArray>(Global.toObject(self), callbackfn, thisArg, null) {
        private final MethodHandle mapInvoker = NativeArray.getMAP_CALLBACK_INVOKER();
        
        protected boolean forEach(Object val, double i) throws Throwable {
          Object r = this.mapInvoker.invokeExact(this.callbackfn, this.thisArg, val, i, this.self);
          ((NativeArray)this.result).defineOwnProperty(ArrayIndex.getArrayIndex(this.index), r);
          return true;
        }
        
        public void applyLoopBegin(ArrayLikeIterator<Object> iter0) {
          this.result = new NativeArray(iter0.getLength());
        }
      }).apply();
  }
  
  public static NativeArray filter(Object self, Object callbackfn, Object thisArg) {
    return (NativeArray)(new IteratorAction<NativeArray>(Global.toObject(self), callbackfn, thisArg, new NativeArray()) {
        private long to = 0L;
        
        private final MethodHandle filterInvoker = NativeArray.getFILTER_CALLBACK_INVOKER();
        
        protected boolean forEach(Object val, double i) throws Throwable {
          if (this.filterInvoker.invokeExact(this.callbackfn, this.thisArg, val, i, this.self))
            ((NativeArray)this.result).defineOwnProperty(ArrayIndex.getArrayIndex(this.to++), val); 
          return true;
        }
      }).apply();
  }
  
  private static Object reduceInner(ArrayLikeIterator<Object> iter, Object self, Object... args) {
    Object callbackfn = (args.length > 0) ? args[0] : ScriptRuntime.UNDEFINED;
    boolean initialValuePresent = (args.length > 1);
    Object initialValue = initialValuePresent ? args[1] : ScriptRuntime.UNDEFINED;
    if (callbackfn == ScriptRuntime.UNDEFINED)
      throw ECMAErrors.typeError("not.a.function", new String[] { "undefined" }); 
    if (!initialValuePresent)
      if (iter.hasNext()) {
        initialValue = iter.next();
      } else {
        throw ECMAErrors.typeError("array.reduce.invalid.init", new String[0]);
      }  
    return (new IteratorAction<Object>(Global.toObject(self), callbackfn, ScriptRuntime.UNDEFINED, initialValue, iter) {
        private final MethodHandle reduceInvoker = NativeArray.getREDUCE_CALLBACK_INVOKER();
        
        protected boolean forEach(Object val, double i) throws Throwable {
          this.result = this.reduceInvoker.invokeExact(this.callbackfn, ScriptRuntime.UNDEFINED, this.result, val, i, this.self);
          return true;
        }
      }).apply();
  }
  
  public static Object reduce(Object self, Object... args) {
    return reduceInner(ArrayLikeIterator.arrayLikeIterator(self), self, args);
  }
  
  public static Object reduceRight(Object self, Object... args) {
    return reduceInner(ArrayLikeIterator.reverseArrayLikeIterator(self), self, args);
  }
  
  public static Object entries(Object self) {
    return ArrayIterator.newArrayKeyValueIterator(self);
  }
  
  public static Object keys(Object self) {
    return ArrayIterator.newArrayKeyIterator(self);
  }
  
  public static Object values(Object self) {
    return ArrayIterator.newArrayValueIterator(self);
  }
  
  public static Object getIterator(Object self) {
    return ArrayIterator.newArrayValueIterator(self);
  }
  
  private static boolean bulkable(ScriptObject self) {
    return (self.isArray() && !hasInheritedArrayEntries(self) && !self.isLengthNotWritable());
  }
  
  private static boolean hasInheritedArrayEntries(ScriptObject self) {
    ScriptObject proto = self.getProto();
    while (proto != null) {
      if (proto.hasArrayEntries())
        return true; 
      proto = proto.getProto();
    } 
    return false;
  }
  
  public String toString() {
    return "NativeArray@" + Debug.id(this) + " [" + getArray().getClass().getSimpleName() + "]";
  }
  
  public SpecializedFunction.LinkLogic getLinkLogic(Class<? extends SpecializedFunction.LinkLogic> clazz) {
    if (clazz == PushLinkLogic.class)
      return PushLinkLogic.INSTANCE; 
    if (clazz == PopLinkLogic.class)
      return PopLinkLogic.INSTANCE; 
    if (clazz == ConcatLinkLogic.class)
      return ConcatLinkLogic.INSTANCE; 
    return null;
  }
  
  public boolean hasPerInstanceAssumptions() {
    return true;
  }
  
  private static abstract class ArrayLinkLogic extends SpecializedFunction.LinkLogic {
    protected static ContinuousArrayData getContinuousArrayData(Object self) {
      try {
        return (ContinuousArrayData)((NativeArray)self).getArray();
      } catch (Exception e) {
        return null;
      } 
    }
    
    public Class<? extends Throwable> getRelinkException() {
      return (Class)ClassCastException.class;
    }
  }
  
  private static final class ConcatLinkLogic extends ArrayLinkLogic {
    private static final SpecializedFunction.LinkLogic INSTANCE = new ConcatLinkLogic();
    
    public boolean canLink(Object self, CallSiteDescriptor desc, LinkRequest request) {
      Object[] args = request.getArguments();
      if (args.length != 3)
        return false; 
      ContinuousArrayData selfData = getContinuousArrayData(self);
      if (selfData == null)
        return false; 
      Object arg = args[2];
      if (arg instanceof NativeArray)
        return (getContinuousArrayData(arg) != null); 
      return JSType.isPrimitive(arg);
    }
  }
  
  private static final class PushLinkLogic extends ArrayLinkLogic {
    private static final SpecializedFunction.LinkLogic INSTANCE = new PushLinkLogic();
    
    public boolean canLink(Object self, CallSiteDescriptor desc, LinkRequest request) {
      return (getContinuousArrayData(self) != null);
    }
  }
  
  private static final class PopLinkLogic extends ArrayLinkLogic {
    private static final SpecializedFunction.LinkLogic INSTANCE = new PopLinkLogic();
    
    public boolean canLink(Object self, CallSiteDescriptor desc, LinkRequest request) {
      ContinuousArrayData data = getContinuousNonEmptyArrayData(self);
      if (data != null) {
        Class<?> elementType = data.getElementType();
        Class<?> returnType = desc.getMethodType().returnType();
        boolean typeFits = (JSType.getAccessorTypeIndex(returnType) >= JSType.getAccessorTypeIndex(elementType));
        return typeFits;
      } 
      return false;
    }
    
    private static ContinuousArrayData getContinuousNonEmptyArrayData(Object self) {
      ContinuousArrayData data = getContinuousArrayData(self);
      if (data != null)
        return (data.length() == 0L) ? null : data; 
      return null;
    }
  }
  
  private static ContinuousArrayData getContinuousNonEmptyArrayDataCCE(Object self) {
    try {
      ContinuousArrayData data = (ContinuousArrayData)((NativeArray)self).getArray();
      if (data.length() != 0L)
        return data; 
    } catch (NullPointerException nullPointerException) {}
    throw new ClassCastException();
  }
  
  private static ContinuousArrayData getContinuousArrayDataCCE(Object self) {
    try {
      return (ContinuousArrayData)((NativeArray)self).getArray();
    } catch (NullPointerException e) {
      throw new ClassCastException();
    } 
  }
  
  private static ContinuousArrayData getContinuousArrayDataCCE(Object self, Class<?> elementType) {
    try {
      return (ContinuousArrayData)((NativeArray)self).getArray(elementType);
    } catch (NullPointerException e) {
      throw new ClassCastException();
    } 
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc_w 'length'
    //   12: bipush #6
    //   14: ldc_w
    //   17: ldc_w
    //   20: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   23: invokeinterface add : (Ljava/lang/Object;)Z
    //   28: pop
    //   29: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   32: putstatic org/openjdk/nashorn/internal/objects/NativeArray.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   35: return
  }
}
