package com.fasterxml.jackson.core.io.doubleparser;

class FastDoubleSwar {
  protected static boolean isDigit(char c) {
    return ((char)(c - 48) < '\n');
  }
  
  protected static boolean isDigit(byte c) {
    return ((char)(c - 48) < '\n');
  }
  
  public static boolean isEightDigits(byte[] a, int offset) {
    return isEightDigitsUtf8(readLongLE(a, offset));
  }
  
  public static boolean isEightDigits(char[] a, int offset) {
    long first = a[offset] | a[offset + 1] << 16L | a[offset + 2] << 32L | a[offset + 3] << 48L;
    long second = a[offset + 4] | a[offset + 5] << 16L | a[offset + 6] << 32L | a[offset + 7] << 48L;
    return isEightDigitsUtf16(first, second);
  }
  
  public static boolean isEightDigits(CharSequence a, int offset) {
    boolean success = true;
    for (int i = 0; i < 8; i++) {
      char ch = a.charAt(i + offset);
      success &= isDigit(ch);
    } 
    return success;
  }
  
  public static boolean isEightDigitsUtf16(long first, long second) {
    long fval = first - 13511005043687472L;
    long sval = second - 13511005043687472L;
    long fpre = first + 19703549022044230L | fval;
    long spre = second + 19703549022044230L | sval;
    return (((fpre | spre) & 0xFF80FF80FF80FF80L) == 0L);
  }
  
  public static boolean isEightDigitsUtf8(long chunk) {
    long val = chunk - 3472328296227680304L;
    long predicate = (chunk + 5063812098665367110L | val) & 0x8080808080808080L;
    return (predicate == 0L);
  }
  
  public static boolean isEightZeroes(byte[] a, int offset) {
    return isEightZeroesUtf8(readLongLE(a, offset));
  }
  
  public static boolean isEightZeroes(CharSequence a, int offset) {
    int j;
    boolean success = true;
    for (int i = 0; i < 8; i++)
      j = success & (('0' == a.charAt(i + offset)) ? 1 : 0); 
    return j;
  }
  
  public static boolean isEightZeroes(char[] a, int offset) {
    long first = a[offset] | a[offset + 1] << 16L | a[offset + 2] << 32L | a[offset + 3] << 48L;
    long second = a[offset + 4] | a[offset + 5] << 16L | a[offset + 6] << 32L | a[offset + 7] << 48L;
    return isEightZeroesUtf16(first, second);
  }
  
  public static boolean isEightZeroesUtf16(long first, long second) {
    return (first == 13511005043687472L && second == 13511005043687472L);
  }
  
  public static boolean isEightZeroesUtf8(long chunk) {
    return (chunk == 3472328296227680304L);
  }
  
  public static int readIntBE(byte[] a, int offset) {
    return (a[offset] & 0xFF) << 24 | (a[offset + 1] & 0xFF) << 16 | (a[offset + 2] & 0xFF) << 8 | a[offset + 3] & 0xFF;
  }
  
  public static int readIntLE(byte[] a, int offset) {
    return (a[offset + 3] & 0xFF) << 24 | (a[offset + 2] & 0xFF) << 16 | (a[offset + 1] & 0xFF) << 8 | a[offset] & 0xFF;
  }
  
  public static long readLongBE(byte[] a, int offset) {
    return (a[offset] & 0xFFL) << 56L | (a[offset + 1] & 0xFFL) << 48L | (a[offset + 2] & 0xFFL) << 40L | (a[offset + 3] & 0xFFL) << 32L | (a[offset + 4] & 0xFFL) << 24L | (a[offset + 5] & 0xFFL) << 16L | (a[offset + 6] & 0xFFL) << 8L | a[offset + 7] & 0xFFL;
  }
  
  public static long readLongLE(byte[] a, int offset) {
    return (a[offset + 7] & 0xFFL) << 56L | (a[offset + 6] & 0xFFL) << 48L | (a[offset + 5] & 0xFFL) << 40L | (a[offset + 4] & 0xFFL) << 32L | (a[offset + 3] & 0xFFL) << 24L | (a[offset + 2] & 0xFFL) << 16L | (a[offset + 1] & 0xFFL) << 8L | a[offset] & 0xFFL;
  }
  
  public static int tryToParseEightDigits(char[] a, int offset) {
    long first = a[offset] | a[offset + 1] << 16L | a[offset + 2] << 32L | a[offset + 3] << 48L;
    long second = a[offset + 4] | a[offset + 5] << 16L | a[offset + 6] << 32L | a[offset + 7] << 48L;
    return tryToParseEightDigitsUtf16(first, second);
  }
  
  public static int tryToParseEightDigits(byte[] a, int offset) {
    return tryToParseEightDigitsUtf8(readLongLE(a, offset));
  }
  
  public static int tryToParseEightDigits(CharSequence str, int offset) {
    long first = str.charAt(offset) | str.charAt(offset + 1) << 16L | str.charAt(offset + 2) << 32L | str.charAt(offset + 3) << 48L;
    long second = str.charAt(offset + 4) | str.charAt(offset + 5) << 16L | str.charAt(offset + 6) << 32L | str.charAt(offset + 7) << 48L;
    return tryToParseEightDigitsUtf16(first, second);
  }
  
  public static int tryToParseEightDigitsUtf16(long first, long second) {
    long fval = first - 13511005043687472L;
    long sval = second - 13511005043687472L;
    long fpre = first + 19703549022044230L | fval;
    long spre = second + 19703549022044230L | sval;
    if (((fpre | spre) & 0xFF80FF80FF80FF80L) != 0L)
      return -1; 
    return (int)(sval * 281475406208040961L >>> 48L) + (int)(fval * 281475406208040961L >>> 48L) * 10000;
  }
  
  public static int tryToParseEightDigitsUtf8(byte[] a, int offset) {
    return tryToParseEightDigitsUtf8(readLongLE(a, offset));
  }
  
  public static int tryToParseEightDigitsUtf8(long chunk) {
    long val = chunk - 3472328296227680304L;
    long predicate = (chunk + 5063812098665367110L | val) & 0x8080808080808080L;
    if (predicate != 0L)
      return -1; 
    long mask = 1095216660735L;
    long mul1 = 4294967296000100L;
    long mul2 = 42949672960001L;
    val = val * 10L + (val >>> 8L);
    val = (val & mask) * mul1 + (val >>> 16L & mask) * mul2 >>> 32L;
    return (int)val;
  }
  
  public static long tryToParseEightHexDigits(CharSequence str, int offset) {
    long first = str.charAt(offset) << 48L | str.charAt(offset + 1) << 32L | str.charAt(offset + 2) << 16L | str.charAt(offset + 3);
    long second = str.charAt(offset + 4) << 48L | str.charAt(offset + 5) << 32L | str.charAt(offset + 6) << 16L | str.charAt(offset + 7);
    return tryToParseEightHexDigitsUtf16(first, second);
  }
  
  public static long tryToParseEightHexDigits(char[] chars, int offset) {
    long first = chars[offset] << 48L | chars[offset + 1] << 32L | chars[offset + 2] << 16L | chars[offset + 3];
    long second = chars[offset + 4] << 48L | chars[offset + 5] << 32L | chars[offset + 6] << 16L | chars[offset + 7];
    return tryToParseEightHexDigitsUtf16(first, second);
  }
  
  public static long tryToParseEightHexDigits(byte[] a, int offset) {
    return tryToParseEightHexDigitsUtf8(readLongBE(a, offset));
  }
  
  public static long tryToParseEightHexDigitsUtf16(long first, long second) {
    if (((first | second) & 0xFF00FF00FF00FF00L) != 0L)
      return -1L; 
    long f = first * 65792L;
    long s = second * 65792L;
    long utf8Bytes = f & 0xFFFF000000000000L | (f & 0xFFFF0000L) << 16L | (s & 0xFFFF000000000000L) >>> 32L | (s & 0xFFFF0000L) >>> 16L;
    return tryToParseEightHexDigitsUtf8(utf8Bytes);
  }
  
  public static long tryToParseEightHexDigitsUtf8(long chunk) {
    long ge_0 = chunk + 5787213827046133840L;
    long le_9 = 4195730024608447034L + (chunk ^ 0x7F7F7F7F7F7F7F7FL);
    long lowerCaseChunk = chunk | 0x2020202020202020L;
    long ge_a = lowerCaseChunk + 2242545357980376863L;
    ge_a &= 0x8080808080808080L;
    long le_f = 7451037802321897319L + (lowerCaseChunk ^ 0x7F7F7F7F7F7F7F7FL);
    if (((ge_0 & le_9 ^ ge_a & le_f) & 0x8080808080808080L) != -9187201950435737472L)
      return -1L; 
    long ge_a_mask = (ge_a >>> 7L) * 255L;
    long vec = lowerCaseChunk - 3472328296227680304L;
    long v = vec & (ge_a_mask ^ 0xFFFFFFFFFFFFFFFFL) | vec - (0x2727272727272727L & ge_a_mask);
    long v2 = v | v >>> 4L;
    long v3 = v2 & 0xFF00FF00FF00FFL;
    long v4 = v3 | v3 >>> 8L;
    long v5 = v4 >>> 16L & 0xFFFF0000L | v4 & 0xFFFFL;
    return v5;
  }
  
  public static int tryToParseFourDigits(char[] a, int offset) {
    long first = a[offset] | a[offset + 1] << 16L | a[offset + 2] << 32L | a[offset + 3] << 48L;
    return tryToParseFourDigitsUtf16(first);
  }
  
  public static int tryToParseFourDigits(CharSequence str, int offset) {
    long first = str.charAt(offset) | str.charAt(offset + 1) << 16L | str.charAt(offset + 2) << 32L | str.charAt(offset + 3) << 48L;
    return tryToParseFourDigitsUtf16(first);
  }
  
  public static int tryToParseFourDigits(byte[] a, int offset) {
    return tryToParseFourDigitsUtf8(readIntLE(a, offset));
  }
  
  public static int tryToParseFourDigitsUtf16(long first) {
    long fval = first - 13511005043687472L;
    long fpre = first + 19703549022044230L | fval;
    if ((fpre & 0xFF80FF80FF80FF80L) != 0L)
      return -1; 
    return (int)(fval * 281475406208040961L >>> 48L);
  }
  
  public static int tryToParseFourDigitsUtf8(int chunk) {
    int val = chunk - 808464432;
    int predicate = (chunk + 1179010630 | val) & 0x80808080;
    if (predicate != 0L)
      return -1; 
    val = val * 2561 >>> 8;
    val = (val & 0xFF) * 100 + ((val & 0xFF0000) >> 16);
    return val;
  }
  
  public static int tryToParseUpTo7Digits(byte[] str, int from, int to) {
    int result = 0;
    boolean success = true;
    for (; from < to; from++) {
      byte ch = str[from];
      success &= isDigit(ch);
      result = 10 * result + ch - 48;
    } 
    return success ? result : -1;
  }
  
  public static int tryToParseUpTo7Digits(char[] str, int from, int to) {
    int result = 0;
    boolean success = true;
    for (; from < to; from++) {
      char ch = str[from];
      success &= isDigit(ch);
      result = 10 * result + ch - 48;
    } 
    return success ? result : -1;
  }
  
  public static int tryToParseUpTo7Digits(CharSequence str, int from, int to) {
    int result = 0;
    boolean success = true;
    for (; from < to; from++) {
      char ch = str.charAt(from);
      success &= isDigit(ch);
      result = 10 * result + ch - 48;
    } 
    return success ? result : -1;
  }
  
  public static void writeIntBE(byte[] a, int offset, int v) {
    a[offset] = (byte)(v >>> 24);
    a[offset + 1] = (byte)(v >>> 16);
    a[offset + 2] = (byte)(v >>> 8);
    a[offset + 3] = (byte)v;
  }
  
  public static void writeLongBE(byte[] a, int offset, long v) {
    a[offset] = (byte)(int)(v >>> 56L);
    a[offset + 1] = (byte)(int)(v >>> 48L);
    a[offset + 2] = (byte)(int)(v >>> 40L);
    a[offset + 3] = (byte)(int)(v >>> 32L);
    a[offset + 4] = (byte)(int)(v >>> 24L);
    a[offset + 5] = (byte)(int)(v >>> 16L);
    a[offset + 6] = (byte)(int)(v >>> 8L);
    a[offset + 7] = (byte)(int)v;
  }
  
  public static double fma(double a, double b, double c) {
    return a * b + c;
  }
}
