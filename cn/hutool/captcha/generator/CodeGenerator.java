package cn.hutool.captcha.generator;

import java.io.Serializable;

public interface CodeGenerator extends Serializable {
  String generate();
  
  boolean verify(String paramString1, String paramString2);
}
