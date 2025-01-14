package cn.hutool.core.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class BoundedPriorityQueue<E> extends PriorityQueue<E> {
  private static final long serialVersionUID = 3794348988671694820L;
  
  private final int capacity;
  
  private final Comparator<? super E> comparator;
  
  public BoundedPriorityQueue(int capacity) {
    this(capacity, (Comparator<? super E>)null);
  }
  
  public BoundedPriorityQueue(int capacity, Comparator<? super E> comparator) {
    super(capacity, (o1, o2) -> {
          int cResult;
          if (comparator != null) {
            cResult = comparator.compare(o1, o2);
          } else {
            Comparable<E> o1c = (Comparable<E>)o1;
            cResult = o1c.compareTo((E)o2);
          } 
          return -cResult;
        });
    this.capacity = capacity;
    this.comparator = comparator;
  }
  
  public boolean offer(E e) {
    if (size() >= this.capacity) {
      E head = peek();
      if (comparator().compare(e, head) <= 0)
        return true; 
      poll();
    } 
    return super.offer(e);
  }
  
  public boolean addAll(E[] c) {
    return addAll(Arrays.asList(c));
  }
  
  public ArrayList<E> toList() {
    ArrayList<E> list = new ArrayList<>(this);
    list.sort(this.comparator);
    return list;
  }
  
  public Iterator<E> iterator() {
    return toList().iterator();
  }
}
