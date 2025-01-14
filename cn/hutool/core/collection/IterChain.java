package cn.hutool.core.collection;

import cn.hutool.core.lang.Chain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IterChain<T> implements Iterator<T>, Chain<Iterator<T>, IterChain<T>> {
  protected final List<Iterator<T>> allIterators = new ArrayList<>();
  
  protected int currentIter;
  
  public IterChain<T> addChain(Iterator<T> iterator) {
    if (this.allIterators.contains(iterator))
      throw new IllegalArgumentException("Duplicate iterator"); 
    this.allIterators.add(iterator);
    return this;
  }
  
  public IterChain() {
    this.currentIter = -1;
  }
  
  @SafeVarargs
  public IterChain(Iterator<T>... iterators) {
    this.currentIter = -1;
    for (Iterator<T> iterator : iterators)
      addChain(iterator); 
  }
  
  public boolean hasNext() {
    if (this.currentIter == -1)
      this.currentIter = 0; 
    int size = this.allIterators.size();
    for (int i = this.currentIter; i < size; i++) {
      Iterator<T> iterator = this.allIterators.get(i);
      if (iterator.hasNext()) {
        this.currentIter = i;
        return true;
      } 
    } 
    return false;
  }
  
  public T next() {
    if (false == hasNext())
      throw new NoSuchElementException(); 
    return ((Iterator<T>)this.allIterators.get(this.currentIter)).next();
  }
  
  public void remove() {
    if (-1 == this.currentIter)
      throw new IllegalStateException("next() has not yet been called"); 
    ((Iterator)this.allIterators.get(this.currentIter)).remove();
  }
  
  public Iterator<Iterator<T>> iterator() {
    return this.allIterators.iterator();
  }
}
