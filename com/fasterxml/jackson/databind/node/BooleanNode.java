package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class BooleanNode extends ValueNode {
  private static final long serialVersionUID = 2L;
  
  public static final BooleanNode TRUE = new BooleanNode(true);
  
  public static final BooleanNode FALSE = new BooleanNode(false);
  
  private final boolean _value;
  
  protected BooleanNode(boolean v) {
    this._value = v;
  }
  
  protected Object readResolve() {
    return this._value ? TRUE : FALSE;
  }
  
  public static BooleanNode getTrue() {
    return TRUE;
  }
  
  public static BooleanNode getFalse() {
    return FALSE;
  }
  
  public static BooleanNode valueOf(boolean b) {
    return b ? TRUE : FALSE;
  }
  
  public JsonNodeType getNodeType() {
    return JsonNodeType.BOOLEAN;
  }
  
  public JsonToken asToken() {
    return this._value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
  }
  
  public boolean booleanValue() {
    return this._value;
  }
  
  public String asText() {
    return this._value ? "true" : "false";
  }
  
  public boolean asBoolean() {
    return this._value;
  }
  
  public boolean asBoolean(boolean defaultValue) {
    return this._value;
  }
  
  public int asInt(int defaultValue) {
    return this._value ? 1 : 0;
  }
  
  public long asLong(long defaultValue) {
    return this._value ? 1L : 0L;
  }
  
  public double asDouble(double defaultValue) {
    return this._value ? 1.0D : 0.0D;
  }
  
  public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
    g.writeBoolean(this._value);
  }
  
  public int hashCode() {
    return this._value ? 3 : 1;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null)
      return false; 
    if (!(o instanceof BooleanNode))
      return false; 
    return (this._value == ((BooleanNode)o)._value);
  }
}
