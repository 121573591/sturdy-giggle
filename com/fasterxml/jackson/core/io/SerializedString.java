package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.SerializableString;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class SerializedString implements SerializableString, Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final JsonStringEncoder JSON_ENCODER = JsonStringEncoder.getInstance();
  
  protected final String _value;
  
  protected byte[] _quotedUTF8Ref;
  
  protected byte[] _unquotedUTF8Ref;
  
  protected char[] _quotedChars;
  
  protected transient String _jdkSerializeValue;
  
  public SerializedString(String v) {
    if (v == null)
      throw new IllegalStateException("Null String illegal for SerializedString"); 
    this._value = v;
  }
  
  private void readObject(ObjectInputStream in) throws IOException {
    this._jdkSerializeValue = in.readUTF();
  }
  
  private void writeObject(ObjectOutputStream out) throws IOException {
    out.writeUTF(this._value);
  }
  
  protected Object readResolve() {
    return new SerializedString(this._jdkSerializeValue);
  }
  
  public final String getValue() {
    return this._value;
  }
  
  public final int charLength() {
    return this._value.length();
  }
  
  public final char[] asQuotedChars() {
    char[] result = this._quotedChars;
    if (result == null)
      this._quotedChars = result = JSON_ENCODER.quoteAsString(this._value); 
    return result;
  }
  
  public final byte[] asQuotedUTF8() {
    byte[] result = this._quotedUTF8Ref;
    if (result == null)
      this._quotedUTF8Ref = result = JSON_ENCODER.quoteAsUTF8(this._value); 
    return result;
  }
  
  public final byte[] asUnquotedUTF8() {
    byte[] result = this._unquotedUTF8Ref;
    if (result == null)
      this._unquotedUTF8Ref = result = JSON_ENCODER.encodeAsUTF8(this._value); 
    return result;
  }
  
  public int appendQuoted(char[] buffer, int offset) {
    char[] result = this._quotedChars;
    if (result == null)
      this._quotedChars = result = JSON_ENCODER.quoteAsString(this._value); 
    int length = result.length;
    if (offset + length > buffer.length)
      return -1; 
    System.arraycopy(result, 0, buffer, offset, length);
    return length;
  }
  
  public int appendQuotedUTF8(byte[] buffer, int offset) {
    byte[] result = this._quotedUTF8Ref;
    if (result == null)
      this._quotedUTF8Ref = result = JSON_ENCODER.quoteAsUTF8(this._value); 
    int length = result.length;
    if (offset + length > buffer.length)
      return -1; 
    System.arraycopy(result, 0, buffer, offset, length);
    return length;
  }
  
  public int appendUnquoted(char[] buffer, int offset) {
    String str = this._value;
    int length = str.length();
    if (offset + length > buffer.length)
      return -1; 
    str.getChars(0, length, buffer, offset);
    return length;
  }
  
  public int appendUnquotedUTF8(byte[] buffer, int offset) {
    byte[] result = this._unquotedUTF8Ref;
    if (result == null)
      this._unquotedUTF8Ref = result = JSON_ENCODER.encodeAsUTF8(this._value); 
    int length = result.length;
    if (offset + length > buffer.length)
      return -1; 
    System.arraycopy(result, 0, buffer, offset, length);
    return length;
  }
  
  public int writeQuotedUTF8(OutputStream out) throws IOException {
    byte[] result = this._quotedUTF8Ref;
    if (result == null)
      this._quotedUTF8Ref = result = JSON_ENCODER.quoteAsUTF8(this._value); 
    int length = result.length;
    out.write(result, 0, length);
    return length;
  }
  
  public int writeUnquotedUTF8(OutputStream out) throws IOException {
    byte[] result = this._unquotedUTF8Ref;
    if (result == null)
      this._unquotedUTF8Ref = result = JSON_ENCODER.encodeAsUTF8(this._value); 
    int length = result.length;
    out.write(result, 0, length);
    return length;
  }
  
  public int putQuotedUTF8(ByteBuffer buffer) {
    byte[] result = this._quotedUTF8Ref;
    if (result == null)
      this._quotedUTF8Ref = result = JSON_ENCODER.quoteAsUTF8(this._value); 
    int length = result.length;
    if (length > buffer.remaining())
      return -1; 
    buffer.put(result, 0, length);
    return length;
  }
  
  public int putUnquotedUTF8(ByteBuffer buffer) {
    byte[] result = this._unquotedUTF8Ref;
    if (result == null)
      this._unquotedUTF8Ref = result = JSON_ENCODER.encodeAsUTF8(this._value); 
    int length = result.length;
    if (length > buffer.remaining())
      return -1; 
    buffer.put(result, 0, length);
    return length;
  }
  
  public final String toString() {
    return this._value;
  }
  
  public final int hashCode() {
    return this._value.hashCode();
  }
  
  public final boolean equals(Object o) {
    if (o == this)
      return true; 
    if (o == null || o.getClass() != getClass())
      return false; 
    SerializedString other = (SerializedString)o;
    return this._value.equals(other._value);
  }
}
