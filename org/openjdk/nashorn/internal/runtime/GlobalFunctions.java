package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Locale;
import org.openjdk.nashorn.internal.lookup.Lookup;

public final class GlobalFunctions {
  public static final MethodHandle PARSEINT = findOwnMH("parseInt", double.class, new Class[] { Object.class, Object.class, Object.class });
  
  public static final MethodHandle PARSEINT_OI = findOwnMH("parseInt", double.class, new Class[] { Object.class, Object.class, int.class });
  
  public static final MethodHandle PARSEINT_Z = Lookup.MH.dropArguments(Lookup.MH.dropArguments(Lookup.MH.constant(double.class, Double.valueOf(Double.NaN)), 0, new Class[] { boolean.class }), 0, new Class[] { Object.class });
  
  public static final MethodHandle PARSEINT_I = Lookup.MH.dropArguments(Lookup.MH.identity(int.class), 0, new Class[] { Object.class });
  
  public static final MethodHandle PARSEINT_O = findOwnMH("parseInt", double.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle PARSEFLOAT = findOwnMH("parseFloat", double.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle IS_NAN_I = Lookup.MH.dropArguments(Lookup.MH.constant(boolean.class, Boolean.valueOf(false)), 0, new Class[] { Object.class });
  
  public static final MethodHandle IS_NAN_J = Lookup.MH.dropArguments(Lookup.MH.constant(boolean.class, Boolean.valueOf(false)), 0, new Class[] { Object.class });
  
  public static final MethodHandle IS_NAN_D = Lookup.MH.dropArguments(Lookup.MH.findStatic(MethodHandles.lookup(), Double.class, "isNaN", Lookup.MH.type(boolean.class, new Class[] { double.class })), 0, new Class[] { Object.class });
  
  public static final MethodHandle IS_NAN = findOwnMH("isNaN", boolean.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle IS_FINITE = findOwnMH("isFinite", boolean.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle ENCODE_URI = findOwnMH("encodeURI", Object.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle ENCODE_URICOMPONENT = findOwnMH("encodeURIComponent", Object.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle DECODE_URI = findOwnMH("decodeURI", Object.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle DECODE_URICOMPONENT = findOwnMH("decodeURIComponent", Object.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle ESCAPE = findOwnMH("escape", String.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle UNESCAPE = findOwnMH("unescape", String.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle ANONYMOUS = findOwnMH("anonymous", Object.class, new Class[] { Object.class });
  
  private static final String UNESCAPED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@*_+-./";
  
  public static double parseInt(Object self, Object string, Object rad) {
    return parseIntInternal(JSType.trimLeft(JSType.toString(string)), JSType.toInt32(rad));
  }
  
  public static double parseInt(Object self, Object string, int rad) {
    return parseIntInternal(JSType.trimLeft(JSType.toString(string)), rad);
  }
  
  public static double parseInt(Object self, Object string) {
    return parseIntInternal(JSType.trimLeft(JSType.toString(string)), 0);
  }
  
  private static double parseIntInternal(String str, int rad) {
    int length = str.length();
    int radix = rad;
    if (length == 0)
      return Double.NaN; 
    boolean negative = false;
    int idx = 0;
    char firstChar = str.charAt(idx);
    if (firstChar < '0') {
      if (firstChar == '-') {
        negative = true;
      } else if (firstChar != '+') {
        return Double.NaN;
      } 
      idx++;
    } 
    boolean stripPrefix = true;
    if (radix != 0) {
      if (radix < 2 || radix > 36)
        return Double.NaN; 
      if (radix != 16)
        stripPrefix = false; 
    } else {
      radix = 10;
    } 
    if (stripPrefix && idx + 1 < length) {
      char c1 = str.charAt(idx);
      char c2 = str.charAt(idx + 1);
      if (c1 == '0' && (c2 == 'x' || c2 == 'X')) {
        radix = 16;
        idx += 2;
      } 
    } 
    double result = 0.0D;
    boolean entered = false;
    while (idx < length) {
      int digit = fastDigit(str.charAt(idx++), radix);
      if (digit < 0)
        break; 
      entered = true;
      result *= radix;
      result += digit;
    } 
    return entered ? (negative ? -result : result) : Double.NaN;
  }
  
  public static double parseFloat(Object self, Object string) {
    String str = JSType.trimLeft(JSType.toString(string));
    int length = str.length();
    if (length == 0)
      return Double.NaN; 
    int start = 0;
    boolean negative = false;
    char ch = str.charAt(0);
    if (ch == '-') {
      start++;
      negative = true;
    } else if (ch == '+') {
      start++;
    } else if (ch == 'N' && 
      str.startsWith("NaN")) {
      return Double.NaN;
    } 
    if (start == length)
      return Double.NaN; 
    ch = str.charAt(start);
    if (ch == 'I' && 
      str.substring(start).startsWith("Infinity"))
      return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY; 
    boolean dotSeen = false;
    boolean exponentOk = false;
    int exponentOffset = -1;
    int end;
    for (end = start; end < length; end++) {
      ch = str.charAt(end);
      switch (ch) {
        case '.':
          if (exponentOffset != -1 || dotSeen)
            break; 
          dotSeen = true;
          break;
        case 'E':
        case 'e':
          if (exponentOffset != -1)
            break; 
          exponentOffset = end;
          break;
        case '+':
        case '-':
          if (exponentOffset != end - 1)
            break; 
          break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          if (exponentOffset != -1)
            exponentOk = true; 
          break;
        default:
          break;
      } 
    } 
    if (exponentOffset != -1 && !exponentOk)
      end = exponentOffset; 
    if (start == end)
      return Double.NaN; 
    try {
      double result = Double.parseDouble(str.substring(start, end));
      return negative ? -result : result;
    } catch (NumberFormatException e) {
      return Double.NaN;
    } 
  }
  
  public static boolean isNaN(Object self, Object number) {
    return Double.isNaN(JSType.toNumber(number));
  }
  
  public static boolean isFinite(Object self, Object number) {
    double value = JSType.toNumber(number);
    return (!Double.isInfinite(value) && !Double.isNaN(value));
  }
  
  public static Object encodeURI(Object self, Object uri) {
    return URIUtils.encodeURI(self, JSType.toString(uri));
  }
  
  public static Object encodeURIComponent(Object self, Object uri) {
    return URIUtils.encodeURIComponent(self, JSType.toString(uri));
  }
  
  public static Object decodeURI(Object self, Object uri) {
    return URIUtils.decodeURI(self, JSType.toString(uri));
  }
  
  public static Object decodeURIComponent(Object self, Object uri) {
    return URIUtils.decodeURIComponent(self, JSType.toString(uri));
  }
  
  public static String escape(Object self, Object string) {
    String str = JSType.toString(string);
    int length = str.length();
    if (length == 0)
      return str; 
    StringBuilder sb = new StringBuilder();
    for (int k = 0; k < length; k++) {
      char ch = str.charAt(k);
      if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@*_+-./".indexOf(ch) != -1) {
        sb.append(ch);
      } else if (ch < 'Ā') {
        sb.append('%');
        if (ch < '\020')
          sb.append('0'); 
        sb.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
      } else {
        sb.append("%u");
        if (ch < 'က')
          sb.append('0'); 
        sb.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
      } 
    } 
    return sb.toString();
  }
  
  public static String unescape(Object self, Object string) {
    String str = JSType.toString(string);
    int length = str.length();
    if (length == 0)
      return str; 
    StringBuilder sb = new StringBuilder();
    int k = 0;
    while (true) {
      char ch;
      if (k < length) {
        ch = str.charAt(k);
        if (ch == '%') {
          if (k < length - 5 && 
            str.charAt(k + 1) == 'u')
            try {
              ch = (char)Integer.parseInt(str.substring(k + 2, k + 6), 16);
              sb.append(ch);
              k += 5;
              continue;
            } catch (NumberFormatException numberFormatException) {} 
          if (k < length - 2) {
            try {
              ch = (char)Integer.parseInt(str.substring(k + 1, k + 3), 16);
              sb.append(ch);
              k += 2;
            } catch (NumberFormatException numberFormatException) {
              sb.append(ch);
            } 
            continue;
          } 
        } 
      } else {
        break;
      } 
      sb.append(ch);
      k++;
    } 
    return sb.toString();
  }
  
  public static Object anonymous(Object self) {
    return ScriptRuntime.UNDEFINED;
  }
  
  private static int fastDigit(int ch, int radix) {
    int n = -1;
    if (ch >= 48 && ch <= 57) {
      n = ch - 48;
    } else if (radix > 10) {
      if (ch >= 97 && ch <= 122) {
        n = ch - 97 + 10;
      } else if (ch >= 65 && ch <= 90) {
        n = ch - 65 + 10;
      } 
    } 
    return (n < radix) ? n : -1;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), GlobalFunctions.class, name, Lookup.MH.type(rtype, types));
  }
}
