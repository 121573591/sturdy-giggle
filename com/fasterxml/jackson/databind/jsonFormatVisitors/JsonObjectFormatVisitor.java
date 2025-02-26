package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public interface JsonObjectFormatVisitor extends JsonFormatVisitorWithSerializerProvider {
  void property(BeanProperty paramBeanProperty) throws JsonMappingException;
  
  void property(String paramString, JsonFormatVisitable paramJsonFormatVisitable, JavaType paramJavaType) throws JsonMappingException;
  
  void optionalProperty(BeanProperty paramBeanProperty) throws JsonMappingException;
  
  void optionalProperty(String paramString, JsonFormatVisitable paramJsonFormatVisitable, JavaType paramJavaType) throws JsonMappingException;
  
  public static class Base implements JsonObjectFormatVisitor {
    protected SerializerProvider _provider;
    
    public Base() {}
    
    public Base(SerializerProvider p) {
      this._provider = p;
    }
    
    public SerializerProvider getProvider() {
      return this._provider;
    }
    
    public void setProvider(SerializerProvider p) {
      this._provider = p;
    }
    
    public void property(BeanProperty prop) throws JsonMappingException {}
    
    public void property(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {}
    
    public void optionalProperty(BeanProperty prop) throws JsonMappingException {}
    
    public void optionalProperty(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {}
  }
}
