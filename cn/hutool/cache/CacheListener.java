package cn.hutool.cache;

public interface CacheListener<K, V> {
  void onRemove(K paramK, V paramV);
}
