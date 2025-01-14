package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

public final class SplitReturn extends Statement {
  private static final long serialVersionUID = 1L;
  
  @Ignore
  public static final SplitReturn INSTANCE = new SplitReturn();
  
  private SplitReturn() {
    super(-1, 0L, 0);
  }
  
  public boolean isTerminal() {
    return true;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    return visitor.enterSplitReturn(this) ? visitor.leaveSplitReturn(this) : this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append(":splitreturn;");
  }
  
  private Object readResolve() {
    return INSTANCE;
  }
}
