package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.util.List;
import jdk.dynalink.SecureLookupSupplier;
import jdk.dynalink.beans.StaticClass;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.parser.Lexer;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.openjdk.nashorn.internal.runtime.doubleconv.DoubleConversion;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;

public enum JSType {
  UNDEFINED("undefined"),
  NULL("object"),
  BOOLEAN("boolean"),
  NUMBER("number"),
  STRING("string"),
  OBJECT("object"),
  FUNCTION("function"),
  SYMBOL("symbol");
  
  private final String typeName;
  
  public static final long MAX_UINT = 4294967295L;
  
  private static final MethodHandles.Lookup JSTYPE_LOOKUP;
  
  public static final CompilerConstants.Call TO_BOOLEAN;
  
  public static final CompilerConstants.Call TO_BOOLEAN_D;
  
  public static final CompilerConstants.Call TO_BOOLEAN_I;
  
  public static final CompilerConstants.Call TO_INTEGER;
  
  public static final CompilerConstants.Call TO_LONG;
  
  public static final CompilerConstants.Call TO_LONG_D;
  
  public static final CompilerConstants.Call TO_NUMBER;
  
  public static final CompilerConstants.Call TO_NUMBER_OPTIMISTIC;
  
  public static final CompilerConstants.Call TO_STRING;
  
  public static final CompilerConstants.Call TO_INT32;
  
  public static final CompilerConstants.Call TO_INT32_L;
  
  public static final CompilerConstants.Call TO_INT32_OPTIMISTIC;
  
  public static final CompilerConstants.Call TO_INT32_D;
  
  public static final CompilerConstants.Call TO_UINT32_OPTIMISTIC;
  
  public static final CompilerConstants.Call TO_UINT32_DOUBLE;
  
  public static final CompilerConstants.Call TO_UINT32;
  
  public static final CompilerConstants.Call TO_UINT32_D;
  
  public static final CompilerConstants.Call TO_STRING_D;
  
  public static final CompilerConstants.Call TO_PRIMITIVE_TO_STRING;
  
  public static final CompilerConstants.Call TO_PRIMITIVE_TO_CHARSEQUENCE;
  
  public static final CompilerConstants.Call THROW_UNWARRANTED;
  
  public static final CompilerConstants.Call ADD_EXACT;
  
  public static final CompilerConstants.Call SUB_EXACT;
  
  public static final CompilerConstants.Call MUL_EXACT;
  
  public static final CompilerConstants.Call DIV_EXACT;
  
  public static final CompilerConstants.Call DIV_ZERO;
  
  public static final CompilerConstants.Call REM_ZERO;
  
  public static final CompilerConstants.Call REM_EXACT;
  
  public static final CompilerConstants.Call DECREMENT_EXACT;
  
  public static final CompilerConstants.Call INCREMENT_EXACT;
  
  public static final CompilerConstants.Call NEGATE_EXACT;
  
  public static final CompilerConstants.Call TO_JAVA_ARRAY;
  
  public static final CompilerConstants.Call TO_JAVA_ARRAY_WITH_LOOKUP;
  
  public static final CompilerConstants.Call VOID_RETURN;
  
  public static final CompilerConstants.Call IS_STRING;
  
  public static final CompilerConstants.Call IS_NUMBER;
  
  private static final List<Type> ACCESSOR_TYPES;
  
  public static final int TYPE_UNDEFINED_INDEX = -1;
  
  public static final int TYPE_INT_INDEX = 0;
  
  public static final int TYPE_DOUBLE_INDEX = 1;
  
  public static final int TYPE_OBJECT_INDEX = 2;
  
  public static final List<MethodHandle> CONVERT_OBJECT;
  
  public static final List<MethodHandle> CONVERT_OBJECT_OPTIMISTIC;
  
  public static final int UNDEFINED_INT = 0;
  
  public static final long UNDEFINED_LONG = 0L;
  
  public static final double UNDEFINED_DOUBLE = NaND;
  
  private static final long MAX_PRECISE_DOUBLE = 9007199254740992L;
  
  private static final long MIN_PRECISE_DOUBLE = -9007199254740992L;
  
  public static final List<MethodHandle> GET_UNDEFINED;
  
  private static final double INT32_LIMIT = 4.294967296E9D;
  
  static {
    JSTYPE_LOOKUP = MethodHandles.lookup();
    TO_BOOLEAN = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toBoolean", boolean.class, new Class[] { Object.class });
    TO_BOOLEAN_D = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toBoolean", boolean.class, new Class[] { double.class });
    TO_BOOLEAN_I = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toBoolean", boolean.class, new Class[] { int.class });
    TO_INTEGER = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toInteger", int.class, new Class[] { Object.class });
    TO_LONG = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toLong", long.class, new Class[] { Object.class });
    TO_LONG_D = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toLong", long.class, new Class[] { double.class });
    TO_NUMBER = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toNumber", double.class, new Class[] { Object.class });
    TO_NUMBER_OPTIMISTIC = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toNumberOptimistic", double.class, new Class[] { Object.class, int.class });
    TO_STRING = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toString", String.class, new Class[] { Object.class });
    TO_INT32 = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toInt32", int.class, new Class[] { Object.class });
    TO_INT32_L = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toInt32", int.class, new Class[] { long.class });
    TO_INT32_OPTIMISTIC = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toInt32Optimistic", int.class, new Class[] { Object.class, int.class });
    TO_INT32_D = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toInt32", int.class, new Class[] { double.class });
    TO_UINT32_OPTIMISTIC = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toUint32Optimistic", int.class, new Class[] { int.class, int.class });
    TO_UINT32_DOUBLE = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toUint32Double", double.class, new Class[] { int.class });
    TO_UINT32 = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toUint32", long.class, new Class[] { Object.class });
    TO_UINT32_D = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toUint32", long.class, new Class[] { double.class });
    TO_STRING_D = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toString", String.class, new Class[] { double.class });
    TO_PRIMITIVE_TO_STRING = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toPrimitiveToString", String.class, new Class[] { Object.class });
    TO_PRIMITIVE_TO_CHARSEQUENCE = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toPrimitiveToCharSequence", CharSequence.class, new Class[] { Object.class });
    THROW_UNWARRANTED = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "throwUnwarrantedOptimismException", Object.class, new Class[] { Object.class, int.class });
    ADD_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "addExact", int.class, new Class[] { int.class, int.class, int.class });
    SUB_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "subExact", int.class, new Class[] { int.class, int.class, int.class });
    MUL_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "mulExact", int.class, new Class[] { int.class, int.class, int.class });
    DIV_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "divExact", int.class, new Class[] { int.class, int.class, int.class });
    DIV_ZERO = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "divZero", int.class, new Class[] { int.class, int.class });
    REM_ZERO = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "remZero", int.class, new Class[] { int.class, int.class });
    REM_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "remExact", int.class, new Class[] { int.class, int.class, int.class });
    DECREMENT_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "decrementExact", int.class, new Class[] { int.class, int.class });
    INCREMENT_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "incrementExact", int.class, new Class[] { int.class, int.class });
    NEGATE_EXACT = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "negateExact", int.class, new Class[] { int.class, int.class });
    TO_JAVA_ARRAY = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toJavaArray", Object.class, new Class[] { Object.class, Class.class });
    TO_JAVA_ARRAY_WITH_LOOKUP = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "toJavaArrayWithLookup", Object.class, new Class[] { Object.class, Class.class, SecureLookupSupplier.class });
    VOID_RETURN = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "voidReturn", void.class, new Class[0]);
    IS_STRING = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "isString", boolean.class, new Class[] { Object.class });
    IS_NUMBER = CompilerConstants.staticCall(JSTYPE_LOOKUP, JSType.class, "isNumber", boolean.class, new Class[] { Object.class });
    ACCESSOR_TYPES = List.of(Type.INT, Type.NUMBER, Type.OBJECT);
    CONVERT_OBJECT = List.of(TO_INT32
        .methodHandle(), TO_NUMBER
        .methodHandle());
    CONVERT_OBJECT_OPTIMISTIC = List.of(TO_INT32_OPTIMISTIC
        .methodHandle(), TO_NUMBER_OPTIMISTIC
        .methodHandle());
    GET_UNDEFINED = List.of(Lookup.MH
        .constant(int.class, Integer.valueOf(0)), Lookup.MH
        .constant(double.class, Double.valueOf(Double.NaN)), Lookup.MH
        .constant(Object.class, Undefined.getUndefined()));
  }
  
  JSType(String typeName) {
    this.typeName = typeName;
  }
  
  public final String typeName() {
    return this.typeName;
  }
  
  public static JSType of(Object obj) {
    if (obj == null)
      return NULL; 
    if (obj instanceof ScriptObject)
      return (obj instanceof ScriptFunction) ? FUNCTION : OBJECT; 
    if (obj instanceof Boolean)
      return BOOLEAN; 
    if (isString(obj))
      return STRING; 
    if (isNumber(obj))
      return NUMBER; 
    if (obj instanceof Symbol)
      return SYMBOL; 
    if (obj == ScriptRuntime.UNDEFINED)
      return UNDEFINED; 
    return Bootstrap.isCallable(obj) ? FUNCTION : OBJECT;
  }
  
  public static JSType ofNoFunction(Object obj) {
    if (obj == null)
      return NULL; 
    if (obj instanceof ScriptObject)
      return OBJECT; 
    if (obj instanceof Boolean)
      return BOOLEAN; 
    if (isString(obj))
      return STRING; 
    if (isNumber(obj))
      return NUMBER; 
    if (obj == ScriptRuntime.UNDEFINED)
      return UNDEFINED; 
    if (obj instanceof Symbol)
      return SYMBOL; 
    return OBJECT;
  }
  
  public static void voidReturn() {}
  
  public static boolean isRepresentableAsInt(long number) {
    return ((int)number == number);
  }
  
  public static boolean isRepresentableAsInt(double number) {
    return ((int)number == number);
  }
  
  public static boolean isStrictlyRepresentableAsInt(double number) {
    return (isRepresentableAsInt(number) && isNotNegativeZero(number));
  }
  
  public static boolean isRepresentableAsInt(Object obj) {
    if (obj instanceof Number)
      return isRepresentableAsInt(((Number)obj).doubleValue()); 
    return false;
  }
  
  public static boolean isRepresentableAsLong(double number) {
    return ((long)number == number);
  }
  
  public static boolean isRepresentableAsDouble(long number) {
    return (9007199254740992L >= number && number >= -9007199254740992L);
  }
  
  private static boolean isNotNegativeZero(double number) {
    return (Double.doubleToRawLongBits(number) != Long.MIN_VALUE);
  }
  
  public static boolean isPrimitive(Object obj) {
    return (obj == null || obj == ScriptRuntime.UNDEFINED || 
      
      isString(obj) || 
      isNumber(obj) || obj instanceof Boolean || obj instanceof Symbol);
  }
  
  public static Object toPrimitive(Object obj) {
    return toPrimitive(obj, (Class<?>)null);
  }
  
  public static Object toPrimitive(Object obj, Class<?> hint) {
    if (obj instanceof ScriptObject)
      return toPrimitive((ScriptObject)obj, hint); 
    if (isPrimitive(obj))
      return obj; 
    if (hint == Number.class && obj instanceof Number)
      return Double.valueOf(((Number)obj).doubleValue()); 
    if (obj instanceof JSObject)
      return toPrimitive((JSObject)obj, hint); 
    if (obj instanceof StaticClass) {
      String name = ((StaticClass)obj).getRepresentedClass().getName();
      return "[JavaClass " + name + "]";
    } 
    return obj.toString();
  }
  
  private static Object toPrimitive(ScriptObject sobj, Class<?> hint) {
    return requirePrimitive(sobj.getDefaultValue(hint));
  }
  
  private static Object requirePrimitive(Object result) {
    if (!isPrimitive(result))
      throw ECMAErrors.typeError("bad.default.value", new String[] { result.toString() }); 
    return result;
  }
  
  public static Object toPrimitive(JSObject jsobj, Class<?> hint) {
    try {
      return requirePrimitive(jsobj.getDefaultValue(hint));
    } catch (UnsupportedOperationException e) {
      throw new ECMAException(Context.getGlobal().newTypeError(e.getMessage()), e);
    } 
  }
  
  public static String toPrimitiveToString(Object obj) {
    return toString(toPrimitive(obj));
  }
  
  public static CharSequence toPrimitiveToCharSequence(Object obj) {
    return toCharSequence(toPrimitive(obj));
  }
  
  public static boolean toBoolean(double num) {
    return (num != 0.0D && !Double.isNaN(num));
  }
  
  public static boolean toBoolean(int num) {
    return (num != 0);
  }
  
  public static boolean toBoolean(Object obj) {
    if (obj instanceof Boolean)
      return ((Boolean)obj).booleanValue(); 
    if (nullOrUndefined(obj))
      return false; 
    if (obj instanceof Number) {
      double num = ((Number)obj).doubleValue();
      return (num != 0.0D && !Double.isNaN(num));
    } 
    if (isString(obj))
      return (((CharSequence)obj).length() > 0); 
    return true;
  }
  
  public static String toString(Object obj) {
    return toStringImpl(obj, false);
  }
  
  public static Object toPropertyKey(Object obj) {
    return (obj instanceof Symbol) ? obj : toStringImpl(obj, false);
  }
  
  public static CharSequence toCharSequence(Object obj) {
    if (obj instanceof ConsString)
      return (CharSequence)obj; 
    return toString(obj);
  }
  
  public static boolean isString(Object obj) {
    return (obj instanceof String || obj instanceof ConsString);
  }
  
  public static boolean isNumber(Object obj) {
    if (obj != null) {
      Class<?> c = obj.getClass();
      return (c == Integer.class || c == Double.class || c == Float.class || c == Short.class || c == Byte.class);
    } 
    return false;
  }
  
  public static String toString(int num) {
    return Integer.toString(num);
  }
  
  public static String toString(double num) {
    if (isRepresentableAsInt(num))
      return Integer.toString((int)num); 
    if (num == Double.POSITIVE_INFINITY)
      return "Infinity"; 
    if (num == Double.NEGATIVE_INFINITY)
      return "-Infinity"; 
    if (Double.isNaN(num))
      return "NaN"; 
    return DoubleConversion.toShortestString(num);
  }
  
  public static String toString(double num, int radix) {
    assert radix >= 2 && radix <= 36 : "invalid radix";
    if (isRepresentableAsInt(num))
      return Integer.toString((int)num, radix); 
    if (num == Double.POSITIVE_INFINITY)
      return "Infinity"; 
    if (num == Double.NEGATIVE_INFINITY)
      return "-Infinity"; 
    if (Double.isNaN(num))
      return "NaN"; 
    if (num == 0.0D)
      return "0"; 
    String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
    StringBuilder sb = new StringBuilder();
    boolean negative = (num < 0.0D);
    double signedNum = negative ? -num : num;
    double intPart = Math.floor(signedNum);
    double decPart = signedNum - intPart;
    do {
      double remainder = intPart % radix;
      sb.append("0123456789abcdefghijklmnopqrstuvwxyz".charAt((int)remainder));
      intPart -= remainder;
      intPart /= radix;
    } while (intPart >= 1.0D);
    if (negative)
      sb.append('-'); 
    sb.reverse();
    if (decPart > 0.0D) {
      int dot = sb.length();
      sb.append('.');
      do {
        decPart *= radix;
        double d = Math.floor(decPart);
        sb.append("0123456789abcdefghijklmnopqrstuvwxyz".charAt((int)d));
        decPart -= d;
      } while (decPart > 0.0D && sb.length() - dot < 1100);
    } 
    return sb.toString();
  }
  
  public static double toNumber(Object obj) {
    if (obj instanceof Double)
      return ((Double)obj).doubleValue(); 
    if (obj instanceof Number)
      return ((Number)obj).doubleValue(); 
    return toNumberGeneric(obj);
  }
  
  public static double toNumberForEq(Object obj) {
    return (obj == null || obj instanceof Symbol || obj instanceof org.openjdk.nashorn.internal.objects.NativeSymbol) ? Double.NaN : toNumber(obj);
  }
  
  public static double toNumberForStrictEq(Object obj) {
    if (obj instanceof Double)
      return ((Double)obj).doubleValue(); 
    if (isNumber(obj))
      return ((Number)obj).doubleValue(); 
    return Double.NaN;
  }
  
  public static Number toNarrowestNumber(long l) {
    if (isRepresentableAsInt(l))
      return Integer.valueOf((int)l); 
    return Double.valueOf(l);
  }
  
  public static double toNumber(Boolean b) {
    return b.booleanValue() ? 1.0D : 0.0D;
  }
  
  public static double toNumber(ScriptObject obj) {
    return toNumber(toPrimitive(obj, Number.class));
  }
  
  public static double toNumberOptimistic(Object obj, int programPoint) {
    if (obj != null) {
      Class<?> clz = obj.getClass();
      if (clz == Double.class || clz == Integer.class || clz == Long.class)
        return ((Number)obj).doubleValue(); 
    } 
    throw new UnwarrantedOptimismException(obj, programPoint);
  }
  
  public static double toNumberMaybeOptimistic(Object obj, int programPoint) {
    return UnwarrantedOptimismException.isValid(programPoint) ? toNumberOptimistic(obj, programPoint) : toNumber(obj);
  }
  
  public static int digit(char ch, int radix) {
    return digit(ch, radix, false);
  }
  
  public static int digit(char ch, int radix, boolean onlyIsoLatin1) {
    char maxInRadix = (char)(97 + radix - 1 - 10);
    char c = Character.toLowerCase(ch);
    if (c >= 'a' && c <= maxInRadix)
      return Character.digit(ch, radix); 
    if (Character.isDigit(ch) && (
      !onlyIsoLatin1 || (ch >= '0' && ch <= '9')))
      return Character.digit(ch, radix); 
    return -1;
  }
  
  public static double toNumber(String str) {
    boolean negative;
    double value;
    int end = str.length();
    if (end == 0)
      return 0.0D; 
    int start = 0;
    char f = str.charAt(0);
    while (Lexer.isJSWhitespace(f)) {
      if (++start == end)
        return 0.0D; 
      f = str.charAt(start);
    } 
    while (Lexer.isJSWhitespace(str.charAt(end - 1)))
      end--; 
    if (f == '-') {
      if (++start == end)
        return Double.NaN; 
      f = str.charAt(start);
      negative = true;
    } else {
      if (f == '+') {
        if (++start == end)
          return Double.NaN; 
        f = str.charAt(start);
      } 
      negative = false;
    } 
    if (start + 1 < end && f == '0' && Character.toLowerCase(str.charAt(start + 1)) == 'x') {
      value = parseRadix(str.toCharArray(), start + 2, end, 16);
    } else {
      if (f == 'I' && end - start == 8 && str.regionMatches(start, "Infinity", 0, 8))
        return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY; 
      for (int i = start; i < end; i++) {
        f = str.charAt(i);
        if ((f < '0' || f > '9') && f != '.' && f != 'e' && f != 'E' && f != '+' && f != '-')
          return Double.NaN; 
      } 
      try {
        value = Double.parseDouble(str.substring(start, end));
      } catch (NumberFormatException e) {
        return Double.NaN;
      } 
    } 
    return negative ? -value : value;
  }
  
  public static int toInteger(Object obj) {
    return (int)toNumber(obj);
  }
  
  public static long toLong(Object obj) {
    return (obj instanceof Long) ? ((Long)obj).longValue() : toLong(toNumber(obj));
  }
  
  public static long toLong(double num) {
    return (long)num;
  }
  
  public static int toInt32(Object obj) {
    return toInt32(toNumber(obj));
  }
  
  public static int toInt32Optimistic(Object obj, int programPoint) {
    if (obj != null && obj.getClass() == Integer.class)
      return ((Integer)obj).intValue(); 
    throw new UnwarrantedOptimismException(obj, programPoint);
  }
  
  public static int toInt32MaybeOptimistic(Object obj, int programPoint) {
    return UnwarrantedOptimismException.isValid(programPoint) ? toInt32Optimistic(obj, programPoint) : toInt32(obj);
  }
  
  public static int toInt32(long num) {
    return (int)((num >= -9007199254740992L && num <= 9007199254740992L) ? num : (long)(num % 4.294967296E9D));
  }
  
  public static int toInt32(double num) {
    return (int)doubleToInt32(num);
  }
  
  public static long toUint32(Object obj) {
    return toUint32(toNumber(obj));
  }
  
  public static long toUint32(double num) {
    return doubleToInt32(num) & 0xFFFFFFFFL;
  }
  
  public static long toUint32(int num) {
    return num & 0xFFFFFFFFL;
  }
  
  public static int toUint32Optimistic(int num, int pp) {
    if (num >= 0)
      return num; 
    throw new UnwarrantedOptimismException(Double.valueOf(toUint32Double(num)), pp, Type.NUMBER);
  }
  
  public static double toUint32Double(int num) {
    return toUint32(num);
  }
  
  public static int toUint16(Object obj) {
    return toUint16(toNumber(obj));
  }
  
  public static int toUint16(int num) {
    return num & 0xFFFF;
  }
  
  public static int toUint16(long num) {
    return (int)num & 0xFFFF;
  }
  
  public static int toUint16(double num) {
    return (int)doubleToInt32(num) & 0xFFFF;
  }
  
  private static long doubleToInt32(double num) {
    int exponent = Math.getExponent(num);
    if (exponent < 31)
      return (long)num; 
    if (exponent >= 84)
      return 0L; 
    double d = (num >= 0.0D) ? Math.floor(num) : Math.ceil(num);
    return (long)(d % 4.294967296E9D);
  }
  
  public static boolean isFinite(double num) {
    return (!Double.isInfinite(num) && !Double.isNaN(num));
  }
  
  public static Double toDouble(double num) {
    return Double.valueOf(num);
  }
  
  public static Double toDouble(long num) {
    return Double.valueOf(num);
  }
  
  public static Double toDouble(int num) {
    return Double.valueOf(num);
  }
  
  public static Object toObject(boolean bool) {
    return Boolean.valueOf(bool);
  }
  
  public static Object toObject(int num) {
    return Integer.valueOf(num);
  }
  
  public static Object toObject(long num) {
    return Long.valueOf(num);
  }
  
  public static Object toObject(double num) {
    return Double.valueOf(num);
  }
  
  public static Object toObject(Object obj) {
    return obj;
  }
  
  public static Object toScriptObject(Object obj) {
    return toScriptObject(Context.getGlobal(), obj);
  }
  
  public static Object toScriptObject(Global global, Object obj) {
    if (nullOrUndefined(obj))
      throw ECMAErrors.typeError(global, "not.an.object", new String[] { ScriptRuntime.safeToString(obj) }); 
    if (obj instanceof ScriptObject)
      return obj; 
    return global.wrapAsObject(obj);
  }
  
  public static Object toJavaArray(Object obj, Class<?> componentType) {
    if (obj instanceof ScriptObject)
      return ((ScriptObject)obj).getArray().asArrayOfType(componentType); 
    if (obj instanceof JSObject) {
      ArrayLikeIterator<?> itr = ArrayLikeIterator.arrayLikeIterator(obj);
      int len = (int)itr.getLength();
      Object[] res = new Object[len];
      int idx = 0;
      while (itr.hasNext())
        res[idx++] = itr.next(); 
      return convertArray(res, componentType);
    } 
    if (obj == null)
      return null; 
    throw new IllegalArgumentException("not a script object");
  }
  
  public static Object toJavaArrayWithLookup(Object obj, Class<?> componentType, SecureLookupSupplier lookupSupplier) {
    return Bootstrap.getLinkerServices().getWithLookup(() -> toJavaArray(obj, componentType), lookupSupplier);
  }
  
  public static Object convertArray(Object[] src, Class<?> componentType) {
    if (componentType == Object.class)
      for (int i = 0; i < src.length; i++) {
        Object e = src[i];
        if (e instanceof ConsString)
          src[i] = e.toString(); 
      }  
    int l = src.length;
    Object dst = Array.newInstance(componentType, l);
    MethodHandle converter = Bootstrap.getLinkerServices().getTypeConverter(Object.class, componentType);
    try {
      for (int i = 0; i < src.length; i++)
        Array.set(dst, i, invoke(converter, src[i])); 
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
    return dst;
  }
  
  public static boolean nullOrUndefined(Object obj) {
    return (obj == null || obj == ScriptRuntime.UNDEFINED);
  }
  
  static String toStringImpl(Object obj, boolean safe) {
    if (obj instanceof String)
      return (String)obj; 
    if (obj instanceof ConsString)
      return obj.toString(); 
    if (isNumber(obj))
      return toString(((Number)obj).doubleValue()); 
    if (obj == ScriptRuntime.UNDEFINED)
      return "undefined"; 
    if (obj == null)
      return "null"; 
    if (obj instanceof Boolean)
      return obj.toString(); 
    if (obj instanceof Symbol) {
      if (safe)
        return obj.toString(); 
      throw ECMAErrors.typeError("symbol.to.string", new String[0]);
    } 
    if (safe && obj instanceof ScriptObject) {
      ScriptObject sobj = (ScriptObject)obj;
      Global gobj = Context.getGlobal();
      return gobj.isError(sobj) ? 
        ECMAException.safeToString(sobj) : 
        sobj.safeToString();
    } 
    return toString(toPrimitive(obj, String.class));
  }
  
  static String trimLeft(String str) {
    int start = 0;
    while (start < str.length() && Lexer.isJSWhitespace(str.charAt(start)))
      start++; 
    return str.substring(start);
  }
  
  private static Object throwUnwarrantedOptimismException(Object value, int programPoint) {
    throw new UnwarrantedOptimismException(value, programPoint);
  }
  
  public static int addExact(int x, int y, int programPoint) throws UnwarrantedOptimismException {
    try {
      return Math.addExact(x, y);
    } catch (ArithmeticException e) {
      throw new UnwarrantedOptimismException(Double.valueOf(x + y), programPoint);
    } 
  }
  
  public static int subExact(int x, int y, int programPoint) throws UnwarrantedOptimismException {
    try {
      return Math.subtractExact(x, y);
    } catch (ArithmeticException e) {
      throw new UnwarrantedOptimismException(Double.valueOf(x - y), programPoint);
    } 
  }
  
  public static int mulExact(int x, int y, int programPoint) throws UnwarrantedOptimismException {
    try {
      return Math.multiplyExact(x, y);
    } catch (ArithmeticException e) {
      throw new UnwarrantedOptimismException(Double.valueOf(x * y), programPoint);
    } 
  }
  
  public static int divExact(int x, int y, int programPoint) throws UnwarrantedOptimismException {
    int res;
    try {
      res = x / y;
    } catch (ArithmeticException e) {
      assert y == 0;
      throw new UnwarrantedOptimismException(Double.valueOf((x > 0) ? Double.POSITIVE_INFINITY : ((x < 0) ? Double.NEGATIVE_INFINITY : Double.NaN)), programPoint);
    } 
    int rem = x % y;
    if (rem == 0)
      return res; 
    throw new UnwarrantedOptimismException(Double.valueOf(x / y), programPoint);
  }
  
  public static int divZero(int x, int y) {
    return (y == 0) ? 0 : (x / y);
  }
  
  public static int remZero(int x, int y) {
    return (y == 0) ? 0 : (x % y);
  }
  
  public static int remExact(int x, int y, int programPoint) throws UnwarrantedOptimismException {
    try {
      return x % y;
    } catch (ArithmeticException e) {
      assert y == 0;
      throw new UnwarrantedOptimismException(Double.valueOf(Double.NaN), programPoint);
    } 
  }
  
  public static int decrementExact(int x, int programPoint) throws UnwarrantedOptimismException {
    try {
      return Math.decrementExact(x);
    } catch (ArithmeticException e) {
      throw new UnwarrantedOptimismException(Double.valueOf(x - 1.0D), programPoint);
    } 
  }
  
  public static int incrementExact(int x, int programPoint) throws UnwarrantedOptimismException {
    try {
      return Math.incrementExact(x);
    } catch (ArithmeticException e) {
      throw new UnwarrantedOptimismException(Double.valueOf(x + 1.0D), programPoint);
    } 
  }
  
  public static int negateExact(int x, int programPoint) throws UnwarrantedOptimismException {
    try {
      if (x == 0)
        throw new UnwarrantedOptimismException(Double.valueOf(-0.0D), programPoint); 
      return Math.negateExact(x);
    } catch (ArithmeticException e) {
      throw new UnwarrantedOptimismException(Double.valueOf(-(x)), programPoint);
    } 
  }
  
  public static int getAccessorTypeIndex(Type type) {
    return getAccessorTypeIndex(type.getTypeClass());
  }
  
  public static int getAccessorTypeIndex(Class<?> type) {
    if (type == null)
      return -1; 
    if (type == int.class)
      return 0; 
    if (type == double.class)
      return 1; 
    if (!type.isPrimitive())
      return 2; 
    return -1;
  }
  
  public static Type getAccessorType(int index) {
    return ACCESSOR_TYPES.get(index);
  }
  
  public static int getNumberOfAccessorTypes() {
    return ACCESSOR_TYPES.size();
  }
  
  private static double parseRadix(char[] chars, int start, int length, int radix) {
    int pos = 0;
    for (int i = start; i < length; i++) {
      if (digit(chars[i], radix) == -1)
        return Double.NaN; 
      pos++;
    } 
    if (pos == 0)
      return Double.NaN; 
    double value = 0.0D;
    for (int j = start; j < start + pos; j++) {
      value *= radix;
      value += digit(chars[j], radix);
    } 
    return value;
  }
  
  private static double toNumberGeneric(Object obj) {
    if (obj == null)
      return 0.0D; 
    if (obj instanceof String)
      return toNumber((String)obj); 
    if (obj instanceof ConsString)
      return toNumber(obj.toString()); 
    if (obj instanceof Boolean)
      return toNumber((Boolean)obj); 
    if (obj instanceof ScriptObject)
      return toNumber((ScriptObject)obj); 
    if (obj instanceof Undefined)
      return Double.NaN; 
    if (obj instanceof Symbol)
      throw ECMAErrors.typeError("symbol.to.number", new String[0]); 
    return toNumber(toPrimitive(obj, Number.class));
  }
  
  private static Object invoke(MethodHandle mh, Object arg) {
    try {
      return mh.invoke(arg);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public static MethodHandle unboxConstant(Object o) {
    if (o != null) {
      if (o.getClass() == Integer.class)
        return Lookup.MH.constant(int.class, o); 
      if (o.getClass() == Double.class)
        return Lookup.MH.constant(double.class, o); 
    } 
    return Lookup.MH.constant(Object.class, o);
  }
  
  public static Class<?> unboxedFieldType(Object o) {
    if (o == null)
      return Object.class; 
    if (o.getClass() == Integer.class)
      return int.class; 
    if (o.getClass() == Double.class)
      return double.class; 
    return Object.class;
  }
}
