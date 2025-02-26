package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UTF8JsonGenerator extends JsonGeneratorImpl {
  private static final byte BYTE_u = 117;
  
  private static final byte BYTE_0 = 48;
  
  private static final byte BYTE_LBRACKET = 91;
  
  private static final byte BYTE_RBRACKET = 93;
  
  private static final byte BYTE_LCURLY = 123;
  
  private static final byte BYTE_RCURLY = 125;
  
  private static final byte BYTE_BACKSLASH = 92;
  
  private static final byte BYTE_COMMA = 44;
  
  private static final byte BYTE_COLON = 58;
  
  private static final int MAX_BYTES_TO_BUFFER = 512;
  
  private static final byte[] HEX_BYTES_UPPER = CharTypes.copyHexBytes(true);
  
  private static final byte[] HEX_BYTES_LOWER = CharTypes.copyHexBytes(false);
  
  private static final byte[] NULL_BYTES = new byte[] { 110, 117, 108, 108 };
  
  private static final byte[] TRUE_BYTES = new byte[] { 116, 114, 117, 101 };
  
  private static final byte[] FALSE_BYTES = new byte[] { 102, 97, 108, 115, 101 };
  
  protected final OutputStream _outputStream;
  
  protected byte _quoteChar;
  
  protected byte[] _outputBuffer;
  
  protected int _outputTail;
  
  protected final int _outputEnd;
  
  protected final int _outputMaxContiguous;
  
  protected char[] _charBuffer;
  
  protected final int _charBufferLength;
  
  protected byte[] _entityBuffer;
  
  protected boolean _bufferRecyclable;
  
  public UTF8JsonGenerator(IOContext ctxt, int features, ObjectCodec codec, OutputStream out, char quoteChar) {
    super(ctxt, features, codec);
    this._outputStream = out;
    this._quoteChar = (byte)quoteChar;
    if (quoteChar != '"')
      this._outputEscapes = CharTypes.get7BitOutputEscapes(quoteChar); 
    this._bufferRecyclable = true;
    this._outputBuffer = ctxt.allocWriteEncodingBuffer();
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = this._outputEnd >> 3;
    this._charBuffer = ctxt.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
    if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII))
      setHighestNonEscapedChar(127); 
  }
  
  public UTF8JsonGenerator(IOContext ctxt, int features, ObjectCodec codec, OutputStream out, char quoteChar, byte[] outputBuffer, int outputOffset, boolean bufferRecyclable) {
    super(ctxt, features, codec);
    this._outputStream = out;
    this._quoteChar = (byte)quoteChar;
    if (quoteChar != '"')
      this._outputEscapes = CharTypes.get7BitOutputEscapes(quoteChar); 
    this._bufferRecyclable = bufferRecyclable;
    this._outputTail = outputOffset;
    this._outputBuffer = outputBuffer;
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = this._outputEnd >> 3;
    this._charBuffer = ctxt.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
  }
  
  @Deprecated
  public UTF8JsonGenerator(IOContext ctxt, int features, ObjectCodec codec, OutputStream out) {
    this(ctxt, features, codec, out, '"');
  }
  
  @Deprecated
  public UTF8JsonGenerator(IOContext ctxt, int features, ObjectCodec codec, OutputStream out, byte[] outputBuffer, int outputOffset, boolean bufferRecyclable) {
    this(ctxt, features, codec, out, '"', outputBuffer, outputOffset, bufferRecyclable);
  }
  
  public Object getOutputTarget() {
    return this._outputStream;
  }
  
  public int getOutputBuffered() {
    return this._outputTail;
  }
  
  public void writeFieldName(String name) throws IOException {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(name);
      return;
    } 
    int status = this._writeContext.writeFieldName(name);
    if (status == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (status == 1) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 44;
    } 
    if (this._cfgUnqNames) {
      _writeStringSegments(name, false);
      return;
    } 
    int len = name.length();
    if (len > this._charBufferLength) {
      _writeStringSegments(name, true);
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    if (len <= this._outputMaxContiguous) {
      if (this._outputTail + len > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(name, 0, len);
    } else {
      _writeStringSegments(name, 0, len);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeFieldName(SerializableString name) throws IOException {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(name);
      return;
    } 
    int status = this._writeContext.writeFieldName(name.getValue());
    if (status == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (status == 1) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 44;
    } 
    if (this._cfgUnqNames) {
      _writeUnq(name);
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    int len = name.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeBytes(name.asQuotedUTF8());
    } else {
      this._outputTail += len;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  private final void _writeUnq(SerializableString name) throws IOException {
    int len = name.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeBytes(name.asQuotedUTF8());
    } else {
      this._outputTail += len;
    } 
  }
  
  public final void writeStartArray() throws IOException {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext();
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartArray((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 91;
    } 
  }
  
  public final void writeStartArray(Object currentValue) throws IOException {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext(currentValue);
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartArray((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 91;
    } 
  }
  
  public void writeStartArray(Object currentValue, int size) throws IOException {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext(currentValue);
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartArray((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 91;
    } 
  }
  
  public final void writeEndArray() throws IOException {
    if (!this._writeContext.inArray())
      _reportError("Current context not Array but " + this._writeContext.typeDesc()); 
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndArray((JsonGenerator)this, this._writeContext.getEntryCount());
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 93;
    } 
    this._writeContext = this._writeContext.clearAndGetParent();
  }
  
  public final void writeStartObject() throws IOException {
    _verifyValueWrite("start an object");
    this._writeContext = this._writeContext.createChildObjectContext();
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartObject((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 123;
    } 
  }
  
  public void writeStartObject(Object forValue) throws IOException {
    _verifyValueWrite("start an object");
    JsonWriteContext ctxt = this._writeContext.createChildObjectContext(forValue);
    streamWriteConstraints().validateNestingDepth(ctxt.getNestingDepth());
    this._writeContext = ctxt;
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartObject((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 123;
    } 
  }
  
  public void writeStartObject(Object forValue, int size) throws IOException {
    writeStartObject(forValue);
  }
  
  public final void writeEndObject() throws IOException {
    if (!this._writeContext.inObject())
      _reportError("Current context not Object but " + this._writeContext.typeDesc()); 
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndObject((JsonGenerator)this, this._writeContext.getEntryCount());
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = 125;
    } 
    this._writeContext = this._writeContext.clearAndGetParent();
  }
  
  protected final void _writePPFieldName(String name) throws IOException {
    int status = this._writeContext.writeFieldName(name);
    if (status == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (status == 1) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator((JsonGenerator)this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries((JsonGenerator)this);
    } 
    if (this._cfgUnqNames) {
      _writeStringSegments(name, false);
      return;
    } 
    int len = name.length();
    if (len > this._charBufferLength) {
      _writeStringSegments(name, true);
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    name.getChars(0, len, this._charBuffer, 0);
    if (len <= this._outputMaxContiguous) {
      if (this._outputTail + len > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(this._charBuffer, 0, len);
    } else {
      _writeStringSegments(this._charBuffer, 0, len);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  protected final void _writePPFieldName(SerializableString name) throws IOException {
    int status = this._writeContext.writeFieldName(name.getValue());
    if (status == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (status == 1) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator((JsonGenerator)this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries((JsonGenerator)this);
    } 
    boolean addQuotes = !this._cfgUnqNames;
    if (addQuotes) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = this._quoteChar;
    } 
    int len = name.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeBytes(name.asQuotedUTF8());
    } else {
      this._outputTail += len;
    } 
    if (addQuotes) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = this._quoteChar;
    } 
  }
  
  public void writeString(String text) throws IOException {
    _verifyValueWrite("write a string");
    if (text == null) {
      _writeNull();
      return;
    } 
    int len = text.length();
    if (len > this._outputMaxContiguous) {
      _writeStringSegments(text, true);
      return;
    } 
    if (this._outputTail + len >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    _writeStringSegment(text, 0, len);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeString(Reader reader, int len) throws IOException {
    _verifyValueWrite("write a string");
    if (reader == null) {
      _reportError("null reader");
      return;
    } 
    int toRead = (len >= 0) ? len : Integer.MAX_VALUE;
    char[] buf = this._charBuffer;
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    while (toRead > 0) {
      int toReadNow = Math.min(toRead, buf.length);
      int numRead = reader.read(buf, 0, toReadNow);
      if (numRead <= 0)
        break; 
      if (this._outputTail + len >= this._outputEnd)
        _flushBuffer(); 
      _writeStringSegments(buf, 0, numRead);
      toRead -= numRead;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    if (toRead > 0 && len >= 0)
      _reportError("Didn't read enough from reader"); 
  }
  
  public void writeString(char[] text, int offset, int len) throws IOException {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    if (len <= this._outputMaxContiguous) {
      if (this._outputTail + len > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(text, offset, len);
    } else {
      _writeStringSegments(text, offset, len);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public final void writeString(SerializableString text) throws IOException {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    int len = text.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeBytes(text.asQuotedUTF8());
    } else {
      this._outputTail += len;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeRawUTF8String(byte[] text, int offset, int len) throws IOException {
    _checkRangeBoundsForByteArray(text, offset, len);
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    _writeBytes(text, offset, len);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeUTF8String(byte[] text, int offset, int len) throws IOException {
    _checkRangeBoundsForByteArray(text, offset, len);
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    if (len <= this._outputMaxContiguous) {
      _writeUTF8Segment(text, offset, len);
    } else {
      _writeUTF8Segments(text, offset, len);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeRaw(String text) throws IOException {
    int len = text.length();
    char[] buf = this._charBuffer;
    if (len <= buf.length) {
      text.getChars(0, len, buf, 0);
      writeRaw(buf, 0, len);
    } else {
      writeRaw(text, 0, len);
    } 
  }
  
  public void writeRaw(String text, int offset, int len) throws IOException {
    _checkRangeBoundsForString(text, offset, len);
    char[] buf = this._charBuffer;
    int cbufLen = buf.length;
    if (len <= cbufLen) {
      text.getChars(offset, offset + len, buf, 0);
      writeRaw(buf, 0, len);
      return;
    } 
    int maxChunk = Math.min(cbufLen, (this._outputEnd >> 2) + (this._outputEnd >> 4));
    int maxBytes = maxChunk * 3;
    while (len > 0) {
      int len2 = Math.min(maxChunk, len);
      text.getChars(offset, offset + len2, buf, 0);
      if (this._outputTail + maxBytes > this._outputEnd)
        _flushBuffer(); 
      if (len2 > 1) {
        char ch = buf[len2 - 1];
        if (ch >= '?' && ch <= '?')
          len2--; 
      } 
      _writeRawSegment(buf, 0, len2);
      offset += len2;
      len -= len2;
    } 
  }
  
  public void writeRaw(SerializableString text) throws IOException {
    int len = text.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeBytes(text.asUnquotedUTF8());
    } else {
      this._outputTail += len;
    } 
  }
  
  public void writeRawValue(SerializableString text) throws IOException {
    _verifyValueWrite("write a raw (unencoded) value");
    int len = text.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeBytes(text.asUnquotedUTF8());
    } else {
      this._outputTail += len;
    } 
  }
  
  public final void writeRaw(char[] cbuf, int offset, int len) throws IOException {
    _checkRangeBoundsForCharArray(cbuf, offset, len);
    int len3 = len + len + len;
    if (this._outputTail + len3 > this._outputEnd) {
      if (this._outputEnd < len3) {
        _writeSegmentedRaw(cbuf, offset, len);
        return;
      } 
      _flushBuffer();
    } 
    len += offset;
    while (offset < len) {
      while (true) {
        int ch = cbuf[offset];
        if (ch > 127) {
          ch = cbuf[offset++];
          if (ch < 2048) {
            this._outputBuffer[this._outputTail++] = (byte)(0xC0 | ch >> 6);
            this._outputBuffer[this._outputTail++] = (byte)(0x80 | ch & 0x3F);
            continue;
          } 
          offset = _outputRawMultiByteChar(ch, cbuf, offset, len);
          continue;
        } 
        this._outputBuffer[this._outputTail++] = (byte)ch;
        if (++offset >= len)
          break; 
      } 
    } 
  }
  
  public void writeRaw(char ch) throws IOException {
    if (this._outputTail + 3 >= this._outputEnd)
      _flushBuffer(); 
    byte[] bbuf = this._outputBuffer;
    if (ch <= '') {
      bbuf[this._outputTail++] = (byte)ch;
    } else if (ch < 'ࠀ') {
      bbuf[this._outputTail++] = (byte)(0xC0 | ch >> 6);
      bbuf[this._outputTail++] = (byte)(0x80 | ch & 0x3F);
    } else {
      _outputRawMultiByteChar(ch, (char[])null, 0, 0);
    } 
  }
  
  private final void _writeSegmentedRaw(char[] cbuf, int offset, int len) throws IOException {
    int end = this._outputEnd;
    byte[] bbuf = this._outputBuffer;
    int inputEnd = offset + len;
    while (offset < inputEnd) {
      while (true) {
        int ch = cbuf[offset];
        if (ch >= 128) {
          if (this._outputTail + 3 >= this._outputEnd)
            _flushBuffer(); 
          ch = cbuf[offset++];
          if (ch < 2048) {
            bbuf[this._outputTail++] = (byte)(0xC0 | ch >> 6);
            bbuf[this._outputTail++] = (byte)(0x80 | ch & 0x3F);
            continue;
          } 
          offset = _outputRawMultiByteChar(ch, cbuf, offset, inputEnd);
          continue;
        } 
        if (this._outputTail >= end)
          _flushBuffer(); 
        bbuf[this._outputTail++] = (byte)ch;
        if (++offset >= inputEnd)
          break; 
      } 
    } 
  }
  
  private void _writeRawSegment(char[] cbuf, int offset, int end) throws IOException {
    while (offset < end) {
      while (true) {
        int ch = cbuf[offset];
        if (ch > 127) {
          ch = cbuf[offset++];
          if (ch < 2048) {
            this._outputBuffer[this._outputTail++] = (byte)(0xC0 | ch >> 6);
            this._outputBuffer[this._outputTail++] = (byte)(0x80 | ch & 0x3F);
            continue;
          } 
          offset = _outputRawMultiByteChar(ch, cbuf, offset, end);
          continue;
        } 
        this._outputBuffer[this._outputTail++] = (byte)ch;
        if (++offset >= end)
          break; 
      } 
    } 
  }
  
  public void writeBinary(Base64Variant b64variant, byte[] data, int offset, int len) throws IOException, JsonGenerationException {
    _checkRangeBoundsForByteArray(data, offset, len);
    _verifyValueWrite("write a binary value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    _writeBinary(b64variant, data, offset, offset + len);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException, JsonGenerationException {
    int bytes;
    _verifyValueWrite("write a binary value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    byte[] encodingBuffer = this._ioContext.allocBase64Buffer();
    try {
      if (dataLength < 0) {
        bytes = _writeBinary(b64variant, data, encodingBuffer);
      } else {
        int missing = _writeBinary(b64variant, data, encodingBuffer, dataLength);
        if (missing > 0)
          _reportError("Too few bytes available: missing " + missing + " bytes (out of " + dataLength + ")"); 
        bytes = dataLength;
      } 
    } finally {
      this._ioContext.releaseBase64Buffer(encodingBuffer);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    return bytes;
  }
  
  public void writeNumber(short s) throws IOException {
    _verifyValueWrite("write a number");
    if (this._outputTail + 6 >= this._outputEnd)
      _flushBuffer(); 
    if (this._cfgNumbersAsStrings) {
      _writeQuotedShort(s);
      return;
    } 
    this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
  }
  
  private final void _writeQuotedShort(short s) throws IOException {
    if (this._outputTail + 8 >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeNumber(int i) throws IOException {
    _verifyValueWrite("write a number");
    if (this._outputTail + 11 >= this._outputEnd)
      _flushBuffer(); 
    if (this._cfgNumbersAsStrings) {
      _writeQuotedInt(i);
      return;
    } 
    this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
  }
  
  private final void _writeQuotedInt(int i) throws IOException {
    if (this._outputTail + 13 >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeNumber(long l) throws IOException {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedLong(l);
      return;
    } 
    if (this._outputTail + 21 >= this._outputEnd)
      _flushBuffer(); 
    this._outputTail = NumberOutput.outputLong(l, this._outputBuffer, this._outputTail);
  }
  
  private final void _writeQuotedLong(long l) throws IOException {
    if (this._outputTail + 23 >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    this._outputTail = NumberOutput.outputLong(l, this._outputBuffer, this._outputTail);
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeNumber(BigInteger value) throws IOException {
    _verifyValueWrite("write a number");
    if (value == null) {
      _writeNull();
    } else if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(value.toString());
    } else {
      writeRaw(value.toString());
    } 
  }
  
  public void writeNumber(double d) throws IOException {
    if (this._cfgNumbersAsStrings || (
      NumberOutput.notFinite(d) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS
      .enabledIn(this._features))) {
      writeString(NumberOutput.toString(d, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
      return;
    } 
    _verifyValueWrite("write a number");
    writeRaw(NumberOutput.toString(d, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
  }
  
  public void writeNumber(float f) throws IOException {
    if (this._cfgNumbersAsStrings || (
      NumberOutput.notFinite(f) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS
      .enabledIn(this._features))) {
      writeString(NumberOutput.toString(f, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
      return;
    } 
    _verifyValueWrite("write a number");
    writeRaw(NumberOutput.toString(f, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
  }
  
  public void writeNumber(BigDecimal value) throws IOException {
    _verifyValueWrite("write a number");
    if (value == null) {
      _writeNull();
    } else if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(_asString(value));
    } else {
      writeRaw(_asString(value));
    } 
  }
  
  public void writeNumber(String encodedValue) throws IOException {
    _verifyValueWrite("write a number");
    if (encodedValue == null) {
      _writeNull();
    } else if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(encodedValue);
    } else {
      writeRaw(encodedValue);
    } 
  }
  
  public void writeNumber(char[] encodedValueBuffer, int offset, int length) throws IOException {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(encodedValueBuffer, offset, length);
    } else {
      writeRaw(encodedValueBuffer, offset, length);
    } 
  }
  
  private final void _writeQuotedRaw(String value) throws IOException {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    writeRaw(value);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  private void _writeQuotedRaw(char[] text, int offset, int length) throws IOException {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    writeRaw(text, offset, length);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeBoolean(boolean state) throws IOException {
    _verifyValueWrite("write a boolean value");
    if (this._outputTail + 5 >= this._outputEnd)
      _flushBuffer(); 
    byte[] keyword = state ? TRUE_BYTES : FALSE_BYTES;
    int len = keyword.length;
    System.arraycopy(keyword, 0, this._outputBuffer, this._outputTail, len);
    this._outputTail += len;
  }
  
  public void writeNull() throws IOException {
    _verifyValueWrite("write a null");
    _writeNull();
  }
  
  protected final void _verifyValueWrite(String typeMsg) throws IOException {
    byte b;
    int status = this._writeContext.writeValue();
    if (this._cfgPrettyPrinter != null) {
      _verifyPrettyValueWrite(typeMsg, status);
      return;
    } 
    switch (status) {
      default:
        return;
      case 1:
        b = 44;
        break;
      case 2:
        b = 58;
        break;
      case 3:
        if (this._rootValueSeparator != null) {
          byte[] raw = this._rootValueSeparator.asUnquotedUTF8();
          if (raw.length > 0)
            _writeBytes(raw); 
        } 
        return;
      case 5:
        _reportCantWriteValueExpectName(typeMsg);
        return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = b;
  }
  
  public void flush() throws IOException {
    _flushBuffer();
    if (this._outputStream != null && 
      isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
      this._outputStream.flush(); 
  }
  
  public void close() throws IOException {
    super.close();
    IOException flushFail = null;
    try {
      if (this._outputBuffer != null && 
        isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT))
        while (true) {
          JsonStreamContext ctxt = getOutputContext();
          if (ctxt.inArray()) {
            writeEndArray();
            continue;
          } 
          if (ctxt.inObject()) {
            writeEndObject();
            continue;
          } 
          break;
        }  
      _flushBuffer();
    } catch (IOException e) {
      flushFail = e;
    } 
    this._outputTail = 0;
    if (this._outputStream != null)
      try {
        if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
          this._outputStream.close();
        } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
          this._outputStream.flush();
        } 
      } catch (IOException|RuntimeException e) {
        if (flushFail != null)
          e.addSuppressed(flushFail); 
        throw e;
      }  
    _releaseBuffers();
    if (flushFail != null)
      throw flushFail; 
  }
  
  protected void _releaseBuffers() {
    byte[] buf = this._outputBuffer;
    if (buf != null && this._bufferRecyclable) {
      this._outputBuffer = null;
      this._ioContext.releaseWriteEncodingBuffer(buf);
    } 
    char[] cbuf = this._charBuffer;
    if (cbuf != null) {
      this._charBuffer = null;
      this._ioContext.releaseConcatBuffer(cbuf);
    } 
  }
  
  private final void _writeBytes(byte[] bytes) throws IOException {
    int len = bytes.length;
    if (this._outputTail + len > this._outputEnd) {
      _flushBuffer();
      if (len > 512) {
        this._outputStream.write(bytes, 0, len);
        return;
      } 
    } 
    System.arraycopy(bytes, 0, this._outputBuffer, this._outputTail, len);
    this._outputTail += len;
  }
  
  private final void _writeBytes(byte[] bytes, int offset, int len) throws IOException {
    if (this._outputTail + len > this._outputEnd) {
      _flushBuffer();
      if (len > 512) {
        this._outputStream.write(bytes, offset, len);
        return;
      } 
    } 
    System.arraycopy(bytes, offset, this._outputBuffer, this._outputTail, len);
    this._outputTail += len;
  }
  
  private final void _writeStringSegments(String text, boolean addQuotes) throws IOException {
    if (addQuotes) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = this._quoteChar;
    } 
    int left = text.length();
    int offset = 0;
    while (left > 0) {
      int len = Math.min(this._outputMaxContiguous, left);
      if (this._outputTail + len > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(text, offset, len);
      offset += len;
      left -= len;
    } 
    if (addQuotes) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = this._quoteChar;
    } 
  }
  
  private final void _writeStringSegments(char[] cbuf, int offset, int totalLen) throws IOException {
    do {
      int len = Math.min(this._outputMaxContiguous, totalLen);
      if (this._outputTail + len > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(cbuf, offset, len);
      offset += len;
      totalLen -= len;
    } while (totalLen > 0);
  }
  
  private final void _writeStringSegments(String text, int offset, int totalLen) throws IOException {
    do {
      int len = Math.min(this._outputMaxContiguous, totalLen);
      if (this._outputTail + len > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(text, offset, len);
      offset += len;
      totalLen -= len;
    } while (totalLen > 0);
  }
  
  private final void _writeStringSegment(char[] cbuf, int offset, int len) throws IOException {
    len += offset;
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    while (offset < len) {
      int ch = cbuf[offset];
      if (ch > 127 || escCodes[ch] != 0)
        break; 
      outputBuffer[outputPtr++] = (byte)ch;
      offset++;
    } 
    this._outputTail = outputPtr;
    if (offset < len)
      if (this._characterEscapes != null) {
        _writeCustomStringSegment2(cbuf, offset, len);
      } else if (this._maximumNonEscapedChar == 0) {
        _writeStringSegment2(cbuf, offset, len);
      } else {
        _writeStringSegmentASCII2(cbuf, offset, len);
      }  
  }
  
  private final void _writeStringSegment(String text, int offset, int len) throws IOException {
    len += offset;
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    while (offset < len) {
      int ch = text.charAt(offset);
      if (ch > 127 || escCodes[ch] != 0)
        break; 
      outputBuffer[outputPtr++] = (byte)ch;
      offset++;
    } 
    this._outputTail = outputPtr;
    if (offset < len)
      if (this._characterEscapes != null) {
        _writeCustomStringSegment2(text, offset, len);
      } else if (this._maximumNonEscapedChar == 0) {
        _writeStringSegment2(text, offset, len);
      } else {
        _writeStringSegmentASCII2(text, offset, len);
      }  
  }
  
  private final void _writeStringSegment2(char[] cbuf, int offset, int end) throws IOException {
    if (this._outputTail + 6 * (end - offset) > this._outputEnd)
      _flushBuffer(); 
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    while (offset < end) {
      int ch = cbuf[offset++];
      if (ch <= 127) {
        if (escCodes[ch] == 0) {
          outputBuffer[outputPtr++] = (byte)ch;
          continue;
        } 
        int escape = escCodes[ch];
        if (escape > 0) {
          outputBuffer[outputPtr++] = 92;
          outputBuffer[outputPtr++] = (byte)escape;
          continue;
        } 
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch <= 2047) {
        outputBuffer[outputPtr++] = (byte)(0xC0 | ch >> 6);
        outputBuffer[outputPtr++] = (byte)(0x80 | ch & 0x3F);
        continue;
      } 
      outputPtr = _outputMultiByteChar(ch, outputPtr);
    } 
    this._outputTail = outputPtr;
  }
  
  private final void _writeStringSegment2(String text, int offset, int end) throws IOException {
    if (this._outputTail + 6 * (end - offset) > this._outputEnd)
      _flushBuffer(); 
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    while (offset < end) {
      int ch = text.charAt(offset++);
      if (ch <= 127) {
        if (escCodes[ch] == 0) {
          outputBuffer[outputPtr++] = (byte)ch;
          continue;
        } 
        int escape = escCodes[ch];
        if (escape > 0) {
          outputBuffer[outputPtr++] = 92;
          outputBuffer[outputPtr++] = (byte)escape;
          continue;
        } 
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch <= 2047) {
        outputBuffer[outputPtr++] = (byte)(0xC0 | ch >> 6);
        outputBuffer[outputPtr++] = (byte)(0x80 | ch & 0x3F);
        continue;
      } 
      outputPtr = _outputMultiByteChar(ch, outputPtr);
    } 
    this._outputTail = outputPtr;
  }
  
  private final void _writeStringSegmentASCII2(char[] cbuf, int offset, int end) throws IOException {
    if (this._outputTail + 6 * (end - offset) > this._outputEnd)
      _flushBuffer(); 
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    int maxUnescaped = this._maximumNonEscapedChar;
    while (offset < end) {
      int ch = cbuf[offset++];
      if (ch <= 127) {
        if (escCodes[ch] == 0) {
          outputBuffer[outputPtr++] = (byte)ch;
          continue;
        } 
        int escape = escCodes[ch];
        if (escape > 0) {
          outputBuffer[outputPtr++] = 92;
          outputBuffer[outputPtr++] = (byte)escape;
          continue;
        } 
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch > maxUnescaped) {
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch <= 2047) {
        outputBuffer[outputPtr++] = (byte)(0xC0 | ch >> 6);
        outputBuffer[outputPtr++] = (byte)(0x80 | ch & 0x3F);
        continue;
      } 
      outputPtr = _outputMultiByteChar(ch, outputPtr);
    } 
    this._outputTail = outputPtr;
  }
  
  private final void _writeStringSegmentASCII2(String text, int offset, int end) throws IOException {
    if (this._outputTail + 6 * (end - offset) > this._outputEnd)
      _flushBuffer(); 
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    int maxUnescaped = this._maximumNonEscapedChar;
    while (offset < end) {
      int ch = text.charAt(offset++);
      if (ch <= 127) {
        if (escCodes[ch] == 0) {
          outputBuffer[outputPtr++] = (byte)ch;
          continue;
        } 
        int escape = escCodes[ch];
        if (escape > 0) {
          outputBuffer[outputPtr++] = 92;
          outputBuffer[outputPtr++] = (byte)escape;
          continue;
        } 
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch > maxUnescaped) {
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch <= 2047) {
        outputBuffer[outputPtr++] = (byte)(0xC0 | ch >> 6);
        outputBuffer[outputPtr++] = (byte)(0x80 | ch & 0x3F);
        continue;
      } 
      outputPtr = _outputMultiByteChar(ch, outputPtr);
    } 
    this._outputTail = outputPtr;
  }
  
  private final void _writeCustomStringSegment2(char[] cbuf, int offset, int end) throws IOException {
    if (this._outputTail + 6 * (end - offset) > this._outputEnd)
      _flushBuffer(); 
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    int maxUnescaped = (this._maximumNonEscapedChar <= 0) ? 65535 : this._maximumNonEscapedChar;
    CharacterEscapes customEscapes = this._characterEscapes;
    while (offset < end) {
      int ch = cbuf[offset++];
      if (ch <= 127) {
        if (escCodes[ch] == 0) {
          outputBuffer[outputPtr++] = (byte)ch;
          continue;
        } 
        int escape = escCodes[ch];
        if (escape > 0) {
          outputBuffer[outputPtr++] = 92;
          outputBuffer[outputPtr++] = (byte)escape;
          continue;
        } 
        if (escape == -2) {
          SerializableString serializableString = customEscapes.getEscapeSequence(ch);
          if (serializableString == null)
            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + 
                Integer.toHexString(ch) + ", although was supposed to have one"); 
          outputPtr = _writeCustomEscape(outputBuffer, outputPtr, serializableString, end - offset);
          continue;
        } 
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch > maxUnescaped) {
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      SerializableString esc = customEscapes.getEscapeSequence(ch);
      if (esc != null) {
        outputPtr = _writeCustomEscape(outputBuffer, outputPtr, esc, end - offset);
        continue;
      } 
      if (ch <= 2047) {
        outputBuffer[outputPtr++] = (byte)(0xC0 | ch >> 6);
        outputBuffer[outputPtr++] = (byte)(0x80 | ch & 0x3F);
        continue;
      } 
      outputPtr = _outputMultiByteChar(ch, outputPtr);
    } 
    this._outputTail = outputPtr;
  }
  
  private final void _writeCustomStringSegment2(String text, int offset, int end) throws IOException {
    if (this._outputTail + 6 * (end - offset) > this._outputEnd)
      _flushBuffer(); 
    int outputPtr = this._outputTail;
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    int maxUnescaped = (this._maximumNonEscapedChar <= 0) ? 65535 : this._maximumNonEscapedChar;
    CharacterEscapes customEscapes = this._characterEscapes;
    while (offset < end) {
      int ch = text.charAt(offset++);
      if (ch <= 127) {
        if (escCodes[ch] == 0) {
          outputBuffer[outputPtr++] = (byte)ch;
          continue;
        } 
        int escape = escCodes[ch];
        if (escape > 0) {
          outputBuffer[outputPtr++] = 92;
          outputBuffer[outputPtr++] = (byte)escape;
          continue;
        } 
        if (escape == -2) {
          SerializableString serializableString = customEscapes.getEscapeSequence(ch);
          if (serializableString == null)
            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + 
                Integer.toHexString(ch) + ", although was supposed to have one"); 
          outputPtr = _writeCustomEscape(outputBuffer, outputPtr, serializableString, end - offset);
          continue;
        } 
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      if (ch > maxUnescaped) {
        outputPtr = _writeGenericEscape(ch, outputPtr);
        continue;
      } 
      SerializableString esc = customEscapes.getEscapeSequence(ch);
      if (esc != null) {
        outputPtr = _writeCustomEscape(outputBuffer, outputPtr, esc, end - offset);
        continue;
      } 
      if (ch <= 2047) {
        outputBuffer[outputPtr++] = (byte)(0xC0 | ch >> 6);
        outputBuffer[outputPtr++] = (byte)(0x80 | ch & 0x3F);
        continue;
      } 
      outputPtr = _outputMultiByteChar(ch, outputPtr);
    } 
    this._outputTail = outputPtr;
  }
  
  private final int _writeCustomEscape(byte[] outputBuffer, int outputPtr, SerializableString esc, int remainingChars) throws IOException, JsonGenerationException {
    byte[] raw = esc.asUnquotedUTF8();
    int len = raw.length;
    if (len > 6)
      return _handleLongCustomEscape(outputBuffer, outputPtr, this._outputEnd, raw, remainingChars); 
    System.arraycopy(raw, 0, outputBuffer, outputPtr, len);
    return outputPtr + len;
  }
  
  private final int _handleLongCustomEscape(byte[] outputBuffer, int outputPtr, int outputEnd, byte[] raw, int remainingChars) throws IOException, JsonGenerationException {
    int len = raw.length;
    if (outputPtr + len > outputEnd) {
      this._outputTail = outputPtr;
      _flushBuffer();
      outputPtr = this._outputTail;
      if (len > outputBuffer.length) {
        this._outputStream.write(raw, 0, len);
        return outputPtr;
      } 
    } 
    System.arraycopy(raw, 0, outputBuffer, outputPtr, len);
    outputPtr += len;
    if (outputPtr + 6 * remainingChars > outputEnd) {
      this._outputTail = outputPtr;
      _flushBuffer();
      return this._outputTail;
    } 
    return outputPtr;
  }
  
  private final void _writeUTF8Segments(byte[] utf8, int offset, int totalLen) throws IOException, JsonGenerationException {
    do {
      int len = Math.min(this._outputMaxContiguous, totalLen);
      _writeUTF8Segment(utf8, offset, len);
      offset += len;
      totalLen -= len;
    } while (totalLen > 0);
  }
  
  private final void _writeUTF8Segment(byte[] utf8, int offset, int len) throws IOException, JsonGenerationException {
    int[] escCodes = this._outputEscapes;
    for (int ptr = offset, end = offset + len; ptr < end; ) {
      int ch = utf8[ptr++];
      if (ch >= 0 && escCodes[ch] != 0) {
        _writeUTF8Segment2(utf8, offset, len);
        return;
      } 
    } 
    if (this._outputTail + len > this._outputEnd)
      _flushBuffer(); 
    System.arraycopy(utf8, offset, this._outputBuffer, this._outputTail, len);
    this._outputTail += len;
  }
  
  private final void _writeUTF8Segment2(byte[] utf8, int offset, int len) throws IOException, JsonGenerationException {
    int outputPtr = this._outputTail;
    if (outputPtr + len * 6 > this._outputEnd) {
      _flushBuffer();
      outputPtr = this._outputTail;
    } 
    byte[] outputBuffer = this._outputBuffer;
    int[] escCodes = this._outputEscapes;
    len += offset;
    while (offset < len) {
      byte b = utf8[offset++];
      int ch = b;
      if (ch < 0 || escCodes[ch] == 0) {
        outputBuffer[outputPtr++] = b;
        continue;
      } 
      int escape = escCodes[ch];
      if (escape > 0) {
        outputBuffer[outputPtr++] = 92;
        outputBuffer[outputPtr++] = (byte)escape;
        continue;
      } 
      outputPtr = _writeGenericEscape(ch, outputPtr);
    } 
    this._outputTail = outputPtr;
  }
  
  protected final void _writeBinary(Base64Variant b64variant, byte[] input, int inputPtr, int inputEnd) throws IOException, JsonGenerationException {
    int safeInputEnd = inputEnd - 3;
    int safeOutputEnd = this._outputEnd - 6;
    int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
    while (inputPtr <= safeInputEnd) {
      if (this._outputTail > safeOutputEnd)
        _flushBuffer(); 
      int b24 = input[inputPtr++] << 8;
      b24 |= input[inputPtr++] & 0xFF;
      b24 = b24 << 8 | input[inputPtr++] & 0xFF;
      this._outputTail = b64variant.encodeBase64Chunk(b24, this._outputBuffer, this._outputTail);
      if (--chunksBeforeLF <= 0) {
        this._outputBuffer[this._outputTail++] = 92;
        this._outputBuffer[this._outputTail++] = 110;
        chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
      } 
    } 
    int inputLeft = inputEnd - inputPtr;
    if (inputLeft > 0) {
      if (this._outputTail > safeOutputEnd)
        _flushBuffer(); 
      int b24 = input[inputPtr++] << 16;
      if (inputLeft == 2)
        b24 |= (input[inputPtr++] & 0xFF) << 8; 
      this._outputTail = b64variant.encodeBase64Partial(b24, inputLeft, this._outputBuffer, this._outputTail);
    } 
  }
  
  protected final int _writeBinary(Base64Variant b64variant, InputStream data, byte[] readBuffer, int bytesLeft) throws IOException, JsonGenerationException {
    int inputPtr = 0;
    int inputEnd = 0;
    int lastFullOffset = -3;
    int safeOutputEnd = this._outputEnd - 6;
    int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
    while (bytesLeft > 2) {
      if (inputPtr > lastFullOffset) {
        inputEnd = _readMore(data, readBuffer, inputPtr, inputEnd, bytesLeft);
        inputPtr = 0;
        if (inputEnd < 3)
          break; 
        lastFullOffset = inputEnd - 3;
      } 
      if (this._outputTail > safeOutputEnd)
        _flushBuffer(); 
      int b24 = readBuffer[inputPtr++] << 8;
      b24 |= readBuffer[inputPtr++] & 0xFF;
      b24 = b24 << 8 | readBuffer[inputPtr++] & 0xFF;
      bytesLeft -= 3;
      this._outputTail = b64variant.encodeBase64Chunk(b24, this._outputBuffer, this._outputTail);
      if (--chunksBeforeLF <= 0) {
        this._outputBuffer[this._outputTail++] = 92;
        this._outputBuffer[this._outputTail++] = 110;
        chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
      } 
    } 
    if (bytesLeft > 0) {
      inputEnd = _readMore(data, readBuffer, inputPtr, inputEnd, bytesLeft);
      inputPtr = 0;
      if (inputEnd > 0) {
        int amount;
        if (this._outputTail > safeOutputEnd)
          _flushBuffer(); 
        int b24 = readBuffer[inputPtr++] << 16;
        if (inputPtr < inputEnd) {
          b24 |= (readBuffer[inputPtr] & 0xFF) << 8;
          amount = 2;
        } else {
          amount = 1;
        } 
        this._outputTail = b64variant.encodeBase64Partial(b24, amount, this._outputBuffer, this._outputTail);
        bytesLeft -= amount;
      } 
    } 
    return bytesLeft;
  }
  
  protected final int _writeBinary(Base64Variant b64variant, InputStream data, byte[] readBuffer) throws IOException, JsonGenerationException {
    int inputPtr = 0;
    int inputEnd = 0;
    int lastFullOffset = -3;
    int bytesDone = 0;
    int safeOutputEnd = this._outputEnd - 6;
    int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
    while (true) {
      if (inputPtr > lastFullOffset) {
        inputEnd = _readMore(data, readBuffer, inputPtr, inputEnd, readBuffer.length);
        inputPtr = 0;
        if (inputEnd < 3)
          break; 
        lastFullOffset = inputEnd - 3;
      } 
      if (this._outputTail > safeOutputEnd)
        _flushBuffer(); 
      int b24 = readBuffer[inputPtr++] << 8;
      b24 |= readBuffer[inputPtr++] & 0xFF;
      b24 = b24 << 8 | readBuffer[inputPtr++] & 0xFF;
      bytesDone += 3;
      this._outputTail = b64variant.encodeBase64Chunk(b24, this._outputBuffer, this._outputTail);
      if (--chunksBeforeLF <= 0) {
        this._outputBuffer[this._outputTail++] = 92;
        this._outputBuffer[this._outputTail++] = 110;
        chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
      } 
    } 
    if (inputPtr < inputEnd) {
      if (this._outputTail > safeOutputEnd)
        _flushBuffer(); 
      int b24 = readBuffer[inputPtr++] << 16;
      int amount = 1;
      if (inputPtr < inputEnd) {
        b24 |= (readBuffer[inputPtr] & 0xFF) << 8;
        amount = 2;
      } 
      bytesDone += amount;
      this._outputTail = b64variant.encodeBase64Partial(b24, amount, this._outputBuffer, this._outputTail);
    } 
    return bytesDone;
  }
  
  private final int _readMore(InputStream in, byte[] readBuffer, int inputPtr, int inputEnd, int maxRead) throws IOException {
    int i = 0;
    while (inputPtr < inputEnd)
      readBuffer[i++] = readBuffer[inputPtr++]; 
    inputPtr = 0;
    inputEnd = i;
    maxRead = Math.min(maxRead, readBuffer.length);
    do {
      int length = maxRead - inputEnd;
      if (length == 0)
        break; 
      int count = in.read(readBuffer, inputEnd, length);
      if (count < 0)
        return inputEnd; 
      inputEnd += count;
    } while (inputEnd < 3);
    return inputEnd;
  }
  
  private final int _outputRawMultiByteChar(int ch, char[] cbuf, int inputOffset, int inputEnd) throws IOException {
    if (ch >= 55296 && 
      ch <= 57343) {
      if (inputOffset >= inputEnd || cbuf == null) {
        _reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", new Object[] { Integer.valueOf(ch) }));
      } else {
        _outputSurrogates(ch, cbuf[inputOffset]);
      } 
      return inputOffset + 1;
    } 
    byte[] bbuf = this._outputBuffer;
    bbuf[this._outputTail++] = (byte)(0xE0 | ch >> 12);
    bbuf[this._outputTail++] = (byte)(0x80 | ch >> 6 & 0x3F);
    bbuf[this._outputTail++] = (byte)(0x80 | ch & 0x3F);
    return inputOffset;
  }
  
  protected final void _outputSurrogates(int surr1, int surr2) throws IOException {
    int c = _decodeSurrogate(surr1, surr2);
    if (this._outputTail + 4 > this._outputEnd)
      _flushBuffer(); 
    byte[] bbuf = this._outputBuffer;
    bbuf[this._outputTail++] = (byte)(0xF0 | c >> 18);
    bbuf[this._outputTail++] = (byte)(0x80 | c >> 12 & 0x3F);
    bbuf[this._outputTail++] = (byte)(0x80 | c >> 6 & 0x3F);
    bbuf[this._outputTail++] = (byte)(0x80 | c & 0x3F);
  }
  
  private final int _outputMultiByteChar(int ch, int outputPtr) throws IOException {
    byte[] HEX_CHARS = getHexBytes();
    byte[] bbuf = this._outputBuffer;
    if (ch >= 55296 && ch <= 57343) {
      bbuf[outputPtr++] = 92;
      bbuf[outputPtr++] = 117;
      bbuf[outputPtr++] = HEX_CHARS[ch >> 12 & 0xF];
      bbuf[outputPtr++] = HEX_CHARS[ch >> 8 & 0xF];
      bbuf[outputPtr++] = HEX_CHARS[ch >> 4 & 0xF];
      bbuf[outputPtr++] = HEX_CHARS[ch & 0xF];
    } else {
      bbuf[outputPtr++] = (byte)(0xE0 | ch >> 12);
      bbuf[outputPtr++] = (byte)(0x80 | ch >> 6 & 0x3F);
      bbuf[outputPtr++] = (byte)(0x80 | ch & 0x3F);
    } 
    return outputPtr;
  }
  
  private final void _writeNull() throws IOException {
    if (this._outputTail + 4 >= this._outputEnd)
      _flushBuffer(); 
    System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
    this._outputTail += 4;
  }
  
  private int _writeGenericEscape(int charToEscape, int outputPtr) throws IOException {
    byte[] bbuf = this._outputBuffer;
    byte[] HEX_CHARS = getHexBytes();
    bbuf[outputPtr++] = 92;
    bbuf[outputPtr++] = 117;
    if (charToEscape > 255) {
      int hi = charToEscape >> 8 & 0xFF;
      bbuf[outputPtr++] = HEX_CHARS[hi >> 4];
      bbuf[outputPtr++] = HEX_CHARS[hi & 0xF];
      charToEscape &= 0xFF;
    } else {
      bbuf[outputPtr++] = 48;
      bbuf[outputPtr++] = 48;
    } 
    bbuf[outputPtr++] = HEX_CHARS[charToEscape >> 4];
    bbuf[outputPtr++] = HEX_CHARS[charToEscape & 0xF];
    return outputPtr;
  }
  
  protected final void _flushBuffer() throws IOException {
    int len = this._outputTail;
    if (len > 0) {
      this._outputTail = 0;
      this._outputStream.write(this._outputBuffer, 0, len);
    } 
  }
  
  private byte[] getHexBytes() {
    return this._cfgWriteHexUppercase ? HEX_BYTES_UPPER : HEX_BYTES_LOWER;
  }
}
