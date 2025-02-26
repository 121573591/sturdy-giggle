package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerNode extends NumericNode {
  private static final BigInteger MIN_INTEGER = BigInteger.valueOf(-2147483648L);
  
  private static final BigInteger MAX_INTEGER = BigInteger.valueOf(2147483647L);
  
  private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
  
  private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
  
  protected final BigInteger _value;
  
  public BigIntegerNode(BigInteger v) {
    this._value = v;
  }
  
  public static BigIntegerNode valueOf(BigInteger v) {
    return new BigIntegerNode(v);
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NUMBER_INT;
  }
  
  public JsonParser.NumberType numberType() {
    return JsonParser.NumberType.BIG_INTEGER;
  }
  
  public boolean isIntegralNumber() {
    return true;
  }
  
  public boolean isBigInteger() {
    return true;
  }
  
  public boolean canConvertToInt() {
    return (this._value.compareTo(MIN_INTEGER) >= 0 && this._value.compareTo(MAX_INTEGER) <= 0);
  }
  
  public boolean canConvertToLong() {
    return (this._value.compareTo(MIN_LONG) >= 0 && this._value.compareTo(MAX_LONG) <= 0);
  }
  
  public Number numberValue() {
    return this._value;
  }
  
  public short shortValue() {
    return this._value.shortValue();
  }
  
  public int intValue() {
    return this._value.intValue();
  }
  
  public long longValue() {
    return this._value.longValue();
  }
  
  public BigInteger bigIntegerValue() {
    return this._value;
  }
  
  public float floatValue() {
    return this._value.floatValue();
  }
  
  public double doubleValue() {
    return this._value.doubleValue();
  }
  
  public BigDecimal decimalValue() {
    return new BigDecimal(this._value);
  }
  
  public String asText() {
    return this._value.toString();
  }
  
  public boolean asBoolean(boolean defaultValue) {
    return !BigInteger.ZERO.equals(this._value);
  }
  
  public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeNumber(this._value);
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (!(o instanceof BigIntegerNode))
      return false; 
    return ((BigIntegerNode)o)._value.equals(this._value);
  }
  
  public int hashCode() {
    return this._value.hashCode();
  }
}
