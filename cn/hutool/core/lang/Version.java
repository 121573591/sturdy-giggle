package cn.hutool.core.lang;

import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.util.CharUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Version implements Comparable<Version>, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final String version;
  
  private final List<Object> sequence;
  
  private final List<Object> pre;
  
  private final List<Object> build;
  
  public static Version of(String v) {
    return new Version(v);
  }
  
  public Version(String v) {
    Assert.notNull(v, "Null version string", new Object[0]);
    int n = v.length();
    if (n == 0)
      throw new IllegalArgumentException("Empty version string"); 
    this.version = v;
    this.sequence = new ArrayList(4);
    this.pre = new ArrayList(2);
    this.build = new ArrayList(2);
    int i = 0;
    char c = v.charAt(i);
    List<Object> sequence = this.sequence;
    List<Object> pre = this.pre;
    List<Object> build = this.build;
    i = takeNumber(v, i, sequence);
    while (i < n) {
      c = v.charAt(i);
      if (c == '.') {
        i++;
        continue;
      } 
      if (c == '-' || c == '+') {
        i++;
        break;
      } 
      if (CharUtil.isNumber(c)) {
        i = takeNumber(v, i, sequence);
        continue;
      } 
      i = takeString(v, i, sequence);
    } 
    if (c == '-' && i >= n)
      return; 
    while (i < n) {
      c = v.charAt(i);
      if (c >= '0' && c <= '9') {
        i = takeNumber(v, i, pre);
      } else {
        i = takeString(v, i, pre);
      } 
      if (i >= n)
        break; 
      c = v.charAt(i);
      if (c == '.' || c == '-') {
        i++;
        continue;
      } 
      if (c == '+') {
        i++;
        break;
      } 
    } 
    if (c == '+' && i >= n)
      return; 
    while (i < n) {
      c = v.charAt(i);
      if (c >= '0' && c <= '9') {
        i = takeNumber(v, i, build);
      } else {
        i = takeString(v, i, build);
      } 
      if (i >= n)
        break; 
      c = v.charAt(i);
      if (c == '.' || c == '-' || c == '+')
        i++; 
    } 
  }
  
  public int compareTo(Version that) {
    int c = compareTokens(this.sequence, that.sequence);
    if (c != 0)
      return c; 
    if (this.pre.isEmpty()) {
      if (!that.pre.isEmpty())
        return 1; 
    } else if (that.pre.isEmpty()) {
      return -1;
    } 
    c = compareTokens(this.pre, that.pre);
    if (c != 0)
      return c; 
    return compareTokens(this.build, that.build);
  }
  
  public boolean equals(Object ob) {
    if (!(ob instanceof Version))
      return false; 
    return (compareTo((Version)ob) == 0);
  }
  
  public int hashCode() {
    return this.version.hashCode();
  }
  
  public String toString() {
    return this.version;
  }
  
  private static int takeNumber(String s, int i, List<Object> acc) {
    char c = s.charAt(i);
    int d = c - 48;
    int n = s.length();
    while (++i < n) {
      c = s.charAt(i);
      if (CharUtil.isNumber(c))
        d = d * 10 + c - 48; 
    } 
    acc.add(Integer.valueOf(d));
    return i;
  }
  
  private static int takeString(String s, int i, List<Object> acc) {
    int b = i;
    int n = s.length();
    while (++i < n) {
      char c = s.charAt(i);
      if (c != '.' && c != '-' && c != '+' && (c < '0' || c > '9'));
    } 
    acc.add(s.substring(b, i));
    return i;
  }
  
  private int compareTokens(List<Object> ts1, List<Object> ts2) {
    int n = Math.min(ts1.size(), ts2.size());
    for (int i = 0; i < n; i++) {
      Object o1 = ts1.get(i);
      Object o2 = ts2.get(i);
      if ((o1 instanceof Integer && o2 instanceof Integer) || (o1 instanceof String && o2 instanceof String)) {
        int c = CompareUtil.compare(o1, o2, null);
        if (c != 0)
          return c; 
      } else {
        int c = o1.toString().compareTo(o2.toString());
        if (c != 0)
          return c; 
      } 
    } 
    List<Object> rest = (ts1.size() > ts2.size()) ? ts1 : ts2;
    int e = rest.size();
    for (int j = n; j < e; ) {
      Object o = rest.get(j);
      if (o instanceof Integer && ((Integer)o).intValue() == 0) {
        j++;
        continue;
      } 
      return ts1.size() - ts2.size();
    } 
    return 0;
  }
}
