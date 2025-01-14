package cn.hutool.core.lang.ansi;

public interface AnsiElement {
  String toString();
  
  default int getCode() {
    return -1;
  }
}
