package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class UUIDDeserializer extends FromStringDeserializer<UUID> {
  private static final long serialVersionUID = 1L;
  
  static final int[] HEX_DIGITS = new int[127];
  
  static {
    Arrays.fill(HEX_DIGITS, -1);
    int i;
    for (i = 0; i < 10; ) {
      HEX_DIGITS[48 + i] = i;
      i++;
    } 
    for (i = 0; i < 6; i++) {
      HEX_DIGITS[97 + i] = 10 + i;
      HEX_DIGITS[65 + i] = 10 + i;
    } 
  }
  
  public UUIDDeserializer() {
    super(UUID.class);
  }
  
  public Object getEmptyValue(DeserializationContext ctxt) {
    return new UUID(0L, 0L);
  }
  
  protected UUID _deserialize(String id, DeserializationContext ctxt) throws IOException {
    if (id.length() != 36) {
      if (id.length() == 24) {
        byte[] stuff = Base64Variants.getDefaultVariant().decode(id);
        return _fromBytes(stuff, ctxt);
      } 
      return _badFormat(id, ctxt);
    } 
    if (id.charAt(8) != '-' || id.charAt(13) != '-' || id
      .charAt(18) != '-' || id.charAt(23) != '-')
      _badFormat(id, ctxt); 
    long l1 = intFromChars(id, 0, ctxt);
    l1 <<= 32L;
    long l2 = shortFromChars(id, 9, ctxt) << 16L;
    l2 |= shortFromChars(id, 14, ctxt);
    long hi = l1 + l2;
    int i1 = shortFromChars(id, 19, ctxt) << 16 | shortFromChars(id, 24, ctxt);
    l1 = i1;
    l1 <<= 32L;
    l2 = intFromChars(id, 28, ctxt);
    l2 = l2 << 32L >>> 32L;
    long lo = l1 | l2;
    return new UUID(hi, lo);
  }
  
  protected UUID _deserializeEmbedded(Object ob, DeserializationContext ctxt) throws IOException {
    if (ob instanceof byte[])
      return _fromBytes((byte[])ob, ctxt); 
    return super._deserializeEmbedded(ob, ctxt);
  }
  
  private UUID _badFormat(String uuidStr, DeserializationContext ctxt) throws IOException {
    return (UUID)ctxt.handleWeirdStringValue(handledType(), uuidStr, "UUID has to be represented by standard 36-char representation", new Object[0]);
  }
  
  int intFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
    return (byteFromChars(str, index, ctxt) << 24) + (
      byteFromChars(str, index + 2, ctxt) << 16) + (
      byteFromChars(str, index + 4, ctxt) << 8) + 
      byteFromChars(str, index + 6, ctxt);
  }
  
  int shortFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
    return (byteFromChars(str, index, ctxt) << 8) + byteFromChars(str, index + 2, ctxt);
  }
  
  int byteFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
    char c1 = str.charAt(index);
    char c2 = str.charAt(index + 1);
    if (c1 <= '' && c2 <= '') {
      int hex = HEX_DIGITS[c1] << 4 | HEX_DIGITS[c2];
      if (hex >= 0)
        return hex; 
    } 
    if (c1 > '' || HEX_DIGITS[c1] < 0)
      return _badChar(str, index, ctxt, c1); 
    return _badChar(str, index + 1, ctxt, c2);
  }
  
  int _badChar(String uuidStr, int index, DeserializationContext ctxt, char c) throws JsonMappingException {
    throw ctxt.weirdStringException(uuidStr, handledType(), 
        String.format("Non-hex character '%c' (value 0x%s), not valid for UUID String", new Object[] { Character.valueOf(c), Integer.toHexString(c) }));
  }
  
  private UUID _fromBytes(byte[] bytes, DeserializationContext ctxt) throws JsonMappingException {
    if (bytes.length != 16)
      throw InvalidFormatException.from(ctxt.getParser(), "Can only construct UUIDs from byte[16]; got " + bytes.length + " bytes", bytes, 
          
          handledType()); 
    return new UUID(_long(bytes, 0), _long(bytes, 8));
  }
  
  private static long _long(byte[] b, int offset) {
    long l1 = _int(b, offset) << 32L;
    long l2 = _int(b, offset + 4);
    l2 = l2 << 32L >>> 32L;
    return l1 | l2;
  }
  
  private static int _int(byte[] b, int offset) {
    return b[offset] << 24 | (b[offset + 1] & 0xFF) << 16 | (b[offset + 2] & 0xFF) << 8 | b[offset + 3] & 0xFF;
  }
}
