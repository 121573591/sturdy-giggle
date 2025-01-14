package io.netty.handler.codec.http2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

final class MaxCapacityQueue<E> implements Queue<E> {
  private final Queue<E> queue;
  
  private final int maxCapacity;
  
  MaxCapacityQueue(Queue<E> queue, int maxCapacity) {
    this.queue = queue;
    this.maxCapacity = maxCapacity;
  }
  
  public boolean add(E element) {
    if (offer(element))
      return true; 
    throw new IllegalStateException();
  }
  
  public boolean offer(E element) {
    if (this.maxCapacity <= this.queue.size())
      return false; 
    return this.queue.offer(element);
  }
  
  public E remove() {
    return this.queue.remove();
  }
  
  public E poll() {
    return this.queue.poll();
  }
  
  public E element() {
    return this.queue.element();
  }
  
  public E peek() {
    return this.queue.peek();
  }
  
  public int size() {
    return this.queue.size();
  }
  
  public boolean isEmpty() {
    return this.queue.isEmpty();
  }
  
  public boolean contains(Object o) {
    return this.queue.contains(o);
  }
  
  public Iterator<E> iterator() {
    return this.queue.iterator();
  }
  
  public Object[] toArray() {
    return this.queue.toArray();
  }
  
  public <T> T[] toArray(T[] a) {
    return (T[])this.queue.toArray((Object[])a);
  }
  
  public boolean remove(Object o) {
    return this.queue.remove(o);
  }
  
  public boolean containsAll(Collection<?> c) {
    return this.queue.containsAll(c);
  }
  
  public boolean addAll(Collection<? extends E> c) {
    if (this.maxCapacity >= size() + c.size())
      return this.queue.addAll(c); 
    throw new IllegalStateException();
  }
  
  public boolean removeAll(Collection<?> c) {
    return this.queue.removeAll(c);
  }
  
  public boolean retainAll(Collection<?> c) {
    return this.queue.retainAll(c);
  }
  
  public void clear() {
    this.queue.clear();
  }
}
