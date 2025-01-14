package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class URLConverter extends AbstractConverter<URL> {
  private static final long serialVersionUID = 1L;
  
  protected URL convertInternal(Object value) {
    try {
      if (value instanceof File)
        return ((File)value).toURI().toURL(); 
      if (value instanceof URI)
        return ((URI)value).toURL(); 
      return new URL(convertToStr(value));
    } catch (Exception exception) {
      return null;
    } 
  }
}
