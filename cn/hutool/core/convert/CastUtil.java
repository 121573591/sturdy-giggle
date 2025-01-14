package cn.hutool.core.convert;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CastUtil {
  public static <T> Collection<T> castUp(Collection<? extends T> collection) {
    return (Collection)collection;
  }
  
  public static <T> Collection<T> castDown(Collection<? super T> collection) {
    return (Collection)collection;
  }
  
  public static <T> Set<T> castUp(Set<? extends T> set) {
    return (Set)set;
  }
  
  public static <T> Set<T> castDown(Set<? super T> set) {
    return (Set)set;
  }
  
  public static <T> List<T> castUp(List<? extends T> list) {
    return (List)list;
  }
  
  public static <T> List<T> castDown(List<? super T> list) {
    return (List)list;
  }
  
  public static <K, V> Map<K, V> castUp(Map<? extends K, ? extends V> map) {
    return (Map)map;
  }
  
  public static <K, V> Map<K, V> castDown(Map<? super K, ? super V> map) {
    return (Map)map;
  }
}
