package cn.hutool.core.comparator;

import cn.hutool.core.util.ObjectUtil;
import java.util.function.Function;

public class FuncComparator<T> extends NullComparator<T> {
  private static final long serialVersionUID = 1L;
  
  private final boolean compareSelf;
  
  private final Function<T, Comparable<?>> func;
  
  public FuncComparator(boolean nullGreater, Function<T, Comparable<?>> func) {
    this(nullGreater, true, func);
  }
  
  public FuncComparator(boolean nullGreater, boolean compareSelf, Function<T, Comparable<?>> func) {
    super(nullGreater, null);
    this.compareSelf = compareSelf;
    this.func = func;
  }
  
  protected int doCompare(T a, T b) {
    Comparable<?> v1;
    Comparable<?> v2;
    try {
      v1 = this.func.apply(a);
      v2 = this.func.apply(b);
    } catch (Exception e) {
      throw new ComparatorException(e);
    } 
    return compare(a, b, v1, v2);
  }
  
  private int compare(T o1, T o2, Comparable v1, Comparable v2) {
    int result = ObjectUtil.compare(v1, v2, this.nullGreater);
    if (this.compareSelf && 0 == result)
      result = CompareUtil.compare(o1, o2, this.nullGreater); 
    return result;
  }
}
