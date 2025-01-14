package cn.hutool.core.text.replacer;

import cn.hutool.core.lang.Replacer;
import cn.hutool.core.text.StrBuilder;
import java.io.Serializable;

public abstract class StrReplacer implements Replacer<CharSequence>, Serializable {
  private static final long serialVersionUID = 1L;
  
  public CharSequence replace(CharSequence t) {
    int len = t.length();
    StrBuilder builder = StrBuilder.create(len);
    int pos = 0;
    while (pos < len) {
      int consumed = replace(t, pos, builder);
      if (0 == consumed) {
        builder.append(t.charAt(pos));
        pos++;
      } 
      pos += consumed;
    } 
    return (CharSequence)builder;
  }
  
  protected abstract int replace(CharSequence paramCharSequence, int paramInt, StrBuilder paramStrBuilder);
}
