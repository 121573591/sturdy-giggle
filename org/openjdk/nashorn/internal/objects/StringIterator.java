package org.openjdk.nashorn.internal.objects;

import java.util.Collections;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;

public class StringIterator extends AbstractIterator {
  private static PropertyMap $nasgenmap$;
  
  private String iteratedString;
  
  private int nextIndex = 0;
  
  private final Global global;
  
  StringIterator(String iteratedString, Global global) {
    super(global.getStringIteratorPrototype(), $nasgenmap$);
    this.iteratedString = iteratedString;
    this.global = global;
  }
  
  public static Object next(Object self, Object arg) {
    if (!(self instanceof StringIterator))
      throw ECMAErrors.typeError("not.a.string.iterator", new String[] { ScriptRuntime.safeToString(self) }); 
    return ((StringIterator)self).next(arg);
  }
  
  public String getClassName() {
    return "String Iterator";
  }
  
  protected IteratorResult next(Object arg) {
    int index = this.nextIndex;
    String string = this.iteratedString;
    if (string == null || index >= string.length()) {
      this.iteratedString = null;
      return makeResult(Undefined.getUndefined(), Boolean.TRUE, this.global);
    } 
    char first = string.charAt(index);
    if (first >= '?' && first <= '?' && index < string.length() - 1) {
      char second = string.charAt(index + 1);
      if (second >= '?' && second <= '?') {
        this.nextIndex += 2;
        return makeResult(String.valueOf(new char[] { first, second }, ), Boolean.FALSE, this.global);
      } 
    } 
    this.nextIndex++;
    return makeResult(String.valueOf(first), Boolean.FALSE, this.global);
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
