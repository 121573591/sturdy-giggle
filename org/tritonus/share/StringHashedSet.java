package org.tritonus.share;

import java.util.Collection;
import java.util.Iterator;

public class StringHashedSet<E> extends ArraySet<E> {
  private static final long serialVersionUID = 1L;
  
  public StringHashedSet() {}
  
  public StringHashedSet(Collection<E> c) {
    super(c);
  }
  
  public boolean add(E elem) {
    if (elem == null)
      return false; 
    return super.add(elem);
  }
  
  public boolean contains(Object elem) {
    if (elem == null)
      return false; 
    String comp = elem.toString();
    Iterator<E> it = iterator();
    while (it.hasNext()) {
      if (comp.equals(it.next().toString()))
        return true; 
    } 
    return false;
  }
  
  public E get(Object elem) {
    if (elem == null)
      return null; 
    String comp = elem.toString();
    Iterator<E> it = iterator();
    while (it.hasNext()) {
      E thisElem = it.next();
      if (comp.equals(thisElem.toString()))
        return thisElem; 
    } 
    return null;
  }
}
