package org.openjdk.nashorn.internal.runtime.regexp.joni;

public abstract class SearchAlgorithm {
  public static final SearchAlgorithm NONE = new SearchAlgorithm() {
      public final String getName() {
        return "NONE";
      }
      
      public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
        return textP;
      }
      
      public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
        return textP;
      }
    };
  
  public static final SearchAlgorithm SLOW = new SearchAlgorithm() {
      public final String getName() {
        return "EXACT";
      }
      
      public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
        char[] target = regex.exact;
        int targetP = regex.exactP;
        int targetEnd = regex.exactEnd;
        int end = textEnd;
        end -= targetEnd - targetP - 1;
        if (end > textRange)
          end = textRange; 
        int s = textP;
        while (s < end) {
          if (text[s] == target[targetP]) {
            int p = s + 1;
            int t = targetP + 1;
            while (t < targetEnd && 
              target[t] == text[p++])
              t++; 
            if (t == targetEnd)
              return s; 
          } 
          s++;
        } 
        return -1;
      }
      
      public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
        char[] target = regex.exact;
        int targetP = regex.exactP;
        int targetEnd = regex.exactEnd;
        int s = textEnd;
        s -= targetEnd - targetP;
        if (s > textStart)
          s = textStart; 
        while (s >= textP) {
          if (text[s] == target[targetP]) {
            int p = s + 1;
            int t = targetP + 1;
            while (t < targetEnd && 
              target[t] == text[p++])
              t++; 
            if (t == targetEnd)
              return s; 
          } 
          s--;
        } 
        return -1;
      }
    };
  
  public static final class SLOW_IC extends SearchAlgorithm {
    public SLOW_IC(Regex regex) {}
    
    public final String getName() {
      return "EXACT_IC";
    }
    
    public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
      char[] target = regex.exact;
      int targetP = regex.exactP;
      int targetEnd = regex.exactEnd;
      int end = textEnd;
      end -= targetEnd - targetP - 1;
      if (end > textRange)
        end = textRange; 
      int s = textP;
      while (s < end) {
        if (lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd))
          return s; 
        s++;
      } 
      return -1;
    }
    
    public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
      char[] target = regex.exact;
      int targetP = regex.exactP;
      int targetEnd = regex.exactEnd;
      int s = textEnd;
      s -= targetEnd - targetP;
      if (s > textStart)
        s = textStart; 
      while (s >= textP) {
        if (lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd))
          return s; 
        s = EncodingHelper.prevCharHead(adjustText, s);
      } 
      return -1;
    }
    
    private static boolean lowerCaseMatch(char[] t, int tPp, int tEnd, char[] chars, int pp, int end) {
      for (int tP = tPp, p = pp; tP < tEnd;) {
        if (t[tP++] != EncodingHelper.toLowerCase(chars[p++]))
          return false; 
      } 
      return true;
    }
  }
  
  public static final SearchAlgorithm BM = new SearchAlgorithm() {
      private static final int BM_BACKWARD_SEARCH_LENGTH_THRESHOLD = 100;
      
      public final String getName() {
        return "EXACT_BM";
      }
      
      public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
        char[] target = regex.exact;
        int targetP = regex.exactP;
        int targetEnd = regex.exactEnd;
        int end = textRange + targetEnd - targetP - 1;
        if (end > textEnd)
          end = textEnd; 
        int tail = targetEnd - 1;
        int s = textP + targetEnd - targetP - 1;
        if (regex.intMap == null) {
          while (s < end) {
            int p = s;
            int t = tail;
            while (text[p] == target[t]) {
              if (t == targetP)
                return p; 
              p--;
              t--;
            } 
            s += regex.map[text[s] & 0xFF];
          } 
        } else {
          while (s < end) {
            int p = s;
            int t = tail;
            while (text[p] == target[t]) {
              if (t == targetP)
                return p; 
              p--;
              t--;
            } 
            s += regex.intMap[text[s] & 0xFF];
          } 
        } 
        return -1;
      }
      
      public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
        char[] target = regex.exact;
        int targetP = regex.exactP;
        int targetEnd = regex.exactEnd;
        if (regex.intMapBackward == null) {
          if (s_ - range_ < 100)
            return SLOW.searchBackward(regex, text, textP, adjustText, textEnd, textStart, s_, range_); 
          setBmBackwardSkip(regex, target, targetP, targetEnd);
        } 
        int s = textEnd - targetEnd - targetP;
        if (textStart < s)
          s = textStart; 
        while (s >= textP) {
          int p = s;
          int t = targetP;
          while (t < targetEnd && text[p] == target[t]) {
            p++;
            t++;
          } 
          if (t == targetEnd)
            return s; 
          s -= regex.intMapBackward[text[s] & 0xFF];
        } 
        return -1;
      }
      
      private void setBmBackwardSkip(Regex regex, char[] chars, int p, int end) {
        int[] skip;
        if (regex.intMapBackward == null) {
          skip = new int[256];
          regex.intMapBackward = skip;
        } else {
          skip = regex.intMapBackward;
        } 
        int len = end - p;
        int i;
        for (i = 0; i < 256; i++)
          skip[i] = len; 
        for (i = len - 1; i > 0; i--)
          skip[chars[i] & 0xFF] = i; 
      }
    };
  
  public static final SearchAlgorithm MAP = new SearchAlgorithm() {
      public final String getName() {
        return "MAP";
      }
      
      public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
        byte[] map = regex.map;
        int s = textP;
        while (s < textRange) {
          if (text[s] > 'ÿ' || map[text[s]] != 0)
            return s; 
          s++;
        } 
        return -1;
      }
      
      public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
        byte[] map = regex.map;
        int s = textStart;
        if (s >= textEnd)
          s = textEnd - 1; 
        while (s >= textP) {
          if (text[s] > 'ÿ' || map[text[s]] != 0)
            return s; 
          s--;
        } 
        return -1;
      }
    };
  
  public abstract String getName();
  
  public abstract int search(Regex paramRegex, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract int searchBackward(Regex paramRegex, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
}
