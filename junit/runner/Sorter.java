package junit.runner;

import java.util.Vector;

public class Sorter {
  public static void sortStrings(Vector values, int left, int right, Swapper swapper) {
    int oleft = left;
    int oright = right;
    String mid = values.elementAt((left + right) / 2);
    do {
      while (((String)values.elementAt(left)).compareTo(mid) < 0)
        left++; 
      while (mid.compareTo(values.elementAt(right)) < 0)
        right--; 
      if (left > right)
        continue; 
      swapper.swap(values, left, right);
      left++;
      right--;
    } while (left <= right);
    if (oleft < right)
      sortStrings(values, oleft, right, swapper); 
    if (left < oright)
      sortStrings(values, left, oright, swapper); 
  }
  
  public static interface Swapper {
    void swap(Vector param1Vector, int param1Int1, int param1Int2);
  }
}
