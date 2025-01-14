package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;

public abstract class Matcher extends IntHolder {
  protected final Regex regex;
  
  protected final char[] chars;
  
  protected final int str;
  
  protected final int end;
  
  protected int msaStart;
  
  protected int msaOptions;
  
  protected final Region msaRegion;
  
  protected int msaBestLen;
  
  protected int msaBestS;
  
  protected int msaBegin;
  
  protected int msaEnd;
  
  int low;
  
  int high;
  
  public Matcher(Regex regex, char[] chars) {
    this(regex, chars, 0, chars.length);
  }
  
  public Matcher(Regex regex, char[] chars, int p, int end) {
    this.regex = regex;
    this.chars = chars;
    this.str = p;
    this.end = end;
    this.msaRegion = (regex.numMem == 0) ? null : new Region(regex.numMem + 1);
  }
  
  protected abstract int matchAt(int paramInt1, int paramInt2, int paramInt3);
  
  public final Region getRegion() {
    return this.msaRegion;
  }
  
  public final int getBegin() {
    return this.msaBegin;
  }
  
  public final int getEnd() {
    return this.msaEnd;
  }
  
  protected final void msaInit(int option, int start) {
    this.msaOptions = option;
    this.msaStart = start;
    this.msaBestLen = -1;
  }
  
  public final int match(int at, int range, int option) {
    msaInit(option, at);
    int prev = EncodingHelper.prevCharHead(this.str, at);
    return matchAt(range, at, prev);
  }
  
  private boolean forwardSearchRange(char[] ch, int string, int e, int s, int range, IntHolder lowPrev) {
    int pprev = -1;
    int p = s;
    if (this.regex.dMin > 0)
      p += this.regex.dMin; 
    while (true) {
      p = this.regex.searchAlgorithm.search(this.regex, ch, p, e, range);
      if (p != -1 && p < range) {
        if (p - this.regex.dMin < s) {
          pprev = p;
          p++;
          continue;
        } 
        if (this.regex.subAnchor != 0)
          switch (this.regex.subAnchor) {
            case 2:
              if (p != string) {
                int prev = EncodingHelper.prevCharHead((pprev != -1) ? pprev : string, p);
                if (!EncodingHelper.isNewLine(ch, prev, e)) {
                  pprev = p;
                  p++;
                  continue;
                } 
              } 
              break;
            case 32:
              if (p == e)
                break; 
              if (!EncodingHelper.isNewLine(ch, p, e)) {
                pprev = p;
                p++;
                continue;
              } 
              break;
          }  
        if (this.regex.dMax == 0) {
          this.low = p;
          if (lowPrev != null)
            if (this.low > s) {
              lowPrev.value = EncodingHelper.prevCharHead(s, p);
            } else {
              lowPrev.value = EncodingHelper.prevCharHead((pprev != -1) ? pprev : string, p);
            }  
        } else if (this.regex.dMax != Integer.MAX_VALUE) {
          this.low = p - this.regex.dMax;
          if (this.low > s) {
            this.low = EncodingHelper.rightAdjustCharHeadWithPrev(this.low, lowPrev);
            if (lowPrev != null && lowPrev.value == -1)
              lowPrev.value = EncodingHelper.prevCharHead((pprev != -1) ? pprev : s, this.low); 
          } else if (lowPrev != null) {
            lowPrev.value = EncodingHelper.prevCharHead((pprev != -1) ? pprev : string, this.low);
          } 
        } 
        this.high = p - this.regex.dMin;
        return true;
      } 
      break;
    } 
    return false;
  }
  
  private boolean backwardSearchRange(char[] ch, int string, int e, int s, int range, int adjrange) {
    int r = range;
    r += this.regex.dMin;
    int p = s;
    while (true) {
      p = this.regex.searchAlgorithm.searchBackward(this.regex, ch, r, adjrange, e, p, s, r);
      if (p != -1) {
        if (this.regex.subAnchor != 0)
          switch (this.regex.subAnchor) {
            case 2:
              if (p != string) {
                int prev = EncodingHelper.prevCharHead(string, p);
                if (!EncodingHelper.isNewLine(ch, prev, e)) {
                  p = prev;
                  continue;
                } 
              } 
              break;
            case 32:
              if (p == e)
                break; 
              if (!EncodingHelper.isNewLine(ch, p, e)) {
                p = EncodingHelper.prevCharHead(adjrange, p);
                if (p == -1)
                  return false; 
                continue;
              } 
              break;
          }  
        if (this.regex.dMax != Integer.MAX_VALUE) {
          this.low = p - this.regex.dMax;
          this.high = p - this.regex.dMin;
        } 
        return true;
      } 
      break;
    } 
    return false;
  }
  
  private boolean matchCheck(int upperRange, int s, int prev) {
    if (matchAt(this.end, s, prev) != -1)
      if (!Option.isFindLongest(this.regex.options))
        return true;  
    return false;
  }
  
  public final int search(int startp, int rangep, int option) {
    int start = startp, range = rangep;
    int origStart = start;
    int origRange = range;
    if (start > this.end || start < this.str)
      return -1; 
    if (this.regex.anchor != 0 && this.str < this.end) {
      if ((this.regex.anchor & 0x4) != 0) {
        if (range > start) {
          range = start + 1;
        } else {
          range = start;
        } 
      } else if ((this.regex.anchor & 0x1) != 0) {
        if (range > start) {
          if (start != this.str)
            return -1; 
          range = this.str + 1;
        } else if (range <= this.str) {
          start = this.str;
          range = this.str;
        } else {
          return -1;
        } 
      } else if ((this.regex.anchor & 0x8) != 0) {
        int maxSemiEnd = this.end, minSemiEnd = maxSemiEnd;
        if (endBuf(start, range, minSemiEnd, maxSemiEnd))
          return -1; 
      } else if ((this.regex.anchor & 0x10) != 0) {
        int preEnd = EncodingHelper.stepBack(this.str, this.end, 1);
        int maxSemiEnd = this.end;
        if (EncodingHelper.isNewLine(this.chars, preEnd, this.end)) {
          int minSemiEnd = preEnd;
          if (minSemiEnd > this.str && start <= minSemiEnd)
            if (endBuf(start, range, minSemiEnd, maxSemiEnd))
              return -1;  
        } else {
          int minSemiEnd = this.end;
          if (endBuf(start, range, minSemiEnd, maxSemiEnd))
            return -1; 
        } 
      } else if ((this.regex.anchor & 0x8000) != 0) {
        if (range > start) {
          range = start + 1;
        } else {
          range = start;
        } 
      } 
    } else if (this.str == this.end) {
      if (this.regex.thresholdLength == 0) {
        int i = start = this.str;
        int prev = -1;
        msaInit(option, start);
        if (matchCheck(this.end, i, prev))
          return match(i); 
        return mismatch();
      } 
      return -1;
    } 
    msaInit(option, origStart);
    int s = start;
    if (range > start) {
      int i;
      if (s > this.str) {
        i = EncodingHelper.prevCharHead(this.str, s);
      } else {
        i = 0;
      } 
      if (this.regex.searchAlgorithm != SearchAlgorithm.NONE) {
        int schRange = range;
        if (this.regex.dMax != 0)
          if (this.regex.dMax == Integer.MAX_VALUE) {
            schRange = this.end;
          } else {
            schRange += this.regex.dMax;
            if (schRange > this.end)
              schRange = this.end; 
          }  
        if (this.end - start < this.regex.thresholdLength)
          return mismatch(); 
        if (this.regex.dMax != Integer.MAX_VALUE)
          do {
            if (!forwardSearchRange(this.chars, this.str, this.end, s, schRange, this))
              return mismatch(); 
            if (s < this.low) {
              s = this.low;
              i = this.value;
            } 
            while (s <= this.high) {
              if (matchCheck(origRange, s, i))
                return match(s); 
              i = s;
              s++;
            } 
          } while (s < range); 
        if (!forwardSearchRange(this.chars, this.str, this.end, s, schRange, null))
          return mismatch(); 
        if ((this.regex.anchor & 0x4000) != 0)
          while (true) {
            if (matchCheck(origRange, s, i))
              return match(s); 
            i = s;
            s++;
            if (s >= range)
              return mismatch(); 
          }  
      } 
      do {
        if (matchCheck(origRange, s, i))
          return match(s); 
        i = s;
        ++s;
      } while (s < range);
      if (s == range && 
        matchCheck(origRange, s, i))
        return match(s); 
    } else {
      if (this.regex.searchAlgorithm != SearchAlgorithm.NONE) {
        int adjrange;
        if (range < this.end) {
          adjrange = range;
        } else {
          adjrange = this.end;
        } 
        if (this.regex.dMax != Integer.MAX_VALUE && this.end - range >= this.regex.thresholdLength)
          while (true) {
            int i = s + this.regex.dMax;
            if (i > this.end)
              i = this.end; 
            if (!backwardSearchRange(this.chars, this.str, this.end, i, range, adjrange))
              return mismatch(); 
            if (s > this.high)
              s = this.high; 
            while (s != -1 && s >= this.low) {
              int prev = EncodingHelper.prevCharHead(this.str, s);
              if (matchCheck(origStart, s, prev))
                return match(s); 
              s = prev;
            } 
            if (s < range)
              return mismatch(); 
          }  
        if (this.end - range < this.regex.thresholdLength)
          return mismatch(); 
        int schStart = s;
        if (this.regex.dMax != 0)
          if (this.regex.dMax == Integer.MAX_VALUE) {
            schStart = this.end;
          } else {
            schStart += this.regex.dMax;
            if (schStart > this.end)
              schStart = this.end; 
          }  
        if (!backwardSearchRange(this.chars, this.str, this.end, schStart, range, adjrange))
          return mismatch(); 
      } 
      do {
        int prev = EncodingHelper.prevCharHead(this.str, s);
        if (matchCheck(origStart, s, prev))
          return match(s); 
        s = prev;
      } while (s >= range);
    } 
    return mismatch();
  }
  
  private boolean endBuf(int startp, int rangep, int minSemiEnd, int maxSemiEnd) {
    int start = startp;
    int range = rangep;
    if (maxSemiEnd - this.str < this.regex.anchorDmin)
      return true; 
    if (range > start) {
      if (minSemiEnd - start > this.regex.anchorDmax) {
        start = minSemiEnd - this.regex.anchorDmax;
        if (start >= this.end)
          start = EncodingHelper.prevCharHead(this.str, this.end); 
      } 
      if (maxSemiEnd - range - 1 < this.regex.anchorDmin)
        range = maxSemiEnd - this.regex.anchorDmin + 1; 
      if (start >= range)
        return true; 
    } else {
      if (minSemiEnd - range > this.regex.anchorDmax)
        range = minSemiEnd - this.regex.anchorDmax; 
      if (maxSemiEnd - start < this.regex.anchorDmin)
        start = maxSemiEnd - this.regex.anchorDmin; 
      if (range > start)
        return true; 
    } 
    return false;
  }
  
  private int match(int s) {
    return s - this.str;
  }
  
  private int mismatch() {
    if (this.msaBestLen >= 0) {
      int s = this.msaBestS;
      return match(s);
    } 
    return -1;
  }
}
