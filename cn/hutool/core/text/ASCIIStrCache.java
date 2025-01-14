package cn.hutool.core.text;

public class ASCIIStrCache {
  private static final int ASCII_LENGTH = 128;
  
  private static final String[] CACHE = new String[128];
  
  static {
    for (char c = Character.MIN_VALUE; c < ''; c = (char)(c + 1))
      CACHE[c] = String.valueOf(c); 
  }
  
  public static String toString(char c) {
    return (c < '') ? CACHE[c] : String.valueOf(c);
  }
}
