package org.openjdk.nashorn.internal.parser;

import org.openjdk.nashorn.internal.runtime.Source;

public class Token {
  public static final int LENGTH_MASK = 268435455;
  
  private static final int LENGTH_SHIFT = 8;
  
  private static final int POSITION_SHIFT = 36;
  
  public static long toDesc(TokenType type, int position, int length) {
    assert position <= 268435455 && length <= 268435455;
    return position << 36L | length << 8L | type
      
      .ordinal();
  }
  
  public static int descPosition(long token) {
    return (int)(token >>> 36L);
  }
  
  public static long withDelimiter(long token) {
    int start, len;
    TokenType tokenType = descType(token);
    switch (tokenType) {
      case STRING:
      case ESCSTRING:
      case EXECSTRING:
      case TEMPLATE:
      case TEMPLATE_TAIL:
        start = descPosition(token) - 1;
        len = descLength(token) + 2;
        return toDesc(tokenType, start, len);
      case TEMPLATE_HEAD:
      case TEMPLATE_MIDDLE:
        start = descPosition(token) - 1;
        len = descLength(token) + 3;
        return toDesc(tokenType, start, len);
    } 
    return token;
  }
  
  public static int descLength(long token) {
    return (int)(token >>> 8L & 0xFFFFFFFL);
  }
  
  public static TokenType descType(long token) {
    return TokenType.getValues()[(int)token & 0xFF];
  }
  
  public static long recast(long token, TokenType newType) {
    return token & 0xFFFFFFFFFFFFFF00L | newType.ordinal();
  }
  
  public static String toString(Source source, long token, boolean verbose) {
    String result;
    TokenType type = descType(token);
    if (source != null && type.getKind() == TokenKind.LITERAL) {
      result = source.getString(token);
    } else {
      result = type.getNameOrType();
    } 
    if (verbose) {
      int position = descPosition(token);
      int length = descLength(token);
      result = result + " (" + result + ", " + position + ")";
    } 
    return result;
  }
  
  public static String toString(Source source, long token) {
    return toString(source, token, false);
  }
  
  public static String toString(long token) {
    return toString(null, token, false);
  }
  
  public static int hashCode(long token) {
    return (int)(token ^ token >>> 32L);
  }
}
