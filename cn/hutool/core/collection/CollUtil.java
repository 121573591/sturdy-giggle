package cn.hutool.core.collection;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.comparator.PinyinComparator;
import cn.hutool.core.comparator.PropertyComparator;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.Matcher;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.hash.Hash32;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CollUtil {
  public static <T> Set<T> emptyIfNull(Set<T> set) {
    return (null == set) ? Collections.<T>emptySet() : set;
  }
  
  public static <T> List<T> emptyIfNull(List<T> list) {
    return (null == list) ? Collections.<T>emptyList() : list;
  }
  
  public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2) {
    if (isEmpty(coll1) && isEmpty(coll2))
      return new ArrayList<>(); 
    if (isEmpty(coll1))
      return new ArrayList<>(coll2); 
    if (isEmpty(coll2))
      return new ArrayList<>(coll1); 
    ArrayList<T> list = new ArrayList<>(Math.max(coll1.size(), coll2.size()));
    Map<T, Integer> map1 = countMap(coll1);
    Map<T, Integer> map2 = countMap(coll2);
    Set<T> elts = newHashSet(coll2);
    elts.addAll(coll1);
    for (T t : elts) {
      int m = Math.max(Convert.toInt(map1.get(t), Integer.valueOf(0)).intValue(), Convert.toInt(map2.get(t), Integer.valueOf(0)).intValue());
      for (int i = 0; i < m; i++)
        list.add(t); 
    } 
    return list;
  }
  
  @SafeVarargs
  public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
    Collection<T> union = union(coll1, coll2);
    for (Collection<T> coll : otherColls) {
      if (!isEmpty(coll))
        union = union(union, coll); 
    } 
    return union;
  }
  
  @SafeVarargs
  public static <T> Set<T> unionDistinct(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
    Set<T> result;
    if (isEmpty(coll1)) {
      result = new LinkedHashSet<>();
    } else {
      result = new LinkedHashSet<>(coll1);
    } 
    if (isNotEmpty(coll2))
      result.addAll(coll2); 
    if (ArrayUtil.isNotEmpty((Object[])otherColls))
      for (Collection<T> otherColl : otherColls) {
        if (!isEmpty(otherColl))
          result.addAll(otherColl); 
      }  
    return result;
  }
  
  @SafeVarargs
  public static <T> List<T> unionAll(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
    if (isEmpty(coll1) && isEmpty(coll2) && ArrayUtil.isEmpty((Object[])otherColls))
      return new ArrayList<>(0); 
    int totalSize = 0;
    totalSize += size(coll1);
    totalSize += size(coll2);
    if (otherColls != null)
      for (Collection<T> otherColl : otherColls)
        totalSize += size(otherColl);  
    List<T> res = new ArrayList<>(totalSize);
    if (coll1 != null)
      res.addAll(coll1); 
    if (coll2 != null)
      res.addAll(coll2); 
    if (otherColls == null)
      return res; 
    for (Collection<T> otherColl : otherColls) {
      if (otherColl != null)
        res.addAll(otherColl); 
    } 
    return res;
  }
  
  public static <T> Collection<T> intersection(Collection<T> coll1, Collection<T> coll2) {
    if (isNotEmpty(coll1) && isNotEmpty(coll2)) {
      ArrayList<T> list = new ArrayList<>(Math.min(coll1.size(), coll2.size()));
      Map<T, Integer> map1 = countMap(coll1);
      Map<T, Integer> map2 = countMap(coll2);
      Set<T> elts = newHashSet(coll2);
      for (T t : elts) {
        int m = Math.min(Convert.toInt(map1.get(t), Integer.valueOf(0)).intValue(), Convert.toInt(map2.get(t), Integer.valueOf(0)).intValue());
        for (int i = 0; i < m; i++)
          list.add(t); 
      } 
      return list;
    } 
    return new ArrayList<>();
  }
  
  @SafeVarargs
  public static <T> Collection<T> intersection(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
    Collection<T> intersection = intersection(coll1, coll2);
    if (isEmpty(intersection))
      return intersection; 
    for (Collection<T> coll : otherColls) {
      intersection = intersection(intersection, coll);
      if (isEmpty(intersection))
        return intersection; 
    } 
    return intersection;
  }
  
  @SafeVarargs
  public static <T> Set<T> intersectionDistinct(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
    if (isEmpty(coll1) || isEmpty(coll2))
      return new LinkedHashSet<>(); 
    Set<T> result = new LinkedHashSet<>(coll1);
    if (ArrayUtil.isNotEmpty((Object[])otherColls))
      for (Collection<T> otherColl : otherColls) {
        if (isNotEmpty(otherColl)) {
          result.retainAll(otherColl);
        } else {
          return new LinkedHashSet<>();
        } 
      }  
    result.retainAll(coll2);
    return result;
  }
  
  public static <T> Collection<T> disjunction(Collection<T> coll1, Collection<T> coll2) {
    if (isEmpty(coll1))
      return coll2; 
    if (isEmpty(coll2))
      return coll1; 
    List<T> result = new ArrayList<>();
    Map<T, Integer> map1 = countMap(coll1);
    Map<T, Integer> map2 = countMap(coll2);
    Set<T> elts = newHashSet(coll2);
    elts.addAll(coll1);
    for (T t : elts) {
      int m = Math.abs(Convert.toInt(map1.get(t), Integer.valueOf(0)).intValue() - Convert.toInt(map2.get(t), Integer.valueOf(0)).intValue());
      for (int i = 0; i < m; i++)
        result.add(t); 
    } 
    return result;
  }
  
  public static <T> Collection<T> subtract(Collection<T> coll1, Collection<T> coll2) {
    Collection<T> result = (Collection<T>)ObjectUtil.clone(coll1);
    try {
      if (null == result) {
        result = create(coll1.getClass());
        result.addAll(coll1);
      } 
      result.removeAll(coll2);
    } catch (UnsupportedOperationException e) {
      result = create(AbstractCollection.class);
      result.addAll(coll1);
      result.removeAll(coll2);
    } 
    return result;
  }
  
  public static <T> List<T> subtractToList(Collection<T> coll1, Collection<T> coll2) {
    if (isEmpty(coll1))
      return ListUtil.empty(); 
    if (isEmpty(coll2))
      return ListUtil.list(true, coll1); 
    List<T> result = new LinkedList<>();
    Set<T> set = new HashSet<>(coll2);
    for (T t : coll1) {
      if (false == set.contains(t))
        result.add(t); 
    } 
    return result;
  }
  
  public static boolean contains(Collection<?> collection, Object value) {
    return (isNotEmpty(collection) && collection.contains(value));
  }
  
  public static boolean safeContains(Collection<?> collection, Object value) {
    try {
      return contains(collection, value);
    } catch (ClassCastException|NullPointerException e) {
      return false;
    } 
  }
  
  public static <T> boolean contains(Collection<T> collection, Predicate<? super T> containFunc) {
    if (isEmpty(collection))
      return false; 
    for (T t : collection) {
      if (containFunc.test(t))
        return true; 
    } 
    return false;
  }
  
  public static boolean containsAny(Collection<?> coll1, Collection<?> coll2) {
    if (isEmpty(coll1) || isEmpty(coll2))
      return false; 
    if (coll1.size() < coll2.size()) {
      for (Object object : coll1) {
        if (coll2.contains(object))
          return true; 
      } 
    } else {
      for (Object object : coll2) {
        if (coll1.contains(object))
          return true; 
      } 
    } 
    return false;
  }
  
  public static boolean containsAll(Collection<?> coll1, Collection<?> coll2) {
    if (isEmpty(coll1))
      return isEmpty(coll2); 
    if (isEmpty(coll2))
      return true; 
    if (coll1 instanceof Set)
      return coll1.containsAll(coll2); 
    Iterator<?> it = coll1.iterator();
    Set<Object> elementsAlreadySeen = new HashSet(coll1.size(), 1.0F);
    for (Object nextElement : coll2) {
      if (elementsAlreadySeen.contains(nextElement))
        continue; 
      boolean foundCurrentElement = false;
      while (it.hasNext()) {
        Object p = it.next();
        elementsAlreadySeen.add(p);
        if (Objects.equals(nextElement, p)) {
          foundCurrentElement = true;
          break;
        } 
      } 
      if (false == foundCurrentElement)
        return false; 
    } 
    return true;
  }
  
  public static <T> Map<T, Integer> countMap(Iterable<T> collection) {
    return IterUtil.countMap((null == collection) ? null : collection.iterator());
  }
  
  public static <T> String join(Iterable<T> iterable, CharSequence conjunction, Function<T, ? extends CharSequence> func) {
    if (null == iterable)
      return null; 
    return IterUtil.join(iterable.iterator(), conjunction, func);
  }
  
  public static <T> String join(Iterable<T> iterable, CharSequence conjunction) {
    if (null == iterable)
      return null; 
    return IterUtil.join(iterable.iterator(), conjunction);
  }
  
  public static <T> String join(Iterable<T> iterable, CharSequence conjunction, String prefix, String suffix) {
    if (null == iterable)
      return null; 
    return IterUtil.join(iterable.iterator(), conjunction, prefix, suffix);
  }
  
  @Deprecated
  public static <T> String join(Iterator<T> iterator, CharSequence conjunction) {
    return IterUtil.join(iterator, conjunction);
  }
  
  public static <T> List<T> popPart(Stack<T> surplusAlaDatas, int partSize) {
    if (isEmpty(surplusAlaDatas))
      return ListUtil.empty(); 
    List<T> currentAlaDatas = new ArrayList<>();
    int size = surplusAlaDatas.size();
    if (size > partSize) {
      for (int i = 0; i < partSize; i++)
        currentAlaDatas.add(surplusAlaDatas.pop()); 
    } else {
      for (int i = 0; i < size; i++)
        currentAlaDatas.add(surplusAlaDatas.pop()); 
    } 
    return currentAlaDatas;
  }
  
  public static <T> List<T> popPart(Deque<T> surplusAlaDatas, int partSize) {
    if (isEmpty(surplusAlaDatas))
      return ListUtil.empty(); 
    List<T> currentAlaDatas = new ArrayList<>();
    int size = surplusAlaDatas.size();
    if (size > partSize) {
      for (int i = 0; i < partSize; i++)
        currentAlaDatas.add(surplusAlaDatas.pop()); 
    } else {
      for (int i = 0; i < size; i++)
        currentAlaDatas.add(surplusAlaDatas.pop()); 
    } 
    return currentAlaDatas;
  }
  
  public static <T> boolean anyMatch(Collection<T> collection, Predicate<T> predicate) {
    if (isEmpty(collection))
      return Boolean.FALSE.booleanValue(); 
    return collection.stream().anyMatch(predicate);
  }
  
  public static <T> boolean allMatch(Collection<T> collection, Predicate<T> predicate) {
    if (isEmpty(collection))
      return Boolean.FALSE.booleanValue(); 
    return collection.stream().allMatch(predicate);
  }
  
  @SafeVarargs
  public static <T> HashSet<T> newHashSet(T... ts) {
    return set(false, ts);
  }
  
  @SafeVarargs
  public static <T> LinkedHashSet<T> newLinkedHashSet(T... ts) {
    return (LinkedHashSet<T>)set(true, ts);
  }
  
  @SafeVarargs
  public static <T> HashSet<T> set(boolean isSorted, T... ts) {
    if (null == ts)
      return isSorted ? new LinkedHashSet<>() : new HashSet<>(); 
    int initialCapacity = Math.max((int)(ts.length / 0.75F) + 1, 16);
    HashSet<T> set = isSorted ? new LinkedHashSet<>(initialCapacity) : new HashSet<>(initialCapacity);
    Collections.addAll(set, ts);
    return set;
  }
  
  public static <T> HashSet<T> newHashSet(Collection<T> collection) {
    return newHashSet(false, collection);
  }
  
  public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
    return isSorted ? new LinkedHashSet<>(collection) : new HashSet<>(collection);
  }
  
  public static <T> HashSet<T> newHashSet(boolean isSorted, Iterator<T> iter) {
    if (null == iter)
      return set(isSorted, (T[])null); 
    HashSet<T> set = isSorted ? new LinkedHashSet<>() : new HashSet<>();
    while (iter.hasNext())
      set.add(iter.next()); 
    return set;
  }
  
  public static <T> HashSet<T> newHashSet(boolean isSorted, Enumeration<T> enumeration) {
    if (null == enumeration)
      return set(isSorted, (T[])null); 
    HashSet<T> set = isSorted ? new LinkedHashSet<>() : new HashSet<>();
    while (enumeration.hasMoreElements())
      set.add(enumeration.nextElement()); 
    return set;
  }
  
  public static <T> List<T> list(boolean isLinked) {
    return ListUtil.list(isLinked);
  }
  
  @SafeVarargs
  public static <T> List<T> list(boolean isLinked, T... values) {
    return ListUtil.list(isLinked, values);
  }
  
  public static <T> List<T> list(boolean isLinked, Collection<T> collection) {
    return ListUtil.list(isLinked, collection);
  }
  
  public static <T> List<T> list(boolean isLinked, Iterable<T> iterable) {
    return ListUtil.list(isLinked, iterable);
  }
  
  public static <T> List<T> list(boolean isLinked, Iterator<T> iter) {
    return ListUtil.list(isLinked, iter);
  }
  
  public static <T> List<T> list(boolean isLinked, Enumeration<T> enumeration) {
    return ListUtil.list(isLinked, enumeration);
  }
  
  @SafeVarargs
  public static <T> ArrayList<T> newArrayList(T... values) {
    return ListUtil.toList(values);
  }
  
  @SafeVarargs
  public static <T> ArrayList<T> toList(T... values) {
    return ListUtil.toList(values);
  }
  
  public static <T> ArrayList<T> newArrayList(Collection<T> collection) {
    return ListUtil.toList(collection);
  }
  
  public static <T> ArrayList<T> newArrayList(Iterable<T> iterable) {
    return ListUtil.toList(iterable);
  }
  
  public static <T> ArrayList<T> newArrayList(Iterator<T> iterator) {
    return ListUtil.toList(iterator);
  }
  
  public static <T> ArrayList<T> newArrayList(Enumeration<T> enumeration) {
    return ListUtil.toList(enumeration);
  }
  
  @SafeVarargs
  public static <T> LinkedList<T> newLinkedList(T... values) {
    return ListUtil.toLinkedList(values);
  }
  
  public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList(Collection<T> collection) {
    return ListUtil.toCopyOnWriteArrayList(collection);
  }
  
  public static <T> BlockingQueue<T> newBlockingQueue(int capacity, boolean isLinked) {
    BlockingQueue<T> queue;
    if (isLinked) {
      queue = new LinkedBlockingDeque<>(capacity);
    } else {
      queue = new ArrayBlockingQueue<>(capacity);
    } 
    return queue;
  }
  
  public static <T> Collection<T> create(Class<?> collectionType) {
    return create(collectionType, null);
  }
  
  public static <T> Collection<T> create(Class<?> collectionType, Class<T> elementType) {
    Collection<T> list;
    if (collectionType.isAssignableFrom(AbstractCollection.class)) {
      list = new ArrayList<>();
    } else if (collectionType.isAssignableFrom(HashSet.class)) {
      list = new HashSet<>();
    } else if (collectionType.isAssignableFrom(LinkedHashSet.class)) {
      list = new LinkedHashSet<>();
    } else if (collectionType.isAssignableFrom(TreeSet.class)) {
      list = new TreeSet<>((o1, o2) -> (o1 instanceof Comparable) ? ((Comparable<Object>)o1).compareTo(o2) : CompareUtil.compare(o1.toString(), o2.toString()));
    } else if (collectionType.isAssignableFrom(EnumSet.class)) {
      list = EnumSet.noneOf((Class<T>)Assert.notNull(elementType));
    } else if (collectionType.isAssignableFrom(ArrayList.class)) {
      list = new ArrayList<>();
    } else if (collectionType.isAssignableFrom(LinkedList.class)) {
      list = new LinkedList<>();
    } else {
      try {
        list = (Collection<T>)ReflectUtil.newInstance(collectionType, new Object[0]);
      } catch (Exception e) {
        Class<?> superclass = collectionType.getSuperclass();
        if (null != superclass && collectionType != superclass)
          return create(superclass); 
        throw new UtilException(e);
      } 
    } 
    return list;
  }
  
  public static <T> ArrayList<T> distinct(Collection<T> collection) {
    if (isEmpty(collection))
      return new ArrayList<>(); 
    if (collection instanceof Set)
      return new ArrayList<>(collection); 
    return new ArrayList<>(new LinkedHashSet<>(collection));
  }
  
  public static <T, K> List<T> distinct(Collection<T> collection, Function<T, K> uniqueGenerator, boolean override) {
    if (isEmpty(collection))
      return new ArrayList<>(); 
    UniqueKeySet<K, T> set = new UniqueKeySet<>(true, uniqueGenerator);
    if (override) {
      set.addAll(collection);
    } else {
      set.addAllIfAbsent(collection);
    } 
    return new ArrayList<>(set);
  }
  
  public static <T> List<T> sub(List<T> list, int start, int end) {
    return ListUtil.sub(list, start, end);
  }
  
  public static <T> List<T> sub(List<T> list, int start, int end, int step) {
    return ListUtil.sub(list, start, end, step);
  }
  
  public static <T> List<T> sub(Collection<T> collection, int start, int end) {
    return sub(collection, start, end, 1);
  }
  
  public static <T> List<T> sub(Collection<T> collection, int start, int end, int step) {
    if (isEmpty(collection))
      return ListUtil.empty(); 
    List<T> list = (collection instanceof List) ? (List<T>)collection : ListUtil.<T>toList(collection);
    return sub(list, start, end, step);
  }
  
  @Deprecated
  public static <T> List<List<T>> splitList(List<T> list, int size) {
    return ListUtil.partition(list, size);
  }
  
  public static <T> List<List<T>> split(Collection<T> collection, int size) {
    List<List<T>> result = new ArrayList<>();
    if (isEmpty(collection))
      return result; 
    int initSize = Math.min(collection.size(), size);
    List<T> subList = new ArrayList<>(initSize);
    for (T t : collection) {
      if (subList.size() >= size) {
        result.add(subList);
        subList = new ArrayList<>(initSize);
      } 
      subList.add(t);
    } 
    result.add(subList);
    return result;
  }
  
  public static <T> Collection<T> edit(Collection<T> collection, Editor<T> editor) {
    if (null == collection || null == editor)
      return collection; 
    Collection<T> collection2 = create(collection.getClass());
    if (isEmpty(collection))
      return collection2; 
    for (T t : collection) {
      T modified = (T)editor.edit(t);
      if (null != modified)
        collection2.add(modified); 
    } 
    return collection2;
  }
  
  public static <T> Collection<T> filterNew(Collection<T> collection, Filter<T> filter) {
    if (null == collection || null == filter)
      return collection; 
    return edit(collection, t -> filter.accept(t) ? t : null);
  }
  
  public static <T extends Collection<E>, E> T removeAny(T collection, E... elesRemoved) {
    collection.removeAll(newHashSet((Object[])elesRemoved));
    return collection;
  }
  
  public static <T extends Collection<E>, E> T filter(T collection, Filter<E> filter) {
    return (T)IterUtil.<Collection, E>filter((Collection)collection, filter);
  }
  
  public static <T extends Collection<E>, E> T removeNull(T collection) {
    return filter(collection, Objects::nonNull);
  }
  
  public static <T extends Collection<E>, E extends CharSequence> T removeEmpty(T collection) {
    return filter(collection, CharSequenceUtil::isNotEmpty);
  }
  
  public static <T extends Collection<E>, E extends CharSequence> T removeBlank(T collection) {
    return filter(collection, CharSequenceUtil::isNotBlank);
  }
  
  public static <T extends Collection<E>, E> T removeWithAddIf(T targetCollection, T resultCollection, Predicate<? super E> predicate) {
    Objects.requireNonNull(predicate);
    Iterator<E> each = targetCollection.iterator();
    while (each.hasNext()) {
      E next = each.next();
      if (predicate.test(next)) {
        resultCollection.add(next);
        each.remove();
      } 
    } 
    return resultCollection;
  }
  
  public static <T extends Collection<E>, E> List<E> removeWithAddIf(T targetCollection, Predicate<? super E> predicate) {
    List<E> removed = new ArrayList<>();
    removeWithAddIf(targetCollection, (T)removed, predicate);
    return removed;
  }
  
  public static List<Object> extract(Iterable<?> collection, Editor<Object> editor) {
    return extract(collection, editor, false);
  }
  
  public static List<Object> extract(Iterable<?> collection, Editor<Object> editor, boolean ignoreNull) {
    return map(collection, editor::edit, ignoreNull);
  }
  
  public static <T, R> List<R> map(Iterable<T> collection, Function<? super T, ? extends R> func, boolean ignoreNull) {
    List<R> fieldValueList = new ArrayList<>();
    if (null == collection)
      return fieldValueList; 
    for (T t : collection) {
      if (null == t && ignoreNull)
        continue; 
      R value = func.apply(t);
      if (null == value && ignoreNull)
        continue; 
      fieldValueList.add(value);
    } 
    return fieldValueList;
  }
  
  public static List<Object> getFieldValues(Iterable<?> collection, String fieldName) {
    return getFieldValues(collection, fieldName, false);
  }
  
  public static List<Object> getFieldValues(Iterable<?> collection, String fieldName, boolean ignoreNull) {
    return map(collection, bean -> (bean instanceof Map) ? ((Map)bean).get(fieldName) : ReflectUtil.getFieldValue(bean, fieldName), ignoreNull);
  }
  
  public static <T> List<T> getFieldValues(Iterable<?> collection, String fieldName, Class<T> elementType) {
    List<Object> fieldValues = getFieldValues(collection, fieldName);
    return Convert.toList(elementType, fieldValues);
  }
  
  public static <K, V> Map<K, V> fieldValueMap(Iterable<V> iterable, String fieldName) {
    return IterUtil.fieldValueMap(IterUtil.getIter(iterable), fieldName);
  }
  
  public static <K, V> Map<K, V> fieldValueAsMap(Iterable<?> iterable, String fieldNameForKey, String fieldNameForValue) {
    return IterUtil.fieldValueAsMap(IterUtil.getIter(iterable), fieldNameForKey, fieldNameForValue);
  }
  
  public static <T> T findOne(Iterable<T> collection, Filter<T> filter) {
    if (null != collection)
      for (T t : collection) {
        if (filter.accept(t))
          return t; 
      }  
    return null;
  }
  
  public static <T> T findOneByField(Iterable<T> collection, String fieldName, Object fieldValue) {
    return findOne(collection, t -> {
          if (t instanceof Map) {
            Map<?, ?> map = (Map<?, ?>)t;
            Object object = map.get(fieldName);
            return ObjectUtil.equal(object, fieldValue);
          } 
          Object value = ReflectUtil.getFieldValue(t, fieldName);
          return ObjectUtil.equal(value, fieldValue);
        });
  }
  
  public static <T> int count(Iterable<T> iterable, Matcher<T> matcher) {
    int count = 0;
    if (null != iterable)
      for (T t : iterable) {
        if (null == matcher || matcher.match(t))
          count++; 
      }  
    return count;
  }
  
  public static <T> int indexOf(Collection<T> collection, Matcher<T> matcher) {
    if (isNotEmpty(collection)) {
      int index = 0;
      for (T t : collection) {
        if (null == matcher || matcher.match(t))
          return index; 
        index++;
      } 
    } 
    return -1;
  }
  
  public static <T> int lastIndexOf(Collection<T> collection, Matcher<T> matcher) {
    if (collection instanceof List)
      return ListUtil.lastIndexOf((List<T>)collection, matcher); 
    int matchIndex = -1;
    if (isNotEmpty(collection)) {
      int index = 0;
      for (T t : collection) {
        if (null == matcher || matcher.match(t))
          matchIndex = index; 
        index++;
      } 
    } 
    return matchIndex;
  }
  
  public static <T> int[] indexOfAll(Collection<T> collection, Matcher<T> matcher) {
    List<Integer> indexList = new ArrayList<>();
    if (null != collection) {
      int index = 0;
      for (T t : collection) {
        if (null == matcher || matcher.match(t))
          indexList.add(Integer.valueOf(index)); 
        index++;
      } 
    } 
    return (int[])Convert.convert(int[].class, indexList);
  }
  
  public static boolean isEmpty(Collection<?> collection) {
    return (collection == null || collection.isEmpty());
  }
  
  public static <T extends Collection<E>, E> T defaultIfEmpty(T collection, T defaultCollection) {
    return isEmpty((Collection<?>)collection) ? defaultCollection : collection;
  }
  
  public static <T extends Collection<E>, E> T defaultIfEmpty(T collection, Supplier<? extends T> supplier) {
    return isEmpty((Collection<?>)collection) ? supplier.get() : collection;
  }
  
  public static boolean isEmpty(Iterable<?> iterable) {
    return IterUtil.isEmpty(iterable);
  }
  
  public static boolean isEmpty(Iterator<?> iterator) {
    return IterUtil.isEmpty(iterator);
  }
  
  public static boolean isEmpty(Enumeration<?> enumeration) {
    return (null == enumeration || false == enumeration.hasMoreElements());
  }
  
  public static boolean isEmpty(Map<?, ?> map) {
    return MapUtil.isEmpty(map);
  }
  
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }
  
  public static boolean isNotEmpty(Iterable<?> iterable) {
    return IterUtil.isNotEmpty(iterable);
  }
  
  public static boolean isNotEmpty(Iterator<?> iterator) {
    return IterUtil.isNotEmpty(iterator);
  }
  
  public static boolean isNotEmpty(Enumeration<?> enumeration) {
    return (null != enumeration && enumeration.hasMoreElements());
  }
  
  public static boolean hasNull(Iterable<?> iterable) {
    return IterUtil.hasNull(iterable);
  }
  
  public static boolean isNotEmpty(Map<?, ?> map) {
    return MapUtil.isNotEmpty(map);
  }
  
  public static Map<String, String> zip(String keys, String values, String delimiter, boolean isOrder) {
    return ArrayUtil.zip((Object[])StrUtil.splitToArray(keys, delimiter), (Object[])StrUtil.splitToArray(values, delimiter), isOrder);
  }
  
  public static Map<String, String> zip(String keys, String values, String delimiter) {
    return zip(keys, values, delimiter, false);
  }
  
  public static <K, V> Map<K, V> zip(Collection<K> keys, Collection<V> values) {
    if (isEmpty(keys) || isEmpty(values))
      return MapUtil.empty(); 
    int entryCount = Math.min(keys.size(), values.size());
    Map<K, V> map = MapUtil.newHashMap(entryCount);
    Iterator<K> keyIterator = keys.iterator();
    Iterator<V> valueIterator = values.iterator();
    while (entryCount > 0) {
      map.put(keyIterator.next(), valueIterator.next());
      entryCount--;
    } 
    return map;
  }
  
  public static <K, V> HashMap<K, V> toMap(Iterable<Map.Entry<K, V>> entryIter) {
    return IterUtil.toMap(entryIter);
  }
  
  public static HashMap<Object, Object> toMap(Object[] array) {
    return MapUtil.of(array);
  }
  
  public static <T> TreeSet<T> toTreeSet(Collection<T> collection, Comparator<T> comparator) {
    TreeSet<T> treeSet = new TreeSet<>(comparator);
    treeSet.addAll(collection);
    return treeSet;
  }
  
  public static <E> Enumeration<E> asEnumeration(Iterator<E> iter) {
    return new IteratorEnumeration<>(iter);
  }
  
  public static <E> Iterator<E> asIterator(Enumeration<E> e) {
    return IterUtil.asIterator(e);
  }
  
  public static <E> Iterable<E> asIterable(Iterator<E> iter) {
    return IterUtil.asIterable(iter);
  }
  
  public static <E> Collection<E> toCollection(Iterable<E> iterable) {
    return (iterable instanceof Collection) ? (Collection<E>)iterable : newArrayList(iterable.iterator());
  }
  
  public static <K, V> Map<K, List<V>> toListMap(Iterable<? extends Map<K, V>> mapList) {
    return MapUtil.toListMap(mapList);
  }
  
  public static <K, V> List<Map<K, V>> toMapList(Map<K, ? extends Iterable<V>> listMap) {
    return MapUtil.toMapList(listMap);
  }
  
  public static <K, V> Map<K, V> toMap(Iterable<V> values, Map<K, V> map, Func1<V, K> keyFunc) {
    return IterUtil.toMap((null == values) ? null : values.iterator(), map, keyFunc);
  }
  
  public static <K, V, E> Map<K, V> toMap(Iterable<E> values, Map<K, V> map, Func1<E, K> keyFunc, Func1<E, V> valueFunc) {
    return IterUtil.toMap((null == values) ? null : values.iterator(), map, keyFunc, valueFunc);
  }
  
  public static <T, S extends T> boolean addIfAbsent(Collection<T> collection, S object) {
    if (object == null || collection == null || collection.contains(object))
      return false; 
    return collection.add((T)object);
  }
  
  public static <T> Collection<T> addAll(Collection<T> collection, Object value) {
    return addAll(collection, value, TypeUtil.getTypeArgument(collection.getClass()));
  }
  
  public static <T> Collection<T> addAll(Collection<T> collection, Object value, Type<Object> elementType) {
    Iterator iter;
    if (null == collection || null == value)
      return collection; 
    if (TypeUtil.isUnknown(elementType))
      elementType = Object.class; 
    if (value instanceof Iterator) {
      iter = (Iterator)value;
    } else if (value instanceof Iterable) {
      if (value instanceof Map && BeanUtil.isBean(TypeUtil.getClass(elementType))) {
        iter = new ArrayIter(new Object[] { value });
      } else {
        iter = ((Iterable)value).iterator();
      } 
    } else if (value instanceof Enumeration) {
      iter = new EnumerationIter((Enumeration)value);
    } else if (ArrayUtil.isArray(value)) {
      iter = new ArrayIter(value);
    } else if (value instanceof CharSequence) {
      String ArrayStr = StrUtil.unWrap((CharSequence)value, '[', ']');
      iter = StrUtil.splitTrim(ArrayStr, ',').iterator();
    } else {
      iter = newArrayList(new Object[] { value }).iterator();
    } 
    ConverterRegistry convert = ConverterRegistry.getInstance();
    while (iter.hasNext())
      collection.add((T)convert.convert(elementType, iter.next())); 
    return collection;
  }
  
  public static <T> Collection<T> addAll(Collection<T> collection, Iterator<T> iterator) {
    if (null != collection && null != iterator)
      while (iterator.hasNext())
        collection.add(iterator.next());  
    return collection;
  }
  
  public static <T> Collection<T> addAll(Collection<T> collection, Iterable<T> iterable) {
    if (iterable == null)
      return collection; 
    return addAll(collection, iterable.iterator());
  }
  
  public static <T> Collection<T> addAll(Collection<T> collection, Enumeration<T> enumeration) {
    if (null != collection && null != enumeration)
      while (enumeration.hasMoreElements())
        collection.add(enumeration.nextElement());  
    return collection;
  }
  
  public static <T> Collection<T> addAll(Collection<T> collection, T[] values) {
    if (null != collection && null != values)
      Collections.addAll(collection, values); 
    return collection;
  }
  
  public static <T> List<T> addAllIfNotContains(List<T> list, List<T> otherList) {
    for (T t : otherList) {
      if (false == list.contains(t))
        list.add(t); 
    } 
    return list;
  }
  
  public static <T> T get(Collection<T> collection, int index) {
    if (null == collection)
      return null; 
    int size = collection.size();
    if (0 == size)
      return null; 
    if (index < 0)
      index += size; 
    if (index >= size || index < 0)
      return null; 
    if (collection instanceof List) {
      List<T> list = (List<T>)collection;
      return list.get(index);
    } 
    return IterUtil.get(collection.iterator(), index);
  }
  
  public static <T> List<T> getAny(Collection<T> collection, int... indexes) {
    int size = collection.size();
    ArrayList<T> result = new ArrayList<>();
    if (collection instanceof List) {
      List<T> list = (List<T>)collection;
      for (int index : indexes) {
        if (index < 0)
          index += size; 
        result.add(list.get(index));
      } 
    } else {
      Object[] array = collection.toArray();
      for (int index : indexes) {
        if (index < 0)
          index += size; 
        result.add((T)array[index]);
      } 
    } 
    return result;
  }
  
  public static <T> T getFirst(Iterable<T> iterable) {
    return IterUtil.getFirst(iterable);
  }
  
  public static <T> T getFirst(Iterator<T> iterator) {
    return IterUtil.getFirst(iterator);
  }
  
  public static <T> T getLast(Collection<T> collection) {
    return get(collection, -1);
  }
  
  @Deprecated
  public static Class<?> getElementType(Iterable<?> iterable) {
    return IterUtil.getElementType(iterable);
  }
  
  @Deprecated
  public static Class<?> getElementType(Iterator<?> iterator) {
    return IterUtil.getElementType(iterator);
  }
  
  public static <K, V> ArrayList<V> valuesOfKeys(Map<K, V> map, K... keys) {
    return MapUtil.valuesOfKeys(map, new ArrayIter<>(keys));
  }
  
  public static <K, V> ArrayList<V> valuesOfKeys(Map<K, V> map, Iterable<K> keys) {
    return valuesOfKeys(map, keys.iterator());
  }
  
  public static <K, V> ArrayList<V> valuesOfKeys(Map<K, V> map, Iterator<K> keys) {
    return MapUtil.valuesOfKeys(map, keys);
  }
  
  @SafeVarargs
  public static <T> List<T> sortPageAll(int pageNo, int pageSize, Comparator<T> comparator, Collection<T>... colls) {
    List<T> list = new ArrayList<>(pageNo * pageSize);
    for (Collection<T> coll : colls)
      list.addAll(coll); 
    if (null != comparator)
      list.sort(comparator); 
    return page(pageNo, pageSize, list);
  }
  
  public static <T> List<T> page(int pageNo, int pageSize, List<T> list) {
    return ListUtil.page(pageNo, pageSize, list);
  }
  
  public static <T> List<T> sort(Collection<T> collection, Comparator<? super T> comparator) {
    List<T> list = new ArrayList<>(collection);
    list.sort(comparator);
    return list;
  }
  
  public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
    return ListUtil.sort(list, c);
  }
  
  public static <T> List<T> sortByProperty(Collection<T> collection, String property) {
    return sort(collection, (Comparator<? super T>)new PropertyComparator(property));
  }
  
  public static <T> List<T> sortByProperty(List<T> list, String property) {
    return ListUtil.sortByProperty(list, property);
  }
  
  public static List<String> sortByPinyin(Collection<String> collection) {
    return sort(collection, (Comparator<? super String>)new PinyinComparator());
  }
  
  public static List<String> sortByPinyin(List<String> list) {
    return ListUtil.sortByPinyin(list);
  }
  
  public static <K, V> TreeMap<K, V> sort(Map<K, V> map, Comparator<? super K> comparator) {
    TreeMap<K, V> result = new TreeMap<>(comparator);
    result.putAll(map);
    return result;
  }
  
  public static <K, V> LinkedHashMap<K, V> sortToMap(Collection<Map.Entry<K, V>> entryCollection, Comparator<Map.Entry<K, V>> comparator) {
    List<Map.Entry<K, V>> list = new LinkedList<>(entryCollection);
    list.sort(comparator);
    LinkedHashMap<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list)
      result.put(entry.getKey(), entry.getValue()); 
    return result;
  }
  
  public static <K, V> LinkedHashMap<K, V> sortByEntry(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
    return sortToMap(map.entrySet(), comparator);
  }
  
  public static <K, V> List<Map.Entry<K, V>> sortEntryToList(Collection<Map.Entry<K, V>> collection) {
    List<Map.Entry<K, V>> list = new LinkedList<>(collection);
    list.sort((o1, o2) -> {
          V v1 = (V)o1.getValue();
          V v2 = (V)o2.getValue();
          return (v1 instanceof Comparable) ? ((Comparable<V>)v1).compareTo(v2) : v1.toString().compareTo(v2.toString());
        });
    return list;
  }
  
  public static <T> void forEach(Iterable<T> iterable, Consumer<T> consumer) {
    if (iterable == null)
      return; 
    forEach(iterable.iterator(), consumer);
  }
  
  public static <T> void forEach(Iterator<T> iterator, Consumer<T> consumer) {
    if (iterator == null)
      return; 
    int index = 0;
    while (iterator.hasNext()) {
      consumer.accept(iterator.next(), index);
      index++;
    } 
  }
  
  public static <T> void forEach(Enumeration<T> enumeration, Consumer<T> consumer) {
    if (enumeration == null)
      return; 
    int index = 0;
    while (enumeration.hasMoreElements()) {
      consumer.accept(enumeration.nextElement(), index);
      index++;
    } 
  }
  
  public static <K, V> void forEach(Map<K, V> map, KVConsumer<K, V> kvConsumer) {
    if (map == null)
      return; 
    int index = 0;
    for (Map.Entry<K, V> entry : map.entrySet()) {
      kvConsumer.accept(entry.getKey(), entry.getValue(), index);
      index++;
    } 
  }
  
  public static <T> List<List<T>> group(Collection<T> collection, Hash32<T> hash) {
    List<List<T>> result = new ArrayList<>();
    if (isEmpty(collection))
      return result; 
    if (null == hash)
      hash = (t -> (null == t) ? 0 : t.hashCode()); 
    for (T t : collection) {
      int index = hash.hash32(t);
      if (result.size() - 1 < index) {
        while (result.size() - 1 < index)
          result.add(null); 
        result.set(index, newArrayList((T[])new Object[] { t }));
        continue;
      } 
      List<T> subList = result.get(index);
      if (null == subList) {
        result.set(index, newArrayList((T[])new Object[] { t }));
        continue;
      } 
      subList.add(t);
    } 
    return result;
  }
  
  public static <T> List<List<T>> groupByField(Collection<T> collection, final String fieldName) {
    return group(collection, new Hash32<T>() {
          private final List<Object> fieldNameList = new ArrayList();
          
          public int hash32(T t) {
            if (null == t || false == BeanUtil.isBean(t.getClass()))
              return 0; 
            Object value = ReflectUtil.getFieldValue(t, fieldName);
            int hash = this.fieldNameList.indexOf(value);
            if (hash < 0) {
              this.fieldNameList.add(value);
              return this.fieldNameList.size() - 1;
            } 
            return hash;
          }
        });
  }
  
  @FunctionalInterface
  public static interface KVConsumer<K, V> extends Serializable {
    void accept(K param1K, V param1V, int param1Int);
  }
  
  @FunctionalInterface
  public static interface Consumer<T> extends Serializable {
    void accept(T param1T, int param1Int);
  }
  
  public static <T> List<T> reverse(List<T> list) {
    return ListUtil.reverse(list);
  }
  
  public static <T> List<T> reverseNew(List<T> list) {
    return ListUtil.reverseNew(list);
  }
  
  public static <T> List<T> setOrAppend(List<T> list, int index, T element) {
    return ListUtil.setOrAppend(list, index, element);
  }
  
  public static <K> Set<K> keySet(Collection<Map<K, ?>> mapCollection) {
    if (isEmpty(mapCollection))
      return new HashSet<>(); 
    HashSet<K> set = new HashSet<>(mapCollection.size() * 16);
    for (Map<K, ?> map : mapCollection)
      set.addAll(map.keySet()); 
    return set;
  }
  
  public static <V> List<V> values(Collection<Map<?, V>> mapCollection) {
    List<V> values = new ArrayList<>();
    for (Map<?, V> map : mapCollection)
      values.addAll(map.values()); 
    return values;
  }
  
  public static <T extends Comparable<? super T>> T max(Collection<T> coll) {
    return isEmpty(coll) ? null : (T)Collections.<Comparable>max(coll);
  }
  
  public static <T extends Comparable<? super T>> T min(Collection<T> coll) {
    return isEmpty(coll) ? null : (T)Collections.<Comparable>min(coll);
  }
  
  public static <T> Collection<T> unmodifiable(Collection<? extends T> c) {
    return Collections.unmodifiableCollection(c);
  }
  
  public static <E, T extends Collection<E>> T empty(Class<?> collectionClass) {
    if (null == collectionClass)
      return (T)Collections.emptyList(); 
    if (Set.class.isAssignableFrom(collectionClass)) {
      if (NavigableSet.class == collectionClass)
        return (T)Collections.emptyNavigableSet(); 
      if (SortedSet.class == collectionClass)
        return (T)Collections.emptySortedSet(); 
      return (T)Collections.emptySet();
    } 
    if (List.class.isAssignableFrom(collectionClass))
      return (T)Collections.emptyList(); 
    throw new IllegalArgumentException(StrUtil.format("[{}] is not support to get empty!", new Object[] { collectionClass }));
  }
  
  public static void clear(Collection<?>... collections) {
    for (Collection<?> collection : collections) {
      if (isNotEmpty(collection))
        collection.clear(); 
    } 
  }
  
  public static <T> void padLeft(List<T> list, int minLen, T padObj) {
    Objects.requireNonNull(list);
    if (list.isEmpty()) {
      padRight(list, minLen, padObj);
      return;
    } 
    for (int i = list.size(); i < minLen; i++)
      list.add(0, padObj); 
  }
  
  public static <T> void padRight(Collection<T> list, int minLen, T padObj) {
    Objects.requireNonNull(list);
    for (int i = list.size(); i < minLen; i++)
      list.add(padObj); 
  }
  
  public static <F, T> Collection<T> trans(Collection<F> collection, Function<? super F, ? extends T> function) {
    return new TransCollection<>(collection, function);
  }
  
  public static <E, K, V> void setValueByMap(Iterable<E> iterable, Map<K, V> map, Function<E, K> keyGenerate, BiConsumer<E, V> biConsumer) {
    iterable.forEach(x -> Optional.ofNullable(map.get(keyGenerate.apply(x))).ifPresent(()));
  }
  
  public static int size(Object object) {
    if (object == null)
      return 0; 
    int total = 0;
    if (object instanceof Map) {
      total = ((Map)object).size();
    } else if (object instanceof Collection) {
      total = ((Collection)object).size();
    } else if (object instanceof Iterable) {
      total = IterUtil.size((Iterable)object);
    } else if (object instanceof Iterator) {
      total = IterUtil.size((Iterator)object);
    } else if (object instanceof Enumeration) {
      Enumeration<?> it = (Enumeration)object;
      while (it.hasMoreElements()) {
        total++;
        it.nextElement();
      } 
    } else if (ArrayUtil.isArray(object)) {
      total = ArrayUtil.length(object);
    } else {
      throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
    } 
    return total;
  }
  
  public static boolean isEqualList(Collection<?> list1, Collection<?> list2) {
    if (list1 == list2)
      return true; 
    if (list1 == null || list2 == null || list1.size() != list2.size())
      return false; 
    return IterUtil.isEqualList(list1, list2);
  }
}
