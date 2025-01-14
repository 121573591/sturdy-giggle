package cn.hutool.core.lang.tree;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

public class Tree<T> extends LinkedHashMap<String, Object> implements Node<T> {
  private static final long serialVersionUID = 1L;
  
  private final TreeNodeConfig treeNodeConfig;
  
  private Tree<T> parent;
  
  public Tree() {
    this((TreeNodeConfig)null);
  }
  
  public Tree(TreeNodeConfig treeNodeConfig) {
    this.treeNodeConfig = (TreeNodeConfig)ObjectUtil.defaultIfNull(treeNodeConfig, TreeNodeConfig.DEFAULT_CONFIG);
  }
  
  public TreeNodeConfig getConfig() {
    return this.treeNodeConfig;
  }
  
  public Tree<T> getParent() {
    return this.parent;
  }
  
  public Tree<T> getNode(T id) {
    return TreeUtil.getNode(this, id);
  }
  
  public List<CharSequence> getParentsName(T id, boolean includeCurrentNode) {
    return TreeUtil.getParentsName(getNode(id), includeCurrentNode);
  }
  
  public List<CharSequence> getParentsName(boolean includeCurrentNode) {
    return TreeUtil.getParentsName(this, includeCurrentNode);
  }
  
  public Tree<T> setParent(Tree<T> parent) {
    this.parent = parent;
    if (null != parent)
      setParentId(parent.getId()); 
    return this;
  }
  
  public T getId() {
    return (T)get(this.treeNodeConfig.getIdKey());
  }
  
  public Tree<T> setId(T id) {
    put(this.treeNodeConfig.getIdKey(), id);
    return this;
  }
  
  public T getParentId() {
    return (T)get(this.treeNodeConfig.getParentIdKey());
  }
  
  public Tree<T> setParentId(T parentId) {
    put(this.treeNodeConfig.getParentIdKey(), parentId);
    return this;
  }
  
  public CharSequence getName() {
    return (CharSequence)get(this.treeNodeConfig.getNameKey());
  }
  
  public Tree<T> setName(CharSequence name) {
    put(this.treeNodeConfig.getNameKey(), name);
    return this;
  }
  
  public Comparable<?> getWeight() {
    return (Comparable)get(this.treeNodeConfig.getWeightKey());
  }
  
  public Tree<T> setWeight(Comparable<?> weight) {
    put(this.treeNodeConfig.getWeightKey(), weight);
    return this;
  }
  
  public List<Tree<T>> getChildren() {
    return (List<Tree<T>>)get(this.treeNodeConfig.getChildrenKey());
  }
  
  public boolean hasChild() {
    return CollUtil.isNotEmpty(getChildren());
  }
  
  public void walk(Consumer<Tree<T>> consumer) {
    consumer.accept(this);
    List<Tree<T>> children = getChildren();
    if (CollUtil.isNotEmpty(children))
      children.forEach(tree -> tree.walk(consumer)); 
  }
  
  public Tree<T> filterNew(Filter<Tree<T>> filter) {
    return cloneTree().filter(filter);
  }
  
  public Tree<T> filter(Filter<Tree<T>> filter) {
    if (filter.accept(this))
      return this; 
    List<Tree<T>> children = getChildren();
    if (CollUtil.isNotEmpty(children)) {
      List<Tree<T>> filteredChildren = new ArrayList<>(children.size());
      for (Tree<T> child : children) {
        Tree<T> filteredChild = child.filter(filter);
        if (null != filteredChild)
          filteredChildren.add(filteredChild); 
      } 
      if (CollUtil.isNotEmpty(filteredChildren))
        return setChildren(filteredChildren); 
      setChildren((List<Tree<T>>)null);
    } 
    return null;
  }
  
  public Tree<T> setChildren(List<Tree<T>> children) {
    if (null == children)
      remove(this.treeNodeConfig.getChildrenKey()); 
    put(this.treeNodeConfig.getChildrenKey(), children);
    return this;
  }
  
  @SafeVarargs
  public final Tree<T> addChildren(Tree<T>... children) {
    if (ArrayUtil.isNotEmpty((Object[])children)) {
      List<Tree<T>> childrenList = getChildren();
      if (null == childrenList) {
        childrenList = new ArrayList<>();
        setChildren(childrenList);
      } 
      for (Tree<T> child : children) {
        child.setParent(this);
        childrenList.add(child);
      } 
    } 
    return this;
  }
  
  public void putExtra(String key, Object value) {
    Assert.notEmpty(key, "Key must be not empty !", new Object[0]);
    put(key, value);
  }
  
  public String toString() {
    StringWriter stringWriter = new StringWriter();
    printTree(this, new PrintWriter(stringWriter), 0);
    return stringWriter.toString();
  }
  
  public Tree<T> cloneTree() {
    Tree<T> result = (Tree<T>)ObjectUtil.clone(this);
    result.setChildren(cloneChildren());
    return result;
  }
  
  private List<Tree<T>> cloneChildren() {
    List<Tree<T>> children = getChildren();
    if (null == children)
      return null; 
    List<Tree<T>> newChildren = new ArrayList<>(children.size());
    children.forEach(t -> newChildren.add(t.cloneTree()));
    return newChildren;
  }
  
  private static void printTree(Tree<?> tree, PrintWriter writer, int intent) {
    writer.println(StrUtil.format("{}{}[{}]", new Object[] { StrUtil.repeat(' ', intent), tree.getName(), tree.getId() }));
    writer.flush();
    List<? extends Tree<?>> children = tree.getChildren();
    if (CollUtil.isNotEmpty(children))
      for (Tree<?> child : children)
        printTree(child, writer, intent + 2);  
  }
}
