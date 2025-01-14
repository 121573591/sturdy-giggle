package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.TokenType;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

class Lexer extends ScannerSupport {
  protected final ScanEnvironment env;
  
  protected final Syntax syntax;
  
  protected final Token token = new Token();
  
  protected Lexer(ScanEnvironment env, char[] chars, int p, int end) {
    super(chars, p, end);
    this.env = env;
    this.syntax = env.syntax;
  }
  
  private int fetchRangeQuantifier() {
    int up;
    mark();
    boolean synAllow = this.syntax.allowInvalidInterval();
    if (!left()) {
      if (synAllow)
        return 1; 
      throw new SyntaxException("end pattern at left brace");
    } 
    if (!synAllow) {
      this.c = peek();
      if (this.c == 41 || this.c == 40 || this.c == 124)
        throw new SyntaxException("end pattern at left brace"); 
    } 
    int low = scanUnsignedNumber();
    if (low < 0)
      throw new SyntaxException("too big number for repeat range"); 
    if (low > 100000)
      throw new SyntaxException("too big number for repeat range"); 
    boolean nonLow = false;
    if (this.p == this._p)
      if (this.syntax.allowIntervalLowAbbrev()) {
        low = 0;
        nonLow = true;
      } else {
        return invalidRangeQuantifier(synAllow);
      }  
    if (!left())
      return invalidRangeQuantifier(synAllow); 
    fetch();
    int ret = 0;
    if (this.c == 44) {
      int prev = this.p;
      up = scanUnsignedNumber();
      if (up < 0)
        throw new ValueException("too big number for repeat range"); 
      if (up > 100000)
        throw new ValueException("too big number for repeat range"); 
      if (this.p == prev) {
        if (nonLow)
          return invalidRangeQuantifier(synAllow); 
        up = -1;
      } 
    } else {
      if (nonLow)
        return invalidRangeQuantifier(synAllow); 
      unfetch();
      up = low;
      ret = 2;
    } 
    if (!left())
      return invalidRangeQuantifier(synAllow); 
    fetch();
    if (this.syntax.opEscBraceInterval()) {
      if (this.c != this.syntax.metaCharTable.esc)
        return invalidRangeQuantifier(synAllow); 
      fetch();
    } 
    if (this.c != 125)
      return invalidRangeQuantifier(synAllow); 
    if (!QuantifierNode.isRepeatInfinite(up) && low > up)
      throw new ValueException("upper is smaller than lower in repeat range"); 
    this.token.type = TokenType.INTERVAL;
    this.token.setRepeatLower(low);
    this.token.setRepeatUpper(up);
    return ret;
  }
  
  private int invalidRangeQuantifier(boolean synAllow) {
    if (synAllow) {
      restore();
      return 1;
    } 
    throw new SyntaxException("invalid repeat range {lower,upper}");
  }
  
  private int fetchEscapedValue() {
    if (!left())
      throw new SyntaxException("end pattern at escape"); 
    fetch();
    switch (this.c) {
      case 77:
        if (this.syntax.op2EscCapitalMBarMeta()) {
          if (!left())
            throw new SyntaxException("end pattern at meta"); 
          fetch();
          if (this.c != 45)
            throw new SyntaxException("invalid meta-code syntax"); 
          if (!left())
            throw new SyntaxException("end pattern at meta"); 
          fetch();
          if (this.c == this.syntax.metaCharTable.esc)
            this.c = fetchEscapedValue(); 
          this.c = this.c & 0xFF | 0x80;
        } else {
          fetchEscapedValueBackSlash();
        } 
        return this.c;
      case 67:
        if (this.syntax.op2EscCapitalCBarControl()) {
          if (!left())
            throw new SyntaxException("end pattern at control"); 
          fetch();
          if (this.c != 45)
            throw new SyntaxException("invalid control-code syntax"); 
          fetchEscapedValueControl();
        } else {
          fetchEscapedValueBackSlash();
        } 
        return this.c;
      case 99:
        if (this.syntax.opEscCControl())
          fetchEscapedValueControl(); 
        break;
    } 
    fetchEscapedValueBackSlash();
    return this.c;
  }
  
  private void fetchEscapedValueBackSlash() {
    this.c = this.env.convertBackslashValue(this.c);
  }
  
  private void fetchEscapedValueControl() {
    if (!left())
      throw new SyntaxException("end pattern at control"); 
    fetch();
    if (this.c == 63) {
      this.c = 127;
    } else {
      if (this.c == this.syntax.metaCharTable.esc)
        this.c = fetchEscapedValue(); 
      this.c &= 0x9F;
    } 
  }
  
  private void fetchTokenInCCFor_charType(boolean flag, int type) {
    this.token.type = TokenType.CHAR_TYPE;
    this.token.setPropCType(type);
    this.token.setPropNot(flag);
  }
  
  private void fetchTokenInCCFor_x() {
    if (!left())
      return; 
    int last = this.p;
    if (peekIs(123) && this.syntax.opEscXBraceHex8()) {
      inc();
      int num = scanUnsignedHexadecimalNumber(8);
      if (num < 0)
        throw new ValueException("too big wide-char value"); 
      if (left()) {
        int c2 = peek();
        if (EncodingHelper.isXDigit(c2))
          throw new ValueException("too long wide-char value"); 
      } 
      if (this.p > last + 1 && left() && peekIs(125)) {
        inc();
        this.token.type = TokenType.CODE_POINT;
        this.token.setCode(num);
      } else {
        this.p = last;
      } 
    } else if (this.syntax.opEscXHex2()) {
      int num = scanUnsignedHexadecimalNumber(2);
      if (num < 0)
        throw new ValueException("too big number"); 
      if (this.p == last)
        num = 0; 
      this.token.type = TokenType.RAW_BYTE;
      this.token.setC(num);
    } 
  }
  
  private void fetchTokenInCCFor_u() {
    if (!left())
      return; 
    int last = this.p;
    if (this.syntax.op2EscUHex4()) {
      int num = scanUnsignedHexadecimalNumber(4);
      if (num < 0)
        throw new ValueException("too big number"); 
      if (this.p == last)
        num = 0; 
      this.token.type = TokenType.CODE_POINT;
      this.token.setCode(num);
    } 
  }
  
  private void fetchTokenInCCFor_digit() {
    if (this.syntax.opEscOctal3()) {
      unfetch();
      int last = this.p;
      int num = scanUnsignedOctalNumber(3);
      if (num < 0)
        throw new ValueException("too big number"); 
      if (this.p == last)
        num = 0; 
      this.token.type = TokenType.RAW_BYTE;
      this.token.setC(num);
    } 
  }
  
  private void fetchTokenInCCFor_and() {
    if (this.syntax.op2CClassSetOp() && left() && peekIs(38)) {
      inc();
      this.token.type = TokenType.CC_AND;
    } 
  }
  
  protected final TokenType fetchTokenInCC() {
    if (!left()) {
      this.token.type = TokenType.EOT;
      return this.token.type;
    } 
    fetch();
    this.token.type = TokenType.CHAR;
    this.token.setC(this.c);
    this.token.escaped = false;
    if (this.c == 93) {
      this.token.type = TokenType.CC_CLOSE;
    } else if (this.c == 45) {
      this.token.type = TokenType.CC_RANGE;
    } else if (this.c == this.syntax.metaCharTable.esc) {
      if (!this.syntax.backSlashEscapeInCC())
        return this.token.type; 
      if (!left())
        throw new SyntaxException("end pattern at escape"); 
      fetch();
      this.token.escaped = true;
      this.token.setC(this.c);
      switch (this.c) {
        case 119:
          fetchTokenInCCFor_charType(false, 268);
          return this.token.type;
        case 87:
          fetchTokenInCCFor_charType(true, 268);
          return this.token.type;
        case 100:
          fetchTokenInCCFor_charType(false, 260);
          return this.token.type;
        case 68:
          fetchTokenInCCFor_charType(true, 260);
          return this.token.type;
        case 115:
          fetchTokenInCCFor_charType(false, 265);
          return this.token.type;
        case 83:
          fetchTokenInCCFor_charType(true, 265);
          return this.token.type;
        case 104:
          if (this.syntax.op2EscHXDigit())
            fetchTokenInCCFor_charType(false, 11); 
          return this.token.type;
        case 72:
          if (this.syntax.op2EscHXDigit())
            fetchTokenInCCFor_charType(true, 11); 
          return this.token.type;
        case 120:
          fetchTokenInCCFor_x();
          return this.token.type;
        case 117:
          fetchTokenInCCFor_u();
          return this.token.type;
        case 48:
        case 49:
        case 50:
        case 51:
        case 52:
        case 53:
        case 54:
        case 55:
          fetchTokenInCCFor_digit();
          return this.token.type;
      } 
      unfetch();
      int num = fetchEscapedValue();
      if (this.token.getC() != num) {
        this.token.setCode(num);
        this.token.type = TokenType.CODE_POINT;
      } 
    } else if (this.c == 38) {
      fetchTokenInCCFor_and();
    } 
    return this.token.type;
  }
  
  private void fetchTokenFor_repeat(int lower, int upper) {
    this.token.type = TokenType.OP_REPEAT;
    this.token.setRepeatLower(lower);
    this.token.setRepeatUpper(upper);
    greedyCheck();
  }
  
  private void fetchTokenFor_openBrace() {
    switch (fetchRangeQuantifier()) {
      case 0:
        greedyCheck();
        break;
      case 2:
        if (this.syntax.fixedIntervalIsGreedyOnly()) {
          possessiveCheck();
          break;
        } 
        greedyCheck();
        break;
    } 
  }
  
  private void fetchTokenFor_anchor(int subType) {
    this.token.type = TokenType.ANCHOR;
    this.token.setAnchor(subType);
  }
  
  private void fetchTokenFor_xBrace() {
    if (!left())
      return; 
    int last = this.p;
    if (peekIs(123) && this.syntax.opEscXBraceHex8()) {
      inc();
      int num = scanUnsignedHexadecimalNumber(8);
      if (num < 0)
        throw new ValueException("too big wide-char value"); 
      if (left() && 
        EncodingHelper.isXDigit(peek()))
        throw new ValueException("too long wide-char value"); 
      if (this.p > last + 1 && left() && peekIs(125)) {
        inc();
        this.token.type = TokenType.CODE_POINT;
        this.token.setCode(num);
      } else {
        this.p = last;
      } 
    } else if (this.syntax.opEscXHex2()) {
      int num = scanUnsignedHexadecimalNumber(2);
      if (num < 0)
        throw new ValueException("too big number"); 
      if (this.p == last)
        num = 0; 
      this.token.type = TokenType.RAW_BYTE;
      this.token.setC(num);
    } 
  }
  
  private void fetchTokenFor_uHex() {
    if (!left())
      return; 
    int last = this.p;
    if (this.syntax.op2EscUHex4()) {
      int num = scanUnsignedHexadecimalNumber(4);
      if (num < 0)
        throw new ValueException("too big number"); 
      if (this.p == last)
        num = 0; 
      this.token.type = TokenType.CODE_POINT;
      this.token.setCode(num);
    } 
  }
  
  private void fetchTokenFor_digit() {
    unfetch();
    int last = this.p;
    int num = scanUnsignedNumber();
    if (num >= 0 && num <= 1000 && 
      this.syntax.opDecimalBackref() && (num <= this.env.numMem || num <= 9)) {
      if (this.syntax.strictCheckBackref() && (
        num > this.env.numMem || this.env.memNodes == null || this.env.memNodes[num] == null))
        throw new ValueException("invalid backref number"); 
      this.token.type = TokenType.BACKREF;
      this.token.setBackrefRef(num);
      return;
    } 
    if (this.c == 56 || this.c == 57) {
      this.p = last;
      inc();
      return;
    } 
    this.p = last;
    fetchTokenFor_zero();
  }
  
  private void fetchTokenFor_zero() {
    if (this.syntax.opEscOctal3()) {
      int last = this.p;
      int num = scanUnsignedOctalNumber((this.c == 48) ? 2 : 3);
      if (num < 0)
        throw new ValueException("too big number"); 
      if (this.p == last)
        num = 0; 
      this.token.type = TokenType.RAW_BYTE;
      this.token.setC(num);
    } else if (this.c != 48) {
      inc();
    } 
  }
  
  private void fetchTokenFor_metaChars() {
    if (this.c == this.syntax.metaCharTable.anyChar) {
      this.token.type = TokenType.ANYCHAR;
    } else if (this.c == this.syntax.metaCharTable.anyTime) {
      fetchTokenFor_repeat(0, -1);
    } else if (this.c == this.syntax.metaCharTable.zeroOrOneTime) {
      fetchTokenFor_repeat(0, 1);
    } else if (this.c == this.syntax.metaCharTable.oneOrMoreTime) {
      fetchTokenFor_repeat(1, -1);
    } else if (this.c == this.syntax.metaCharTable.anyCharAnyTime) {
      this.token.type = TokenType.ANYCHAR_ANYTIME;
    } 
  }
  
  protected final TokenType fetchToken() {
    label171: while (true) {
      if (!left()) {
        this.token.type = TokenType.EOT;
        return this.token.type;
      } 
      this.token.type = TokenType.STRING;
      this.token.backP = this.p;
      fetch();
      if (this.c == this.syntax.metaCharTable.esc && !this.syntax.op2IneffectiveEscape()) {
        if (!left())
          throw new SyntaxException("end pattern at escape"); 
        this.token.backP = this.p;
        fetch();
        this.token.setC(this.c);
        this.token.escaped = true;
        switch (this.c) {
          case 42:
            if (this.syntax.opEscAsteriskZeroInf())
              fetchTokenFor_repeat(0, -1); 
            break;
          case 43:
            if (this.syntax.opEscPlusOneInf())
              fetchTokenFor_repeat(1, -1); 
            break;
          case 63:
            if (this.syntax.opEscQMarkZeroOne())
              fetchTokenFor_repeat(0, 1); 
            break;
          case 123:
            if (this.syntax.opEscBraceInterval())
              fetchTokenFor_openBrace(); 
            break;
          case 124:
            if (this.syntax.opEscVBarAlt())
              this.token.type = TokenType.ALT; 
            break;
          case 40:
            if (this.syntax.opEscLParenSubexp())
              this.token.type = TokenType.SUBEXP_OPEN; 
            break;
          case 41:
            if (this.syntax.opEscLParenSubexp())
              this.token.type = TokenType.SUBEXP_CLOSE; 
            break;
          case 119:
            if (this.syntax.opEscWWord())
              fetchTokenInCCFor_charType(false, 268); 
            break;
          case 87:
            if (this.syntax.opEscWWord())
              fetchTokenInCCFor_charType(true, 268); 
            break;
          case 98:
            if (this.syntax.opEscBWordBound())
              fetchTokenFor_anchor(64); 
            break;
          case 66:
            if (this.syntax.opEscBWordBound())
              fetchTokenFor_anchor(128); 
            break;
          case 60:
            if (this.syntax.opEscLtGtWordBeginEnd())
              fetchTokenFor_anchor(256); 
            break;
          case 62:
            if (this.syntax.opEscLtGtWordBeginEnd())
              fetchTokenFor_anchor(512); 
            break;
          case 115:
            if (this.syntax.opEscSWhiteSpace())
              fetchTokenInCCFor_charType(false, 265); 
            break;
          case 83:
            if (this.syntax.opEscSWhiteSpace())
              fetchTokenInCCFor_charType(true, 265); 
            break;
          case 100:
            if (this.syntax.opEscDDigit())
              fetchTokenInCCFor_charType(false, 260); 
            break;
          case 68:
            if (this.syntax.opEscDDigit())
              fetchTokenInCCFor_charType(true, 260); 
            break;
          case 104:
            if (this.syntax.op2EscHXDigit())
              fetchTokenInCCFor_charType(false, 11); 
            break;
          case 72:
            if (this.syntax.op2EscHXDigit())
              fetchTokenInCCFor_charType(true, 11); 
            break;
          case 65:
            if (this.syntax.opEscAZBufAnchor())
              fetchTokenFor_anchor(1); 
            break;
          case 90:
            if (this.syntax.opEscAZBufAnchor())
              fetchTokenFor_anchor(16); 
            break;
          case 122:
            if (this.syntax.opEscAZBufAnchor())
              fetchTokenFor_anchor(8); 
            break;
          case 71:
            if (this.syntax.opEscCapitalGBeginAnchor())
              fetchTokenFor_anchor(4); 
            break;
          case 96:
            if (this.syntax.op2EscGnuBufAnchor())
              fetchTokenFor_anchor(1); 
            break;
          case 39:
            if (this.syntax.op2EscGnuBufAnchor())
              fetchTokenFor_anchor(8); 
            break;
          case 120:
            fetchTokenFor_xBrace();
            break;
          case 117:
            fetchTokenFor_uHex();
            break;
          case 49:
          case 50:
          case 51:
          case 52:
          case 53:
          case 54:
          case 55:
          case 56:
          case 57:
            fetchTokenFor_digit();
            break;
          case 48:
            fetchTokenFor_zero();
            break;
        } 
        unfetch();
        int num = fetchEscapedValue();
        if (this.token.getC() != num) {
          this.token.type = TokenType.CODE_POINT;
          this.token.setCode(num);
          break;
        } 
        this.p = this.token.backP + 1;
        break;
      } 
      this.token.setC(this.c);
      this.token.escaped = false;
      if (this.c != 0 && this.syntax.opVariableMetaCharacters()) {
        fetchTokenFor_metaChars();
        break;
      } 
      switch (this.c) {
        case 46:
          if (this.syntax.opDotAnyChar())
            this.token.type = TokenType.ANYCHAR; 
          break;
        case 42:
          if (this.syntax.opAsteriskZeroInf())
            fetchTokenFor_repeat(0, -1); 
          break;
        case 43:
          if (this.syntax.opPlusOneInf())
            fetchTokenFor_repeat(1, -1); 
          break;
        case 63:
          if (this.syntax.opQMarkZeroOne())
            fetchTokenFor_repeat(0, 1); 
          break;
        case 123:
          if (this.syntax.opBraceInterval())
            fetchTokenFor_openBrace(); 
          break;
        case 124:
          if (this.syntax.opVBarAlt())
            this.token.type = TokenType.ALT; 
          break;
        case 40:
          if (peekIs(63) && this.syntax.op2QMarkGroupEffect()) {
            inc();
            if (peekIs(35)) {
              fetch();
              while (true) {
                if (!left())
                  throw new SyntaxException("end pattern in group"); 
                fetch();
                if (this.c == this.syntax.metaCharTable.esc) {
                  if (left())
                    fetch(); 
                  continue;
                } 
                if (this.c == 41)
                  continue label171; 
              } 
            } 
            unfetch();
          } 
          if (this.syntax.opLParenSubexp())
            this.token.type = TokenType.SUBEXP_OPEN; 
          break;
        case 41:
          if (this.syntax.opLParenSubexp())
            this.token.type = TokenType.SUBEXP_CLOSE; 
          break;
        case 94:
          if (this.syntax.opLineAnchor())
            fetchTokenFor_anchor(Option.isSingleline(this.env.option) ? 1 : 2); 
          break;
        case 36:
          if (this.syntax.opLineAnchor())
            fetchTokenFor_anchor(Option.isSingleline(this.env.option) ? 8 : 32); 
          break;
        case 91:
          if (this.syntax.opBracketCC())
            this.token.type = TokenType.CC_CC_OPEN; 
          break;
        case 35:
          if (Option.isExtend(this.env.option)) {
            while (left()) {
              fetch();
              if (EncodingHelper.isNewLine(this.c))
                break; 
            } 
            continue;
          } 
          break;
        case 9:
        case 10:
        case 12:
        case 13:
        case 32:
          if (Option.isExtend(this.env.option))
            continue; 
          break;
      } 
      break;
    } 
    return this.token.type;
  }
  
  private void greedyCheck() {
    if (left() && peekIs(63) && this.syntax.opQMarkNonGreedy()) {
      fetch();
      this.token.setRepeatGreedy(false);
      this.token.setRepeatPossessive(false);
    } else {
      possessiveCheck();
    } 
  }
  
  private void possessiveCheck() {
    if (left() && peekIs(43) && ((this.syntax
      .op2PlusPossessiveRepeat() && this.token.type != TokenType.INTERVAL) || (this.syntax
      .op2PlusPossessiveInterval() && this.token.type == TokenType.INTERVAL))) {
      fetch();
      this.token.setRepeatGreedy(true);
      this.token.setRepeatPossessive(true);
    } else {
      this.token.setRepeatGreedy(true);
      this.token.setRepeatPossessive(false);
    } 
  }
  
  protected final void syntaxWarn(String message, char ch) {
    syntaxWarn(message.replace("<%n>", Character.toString(ch)));
  }
  
  protected final void syntaxWarn(String message) {
    this.env.reg.warnings.warn(message + ": /" + message + "/");
  }
}
