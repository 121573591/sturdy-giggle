package cn.hutool.core.collection;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.stream.CollectorUtil;
import cn.hutool.core.stream.StreamUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollStreamUtil {
  public static <V, K> Map<K, V> toIdentityMap(Collection<V> collection, Function<V, K> key) {
    return toIdentityMap(collection, key, false);
  }
  
  public static <V, K> Map<K, V> toIdentityMap(Collection<V> collection, Function<V, K> key, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return MapUtil.newHashMap(0); 
    return toMap(collection, v -> Opt.ofNullable(v).map(key).get(), Function.identity(), isParallel);
  }
  
  public static <E, K, V> Map<K, V> toMap(Collection<E> collection, Function<E, K> key, Function<E, V> value) {
    return toMap(collection, key, value, false);
  }
  
  public static <E, K, V> Map<K, V> toMap(Collection<E> collection, Function<E, K> key, Function<E, V> value, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return MapUtil.newHashMap(0); 
    return (Map<K, V>)StreamUtil.of(collection, isParallel)
      .collect(HashMap::new, (m, v) -> m.put(key.apply(v), value.apply(v)), HashMap::putAll);
  }
  
  public static <E, K> Map<K, List<E>> groupByKey(Collection<E> collection, Function<E, K> key) {
    return groupByKey(collection, key, false);
  }
  
  public static <E, K> Map<K, List<E>> groupByKey(Collection<E> collection, Function<E, K> key, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return MapUtil.newHashMap(0); 
    return groupBy(collection, key, Collectors.toList(), isParallel);
  }
  
  public static <E, K, U> Map<K, Map<U, List<E>>> groupBy2Key(Collection<E> collection, Function<E, K> key1, Function<E, U> key2) {
    return groupBy2Key(collection, key1, key2, false);
  }
  
  public static <E, K, U> Map<K, Map<U, List<E>>> groupBy2Key(Collection<E> collection, Function<E, K> key1, Function<E, U> key2, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return MapUtil.newHashMap(0); 
    return groupBy(collection, key1, CollectorUtil.groupingBy(key2, Collectors.toList()), isParallel);
  }
  
  public static <E, T, U> Map<T, Map<U, E>> group2Map(Collection<E> collection, Function<E, T> key1, Function<E, U> key2) {
    return group2Map(collection, key1, key2, false);
  }
  
  public static <E, T, U> Map<T, Map<U, E>> group2Map(Collection<E> collection, Function<E, T> key1, Function<E, U> key2, boolean isParallel) {
    if (CollUtil.isEmpty(collection) || key1 == null || key2 == null)
      return MapUtil.newHashMap(0); 
    return groupBy(collection, key1, CollectorUtil.toMap(key2, Function.identity(), (l, r) -> l), isParallel);
  }
  
  public static <E, K, V> Map<K, List<V>> groupKeyValue(Collection<E> collection, Function<E, K> key, Function<E, V> value) {
    return groupKeyValue(collection, key, value, false);
  }
  
  public static <E, K, V> Map<K, List<V>> groupKeyValue(Collection<E> collection, Function<E, K> key, Function<E, V> value, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return MapUtil.newHashMap(0); 
    return groupBy(collection, key, Collectors.mapping(v -> Opt.ofNullable(v).map(value).orElse(null), (Collector)Collectors.toList()), isParallel);
  }
  
  public static <E, K, D> Map<K, D> groupBy(Collection<E> collection, Function<E, K> key, Collector<E, ?, D> downstream) {
    if (CollUtil.isEmpty(collection))
      return MapUtil.newHashMap(0); 
    return groupBy(collection, key, downstream, false);
  }
  
  public static <E, K, D> Map<K, D> groupBy(Collection<E> collection, Function<E, K> key, Collector<E, ?, D> downstream, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return MapUtil.newHashMap(0); 
    return (Map<K, D>)StreamUtil.of(collection, isParallel).collect(CollectorUtil.groupingBy(key, downstream));
  }
  
  public static <E, T> List<T> toList(Collection<E> collection, Function<E, T> function) {
    return toList(collection, function, false);
  }
  
  public static <E, T> List<T> toList(Collection<E> collection, Function<E, T> function, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return CollUtil.newArrayList((T[])new Object[0]); 
    return (List<T>)StreamUtil.of(collection, isParallel)
      .<T>map(function)
      .filter(Objects::nonNull)
      .collect(Collectors.toList());
  }
  
  public static <E, T> Set<T> toSet(Collection<E> collection, Function<E, T> function) {
    return toSet(collection, function, false);
  }
  
  public static <E, T> Set<T> toSet(Collection<E> collection, Function<E, T> function, boolean isParallel) {
    if (CollUtil.isEmpty(collection))
      return CollUtil.newHashSet((T[])new Object[0]); 
    return (Set<T>)StreamUtil.of(collection, isParallel)
      .<T>map(function)
      .filter(Objects::nonNull)
      .collect(Collectors.toSet());
  }
  
  public static <K, X, Y, V> Map<K, V> merge(Map<K, X> map1, Map<K, Y> map2, BiFunction<X, Y, V> merge) {
    if (MapUtil.isEmpty(map1) && MapUtil.isEmpty(map2))
      return MapUtil.newHashMap(0); 
    if (MapUtil.isEmpty(map1)) {
      map1 = MapUtil.newHashMap(0);
    } else if (MapUtil.isEmpty(map2)) {
      map2 = MapUtil.newHashMap(0);
    } 
    Set<K> key = new HashSet<>();
    key.addAll(map1.keySet());
    key.addAll(map2.keySet());
    Map<K, V> map = MapUtil.newHashMap(key.size());
    for (K t : key) {
      X x = map1.get(t);
      Y y = map2.get(t);
      V z = merge.apply(x, y);
      if (z != null)
        map.put(t, z); 
    } 
    return map;
  }
}
