package cn.hutool.core.lang.intern;

public class JdkStringInterner implements Interner<String> {
  public String intern(String sample) {
    if (null == sample)
      return null; 
    return sample.intern();
  }
}
