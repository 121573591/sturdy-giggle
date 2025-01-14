package cn.hutool.core.map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LinkedForestMap<K, V> implements ForestMap<K, V> {
  private final Map<K, TreeEntryNode<K, V>> nodes;
  
  private final boolean allowOverrideParent;
  
  public LinkedForestMap(boolean allowOverrideParent) {
    this.allowOverrideParent = allowOverrideParent;
    this.nodes = new LinkedHashMap<>();
  }
  
  public int size() {
    return this.nodes.size();
  }
  
  public boolean isEmpty() {
    return this.nodes.isEmpty();
  }
  
  public boolean containsKey(Object key) {
    return this.nodes.containsKey(key);
  }
  
  public boolean containsValue(Object value) {
    return this.nodes.containsValue(value);
  }
  
  public TreeEntry<K, V> get(Object key) {
    return this.nodes.get(key);
  }
  
  public TreeEntry<K, V> remove(Object key) {
    TreeEntryNode<K, V> target = this.nodes.remove(key);
    if (ObjectUtil.isNull(target))
      return null; 
    if (target.hasParent()) {
      TreeEntryNode<K, V> parent = target.getDeclaredParent();
      Map<K, TreeEntry<K, V>> targetChildren = target.getChildren();
      parent.removeDeclaredChild(target.getKey());
      target.clear();
      targetChildren.forEach((k, c) -> parent.addChild((TreeEntryNode)c));
    } 
    return target;
  }
  
  public void clear() {
    this.nodes.values().forEach(TreeEntryNode::clear);
    this.nodes.clear();
  }
  
  public Set<K> keySet() {
    return this.nodes.keySet();
  }
  
  public Collection<TreeEntry<K, V>> values() {
    return new ArrayList<>(this.nodes.values());
  }
  
  public Set<Map.Entry<K, TreeEntry<K, V>>> entrySet() {
    return (Set<Map.Entry<K, TreeEntry<K, V>>>)this.nodes.entrySet().stream()
      .map(this::wrap)
      .collect(Collectors.toSet());
  }
  
  private Map.Entry<K, TreeEntry<K, V>> wrap(Map.Entry<K, TreeEntryNode<K, V>> nodeEntry) {
    return new EntryNodeWrapper<>(nodeEntry.getValue());
  }
  
  public TreeEntryNode<K, V> putNode(K key, V value) {
    TreeEntryNode<K, V> target = this.nodes.get(key);
    if (ObjectUtil.isNotNull(target)) {
      V oldVal = target.getValue();
      target.setValue(value);
      return target.copy(oldVal);
    } 
    target = new TreeEntryNode<>(null, key, value);
    this.nodes.put(key, target);
    return null;
  }
  
  public void putLinkedNodes(K parentKey, V parentValue, K childKey, V childValue) {
    linkNodes(parentKey, childKey, (parent, child) -> {
          parent.setValue(parentValue);
          child.setValue(childValue);
        });
  }
  
  public void putLinkedNodes(K parentKey, K childKey, V childValue) {
    linkNodes(parentKey, childKey, (parent, child) -> child.setValue(childValue));
  }
  
  public void linkNodes(K parentKey, K childKey, BiConsumer<TreeEntry<K, V>, TreeEntry<K, V>> consumer) {
    consumer = (BiConsumer<TreeEntry<K, V>, TreeEntry<K, V>>)ObjectUtil.defaultIfNull(consumer, (parent, child) -> {
        
        });
    TreeEntryNode<K, V> parentNode = this.nodes.computeIfAbsent(parentKey, t -> new TreeEntryNode<>(null, t));
    TreeEntryNode<K, V> childNode = this.nodes.get(childKey);
    if (ObjectUtil.isNull(childNode)) {
      childNode = new TreeEntryNode<>(parentNode, childKey);
      consumer.accept(parentNode, childNode);
      this.nodes.put(childKey, childNode);
      return;
    } 
    if (ObjectUtil.equals(parentNode, childNode.getDeclaredParent())) {
      consumer.accept(parentNode, childNode);
      return;
    } 
    if (false == childNode.hasParent()) {
      parentNode.addChild(childNode);
    } else if (this.allowOverrideParent) {
      childNode.getDeclaredParent().removeDeclaredChild(childNode.getKey());
      parentNode.addChild(childNode);
    } else {
      throw new IllegalArgumentException(StrUtil.format("[{}] has been used as child of [{}], can not be overwrite as child of [{}]", new Object[] { childNode
              
              .getKey(), childNode.getDeclaredParent().getKey(), parentKey }));
    } 
    consumer.accept(parentNode, childNode);
  }
  
  public void unlinkNode(K parentKey, K childKey) {
    TreeEntryNode<K, V> childNode = this.nodes.get(childKey);
    if (ObjectUtil.isNull(childNode))
      return; 
    if (childNode.hasParent())
      childNode.getDeclaredParent().removeDeclaredChild(childNode.getKey()); 
  }
  
  public static class TreeEntryNode<K, V> implements TreeEntry<K, V> {
    private TreeEntryNode<K, V> root;
    
    private TreeEntryNode<K, V> parent;
    
    private int weight;
    
    private final Map<K, TreeEntryNode<K, V>> children;
    
    private final K key;
    
    private V value;
    
    public TreeEntryNode(TreeEntryNode<K, V> parent, K key) {
      this(parent, key, (V)null);
    }
    
    public TreeEntryNode(TreeEntryNode<K, V> parent, K key, V value) {
      this.parent = parent;
      this.key = key;
      this.value = value;
      this.children = new LinkedHashMap<>();
      if (ObjectUtil.isNull(parent)) {
        this.root = this;
        this.weight = 0;
      } else {
        parent.addChild(this);
        parent.weight++;
        this.root = parent.root;
      } 
    }
    
    public K getKey() {
      return this.key;
    }
    
    public int getWeight() {
      return this.weight;
    }
    
    public V getValue() {
      return this.value;
    }
    
    public V setValue(V value) {
      V oldVal = getValue();
      this.value = value;
      return oldVal;
    }
    
    TreeEntryNode<K, V> traverseParentNodes(boolean includeCurrent, Consumer<TreeEntryNode<K, V>> consumer, Predicate<TreeEntryNode<K, V>> breakTraverse) {
      breakTraverse = (Predicate<TreeEntryNode<K, V>>)ObjectUtil.defaultIfNull(breakTraverse, a -> ());
      TreeEntryNode<K, V> curr = includeCurrent ? this : this.parent;
      while (ObjectUtil.isNotNull(curr)) {
        consumer.accept(curr);
        if (breakTraverse.test(curr))
          break; 
        curr = curr.parent;
      } 
      return curr;
    }
    
    public boolean isRoot() {
      return (getRoot() == this);
    }
    
    public TreeEntryNode<K, V> getRoot() {
      if (ObjectUtil.isNotNull(this.root))
        return this.root; 
      this.root = traverseParentNodes(true, p -> {
          
          }p -> !p.hasParent());
      return this.root;
    }
    
    public TreeEntryNode<K, V> getDeclaredParent() {
      return this.parent;
    }
    
    public TreeEntryNode<K, V> getParent(K key) {
      return traverseParentNodes(false, p -> {
          
          }p -> p.equalsKey(key));
    }
    
    public void forEachChild(boolean includeSelf, Consumer<TreeEntry<K, V>> nodeConsumer) {
      traverseChildNodes(includeSelf, (index, child) -> nodeConsumer.accept(child), (BiPredicate<Integer, TreeEntryNode<K, V>>)null);
    }
    
    public boolean equalsKey(K key) {
      return ObjectUtil.equal(getKey(), key);
    }
    
    TreeEntryNode<K, V> traverseChildNodes(boolean includeCurrent, BiConsumer<Integer, TreeEntryNode<K, V>> consumer, BiPredicate<Integer, TreeEntryNode<K, V>> breakTraverse) {
      breakTraverse = (BiPredicate<Integer, TreeEntryNode<K, V>>)ObjectUtil.defaultIfNull(breakTraverse, (i, n) -> false);
      Deque<List<TreeEntryNode<K, V>>> keyNodeDeque = CollUtil.newLinkedList((Object[])new List[] { CollUtil.newArrayList((Object[])new TreeEntryNode[] { this }) });
      boolean needProcess = includeCurrent;
      int index = includeCurrent ? 0 : 1;
      TreeEntryNode<K, V> lastNode = null;
      while (!keyNodeDeque.isEmpty()) {
        List<TreeEntryNode<K, V>> curr = keyNodeDeque.removeFirst();
        List<TreeEntryNode<K, V>> next = new ArrayList<>();
        for (TreeEntryNode<K, V> node : curr) {
          if (needProcess) {
            consumer.accept(Integer.valueOf(index), node);
            if (breakTraverse.test(Integer.valueOf(index), node))
              return node; 
          } else {
            needProcess = true;
          } 
          CollUtil.addAll(next, node.children.values());
        } 
        if (!next.isEmpty())
          keyNodeDeque.addLast(next); 
        lastNode = (TreeEntryNode<K, V>)CollUtil.getLast(next);
        index++;
      } 
      return lastNode;
    }
    
    void addChild(TreeEntryNode<K, V> child) {
      if (containsChild(child.key))
        return; 
      traverseParentNodes(true, s -> Assert.notEquals(s.key, child.key, "circular reference between [{}] and [{}]!", new Object[] { s.key, this.key }), (Predicate<TreeEntryNode<K, V>>)null);
      child.parent = this;
      child.traverseChildNodes(true, (i, c) -> {
            c.root = getRoot();
            c.weight = i.intValue() + getWeight() + 1;
          }(BiPredicate<Integer, TreeEntryNode<K, V>>)null);
      this.children.put(child.key, child);
    }
    
    void removeDeclaredChild(K key) {
      TreeEntryNode<K, V> child = this.children.get(key);
      if (ObjectUtil.isNull(child))
        return; 
      this.children.remove(key);
      child.parent = null;
      child.traverseChildNodes(true, (i, c) -> {
            c.root = child;
            c.weight = i.intValue();
          }(BiPredicate<Integer, TreeEntryNode<K, V>>)null);
    }
    
    public TreeEntryNode<K, V> getChild(K key) {
      return traverseChildNodes(false, (i, c) -> {
          
          }(i, c) -> c.equalsKey(key));
    }
    
    public Map<K, TreeEntry<K, V>> getDeclaredChildren() {
      return new LinkedHashMap<>((Map)this.children);
    }
    
    public Map<K, TreeEntry<K, V>> getChildren() {
      Map<K, TreeEntry<K, V>> childrenMap = new LinkedHashMap<>();
      traverseChildNodes(false, (i, c) -> (TreeEntry)childrenMap.put(c.getKey(), c), (BiPredicate<Integer, TreeEntryNode<K, V>>)null);
      return childrenMap;
    }
    
    void clear() {
      this.root = null;
      this.children.clear();
      this.parent = null;
    }
    
    public boolean equals(Object o) {
      if (this == o)
        return true; 
      if (o == null || getClass().equals(o.getClass()) || ClassUtil.isAssignable(getClass(), o.getClass()))
        return false; 
      TreeEntry<?, ?> treeEntry = (TreeEntry<?, ?>)o;
      return ObjectUtil.equals(getKey(), treeEntry.getKey());
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { getKey() });
    }
    
    TreeEntryNode<K, V> copy(V value) {
      TreeEntryNode<K, V> copiedNode = new TreeEntryNode(this.parent, this.key, (V)ObjectUtil.defaultIfNull(value, this.value));
      copiedNode.children.putAll(this.children);
      return copiedNode;
    }
  }
  
  public static class EntryNodeWrapper<K, V, N extends TreeEntry<K, V>> implements Map.Entry<K, TreeEntry<K, V>> {
    private final N entryNode;
    
    EntryNodeWrapper(N entryNode) {
      this.entryNode = entryNode;
    }
    
    public K getKey() {
      return (K)this.entryNode.getKey();
    }
    
    public TreeEntry<K, V> getValue() {
      return (TreeEntry<K, V>)this.entryNode;
    }
    
    public TreeEntry<K, V> setValue(TreeEntry<K, V> value) {
      throw new UnsupportedOperationException();
    }
  }
}
