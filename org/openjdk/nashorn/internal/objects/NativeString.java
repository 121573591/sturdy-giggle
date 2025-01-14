package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.openjdk.nashorn.internal.runtime.ConsString;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.OptimisticBuiltins;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.linker.NashornGuards;
import org.openjdk.nashorn.internal.runtime.linker.PrimitiveLookup;

public final class NativeString extends ScriptObject implements OptimisticBuiltins {
  private final CharSequence value;
  
  static final MethodHandle WRAPFILTER = findOwnMH("wrapFilter", Lookup.MH.type(NativeString.class, new Class[] { Object.class }));
  
  private static final MethodHandle PROTOFILTER = findOwnMH("protoFilter", Lookup.MH.type(Object.class, new Class[] { Object.class }));
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private NativeString(CharSequence value) {
    this(value, Global.instance());
  }
  
  NativeString(CharSequence value, Global global) {
    this(value, global.getStringPrototype(), $nasgenmap$);
  }
  
  private NativeString(CharSequence value, ScriptObject proto, PropertyMap map) {
    super(proto, map);
    assert JSType.isString(value);
    this.value = value;
  }
  
  public String safeToString() {
    return "[String " + toString() + "]";
  }
  
  public String toString() {
    return getStringValue();
  }
  
  private String getStringValue() {
    return (this.value instanceof String) ? (String)this.value : this.value.toString();
  }
  
  private CharSequence getValue() {
    return this.value;
  }
  
  public String getClassName() {
    return "String";
  }
  
  public Object getLength() {
    return Integer.valueOf(this.value.length());
  }
  
  protected GuardedInvocation findGetMethod(CallSiteDescriptor desc, LinkRequest request) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    if ("length".equals(name) && NashornCallSiteDescriptor.isMethodFirstOperation(desc))
      return null; 
    return super.findGetMethod(desc, request);
  }
  
  protected GuardedInvocation findGetIndexMethod(CallSiteDescriptor desc, LinkRequest request) {
    Object self = request.getReceiver();
    Class<?> returnType = desc.getMethodType().returnType();
    if (returnType == Object.class && JSType.isString(self))
      try {
        return new GuardedInvocation(Lookup.MH.findStatic(MethodHandles.lookup(), NativeString.class, "get", desc.getMethodType()), NashornGuards.getStringGuard());
      } catch (org.openjdk.nashorn.internal.lookup.MethodHandleFactory.LookupException lookupException) {} 
    return super.findGetIndexMethod(desc, request);
  }
  
  private static Object get(Object self, Object key) {
    CharSequence cs = JSType.toCharSequence(self);
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    if (index >= 0 && index < cs.length())
      return String.valueOf(cs.charAt(index)); 
    return ((ScriptObject)Global.toObject(self)).get(primitiveKey);
  }
  
  private static Object get(Object self, double key) {
    if (JSType.isRepresentableAsInt(key))
      return get(self, (int)key); 
    return ((ScriptObject)Global.toObject(self)).get(key);
  }
  
  private static Object get(Object self, long key) {
    CharSequence cs = JSType.toCharSequence(self);
    if (key >= 0L && key < cs.length())
      return String.valueOf(cs.charAt((int)key)); 
    return ((ScriptObject)Global.toObject(self)).get(key);
  }
  
  private static Object get(Object self, int key) {
    CharSequence cs = JSType.toCharSequence(self);
    if (key >= 0 && key < cs.length())
      return String.valueOf(cs.charAt(key)); 
    return ((ScriptObject)Global.toObject(self)).get(key);
  }
  
  public Object get(Object key) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    if (index >= 0 && index < this.value.length())
      return String.valueOf(this.value.charAt(index)); 
    return super.get(primitiveKey);
  }
  
  public Object get(double key) {
    if (JSType.isRepresentableAsInt(key))
      return get((int)key); 
    return super.get(key);
  }
  
  public Object get(int key) {
    if (key >= 0 && key < this.value.length())
      return String.valueOf(this.value.charAt(key)); 
    return super.get(key);
  }
  
  public int getInt(Object key, int programPoint) {
    return JSType.toInt32MaybeOptimistic(get(key), programPoint);
  }
  
  public int getInt(double key, int programPoint) {
    return JSType.toInt32MaybeOptimistic(get(key), programPoint);
  }
  
  public int getInt(int key, int programPoint) {
    return JSType.toInt32MaybeOptimistic(get(key), programPoint);
  }
  
  public double getDouble(Object key, int programPoint) {
    return JSType.toNumberMaybeOptimistic(get(key), programPoint);
  }
  
  public double getDouble(double key, int programPoint) {
    return JSType.toNumberMaybeOptimistic(get(key), programPoint);
  }
  
  public double getDouble(int key, int programPoint) {
    return JSType.toNumberMaybeOptimistic(get(key), programPoint);
  }
  
  public boolean has(Object key) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    return (isValidStringIndex(index) || super.has(primitiveKey));
  }
  
  public boolean has(int key) {
    return (isValidStringIndex(key) || super.has(key));
  }
  
  public boolean has(double key) {
    int index = ArrayIndex.getArrayIndex(key);
    return (isValidStringIndex(index) || super.has(key));
  }
  
  public boolean hasOwnProperty(Object key) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    return (isValidStringIndex(index) || super.hasOwnProperty(primitiveKey));
  }
  
  public boolean hasOwnProperty(int key) {
    return (isValidStringIndex(key) || super.hasOwnProperty(key));
  }
  
  public boolean hasOwnProperty(double key) {
    int index = ArrayIndex.getArrayIndex(key);
    return (isValidStringIndex(index) || super.hasOwnProperty(key));
  }
  
  public boolean delete(int key, boolean strict) {
    return (!checkDeleteIndex(key, strict) && super.delete(key, strict));
  }
  
  public boolean delete(double key, boolean strict) {
    int index = ArrayIndex.getArrayIndex(key);
    return (!checkDeleteIndex(index, strict) && super.delete(key, strict));
  }
  
  public boolean delete(Object key, boolean strict) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    return (!checkDeleteIndex(index, strict) && super.delete(primitiveKey, strict));
  }
  
  private boolean checkDeleteIndex(int index, boolean strict) {
    if (isValidStringIndex(index)) {
      if (strict)
        throw ECMAErrors.typeError("cant.delete.property", new String[] { Integer.toString(index), ScriptRuntime.safeToString(this) }); 
      return true;
    } 
    return false;
  }
  
  public Object getOwnPropertyDescriptor(Object key) {
    int index = ArrayIndex.getArrayIndex(key);
    if (index >= 0 && index < this.value.length()) {
      Global global = Global.instance();
      return global.newDataDescriptor(String.valueOf(this.value.charAt(index)), false, true, false);
    } 
    return super.getOwnPropertyDescriptor(key);
  }
  
  protected <T> T[] getOwnKeys(Class<T> type, boolean all, Set<T> nonEnumerable) {
    if (type != String.class)
      return (T[])super.getOwnKeys(type, all, nonEnumerable); 
    List<Object> keys = new ArrayList();
    for (int i = 0; i < this.value.length(); i++)
      keys.add(JSType.toString(i)); 
    keys.addAll(Arrays.asList(super.getOwnKeys(type, all, nonEnumerable)));
    return keys.toArray((T[])Array.newInstance(type, keys.size()));
  }
  
  public static Object length(Object self) {
    return Integer.valueOf(getCharSequence(self).length());
  }
  
  public static String fromCharCode(Object self, Object... args) {
    char[] buf = new char[args.length];
    int index = 0;
    for (Object arg : args)
      buf[index++] = (char)JSType.toUint16(arg); 
    return new String(buf);
  }
  
  public static Object fromCharCode(Object self, Object value) {
    if (value instanceof Integer)
      return fromCharCode(self, ((Integer)value).intValue()); 
    return Character.toString((char)JSType.toUint16(value));
  }
  
  public static String fromCharCode(Object self, int value) {
    return Character.toString((char)(value & 0xFFFF));
  }
  
  public static Object fromCharCode(Object self, int ch1, int ch2) {
    return Character.toString((char)(ch1 & 0xFFFF)) + Character.toString((char)(ch1 & 0xFFFF));
  }
  
  public static Object fromCharCode(Object self, int ch1, int ch2, int ch3) {
    return Character.toString((char)(ch1 & 0xFFFF)) + Character.toString((char)(ch1 & 0xFFFF)) + Character.toString((char)(ch2 & 0xFFFF));
  }
  
  public static String fromCharCode(Object self, int ch1, int ch2, int ch3, int ch4) {
    return Character.toString((char)(ch1 & 0xFFFF)) + Character.toString((char)(ch1 & 0xFFFF)) + Character.toString((char)(ch2 & 0xFFFF)) + Character.toString((char)(ch3 & 0xFFFF));
  }
  
  public static String fromCharCode(Object self, double value) {
    return Character.toString((char)JSType.toUint16(value));
  }
  
  public static String toString(Object self) {
    return getString(self);
  }
  
  public static String valueOf(Object self) {
    return getString(self);
  }
  
  public static String charAt(Object self, Object pos) {
    return charAtImpl(checkObjectToString(self), JSType.toInteger(pos));
  }
  
  public static String charAt(Object self, double pos) {
    return charAt(self, (int)pos);
  }
  
  public static String charAt(Object self, int pos) {
    return charAtImpl(checkObjectToString(self), pos);
  }
  
  private static String charAtImpl(String str, int pos) {
    return (pos < 0 || pos >= str.length()) ? "" : String.valueOf(str.charAt(pos));
  }
  
  private static int getValidChar(Object self, int pos) {
    try {
      return ((CharSequence)self).charAt(pos);
    } catch (IndexOutOfBoundsException e) {
      throw new ClassCastException();
    } 
  }
  
  public static double charCodeAt(Object self, Object pos) {
    String str = checkObjectToString(self);
    int idx = JSType.toInteger(pos);
    return (idx < 0 || idx >= str.length()) ? Double.NaN : str.charAt(idx);
  }
  
  public static int charCodeAt(Object self, double pos) {
    return charCodeAt(self, (int)pos);
  }
  
  public static int charCodeAt(Object self, int pos) {
    return getValidChar(self, pos);
  }
  
  public static Object concat(Object self, Object... args) {
    ConsString consString;
    CharSequence cs = checkObjectToString(self);
    if (args != null)
      for (Object obj : args)
        consString = new ConsString(cs, JSType.toCharSequence(obj));  
    return consString;
  }
  
  public static int indexOf(Object self, Object search, Object pos) {
    String str = checkObjectToString(self);
    return str.indexOf(JSType.toString(search), JSType.toInteger(pos));
  }
  
  public static int indexOf(Object self, Object search) {
    return indexOf(self, search, 0);
  }
  
  public static int indexOf(Object self, Object search, double pos) {
    return indexOf(self, search, (int)pos);
  }
  
  public static int indexOf(Object self, Object search, int pos) {
    return checkObjectToString(self).indexOf(JSType.toString(search), pos);
  }
  
  public static int lastIndexOf(Object self, Object search, Object pos) {
    int end;
    String str = checkObjectToString(self);
    String searchStr = JSType.toString(search);
    int length = str.length();
    if (pos == ScriptRuntime.UNDEFINED) {
      end = length;
    } else {
      double numPos = JSType.toNumber(pos);
      end = Double.isNaN(numPos) ? length : (int)numPos;
      if (end < 0) {
        end = 0;
      } else if (end > length) {
        end = length;
      } 
    } 
    return str.lastIndexOf(searchStr, end);
  }
  
  public static double localeCompare(Object self, Object that) {
    String str = checkObjectToString(self);
    Collator collator = Collator.getInstance((Global.getEnv())._locale);
    collator.setStrength(3);
    collator.setDecomposition(1);
    return collator.compare(str, JSType.toString(that));
  }
  
  public static ScriptObject match(Object self, Object regexp) {
    NativeRegExp nativeRegExp;
    String str = checkObjectToString(self);
    if (regexp == ScriptRuntime.UNDEFINED) {
      nativeRegExp = new NativeRegExp("");
    } else {
      nativeRegExp = Global.toRegExp(regexp);
    } 
    if (!nativeRegExp.getGlobal())
      return nativeRegExp.exec(str); 
    nativeRegExp.setLastIndex(0);
    List<Object> matches = new ArrayList();
    ScriptObject result;
    while ((result = nativeRegExp.exec(str)) != null) {
      String matchStr = JSType.toString(result.get(0));
      if (matchStr.isEmpty())
        nativeRegExp.setLastIndex(nativeRegExp.getLastIndex() + 1); 
      matches.add(matchStr);
    } 
    if (matches.isEmpty())
      return null; 
    return new NativeArray(matches.toArray());
  }
  
  public static String replace(Object self, Object string, Object replacement) throws Throwable {
    NativeRegExp nativeRegExp;
    String str = checkObjectToString(self);
    if (string instanceof NativeRegExp) {
      nativeRegExp = (NativeRegExp)string;
    } else {
      nativeRegExp = NativeRegExp.flatRegExp(JSType.toString(string));
    } 
    if (Bootstrap.isCallable(replacement))
      return nativeRegExp.replace(str, "", replacement); 
    return nativeRegExp.replace(str, JSType.toString(replacement), null);
  }
  
  public static int search(Object self, Object string) {
    String str = checkObjectToString(self);
    NativeRegExp nativeRegExp = Global.toRegExp((string == ScriptRuntime.UNDEFINED) ? "" : string);
    return nativeRegExp.search(str);
  }
  
  public static String slice(Object self, Object start, Object end) {
    String str = checkObjectToString(self);
    if (end == ScriptRuntime.UNDEFINED)
      return slice(str, JSType.toInteger(start)); 
    return slice(str, JSType.toInteger(start), JSType.toInteger(end));
  }
  
  public static String slice(Object self, int start) {
    String str = checkObjectToString(self);
    int from = (start < 0) ? Math.max(str.length() + start, 0) : Math.min(start, str.length());
    return str.substring(from);
  }
  
  public static String slice(Object self, double start) {
    return slice(self, (int)start);
  }
  
  public static String slice(Object self, int start, int end) {
    String str = checkObjectToString(self);
    int len = str.length();
    int from = (start < 0) ? Math.max(len + start, 0) : Math.min(start, len);
    int to = (end < 0) ? Math.max(len + end, 0) : Math.min(end, len);
    return str.substring(Math.min(from, to), to);
  }
  
  public static String slice(Object self, double start, double end) {
    return slice(self, (int)start, (int)end);
  }
  
  public static ScriptObject split(Object self, Object separator, Object limit) {
    String str = checkObjectToString(self);
    long lim = (limit == ScriptRuntime.UNDEFINED) ? 4294967295L : JSType.toUint32(limit);
    if (separator == ScriptRuntime.UNDEFINED)
      return (lim == 0L) ? new NativeArray() : new NativeArray(new Object[] { str }); 
    if (separator instanceof NativeRegExp)
      return ((NativeRegExp)separator).split(str, lim); 
    return splitString(str, JSType.toString(separator), lim);
  }
  
  private static ScriptObject splitString(String str, String separator, long limit) {
    if (separator.isEmpty()) {
      int length = (int)Math.min(str.length(), limit);
      Object[] array = new Object[length];
      for (int i = 0; i < length; i++)
        array[i] = String.valueOf(str.charAt(i)); 
      return new NativeArray(array);
    } 
    List<String> elements = new LinkedList<>();
    int strLength = str.length();
    int sepLength = separator.length();
    int pos = 0;
    int n = 0;
    while (pos < strLength && n < limit) {
      int found = str.indexOf(separator, pos);
      if (found == -1)
        break; 
      elements.add(str.substring(pos, found));
      n++;
      pos = found + sepLength;
    } 
    if (pos <= strLength && n < limit)
      elements.add(str.substring(pos)); 
    return new NativeArray(elements.toArray());
  }
  
  public static String substr(Object self, Object start, Object length) {
    String str = JSType.toString(self);
    int strLength = str.length();
    int intStart = JSType.toInteger(start);
    if (intStart < 0)
      intStart = Math.max(intStart + strLength, 0); 
    int intLen = Math.min(Math.max((length == ScriptRuntime.UNDEFINED) ? Integer.MAX_VALUE : JSType.toInteger(length), 0), strLength - intStart);
    return (intLen <= 0) ? "" : str.substring(intStart, intStart + intLen);
  }
  
  public static String substring(Object self, Object start, Object end) {
    String str = checkObjectToString(self);
    if (end == ScriptRuntime.UNDEFINED)
      return substring(str, JSType.toInteger(start)); 
    return substring(str, JSType.toInteger(start), JSType.toInteger(end));
  }
  
  public static String substring(Object self, int start) {
    String str = checkObjectToString(self);
    if (start < 0)
      return str; 
    if (start >= str.length())
      return ""; 
    return str.substring(start);
  }
  
  public static String substring(Object self, double start) {
    return substring(self, (int)start);
  }
  
  public static String substring(Object self, int start, int end) {
    String str = checkObjectToString(self);
    int len = str.length();
    int validStart = (start < 0) ? 0 : Math.min(start, len);
    int validEnd = (end < 0) ? 0 : Math.min(end, len);
    if (validStart < validEnd)
      return str.substring(validStart, validEnd); 
    return str.substring(validEnd, validStart);
  }
  
  public static String substring(Object self, double start, double end) {
    return substring(self, (int)start, (int)end);
  }
  
  public static String toLowerCase(Object self) {
    return checkObjectToString(self).toLowerCase(Locale.ROOT);
  }
  
  public static String toLocaleLowerCase(Object self) {
    return checkObjectToString(self).toLowerCase((Global.getEnv())._locale);
  }
  
  public static String toUpperCase(Object self) {
    return checkObjectToString(self).toUpperCase(Locale.ROOT);
  }
  
  public static String toLocaleUpperCase(Object self) {
    return checkObjectToString(self).toUpperCase((Global.getEnv())._locale);
  }
  
  public static String trim(Object self) {
    String str = checkObjectToString(self);
    int start = 0;
    int end = str.length() - 1;
    while (start <= end && ScriptRuntime.isJSWhitespace(str.charAt(start)))
      start++; 
    while (end > start && ScriptRuntime.isJSWhitespace(str.charAt(end)))
      end--; 
    return str.substring(start, end + 1);
  }
  
  public static String trimLeft(Object self) {
    String str = checkObjectToString(self);
    int start = 0;
    int end = str.length() - 1;
    while (start <= end && ScriptRuntime.isJSWhitespace(str.charAt(start)))
      start++; 
    return str.substring(start, end + 1);
  }
  
  public static String trimRight(Object self) {
    String str = checkObjectToString(self);
    int start = 0;
    int end = str.length() - 1;
    while (end >= 0 && ScriptRuntime.isJSWhitespace(str.charAt(end)))
      end--; 
    return str.substring(0, end + 1);
  }
  
  private static ScriptObject newObj(CharSequence str) {
    return new NativeString(str);
  }
  
  public static Object constructor(boolean newObj, Object self, Object... args) {
    CharSequence str = (args.length > 0) ? JSType.toCharSequence(args[0]) : "";
    return newObj ? newObj(str) : str.toString();
  }
  
  public static Object constructor(boolean newObj, Object self) {
    return newObj ? newObj("") : "";
  }
  
  public static Object constructor(boolean newObj, Object self, Object arg) {
    CharSequence str = JSType.toCharSequence(arg);
    return newObj ? newObj(str) : str.toString();
  }
  
  public static Object constructor(boolean newObj, Object self, int arg) {
    String str = Integer.toString(arg);
    return newObj ? newObj(str) : str;
  }
  
  public static Object constructor(boolean newObj, Object self, double arg) {
    String str = JSType.toString(arg);
    return newObj ? newObj(str) : str;
  }
  
  public static Object constructor(boolean newObj, Object self, boolean arg) {
    String str = Boolean.toString(arg);
    return newObj ? newObj(str) : str;
  }
  
  public static Object getIterator(Object self) {
    return new StringIterator(checkObjectToString(self), Global.instance());
  }
  
  public static GuardedInvocation lookupPrimitive(LinkRequest request, Object receiver) {
    return PrimitiveLookup.lookupPrimitive(request, NashornGuards.getStringGuard(), new NativeString((CharSequence)receiver), WRAPFILTER, PROTOFILTER);
  }
  
  private static NativeString wrapFilter(Object receiver) {
    return new NativeString((CharSequence)receiver);
  }
  
  private static Object protoFilter(Object object) {
    return Global.instance().getStringPrototype();
  }
  
  private static CharSequence getCharSequence(Object self) {
    if (JSType.isString(self))
      return (CharSequence)self; 
    if (self instanceof NativeString)
      return ((NativeString)self).getValue(); 
    if (self != null && self == Global.instance().getStringPrototype())
      return ""; 
    throw ECMAErrors.typeError("not.a.string", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  private static String getString(Object self) {
    if (self instanceof String)
      return (String)self; 
    if (self instanceof ConsString)
      return self.toString(); 
    if (self instanceof NativeString)
      return ((NativeString)self).getStringValue(); 
    if (self != null && self == Global.instance().getStringPrototype())
      return ""; 
    throw ECMAErrors.typeError("not.a.string", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  private static String checkObjectToString(Object self) {
    if (self instanceof String)
      return (String)self; 
    if (self instanceof ConsString)
      return self.toString(); 
    Global.checkObjectCoercible(self);
    return JSType.toString(self);
  }
  
  private boolean isValidStringIndex(int key) {
    return (key >= 0 && key < this.value.length());
  }
  
  private static MethodHandle findOwnMH(String name, MethodType type) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), NativeString.class, name, type);
  }
  
  public SpecializedFunction.LinkLogic getLinkLogic(Class<? extends SpecializedFunction.LinkLogic> clazz) {
    if (clazz == CharCodeAtLinkLogic.class)
      return CharCodeAtLinkLogic.INSTANCE; 
    return null;
  }
  
  public boolean hasPerInstanceAssumptions() {
    return false;
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc 'length'
    //   11: bipush #7
    //   13: ldc_w
    //   16: aconst_null
    //   17: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   20: invokeinterface add : (Ljava/lang/Object;)Z
    //   25: pop
    //   26: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   29: putstatic org/openjdk/nashorn/internal/objects/NativeString.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   32: return
  }
  
  private static final class CharCodeAtLinkLogic extends SpecializedFunction.LinkLogic {
    private static final CharCodeAtLinkLogic INSTANCE = new CharCodeAtLinkLogic();
    
    public boolean canLink(Object self, CallSiteDescriptor desc, LinkRequest request) {
      try {
        CharSequence cs = (CharSequence)self;
        int intIndex = JSType.toInteger(request.getArguments()[2]);
        return (intIndex >= 0 && intIndex < cs.length());
      } catch (ClassCastException|IndexOutOfBoundsException classCastException) {
        return false;
      } 
    }
    
    public Class<? extends Throwable> getRelinkException() {
      return (Class)ClassCastException.class;
    }
  }
}
