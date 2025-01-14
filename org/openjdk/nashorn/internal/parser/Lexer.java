package org.openjdk.nashorn.internal.parser;

import java.io.Serializable;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSErrorType;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.options.Options;

public class Lexer extends Scanner {
  private static final boolean XML_LITERALS = Options.getBooleanProperty("nashorn.lexer.xmlliterals");
  
  private final Source source;
  
  private final TokenStream stream;
  
  private final boolean scripting;
  
  private final boolean es6;
  
  private final boolean nested;
  
  int pendingLine;
  
  private int linePosition;
  
  private TokenType last;
  
  private final boolean pauseOnFunctionBody;
  
  private boolean pauseOnNextLeftBrace;
  
  private int templateExpressionOpenBraces;
  
  private static final String JAVASCRIPT_OTHER_WHITESPACE = "    ᠎             　﻿";
  
  private static final String JAVASCRIPT_WHITESPACE_IN_REGEXP = "\\u000a\\u000d\\u2028\\u2029\\u0009\\u0020\\u000b\\u000c\\u00a0\\u1680\\u180e\\u2000\\u2001\\u2002\\u2003\\u2004\\u2005\\u2006\\u2007\\u2008\\u2009\\u200a\\u202f\\u205f\\u3000\\ufeff";
  
  static String unicodeEscape(char ch) {
    StringBuilder sb = new StringBuilder();
    sb.append("\\u");
    String hex = Integer.toHexString(ch);
    for (int i = hex.length(); i < 4; i++)
      sb.append('0'); 
    sb.append(hex);
    return sb.toString();
  }
  
  public Lexer(Source source, TokenStream stream) {
    this(source, stream, false, false);
  }
  
  public Lexer(Source source, TokenStream stream, boolean scripting, boolean es6) {
    this(source, 0, source.getLength(), stream, scripting, es6, false);
  }
  
  public Lexer(Source source, int start, int len, TokenStream stream, boolean scripting, boolean es6, boolean pauseOnFunctionBody) {
    super(source.getContent(), 1, start, len);
    this.source = source;
    this.stream = stream;
    this.scripting = scripting;
    this.es6 = es6;
    this.nested = false;
    this.pendingLine = 1;
    this.last = TokenType.EOL;
    this.pauseOnFunctionBody = pauseOnFunctionBody;
  }
  
  private Lexer(Lexer lexer, State state) {
    super(lexer, state);
    this.source = lexer.source;
    this.stream = lexer.stream;
    this.scripting = lexer.scripting;
    this.es6 = lexer.es6;
    this.nested = true;
    this.pendingLine = state.pendingLine;
    this.linePosition = state.linePosition;
    this.last = TokenType.EOL;
    this.pauseOnFunctionBody = false;
  }
  
  static class State extends Scanner.State {
    public final int pendingLine;
    
    public final int linePosition;
    
    public final TokenType last;
    
    State(int position, int limit, int line, int pendingLine, int linePosition, TokenType last) {
      super(position, limit, line);
      this.pendingLine = pendingLine;
      this.linePosition = linePosition;
      this.last = last;
    }
  }
  
  State saveState() {
    return new State(this.position, this.limit, this.line, this.pendingLine, this.linePosition, this.last);
  }
  
  void restoreState(State state) {
    restoreState(state);
    this.pendingLine = state.pendingLine;
    this.linePosition = state.linePosition;
    this.last = state.last;
  }
  
  protected void add(TokenType type, int start, int end) {
    this.last = type;
    if (type == TokenType.EOL) {
      this.pendingLine = end;
      this.linePosition = start;
    } else {
      if (this.pendingLine != -1) {
        this.stream.put(Token.toDesc(TokenType.EOL, this.linePosition, this.pendingLine));
        this.pendingLine = -1;
      } 
      this.stream.put(Token.toDesc(type, start, end - start));
    } 
  }
  
  protected void add(TokenType type, int start) {
    add(type, start, this.position);
  }
  
  public static String getWhitespaceRegExp() {
    return "\\u000a\\u000d\\u2028\\u2029\\u0009\\u0020\\u000b\\u000c\\u00a0\\u1680\\u180e\\u2000\\u2001\\u2002\\u2003\\u2004\\u2005\\u2006\\u2007\\u2008\\u2009\\u200a\\u202f\\u205f\\u3000\\ufeff";
  }
  
  private void skipEOL(boolean addEOL) {
    if (this.ch0 == '\r') {
      skip(1);
      if (this.ch0 == '\n')
        skip(1); 
    } else {
      skip(1);
    } 
    this.line++;
    if (addEOL)
      add(TokenType.EOL, this.position, this.line); 
  }
  
  private void skipLine(boolean addEOL) {
    while (!isEOL(this.ch0) && !atEOF())
      skip(1); 
    skipEOL(addEOL);
  }
  
  public static boolean isJSWhitespace(char ch) {
    return (ch == ' ' || (ch >= '\t' && ch <= '\r') || (ch >= ' ' && 
      
      isOtherJSWhitespace(ch)));
  }
  
  private static boolean isOtherJSWhitespace(char ch) {
    return ("    ᠎             　﻿".indexOf(ch) != -1);
  }
  
  public static boolean isJSEOL(char ch) {
    return (ch == '\n' || ch == '\r' || ch == ' ' || ch == ' ');
  }
  
  protected boolean isStringDelimiter(char ch) {
    return (ch == '\'' || ch == '"');
  }
  
  private static boolean isTemplateDelimiter(char ch) {
    return (ch == '`');
  }
  
  protected boolean isWhitespace(char ch) {
    return isJSWhitespace(ch);
  }
  
  protected boolean isEOL(char ch) {
    return isJSEOL(ch);
  }
  
  private void skipWhitespace(boolean addEOL) {
    while (isWhitespace(this.ch0)) {
      if (isEOL(this.ch0)) {
        skipEOL(addEOL);
        continue;
      } 
      skip(1);
    } 
  }
  
  protected boolean skipComments() {
    int start = this.position;
    if (this.ch0 == '/') {
      if (this.ch1 == '/') {
        skip(2);
        boolean directiveComment = false;
        if ((this.ch0 == '#' || this.ch0 == '@') && this.ch1 == ' ')
          directiveComment = true; 
        while (!atEOF() && !isEOL(this.ch0))
          skip(1); 
        add(directiveComment ? TokenType.DIRECTIVE_COMMENT : TokenType.COMMENT, start);
        return true;
      } 
      if (this.ch1 == '*') {
        skip(2);
        while (!atEOF() && (this.ch0 != '*' || this.ch1 != '/')) {
          if (isEOL(this.ch0)) {
            skipEOL(true);
            continue;
          } 
          skip(1);
        } 
        if (atEOF()) {
          add(TokenType.ERROR, start);
        } else {
          skip(2);
        } 
        add(TokenType.COMMENT, start);
        return true;
      } 
    } else if (this.ch0 == '#') {
      assert this.scripting;
      skip(1);
      while (!atEOF() && !isEOL(this.ch0))
        skip(1); 
      add(TokenType.COMMENT, start);
      return true;
    } 
    return false;
  }
  
  public RegexToken valueOfPattern(int start, int length) {
    int savePosition = this.position;
    reset(start);
    StringBuilder sb = new StringBuilder(length);
    skip(1);
    boolean inBrackets = false;
    while ((!atEOF() && this.ch0 != '/' && !isEOL(this.ch0)) || inBrackets) {
      if (this.ch0 == '\\') {
        sb.append(this.ch0);
        sb.append(this.ch1);
        skip(2);
        continue;
      } 
      if (this.ch0 == '[') {
        inBrackets = true;
      } else if (this.ch0 == ']') {
        inBrackets = false;
      } 
      sb.append(this.ch0);
      skip(1);
    } 
    String regex = sb.toString();
    skip(1);
    String options = this.source.getString(this.position, scanIdentifier());
    reset(savePosition);
    return new RegexToken(regex, options);
  }
  
  public boolean canStartLiteral(TokenType token) {
    return (token.startsWith('/') || ((this.scripting || XML_LITERALS) && token.startsWith('<')));
  }
  
  protected boolean scanLiteral(long token, TokenType startTokenType, LineInfoReceiver lir) {
    if (!canStartLiteral(startTokenType))
      return false; 
    if (this.stream.get(this.stream.last()) != token)
      return false; 
    State state = saveState();
    reset(Token.descPosition(token));
    if (this.ch0 == '/')
      return scanRegEx(); 
    if (this.ch0 == '<') {
      if (this.ch1 == '<')
        return scanHereString(lir, state); 
      if (Character.isJavaIdentifierStart(this.ch1))
        return scanXMLLiteral(); 
    } 
    return false;
  }
  
  private boolean scanRegEx() {
    assert this.ch0 == '/';
    if (this.ch1 != '/' && this.ch1 != '*') {
      int start = this.position;
      skip(1);
      boolean inBrackets = false;
      while (!atEOF() && (this.ch0 != '/' || inBrackets) && !isEOL(this.ch0)) {
        if (this.ch0 == '\\') {
          skip(1);
          if (isEOL(this.ch0)) {
            reset(start);
            return false;
          } 
        } else if (this.ch0 == '[') {
          inBrackets = true;
        } else if (this.ch0 == ']') {
          inBrackets = false;
        } 
        skip(1);
      } 
      if (this.ch0 == '/') {
        skip(1);
        while ((!atEOF() && Character.isJavaIdentifierPart(this.ch0)) || (this.ch0 == '\\' && this.ch1 == 'u'))
          skip(1); 
        add(TokenType.REGEX, start);
        return true;
      } 
      reset(start);
    } 
    return false;
  }
  
  protected static int convertDigit(char ch, int base) {
    int digit;
    if ('0' <= ch && ch <= '9') {
      digit = ch - 48;
    } else if ('A' <= ch && ch <= 'Z') {
      digit = ch - 65 + 10;
    } else if ('a' <= ch && ch <= 'z') {
      digit = ch - 97 + 10;
    } else {
      return -1;
    } 
    return (digit < base) ? digit : -1;
  }
  
  private int hexSequence(int length, TokenType type) {
    int value = 0;
    for (int i = 0; i < length; i++) {
      int digit = convertDigit(this.ch0, 16);
      if (digit == -1) {
        error(message("invalid.hex", new String[0]), type, this.position, this.limit);
        return (i == 0) ? -1 : value;
      } 
      value = digit | value << 4;
      skip(1);
    } 
    return value;
  }
  
  private int octalSequence() {
    int value = 0;
    for (int i = 0; i < 3; i++) {
      int digit = convertDigit(this.ch0, 8);
      if (digit == -1)
        break; 
      value = digit | value << 3;
      skip(1);
      if (i == 1 && value >= 32)
        break; 
    } 
    return value;
  }
  
  private String valueOfIdent(int start, int length) throws RuntimeException {
    int savePosition = this.position;
    int end = start + length;
    reset(start);
    StringBuilder sb = new StringBuilder(length);
    while (!atEOF() && this.position < end && !isEOL(this.ch0)) {
      if (this.ch0 == '\\' && this.ch1 == 'u') {
        skip(2);
        int ch = hexSequence(4, TokenType.IDENT);
        assert !isWhitespace((char)ch);
        assert ch >= 0;
        sb.append((char)ch);
        continue;
      } 
      sb.append(this.ch0);
      skip(1);
    } 
    reset(savePosition);
    return sb.toString();
  }
  
  private void scanIdentifierOrKeyword() {
    int start = this.position;
    int length = scanIdentifier();
    TokenType type = TokenLookup.lookupKeyword(this.content, start, length);
    if (type == TokenType.FUNCTION && this.pauseOnFunctionBody)
      this.pauseOnNextLeftBrace = true; 
    add(type, start);
  }
  
  private String valueOfString(int start, int length, boolean strict) throws RuntimeException {
    int savePosition = this.position;
    int end = start + length;
    reset(start);
    StringBuilder sb = new StringBuilder(length);
    while (this.position < end) {
      if (this.ch0 == '\\') {
        int ch;
        skip(1);
        char next = this.ch0;
        int afterSlash = this.position;
        skip(1);
        switch (next) {
          case '0':
          case '1':
          case '2':
          case '3':
          case '4':
          case '5':
          case '6':
          case '7':
            if (strict)
              if (next != '0' || (this.ch0 >= '0' && this.ch0 <= '9'))
                error(message("strict.no.octal", new String[0]), TokenType.STRING, this.position, this.limit);  
            reset(afterSlash);
            ch = octalSequence();
            if (ch < 0) {
              sb.append('\\');
              sb.append('x');
              continue;
            } 
            sb.append((char)ch);
            continue;
          case 'n':
            sb.append('\n');
            continue;
          case 't':
            sb.append('\t');
            continue;
          case 'b':
            sb.append('\b');
            continue;
          case 'f':
            sb.append('\f');
            continue;
          case 'r':
            sb.append('\r');
            continue;
          case '\'':
            sb.append('\'');
            continue;
          case '"':
            sb.append('"');
            continue;
          case '\\':
            sb.append('\\');
            continue;
          case '\r':
            if (this.ch0 == '\n')
              skip(1); 
            continue;
          case '\n':
          case ' ':
          case ' ':
            continue;
          case 'x':
            ch = hexSequence(2, TokenType.STRING);
            if (ch < 0) {
              sb.append('\\');
              sb.append('x');
              continue;
            } 
            sb.append((char)ch);
            continue;
          case 'u':
            ch = hexSequence(4, TokenType.STRING);
            if (ch < 0) {
              sb.append('\\');
              sb.append('u');
              continue;
            } 
            sb.append((char)ch);
            continue;
          case 'v':
            sb.append('\013');
            continue;
        } 
        sb.append(next);
        continue;
      } 
      if (this.ch0 == '\r') {
        sb.append('\n');
        skip((this.ch1 == '\n') ? 2 : 1);
        continue;
      } 
      sb.append(this.ch0);
      skip(1);
    } 
    reset(savePosition);
    return sb.toString();
  }
  
  protected void scanString(boolean add) {
    TokenType type = TokenType.STRING;
    char quote = this.ch0;
    skip(1);
    State stringState = saveState();
    while (!atEOF() && this.ch0 != quote && !isEOL(this.ch0)) {
      if (this.ch0 == '\\') {
        type = TokenType.ESCSTRING;
        skip(1);
        if (isEOL(this.ch0)) {
          skipEOL(false);
          continue;
        } 
      } 
      skip(1);
    } 
    if (this.ch0 == quote) {
      skip(1);
    } else {
      error(message("missing.close.quote", new String[0]), TokenType.STRING, this.position, this.limit);
    } 
    if (add) {
      stringState.setLimit(this.position - 1);
      if (this.scripting && !stringState.isEmpty()) {
        switch (quote) {
          case '`':
            add(TokenType.EXECSTRING, stringState.position, stringState.limit);
            add(TokenType.LBRACE, stringState.position, stringState.position);
            editString(type, stringState);
            add(TokenType.RBRACE, stringState.limit, stringState.limit);
            break;
          case '"':
            editString(type, stringState);
            break;
          case '\'':
            add(type, stringState.position, stringState.limit);
            break;
        } 
      } else {
        add(type, stringState.position, stringState.limit);
      } 
    } 
  }
  
  private void scanTemplate() {
    assert this.ch0 == '`';
    TokenType type = TokenType.TEMPLATE;
    skip(1);
    State stringState = saveState();
    while (!atEOF()) {
      if (this.ch0 == '`') {
        skip(1);
        stringState.setLimit(this.position - 1);
        add((type == TokenType.TEMPLATE) ? type : TokenType.TEMPLATE_TAIL, stringState.position, stringState.limit);
        return;
      } 
      if (this.ch0 == '$' && this.ch1 == '{') {
        skip(2);
        stringState.setLimit(this.position - 2);
        add((type == TokenType.TEMPLATE) ? TokenType.TEMPLATE_HEAD : type, stringState.position, stringState.limit);
        Lexer expressionLexer = new Lexer(this, saveState());
        expressionLexer.templateExpressionOpenBraces = 1;
        expressionLexer.lexify();
        restoreState(expressionLexer.saveState());
        assert this.ch0 == '}';
        type = TokenType.TEMPLATE_MIDDLE;
        skip(1);
        stringState = saveState();
        continue;
      } 
      if (this.ch0 == '\\') {
        skip(1);
        if (isEOL(this.ch0)) {
          skipEOL(false);
          continue;
        } 
      } else if (isEOL(this.ch0)) {
        skipEOL(false);
        continue;
      } 
      skip(1);
    } 
    error(message("missing.close.quote", new String[0]), TokenType.TEMPLATE, this.position, this.limit);
  }
  
  private static Number valueOf(String valueString, int radix) throws NumberFormatException {
    try {
      return Integer.valueOf(Integer.parseInt(valueString, radix));
    } catch (NumberFormatException e) {
      if (radix == 10)
        return Double.valueOf(valueString); 
      double value = 0.0D;
      for (int i = 0; i < valueString.length(); i++) {
        char ch = valueString.charAt(i);
        int digit = convertDigit(ch, radix);
        value *= radix;
        value += digit;
      } 
      return Double.valueOf(value);
    } 
  }
  
  protected void scanNumber() {
    int start = this.position;
    TokenType type = TokenType.DECIMAL;
    int digit = convertDigit(this.ch0, 10);
    if (digit == 0 && (this.ch1 == 'x' || this.ch1 == 'X') && convertDigit(this.ch2, 16) != -1) {
      skip(3);
      while (convertDigit(this.ch0, 16) != -1)
        skip(1); 
      type = TokenType.HEXADECIMAL;
    } else if (digit == 0 && this.es6 && (this.ch1 == 'o' || this.ch1 == 'O') && convertDigit(this.ch2, 8) != -1) {
      skip(3);
      while (convertDigit(this.ch0, 8) != -1)
        skip(1); 
      type = TokenType.OCTAL;
    } else if (digit == 0 && this.es6 && (this.ch1 == 'b' || this.ch1 == 'B') && convertDigit(this.ch2, 2) != -1) {
      skip(3);
      while (convertDigit(this.ch0, 2) != -1)
        skip(1); 
      type = TokenType.BINARY_NUMBER;
    } else {
      boolean octal = (digit == 0);
      if (digit != -1)
        skip(1); 
      while ((digit = convertDigit(this.ch0, 10)) != -1) {
        octal = (octal && digit < 8);
        skip(1);
      } 
      if (octal && this.position - start > 1) {
        type = TokenType.OCTAL_LEGACY;
      } else if (this.ch0 == '.' || this.ch0 == 'E' || this.ch0 == 'e') {
        if (this.ch0 == '.') {
          skip(1);
          while (convertDigit(this.ch0, 10) != -1)
            skip(1); 
        } 
        if (this.ch0 == 'E' || this.ch0 == 'e') {
          skip(1);
          if (this.ch0 == '+' || this.ch0 == '-')
            skip(1); 
          while (convertDigit(this.ch0, 10) != -1)
            skip(1); 
        } 
        type = TokenType.FLOATING;
      } 
    } 
    if (Character.isJavaIdentifierStart(this.ch0))
      error(message("missing.space.after.number", new String[0]), type, this.position, 1); 
    add(type, start);
  }
  
  XMLToken valueOfXML(int start, int length) {
    return new XMLToken(this.source.getString(start, length));
  }
  
  private boolean scanXMLLiteral() {
    assert this.ch0 == '<' && Character.isJavaIdentifierStart(this.ch1);
    if (XML_LITERALS) {
      int start = this.position;
      int openCount = 0;
      do {
        if (this.ch0 == '<') {
          if (this.ch1 == '/' && Character.isJavaIdentifierStart(this.ch2)) {
            skip(3);
            openCount--;
          } else if (Character.isJavaIdentifierStart(this.ch1)) {
            skip(2);
            openCount++;
          } else if (this.ch1 == '?') {
            skip(2);
          } else if (this.ch1 == '!' && this.ch2 == '-' && this.ch3 == '-') {
            skip(4);
          } else {
            reset(start);
            return false;
          } 
          while (!atEOF() && this.ch0 != '>') {
            if (this.ch0 == '/' && this.ch1 == '>') {
              openCount--;
              skip(1);
              break;
            } 
            if (this.ch0 == '"' || this.ch0 == '\'') {
              scanString(false);
              continue;
            } 
            skip(1);
          } 
          if (this.ch0 != '>') {
            reset(start);
            return false;
          } 
          skip(1);
        } else {
          if (atEOF()) {
            reset(start);
            return false;
          } 
          skip(1);
        } 
      } while (openCount > 0);
      add(TokenType.XML, start);
      return true;
    } 
    return false;
  }
  
  private int scanIdentifier() {
    int start = this.position;
    if (this.ch0 == '\\' && this.ch1 == 'u') {
      skip(2);
      int ch = hexSequence(4, TokenType.IDENT);
      if (!Character.isJavaIdentifierStart(ch))
        error(message("illegal.identifier.character", new String[0]), TokenType.IDENT, start, this.position); 
    } else if (!Character.isJavaIdentifierStart(this.ch0)) {
      return 0;
    } 
    while (!atEOF()) {
      if (this.ch0 == '\\' && this.ch1 == 'u') {
        skip(2);
        int ch = hexSequence(4, TokenType.IDENT);
        if (!Character.isJavaIdentifierPart(ch))
          error(message("illegal.identifier.character", new String[0]), TokenType.IDENT, start, this.position); 
        continue;
      } 
      if (Character.isJavaIdentifierPart(this.ch0))
        skip(1); 
    } 
    return this.position - start;
  }
  
  private boolean identifierEqual(int aStart, int aLength, int bStart, int bLength) {
    if (aLength == bLength) {
      for (int i = 0; i < aLength; i++) {
        if (this.content[aStart + i] != this.content[bStart + i])
          return false; 
      } 
      return true;
    } 
    return false;
  }
  
  private boolean hasHereMarker(int identStart, int identLength) {
    skipWhitespace(false);
    return identifierEqual(identStart, identLength, this.position, scanIdentifier());
  }
  
  private static class EditStringLexer extends Lexer {
    final TokenType stringType;
    
    EditStringLexer(Lexer lexer, TokenType stringType, Lexer.State stringState) {
      super(lexer, stringState);
      this.stringType = stringType;
    }
    
    public void lexify() {
      int stringStart = this.position;
      boolean primed = false;
      while (!atEOF()) {
        if (this.ch0 == '\\' && this.stringType == TokenType.ESCSTRING) {
          skip(2);
          continue;
        } 
        if (this.ch0 == '$' && this.ch1 == '{') {
          if (!primed || stringStart != this.position) {
            if (primed)
              add(TokenType.ADD, stringStart, stringStart + 1); 
            add(this.stringType, stringStart, this.position);
            primed = true;
          } 
          skip(2);
          Lexer.State expressionState = saveState();
          int braceCount = 1;
          while (!atEOF()) {
            if (this.ch0 == '}') {
              if (--braceCount == 0)
                break; 
            } else if (this.ch0 == '{') {
              braceCount++;
            } 
            skip(1);
          } 
          if (braceCount != 0)
            error(Lexer.message("edit.string.missing.brace", new String[0]), TokenType.LBRACE, expressionState.position - 1, 1); 
          expressionState.setLimit(this.position);
          skip(1);
          stringStart = this.position;
          add(TokenType.ADD, expressionState.position, expressionState.position + 1);
          add(TokenType.LPAREN, expressionState.position, expressionState.position + 1);
          Lexer lexer = new Lexer(this, expressionState);
          lexer.lexify();
          add(TokenType.RPAREN, this.position - 1, this.position);
          continue;
        } 
        skip(1);
      } 
      if (stringStart != this.limit) {
        if (primed)
          add(TokenType.ADD, stringStart, 1); 
        add(this.stringType, stringStart, this.limit);
      } 
    }
  }
  
  private void editString(TokenType stringType, State stringState) {
    EditStringLexer lexer = new EditStringLexer(this, stringType, stringState);
    lexer.lexify();
    this.last = stringType;
  }
  
  private boolean scanHereString(LineInfoReceiver lir, State oldState) {
    assert this.ch0 == '<' && this.ch1 == '<';
    if (this.scripting) {
      State saved = saveState();
      boolean excludeLastEOL = (this.ch2 != '<');
      if (excludeLastEOL) {
        skip(2);
      } else {
        skip(3);
      } 
      char quoteChar = this.ch0;
      boolean noStringEditing = (quoteChar == '"' || quoteChar == '\'');
      if (noStringEditing)
        skip(1); 
      int identStart = this.position;
      int identLength = scanIdentifier();
      if (noStringEditing) {
        if (this.ch0 != quoteChar) {
          error(message("here.non.matching.delimiter", new String[0]), this.last, this.position, this.position);
          restoreState(saved);
          return false;
        } 
        skip(1);
      } 
      if (identLength == 0) {
        restoreState(saved);
        return false;
      } 
      State restState = saveState();
      int lastLine = this.line;
      skipLine(false);
      lastLine++;
      int lastLinePosition = this.position;
      restState.setLimit(this.position);
      if (oldState.position > this.position) {
        restoreState(oldState);
        skipLine(false);
      } 
      State stringState = saveState();
      int stringEnd = this.position;
      while (!atEOF()) {
        skipWhitespace(false);
        lastLinePosition = this.position;
        stringEnd = this.position;
        if (hasHereMarker(identStart, identLength))
          break; 
        skipLine(false);
        lastLine++;
        lastLinePosition = this.position;
        stringEnd = this.position;
      } 
      lir.lineInfo(lastLine, lastLinePosition);
      stringState.setLimit(stringEnd);
      if (stringState.isEmpty() || atEOF()) {
        error(message("here.missing.end.marker", new String[] { this.source.getString(identStart, identLength) }), this.last, this.position, this.position);
        restoreState(saved);
        return false;
      } 
      if (excludeLastEOL) {
        if (this.content[stringEnd - 1] == '\n')
          stringEnd--; 
        if (this.content[stringEnd - 1] == '\r')
          stringEnd--; 
        stringState.setLimit(stringEnd);
      } 
      if (!noStringEditing && !stringState.isEmpty()) {
        editString(TokenType.STRING, stringState);
      } else {
        add(TokenType.STRING, stringState.position, stringState.limit);
      } 
      Lexer restLexer = new Lexer(this, restState);
      restLexer.lexify();
      return true;
    } 
    return false;
  }
  
  public void lexify() {
    while (!this.stream.isFull() || this.nested) {
      skipWhitespace(true);
      if (atEOF()) {
        if (!this.nested)
          add(TokenType.EOF, this.position); 
        break;
      } 
      if (this.ch0 == '/' && skipComments())
        continue; 
      if (this.scripting && this.ch0 == '#' && skipComments())
        continue; 
      if (this.ch0 == '.' && convertDigit(this.ch1, 10) != -1) {
        scanNumber();
        continue;
      } 
      TokenType type;
      if ((type = TokenLookup.lookupOperator(this.ch0, this.ch1, this.ch2, this.ch3)) != null) {
        if (this.templateExpressionOpenBraces > 0)
          if (type == TokenType.LBRACE) {
            this.templateExpressionOpenBraces++;
          } else if (type == TokenType.RBRACE && 
            --this.templateExpressionOpenBraces == 0) {
            break;
          }  
        int typeLength = type.getLength();
        skip(typeLength);
        add(type, this.position - typeLength);
        if (canStartLiteral(type))
          break; 
        if (type == TokenType.LBRACE && this.pauseOnNextLeftBrace) {
          this.pauseOnNextLeftBrace = false;
          break;
        } 
        continue;
      } 
      if (Character.isJavaIdentifierStart(this.ch0) || (this.ch0 == '\\' && this.ch1 == 'u')) {
        scanIdentifierOrKeyword();
        continue;
      } 
      if (isStringDelimiter(this.ch0)) {
        scanString(true);
        continue;
      } 
      if (Character.isDigit(this.ch0)) {
        scanNumber();
        continue;
      } 
      if (isTemplateDelimiter(this.ch0) && this.es6) {
        scanTemplate();
        continue;
      } 
      if (isTemplateDelimiter(this.ch0) && this.scripting) {
        scanString(true);
        continue;
      } 
      skip(1);
      add(TokenType.ERROR, this.position - 1);
    } 
  }
  
  Object getValueOf(long token, boolean strict) {
    String str;
    double value;
    int start = Token.descPosition(token);
    int len = Token.descLength(token);
    switch (Token.descType(token)) {
      case DECIMAL:
        return valueOf(this.source.getString(start, len), 10);
      case HEXADECIMAL:
        return valueOf(this.source.getString(start + 2, len - 2), 16);
      case OCTAL_LEGACY:
        return valueOf(this.source.getString(start, len), 8);
      case OCTAL:
        return valueOf(this.source.getString(start + 2, len - 2), 8);
      case BINARY_NUMBER:
        return valueOf(this.source.getString(start + 2, len - 2), 2);
      case FLOATING:
        str = this.source.getString(start, len);
        value = Double.parseDouble(str);
        if (str.indexOf('.') != -1)
          return Double.valueOf(value); 
        if (JSType.isStrictlyRepresentableAsInt(value))
          return Integer.valueOf((int)value); 
        return Double.valueOf(value);
      case STRING:
      case DIRECTIVE_COMMENT:
        return this.source.getString(start, len);
      case ESCSTRING:
        return valueOfString(start, len, strict);
      case IDENT:
        return valueOfIdent(start, len);
      case REGEX:
        return valueOfPattern(start, len);
      case TEMPLATE:
      case TEMPLATE_HEAD:
      case TEMPLATE_MIDDLE:
      case TEMPLATE_TAIL:
        return valueOfString(start, len, true);
      case XML:
        return valueOfXML(start, len);
    } 
    return null;
  }
  
  public String valueOfRawString(long token) {
    int start = Token.descPosition(token);
    int length = Token.descLength(token);
    int savePosition = this.position;
    int end = start + length;
    reset(start);
    StringBuilder sb = new StringBuilder(length);
    while (this.position < end) {
      if (this.ch0 == '\r') {
        sb.append('\n');
        skip((this.ch1 == '\n') ? 2 : 1);
        continue;
      } 
      sb.append(this.ch0);
      skip(1);
    } 
    reset(savePosition);
    return sb.toString();
  }
  
  protected static String message(String msgId, String... args) {
    return ECMAErrors.getMessage("lexer.error." + msgId, args);
  }
  
  protected void error(String message, TokenType type, int start, int length) throws ParserException {
    long token = Token.toDesc(type, start, length);
    int pos = Token.descPosition(token);
    int lineNum = this.source.getLine(pos);
    int columnNum = this.source.getColumn(pos);
    String formatted = ErrorManager.format(message, this.source, lineNum, columnNum, token);
    throw new ParserException(JSErrorType.SYNTAX_ERROR, formatted, this.source, lineNum, columnNum, token);
  }
  
  public static abstract class LexerToken implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String expression;
    
    protected LexerToken(String expression) {
      this.expression = expression;
    }
    
    public String getExpression() {
      return this.expression;
    }
  }
  
  public static class RegexToken extends LexerToken {
    private static final long serialVersionUID = 1L;
    
    private final String options;
    
    public RegexToken(String expression, String options) {
      super(expression);
      this.options = options;
    }
    
    public String getOptions() {
      return this.options;
    }
    
    public String toString() {
      return "/" + getExpression() + "/" + this.options;
    }
  }
  
  public static class XMLToken extends LexerToken {
    private static final long serialVersionUID = 1L;
    
    public XMLToken(String expression) {
      super(expression);
    }
  }
  
  protected static interface LineInfoReceiver {
    void lineInfo(int param1Int1, int param1Int2);
  }
}
