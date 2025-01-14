package cn.hutool.core.comparator;

import java.io.Serializable;
import java.util.Comparator;

public class ReverseComparator<E> implements Comparator<E>, Serializable {
  private static final long serialVersionUID = 8083701245147495562L;
  
  private final Comparator<? super E> comparator;
  
  public ReverseComparator(Comparator<? super E> comparator) {
    this.comparator = (null == comparator) ? ComparableComparator.INSTANCE : comparator;
  }
  
  public int compare(E o1, E o2) {
    return this.comparator.compare(o2, o1);
  }
  
  public int hashCode() {
    return "ReverseComparator".hashCode() ^ this.comparator.hashCode();
  }
  
  public boolean equals(Object object) {
    if (this == object)
      return true; 
    if (null == object)
      return false; 
    if (object.getClass().equals(getClass())) {
      ReverseComparator<?> thatrc = (ReverseComparator)object;
      return this.comparator.equals(thatrc.comparator);
    } 
    return false;
  }
}
