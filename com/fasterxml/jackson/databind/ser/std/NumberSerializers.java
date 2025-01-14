package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;

public class NumberSerializers {
  public static void addAll(Map<String, JsonSerializer<?>> allDeserializers) {
    allDeserializers.put(Integer.class.getName(), new IntegerSerializer(Integer.class));
    allDeserializers.put(int.class.getName(), new IntegerSerializer(int.class));
    allDeserializers.put(Long.class.getName(), new LongSerializer(Long.class));
    allDeserializers.put(long.class.getName(), new LongSerializer(long.class));
    allDeserializers.put(Byte.class.getName(), IntLikeSerializer.instance);
    allDeserializers.put(byte.class.getName(), IntLikeSerializer.instance);
    allDeserializers.put(Short.class.getName(), ShortSerializer.instance);
    allDeserializers.put(short.class.getName(), ShortSerializer.instance);
    allDeserializers.put(Double.class.getName(), new DoubleSerializer(Double.class));
    allDeserializers.put(double.class.getName(), new DoubleSerializer(double.class));
    allDeserializers.put(Float.class.getName(), FloatSerializer.instance);
    allDeserializers.put(float.class.getName(), FloatSerializer.instance);
  }
  
  public static abstract class Base<T> extends StdScalarSerializer<T> implements ContextualSerializer {
    protected final JsonParser.NumberType _numberType;
    
    protected final String _schemaType;
    
    protected final boolean _isInt;
    
    protected Base(Class<?> cls, JsonParser.NumberType numberType, String schemaType) {
      super(cls, false);
      this._numberType = numberType;
      this._schemaType = schemaType;
      this._isInt = (numberType == JsonParser.NumberType.INT || numberType == JsonParser.NumberType.LONG || numberType == JsonParser.NumberType.BIG_INTEGER);
    }
    
    @Deprecated
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
      return (JsonNode)createSchemaNode(this._schemaType, true);
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      if (this._isInt) {
        visitIntFormat(visitor, typeHint, this._numberType);
      } else {
        visitFloatFormat(visitor, typeHint, this._numberType);
      } 
    }
    
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
      JsonFormat.Value format = findFormatOverrides(prov, property, handledType());
      if (format != null)
        switch (format.getShape()) {
          case STRING:
            if (handledType() == BigDecimal.class)
              return NumberSerializer.bigDecimalAsStringSerializer(); 
            return ToStringSerializer.instance;
        }  
      return this;
    }
  }
  
  @JacksonStdImpl
  public static class ShortSerializer extends Base<Object> {
    static final ShortSerializer instance = new ShortSerializer();
    
    public ShortSerializer() {
      super(Short.class, JsonParser.NumberType.INT, "integer");
    }
    
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(((Short)value).shortValue());
    }
  }
  
  @JacksonStdImpl
  public static class IntegerSerializer extends Base<Object> {
    public IntegerSerializer(Class<?> type) {
      super(type, JsonParser.NumberType.INT, "integer");
    }
    
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(((Integer)value).intValue());
    }
    
    public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      serialize(value, gen, provider);
    }
  }
  
  @JacksonStdImpl
  public static class IntLikeSerializer extends Base<Object> {
    static final IntLikeSerializer instance = new IntLikeSerializer();
    
    public IntLikeSerializer() {
      super(Number.class, JsonParser.NumberType.INT, "integer");
    }
    
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(((Number)value).intValue());
    }
  }
  
  @JacksonStdImpl
  public static class LongSerializer extends Base<Object> {
    public LongSerializer(Class<?> cls) {
      super(cls, JsonParser.NumberType.LONG, "integer");
    }
    
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(((Long)value).longValue());
    }
  }
  
  @JacksonStdImpl
  public static class FloatSerializer extends Base<Object> {
    static final FloatSerializer instance = new FloatSerializer();
    
    public FloatSerializer() {
      super(Float.class, JsonParser.NumberType.FLOAT, "number");
    }
    
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(((Float)value).floatValue());
    }
  }
  
  @JacksonStdImpl
  public static class DoubleSerializer extends Base<Object> {
    public DoubleSerializer(Class<?> cls) {
      super(cls, JsonParser.NumberType.DOUBLE, "number");
    }
    
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(((Double)value).doubleValue());
    }
    
    public void serializeWithType(Object value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      Double d = (Double)value;
      if (NumberOutput.notFinite(d.doubleValue())) {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer
            
            .typeId(value, JsonToken.VALUE_NUMBER_FLOAT));
        g.writeNumber(d.doubleValue());
        typeSer.writeTypeSuffix(g, typeIdDef);
      } else {
        g.writeNumber(d.doubleValue());
      } 
    }
    
    @Deprecated
    public static boolean notFinite(double value) {
      return NumberOutput.notFinite(value);
    }
  }
}
