package cn.hutool.core.text.finder;

public interface Finder {
  public static final int INDEX_NOT_FOUND = -1;
  
  int start(int paramInt);
  
  int end(int paramInt);
  
  default Finder reset() {
    return this;
  }
}
