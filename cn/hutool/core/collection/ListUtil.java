package cn.hutool.core.collection;

import cn.hutool.core.comparator.PinyinComparator;
import cn.hutool.core.comparator.PropertyComparator;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Matcher;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class ListUtil {
  public static <T> List<T> list(boolean isLinked) {
    return isLinked ? new LinkedList<>() : new ArrayList<>();
  }
  
  @SafeVarargs
  public static <T> List<T> list(boolean isLinked, T... values) {
    if (ArrayUtil.isEmpty((Object[])values))
      return list(isLinked); 
    List<T> arrayList = isLinked ? new LinkedList<>() : new ArrayList<>(values.length);
    Collections.addAll(arrayList, values);
    return arrayList;
  }
  
  public static <T> List<T> list(boolean isLinked, Collection<T> collection) {
    if (null == collection)
      return list(isLinked); 
    return isLinked ? new LinkedList<>(collection) : new ArrayList<>(collection);
  }
  
  public static <T> List<T> list(boolean isLinked, Iterable<T> iterable) {
    if (null == iterable)
      return list(isLinked); 
    return list(isLinked, iterable.iterator());
  }
  
  public static <T> List<T> list(boolean isLinked, Iterator<T> iter) {
    List<T> list = list(isLinked);
    if (null != iter)
      while (iter.hasNext())
        list.add(iter.next());  
    return list;
  }
  
  public static <T> List<T> list(boolean isLinked, Enumeration<T> enumration) {
    List<T> list = list(isLinked);
    if (null != enumration)
      while (enumration.hasMoreElements())
        list.add(enumration.nextElement());  
    return list;
  }
  
  @SafeVarargs
  public static <T> ArrayList<T> toList(T... values) {
    return (ArrayList<T>)list(false, values);
  }
  
  @SafeVarargs
  public static <T> LinkedList<T> toLinkedList(T... values) {
    return (LinkedList<T>)list(true, values);
  }
  
  @SafeVarargs
  public static <T> List<T> of(T... ts) {
    if (ArrayUtil.isEmpty((Object[])ts))
      return Collections.emptyList(); 
    return Collections.unmodifiableList(toList(ts));
  }
  
  public static <T> CopyOnWriteArrayList<T> toCopyOnWriteArrayList(Collection<T> collection) {
    return (null == collection) ? new CopyOnWriteArrayList<>() : new CopyOnWriteArrayList<>(collection);
  }
  
  public static <T> ArrayList<T> toList(Collection<T> collection) {
    return (ArrayList<T>)list(false, collection);
  }
  
  public static <T> ArrayList<T> toList(Iterable<T> iterable) {
    return (ArrayList<T>)list(false, iterable);
  }
  
  public static <T> ArrayList<T> toList(Iterator<T> iterator) {
    return (ArrayList<T>)list(false, iterator);
  }
  
  public static <T> ArrayList<T> toList(Enumeration<T> enumeration) {
    return (ArrayList<T>)list(false, enumeration);
  }
  
  public static <T> List<T> page(int pageNo, int pageSize, List<T> list) {
    if (CollUtil.isEmpty(list))
      return new ArrayList<>(0); 
    int resultSize = list.size();
    if (resultSize <= pageSize) {
      if (pageNo < PageUtil.getFirstPageNo() + 1)
        return unmodifiable(list); 
      return new ArrayList<>(0);
    } 
    if ((pageNo - PageUtil.getFirstPageNo()) * pageSize > resultSize)
      return new ArrayList<>(0); 
    int[] startEnd = PageUtil.transToStartEnd(pageNo, pageSize);
    if (startEnd[1] > resultSize) {
      startEnd[1] = resultSize;
      if (startEnd[0] > startEnd[1])
        return new ArrayList<>(0); 
    } 
    return sub(list, startEnd[0], startEnd[1]);
  }
  
  public static <T> void page(List<T> list, int pageSize, Consumer<List<T>> pageListConsumer) {
    if (CollUtil.isEmpty(list) || pageSize <= 0)
      return; 
    int total = list.size();
    int totalPage = PageUtil.totalPage(total, pageSize);
    for (int pageNo = PageUtil.getFirstPageNo(); pageNo < totalPage + PageUtil.getFirstPageNo(); pageNo++) {
      int[] startEnd = PageUtil.transToStartEnd(pageNo, pageSize);
      if (startEnd[1] > total)
        startEnd[1] = total; 
      pageListConsumer.accept(sub(list, startEnd[0], startEnd[1]));
    } 
  }
  
  public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
    if (CollUtil.isEmpty(list))
      return list; 
    list.sort(c);
    return list;
  }
  
  public static <T> List<T> sortByProperty(List<T> list, String property) {
    return sort(list, (Comparator<? super T>)new PropertyComparator(property));
  }
  
  public static List<String> sortByPinyin(List<String> list) {
    return sort(list, (Comparator<? super String>)new PinyinComparator());
  }
  
  public static <T> List<T> reverse(List<T> list) {
    Collections.reverse(list);
    return list;
  }
  
  public static <T> List<T> reverseNew(List<T> list) {
    List<T> list2 = (List<T>)ObjectUtil.clone(list);
    if (null == list2)
      list2 = new ArrayList<>(list); 
    try {
      return reverse(list2);
    } catch (UnsupportedOperationException e) {
      return reverse(list(false, list));
    } 
  }
  
  public static <T> List<T> setOrAppend(List<T> list, int index, T element) {
    Assert.notNull(list, "List must be not null !", new Object[0]);
    if (index < list.size()) {
      list.set(index, element);
    } else {
      list.add(element);
    } 
    return list;
  }
  
  public static <T> List<T> setOrPadding(List<T> list, int index, T element) {
    return setOrPadding(list, index, element, null);
  }
  
  public static <T> List<T> setOrPadding(List<T> list, int index, T element, T paddingElement) {
    Assert.notNull(list, "List must be not null !", new Object[0]);
    int size = list.size();
    if (index < size) {
      list.set(index, element);
    } else {
      Validator.checkIndexLimit(index, list.size());
      for (int i = size; i < index; i++)
        list.add(paddingElement); 
      list.add(element);
    } 
    return list;
  }
  
  public static <T> List<T> sub(List<T> list, int start, int end) {
    return sub(list, start, end, 1);
  }
  
  public static <T> List<T> sub(List<T> list, int start, int end, int step) {
    if (list == null)
      return null; 
    if (list.isEmpty())
      return new ArrayList<>(0); 
    int size = list.size();
    if (start < 0)
      start += size; 
    if (end < 0)
      end += size; 
    if (start == size)
      return new ArrayList<>(0); 
    if (start > end) {
      int tmp = start;
      start = end;
      end = tmp;
    } 
    if (end > size) {
      if (start >= size)
        return new ArrayList<>(0); 
      end = size;
    } 
    if (step < 1)
      step = 1; 
    List<T> result = new ArrayList<>();
    int i;
    for (i = start; i < end; i += step)
      result.add(list.get(i)); 
    return result;
  }
  
  public static <T> int lastIndexOf(List<T> list, Matcher<T> matcher) {
    if (null != list) {
      int size = list.size();
      if (size > 0)
        for (int i = size - 1; i >= 0; i--) {
          if (null == matcher || matcher.match(list.get(i)))
            return i; 
        }  
    } 
    return -1;
  }
  
  public static <T> int[] indexOfAll(List<T> list, Matcher<T> matcher) {
    return CollUtil.indexOfAll(list, matcher);
  }
  
  public static <T> List<T> unmodifiable(List<T> list) {
    if (null == list)
      return null; 
    return Collections.unmodifiableList(list);
  }
  
  public static <T> List<T> empty() {
    return Collections.emptyList();
  }
  
  public static <T> List<List<T>> partition(List<T> list, int size) {
    if (CollUtil.isEmpty(list))
      return empty(); 
    return (list instanceof java.util.RandomAccess) ? new RandomAccessPartition<>(list, size) : new Partition<>(list, size);
  }
  
  public static <T> List<List<T>> split(List<T> list, int size) {
    return partition(list, size);
  }
  
  public static <T> List<List<T>> splitAvg(List<T> list, int limit) {
    if (CollUtil.isEmpty(list))
      return empty(); 
    return (list instanceof java.util.RandomAccess) ? new RandomAccessAvgPartition<>(list, limit) : new AvgPartition<>(list, limit);
  }
  
  public static <T> void swapTo(List<T> list, T element, Integer targetIndex) {
    if (CollUtil.isNotEmpty(list)) {
      int index = list.indexOf(element);
      if (index >= 0)
        Collections.swap(list, index, targetIndex.intValue()); 
    } 
  }
  
  public static <T> void swapElement(List<T> list, T element, T targetElement) {
    if (CollUtil.isNotEmpty(list)) {
      int targetIndex = list.indexOf(targetElement);
      if (targetIndex >= 0)
        swapTo(list, element, Integer.valueOf(targetIndex)); 
    } 
  }
}
