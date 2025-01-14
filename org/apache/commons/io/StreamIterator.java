package org.apache.commons.io;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

class StreamIterator<E> implements Iterator<E>, Closeable {
  private final Iterator<E> iterator;
  
  private final Stream<E> stream;
  
  public static <T> Iterator<T> iterator(Stream<T> stream) {
    return (Iterator)(new StreamIterator(stream)).iterator;
  }
  
  private StreamIterator(Stream<E> stream) {
    this.stream = Objects.<Stream<E>>requireNonNull(stream, "stream");
    this.iterator = stream.iterator();
  }
  
  public boolean hasNext() {
    boolean hasNext = this.iterator.hasNext();
    if (!hasNext)
      close(); 
    return hasNext;
  }
  
  public E next() {
    E next = this.iterator.next();
    if (next == null)
      close(); 
    return next;
  }
  
  public void close() {
    this.stream.close();
  }
}
