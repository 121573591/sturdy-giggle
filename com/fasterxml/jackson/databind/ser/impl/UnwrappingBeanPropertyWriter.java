package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final NameTransformer _nameTransformer;
  
  public UnwrappingBeanPropertyWriter(BeanPropertyWriter base, NameTransformer unwrapper) {
    super(base);
    this._nameTransformer = unwrapper;
  }
  
  protected UnwrappingBeanPropertyWriter(UnwrappingBeanPropertyWriter base, NameTransformer transformer, SerializedString name) {
    super(base, name);
    this._nameTransformer = transformer;
  }
  
  public UnwrappingBeanPropertyWriter rename(NameTransformer transformer) {
    String oldName = this._name.getValue();
    String newName = transformer.transform(oldName);
    transformer = NameTransformer.chainedTransformer(transformer, this._nameTransformer);
    return _new(transformer, new SerializedString(newName));
  }
  
  protected UnwrappingBeanPropertyWriter _new(NameTransformer transformer, SerializedString newName) {
    return new UnwrappingBeanPropertyWriter(this, transformer, newName);
  }
  
  public boolean isUnwrapping() {
    return true;
  }
  
  public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
    Object value = get(bean);
    if (value == null)
      return; 
    JsonSerializer<Object> ser = this._serializer;
    if (ser == null) {
      Class<?> cls = value.getClass();
      PropertySerializerMap map = this._dynamicSerializers;
      ser = map.serializerFor(cls);
      if (ser == null)
        ser = _findAndAddDynamic(map, cls, prov); 
    } 
    if (this._suppressableValue != null)
      if (MARKER_FOR_EMPTY == this._suppressableValue) {
        if (ser.isEmpty(prov, value))
          return; 
      } else if (this._suppressableValue.equals(value)) {
        return;
      }  
    if (value == bean && 
      _handleSelfReference(bean, gen, prov, ser))
      return; 
    if (!ser.isUnwrappingSerializer())
      gen.writeFieldName((SerializableString)this._name); 
    if (this._typeSerializer == null) {
      ser.serialize(value, gen, prov);
    } else {
      ser.serializeWithType(value, gen, prov, this._typeSerializer);
    } 
  }
  
  public void assignSerializer(JsonSerializer<Object> ser) {
    if (ser != null) {
      NameTransformer t = this._nameTransformer;
      if (ser.isUnwrappingSerializer() && ser instanceof UnwrappingBeanSerializer)
        t = NameTransformer.chainedTransformer(t, ((UnwrappingBeanSerializer)ser)._nameTransformer); 
      ser = ser.unwrappingSerializer(t);
    } 
    super.assignSerializer(ser);
  }
  
  public void depositSchemaProperty(final JsonObjectFormatVisitor visitor, SerializerProvider provider) throws JsonMappingException {
    JsonSerializer<Object> ser = provider.findValueSerializer(getType(), (BeanProperty)this).unwrappingSerializer(this._nameTransformer);
    if (ser.isUnwrappingSerializer()) {
      ser.acceptJsonFormatVisitor((JsonFormatVisitorWrapper)new JsonFormatVisitorWrapper.Base(provider) {
            public JsonObjectFormatVisitor expectObjectFormat(JavaType type) throws JsonMappingException {
              return visitor;
            }
          }getType());
    } else {
      super.depositSchemaProperty(visitor, provider);
    } 
  }
  
  protected void _depositSchemaProperty(ObjectNode propertiesNode, JsonNode schemaNode) {
    JsonNode props = schemaNode.get("properties");
    if (props != null) {
      Iterator<Map.Entry<String, JsonNode>> it = props.fields();
      while (it.hasNext()) {
        Map.Entry<String, JsonNode> entry = it.next();
        String name = entry.getKey();
        if (this._nameTransformer != null)
          name = this._nameTransformer.transform(name); 
        propertiesNode.set(name, entry.getValue());
      } 
    } 
  }
  
  protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, Class<?> type, SerializerProvider provider) throws JsonMappingException {
    if (this._nonTrivialBaseType != null) {
      JavaType subtype = provider.constructSpecializedType(this._nonTrivialBaseType, type);
      serializer = provider.findValueSerializer(subtype, (BeanProperty)this);
    } else {
      serializer = provider.findValueSerializer(type, (BeanProperty)this);
    } 
    NameTransformer t = this._nameTransformer;
    if (serializer.isUnwrappingSerializer() && serializer instanceof UnwrappingBeanSerializer)
      t = NameTransformer.chainedTransformer(t, ((UnwrappingBeanSerializer)serializer)._nameTransformer); 
    JsonSerializer<Object> serializer = serializer.unwrappingSerializer(t);
    this._dynamicSerializers = this._dynamicSerializers.newWith(type, serializer);
    return serializer;
  }
}
