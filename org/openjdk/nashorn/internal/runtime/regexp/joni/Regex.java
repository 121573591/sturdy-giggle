package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.RegexState;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public final class Regex implements RegexState {
  int[] code;
  
  int codeLength;
  
  boolean stackNeeded;
  
  Object[] operands;
  
  int operandLength;
  
  int numMem;
  
  int numRepeat;
  
  int numNullCheck;
  
  int captureHistory;
  
  int btMemStart;
  
  int btMemEnd;
  
  int stackPopLevel;
  
  int[] repeatRangeLo;
  
  int[] repeatRangeHi;
  
  WarnCallback warnings;
  
  MatcherFactory factory;
  
  protected Analyser analyser;
  
  int options;
  
  final int caseFoldFlag;
  
  SearchAlgorithm searchAlgorithm;
  
  int thresholdLength;
  
  int anchor;
  
  int anchorDmin;
  
  int anchorDmax;
  
  int subAnchor;
  
  char[] exact;
  
  int exactP;
  
  int exactEnd;
  
  byte[] map;
  
  int[] intMap;
  
  int[] intMapBackward;
  
  int dMin;
  
  int dMax;
  
  char[][] templates;
  
  int templateNum;
  
  public Regex(CharSequence cs) {
    this(cs.toString());
  }
  
  public Regex(String str) {
    this(str.toCharArray(), 0, str.length(), 0);
  }
  
  public Regex(char[] chars) {
    this(chars, 0, chars.length, 0);
  }
  
  public Regex(char[] chars, int p, int end) {
    this(chars, p, end, 0);
  }
  
  public Regex(char[] chars, int p, int end, int option) {
    this(chars, p, end, option, Syntax.RUBY, WarnCallback.DEFAULT);
  }
  
  public Regex(char[] chars, int p, int end, int option, Syntax syntax) {
    this(chars, p, end, option, 1073741824, syntax, WarnCallback.DEFAULT);
  }
  
  public Regex(char[] chars, int p, int end, int option, WarnCallback warnings) {
    this(chars, p, end, option, Syntax.RUBY, warnings);
  }
  
  public Regex(char[] chars, int p, int end, int option, Syntax syntax, WarnCallback warnings) {
    this(chars, p, end, option, 1073741824, syntax, warnings);
  }
  
  public Regex(char[] chars, int p, int end, int optionp, int caseFoldFlag, Syntax syntax, WarnCallback warnings) {
    int option = optionp;
    if ((option & 0x180) == 384)
      throw new ValueException("invalid combination of options"); 
    if ((option & 0x40) != 0) {
      option |= syntax.options;
      option &= 0xFFFFFFF7;
    } else {
      option |= syntax.options;
    } 
    this.options = option;
    this.caseFoldFlag = caseFoldFlag;
    this.warnings = warnings;
    (new Analyser(new ScanEnvironment(this, syntax), chars, p, end)).compile();
    this.warnings = null;
  }
  
  public Matcher matcher(char[] chars) {
    return matcher(chars, 0, chars.length);
  }
  
  public Matcher matcher(char[] chars, int p, int end) {
    return this.factory.create(this, chars, p, end);
  }
  
  public WarnCallback getWarnings() {
    return this.warnings;
  }
  
  public int numberOfCaptures() {
    return this.numMem;
  }
  
  void setupBMSkipMap() {
    char[] chars = this.exact;
    int p = this.exactP;
    int end = this.exactEnd;
    int len = end - p;
    if (len < 256) {
      if (this.map == null)
        this.map = new byte[256]; 
      int i;
      for (i = 0; i < 256; i++)
        this.map[i] = (byte)len; 
      for (i = 0; i < len - 1; i++)
        this.map[chars[p + i] & 0xFF] = (byte)(len - 1 - i); 
    } else {
      if (this.intMap == null)
        this.intMap = new int[256]; 
      for (int i = 0; i < len - 1; i++)
        this.intMap[chars[p + i] & 0xFF] = len - 1 - i; 
    } 
  }
  
  void setExactInfo(OptExactInfo e) {
    if (e.length == 0)
      return; 
    this.exact = e.chars;
    this.exactP = 0;
    this.exactEnd = e.length;
    if (e.ignoreCase) {
      this.searchAlgorithm = new SearchAlgorithm.SLOW_IC(this);
    } else if (e.length >= 2) {
      setupBMSkipMap();
      this.searchAlgorithm = SearchAlgorithm.BM;
    } else {
      this.searchAlgorithm = SearchAlgorithm.SLOW;
    } 
    this.dMin = e.mmd.min;
    this.dMax = e.mmd.max;
    if (this.dMin != Integer.MAX_VALUE)
      this.thresholdLength = this.dMin + this.exactEnd - this.exactP; 
  }
  
  void setOptimizeMapInfo(OptMapInfo m) {
    this.map = m.map;
    this.searchAlgorithm = SearchAlgorithm.MAP;
    this.dMin = m.mmd.min;
    this.dMax = m.mmd.max;
    if (this.dMin != Integer.MAX_VALUE)
      this.thresholdLength = this.dMin + 1; 
  }
  
  void setSubAnchor(OptAnchorInfo anc) {
    this.subAnchor |= anc.leftAnchor & 0x2;
    this.subAnchor |= anc.rightAnchor & 0x20;
  }
  
  void clearOptimizeInfo() {
    this.searchAlgorithm = SearchAlgorithm.NONE;
    this.anchor = 0;
    this.anchorDmax = 0;
    this.anchorDmin = 0;
    this.subAnchor = 0;
    this.exact = null;
    this.exactP = this.exactEnd = 0;
  }
  
  public String optimizeInfoToString() {
    StringBuilder s = new StringBuilder();
    s.append("optimize: ").append(this.searchAlgorithm.getName()).append("\n");
    s.append("  anchor:     ").append(OptAnchorInfo.anchorToString(this.anchor));
    if ((this.anchor & 0x18) != 0)
      s.append(MinMaxLen.distanceRangeToString(this.anchorDmin, this.anchorDmax)); 
    s.append("\n");
    if (this.searchAlgorithm != SearchAlgorithm.NONE)
      s.append("  sub anchor: ").append(OptAnchorInfo.anchorToString(this.subAnchor)).append("\n"); 
    s.append("dmin: ").append(this.dMin).append(" dmax: ").append(this.dMax).append("\n");
    s.append("threshold length: ").append(this.thresholdLength).append("\n");
    if (this.exact != null) {
      s.append("exact: [").append(this.exact, this.exactP, this.exactEnd - this.exactP).append("]: length: ").append(this.exactEnd - this.exactP).append("\n");
    } else if (this.searchAlgorithm == SearchAlgorithm.MAP) {
      int n = 0;
      for (int i = 0; i < 256; i++) {
        if (this.map[i] != 0)
          n++; 
      } 
      s.append("map: n = ").append(n).append("\n");
      if (n > 0) {
        int c = 0;
        s.append("[");
        for (int j = 0; j < 256; j++) {
          if (this.map[j] != 0) {
            if (c > 0)
              s.append(", "); 
            c++;
            s.append((char)j);
          } 
        } 
        s.append("]\n");
      } 
    } 
    return s.toString();
  }
  
  public int getOptions() {
    return this.options;
  }
  
  public String dumpTree() {
    return (this.analyser == null) ? null : this.analyser.root.toString();
  }
  
  public String dumpByteCode() {
    return (new ByteCodePrinter(this)).byteCodeListToString();
  }
}
