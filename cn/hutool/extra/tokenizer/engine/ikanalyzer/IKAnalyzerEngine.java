package cn.hutool.extra.tokenizer.engine.ikanalyzer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import org.wltea.analyzer.core.IKSegmenter;

public class IKAnalyzerEngine implements TokenizerEngine {
  public IKAnalyzerEngine() {}
  
  @Deprecated
  public IKAnalyzerEngine(IKSegmenter seg) {}
  
  public Result parse(CharSequence text) {
    IKSegmenter copySeg = new IKSegmenter(null, true);
    copySeg.reset(StrUtil.getReader(text));
    return (Result)new IKAnalyzerResult(copySeg);
  }
}
