package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable {
  protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
  
  protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
  
  protected final JavaType _beanType;
  
  protected final BeanPropertyWriter[] _props;
  
  protected final BeanPropertyWriter[] _filteredProps;
  
  protected final AnyGetterWriter _anyGetterWriter;
  
  protected final Object _propertyFilterId;
  
  protected final AnnotatedMember _typeId;
  
  protected final ObjectIdWriter _objectIdWriter;
  
  protected final JsonFormat.Shape _serializationShape;
  
  protected BeanSerializerBase(JavaType type, BeanSerializerBuilder builder, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
    super(type);
    this._beanType = type;
    this._props = properties;
    this._filteredProps = filteredProperties;
    if (builder == null) {
      this._typeId = null;
      this._anyGetterWriter = null;
      this._propertyFilterId = null;
      this._objectIdWriter = null;
      this._serializationShape = null;
    } else {
      this._typeId = builder.getTypeId();
      this._anyGetterWriter = builder.getAnyGetter();
      this._propertyFilterId = builder.getFilterId();
      this._objectIdWriter = builder.getObjectIdWriter();
      JsonFormat.Value format = builder.getBeanDescription().findExpectedFormat(null);
      this._serializationShape = format.getShape();
    } 
  }
  
  protected BeanSerializerBase(BeanSerializerBase src, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
    super(src._handledType);
    this._beanType = src._beanType;
    this._props = properties;
    this._filteredProps = filteredProperties;
    this._typeId = src._typeId;
    this._anyGetterWriter = src._anyGetterWriter;
    this._objectIdWriter = src._objectIdWriter;
    this._propertyFilterId = src._propertyFilterId;
    this._serializationShape = src._serializationShape;
  }
  
  protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter) {
    this(src, objectIdWriter, src._propertyFilterId);
  }
  
  protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter, Object filterId) {
    super(src._handledType);
    this._beanType = src._beanType;
    this._props = src._props;
    this._filteredProps = src._filteredProps;
    this._typeId = src._typeId;
    this._anyGetterWriter = src._anyGetterWriter;
    this._objectIdWriter = objectIdWriter;
    this._propertyFilterId = filterId;
    this._serializationShape = src._serializationShape;
  }
  
  @Deprecated
  protected BeanSerializerBase(BeanSerializerBase src, String[] toIgnore) {
    this(src, ArrayBuilders.arrayToSet((Object[])toIgnore), (Set<String>)null);
  }
  
  @Deprecated
  protected BeanSerializerBase(BeanSerializerBase src, Set<String> toIgnore) {
    this(src, toIgnore, (Set<String>)null);
  }
  
  protected BeanSerializerBase(BeanSerializerBase src, Set<String> toIgnore, Set<String> toInclude) {
    super(src._handledType);
    this._beanType = src._beanType;
    BeanPropertyWriter[] propsIn = src._props;
    BeanPropertyWriter[] fpropsIn = src._filteredProps;
    int len = propsIn.length;
    ArrayList<BeanPropertyWriter> propsOut = new ArrayList<>(len);
    ArrayList<BeanPropertyWriter> fpropsOut = (fpropsIn == null) ? null : new ArrayList<>(len);
    for (int i = 0; i < len; i++) {
      BeanPropertyWriter bpw = propsIn[i];
      if (!IgnorePropertiesUtil.shouldIgnore(bpw.getName(), toIgnore, toInclude)) {
        propsOut.add(bpw);
        if (fpropsIn != null)
          fpropsOut.add(fpropsIn[i]); 
      } 
    } 
    this._props = propsOut.<BeanPropertyWriter>toArray(new BeanPropertyWriter[propsOut.size()]);
    this._filteredProps = (fpropsOut == null) ? null : fpropsOut.<BeanPropertyWriter>toArray(new BeanPropertyWriter[fpropsOut.size()]);
    this._typeId = src._typeId;
    this._anyGetterWriter = src._anyGetterWriter;
    this._objectIdWriter = src._objectIdWriter;
    this._propertyFilterId = src._propertyFilterId;
    this._serializationShape = src._serializationShape;
  }
  
  @Deprecated
  protected BeanSerializerBase withIgnorals(Set<String> toIgnore) {
    return withByNameInclusion(toIgnore, (Set<String>)null);
  }
  
  @Deprecated
  protected BeanSerializerBase withIgnorals(String[] toIgnore) {
    return withIgnorals(ArrayBuilders.arrayToSet((Object[])toIgnore));
  }
  
  protected BeanSerializerBase(BeanSerializerBase src) {
    this(src, src._props, src._filteredProps);
  }
  
  protected BeanSerializerBase(BeanSerializerBase src, NameTransformer unwrapper) {
    this(src, rename(src._props, unwrapper), rename(src._filteredProps, unwrapper));
  }
  
  private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] props, NameTransformer transformer) {
    if (props == null || props.length == 0 || transformer == null || transformer == NameTransformer.NOP)
      return props; 
    int len = props.length;
    BeanPropertyWriter[] result = new BeanPropertyWriter[len];
    for (int i = 0; i < len; i++) {
      BeanPropertyWriter bpw = props[i];
      if (bpw != null)
        result[i] = bpw.rename(transformer); 
    } 
    return result;
  }
  
  public void resolve(SerializerProvider provider) throws JsonMappingException {
    int filteredCount = (this._filteredProps == null) ? 0 : this._filteredProps.length;
    for (int i = 0, len = this._props.length; i < len; i++) {
      ContainerSerializer containerSerializer;
      BeanPropertyWriter prop = this._props[i];
      if (!prop.willSuppressNulls() && !prop.hasNullSerializer()) {
        JsonSerializer<Object> nullSer = provider.findNullValueSerializer((BeanProperty)prop);
        if (nullSer != null) {
          prop.assignNullSerializer(nullSer);
          if (i < filteredCount) {
            BeanPropertyWriter w2 = this._filteredProps[i];
            if (w2 != null)
              w2.assignNullSerializer(nullSer); 
          } 
        } 
      } 
      if (prop.hasSerializer())
        continue; 
      JsonSerializer<Object> ser = findConvertingSerializer(provider, prop);
      if (ser == null) {
        JavaType type = prop.getSerializationType();
        if (type == null) {
          type = prop.getType();
          if (!type.isFinal()) {
            if (type.isContainerType() || type.containedTypeCount() > 0)
              prop.setNonTrivialBaseType(type); 
            continue;
          } 
        } 
        ser = provider.findValueSerializer(type, (BeanProperty)prop);
        if (type.isContainerType()) {
          TypeSerializer typeSer = (TypeSerializer)type.getContentType().getTypeHandler();
          if (typeSer != null)
            if (ser instanceof ContainerSerializer) {
              ContainerSerializer containerSerializer1 = ((ContainerSerializer)ser).withValueTypeSerializer(typeSer);
              containerSerializer = containerSerializer1;
            }  
        } 
      } 
      if (i < filteredCount) {
        BeanPropertyWriter w2 = this._filteredProps[i];
        if (w2 != null) {
          w2.assignSerializer((JsonSerializer)containerSerializer);
          continue;
        } 
      } 
      prop.assignSerializer((JsonSerializer)containerSerializer);
      continue;
    } 
    if (this._anyGetterWriter != null)
      this._anyGetterWriter.resolve(provider); 
  }
  
  protected JsonSerializer<Object> findConvertingSerializer(SerializerProvider provider, BeanPropertyWriter prop) throws JsonMappingException {
    AnnotationIntrospector intr = provider.getAnnotationIntrospector();
    if (intr != null) {
      AnnotatedMember m = prop.getMember();
      if (m != null) {
        Object convDef = intr.findSerializationConverter((Annotated)m);
        if (convDef != null) {
          Converter<Object, Object> conv = provider.converterInstance((Annotated)prop.getMember(), convDef);
          JavaType delegateType = conv.getOutputType(provider.getTypeFactory());
          JsonSerializer<?> ser = delegateType.isJavaLangObject() ? null : provider.findValueSerializer(delegateType, (BeanProperty)prop);
          return new StdDelegatingSerializer(conv, delegateType, ser);
        } 
      } 
    } 
    return null;
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
    AnnotationIntrospector intr = provider.getAnnotationIntrospector();
    AnnotatedMember accessor = (property == null || intr == null) ? null : property.getMember();
    SerializationConfig config = provider.getConfig();
    JsonFormat.Value format = findFormatOverrides(provider, property, this._handledType);
    JsonFormat.Shape shape = null;
    if (format != null && format.hasShape()) {
      shape = format.getShape();
      if (shape != JsonFormat.Shape.ANY && shape != this._serializationShape)
        if (this._beanType.isEnumType()) {
          BeanDescription desc;
          JsonSerializer<?> ser;
          switch (shape) {
            case STRING:
            case NUMBER:
            case NUMBER_INT:
              desc = config.introspectClassAnnotations(this._beanType);
              ser = EnumSerializer.construct(this._beanType.getRawClass(), provider
                  .getConfig(), desc, format);
              return provider.handlePrimaryContextualization(ser, property);
          } 
        } else if (shape == JsonFormat.Shape.NATURAL && (
          !this._beanType.isMapLikeType() || !Map.class.isAssignableFrom(this._handledType))) {
          if (Map.Entry.class.isAssignableFrom(this._handledType)) {
            JavaType mapEntryType = this._beanType.findSuperType(Map.Entry.class);
            JavaType kt = mapEntryType.containedTypeOrUnknown(0);
            JavaType vt = mapEntryType.containedTypeOrUnknown(1);
            MapEntrySerializer mapEntrySerializer = new MapEntrySerializer(this._beanType, kt, vt, false, null, property);
            return provider.handlePrimaryContextualization((JsonSerializer)mapEntrySerializer, property);
          } 
        }  
    } 
    ObjectIdWriter oiw = this._objectIdWriter;
    int idPropOrigIndex = 0;
    Set<String> ignoredProps = null;
    Set<String> includedProps = null;
    Object newFilterId = null;
    if (accessor != null) {
      ignoredProps = intr.findPropertyIgnoralByName((MapperConfig)config, (Annotated)accessor).findIgnoredForSerialization();
      includedProps = intr.findPropertyInclusionByName((MapperConfig)config, (Annotated)accessor).getIncluded();
      ObjectIdInfo objectIdInfo = intr.findObjectIdInfo((Annotated)accessor);
      if (objectIdInfo == null) {
        if (oiw != null) {
          objectIdInfo = intr.findObjectReferenceInfo((Annotated)accessor, null);
          if (objectIdInfo != null)
            oiw = this._objectIdWriter.withAlwaysAsId(objectIdInfo.getAlwaysAsId()); 
        } 
      } else {
        objectIdInfo = intr.findObjectReferenceInfo((Annotated)accessor, objectIdInfo);
        Class<?> implClass = objectIdInfo.getGeneratorType();
        JavaType type = provider.constructType(implClass);
        JavaType idType = provider.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
        if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
          String propName = objectIdInfo.getPropertyName().getSimpleName();
          BeanPropertyWriter idProp = null;
          for (int i = 0, len = this._props.length;; i++) {
            if (i == len)
              provider.reportBadDefinition(this._beanType, String.format("Invalid Object Id definition for %s: cannot find property with name %s", new Object[] { ClassUtil.nameOf(handledType()), ClassUtil.name(propName) })); 
            BeanPropertyWriter prop = this._props[i];
            if (propName.equals(prop.getName())) {
              idProp = prop;
              idPropOrigIndex = i;
              break;
            } 
          } 
          idType = idProp.getType();
          PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator = new PropertyBasedObjectIdGenerator(objectIdInfo, idProp);
          oiw = ObjectIdWriter.construct(idType, (PropertyName)null, (ObjectIdGenerator)propertyBasedObjectIdGenerator, objectIdInfo.getAlwaysAsId());
        } else {
          ObjectIdGenerator<?> gen = provider.objectIdGeneratorInstance((Annotated)accessor, objectIdInfo);
          oiw = ObjectIdWriter.construct(idType, objectIdInfo.getPropertyName(), gen, objectIdInfo
              .getAlwaysAsId());
        } 
      } 
      Object filterId = intr.findFilterId((Annotated)accessor);
      if (filterId != null && !filterId.equals(this._propertyFilterId))
        newFilterId = filterId; 
    } 
    BeanSerializerBase contextual = this;
    if (idPropOrigIndex > 0) {
      BeanPropertyWriter[] newFiltered, newProps = Arrays.<BeanPropertyWriter>copyOf(this._props, this._props.length);
      BeanPropertyWriter bpw = newProps[idPropOrigIndex];
      System.arraycopy(newProps, 0, newProps, 1, idPropOrigIndex);
      newProps[0] = bpw;
      if (this._filteredProps == null) {
        newFiltered = null;
      } else {
        newFiltered = Arrays.<BeanPropertyWriter>copyOf(this._filteredProps, this._filteredProps.length);
        bpw = newFiltered[idPropOrigIndex];
        System.arraycopy(newFiltered, 0, newFiltered, 1, idPropOrigIndex);
        newFiltered[0] = bpw;
      } 
      contextual = contextual.withProperties(newProps, newFiltered);
    } 
    if (oiw != null) {
      JsonSerializer<?> ser = provider.findValueSerializer(oiw.idType, property);
      oiw = oiw.withSerializer(ser);
      if (oiw != this._objectIdWriter)
        contextual = contextual.withObjectIdWriter(oiw); 
    } 
    if ((ignoredProps != null && !ignoredProps.isEmpty()) || includedProps != null)
      contextual = contextual.withByNameInclusion(ignoredProps, includedProps); 
    if (newFilterId != null)
      contextual = contextual.withFilterId(newFilterId); 
    if (shape == null)
      shape = this._serializationShape; 
    if (shape == JsonFormat.Shape.ARRAY)
      return contextual.asArraySerializer(); 
    return contextual;
  }
  
  public Iterator<PropertyWriter> properties() {
    return Arrays.asList((Object[])this._props).iterator();
  }
  
  public boolean usesObjectId() {
    return (this._objectIdWriter != null);
  }
  
  public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    if (this._objectIdWriter != null) {
      _serializeWithObjectId(bean, gen, provider, typeSer);
      return;
    } 
    WritableTypeId typeIdDef = _typeIdDef(typeSer, bean, JsonToken.START_OBJECT);
    typeSer.writeTypePrefix(gen, typeIdDef);
    gen.setCurrentValue(bean);
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(bean, gen, provider);
    } else {
      serializeFields(bean, gen, provider);
    } 
    typeSer.writeTypeSuffix(gen, typeIdDef);
  }
  
  protected final void _serializeWithObjectId(Object bean, JsonGenerator gen, SerializerProvider provider, boolean startEndObject) throws IOException {
    ObjectIdWriter w = this._objectIdWriter;
    WritableObjectId objectId = provider.findObjectId(bean, w.generator);
    if (objectId.writeAsId(gen, provider, w))
      return; 
    Object id = objectId.generateId(bean);
    if (w.alwaysAsId) {
      w.serializer.serialize(id, gen, provider);
      return;
    } 
    if (startEndObject)
      gen.writeStartObject(bean); 
    objectId.writeAsField(gen, provider, w);
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(bean, gen, provider);
    } else {
      serializeFields(bean, gen, provider);
    } 
    if (startEndObject)
      gen.writeEndObject(); 
  }
  
  protected final void _serializeWithObjectId(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    ObjectIdWriter w = this._objectIdWriter;
    WritableObjectId objectId = provider.findObjectId(bean, w.generator);
    if (objectId.writeAsId(gen, provider, w))
      return; 
    Object id = objectId.generateId(bean);
    if (w.alwaysAsId) {
      w.serializer.serialize(id, gen, provider);
      return;
    } 
    _serializeObjectId(bean, gen, provider, typeSer, objectId);
  }
  
  protected void _serializeObjectId(Object bean, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer, WritableObjectId objectId) throws IOException {
    ObjectIdWriter w = this._objectIdWriter;
    WritableTypeId typeIdDef = _typeIdDef(typeSer, bean, JsonToken.START_OBJECT);
    typeSer.writeTypePrefix(g, typeIdDef);
    g.setCurrentValue(bean);
    objectId.writeAsField(g, provider, w);
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(bean, g, provider);
    } else {
      serializeFields(bean, g, provider);
    } 
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  protected final WritableTypeId _typeIdDef(TypeSerializer typeSer, Object bean, JsonToken valueShape) {
    if (this._typeId == null)
      return typeSer.typeId(bean, valueShape); 
    Object typeId = this._typeId.getValue(bean);
    if (typeId == null)
      typeId = ""; 
    return typeSer.typeId(bean, valueShape, typeId);
  }
  
  @Deprecated
  protected final String _customTypeId(Object bean) {
    Object typeId = this._typeId.getValue(bean);
    if (typeId == null)
      return ""; 
    return (typeId instanceof String) ? (String)typeId : typeId.toString();
  }
  
  protected void serializeFields(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
    BeanPropertyWriter[] props;
    if (this._filteredProps != null && provider.getActiveView() != null) {
      props = this._filteredProps;
    } else {
      props = this._props;
    } 
    int i = 0;
    try {
      for (int len = props.length; i < len; i++) {
        BeanPropertyWriter prop = props[i];
        if (prop != null)
          prop.serializeAsField(bean, gen, provider); 
      } 
      if (this._anyGetterWriter != null)
        this._anyGetterWriter.getAndSerialize(bean, gen, provider); 
    } catch (Exception e) {
      String name = (i == props.length) ? "[anySetter]" : props[i].getName();
      wrapAndThrow(provider, e, bean, name);
    } catch (StackOverflowError e) {
      JsonMappingException jsonMappingException = new JsonMappingException((Closeable)gen, "Infinite recursion (StackOverflowError)", e);
      String name = (i == props.length) ? "[anySetter]" : props[i].getName();
      jsonMappingException.prependPath(bean, name);
      throw jsonMappingException;
    } 
  }
  
  protected void serializeFieldsFiltered(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
    BeanPropertyWriter[] props;
    if (this._filteredProps != null && provider.getActiveView() != null) {
      props = this._filteredProps;
    } else {
      props = this._props;
    } 
    PropertyFilter filter = findPropertyFilter(provider, this._propertyFilterId, bean);
    if (filter == null) {
      serializeFields(bean, gen, provider);
      return;
    } 
    int i = 0;
    try {
      for (int len = props.length; i < len; i++) {
        BeanPropertyWriter prop = props[i];
        if (prop != null)
          filter.serializeAsField(bean, gen, provider, (PropertyWriter)prop); 
      } 
      if (this._anyGetterWriter != null)
        this._anyGetterWriter.getAndFilter(bean, gen, provider, filter); 
    } catch (Exception e) {
      String name = (i == props.length) ? "[anySetter]" : props[i].getName();
      wrapAndThrow(provider, e, bean, name);
    } catch (StackOverflowError e) {
      JsonMappingException jsonMappingException = new JsonMappingException((Closeable)gen, "Infinite recursion (StackOverflowError)", e);
      String name = (i == props.length) ? "[anySetter]" : props[i].getName();
      jsonMappingException.prependPath(bean, name);
      throw jsonMappingException;
    } 
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
    PropertyFilter filter;
    ObjectNode o = createSchemaNode("object", true);
    JsonSerializableSchema ann = (JsonSerializableSchema)this._handledType.getAnnotation(JsonSerializableSchema.class);
    if (ann != null) {
      String id = ann.id();
      if (id != null && !id.isEmpty())
        o.put("id", id); 
    } 
    ObjectNode propertiesNode = o.objectNode();
    if (this._propertyFilterId != null) {
      filter = findPropertyFilter(provider, this._propertyFilterId, null);
    } else {
      filter = null;
    } 
    for (int i = 0; i < this._props.length; i++) {
      BeanPropertyWriter prop = this._props[i];
      if (filter == null) {
        prop.depositSchemaProperty(propertiesNode, provider);
      } else {
        filter.depositSchemaProperty((PropertyWriter)prop, propertiesNode, provider);
      } 
    } 
    o.set("properties", (JsonNode)propertiesNode);
    return (JsonNode)o;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    if (visitor == null)
      return; 
    JsonObjectFormatVisitor objectVisitor = visitor.expectObjectFormat(typeHint);
    if (objectVisitor == null)
      return; 
    SerializerProvider provider = visitor.getProvider();
    if (this._propertyFilterId != null) {
      PropertyFilter filter = findPropertyFilter(visitor.getProvider(), this._propertyFilterId, null);
      for (int i = 0, end = this._props.length; i < end; i++)
        filter.depositSchemaProperty((PropertyWriter)this._props[i], objectVisitor, provider); 
    } else {
      BeanPropertyWriter[] props;
      Class<?> view = (this._filteredProps == null || provider == null) ? null : provider.getActiveView();
      if (view != null) {
        props = this._filteredProps;
      } else {
        props = this._props;
      } 
      for (int i = 0, end = props.length; i < end; i++) {
        BeanPropertyWriter prop = props[i];
        if (prop != null)
          prop.depositSchemaProperty(objectVisitor, provider); 
      } 
    } 
  }
  
  public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter paramObjectIdWriter);
  
  protected abstract BeanSerializerBase withByNameInclusion(Set<String> paramSet1, Set<String> paramSet2);
  
  protected abstract BeanSerializerBase asArraySerializer();
  
  public abstract BeanSerializerBase withFilterId(Object paramObject);
  
  protected abstract BeanSerializerBase withProperties(BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2);
  
  public abstract void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws IOException;
}
