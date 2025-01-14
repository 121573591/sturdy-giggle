package cn.hutool.core.lang.tree;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.lang.tree.parser.NodeParser;
import cn.hutool.core.util.ObjectUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtil {
  public static Tree<Integer> buildSingle(List<TreeNode<Integer>> list) {
    return buildSingle(list, Integer.valueOf(0));
  }
  
  public static List<Tree<Integer>> build(List<TreeNode<Integer>> list) {
    return build(list, Integer.valueOf(0));
  }
  
  public static <E> Tree<E> buildSingle(List<TreeNode<E>> list, E parentId) {
    return buildSingle(list, parentId, TreeNodeConfig.DEFAULT_CONFIG, (NodeParser<TreeNode<E>, E>)new DefaultNodeParser());
  }
  
  public static <E> List<Tree<E>> build(List<TreeNode<E>> list, E parentId) {
    return build(list, parentId, TreeNodeConfig.DEFAULT_CONFIG, (NodeParser<TreeNode<E>, E>)new DefaultNodeParser());
  }
  
  public static <T, E> Tree<E> buildSingle(List<T> list, E parentId, NodeParser<T, E> nodeParser) {
    return buildSingle(list, parentId, TreeNodeConfig.DEFAULT_CONFIG, nodeParser);
  }
  
  public static <T, E> List<Tree<E>> build(List<T> list, E parentId, NodeParser<T, E> nodeParser) {
    return build(list, parentId, TreeNodeConfig.DEFAULT_CONFIG, nodeParser);
  }
  
  public static <T, E> List<Tree<E>> build(List<T> list, E rootId, TreeNodeConfig treeNodeConfig, NodeParser<T, E> nodeParser) {
    return buildSingle(list, rootId, treeNodeConfig, nodeParser).getChildren();
  }
  
  public static <T, E> Tree<E> buildSingle(List<T> list, E rootId, TreeNodeConfig treeNodeConfig, NodeParser<T, E> nodeParser) {
    return TreeBuilder.<E>of(rootId, treeNodeConfig)
      .<T>append(list, rootId, nodeParser).build();
  }
  
  public static <E> List<Tree<E>> build(Map<E, Tree<E>> map, E rootId) {
    return buildSingle(map, rootId).getChildren();
  }
  
  public static <E> Tree<E> buildSingle(Map<E, Tree<E>> map, E rootId) {
    Tree<E> tree = (Tree<E>)IterUtil.getFirstNoneNull(map.values());
    if (null != tree) {
      TreeNodeConfig config = tree.getConfig();
      return TreeBuilder.<E>of(rootId, config)
        .append(map)
        .build();
    } 
    return createEmptyNode(rootId);
  }
  
  public static <T> Tree<T> getNode(Tree<T> node, T id) {
    if (ObjectUtil.equal(id, node.getId()))
      return node; 
    List<Tree<T>> children = node.getChildren();
    if (null == children)
      return null; 
    for (Tree<T> child : children) {
      Tree<T> childNode = child.getNode(id);
      if (null != childNode)
        return childNode; 
    } 
    return null;
  }
  
  public static <T> List<CharSequence> getParentsName(Tree<T> node, boolean includeCurrentNode) {
    List<CharSequence> result = new ArrayList<>();
    if (null == node)
      return result; 
    if (includeCurrentNode)
      result.add(node.getName()); 
    Tree<T> parent = node.getParent();
    while (null != parent) {
      CharSequence name = parent.getName();
      parent = parent.getParent();
      if (null != name || null != parent)
        result.add(name); 
    } 
    return result;
  }
  
  public static <T> List<T> getParentsId(Tree<T> node, boolean includeCurrentNode) {
    List<T> result = new ArrayList<>();
    if (null == node)
      return result; 
    if (includeCurrentNode)
      result.add(node.getId()); 
    Tree<T> parent = node.getParent();
    while (null != parent) {
      T id = parent.getId();
      parent = parent.getParent();
      if (null != id || null != parent)
        result.add(id); 
    } 
    return result;
  }
  
  public static <E> Tree<E> createEmptyNode(E id) {
    return (new Tree<>()).setId(id);
  }
}
