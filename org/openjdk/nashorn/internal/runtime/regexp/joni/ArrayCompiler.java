package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.AnchorNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.BackRefNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.ConsAltNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.Node;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

final class ArrayCompiler extends Compiler {
  private int[] code;
  
  private int codeLength;
  
  private char[][] templates;
  
  private int templateNum;
  
  private static final int REPEAT_RANGE_ALLOC = 8;
  
  private static final int QUANTIFIER_EXPAND_LIMIT_SIZE = 50;
  
  ArrayCompiler(Analyser analyser) {
    super(analyser);
  }
  
  protected final void prepare() {
    int codeSize = 8;
    this.code = new int[codeSize];
    this.codeLength = 0;
  }
  
  protected final void finish() {
    addOpcode(1);
    addOpcode(0);
    this.regex.code = this.code;
    this.regex.codeLength = this.codeLength;
    this.regex.templates = this.templates;
    this.regex.templateNum = this.templateNum;
    this.regex.factory = MatcherFactory.DEFAULT;
  }
  
  protected void compileAltNode(ConsAltNode node) {
    ConsAltNode aln = node;
    int len = 0;
    do {
      len += compileLengthTree(aln.car);
      if (aln.cdr == null)
        continue; 
      len += 4;
    } while ((aln = aln.cdr) != null);
    int pos = this.codeLength + len;
    aln = node;
    do {
      len = compileLengthTree(aln.car);
      if (aln.cdr != null)
        addOpcodeRelAddr(56, len + 2); 
      compileTree(aln.car);
      if (aln.cdr == null)
        continue; 
      len = pos - this.codeLength + 2;
      addOpcodeRelAddr(55, len);
    } while ((aln = aln.cdr) != null);
  }
  
  private static boolean isNeedStrLenOpExact(int op) {
    return (op == 7 || op == 15);
  }
  
  private static boolean opTemplated(int op) {
    return isNeedStrLenOpExact(op);
  }
  
  private static int selectStrOpcode(int strLength, boolean ignoreCase) {
    int op;
    if (ignoreCase) {
      switch (strLength) {
        case 1:
          op = 14;
          return op;
      } 
      op = 15;
    } else {
      switch (strLength) {
        case 1:
          op = 2;
          return op;
        case 2:
          op = 3;
          return op;
        case 3:
          op = 4;
          return op;
        case 4:
          op = 5;
          return op;
        case 5:
          op = 6;
          return op;
      } 
      op = 7;
    } 
    return op;
  }
  
  private void compileTreeEmptyCheck(Node node, int emptyInfo) {
    int savedNumNullCheck = this.regex.numNullCheck;
    if (emptyInfo != 0) {
      addOpcode(66);
      addMemNum(this.regex.numNullCheck);
      this.regex.numNullCheck++;
    } 
    compileTree(node);
    if (emptyInfo != 0) {
      switch (emptyInfo) {
        case 1:
          addOpcode(67);
          break;
        case 2:
          addOpcode(68);
          break;
      } 
      addMemNum(savedNumNullCheck);
    } 
  }
  
  private static int addCompileStringlength(char[] chars, int p, int strLength, boolean ignoreCase) {
    int op = selectStrOpcode(strLength, ignoreCase);
    int len = 1;
    if (opTemplated(op)) {
      len += 3;
    } else {
      if (isNeedStrLenOpExact(op))
        len++; 
      len += strLength;
    } 
    return len;
  }
  
  protected final void addCompileString(char[] chars, int p, int strLength, boolean ignoreCase) {
    int op = selectStrOpcode(strLength, ignoreCase);
    addOpcode(op);
    if (isNeedStrLenOpExact(op))
      addLength(strLength); 
    if (opTemplated(op)) {
      addInt(this.templateNum);
      addInt(p);
      addTemplate(chars);
    } else {
      addChars(chars, p, strLength);
    } 
  }
  
  private static int compileLengthStringNode(Node node) {
    StringNode sn = (StringNode)node;
    if (sn.length() <= 0)
      return 0; 
    boolean ambig = sn.isAmbig();
    int prev = sn.p, p = prev;
    int end = sn.end;
    char[] chars = sn.chars;
    p++;
    int slen = 1;
    int rlen = 0;
    while (p < end) {
      slen++;
      p++;
    } 
    int r = addCompileStringlength(chars, prev, slen, ambig);
    rlen += r;
    return rlen;
  }
  
  private static int compileLengthStringRawNode(StringNode sn) {
    if (sn.length() <= 0)
      return 0; 
    return addCompileStringlength(sn.chars, sn.p, sn.length(), false);
  }
  
  private void addMultiByteCClass(CodeRangeBuffer mbuf) {
    addLength(mbuf.used);
    addInts(mbuf.p, mbuf.used);
  }
  
  private static int compileLengthCClassNode(CClassNode cc) {
    int len;
    if (cc.isShare())
      return 2; 
    if (cc.mbuf == null) {
      len = 9;
    } else {
      if (cc.bs.isEmpty()) {
        len = 1;
      } else {
        len = 9;
      } 
      len += 1 + cc.mbuf.used;
    } 
    return len;
  }
  
  protected void compileCClassNode(CClassNode cc) {
    if (cc.isShare()) {
      addOpcode(22);
      addPointer(cc);
      return;
    } 
    if (cc.mbuf == null) {
      if (cc.isNot()) {
        addOpcode(19);
      } else {
        addOpcode(16);
      } 
      addInts(cc.bs.bits, 8);
    } else if (cc.bs.isEmpty()) {
      if (cc.isNot()) {
        addOpcode(20);
      } else {
        addOpcode(17);
      } 
      addMultiByteCClass(cc.mbuf);
    } else {
      if (cc.isNot()) {
        addOpcode(21);
      } else {
        addOpcode(18);
      } 
      addInts(cc.bs.bits, 8);
      addMultiByteCClass(cc.mbuf);
    } 
  }
  
  protected void compileAnyCharNode() {
    if (Option.isMultiline(this.regex.options)) {
      addOpcode(24);
    } else {
      addOpcode(23);
    } 
  }
  
  protected void compileBackrefNode(BackRefNode node) {
    if (Option.isIgnoreCase(this.regex.options)) {
      addOpcode(44);
      addMemNum(node.backRef);
    } else {
      switch (node.backRef) {
        case 1:
          addOpcode(41);
          return;
        case 2:
          addOpcode(42);
          return;
      } 
      addOpcode(43);
      addOpcode(node.backRef);
    } 
  }
  
  private void entryRepeatRange(int id, int lower, int upper) {
    if (this.regex.repeatRangeLo == null) {
      this.regex.repeatRangeLo = new int[8];
      this.regex.repeatRangeHi = new int[8];
    } else if (id >= this.regex.repeatRangeLo.length) {
      int[] tmp = new int[this.regex.repeatRangeLo.length + 8];
      System.arraycopy(this.regex.repeatRangeLo, 0, tmp, 0, this.regex.repeatRangeLo.length);
      this.regex.repeatRangeLo = tmp;
      tmp = new int[this.regex.repeatRangeHi.length + 8];
      System.arraycopy(this.regex.repeatRangeHi, 0, tmp, 0, this.regex.repeatRangeHi.length);
      this.regex.repeatRangeHi = tmp;
    } 
    this.regex.repeatRangeLo[id] = lower;
    this.regex.repeatRangeHi[id] = QuantifierNode.isRepeatInfinite(upper) ? Integer.MAX_VALUE : upper;
  }
  
  private void compileRangeRepeatNode(QuantifierNode qn, int targetLen, int emptyInfo) {
    int numRepeat = this.regex.numRepeat;
    addOpcode(qn.greedy ? 60 : 61);
    addMemNum(numRepeat);
    this.regex.numRepeat++;
    addRelAddr(targetLen + 2);
    entryRepeatRange(numRepeat, qn.lower, qn.upper);
    compileTreeEmptyCheck(qn.target, emptyInfo);
    if (qn.isInRepeat()) {
      addOpcode(qn.greedy ? 64 : 65);
    } else {
      addOpcode(qn.greedy ? 62 : 63);
    } 
    addMemNum(numRepeat);
  }
  
  private static boolean cknOn(int ckn) {
    return (ckn > 0);
  }
  
  private int compileNonCECLengthQuantifierNode(QuantifierNode qn) {
    int len;
    boolean infinite = QuantifierNode.isRepeatInfinite(qn.upper);
    int emptyInfo = qn.targetEmptyInfo;
    int tlen = compileLengthTree(qn.target);
    if (qn.target.getType() == 3 && 
      qn.greedy && infinite) {
      if (qn.nextHeadExact != null)
        return 2 + tlen * qn.lower; 
      return 1 + tlen * qn.lower;
    } 
    int modTLen = 0;
    if (emptyInfo != 0) {
      modTLen = tlen + 4;
    } else {
      modTLen = tlen;
    } 
    if (infinite && (qn.lower <= 1 || tlen * qn.lower <= 50)) {
      if (qn.lower == 1 && tlen > 50) {
        len = 2;
      } else {
        len = tlen * qn.lower;
      } 
      if (qn.greedy) {
        if (qn.headExact != null) {
          len += 3 + modTLen + 2;
        } else if (qn.nextHeadExact != null) {
          len += 3 + modTLen + 2;
        } else {
          len += 2 + modTLen + 2;
        } 
      } else {
        len += 2 + modTLen + 2;
      } 
    } else if (qn.upper == 0 && qn.isRefered) {
      len = 2 + tlen;
    } else if (!infinite && qn.greedy && (qn.upper == 1 || (tlen + 2) * qn.upper <= 50)) {
      len = tlen * qn.lower;
      len += (2 + tlen) * (qn.upper - qn.lower);
    } else if (!qn.greedy && qn.upper == 1 && qn.lower == 0) {
      len = 4 + tlen;
    } else {
      len = 2 + modTLen + 1 + 1 + 1;
    } 
    return len;
  }
  
  protected void compileNonCECQuantifierNode(QuantifierNode qn) {
    int modTLen;
    boolean infinite = QuantifierNode.isRepeatInfinite(qn.upper);
    int emptyInfo = qn.targetEmptyInfo;
    int tlen = compileLengthTree(qn.target);
    if (qn.isAnyCharStar()) {
      compileTreeNTimes(qn.target, qn.lower);
      if (qn.nextHeadExact != null) {
        if (Option.isMultiline(this.regex.options)) {
          addOpcode(28);
        } else {
          addOpcode(27);
        } 
        StringNode sn = (StringNode)qn.nextHeadExact;
        addChars(sn.chars, sn.p, 1);
        return;
      } 
      if (Option.isMultiline(this.regex.options)) {
        addOpcode(26);
      } else {
        addOpcode(25);
      } 
      return;
    } 
    if (emptyInfo != 0) {
      modTLen = tlen + 4;
    } else {
      modTLen = tlen;
    } 
    if (infinite && (qn.lower <= 1 || tlen * qn.lower <= 50)) {
      if (qn.lower == 1 && tlen > 50) {
        if (qn.greedy) {
          if (qn.headExact != null) {
            addOpcodeRelAddr(55, 3);
          } else if (qn.nextHeadExact != null) {
            addOpcodeRelAddr(55, 3);
          } else {
            addOpcodeRelAddr(55, 2);
          } 
        } else {
          addOpcodeRelAddr(55, 2);
        } 
      } else {
        compileTreeNTimes(qn.target, qn.lower);
      } 
      if (qn.greedy) {
        if (qn.headExact != null) {
          addOpcodeRelAddr(58, modTLen + 2);
          StringNode sn = (StringNode)qn.headExact;
          addChars(sn.chars, sn.p, 1);
          compileTreeEmptyCheck(qn.target, emptyInfo);
          addOpcodeRelAddr(55, -(modTLen + 2 + 3));
        } else if (qn.nextHeadExact != null) {
          addOpcodeRelAddr(59, modTLen + 2);
          StringNode sn = (StringNode)qn.nextHeadExact;
          addChars(sn.chars, sn.p, 1);
          compileTreeEmptyCheck(qn.target, emptyInfo);
          addOpcodeRelAddr(55, -(modTLen + 2 + 3));
        } else {
          addOpcodeRelAddr(56, modTLen + 2);
          compileTreeEmptyCheck(qn.target, emptyInfo);
          addOpcodeRelAddr(55, -(modTLen + 2 + 2));
        } 
      } else {
        addOpcodeRelAddr(55, modTLen);
        compileTreeEmptyCheck(qn.target, emptyInfo);
        addOpcodeRelAddr(56, -(modTLen + 2));
      } 
    } else if (qn.upper == 0 && qn.isRefered) {
      addOpcodeRelAddr(55, tlen);
      compileTree(qn.target);
    } else if (!infinite && qn.greedy && (qn.upper == 1 || (tlen + 2) * qn.upper <= 50)) {
      int n = qn.upper - qn.lower;
      compileTreeNTimes(qn.target, qn.lower);
      for (int i = 0; i < n; i++) {
        addOpcodeRelAddr(56, (n - i) * tlen + (n - i - 1) * 2);
        compileTree(qn.target);
      } 
    } else if (!qn.greedy && qn.upper == 1 && qn.lower == 0) {
      addOpcodeRelAddr(56, 2);
      addOpcodeRelAddr(55, tlen);
      compileTree(qn.target);
    } else {
      compileRangeRepeatNode(qn, modTLen, emptyInfo);
    } 
  }
  
  private int compileLengthOptionNode(EncloseNode node) {
    int prev = this.regex.options;
    this.regex.options = node.option;
    int tlen = compileLengthTree(node.target);
    this.regex.options = prev;
    if (Option.isDynamic(prev ^ node.option))
      return 5 + tlen + 2; 
    return tlen;
  }
  
  protected void compileOptionNode(EncloseNode node) {
    int prev = this.regex.options;
    if (Option.isDynamic(prev ^ node.option)) {
      addOpcodeOption(86, node.option);
      addOpcodeOption(87, prev);
      addOpcode(54);
    } 
    this.regex.options = node.option;
    compileTree(node.target);
    this.regex.options = prev;
    if (Option.isDynamic(prev ^ node.option))
      addOpcodeOption(87, prev); 
  }
  
  private int compileLengthEncloseNode(EncloseNode node) {
    int tlen, len;
    if (node.isOption())
      return compileLengthOptionNode(node); 
    if (node.target != null) {
      tlen = compileLengthTree(node.target);
    } else {
      tlen = 0;
    } 
    switch (node.type) {
      case 1:
        if (BitStatus.bsAt(this.regex.btMemStart, node.regNum)) {
          len = 2;
        } else {
          len = 2;
        } 
        len += tlen + (BitStatus.bsAt(this.regex.btMemEnd, node.regNum) ? 2 : 2);
        return len;
      case 4:
        if (node.isStopBtSimpleRepeat()) {
          QuantifierNode qn = (QuantifierNode)node.target;
          tlen = compileLengthTree(qn.target);
          len = tlen * qn.lower + 2 + tlen + 1 + 2;
        } else {
          len = 1 + tlen + 1;
        } 
        return len;
    } 
    newInternalException("internal parser error (bug)");
    return 0;
  }
  
  protected void compileEncloseNode(EncloseNode node) {
    switch (node.type) {
      case 1:
        if (BitStatus.bsAt(this.regex.btMemStart, node.regNum)) {
          addOpcode(49);
        } else {
          addOpcode(48);
        } 
        addMemNum(node.regNum);
        compileTree(node.target);
        if (BitStatus.bsAt(this.regex.btMemEnd, node.regNum)) {
          addOpcode(50);
        } else {
          addOpcode(52);
        } 
        addMemNum(node.regNum);
        return;
      case 4:
        if (node.isStopBtSimpleRepeat()) {
          QuantifierNode qn = (QuantifierNode)node.target;
          compileTreeNTimes(qn.target, qn.lower);
          int len = compileLengthTree(qn.target);
          addOpcodeRelAddr(56, len + 1 + 2);
          compileTree(qn.target);
          addOpcode(57);
          addOpcodeRelAddr(55, -(2 + len + 1 + 2));
        } else {
          addOpcode(74);
          compileTree(node.target);
          addOpcode(75);
        } 
        return;
    } 
    newInternalException("internal parser error (bug)");
  }
  
  private int compileLengthAnchorNode(AnchorNode node) {
    int tlen;
    if (node.target != null) {
      tlen = compileLengthTree(node.target);
    } else {
      tlen = 0;
    } 
    switch (node.type) {
      case 1024:
        len = 1 + tlen + 1;
        return len;
      case 2048:
        len = 2 + tlen + 1;
        return len;
      case 4096:
        len = 2 + tlen;
        return len;
      case 8192:
        len = 3 + tlen + 1;
        return len;
    } 
    int len = 1;
    return len;
  }
  
  protected void compileAnchorNode(AnchorNode node) {
    int len, n;
    switch (node.type) {
      case 1:
        addOpcode(35);
        return;
      case 8:
        addOpcode(36);
        return;
      case 2:
        addOpcode(37);
        return;
      case 32:
        addOpcode(38);
        return;
      case 16:
        addOpcode(39);
        return;
      case 4:
        addOpcode(40);
        return;
      case 64:
        addOpcode(31);
        return;
      case 128:
        addOpcode(32);
        return;
      case 256:
        addOpcode(33);
        return;
      case 512:
        addOpcode(34);
        return;
      case 1024:
        addOpcode(70);
        compileTree(node.target);
        addOpcode(71);
        return;
      case 2048:
        len = compileLengthTree(node.target);
        addOpcodeRelAddr(72, len + 1);
        compileTree(node.target);
        addOpcode(73);
        return;
      case 4096:
        addOpcode(76);
        if (node.charLength < 0) {
          n = this.analyser.getCharLengthTree(node.target);
          if (this.analyser.returnCode != 0)
            newSyntaxException("invalid pattern in look-behind"); 
        } else {
          n = node.charLength;
        } 
        addLength(n);
        compileTree(node.target);
        return;
      case 8192:
        len = compileLengthTree(node.target);
        addOpcodeRelAddr(77, len + 1);
        if (node.charLength < 0) {
          n = this.analyser.getCharLengthTree(node.target);
          if (this.analyser.returnCode != 0)
            newSyntaxException("invalid pattern in look-behind"); 
        } else {
          n = node.charLength;
        } 
        addLength(n);
        compileTree(node.target);
        addOpcode(78);
        return;
    } 
    newInternalException("internal parser error (bug)");
  }
  
  private int compileLengthTree(Node node) {
    ConsAltNode lin, aln;
    int n;
    StringNode sn;
    BackRefNode br;
    int len = 0;
    switch (node.getType()) {
      case 8:
        lin = (ConsAltNode)node;
        do {
          len += compileLengthTree(lin.car);
        } while ((lin = lin.cdr) != null);
        return len;
      case 9:
        aln = (ConsAltNode)node;
        n = 0;
        do {
          len += compileLengthTree(aln.car);
          n++;
        } while ((aln = aln.cdr) != null);
        len += 4 * (n - 1);
        return len;
      case 0:
        sn = (StringNode)node;
        if (sn.isRaw()) {
          len = compileLengthStringRawNode(sn);
        } else {
          len = compileLengthStringNode((Node)sn);
        } 
        return len;
      case 1:
        len = compileLengthCClassNode((CClassNode)node);
        return len;
      case 2:
      case 3:
        len = 1;
        return len;
      case 4:
        br = (BackRefNode)node;
        len = (!Option.isIgnoreCase(this.regex.options) && br.backRef <= 2) ? 1 : 2;
        return len;
      case 5:
        len = compileNonCECLengthQuantifierNode((QuantifierNode)node);
        return len;
      case 6:
        len = compileLengthEncloseNode((EncloseNode)node);
        return len;
      case 7:
        len = compileLengthAnchorNode((AnchorNode)node);
        return len;
    } 
    newInternalException("internal parser error (bug)");
    return len;
  }
  
  private void ensure(int size) {
    if (size >= this.code.length) {
      int length = this.code.length << 1;
      while (length <= size)
        length <<= 1; 
      int[] tmp = new int[length];
      System.arraycopy(this.code, 0, tmp, 0, this.code.length);
      this.code = tmp;
    } 
  }
  
  private void addInt(int i) {
    if (this.codeLength >= this.code.length) {
      int[] tmp = new int[this.code.length << 1];
      System.arraycopy(this.code, 0, tmp, 0, this.code.length);
      this.code = tmp;
    } 
    this.code[this.codeLength++] = i;
  }
  
  void setInt(int i, int offset) {
    ensure(offset);
    this.regex.code[offset] = i;
  }
  
  private void addObject(Object o) {
    if (this.regex.operands == null) {
      this.regex.operands = new Object[4];
    } else if (this.regex.operandLength >= this.regex.operands.length) {
      Object[] tmp = new Object[this.regex.operands.length << 1];
      System.arraycopy(this.regex.operands, 0, tmp, 0, this.regex.operands.length);
      this.regex.operands = tmp;
    } 
    addInt(this.regex.operandLength);
    this.regex.operands[this.regex.operandLength++] = o;
  }
  
  private void addChars(char[] chars, int pp, int length) {
    ensure(this.codeLength + length);
    int p = pp;
    int end = p + length;
    while (p < end)
      this.code[this.codeLength++] = chars[p++]; 
  }
  
  private void addInts(int[] ints, int length) {
    ensure(this.codeLength + length);
    System.arraycopy(ints, 0, this.code, this.codeLength, length);
    this.codeLength += length;
  }
  
  private void addOpcode(int opcode) {
    addInt(opcode);
    switch (opcode) {
      case 25:
      case 26:
      case 27:
      case 28:
      case 49:
      case 50:
      case 51:
      case 53:
      case 56:
      case 58:
      case 59:
      case 60:
      case 61:
      case 63:
      case 64:
      case 65:
      case 66:
      case 69:
      case 70:
      case 72:
      case 74:
      case 77:
      case 79:
      case 80:
      case 81:
      case 82:
      case 83:
      case 84:
      case 85:
        this.regex.stackNeeded = true;
        break;
    } 
  }
  
  private void addStateCheckNum(int num) {
    addInt(num);
  }
  
  private void addRelAddr(int addr) {
    addInt(addr);
  }
  
  private void addAbsAddr(int addr) {
    addInt(addr);
  }
  
  private void addLength(int length) {
    addInt(length);
  }
  
  private void addMemNum(int num) {
    addInt(num);
  }
  
  private void addPointer(Object o) {
    addObject(o);
  }
  
  private void addOption(int option) {
    addInt(option);
  }
  
  private void addOpcodeRelAddr(int opcode, int addr) {
    addOpcode(opcode);
    addRelAddr(addr);
  }
  
  private void addOpcodeOption(int opcode, int option) {
    addOpcode(opcode);
    addOption(option);
  }
  
  private void addTemplate(char[] chars) {
    if (this.templateNum == 0) {
      this.templates = new char[2][];
    } else if (this.templateNum == this.templates.length) {
      char[][] tmp = new char[this.templateNum * 2][];
      System.arraycopy(this.templates, 0, tmp, 0, this.templateNum);
      this.templates = tmp;
    } 
    this.templates[this.templateNum++] = chars;
  }
}
