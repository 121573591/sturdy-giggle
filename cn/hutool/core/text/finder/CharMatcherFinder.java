package cn.hutool.core.text.finder;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Matcher;

public class CharMatcherFinder extends TextFinder {
  private static final long serialVersionUID = 1L;
  
  private final Matcher<Character> matcher;
  
  public CharMatcherFinder(Matcher<Character> matcher) {
    this.matcher = matcher;
  }
  
  public int start(int from) {
    Assert.notNull(this.text, "Text to find must be not null!", new Object[0]);
    int limit = getValidEndIndex();
    if (this.negative) {
      for (int i = from; i > limit; i--) {
        if (this.matcher.match(Character.valueOf(this.text.charAt(i))))
          return i; 
      } 
    } else {
      for (int i = from; i < limit; i++) {
        if (this.matcher.match(Character.valueOf(this.text.charAt(i))))
          return i; 
      } 
    } 
    return -1;
  }
  
  public int end(int start) {
    if (start < 0)
      return -1; 
    return start + 1;
  }
}
