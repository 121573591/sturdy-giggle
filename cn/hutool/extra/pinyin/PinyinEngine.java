package cn.hutool.extra.pinyin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import java.util.List;

public interface PinyinEngine {
  String getPinyin(char paramChar);
  
  String getPinyin(String paramString1, String paramString2);
  
  default char getFirstLetter(char c) {
    return getPinyin(c).charAt(0);
  }
  
  default String getFirstLetter(String str, String separator) {
    String splitSeparator = StrUtil.isEmpty(separator) ? "#" : separator;
    List<String> split = StrUtil.split(getPinyin(str, splitSeparator), splitSeparator);
    return CollUtil.join(split, separator, s -> String.valueOf((s.length() > 0) ? Character.valueOf(s.charAt(0)) : ""));
  }
}
