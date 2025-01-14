package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.constants.AnchorType;

final class OptAnchorInfo implements AnchorType {
  int leftAnchor;
  
  int rightAnchor;
  
  void clear() {
    this.leftAnchor = this.rightAnchor = 0;
  }
  
  void copy(OptAnchorInfo other) {
    this.leftAnchor = other.leftAnchor;
    this.rightAnchor = other.rightAnchor;
  }
  
  void concat(OptAnchorInfo left, OptAnchorInfo right, int leftLength, int rightLength) {
    this.leftAnchor = left.leftAnchor;
    if (leftLength == 0)
      this.leftAnchor |= right.leftAnchor; 
    this.rightAnchor = right.rightAnchor;
    if (rightLength == 0)
      this.rightAnchor |= left.rightAnchor; 
  }
  
  boolean isSet(int anchor) {
    if ((this.leftAnchor & anchor) != 0)
      return true; 
    return ((this.rightAnchor & anchor) != 0);
  }
  
  void add(int anchor) {
    if (isLeftAnchor(anchor)) {
      this.leftAnchor |= anchor;
    } else {
      this.rightAnchor |= anchor;
    } 
  }
  
  void remove(int anchor) {
    if (isLeftAnchor(anchor)) {
      this.leftAnchor &= anchor ^ 0xFFFFFFFF;
    } else {
      this.rightAnchor &= anchor ^ 0xFFFFFFFF;
    } 
  }
  
  void altMerge(OptAnchorInfo other) {
    this.leftAnchor &= other.leftAnchor;
    this.rightAnchor &= other.rightAnchor;
  }
  
  static boolean isLeftAnchor(int anchor) {
    return (anchor != 8 && anchor != 16 && anchor != 32 && anchor != 1024 && anchor != 2048);
  }
  
  static String anchorToString(int anchor) {
    StringBuffer s = new StringBuffer("[");
    if ((anchor & 0x1) != 0)
      s.append("begin-buf "); 
    if ((anchor & 0x2) != 0)
      s.append("begin-line "); 
    if ((anchor & 0x4) != 0)
      s.append("begin-pos "); 
    if ((anchor & 0x8) != 0)
      s.append("end-buf "); 
    if ((anchor & 0x10) != 0)
      s.append("semi-end-buf "); 
    if ((anchor & 0x20) != 0)
      s.append("end-line "); 
    if ((anchor & 0x4000) != 0)
      s.append("anychar-star "); 
    if ((anchor & 0x8000) != 0)
      s.append("anychar-star-pl "); 
    s.append("]");
    return s.toString();
  }
}
