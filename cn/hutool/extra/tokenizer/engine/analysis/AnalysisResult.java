package cn.hutool.extra.tokenizer.engine.analysis;

import cn.hutool.extra.tokenizer.AbstractResult;
import cn.hutool.extra.tokenizer.TokenizerException;
import cn.hutool.extra.tokenizer.Word;
import java.io.IOException;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class AnalysisResult extends AbstractResult {
  private final TokenStream stream;
  
  public AnalysisResult(TokenStream stream) {
    this.stream = stream;
  }
  
  protected Word nextWord() {
    try {
      if (this.stream.incrementToken())
        return new AnalysisWord((CharTermAttribute)this.stream.getAttribute(CharTermAttribute.class)); 
    } catch (IOException e) {
      throw new TokenizerException(e);
    } 
    return null;
  }
}
