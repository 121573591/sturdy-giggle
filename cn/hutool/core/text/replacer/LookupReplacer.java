package cn.hutool.core.text.replacer;

import cn.hutool.core.text.StrBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LookupReplacer extends StrReplacer {
  private static final long serialVersionUID = 1L;
  
  private final Map<String, String> lookupMap = new HashMap<>();
  
  private final Set<Character> prefixSet = new HashSet<>();
  
  private final int minLength;
  
  private final int maxLength;
  
  public LookupReplacer(String[]... lookup) {
    int minLength = Integer.MAX_VALUE;
    int maxLength = 0;
    for (String[] pair : lookup) {
      String key = pair[0];
      this.lookupMap.put(key, pair[1]);
      this.prefixSet.add(Character.valueOf(key.charAt(0)));
      int keySize = key.length();
      if (keySize > maxLength)
        maxLength = keySize; 
      if (keySize < minLength)
        minLength = keySize; 
    } 
    this.maxLength = maxLength;
    this.minLength = minLength;
  }
  
  protected int replace(CharSequence str, int pos, StrBuilder out) {
    if (this.prefixSet.contains(Character.valueOf(str.charAt(pos)))) {
      int max = this.maxLength;
      if (pos + this.maxLength > str.length())
        max = str.length() - pos; 
      for (int i = max; i >= this.minLength; i--) {
        CharSequence subSeq = str.subSequence(pos, pos + i);
        String result = this.lookupMap.get(subSeq.toString());
        if (null != result) {
          out.append(result);
          return i;
        } 
      } 
    } 
    return 0;
  }
}
