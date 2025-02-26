package cn.hutool.extra.tokenizer.engine.analysis;

import cn.hutool.extra.tokenizer.Word;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Attribute;

public class AnalysisWord implements Word {
  private static final long serialVersionUID = 1L;
  
  private final Attribute word;
  
  public AnalysisWord(CharTermAttribute word) {
    this.word = (Attribute)word;
  }
  
  public String getText() {
    return this.word.toString();
  }
  
  public int getStartOffset() {
    if (this.word instanceof OffsetAttribute)
      return ((OffsetAttribute)this.word).startOffset(); 
    return -1;
  }
  
  public int getEndOffset() {
    if (this.word instanceof OffsetAttribute)
      return ((OffsetAttribute)this.word).endOffset(); 
    return -1;
  }
  
  public String toString() {
    return getText();
  }
}
