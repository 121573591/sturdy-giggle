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
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class ReaderBasedJsonParser extends ParserBase {
  private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
  
  private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
  
  private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
  
  private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
  
  private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
  
  private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
  
  private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
  
  private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
  
  protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
  
  protected Reader _reader;
  
  protected char[] _inputBuffer;
  
  protected boolean _bufferRecyclable;
  
  protected ObjectCodec _objectCodec;
  
  protected final CharsToNameCanonicalizer _symbols;
  
  protected final int _hashSeed;
  
  protected boolean _tokenIncomplete;
  
  protected long _nameStartOffset;
  
  protected int _nameStartRow;
  
  protected int _nameStartCol;
  
  public ReaderBasedJsonParser(IOContext ctxt, int features, Reader r, ObjectCodec codec, CharsToNameCanonicalizer st, char[] inputBuffer, int start, int end, boolean bufferRecyclable) {
    super(ctxt, features);
    this._reader = r;
    this._objectCodec = codec;
    this._inputBuffer = inputBuffer;
    this._inputPtr = start;
    this._inputEnd = end;
    this._currInputRowStart = start;
    this._currInputProcessed = -start;
    this._symbols = st;
    this._hashSeed = st.hashSeed();
    this._bufferRecyclable = bufferRecyclable;
  }
  
  public ReaderBasedJsonParser(IOContext ctxt, int features, Reader r, ObjectCodec codec, CharsToNameCanonicalizer st) {
    super(ctxt, features);
    this._reader = r;
    this._inputBuffer = ctxt.allocTokenBuffer();
    this._inputPtr = 0;
    this._inputEnd = 0;
    this._objectCodec = codec;
    this._symbols = st;
    this._hashSeed = st.hashSeed();
    this._bufferRecyclable = true;
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
  
  public int releaseBuffered(Writer w) throws IOException {
    int count = this._inputEnd - this._inputPtr;
    if (count < 1)
      return 0; 
    int origPtr = this._inputPtr;
    this._inputPtr += count;
    w.write(this._inputBuffer, origPtr, count);
    return count;
  }
  
  public Object getInputSource() {
    return this._reader;
  }
  
  @Deprecated
  protected char getNextChar(String eofMsg) throws IOException {
    return getNextChar(eofMsg, (JsonToken)null);
  }
  
  protected char getNextChar(String eofMsg, JsonToken forToken) throws IOException {
    if (this._inputPtr >= this._inputEnd && 
      !_loadMore())
      _reportInvalidEOF(eofMsg, forToken); 
    return this._inputBuffer[this._inputPtr++];
  }
  
  protected void _closeInput() throws IOException {
    if (this._reader != null) {
      if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))
        this._reader.close(); 
      this._reader = null;
    } 
  }
  
  protected void _releaseBuffers() throws IOException {
    super._releaseBuffers();
    this._symbols.release();
    if (this._bufferRecyclable) {
      char[] buf = this._inputBuffer;
      if (buf != null) {
        this._inputBuffer = null;
        this._ioContext.releaseTokenBuffer(buf);
      } 
    } 
  }
  
  protected void _loadMoreGuaranteed() throws IOException {
    if (!_loadMore())
      _reportInvalidEOF(); 
  }
  
  protected boolean _loadMore() throws IOException {
    if (this._reader != null) {
      int bufSize = this._inputEnd;
      this._currInputProcessed += bufSize;
      this._currInputRowStart -= bufSize;
      streamReadConstraints().validateDocumentLength(this._currInputProcessed);
      int count = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
      if (count > 0) {
        this._nameStartOffset -= bufSize;
        this._inputPtr = 0;
        this._inputEnd = count;
        return true;
      } 
      this._inputPtr = this._inputEnd = 0;
      _closeInput();
      if (count == 0)
        throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd); 
    } 
    return false;
  }
  
  public final String getText() throws IOException {
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete) {
        this._tokenIncomplete = false;
        _finishString();
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
  
  public final String getValueAsString() throws IOException {
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete) {
        this._tokenIncomplete = false;
        _finishString();
      } 
      return this._textBuffer.contentsAsString();
    } 
    if (this._currToken == JsonToken.FIELD_NAME)
      return getCurrentName(); 
    return super.getValueAsString(null);
  }
  
  public final String getValueAsString(String defValue) throws IOException {
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete) {
        this._tokenIncomplete = false;
        _finishString();
      } 
      return this._textBuffer.contentsAsString();
    } 
    if (this._currToken == JsonToken.FIELD_NAME)
      return getCurrentName(); 
    return super.getValueAsString(defValue);
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
  
  public final char[] getTextCharacters() throws IOException {
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
  
  public final int getTextLength() throws IOException {
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
  
  public final int getTextOffset() throws IOException {
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
    if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT && this._binaryValue != null)
      return this._binaryValue; 
    if (this._currToken != JsonToken.VALUE_STRING)
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
      char ch = this._inputBuffer[this._inputPtr++];
      if (ch > ' ') {
        int bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (ch == '"')
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
        ch = this._inputBuffer[this._inputPtr++];
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0)
          bits = _decodeBase64Escape(b64variant, ch, 1); 
        decodedData = decodedData << 6 | bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++];
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == '"') {
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
            ch = this._inputBuffer[this._inputPtr++];
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
        ch = this._inputBuffer[this._inputPtr++];
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == '"') {
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
  
  public final JsonToken nextToken() throws IOException {
    JsonToken t;
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
    if (i == 93 || i == 125) {
      _closeScope(i);
      return this._currToken;
    } 
    if (this._parsingContext.expectComma()) {
      i = _skipComma(i);
      if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (
        i == 93 || i == 125)) {
        _closeScope(i);
        return this._currToken;
      } 
    } 
    boolean inObject = this._parsingContext.inObject();
    if (inObject) {
      _updateNameLocation();
      String name = (i == 34) ? _parseName() : _handleOddName(i);
      this._parsingContext.setCurrentName(name);
      this._currToken = JsonToken.FIELD_NAME;
      i = _skipColon();
    } 
    _updateLocation();
    switch (i) {
      case 34:
        this._tokenIncomplete = true;
        t = JsonToken.VALUE_STRING;
        break;
      case 91:
        if (!inObject)
          createChildArrayContext(this._tokenInputRow, this._tokenInputCol); 
        t = JsonToken.START_ARRAY;
        break;
      case 123:
        if (!inObject)
          createChildObjectContext(this._tokenInputRow, this._tokenInputCol); 
        t = JsonToken.START_OBJECT;
        break;
      case 125:
        _reportUnexpectedChar(i, "expected a value");
      case 116:
        _matchTrue();
        t = JsonToken.VALUE_TRUE;
        break;
      case 102:
        _matchFalse();
        t = JsonToken.VALUE_FALSE;
        break;
      case 110:
        _matchNull();
        t = JsonToken.VALUE_NULL;
        break;
      case 45:
        t = _parseSignedNumber(true);
        break;
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          t = _parseSignedNumber(false);
          break;
        } 
        t = _handleOddValue(i);
        break;
      case 46:
        t = _parseFloatThatStartsWithPeriod(false);
        break;
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
        break;
      default:
        t = _handleOddValue(i);
        break;
    } 
    if (inObject) {
      this._nextToken = t;
      return this._currToken;
    } 
    this._currToken = t;
    return t;
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
  
  public boolean nextFieldName(SerializableString sstr) throws IOException {
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
    if (i == 93 || i == 125) {
      _closeScope(i);
      return false;
    } 
    if (this._parsingContext.expectComma()) {
      i = _skipComma(i);
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
      char[] nameChars = sstr.asQuotedChars();
      int len = nameChars.length;
      if (this._inputPtr + len + 4 < this._inputEnd) {
        int end = this._inputPtr + len;
        if (this._inputBuffer[end] == '"') {
          int offset = 0;
          int ptr = this._inputPtr;
          while (true) {
            if (ptr == end) {
              this._parsingContext.setCurrentName(sstr.getValue());
              _isNextTokenNameYes(_skipColonFast(ptr + 1));
              return true;
            } 
            if (nameChars[offset] != this._inputBuffer[ptr])
              break; 
            offset++;
            ptr++;
          } 
        } 
      } 
    } 
    return _isNextTokenNameMaybe(i, sstr.getValue());
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
    if (i == 93 || i == 125) {
      _closeScope(i);
      return null;
    } 
    if (this._parsingContext.expectComma()) {
      i = _skipComma(i);
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
    String name = (i == 34) ? _parseName() : _handleOddName(i);
    this._parsingContext.setCurrentName(name);
    this._currToken = JsonToken.FIELD_NAME;
    i = _skipColon();
    _updateLocation();
    if (i == 34) {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return name;
    } 
    switch (i) {
      case 45:
        t = _parseSignedNumber(true);
        this._nextToken = t;
        return name;
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          t = _parseSignedNumber(false);
        } else {
          t = _handleOddValue(i);
        } 
        this._nextToken = t;
        return name;
      case 46:
        t = _parseFloatThatStartsWithPeriod(false);
        this._nextToken = t;
        return name;
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
        return name;
      case 102:
        _matchFalse();
        t = JsonToken.VALUE_FALSE;
        this._nextToken = t;
        return name;
      case 110:
        _matchNull();
        t = JsonToken.VALUE_NULL;
        this._nextToken = t;
        return name;
      case 116:
        _matchTrue();
        t = JsonToken.VALUE_TRUE;
        this._nextToken = t;
        return name;
      case 91:
        t = JsonToken.START_ARRAY;
        this._nextToken = t;
        return name;
      case 123:
        t = JsonToken.START_OBJECT;
        this._nextToken = t;
        return name;
    } 
    JsonToken t = _handleOddValue(i);
    this._nextToken = t;
    return name;
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
        _matchToken("true", 1);
        this._nextToken = JsonToken.VALUE_TRUE;
        return;
      case 102:
        _matchToken("false", 1);
        this._nextToken = JsonToken.VALUE_FALSE;
        return;
      case 110:
        _matchToken("null", 1);
        this._nextToken = JsonToken.VALUE_NULL;
        return;
      case 45:
        this._nextToken = _parseSignedNumber(true);
        return;
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          this._nextToken = _parseSignedNumber(false);
        } else {
          this._nextToken = _handleOddValue(i);
        } 
        return;
      case 46:
        this._nextToken = _parseFloatThatStartsWithPeriod(false);
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
    this._nextToken = _handleOddValue(i);
  }
  
  protected boolean _isNextTokenNameMaybe(int i, String nameToMatch) throws IOException {
    String name = (i == 34) ? _parseName() : _handleOddName(i);
    this._parsingContext.setCurrentName(name);
    this._currToken = JsonToken.FIELD_NAME;
    i = _skipColon();
    _updateLocation();
    if (i == 34) {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return nameToMatch.equals(name);
    } 
    switch (i) {
      case 45:
        t = _parseSignedNumber(true);
        this._nextToken = t;
        return nameToMatch.equals(name);
      case 43:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
          t = _parseSignedNumber(false);
        } else {
          t = _handleOddValue(i);
        } 
        this._nextToken = t;
        return nameToMatch.equals(name);
      case 46:
        t = _parseFloatThatStartsWithPeriod(false);
        this._nextToken = t;
        return nameToMatch.equals(name);
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
        return nameToMatch.equals(name);
      case 102:
        _matchFalse();
        t = JsonToken.VALUE_FALSE;
        this._nextToken = t;
        return nameToMatch.equals(name);
      case 110:
        _matchNull();
        t = JsonToken.VALUE_NULL;
        this._nextToken = t;
        return nameToMatch.equals(name);
      case 116:
        _matchTrue();
        t = JsonToken.VALUE_TRUE;
        this._nextToken = t;
        return nameToMatch.equals(name);
      case 91:
        t = JsonToken.START_ARRAY;
        this._nextToken = t;
        return nameToMatch.equals(name);
      case 123:
        t = JsonToken.START_OBJECT;
        this._nextToken = t;
        return nameToMatch.equals(name);
    } 
    JsonToken t = _handleOddValue(i);
    this._nextToken = t;
    return nameToMatch.equals(name);
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
        _matchToken("true", 1);
        return this._currToken = JsonToken.VALUE_TRUE;
      case 102:
        _matchToken("false", 1);
        return this._currToken = JsonToken.VALUE_FALSE;
      case 110:
        _matchToken("null", 1);
        return this._currToken = JsonToken.VALUE_NULL;
      case 45:
        return this._currToken = _parseSignedNumber(true);
      case 46:
        return this._currToken = _parseFloatThatStartsWithPeriod(false);
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
      case 44:
        if (!this._parsingContext.inRoot() && (
          this._features & FEAT_MASK_ALLOW_MISSING) != 0) {
          this._inputPtr--;
          return this._currToken = JsonToken.VALUE_NULL;
        } 
        break;
    } 
    return this._currToken = _handleOddValue(i);
  }
  
  public final String nextTextValue() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken t = this._nextToken;
      this._nextToken = null;
      this._currToken = t;
      if (t == JsonToken.VALUE_STRING) {
        if (this._tokenIncomplete) {
          this._tokenIncomplete = false;
          _finishString();
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
  
  public final int nextIntValue(int defaultValue) throws IOException {
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
  
  public final long nextLongValue(long defaultValue) throws IOException {
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
  
  public final Boolean nextBooleanValue() throws IOException {
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
    if (t != null) {
      int id = t.id();
      if (id == 9)
        return Boolean.TRUE; 
      if (id == 10)
        return Boolean.FALSE; 
    } 
    return null;
  }
  
  @Deprecated
  protected final JsonToken _parseFloatThatStartsWithPeriod() throws IOException {
    return _parseFloatThatStartsWithPeriod(false);
  }
  
  protected final JsonToken _parseFloatThatStartsWithPeriod(boolean neg) throws IOException {
    if (!isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
      return _handleOddValue(46); 
    int startPtr = this._inputPtr - 1;
    if (neg)
      startPtr--; 
    return _parseFloat(46, startPtr, this._inputPtr, neg, 0);
  }
  
  protected final JsonToken _parseUnsignedNumber(int ch) throws IOException {
    int ptr = this._inputPtr;
    int startPtr = ptr - 1;
    int inputLen = this._inputEnd;
    if (ch == 48)
      return _parseNumber2(false, startPtr); 
    int intLen = 1;
    while (true) {
      if (ptr >= inputLen) {
        this._inputPtr = startPtr;
        return _parseNumber2(false, startPtr);
      } 
      ch = this._inputBuffer[ptr++];
      if (ch < 48 || ch > 57)
        break; 
      intLen++;
    } 
    if (ch == 46 || ch == 101 || ch == 69) {
      this._inputPtr = ptr;
      return _parseFloat(ch, startPtr, ptr, false, intLen);
    } 
    this._inputPtr = --ptr;
    if (this._parsingContext.inRoot())
      _verifyRootSpace(ch); 
    int len = ptr - startPtr;
    this._textBuffer.resetWithShared(this._inputBuffer, startPtr, len);
    return resetInt(false, intLen);
  }
  
  private final JsonToken _parseFloat(int ch, int startPtr, int ptr, boolean neg, int intLen) throws IOException {
    int inputLen = this._inputEnd;
    int fractLen = 0;
    if (ch == 46) {
      while (true) {
        if (ptr >= inputLen)
          return _parseNumber2(neg, startPtr); 
        ch = this._inputBuffer[ptr++];
        if (ch < 48 || ch > 57)
          break; 
        fractLen++;
      } 
      if (fractLen == 0 && 
        !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
        _reportUnexpectedNumberChar(ch, "Decimal point not followed by a digit"); 
    } 
    int expLen = 0;
    if (ch == 101 || ch == 69) {
      if (ptr >= inputLen) {
        this._inputPtr = startPtr;
        return _parseNumber2(neg, startPtr);
      } 
      ch = this._inputBuffer[ptr++];
      if (ch == 45 || ch == 43) {
        if (ptr >= inputLen) {
          this._inputPtr = startPtr;
          return _parseNumber2(neg, startPtr);
        } 
        ch = this._inputBuffer[ptr++];
      } 
      while (ch <= 57 && ch >= 48) {
        expLen++;
        if (ptr >= inputLen) {
          this._inputPtr = startPtr;
          return _parseNumber2(neg, startPtr);
        } 
        ch = this._inputBuffer[ptr++];
      } 
      if (expLen == 0)
        _reportUnexpectedNumberChar(ch, "Exponent indicator not followed by a digit"); 
    } 
    this._inputPtr = --ptr;
    if (this._parsingContext.inRoot())
      _verifyRootSpace(ch); 
    int len = ptr - startPtr;
    this._textBuffer.resetWithShared(this._inputBuffer, startPtr, len);
    return resetFloat(neg, intLen, fractLen, expLen);
  }
  
  private final JsonToken _parseSignedNumber(boolean negative) throws IOException {
    int ptr = this._inputPtr;
    int startPtr = negative ? (ptr - 1) : ptr;
    int inputEnd = this._inputEnd;
    if (ptr >= inputEnd)
      return _parseNumber2(negative, startPtr); 
    int ch = this._inputBuffer[ptr++];
    if (ch > 57 || ch < 48) {
      this._inputPtr = ptr;
      if (ch == 46)
        return _parseFloatThatStartsWithPeriod(negative); 
      return _handleInvalidNumberStart(ch, negative, true);
    } 
    if (ch == 48)
      return _parseNumber2(negative, startPtr); 
    int intLen = 1;
    while (true) {
      if (ptr >= inputEnd)
        return _parseNumber2(negative, startPtr); 
      ch = this._inputBuffer[ptr++];
      if (ch < 48 || ch > 57)
        break; 
      intLen++;
    } 
    if (ch == 46 || ch == 101 || ch == 69) {
      this._inputPtr = ptr;
      return _parseFloat(ch, startPtr, ptr, negative, intLen);
    } 
    this._inputPtr = --ptr;
    if (this._parsingContext.inRoot())
      _verifyRootSpace(ch); 
    int len = ptr - startPtr;
    this._textBuffer.resetWithShared(this._inputBuffer, startPtr, len);
    return resetInt(negative, intLen);
  }
  
  private final JsonToken _parseNumber2(boolean neg, int startPtr) throws IOException {
    this._inputPtr = neg ? (startPtr + 1) : startPtr;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int outPtr = 0;
    if (neg)
      outBuf[outPtr++] = '-'; 
    int intLen = 0;
    char c = (this._inputPtr < this._inputEnd) ? this._inputBuffer[this._inputPtr++] : getNextChar("No digit following minus sign", JsonToken.VALUE_NUMBER_INT);
    if (c == '0')
      c = _verifyNoLeadingZeroes(); 
    boolean eof = false;
    while (c >= '0' && c <= '9') {
      intLen++;
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = c;
      if (this._inputPtr >= this._inputEnd && !_loadMore()) {
        c = Character.MIN_VALUE;
        eof = true;
        break;
      } 
      c = this._inputBuffer[this._inputPtr++];
    } 
    if (intLen == 0)
      if (!isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
        return _handleInvalidNumberStart(c, neg);  
    int fractLen = -1;
    if (c == '.') {
      fractLen = 0;
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = c;
      while (true) {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
          eof = true;
          break;
        } 
        c = this._inputBuffer[this._inputPtr++];
        if (c < '0' || c > '9')
          break; 
        fractLen++;
        if (outPtr >= outBuf.length) {
          outBuf = this._textBuffer.finishCurrentSegment();
          outPtr = 0;
        } 
        outBuf[outPtr++] = c;
      } 
      if (fractLen == 0 && 
        !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
        _reportUnexpectedNumberChar(c, "Decimal point not followed by a digit"); 
    } 
    int expLen = -1;
    if (c == 'e' || c == 'E') {
      expLen = 0;
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = c;
      c = (this._inputPtr < this._inputEnd) ? this._inputBuffer[this._inputPtr++] : getNextChar("expected a digit for number exponent", JsonToken.VALUE_NUMBER_FLOAT);
      if (c == '-' || c == '+') {
        if (outPtr >= outBuf.length) {
          outBuf = this._textBuffer.finishCurrentSegment();
          outPtr = 0;
        } 
        outBuf[outPtr++] = c;
        c = (this._inputPtr < this._inputEnd) ? this._inputBuffer[this._inputPtr++] : getNextChar("expected a digit for number exponent", JsonToken.VALUE_NUMBER_FLOAT);
      } 
      while (c <= '9' && c >= '0') {
        expLen++;
        if (outPtr >= outBuf.length) {
          outBuf = this._textBuffer.finishCurrentSegment();
          outPtr = 0;
        } 
        outBuf[outPtr++] = c;
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
          eof = true;
          break;
        } 
        c = this._inputBuffer[this._inputPtr++];
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
    if (fractLen < 0 && expLen < 0)
      return resetInt(neg, intLen); 
    return resetFloat(neg, intLen, fractLen, expLen);
  }
  
  private final char _verifyNoLeadingZeroes() throws IOException {
    if (this._inputPtr < this._inputEnd) {
      char ch = this._inputBuffer[this._inputPtr];
      if (ch < '0' || ch > '9')
        return '0'; 
    } 
    return _verifyNLZ2();
  }
  
  private char _verifyNLZ2() throws IOException {
    if (this._inputPtr >= this._inputEnd && !_loadMore())
      return '0'; 
    char ch = this._inputBuffer[this._inputPtr];
    if (ch < '0' || ch > '9')
      return '0'; 
    if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0)
      reportInvalidNumber("Leading zeroes not allowed"); 
    this._inputPtr++;
    if (ch == '0')
      while (this._inputPtr < this._inputEnd || _loadMore()) {
        ch = this._inputBuffer[this._inputPtr];
        if (ch < '0' || ch > '9')
          return '0'; 
        this._inputPtr++;
        if (ch != '0')
          break; 
      }  
    return ch;
  }
  
  protected JsonToken _handleInvalidNumberStart(int ch, boolean negative) throws IOException {
    return _handleInvalidNumberStart(ch, negative, false);
  }
  
  protected JsonToken _handleInvalidNumberStart(int ch, boolean negative, boolean hasSign) throws IOException {
    if (ch == 73) {
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_INT); 
      ch = this._inputBuffer[this._inputPtr++];
      if (ch == 78) {
        String match = negative ? "-INF" : "+INF";
        _matchToken(match, 3);
        if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0)
          return resetAsNaN(match, negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY); 
        _reportError("Non-standard token '" + match + "': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow");
      } else if (ch == 110) {
        String match = negative ? "-Infinity" : "+Infinity";
        _matchToken(match, 3);
        if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0)
          return resetAsNaN(match, negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY); 
        _reportError("Non-standard token '" + match + "': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow");
      } 
    } 
    if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) && hasSign && !negative)
      _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow"); 
    String message = negative ? "expected digit (0-9) to follow minus sign, for valid numeric value" : "expected digit (0-9) for valid numeric value";
    _reportUnexpectedNumberChar(ch, message);
    return null;
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
  
  protected final String _parseName() throws IOException {
    int ptr = this._inputPtr;
    int hash = this._hashSeed;
    int[] codes = _icLatin1;
    while (ptr < this._inputEnd) {
      int ch = this._inputBuffer[ptr];
      if (ch < codes.length && codes[ch] != 0) {
        if (ch == 34) {
          int i = this._inputPtr;
          this._inputPtr = ptr + 1;
          return this._symbols.findSymbol(this._inputBuffer, i, ptr - i, hash);
        } 
        break;
      } 
      hash = hash * 33 + ch;
      ptr++;
    } 
    int start = this._inputPtr;
    this._inputPtr = ptr;
    return _parseName2(start, hash, 34);
  }
  
  private String _parseName2(int startPtr, int hash, int endChar) throws IOException {
    this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
    char[] outBuf = this._textBuffer.getCurrentSegment();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    while (true) {
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME); 
      char c = this._inputBuffer[this._inputPtr++];
      int i = c;
      if (i <= 92)
        if (i == 92) {
          c = _decodeEscaped();
        } else if (i <= endChar) {
          if (i == endChar)
            break; 
          if (i < 32)
            _throwUnquotedSpace(i, "name"); 
        }  
      hash = hash * 33 + c;
      outBuf[outPtr++] = c;
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
    } 
    this._textBuffer.setCurrentLength(outPtr);
    TextBuffer tb = this._textBuffer;
    char[] buf = tb.getTextBuffer();
    int start = tb.getTextOffset();
    return this._symbols.findSymbol(buf, start, tb.size(), hash);
  }
  
  protected String _handleOddName(int i) throws IOException {
    boolean firstOk;
    if (i == 39 && (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0)
      return _parseAposName(); 
    if ((this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0)
      _reportUnexpectedChar(i, "was expecting double-quote to start field name"); 
    int[] codes = CharTypes.getInputCodeLatin1JsNames();
    int maxCode = codes.length;
    if (i < maxCode) {
      firstOk = (codes[i] == 0);
    } else {
      firstOk = Character.isJavaIdentifierPart((char)i);
    } 
    if (!firstOk)
      _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name"); 
    int ptr = this._inputPtr;
    int hash = this._hashSeed;
    int inputLen = this._inputEnd;
    if (ptr < inputLen)
      do {
        int ch = this._inputBuffer[ptr];
        if (ch < maxCode) {
          if (codes[ch] != 0) {
            int j = this._inputPtr - 1;
            this._inputPtr = ptr;
            return this._symbols.findSymbol(this._inputBuffer, j, ptr - j, hash);
          } 
        } else if (!Character.isJavaIdentifierPart((char)ch)) {
          int j = this._inputPtr - 1;
          this._inputPtr = ptr;
          return this._symbols.findSymbol(this._inputBuffer, j, ptr - j, hash);
        } 
        hash = hash * 33 + ch;
        ++ptr;
      } while (ptr < inputLen); 
    int start = this._inputPtr - 1;
    this._inputPtr = ptr;
    return _handleOddName2(start, hash, codes);
  }
  
  protected String _parseAposName() throws IOException {
    int ptr = this._inputPtr;
    int hash = this._hashSeed;
    int inputLen = this._inputEnd;
    if (ptr < inputLen) {
      int[] codes = _icLatin1;
      int maxCode = codes.length;
      do {
        int ch = this._inputBuffer[ptr];
        if (ch == 39) {
          int i = this._inputPtr;
          this._inputPtr = ptr + 1;
          return this._symbols.findSymbol(this._inputBuffer, i, ptr - i, hash);
        } 
        if (ch < maxCode && codes[ch] != 0)
          break; 
        hash = hash * 33 + ch;
        ++ptr;
      } while (ptr < inputLen);
    } 
    int start = this._inputPtr;
    this._inputPtr = ptr;
    return _parseName2(start, hash, 39);
  }
  
  protected JsonToken _handleOddValue(int i) throws IOException {
    switch (i) {
      case 39:
        if ((this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0)
          return _handleApos(); 
        break;
      case 93:
        if (!this._parsingContext.inArray())
          break; 
      case 44:
        if (!this._parsingContext.inRoot() && (
          this._features & FEAT_MASK_ALLOW_MISSING) != 0) {
          this._inputPtr--;
          return JsonToken.VALUE_NULL;
        } 
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
        return _handleInvalidNumberStart(this._inputBuffer[this._inputPtr++], false, true);
    } 
    if (Character.isJavaIdentifierStart(i))
      _reportInvalidToken("" + (char)i, _validJsonTokenList()); 
    _reportUnexpectedChar(i, "expected a valid value " + _validJsonValueList());
    return null;
  }
  
  protected JsonToken _handleApos() throws IOException {
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    while (true) {
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING); 
      char c = this._inputBuffer[this._inputPtr++];
      int i = c;
      if (i <= 92)
        if (i == 92) {
          c = _decodeEscaped();
        } else if (i <= 39) {
          if (i == 39)
            break; 
          if (i < 32)
            _throwUnquotedSpace(i, "string value"); 
        }  
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = c;
    } 
    this._textBuffer.setCurrentLength(outPtr);
    return JsonToken.VALUE_STRING;
  }
  
  private String _handleOddName2(int startPtr, int hash, int[] codes) throws IOException {
    this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
    char[] outBuf = this._textBuffer.getCurrentSegment();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    int maxCode = codes.length;
    while (this._inputPtr < this._inputEnd || 
      _loadMore()) {
      char c = this._inputBuffer[this._inputPtr];
      int i = c;
      if ((i < maxCode) ? (
        codes[i] != 0) : 
        
        !Character.isJavaIdentifierPart(c))
        break; 
      this._inputPtr++;
      hash = hash * 33 + i;
      outBuf[outPtr++] = c;
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
    } 
    this._textBuffer.setCurrentLength(outPtr);
    TextBuffer tb = this._textBuffer;
    char[] buf = tb.getTextBuffer();
    int start = tb.getTextOffset();
    return this._symbols.findSymbol(buf, start, tb.size(), hash);
  }
  
  protected final void _finishString() throws IOException {
    int ptr = this._inputPtr;
    int inputLen = this._inputEnd;
    if (ptr < inputLen) {
      int[] codes = _icLatin1;
      int maxCode = codes.length;
      do {
        int ch = this._inputBuffer[ptr];
        if (ch < maxCode && codes[ch] != 0) {
          if (ch == 34) {
            this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
            this._inputPtr = ptr + 1;
            return;
          } 
          break;
        } 
        ++ptr;
      } while (ptr < inputLen);
    } 
    this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
    this._inputPtr = ptr;
    _finishString2();
  }
  
  protected void _finishString2() throws IOException {
    char[] outBuf = this._textBuffer.getCurrentSegment();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    int[] codes = _icLatin1;
    int maxCode = codes.length;
    while (true) {
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING); 
      char c = this._inputBuffer[this._inputPtr++];
      int i = c;
      if (i < maxCode && codes[i] != 0) {
        if (i == 34)
          break; 
        if (i == 92) {
          c = _decodeEscaped();
        } else if (i < 32) {
          _throwUnquotedSpace(i, "string value");
        } 
      } 
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      outBuf[outPtr++] = c;
    } 
    this._textBuffer.setCurrentLength(outPtr);
  }
  
  protected final void _skipString() throws IOException {
    this._tokenIncomplete = false;
    int inPtr = this._inputPtr;
    int inLen = this._inputEnd;
    char[] inBuf = this._inputBuffer;
    while (true) {
      if (inPtr >= inLen) {
        this._inputPtr = inPtr;
        if (!_loadMore())
          _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING); 
        inPtr = this._inputPtr;
        inLen = this._inputEnd;
      } 
      char c = inBuf[inPtr++];
      int i = c;
      if (i <= 92) {
        if (i == 92) {
          this._inputPtr = inPtr;
          _decodeEscaped();
          inPtr = this._inputPtr;
          inLen = this._inputEnd;
          continue;
        } 
        if (i <= 34) {
          if (i == 34) {
            this._inputPtr = inPtr;
            break;
          } 
          if (i < 32) {
            this._inputPtr = inPtr;
            _throwUnquotedSpace(i, "string value");
          } 
        } 
      } 
    } 
  }
  
  protected final void _skipCR() throws IOException {
    if ((this._inputPtr < this._inputEnd || _loadMore()) && 
      this._inputBuffer[this._inputPtr] == '\n')
      this._inputPtr++; 
    this._currInputRow++;
    this._currInputRowStart = this._inputPtr;
  }
  
  private final int _skipColon() throws IOException {
    if (this._inputPtr + 4 >= this._inputEnd)
      return _skipColon2(false); 
    char c = this._inputBuffer[this._inputPtr];
    if (c == ':') {
      int i = this._inputBuffer[++this._inputPtr];
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
    if (c == ' ' || c == '\t')
      c = this._inputBuffer[++this._inputPtr]; 
    if (c == ':') {
      int i = this._inputBuffer[++this._inputPtr];
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
      int i = this._inputBuffer[this._inputPtr++];
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
      if (i < 32) {
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
    boolean gotColon = (i == 58);
    if (gotColon) {
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
    } 
    this._inputPtr = ptr - 1;
    return _skipColon2(gotColon);
  }
  
  private final int _skipComma(int i) throws IOException {
    if (i != 44)
      _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries"); 
    while (this._inputPtr < this._inputEnd) {
      i = this._inputBuffer[this._inputPtr++];
      if (i > 32) {
        if (i == 47 || i == 35) {
          this._inputPtr--;
          return _skipAfterComma2();
        } 
        return i;
      } 
      if (i < 32) {
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
    return _skipAfterComma2();
  }
  
  private final int _skipAfterComma2() throws IOException {
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++];
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
      if (i < 32) {
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
    int i = this._inputBuffer[this._inputPtr++];
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
      i = this._inputBuffer[this._inputPtr++];
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
  
  private int _skipWSOrEnd2() throws IOException {
    while (true) {
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        return _eofAsNextChar(); 
      int i = this._inputBuffer[this._inputPtr++];
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
  }
  
  private void _skipComment() throws IOException {
    if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0)
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)"); 
    if (this._inputPtr >= this._inputEnd && !_loadMore())
      _reportInvalidEOF(" in a comment", null); 
    char c = this._inputBuffer[this._inputPtr++];
    if (c == '/') {
      _skipLine();
    } else if (c == '*') {
      _skipCComment();
    } else {
      _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
    } 
  }
  
  private void _skipCComment() throws IOException {
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++];
      if (i <= 42) {
        if (i == 42) {
          if (this._inputPtr >= this._inputEnd && !_loadMore())
            break; 
          if (this._inputBuffer[this._inputPtr] == '/') {
            this._inputPtr++;
            return;
          } 
          continue;
        } 
        if (i < 32) {
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
    } 
    _reportInvalidEOF(" in a comment", null);
  }
  
  private boolean _skipYAMLComment() throws IOException {
    if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0)
      return false; 
    _skipLine();
    return true;
  }
  
  private void _skipLine() throws IOException {
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      int i = this._inputBuffer[this._inputPtr++];
      if (i < 32) {
        if (i == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          break;
        } 
        if (i == 13) {
          _skipCR();
          break;
        } 
        if (i != 9)
          _throwInvalidSpace(i); 
      } 
    } 
  }
  
  protected char _decodeEscaped() throws IOException {
    if (this._inputPtr >= this._inputEnd && 
      !_loadMore())
      _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING); 
    char c = this._inputBuffer[this._inputPtr++];
    switch (c) {
      case 'b':
        return '\b';
      case 't':
        return '\t';
      case 'n':
        return '\n';
      case 'f':
        return '\f';
      case 'r':
        return '\r';
      case '"':
      case '/':
      case '\\':
        return c;
      case 'u':
        break;
      default:
        return _handleUnrecognizedCharacterEscape(c);
    } 
    int value = 0;
    for (int i = 0; i < 4; i++) {
      if (this._inputPtr >= this._inputEnd && 
        !_loadMore())
        _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING); 
      int ch = this._inputBuffer[this._inputPtr++];
      int digit = CharTypes.charToHex(ch);
      if (digit < 0)
        _reportUnexpectedChar(ch, "expected a hex-digit for character escape sequence"); 
      value = value << 4 | digit;
    } 
    return (char)value;
  }
  
  private final void _matchTrue() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 3 < this._inputEnd) {
      char[] b = this._inputBuffer;
      if (b[ptr] == 'r' && b[++ptr] == 'u' && b[++ptr] == 'e') {
        char c = b[++ptr];
        if (c < '0' || c == ']' || c == '}') {
          this._inputPtr = ptr;
          return;
        } 
      } 
    } 
    _matchToken("true", 1);
  }
  
  private final void _matchFalse() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 4 < this._inputEnd) {
      char[] b = this._inputBuffer;
      if (b[ptr] == 'a' && b[++ptr] == 'l' && b[++ptr] == 's' && b[++ptr] == 'e') {
        char c = b[++ptr];
        if (c < '0' || c == ']' || c == '}') {
          this._inputPtr = ptr;
          return;
        } 
      } 
    } 
    _matchToken("false", 1);
  }
  
  private final void _matchNull() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 3 < this._inputEnd) {
      char[] b = this._inputBuffer;
      if (b[ptr] == 'u' && b[++ptr] == 'l' && b[++ptr] == 'l') {
        char c = b[++ptr];
        if (c < '0' || c == ']' || c == '}') {
          this._inputPtr = ptr;
          return;
        } 
      } 
    } 
    _matchToken("null", 1);
  }
  
  protected final void _matchToken(String matchStr, int i) throws IOException {
    int len = matchStr.length();
    if (this._inputPtr + len >= this._inputEnd) {
      _matchToken2(matchStr, i);
      return;
    } 
    while (true) {
      if (this._inputBuffer[this._inputPtr] != matchStr.charAt(i))
        _reportInvalidToken(matchStr.substring(0, i)); 
      this._inputPtr++;
      if (++i >= len) {
        int ch = this._inputBuffer[this._inputPtr];
        if (ch >= 48 && ch != 93 && ch != 125)
          _checkMatchEnd(matchStr, i, ch); 
        return;
      } 
    } 
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
    int ch = this._inputBuffer[this._inputPtr];
    if (ch >= 48 && ch != 93 && ch != 125)
      _checkMatchEnd(matchStr, i, ch); 
  }
  
  private final void _checkMatchEnd(String matchStr, int i, int c) throws IOException {
    char ch = (char)c;
    if (Character.isJavaIdentifierPart(ch))
      _reportInvalidToken(matchStr.substring(0, i)); 
  }
  
  protected byte[] _decodeBase64(Base64Variant b64variant) throws IOException {
    ByteArrayBuilder builder = _getByteArrayBuilder();
    while (true) {
      if (this._inputPtr >= this._inputEnd)
        _loadMoreGuaranteed(); 
      char ch = this._inputBuffer[this._inputPtr++];
      if (ch > ' ') {
        int bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (ch == '"')
            return builder.toByteArray(); 
          bits = _decodeBase64Escape(b64variant, ch, 0);
          if (bits < 0)
            continue; 
        } 
        int decodedData = bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++];
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0)
          bits = _decodeBase64Escape(b64variant, ch, 1); 
        decodedData = decodedData << 6 | bits;
        if (this._inputPtr >= this._inputEnd)
          _loadMoreGuaranteed(); 
        ch = this._inputBuffer[this._inputPtr++];
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == '"') {
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
            ch = this._inputBuffer[this._inputPtr++];
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
        ch = this._inputBuffer[this._inputPtr++];
        bits = b64variant.decodeBase64Char(ch);
        if (bits < 0) {
          if (bits != -2) {
            if (ch == '"') {
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
      long total = this._currInputProcessed + this._nameStartOffset - 1L;
      return new JsonLocation(_contentReference(), -1L, total, this._nameStartRow, this._nameStartCol);
    } 
    return new JsonLocation(_contentReference(), -1L, this._tokenInputTotal - 1L, this._tokenInputRow, this._tokenInputCol);
  }
  
  public JsonLocation getCurrentLocation() {
    int col = this._inputPtr - this._currInputRowStart + 1;
    return new JsonLocation(_contentReference(), -1L, this._currInputProcessed + this._inputPtr, this._currInputRow, col);
  }
  
  private final void _updateLocation() {
    int ptr = this._inputPtr;
    this._tokenInputTotal = this._currInputProcessed + ptr;
    this._tokenInputRow = this._currInputRow;
    this._tokenInputCol = ptr - this._currInputRowStart;
  }
  
  private final void _updateNameLocation() {
    int ptr = this._inputPtr;
    this._nameStartOffset = ptr;
    this._nameStartRow = this._currInputRow;
    this._nameStartCol = ptr - this._currInputRowStart;
  }
  
  protected void _reportInvalidToken(String matchedPart) throws IOException {
    _reportInvalidToken(matchedPart, _validJsonTokenList());
  }
  
  protected void _reportInvalidToken(String matchedPart, String msg) throws IOException {
    StringBuilder sb = new StringBuilder(matchedPart);
    while (this._inputPtr < this._inputEnd || _loadMore()) {
      char c = this._inputBuffer[this._inputPtr];
      if (!Character.isJavaIdentifierPart(c))
        break; 
      this._inputPtr++;
      sb.append(c);
      if (sb.length() >= this._ioContext.errorReportConfiguration().getMaxErrorTokenLength()) {
        sb.append("...");
        break;
      } 
    } 
    _reportError("Unrecognized token '%s': was expecting %s", sb, msg);
  }
  
  private void _closeScope(int i) throws JsonParseException {
    if (i == 93) {
      _updateLocation();
      if (!this._parsingContext.inArray())
        _reportMismatchedEndMarker(i, '}'); 
      this._parsingContext = this._parsingContext.clearAndGetParent();
      this._currToken = JsonToken.END_ARRAY;
    } 
    if (i == 125) {
      _updateLocation();
      if (!this._parsingContext.inObject())
        _reportMismatchedEndMarker(i, ']'); 
      this._parsingContext = this._parsingContext.clearAndGetParent();
      this._currToken = JsonToken.END_OBJECT;
    } 
  }
}
