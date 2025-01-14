package org.openjdk.nashorn.internal.runtime.regexp.joni.ast;

import org.openjdk.nashorn.internal.runtime.regexp.joni.Option;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.EncloseType;

public final class EncloseNode extends StateNode implements EncloseType {
  public final int type;
  
  public int regNum;
  
  public int option;
  
  public Node target;
  
  public int callAddr;
  
  public int minLength;
  
  public int maxLength;
  
  public int charLength;
  
  public int optCount;
  
  public EncloseNode(int type) {
    this.type = type;
    this.callAddr = -1;
  }
  
  public EncloseNode() {
    this(1);
  }
  
  public EncloseNode(int option, int i) {
    this(2);
    this.option = option;
  }
  
  public int getType() {
    return 6;
  }
  
  protected void setChild(Node newChild) {
    this.target = newChild;
  }
  
  protected Node getChild() {
    return this.target;
  }
  
  public void setTarget(Node tgt) {
    this.target = tgt;
    tgt.parent = this;
  }
  
  public String getName() {
    return "Enclose";
  }
  
  public String toString(int level) {
    StringBuilder value = new StringBuilder(super.toString(level));
    value.append("\n  type: ").append(typeToString());
    value.append("\n  regNum: ").append(this.regNum);
    value.append("\n  option: ").append(Option.toString(this.option));
    value.append("\n  target: ").append(pad(this.target, level + 1));
    value.append("\n  callAddr: ").append(this.callAddr);
    value.append("\n  minLength: ").append(this.minLength);
    value.append("\n  maxLength: ").append(this.maxLength);
    value.append("\n  charLength: ").append(this.charLength);
    value.append("\n  optCount: ").append(this.optCount);
    return value.toString();
  }
  
  public String typeToString() {
    StringBuilder types = new StringBuilder();
    if (isStopBacktrack())
      types.append("STOP_BACKTRACK "); 
    if (isMemory())
      types.append("MEMORY "); 
    if (isOption())
      types.append("OPTION "); 
    return types.toString();
  }
  
  public boolean isMemory() {
    return ((this.type & 0x1) != 0);
  }
  
  public boolean isOption() {
    return ((this.type & 0x2) != 0);
  }
  
  public boolean isStopBacktrack() {
    return ((this.type & 0x4) != 0);
  }
}
