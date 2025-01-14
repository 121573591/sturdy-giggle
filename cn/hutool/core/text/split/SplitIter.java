package cn.hutool.core.text.split;

import cn.hutool.core.collection.ComputeIter;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.finder.TextFinder;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SplitIter extends ComputeIter<String> implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final String text;
  
  private final TextFinder finder;
  
  private final int limit;
  
  private final boolean ignoreEmpty;
  
  private int offset;
  
  private int count;
  
  public SplitIter(CharSequence text, TextFinder separatorFinder, int limit, boolean ignoreEmpty) {
    Assert.notNull(text, "Text must be not null!", new Object[0]);
    this.text = text.toString();
    this.finder = separatorFinder.setText(text);
    this.limit = (limit > 0) ? limit : Integer.MAX_VALUE;
    this.ignoreEmpty = ignoreEmpty;
  }
  
  protected String computeNext() {
    if (this.count >= this.limit || this.offset > this.text.length())
      return null; 
    if (this.count == this.limit - 1) {
      if (this.ignoreEmpty && this.offset == this.text.length())
        return null; 
      this.count++;
      return this.text.substring(this.offset);
    } 
    int start = this.finder.start(this.offset);
    if (start < 0) {
      if (this.offset <= this.text.length()) {
        String str = this.text.substring(this.offset);
        if (false == this.ignoreEmpty || false == str.isEmpty()) {
          this.offset = Integer.MAX_VALUE;
          return str;
        } 
      } 
      return null;
    } 
    String result = this.text.substring(this.offset, start);
    this.offset = this.finder.end(start);
    if (this.ignoreEmpty && result.isEmpty())
      return computeNext(); 
    this.count++;
    return result;
  }
  
  public void reset() {
    this.finder.reset();
    this.offset = 0;
    this.count = 0;
  }
  
  public String[] toArray(boolean trim) {
    return toList(trim).<String>toArray(new String[0]);
  }
  
  public List<String> toList(boolean trim) {
    return toList(str -> trim ? StrUtil.trim(str) : str);
  }
  
  public <T> List<T> toList(Function<String, T> mapping) {
    List<T> result = new ArrayList<>();
    while (hasNext()) {
      T apply = mapping.apply(next());
      if (this.ignoreEmpty && StrUtil.isEmptyIfStr(apply))
        continue; 
      result.add(apply);
    } 
    if (result.isEmpty())
      return new ArrayList<>(0); 
    return result;
  }
}
