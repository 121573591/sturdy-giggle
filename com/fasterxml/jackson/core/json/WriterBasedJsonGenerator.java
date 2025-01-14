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
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public class WriterBasedJsonGenerator extends JsonGeneratorImpl {
  protected static final int SHORT_WRITE = 32;
  
  protected static final char[] HEX_CHARS_UPPER = CharTypes.copyHexChars(true);
  
  protected static final char[] HEX_CHARS_LOWER = CharTypes.copyHexChars(false);
  
  protected final Writer _writer;
  
  protected char _quoteChar;
  
  protected char[] _outputBuffer;
  
  protected int _outputHead;
  
  protected int _outputTail;
  
  protected int _outputEnd;
  
  protected char[] _entityBuffer;
  
  protected SerializableString _currentEscape;
  
  protected char[] _copyBuffer;
  
  private char[] getHexChars() {
    return this._cfgWriteHexUppercase ? HEX_CHARS_UPPER : HEX_CHARS_LOWER;
  }
  
  @Deprecated
  public WriterBasedJsonGenerator(IOContext ctxt, int features, ObjectCodec codec, Writer w) {
    this(ctxt, features, codec, w, '"');
  }
  
  public WriterBasedJsonGenerator(IOContext ctxt, int features, ObjectCodec codec, Writer w, char quoteChar) {
    super(ctxt, features, codec);
    this._writer = w;
    this._outputBuffer = ctxt.allocConcatBuffer();
    this._outputEnd = this._outputBuffer.length;
    this._quoteChar = quoteChar;
    if (quoteChar != '"')
      this._outputEscapes = CharTypes.get7BitOutputEscapes(quoteChar); 
  }
  
  public Object getOutputTarget() {
    return this._writer;
  }
  
  public int getOutputBuffered() {
    int len = this._outputTail - this._outputHead;
    return Math.max(0, len);
  }
  
  public boolean canWriteFormattedNumbers() {
    return true;
  }
  
  public void writeFieldName(String name) throws IOException {
    int status = this._writeContext.writeFieldName(name);
    if (status == 4)
      _reportError("Can not write a field name, expecting a value"); 
    _writeFieldName(name, (status == 1));
  }
  
  public void writeFieldName(SerializableString name) throws IOException {
    int status = this._writeContext.writeFieldName(name.getValue());
    if (status == 4)
      _reportError("Can not write a field name, expecting a value"); 
    _writeFieldName(name, (status == 1));
  }
  
  protected final void _writeFieldName(String name, boolean commaBefore) throws IOException {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(name, commaBefore);
      return;
    } 
    if (this._outputTail + 1 >= this._outputEnd)
      _flushBuffer(); 
    if (commaBefore)
      this._outputBuffer[this._outputTail++] = ','; 
    if (this._cfgUnqNames) {
      _writeString(name);
      return;
    } 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    _writeString(name);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  protected final void _writeFieldName(SerializableString name, boolean commaBefore) throws IOException {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(name, commaBefore);
      return;
    } 
    if (this._outputTail + 1 >= this._outputEnd)
      _flushBuffer(); 
    if (commaBefore)
      this._outputBuffer[this._outputTail++] = ','; 
    if (this._cfgUnqNames) {
      char[] ch = name.asQuotedChars();
      writeRaw(ch, 0, ch.length);
      return;
    } 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    int len = name.appendQuoted(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeFieldNameTail(name);
      return;
    } 
    this._outputTail += len;
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  private final void _writeFieldNameTail(SerializableString name) throws IOException {
    char[] quoted = name.asQuotedChars();
    writeRaw(quoted, 0, quoted.length);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeStartArray() throws IOException {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext();
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartArray((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = '[';
    } 
  }
  
  public void writeStartArray(Object currentValue) throws IOException {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext(currentValue);
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartArray((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = '[';
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
      this._outputBuffer[this._outputTail++] = '[';
    } 
  }
  
  public void writeEndArray() throws IOException {
    if (!this._writeContext.inArray())
      _reportError("Current context not Array but " + this._writeContext.typeDesc()); 
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndArray((JsonGenerator)this, this._writeContext.getEntryCount());
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = ']';
    } 
    this._writeContext = this._writeContext.clearAndGetParent();
  }
  
  public void writeStartObject() throws IOException {
    _verifyValueWrite("start an object");
    this._writeContext = this._writeContext.createChildObjectContext();
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartObject((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = '{';
    } 
  }
  
  public void writeStartObject(Object forValue) throws IOException {
    _verifyValueWrite("start an object");
    JsonWriteContext ctxt = this._writeContext.createChildObjectContext(forValue);
    streamWriteConstraints().validateNestingDepth(this._writeContext.getNestingDepth());
    this._writeContext = ctxt;
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartObject((JsonGenerator)this);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = '{';
    } 
  }
  
  public void writeStartObject(Object forValue, int size) throws IOException {
    writeStartObject(forValue);
  }
  
  public void writeEndObject() throws IOException {
    if (!this._writeContext.inObject())
      _reportError("Current context not Object but " + this._writeContext.typeDesc()); 
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndObject((JsonGenerator)this, this._writeContext.getEntryCount());
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = '}';
    } 
    this._writeContext = this._writeContext.clearAndGetParent();
  }
  
  protected final void _writePPFieldName(String name, boolean commaBefore) throws IOException {
    if (commaBefore) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator((JsonGenerator)this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries((JsonGenerator)this);
    } 
    if (this._cfgUnqNames) {
      _writeString(name);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = this._quoteChar;
      _writeString(name);
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = this._quoteChar;
    } 
  }
  
  protected final void _writePPFieldName(SerializableString name, boolean commaBefore) throws IOException {
    if (commaBefore) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator((JsonGenerator)this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries((JsonGenerator)this);
    } 
    char[] quoted = name.asQuotedChars();
    if (this._cfgUnqNames) {
      writeRaw(quoted, 0, quoted.length);
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = this._quoteChar;
      writeRaw(quoted, 0, quoted.length);
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
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    _writeString(text);
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
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    char[] buf = _allocateCopyBuffer();
    while (toRead > 0) {
      int toReadNow = Math.min(toRead, buf.length);
      int numRead = reader.read(buf, 0, toReadNow);
      if (numRead <= 0)
        break; 
      _writeString(buf, 0, numRead);
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
    _writeString(text, offset, len);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeString(SerializableString sstr) throws IOException {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    int len = sstr.appendQuoted(this._outputBuffer, this._outputTail);
    if (len < 0) {
      _writeString2(sstr);
      return;
    } 
    this._outputTail += len;
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  private void _writeString2(SerializableString sstr) throws IOException {
    char[] text = sstr.asQuotedChars();
    int len = text.length;
    if (len < 32) {
      int room = this._outputEnd - this._outputTail;
      if (len > room)
        _flushBuffer(); 
      System.arraycopy(text, 0, this._outputBuffer, this._outputTail, len);
      this._outputTail += len;
    } else {
      _flushBuffer();
      this._writer.write(text, 0, len);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeRawUTF8String(byte[] text, int offset, int length) throws IOException {
    _reportUnsupportedOperation();
  }
  
  public void writeUTF8String(byte[] text, int offset, int length) throws IOException {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(String text) throws IOException {
    int len = text.length();
    int room = this._outputEnd - this._outputTail;
    if (room == 0) {
      _flushBuffer();
      room = this._outputEnd - this._outputTail;
    } 
    if (room >= len) {
      text.getChars(0, len, this._outputBuffer, this._outputTail);
      this._outputTail += len;
    } else {
      writeRawLong(text);
    } 
  }
  
  public void writeRaw(String text, int offset, int len) throws IOException {
    _checkRangeBoundsForString(text, offset, len);
    int room = this._outputEnd - this._outputTail;
    if (room < len) {
      _flushBuffer();
      room = this._outputEnd - this._outputTail;
    } 
    if (room >= len) {
      text.getChars(offset, offset + len, this._outputBuffer, this._outputTail);
      this._outputTail += len;
    } else {
      writeRawLong(text.substring(offset, offset + len));
    } 
  }
  
  public void writeRaw(SerializableString text) throws IOException {
    int len = text.appendUnquoted(this._outputBuffer, this._outputTail);
    if (len < 0) {
      writeRaw(text.getValue());
      return;
    } 
    this._outputTail += len;
  }
  
  public void writeRaw(char[] cbuf, int offset, int len) throws IOException {
    _checkRangeBoundsForCharArray(cbuf, offset, len);
    if (len < 32) {
      int room = this._outputEnd - this._outputTail;
      if (len > room)
        _flushBuffer(); 
      System.arraycopy(cbuf, offset, this._outputBuffer, this._outputTail, len);
      this._outputTail += len;
      return;
    } 
    _flushBuffer();
    this._writer.write(cbuf, offset, len);
  }
  
  public void writeRaw(char c) throws IOException {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = c;
  }
  
  private void writeRawLong(String text) throws IOException {
    int room = this._outputEnd - this._outputTail;
    text.getChars(0, room, this._outputBuffer, this._outputTail);
    this._outputTail += room;
    _flushBuffer();
    int offset = room;
    int len = text.length() - room;
    while (len > this._outputEnd) {
      int amount = this._outputEnd;
      text.getChars(offset, offset + amount, this._outputBuffer, 0);
      this._outputHead = 0;
      this._outputTail = amount;
      _flushBuffer();
      offset += amount;
      len -= amount;
    } 
    text.getChars(offset, offset + len, this._outputBuffer, 0);
    this._outputHead = 0;
    this._outputTail = len;
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
    if (this._cfgNumbersAsStrings) {
      _writeQuotedShort(s);
      return;
    } 
    if (this._outputTail + 6 >= this._outputEnd)
      _flushBuffer(); 
    this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
  }
  
  private void _writeQuotedShort(short s) throws IOException {
    if (this._outputTail + 8 >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = this._quoteChar;
    this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
    this._outputBuffer[this._outputTail++] = this._quoteChar;
  }
  
  public void writeNumber(int i) throws IOException {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedInt(i);
      return;
    } 
    if (this._outputTail + 11 >= this._outputEnd)
      _flushBuffer(); 
    this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
  }
  
  private void _writeQuotedInt(int i) throws IOException {
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
  
  private void _writeQuotedLong(long l) throws IOException {
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
      NumberOutput.notFinite(d) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
      writeString(NumberOutput.toString(d, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
      return;
    } 
    _verifyValueWrite("write a number");
    writeRaw(NumberOutput.toString(d, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
  }
  
  public void writeNumber(float f) throws IOException {
    if (this._cfgNumbersAsStrings || (
      NumberOutput.notFinite(f) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
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
  
  private void _writeQuotedRaw(String value) throws IOException {
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
    int ptr = this._outputTail;
    char[] buf = this._outputBuffer;
    if (state) {
      buf[ptr] = 't';
      buf[++ptr] = 'r';
      buf[++ptr] = 'u';
      buf[++ptr] = 'e';
    } else {
      buf[ptr] = 'f';
      buf[++ptr] = 'a';
      buf[++ptr] = 'l';
      buf[++ptr] = 's';
      buf[++ptr] = 'e';
    } 
    this._outputTail = ptr + 1;
  }
  
  public void writeNull() throws IOException {
    _verifyValueWrite("write a null");
    _writeNull();
  }
  
  protected final void _verifyValueWrite(String typeMsg) throws IOException {
    char c;
    int status = this._writeContext.writeValue();
    if (this._cfgPrettyPrinter != null) {
      _verifyPrettyValueWrite(typeMsg, status);
      return;
    } 
    switch (status) {
      default:
        return;
      case 1:
        c = ',';
        break;
      case 2:
        c = ':';
        break;
      case 3:
        if (this._rootValueSeparator != null)
          writeRaw(this._rootValueSeparator.getValue()); 
        return;
      case 5:
        _reportCantWriteValueExpectName(typeMsg);
        return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail++] = c;
  }
  
  public void flush() throws IOException {
    _flushBuffer();
    if (this._writer != null && 
      isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
      this._writer.flush(); 
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
    this._outputHead = 0;
    this._outputTail = 0;
    if (this._writer != null)
      try {
        if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
          this._writer.close();
        } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
          this._writer.flush();
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
    char[] buf = this._outputBuffer;
    if (buf != null) {
      this._outputBuffer = null;
      this._ioContext.releaseConcatBuffer(buf);
    } 
    buf = this._copyBuffer;
    if (buf != null) {
      this._copyBuffer = null;
      this._ioContext.releaseNameCopyBuffer(buf);
    } 
  }
  
  private void _writeString(String text) throws IOException {
    int len = text.length();
    if (len > this._outputEnd) {
      _writeLongString(text);
      return;
    } 
    if (this._outputTail + len > this._outputEnd)
      _flushBuffer(); 
    text.getChars(0, len, this._outputBuffer, this._outputTail);
    if (this._characterEscapes != null) {
      _writeStringCustom(len);
    } else if (this._maximumNonEscapedChar != 0) {
      _writeStringASCII(len, this._maximumNonEscapedChar);
    } else {
      _writeString2(len);
    } 
  }
  
  private void _writeString2(int len) throws IOException {
    int end = this._outputTail + len;
    int[] escCodes = this._outputEscapes;
    int escLen = escCodes.length;
    while (this._outputTail < end) {
      label17: while (true) {
        char c = this._outputBuffer[this._outputTail];
        if (c < escLen && escCodes[c] != 0) {
          int flushLen = this._outputTail - this._outputHead;
          if (flushLen > 0) {
            this._writer.write(this._outputBuffer, this._outputHead, flushLen);
            break label17;
          } 
          char c1 = this._outputBuffer[this._outputTail++];
          _prependOrWriteCharacterEscape(c1, escCodes[c1]);
          continue;
        } 
        if (++this._outputTail >= end)
          break; 
      } 
    } 
  }
  
  private void _writeLongString(String text) throws IOException {
    _flushBuffer();
    int textLen = text.length();
    int offset = 0;
    do {
      int max = this._outputEnd;
      int segmentLen = (offset + max > textLen) ? (textLen - offset) : max;
      text.getChars(offset, offset + segmentLen, this._outputBuffer, 0);
      if (this._characterEscapes != null) {
        _writeSegmentCustom(segmentLen);
      } else if (this._maximumNonEscapedChar != 0) {
        _writeSegmentASCII(segmentLen, this._maximumNonEscapedChar);
      } else {
        _writeSegment(segmentLen);
      } 
      offset += segmentLen;
    } while (offset < textLen);
  }
  
  private void _writeSegment(int end) throws IOException {
    int[] escCodes = this._outputEscapes;
    int escLen = escCodes.length;
    int ptr = 0;
    int start = ptr;
    while (ptr < end) {
      char c;
      do {
        c = this._outputBuffer[ptr];
        if (c < escLen && escCodes[c] != 0)
          break; 
      } while (++ptr < end);
      int flushLen = ptr - start;
      if (flushLen > 0) {
        this._writer.write(this._outputBuffer, start, flushLen);
        if (ptr >= end)
          break; 
      } 
      ptr++;
      start = _prependOrWriteCharacterEscape(this._outputBuffer, ptr, end, c, escCodes[c]);
    } 
  }
  
  private void _writeString(char[] text, int offset, int len) throws IOException {
    if (this._characterEscapes != null) {
      _writeStringCustom(text, offset, len);
      return;
    } 
    if (this._maximumNonEscapedChar != 0) {
      _writeStringASCII(text, offset, len, this._maximumNonEscapedChar);
      return;
    } 
    len += offset;
    int[] escCodes = this._outputEscapes;
    int escLen = escCodes.length;
    while (offset < len) {
      int start = offset;
      do {
        char c1 = text[offset];
        if (c1 < escLen && escCodes[c1] != 0)
          break; 
      } while (++offset < len);
      int newAmount = offset - start;
      if (newAmount < 32) {
        if (this._outputTail + newAmount > this._outputEnd)
          _flushBuffer(); 
        if (newAmount > 0) {
          System.arraycopy(text, start, this._outputBuffer, this._outputTail, newAmount);
          this._outputTail += newAmount;
        } 
      } else {
        _flushBuffer();
        this._writer.write(text, start, newAmount);
      } 
      if (offset >= len)
        break; 
      char c = text[offset++];
      _appendCharacterEscape(c, escCodes[c]);
    } 
  }
  
  private void _writeStringASCII(int len, int maxNonEscaped) throws IOException, JsonGenerationException {
    // Byte code:
    //   0: aload_0
    //   1: getfield _outputTail : I
    //   4: iload_1
    //   5: iadd
    //   6: istore_3
    //   7: aload_0
    //   8: getfield _outputEscapes : [I
    //   11: astore #4
    //   13: aload #4
    //   15: arraylength
    //   16: iload_2
    //   17: iconst_1
    //   18: iadd
    //   19: invokestatic min : (II)I
    //   22: istore #5
    //   24: iconst_0
    //   25: istore #6
    //   27: aload_0
    //   28: getfield _outputTail : I
    //   31: iload_3
    //   32: if_icmpge -> 152
    //   35: aload_0
    //   36: getfield _outputBuffer : [C
    //   39: aload_0
    //   40: getfield _outputTail : I
    //   43: caload
    //   44: istore #7
    //   46: iload #7
    //   48: iload #5
    //   50: if_icmpge -> 68
    //   53: aload #4
    //   55: iload #7
    //   57: iaload
    //   58: istore #6
    //   60: iload #6
    //   62: ifeq -> 80
    //   65: goto -> 98
    //   68: iload #7
    //   70: iload_2
    //   71: if_icmple -> 80
    //   74: iconst_m1
    //   75: istore #6
    //   77: goto -> 98
    //   80: aload_0
    //   81: dup
    //   82: getfield _outputTail : I
    //   85: iconst_1
    //   86: iadd
    //   87: dup_x1
    //   88: putfield _outputTail : I
    //   91: iload_3
    //   92: if_icmplt -> 35
    //   95: goto -> 152
    //   98: aload_0
    //   99: getfield _outputTail : I
    //   102: aload_0
    //   103: getfield _outputHead : I
    //   106: isub
    //   107: istore #8
    //   109: iload #8
    //   111: ifle -> 131
    //   114: aload_0
    //   115: getfield _writer : Ljava/io/Writer;
    //   118: aload_0
    //   119: getfield _outputBuffer : [C
    //   122: aload_0
    //   123: getfield _outputHead : I
    //   126: iload #8
    //   128: invokevirtual write : ([CII)V
    //   131: aload_0
    //   132: dup
    //   133: getfield _outputTail : I
    //   136: iconst_1
    //   137: iadd
    //   138: putfield _outputTail : I
    //   141: aload_0
    //   142: iload #7
    //   144: iload #6
    //   146: invokespecial _prependOrWriteCharacterEscape : (CI)V
    //   149: goto -> 27
    //   152: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #1289	-> 0
    //   #1290	-> 7
    //   #1291	-> 13
    //   #1292	-> 24
    //   #1295	-> 27
    //   #1300	-> 35
    //   #1301	-> 46
    //   #1302	-> 53
    //   #1303	-> 60
    //   #1304	-> 65
    //   #1306	-> 68
    //   #1307	-> 74
    //   #1308	-> 77
    //   #1310	-> 80
    //   #1311	-> 95
    //   #1314	-> 98
    //   #1315	-> 109
    //   #1316	-> 114
    //   #1318	-> 131
    //   #1319	-> 141
    //   #1320	-> 149
    //   #1321	-> 152
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   46	103	7	c	C
    //   109	40	8	flushLen	I
    //   0	153	0	this	Lcom/fasterxml/jackson/core/json/WriterBasedJsonGenerator;
    //   0	153	1	len	I
    //   0	153	2	maxNonEscaped	I
    //   7	146	3	end	I
    //   13	140	4	escCodes	[I
    //   24	129	5	escLimit	I
    //   27	126	6	escCode	I
  }
  
  private void _writeSegmentASCII(int end, int maxNonEscaped) throws IOException, JsonGenerationException {
    int[] escCodes = this._outputEscapes;
    int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
    int ptr = 0;
    int escCode = 0;
    int start = ptr;
    while (ptr < end) {
      char c;
      do {
        c = this._outputBuffer[ptr];
        if (c < escLimit) {
          escCode = escCodes[c];
          if (escCode != 0)
            break; 
        } else if (c > maxNonEscaped) {
          escCode = -1;
          break;
        } 
      } while (++ptr < end);
      int flushLen = ptr - start;
      if (flushLen > 0) {
        this._writer.write(this._outputBuffer, start, flushLen);
        if (ptr >= end)
          break; 
      } 
      ptr++;
      start = _prependOrWriteCharacterEscape(this._outputBuffer, ptr, end, c, escCode);
    } 
  }
  
  private void _writeStringASCII(char[] text, int offset, int len, int maxNonEscaped) throws IOException, JsonGenerationException {
    len += offset;
    int[] escCodes = this._outputEscapes;
    int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
    int escCode = 0;
    while (offset < len) {
      char c;
      int start = offset;
      do {
        c = text[offset];
        if (c < escLimit) {
          escCode = escCodes[c];
          if (escCode != 0)
            break; 
        } else if (c > maxNonEscaped) {
          escCode = -1;
          break;
        } 
      } while (++offset < len);
      int newAmount = offset - start;
      if (newAmount < 32) {
        if (this._outputTail + newAmount > this._outputEnd)
          _flushBuffer(); 
        if (newAmount > 0) {
          System.arraycopy(text, start, this._outputBuffer, this._outputTail, newAmount);
          this._outputTail += newAmount;
        } 
      } else {
        _flushBuffer();
        this._writer.write(text, start, newAmount);
      } 
      if (offset >= len)
        break; 
      offset++;
      _appendCharacterEscape(c, escCode);
    } 
  }
  
  private void _writeStringCustom(int len) throws IOException, JsonGenerationException {
    // Byte code:
    //   0: aload_0
    //   1: getfield _outputTail : I
    //   4: iload_1
    //   5: iadd
    //   6: istore_2
    //   7: aload_0
    //   8: getfield _outputEscapes : [I
    //   11: astore_3
    //   12: aload_0
    //   13: getfield _maximumNonEscapedChar : I
    //   16: iconst_1
    //   17: if_icmpge -> 26
    //   20: ldc_w 65535
    //   23: goto -> 30
    //   26: aload_0
    //   27: getfield _maximumNonEscapedChar : I
    //   30: istore #4
    //   32: aload_3
    //   33: arraylength
    //   34: iload #4
    //   36: iconst_1
    //   37: iadd
    //   38: invokestatic min : (II)I
    //   41: istore #5
    //   43: iconst_0
    //   44: istore #6
    //   46: aload_0
    //   47: getfield _characterEscapes : Lcom/fasterxml/jackson/core/io/CharacterEscapes;
    //   50: astore #7
    //   52: aload_0
    //   53: getfield _outputTail : I
    //   56: iload_2
    //   57: if_icmpge -> 199
    //   60: aload_0
    //   61: getfield _outputBuffer : [C
    //   64: aload_0
    //   65: getfield _outputTail : I
    //   68: caload
    //   69: istore #8
    //   71: iload #8
    //   73: iload #5
    //   75: if_icmpge -> 92
    //   78: aload_3
    //   79: iload #8
    //   81: iaload
    //   82: istore #6
    //   84: iload #6
    //   86: ifeq -> 127
    //   89: goto -> 145
    //   92: iload #8
    //   94: iload #4
    //   96: if_icmple -> 105
    //   99: iconst_m1
    //   100: istore #6
    //   102: goto -> 145
    //   105: aload_0
    //   106: aload #7
    //   108: iload #8
    //   110: invokevirtual getEscapeSequence : (I)Lcom/fasterxml/jackson/core/SerializableString;
    //   113: dup_x1
    //   114: putfield _currentEscape : Lcom/fasterxml/jackson/core/SerializableString;
    //   117: ifnull -> 127
    //   120: bipush #-2
    //   122: istore #6
    //   124: goto -> 145
    //   127: aload_0
    //   128: dup
    //   129: getfield _outputTail : I
    //   132: iconst_1
    //   133: iadd
    //   134: dup_x1
    //   135: putfield _outputTail : I
    //   138: iload_2
    //   139: if_icmplt -> 60
    //   142: goto -> 199
    //   145: aload_0
    //   146: getfield _outputTail : I
    //   149: aload_0
    //   150: getfield _outputHead : I
    //   153: isub
    //   154: istore #9
    //   156: iload #9
    //   158: ifle -> 178
    //   161: aload_0
    //   162: getfield _writer : Ljava/io/Writer;
    //   165: aload_0
    //   166: getfield _outputBuffer : [C
    //   169: aload_0
    //   170: getfield _outputHead : I
    //   173: iload #9
    //   175: invokevirtual write : ([CII)V
    //   178: aload_0
    //   179: dup
    //   180: getfield _outputTail : I
    //   183: iconst_1
    //   184: iadd
    //   185: putfield _outputTail : I
    //   188: aload_0
    //   189: iload #8
    //   191: iload #6
    //   193: invokespecial _prependOrWriteCharacterEscape : (CI)V
    //   196: goto -> 52
    //   199: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #1433	-> 0
    //   #1434	-> 7
    //   #1435	-> 12
    //   #1436	-> 32
    //   #1437	-> 43
    //   #1438	-> 46
    //   #1441	-> 52
    //   #1446	-> 60
    //   #1447	-> 71
    //   #1448	-> 78
    //   #1449	-> 84
    //   #1450	-> 89
    //   #1452	-> 92
    //   #1453	-> 99
    //   #1454	-> 102
    //   #1456	-> 105
    //   #1457	-> 120
    //   #1458	-> 124
    //   #1461	-> 127
    //   #1462	-> 142
    //   #1465	-> 145
    //   #1466	-> 156
    //   #1467	-> 161
    //   #1469	-> 178
    //   #1470	-> 188
    //   #1471	-> 196
    //   #1472	-> 199
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   71	125	8	c	C
    //   156	40	9	flushLen	I
    //   0	200	0	this	Lcom/fasterxml/jackson/core/json/WriterBasedJsonGenerator;
    //   0	200	1	len	I
    //   7	193	2	end	I
    //   12	188	3	escCodes	[I
    //   32	168	4	maxNonEscaped	I
    //   43	157	5	escLimit	I
    //   46	154	6	escCode	I
    //   52	148	7	customEscapes	Lcom/fasterxml/jackson/core/io/CharacterEscapes;
  }
  
  private void _writeSegmentCustom(int end) throws IOException, JsonGenerationException {
    int[] escCodes = this._outputEscapes;
    int maxNonEscaped = (this._maximumNonEscapedChar < 1) ? 65535 : this._maximumNonEscapedChar;
    int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
    CharacterEscapes customEscapes = this._characterEscapes;
    int ptr = 0;
    int escCode = 0;
    int start = ptr;
    while (ptr < end) {
      char c;
      do {
        c = this._outputBuffer[ptr];
        if (c < escLimit) {
          escCode = escCodes[c];
          if (escCode != 0)
            break; 
        } else {
          if (c > maxNonEscaped) {
            escCode = -1;
            break;
          } 
          if ((this._currentEscape = customEscapes.getEscapeSequence(c)) != null) {
            escCode = -2;
            break;
          } 
        } 
      } while (++ptr < end);
      int flushLen = ptr - start;
      if (flushLen > 0) {
        this._writer.write(this._outputBuffer, start, flushLen);
        if (ptr >= end)
          break; 
      } 
      ptr++;
      start = _prependOrWriteCharacterEscape(this._outputBuffer, ptr, end, c, escCode);
    } 
  }
  
  private void _writeStringCustom(char[] text, int offset, int len) throws IOException, JsonGenerationException {
    len += offset;
    int[] escCodes = this._outputEscapes;
    int maxNonEscaped = (this._maximumNonEscapedChar < 1) ? 65535 : this._maximumNonEscapedChar;
    int escLimit = Math.min(escCodes.length, maxNonEscaped + 1);
    CharacterEscapes customEscapes = this._characterEscapes;
    int escCode = 0;
    while (offset < len) {
      char c;
      int start = offset;
      do {
        c = text[offset];
        if (c < escLimit) {
          escCode = escCodes[c];
          if (escCode != 0)
            break; 
        } else {
          if (c > maxNonEscaped) {
            escCode = -1;
            break;
          } 
          if ((this._currentEscape = customEscapes.getEscapeSequence(c)) != null) {
            escCode = -2;
            break;
          } 
        } 
      } while (++offset < len);
      int newAmount = offset - start;
      if (newAmount < 32) {
        if (this._outputTail + newAmount > this._outputEnd)
          _flushBuffer(); 
        if (newAmount > 0) {
          System.arraycopy(text, start, this._outputBuffer, this._outputTail, newAmount);
          this._outputTail += newAmount;
        } 
      } else {
        _flushBuffer();
        this._writer.write(text, start, newAmount);
      } 
      if (offset >= len)
        break; 
      offset++;
      _appendCharacterEscape(c, escCode);
    } 
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
        this._outputBuffer[this._outputTail++] = '\\';
        this._outputBuffer[this._outputTail++] = 'n';
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
        this._outputBuffer[this._outputTail++] = '\\';
        this._outputBuffer[this._outputTail++] = 'n';
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
        this._outputBuffer[this._outputTail++] = '\\';
        this._outputBuffer[this._outputTail++] = 'n';
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
  
  private int _readMore(InputStream in, byte[] readBuffer, int inputPtr, int inputEnd, int maxRead) throws IOException {
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
  
  private final void _writeNull() throws IOException {
    if (this._outputTail + 4 >= this._outputEnd)
      _flushBuffer(); 
    int ptr = this._outputTail;
    char[] buf = this._outputBuffer;
    buf[ptr] = 'n';
    buf[++ptr] = 'u';
    buf[++ptr] = 'l';
    buf[++ptr] = 'l';
    this._outputTail = ptr + 1;
  }
  
  private void _prependOrWriteCharacterEscape(char ch, int escCode) throws IOException, JsonGenerationException {
    String escape;
    if (escCode >= 0) {
      if (this._outputTail >= 2) {
        int ptr = this._outputTail - 2;
        this._outputHead = ptr;
        this._outputBuffer[ptr++] = '\\';
        this._outputBuffer[ptr] = (char)escCode;
        return;
      } 
      char[] buf = this._entityBuffer;
      if (buf == null)
        buf = _allocateEntityBuffer(); 
      this._outputHead = this._outputTail;
      buf[1] = (char)escCode;
      this._writer.write(buf, 0, 2);
      return;
    } 
    if (escCode != -2) {
      char[] HEX_CHARS = getHexChars();
      if (this._outputTail >= 6) {
        char[] arrayOfChar = this._outputBuffer;
        int ptr = this._outputTail - 6;
        this._outputHead = ptr;
        arrayOfChar[ptr] = '\\';
        arrayOfChar[++ptr] = 'u';
        if (ch > 'ÿ') {
          int hi = ch >> 8 & 0xFF;
          arrayOfChar[++ptr] = HEX_CHARS[hi >> 4];
          arrayOfChar[++ptr] = HEX_CHARS[hi & 0xF];
          ch = (char)(ch & 0xFF);
        } else {
          arrayOfChar[++ptr] = '0';
          arrayOfChar[++ptr] = '0';
        } 
        arrayOfChar[++ptr] = HEX_CHARS[ch >> 4];
        arrayOfChar[++ptr] = HEX_CHARS[ch & 0xF];
        return;
      } 
      char[] buf = this._entityBuffer;
      if (buf == null)
        buf = _allocateEntityBuffer(); 
      this._outputHead = this._outputTail;
      if (ch > 'ÿ') {
        int hi = ch >> 8 & 0xFF;
        int lo = ch & 0xFF;
        buf[10] = HEX_CHARS[hi >> 4];
        buf[11] = HEX_CHARS[hi & 0xF];
        buf[12] = HEX_CHARS[lo >> 4];
        buf[13] = HEX_CHARS[lo & 0xF];
        this._writer.write(buf, 8, 6);
      } else {
        buf[6] = HEX_CHARS[ch >> 4];
        buf[7] = HEX_CHARS[ch & 0xF];
        this._writer.write(buf, 2, 6);
      } 
      return;
    } 
    if (this._currentEscape == null) {
      escape = this._characterEscapes.getEscapeSequence(ch).getValue();
    } else {
      escape = this._currentEscape.getValue();
      this._currentEscape = null;
    } 
    int len = escape.length();
    if (this._outputTail >= len) {
      int ptr = this._outputTail - len;
      this._outputHead = ptr;
      escape.getChars(0, len, this._outputBuffer, ptr);
      return;
    } 
    this._outputHead = this._outputTail;
    this._writer.write(escape);
  }
  
  private int _prependOrWriteCharacterEscape(char[] buffer, int ptr, int end, char ch, int escCode) throws IOException, JsonGenerationException {
    String escape;
    if (escCode >= 0) {
      if (ptr > 1 && ptr < end) {
        ptr -= 2;
        buffer[ptr] = '\\';
        buffer[ptr + 1] = (char)escCode;
      } else {
        char[] ent = this._entityBuffer;
        if (ent == null)
          ent = _allocateEntityBuffer(); 
        ent[1] = (char)escCode;
        this._writer.write(ent, 0, 2);
      } 
      return ptr;
    } 
    if (escCode != -2) {
      char[] HEX_CHARS = getHexChars();
      if (ptr > 5 && ptr < end) {
        ptr -= 6;
        buffer[ptr++] = '\\';
        buffer[ptr++] = 'u';
        if (ch > 'ÿ') {
          int hi = ch >> 8 & 0xFF;
          buffer[ptr++] = HEX_CHARS[hi >> 4];
          buffer[ptr++] = HEX_CHARS[hi & 0xF];
          ch = (char)(ch & 0xFF);
        } else {
          buffer[ptr++] = '0';
          buffer[ptr++] = '0';
        } 
        buffer[ptr++] = HEX_CHARS[ch >> 4];
        buffer[ptr] = HEX_CHARS[ch & 0xF];
        ptr -= 5;
      } else {
        char[] ent = this._entityBuffer;
        if (ent == null)
          ent = _allocateEntityBuffer(); 
        this._outputHead = this._outputTail;
        if (ch > 'ÿ') {
          int hi = ch >> 8 & 0xFF;
          int lo = ch & 0xFF;
          ent[10] = HEX_CHARS[hi >> 4];
          ent[11] = HEX_CHARS[hi & 0xF];
          ent[12] = HEX_CHARS[lo >> 4];
          ent[13] = HEX_CHARS[lo & 0xF];
          this._writer.write(ent, 8, 6);
        } else {
          ent[6] = HEX_CHARS[ch >> 4];
          ent[7] = HEX_CHARS[ch & 0xF];
          this._writer.write(ent, 2, 6);
        } 
      } 
      return ptr;
    } 
    if (this._currentEscape == null) {
      escape = this._characterEscapes.getEscapeSequence(ch).getValue();
    } else {
      escape = this._currentEscape.getValue();
      this._currentEscape = null;
    } 
    int len = escape.length();
    if (ptr >= len && ptr < end) {
      ptr -= len;
      escape.getChars(0, len, buffer, ptr);
    } else {
      this._writer.write(escape);
    } 
    return ptr;
  }
  
  private void _appendCharacterEscape(char ch, int escCode) throws IOException, JsonGenerationException {
    String escape;
    if (escCode >= 0) {
      if (this._outputTail + 2 > this._outputEnd)
        _flushBuffer(); 
      this._outputBuffer[this._outputTail++] = '\\';
      this._outputBuffer[this._outputTail++] = (char)escCode;
      return;
    } 
    if (escCode != -2) {
      if (this._outputTail + 5 >= this._outputEnd)
        _flushBuffer(); 
      int ptr = this._outputTail;
      char[] buf = this._outputBuffer;
      char[] HEX_CHARS = getHexChars();
      buf[ptr++] = '\\';
      buf[ptr++] = 'u';
      if (ch > 'ÿ') {
        int hi = ch >> 8 & 0xFF;
        buf[ptr++] = HEX_CHARS[hi >> 4];
        buf[ptr++] = HEX_CHARS[hi & 0xF];
        ch = (char)(ch & 0xFF);
      } else {
        buf[ptr++] = '0';
        buf[ptr++] = '0';
      } 
      buf[ptr++] = HEX_CHARS[ch >> 4];
      buf[ptr++] = HEX_CHARS[ch & 0xF];
      this._outputTail = ptr;
      return;
    } 
    if (this._currentEscape == null) {
      escape = this._characterEscapes.getEscapeSequence(ch).getValue();
    } else {
      escape = this._currentEscape.getValue();
      this._currentEscape = null;
    } 
    int len = escape.length();
    if (this._outputTail + len > this._outputEnd) {
      _flushBuffer();
      if (len > this._outputEnd) {
        this._writer.write(escape);
        return;
      } 
    } 
    escape.getChars(0, len, this._outputBuffer, this._outputTail);
    this._outputTail += len;
  }
  
  private char[] _allocateEntityBuffer() {
    char[] buf = new char[14];
    buf[0] = '\\';
    buf[2] = '\\';
    buf[3] = 'u';
    buf[4] = '0';
    buf[5] = '0';
    buf[8] = '\\';
    buf[9] = 'u';
    this._entityBuffer = buf;
    return buf;
  }
  
  private char[] _allocateCopyBuffer() {
    if (this._copyBuffer == null)
      this._copyBuffer = this._ioContext.allocNameCopyBuffer(2000); 
    return this._copyBuffer;
  }
  
  protected void _flushBuffer() throws IOException {
    int len = this._outputTail - this._outputHead;
    if (len > 0) {
      int offset = this._outputHead;
      this._outputTail = this._outputHead = 0;
      this._writer.write(this._outputBuffer, offset, len);
    } 
  }
}
