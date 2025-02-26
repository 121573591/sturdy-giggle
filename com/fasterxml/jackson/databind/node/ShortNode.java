package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ShortNode extends NumericNode {
  protected final short _value;
  
  public ShortNode(short v) {
    this._value = v;
  }
  
  public static ShortNode valueOf(short l) {
    return new ShortNode(l);
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NUMBER_INT;
  }
  
  public JsonParser.NumberType numberType() {
    return JsonParser.NumberType.INT;
  }
  
  public boolean isIntegralNumber() {
    return true;
  }
  
  public boolean isShort() {
    return true;
  }
  
  public boolean canConvertToInt() {
    return true;
  }
  
  public boolean canConvertToLong() {
    return true;
  }
  
  public Number numberValue() {
    return Short.valueOf(this._value);
  }
  
  public short shortValue() {
    return this._value;
  }
  
  public int intValue() {
    return this._value;
  }
  
  public long longValue() {
    return this._value;
  }
  
  public float floatValue() {
    return this._value;
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
    return (this._value != 0);
  }
  
  public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeNumber(this._value);
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (o instanceof ShortNode)
      return (((ShortNode)o)._value == this._value); 
    return false;
  }
  
  public int hashCode() {
    return this._value;
  }
}
