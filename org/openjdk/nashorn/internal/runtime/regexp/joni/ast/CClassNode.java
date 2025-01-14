package org.openjdk.nashorn.internal.runtime.regexp.joni.ast;

import org.openjdk.nashorn.internal.runtime.regexp.joni.BitSet;
import org.openjdk.nashorn.internal.runtime.regexp.joni.CodeRangeBuffer;
import org.openjdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;
import org.openjdk.nashorn.internal.runtime.regexp.joni.Syntax;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.CCVALTYPE;
import org.openjdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public final class CClassNode extends Node {
  private static final int FLAG_NCCLASS_NOT = 1;
  
  private static final int FLAG_NCCLASS_SHARE = 2;
  
  int flags;
  
  public final BitSet bs = new BitSet();
  
  public CodeRangeBuffer mbuf;
  
  private int ctype;
  
  private static final short[] AsciiCtypeTable = new short[] { 
      16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16908, 
      16905, 16904, 16904, 16904, 16392, 16392, 16392, 16392, 16392, 16392, 
      16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 
      16392, 16392, 17028, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 
      16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 30896, 30896, 
      30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 16800, 16800, 
      16800, 16800, 16800, 16800, 16800, 31906, 31906, 31906, 31906, 31906, 
      31906, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 
      29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 
      29858, 16800, 16800, 16800, 16800, 20896, 16800, 30946, 30946, 30946, 
      30946, 30946, 30946, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 
      28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 
      28898, 28898, 28898, 16800, 16800, 16800, 16800, 16392, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0 };
  
  public void clear() {
    this.bs.clear();
    this.flags = 0;
    this.mbuf = null;
  }
  
  public int getType() {
    return 1;
  }
  
  public String getName() {
    return "Character Class";
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof CClassNode))
      return false; 
    CClassNode cc = (CClassNode)other;
    return (this.ctype == cc.ctype && isNot() == cc.isNot());
  }
  
  public int hashCode() {
    return super.hashCode();
  }
  
  public String toString(int level) {
    StringBuilder value = new StringBuilder();
    value.append("\n  flags: ").append(flagsToString());
    value.append("\n  bs: ").append(pad(this.bs, level + 1));
    value.append("\n  mbuf: ").append(pad(this.mbuf, level + 1));
    return value.toString();
  }
  
  public String flagsToString() {
    StringBuilder f = new StringBuilder();
    if (isNot())
      f.append("NOT "); 
    if (isShare())
      f.append("SHARE "); 
    return f.toString();
  }
  
  public boolean isEmpty() {
    return (this.mbuf == null && this.bs.isEmpty());
  }
  
  public void addCodeRangeToBuf(int from, int to) {
    this.mbuf = CodeRangeBuffer.addCodeRangeToBuff(this.mbuf, from, to);
  }
  
  public void addCodeRange(ScanEnvironment env, int from, int to) {
    this.mbuf = CodeRangeBuffer.addCodeRange(this.mbuf, env, from, to);
  }
  
  public void addAllMultiByteRange() {
    this.mbuf = CodeRangeBuffer.addAllMultiByteRange(this.mbuf);
  }
  
  public void clearNotFlag() {
    if (isNot()) {
      this.bs.invert();
      this.mbuf = CodeRangeBuffer.notCodeRangeBuff(this.mbuf);
      clearNot();
    } 
  }
  
  public void and(CClassNode other) {
    boolean not1 = isNot();
    BitSet bsr1 = this.bs;
    CodeRangeBuffer buf1 = this.mbuf;
    boolean not2 = other.isNot();
    BitSet bsr2 = other.bs;
    CodeRangeBuffer buf2 = other.mbuf;
    if (not1) {
      BitSet bs1 = new BitSet();
      bsr1.invertTo(bs1);
      bsr1 = bs1;
    } 
    if (not2) {
      BitSet bs2 = new BitSet();
      bsr2.invertTo(bs2);
      bsr2 = bs2;
    } 
    bsr1.and(bsr2);
    if (bsr1 != this.bs) {
      this.bs.copy(bsr1);
      bsr1 = this.bs;
    } 
    if (not1)
      this.bs.invert(); 
    CodeRangeBuffer pbuf = null;
    if (not1 && not2) {
      pbuf = CodeRangeBuffer.orCodeRangeBuff(buf1, false, buf2, false);
    } else {
      pbuf = CodeRangeBuffer.andCodeRangeBuff(buf1, not1, buf2, not2);
      if (not1)
        pbuf = CodeRangeBuffer.notCodeRangeBuff(pbuf); 
    } 
    this.mbuf = pbuf;
  }
  
  public void or(CClassNode other) {
    boolean not1 = isNot();
    BitSet bsr1 = this.bs;
    CodeRangeBuffer buf1 = this.mbuf;
    boolean not2 = other.isNot();
    BitSet bsr2 = other.bs;
    CodeRangeBuffer buf2 = other.mbuf;
    if (not1) {
      BitSet bs1 = new BitSet();
      bsr1.invertTo(bs1);
      bsr1 = bs1;
    } 
    if (not2) {
      BitSet bs2 = new BitSet();
      bsr2.invertTo(bs2);
      bsr2 = bs2;
    } 
    bsr1.or(bsr2);
    if (bsr1 != this.bs) {
      this.bs.copy(bsr1);
      bsr1 = this.bs;
    } 
    if (not1)
      this.bs.invert(); 
    CodeRangeBuffer pbuf = null;
    if (not1 && not2) {
      pbuf = CodeRangeBuffer.andCodeRangeBuff(buf1, false, buf2, false);
    } else {
      pbuf = CodeRangeBuffer.orCodeRangeBuff(buf1, not1, buf2, not2);
      if (not1)
        pbuf = CodeRangeBuffer.notCodeRangeBuff(pbuf); 
    } 
    this.mbuf = pbuf;
  }
  
  public void addCTypeByRange(int ct, boolean not, int sbOut, int[] mbr) {
    int n = mbr[0];
    if (!not) {
      int i;
      for (i = 0; i < n; i++) {
        for (int j = mbr[i * 2 + 1]; j <= mbr[i * 2 + 2]; j++) {
          if (j >= sbOut) {
            if (j >= mbr[i * 2 + 1]) {
              addCodeRangeToBuf(j, mbr[i * 2 + 2]);
              i++;
            } 
            for (; i < n; i++)
              addCodeRangeToBuf(mbr[2 * i + 1], mbr[2 * i + 2]); 
            return;
          } 
          this.bs.set(j);
        } 
      } 
      for (i = 0; i < n; i++)
        addCodeRangeToBuf(mbr[2 * i + 1], mbr[2 * i + 2]); 
    } else {
      int prev = 0;
      for (int k = 0; k < n; k++) {
        for (int m = prev; m < mbr[2 * k + 1]; m++) {
          if (m >= sbOut) {
            prev = sbOut;
            for (k = 0; k < n; k++) {
              if (prev < mbr[2 * k + 1])
                addCodeRangeToBuf(prev, mbr[k * 2 + 1] - 1); 
              prev = mbr[k * 2 + 2] + 1;
            } 
            if (prev < Integer.MAX_VALUE)
              addCodeRangeToBuf(prev, 2147483647); 
            return;
          } 
          this.bs.set(m);
        } 
        prev = mbr[2 * k + 2] + 1;
      } 
      for (int j = prev; j < sbOut; j++)
        this.bs.set(j); 
      prev = sbOut;
      for (int i = 0; i < n; i++) {
        if (prev < mbr[2 * i + 1])
          addCodeRangeToBuf(prev, mbr[i * 2 + 1] - 1); 
        prev = mbr[i * 2 + 2] + 1;
      } 
      if (prev < Integer.MAX_VALUE)
        addCodeRangeToBuf(prev, 2147483647); 
    } 
  }
  
  public void addCType(int ctp, boolean not, ScanEnvironment env, IntHolder sbOut) {
    int ct = ctp;
    switch (ct) {
      case 260:
      case 265:
      case 268:
        ct ^= 0x100;
        if (env.syntax == Syntax.JAVASCRIPT && ct == 9)
          break; 
        if (not) {
          for (int c = 0; c < 256; c++) {
            if ((AsciiCtypeTable[c] & 1 << ct) == 0)
              this.bs.set(c); 
          } 
          addAllMultiByteRange();
        } else {
          for (int c = 0; c < 256; c++) {
            if ((AsciiCtypeTable[c] & 1 << ct) != 0)
              this.bs.set(c); 
          } 
        } 
        return;
    } 
    int[] ranges = EncodingHelper.ctypeCodeRange(ct, sbOut);
    if (ranges != null) {
      addCTypeByRange(ct, not, sbOut.value, ranges);
      return;
    } 
    switch (ct) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 6:
      case 8:
      case 9:
      case 10:
      case 11:
      case 13:
      case 14:
        if (not) {
          for (int c = 0; c < 256; c++) {
            if (!EncodingHelper.isCodeCType(c, ct))
              this.bs.set(c); 
          } 
          addAllMultiByteRange();
        } else {
          for (int c = 0; c < 256; c++) {
            if (EncodingHelper.isCodeCType(c, ct))
              this.bs.set(c); 
          } 
        } 
        return;
      case 5:
      case 7:
        if (not) {
          for (int c = 0; c < 256; c++) {
            if (!EncodingHelper.isCodeCType(c, ct))
              this.bs.set(c); 
          } 
        } else {
          for (int c = 0; c < 256; c++) {
            if (EncodingHelper.isCodeCType(c, ct))
              this.bs.set(c); 
          } 
          addAllMultiByteRange();
        } 
        return;
      case 12:
        if (!not) {
          for (int c = 0; c < 256; c++) {
            if (EncodingHelper.isWord(c))
              this.bs.set(c); 
          } 
          addAllMultiByteRange();
        } else {
          for (int c = 0; c < 256; c++) {
            if (!EncodingHelper.isWord(c))
              this.bs.set(c); 
          } 
        } 
        return;
    } 
    throw new InternalException("internal parser error (bug)");
  }
  
  public static final class CCStateArg {
    public int v;
    
    public int vs;
    
    public boolean vsIsRaw;
    
    public boolean vIsRaw;
    
    public CCVALTYPE inType;
    
    public CCVALTYPE type;
    
    public CCSTATE state;
  }
  
  public void nextStateClass(CCStateArg arg, ScanEnvironment env) {
    if (arg.state == CCSTATE.RANGE)
      throw new SyntaxException("char-class value at end of range"); 
    if (arg.state == CCSTATE.VALUE && arg.type != CCVALTYPE.CLASS)
      if (arg.type == CCVALTYPE.SB) {
        this.bs.set(arg.vs);
      } else if (arg.type == CCVALTYPE.CODE_POINT) {
        addCodeRange(env, arg.vs, arg.vs);
      }  
    arg.state = CCSTATE.VALUE;
    arg.type = CCVALTYPE.CLASS;
  }
  
  public void nextStateValue(CCStateArg arg, ScanEnvironment env) {
    switch (arg.state) {
      case VALUE:
        if (arg.type == CCVALTYPE.SB) {
          if (arg.vs > 255)
            throw new ValueException("invalid code point value"); 
          this.bs.set(arg.vs);
          break;
        } 
        if (arg.type == CCVALTYPE.CODE_POINT)
          addCodeRange(env, arg.vs, arg.vs); 
        break;
      case RANGE:
        if (arg.inType == arg.type) {
          if (arg.inType == CCVALTYPE.SB) {
            if (arg.vs > 255 || arg.v > 255)
              throw new ValueException("invalid code point value"); 
            if (arg.vs > arg.v) {
              if (env.syntax.allowEmptyRangeInCC()) {
                arg.state = CCSTATE.COMPLETE;
                break;
              } 
              throw new ValueException("empty range in char class");
            } 
            this.bs.setRange(arg.vs, arg.v);
          } else {
            addCodeRange(env, arg.vs, arg.v);
          } 
        } else {
          if (arg.vs > arg.v) {
            if (env.syntax.allowEmptyRangeInCC()) {
              arg.state = CCSTATE.COMPLETE;
              break;
            } 
            throw new ValueException("empty range in char class");
          } 
          this.bs.setRange(arg.vs, (arg.v < 255) ? arg.v : 255);
          addCodeRange(env, arg.vs, arg.v);
        } 
        arg.state = CCSTATE.COMPLETE;
        break;
      case COMPLETE:
      case START:
        arg.state = CCSTATE.VALUE;
        break;
    } 
    arg.vsIsRaw = arg.vIsRaw;
    arg.vs = arg.v;
    arg.type = arg.inType;
  }
  
  public boolean isCodeInCCLength(int code) {
    boolean found;
    if (code > 255) {
      found = (this.mbuf != null && this.mbuf.isInCodeRange(code));
    } else {
      found = this.bs.at(code);
    } 
    if (isNot())
      return !found; 
    return found;
  }
  
  public boolean isCodeInCC(int code) {
    return isCodeInCCLength(code);
  }
  
  public void setNot() {
    this.flags |= 0x1;
  }
  
  public void clearNot() {
    this.flags &= 0xFFFFFFFE;
  }
  
  public boolean isNot() {
    return ((this.flags & 0x1) != 0);
  }
  
  public void setShare() {
    this.flags |= 0x2;
  }
  
  public void clearShare() {
    this.flags &= 0xFFFFFFFD;
  }
  
  public boolean isShare() {
    return ((this.flags & 0x2) != 0);
  }
}
