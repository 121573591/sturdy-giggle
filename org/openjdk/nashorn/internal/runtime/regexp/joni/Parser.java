package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.AnchorNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.AnyCharNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.BackRefNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.ConsAltNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.Node;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.CCVALTYPE;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.TokenType;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

class Parser extends Lexer {
  protected final Regex regex;
  
  protected Node root;
  
  protected int returnCode;
  
  protected Parser(ScanEnvironment env, char[] chars, int p, int end) {
    super(env, chars, p, end);
    this.regex = env.reg;
  }
  
  protected final Node parse() {
    this.root = parseRegexp();
    this.regex.numMem = this.env.numMem;
    return this.root;
  }
  
  private boolean codeExistCheck(int code, boolean ignoreEscaped) {
    mark();
    boolean inEsc = false;
    while (left()) {
      if (ignoreEscaped && inEsc) {
        inEsc = false;
        continue;
      } 
      fetch();
      if (this.c == code) {
        restore();
        return true;
      } 
      if (this.c == this.syntax.metaCharTable.esc)
        inEsc = true; 
    } 
    restore();
    return false;
  }
  
  private CClassNode parseCharClass() {
    boolean neg;
    fetchTokenInCC();
    if (this.token.type == TokenType.CHAR && this.token.getC() == 94 && !this.token.escaped) {
      neg = true;
      fetchTokenInCC();
    } else {
      neg = false;
    } 
    if (this.token.type == TokenType.CC_CLOSE) {
      if (!codeExistCheck(93, true))
        throw new SyntaxException("empty char-class"); 
      this.env.ccEscWarn("]");
      this.token.type = TokenType.CHAR;
    } 
    CClassNode cc = new CClassNode();
    CClassNode prevCC = null;
    CClassNode workCC = null;
    CClassNode.CCStateArg arg = new CClassNode.CCStateArg();
    boolean andStart = false;
    arg.state = CCSTATE.START;
    while (this.token.type != TokenType.CC_CLOSE) {
      CClassNode acc;
      boolean fetched = false;
      switch (this.token.type) {
        case CHAR:
          if (this.token.getC() > 255) {
            arg.inType = CCVALTYPE.CODE_POINT;
          } else {
            arg.inType = CCVALTYPE.SB;
          } 
          arg.v = this.token.getC();
          arg.vIsRaw = false;
          parseCharClassValEntry2(cc, arg);
          break;
        case RAW_BYTE:
          arg.v = this.token.getC();
          arg.inType = CCVALTYPE.SB;
          arg.vIsRaw = true;
          parseCharClassValEntry2(cc, arg);
          break;
        case CODE_POINT:
          arg.v = this.token.getCode();
          arg.vIsRaw = true;
          parseCharClassValEntry(cc, arg);
          break;
        case CHAR_TYPE:
          cc.addCType(this.token.getPropCType(), this.token.getPropNot(), this.env, this);
          cc.nextStateClass(arg, this.env);
          break;
        case CC_RANGE:
          if (arg.state == CCSTATE.VALUE) {
            fetchTokenInCC();
            fetched = true;
            if (this.token.type == TokenType.CC_CLOSE) {
              parseCharClassRangeEndVal(cc, arg);
              break;
            } 
            if (this.token.type == TokenType.CC_AND) {
              this.env.ccEscWarn("-");
              parseCharClassRangeEndVal(cc, arg);
              break;
            } 
            arg.state = CCSTATE.RANGE;
            break;
          } 
          if (arg.state == CCSTATE.START) {
            arg.v = this.token.getC();
            arg.vIsRaw = false;
            fetchTokenInCC();
            fetched = true;
            if (this.token.type == TokenType.CC_RANGE || andStart)
              this.env.ccEscWarn("-"); 
            parseCharClassValEntry(cc, arg);
            break;
          } 
          if (arg.state == CCSTATE.RANGE) {
            this.env.ccEscWarn("-");
            parseCharClassSbChar(cc, arg);
            break;
          } 
          fetchTokenInCC();
          fetched = true;
          if (this.token.type == TokenType.CC_CLOSE) {
            parseCharClassRangeEndVal(cc, arg);
            break;
          } 
          if (this.token.type == TokenType.CC_AND) {
            this.env.ccEscWarn("-");
            parseCharClassRangeEndVal(cc, arg);
            break;
          } 
          if (this.syntax.allowDoubleRangeOpInCC()) {
            this.env.ccEscWarn("-");
            arg.inType = CCVALTYPE.SB;
            arg.v = 45;
            arg.vIsRaw = false;
            parseCharClassValEntry2(cc, arg);
            break;
          } 
          throw new SyntaxException("unmatched range specifier in char-class");
        case CC_CC_OPEN:
          acc = parseCharClass();
          cc.or(acc);
          break;
        case CC_AND:
          if (arg.state == CCSTATE.VALUE) {
            arg.v = 0;
            arg.vIsRaw = false;
            cc.nextStateValue(arg, this.env);
          } 
          andStart = true;
          arg.state = CCSTATE.START;
          if (prevCC != null) {
            prevCC.and(cc);
          } else {
            prevCC = cc;
            if (workCC == null)
              workCC = new CClassNode(); 
            cc = workCC;
          } 
          cc.clear();
          break;
        case EOT:
          throw new SyntaxException("premature end of char-class");
        default:
          throw new InternalException("internal parser error (bug)");
      } 
      if (!fetched)
        fetchTokenInCC(); 
    } 
    if (arg.state == CCSTATE.VALUE) {
      arg.v = 0;
      arg.vIsRaw = false;
      cc.nextStateValue(arg, this.env);
    } 
    if (prevCC != null) {
      prevCC.and(cc);
      cc = prevCC;
    } 
    if (neg) {
      cc.setNot();
    } else {
      cc.clearNot();
    } 
    if (cc.isNot() && this.syntax.notNewlineInNegativeCC() && 
      !cc.isEmpty()) {
      int NEW_LINE = 10;
      if (EncodingHelper.isNewLine(10))
        cc.bs.set(10); 
    } 
    return cc;
  }
  
  private void parseCharClassSbChar(CClassNode cc, CClassNode.CCStateArg arg) {
    arg.inType = CCVALTYPE.SB;
    arg.v = this.token.getC();
    arg.vIsRaw = false;
    parseCharClassValEntry2(cc, arg);
  }
  
  private void parseCharClassRangeEndVal(CClassNode cc, CClassNode.CCStateArg arg) {
    arg.v = 45;
    arg.vIsRaw = false;
    parseCharClassValEntry(cc, arg);
  }
  
  private void parseCharClassValEntry(CClassNode cc, CClassNode.CCStateArg arg) {
    arg.inType = (arg.v <= 255) ? CCVALTYPE.SB : CCVALTYPE.CODE_POINT;
    parseCharClassValEntry2(cc, arg);
  }
  
  private void parseCharClassValEntry2(CClassNode cc, CClassNode.CCStateArg arg) {
    cc.nextStateValue(arg, this.env);
  }
  
  private Node parseEnclose(TokenType term) {
    EncloseNode encloseNode;
    Node node = null;
    if (!left())
      throw new SyntaxException("end pattern with unmatched parenthesis"); 
    int option = this.env.option;
    if (peekIs(63) && this.syntax.op2QMarkGroupEffect()) {
      AnchorNode anchorNode;
      boolean neg;
      inc();
      if (!left())
        throw new SyntaxException("end pattern in group"); 
      fetch();
      switch (this.c) {
        case 58:
          fetchToken();
          node = parseSubExp(term);
          this.returnCode = 1;
          return node;
        case 61:
          anchorNode = new AnchorNode(1024);
          break;
        case 33:
          anchorNode = new AnchorNode(2048);
          break;
        case 62:
          encloseNode = new EncloseNode(4);
          break;
        case 39:
          break;
        case 60:
          fetch();
          if (this.c == 61) {
            AnchorNode anchorNode1 = new AnchorNode(4096);
            break;
          } 
          if (this.c == 33) {
            AnchorNode anchorNode1 = new AnchorNode(8192);
            break;
          } 
          throw new SyntaxException("undefined group option");
        case 64:
          if (this.syntax.op2AtMarkCaptureHistory()) {
            EncloseNode en = new EncloseNode();
            int num = this.env.addMemEntry();
            if (num >= 32)
              throw new ValueException("group number is too big for capture history"); 
            en.regNum = num;
            encloseNode = en;
            break;
          } 
          throw new SyntaxException("undefined group option");
        case 45:
        case 105:
        case 109:
        case 115:
        case 120:
          neg = false;
          while (true) {
            switch (this.c) {
              case 41:
              case 58:
                break;
              case 45:
                neg = true;
                break;
              case 120:
                option = BitStatus.bsOnOff(option, 2, neg);
                break;
              case 105:
                option = BitStatus.bsOnOff(option, 1, neg);
                break;
              case 115:
                if (this.syntax.op2OptionPerl()) {
                  option = BitStatus.bsOnOff(option, 4, neg);
                  break;
                } 
                throw new SyntaxException("undefined group option");
              case 109:
                if (this.syntax.op2OptionPerl()) {
                  option = BitStatus.bsOnOff(option, 8, !neg);
                  break;
                } 
                if (this.syntax.op2OptionRuby()) {
                  option = BitStatus.bsOnOff(option, 4, neg);
                  break;
                } 
                throw new SyntaxException("undefined group option");
              default:
                throw new SyntaxException("undefined group option");
            } 
            if (this.c == 41) {
              EncloseNode en = new EncloseNode(option, 0);
              encloseNode = en;
              this.returnCode = 2;
              return (Node)encloseNode;
            } 
            if (this.c == 58) {
              int prev = this.env.option;
              this.env.option = option;
              fetchToken();
              Node node1 = parseSubExp(term);
              this.env.option = prev;
              EncloseNode en = new EncloseNode(option, 0);
              en.setTarget(node1);
              encloseNode = en;
              this.returnCode = 0;
              return (Node)encloseNode;
            } 
            if (!left())
              throw new SyntaxException("end pattern in group"); 
            fetch();
          } 
        default:
          throw new SyntaxException("undefined group option");
      } 
    } else {
      if (Option.isDontCaptureGroup(this.env.option)) {
        fetchToken();
        node = parseSubExp(term);
        this.returnCode = 1;
        return node;
      } 
      EncloseNode en = new EncloseNode();
      int num = this.env.addMemEntry();
      en.regNum = num;
      encloseNode = en;
    } 
    fetchToken();
    Node target = parseSubExp(term);
    if (encloseNode.getType() == 7) {
      AnchorNode an = (AnchorNode)encloseNode;
      an.setTarget(target);
    } else {
      EncloseNode en = encloseNode;
      en.setTarget(target);
      if (en.type == 1)
        this.env.setMemNode(en.regNum, (Node)encloseNode); 
    } 
    this.returnCode = 0;
    return (Node)encloseNode;
  }
  
  private Node parseExp(TokenType term) {
    StringNode stringNode2;
    CClassNode cClassNode1;
    ConsAltNode consAltNode;
    AnyCharNode anyCharNode;
    QuantifierNode quantifierNode1;
    BackRefNode backRefNode;
    AnchorNode anchorNode;
    StringNode stringNode1;
    char[] buf;
    CClassNode cClassNode2, ccn, cc;
    QuantifierNode qn;
    int backRef;
    if (this.token.type == term)
      return (Node)StringNode.createEmpty(); 
    Node node = null;
    boolean group = false;
    switch (this.token.type) {
      case EOT:
      case ALT:
        return (Node)StringNode.createEmpty();
      case SUBEXP_OPEN:
        node = parseEnclose(TokenType.SUBEXP_CLOSE);
        if (this.returnCode == 1) {
          group = true;
        } else if (this.returnCode == 2) {
          int prev = this.env.option;
          EncloseNode en = (EncloseNode)node;
          this.env.option = en.option;
          fetchToken();
          Node target = parseSubExp(term);
          this.env.option = prev;
          en.setTarget(target);
          return node;
        } 
        fetchToken();
        return parseExpRepeat(node, group);
      case SUBEXP_CLOSE:
        if (!this.syntax.allowUnmatchedCloseSubexp())
          throw new SyntaxException("unmatched close parenthesis"); 
        if (this.token.escaped)
          return parseExpTkRawByte(group); 
        return parseExpTkByte(group);
      case STRING:
        return parseExpTkByte(group);
      case RAW_BYTE:
        return parseExpTkRawByte(group);
      case CODE_POINT:
        buf = new char[] { (char)this.token.getCode() };
        stringNode2 = new StringNode(buf, 0, 1);
        fetchToken();
        return parseExpRepeat((Node)stringNode2, group);
      case CHAR_TYPE:
        switch (this.token.getPropCType()) {
          case 260:
          case 265:
          case 268:
            cClassNode2 = new CClassNode();
            cClassNode2.addCType(this.token.getPropCType(), false, this.env, this);
            if (this.token.getPropNot())
              cClassNode2.setNot(); 
            cClassNode1 = cClassNode2;
            fetchToken();
            return parseExpRepeat((Node)cClassNode1, group);
          case 4:
          case 9:
          case 11:
            ccn = new CClassNode();
            ccn.addCType(this.token.getPropCType(), false, this.env, this);
            if (this.token.getPropNot())
              ccn.setNot(); 
            cClassNode1 = ccn;
            fetchToken();
            return parseExpRepeat((Node)cClassNode1, group);
        } 
        throw new InternalException("internal parser error (bug)");
      case CC_CC_OPEN:
        cc = parseCharClass();
        cClassNode1 = cc;
        if (Option.isIgnoreCase(this.env.option)) {
          ApplyCaseFoldArg arg = new ApplyCaseFoldArg(this.env, cc);
          EncodingHelper.applyAllCaseFold(this.env.caseFoldFlag, ApplyCaseFold.INSTANCE, arg);
          if (arg.altRoot != null)
            consAltNode = ConsAltNode.newAltNode((Node)cClassNode1, arg.altRoot); 
        } 
        fetchToken();
        return parseExpRepeat((Node)consAltNode, group);
      case ANYCHAR:
        anyCharNode = new AnyCharNode();
        fetchToken();
        return parseExpRepeat((Node)anyCharNode, group);
      case ANYCHAR_ANYTIME:
        anyCharNode = new AnyCharNode();
        qn = new QuantifierNode(0, -1, false);
        qn.setTarget((Node)anyCharNode);
        quantifierNode1 = qn;
        fetchToken();
        return parseExpRepeat((Node)quantifierNode1, group);
      case BACKREF:
        backRef = this.token.getBackrefRef();
        backRefNode = new BackRefNode(backRef, this.env);
        fetchToken();
        return parseExpRepeat((Node)backRefNode, group);
      case ANCHOR:
        anchorNode = new AnchorNode(this.token.getAnchor());
        fetchToken();
        return parseExpRepeat((Node)anchorNode, group);
      case OP_REPEAT:
      case INTERVAL:
        if (this.syntax.contextIndepRepeatOps()) {
          if (this.syntax.contextInvalidRepeatOps())
            throw new SyntaxException("target of repeat operator is not specified"); 
          stringNode1 = StringNode.createEmpty();
        } else {
          return parseExpTkByte(group);
        } 
        fetchToken();
        return parseExpRepeat((Node)stringNode1, group);
    } 
    throw new InternalException("internal parser error (bug)");
  }
  
  private Node parseExpTkByte(boolean group) {
    StringNode node = new StringNode(this.chars, this.token.backP, this.p);
    while (true) {
      fetchToken();
      if (this.token.type != TokenType.STRING)
        break; 
      if (this.token.backP == node.end) {
        node.end = this.p;
        continue;
      } 
      node.cat(this.chars, this.token.backP, this.p);
    } 
    return parseExpRepeat((Node)node, group);
  }
  
  private Node parseExpTkRawByte(boolean group) {
    StringNode node = new StringNode((char)this.token.getC());
    node.setRaw();
    fetchToken();
    node.clearRaw();
    return parseExpRepeat((Node)node, group);
  }
  
  private Node parseExpRepeat(Node targetp, boolean group) {
    ConsAltNode consAltNode;
    Node target = targetp;
    while (this.token.type == TokenType.OP_REPEAT || this.token.type == TokenType.INTERVAL) {
      EncloseNode encloseNode1, encloseNode2;
      if (target.isInvalidQuantifier())
        throw new SyntaxException("target of repeat operator is invalid"); 
      QuantifierNode qtfr = new QuantifierNode(this.token.getRepeatLower(), this.token.getRepeatUpper(), (this.token.type == TokenType.INTERVAL));
      qtfr.greedy = this.token.getRepeatGreedy();
      int ret = qtfr.setQuantifier(target, group, this.env, this.chars, getBegin(), getEnd());
      QuantifierNode quantifierNode1 = qtfr;
      if (this.token.getRepeatPossessive()) {
        EncloseNode en = new EncloseNode(4);
        en.setTarget((Node)quantifierNode1);
        encloseNode2 = en;
      } 
      if (ret == 0) {
        encloseNode1 = encloseNode2;
      } else if (ret == 2) {
        consAltNode = ConsAltNode.newListNode((Node)encloseNode1, null);
        ConsAltNode tmp = consAltNode.setCdr(ConsAltNode.newListNode((Node)encloseNode2, null));
        fetchToken();
        return parseExpRepeatForCar((Node)consAltNode, tmp, group);
      } 
      fetchToken();
    } 
    return (Node)consAltNode;
  }
  
  private Node parseExpRepeatForCar(Node top, ConsAltNode target, boolean group) {
    while (this.token.type == TokenType.OP_REPEAT || this.token.type == TokenType.INTERVAL) {
      EncloseNode encloseNode;
      if (target.car.isInvalidQuantifier())
        throw new SyntaxException("target of repeat operator is invalid"); 
      QuantifierNode qtfr = new QuantifierNode(this.token.getRepeatLower(), this.token.getRepeatUpper(), (this.token.type == TokenType.INTERVAL));
      qtfr.greedy = this.token.getRepeatGreedy();
      int ret = qtfr.setQuantifier(target.car, group, this.env, this.chars, getBegin(), getEnd());
      QuantifierNode quantifierNode1 = qtfr;
      if (this.token.getRepeatPossessive()) {
        EncloseNode en = new EncloseNode(4);
        en.setTarget((Node)quantifierNode1);
        encloseNode = en;
      } 
      if (ret == 0) {
        target.setCar((Node)encloseNode);
      } else if (ret == 2 && 
        !$assertionsDisabled) {
        throw new AssertionError();
      } 
      fetchToken();
    } 
    return top;
  }
  
  private Node parseBranch(TokenType term) {
    Node node = parseExp(term);
    if (this.token.type == TokenType.EOT || this.token.type == term || this.token.type == TokenType.ALT)
      return node; 
    ConsAltNode top = ConsAltNode.newListNode(node, null);
    ConsAltNode t = top;
    while (this.token.type != TokenType.EOT && this.token.type != term && this.token.type != TokenType.ALT) {
      ConsAltNode consAltNode;
      node = parseExp(term);
      if (node.getType() == 8) {
        t.setCdr((ConsAltNode)node);
        while (((ConsAltNode)node).cdr != null)
          consAltNode = ((ConsAltNode)node).cdr; 
        t = consAltNode;
        continue;
      } 
      t.setCdr(ConsAltNode.newListNode((Node)consAltNode, null));
      t = t.cdr;
    } 
    return (Node)top;
  }
  
  private Node parseSubExp(TokenType term) {
    Node node = parseBranch(term);
    if (this.token.type == term)
      return node; 
    if (this.token.type == TokenType.ALT) {
      ConsAltNode top = ConsAltNode.newAltNode(node, null);
      ConsAltNode t = top;
      while (this.token.type == TokenType.ALT) {
        fetchToken();
        node = parseBranch(term);
        t.setCdr(ConsAltNode.newAltNode(node, null));
        t = t.cdr;
      } 
      if (this.token.type != term)
        parseSubExpError(term); 
      return (Node)top;
    } 
    parseSubExpError(term);
    return null;
  }
  
  private static void parseSubExpError(TokenType term) {
    if (term == TokenType.SUBEXP_CLOSE)
      throw new SyntaxException("end pattern with unmatched parenthesis"); 
    throw new InternalException("internal parser error (bug)");
  }
  
  private Node parseRegexp() {
    fetchToken();
    return parseSubExp(TokenType.EOT);
  }
}
