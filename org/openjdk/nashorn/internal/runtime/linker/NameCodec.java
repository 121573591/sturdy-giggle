package org.openjdk.nashorn.internal.runtime.linker;

public final class NameCodec {
  private static final char ESCAPE_C = '\\';
  
  private static final char NULL_ESCAPE_C = '=';
  
  private static final String NULL_ESCAPE = "\\=";
  
  public static final String EMPTY_NAME = new String(new char[] { '\\', '=' });
  
  private static final String DANGEROUS_CHARS = "\\/.;:$[]<>";
  
  private static final String REPLACEMENT_CHARS = "-|,?!%{}^_";
  
  private static final int DANGEROUS_CHAR_FIRST_INDEX = 1;
  
  public static String encode(String name) {
    String bn = mangle(name);
    assert bn == name || looksMangled(bn) : bn;
    assert name.equals(decode(bn)) : name;
    return bn;
  }
  
  public static String decode(String name) {
    String sn = name;
    if (!sn.isEmpty() && looksMangled(name)) {
      sn = demangle(name);
      assert name.equals(mangle(sn)) : name + " => " + name + " => " + sn;
    } 
    return sn;
  }
  
  private static boolean looksMangled(String s) {
    return (s.charAt(0) == '\\');
  }
  
  private static String mangle(String s) {
    if (s.length() == 0)
      return "\\="; 
    StringBuilder sb = null;
    for (int i = 0, slen = s.length(); i < slen; i++) {
      char c = s.charAt(i);
      boolean needEscape = false;
      if (c == '\\') {
        if (i + 1 < slen) {
          char c1 = s.charAt(i + 1);
          if ((i == 0 && c1 == '=') || c1 != 
            originalOfReplacement(c1))
            needEscape = true; 
        } 
      } else {
        needEscape = isDangerous(c);
      } 
      if (!needEscape) {
        if (sb != null)
          sb.append(c); 
      } else {
        if (sb == null) {
          sb = new StringBuilder(s.length() + 10);
          if (s.charAt(0) != '\\' && i > 0)
            sb.append("\\="); 
          sb.append(s, 0, i);
        } 
        sb.append('\\');
        sb.append(replacementOf(c));
      } 
    } 
    if (sb != null)
      return sb.toString(); 
    return s;
  }
  
  private static String demangle(String s) {
    StringBuilder sb = null;
    int stringStart = 0;
    if (s.startsWith("\\="))
      stringStart = 2; 
    for (int i = stringStart, slen = s.length(); i < slen; i++) {
      char c = s.charAt(i);
      if (c == '\\' && i + 1 < slen) {
        char rc = s.charAt(i + 1);
        char oc = originalOfReplacement(rc);
        if (oc != rc) {
          if (sb == null) {
            sb = new StringBuilder(s.length());
            sb.append(s, stringStart, i);
          } 
          i++;
          c = oc;
        } 
      } 
      if (sb != null)
        sb.append(c); 
    } 
    if (sb != null)
      return sb.toString(); 
    return s.substring(stringStart);
  }
  
  private static final long[] SPECIAL_BITMAP = new long[2];
  
  static {
    String SPECIAL = "\\/.;:$[]<>-|,?!%{}^_";
    for (char c : "\\/.;:$[]<>-|,?!%{}^_".toCharArray())
      SPECIAL_BITMAP[c >>> 6] = SPECIAL_BITMAP[c >>> 6] | 1L << c; 
  }
  
  private static boolean isSpecial(char c) {
    if (c >>> 6 < SPECIAL_BITMAP.length)
      return ((SPECIAL_BITMAP[c >>> 6] >> c & 0x1L) != 0L); 
    return false;
  }
  
  private static char replacementOf(char c) {
    if (!isSpecial(c))
      return c; 
    int i = "\\/.;:$[]<>".indexOf(c);
    if (i < 0)
      return c; 
    return "-|,?!%{}^_".charAt(i);
  }
  
  private static char originalOfReplacement(char c) {
    if (!isSpecial(c))
      return c; 
    int i = "-|,?!%{}^_".indexOf(c);
    if (i < 0)
      return c; 
    return "\\/.;:$[]<>".charAt(i);
  }
  
  private static boolean isDangerous(char c) {
    if (!isSpecial(c))
      return false; 
    return ("\\/.;:$[]<>".indexOf(c) >= 1);
  }
}
