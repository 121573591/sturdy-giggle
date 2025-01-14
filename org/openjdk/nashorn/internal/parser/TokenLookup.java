package org.openjdk.nashorn.internal.parser;

public final class TokenLookup {
  private static final TokenType[] table = new TokenType[95];
  
  private static final int tableBase = 32;
  
  private static final int tableLimit = 126;
  
  private static final int tableLength = 95;
  
  static {
    for (TokenType tokenType : TokenType.getValues()) {
      String name = tokenType.getName();
      if (name != null)
        if (tokenType.getKind() != TokenKind.SPECIAL) {
          char first = name.charAt(0);
          int index = first - 32;
          assert index < 95 : "Token name does not fit lookup table";
          int length = tokenType.getLength();
          TokenType prev = null;
          TokenType next = table[index];
          while (next != null && next.getLength() > length) {
            prev = next;
            next = next.getNext();
          } 
          tokenType.setNext(next);
          if (prev == null) {
            table[index] = tokenType;
          } else {
            prev.setNext(tokenType);
          } 
        }  
    } 
  }
  
  public static TokenType lookupKeyword(char[] content, int position, int length) {
    assert table != null : "Token lookup table is not initialized";
    char first = content[position];
    if ('a' <= first && first <= 'z') {
      int index = first - 32;
      TokenType tokenType = table[index];
      while (tokenType != null) {
        int tokenLength = tokenType.getLength();
        if (tokenLength == length) {
          String name = tokenType.getName();
          int i;
          for (i = 0; i < length && 
            content[position + i] == name.charAt(i); i++);
          if (i == length)
            return tokenType; 
        } else if (tokenLength < length) {
          break;
        } 
        tokenType = tokenType.getNext();
      } 
    } 
    return TokenType.IDENT;
  }
  
  public static TokenType lookupOperator(char ch0, char ch1, char ch2, char ch3) {
    assert table != null : "Token lookup table is not initialized";
    if (' ' < ch0 && ch0 <= '~' && ('a' > ch0 || ch0 > 'z')) {
      int index = ch0 - 32;
      TokenType tokenType = table[index];
      while (tokenType != null) {
        String name = tokenType.getName();
        switch (name.length()) {
          case 1:
            return tokenType;
          case 2:
            if (name.charAt(1) == ch1)
              return tokenType; 
            break;
          case 3:
            if (name.charAt(1) == ch1 && name
              .charAt(2) == ch2)
              return tokenType; 
            break;
          case 4:
            if (name.charAt(1) == ch1 && name
              .charAt(2) == ch2 && name
              .charAt(3) == ch3)
              return tokenType; 
            break;
        } 
        tokenType = tokenType.getNext();
      } 
    } 
    return null;
  }
}
