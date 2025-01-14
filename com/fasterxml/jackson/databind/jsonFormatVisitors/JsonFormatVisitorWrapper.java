package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public interface JsonFormatVisitorWrapper extends JsonFormatVisitorWithSerializerProvider {
  JsonObjectFormatVisitor expectObjectFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonArrayFormatVisitor expectArrayFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonStringFormatVisitor expectStringFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonNumberFormatVisitor expectNumberFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonIntegerFormatVisitor expectIntegerFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonBooleanFormatVisitor expectBooleanFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonNullFormatVisitor expectNullFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonAnyFormatVisitor expectAnyFormat(JavaType paramJavaType) throws JsonMappingException;
  
  JsonMapFormatVisitor expectMapFormat(JavaType paramJavaType) throws JsonMappingException;
  
  public static class Base implements JsonFormatVisitorWrapper {
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
    
    public JsonObjectFormatVisitor expectObjectFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonArrayFormatVisitor expectArrayFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonStringFormatVisitor expectStringFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonNumberFormatVisitor expectNumberFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonIntegerFormatVisitor expectIntegerFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonBooleanFormatVisitor expectBooleanFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonNullFormatVisitor expectNullFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonAnyFormatVisitor expectAnyFormat(JavaType type) throws JsonMappingException {
      return null;
    }
    
    public JsonMapFormatVisitor expectMapFormat(JavaType type) throws JsonMappingException {
      return null;
    }
  }
}
