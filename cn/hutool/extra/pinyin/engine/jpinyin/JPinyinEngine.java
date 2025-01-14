package cn.hutool.extra.pinyin.engine.jpinyin;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.extra.pinyin.PinyinEngine;
import cn.hutool.extra.pinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class JPinyinEngine implements PinyinEngine {
  PinyinFormat format;
  
  public JPinyinEngine() {
    this(null);
  }
  
  public JPinyinEngine(PinyinFormat format) {
    init(format);
  }
  
  public void init(PinyinFormat format) {
    if (null == format)
      format = PinyinFormat.WITHOUT_TONE; 
    this.format = format;
  }
  
  public String getPinyin(char c) {
    String[] results = PinyinHelper.convertToPinyinArray(c, this.format);
    return ArrayUtil.isEmpty((Object[])results) ? String.valueOf(c) : results[0];
  }
  
  public String getPinyin(String str, String separator) {
    try {
      return PinyinHelper.convertToPinyinString(str, separator, this.format);
    } catch (PinyinException e) {
      throw new PinyinException(e);
    } 
  }
}
