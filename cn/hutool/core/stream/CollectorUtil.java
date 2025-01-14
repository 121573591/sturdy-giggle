package cn.hutool.core.stream;

import cn.hutool.core.lang.Opt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorUtil {
  public static final Set<Collector.Characteristics> CH_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
  
  public static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();
  
  public static <T> Collector<T, ?, String> joining(CharSequence delimiter) {
    return joining(delimiter, Object::toString);
  }
  
  public static <T> Collector<T, ?, String> joining(CharSequence delimiter, Function<T, ? extends CharSequence> toStringFunc) {
    return joining(delimiter, "", "", toStringFunc);
  }
  
  public static <T> Collector<T, ?, String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix, Function<T, ? extends CharSequence> toStringFunc) {
    return new SimpleCollector<>(() -> new StringJoiner(delimiter, prefix, suffix), (joiner, ele) -> joiner.add(toStringFunc.apply(ele)), StringJoiner::merge, StringJoiner::toString, 
        
        Collections.emptySet());
  }
  
  public static <T, K, D, A, M extends Map<K, D>> Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier, Supplier<M> mapFactory, Collector<? super T, A, D> downstream) {
    Supplier<A> downstreamSupplier = downstream.supplier();
    BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
    BiConsumer<Map<K, A>, T> accumulator = (m, t) -> {
        K key = (K)Opt.ofNullable(t).map(classifier).orElse(null);
        A container = m.computeIfAbsent(key, ());
        downstreamAccumulator.accept(container, t);
      };
    BinaryOperator<Map<K, A>> merger = mapMerger(downstream.combiner());
    Supplier<Map<K, A>> mangledFactory = mapFactory;
    if (downstream.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH))
      return new SimpleCollector<>(mangledFactory, accumulator, merger, CH_ID); 
    Function<A, A> downstreamFinisher = downstream.finisher();
    Function<Map<K, A>, M> finisher = intermediate -> {
        intermediate.replaceAll(());
        return intermediate;
      };
    return new SimpleCollector<>(mangledFactory, accumulator, merger, finisher, CH_NOID);
  }
  
  public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream) {
    return groupingBy(classifier, java.util.HashMap::new, downstream);
  }
  
  public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier) {
    return groupingBy(classifier, (Collector)Collectors.toList());
  }
  
  public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
    return toMap(keyMapper, valueMapper, mergeFunction, java.util.HashMap::new);
  }
  
  public static <T, K, U, M extends Map<K, U>> Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
    BiConsumer<M, T> accumulator = (map, element) -> map.put(Opt.ofNullable(element).map(keyMapper).get(), Opt.ofNullable(element).map(valueMapper).get());
    return new SimpleCollector<>(mapSupplier, accumulator, mapMerger(mergeFunction), CH_ID);
  }
  
  public static <K, V, M extends Map<K, V>> BinaryOperator<M> mapMerger(BinaryOperator<V> mergeFunction) {
    return (m1, m2) -> {
        for (Map.Entry<K, V> e : (Iterable<Map.Entry<K, V>>)m2.entrySet())
          m1.merge(e.getKey(), e.getValue(), mergeFunction); 
        return m1;
      };
  }
  
  public static <K, V> Collector<Map<K, V>, ?, Map<K, List<V>>> reduceListMap() {
    return reduceListMap(java.util.HashMap::new);
  }
  
  public static <K, V, R extends Map<K, List<V>>> Collector<Map<K, V>, ?, R> reduceListMap(Supplier<R> mapSupplier) {
    return Collectors.reducing(mapSupplier.get(), value -> {
          Map map = mapSupplier.get();
          value.forEach(());
          return map;
        }(l, r) -> {
          Map map = mapSupplier.get();
          map.putAll(l);
          r.forEach(());
          return map;
        });
  }
  
  public static <T, K, R, C extends java.util.Collection<R>, M extends Map<K, C>> Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier, Function<? super T, ? extends R> valueMapper, Supplier<C> valueCollFactory, Supplier<M> mapFactory) {
    return (Collector)groupingBy(classifier, mapFactory, Collectors.mapping(valueMapper, 
          Collectors.toCollection(valueCollFactory)));
  }
  
  public static <T, K, R, C extends java.util.Collection<R>> Collector<T, ?, Map<K, C>> groupingBy(Function<? super T, ? extends K> classifier, Function<? super T, ? extends R> valueMapper, Supplier<C> valueCollFactory) {
    return groupingBy(classifier, valueMapper, valueCollFactory, java.util.HashMap::new);
  }
  
  public static <T, K, R> Collector<T, ?, Map<K, List<R>>> groupingBy(Function<? super T, ? extends K> classifier, Function<? super T, ? extends R> valueMapper) {
    return groupingBy(classifier, valueMapper, ArrayList::new, java.util.HashMap::new);
  }
}
