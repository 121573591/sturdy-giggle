package cn.hutool.extra.pinyin.engine.tinypinyin;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinEngine;
import com.github.promeg.pinyinhelper.Pinyin;

public class TinyPinyinEngine implements PinyinEngine {
  public TinyPinyinEngine() {
    this(null);
  }
  
  public TinyPinyinEngine(Pinyin.Config config) {
    Pinyin.init(config);
  }
  
  public String getPinyin(char c) {
    if (false == Pinyin.isChinese(c))
      return String.valueOf(c); 
    return Pinyin.toPinyin(c).toLowerCase();
  }
  
  public String getPinyin(String str, String separator) {
    String pinyin = Pinyin.toPinyin(str, separator);
    return StrUtil.isEmpty(pinyin) ? pinyin : pinyin.toLowerCase();
  }
}
