package cn.hutool.core.lang.tree.parser;

import cn.hutool.core.lang.tree.Tree;

@FunctionalInterface
public interface NodeParser<T, E> {
  void parse(T paramT, Tree<E> paramTree);
}
