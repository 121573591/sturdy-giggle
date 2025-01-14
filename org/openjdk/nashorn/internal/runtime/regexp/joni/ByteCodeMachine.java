package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

class ByteCodeMachine extends StackMachine {
  private int bestLen;
  
  private int s = 0;
  
  private int range;
  
  private int sprev;
  
  private int sstart;
  
  private int sbegin;
  
  private final int[] code;
  
  private int ip;
  
  ByteCodeMachine(Regex regex, char[] chars, int p, int end) {
    super(regex, chars, p, end);
    this.code = regex.code;
  }
  
  private boolean stringCmpIC(int caseFlodFlag, int s1p, IntHolder ps2, int mbLen, int textEnd) {
    int s1 = s1p;
    int s2 = ps2.value;
    int end1 = s1 + mbLen;
    while (s1 < end1) {
      char c1 = EncodingHelper.toLowerCase(this.chars[s1++]);
      char c2 = EncodingHelper.toLowerCase(this.chars[s2++]);
      if (c1 != c2)
        return false; 
    } 
    ps2.value = s2;
    return true;
  }
  
  private void debugMatchBegin() {
    Config.log.println("match_at: str: " + this.str + ", end: " + this.end + ", start: " + this.sstart + ", sprev: " + this.sprev);
    Config.log.println("size: " + this.end - this.str + ", start offset: " + this.sstart - this.str);
  }
  
  private void debugMatchLoop() {}
  
  protected final int matchAt(int r, int ss, int sp) {
    this.range = r;
    this.sstart = ss;
    this.sprev = sp;
    this.stk = 0;
    this.ip = 0;
    init();
    this.bestLen = -1;
    this.s = ss;
    int[] c = this.code;
    while (true) {
      this.sbegin = this.s;
      switch (c[this.ip++]) {
        case 1:
          if (opEnd())
            return finish(); 
          continue;
        case 2:
          opExact1();
          continue;
        case 3:
          opExact2();
          continue;
        case 4:
          opExact3();
          continue;
        case 5:
          opExact4();
          continue;
        case 6:
          opExact5();
          continue;
        case 7:
          opExactN();
          continue;
        case 14:
          opExact1IC();
          continue;
        case 15:
          opExactNIC();
          continue;
        case 16:
          opCClass();
          continue;
        case 17:
          opCClassMB();
          continue;
        case 18:
          opCClassMIX();
          continue;
        case 19:
          opCClassNot();
          continue;
        case 20:
          opCClassMBNot();
          continue;
        case 21:
          opCClassMIXNot();
          continue;
        case 22:
          opCClassNode();
          continue;
        case 23:
          opAnyChar();
          continue;
        case 24:
          opAnyCharML();
          continue;
        case 25:
          opAnyCharStar();
          continue;
        case 26:
          opAnyCharMLStar();
          continue;
        case 27:
          opAnyCharStarPeekNext();
          continue;
        case 28:
          opAnyCharMLStarPeekNext();
          continue;
        case 29:
          opWord();
          continue;
        case 30:
          opNotWord();
          continue;
        case 31:
          opWordBound();
          continue;
        case 32:
          opNotWordBound();
          continue;
        case 33:
          opWordBegin();
          continue;
        case 34:
          opWordEnd();
          continue;
        case 35:
          opBeginBuf();
          continue;
        case 36:
          opEndBuf();
          continue;
        case 37:
          opBeginLine();
          continue;
        case 38:
          opEndLine();
          continue;
        case 39:
          opSemiEndBuf();
          continue;
        case 40:
          opBeginPosition();
          continue;
        case 49:
          opMemoryStartPush();
          continue;
        case 48:
          opMemoryStart();
          continue;
        case 50:
          opMemoryEndPush();
          continue;
        case 52:
          opMemoryEnd();
          continue;
        case 51:
          opMemoryEndPushRec();
          continue;
        case 53:
          opMemoryEndRec();
          continue;
        case 41:
          opBackRef1();
          continue;
        case 42:
          opBackRef2();
          continue;
        case 43:
          opBackRefN();
          continue;
        case 44:
          opBackRefNIC();
          continue;
        case 45:
          opBackRefMulti();
          continue;
        case 46:
          opBackRefMultiIC();
          continue;
        case 47:
          opBackRefAtLevel();
          continue;
        case 66:
          opNullCheckStart();
          continue;
        case 67:
          opNullCheckEnd();
          continue;
        case 68:
          opNullCheckEndMemST();
          continue;
        case 55:
          opJump();
          continue;
        case 56:
          opPush();
          continue;
        case 57:
          opPop();
          continue;
        case 58:
          opPushOrJumpExact1();
          continue;
        case 59:
          opPushIfPeekNext();
          continue;
        case 60:
          opRepeat();
          continue;
        case 61:
          opRepeatNG();
          continue;
        case 62:
          opRepeatInc();
          continue;
        case 64:
          opRepeatIncSG();
          continue;
        case 63:
          opRepeatIncNG();
          continue;
        case 65:
          opRepeatIncNGSG();
          continue;
        case 70:
          opPushPos();
          continue;
        case 71:
          opPopPos();
          continue;
        case 72:
          opPushPosNot();
          continue;
        case 73:
          opFailPos();
          continue;
        case 74:
          opPushStopBT();
          continue;
        case 75:
          opPopStopBT();
          continue;
        case 76:
          opLookBehind();
          continue;
        case 77:
          opPushLookBehindNot();
          continue;
        case 78:
          opFailLookBehindNot();
          continue;
        case 0:
          return finish();
        case 54:
          opFail();
          continue;
      } 
      break;
    } 
    throw new InternalException("undefined bytecode (bug)");
  }
  
  private boolean opEnd() {
    int n = this.s - this.sstart;
    if (n > this.bestLen) {
      if (Option.isFindLongest(this.regex.options))
        if (n > this.msaBestLen) {
          this.msaBestLen = n;
          this.msaBestS = this.sstart;
        } else {
          return endBestLength();
        }  
      this.bestLen = n;
      Region region = this.msaRegion;
      if (region != null) {
        region.beg[0] = this.msaBegin = this.sstart - this.str;
        region.end[0] = this.msaEnd = this.s - this.str;
        for (int i = 1; i <= this.regex.numMem; i++) {
          if (this.repeatStk[this.memEndStk + i] != -1) {
            region.beg[i] = BitStatus.bsAt(this.regex.btMemStart, i) ? (
              this.stack[this.repeatStk[this.memStartStk + i]].getMemPStr() - this.str) : (
              this.repeatStk[this.memStartStk + i] - this.str);
            region.end[i] = BitStatus.bsAt(this.regex.btMemEnd, i) ? 
              this.stack[this.repeatStk[this.memEndStk + i]].getMemPStr() : (
              this.repeatStk[this.memEndStk + i] - this.str);
          } else {
            region.end[i] = -1;
            region.beg[i] = -1;
          } 
        } 
      } else {
        this.msaBegin = this.sstart - this.str;
        this.msaEnd = this.s - this.str;
      } 
    } else {
      Region region = this.msaRegion;
      if (region != null) {
        region.clear();
      } else {
        this.msaBegin = this.msaEnd = 0;
      } 
    } 
    return endBestLength();
  }
  
  private boolean endBestLength() {
    if (Option.isFindCondition(this.regex.options)) {
      if (Option.isFindNotEmpty(this.regex.options) && this.s == this.sstart) {
        this.bestLen = -1;
        opFail();
        return false;
      } 
      if (Option.isFindLongest(this.regex.options) && this.s < this.range) {
        opFail();
        return false;
      } 
    } 
    return true;
  }
  
  private void opExact1() {
    if (this.s >= this.range || this.code[this.ip] != this.chars[this.s++]) {
      opFail();
      return;
    } 
    this.ip++;
    this.sprev = this.sbegin;
  }
  
  private void opExact2() {
    if (this.s + 2 > this.range) {
      opFail();
      return;
    } 
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.sprev = this.s;
    this.ip++;
    this.s++;
  }
  
  private void opExact3() {
    if (this.s + 3 > this.range) {
      opFail();
      return;
    } 
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.sprev = this.s;
    this.ip++;
    this.s++;
  }
  
  private void opExact4() {
    if (this.s + 4 > this.range) {
      opFail();
      return;
    } 
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.sprev = this.s;
    this.ip++;
    this.s++;
  }
  
  private void opExact5() {
    if (this.s + 5 > this.range) {
      opFail();
      return;
    } 
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.ip++;
    this.s++;
    if (this.code[this.ip] != this.chars[this.s]) {
      opFail();
      return;
    } 
    this.sprev = this.s;
    this.ip++;
    this.s++;
  }
  
  private void opExactN() {
    int tlen = this.code[this.ip++];
    if (this.s + tlen > this.range) {
      opFail();
      return;
    } 
    char[] bs = this.regex.templates[this.code[this.ip++]];
    int ps = this.code[this.ip++];
    while (tlen-- > 0) {
      if (bs[ps++] != this.chars[this.s++]) {
        opFail();
        return;
      } 
    } 
    this.sprev = this.s - 1;
  }
  
  private void opExact1IC() {
    if (this.s >= this.range || this.code[this.ip] != EncodingHelper.toLowerCase(this.chars[this.s++])) {
      opFail();
      return;
    } 
    this.ip++;
    this.sprev = this.sbegin;
  }
  
  private void opExactNIC() {
    int tlen = this.code[this.ip++];
    if (this.s + tlen > this.range) {
      opFail();
      return;
    } 
    char[] bs = this.regex.templates[this.code[this.ip++]];
    int ps = this.code[this.ip++];
    while (tlen-- > 0) {
      if (bs[ps++] != EncodingHelper.toLowerCase(this.chars[this.s++])) {
        opFail();
        return;
      } 
    } 
    this.sprev = this.s - 1;
  }
  
  private boolean isInBitSet() {
    int c = this.chars[this.s];
    return (c <= 255 && (this.code[this.ip + (c >>> BitSet.ROOM_SHIFT)] & 1 << c) != 0);
  }
  
  private void opCClass() {
    if (this.s >= this.range || !isInBitSet()) {
      opFail();
      return;
    } 
    this.ip += 8;
    this.s++;
    this.sprev = this.sbegin;
  }
  
  private boolean isInClassMB() {
    int tlen = this.code[this.ip++];
    if (this.s >= this.range)
      return false; 
    int ss = this.s;
    this.s++;
    int c = this.chars[ss];
    if (!EncodingHelper.isInCodeRange(this.code, this.ip, c))
      return false; 
    this.ip += tlen;
    return true;
  }
  
  private void opCClassMB() {
    if (this.s >= this.range || this.chars[this.s] <= 'ÿ') {
      opFail();
      return;
    } 
    if (!isInClassMB()) {
      opFail();
      return;
    } 
    this.sprev = this.sbegin;
  }
  
  private void opCClassMIX() {
    if (this.s >= this.range) {
      opFail();
      return;
    } 
    if (this.chars[this.s] > 'ÿ') {
      this.ip += 8;
      if (!isInClassMB()) {
        opFail();
        return;
      } 
    } else {
      if (!isInBitSet()) {
        opFail();
        return;
      } 
      this.ip += 8;
      int tlen = this.code[this.ip++];
      this.ip += tlen;
      this.s++;
    } 
    this.sprev = this.sbegin;
  }
  
  private void opCClassNot() {
    if (this.s >= this.range || isInBitSet()) {
      opFail();
      return;
    } 
    this.ip += 8;
    this.s++;
    this.sprev = this.sbegin;
  }
  
  private boolean isNotInClassMB() {
    int tlen = this.code[this.ip++];
    if (this.s + 1 > this.range) {
      if (this.s >= this.range)
        return false; 
      this.s = this.end;
      this.ip += tlen;
      return true;
    } 
    int ss = this.s;
    this.s++;
    int c = this.chars[ss];
    if (EncodingHelper.isInCodeRange(this.code, this.ip, c))
      return false; 
    this.ip += tlen;
    return true;
  }
  
  private void opCClassMBNot() {
    if (this.s >= this.range) {
      opFail();
      return;
    } 
    if (this.chars[this.s] <= 'ÿ') {
      this.s++;
      int tlen = this.code[this.ip++];
      this.ip += tlen;
      this.sprev = this.sbegin;
      return;
    } 
    if (!isNotInClassMB()) {
      opFail();
      return;
    } 
    this.sprev = this.sbegin;
  }
  
  private void opCClassMIXNot() {
    if (this.s >= this.range) {
      opFail();
      return;
    } 
    if (this.chars[this.s] > 'ÿ') {
      this.ip += 8;
      if (!isNotInClassMB()) {
        opFail();
        return;
      } 
    } else {
      if (isInBitSet()) {
        opFail();
        return;
      } 
      this.ip += 8;
      int tlen = this.code[this.ip++];
      this.ip += tlen;
      this.s++;
    } 
    this.sprev = this.sbegin;
  }
  
  private void opCClassNode() {
    if (this.s >= this.range) {
      opFail();
      return;
    } 
    CClassNode cc = (CClassNode)this.regex.operands[this.code[this.ip++]];
    int ss = this.s;
    this.s++;
    int c = this.chars[ss];
    if (!cc.isCodeInCCLength(c)) {
      opFail();
      return;
    } 
    this.sprev = this.sbegin;
  }
  
  private void opAnyChar() {
    if (this.s >= this.range) {
      opFail();
      return;
    } 
    if (EncodingHelper.isNewLine(this.chars[this.s])) {
      opFail();
      return;
    } 
    this.s++;
    this.sprev = this.sbegin;
  }
  
  private void opAnyCharML() {
    if (this.s >= this.range) {
      opFail();
      return;
    } 
    this.s++;
    this.sprev = this.sbegin;
  }
  
  private void opAnyCharStar() {
    char[] ch = this.chars;
    while (this.s < this.range) {
      pushAlt(this.ip, this.s, this.sprev);
      if (EncodingHelper.isNewLine(ch, this.s, this.end)) {
        opFail();
        return;
      } 
      this.sprev = this.s;
      this.s++;
    } 
  }
  
  private void opAnyCharMLStar() {
    while (this.s < this.range) {
      pushAlt(this.ip, this.s, this.sprev);
      this.sprev = this.s;
      this.s++;
    } 
  }
  
  private void opAnyCharStarPeekNext() {
    char c = (char)this.code[this.ip];
    char[] ch = this.chars;
    while (this.s < this.range) {
      char b = ch[this.s];
      if (c == b)
        pushAlt(this.ip + 1, this.s, this.sprev); 
      if (EncodingHelper.isNewLine(b)) {
        opFail();
        return;
      } 
      this.sprev = this.s;
      this.s++;
    } 
    this.ip++;
    this.sprev = this.sbegin;
  }
  
  private void opAnyCharMLStarPeekNext() {
    char c = (char)this.code[this.ip];
    char[] ch = this.chars;
    while (this.s < this.range) {
      if (c == ch[this.s])
        pushAlt(this.ip + 1, this.s, this.sprev); 
      this.sprev = this.s;
      this.s++;
    } 
    this.ip++;
    this.sprev = this.sbegin;
  }
  
  private void opWord() {
    if (this.s >= this.range || !EncodingHelper.isWord(this.chars[this.s])) {
      opFail();
      return;
    } 
    this.s++;
    this.sprev = this.sbegin;
  }
  
  private void opNotWord() {
    if (this.s >= this.range || EncodingHelper.isWord(this.chars[this.s])) {
      opFail();
      return;
    } 
    this.s++;
    this.sprev = this.sbegin;
  }
  
  private void opWordBound() {
    if (this.s == this.str) {
      if (this.s >= this.range || !EncodingHelper.isWord(this.chars[this.s])) {
        opFail();
        return;
      } 
    } else if (this.s == this.end) {
      if (this.sprev >= this.end || !EncodingHelper.isWord(this.chars[this.sprev])) {
        opFail();
        return;
      } 
    } else if (EncodingHelper.isWord(this.chars[this.s]) == EncodingHelper.isWord(this.chars[this.sprev])) {
      opFail();
      return;
    } 
  }
  
  private void opNotWordBound() {
    if (this.s == this.str) {
      if (this.s < this.range && EncodingHelper.isWord(this.chars[this.s])) {
        opFail();
        return;
      } 
    } else if (this.s == this.end) {
      if (this.sprev < this.end && EncodingHelper.isWord(this.chars[this.sprev])) {
        opFail();
        return;
      } 
    } else if (EncodingHelper.isWord(this.chars[this.s]) != EncodingHelper.isWord(this.chars[this.sprev])) {
      opFail();
      return;
    } 
  }
  
  private void opWordBegin() {
    if (this.s < this.range && EncodingHelper.isWord(this.chars[this.s]) && (
      this.s == this.str || !EncodingHelper.isWord(this.chars[this.sprev])))
      return; 
    opFail();
  }
  
  private void opWordEnd() {
    if (this.s != this.str && EncodingHelper.isWord(this.chars[this.sprev]) && (
      this.s == this.end || !EncodingHelper.isWord(this.chars[this.s])))
      return; 
    opFail();
  }
  
  private void opBeginBuf() {
    if (this.s != this.str)
      opFail(); 
  }
  
  private void opEndBuf() {
    if (this.s != this.end)
      opFail(); 
  }
  
  private void opBeginLine() {
    if (this.s == this.str) {
      if (Option.isNotBol(this.msaOptions))
        opFail(); 
      return;
    } 
    if (EncodingHelper.isNewLine(this.chars, this.sprev, this.end) && this.s != this.end)
      return; 
    opFail();
  }
  
  private void opEndLine() {
    if (this.s == this.end) {
      if ((this.str == this.end || !EncodingHelper.isNewLine(this.chars, this.sprev, this.end)) && 
        Option.isNotEol(this.msaOptions))
        opFail(); 
      return;
    } 
    if (EncodingHelper.isNewLine(this.chars, this.s, this.end))
      return; 
    opFail();
  }
  
  private void opSemiEndBuf() {
    if (this.s == this.end) {
      if ((this.str == this.end || !EncodingHelper.isNewLine(this.chars, this.sprev, this.end)) && 
        Option.isNotEol(this.msaOptions))
        opFail(); 
      return;
    } 
    if (EncodingHelper.isNewLine(this.chars, this.s, this.end) && this.s + 1 == this.end)
      return; 
    opFail();
  }
  
  private void opBeginPosition() {
    if (this.s != this.msaStart)
      opFail(); 
  }
  
  private void opMemoryStartPush() {
    int mem = this.code[this.ip++];
    pushMemStart(mem, this.s);
  }
  
  private void opMemoryStart() {
    int mem = this.code[this.ip++];
    this.repeatStk[this.memStartStk + mem] = this.s;
  }
  
  private void opMemoryEndPush() {
    int mem = this.code[this.ip++];
    pushMemEnd(mem, this.s);
  }
  
  private void opMemoryEnd() {
    int mem = this.code[this.ip++];
    this.repeatStk[this.memEndStk + mem] = this.s;
  }
  
  private void opMemoryEndPushRec() {
    int mem = this.code[this.ip++];
    int stkp = getMemStart(mem);
    pushMemEnd(mem, this.s);
    this.repeatStk[this.memStartStk + mem] = stkp;
  }
  
  private void opMemoryEndRec() {
    int mem = this.code[this.ip++];
    this.repeatStk[this.memEndStk + mem] = this.s;
    int stkp = getMemStart(mem);
    if (BitStatus.bsAt(this.regex.btMemStart, mem)) {
      this.repeatStk[this.memStartStk + mem] = stkp;
    } else {
      this.repeatStk[this.memStartStk + mem] = this.stack[stkp].getMemPStr();
    } 
    pushMemEndMark(mem);
  }
  
  private boolean backrefInvalid(int mem) {
    return (this.repeatStk[this.memEndStk + mem] == -1 || this.repeatStk[this.memStartStk + mem] == -1);
  }
  
  private int backrefStart(int mem) {
    return BitStatus.bsAt(this.regex.btMemStart, mem) ? this.stack[this.repeatStk[this.memStartStk + mem]].getMemPStr() : this.repeatStk[this.memStartStk + mem];
  }
  
  private int backrefEnd(int mem) {
    return BitStatus.bsAt(this.regex.btMemEnd, mem) ? this.stack[this.repeatStk[this.memEndStk + mem]].getMemPStr() : this.repeatStk[this.memEndStk + mem];
  }
  
  private void backref(int mem) {
    if (mem > this.regex.numMem || backrefInvalid(mem)) {
      opFail();
      return;
    } 
    int pstart = backrefStart(mem);
    int pend = backrefEnd(mem);
    int n = pend - pstart;
    if (this.s + n > this.range) {
      opFail();
      return;
    } 
    this.sprev = this.s;
    while (n-- > 0) {
      if (this.chars[pstart++] != this.chars[this.s++]) {
        opFail();
        return;
      } 
    } 
    if (this.sprev < this.range)
      while (this.sprev + 1 < this.s)
        this.sprev++;  
  }
  
  private void opBackRef1() {
    backref(1);
  }
  
  private void opBackRef2() {
    backref(2);
  }
  
  private void opBackRefN() {
    backref(this.code[this.ip++]);
  }
  
  private void opBackRefNIC() {
    int mem = this.code[this.ip++];
    if (mem > this.regex.numMem || backrefInvalid(mem)) {
      opFail();
      return;
    } 
    int pstart = backrefStart(mem);
    int pend = backrefEnd(mem);
    int n = pend - pstart;
    if (this.s + n > this.range) {
      opFail();
      return;
    } 
    this.sprev = this.s;
    this.value = this.s;
    if (!stringCmpIC(this.regex.caseFoldFlag, pstart, this, n, this.end)) {
      opFail();
      return;
    } 
    this.s = this.value;
    while (this.sprev + 1 < this.s)
      this.sprev++; 
  }
  
  private void opBackRefMulti() {
    int tlen = this.code[this.ip++];
    int i;
    label30: for (i = 0; i < tlen; ) {
      int mem = this.code[this.ip++];
      if (backrefInvalid(mem)) {
        i++;
        continue;
      } 
      int pstart = backrefStart(mem);
      int pend = backrefEnd(mem);
      int n = pend - pstart;
      if (this.s + n > this.range) {
        opFail();
        return;
      } 
      this.sprev = this.s;
      int swork = this.s;
      while (n-- > 0) {
        if (this.chars[pstart++] != this.chars[swork++])
          continue label30; 
      } 
      this.s = swork;
      if (this.sprev < this.range)
        while (this.sprev + 1 < this.s)
          this.sprev++;  
      this.ip += tlen - i - 1;
    } 
    if (i == tlen) {
      opFail();
      return;
    } 
  }
  
  private void opBackRefMultiIC() {
    int tlen = this.code[this.ip++];
    int i;
    for (i = 0; i < tlen; i++) {
      int mem = this.code[this.ip++];
      if (!backrefInvalid(mem)) {
        int pstart = backrefStart(mem);
        int pend = backrefEnd(mem);
        int n = pend - pstart;
        if (this.s + n > this.range) {
          opFail();
          return;
        } 
        this.sprev = this.s;
        this.value = this.s;
        if (stringCmpIC(this.regex.caseFoldFlag, pstart, this, n, this.end)) {
          this.s = this.value;
          while (this.sprev + 1 < this.s)
            this.sprev++; 
          this.ip += tlen - i - 1;
          break;
        } 
      } 
    } 
    if (i == tlen) {
      opFail();
      return;
    } 
  }
  
  private boolean memIsInMemp(int mem, int num, int mempp) {
    for (int i = 0, memp = mempp; i < num; i++) {
      int m = this.code[memp++];
      if (mem == m)
        return true; 
    } 
    return false;
  }
  
  private boolean backrefMatchAtNestedLevel(boolean ignoreCase, int caseFoldFlag, int nest, int memNum, int memp) {
    int pend = -1;
    int level = 0;
    int k = this.stk - 1;
    while (k >= 0) {
      StackEntry e = this.stack[k];
      if (e.type == 2048) {
        level--;
      } else if (e.type == 2304) {
        level++;
      } else if (level == nest) {
        if (e.type == 256) {
          if (memIsInMemp(e.getMemNum(), memNum, memp)) {
            int pstart = e.getMemPStr();
            if (pend != -1) {
              if (pend - pstart > this.end - this.s)
                return false; 
              int p = pstart;
              this.value = this.s;
              if (ignoreCase) {
                if (!stringCmpIC(caseFoldFlag, pstart, this, pend - pstart, this.end))
                  return false; 
              } else {
                while (p < pend) {
                  if (this.chars[p++] != this.chars[this.value++])
                    return false; 
                } 
              } 
              this.s = this.value;
              return true;
            } 
          } 
        } else if (e.type == 33280 && 
          memIsInMemp(e.getMemNum(), memNum, memp)) {
          pend = e.getMemPStr();
        } 
      } 
      k--;
    } 
    return false;
  }
  
  private void opBackRefAtLevel() {
    int ic = this.code[this.ip++];
    int level = this.code[this.ip++];
    int tlen = this.code[this.ip++];
    this.sprev = this.s;
    if (backrefMatchAtNestedLevel((ic != 0), this.regex.caseFoldFlag, level, tlen, this.ip)) {
      while (this.sprev + 1 < this.s)
        this.sprev++; 
      this.ip += tlen;
    } else {
      opFail();
      return;
    } 
  }
  
  private void opNullCheckStart() {
    int mem = this.code[this.ip++];
    pushNullCheckStart(mem, this.s);
  }
  
  private void nullCheckFound() {
    switch (this.code[this.ip++]) {
      case 55:
      case 56:
        this.ip++;
        return;
      case 62:
      case 63:
      case 64:
      case 65:
        this.ip++;
        return;
    } 
    throw new InternalException("unexpected bytecode (bug)");
  }
  
  private void opNullCheckEnd() {
    int mem = this.code[this.ip++];
    int isNull = nullCheck(mem, this.s);
    if (isNull != 0)
      nullCheckFound(); 
  }
  
  private void opNullCheckEndMemST() {
    int mem = this.code[this.ip++];
    int isNull = nullCheckMemSt(mem, this.s);
    if (isNull != 0) {
      if (isNull == -1) {
        opFail();
        return;
      } 
      nullCheckFound();
    } 
  }
  
  private void opJump() {
    this.ip += this.code[this.ip] + 1;
  }
  
  private void opPush() {
    int addr = this.code[this.ip++];
    pushAlt(this.ip + addr, this.s, this.sprev);
  }
  
  private void opPop() {
    popOne();
  }
  
  private void opPushOrJumpExact1() {
    int addr = this.code[this.ip++];
    if (this.s < this.range && this.code[this.ip] == this.chars[this.s]) {
      this.ip++;
      pushAlt(this.ip + addr, this.s, this.sprev);
      return;
    } 
    this.ip += addr + 1;
  }
  
  private void opPushIfPeekNext() {
    int addr = this.code[this.ip++];
    if (this.s < this.range && this.code[this.ip] == this.chars[this.s]) {
      this.ip++;
      pushAlt(this.ip + addr, this.s, this.sprev);
      return;
    } 
    this.ip++;
  }
  
  private void opRepeat() {
    int mem = this.code[this.ip++];
    int addr = this.code[this.ip++];
    this.repeatStk[mem] = this.stk;
    pushRepeat(mem, this.ip);
    if (this.regex.repeatRangeLo[mem] == 0)
      pushAlt(this.ip + addr, this.s, this.sprev); 
  }
  
  private void opRepeatNG() {
    int mem = this.code[this.ip++];
    int addr = this.code[this.ip++];
    this.repeatStk[mem] = this.stk;
    pushRepeat(mem, this.ip);
    if (this.regex.repeatRangeLo[mem] == 0) {
      pushAlt(this.ip, this.s, this.sprev);
      this.ip += addr;
    } 
  }
  
  private void repeatInc(int mem, int si) {
    StackEntry e = this.stack[si];
    e.increaseRepeatCount();
    if (e.getRepeatCount() < this.regex.repeatRangeHi[mem])
      if (e.getRepeatCount() >= this.regex.repeatRangeLo[mem]) {
        pushAlt(this.ip, this.s, this.sprev);
        this.ip = e.getRepeatPCode();
      } else {
        this.ip = e.getRepeatPCode();
      }  
    pushRepeatInc(si);
  }
  
  private void opRepeatInc() {
    int mem = this.code[this.ip++];
    int si = this.repeatStk[mem];
    repeatInc(mem, si);
  }
  
  private void opRepeatIncSG() {
    int mem = this.code[this.ip++];
    int si = getRepeat(mem);
    repeatInc(mem, si);
  }
  
  private void repeatIncNG(int mem, int si) {
    StackEntry e = this.stack[si];
    e.increaseRepeatCount();
    if (e.getRepeatCount() < this.regex.repeatRangeHi[mem]) {
      if (e.getRepeatCount() >= this.regex.repeatRangeLo[mem]) {
        int pcode = e.getRepeatPCode();
        pushRepeatInc(si);
        pushAlt(pcode, this.s, this.sprev);
      } else {
        this.ip = e.getRepeatPCode();
        pushRepeatInc(si);
      } 
    } else if (e.getRepeatCount() == this.regex.repeatRangeHi[mem]) {
      pushRepeatInc(si);
    } 
  }
  
  private void opRepeatIncNG() {
    int mem = this.code[this.ip++];
    int si = this.repeatStk[mem];
    repeatIncNG(mem, si);
  }
  
  private void opRepeatIncNGSG() {
    int mem = this.code[this.ip++];
    int si = getRepeat(mem);
    repeatIncNG(mem, si);
  }
  
  private void opPushPos() {
    pushPos(this.s, this.sprev);
  }
  
  private void opPopPos() {
    StackEntry e = this.stack[posEnd()];
    this.s = e.getStatePStr();
    this.sprev = e.getStatePStrPrev();
  }
  
  private void opPushPosNot() {
    int addr = this.code[this.ip++];
    pushPosNot(this.ip + addr, this.s, this.sprev);
  }
  
  private void opFailPos() {
    popTilPosNot();
    opFail();
  }
  
  private void opPushStopBT() {
    pushStopBT();
  }
  
  private void opPopStopBT() {
    stopBtEnd();
  }
  
  private void opLookBehind() {
    int tlen = this.code[this.ip++];
    this.s = EncodingHelper.stepBack(this.str, this.s, tlen);
    if (this.s == -1) {
      opFail();
      return;
    } 
    this.sprev = EncodingHelper.prevCharHead(this.str, this.s);
  }
  
  private void opPushLookBehindNot() {
    int addr = this.code[this.ip++];
    int tlen = this.code[this.ip++];
    int q = EncodingHelper.stepBack(this.str, this.s, tlen);
    if (q == -1) {
      this.ip += addr;
    } else {
      pushLookBehindNot(this.ip + addr, this.s, this.sprev);
      this.s = q;
      this.sprev = EncodingHelper.prevCharHead(this.str, this.s);
    } 
  }
  
  private void opFailLookBehindNot() {
    popTilLookBehindNot();
    opFail();
  }
  
  private void opFail() {
    if (this.stack == null) {
      this.ip = this.regex.codeLength - 1;
      return;
    } 
    StackEntry e = pop();
    this.ip = e.getStatePCode();
    this.s = e.getStatePStr();
    this.sprev = e.getStatePStrPrev();
  }
  
  private int finish() {
    return this.bestLen;
  }
}
