package org.openjdk.nashorn.internal.parser;

import java.util.HashMap;
import java.util.Map;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSErrorType;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.regexp.RegExpFactory;

public abstract class AbstractParser {
  protected final Source source;
  
  protected final ErrorManager errors;
  
  protected TokenStream stream;
  
  protected int k;
  
  protected long previousToken;
  
  protected long token;
  
  protected TokenType type;
  
  protected TokenType last;
  
  protected int start;
  
  protected int finish;
  
  protected int line;
  
  protected int linePosition;
  
  protected Lexer lexer;
  
  protected boolean isStrictMode;
  
  protected final int lineOffset;
  
  private final Map<String, String> canonicalNames = new HashMap<>();
  
  private static final String SOURCE_URL_PREFIX = "sourceURL=";
  
  protected AbstractParser(Source source, ErrorManager errors, boolean strict, int lineOffset) {
    if (source.getLength() > 268435455)
      throw new RuntimeException("Source exceeds size limit of 268435455 bytes"); 
    this.source = source;
    this.errors = errors;
    this.k = -1;
    this.token = Token.toDesc(TokenType.EOL, 0, 1);
    this.type = TokenType.EOL;
    this.last = TokenType.EOL;
    this.isStrictMode = strict;
    this.lineOffset = lineOffset;
  }
  
  protected final long getToken(int i) {
    while (i > this.stream.last()) {
      if (this.stream.isFull())
        this.stream.grow(); 
      this.lexer.lexify();
    } 
    return this.stream.get(i);
  }
  
  protected final TokenType T(int i) {
    return Token.descType(getToken(i));
  }
  
  protected final TokenType next() {
    do {
      nextOrEOL();
    } while (this.type == TokenType.EOL || this.type == TokenType.COMMENT);
    return this.type;
  }
  
  protected final TokenType nextOrEOL() {
    do {
      nextToken();
      if (this.type != TokenType.DIRECTIVE_COMMENT)
        continue; 
      checkDirectiveComment();
    } while (this.type == TokenType.COMMENT || this.type == TokenType.DIRECTIVE_COMMENT);
    return this.type;
  }
  
  private void checkDirectiveComment() {
    if (this.source.getExplicitURL() != null)
      return; 
    String comment = (String)this.lexer.getValueOf(this.token, this.isStrictMode);
    int len = comment.length();
    if (len > 4 && comment.substring(4).startsWith("sourceURL="))
      this.source.setExplicitURL(comment.substring(4 + "sourceURL=".length())); 
  }
  
  private void nextToken() {
    if (this.type != TokenType.COMMENT)
      this.last = this.type; 
    if (this.type != TokenType.EOF) {
      this.k++;
      long lastToken = this.token;
      this.previousToken = this.token;
      this.token = getToken(this.k);
      this.type = Token.descType(this.token);
      if (this.last != TokenType.EOL)
        this.finish = this.start + Token.descLength(lastToken); 
      if (this.type == TokenType.EOL) {
        this.line = Token.descLength(this.token);
        this.linePosition = Token.descPosition(this.token);
      } else {
        this.start = Token.descPosition(this.token);
      } 
    } 
  }
  
  protected static String message(String msgId, String... args) {
    return ECMAErrors.getMessage("parser.error." + msgId, args);
  }
  
  protected final ParserException error(String message, long errorToken) {
    return error(JSErrorType.SYNTAX_ERROR, message, errorToken);
  }
  
  protected final ParserException error(JSErrorType errorType, String message, long errorToken) {
    int position = Token.descPosition(errorToken);
    int lineNum = this.source.getLine(position);
    int columnNum = this.source.getColumn(position);
    String formatted = ErrorManager.format(message, this.source, lineNum, columnNum, errorToken);
    return new ParserException(errorType, formatted, this.source, lineNum, columnNum, errorToken);
  }
  
  protected final ParserException error(String message) {
    return error(JSErrorType.SYNTAX_ERROR, message);
  }
  
  protected final ParserException error(JSErrorType errorType, String message) {
    int position = Token.descPosition(this.token);
    int column = position - this.linePosition;
    String formatted = ErrorManager.format(message, this.source, this.line, column, this.token);
    return new ParserException(errorType, formatted, this.source, this.line, column, this.token);
  }
  
  protected final void warning(JSErrorType errorType, String message, long errorToken) {
    this.errors.warning(error(errorType, message, errorToken));
  }
  
  protected final String expectMessage(TokenType expected) {
    String msg, tokenString = Token.toString(this.source, this.token);
    if (expected == null) {
      msg = message("expected.stmt", new String[] { tokenString });
    } else {
      String expectedName = expected.getNameOrType();
      msg = message("expected", new String[] { expectedName, tokenString });
    } 
    return msg;
  }
  
  protected final void expect(TokenType expected) throws ParserException {
    expectDontAdvance(expected);
    next();
  }
  
  protected final void expectDontAdvance(TokenType expected) throws ParserException {
    if (this.type != expected)
      throw error(expectMessage(expected)); 
  }
  
  protected final Object expectValue(TokenType expected) throws ParserException {
    if (this.type != expected)
      throw error(expectMessage(expected)); 
    Object value = getValue();
    next();
    return value;
  }
  
  protected final Object getValue() {
    return getValue(this.token);
  }
  
  protected final Object getValue(long valueToken) {
    try {
      return this.lexer.getValueOf(valueToken, this.isStrictMode);
    } catch (ParserException e) {
      this.errors.error(e);
      return null;
    } 
  }
  
  protected final boolean isNonStrictModeIdent() {
    return (!this.isStrictMode && this.type.getKind() == TokenKind.FUTURESTRICT);
  }
  
  protected final IdentNode getIdent() {
    long identToken = this.token;
    if (isNonStrictModeIdent()) {
      identToken = Token.recast(this.token, TokenType.IDENT);
      String str = (String)getValue(identToken);
      next();
      return createIdentNode(identToken, this.finish, str).setIsFutureStrictName();
    } 
    String ident = (String)expectValue(TokenType.IDENT);
    if (ident == null)
      return null; 
    return createIdentNode(identToken, this.finish, ident);
  }
  
  protected IdentNode createIdentNode(long identToken, int identFinish, String name) {
    String existingName = this.canonicalNames.putIfAbsent(name, name);
    String canonicalName = (existingName != null) ? existingName : name;
    return new IdentNode(identToken, identFinish, canonicalName);
  }
  
  protected final boolean isIdentifierName() {
    TokenKind kind = this.type.getKind();
    if (kind == TokenKind.KEYWORD || kind == TokenKind.FUTURE || kind == TokenKind.FUTURESTRICT)
      return true; 
    if (kind == TokenKind.LITERAL) {
      switch (this.type) {
        case FALSE:
        case NULL:
        case TRUE:
          return true;
      } 
      return false;
    } 
    long identToken = Token.recast(this.token, TokenType.IDENT);
    String ident = (String)getValue(identToken);
    return (!ident.isEmpty() && Character.isJavaIdentifierStart(ident.charAt(0)));
  }
  
  protected final IdentNode getIdentifierName() {
    if (this.type == TokenType.IDENT)
      return getIdent(); 
    if (isIdentifierName()) {
      long identToken = Token.recast(this.token, TokenType.IDENT);
      String ident = (String)getValue(identToken);
      next();
      return createIdentNode(identToken, this.finish, ident);
    } 
    expect(TokenType.IDENT);
    return null;
  }
  
  protected final LiteralNode<?> getLiteral() throws ParserException {
    long literalToken = this.token;
    Object value = getValue();
    next();
    LiteralNode<?> node = null;
    if (value == null) {
      node = LiteralNode.newInstance(literalToken, this.finish);
    } else if (value instanceof Number) {
      node = LiteralNode.newInstance(literalToken, this.finish, (Number)value);
    } else if (value instanceof String) {
      node = LiteralNode.newInstance(literalToken, this.finish, (String)value);
    } else if (value instanceof Lexer.LexerToken) {
      if (value instanceof Lexer.RegexToken) {
        Lexer.RegexToken regex = (Lexer.RegexToken)value;
        try {
          RegExpFactory.validate(regex.getExpression(), regex.getOptions());
        } catch (ParserException e) {
          throw error(e.getMessage());
        } 
      } 
      node = LiteralNode.newInstance(literalToken, this.finish, (Lexer.LexerToken)value);
    } else {
      assert false : "unknown type for LiteralNode: " + value.getClass();
    } 
    return node;
  }
}
