package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class UUIDSerializer extends StdScalarSerializer<UUID> implements ContextualSerializer {
  static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
  
  protected final Boolean _asBinary;
  
  public UUIDSerializer() {
    this((Boolean)null);
  }
  
  protected UUIDSerializer(Boolean asBinary) {
    super(UUID.class);
    this._asBinary = asBinary;
  }
  
  public boolean isEmpty(SerializerProvider prov, UUID value) {
    return (value.getLeastSignificantBits() == 0L && value
      .getMostSignificantBits() == 0L);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
    JsonFormat.Value format = findFormatOverrides(serializers, property, 
        handledType());
    Boolean asBinary = null;
    if (format != null) {
      JsonFormat.Shape shape = format.getShape();
      if (shape == JsonFormat.Shape.BINARY) {
        asBinary = Boolean.valueOf(true);
      } else if (shape == JsonFormat.Shape.STRING) {
        asBinary = Boolean.valueOf(false);
      } 
    } 
    if (!Objects.equals(asBinary, this._asBinary))
      return new UUIDSerializer(asBinary); 
    return this;
  }
  
  public void serialize(UUID value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (_writeAsBinary(gen)) {
      gen.writeBinary(_asBytes(value));
      return;
    } 
    char[] ch = new char[36];
    long msb = value.getMostSignificantBits();
    _appendInt((int)(msb >> 32L), ch, 0);
    ch[8] = '-';
    int i = (int)msb;
    _appendShort(i >>> 16, ch, 9);
    ch[13] = '-';
    _appendShort(i, ch, 14);
    ch[18] = '-';
    long lsb = value.getLeastSignificantBits();
    _appendShort((int)(lsb >>> 48L), ch, 19);
    ch[23] = '-';
    _appendShort((int)(lsb >>> 32L), ch, 24);
    _appendInt((int)lsb, ch, 28);
    gen.writeString(ch, 0, 36);
  }
  
  protected boolean _writeAsBinary(JsonGenerator g) {
    if (this._asBinary != null)
      return this._asBinary.booleanValue(); 
    return (!(g instanceof com.fasterxml.jackson.databind.util.TokenBuffer) && g.canWriteBinaryNatively());
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
    visitStringFormat(visitor, typeHint, JsonValueFormat.UUID);
  }
  
  private static void _appendInt(int bits, char[] ch, int offset) {
    _appendShort(bits >> 16, ch, offset);
    _appendShort(bits, ch, offset + 4);
  }
  
  private static void _appendShort(int bits, char[] ch, int offset) {
    ch[offset] = HEX_CHARS[bits >> 12 & 0xF];
    ch[++offset] = HEX_CHARS[bits >> 8 & 0xF];
    ch[++offset] = HEX_CHARS[bits >> 4 & 0xF];
    ch[++offset] = HEX_CHARS[bits & 0xF];
  }
  
  private static final byte[] _asBytes(UUID uuid) {
    byte[] buffer = new byte[16];
    long hi = uuid.getMostSignificantBits();
    long lo = uuid.getLeastSignificantBits();
    _appendInt((int)(hi >> 32L), buffer, 0);
    _appendInt((int)hi, buffer, 4);
    _appendInt((int)(lo >> 32L), buffer, 8);
    _appendInt((int)lo, buffer, 12);
    return buffer;
  }
  
  private static final void _appendInt(int value, byte[] buffer, int offset) {
    buffer[offset] = (byte)(value >> 24);
    buffer[++offset] = (byte)(value >> 16);
    buffer[++offset] = (byte)(value >> 8);
    buffer[++offset] = (byte)value;
  }
}
