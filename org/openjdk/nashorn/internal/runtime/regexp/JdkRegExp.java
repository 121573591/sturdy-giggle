package org.openjdk.nashorn.internal.runtime.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.openjdk.nashorn.internal.runtime.ParserException;

public class JdkRegExp extends RegExp {
  private Pattern pattern;
  
  public JdkRegExp(String source, String flags) throws ParserException {
    super(source, flags);
    int intFlags = 0;
    if (isIgnoreCase())
      intFlags |= 0x42; 
    if (isMultiline())
      intFlags |= 0x8; 
    try {
      RegExpScanner parsed;
      try {
        parsed = RegExpScanner.scan(source);
      } catch (PatternSyntaxException e) {
        Pattern.compile(source, intFlags);
        throw e;
      } 
      if (parsed != null) {
        this.pattern = Pattern.compile(parsed.getJavaPattern(), intFlags);
        this.groupsInNegativeLookahead = parsed.getGroupsInNegativeLookahead();
      } 
    } catch (PatternSyntaxException e2) {
      RegExpScanner parsed;
      throwParserException("syntax", parsed.getMessage());
    } catch (StackOverflowError e3) {
      throw new RuntimeException(e3);
    } 
  }
  
  public RegExpMatcher match(String str) {
    if (this.pattern == null)
      return null; 
    return new DefaultMatcher(str);
  }
  
  class DefaultMatcher implements RegExpMatcher {
    final String input;
    
    final Matcher defaultMatcher;
    
    DefaultMatcher(String input) {
      this.input = input;
      this.defaultMatcher = JdkRegExp.this.pattern.matcher(input);
    }
    
    public boolean search(int start) {
      return this.defaultMatcher.find(start);
    }
    
    public String getInput() {
      return this.input;
    }
    
    public int start() {
      return this.defaultMatcher.start();
    }
    
    public int start(int group) {
      return this.defaultMatcher.start(group);
    }
    
    public int end() {
      return this.defaultMatcher.end();
    }
    
    public int end(int group) {
      return this.defaultMatcher.end(group);
    }
    
    public String group() {
      return this.defaultMatcher.group();
    }
    
    public String group(int group) {
      return this.defaultMatcher.group(group);
    }
    
    public int groupCount() {
      return this.defaultMatcher.groupCount();
    }
  }
}
