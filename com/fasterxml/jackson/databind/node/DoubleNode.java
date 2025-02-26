package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class DoubleNode extends NumericNode {
  protected final double _value;
  
  public DoubleNode(double v) {
    this._value = v;
  }
  
  public static DoubleNode valueOf(double v) {
    return new DoubleNode(v);
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NUMBER_FLOAT;
  }
  
  public JsonParser.NumberType numberType() {
    return JsonParser.NumberType.DOUBLE;
  }
  
  public boolean isFloatingPointNumber() {
    return true;
  }
  
  public boolean isDouble() {
    return true;
  }
  
  public boolean canConvertToInt() {
    return (this._value >= -2.147483648E9D && this._value <= 2.147483647E9D);
  }
  
  public boolean canConvertToLong() {
    return (this._value >= -9.223372036854776E18D && this._value <= 9.223372036854776E18D);
  }
  
  public boolean canConvertToExactIntegral() {
    return (!Double.isNaN(this._value) && !Double.isInfinite(this._value) && this._value == 
      Math.rint(this._value));
  }
  
  public Number numberValue() {
    return Double.valueOf(this._value);
  }
  
  public short shortValue() {
    return (short)(int)this._value;
  }
  
  public int intValue() {
    return (int)this._value;
  }
  
  public long longValue() {
    return (long)this._value;
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
    return decimalValue().toBigInteger();
  }
  
  public String asText() {
    return NumberOutput.toString(this._value);
  }
  
  public boolean isNaN() {
    return (Double.isNaN(this._value) || Double.isInfinite(this._value));
  }
  
  public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeNumber(this._value);
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (o instanceof DoubleNode) {
      double otherValue = ((DoubleNode)o)._value;
      return (Double.compare(this._value, otherValue) == 0);
    } 
    return false;
  }
  
  public int hashCode() {
    long l = Double.doubleToLongBits(this._value);
    return (int)l ^ (int)(l >> 32L);
  }
}
