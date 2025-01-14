package cn.hutool.core.lang.tree;

import java.util.Map;
import java.util.Objects;

public class TreeNode<T> implements Node<T> {
  private static final long serialVersionUID = 1L;
  
  private T id;
  
  private T parentId;
  
  private CharSequence name;
  
  private Comparable<?> weight = Integer.valueOf(0);
  
  private Map<String, Object> extra;
  
  public TreeNode(T id, T parentId, String name, Comparable<?> weight) {
    this.id = id;
    this.parentId = parentId;
    this.name = name;
    if (weight != null)
      this.weight = weight; 
  }
  
  public T getId() {
    return this.id;
  }
  
  public TreeNode<T> setId(T id) {
    this.id = id;
    return this;
  }
  
  public T getParentId() {
    return this.parentId;
  }
  
  public TreeNode<T> setParentId(T parentId) {
    this.parentId = parentId;
    return this;
  }
  
  public CharSequence getName() {
    return this.name;
  }
  
  public TreeNode<T> setName(CharSequence name) {
    this.name = name;
    return this;
  }
  
  public Comparable<?> getWeight() {
    return this.weight;
  }
  
  public TreeNode<T> setWeight(Comparable<?> weight) {
    this.weight = weight;
    return this;
  }
  
  public Map<String, Object> getExtra() {
    return this.extra;
  }
  
  public TreeNode<T> setExtra(Map<String, Object> extra) {
    this.extra = extra;
    return this;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o == null || getClass() != o.getClass())
      return false; 
    TreeNode<?> treeNode = (TreeNode)o;
    return Objects.equals(this.id, treeNode.id);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public TreeNode() {}
}
