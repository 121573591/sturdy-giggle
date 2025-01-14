package org.openjdk.nashorn.internal.runtime.regexp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import org.openjdk.nashorn.internal.parser.Lexer;
import org.openjdk.nashorn.internal.parser.Scanner;
import org.openjdk.nashorn.internal.runtime.BitVector;

final class RegExpScanner extends Scanner {
  private final StringBuilder sb;
  
  private final Map<Character, Integer> expected = new HashMap<>();
  
  private final List<Capture> caps = new LinkedList<>();
  
  private final LinkedList<Integer> forwardReferences = new LinkedList<>();
  
  private int negLookaheadLevel;
  
  private int negLookaheadGroup;
  
  private boolean inCharClass = false;
  
  private boolean inNegativeClass = false;
  
  private static final String NON_IDENT_ESCAPES = "$^*+(){}[]|\\.?-";
  
  private static class Capture {
    private final int negLookaheadLevel;
    
    private final int negLookaheadGroup;
    
    Capture(int negLookaheadGroup, int negLookaheadLevel) {
      this.negLookaheadGroup = negLookaheadGroup;
      this.negLookaheadLevel = negLookaheadLevel;
    }
    
    boolean canBeReferencedFrom(int group, int level) {
      return (this.negLookaheadLevel == 0 || (group == this.negLookaheadGroup && level >= this.negLookaheadLevel));
    }
  }
  
  private RegExpScanner(String string) {
    super(string);
    this.sb = new StringBuilder(this.limit);
    reset(0);
    this.expected.put(Character.valueOf(']'), Integer.valueOf(0));
    this.expected.put(Character.valueOf('}'), Integer.valueOf(0));
  }
  
  private void processForwardReferences() {
    Iterator<Integer> iterator = this.forwardReferences.descendingIterator();
    while (iterator.hasNext()) {
      int pos = ((Integer)iterator.next()).intValue();
      int num = ((Integer)iterator.next()).intValue();
      if (num > this.caps.size()) {
        StringBuilder buffer = new StringBuilder();
        octalOrLiteral(Integer.toString(num), buffer);
        this.sb.insert(pos, buffer);
      } 
    } 
    this.forwardReferences.clear();
  }
  
  public static RegExpScanner scan(String string) {
    RegExpScanner scanner = new RegExpScanner(string);
    try {
      scanner.disjunction();
    } catch (Exception e) {
      throw new PatternSyntaxException(e.getMessage(), string, scanner.position);
    } 
    if (scanner.position != string.length()) {
      String p = scanner.getStringBuilder().toString();
      throw new PatternSyntaxException(string, p, p.length() + 1);
    } 
    scanner.processForwardReferences();
    return scanner;
  }
  
  final StringBuilder getStringBuilder() {
    return this.sb;
  }
  
  String getJavaPattern() {
    return this.sb.toString();
  }
  
  BitVector getGroupsInNegativeLookahead() {
    BitVector vec = null;
    for (int i = 0; i < this.caps.size(); i++) {
      Capture cap = this.caps.get(i);
      if (cap.negLookaheadLevel > 0) {
        if (vec == null)
          vec = new BitVector((this.caps.size() + 1)); 
        vec.set((i + 1));
      } 
    } 
    return vec;
  }
  
  private boolean commit(int n) {
    switch (n) {
      case 1:
        this.sb.append(this.ch0);
        skip(1);
        return true;
      case 2:
        this.sb.append(this.ch0);
        this.sb.append(this.ch1);
        skip(2);
        return true;
      case 3:
        this.sb.append(this.ch0);
        this.sb.append(this.ch1);
        this.sb.append(this.ch2);
        skip(3);
        return true;
    } 
    assert false : "Should not reach here";
    return true;
  }
  
  private void restart(int startIn, int startOut) {
    reset(startIn);
    this.sb.setLength(startOut);
  }
  
  private void push(char ch) {
    this.expected.put(Character.valueOf(ch), Integer.valueOf(((Integer)this.expected.get(Character.valueOf(ch))).intValue() + 1));
  }
  
  private void pop(char ch) {
    this.expected.put(Character.valueOf(ch), Integer.valueOf(Math.min(0, ((Integer)this.expected.get(Character.valueOf(ch))).intValue() - 1)));
  }
  
  private void disjunction() {
    while (true) {
      alternative();
      if (this.ch0 == '|') {
        commit(1);
        continue;
      } 
      break;
    } 
  }
  
  private void alternative() {
    while (term());
  }
  
  private boolean term() {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (assertion())
      return true; 
    if (atom()) {
      quantifier();
      return true;
    } 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean assertion() {
    boolean isNegativeLookahead;
    int startIn = this.position;
    int startOut = this.sb.length();
    switch (this.ch0) {
      case '$':
      case '^':
        return commit(1);
      case '\\':
        if (this.ch1 == 'b' || this.ch1 == 'B')
          return commit(2); 
        break;
      case '(':
        if (this.ch1 != '?')
          break; 
        if (this.ch2 != '=' && this.ch2 != '!')
          break; 
        isNegativeLookahead = (this.ch2 == '!');
        commit(3);
        if (isNegativeLookahead) {
          if (this.negLookaheadLevel == 0)
            this.negLookaheadGroup++; 
          this.negLookaheadLevel++;
        } 
        disjunction();
        if (isNegativeLookahead)
          this.negLookaheadLevel--; 
        if (this.ch0 == ')')
          return commit(1); 
        break;
    } 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean quantifier() {
    if (quantifierPrefix()) {
      if (this.ch0 == '?')
        commit(1); 
      return true;
    } 
    return false;
  }
  
  private boolean quantifierPrefix() {
    int startIn = this.position;
    int startOut = this.sb.length();
    switch (this.ch0) {
      case '*':
      case '+':
      case '?':
        return commit(1);
      case '{':
        commit(1);
        if (!decimalDigits())
          break; 
        push('}');
        if (this.ch0 == ',') {
          commit(1);
          decimalDigits();
        } 
        if (this.ch0 == '}') {
          pop('}');
          commit(1);
        } else {
          restart(startIn, startOut);
          return false;
        } 
        return true;
    } 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean atom() {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (patternCharacter())
      return true; 
    if (this.ch0 == '.')
      return commit(1); 
    if (this.ch0 == '\\') {
      commit(1);
      if (atomEscape())
        return true; 
    } 
    if (characterClass())
      return true; 
    if (this.ch0 == '(') {
      commit(1);
      if (this.ch0 == '?' && this.ch1 == ':') {
        commit(2);
      } else {
        this.caps.add(new Capture(this.negLookaheadGroup, this.negLookaheadLevel));
      } 
      disjunction();
      if (this.ch0 == ')') {
        commit(1);
        return true;
      } 
    } 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean patternCharacter() {
    int n;
    if (atEOF())
      return false; 
    switch (this.ch0) {
      case '$':
      case '(':
      case ')':
      case '*':
      case '+':
      case '.':
      case '?':
      case '[':
      case '\\':
      case '^':
      case '|':
        return false;
      case ']':
      case '}':
        n = ((Integer)this.expected.get(Character.valueOf(this.ch0))).intValue();
        if (n != 0)
          return false; 
      case '{':
        if (!quantifierPrefix()) {
          this.sb.append('\\');
          return commit(1);
        } 
        return false;
    } 
    return commit(1);
  }
  
  private boolean atomEscape() {
    return (decimalEscape() || characterClassEscape() || characterEscape() || identityEscape());
  }
  
  private boolean characterEscape() {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (controlEscape())
      return true; 
    if (this.ch0 == 'c') {
      commit(1);
      if (controlLetter())
        return true; 
      restart(startIn, startOut);
    } 
    if (hexEscapeSequence() || unicodeEscapeSequence())
      return true; 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean scanEscapeSequence(char leader, int length) {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (this.ch0 != leader)
      return false; 
    commit(1);
    for (int i = 0; i < length; i++) {
      char ch0l = Character.toLowerCase(this.ch0);
      if ((ch0l >= 'a' && ch0l <= 'f') || isDecimalDigit(this.ch0)) {
        commit(1);
      } else {
        restart(startIn, startOut);
        return false;
      } 
    } 
    return true;
  }
  
  private boolean hexEscapeSequence() {
    return scanEscapeSequence('x', 2);
  }
  
  private boolean unicodeEscapeSequence() {
    return scanEscapeSequence('u', 4);
  }
  
  private boolean controlEscape() {
    switch (this.ch0) {
      case 'f':
      case 'n':
      case 'r':
      case 't':
      case 'v':
        return commit(1);
    } 
    return false;
  }
  
  private boolean controlLetter() {
    if ((this.ch0 >= 'A' && this.ch0 <= 'Z') || (this.ch0 >= 'a' && this.ch0 <= 'z') || (this.inCharClass && (
      isDecimalDigit(this.ch0) || this.ch0 == '_'))) {
      this.sb.setLength(this.sb.length() - 1);
      unicode(this.ch0 % 32, this.sb);
      skip(1);
      return true;
    } 
    return false;
  }
  
  private boolean identityEscape() {
    if (atEOF())
      throw new RuntimeException("\\ at end of pattern"); 
    if (this.ch0 == 'c') {
      this.sb.append('\\');
    } else if ("$^*+(){}[]|\\.?-".indexOf(this.ch0) == -1) {
      this.sb.setLength(this.sb.length() - 1);
    } 
    return commit(1);
  }
  
  private boolean decimalEscape() {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (this.ch0 == '0' && !isOctalDigit(this.ch1)) {
      skip(1);
      this.sb.append("\000");
      return true;
    } 
    if (isDecimalDigit(this.ch0)) {
      if (this.ch0 == '0') {
        if (this.inCharClass) {
          int octalValue = 0;
          while (isOctalDigit(this.ch0)) {
            octalValue = octalValue * 8 + this.ch0 - 48;
            skip(1);
          } 
          unicode(octalValue, this.sb);
        } else {
          decimalDigits();
        } 
      } else {
        int decimalValue = 0;
        while (isDecimalDigit(this.ch0)) {
          decimalValue = decimalValue * 10 + this.ch0 - 48;
          skip(1);
        } 
        if (this.inCharClass) {
          this.sb.setLength(this.sb.length() - 1);
          octalOrLiteral(Integer.toString(decimalValue), this.sb);
        } else if (decimalValue <= this.caps.size()) {
          Capture capture = this.caps.get(decimalValue - 1);
          if (!capture.canBeReferencedFrom(this.negLookaheadGroup, this.negLookaheadLevel)) {
            this.sb.setLength(this.sb.length() - 1);
          } else {
            this.sb.append(decimalValue);
          } 
        } else {
          this.sb.setLength(this.sb.length() - 1);
          this.forwardReferences.add(Integer.valueOf(decimalValue));
          this.forwardReferences.add(Integer.valueOf(this.sb.length()));
        } 
      } 
      return true;
    } 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean characterClassEscape() {
    switch (this.ch0) {
      case 's':
        if (RegExpFactory.usesJavaUtilRegex()) {
          this.sb.setLength(this.sb.length() - 1);
          if (this.inCharClass) {
            this.sb.append(Lexer.getWhitespaceRegExp());
          } else {
            this.sb.append('[').append(Lexer.getWhitespaceRegExp()).append(']');
          } 
          skip(1);
          return true;
        } 
        return commit(1);
      case 'S':
        if (RegExpFactory.usesJavaUtilRegex()) {
          this.sb.setLength(this.sb.length() - 1);
          this.sb.append(this.inNegativeClass ? "&&[" : "[^").append(Lexer.getWhitespaceRegExp()).append(']');
          skip(1);
          return true;
        } 
        return commit(1);
      case 'D':
      case 'W':
      case 'd':
      case 'w':
        return commit(1);
    } 
    return false;
  }
  
  private boolean characterClass() {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (this.ch0 == '[')
      try {
        this.inCharClass = true;
        push(']');
        commit(1);
        if (this.ch0 == '^') {
          this.inNegativeClass = true;
          commit(1);
        } 
        if (classRanges() && this.ch0 == ']') {
          pop(']');
          commit(1);
          if (this.position == startIn + 2) {
            this.sb.setLength(this.sb.length() - 1);
            this.sb.append("^\\s\\S]");
          } else if (this.position == startIn + 3 && this.inNegativeClass) {
            this.sb.setLength(this.sb.length() - 2);
            this.sb.append("\\s\\S]");
          } 
          return true;
        } 
      } finally {
        this.inCharClass = false;
        this.inNegativeClass = false;
      }  
    restart(startIn, startOut);
    return false;
  }
  
  private boolean classRanges() {
    nonemptyClassRanges();
    return true;
  }
  
  private boolean nonemptyClassRanges() {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (classAtom()) {
      if (this.ch0 == '-') {
        commit(1);
        if (classAtom() && classRanges())
          return true; 
      } 
      nonemptyClassRangesNoDash();
      return true;
    } 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean nonemptyClassRangesNoDash() {
    int startIn = this.position;
    int startOut = this.sb.length();
    if (classAtomNoDash()) {
      if (this.ch0 == '-') {
        commit(1);
        if (classAtom() && classRanges())
          return true; 
      } 
      nonemptyClassRangesNoDash();
      return true;
    } 
    if (classAtom())
      return true; 
    restart(startIn, startOut);
    return false;
  }
  
  private boolean classAtom() {
    if (this.ch0 == '-')
      return commit(1); 
    return classAtomNoDash();
  }
  
  private boolean classAtomNoDash() {
    if (atEOF())
      return false; 
    int startIn = this.position;
    int startOut = this.sb.length();
    switch (this.ch0) {
      case '-':
      case ']':
        return false;
      case '[':
        this.sb.append('\\');
        return commit(1);
      case '\\':
        commit(1);
        if (classEscape())
          return true; 
        restart(startIn, startOut);
        return false;
    } 
    return commit(1);
  }
  
  private boolean classEscape() {
    if (decimalEscape())
      return true; 
    if (this.ch0 == 'b') {
      this.sb.setLength(this.sb.length() - 1);
      this.sb.append('\b');
      skip(1);
      return true;
    } 
    return (characterEscape() || characterClassEscape() || identityEscape());
  }
  
  private boolean decimalDigits() {
    if (!isDecimalDigit(this.ch0))
      return false; 
    while (isDecimalDigit(this.ch0))
      commit(1); 
    return true;
  }
  
  private static void unicode(int value, StringBuilder buffer) {
    String hex = Integer.toHexString(value);
    buffer.append('u');
    for (int i = 0; i < 4 - hex.length(); i++)
      buffer.append('0'); 
    buffer.append(hex);
  }
  
  private static void octalOrLiteral(String numberLiteral, StringBuilder buffer) {
    int length = numberLiteral.length();
    int octalValue = 0;
    int pos = 0;
    while (pos < length && octalValue < 32) {
      char ch = numberLiteral.charAt(pos);
      if (isOctalDigit(ch)) {
        octalValue = octalValue * 8 + ch - 48;
        pos++;
      } 
    } 
    if (octalValue > 0) {
      buffer.append('\\');
      unicode(octalValue, buffer);
      buffer.append(numberLiteral.substring(pos));
    } else {
      buffer.append(numberLiteral);
    } 
  }
  
  private static boolean isOctalDigit(char ch) {
    return (ch >= '0' && ch <= '7');
  }
  
  private static boolean isDecimalDigit(char ch) {
    return (ch >= '0' && ch <= '9');
  }
}
