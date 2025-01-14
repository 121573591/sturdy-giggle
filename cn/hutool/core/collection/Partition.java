package cn.hutool.core.collection;

import cn.hutool.core.lang.Assert;
import java.util.AbstractList;
import java.util.List;

public class Partition<T> extends AbstractList<List<T>> {
  protected final List<T> list;
  
  protected final int size;
  
  public Partition(List<T> list, int size) {
    this.list = (List<T>)Assert.notNull(list);
    this.size = Math.min(list.size(), size);
  }
  
  public List<T> get(int index) {
    int start = index * this.size;
    int end = Math.min(start + this.size, this.list.size());
    return this.list.subList(start, end);
  }
  
  public int size() {
    int size = this.size;
    if (0 == size)
      return 0; 
    int total = this.list.size();
    return (total + size - 1) / size;
  }
  
  public boolean isEmpty() {
    return this.list.isEmpty();
  }
}
