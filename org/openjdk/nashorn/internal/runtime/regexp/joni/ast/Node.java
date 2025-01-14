package org.openjdk.nashorn.internal.runtime.regexp.joni.ast;

import java.util.Set;
import org.openjdk.nashorn.internal.runtime.regexp.joni.WarnCallback;
import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.NodeType;

public abstract class Node implements NodeType {
  public Node parent;
  
  public abstract int getType();
  
  public final int getType2Bit() {
    return 1 << getType();
  }
  
  protected void setChild(Node tgt) {}
  
  protected Node getChild() {
    return null;
  }
  
  public void swap(Node with) {
    if (this.parent != null)
      this.parent.setChild(with); 
    if (with.parent != null)
      with.parent.setChild(this); 
    Node tmp = this.parent;
    this.parent = with.parent;
    with.parent = tmp;
  }
  
  public void verifyTree(Set<Node> set, WarnCallback warnings) {
    if (!set.contains(this) && getChild() != null) {
      set.add(this);
      if ((getChild()).parent != this)
        warnings.warn("broken link to child: " + getAddressName() + " -> " + getChild().getAddressName()); 
      getChild().verifyTree(set, warnings);
    } 
  }
  
  public abstract String getName();
  
  protected abstract String toString(int paramInt);
  
  public String getAddressName() {
    return getName() + ":0x" + getName();
  }
  
  public final String toString() {
    StringBuilder s = new StringBuilder();
    s.append("<").append(getAddressName()).append(" (").append((this.parent == null) ? "NULL" : this.parent.getAddressName()).append(")>");
    return "" + s + s;
  }
  
  protected static String pad(Object value, int level) {
    if (value == null)
      return "NULL"; 
    StringBuilder pad = new StringBuilder("  ");
    for (int i = 0; i < level; i++)
      pad.append(pad); 
    return value.toString().replace("\n", "\n" + pad);
  }
  
  public final boolean isInvalidQuantifier() {
    return false;
  }
  
  public final boolean isAllowedInLookBehind() {
    return ((getType2Bit() & 0x7EF) != 0);
  }
  
  public final boolean isSimple() {
    return ((getType2Bit() & 0x1F) != 0);
  }
}
