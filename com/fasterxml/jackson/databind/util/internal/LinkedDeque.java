package com.fasterxml.jackson.databind.util.internal;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class LinkedDeque<E extends Linked<E>> extends AbstractCollection<E> implements Deque<E> {
  E first;
  
  E last;
  
  void linkFirst(E e) {
    E f = this.first;
    this.first = e;
    if (f == null) {
      this.last = e;
    } else {
      f.setPrevious(e);
      e.setNext(f);
    } 
  }
  
  void linkLast(E e) {
    E l = this.last;
    this.last = e;
    if (l == null) {
      this.first = e;
    } else {
      l.setNext(e);
      e.setPrevious(l);
    } 
  }
  
  E unlinkFirst() {
    E f = this.first;
    E next = f.getNext();
    f.setNext(null);
    this.first = next;
    if (next == null) {
      this.last = null;
    } else {
      next.setPrevious(null);
    } 
    return f;
  }
  
  E unlinkLast() {
    E l = this.last;
    E prev = l.getPrevious();
    l.setPrevious(null);
    this.last = prev;
    if (prev == null) {
      this.first = null;
    } else {
      prev.setNext(null);
    } 
    return l;
  }
  
  void unlink(E e) {
    E prev = e.getPrevious();
    E next = e.getNext();
    if (prev == null) {
      this.first = next;
    } else {
      prev.setNext(next);
      e.setPrevious(null);
    } 
    if (next == null) {
      this.last = prev;
    } else {
      next.setPrevious(prev);
      e.setNext(null);
    } 
  }
  
  public boolean isEmpty() {
    return (this.first == null);
  }
  
  void checkNotEmpty() {
    if (isEmpty())
      throw new NoSuchElementException(); 
  }
  
  public int size() {
    int size = 0;
    for (E e = this.first; e != null; e = e.getNext())
      size++; 
    return size;
  }
  
  public void clear() {
    for (E e = this.first; e != null; ) {
      E next = e.getNext();
      e.setPrevious(null);
      e.setNext(null);
      e = next;
    } 
    this.first = this.last = null;
  }
  
  public boolean contains(Object o) {
    return (o instanceof Linked && contains((Linked)o));
  }
  
  boolean contains(Linked<?> e) {
    return (e.getPrevious() != null || e
      .getNext() != null || e == this.first);
  }
  
  public void moveToFront(E e) {
    if (e != this.first) {
      unlink(e);
      linkFirst(e);
    } 
  }
  
  public void moveToBack(E e) {
    if (e != this.last) {
      unlink(e);
      linkLast(e);
    } 
  }
  
  public E peek() {
    return peekFirst();
  }
  
  public E peekFirst() {
    return this.first;
  }
  
  public E peekLast() {
    return this.last;
  }
  
  public E getFirst() {
    checkNotEmpty();
    return peekFirst();
  }
  
  public E getLast() {
    checkNotEmpty();
    return peekLast();
  }
  
  public E element() {
    return getFirst();
  }
  
  public boolean offer(E e) {
    return offerLast(e);
  }
  
  public boolean offerFirst(E e) {
    if (contains((Linked<?>)e))
      return false; 
    linkFirst(e);
    return true;
  }
  
  public boolean offerLast(E e) {
    if (contains((Linked<?>)e))
      return false; 
    linkLast(e);
    return true;
  }
  
  public boolean add(E e) {
    return offerLast(e);
  }
  
  public void addFirst(E e) {
    if (!offerFirst(e))
      throw new IllegalArgumentException(); 
  }
  
  public void addLast(E e) {
    if (!offerLast(e))
      throw new IllegalArgumentException(); 
  }
  
  public E poll() {
    return pollFirst();
  }
  
  public E pollFirst() {
    return isEmpty() ? null : unlinkFirst();
  }
  
  public E pollLast() {
    return isEmpty() ? null : unlinkLast();
  }
  
  public E remove() {
    return removeFirst();
  }
  
  public boolean remove(Object o) {
    return (o instanceof Linked && remove((E)o));
  }
  
  boolean remove(E e) {
    if (contains((Linked<?>)e)) {
      unlink(e);
      return true;
    } 
    return false;
  }
  
  public E removeFirst() {
    checkNotEmpty();
    return pollFirst();
  }
  
  public boolean removeFirstOccurrence(Object o) {
    return remove(o);
  }
  
  public E removeLast() {
    checkNotEmpty();
    return pollLast();
  }
  
  public boolean removeLastOccurrence(Object o) {
    return remove(o);
  }
  
  public boolean removeAll(Collection<?> c) {
    boolean modified = false;
    for (Object o : c)
      modified |= remove(o); 
    return modified;
  }
  
  public void push(E e) {
    addFirst(e);
  }
  
  public E pop() {
    return removeFirst();
  }
  
  public Iterator<E> iterator() {
    return new AbstractLinkedIterator((Linked)this.first) {
        E computeNext() {
          return this.cursor.getNext();
        }
      };
  }
  
  public Iterator<E> descendingIterator() {
    return new AbstractLinkedIterator((Linked)this.last) {
        E computeNext() {
          return this.cursor.getPrevious();
        }
      };
  }
  
  abstract class AbstractLinkedIterator implements Iterator<E> {
    E cursor;
    
    AbstractLinkedIterator(E start) {
      this.cursor = start;
    }
    
    public boolean hasNext() {
      return (this.cursor != null);
    }
    
    public E next() {
      if (!hasNext())
        throw new NoSuchElementException(); 
      E e = this.cursor;
      this.cursor = computeNext();
      return e;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
    
    abstract E computeNext();
  }
}
