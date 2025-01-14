package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

@JacksonStdImpl
public class JsonValueSerializer extends StdSerializer<Object> implements ContextualSerializer, JsonFormatVisitable {
  protected final AnnotatedMember _accessor;
  
  protected final TypeSerializer _valueTypeSerializer;
  
  protected final JsonSerializer<Object> _valueSerializer;
  
  protected final BeanProperty _property;
  
  protected final JavaType _valueType;
  
  protected final boolean _forceTypeInformation;
  
  protected final Set<String> _ignoredProperties;
  
  protected transient PropertySerializerMap _dynamicSerializers;
  
  public JsonValueSerializer(AnnotatedMember accessor, TypeSerializer vts, JsonSerializer<?> ser, Set<String> ignoredProperties) {
    super(accessor.getType());
    this._accessor = accessor;
    this._valueType = accessor.getType();
    this._valueTypeSerializer = vts;
    this._valueSerializer = (JsonSerializer)ser;
    this._property = null;
    this._forceTypeInformation = true;
    this._ignoredProperties = ignoredProperties;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
  }
  
  @Deprecated
  public JsonValueSerializer(AnnotatedMember accessor, TypeSerializer vts, JsonSerializer<?> ser) {
    this(accessor, vts, ser, Collections.emptySet());
  }
  
  @Deprecated
  public JsonValueSerializer(AnnotatedMember accessor, JsonSerializer<?> ser) {
    this(accessor, (TypeSerializer)null, ser, Collections.emptySet());
  }
  
  public JsonValueSerializer(JsonValueSerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> ser, boolean forceTypeInfo) {
    super(_notNullClass(src.handledType()));
    this._accessor = src._accessor;
    this._valueType = src._valueType;
    this._valueTypeSerializer = vts;
    this._valueSerializer = (JsonSerializer)ser;
    this._property = property;
    this._forceTypeInformation = forceTypeInfo;
    this._ignoredProperties = src._ignoredProperties;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
  }
  
  public static JsonValueSerializer construct(SerializationConfig config, AnnotatedMember accessor, TypeSerializer vts, JsonSerializer<?> ser) {
    JsonIgnoreProperties.Value ignorals = config.getAnnotationIntrospector().findPropertyIgnoralByName((MapperConfig)config, (Annotated)accessor);
    Set<String> ignoredProperties = ignorals.findIgnoredForSerialization();
    ser = _withIgnoreProperties(ser, ignoredProperties);
    return new JsonValueSerializer(accessor, vts, ser, ignoredProperties);
  }
  
  private static final Class<Object> _notNullClass(Class<?> cls) {
    return (cls == null) ? Object.class : (Class)cls;
  }
  
  protected JsonValueSerializer withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer<?> ser, boolean forceTypeInfo) {
    if (this._property == property && this._valueTypeSerializer == vts && this._valueSerializer == ser && forceTypeInfo == this._forceTypeInformation)
      return this; 
    return new JsonValueSerializer(this, property, vts, ser, forceTypeInfo);
  }
  
  public boolean isEmpty(SerializerProvider ctxt, Object bean) {
    Object referenced = this._accessor.getValue(bean);
    if (referenced == null)
      return true; 
    JsonSerializer<Object> ser = this._valueSerializer;
    if (ser == null)
      try {
        ser = _findDynamicSerializer(ctxt, referenced.getClass());
      } catch (JsonMappingException e) {
        throw new RuntimeJsonMappingException(e);
      }  
    return ser.isEmpty(ctxt, referenced);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider ctxt, BeanProperty property) throws JsonMappingException {
    TypeSerializer typeSer = this._valueTypeSerializer;
    if (typeSer != null)
      typeSer = typeSer.forProperty(property); 
    JsonSerializer<?> ser = this._valueSerializer;
    if (ser == null) {
      if (ctxt.isEnabled(MapperFeature.USE_STATIC_TYPING) || this._valueType.isFinal()) {
        ser = ctxt.findPrimaryPropertySerializer(this._valueType, property);
        ser = _withIgnoreProperties(ser, this._ignoredProperties);
        boolean forceTypeInformation = isNaturalTypeWithStdHandling(this._valueType.getRawClass(), ser);
        return withResolved(property, typeSer, ser, forceTypeInformation);
      } 
      if (property != this._property)
        return withResolved(property, typeSer, ser, this._forceTypeInformation); 
    } else {
      ser = ctxt.handlePrimaryContextualization(ser, property);
      return withResolved(property, typeSer, ser, this._forceTypeInformation);
    } 
    return this;
  }
  
  public void serialize(Object bean, JsonGenerator gen, SerializerProvider ctxt) throws IOException {
    Object value;
    try {
      value = this._accessor.getValue(bean);
    } catch (Exception e) {
      value = null;
      wrapAndThrow(ctxt, e, bean, this._accessor.getName() + "()");
    } 
    if (value == null) {
      ctxt.defaultSerializeNull(gen);
    } else {
      JsonSerializer<Object> ser = this._valueSerializer;
      if (ser == null)
        ser = _findDynamicSerializer(ctxt, value.getClass()); 
      if (this._valueTypeSerializer != null) {
        ser.serializeWithType(value, gen, ctxt, this._valueTypeSerializer);
      } else {
        ser.serialize(value, gen, ctxt);
      } 
    } 
  }
  
  public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider ctxt, TypeSerializer typeSer0) throws IOException {
    Object value;
    try {
      value = this._accessor.getValue(bean);
    } catch (Exception e) {
      value = null;
      wrapAndThrow(ctxt, e, bean, this._accessor.getName() + "()");
    } 
    if (value == null) {
      ctxt.defaultSerializeNull(gen);
      return;
    } 
    JsonSerializer<Object> ser = this._valueSerializer;
    if (ser == null) {
      ser = _findDynamicSerializer(ctxt, value.getClass());
    } else if (this._forceTypeInformation) {
      WritableTypeId typeIdDef = typeSer0.writeTypePrefix(gen, typeSer0
          .typeId(bean, JsonToken.VALUE_STRING));
      ser.serialize(value, gen, ctxt);
      typeSer0.writeTypeSuffix(gen, typeIdDef);
      return;
    } 
    TypeSerializerRerouter rr = new TypeSerializerRerouter(typeSer0, bean);
    ser.serializeWithType(value, gen, ctxt, rr);
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider ctxt, Type typeHint) throws JsonMappingException {
    if (this._valueSerializer instanceof SchemaAware)
      return ((SchemaAware)this._valueSerializer)
        .getSchema(ctxt, null); 
    return JsonSchema.getDefaultSchemaNode();
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    Class<?> declaring = this._accessor.getDeclaringClass();
    if (declaring != null && ClassUtil.isEnumType(declaring) && 
      _acceptJsonFormatVisitorForEnum(visitor, typeHint, declaring))
      return; 
    JsonSerializer<Object> ser = this._valueSerializer;
    if (ser == null) {
      ser = visitor.getProvider().findTypedValueSerializer(this._valueType, false, this._property);
      if (ser == null) {
        visitor.expectAnyFormat(typeHint);
        return;
      } 
    } 
    ser.acceptJsonFormatVisitor(visitor, this._valueType);
  }
  
  protected boolean _acceptJsonFormatVisitorForEnum(JsonFormatVisitorWrapper visitor, JavaType typeHint, Class<?> enumType) throws JsonMappingException {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokeinterface expectStringFormat : (Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/jsonFormatVisitors/JsonStringFormatVisitor;
    //   7: astore #4
    //   9: aload #4
    //   11: ifnull -> 160
    //   14: new java/util/LinkedHashSet
    //   17: dup
    //   18: invokespecial <init> : ()V
    //   21: astore #5
    //   23: aload_3
    //   24: invokevirtual getEnumConstants : ()[Ljava/lang/Object;
    //   27: astore #6
    //   29: aload #6
    //   31: arraylength
    //   32: istore #7
    //   34: iconst_0
    //   35: istore #8
    //   37: iload #8
    //   39: iload #7
    //   41: if_icmpge -> 151
    //   44: aload #6
    //   46: iload #8
    //   48: aaload
    //   49: astore #9
    //   51: aload #5
    //   53: aload_0
    //   54: getfield _accessor : Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;
    //   57: aload #9
    //   59: invokevirtual getValue : (Ljava/lang/Object;)Ljava/lang/Object;
    //   62: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   65: invokeinterface add : (Ljava/lang/Object;)Z
    //   70: pop
    //   71: goto -> 145
    //   74: astore #10
    //   76: aload #10
    //   78: astore #11
    //   80: aload #11
    //   82: instanceof java/lang/reflect/InvocationTargetException
    //   85: ifeq -> 106
    //   88: aload #11
    //   90: invokevirtual getCause : ()Ljava/lang/Throwable;
    //   93: ifnull -> 106
    //   96: aload #11
    //   98: invokevirtual getCause : ()Ljava/lang/Throwable;
    //   101: astore #11
    //   103: goto -> 80
    //   106: aload #11
    //   108: invokestatic throwIfError : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   111: pop
    //   112: aload #11
    //   114: aload #9
    //   116: new java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial <init> : ()V
    //   123: aload_0
    //   124: getfield _accessor : Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;
    //   127: invokevirtual getName : ()Ljava/lang/String;
    //   130: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: ldc '()'
    //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual toString : ()Ljava/lang/String;
    //   141: invokestatic wrapWithPath : (Ljava/lang/Throwable;Ljava/lang/Object;Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonMappingException;
    //   144: athrow
    //   145: iinc #8, 1
    //   148: goto -> 37
    //   151: aload #4
    //   153: aload #5
    //   155: invokeinterface enumTypes : (Ljava/util/Set;)V
    //   160: iconst_1
    //   161: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #400	-> 0
    //   #401	-> 9
    //   #402	-> 14
    //   #403	-> 23
    //   #408	-> 51
    //   #416	-> 71
    //   #409	-> 74
    //   #410	-> 76
    //   #411	-> 80
    //   #412	-> 96
    //   #414	-> 106
    //   #415	-> 112
    //   #403	-> 145
    //   #418	-> 151
    //   #420	-> 160
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   80	65	11	t	Ljava/lang/Throwable;
    //   76	69	10	e	Ljava/lang/Exception;
    //   51	94	9	en	Ljava/lang/Object;
    //   23	137	5	enums	Ljava/util/Set;
    //   0	162	0	this	Lcom/fasterxml/jackson/databind/ser/std/JsonValueSerializer;
    //   0	162	1	visitor	Lcom/fasterxml/jackson/databind/jsonFormatVisitors/JsonFormatVisitorWrapper;
    //   0	162	2	typeHint	Lcom/fasterxml/jackson/databind/JavaType;
    //   0	162	3	enumType	Ljava/lang/Class;
    //   9	153	4	stringVisitor	Lcom/fasterxml/jackson/databind/jsonFormatVisitors/JsonStringFormatVisitor;
    // Local variable type table:
    //   start	length	slot	name	signature
    //   23	137	5	enums	Ljava/util/Set<Ljava/lang/String;>;
    //   0	162	3	enumType	Ljava/lang/Class<*>;
    // Exception table:
    //   from	to	target	type
    //   51	71	74	java/lang/Exception
  }
  
  protected boolean isNaturalTypeWithStdHandling(Class<?> rawType, JsonSerializer<?> ser) {
    if (rawType.isPrimitive()) {
      if (rawType != int.class && rawType != boolean.class && rawType != double.class)
        return false; 
    } else if (rawType != String.class && rawType != Integer.class && rawType != Boolean.class && rawType != Double.class) {
      return false;
    } 
    return isDefaultSerializer(ser);
  }
  
  protected JsonSerializer<Object> _findDynamicSerializer(SerializerProvider ctxt, Class<?> valueClass) throws JsonMappingException {
    JsonSerializer<Object> serializer = this._dynamicSerializers.serializerFor(valueClass);
    if (serializer == null)
      if (this._valueType.hasGenericTypes()) {
        JavaType fullType = ctxt.constructSpecializedType(this._valueType, valueClass);
        serializer = ctxt.findPrimaryPropertySerializer(fullType, this._property);
        serializer = _withIgnoreProperties(serializer, this._ignoredProperties);
        PropertySerializerMap.SerializerAndMapResult result = this._dynamicSerializers.addSerializer(fullType, serializer);
        this._dynamicSerializers = result.map;
      } else {
        serializer = ctxt.findPrimaryPropertySerializer(valueClass, this._property);
        serializer = _withIgnoreProperties(serializer, this._ignoredProperties);
        PropertySerializerMap.SerializerAndMapResult result = this._dynamicSerializers.addSerializer(valueClass, serializer);
        this._dynamicSerializers = result.map;
      }  
    return serializer;
  }
  
  protected static JsonSerializer<Object> _withIgnoreProperties(JsonSerializer<?> ser, Set<String> ignoredProperties) {
    if (ser != null && 
      !ignoredProperties.isEmpty())
      ser = ser.withIgnoredProperties(ignoredProperties); 
    return (JsonSerializer)ser;
  }
  
  public String toString() {
    return "(@JsonValue serializer for method " + this._accessor.getDeclaringClass() + "#" + this._accessor.getName() + ")";
  }
  
  static class TypeSerializerRerouter extends TypeSerializer {
    protected final TypeSerializer _typeSerializer;
    
    protected final Object _forObject;
    
    public TypeSerializerRerouter(TypeSerializer ts, Object ob) {
      this._typeSerializer = ts;
      this._forObject = ob;
    }
    
    public TypeSerializer forProperty(BeanProperty prop) {
      throw new UnsupportedOperationException();
    }
    
    public JsonTypeInfo.As getTypeInclusion() {
      return this._typeSerializer.getTypeInclusion();
    }
    
    public String getPropertyName() {
      return this._typeSerializer.getPropertyName();
    }
    
    public TypeIdResolver getTypeIdResolver() {
      return this._typeSerializer.getTypeIdResolver();
    }
    
    public WritableTypeId writeTypePrefix(JsonGenerator g, WritableTypeId typeId) throws IOException {
      typeId.forValue = this._forObject;
      return this._typeSerializer.writeTypePrefix(g, typeId);
    }
    
    public WritableTypeId writeTypeSuffix(JsonGenerator g, WritableTypeId typeId) throws IOException {
      return this._typeSerializer.writeTypeSuffix(g, typeId);
    }
    
    @Deprecated
    public void writeTypePrefixForScalar(Object value, JsonGenerator gen) throws IOException {
      this._typeSerializer.writeTypePrefixForScalar(this._forObject, gen);
    }
    
    @Deprecated
    public void writeTypePrefixForObject(Object value, JsonGenerator gen) throws IOException {
      this._typeSerializer.writeTypePrefixForObject(this._forObject, gen);
    }
    
    @Deprecated
    public void writeTypePrefixForArray(Object value, JsonGenerator gen) throws IOException {
      this._typeSerializer.writeTypePrefixForArray(this._forObject, gen);
    }
    
    @Deprecated
    public void writeTypeSuffixForScalar(Object value, JsonGenerator gen) throws IOException {
      this._typeSerializer.writeTypeSuffixForScalar(this._forObject, gen);
    }
    
    @Deprecated
    public void writeTypeSuffixForObject(Object value, JsonGenerator gen) throws IOException {
      this._typeSerializer.writeTypeSuffixForObject(this._forObject, gen);
    }
    
    @Deprecated
    public void writeTypeSuffixForArray(Object value, JsonGenerator gen) throws IOException {
      this._typeSerializer.writeTypeSuffixForArray(this._forObject, gen);
    }
    
    @Deprecated
    public void writeTypePrefixForScalar(Object value, JsonGenerator gen, Class<?> type) throws IOException {
      this._typeSerializer.writeTypePrefixForScalar(this._forObject, gen, type);
    }
    
    @Deprecated
    public void writeTypePrefixForObject(Object value, JsonGenerator gen, Class<?> type) throws IOException {
      this._typeSerializer.writeTypePrefixForObject(this._forObject, gen, type);
    }
    
    @Deprecated
    public void writeTypePrefixForArray(Object value, JsonGenerator gen, Class<?> type) throws IOException {
      this._typeSerializer.writeTypePrefixForArray(this._forObject, gen, type);
    }
    
    @Deprecated
    public void writeCustomTypePrefixForScalar(Object value, JsonGenerator gen, String typeId) throws IOException {
      this._typeSerializer.writeCustomTypePrefixForScalar(this._forObject, gen, typeId);
    }
    
    @Deprecated
    public void writeCustomTypePrefixForObject(Object value, JsonGenerator gen, String typeId) throws IOException {
      this._typeSerializer.writeCustomTypePrefixForObject(this._forObject, gen, typeId);
    }
    
    @Deprecated
    public void writeCustomTypePrefixForArray(Object value, JsonGenerator gen, String typeId) throws IOException {
      this._typeSerializer.writeCustomTypePrefixForArray(this._forObject, gen, typeId);
    }
    
    @Deprecated
    public void writeCustomTypeSuffixForScalar(Object value, JsonGenerator gen, String typeId) throws IOException {
      this._typeSerializer.writeCustomTypeSuffixForScalar(this._forObject, gen, typeId);
    }
    
    @Deprecated
    public void writeCustomTypeSuffixForObject(Object value, JsonGenerator gen, String typeId) throws IOException {
      this._typeSerializer.writeCustomTypeSuffixForObject(this._forObject, gen, typeId);
    }
    
    @Deprecated
    public void writeCustomTypeSuffixForArray(Object value, JsonGenerator gen, String typeId) throws IOException {
      this._typeSerializer.writeCustomTypeSuffixForArray(this._forObject, gen, typeId);
    }
  }
}
