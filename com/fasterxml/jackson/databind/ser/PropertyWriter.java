package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.Serializable;

public abstract class PropertyWriter extends ConcreteBeanPropertyBase implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected PropertyWriter(PropertyMetadata md) {
    super(md);
  }
  
  protected PropertyWriter(BeanPropertyDefinition propDef) {
    super(propDef.getMetadata());
  }
  
  protected PropertyWriter(PropertyWriter base) {
    super(base);
  }
  
  public abstract String getName();
  
  public abstract PropertyName getFullName();
  
  public <A extends java.lang.annotation.Annotation> A findAnnotation(Class<A> acls) {
    A ann = getAnnotation(acls);
    if (ann == null)
      ann = getContextAnnotation(acls); 
    return ann;
  }
  
  public abstract <A extends java.lang.annotation.Annotation> A getAnnotation(Class<A> paramClass);
  
  public abstract <A extends java.lang.annotation.Annotation> A getContextAnnotation(Class<A> paramClass);
  
  public abstract void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws Exception;
  
  public abstract void serializeAsOmittedField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws Exception;
  
  public abstract void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws Exception;
  
  public abstract void serializeAsPlaceholder(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws Exception;
  
  public abstract void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor, SerializerProvider paramSerializerProvider) throws JsonMappingException;
  
  @Deprecated
  public abstract void depositSchemaProperty(ObjectNode paramObjectNode, SerializerProvider paramSerializerProvider) throws JsonMappingException;
}
