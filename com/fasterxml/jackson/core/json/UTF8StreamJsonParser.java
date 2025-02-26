package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.exc.StreamConstraintsException;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

public class UTF8StreamJsonParser extends ParserBase {
  static final byte BYTE_LF = 10;
  
  private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
  
  private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
  
  private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
  
  private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
  
  private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
  
  private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
  
  private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
  
  private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
  
  private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
  
  protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
  
  protected ObjectCodec _objectCodec;
  
  protected final ByteQuadsCanonicalizer _symbols;
  
  protected int[] _quadBuffer = new int[16];
  
  protected boolean _tokenIncomplete;
  
  private int _quad1;
  
  protected int _nameStartOffset;
  
  protected int _nameStartRow;
  
  protected int _nameStartCol;
  
  protected InputStream _inputStream;
  
  protected byte[] _inputBuffer;
  
  protected boolean _bufferRecyclable;
  
  @Deprecated
  public UTF8StreamJsonParser(IOContext ctxt, int features, InputStream in, ObjectCodec codec, ByteQuadsCanonicalizer sym, byte[] inputBuffer, int start, int end, boolean bufferRecyclable) {
    this(ctxt, features, in, codec, sym, inputBuffer, start, end, 0, bufferRecyclable);
  }
  
  public UTF8StreamJsonParser(IOContext ctxt, int features, InputStream in, ObjectCodec codec, ByteQuadsCanonicalizer sym, byte[] inputBuffer, int start, int end, int bytesPreProcessed, boolean bufferRecyclable) {
    super(ctxt, features);
    this._inputStream = in;
    this._objectCodec = codec;
    this._symbols = sym;
    this._inputBuffer = inputBuffer;
    this._inputPtr = start;
    this._inputEnd = end;
    this._currInputRowStart = start - bytesPreProcessed;
    this._currInputProcessed = (-start + bytesPreProcessed);
    this._bufferRecyclable = bufferRecyclable;
  }
  
  public ObjectCodec getCodec() {
    return this._objectCodec;
  }
  
  public void setCodec(ObjectCodec c) {
    this._objectCodec = c;
  }
  
  public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
    return JSON_READ_CAPABILITIES;
  }
  
  public int releaseBuffered(OutputStream out) throws IOException {
    int count = this._inputEnd - this._inputPtr;
    if (count < 1)
      return 0; 
    int origPtr = this._inputPtr;
    this._inputPtr += count;
    out.write(this._inputBuffer, origPtr, count);
    return count;
  }
  
  public Object getInputSource() {
    return this._inputStream;
  }
  
  protected final boolean _loadMore() throws IOException {
    if (this._inputStream != null) {
      int space = this._inputBuffer.length;
      if (space == 0)
        return false; 
      int bufSize = this._inputEnd;
      this._currInputProcessed += bufSize;
      this._currInputRowStart -= bufSize;
      streamReadConstraints().validateDocumentLength(this._currInputProcessed);
      int count = this._inputStream.read(this._inputBuffer, 0, space);
      if (count > 0) {
        this._nameStartOffset -= bufSize;
        this._inputPtr = 0;
        this._inputEnd = count;
        return true;
      } 
      this._inputPtr = this._inputEnd = 0;
      _closeInput();
      if (count == 0)
        throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes"); 
    } 
    return false;
  }
  
  protected void _closeInput() throws IOException {
    if (this._inputStream != null) {
      if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))
        this._inputStream.close(); 
      this._inputStream = null;
    } 
  }
  
  protected void _releaseBuffers() throws IOException {
    super._releaseBuffers();
    this._symbols.release();
    if (this._bufferRecyclable) {
      byte[] buf = this._inputBuffer;
      if (buf != null)
        if (buf != NO_BYTES) {
          this._inputBuffer = NO_BYTES;
          this._ioContext.releaseReadIOBuffer(buf);
        }  
    } 
  }
  
  public String getText() throws IOException {
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete) {
        this._tokenIncomplete = false;
        return _finishAndReturnString();
      } 
      return this._textBuffer.contentsAsString();
    } 
    return _getText2(this._currToken);
  }
  
  public int getText(Writer writer) throws IOException {
    JsonToken t = this._currToken;
    if (t == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete) {
        this._tokenIncomplete = false;
        _finishString();
      } 
      return this._textBuffer.contentsToWriter(writer);
    } 
    if (t == JsonToken.FIELD_NAME) {
      String n = this._parsingContext.getCurrentName();
      writer.write(n);
      return n.length();
    } 
    if (t != null) {
      if (t.isNumeric())
        return this._textBuffer.contentsToWriter(writer); 
      char[] ch = t.asCharArray();
      writer.write(ch);
      return ch.length;
    } 
    return 0;
  }
  
  public String getValueAsString() throws IOException {
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete) {
        this._tokenIncomplete = false;
        return _finishAndReturnString();
      } 
      return this._textBuffer.contentsAsString();
    } 
    if (this._currToken == JsonToken.FIELD_NAME)
      return getCurrentName(); 
    return super.getValueAsString(null);
  }
  
  public String getValueAsString(String defValue) throws IOException {
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete) {
        this._tokenIncomplete = false;
        return _finishAndReturnString();
      } 
      return this._textBuffer.contentsAsString();
    } 
    if (this._currToken == JsonToken.FIELD_NAME)
      return getCurrentName(); 
    return super.getValueAsString(defValue);
  }
  
  public int getValueAsInt() throws IOException {
    JsonToken t = this._currToken;
    if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
      if ((this._numTypesValid & 0x1) == 0) {
        if (this._numTypesValid == 0)
          return _parseIntValue(); 
        if ((this._numTypesValid & 0x1) == 0)
          convertNumberToInt(); 
      } 
      return this._numberInt;
    } 
    return super.getValueAsInt(0);
  }
  
  public int getValueAsInt(int defValue) throws IOException {
    JsonToken t = this._currToken;
    if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
      if ((this._numTypesValid & 0x1) == 0) {
        if (this._numTypesValid == 0)
          return _parseIntValue(); 
        if ((this._numTypesValid & 0x1) == 0)
          convertNumberToInt(); 
      } 
      return this._numberInt;
    } 
    return super.getValueAsInt(defValue);
  }
  
  protected final String _getText2(JsonToken t) throws IOException {
    if (t == null)
      return null; 
    switch (t.id()) {
      case 5:
        return this._parsingContext.getCurrentName();
      case 6:
      case 7:
      case 8:
        return this._textBuffer.contentsAsString();
    } 
    return t.asString();
  }
  
  public char[] getTextCharacters() throws IOException {
    if (this._currToken != null) {
      switch (this._currToken.id()) {
        case 5:
          if (!this._nameCopied) {
            String name = this._parsingContext.getCurrentName();
            int nameLen = name.length();
            if (this._nameCopyBuffer == null) {
              this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(nameLen);
            } else if (this._nameCopyBuffer.length < nameLen) {
              this._nameCopyBuffer = new char[nameLen];
            } 
            name.getChars(0, nameLen, this._nameCopyBuffer, 0);
            this._nameCopied = true;
          } 
          return this._nameCopyBuffer;
        case 6:
          if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
          } 
        case 7:
        case 8:
          return this._textBuffer.getTextBuffer();
      } 
      return this._currToken.asCharArray();
    } 
    return null;
  }
  
  public int getTextLength() throws IOException {
    if (this._currToken != null) {
      switch (this._currToken.id()) {
        case 5:
          return this._parsingContext.getCurrentName().length();
        case 6:
          if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
          } 
        case 7:
        case 8:
          return this._textBuffer.size();
      } 
      return (this._currToken.asCharArray()).length;
    } 
    return 0;
  }
  
  public int getTextOffset() throws IOException {
    if (this._currToken != null)
      switch (this._currToken.id()) {
        case 5:
          return 0;
        case 6:
          if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
          } 
        case 7:
        case 8:
          return this._textBuffer.getTextOffset();
      }  
    return 0;
  }
  
  public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
    if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null))
      _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary"); 
    if (this._tokenIncomplete) {
      try {
        this._binaryValue = _decodeBase64(b64variant);
      } catch (IllegalArgumentException iae) {
        throw _constructError("Failed to decode VALUE_STRING as base64 (" + b64variant + "): " + iae.getMessage());
      } 
      this._tokenIncomplete = false;
    } else if (this._binaryValue == null) {
      ByteArrayBuilder builder = _getByteArrayBuilder();
      _decodeBase64(getText(), builder, b64variant);
      this._binaryValue = builder.toByteArray();
    } 
    return this._binaryValue;
  }
  
  public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
    if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
      byte[] b = getBinaryValue(b64variant);
      out.write(b);
      return b.length;
    } 
    byte[] buf = this._ioContext.allocBase64Buffer();
    try {
      return _readBinary(b64variant, out, buf);
    } finally {
      this._ioContext.releaseBase64Buffer(buf);
    } 
  }
  
  protected int _readBinary(Base64Variant b64variant, OutputStream out, byte[] buffer) throws IOException {
    int outputPtr = 0;
    int outputEnd = buffer.length - 3;
    int outputCount = 0;
    while (true) {
      if (this._inputPtr >= this._inputEnd)
        _loadMoreGuaranteed(); 
      int ch = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (ch > 32) {
        int bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (ch == 34)
            break; 
          bits = _decodeBase64Escape(b64variant, ch, 0);
          if (bits < 0)
            continue; 
        } 
        if (outputPtr > outputEnd) {
          outputCount += outputPtr;
          out.write(buffer, 0, outputPtr);
          outputPtr = 0;
        } 
        int decodedData = bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++] & 0xFF;
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0)
          bits = _decodeBase64Escape(b64variant, ch, 1); 
        decodedData = decodedData << 6 | bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++] & 0xFF;
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == 34) {
              decodedData >>= 4;
              buffer[outputPtr++] = (byte)decodedData;
              if (b64variant.requiresPaddingOnRead()) {
                this._inputPtr--;
                _handleBase64MissingPadding(b64variant);
              } 
              break;
            } 
            bits = _decodeBase64Escape(b64variant, ch, 2);
          } 
          if (bits == -2) {
            if (this._inputPtr >= this._inputEnd)
              _loadMoreGuaranteed(); 
            ch = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (!b64variant.usesPaddingChar(ch) && 
              _decodeBase64Escape(b64variant, ch, 3) != -2)
              throw reportInvalidBase64Char(b64variant, ch, 3, "expected padding character '" + b64variant.getPaddingChar() + "'"); 
            decodedData >>= 4;
            buffer[outputPtr++] = (byte)decodedData;
            continue;
          } 
        } 
        decodedData = decodedData << 6 | bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++] & 0xFF;
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == 34) {
              decodedData >>= 2;
              buffer[outputPtr++] = (byte)(decodedData >> 8);
              buffer[outputPtr++] = (byte)decodedData;
              if (b64variant.requiresPaddingOnRead()) {
                this._inputPtr--;
                _handleBase64MissingPadding(b64variant);
              } 
              break;
            } 
            bits = _decodeBase64Escape(b64variant, ch, 3);
          } 
          if (bits == -2) {
            decodedData >>= 2;
            buffer[outputPtr++] = (byte)(decodedData >> 8);
            buffer[outputPtr++] = (byte)decodedData;
            continue;
          } 
        } 
        decodedData = decodedData << 6 | bits;
        buffer[outputPtr++] = (byte)(decodedData >> 16);
        buffer[outputPtr++] = (byte)(decodedData >> 8);
        buffer[outputPtr++] = (byte)decodedData;
      } 
    } 
    this._tokenIncomplete = false;
    if (outputPtr > 0) {
      outputCount += outputPtr;
      out.write(buffer, 0, outputPtr);
    } 
    return outputCount;
  }
  
  public JsonToken nextToken() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME)
      return _nextAfterName(); 
    this._numTypesValid = 0;
    if (this._tokenIncomplete)
      _skipString(); 
    int i = _skipWSOrEnd();
    if (i < 0) {
      close();
      return this._currToken = null;
    } 
    this._binaryValue = null;
    if (i == 93) {
      _closeArrayScope();
      return this._currToken = JsonToken.END_ARRAY;
    } 
    if (i == 125) {
      _closeObjectScope();
      return this._currToken = JsonToken.END_OBJECT;
    } 
    if (this._parsingContext.expectComma()) {
      if (i != 44)
        _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries"); 
      i = _skipWS();
      if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (
        i == 93 || i == 125))
        return _closeScope(i); 
    } 
    if (!this._parsingContext.inObject()) {
      _updateLocation();
      return _nextTokenNotInObject(i);
    } 
    _updateNameLocation();
    String n = _parseName(i);
    this._parsingContext.setCurrentName(n);
    this._currToken = JsonToken.FIELD_NAME;
    i = _skipColon();
    _updateLocation();
    if (i == 34) {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return this._currToken;
    } 
    switch (i) {
      case 45:
        t = _parseSignedNumber(true);
        this._nextToken = t;
        return this._currToken;
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          t = _parseSignedNumber(false);
        } else {
          t = _handleUnexpectedValue(i);
        } 
        this._nextToken = t;
        return this._currToken;
      case 46:
        t = _parseFloatThatStartsWithPeriod(false, false);
        this._nextToken = t;
        return this._currToken;
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        t = _parseUnsignedNumber(i);
        this._nextToken = t;
        return this._currToken;
      case 102:
        _matchFalse();
        t = JsonToken.VALUE_FALSE;
        this._nextToken = t;
        return this._currToken;
      case 110:
        _matchNull();
        t = JsonToken.VALUE_NULL;
        this._nextToken = t;
        return this._currToken;
      case 116:
        _matchTrue();
        t = JsonToken.VALUE_TRUE;
        this._nextToken = t;
        return this._currToken;
      case 91:
        t = JsonToken.START_ARRAY;
        this._nextToken = t;
        return this._currToken;
      case 123:
        t = JsonToken.START_OBJECT;
        this._nextToken = t;
        return this._currToken;
    } 
    JsonToken t = _handleUnexpectedValue(i);
    this._nextToken = t;
    return this._currToken;
  }
  
  private final JsonToken _nextTokenNotInObject(int i) throws IOException {
    if (i == 34) {
      this._tokenIncomplete = true;
      return this._currToken = JsonToken.VALUE_STRING;
    } 
    switch (i) {
      case 91:
        createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        return this._currToken = JsonToken.START_ARRAY;
      case 123:
        createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        return this._currToken = JsonToken.START_OBJECT;
      case 116:
        _matchTrue();
        return this._currToken = JsonToken.VALUE_TRUE;
      case 102:
        _matchFalse();
        return this._currToken = JsonToken.VALUE_FALSE;
      case 110:
        _matchNull();
        return this._currToken = JsonToken.VALUE_NULL;
      case 45:
        return this._currToken = _parseSignedNumber(true);
      case 43:
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()))
          return this._currToken = _handleUnexpectedValue(i); 
        return this._currToken = _parseSignedNumber(false);
      case 46:
        return this._currToken = _parseFloatThatStartsWithPeriod(false, false);
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        return this._currToken = _parseUnsignedNumber(i);
    } 
    return this._currToken = _handleUnexpectedValue(i);
  }
  
  private final JsonToken _nextAfterName() throws IOException {
    this._nameCopied = false;
    JsonToken t = this._nextToken;
    this._nextToken = null;
    if (t == JsonToken.START_ARRAY) {
      createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
    } else if (t == JsonToken.START_OBJECT) {
      createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
    } 
    return this._currToken = t;
  }
  
  public void finishToken() throws IOException {
    if (this._tokenIncomplete) {
      this._tokenIncomplete = false;
      _finishString();
    } 
  }
  
  public boolean nextFieldName(SerializableString str) throws IOException {
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME) {
      _nextAfterName();
      return false;
    } 
    if (this._tokenIncomplete)
      _skipString(); 
    int i = _skipWSOrEnd();
    if (i < 0) {
      close();
      this._currToken = null;
      return false;
    } 
    this._binaryValue = null;
    if (i == 93) {
      _closeArrayScope();
      this._currToken = JsonToken.END_ARRAY;
      return false;
    } 
    if (i == 125) {
      _closeObjectScope();
      this._currToken = JsonToken.END_OBJECT;
      return false;
    } 
    if (this._parsingContext.expectComma()) {
      if (i != 44)
        _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries"); 
      i = _skipWS();
      if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (
        i == 93 || i == 125)) {
        _closeScope(i);
        return false;
      } 
    } 
    if (!this._parsingContext.inObject()) {
      _updateLocation();
      _nextTokenNotInObject(i);
      return false;
    } 
    _updateNameLocation();
    if (i == 34) {
      byte[] nameBytes = str.asQuotedUTF8();
      int len = nameBytes.length;
      if (this._inputPtr + len + 4 < this._inputEnd) {
        int end = this._inputPtr + len;
        if (this._inputBuffer[end] == 34) {
          int offset = 0;
          int ptr = this._inputPtr;
          while (true) {
            if (ptr == end) {
              this._parsingContext.setCurrentName(str.getValue());
              i = _skipColonFast(ptr + 1);
              _isNextTokenNameYes(i);
              return true;
            } 
            if (nameBytes[offset] != this._inputBuffer[ptr])
              break; 
            offset++;
            ptr++;
          } 
        } 
      } 
    } 
    return _isNextTokenNameMaybe(i, str);
  }
  
  public String nextFieldName() throws IOException {
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME) {
      _nextAfterName();
      return null;
    } 
    if (this._tokenIncomplete)
      _skipString(); 
    int i = _skipWSOrEnd();
    if (i < 0) {
      close();
      this._currToken = null;
      return null;
    } 
    this._binaryValue = null;
    if (i == 93) {
      _closeArrayScope();
      this._currToken = JsonToken.END_ARRAY;
      return null;
    } 
    if (i == 125) {
      _closeObjectScope();
      this._currToken = JsonToken.END_OBJECT;
      return null;
    } 
    if (this._parsingContext.expectComma()) {
      if (i != 44)
        _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries"); 
      i = _skipWS();
      if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (
        i == 93 || i == 125)) {
        _closeScope(i);
        return null;
      } 
    } 
    if (!this._parsingContext.inObject()) {
      _updateLocation();
      _nextTokenNotInObject(i);
      return null;
    } 
    _updateNameLocation();
    String nameStr = _parseName(i);
    this._parsingContext.setCurrentName(nameStr);
    this._currToken = JsonToken.FIELD_NAME;
    i = _skipColon();
    _updateLocation();
    if (i == 34) {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return nameStr;
    } 
    switch (i) {
      case 45:
        t = _parseSignedNumber(true);
        this._nextToken = t;
        return nameStr;
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          t = _parseSignedNumber(false);
        } else {
          t = _handleUnexpectedValue(i);
        } 
        this._nextToken = t;
        return nameStr;
      case 46:
        t = _parseFloatThatStartsWithPeriod(false, false);
        this._nextToken = t;
        return nameStr;
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        t = _parseUnsignedNumber(i);
        this._nextToken = t;
        return nameStr;
      case 102:
        _matchFalse();
        t = JsonToken.VALUE_FALSE;
        this._nextToken = t;
        return nameStr;
      case 110:
        _matchNull();
        t = JsonToken.VALUE_NULL;
        this._nextToken = t;
        return nameStr;
      case 116:
        _matchTrue();
        t = JsonToken.VALUE_TRUE;
        this._nextToken = t;
        return nameStr;
      case 91:
        t = JsonToken.START_ARRAY;
        this._nextToken = t;
        return nameStr;
      case 123:
        t = JsonToken.START_OBJECT;
        this._nextToken = t;
        return nameStr;
    } 
    JsonToken t = _handleUnexpectedValue(i);
    this._nextToken = t;
    return nameStr;
  }
  
  private final int _skipColonFast(int ptr) throws IOException {
    int i = this._inputBuffer[ptr++];
    if (i == 58) {
      i = this._inputBuffer[ptr++];
      if (i > 32) {
        if (i != 47 && i != 35) {
          this._inputPtr = ptr;
          return i;
        } 
      } else if (i == 32 || i == 9) {
        i = this._inputBuffer[ptr++];
        if (i > 32 && 
          i != 47 && i != 35) {
          this._inputPtr = ptr;
          return i;
        } 
      } 
      this._inputPtr = ptr - 1;
      return _skipColon2(true);
    } 
    if (i == 32 || i == 9)
      i = this._inputBuffer[ptr++]; 
    if (i == 58) {
      i = this._inputBuffer[ptr++];
      if (i > 32) {
        if (i != 47 && i != 35) {
          this._inputPtr = ptr;
          return i;
        } 
      } else if (i == 32 || i == 9) {
        i = this._inputBuffer[ptr++];
        if (i > 32 && 
          i != 47 && i != 35) {
          this._inputPtr = ptr;
          return i;
        } 
      } 
      this._inputPtr = ptr - 1;
      return _skipColon2(true);
    } 
    this._inputPtr = ptr - 1;
    return _skipColon2(false);
  }
  
  private final void _isNextTokenNameYes(int i) throws IOException {
    this._currToken = JsonToken.FIELD_NAME;
    _updateLocation();
    switch (i) {
      case 34:
        this._tokenIncomplete = true;
        this._nextToken = JsonToken.VALUE_STRING;
        return;
      case 91:
        this._nextToken = JsonToken.START_ARRAY;
        return;
      case 123:
        this._nextToken = JsonToken.START_OBJECT;
        return;
      case 116:
        _matchTrue();
        this._nextToken = JsonToken.VALUE_TRUE;
        return;
      case 102:
        _matchFalse();
        this._nextToken = JsonToken.VALUE_FALSE;
        return;
      case 110:
        _matchNull();
        this._nextToken = JsonToken.VALUE_NULL;
        return;
      case 45:
        this._nextToken = _parseSignedNumber(true);
        return;
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          this._nextToken = _parseSignedNumber(false);
        } else {
          this._nextToken = _handleUnexpectedValue(i);
        } 
        return;
      case 46:
        this._nextToken = _parseFloatThatStartsWithPeriod(false, false);
        return;
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        this._nextToken = _parseUnsignedNumber(i);
        return;
    } 
    this._nextToken = _handleUnexpectedValue(i);
  }
  
  private final boolean _isNextTokenNameMaybe(int i, SerializableString str) throws IOException {
    String n = _parseName(i);
    this._parsingContext.setCurrentName(n);
    boolean match = n.equals(str.getValue());
    this._currToken = JsonToken.FIELD_NAME;
    i = _skipColon();
    _updateLocation();
    if (i == 34) {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return match;
    } 
    switch (i) {
      case 91:
        t = JsonToken.START_ARRAY;
        this._nextToken = t;
        return match;
      case 123:
        t = JsonToken.START_OBJECT;
        this._nextToken = t;
        return match;
      case 116:
        _matchTrue();
        t = JsonToken.VALUE_TRUE;
        this._nextToken = t;
        return match;
      case 102:
        _matchFalse();
        t = JsonToken.VALUE_FALSE;
        this._nextToken = t;
        return match;
      case 110:
        _matchNull();
        t = JsonToken.VALUE_NULL;
        this._nextToken = t;
        return match;
      case 45:
        t = _parseSignedNumber(true);
        this._nextToken = t;
        return match;
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          t = _parseSignedNumber(false);
        } else {
          t = _handleUnexpectedValue(i);
        } 
        this._nextToken = t;
        return match;
      case 46:
        t = _parseFloatThatStartsWithPeriod(false, false);
        this._nextToken = t;
        return match;
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        t = _parseUnsignedNumber(i);
        this._nextToken = t;
        return match;
    } 
    JsonToken t = _handleUnexpectedValue(i);
    this._nextToken = t;
    return match;
  }
  
  public String nextTextValue() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken t = this._nextToken;
      this._nextToken = null;
      this._currToken = t;
      if (t == JsonToken.VALUE_STRING) {
        if (this._tokenIncomplete) {
          this._tokenIncomplete = false;
          return _finishAndReturnString();
        } 
        return this._textBuffer.contentsAsString();
      } 
      if (t == JsonToken.START_ARRAY) {
        createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      } else if (t == JsonToken.START_OBJECT) {
        createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      } 
      return null;
    } 
    return (nextToken() == JsonToken.VALUE_STRING) ? getText() : null;
  }
  
  public int nextIntValue(int defaultValue) throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken t = this._nextToken;
      this._nextToken = null;
      this._currToken = t;
      if (t == JsonToken.VALUE_NUMBER_INT)
        return getIntValue(); 
      if (t == JsonToken.START_ARRAY) {
        createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      } else if (t == JsonToken.START_OBJECT) {
        createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      } 
      return defaultValue;
    } 
    return (nextToken() == JsonToken.VALUE_NUMBER_INT) ? getIntValue() : defaultValue;
  }
  
  public long nextLongValue(long defaultValue) throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken t = this._nextToken;
      this._nextToken = null;
      this._currToken = t;
      if (t == JsonToken.VALUE_NUMBER_INT)
        return getLongValue(); 
      if (t == JsonToken.START_ARRAY) {
        createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      } else if (t == JsonToken.START_OBJECT) {
        createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      } 
      return defaultValue;
    } 
    return (nextToken() == JsonToken.VALUE_NUMBER_INT) ? getLongValue() : defaultValue;
  }
  
  public Boolean nextBooleanValue() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken jsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = jsonToken;
      if (jsonToken == JsonToken.VALUE_TRUE)
        return Boolean.TRUE; 
      if (jsonToken == JsonToken.VALUE_FALSE)
        return Boolean.FALSE; 
      if (jsonToken == JsonToken.START_ARRAY) {
        createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      } else if (jsonToken == JsonToken.START_OBJECT) {
        createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      } 
      return null;
    } 
    JsonToken t = nextToken();
    if (t == JsonToken.VALUE_TRUE)
      return Boolean.TRUE; 
    if (t == JsonToken.VALUE_FALSE)
      return Boolean.FALSE; 
    return null;
  }
  
  @Deprecated
  protected final JsonToken _parseFloatThatStartsWithPeriod() throws IOException {
    return _parseFloatThatStartsWithPeriod(false, false);
  }
  
  protected final JsonToken _parseFloatThatStartsWithPeriod(boolean neg, boolean hasSign) throws IOException {
    if (!isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
      return _handleUnexpectedValue(46); 
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int outPtr = 0;
    if (neg)
      outBuf[outPtr++] = '-'; 
    return _parseFloat(outBuf, outPtr, 46, neg, 0);
  }
  
  protected JsonToken _parseUnsignedNumber(int c) throws IOException {
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    if (c == 48)
      c = _verifyNoLeadingZeroes(); 
    outBuf[0] = (char)c;
    int intLen = 1;
    int outPtr = 1;
    int end = Math.min(this._inputEnd, this._inputPtr + outBuf.length - 1);
    while (true) {
      if (this._inputPtr >= end)
        return _parseNumber2(outBuf, outPtr, false, intLen); 
      c = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (c < 48 || c > 57)
        break; 
      intLen++;
      outBuf[outPtr++] = (char)c;
    } 
    if (c == 46 || c == 101 || c == 69)
      return _parseFloat(outBuf, outPtr, c, false, intLen); 
    this._inputPtr--;
    this._textBuffer.setCurrentLength(outPtr);
    if (this._parsingContext.inRoot())
      _verifyRootSpace(c); 
    return resetInt(false, intLen);
  }
  
  private final JsonToken _parseSignedNumber(boolean negative) throws IOException {
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int outPtr = 0;
    if (negative)
      outBuf[outPtr++] = '-'; 
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    int c = this._inputBuffer[this._inputPtr++] & 0xFF;
    if (c <= 48) {
      if (c != 48) {
        if (c == 46)
          return _parseFloatThatStartsWithPeriod(negative, true); 
        return _handleInvalidNumberStart(c, negative, true);
      } 
      c = _verifyNoLeadingZeroes();
    } else if (c > 57) {
      return _handleInvalidNumberStart(c, negative, true);
    } 
    outBuf[outPtr++] = (char)c;
    int intLen = 1;
    int end = Math.min(this._inputEnd, this._inputPtr + outBuf.length - outPtr);
    while (true) {
      if (this._inputPtr >= end)
        return _parseNumber2(outBuf, outPtr, negative, intLen); 
      c = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (c < 48 || c > 57)
        break; 
      intLen++;
      outBuf[outPtr++] = (char)c;
    } 
    if (c == 46 || c == 101 || c == 69)
      return _parseFloat(outBuf, outPtr, c, negative, intLen); 
    this._inputPtr--;
    this._textBuffer.setCurrentLength(outPtr);
    if (this._parsingContext.inRoot())
      _verifyRootSpace(c); 
    return resetInt(negative, intLen);
  }
  
  private final JsonToken _parseNumber2(char[] outBuf, int outPtr, boolean negative, int intPartLength) throws IOException {
    while (true) {
      if (this._inputPtr >= this._inputEnd && !_loadMore()) {
        this._textBuffer.setCurrentLength(outPtr);
        return resetInt(negative, intPartLength);
      } 
      int c = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (c > 57 || c < 48) {
        if (c == 46 || c == 101 || c == 69)
          return _parseFloat(outBuf, outPtr, c, negative, intPartLength); 
        break;
      } 
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = (char)c;
      intPartLength++;
    } 
    this._inputPtr--;
    this._textBuffer.setCurrentLength(outPtr);
    if (this._parsingContext.inRoot())
      _verifyRootSpace(this._inputBuffer[this._inputPtr] & 0xFF); 
    return resetInt(negative, intPartLength);
  }
  
  private final int _verifyNoLeadingZeroes() throws IOException {
    if (this._inputPtr >= this._inputEnd && !_loadMore())
      return 48; 
    int ch = this._inputBuffer[this._inputPtr] & 0xFF;
    if (ch < 48 || ch > 57)
      return 48; 
    if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0)
      reportInvalidNumber("Leading zeroes not allowed"); 
    this._inputPtr++;
    if (ch == 48)
      while (this._inputPtr < this._inputEnd || _loadMore()) {
        ch = this._inputBuffer[this._inputPtr] & 0xFF;
        if (ch < 48 || ch > 57)
          return 48; 
        this._inputPtr++;
        if (ch != 48)
          break; 
      }  
    return ch;
  }
  
  private final JsonToken _parseFloat(char[] outBuf, int outPtr, int c, boolean negative, int integerPartLength) throws IOException {
    int fractLen = 0;
    boolean eof = false;
    if (c == 46) {
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = (char)c;
      while (true) {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
          eof = true;
          break;
        } 
        c = this._inputBuffer[this._inputPtr++] & 0xFF;
        if (c < 48 || c > 57)
          break; 
        fractLen++;
        if (outPtr >= outBuf.length) {
          outBuf = this._textBuffer.finishCurrentSegment();
          outPtr = 0;
        } 
        outBuf[outPtr++] = (char)c;
      } 
      if (fractLen == 0 && 
        !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
        _reportUnexpectedNumberChar(c, "Decimal point not followed by a digit"); 
    } 
    int expLen = 0;
    if (c == 101 || c == 69) {
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = (char)c;
      if (this._inputPtr >= this._inputEnd)
        _loadMoreGuaranteed(); 
      c = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (c == 45 || c == 43) {
        if (outPtr >= outBuf.length) {
          outBuf = this._textBuffer.finishCurrentSegment();
          outPtr = 0;
        } 
        outBuf[outPtr++] = (char)c;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        c = this._inputBuffer[this._inputPtr++] & 0xFF;
      } 
      while (c >= 48 && c <= 57) {
        expLen++;
        if (outPtr >= outBuf.length) {
          outBuf = this._textBuffer.finishCurrentSegment();
          outPtr = 0;
        } 
        outBuf[outPtr++] = (char)c;
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
          eof = true;
          break;
        } 
        c = this._inputBuffer[this._inputPtr++] & 0xFF;
      } 
      if (expLen == 0)
        _reportUnexpectedNumberChar(c, "Exponent indicator not followed by a digit"); 
    } 
    if (!eof) {
      this._inputPtr--;
      if (this._parsingContext.inRoot())
        _verifyRootSpace(c); 
    } 
    this._textBuffer.setCurrentLength(outPtr);
    return resetFloat(negative, integerPartLength, fractLen, expLen);
  }
  
  private final void _verifyRootSpace(int ch) throws IOException {
    this._inputPtr++;
    switch (ch) {
      case 9:
      case 32:
        return;
      case 13:
        this._inputPtr--;
        return;
      case 10:
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
        return;
    } 
    _reportMissingRootWS(ch);
  }
  
  protected final String _parseName(int i) throws IOException {
    if (i != 34)
      return _handleOddName(i); 
    if (this._inputPtr + 13 > this._inputEnd)
      return slowParseName(); 
    byte[] input = this._inputBuffer;
    int[] codes = _icLatin1;
    int q = input[this._inputPtr++] & 0xFF;
    if (codes[q] == 0) {
      i = input[this._inputPtr++] & 0xFF;
      if (codes[i] == 0) {
        q = q << 8 | i;
        i = input[this._inputPtr++] & 0xFF;
        if (codes[i] == 0) {
          q = q << 8 | i;
          i = input[this._inputPtr++] & 0xFF;
          if (codes[i] == 0) {
            q = q << 8 | i;
            i = input[this._inputPtr++] & 0xFF;
            if (codes[i] == 0) {
              this._quad1 = q;
              return parseMediumName(i);
            } 
            if (i == 34)
              return findName(q, 4); 
            return parseName(q, i, 4);
          } 
          if (i == 34)
            return findName(q, 3); 
          return parseName(q, i, 3);
        } 
        if (i == 34)
          return findName(q, 2); 
        return parseName(q, i, 2);
      } 
      if (i == 34)
        return findName(q, 1); 
      return parseName(q, i, 1);
    } 
    if (q == 34)
      return ""; 
    return parseName(0, q, 0);
  }
  
  protected final String parseMediumName(int q2) throws IOException {
    byte[] input = this._inputBuffer;
    int[] codes = _icLatin1;
    int i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, 1); 
      return parseName(this._quad1, q2, i, 1);
    } 
    q2 = q2 << 8 | i;
    i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, 2); 
      return parseName(this._quad1, q2, i, 2);
    } 
    q2 = q2 << 8 | i;
    i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, 3); 
      return parseName(this._quad1, q2, i, 3);
    } 
    q2 = q2 << 8 | i;
    i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, 4); 
      return parseName(this._quad1, q2, i, 4);
    } 
    return parseMediumName2(i, q2);
  }
  
  protected final String parseMediumName2(int q3, int q2) throws IOException {
    byte[] input = this._inputBuffer;
    int[] codes = _icLatin1;
    int i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, q3, 1); 
      return parseName(this._quad1, q2, q3, i, 1);
    } 
    q3 = q3 << 8 | i;
    i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, q3, 2); 
      return parseName(this._quad1, q2, q3, i, 2);
    } 
    q3 = q3 << 8 | i;
    i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, q3, 3); 
      return parseName(this._quad1, q2, q3, i, 3);
    } 
    q3 = q3 << 8 | i;
    i = input[this._inputPtr++] & 0xFF;
    if (codes[i] != 0) {
      if (i == 34)
        return findName(this._quad1, q2, q3, 4); 
      return parseName(this._quad1, q2, q3, i, 4);
    } 
    return parseLongName(i, q2, q3);
  }
  
  protected final String parseLongName(int q, int q2, int q3) throws IOException {
    this._quadBuffer[0] = this._quad1;
    this._quadBuffer[1] = q2;
    this._quadBuffer[2] = q3;
    byte[] input = this._inputBuffer;
    int[] codes = _icLatin1;
    int qlen = 3;
    while (this._inputPtr + 4 <= this._inputEnd) {
      int i = input[this._inputPtr++] & 0xFF;
      if (codes[i] != 0) {
        if (i == 34)
          return findName(this._quadBuffer, qlen, q, 1); 
        return parseEscapedName(this._quadBuffer, qlen, q, i, 1);
      } 
      q = q << 8 | i;
      i = input[this._inputPtr++] & 0xFF;
      if (codes[i] != 0) {
        if (i == 34)
          return findName(this._quadBuffer, qlen, q, 2); 
        return parseEscapedName(this._quadBuffer, qlen, q, i, 2);
      } 
      q = q << 8 | i;
      i = input[this._inputPtr++] & 0xFF;
      if (codes[i] != 0) {
        if (i == 34)
          return findName(this._quadBuffer, qlen, q, 3); 
        return parseEscapedName(this._quadBuffer, qlen, q, i, 3);
      } 
      q = q << 8 | i;
      i = input[this._inputPtr++] & 0xFF;
      if (codes[i] != 0) {
        if (i == 34)
          return findName(this._quadBuffer, qlen, q, 4); 
        return parseEscapedName(this._quadBuffer, qlen, q, i, 4);
      } 
      if (qlen >= this._quadBuffer.length)
        this._quadBuffer = _growNameDecodeBuffer(this._quadBuffer, qlen); 
      this._quadBuffer[qlen++] = q;
      q = i;
    } 
    return parseEscapedName(this._quadBuffer, qlen, 0, q, 0);
  }
  
  protected String slowParseName() throws IOException {
    if (this._inputPtr >= this._inputEnd && 
      !_loadMore())
      _reportInvalidEOF(": was expecting closing '\"' for name", JsonToken.FIELD_NAME); 
    int i = this._inputBuffer[this._inputPtr++] & 0xFF;
    if (i == 34)
      return ""; 
    return parseEscapedName(this._quadBuffer, 0, 0, i, 0);
  }
  
  private final String parseName(int q1, int ch, int lastQuadBytes) throws IOException {
    return parseEscapedName(this._quadBuffer, 0, q1, ch, lastQuadBytes);
  }
  
  private final String parseName(int q1, int q2, int ch, int lastQuadBytes) throws IOException {
    this._quadBuffer[0] = q1;
    return parseEscapedName(this._quadBuffer, 1, q2, ch, lastQuadBytes);
  }
  
  private final String parseName(int q1, int q2, int q3, int ch, int lastQuadBytes) throws IOException {
    this._quadBuffer[0] = q1;
    this._quadBuffer[1] = q2;
    return parseEscapedName(this._quadBuffer, 2, q3, ch, lastQuadBytes);
  }
  
  protected final String parseEscapedName(int[] quads, int qlen, int currQuad, int ch, int currQuadBytes) throws IOException {
    int[] codes = _icLatin1;
    while (true) {
      if (codes[ch] != 0) {
        if (ch == 34)
          break; 
        if (ch != 92) {
          _throwUnquotedSpace(ch, "name");
        } else {
          ch = _decodeEscaped();
        } 
        if (ch > 127) {
          if (currQuadBytes >= 4) {
            if (qlen >= quads.length)
              this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
            quads[qlen++] = currQuad;
            currQuad = 0;
            currQuadBytes = 0;
          } 
          if (ch < 2048) {
            currQuad = currQuad << 8 | 0xC0 | ch >> 6;
            currQuadBytes++;
          } else {
            currQuad = currQuad << 8 | 0xE0 | ch >> 12;
            currQuadBytes++;
            if (currQuadBytes >= 4) {
              if (qlen >= quads.length)
                this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
              quads[qlen++] = currQuad;
              currQuad = 0;
              currQuadBytes = 0;
            } 
            currQuad = currQuad << 8 | 0x80 | ch >> 6 & 0x3F;
            currQuadBytes++;
          } 
          ch = 0x80 | ch & 0x3F;
        } 
      } 
      if (currQuadBytes < 4) {
        currQuadBytes++;
        currQuad = currQuad << 8 | ch;
      } else {
        if (qlen >= quads.length)
          this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
        quads[qlen++] = currQuad;
        currQuad = ch;
        currQuadBytes = 1;
      } 
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME); 
      ch = this._inputBuffer[this._inputPtr++] & 0xFF;
    } 
    if (currQuadBytes > 0) {
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = _padLastQuad(currQuad, currQuadBytes);
    } 
    String name = this._symbols.findName(quads, qlen);
    if (name == null)
      name = addName(quads, qlen, currQuadBytes); 
    return name;
  }
  
  protected String _handleOddName(int ch) throws IOException {
    if (ch == 39 && (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0)
      return _parseAposName(); 
    if ((this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
      char c = (char)_decodeCharForError(ch);
      _reportUnexpectedChar(c, "was expecting double-quote to start field name");
    } 
    int[] codes = CharTypes.getInputCodeUtf8JsNames();
    if (codes[ch] != 0)
      _reportUnexpectedChar(ch, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name"); 
    int[] quads = this._quadBuffer;
    int qlen = 0;
    int currQuad = 0;
    int currQuadBytes = 0;
    while (true) {
      if (currQuadBytes < 4) {
        currQuadBytes++;
        currQuad = currQuad << 8 | ch;
      } else {
        if (qlen >= quads.length)
          this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
        quads[qlen++] = currQuad;
        currQuad = ch;
        currQuadBytes = 1;
      } 
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME); 
      ch = this._inputBuffer[this._inputPtr] & 0xFF;
      if (codes[ch] != 0)
        break; 
      this._inputPtr++;
    } 
    if (currQuadBytes > 0) {
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = currQuad;
    } 
    String name = this._symbols.findName(quads, qlen);
    if (name == null)
      name = addName(quads, qlen, currQuadBytes); 
    return name;
  }
  
  protected String _parseAposName() throws IOException {
    if (this._inputPtr >= this._inputEnd && 
      !_loadMore())
      _reportInvalidEOF(": was expecting closing ''' for field name", JsonToken.FIELD_NAME); 
    int ch = this._inputBuffer[this._inputPtr++] & 0xFF;
    if (ch == 39)
      return ""; 
    int[] quads = this._quadBuffer;
    int qlen = 0;
    int currQuad = 0;
    int currQuadBytes = 0;
    int[] codes = _icLatin1;
    while (ch != 39) {
      if (codes[ch] != 0 && ch != 34) {
        if (ch != 92) {
          _throwUnquotedSpace(ch, "name");
        } else {
          ch = _decodeEscaped();
        } 
        if (ch > 127) {
          if (currQuadBytes >= 4) {
            if (qlen >= quads.length)
              this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
            quads[qlen++] = currQuad;
            currQuad = 0;
            currQuadBytes = 0;
          } 
          if (ch < 2048) {
            currQuad = currQuad << 8 | 0xC0 | ch >> 6;
            currQuadBytes++;
          } else {
            currQuad = currQuad << 8 | 0xE0 | ch >> 12;
            currQuadBytes++;
            if (currQuadBytes >= 4) {
              if (qlen >= quads.length)
                this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
              quads[qlen++] = currQuad;
              currQuad = 0;
              currQuadBytes = 0;
            } 
            currQuad = currQuad << 8 | 0x80 | ch >> 6 & 0x3F;
            currQuadBytes++;
          } 
          ch = 0x80 | ch & 0x3F;
        } 
      } 
      if (currQuadBytes < 4) {
        currQuadBytes++;
        currQuad = currQuad << 8 | ch;
      } else {
        if (qlen >= quads.length)
          this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
        quads[qlen++] = currQuad;
        currQuad = ch;
        currQuadBytes = 1;
      } 
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME); 
      ch = this._inputBuffer[this._inputPtr++] & 0xFF;
    } 
    if (currQuadBytes > 0) {
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = _padLastQuad(currQuad, currQuadBytes);
    } 
    String name = this._symbols.findName(quads, qlen);
    if (name == null)
      name = addName(quads, qlen, currQuadBytes); 
    return name;
  }
  
  private final String findName(int q1, int lastQuadBytes) throws JsonParseException, StreamConstraintsException {
    q1 = _padLastQuad(q1, lastQuadBytes);
    String name = this._symbols.findName(q1);
    if (name != null)
      return name; 
    this._quadBuffer[0] = q1;
    return addName(this._quadBuffer, 1, lastQuadBytes);
  }
  
  private final String findName(int q1, int q2, int lastQuadBytes) throws JsonParseException, StreamConstraintsException {
    q2 = _padLastQuad(q2, lastQuadBytes);
    String name = this._symbols.findName(q1, q2);
    if (name != null)
      return name; 
    this._quadBuffer[0] = q1;
    this._quadBuffer[1] = q2;
    return addName(this._quadBuffer, 2, lastQuadBytes);
  }
  
  private final String findName(int q1, int q2, int q3, int lastQuadBytes) throws JsonParseException, StreamConstraintsException {
    q3 = _padLastQuad(q3, lastQuadBytes);
    String name = this._symbols.findName(q1, q2, q3);
    if (name != null)
      return name; 
    int[] quads = this._quadBuffer;
    quads[0] = q1;
    quads[1] = q2;
    quads[2] = _padLastQuad(q3, lastQuadBytes);
    return addName(quads, 3, lastQuadBytes);
  }
  
  private final String findName(int[] quads, int qlen, int lastQuad, int lastQuadBytes) throws JsonParseException, StreamConstraintsException {
    if (qlen >= quads.length)
      this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
    quads[qlen++] = _padLastQuad(lastQuad, lastQuadBytes);
    String name = this._symbols.findName(quads, qlen);
    if (name == null)
      return addName(quads, qlen, lastQuadBytes); 
    return name;
  }
  
  private final String addName(int[] quads, int qlen, int lastQuadBytes) throws JsonParseException, StreamConstraintsException {
    int lastQuad, byteLen = (qlen << 2) - 4 + lastQuadBytes;
    this._streamReadConstraints.validateNameLength(byteLen);
    if (lastQuadBytes < 4) {
      lastQuad = quads[qlen - 1];
      quads[qlen - 1] = lastQuad << 4 - lastQuadBytes << 3;
    } else {
      lastQuad = 0;
    } 
    char[] cbuf = this._textBuffer.emptyAndGetCurrentSegment();
    int cix = 0;
    for (int ix = 0; ix < byteLen; ) {
      int ch = quads[ix >> 2];
      int byteIx = ix & 0x3;
      ch = ch >> 3 - byteIx << 3 & 0xFF;
      ix++;
      if (ch > 127) {
        int needed;
        if ((ch & 0xE0) == 192) {
          ch &= 0x1F;
          needed = 1;
        } else if ((ch & 0xF0) == 224) {
          ch &= 0xF;
          needed = 2;
        } else if ((ch & 0xF8) == 240) {
          ch &= 0x7;
          needed = 3;
        } else {
          _reportInvalidInitial(ch);
          needed = ch = 1;
        } 
        if (ix + needed > byteLen)
          _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME); 
        int ch2 = quads[ix >> 2];
        byteIx = ix & 0x3;
        ch2 >>= 3 - byteIx << 3;
        ix++;
        if ((ch2 & 0xC0) != 128)
          _reportInvalidOther(ch2); 
        ch = ch << 6 | ch2 & 0x3F;
        if (needed > 1) {
          ch2 = quads[ix >> 2];
          byteIx = ix & 0x3;
          ch2 >>= 3 - byteIx << 3;
          ix++;
          if ((ch2 & 0xC0) != 128)
            _reportInvalidOther(ch2); 
          ch = ch << 6 | ch2 & 0x3F;
          if (needed > 2) {
            ch2 = quads[ix >> 2];
            byteIx = ix & 0x3;
            ch2 >>= 3 - byteIx << 3;
            ix++;
            if ((ch2 & 0xC0) != 128)
              _reportInvalidOther(ch2 & 0xFF); 
            ch = ch << 6 | ch2 & 0x3F;
          } 
        } 
        if (needed > 2) {
          ch -= 65536;
          if (cix >= cbuf.length)
            cbuf = this._textBuffer.expandCurrentSegment(); 
          cbuf[cix++] = (char)(55296 + (ch >> 10));
          ch = 0xDC00 | ch & 0x3FF;
        } 
      } 
      if (cix >= cbuf.length)
        cbuf = this._textBuffer.expandCurrentSegment(); 
      cbuf[cix++] = (char)ch;
    } 
    String baseName = new String(cbuf, 0, cix);
    if (lastQuadBytes < 4)
      quads[qlen - 1] = lastQuad; 
    return this._symbols.addName(baseName, quads, qlen);
  }
  
  private static final int _padLastQuad(int q, int bytes) {
    return (bytes == 4) ? q : (q | -1 << bytes << 3);
  }
  
  protected void _loadMoreGuaranteed() throws IOException {
    if (!_loadMore())
      _reportInvalidEOF(); 
  }
  
  protected void _finishString() throws IOException {
    int ptr = this._inputPtr;
    if (ptr >= this._inputEnd) {
      _loadMoreGuaranteed();
      ptr = this._inputPtr;
    } 
    int outPtr = 0;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int[] codes = _icUTF8;
    int max = Math.min(this._inputEnd, ptr + outBuf.length);
    byte[] inputBuffer = this._inputBuffer;
    while (ptr < max) {
      int c = inputBuffer[ptr] & 0xFF;
      if (codes[c] != 0) {
        if (c == 34) {
          this._inputPtr = ptr + 1;
          this._textBuffer.setCurrentLength(outPtr);
          return;
        } 
        break;
      } 
      ptr++;
      outBuf[outPtr++] = (char)c;
    } 
    this._inputPtr = ptr;
    _finishString2(outBuf, outPtr);
  }
  
  protected String _finishAndReturnString() throws IOException {
    int ptr = this._inputPtr;
    if (ptr >= this._inputEnd) {
      _loadMoreGuaranteed();
      ptr = this._inputPtr;
    } 
    int outPtr = 0;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int[] codes = _icUTF8;
    int max = Math.min(this._inputEnd, ptr + outBuf.length);
    byte[] inputBuffer = this._inputBuffer;
    while (ptr < max) {
      int c = inputBuffer[ptr] & 0xFF;
      if (codes[c] != 0) {
        if (c == 34) {
          this._inputPtr = ptr + 1;
          return this._textBuffer.setCurrentAndReturn(outPtr);
        } 
        break;
      } 
      ptr++;
      outBuf[outPtr++] = (char)c;
    } 
    this._inputPtr = ptr;
    _finishString2(outBuf, outPtr);
    return this._textBuffer.contentsAsString();
  }
  
  private final void _finishString2(char[] outBuf, int outPtr) throws IOException {
    int[] codes = _icUTF8;
    byte[] inputBuffer = this._inputBuffer;
    while (true) {
      int ptr = this._inputPtr;
      if (ptr >= this._inputEnd) {
        _loadMoreGuaranteed();
        ptr = this._inputPtr;
      } 
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      int max = Math.min(this._inputEnd, ptr + outBuf.length - outPtr);
      while (ptr < max) {
        int c = inputBuffer[ptr++] & 0xFF;
        if (codes[c] != 0) {
          this._inputPtr = ptr;
        } else {
          outBuf[outPtr++] = (char)c;
          continue;
        } 
        if (c == 34)
          break; 
        switch (codes[c]) {
          case 1:
            c = _decodeEscaped();
            break;
          case 2:
            c = _decodeUtf8_2(c);
            break;
          case 3:
            if (this._inputEnd - this._inputPtr >= 2) {
              c = _decodeUtf8_3fast(c);
              break;
            } 
            c = _decodeUtf8_3(c);
            break;
          case 4:
            c = _decodeUtf8_4(c);
            outBuf[outPtr++] = (char)(0xD800 | c >> 10);
            if (outPtr >= outBuf.length) {
              outBuf = this._textBuffer.finishCurrentSegment();
              outPtr = 0;
            } 
            c = 0xDC00 | c & 0x3FF;
            break;
          default:
            if (c < 32) {
              _throwUnquotedSpace(c, "string value");
              break;
            } 
            _reportInvalidChar(c);
            break;
        } 
        if (outPtr >= outBuf.length) {
          outBuf = this._textBuffer.finishCurrentSegment();
          outPtr = 0;
        } 
        outBuf[outPtr++] = (char)c;
      } 
      this._inputPtr = ptr;
    } 
    this._textBuffer.setCurrentLength(outPtr);
  }
  
  protected void _skipString() throws IOException {
    this._tokenIncomplete = false;
    int[] codes = _icUTF8;
    byte[] inputBuffer = this._inputBuffer;
    while (true) {
      int ptr = this._inputPtr;
      int max = this._inputEnd;
      if (ptr >= max) {
        _loadMoreGuaranteed();
        ptr = this._inputPtr;
        max = this._inputEnd;
      } 
      while (ptr < max) {
        int c = inputBuffer[ptr++] & 0xFF;
        if (codes[c] != 0) {
          this._inputPtr = ptr;
          if (c != 34) {
            switch (codes[c]) {
              case 1:
                _decodeEscaped();
                continue;
              case 2:
                _skipUtf8_2();
                continue;
              case 3:
                _skipUtf8_3();
                continue;
              case 4:
                _skipUtf8_4(c);
                continue;
            } 
            if (c < 32) {
              _throwUnquotedSpace(c, "string value");
              continue;
            } 
            _reportInvalidChar(c);
            continue;
          } 
          return;
        } 
      } 
      this._inputPtr = ptr;
    } 
  }
  
  protected JsonToken _handleUnexpectedValue(int c) throws IOException {
    switch (c) {
      case 93:
        if (!this._parsingContext.inArray())
          break; 
      case 44:
        if (!this._parsingContext.inRoot() && (
          this._features & FEAT_MASK_ALLOW_MISSING) != 0) {
          this._inputPtr--;
          return JsonToken.VALUE_NULL;
        } 
      case 125:
        _reportUnexpectedChar(c, "expected a value");
      case 39:
        if ((this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0)
          return _handleApos(); 
        break;
      case 78:
        _matchToken("NaN", 1);
        if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0)
          return resetAsNaN("NaN", Double.NaN); 
        _reportError("Non-standard token 'NaN': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow");
        break;
      case 73:
        _matchToken("Infinity", 1);
        if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0)
          return resetAsNaN("Infinity", Double.POSITIVE_INFINITY); 
        _reportError("Non-standard token 'Infinity': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow");
        break;
      case 43:
        if (this._inputPtr >= this._inputEnd && 
          !_loadMore())
          _reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_INT); 
        return _handleInvalidNumberStart(this._inputBuffer[this._inputPtr++] & 0xFF, false, true);
    } 
    if (Character.isJavaIdentifierStart(c))
      _reportInvalidToken("" + (char)c, _validJsonTokenList()); 
    _reportUnexpectedChar(c, "expected a valid value " + _validJsonValueList());
    return null;
  }
  
  protected JsonToken _handleApos() throws IOException {
    int c = 0;
    int outPtr = 0;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int[] codes = _icUTF8;
    byte[] inputBuffer = this._inputBuffer;
    while (true) {
      if (this._inputPtr >= this._inputEnd)
        _loadMoreGuaranteed(); 
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      int max = this._inputEnd;
      int max2 = this._inputPtr + outBuf.length - outPtr;
      if (max2 < max)
        max = max2; 
      while (this._inputPtr < max) {
        c = inputBuffer[this._inputPtr++] & 0xFF;
        if (c != 39) {
          if (codes[c] != 0 && c != 34) {
            switch (codes[c]) {
              case 1:
                c = _decodeEscaped();
                break;
              case 2:
                c = _decodeUtf8_2(c);
                break;
              case 3:
                if (this._inputEnd - this._inputPtr >= 2) {
                  c = _decodeUtf8_3fast(c);
                  break;
                } 
                c = _decodeUtf8_3(c);
                break;
              case 4:
                c = _decodeUtf8_4(c);
                outBuf[outPtr++] = (char)(0xD800 | c >> 10);
                if (outPtr >= outBuf.length) {
                  outBuf = this._textBuffer.finishCurrentSegment();
                  outPtr = 0;
                } 
                c = 0xDC00 | c & 0x3FF;
                break;
              default:
                if (c < 32)
                  _throwUnquotedSpace(c, "string value"); 
                _reportInvalidChar(c);
                break;
            } 
            if (outPtr >= outBuf.length) {
              outBuf = this._textBuffer.finishCurrentSegment();
              outPtr = 0;
            } 
            outBuf[outPtr++] = (char)c;
            continue;
          } 
          outBuf[outPtr++] = (char)c;
          continue;
        } 
        this._textBuffer.setCurrentLength(outPtr);
        return JsonToken.VALUE_STRING;
      } 
    } 
    this._textBuffer.setCurrentLength(outPtr);
    return JsonToken.VALUE_STRING;
  }
  
  protected JsonToken _handleInvalidNumberStart(int ch, boolean neg) throws IOException {
    return _handleInvalidNumberStart(ch, neg, false);
  }
  
  protected JsonToken _handleInvalidNumberStart(int ch, boolean neg, boolean hasSign) throws IOException {
    while (ch == 73) {
      String match;
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_FLOAT); 
      ch = this._inputBuffer[this._inputPtr++];
      if (ch == 78) {
        match = neg ? "-INF" : "+INF";
      } else if (ch == 110) {
        match = neg ? "-Infinity" : "+Infinity";
      } else {
        break;
      } 
      _matchToken(match, 3);
      if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0)
        return resetAsNaN(match, neg ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY); 
      _reportError("Non-standard token '%s': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow", match);
    } 
    if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) && hasSign && !neg)
      _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow"); 
    String message = neg ? "expected digit (0-9) to follow minus sign, for valid numeric value" : "expected digit (0-9) for valid numeric value";
    _reportUnexpectedNumberChar(ch, message);
    return null;
  }
  
  protected final void _matchTrue() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 3 < this._inputEnd) {
      byte[] buf = this._inputBuffer;
      if (buf[ptr++] == 114 && buf[ptr++] == 117 && buf[ptr++] == 101) {
        int ch = buf[ptr] & 0xFF;
        if (ch < 48 || ch == 93 || ch == 125) {
          this._inputPtr = ptr;
          return;
        } 
      } 
    } 
    _matchToken2("true", 1);
  }
  
  protected final void _matchFalse() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 4 < this._inputEnd) {
      byte[] buf = this._inputBuffer;
      if (buf[ptr++] == 97 && buf[ptr++] == 108 && buf[ptr++] == 115 && buf[ptr++] == 101) {
        int ch = buf[ptr] & 0xFF;
        if (ch < 48 || ch == 93 || ch == 125) {
          this._inputPtr = ptr;
          return;
        } 
      } 
    } 
    _matchToken2("false", 1);
  }
  
  protected final void _matchNull() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 3 < this._inputEnd) {
      byte[] buf = this._inputBuffer;
      if (buf[ptr++] == 117 && buf[ptr++] == 108 && buf[ptr++] == 108) {
        int ch = buf[ptr] & 0xFF;
        if (ch < 48 || ch == 93 || ch == 125) {
          this._inputPtr = ptr;
          return;
        } 
      } 
    } 
    _matchToken2("null", 1);
  }
  
  protected final void _matchToken(String matchStr, int i) throws IOException {
    int len = matchStr.length();
    if (this._inputPtr + len >= this._inputEnd) {
      _matchToken2(matchStr, i);
      return;
    } 
    do {
      if (this._inputBuffer[this._inputPtr] != matchStr.charAt(i))
        _reportInvalidToken(matchStr.substring(0, i)); 
      this._inputPtr++;
    } while (++i < len);
    int ch = this._inputBuffer[this._inputPtr] & 0xFF;
    if (ch >= 48 && ch != 93 && ch != 125)
      _checkMatchEnd(matchStr, i, ch); 
  }
  
  private final void _matchToken2(String matchStr, int i) throws IOException {
    int len = matchStr.length();
    do {
      if ((this._inputPtr >= this._inputEnd && !_loadMore()) || this._inputBuffer[this._inputPtr] != matchStr
        .charAt(i))
        _reportInvalidToken(matchStr.substring(0, i)); 
      this._inputPtr++;
    } while (++i < len);
    if (this._inputPtr >= this._inputEnd && !_loadMore())
      return; 
    int ch = this._inputBuffer[this._inputPtr] & 0xFF;
    if (ch >= 48 && ch != 93 && ch != 125)
      _checkMatchEnd(matchStr, i, ch); 
  }
  
  private final void _checkMatchEnd(String matchStr, int i, int ch) throws IOException {
    char c = (char)_decodeCharForError(ch);
    if (Character.isJavaIdentifierPart(c))
      _reportInvalidToken(matchStr.substring(0, i)); 
  }
  
  private final int _skipWS() throws IOException {
    while (this._inputPtr < this._inputEnd) {
      int i = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (i > 32) {
        if (i == 47 || i == 35) {
          this._inputPtr--;
          return _skipWS2();
        } 
        return i;
      } 
      if (i != 32) {
        if (i == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          continue;
        } 
        if (i == 13) {
          _skipCR();
          continue;
        } 
        if (i != 9)
          _throwInvalidSpace(i); 
      } 
    } 
    return _skipWS2();
  }
  
  private final int _skipWS2() throws IOException {
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (i > 32) {
        if (i == 47) {
          _skipComment();
          continue;
        } 
        if (i == 35 && 
          _skipYAMLComment())
          continue; 
        return i;
      } 
      if (i != 32) {
        if (i == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          continue;
        } 
        if (i == 13) {
          _skipCR();
          continue;
        } 
        if (i != 9)
          _throwInvalidSpace(i); 
      } 
    } 
    throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.typeDesc() + " entries");
  }
  
  private final int _skipWSOrEnd() throws IOException {
    if (this._inputPtr >= this._inputEnd && 
      !_loadMore())
      return _eofAsNextChar(); 
    int i = this._inputBuffer[this._inputPtr++] & 0xFF;
    if (i > 32) {
      if (i == 47 || i == 35) {
        this._inputPtr--;
        return _skipWSOrEnd2();
      } 
      return i;
    } 
    if (i != 32)
      if (i == 10) {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
      } else if (i == 13) {
        _skipCR();
      } else if (i != 9) {
        _throwInvalidSpace(i);
      }  
    while (this._inputPtr < this._inputEnd) {
      i = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (i > 32) {
        if (i == 47 || i == 35) {
          this._inputPtr--;
          return _skipWSOrEnd2();
        } 
        return i;
      } 
      if (i != 32) {
        if (i == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          continue;
        } 
        if (i == 13) {
          _skipCR();
          continue;
        } 
        if (i != 9)
          _throwInvalidSpace(i); 
      } 
    } 
    return _skipWSOrEnd2();
  }
  
  private final int _skipWSOrEnd2() throws IOException {
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (i > 32) {
        if (i == 47) {
          _skipComment();
          continue;
        } 
        if (i == 35 && 
          _skipYAMLComment())
          continue; 
        return i;
      } 
      if (i != 32) {
        if (i == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          continue;
        } 
        if (i == 13) {
          _skipCR();
          continue;
        } 
        if (i != 9)
          _throwInvalidSpace(i); 
      } 
    } 
    return _eofAsNextChar();
  }
  
  private final int _skipColon() throws IOException {
    if (this._inputPtr + 4 >= this._inputEnd)
      return _skipColon2(false); 
    int i = this._inputBuffer[this._inputPtr];
    if (i == 58) {
      i = this._inputBuffer[++this._inputPtr];
      if (i > 32) {
        if (i == 47 || i == 35)
          return _skipColon2(true); 
        this._inputPtr++;
        return i;
      } 
      if (i == 32 || i == 9) {
        i = this._inputBuffer[++this._inputPtr];
        if (i > 32) {
          if (i == 47 || i == 35)
            return _skipColon2(true); 
          this._inputPtr++;
          return i;
        } 
      } 
      return _skipColon2(true);
    } 
    if (i == 32 || i == 9)
      i = this._inputBuffer[++this._inputPtr]; 
    if (i == 58) {
      i = this._inputBuffer[++this._inputPtr];
      if (i > 32) {
        if (i == 47 || i == 35)
          return _skipColon2(true); 
        this._inputPtr++;
        return i;
      } 
      if (i == 32 || i == 9) {
        i = this._inputBuffer[++this._inputPtr];
        if (i > 32) {
          if (i == 47 || i == 35)
            return _skipColon2(true); 
          this._inputPtr++;
          return i;
        } 
      } 
      return _skipColon2(true);
    } 
    return _skipColon2(false);
  }
  
  private final int _skipColon2(boolean gotColon) throws IOException {
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (i > 32) {
        if (i == 47) {
          _skipComment();
          continue;
        } 
        if (i == 35 && 
          _skipYAMLComment())
          continue; 
        if (gotColon)
          return i; 
        if (i != 58)
          _reportUnexpectedChar(i, "was expecting a colon to separate field name and value"); 
        gotColon = true;
        continue;
      } 
      if (i != 32) {
        if (i == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          continue;
        } 
        if (i == 13) {
          _skipCR();
          continue;
        } 
        if (i != 9)
          _throwInvalidSpace(i); 
      } 
    } 
    _reportInvalidEOF(" within/between " + this._parsingContext.typeDesc() + " entries", null);
    return -1;
  }
  
  private final void _skipComment() throws IOException {
    if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0)
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)"); 
    if (this._inputPtr >= this._inputEnd && !_loadMore())
      _reportInvalidEOF(" in a comment", null); 
    int c = this._inputBuffer[this._inputPtr++] & 0xFF;
    if (c == 47) {
      _skipLine();
    } else if (c == 42) {
      _skipCComment();
    } else {
      _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
    } 
  }
  
  private final void _skipCComment() throws IOException {
    int[] codes = CharTypes.getInputCodeComment();
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++] & 0xFF;
      int code = codes[i];
      if (code != 0) {
        switch (code) {
          case 42:
            if (this._inputPtr >= this._inputEnd && !_loadMore())
              break; 
            if (this._inputBuffer[this._inputPtr] == 47) {
              this._inputPtr++;
              return;
            } 
            continue;
          case 10:
            this._currInputRow++;
            this._currInputRowStart = this._inputPtr;
            continue;
          case 13:
            _skipCR();
            continue;
          case 2:
            _skipUtf8_2();
            continue;
          case 3:
            _skipUtf8_3();
            continue;
          case 4:
            _skipUtf8_4(i);
            continue;
        } 
        _reportInvalidChar(i);
      } 
    } 
    _reportInvalidEOF(" in a comment", null);
  }
  
  private final boolean _skipYAMLComment() throws IOException {
    if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0)
      return false; 
    _skipLine();
    return true;
  }
  
  private final void _skipLine() throws IOException {
    int[] codes = CharTypes.getInputCodeComment();
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++] & 0xFF;
      int code = codes[i];
      if (code != 0) {
        switch (code) {
          case 10:
            this._currInputRow++;
            this._currInputRowStart = this._inputPtr;
            return;
          case 13:
            _skipCR();
            return;
          case 42:
            continue;
          case 2:
            _skipUtf8_2();
            continue;
          case 3:
            _skipUtf8_3();
            continue;
          case 4:
            _skipUtf8_4(i);
            continue;
        } 
        if (code < 0)
          _reportInvalidChar(i); 
      } 
    } 
  }
  
  protected char _decodeEscaped() throws IOException {
    if (this._inputPtr >= this._inputEnd && 
      !_loadMore())
      _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING); 
    int c = this._inputBuffer[this._inputPtr++];
    switch (c) {
      case 98:
        return '\b';
      case 116:
        return '\t';
      case 110:
        return '\n';
      case 102:
        return '\f';
      case 114:
        return '\r';
      case 34:
      case 47:
      case 92:
        return (char)c;
      case 117:
        break;
      default:
        return _handleUnrecognizedCharacterEscape((char)_decodeCharForError(c));
    } 
    int value = 0;
    for (int i = 0; i < 4; i++) {
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING); 
      int ch = this._inputBuffer[this._inputPtr++];
      int digit = CharTypes.charToHex(ch);
      if (digit < 0)
        _reportUnexpectedChar(ch & 0xFF, "expected a hex-digit for character escape sequence"); 
      value = value << 4 | digit;
    } 
    return (char)value;
  }
  
  protected int _decodeCharForError(int firstByte) throws IOException {
    int c = firstByte & 0xFF;
    if (c > 127) {
      int needed;
      if ((c & 0xE0) == 192) {
        c &= 0x1F;
        needed = 1;
      } else if ((c & 0xF0) == 224) {
        c &= 0xF;
        needed = 2;
      } else if ((c & 0xF8) == 240) {
        c &= 0x7;
        needed = 3;
      } else {
        _reportInvalidInitial(c & 0xFF);
        needed = 1;
      } 
      int d = nextByte();
      if ((d & 0xC0) != 128)
        _reportInvalidOther(d & 0xFF); 
      c = c << 6 | d & 0x3F;
      if (needed > 1) {
        d = nextByte();
        if ((d & 0xC0) != 128)
          _reportInvalidOther(d & 0xFF); 
        c = c << 6 | d & 0x3F;
        if (needed > 2) {
          d = nextByte();
          if ((d & 0xC0) != 128)
            _reportInvalidOther(d & 0xFF); 
          c = c << 6 | d & 0x3F;
        } 
      } 
    } 
    return c;
  }
  
  private final int _decodeUtf8_2(int c) throws IOException {
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    int d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    return (c & 0x1F) << 6 | d & 0x3F;
  }
  
  private final int _decodeUtf8_3(int c1) throws IOException {
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    c1 &= 0xF;
    int d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    int c = c1 << 6 | d & 0x3F;
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    c = c << 6 | d & 0x3F;
    return c;
  }
  
  private final int _decodeUtf8_3fast(int c1) throws IOException {
    c1 &= 0xF;
    int d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    int c = c1 << 6 | d & 0x3F;
    d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    c = c << 6 | d & 0x3F;
    return c;
  }
  
  private final int _decodeUtf8_4(int c) throws IOException {
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    int d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    c = (c & 0x7) << 6 | d & 0x3F;
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    c = c << 6 | d & 0x3F;
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    return (c << 6 | d & 0x3F) - 65536;
  }
  
  private final void _skipUtf8_2() throws IOException {
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    int c = this._inputBuffer[this._inputPtr++];
    if ((c & 0xC0) != 128)
      _reportInvalidOther(c & 0xFF, this._inputPtr); 
  }
  
  private final void _skipUtf8_3() throws IOException {
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    int c = this._inputBuffer[this._inputPtr++];
    if ((c & 0xC0) != 128)
      _reportInvalidOther(c & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    c = this._inputBuffer[this._inputPtr++];
    if ((c & 0xC0) != 128)
      _reportInvalidOther(c & 0xFF, this._inputPtr); 
  }
  
  private final void _skipUtf8_4(int c) throws IOException {
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    int d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    d = this._inputBuffer[this._inputPtr++];
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
  }
  
  protected final void _skipCR() throws IOException {
    if ((this._inputPtr < this._inputEnd || _loadMore()) && 
      this._inputBuffer[this._inputPtr] == 10)
      this._inputPtr++; 
    this._currInputRow++;
    this._currInputRowStart = this._inputPtr;
  }
  
  private int nextByte() throws IOException {
    if (this._inputPtr >= this._inputEnd)
      _loadMoreGuaranteed(); 
    return this._inputBuffer[this._inputPtr++] & 0xFF;
  }
  
  protected void _reportInvalidToken(String matchedPart, int ptr) throws IOException {
    this._inputPtr = ptr;
    _reportInvalidToken(matchedPart, _validJsonTokenList());
  }
  
  protected void _reportInvalidToken(String matchedPart) throws IOException {
    _reportInvalidToken(matchedPart, _validJsonTokenList());
  }
  
  protected void _reportInvalidToken(String matchedPart, String msg) throws IOException {
    StringBuilder sb = new StringBuilder(matchedPart);
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++];
      char c = (char)_decodeCharForError(i);
      if (!Character.isJavaIdentifierPart(c))
        break; 
      sb.append(c);
      if (sb.length() >= this._ioContext.errorReportConfiguration().getMaxErrorTokenLength()) {
        sb.append("...");
        break;
      } 
    } 
    _reportError("Unrecognized token '%s': was expecting %s", sb, msg);
  }
  
  protected void _reportInvalidChar(int c) throws JsonParseException {
    if (c < 32)
      _throwInvalidSpace(c); 
    _reportInvalidInitial(c);
  }
  
  protected void _reportInvalidInitial(int mask) throws JsonParseException {
    _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(mask));
  }
  
  protected void _reportInvalidOther(int mask) throws JsonParseException {
    _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(mask));
  }
  
  protected void _reportInvalidOther(int mask, int ptr) throws JsonParseException {
    this._inputPtr = ptr;
    _reportInvalidOther(mask);
  }
  
  protected final byte[] _decodeBase64(Base64Variant b64variant) throws IOException {
    ByteArrayBuilder builder = _getByteArrayBuilder();
    while (true) {
      if (this._inputPtr >= this._inputEnd)
        _loadMoreGuaranteed(); 
      int ch = this._inputBuffer[this._inputPtr++] & 0xFF;
      if (ch > 32) {
        int bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (ch == 34)
            return builder.toByteArray(); 
          bits = _decodeBase64Escape(b64variant, ch, 0);
          if (bits < 0)
            continue; 
        } 
        int decodedData = bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++] & 0xFF;
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0)
          bits = _decodeBase64Escape(b64variant, ch, 1); 
        decodedData = decodedData << 6 | bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++] & 0xFF;
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == 34) {
              decodedData >>= 4;
              builder.append(decodedData);
              if (b64variant.requiresPaddingOnRead()) {
                this._inputPtr--;
                _handleBase64MissingPadding(b64variant);
              } 
              return builder.toByteArray();
            } 
            bits = _decodeBase64Escape(b64variant, ch, 2);
          } 
          if (bits == -2) {
            if (this._inputPtr >= this._inputEnd)
              _loadMoreGuaranteed(); 
            ch = this._inputBuffer[this._inputPtr++] & 0xFF;
            if (!b64variant.usesPaddingChar(ch) && 
              _decodeBase64Escape(b64variant, ch, 3) != -2)
              throw reportInvalidBase64Char(b64variant, ch, 3, "expected padding character '" + b64variant.getPaddingChar() + "'"); 
            decodedData >>= 4;
            builder.append(decodedData);
            continue;
          } 
        } 
        decodedData = decodedData << 6 | bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++] & 0xFF;
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == 34) {
              decodedData >>= 2;
              builder.appendTwoBytes(decodedData);
              if (b64variant.requiresPaddingOnRead()) {
                this._inputPtr--;
                _handleBase64MissingPadding(b64variant);
              } 
              return builder.toByteArray();
            } 
            bits = _decodeBase64Escape(b64variant, ch, 3);
          } 
          if (bits == -2) {
            decodedData >>= 2;
            builder.appendTwoBytes(decodedData);
            continue;
          } 
        } 
        decodedData = decodedData << 6 | bits;
        builder.appendThreeBytes(decodedData);
      } 
    } 
  }
  
  public JsonLocation getTokenLocation() {
    if (this._currToken == JsonToken.FIELD_NAME) {
      long total = this._currInputProcessed + (this._nameStartOffset - 1);
      return new JsonLocation(_contentReference(), total, -1L, this._nameStartRow, this._nameStartCol);
    } 
    return new JsonLocation(_contentReference(), this._tokenInputTotal - 1L, -1L, this._tokenInputRow, this._tokenInputCol);
  }
  
  public JsonLocation getCurrentLocation() {
    int col = this._inputPtr - this._currInputRowStart + 1;
    return new JsonLocation(_contentReference(), this._currInputProcessed + this._inputPtr, -1L, this._currInputRow, col);
  }
  
  private final void _updateLocation() {
    this._tokenInputRow = this._currInputRow;
    int ptr = this._inputPtr;
    this._tokenInputTotal = this._currInputProcessed + ptr;
    this._tokenInputCol = ptr - this._currInputRowStart;
  }
  
  private final void _updateNameLocation() {
    this._nameStartRow = this._currInputRow;
    int ptr = this._inputPtr;
    this._nameStartOffset = ptr;
    this._nameStartCol = ptr - this._currInputRowStart;
  }
  
  private final JsonToken _closeScope(int i) throws JsonParseException {
    if (i == 125) {
      _closeObjectScope();
      return this._currToken = JsonToken.END_OBJECT;
    } 
    _closeArrayScope();
    return this._currToken = JsonToken.END_ARRAY;
  }
  
  private final void _closeArrayScope() throws JsonParseException {
    _updateLocation();
    if (!this._parsingContext.inArray())
      _reportMismatchedEndMarker(93, '}'); 
    this._parsingContext = this._parsingContext.clearAndGetParent();
  }
  
  private final void _closeObjectScope() throws JsonParseException {
    _updateLocation();
    if (!this._parsingContext.inObject())
      _reportMismatchedEndMarker(125, ']'); 
    this._parsingContext = this._parsingContext.clearAndGetParent();
  }
}
