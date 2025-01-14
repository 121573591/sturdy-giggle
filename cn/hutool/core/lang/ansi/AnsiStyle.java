package cn.hutool.core.lang.ansi;

import cn.hutool.core.util.StrUtil;

public enum AnsiStyle implements AnsiElement {
  NORMAL(0),
  BOLD(1),
  FAINT(2),
  ITALIC(3),
  UNDERLINE(4);
  
  private final int code;
  
  AnsiStyle(int code) {
    this.code = code;
  }
  
  public int getCode() {
    return this.code;
  }
  
  public String toString() {
    return StrUtil.toString(Integer.valueOf(this.code));
  }
}
