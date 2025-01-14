package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import java.util.Locale;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.doubleconv.DoubleConversion;
import org.openjdk.nashorn.internal.runtime.linker.NashornGuards;
import org.openjdk.nashorn.internal.runtime.linker.PrimitiveLookup;

public final class NativeNumber extends ScriptObject {
  static final MethodHandle WRAPFILTER = findOwnMH("wrapFilter", Lookup.MH.type(NativeNumber.class, new Class[] { Object.class }));
  
  private static final MethodHandle PROTOFILTER = findOwnMH("protoFilter", Lookup.MH.type(Object.class, new Class[] { Object.class }));
  
  public static final double MAX_VALUE = 1.7976931348623157E308D;
  
  public static final double MIN_VALUE = 4.9E-324D;
  
  public static final double NaN = NaND;
  
  public static final double NEGATIVE_INFINITY = -InfinityD;
  
  public static final double POSITIVE_INFINITY = InfinityD;
  
  private final double value;
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private NativeNumber(double value, ScriptObject proto, PropertyMap map) {
    super(proto, map);
    this.value = value;
  }
  
  NativeNumber(double value, Global global) {
    this(value, global.getNumberPrototype(), $nasgenmap$);
  }
  
  private NativeNumber(double value) {
    this(value, Global.instance());
  }
  
  public String safeToString() {
    return "[Number " + toString() + "]";
  }
  
  public String toString() {
    return Double.toString(getValue());
  }
  
  public double getValue() {
    return doubleValue();
  }
  
  public double doubleValue() {
    return this.value;
  }
  
  public String getClassName() {
    return "Number";
  }
  
  public static Object constructor(boolean newObj, Object self, Object... args) {
    double num = (args.length > 0) ? JSType.toNumber(args[0]) : 0.0D;
    return newObj ? new NativeNumber(num) : Double.valueOf(num);
  }
  
  public static String toFixed(Object self, Object fractionDigits) {
    return toFixed(self, JSType.toInteger(fractionDigits));
  }
  
  public static String toFixed(Object self, int fractionDigits) {
    if (fractionDigits < 0 || fractionDigits > 20)
      throw ECMAErrors.rangeError("invalid.fraction.digits", new String[] { "toFixed" }); 
    double x = getNumberValue(self);
    if (Double.isNaN(x))
      return "NaN"; 
    if (Math.abs(x) >= 1.0E21D)
      return JSType.toString(x); 
    return DoubleConversion.toFixed(x, fractionDigits);
  }
  
  public static String toExponential(Object self, Object fractionDigits) {
    double x = getNumberValue(self);
    boolean trimZeros = (fractionDigits == ScriptRuntime.UNDEFINED);
    int f = trimZeros ? 16 : JSType.toInteger(fractionDigits);
    if (Double.isNaN(x))
      return "NaN"; 
    if (Double.isInfinite(x))
      return (x > 0.0D) ? "Infinity" : "-Infinity"; 
    if (fractionDigits != ScriptRuntime.UNDEFINED && (f < 0 || f > 20))
      throw ECMAErrors.rangeError("invalid.fraction.digits", new String[] { "toExponential" }); 
    String res = String.format(Locale.US, "%1." + f + "e", new Object[] { Double.valueOf(x) });
    return fixExponent(res, trimZeros);
  }
  
  public static String toPrecision(Object self, Object precision) {
    double x = getNumberValue(self);
    if (precision == ScriptRuntime.UNDEFINED)
      return JSType.toString(x); 
    return toPrecision(x, JSType.toInteger(precision));
  }
  
  public static String toPrecision(Object self, int precision) {
    return toPrecision(getNumberValue(self), precision);
  }
  
  private static String toPrecision(double x, int p) {
    if (Double.isNaN(x))
      return "NaN"; 
    if (Double.isInfinite(x))
      return (x > 0.0D) ? "Infinity" : "-Infinity"; 
    if (p < 1 || p > 21)
      throw ECMAErrors.rangeError("invalid.precision", new String[0]); 
    if (x == 0.0D && p <= 1)
      return "0"; 
    return DoubleConversion.toPrecision(x, p);
  }
  
  public static String toString(Object self, Object radix) {
    if (radix != ScriptRuntime.UNDEFINED) {
      int intRadix = JSType.toInteger(radix);
      if (intRadix != 10) {
        if (intRadix < 2 || intRadix > 36)
          throw ECMAErrors.rangeError("invalid.radix", new String[0]); 
        return JSType.toString(getNumberValue(self), intRadix);
      } 
    } 
    return JSType.toString(getNumberValue(self));
  }
  
  public static String toLocaleString(Object self) {
    return JSType.toString(getNumberValue(self));
  }
  
  public static double valueOf(Object self) {
    return getNumberValue(self);
  }
  
  public static GuardedInvocation lookupPrimitive(LinkRequest request, Object receiver) {
    return PrimitiveLookup.lookupPrimitive(request, NashornGuards.getNumberGuard(), new NativeNumber(((Number)receiver).doubleValue()), WRAPFILTER, PROTOFILTER);
  }
  
  private static NativeNumber wrapFilter(Object receiver) {
    return new NativeNumber(((Number)receiver).doubleValue());
  }
  
  private static Object protoFilter(Object object) {
    return Global.instance().getNumberPrototype();
  }
  
  private static double getNumberValue(Object self) {
    if (self instanceof Number)
      return ((Number)self).doubleValue(); 
    if (self instanceof NativeNumber)
      return ((NativeNumber)self).getValue(); 
    if (self != null && self == Global.instance().getNumberPrototype())
      return 0.0D; 
    throw ECMAErrors.typeError("not.a.number", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  private static String fixExponent(String str, boolean trimZeros) {
    int index = str.indexOf('e');
    if (index < 1)
      return str; 
    int expPadding = (str.charAt(index + 2) == '0') ? 3 : 2;
    int fractionOffset = index;
    if (trimZeros) {
      assert fractionOffset > 0;
      char c = str.charAt(fractionOffset - 1);
      while (fractionOffset > 1 && (c == '0' || c == '.'))
        c = str.charAt(--fractionOffset - 1); 
    } 
    if (fractionOffset < index || expPadding == 3)
      return str.substring(0, fractionOffset) + str.substring(0, fractionOffset) + str
        .substring(index, index + 2); 
    return str;
  }
  
  private static MethodHandle findOwnMH(String name, MethodType type) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), NativeNumber.class, name, type);
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
