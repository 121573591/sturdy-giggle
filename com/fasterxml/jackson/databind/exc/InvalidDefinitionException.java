package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.io.Closeable;

public class InvalidDefinitionException extends JsonMappingException {
  protected final JavaType _type;
  
  protected transient BeanDescription _beanDesc;
  
  protected transient BeanPropertyDefinition _property;
  
  protected InvalidDefinitionException(JsonParser p, String msg, JavaType type) {
    super((Closeable)p, msg);
    this._type = type;
    this._beanDesc = null;
    this._property = null;
  }
  
  protected InvalidDefinitionException(JsonGenerator g, String msg, JavaType type) {
    super((Closeable)g, msg);
    this._type = type;
    this._beanDesc = null;
    this._property = null;
  }
  
  protected InvalidDefinitionException(JsonParser p, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
    super((Closeable)p, msg);
    this._type = (bean == null) ? null : bean.getType();
    this._beanDesc = bean;
    this._property = prop;
  }
  
  protected InvalidDefinitionException(JsonGenerator g, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
    super((Closeable)g, msg);
    this._type = (bean == null) ? null : bean.getType();
    this._beanDesc = bean;
    this._property = prop;
  }
  
  public static InvalidDefinitionException from(JsonParser p, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
    return new InvalidDefinitionException(p, msg, bean, prop);
  }
  
  public static InvalidDefinitionException from(JsonParser p, String msg, JavaType type) {
    return new InvalidDefinitionException(p, msg, type);
  }
  
  public static InvalidDefinitionException from(JsonGenerator g, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
    return new InvalidDefinitionException(g, msg, bean, prop);
  }
  
  public static InvalidDefinitionException from(JsonGenerator g, String msg, JavaType type) {
    return new InvalidDefinitionException(g, msg, type);
  }
  
  public JavaType getType() {
    return this._type;
  }
  
  public BeanDescription getBeanDescription() {
    return this._beanDesc;
  }
  
  public BeanPropertyDefinition getProperty() {
    return this._property;
  }
}
