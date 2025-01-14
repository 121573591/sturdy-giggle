package cn.hutool.extra.tokenizer;

import cn.hutool.core.collection.ComputeIter;

public abstract class AbstractResult extends ComputeIter<Word> implements Result {
  protected Word computeNext() {
    return nextWord();
  }
  
  protected abstract Word nextWord();
}
