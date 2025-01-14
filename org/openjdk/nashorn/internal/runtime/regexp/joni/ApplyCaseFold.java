package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;

final class ApplyCaseFold {
  public static void apply(int from, int to, Object o) {
    ApplyCaseFoldArg arg = (ApplyCaseFoldArg)o;
    ScanEnvironment env = arg.env;
    CClassNode cc = arg.cc;
    BitSet bs = cc.bs;
    boolean inCC = cc.isCodeInCC(from);
    if ((inCC && !cc.isNot()) || (!inCC && cc.isNot()))
      if (to >= 256) {
        cc.addCodeRange(env, to, to);
      } else {
        bs.set(to);
      }  
  }
  
  static final ApplyCaseFold INSTANCE = new ApplyCaseFold();
}
