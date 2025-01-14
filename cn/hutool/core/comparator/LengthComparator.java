package cn.hutool.core.comparator;

import java.util.Comparator;

public class LengthComparator implements Comparator<CharSequence> {
  public static final LengthComparator INSTANCE = new LengthComparator();
  
  public int compare(CharSequence o1, CharSequence o2) {
    int result = Integer.compare(o1.length(), o2.length());
    if (0 == result)
      result = CompareUtil.compare(o1.toString(), o2.toString()); 
    return result;
  }
}
