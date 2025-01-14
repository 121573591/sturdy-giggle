package cn.hutool.core.map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import java.util.Map;
import java.util.function.Consumer;

public interface TreeEntry<K, V> extends Map.Entry<K, V> {
  boolean equals(Object paramObject);
  
  int hashCode();
  
  int getWeight();
  
  TreeEntry<K, V> getRoot();
  
  default boolean hasParent() {
    return ObjectUtil.isNotNull(getDeclaredParent());
  }
  
  TreeEntry<K, V> getDeclaredParent();
  
  TreeEntry<K, V> getParent(K paramK);
  
  default boolean containsParent(K key) {
    return ObjectUtil.isNotNull(getParent(key));
  }
  
  void forEachChild(boolean paramBoolean, Consumer<TreeEntry<K, V>> paramConsumer);
  
  Map<K, TreeEntry<K, V>> getDeclaredChildren();
  
  Map<K, TreeEntry<K, V>> getChildren();
  
  default boolean hasChildren() {
    return CollUtil.isNotEmpty(getDeclaredChildren());
  }
  
  TreeEntry<K, V> getChild(K paramK);
  
  default boolean containsChild(K key) {
    return ObjectUtil.isNotNull(getChild(key));
  }
}
