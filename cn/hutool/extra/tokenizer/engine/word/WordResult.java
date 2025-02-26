package cn.hutool.extra.tokenizer.engine.word;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.Word;
import java.util.Iterator;
import java.util.List;
import org.apdplat.word.segmentation.Word;

public class WordResult implements Result {
  private final Iterator<Word> wordIter;
  
  public WordResult(List<Word> result) {
    this.wordIter = result.iterator();
  }
  
  public boolean hasNext() {
    return this.wordIter.hasNext();
  }
  
  public Word next() {
    return new WordWord(this.wordIter.next());
  }
  
  public void remove() {
    this.wordIter.remove();
  }
}
