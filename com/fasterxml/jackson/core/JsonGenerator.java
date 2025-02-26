package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.core.util.JacksonFeature;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class JsonGenerator implements Closeable, Flushable, Versioned {
  protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_WRITE_CAPABILITIES = JacksonFeatureSet.fromDefaults((JacksonFeature[])StreamWriteCapability.values());
  
  protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_TEXTUAL_WRITE_CAPABILITIES = DEFAULT_WRITE_CAPABILITIES
    .with(StreamWriteCapability.CAN_WRITE_FORMATTED_NUMBERS);
  
  protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_BINARY_WRITE_CAPABILITIES = DEFAULT_WRITE_CAPABILITIES
    .with(StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY);
  
  protected PrettyPrinter _cfgPrettyPrinter;
  
  public abstract JsonGenerator setCodec(ObjectCodec paramObjectCodec);
  
  public abstract ObjectCodec getCodec();
  
  public enum Feature {
    AUTO_CLOSE_TARGET(true),
    AUTO_CLOSE_JSON_CONTENT(true),
    FLUSH_PASSED_TO_STREAM(true),
    QUOTE_FIELD_NAMES(true),
    QUOTE_NON_NUMERIC_NUMBERS(true),
    ESCAPE_NON_ASCII(false),
    WRITE_NUMBERS_AS_STRINGS(false),
    WRITE_BIGDECIMAL_AS_PLAIN(false),
    STRICT_DUPLICATE_DETECTION(false),
    IGNORE_UNKNOWN(false),
    USE_FAST_DOUBLE_WRITER(false),
    WRITE_HEX_UPPER_CASE(true);
    
    private final boolean _defaultState;
    
    private final int _mask;
    
    public static int collectDefaults() {
      int flags = 0;
      for (Feature f : values()) {
        if (f.enabledByDefault())
          flags |= f.getMask(); 
      } 
      return flags;
    }
    
    Feature(boolean defaultState) {
      this._defaultState = defaultState;
      this._mask = 1 << ordinal();
    }
    
    public boolean enabledByDefault() {
      return this._defaultState;
    }
    
    public boolean enabledIn(int flags) {
      return ((flags & this._mask) != 0);
    }
    
    public int getMask() {
      return this._mask;
    }
  }
  
  public StreamWriteConstraints streamWriteConstraints() {
    return StreamWriteConstraints.defaults();
  }
  
  public abstract Version version();
  
  public abstract JsonStreamContext getOutputContext();
  
  public Object getOutputTarget() {
    return null;
  }
  
  public Object currentValue() {
    return getCurrentValue();
  }
  
  public void assignCurrentValue(Object v) {
    setCurrentValue(v);
  }
  
  public Object getCurrentValue() {
    JsonStreamContext ctxt = getOutputContext();
    return (ctxt == null) ? null : ctxt.getCurrentValue();
  }
  
  public void setCurrentValue(Object v) {
    JsonStreamContext ctxt = getOutputContext();
    if (ctxt != null)
      ctxt.setCurrentValue(v); 
  }
  
  public abstract JsonGenerator enable(Feature paramFeature);
  
  public abstract JsonGenerator disable(Feature paramFeature);
  
  public final JsonGenerator configure(Feature f, boolean state) {
    if (state) {
      enable(f);
    } else {
      disable(f);
    } 
    return this;
  }
  
  public abstract boolean isEnabled(Feature paramFeature);
  
  public boolean isEnabled(StreamWriteFeature f) {
    return isEnabled(f.mappedFeature());
  }
  
  public abstract int getFeatureMask();
  
  @Deprecated
  public abstract JsonGenerator setFeatureMask(int paramInt);
  
  public JsonGenerator overrideStdFeatures(int values, int mask) {
    int oldState = getFeatureMask();
    int newState = oldState & (mask ^ 0xFFFFFFFF) | values & mask;
    return setFeatureMask(newState);
  }
  
  public int getFormatFeatures() {
    return 0;
  }
  
  public JsonGenerator overrideFormatFeatures(int values, int mask) {
    return this;
  }
  
  public void setSchema(FormatSchema schema) {
    throw new UnsupportedOperationException(String.format("Generator of type %s does not support schema of type '%s'", new Object[] { getClass().getName(), schema.getSchemaType() }));
  }
  
  public FormatSchema getSchema() {
    return null;
  }
  
  public JsonGenerator setPrettyPrinter(PrettyPrinter pp) {
    this._cfgPrettyPrinter = pp;
    return this;
  }
  
  public PrettyPrinter getPrettyPrinter() {
    return this._cfgPrettyPrinter;
  }
  
  public abstract JsonGenerator useDefaultPrettyPrinter();
  
  public JsonGenerator setHighestNonEscapedChar(int charCode) {
    return this;
  }
  
  public int getHighestEscapedChar() {
    return 0;
  }
  
  public CharacterEscapes getCharacterEscapes() {
    return null;
  }
  
  public JsonGenerator setCharacterEscapes(CharacterEscapes esc) {
    return this;
  }
  
  public JsonGenerator setRootValueSeparator(SerializableString sep) {
    throw new UnsupportedOperationException();
  }
  
  public int getOutputBuffered() {
    return -1;
  }
  
  public boolean canUseSchema(FormatSchema schema) {
    return false;
  }
  
  public boolean canWriteObjectId() {
    return false;
  }
  
  public boolean canWriteTypeId() {
    return false;
  }
  
  public boolean canWriteBinaryNatively() {
    return false;
  }
  
  public boolean canOmitFields() {
    return true;
  }
  
  public boolean canWriteFormattedNumbers() {
    return false;
  }
  
  public JacksonFeatureSet<StreamWriteCapability> getWriteCapabilities() {
    return DEFAULT_WRITE_CAPABILITIES;
  }
  
  public abstract void writeStartArray() throws IOException;
  
  @Deprecated
  public void writeStartArray(int size) throws IOException {
    writeStartArray();
  }
  
  public void writeStartArray(Object forValue) throws IOException {
    writeStartArray();
    setCurrentValue(forValue);
  }
  
  public void writeStartArray(Object forValue, int size) throws IOException {
    writeStartArray(size);
    setCurrentValue(forValue);
  }
  
  public abstract void writeEndArray() throws IOException;
  
  public abstract void writeStartObject() throws IOException;
  
  public void writeStartObject(Object forValue) throws IOException {
    writeStartObject();
    setCurrentValue(forValue);
  }
  
  public void writeStartObject(Object forValue, int size) throws IOException {
    writeStartObject(forValue);
  }
  
  public abstract void writeEndObject() throws IOException;
  
  public abstract void writeFieldName(String paramString) throws IOException;
  
  public abstract void writeFieldName(SerializableString paramSerializableString) throws IOException;
  
  public void writeFieldId(long id) throws IOException {
    writeFieldName(Long.toString(id));
  }
  
  public void writeArray(int[] array, int offset, int length) throws IOException {
    if (array == null)
      throw new IllegalArgumentException("null array"); 
    _verifyOffsets(array.length, offset, length);
    writeStartArray(array, length);
    for (int i = offset, end = offset + length; i < end; i++)
      writeNumber(array[i]); 
    writeEndArray();
  }
  
  public void writeArray(long[] array, int offset, int length) throws IOException {
    if (array == null)
      throw new IllegalArgumentException("null array"); 
    _verifyOffsets(array.length, offset, length);
    writeStartArray(array, length);
    for (int i = offset, end = offset + length; i < end; i++)
      writeNumber(array[i]); 
    writeEndArray();
  }
  
  public void writeArray(double[] array, int offset, int length) throws IOException {
    if (array == null)
      throw new IllegalArgumentException("null array"); 
    _verifyOffsets(array.length, offset, length);
    writeStartArray(array, length);
    for (int i = offset, end = offset + length; i < end; i++)
      writeNumber(array[i]); 
    writeEndArray();
  }
  
  public void writeArray(String[] array, int offset, int length) throws IOException {
    if (array == null)
      throw new IllegalArgumentException("null array"); 
    _verifyOffsets(array.length, offset, length);
    writeStartArray(array, length);
    for (int i = offset, end = offset + length; i < end; i++)
      writeString(array[i]); 
    writeEndArray();
  }
  
  public abstract void writeString(String paramString) throws IOException;
  
  public void writeString(Reader reader, int len) throws IOException {
    _reportUnsupportedOperation();
  }
  
  public abstract void writeString(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeString(SerializableString paramSerializableString) throws IOException;
  
  public abstract void writeRawUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeRaw(String paramString) throws IOException;
  
  public abstract void writeRaw(String paramString, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeRaw(char paramChar) throws IOException;
  
  public void writeRaw(SerializableString raw) throws IOException {
    writeRaw(raw.getValue());
  }
  
  public abstract void writeRawValue(String paramString) throws IOException;
  
  public abstract void writeRawValue(String paramString, int paramInt1, int paramInt2) throws IOException;
  
  public abstract void writeRawValue(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
  
  public void writeRawValue(SerializableString raw) throws IOException {
    writeRawValue(raw.getValue());
  }
  
  public abstract void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public void writeBinary(byte[] data, int offset, int len) throws IOException {
    writeBinary(Base64Variants.getDefaultVariant(), data, offset, len);
  }
  
  public void writeBinary(byte[] data) throws IOException {
    writeBinary(Base64Variants.getDefaultVariant(), data, 0, data.length);
  }
  
  public int writeBinary(InputStream data, int dataLength) throws IOException {
    return writeBinary(Base64Variants.getDefaultVariant(), data, dataLength);
  }
  
  public abstract int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt) throws IOException;
  
  public void writeNumber(short v) throws IOException {
    writeNumber(v);
  }
  
  public abstract void writeNumber(int paramInt) throws IOException;
  
  public abstract void writeNumber(long paramLong) throws IOException;
  
  public abstract void writeNumber(BigInteger paramBigInteger) throws IOException;
  
  public abstract void writeNumber(double paramDouble) throws IOException;
  
  public abstract void writeNumber(float paramFloat) throws IOException;
  
  public abstract void writeNumber(BigDecimal paramBigDecimal) throws IOException;
  
  public abstract void writeNumber(String paramString) throws IOException;
  
  public void writeNumber(char[] encodedValueBuffer, int offset, int len) throws IOException {
    writeNumber(new String(encodedValueBuffer, offset, len));
  }
  
  public abstract void writeBoolean(boolean paramBoolean) throws IOException;
  
  public abstract void writeNull() throws IOException;
  
  public void writeEmbeddedObject(Object object) throws IOException {
    if (object == null) {
      writeNull();
      return;
    } 
    if (object instanceof byte[]) {
      writeBinary((byte[])object);
      return;
    } 
    throw new JsonGenerationException("No native support for writing embedded objects of type " + object
        .getClass().getName(), this);
  }
  
  public void writeObjectId(Object id) throws IOException {
    throw new JsonGenerationException("No native support for writing Object Ids", this);
  }
  
  public void writeObjectRef(Object referenced) throws IOException {
    throw new JsonGenerationException("No native support for writing Object Ids", this);
  }
  
  public void writeTypeId(Object id) throws IOException {
    throw new JsonGenerationException("No native support for writing Type Ids", this);
  }
  
  public WritableTypeId writeTypePrefix(WritableTypeId typeIdDef) throws IOException {
    Object id = typeIdDef.id;
    JsonToken valueShape = typeIdDef.valueShape;
    if (canWriteTypeId()) {
      typeIdDef.wrapperWritten = false;
      writeTypeId(id);
    } else {
      String idStr = (id instanceof String) ? (String)id : String.valueOf(id);
      typeIdDef.wrapperWritten = true;
      WritableTypeId.Inclusion incl = typeIdDef.include;
      if (valueShape != JsonToken.START_OBJECT && incl
        .requiresObjectContext())
        typeIdDef.include = incl = WritableTypeId.Inclusion.WRAPPER_ARRAY; 
      switch (incl) {
        case PARENT_PROPERTY:
        case PAYLOAD_PROPERTY:
          break;
        case METADATA_PROPERTY:
          writeStartObject(typeIdDef.forValue);
          writeStringField(typeIdDef.asProperty, idStr);
          return typeIdDef;
        case WRAPPER_OBJECT:
          writeStartObject();
          writeFieldName(idStr);
          break;
        default:
          writeStartArray();
          writeString(idStr);
          break;
      } 
    } 
    if (valueShape == JsonToken.START_OBJECT) {
      writeStartObject(typeIdDef.forValue);
    } else if (valueShape == JsonToken.START_ARRAY) {
      writeStartArray();
    } 
    return typeIdDef;
  }
  
  public WritableTypeId writeTypeSuffix(WritableTypeId typeIdDef) throws IOException {
    JsonToken valueShape = typeIdDef.valueShape;
    if (valueShape == JsonToken.START_OBJECT) {
      writeEndObject();
    } else if (valueShape == JsonToken.START_ARRAY) {
      writeEndArray();
    } 
    if (typeIdDef.wrapperWritten) {
      Object id;
      String idStr;
      switch (typeIdDef.include) {
        case WRAPPER_ARRAY:
          writeEndArray();
        case PARENT_PROPERTY:
          id = typeIdDef.id;
          idStr = (id instanceof String) ? (String)id : String.valueOf(id);
          writeStringField(typeIdDef.asProperty, idStr);
        case PAYLOAD_PROPERTY:
        case METADATA_PROPERTY:
          return typeIdDef;
      } 
      writeEndObject();
    } 
  }
  
  public void writePOJO(Object pojo) throws IOException {
    writeObject(pojo);
  }
  
  public abstract void writeObject(Object paramObject) throws IOException;
  
  public abstract void writeTree(TreeNode paramTreeNode) throws IOException;
  
  public void writeBinaryField(String fieldName, byte[] data) throws IOException {
    writeFieldName(fieldName);
    writeBinary(data);
  }
  
  public void writeBooleanField(String fieldName, boolean value) throws IOException {
    writeFieldName(fieldName);
    writeBoolean(value);
  }
  
  public void writeNullField(String fieldName) throws IOException {
    writeFieldName(fieldName);
    writeNull();
  }
  
  public void writeStringField(String fieldName, String value) throws IOException {
    writeFieldName(fieldName);
    writeString(value);
  }
  
  public void writeNumberField(String fieldName, short value) throws IOException {
    writeFieldName(fieldName);
    writeNumber(value);
  }
  
  public void writeNumberField(String fieldName, int value) throws IOException {
    writeFieldName(fieldName);
    writeNumber(value);
  }
  
  public void writeNumberField(String fieldName, long value) throws IOException {
    writeFieldName(fieldName);
    writeNumber(value);
  }
  
  public void writeNumberField(String fieldName, BigInteger value) throws IOException {
    writeFieldName(fieldName);
    writeNumber(value);
  }
  
  public void writeNumberField(String fieldName, float value) throws IOException {
    writeFieldName(fieldName);
    writeNumber(value);
  }
  
  public void writeNumberField(String fieldName, double value) throws IOException {
    writeFieldName(fieldName);
    writeNumber(value);
  }
  
  public void writeNumberField(String fieldName, BigDecimal value) throws IOException {
    writeFieldName(fieldName);
    writeNumber(value);
  }
  
  public void writeArrayFieldStart(String fieldName) throws IOException {
    writeFieldName(fieldName);
    writeStartArray();
  }
  
  public void writeObjectFieldStart(String fieldName) throws IOException {
    writeFieldName(fieldName);
    writeStartObject();
  }
  
  public void writePOJOField(String fieldName, Object pojo) throws IOException {
    writeObjectField(fieldName, pojo);
  }
  
  public void writeObjectField(String fieldName, Object pojo) throws IOException {
    writeFieldName(fieldName);
    writeObject(pojo);
  }
  
  public void writeOmittedField(String fieldName) throws IOException {}
  
  public void copyCurrentEvent(JsonParser p) throws IOException {
    JsonToken t = p.currentToken();
    int token = (t == null) ? -1 : t.id();
    switch (token) {
      case -1:
        _reportError("No current event to copy");
        return;
      case 1:
        writeStartObject();
        return;
      case 2:
        writeEndObject();
        return;
      case 3:
        writeStartArray();
        return;
      case 4:
        writeEndArray();
        return;
      case 5:
        writeFieldName(p.getCurrentName());
        return;
      case 6:
        _copyCurrentStringValue(p);
        return;
      case 7:
        _copyCurrentIntValue(p);
        return;
      case 8:
        _copyCurrentFloatValue(p);
        return;
      case 9:
        writeBoolean(true);
        return;
      case 10:
        writeBoolean(false);
        return;
      case 11:
        writeNull();
        return;
      case 12:
        writeObject(p.getEmbeddedObject());
        return;
    } 
    throw new IllegalStateException("Internal error: unknown current token, " + t);
  }
  
  public void copyCurrentEventExact(JsonParser p) throws IOException {
    JsonToken t = p.currentToken();
    int token = (t == null) ? -1 : t.id();
    switch (token) {
      case -1:
        _reportError("No current event to copy");
        return;
      case 1:
        writeStartObject();
        return;
      case 2:
        writeEndObject();
        return;
      case 3:
        writeStartArray();
        return;
      case 4:
        writeEndArray();
        return;
      case 5:
        writeFieldName(p.getCurrentName());
        return;
      case 6:
        _copyCurrentStringValue(p);
        return;
      case 7:
        _copyCurrentIntValue(p);
        return;
      case 8:
        _copyCurrentFloatValueExact(p);
        return;
      case 9:
        writeBoolean(true);
        return;
      case 10:
        writeBoolean(false);
        return;
      case 11:
        writeNull();
        return;
      case 12:
        writeObject(p.getEmbeddedObject());
        return;
    } 
    throw new IllegalStateException("Internal error: unknown current token, " + t);
  }
  
  public void copyCurrentStructure(JsonParser p) throws IOException {
    JsonToken t = p.currentToken();
    int id = (t == null) ? -1 : t.id();
    if (id == 5) {
      writeFieldName(p.getCurrentName());
      t = p.nextToken();
      id = (t == null) ? -1 : t.id();
    } 
    switch (id) {
      case 1:
        writeStartObject();
        _copyCurrentContents(p);
        return;
      case 3:
        writeStartArray();
        _copyCurrentContents(p);
        return;
    } 
    copyCurrentEvent(p);
  }
  
  protected void _copyCurrentContents(JsonParser p) throws IOException {
    int depth = 1;
    JsonToken t;
    while ((t = p.nextToken()) != null) {
      switch (t.id()) {
        case 5:
          writeFieldName(p.getCurrentName());
          continue;
        case 3:
          writeStartArray();
          depth++;
          continue;
        case 1:
          writeStartObject();
          depth++;
          continue;
        case 4:
          writeEndArray();
          if (--depth == 0)
            return; 
          continue;
        case 2:
          writeEndObject();
          if (--depth == 0)
            return; 
          continue;
        case 6:
          _copyCurrentStringValue(p);
          continue;
        case 7:
          _copyCurrentIntValue(p);
          continue;
        case 8:
          _copyCurrentFloatValue(p);
          continue;
        case 9:
          writeBoolean(true);
          continue;
        case 10:
          writeBoolean(false);
          continue;
        case 11:
          writeNull();
          continue;
        case 12:
          writeObject(p.getEmbeddedObject());
          continue;
      } 
      throw new IllegalStateException("Internal error: unknown current token, " + t);
    } 
  }
  
  protected void _copyCurrentFloatValue(JsonParser p) throws IOException {
    JsonParser.NumberType t = p.getNumberType();
    if (t == JsonParser.NumberType.BIG_DECIMAL) {
      writeNumber(p.getDecimalValue());
    } else if (t == JsonParser.NumberType.FLOAT) {
      writeNumber(p.getFloatValue());
    } else {
      writeNumber(p.getDoubleValue());
    } 
  }
  
  protected void _copyCurrentFloatValueExact(JsonParser p) throws IOException {
    Number n = p.getNumberValueExact();
    if (n instanceof BigDecimal) {
      writeNumber((BigDecimal)n);
    } else if (n instanceof Double) {
      writeNumber(n.doubleValue());
    } else {
      writeNumber(n.floatValue());
    } 
  }
  
  protected void _copyCurrentIntValue(JsonParser p) throws IOException {
    JsonParser.NumberType n = p.getNumberType();
    if (n == JsonParser.NumberType.INT) {
      writeNumber(p.getIntValue());
    } else if (n == JsonParser.NumberType.LONG) {
      writeNumber(p.getLongValue());
    } else {
      writeNumber(p.getBigIntegerValue());
    } 
  }
  
  protected void _copyCurrentStringValue(JsonParser p) throws IOException {
    if (p.hasTextCharacters()) {
      writeString(p.getTextCharacters(), p.getTextOffset(), p.getTextLength());
    } else {
      writeString(p.getText());
    } 
  }
  
  public abstract void flush() throws IOException;
  
  public abstract boolean isClosed();
  
  public abstract void close() throws IOException;
  
  protected void _reportError(String msg) throws JsonGenerationException {
    throw new JsonGenerationException(msg, this);
  }
  
  protected final void _throwInternal() {
    VersionUtil.throwInternal();
  }
  
  protected void _reportUnsupportedOperation() {
    throw new UnsupportedOperationException("Operation not supported by generator of type " + getClass().getName());
  }
  
  protected final void _verifyOffsets(int arrayLength, int offset, int length) {
    if (offset < 0 || offset + length > arrayLength)
      throw new IllegalArgumentException(String.format("invalid argument(s) (offset=%d, length=%d) for input array of %d element", new Object[] { Integer.valueOf(offset), Integer.valueOf(length), Integer.valueOf(arrayLength) })); 
  }
  
  protected void _writeSimpleObject(Object value) throws IOException {
    if (value == null) {
      writeNull();
      return;
    } 
    if (value instanceof String) {
      writeString((String)value);
      return;
    } 
    if (value instanceof Number) {
      Number n = (Number)value;
      if (n instanceof Integer) {
        writeNumber(n.intValue());
        return;
      } 
      if (n instanceof Long) {
        writeNumber(n.longValue());
        return;
      } 
      if (n instanceof Double) {
        writeNumber(n.doubleValue());
        return;
      } 
      if (n instanceof Float) {
        writeNumber(n.floatValue());
        return;
      } 
      if (n instanceof Short) {
        writeNumber(n.shortValue());
        return;
      } 
      if (n instanceof Byte) {
        writeNumber((short)n.byteValue());
        return;
      } 
      if (n instanceof BigInteger) {
        writeNumber((BigInteger)n);
        return;
      } 
      if (n instanceof BigDecimal) {
        writeNumber((BigDecimal)n);
        return;
      } 
      if (n instanceof AtomicInteger) {
        writeNumber(((AtomicInteger)n).get());
        return;
      } 
      if (n instanceof AtomicLong) {
        writeNumber(((AtomicLong)n).get());
        return;
      } 
    } else {
      if (value instanceof byte[]) {
        writeBinary((byte[])value);
        return;
      } 
      if (value instanceof Boolean) {
        writeBoolean(((Boolean)value).booleanValue());
        return;
      } 
      if (value instanceof AtomicBoolean) {
        writeBoolean(((AtomicBoolean)value).get());
        return;
      } 
    } 
    throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + value
        .getClass().getName() + ")");
  }
}
