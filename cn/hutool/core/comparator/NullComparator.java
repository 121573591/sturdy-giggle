package cn.hutool.core.comparator;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class NullComparator<T> implements Comparator<T>, Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final boolean nullGreater;
  
  protected final Comparator<T> comparator;
  
  public NullComparator(boolean nullGreater, Comparator<? super T> comparator) {
    this.nullGreater = nullGreater;
    this.comparator = (Comparator)comparator;
  }
  
  public int compare(T a, T b) {
    if (a == b)
      return 0; 
    if (a == null)
      return this.nullGreater ? 1 : -1; 
    if (b == null)
      return this.nullGreater ? -1 : 1; 
    return doCompare(a, b);
  }
  
  public Comparator<T> thenComparing(Comparator<? super T> other) {
    Objects.requireNonNull(other);
    return new NullComparator(this.nullGreater, (this.comparator == null) ? other : this.comparator.thenComparing(other));
  }
  
  protected int doCompare(T a, T b) {
    if (null == this.comparator) {
      if (a instanceof Comparable && b instanceof Comparable)
        return ((Comparable<T>)a).compareTo(b); 
      return 0;
    } 
    return this.comparator.compare(a, b);
  }
}
