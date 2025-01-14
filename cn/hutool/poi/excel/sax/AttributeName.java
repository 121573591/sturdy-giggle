package cn.hutool.poi.excel.sax;

import org.xml.sax.Attributes;

public enum AttributeName {
  r, s, t;
  
  public boolean match(String attributeName) {
    return name().equals(attributeName);
  }
  
  public String getValue(Attributes attributes) {
    return attributes.getValue(name());
  }
}
