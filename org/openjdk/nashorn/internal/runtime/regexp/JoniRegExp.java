package org.openjdk.nashorn.internal.runtime.regexp;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.Matcher;
import org.openjdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.openjdk.nashorn.internal.runtime.regexp.joni.Region;
import org.openjdk.nashorn.internal.runtime.regexp.joni.Syntax;

public class JoniRegExp extends RegExp {
  private Regex regex;
  
  public JoniRegExp(String pattern, String flags) throws ParserException {
    super(pattern, flags);
    int option = 8;
    if (isIgnoreCase())
      option |= 0x1; 
    if (isMultiline()) {
      option &= 0xFFFFFFF7;
      option |= 0x40;
    } 
    try {
      RegExpScanner parsed;
      try {
        parsed = RegExpScanner.scan(pattern);
      } catch (PatternSyntaxException e) {
        Pattern.compile(pattern, 0);
        throw e;
      } 
      if (parsed != null) {
        char[] javaPattern = parsed.getJavaPattern().toCharArray();
        this.regex = new Regex(javaPattern, 0, javaPattern.length, option, Syntax.JAVASCRIPT);
        this.groupsInNegativeLookahead = parsed.getGroupsInNegativeLookahead();
      } 
    } catch (PatternSyntaxException|org.openjdk.nashorn.internal.runtime.regexp.joni.exception.JOniException e2) {
      RegExpScanner parsed;
      throwParserException("syntax", parsed.getMessage());
    } catch (StackOverflowError e3) {
      throw new RuntimeException(e3);
    } 
  }
  
  public RegExpMatcher match(String input) {
    if (this.regex == null)
      return null; 
    return new JoniMatcher(input);
  }
  
  public static class Factory extends RegExpFactory {
    public RegExp compile(String pattern, String flags) throws ParserException {
      return new JoniRegExp(pattern, flags);
    }
  }
  
  class JoniMatcher implements RegExpMatcher {
    final String input;
    
    final Matcher joniMatcher;
    
    JoniMatcher(String input) {
      this.input = input;
      this.joniMatcher = JoniRegExp.this.regex.matcher(input.toCharArray());
    }
    
    public boolean search(int start) {
      return (this.joniMatcher.search(start, this.input.length(), 0) > -1);
    }
    
    public String getInput() {
      return this.input;
    }
    
    public int start() {
      return this.joniMatcher.getBegin();
    }
    
    public int start(int group) {
      return (group == 0) ? start() : (this.joniMatcher.getRegion()).beg[group];
    }
    
    public int end() {
      return this.joniMatcher.getEnd();
    }
    
    public int end(int group) {
      return (group == 0) ? end() : (this.joniMatcher.getRegion()).end[group];
    }
    
    public String group() {
      return this.input.substring(this.joniMatcher.getBegin(), this.joniMatcher.getEnd());
    }
    
    public String group(int group) {
      if (group == 0)
        return group(); 
      Region region = this.joniMatcher.getRegion();
      return this.input.substring(region.beg[group], region.end[group]);
    }
    
    public int groupCount() {
      Region region = this.joniMatcher.getRegion();
      return (region == null) ? 0 : (region.numRegs - 1);
    }
  }
}
