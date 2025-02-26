package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class LongNode extends NumericNode {
  protected final long _value;
  
  public LongNode(long v) {
    this._value = v;
  }
  
  public static LongNode valueOf(long l) {
    return new LongNode(l);
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NUMBER_INT;
  }
  
  public JsonParser.NumberType numberType() {
    return JsonParser.NumberType.LONG;
  }
  
  public boolean isIntegralNumber() {
    return true;
  }
  
  public boolean isLong() {
    return true;
  }
  
  public boolean canConvertToInt() {
    return (this._value >= -2147483648L && this._value <= 2147483647L);
  }
  
  public boolean canConvertToLong() {
    return true;
  }
  
  public Number numberValue() {
    return Long.valueOf(this._value);
  }
  
  public short shortValue() {
    return (short)(int)this._value;
  }
  
  public int intValue() {
    return (int)this._value;
  }
  
  public long longValue() {
    return this._value;
  }
  
  public float floatValue() {
    return (float)this._value;
  }
  
  public double doubleValue() {
    return this._value;
  }
  
  public BigDecimal decimalValue() {
    return BigDecimal.valueOf(this._value);
  }
  
  public BigInteger bigIntegerValue() {
    return BigInteger.valueOf(this._value);
  }
  
  public String asText() {
    return NumberOutput.toString(this._value);
  }
  
  public boolean asBoolean(boolean defaultValue) {
    return (this._value != 0L);
  }
  
  public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeNumber(this._value);
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (o instanceof LongNode)
      return (((LongNode)o)._value == this._value); 
    return false;
  }
  
  public int hashCode() {
    return (int)this._value ^ (int)(this._value >> 32L);
  }
}
