package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.io.doubleparser.JavaDoubleParser;
import com.fasterxml.jackson.core.io.doubleparser.JavaFloatParser;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class NumberInput {
  @Deprecated
  public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
  
  static final long L_BILLION = 1000000000L;
  
  static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
  
  static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);
  
  public static int parseInt(char[] ch, int off, int len) {
    if (len > 0 && ch[off] == '+') {
      off++;
      len--;
    } 
    int num = ch[off + len - 1] - 48;
    switch (len) {
      case 9:
        num += (ch[off++] - 48) * 100000000;
      case 8:
        num += (ch[off++] - 48) * 10000000;
      case 7:
        num += (ch[off++] - 48) * 1000000;
      case 6:
        num += (ch[off++] - 48) * 100000;
      case 5:
        num += (ch[off++] - 48) * 10000;
      case 4:
        num += (ch[off++] - 48) * 1000;
      case 3:
        num += (ch[off++] - 48) * 100;
      case 2:
        num += (ch[off] - 48) * 10;
        break;
    } 
    return num;
  }
  
  public static int parseInt(String s) {
    char c = s.charAt(0);
    int len = s.length();
    boolean neg = (c == '-');
    int offset = 1;
    if (neg) {
      if (len == 1 || len > 10)
        return Integer.parseInt(s); 
      c = s.charAt(offset++);
    } else if (len > 9) {
      return Integer.parseInt(s);
    } 
    if (c > '9' || c < '0')
      return Integer.parseInt(s); 
    int num = c - 48;
    if (offset < len) {
      c = s.charAt(offset++);
      if (c > '9' || c < '0')
        return Integer.parseInt(s); 
      num = num * 10 + c - 48;
      if (offset < len) {
        c = s.charAt(offset++);
        if (c > '9' || c < '0')
          return Integer.parseInt(s); 
        num = num * 10 + c - 48;
        if (offset < len)
          do {
            c = s.charAt(offset++);
            if (c > '9' || c < '0')
              return Integer.parseInt(s); 
            num = num * 10 + c - 48;
          } while (offset < len); 
      } 
    } 
    return neg ? -num : num;
  }
  
  public static long parseLong(char[] ch, int off, int len) {
    int len1 = len - 9;
    long val = parseInt(ch, off, len1) * 1000000000L;
    return val + parseInt(ch, off + len1, 9);
  }
  
  public static long parseLong19(char[] ch, int off, boolean negative) {
    long num = 0L;
    for (int i = 0; i < 19; i++) {
      char c = ch[off + i];
      num = num * 10L + (c - 48);
    } 
    return negative ? -num : num;
  }
  
  public static long parseLong(String s) {
    int length = s.length();
    if (length <= 9)
      return parseInt(s); 
    return Long.parseLong(s);
  }
  
  public static boolean inLongRange(char[] ch, int off, int len, boolean negative) {
    String cmpStr = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
    int cmpLen = cmpStr.length();
    if (len < cmpLen)
      return true; 
    if (len > cmpLen)
      return false; 
    for (int i = 0; i < cmpLen; i++) {
      int diff = ch[off + i] - cmpStr.charAt(i);
      if (diff != 0)
        return (diff < 0); 
    } 
    return true;
  }
  
  public static boolean inLongRange(String s, boolean negative) {
    String cmp = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
    int cmpLen = cmp.length();
    int alen = s.length();
    if (alen < cmpLen)
      return true; 
    if (alen > cmpLen)
      return false; 
    for (int i = 0; i < cmpLen; i++) {
      int diff = s.charAt(i) - cmp.charAt(i);
      if (diff != 0)
        return (diff < 0); 
    } 
    return true;
  }
  
  public static int parseAsInt(String s, int def) {
    if (s == null)
      return def; 
    s = s.trim();
    int len = s.length();
    if (len == 0)
      return def; 
    int i = 0;
    char sign = s.charAt(0);
    if (sign == '+') {
      s = s.substring(1);
      len = s.length();
    } else if (sign == '-') {
      i = 1;
    } 
    for (; i < len; i++) {
      char c = s.charAt(i);
      if (c > '9' || c < '0')
        try {
          return (int)parseDouble(s, true);
        } catch (NumberFormatException e) {
          return def;
        }  
    } 
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException numberFormatException) {
      return def;
    } 
  }
  
  public static long parseAsLong(String s, long def) {
    if (s == null)
      return def; 
    s = s.trim();
    int len = s.length();
    if (len == 0)
      return def; 
    int i = 0;
    char sign = s.charAt(0);
    if (sign == '+') {
      s = s.substring(1);
      len = s.length();
    } else if (sign == '-') {
      i = 1;
    } 
    for (; i < len; i++) {
      char c = s.charAt(i);
      if (c > '9' || c < '0')
        try {
          return (long)parseDouble(s, true);
        } catch (NumberFormatException e) {
          return def;
        }  
    } 
    try {
      return Long.parseLong(s);
    } catch (NumberFormatException numberFormatException) {
      return def;
    } 
  }
  
  public static double parseAsDouble(String s, double def) {
    return parseAsDouble(s, def, false);
  }
  
  public static double parseAsDouble(String s, double def, boolean useFastParser) {
    if (s == null)
      return def; 
    s = s.trim();
    int len = s.length();
    if (len == 0)
      return def; 
    try {
      return parseDouble(s, useFastParser);
    } catch (NumberFormatException numberFormatException) {
      return def;
    } 
  }
  
  public static double parseDouble(String s) throws NumberFormatException {
    return parseDouble(s, false);
  }
  
  public static double parseDouble(String s, boolean useFastParser) throws NumberFormatException {
    return useFastParser ? JavaDoubleParser.parseDouble(s) : Double.parseDouble(s);
  }
  
  public static float parseFloat(String s) throws NumberFormatException {
    return parseFloat(s, false);
  }
  
  public static float parseFloat(String s, boolean useFastParser) throws NumberFormatException {
    return useFastParser ? JavaFloatParser.parseFloat(s) : Float.parseFloat(s);
  }
  
  public static BigDecimal parseBigDecimal(String s) throws NumberFormatException {
    return BigDecimalParser.parse(s);
  }
  
  public static BigDecimal parseBigDecimal(String s, boolean useFastParser) throws NumberFormatException {
    return useFastParser ? 
      BigDecimalParser.parseWithFastParser(s) : 
      BigDecimalParser.parse(s);
  }
  
  public static BigDecimal parseBigDecimal(char[] ch, int off, int len) throws NumberFormatException {
    return BigDecimalParser.parse(ch, off, len);
  }
  
  public static BigDecimal parseBigDecimal(char[] ch, int off, int len, boolean useFastParser) throws NumberFormatException {
    return useFastParser ? 
      BigDecimalParser.parseWithFastParser(ch, off, len) : 
      BigDecimalParser.parse(ch, off, len);
  }
  
  public static BigDecimal parseBigDecimal(char[] ch) throws NumberFormatException {
    return BigDecimalParser.parse(ch);
  }
  
  public static BigDecimal parseBigDecimal(char[] ch, boolean useFastParser) throws NumberFormatException {
    return useFastParser ? 
      BigDecimalParser.parseWithFastParser(ch, 0, ch.length) : 
      BigDecimalParser.parse(ch);
  }
  
  public static BigInteger parseBigInteger(String s) throws NumberFormatException {
    return new BigInteger(s);
  }
  
  public static BigInteger parseBigInteger(String s, boolean useFastParser) throws NumberFormatException {
    if (useFastParser)
      return BigIntegerParser.parseWithFastParser(s); 
    return parseBigInteger(s);
  }
  
  public static BigInteger parseBigIntegerWithRadix(String s, int radix, boolean useFastParser) throws NumberFormatException {
    if (useFastParser)
      return BigIntegerParser.parseWithFastParser(s, radix); 
    return new BigInteger(s, radix);
  }
}
