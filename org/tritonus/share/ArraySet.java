package org.tritonus.share;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ArraySet<E> extends ArrayList<E> implements Set<E> {
  private static final long serialVersionUID = 1L;
  
  public ArraySet() {}
  
  public ArraySet(Collection<E> c) {
    this();
    addAll(c);
  }
  
  public boolean add(E element) {
    if (!contains(element)) {
      super.add(element);
      return true;
    } 
    return false;
  }
  
  public void add(int index, E element) {
    throw new UnsupportedOperationException("ArraySet.add(int index, Object element) unsupported");
  }
  
  public E set(int index, E element) {
    throw new UnsupportedOperationException("ArraySet.set(int index, Object element) unsupported");
  }
}
