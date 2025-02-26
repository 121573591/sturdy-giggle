package cn.hutool.core.comparator;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public class CompareUtil {
  public static <E extends Comparable<? super E>> Comparator<E> naturalComparator() {
    return ComparableComparator.INSTANCE;
  }
  
  public static <T> int compare(T c1, T c2, Comparator<T> comparator) {
    if (null == comparator)
      return compare((Comparable)c1, (Comparable)c2); 
    return comparator.compare(c1, c2);
  }
  
  public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
    return compare(c1, c2, false);
  }
  
  public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean isNullGreater) {
    if (c1 == c2)
      return 0; 
    if (c1 == null)
      return isNullGreater ? 1 : -1; 
    if (c2 == null)
      return isNullGreater ? -1 : 1; 
    return c1.compareTo(c2);
  }
  
  public static <T> int compare(T o1, T o2, boolean isNullGreater) {
    if (o1 == o2)
      return 0; 
    if (null == o1)
      return isNullGreater ? 1 : -1; 
    if (null == o2)
      return isNullGreater ? -1 : 1; 
    if (o1 instanceof Comparable && o2 instanceof Comparable)
      return ((Comparable<T>)o1).compareTo(o2); 
    if (o1.equals(o2))
      return 0; 
    int result = Integer.compare(o1.hashCode(), o2.hashCode());
    if (0 == result)
      result = compare(o1.toString(), o2.toString()); 
    return result;
  }
  
  public static <T> Comparator<T> comparingPinyin(Function<T, String> keyExtractor) {
    return comparingPinyin(keyExtractor, false);
  }
  
  public static <T> Comparator<T> comparingPinyin(Function<T, String> keyExtractor, boolean reverse) {
    Objects.requireNonNull(keyExtractor);
    PinyinComparator pinyinComparator = new PinyinComparator();
    if (reverse)
      return (o1, o2) -> pinyinComparator.compare(keyExtractor.apply(o2), keyExtractor.apply(o1)); 
    return (o1, o2) -> pinyinComparator.compare(keyExtractor.apply(o1), keyExtractor.apply(o2));
  }
  
  public static <T, U> Comparator<T> comparingIndexed(Function<? super T, ? extends U> keyExtractor, U... objs) {
    return comparingIndexed(keyExtractor, false, objs);
  }
  
  public static <T, U> Comparator<T> comparingIndexed(Function<? super T, ? extends U> keyExtractor, boolean atEndIfMiss, U... objs) {
    Objects.requireNonNull(keyExtractor);
    IndexedComparator<U> indexedComparator = new IndexedComparator<>(atEndIfMiss, objs);
    return (o1, o2) -> indexedComparator.compare(keyExtractor.apply(o1), keyExtractor.apply(o2));
  }
}
