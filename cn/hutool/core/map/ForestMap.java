package cn.hutool.core.map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ObjectUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface ForestMap<K, V> extends Map<K, TreeEntry<K, V>> {
  default TreeEntry<K, V> put(K key, TreeEntry<K, V> node) {
    return putNode(key, node.getValue());
  }
  
  default void putAll(Map<? extends K, ? extends TreeEntry<K, V>> treeEntryMap) {
    if (CollUtil.isEmpty(treeEntryMap))
      return; 
    treeEntryMap.forEach((k, v) -> {
          if (v.hasParent()) {
            TreeEntry<K, V> parent = v.getDeclaredParent();
            putLinkedNodes(parent.getKey(), parent.getValue(), v.getKey(), v.getValue());
          } else {
            putNode(v.getKey(), v.getValue());
          } 
        });
  }
  
  default <C extends Collection<V>> void putAllNode(C values, Function<V, K> keyGenerator, Function<V, K> parentKeyGenerator, boolean ignoreNullNode) {
    if (CollUtil.isEmpty((Collection)values))
      return; 
    values.forEach(v -> {
          K key = keyGenerator.apply(v);
          K parentKey = parentKeyGenerator.apply(v);
          boolean hasKey = ObjectUtil.isNotNull(key);
          boolean hasParentKey = ObjectUtil.isNotNull(parentKey);
          if (!ignoreNullNode || (hasKey && hasParentKey)) {
            linkNodes(parentKey, key);
            get(key).setValue((V)v);
            return;
          } 
          if (!hasKey && !hasParentKey)
            return; 
          if (hasKey) {
            putNode(key, (V)v);
            return;
          } 
          putNode(parentKey, (V)null);
        });
  }
  
  default void putLinkedNodes(K parentKey, V parentValue, K childKey, V childValue) {
    putNode(parentKey, parentValue);
    putNode(childKey, childValue);
    linkNodes(parentKey, childKey);
  }
  
  default void linkNodes(K parentKey, K childKey) {
    linkNodes(parentKey, childKey, (BiConsumer<TreeEntry<K, V>, TreeEntry<K, V>>)null);
  }
  
  default Set<TreeEntry<K, V>> getTreeNodes(K key) {
    TreeEntry<K, V> target = get(key);
    if (ObjectUtil.isNull(target))
      return Collections.emptySet(); 
    Set<TreeEntry<K, V>> results = CollUtil.newLinkedHashSet((Object[])new TreeEntry[] { target.getRoot() });
    CollUtil.addAll(results, target.getRoot().getChildren().values());
    return results;
  }
  
  default TreeEntry<K, V> getRootNode(K key) {
    return (TreeEntry<K, V>)Opt.ofNullable(get(key))
      .map(TreeEntry::getRoot)
      .orElse(null);
  }
  
  default TreeEntry<K, V> getDeclaredParentNode(K key) {
    return (TreeEntry<K, V>)Opt.ofNullable(get(key))
      .map(TreeEntry::getDeclaredParent)
      .orElse(null);
  }
  
  default TreeEntry<K, V> getParentNode(K key, K parentKey) {
    return (TreeEntry<K, V>)Opt.ofNullable(get(key))
      .map(t -> t.getParent(parentKey))
      .orElse(null);
  }
  
  default boolean containsParentNode(K key, K parentKey) {
    return ((Boolean)Opt.ofNullable(get(key))
      .map(m -> Boolean.valueOf(m.containsParent(parentKey)))
      .orElse(Boolean.valueOf(false))).booleanValue();
  }
  
  default V getNodeValue(K key) {
    return (V)Opt.ofNullable(get(key))
      .map(Map.Entry::getValue)
      .get();
  }
  
  default boolean containsChildNode(K parentKey, K childKey) {
    return ((Boolean)Opt.ofNullable(get(parentKey))
      .map(m -> Boolean.valueOf(m.containsChild(childKey)))
      .orElse(Boolean.valueOf(false))).booleanValue();
  }
  
  default Collection<TreeEntry<K, V>> getDeclaredChildNodes(K key) {
    return (Collection<TreeEntry<K, V>>)Opt.ofNullable(get(key))
      .map(TreeEntry::getDeclaredChildren)
      .map(Map::values)
      .orElseGet(Collections::emptyList);
  }
  
  default Collection<TreeEntry<K, V>> getChildNodes(K key) {
    return (Collection<TreeEntry<K, V>>)Opt.ofNullable(get(key))
      .map(TreeEntry::getChildren)
      .map(Map::values)
      .orElseGet(Collections::emptyList);
  }
  
  TreeEntry<K, V> remove(Object paramObject);
  
  void clear();
  
  TreeEntry<K, V> putNode(K paramK, V paramV);
  
  void putLinkedNodes(K paramK1, K paramK2, V paramV);
  
  void linkNodes(K paramK1, K paramK2, BiConsumer<TreeEntry<K, V>, TreeEntry<K, V>> paramBiConsumer);
  
  void unlinkNode(K paramK1, K paramK2);
}
