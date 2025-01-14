package cn.hutool.core.text.finder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternFinder extends TextFinder {
  private static final long serialVersionUID = 1L;
  
  private final Pattern pattern;
  
  private Matcher matcher;
  
  public PatternFinder(String regex, boolean caseInsensitive) {
    this(Pattern.compile(regex, caseInsensitive ? 2 : 0));
  }
  
  public PatternFinder(Pattern pattern) {
    this.pattern = pattern;
  }
  
  public TextFinder setText(CharSequence text) {
    this.matcher = this.pattern.matcher(text);
    return super.setText(text);
  }
  
  public TextFinder setNegative(boolean negative) {
    throw new UnsupportedOperationException("Negative is invalid for Pattern!");
  }
  
  public int start(int from) {
    if (this.matcher.find(from)) {
      int end = this.matcher.end();
      if (end <= getValidEndIndex()) {
        int start = this.matcher.start();
        if (start == end)
          return -1; 
        return start;
      } 
    } 
    return -1;
  }
  
  public int end(int start) {
    int limit, end = this.matcher.end();
    if (this.endIndex < 0) {
      limit = this.text.length();
    } else {
      limit = Math.min(this.endIndex, this.text.length());
    } 
    return (end <= limit) ? end : -1;
  }
  
  public PatternFinder reset() {
    this.matcher.reset();
    return this;
  }
}
