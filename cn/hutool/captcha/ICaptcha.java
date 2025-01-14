package cn.hutool.captcha;

import java.io.OutputStream;
import java.io.Serializable;

public interface ICaptcha extends Serializable {
  void createCode();
  
  String getCode();
  
  boolean verify(String paramString);
  
  void write(OutputStream paramOutputStream);
}
