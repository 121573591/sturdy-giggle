package cn.hutool.poi.excel.sax;

public enum ElementName {
  row, c, v, f;
  
  public boolean match(String elementName) {
    return name().equals(elementName);
  }
  
  public static ElementName of(String elementName) {
    try {
      return valueOf(elementName);
    } catch (Exception exception) {
      return null;
    } 
  }
}
