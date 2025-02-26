package cn.hutool.extra.tokenizer.engine.ikanalyzer;

import cn.hutool.extra.tokenizer.AbstractResult;
import cn.hutool.extra.tokenizer.TokenizerException;
import cn.hutool.extra.tokenizer.Word;
import java.io.IOException;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class IKAnalyzerResult extends AbstractResult {
  private final IKSegmenter seg;
  
  public IKAnalyzerResult(IKSegmenter seg) {
    this.seg = seg;
  }
  
  protected Word nextWord() {
    Lexeme next;
    try {
      next = this.seg.next();
    } catch (IOException e) {
      throw new TokenizerException(e);
    } 
    if (null == next)
      return null; 
    return new IKAnalyzerWord(next);
  }
}
