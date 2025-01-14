package cn.hutool.extra.tokenizer.engine.ansj;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.Word;
import java.util.Iterator;
import org.ansj.domain.Result;
import org.ansj.domain.Term;

public class AnsjResult implements Result {
  private final Iterator<Term> result;
  
  public AnsjResult(Result ansjResult) {
    this.result = ansjResult.iterator();
  }
  
  public boolean hasNext() {
    return this.result.hasNext();
  }
  
  public Word next() {
    return new AnsjWord(this.result.next());
  }
  
  public void remove() {
    this.result.remove();
  }
  
  public Iterator<Word> iterator() {
    return (Iterator<Word>)this;
  }
}
