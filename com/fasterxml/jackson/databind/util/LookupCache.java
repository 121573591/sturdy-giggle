package com.fasterxml.jackson.databind.util;

import java.util.function.BiConsumer;

public interface LookupCache<K, V> {
  default void contents(BiConsumer<K, V> consumer) {
    throw new UnsupportedOperationException();
  }
  
  default LookupCache<K, V> emptyCopy() {
    throw new UnsupportedOperationException("LookupCache implementation " + 
        getClass().getName() + " does not implement `emptyCopy()`");
  }
  
  int size();
  
  V get(Object paramObject);
  
  V put(K paramK, V paramV);
  
  V putIfAbsent(K paramK, V paramV);
  
  void clear();
}
