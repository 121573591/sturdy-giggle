package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

class ByteCodePrinter {
  final int[] code;
  
  final int codeLength;
  
  final char[][] templates;
  
  Object[] operands;
  
  private static final String[] OpCodeNames = new String[] { 
      "finish", "end", "exact1", "exact2", "exact3", "exact4", "exact5", "exactn", "exactmb2-n1", "exactmb2-n2", 
      "exactmb2-n3", "exactmb2-n", "exactmb3n", "exactmbn", "exact1-ic", "exactn-ic", "cclass", "cclass-mb", "cclass-mix", "cclass-not", 
      "cclass-mb-not", "cclass-mix-not", "cclass-node", "anychar", "anychar-ml", "anychar*", "anychar-ml*", "anychar*-peek-next", "anychar-ml*-peek-next", "word", 
      "not-word", "word-bound", "not-word-bound", "word-begin", "word-end", "begin-buf", "end-buf", "begin-line", "end-line", "semi-end-buf", 
      "begin-position", "backref1", "backref2", "backrefn", "backrefn-ic", "backref_multi", "backref_multi-ic", "backref_at_level", "mem-start", "mem-start-push", 
      "mem-end-push", "mem-end-push-rec", "mem-end", "mem-end-rec", "fail", "jump", "push", "pop", "push-or-jump-e1", "push-if-peek-next", 
      "repeat", "repeat-ng", "repeat-inc", "repeat-inc-ng", "repeat-inc-sg", "repeat-inc-ng-sg", "null-check-start", "null-check-end", "null-check-end-memst", "null-check-end-memst-push", 
      "push-pos", "pop-pos", "push-pos-not", "fail-pos", "push-stop-bt", "pop-stop-bt", "look-behind", "push-look-behind-not", "fail-look-behind-not", "call", 
      "return", "state-check-push", "state-check-push-or-jump", "state-check", "state-check-anychar*", "state-check-anychar-ml*", "set-option-push", "set-option" };
  
  private static final int[] OpCodeArgTypes = new int[] { 
      0, 0, -1, -1, -1, -1, -1, -1, -1, -1, 
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
      -1, -1, -1, 0, 0, 0, 0, -1, -1, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 4, -1, -1, -1, -1, 4, 4, 
      4, 4, 4, 4, 0, 1, 1, 0, -1, -1, 
      -1, -1, 4, 4, 4, 4, 4, 4, 4, 4, 
      0, 0, 1, 0, 0, 0, -1, -1, 0, 2, 
      0, -1, -1, 6, 6, 6, 5, 5 };
  
  public ByteCodePrinter(Regex regex) {
    this.code = regex.code;
    this.codeLength = regex.codeLength;
    this.operands = regex.operands;
    this.templates = regex.templates;
  }
  
  public String byteCodeListToString() {
    return compiledByteCodeListToString();
  }
  
  private void pString(StringBuilder sb, int len, int s) {
    sb.append(":");
    sb.append(new String(this.code, s, len));
  }
  
  private void pLenString(StringBuilder sb, int len, int s) {
    sb.append(":").append(len).append(":");
    sb.append(new String(this.code, s, len));
  }
  
  private static void pLenStringFromTemplate(StringBuilder sb, int len, char[] tm, int idx) {
    sb.append(":T:").append(len).append(":");
    sb.append(tm, idx, len);
  }
  
  public int compiledByteCodeToString(StringBuilder sb, int bptr) {
    int len, n, mem, addr, scn, cod;
    BitSet bs;
    CClassNode cc;
    int tm, idx, i, option, level, j, bp = bptr;
    sb.append("[").append(OpCodeNames[this.code[bp]]);
    int argType = OpCodeArgTypes[this.code[bp]];
    int ip = bp;
    if (argType != -1) {
      bp++;
      switch (argType) {
        default:
          sb.append("]");
          return bp;
        case 1:
          sb.append(":(").append(this.code[bp]).append(")");
          bp++;
        case 2:
          sb.append(":(").append(this.code[bp]).append(")");
          bp++;
        case 3:
          sb.append(":").append(this.code[bp]);
          bp++;
        case 4:
          sb.append(":").append(this.code[bp]);
          bp++;
        case 5:
          sb.append(":").append(this.code[bp]);
          bp++;
        case 6:
          break;
      } 
      sb.append(":").append(this.code[bp]);
      bp += 2;
    } 
    switch (this.code[bp++]) {
      case 2:
      case 27:
      case 28:
        pString(sb, 1, bp++);
      case 3:
        pString(sb, 2, bp);
        bp += 2;
      case 4:
        pString(sb, 3, bp);
        bp += 3;
      case 5:
        pString(sb, 4, bp);
        bp += 4;
      case 6:
        pString(sb, 5, bp);
        bp += 5;
      case 7:
        len = this.code[bp];
        bp++;
        tm = this.code[bp];
        bp++;
        idx = this.code[bp];
        bp++;
        pLenStringFromTemplate(sb, len, this.templates[tm], idx);
      case 14:
        pString(sb, 1, bp);
        bp++;
      case 15:
        len = this.code[bp];
        bp++;
        tm = this.code[bp];
        bp++;
        idx = this.code[bp];
        bp++;
        pLenStringFromTemplate(sb, len, this.templates[tm], idx);
      case 16:
        bs = new BitSet();
        System.arraycopy(this.code, bp, bs.bits, 0, 8);
        n = bs.numOn();
        bp += 8;
        sb.append(":").append(n);
      case 19:
        bs = new BitSet();
        System.arraycopy(this.code, bp, bs.bits, 0, 8);
        n = bs.numOn();
        bp += 8;
        sb.append(":").append(n);
      case 17:
      case 20:
        len = this.code[bp];
        bp++;
        cod = this.code[bp];
        bp += len;
        sb.append(":").append(cod).append(":").append(len);
      case 18:
      case 21:
        bs = new BitSet();
        System.arraycopy(this.code, bp, bs.bits, 0, 8);
        n = bs.numOn();
        bp += 8;
        len = this.code[bp];
        bp++;
        cod = this.code[bp];
        bp += len;
        sb.append(":").append(n).append(":").append(cod).append(":").append(len);
      case 22:
        cc = (CClassNode)this.operands[this.code[bp]];
        bp++;
        n = cc.bs.numOn();
        sb.append(":").append(cc).append(":").append(n);
      case 44:
        mem = this.code[bp];
        bp++;
        sb.append(":").append(mem);
      case 45:
      case 46:
        sb.append(" ");
        len = this.code[bp];
        bp++;
        for (i = 0; i < len; i++) {
          mem = this.code[bp];
          bp++;
          if (i > 0)
            sb.append(", "); 
          sb.append(mem);
        } 
      case 47:
        option = this.code[bp];
        bp++;
        sb.append(":").append(option);
        level = this.code[bp];
        bp++;
        sb.append(":").append(level);
        sb.append(" ");
        len = this.code[bp];
        bp++;
        for (j = 0; j < len; j++) {
          mem = this.code[bp];
          bp++;
          if (j > 0)
            sb.append(", "); 
          sb.append(mem);
        } 
      case 60:
      case 61:
        mem = this.code[bp];
        bp++;
        addr = this.code[bp];
        bp++;
        sb.append(":").append(mem).append(":").append(addr);
      case 58:
      case 59:
        addr = this.code[bp];
        bp++;
        sb.append(":(").append(addr).append(")");
        pString(sb, 1, bp);
        bp++;
      case 76:
        len = this.code[bp];
        bp++;
        sb.append(":").append(len);
      case 77:
        addr = this.code[bp];
        bp++;
        len = this.code[bp];
        bp++;
        sb.append(":").append(len).append(":(").append(addr).append(")");
      case 81:
      case 82:
        scn = this.code[bp];
        bp++;
        addr = this.code[bp];
        bp++;
        sb.append(":").append(scn).append(":(").append(addr).append(")");
    } 
    throw new InternalException("undefined code: " + this.code[--bp]);
  }
  
  private String compiledByteCodeListToString() {
    StringBuilder sb = new StringBuilder();
    sb.append("code length: ").append(this.codeLength).append("\n");
    int ncode = 0;
    int bp = 0;
    int end = this.codeLength;
    while (bp < end) {
      ncode++;
      if (bp > 0)
        sb.append((ncode % 5 == 0) ? "\n" : " "); 
      bp = compiledByteCodeToString(sb, bp);
    } 
    sb.append("\n");
    return sb.toString();
  }
}
