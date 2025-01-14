package org.openjdk.nashorn.internal.runtime.regexp.joni;

public abstract class MatcherFactory {
  static final MatcherFactory DEFAULT = new MatcherFactory() {
      public Matcher create(Regex regex, char[] chars, int p, int end) {
        return new ByteCodeMachine(regex, chars, p, end);
      }
    };
  
  public abstract Matcher create(Regex paramRegex, char[] paramArrayOfchar, int paramInt1, int paramInt2);
}
