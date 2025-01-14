package cn.hutool.core.comparator;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import java.util.Comparator;

public class IndexedComparator<T> implements Comparator<T> {
  private final boolean atEndIfMiss;
  
  private final T[] array;
  
  public IndexedComparator(T... objs) {
    this(false, objs);
  }
  
  public IndexedComparator(boolean atEndIfMiss, T... objs) {
    Assert.notNull(objs, "'objs' array must not be null", new Object[0]);
    this.atEndIfMiss = atEndIfMiss;
    this.array = objs;
  }
  
  public int compare(T o1, T o2) {
    int index1 = getOrder(o1);
    int index2 = getOrder(o2);
    if (index1 == index2) {
      if (index1 < 0 || index1 == this.array.length)
        return 1; 
      return 0;
    } 
    return Integer.compare(index1, index2);
  }
  
  private int getOrder(T object) {
    int order = ArrayUtil.indexOf((Object[])this.array, object);
    if (order < 0)
      order = this.atEndIfMiss ? this.array.length : -1; 
    return order;
  }
}
