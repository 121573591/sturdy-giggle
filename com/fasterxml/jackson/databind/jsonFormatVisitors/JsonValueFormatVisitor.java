package com.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.Set;

public interface JsonValueFormatVisitor {
  void format(JsonValueFormat paramJsonValueFormat);
  
  void enumTypes(Set<String> paramSet);
  
  public static class Base implements JsonValueFormatVisitor {
    public void format(JsonValueFormat format) {}
    
    public void enumTypes(Set<String> enums) {}
  }
}
