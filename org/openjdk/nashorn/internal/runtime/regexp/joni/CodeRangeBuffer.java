package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public final class CodeRangeBuffer implements Cloneable {
  private static final int INIT_MULTI_BYTE_RANGE_SIZE = 5;
  
  private static final int ALL_MULTI_BYTE_RANGE = 2147483647;
  
  int[] p;
  
  int used;
  
  public CodeRangeBuffer() {
    this.p = new int[5];
    writeCodePoint(0, 0);
  }
  
  public boolean isInCodeRange(int code) {
    int low = 0;
    int n = this.p[0];
    int high = n;
    while (low < high) {
      int x = low + high >> 1;
      if (code > this.p[(x << 1) + 2]) {
        low = x + 1;
        continue;
      } 
      high = x;
    } 
    return (low < n && code >= this.p[(low << 1) + 1]);
  }
  
  private CodeRangeBuffer(CodeRangeBuffer orig) {
    this.p = new int[orig.p.length];
    System.arraycopy(orig.p, 0, this.p, 0, this.p.length);
    this.used = orig.used;
  }
  
  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append("CodeRange");
    buf.append("\n  used: ").append(this.used);
    buf.append("\n  code point: ").append(this.p[0]);
    buf.append("\n  ranges: ");
    for (int i = 0; i < this.p[0]; i++) {
      buf.append("[").append(rangeNumToString(this.p[i * 2 + 1])).append("..").append(rangeNumToString(this.p[i * 2 + 2])).append("]");
      if (i > 0 && i % 6 == 0)
        buf.append("\n          "); 
    } 
    return buf.toString();
  }
  
  private static String rangeNumToString(int num) {
    return "0x" + Integer.toString(num, 16);
  }
  
  public void expand(int low) {
    int length = this.p.length;
    while (true) {
      length <<= 1;
      if (length >= low) {
        int[] tmp = new int[length];
        System.arraycopy(this.p, 0, tmp, 0, this.used);
        this.p = tmp;
        return;
      } 
    } 
  }
  
  public void ensureSize(int size) {
    int length = this.p.length;
    for (; length < size; length <<= 1);
    if (this.p.length != length) {
      int[] tmp = new int[length];
      System.arraycopy(this.p, 0, tmp, 0, this.used);
      this.p = tmp;
    } 
  }
  
  private void moveRight(int from, int to, int n) {
    if (to + n > this.p.length)
      expand(to + n); 
    System.arraycopy(this.p, from, this.p, to, n);
    if (to + n > this.used)
      this.used = to + n; 
  }
  
  protected void moveLeft(int from, int to, int n) {
    System.arraycopy(this.p, from, this.p, to, n);
  }
  
  private void moveLeftAndReduce(int from, int to) {
    System.arraycopy(this.p, from, this.p, to, this.used - from);
    this.used -= from - to;
  }
  
  public void writeCodePoint(int pos, int b) {
    int u = pos + 1;
    if (this.p.length < u)
      expand(u); 
    this.p[pos] = b;
    if (this.used < u)
      this.used = u; 
  }
  
  public CodeRangeBuffer clone() {
    return new CodeRangeBuffer(this);
  }
  
  public static CodeRangeBuffer addCodeRangeToBuff(CodeRangeBuffer pbufp, int fromp, int top) {
    int from = fromp, to = top;
    CodeRangeBuffer pbuf = pbufp;
    if (from > to) {
      int i = from;
      from = to;
      to = i;
    } 
    if (pbuf == null)
      pbuf = new CodeRangeBuffer(); 
    int[] p = pbuf.p;
    int n = p[0];
    int low = 0;
    int bound = n;
    while (low < bound) {
      int x = low + bound >>> 1;
      if (from > p[x * 2 + 2]) {
        low = x + 1;
        continue;
      } 
      bound = x;
    } 
    int high = low;
    bound = n;
    while (high < bound) {
      int x = high + bound >>> 1;
      if (to >= p[x * 2 + 1] - 1) {
        high = x + 1;
        continue;
      } 
      bound = x;
    } 
    int incN = low + 1 - high;
    if (n + incN > 10000)
      throw new ValueException("too many multibyte code ranges are specified"); 
    if (incN != 1) {
      if (from > p[low * 2 + 1])
        from = p[low * 2 + 1]; 
      if (to < p[(high - 1) * 2 + 2])
        to = p[(high - 1) * 2 + 2]; 
    } 
    if (incN != 0 && high < n) {
      int fromPos = 1 + high * 2;
      int toPos = 1 + (low + 1) * 2;
      int size = (n - high) * 2;
      if (incN > 0) {
        pbuf.moveRight(fromPos, toPos, size);
      } else {
        pbuf.moveLeftAndReduce(fromPos, toPos);
      } 
    } 
    int pos = 1 + low * 2;
    pbuf.writeCodePoint(pos, from);
    pbuf.writeCodePoint(pos + 1, to);
    n += incN;
    pbuf.writeCodePoint(0, n);
    return pbuf;
  }
  
  public static CodeRangeBuffer addCodeRange(CodeRangeBuffer pbuf, ScanEnvironment env, int from, int to) {
    if (from > to) {
      if (env.syntax.allowEmptyRangeInCC())
        return pbuf; 
      throw new ValueException("empty range in char class");
    } 
    return addCodeRangeToBuff(pbuf, from, to);
  }
  
  protected static CodeRangeBuffer setAllMultiByteRange(CodeRangeBuffer pbuf) {
    return addCodeRangeToBuff(pbuf, EncodingHelper.mbcodeStartPosition(), 2147483647);
  }
  
  public static CodeRangeBuffer addAllMultiByteRange(CodeRangeBuffer pbuf) {
    return setAllMultiByteRange(pbuf);
  }
  
  public static CodeRangeBuffer notCodeRangeBuff(CodeRangeBuffer bbuf) {
    CodeRangeBuffer pbuf = null;
    if (bbuf == null)
      return setAllMultiByteRange(pbuf); 
    int[] p = bbuf.p;
    int n = p[0];
    if (n <= 0)
      return setAllMultiByteRange(pbuf); 
    int pre = EncodingHelper.mbcodeStartPosition();
    int to = 0;
    for (int i = 0; i < n; i++) {
      int from = p[i * 2 + 1];
      to = p[i * 2 + 2];
      if (pre <= from - 1)
        pbuf = addCodeRangeToBuff(pbuf, pre, from - 1); 
      if (to == Integer.MAX_VALUE)
        break; 
      pre = to + 1;
    } 
    if (to < Integer.MAX_VALUE)
      pbuf = addCodeRangeToBuff(pbuf, to + 1, 2147483647); 
    return pbuf;
  }
  
  public static CodeRangeBuffer orCodeRangeBuff(CodeRangeBuffer bbuf1p, boolean not1p, CodeRangeBuffer bbuf2p, boolean not2p) {
    CodeRangeBuffer pbuf = null;
    CodeRangeBuffer bbuf1 = bbuf1p;
    CodeRangeBuffer bbuf2 = bbuf2p;
    boolean not1 = not1p;
    boolean not2 = not2p;
    if (bbuf1 == null && bbuf2 == null) {
      if (not1 || not2)
        return setAllMultiByteRange(pbuf); 
      return null;
    } 
    if (bbuf2 == null) {
      boolean tnot = not1;
      not1 = not2;
      not2 = tnot;
      CodeRangeBuffer tbuf = bbuf1;
      bbuf1 = bbuf2;
      bbuf2 = tbuf;
    } 
    if (bbuf1 == null) {
      if (not1)
        return setAllMultiByteRange(pbuf); 
      if (!not2)
        return bbuf2.clone(); 
      return notCodeRangeBuff(bbuf2);
    } 
    if (not1) {
      boolean tnot = not1;
      not1 = not2;
      not2 = tnot;
      CodeRangeBuffer tbuf = bbuf1;
      bbuf1 = bbuf2;
      bbuf2 = tbuf;
    } 
    if (!not2 && !not1) {
      pbuf = bbuf2.clone();
    } else if (!not1) {
      pbuf = notCodeRangeBuff(bbuf2);
    } 
    int[] p1 = bbuf1.p;
    int n1 = p1[0];
    for (int i = 0; i < n1; i++) {
      int from = p1[i * 2 + 1];
      int to = p1[i * 2 + 2];
      pbuf = addCodeRangeToBuff(pbuf, from, to);
    } 
    return pbuf;
  }
  
  public static CodeRangeBuffer andCodeRange1(CodeRangeBuffer pbufp, int from1p, int to1p, int[] data, int n) {
    CodeRangeBuffer pbuf = pbufp;
    int from1 = from1p, to1 = to1p;
    for (int i = 0; i < n; i++) {
      int from2 = data[i * 2 + 1];
      int to2 = data[i * 2 + 2];
      if (from2 < from1) {
        if (to2 < from1)
          continue; 
        from1 = to2 + 1;
      } else if (from2 <= to1) {
        if (to2 < to1) {
          if (from1 <= from2 - 1)
            pbuf = addCodeRangeToBuff(pbuf, from1, from2 - 1); 
          from1 = to2 + 1;
        } else {
          to1 = from2 - 1;
        } 
      } else {
        from1 = from2;
      } 
      if (from1 > to1)
        break; 
      continue;
    } 
    if (from1 <= to1)
      pbuf = addCodeRangeToBuff(pbuf, from1, to1); 
    return pbuf;
  }
  
  public static CodeRangeBuffer andCodeRangeBuff(CodeRangeBuffer bbuf1p, boolean not1p, CodeRangeBuffer bbuf2p, boolean not2p) {
    CodeRangeBuffer pbuf = null;
    CodeRangeBuffer bbuf1 = bbuf1p;
    CodeRangeBuffer bbuf2 = bbuf2p;
    boolean not1 = not1p, not2 = not2p;
    if (bbuf1 == null) {
      if (not1 && bbuf2 != null)
        return bbuf2.clone(); 
      return null;
    } 
    if (bbuf2 == null) {
      if (not2)
        return bbuf1.clone(); 
      return null;
    } 
    if (not1) {
      boolean tnot = not1;
      not1 = not2;
      not2 = tnot;
      CodeRangeBuffer tbuf = bbuf1;
      bbuf1 = bbuf2;
      bbuf2 = tbuf;
    } 
    int[] p1 = bbuf1.p;
    int n1 = p1[0];
    int[] p2 = bbuf2.p;
    int n2 = p2[0];
    if (!not2 && !not1) {
      for (int i = 0; i < n1; i++) {
        int from1 = p1[i * 2 + 1];
        int to1 = p1[i * 2 + 2];
        for (int j = 0; j < n2; j++) {
          int from2 = p2[j * 2 + 1];
          int to2 = p2[j * 2 + 2];
          if (from2 > to1)
            break; 
          if (to2 >= from1) {
            int from = (from1 > from2) ? from1 : from2;
            int to = (to1 < to2) ? to1 : to2;
            pbuf = addCodeRangeToBuff(pbuf, from, to);
          } 
        } 
      } 
    } else if (!not1) {
      for (int i = 0; i < n1; i++) {
        int from1 = p1[i * 2 + 1];
        int to1 = p1[i * 2 + 2];
        pbuf = andCodeRange1(pbuf, from1, to1, p2, n2);
      } 
    } 
    return pbuf;
  }
}
