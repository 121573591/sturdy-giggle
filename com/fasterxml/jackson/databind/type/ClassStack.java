package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.ArrayList;

public final class ClassStack {
  protected final ClassStack _parent;
  
  protected final Class<?> _current;
  
  private ArrayList<ResolvedRecursiveType> _selfRefs;
  
  public ClassStack(Class<?> rootType) {
    this(null, rootType);
  }
  
  private ClassStack(ClassStack parent, Class<?> curr) {
    this._parent = parent;
    this._current = curr;
  }
  
  public ClassStack child(Class<?> cls) {
    return new ClassStack(this, cls);
  }
  
  public void addSelfReference(ResolvedRecursiveType ref) {
    if (this._selfRefs == null)
      this._selfRefs = new ArrayList<>(); 
    this._selfRefs.add(ref);
  }
  
  public void resolveSelfReferences(JavaType resolved) {
    if (this._selfRefs != null)
      for (ResolvedRecursiveType ref : this._selfRefs)
        ref.setReference(resolved);  
  }
  
  public ClassStack find(Class<?> cls) {
    if (this._current == cls)
      return this; 
    for (ClassStack curr = this._parent; curr != null; curr = curr._parent) {
      if (curr._current == cls)
        return curr; 
    } 
    return null;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ClassStack (self-refs: ")
      .append((this._selfRefs == null) ? "0" : String.valueOf(this._selfRefs.size()))
      .append(')');
    for (ClassStack curr = this; curr != null; curr = curr._parent)
      sb.append(' ').append(curr._current.getName()); 
    sb.append(']');
    return sb.toString();
  }
}
