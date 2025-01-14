package cn.hutool.core.lang.tree;

import cn.hutool.core.comparator.CompareUtil;
import java.io.Serializable;

public interface Node<T> extends Comparable<Node<T>>, Serializable {
  default int compareTo(Node node) {
    if (null == node)
      return 1; 
    Comparable<?> weight = getWeight();
    Comparable<?> weightOther = node.getWeight();
    return CompareUtil.compare(weight, weightOther);
  }
  
  Node<T> setWeight(Comparable<?> paramComparable);
  
  Comparable<?> getWeight();
  
  Node<T> setName(CharSequence paramCharSequence);
  
  CharSequence getName();
  
  Node<T> setParentId(T paramT);
  
  T getParentId();
  
  Node<T> setId(T paramT);
  
  T getId();
}
