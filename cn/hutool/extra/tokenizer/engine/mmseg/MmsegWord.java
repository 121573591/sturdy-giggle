package cn.hutool.extra.tokenizer.engine.mmseg;

import cn.hutool.extra.tokenizer.Word;
import com.chenlb.mmseg4j.Word;

public class MmsegWord implements Word {
  private static final long serialVersionUID = 1L;
  
  private final Word word;
  
  public MmsegWord(Word word) {
    this.word = word;
  }
  
  public String getText() {
    return this.word.getString();
  }
  
  public int getStartOffset() {
    return this.word.getStartOffset();
  }
  
  public int getEndOffset() {
    return this.word.getEndOffset();
  }
  
  public String toString() {
    return getText();
  }
}
