package org.openjdk.nashorn.internal.runtime;

import org.openjdk.nashorn.api.scripting.NashornException;
import org.openjdk.nashorn.internal.parser.Lexer;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.parser.TokenStream;
import org.openjdk.nashorn.internal.parser.TokenType;

public final class Debug {
  public static String firstJSFrame(Throwable t) {
    for (StackTraceElement ste : t.getStackTrace()) {
      if (ECMAErrors.isScriptFrame(ste))
        return ste.toString(); 
    } 
    return "<native code>";
  }
  
  public static String firstJSFrame() {
    return firstJSFrame(new Throwable());
  }
  
  public static String scriptStack() {
    return NashornException.getScriptStackString(new Throwable());
  }
  
  public static String id(Object x) {
    return String.format("0x%08x", new Object[] { Integer.valueOf(System.identityHashCode(x)) });
  }
  
  public static int intId(Object x) {
    return System.identityHashCode(x);
  }
  
  public static String stackTraceElementAt(int depth) {
    return (new Throwable()).getStackTrace()[depth + 1].toString();
  }
  
  public static String caller(int depth, int count, String... ignores) {
    String result = "";
    StackTraceElement[] callers = Thread.currentThread().getStackTrace();
    int c = count;
    for (int i = depth + 1; i < callers.length && c != 0; i++) {
      StackTraceElement element = callers[i];
      String method = element.getMethodName();
      String[] arrayOfString = ignores;
      int j = arrayOfString.length;
      byte b = 0;
      while (true) {
        if (b < j) {
          String ignore = arrayOfString[b];
          if (method.compareTo(ignore) == 0)
            break; 
          b++;
          continue;
        } 
        result = result + result;
        c--;
        break;
      } 
    } 
    return result.isEmpty() ? "<no caller>" : result;
  }
  
  public static void dumpTokens(Source source, Lexer lexer, TokenStream stream) {
    TokenType type;
    int k = 0;
    do {
      while (k > stream.last())
        lexer.lexify(); 
      long token = stream.get(k);
      type = Token.descType(token);
      System.out.println("" + k + ": " + k);
      k++;
    } while (type != TokenType.EOF);
  }
}
