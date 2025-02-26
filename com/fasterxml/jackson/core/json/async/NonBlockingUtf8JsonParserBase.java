package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class NonBlockingUtf8JsonParserBase extends NonBlockingJsonParserBase {
  private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
  
  private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
  
  private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
  
  private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
  
  private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
  
  private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
  
  private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
  
  private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
  
  protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
  
  protected int _origBufferLen;
  
  protected NonBlockingUtf8JsonParserBase(IOContext ctxt, int parserFeatures, ByteQuadsCanonicalizer sym) {
    super(ctxt, parserFeatures, sym);
  }
  
  public final boolean needMoreInput() {
    return (this._inputPtr >= this._inputEnd && !this._endOfInput);
  }
  
  public void endOfInput() {
    this._endOfInput = true;
  }
  
  protected char _decodeEscaped() throws IOException {
    VersionUtil.throwInternal();
    return ' ';
  }
  
  public JsonToken nextToken() throws IOException {
    if (this._inputPtr >= this._inputEnd) {
      if (this._closed)
        return null; 
      if (this._endOfInput) {
        if (this._currToken == JsonToken.NOT_AVAILABLE)
          return _finishTokenWithEOF(); 
        return _eofAsNextToken();
      } 
      return JsonToken.NOT_AVAILABLE;
    } 
    if (this._currToken == JsonToken.NOT_AVAILABLE)
      return _finishToken(); 
    this._numTypesValid = 0;
    this._tokenInputTotal = this._currInputProcessed + this._inputPtr;
    this._binaryValue = null;
    int ch = getNextUnsignedByteFromBuffer();
    switch (this._majorState) {
      case 0:
        return _startDocument(ch);
      case 1:
        return _startValue(ch);
      case 2:
        return _startFieldName(ch);
      case 3:
        return _startFieldNameAfterComma(ch);
      case 4:
        return _startValueExpectColon(ch);
      case 5:
        return _startValue(ch);
      case 6:
        return _startValueExpectComma(ch);
    } 
    VersionUtil.throwInternal();
    return null;
  }
  
  protected abstract byte getNextSignedByteFromBuffer();
  
  protected abstract int getNextUnsignedByteFromBuffer();
  
  protected abstract byte getByteFromBuffer(int paramInt);
  
  protected final JsonToken _finishToken() throws IOException {
    int c;
    switch (this._minorState) {
      case 1:
        return _finishBOM(this._pending32);
      case 4:
        return _startFieldName(getNextUnsignedByteFromBuffer());
      case 5:
        return _startFieldNameAfterComma(getNextUnsignedByteFromBuffer());
      case 7:
        return _parseEscapedName(this._quadLength, this._pending32, this._pendingBytes);
      case 8:
        return _finishFieldWithEscape();
      case 9:
        return _finishAposName(this._quadLength, this._pending32, this._pendingBytes);
      case 10:
        return _finishUnquotedName(this._quadLength, this._pending32, this._pendingBytes);
      case 12:
        return _startValue(getNextUnsignedByteFromBuffer());
      case 15:
        return _startValueAfterComma(getNextUnsignedByteFromBuffer());
      case 13:
        return _startValueExpectComma(getNextUnsignedByteFromBuffer());
      case 14:
        return _startValueExpectColon(getNextUnsignedByteFromBuffer());
      case 16:
        return _finishKeywordToken("null", this._pending32, JsonToken.VALUE_NULL);
      case 17:
        return _finishKeywordToken("true", this._pending32, JsonToken.VALUE_TRUE);
      case 18:
        return _finishKeywordToken("false", this._pending32, JsonToken.VALUE_FALSE);
      case 19:
        return _finishNonStdToken(this._nonStdTokenType, this._pending32);
      case 22:
        return _finishNumberPlus(getNextUnsignedByteFromBuffer());
      case 23:
        return _finishNumberMinus(getNextUnsignedByteFromBuffer());
      case 24:
        return _finishNumberLeadingZeroes();
      case 25:
        return _finishNumberLeadingNegZeroes();
      case 26:
        return _finishNumberIntegralPart(this._textBuffer.getBufferWithoutReset(), this._textBuffer
            .getCurrentSegmentSize());
      case 30:
        return _finishFloatFraction();
      case 31:
        return _finishFloatExponent(true, getNextUnsignedByteFromBuffer());
      case 32:
        return _finishFloatExponent(false, getNextUnsignedByteFromBuffer());
      case 40:
        return _finishRegularString();
      case 42:
        this._textBuffer.append((char)_decodeUTF8_2(this._pending32, getNextSignedByteFromBuffer()));
        if (this._minorStateAfterSplit == 45)
          return _finishAposString(); 
        return _finishRegularString();
      case 43:
        if (!_decodeSplitUTF8_3(this._pending32, this._pendingBytes, getNextSignedByteFromBuffer()))
          return JsonToken.NOT_AVAILABLE; 
        if (this._minorStateAfterSplit == 45)
          return _finishAposString(); 
        return _finishRegularString();
      case 44:
        if (!_decodeSplitUTF8_4(this._pending32, this._pendingBytes, getNextSignedByteFromBuffer()))
          return JsonToken.NOT_AVAILABLE; 
        if (this._minorStateAfterSplit == 45)
          return _finishAposString(); 
        return _finishRegularString();
      case 41:
        c = _decodeSplitEscaped(this._quoted32, this._quotedDigits);
        if (c < 0)
          return JsonToken.NOT_AVAILABLE; 
        this._textBuffer.append((char)c);
        if (this._minorStateAfterSplit == 45)
          return _finishAposString(); 
        return _finishRegularString();
      case 45:
        return _finishAposString();
      case 50:
        return _finishErrorToken();
      case 51:
        return _startSlashComment(this._pending32);
      case 52:
        return _finishCComment(this._pending32, true);
      case 53:
        return _finishCComment(this._pending32, false);
      case 54:
        return _finishCppComment(this._pending32);
      case 55:
        return _finishHashComment(this._pending32);
    } 
    VersionUtil.throwInternal();
    return null;
  }
  
  protected final JsonToken _finishTokenWithEOF() throws IOException {
    int len;
    JsonToken t = this._currToken;
    switch (this._minorState) {
      case 3:
        return _eofAsNextToken();
      case 12:
        return _eofAsNextToken();
      case 16:
        return _finishKeywordTokenWithEOF("null", this._pending32, JsonToken.VALUE_NULL);
      case 17:
        return _finishKeywordTokenWithEOF("true", this._pending32, JsonToken.VALUE_TRUE);
      case 18:
        return _finishKeywordTokenWithEOF("false", this._pending32, JsonToken.VALUE_FALSE);
      case 19:
        return _finishNonStdTokenWithEOF(this._nonStdTokenType, this._pending32);
      case 50:
        return _finishErrorTokenWithEOF();
      case 24:
      case 25:
        return _valueCompleteInt(0, "0");
      case 26:
        len = this._textBuffer.getCurrentSegmentSize();
        if (this._numberNegative)
          len--; 
        this._intLength = len;
        return _valueComplete(JsonToken.VALUE_NUMBER_INT);
      case 30:
        this._expLength = 0;
      case 32:
        return _valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
      case 31:
        _reportInvalidEOF(": was expecting fraction after exponent marker", JsonToken.VALUE_NUMBER_FLOAT);
      case 52:
      case 53:
        _reportInvalidEOF(": was expecting closing '*/' for comment", JsonToken.NOT_AVAILABLE);
      case 54:
      case 55:
        return _eofAsNextToken();
    } 
    _reportInvalidEOF(": was expecting rest of token (internal state: " + this._minorState + ")", this._currToken);
    return t;
  }
  
  private final JsonToken _startDocument(int ch) throws IOException {
    ch &= 0xFF;
    if (ch == 239 && this._minorState != 1)
      return _finishBOM(1); 
    while (ch <= 32) {
      if (ch != 32)
        if (ch == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
        } else if (ch == 13) {
          this._currInputRowAlt++;
          this._currInputRowStart = this._inputPtr;
        } else if (ch != 9) {
          _throwInvalidSpace(ch);
        }  
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 3;
        if (this._closed)
          return null; 
        if (this._endOfInput)
          return _eofAsNextToken(); 
        return JsonToken.NOT_AVAILABLE;
      } 
      ch = getNextUnsignedByteFromBuffer();
    } 
    return _startValue(ch);
  }
  
  private final JsonToken _finishBOM(int bytesHandled) throws IOException {
    while (this._inputPtr < this._inputEnd) {
      int ch = getNextUnsignedByteFromBuffer();
      switch (bytesHandled) {
        case 3:
          this._currInputProcessed -= 3L;
          return _startDocument(ch);
        case 2:
          if (ch != 191)
            _reportError("Unexpected byte 0x%02x following 0xEF 0xBB; should get 0xBF as third byte of UTF-8 BOM", Integer.valueOf(ch)); 
          break;
        case 1:
          if (ch != 187)
            _reportError("Unexpected byte 0x%02x following 0xEF; should get 0xBB as second byte UTF-8 BOM", Integer.valueOf(ch)); 
          break;
      } 
      bytesHandled++;
    } 
    this._pending32 = bytesHandled;
    this._minorState = 1;
    return this._currToken = JsonToken.NOT_AVAILABLE;
  }
  
  private final JsonToken _startFieldName(int ch) throws IOException {
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 4;
        return this._currToken;
      } 
    } 
    _updateTokenLocation();
    if (ch != 34) {
      if (ch == 125)
        return _closeObjectScope(); 
      return _handleOddName(ch);
    } 
    if (this._inputPtr + 13 <= this._inputEnd) {
      String n = _fastParseName();
      if (n != null)
        return _fieldComplete(n); 
    } 
    return _parseEscapedName(0, 0, 0);
  }
  
  private final JsonToken _startFieldNameAfterComma(int ch) throws IOException {
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 5;
        return this._currToken;
      } 
    } 
    if (ch != 44) {
      if (ch == 125)
        return _closeObjectScope(); 
      if (ch == 35)
        return _finishHashComment(5); 
      if (ch == 47)
        return _startSlashComment(5); 
      _reportUnexpectedChar(ch, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
    } 
    int ptr = this._inputPtr;
    if (ptr >= this._inputEnd) {
      this._minorState = 4;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    ch = getByteFromBuffer(ptr);
    this._inputPtr = ptr + 1;
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 4;
        return this._currToken;
      } 
    } 
    _updateTokenLocation();
    if (ch != 34) {
      if (ch == 125 && (
        this._features & FEAT_MASK_TRAILING_COMMA) != 0)
        return _closeObjectScope(); 
      return _handleOddName(ch);
    } 
    if (this._inputPtr + 13 <= this._inputEnd) {
      String n = _fastParseName();
      if (n != null)
        return _fieldComplete(n); 
    } 
    return _parseEscapedName(0, 0, 0);
  }
  
  private final JsonToken _startValue(int ch) throws IOException {
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 12;
        return this._currToken;
      } 
    } 
    _updateTokenLocation();
    this._parsingContext.expectComma();
    if (ch == 34)
      return _startString(); 
    switch (ch) {
      case 35:
        return _finishHashComment(12);
      case 43:
        return _startPositiveNumber();
      case 45:
        return _startNegativeNumber();
      case 47:
        return _startSlashComment(12);
      case 46:
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
          return _startFloatThatStartsWithPeriod(); 
        break;
      case 48:
        return _startNumberLeadingZero();
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        return _startPositiveNumber(ch);
      case 102:
        return _startFalseToken();
      case 110:
        return _startNullToken();
      case 116:
        return _startTrueToken();
      case 91:
        return _startArrayScope();
      case 93:
        return _closeArrayScope();
      case 123:
        return _startObjectScope();
      case 125:
        return _closeObjectScope();
    } 
    return _startUnexpectedValue(false, ch);
  }
  
  private final JsonToken _startValueExpectComma(int ch) throws IOException {
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 13;
        return this._currToken;
      } 
    } 
    if (ch != 44) {
      if (ch == 93)
        return _closeArrayScope(); 
      if (ch == 125)
        return _closeObjectScope(); 
      if (ch == 47)
        return _startSlashComment(13); 
      if (ch == 35)
        return _finishHashComment(13); 
      _reportUnexpectedChar(ch, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
    } 
    this._parsingContext.expectComma();
    int ptr = this._inputPtr;
    if (ptr >= this._inputEnd) {
      this._minorState = 15;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    ch = getByteFromBuffer(ptr);
    this._inputPtr = ptr + 1;
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 15;
        return this._currToken;
      } 
    } 
    _updateTokenLocation();
    if (ch == 34)
      return _startString(); 
    switch (ch) {
      case 35:
        return _finishHashComment(15);
      case 43:
        return _startPositiveNumber();
      case 45:
        return _startNegativeNumber();
      case 47:
        return _startSlashComment(15);
      case 48:
        return _startNumberLeadingZero();
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        return _startPositiveNumber(ch);
      case 102:
        return _startFalseToken();
      case 110:
        return _startNullToken();
      case 116:
        return _startTrueToken();
      case 91:
        return _startArrayScope();
      case 93:
        if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0)
          return _closeArrayScope(); 
        break;
      case 123:
        return _startObjectScope();
      case 125:
        if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0)
          return _closeObjectScope(); 
        break;
    } 
    return _startUnexpectedValue(true, ch);
  }
  
  private final JsonToken _startValueExpectColon(int ch) throws IOException {
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 14;
        return this._currToken;
      } 
    } 
    if (ch != 58) {
      if (ch == 47)
        return _startSlashComment(14); 
      if (ch == 35)
        return _finishHashComment(14); 
      _reportUnexpectedChar(ch, "was expecting a colon to separate field name and value");
    } 
    int ptr = this._inputPtr;
    if (ptr >= this._inputEnd) {
      this._minorState = 12;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    ch = getByteFromBuffer(ptr);
    this._inputPtr = ptr + 1;
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 12;
        return this._currToken;
      } 
    } 
    _updateTokenLocation();
    if (ch == 34)
      return _startString(); 
    switch (ch) {
      case 35:
        return _finishHashComment(12);
      case 43:
        return _startPositiveNumber();
      case 45:
        return _startNegativeNumber();
      case 47:
        return _startSlashComment(12);
      case 48:
        return _startNumberLeadingZero();
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        return _startPositiveNumber(ch);
      case 102:
        return _startFalseToken();
      case 110:
        return _startNullToken();
      case 116:
        return _startTrueToken();
      case 91:
        return _startArrayScope();
      case 123:
        return _startObjectScope();
    } 
    return _startUnexpectedValue(false, ch);
  }
  
  private final JsonToken _startValueAfterComma(int ch) throws IOException {
    if (ch <= 32) {
      ch = _skipWS(ch);
      if (ch <= 0) {
        this._minorState = 15;
        return this._currToken;
      } 
    } 
    _updateTokenLocation();
    if (ch == 34)
      return _startString(); 
    switch (ch) {
      case 35:
        return _finishHashComment(15);
      case 43:
        return _startPositiveNumber();
      case 45:
        return _startNegativeNumber();
      case 47:
        return _startSlashComment(15);
      case 48:
        return _startNumberLeadingZero();
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        return _startPositiveNumber(ch);
      case 102:
        return _startFalseToken();
      case 110:
        return _startNullToken();
      case 116:
        return _startTrueToken();
      case 91:
        return _startArrayScope();
      case 93:
        if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0)
          return _closeArrayScope(); 
        break;
      case 123:
        return _startObjectScope();
      case 125:
        if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0)
          return _closeObjectScope(); 
        break;
    } 
    return _startUnexpectedValue(true, ch);
  }
  
  protected JsonToken _startUnexpectedValue(boolean leadingComma, int ch) throws IOException {
    switch (ch) {
      case 93:
        if (!this._parsingContext.inArray())
          break; 
      case 44:
        if (!this._parsingContext.inRoot() && (
          this._features & FEAT_MASK_ALLOW_MISSING) != 0) {
          this._inputPtr--;
          return _valueComplete(JsonToken.VALUE_NULL);
        } 
        break;
      case 39:
        if ((this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0)
          return _startAposString(); 
        break;
      case 43:
        return _finishNonStdToken(2, 1);
      case 78:
        return _finishNonStdToken(0, 1);
      case 73:
        return _finishNonStdToken(1, 1);
    } 
    _reportUnexpectedChar(ch, "expected a valid value " + _validJsonValueList());
    return null;
  }
  
  private final int _skipWS(int ch) throws IOException {
    while (true) {
      if (ch != 32)
        if (ch == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
        } else if (ch == 13) {
          this._currInputRowAlt++;
          this._currInputRowStart = this._inputPtr;
        } else if (ch != 9) {
          _throwInvalidSpace(ch);
        }  
      if (this._inputPtr >= this._inputEnd) {
        this._currToken = JsonToken.NOT_AVAILABLE;
        return 0;
      } 
      ch = getNextUnsignedByteFromBuffer();
      if (ch > 32)
        return ch; 
    } 
  }
  
  private final JsonToken _startSlashComment(int fromMinorState) throws IOException {
    if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0)
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)"); 
    if (this._inputPtr >= this._inputEnd) {
      this._pending32 = fromMinorState;
      this._minorState = 51;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    int ch = getNextSignedByteFromBuffer();
    if (ch == 42)
      return _finishCComment(fromMinorState, false); 
    if (ch == 47)
      return _finishCppComment(fromMinorState); 
    _reportUnexpectedChar(ch & 0xFF, "was expecting either '*' or '/' for a comment");
    return null;
  }
  
  private final JsonToken _finishHashComment(int fromMinorState) throws IOException {
    if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0)
      _reportUnexpectedChar(35, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_YAML_COMMENTS' not enabled for parser)"); 
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 55;
        this._pending32 = fromMinorState;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getNextUnsignedByteFromBuffer();
      if (ch < 32) {
        if (ch == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          break;
        } 
        if (ch == 13) {
          this._currInputRowAlt++;
          this._currInputRowStart = this._inputPtr;
          break;
        } 
        if (ch != 9)
          _throwInvalidSpace(ch); 
      } 
    } 
    return _startAfterComment(fromMinorState);
  }
  
  private final JsonToken _finishCppComment(int fromMinorState) throws IOException {
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 54;
        this._pending32 = fromMinorState;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getNextUnsignedByteFromBuffer();
      if (ch < 32) {
        if (ch == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
          break;
        } 
        if (ch == 13) {
          this._currInputRowAlt++;
          this._currInputRowStart = this._inputPtr;
          break;
        } 
        if (ch != 9)
          _throwInvalidSpace(ch); 
      } 
    } 
    return _startAfterComment(fromMinorState);
  }
  
  private final JsonToken _finishCComment(int fromMinorState, boolean gotStar) throws IOException {
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = gotStar ? 52 : 53;
        this._pending32 = fromMinorState;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getNextUnsignedByteFromBuffer();
      if (ch < 32) {
        if (ch == 10) {
          this._currInputRow++;
          this._currInputRowStart = this._inputPtr;
        } else if (ch == 13) {
          this._currInputRowAlt++;
          this._currInputRowStart = this._inputPtr;
        } else if (ch != 9) {
          _throwInvalidSpace(ch);
        } 
      } else {
        if (ch == 42) {
          gotStar = true;
          continue;
        } 
        if (ch == 47 && 
          gotStar)
          break; 
      } 
      gotStar = false;
    } 
    return _startAfterComment(fromMinorState);
  }
  
  private final JsonToken _startAfterComment(int fromMinorState) throws IOException {
    if (this._inputPtr >= this._inputEnd) {
      this._minorState = fromMinorState;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    int ch = getNextUnsignedByteFromBuffer();
    switch (fromMinorState) {
      case 4:
        return _startFieldName(ch);
      case 5:
        return _startFieldNameAfterComma(ch);
      case 12:
        return _startValue(ch);
      case 13:
        return _startValueExpectComma(ch);
      case 14:
        return _startValueExpectColon(ch);
      case 15:
        return _startValueAfterComma(ch);
    } 
    VersionUtil.throwInternal();
    return null;
  }
  
  protected JsonToken _startFalseToken() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 4 < this._inputEnd && 
      getByteFromBuffer(ptr++) == 97 && 
      getByteFromBuffer(ptr++) == 108 && 
      getByteFromBuffer(ptr++) == 115 && 
      getByteFromBuffer(ptr++) == 101) {
      int ch = getByteFromBuffer(ptr) & 0xFF;
      if (ch < 48 || ch == 93 || ch == 125) {
        this._inputPtr = ptr;
        return _valueComplete(JsonToken.VALUE_FALSE);
      } 
    } 
    this._minorState = 18;
    return _finishKeywordToken("false", 1, JsonToken.VALUE_FALSE);
  }
  
  protected JsonToken _startTrueToken() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 3 < this._inputEnd && 
      getByteFromBuffer(ptr++) == 114 && 
      getByteFromBuffer(ptr++) == 117 && 
      getByteFromBuffer(ptr++) == 101) {
      int ch = getByteFromBuffer(ptr) & 0xFF;
      if (ch < 48 || ch == 93 || ch == 125) {
        this._inputPtr = ptr;
        return _valueComplete(JsonToken.VALUE_TRUE);
      } 
    } 
    this._minorState = 17;
    return _finishKeywordToken("true", 1, JsonToken.VALUE_TRUE);
  }
  
  protected JsonToken _startNullToken() throws IOException {
    int ptr = this._inputPtr;
    if (ptr + 3 < this._inputEnd && 
      getByteFromBuffer(ptr++) == 117 && 
      getByteFromBuffer(ptr++) == 108 && 
      getByteFromBuffer(ptr++) == 108) {
      int ch = getByteFromBuffer(ptr) & 0xFF;
      if (ch < 48 || ch == 93 || ch == 125) {
        this._inputPtr = ptr;
        return _valueComplete(JsonToken.VALUE_NULL);
      } 
    } 
    this._minorState = 16;
    return _finishKeywordToken("null", 1, JsonToken.VALUE_NULL);
  }
  
  protected JsonToken _finishKeywordToken(String expToken, int matched, JsonToken result) throws IOException {
    int end = expToken.length();
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._pending32 = matched;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getByteFromBuffer(this._inputPtr);
      if (matched == end) {
        if (ch < 48 || ch == 93 || ch == 125)
          return _valueComplete(result); 
        break;
      } 
      if (ch != expToken.charAt(matched))
        break; 
      matched++;
      this._inputPtr++;
    } 
    this._minorState = 50;
    this._textBuffer.resetWithCopy(expToken, 0, matched);
    return _finishErrorToken();
  }
  
  protected JsonToken _finishKeywordTokenWithEOF(String expToken, int matched, JsonToken result) throws IOException {
    if (matched == expToken.length())
      return this._currToken = result; 
    this._textBuffer.resetWithCopy(expToken, 0, matched);
    return _finishErrorTokenWithEOF();
  }
  
  protected JsonToken _finishNonStdToken(int type, int matched) throws IOException {
    String expToken = _nonStdToken(type);
    int end = expToken.length();
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._nonStdTokenType = type;
        this._pending32 = matched;
        this._minorState = 19;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getByteFromBuffer(this._inputPtr);
      if (matched == end) {
        if (ch < 48 || ch == 93 || ch == 125)
          return _valueNonStdNumberComplete(type); 
        break;
      } 
      if (ch != expToken.charAt(matched))
        break; 
      matched++;
      this._inputPtr++;
    } 
    this._minorState = 50;
    this._textBuffer.resetWithCopy(expToken, 0, matched);
    return _finishErrorToken();
  }
  
  protected JsonToken _finishNonStdTokenWithEOF(int type, int matched) throws IOException {
    String expToken = _nonStdToken(type);
    if (matched == expToken.length())
      return _valueNonStdNumberComplete(type); 
    this._textBuffer.resetWithCopy(expToken, 0, matched);
    return _finishErrorTokenWithEOF();
  }
  
  protected JsonToken _finishErrorToken() throws IOException {
    while (this._inputPtr < this._inputEnd) {
      int i = getNextSignedByteFromBuffer();
      char ch = (char)i;
      if (Character.isJavaIdentifierPart(ch)) {
        this._textBuffer.append(ch);
        if (this._textBuffer.size() < this._ioContext.errorReportConfiguration().getMaxErrorTokenLength())
          continue; 
      } 
      return _reportErrorToken(this._textBuffer.contentsAsString());
    } 
    return this._currToken = JsonToken.NOT_AVAILABLE;
  }
  
  protected JsonToken _finishErrorTokenWithEOF() throws IOException {
    return _reportErrorToken(this._textBuffer.contentsAsString());
  }
  
  protected JsonToken _reportErrorToken(String actualToken) throws IOException {
    _reportError("Unrecognized token '%s': was expecting %s", this._textBuffer.contentsAsString(), 
        _validJsonTokenList());
    return JsonToken.NOT_AVAILABLE;
  }
  
  protected JsonToken _startFloatThatStartsWithPeriod() throws IOException {
    this._numberNegative = false;
    this._intLength = 0;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    return _startFloat(outBuf, 0, 46);
  }
  
  protected JsonToken _startPositiveNumber(int ch) throws IOException {
    this._numberNegative = false;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    outBuf[0] = (char)ch;
    if (this._inputPtr >= this._inputEnd) {
      this._minorState = 26;
      this._textBuffer.setCurrentLength(1);
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    int outPtr = 1;
    ch = getByteFromBuffer(this._inputPtr) & 0xFF;
    while (true) {
      if (ch < 48) {
        if (ch == 46) {
          this._intLength = outPtr;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      if (ch > 57) {
        if (ch == 101 || ch == 69) {
          this._intLength = outPtr;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      if (outPtr >= outBuf.length)
        outBuf = this._textBuffer.expandCurrentSegment(); 
      outBuf[outPtr++] = (char)ch;
      if (++this._inputPtr >= this._inputEnd) {
        this._minorState = 26;
        this._textBuffer.setCurrentLength(outPtr);
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      ch = getByteFromBuffer(this._inputPtr) & 0xFF;
    } 
    this._intLength = outPtr;
    this._textBuffer.setCurrentLength(outPtr);
    return _valueComplete(JsonToken.VALUE_NUMBER_INT);
  }
  
  protected JsonToken _startNegativeNumber() throws IOException {
    this._numberNegative = true;
    if (this._inputPtr >= this._inputEnd) {
      this._minorState = 23;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    int ch = getNextUnsignedByteFromBuffer();
    if (ch <= 48) {
      if (ch == 48)
        return _finishNumberLeadingNegZeroes(); 
      _reportUnexpectedNumberChar(ch, "expected digit (0-9) to follow minus sign, for valid numeric value");
    } else if (ch > 57) {
      if (ch == 73)
        return _finishNonStdToken(3, 2); 
      _reportUnexpectedNumberChar(ch, "expected digit (0-9) to follow minus sign, for valid numeric value");
    } 
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    outBuf[0] = '-';
    outBuf[1] = (char)ch;
    if (this._inputPtr >= this._inputEnd) {
      this._minorState = 26;
      this._textBuffer.setCurrentLength(2);
      this._intLength = 1;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    ch = getByteFromBuffer(this._inputPtr);
    int outPtr = 2;
    while (true) {
      if (ch < 48) {
        if (ch == 46) {
          this._intLength = outPtr - 1;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      if (ch > 57) {
        if (ch == 101 || ch == 69) {
          this._intLength = outPtr - 1;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      if (outPtr >= outBuf.length)
        outBuf = this._textBuffer.expandCurrentSegment(); 
      outBuf[outPtr++] = (char)ch;
      if (++this._inputPtr >= this._inputEnd) {
        this._minorState = 26;
        this._textBuffer.setCurrentLength(outPtr);
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      ch = getByteFromBuffer(this._inputPtr) & 0xFF;
    } 
    this._intLength = outPtr - 1;
    this._textBuffer.setCurrentLength(outPtr);
    return _valueComplete(JsonToken.VALUE_NUMBER_INT);
  }
  
  protected JsonToken _startPositiveNumber() throws IOException {
    this._numberNegative = false;
    if (this._inputPtr >= this._inputEnd) {
      this._minorState = 22;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    int ch = getNextUnsignedByteFromBuffer();
    if (ch <= 48) {
      if (ch == 48) {
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()))
          _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow"); 
        return _finishNumberLeadingPosZeroes();
      } 
      _reportUnexpectedNumberChar(ch, "expected digit (0-9) to follow plus sign, for valid numeric value");
    } else if (ch > 57) {
      if (ch == 73)
        return _finishNonStdToken(2, 2); 
      _reportUnexpectedNumberChar(ch, "expected digit (0-9) to follow plus sign, for valid numeric value");
    } 
    if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()))
      _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow"); 
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    outBuf[0] = '+';
    outBuf[1] = (char)ch;
    if (this._inputPtr >= this._inputEnd) {
      this._minorState = 26;
      this._textBuffer.setCurrentLength(2);
      this._intLength = 1;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    ch = getByteFromBuffer(this._inputPtr);
    int outPtr = 2;
    while (true) {
      if (ch < 48) {
        if (ch == 46) {
          this._intLength = outPtr - 1;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      if (ch > 57) {
        if (ch == 101 || ch == 69) {
          this._intLength = outPtr - 1;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      if (outPtr >= outBuf.length)
        outBuf = this._textBuffer.expandCurrentSegment(); 
      outBuf[outPtr++] = (char)ch;
      if (++this._inputPtr >= this._inputEnd) {
        this._minorState = 26;
        this._textBuffer.setCurrentLength(outPtr);
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      ch = getByteFromBuffer(this._inputPtr) & 0xFF;
    } 
    this._intLength = outPtr - 1;
    this._textBuffer.setCurrentLength(outPtr);
    return _valueComplete(JsonToken.VALUE_NUMBER_INT);
  }
  
  protected JsonToken _startNumberLeadingZero() throws IOException {
    int ptr = this._inputPtr;
    if (ptr >= this._inputEnd) {
      this._minorState = 24;
      return this._currToken = JsonToken.NOT_AVAILABLE;
    } 
    int ch = getByteFromBuffer(ptr++) & 0xFF;
    if (ch < 48) {
      if (ch == 46) {
        this._inputPtr = ptr;
        this._intLength = 1;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        outBuf[0] = '0';
        return _startFloat(outBuf, 1, ch);
      } 
    } else if (ch > 57) {
      if (ch == 101 || ch == 69) {
        this._inputPtr = ptr;
        this._intLength = 1;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        outBuf[0] = '0';
        return _startFloat(outBuf, 1, ch);
      } 
      if (ch != 93 && ch != 125)
        _reportUnexpectedNumberChar(ch, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'"); 
    } else {
      return _finishNumberLeadingZeroes();
    } 
    return _valueCompleteInt(0, "0");
  }
  
  protected JsonToken _finishNumberMinus(int ch) throws IOException {
    return _finishNumberPlusMinus(ch, true);
  }
  
  protected JsonToken _finishNumberPlus(int ch) throws IOException {
    return _finishNumberPlusMinus(ch, false);
  }
  
  protected JsonToken _finishNumberPlusMinus(int ch, boolean negative) throws IOException {
    if (ch <= 48) {
      if (ch == 48) {
        if (negative)
          return _finishNumberLeadingNegZeroes(); 
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()))
          _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow"); 
        return _finishNumberLeadingPosZeroes();
      } 
      if (ch == 46 && isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
        if (negative) {
          this._inputPtr--;
          return _finishNumberLeadingNegZeroes();
        } 
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()))
          _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow"); 
        this._inputPtr--;
        return _finishNumberLeadingPosZeroes();
      } 
      String message = negative ? "expected digit (0-9) to follow minus sign, for valid numeric value" : "expected digit (0-9) for valid numeric value";
      _reportUnexpectedNumberChar(ch, message);
    } else if (ch > 57) {
      if (ch == 73) {
        int token = negative ? 3 : 2;
        return _finishNonStdToken(token, 2);
      } 
      String message = negative ? "expected digit (0-9) to follow minus sign, for valid numeric value" : "expected digit (0-9) for valid numeric value";
      _reportUnexpectedNumberChar(ch, message);
    } 
    if (!negative && !isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()))
      _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow"); 
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    outBuf[0] = negative ? '-' : '+';
    outBuf[1] = (char)ch;
    this._intLength = 1;
    return _finishNumberIntegralPart(outBuf, 2);
  }
  
  protected JsonToken _finishNumberLeadingZeroes() throws IOException {
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 24;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getNextUnsignedByteFromBuffer();
      if (ch < 48) {
        if (ch == 46) {
          char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
          arrayOfChar[0] = '0';
          this._intLength = 1;
          return _startFloat(arrayOfChar, 1, ch);
        } 
        break;
      } 
      if (ch > 57) {
        if (ch == 101 || ch == 69) {
          char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
          arrayOfChar[0] = '0';
          this._intLength = 1;
          return _startFloat(arrayOfChar, 1, ch);
        } 
        if (ch != 93 && ch != 125)
          _reportUnexpectedNumberChar(ch, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'"); 
        break;
      } 
      if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0)
        reportInvalidNumber("Leading zeroes not allowed"); 
      if (ch == 48)
        continue; 
      char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
      outBuf[0] = (char)ch;
      this._intLength = 1;
      return _finishNumberIntegralPart(outBuf, 1);
    } 
    this._inputPtr--;
    return _valueCompleteInt(0, "0");
  }
  
  protected JsonToken _finishNumberLeadingNegZeroes() throws IOException {
    return _finishNumberLeadingPosNegZeroes(true);
  }
  
  protected JsonToken _finishNumberLeadingPosZeroes() throws IOException {
    return _finishNumberLeadingPosNegZeroes(false);
  }
  
  protected JsonToken _finishNumberLeadingPosNegZeroes(boolean negative) throws IOException {
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = negative ? 25 : 24;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getNextUnsignedByteFromBuffer();
      if (ch < 48) {
        if (ch == 46) {
          char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
          arrayOfChar[0] = negative ? '-' : '+';
          arrayOfChar[1] = '0';
          this._intLength = 1;
          return _startFloat(arrayOfChar, 2, ch);
        } 
        break;
      } 
      if (ch > 57) {
        if (ch == 101 || ch == 69) {
          char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
          arrayOfChar[0] = negative ? '-' : '+';
          arrayOfChar[1] = '0';
          this._intLength = 1;
          return _startFloat(arrayOfChar, 2, ch);
        } 
        if (ch != 93 && ch != 125)
          _reportUnexpectedNumberChar(ch, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'"); 
        break;
      } 
      if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0)
        reportInvalidNumber("Leading zeroes not allowed"); 
      if (ch == 48)
        continue; 
      char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
      outBuf[0] = negative ? '-' : '+';
      outBuf[1] = (char)ch;
      this._intLength = 1;
      return _finishNumberIntegralPart(outBuf, 2);
    } 
    this._inputPtr--;
    return _valueCompleteInt(0, "0");
  }
  
  protected JsonToken _finishNumberIntegralPart(char[] outBuf, int outPtr) throws IOException {
    int negMod = this._numberNegative ? -1 : 0;
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 26;
        this._textBuffer.setCurrentLength(outPtr);
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getByteFromBuffer(this._inputPtr) & 0xFF;
      if (ch < 48) {
        if (ch == 46) {
          this._intLength = outPtr + negMod;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      if (ch > 57) {
        if (ch == 101 || ch == 69) {
          this._intLength = outPtr + negMod;
          this._inputPtr++;
          return _startFloat(outBuf, outPtr, ch);
        } 
        break;
      } 
      this._inputPtr++;
      if (outPtr >= outBuf.length)
        outBuf = this._textBuffer.expandCurrentSegment(); 
      outBuf[outPtr++] = (char)ch;
    } 
    this._intLength = outPtr + negMod;
    this._textBuffer.setCurrentLength(outPtr);
    return _valueComplete(JsonToken.VALUE_NUMBER_INT);
  }
  
  protected JsonToken _startFloat(char[] outBuf, int outPtr, int ch) throws IOException {
    int fractLen = 0;
    if (ch == 46) {
      if (outPtr >= outBuf.length)
        outBuf = this._textBuffer.expandCurrentSegment(); 
      outBuf[outPtr++] = '.';
      while (true) {
        if (this._inputPtr >= this._inputEnd) {
          this._textBuffer.setCurrentLength(outPtr);
          this._minorState = 30;
          this._fractLength = fractLen;
          return this._currToken = JsonToken.NOT_AVAILABLE;
        } 
        ch = getNextSignedByteFromBuffer();
        if (ch < 48 || ch > 57) {
          ch &= 0xFF;
          if (fractLen == 0 && 
            !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
            _reportUnexpectedNumberChar(ch, "Decimal point not followed by a digit"); 
          break;
        } 
        if (outPtr >= outBuf.length)
          outBuf = this._textBuffer.expandCurrentSegment(); 
        outBuf[outPtr++] = (char)ch;
        fractLen++;
      } 
    } 
    this._fractLength = fractLen;
    int expLen = 0;
    if (ch == 101 || ch == 69) {
      if (outPtr >= outBuf.length)
        outBuf = this._textBuffer.expandCurrentSegment(); 
      outBuf[outPtr++] = (char)ch;
      if (this._inputPtr >= this._inputEnd) {
        this._textBuffer.setCurrentLength(outPtr);
        this._minorState = 31;
        this._expLength = 0;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      ch = getNextSignedByteFromBuffer();
      if (ch == 45 || ch == 43) {
        if (outPtr >= outBuf.length)
          outBuf = this._textBuffer.expandCurrentSegment(); 
        outBuf[outPtr++] = (char)ch;
        if (this._inputPtr >= this._inputEnd) {
          this._textBuffer.setCurrentLength(outPtr);
          this._minorState = 32;
          this._expLength = 0;
          return this._currToken = JsonToken.NOT_AVAILABLE;
        } 
        ch = getNextSignedByteFromBuffer();
      } 
      while (ch >= 48 && ch <= 57) {
        expLen++;
        if (outPtr >= outBuf.length)
          outBuf = this._textBuffer.expandCurrentSegment(); 
        outBuf[outPtr++] = (char)ch;
        if (this._inputPtr >= this._inputEnd) {
          this._textBuffer.setCurrentLength(outPtr);
          this._minorState = 32;
          this._expLength = expLen;
          return this._currToken = JsonToken.NOT_AVAILABLE;
        } 
        ch = getNextSignedByteFromBuffer();
      } 
      ch &= 0xFF;
      if (expLen == 0)
        _reportUnexpectedNumberChar(ch, "Exponent indicator not followed by a digit"); 
    } 
    this._inputPtr--;
    this._textBuffer.setCurrentLength(outPtr);
    this._expLength = expLen;
    return _valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
  }
  
  protected JsonToken _finishFloatFraction() throws IOException {
    int fractLen = this._fractLength;
    char[] outBuf = this._textBuffer.getBufferWithoutReset();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    int ch = getNextSignedByteFromBuffer();
    boolean loop = true;
    while (loop) {
      if (ch >= 48 && ch <= 57) {
        fractLen++;
        if (outPtr >= outBuf.length)
          outBuf = this._textBuffer.expandCurrentSegment(); 
        outBuf[outPtr++] = (char)ch;
        if (this._inputPtr >= this._inputEnd) {
          this._textBuffer.setCurrentLength(outPtr);
          this._fractLength = fractLen;
          return JsonToken.NOT_AVAILABLE;
        } 
        ch = getNextSignedByteFromBuffer();
        continue;
      } 
      if (ch == 102 || ch == 100 || ch == 70 || ch == 68) {
        _reportUnexpectedNumberChar(ch, "JSON does not support parsing numbers that have 'f' or 'd' suffixes");
        continue;
      } 
      if (ch == 46) {
        _reportUnexpectedNumberChar(ch, "Cannot parse number with more than one decimal point");
        continue;
      } 
      loop = false;
    } 
    if (fractLen == 0 && 
      !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()))
      _reportUnexpectedNumberChar(ch, "Decimal point not followed by a digit"); 
    this._fractLength = fractLen;
    this._textBuffer.setCurrentLength(outPtr);
    if (ch == 101 || ch == 69) {
      this._textBuffer.append((char)ch);
      this._expLength = 0;
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 31;
        return JsonToken.NOT_AVAILABLE;
      } 
      this._minorState = 32;
      return _finishFloatExponent(true, getNextUnsignedByteFromBuffer());
    } 
    this._inputPtr--;
    this._textBuffer.setCurrentLength(outPtr);
    this._expLength = 0;
    return _valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
  }
  
  protected JsonToken _finishFloatExponent(boolean checkSign, int ch) throws IOException {
    if (checkSign) {
      this._minorState = 32;
      if (ch == 45 || ch == 43) {
        this._textBuffer.append((char)ch);
        if (this._inputPtr >= this._inputEnd) {
          this._minorState = 32;
          this._expLength = 0;
          return JsonToken.NOT_AVAILABLE;
        } 
        ch = getNextSignedByteFromBuffer();
      } 
    } 
    char[] outBuf = this._textBuffer.getBufferWithoutReset();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    int expLen = this._expLength;
    while (ch >= 48 && ch <= 57) {
      expLen++;
      if (outPtr >= outBuf.length)
        outBuf = this._textBuffer.expandCurrentSegment(); 
      outBuf[outPtr++] = (char)ch;
      if (this._inputPtr >= this._inputEnd) {
        this._textBuffer.setCurrentLength(outPtr);
        this._expLength = expLen;
        return JsonToken.NOT_AVAILABLE;
      } 
      ch = getNextSignedByteFromBuffer();
    } 
    ch &= 0xFF;
    if (expLen == 0)
      _reportUnexpectedNumberChar(ch, "Exponent indicator not followed by a digit"); 
    this._inputPtr--;
    this._textBuffer.setCurrentLength(outPtr);
    this._expLength = expLen;
    return _valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
  }
  
  private final String _fastParseName() throws IOException {
    int[] codes = _icLatin1;
    int ptr = this._inputPtr;
    int q0 = getByteFromBuffer(ptr++) & 0xFF;
    if (codes[q0] == 0) {
      int i = getByteFromBuffer(ptr++) & 0xFF;
      if (codes[i] == 0) {
        int q = q0 << 8 | i;
        i = getByteFromBuffer(ptr++) & 0xFF;
        if (codes[i] == 0) {
          q = q << 8 | i;
          i = getByteFromBuffer(ptr++) & 0xFF;
          if (codes[i] == 0) {
            q = q << 8 | i;
            i = getByteFromBuffer(ptr++) & 0xFF;
            if (codes[i] == 0) {
              this._quad1 = q;
              return _parseMediumName(ptr, i);
            } 
            if (i == 34) {
              this._inputPtr = ptr;
              return _findName(q, 4);
            } 
            return null;
          } 
          if (i == 34) {
            this._inputPtr = ptr;
            return _findName(q, 3);
          } 
          return null;
        } 
        if (i == 34) {
          this._inputPtr = ptr;
          return _findName(q, 2);
        } 
        return null;
      } 
      if (i == 34) {
        this._inputPtr = ptr;
        return _findName(q0, 1);
      } 
      return null;
    } 
    if (q0 == 34) {
      this._inputPtr = ptr;
      return "";
    } 
    return null;
  }
  
  private final String _parseMediumName(int ptr, int q2) throws IOException {
    int[] codes = _icLatin1;
    int i = getByteFromBuffer(ptr++) & 0xFF;
    if (codes[i] == 0) {
      q2 = q2 << 8 | i;
      i = getByteFromBuffer(ptr++) & 0xFF;
      if (codes[i] == 0) {
        q2 = q2 << 8 | i;
        i = getByteFromBuffer(ptr++) & 0xFF;
        if (codes[i] == 0) {
          q2 = q2 << 8 | i;
          i = getByteFromBuffer(ptr++) & 0xFF;
          if (codes[i] == 0)
            return _parseMediumName2(ptr, i, q2); 
          if (i == 34) {
            this._inputPtr = ptr;
            return _findName(this._quad1, q2, 4);
          } 
          return null;
        } 
        if (i == 34) {
          this._inputPtr = ptr;
          return _findName(this._quad1, q2, 3);
        } 
        return null;
      } 
      if (i == 34) {
        this._inputPtr = ptr;
        return _findName(this._quad1, q2, 2);
      } 
      return null;
    } 
    if (i == 34) {
      this._inputPtr = ptr;
      return _findName(this._quad1, q2, 1);
    } 
    return null;
  }
  
  private final String _parseMediumName2(int ptr, int q3, int q2) throws IOException {
    int[] codes = _icLatin1;
    int i = getByteFromBuffer(ptr++) & 0xFF;
    if (codes[i] != 0) {
      if (i == 34) {
        this._inputPtr = ptr;
        return _findName(this._quad1, q2, q3, 1);
      } 
      return null;
    } 
    q3 = q3 << 8 | i;
    i = getByteFromBuffer(ptr++) & 0xFF;
    if (codes[i] != 0) {
      if (i == 34) {
        this._inputPtr = ptr;
        return _findName(this._quad1, q2, q3, 2);
      } 
      return null;
    } 
    q3 = q3 << 8 | i;
    i = getByteFromBuffer(ptr++) & 0xFF;
    if (codes[i] != 0) {
      if (i == 34) {
        this._inputPtr = ptr;
        return _findName(this._quad1, q2, q3, 3);
      } 
      return null;
    } 
    q3 = q3 << 8 | i;
    i = getByteFromBuffer(ptr++) & 0xFF;
    if (i == 34) {
      this._inputPtr = ptr;
      return _findName(this._quad1, q2, q3, 4);
    } 
    return null;
  }
  
  private final JsonToken _parseEscapedName(int qlen, int currQuad, int currQuadBytes) throws IOException {
    int[] quads = this._quadBuffer;
    int[] codes = _icLatin1;
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._quadLength = qlen;
        this._pending32 = currQuad;
        this._pendingBytes = currQuadBytes;
        this._minorState = 7;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getNextUnsignedByteFromBuffer();
      if (codes[ch] == 0) {
        if (currQuadBytes < 4) {
          currQuadBytes++;
          currQuad = currQuad << 8 | ch;
          continue;
        } 
        if (qlen >= quads.length)
          this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
        quads[qlen++] = currQuad;
        currQuad = ch;
        currQuadBytes = 1;
        continue;
      } 
      if (ch == 34)
        break; 
      if (ch != 92) {
        _throwUnquotedSpace(ch, "name");
      } else {
        ch = _decodeCharEscape();
        if (ch < 0) {
          this._minorState = 8;
          this._minorStateAfterSplit = 7;
          this._quadLength = qlen;
          this._pending32 = currQuad;
          this._pendingBytes = currQuadBytes;
          return this._currToken = JsonToken.NOT_AVAILABLE;
        } 
      } 
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      if (ch > 127) {
        if (currQuadBytes >= 4) {
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
            quads[qlen++] = currQuad;
            currQuad = 0;
            currQuadBytes = 0;
          } 
          currQuad = currQuad << 8 | 0x80 | ch >> 6 & 0x3F;
          currQuadBytes++;
        } 
        ch = 0x80 | ch & 0x3F;
      } 
      if (currQuadBytes < 4) {
        currQuadBytes++;
        currQuad = currQuad << 8 | ch;
        continue;
      } 
      quads[qlen++] = currQuad;
      currQuad = ch;
      currQuadBytes = 1;
    } 
    if (currQuadBytes > 0) {
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = _padLastQuad(currQuad, currQuadBytes);
    } else if (qlen == 0) {
      return _fieldComplete("");
    } 
    String name = this._symbols.findName(quads, qlen);
    if (name == null)
      name = _addName(quads, qlen, currQuadBytes); 
    return _fieldComplete(name);
  }
  
  private JsonToken _handleOddName(int ch) throws IOException {
    switch (ch) {
      case 35:
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) != 0)
          return _finishHashComment(4); 
        break;
      case 47:
        return _startSlashComment(4);
      case 39:
        if ((this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0)
          return _finishAposName(0, 0, 0); 
        break;
      case 93:
        return _closeArrayScope();
    } 
    if ((this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
      char c = (char)ch;
      _reportUnexpectedChar(c, "was expecting double-quote to start field name");
    } 
    int[] codes = CharTypes.getInputCodeUtf8JsNames();
    if (codes[ch] != 0)
      _reportUnexpectedChar(ch, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name"); 
    return _finishUnquotedName(0, ch, 1);
  }
  
  private JsonToken _finishUnquotedName(int qlen, int currQuad, int currQuadBytes) throws IOException {
    int[] quads = this._quadBuffer;
    int[] codes = CharTypes.getInputCodeUtf8JsNames();
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._quadLength = qlen;
        this._pending32 = currQuad;
        this._pendingBytes = currQuadBytes;
        this._minorState = 10;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getByteFromBuffer(this._inputPtr) & 0xFF;
      if (codes[ch] != 0)
        break; 
      this._inputPtr++;
      if (currQuadBytes < 4) {
        currQuadBytes++;
        currQuad = currQuad << 8 | ch;
        continue;
      } 
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = currQuad;
      currQuad = ch;
      currQuadBytes = 1;
    } 
    if (currQuadBytes > 0) {
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = currQuad;
    } 
    String name = this._symbols.findName(quads, qlen);
    if (name == null)
      name = _addName(quads, qlen, currQuadBytes); 
    return _fieldComplete(name);
  }
  
  private JsonToken _finishAposName(int qlen, int currQuad, int currQuadBytes) throws IOException {
    int[] quads = this._quadBuffer;
    int[] codes = _icLatin1;
    while (true) {
      if (this._inputPtr >= this._inputEnd) {
        this._quadLength = qlen;
        this._pending32 = currQuad;
        this._pendingBytes = currQuadBytes;
        this._minorState = 9;
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      int ch = getNextUnsignedByteFromBuffer();
      if (ch == 39)
        break; 
      if (ch != 34 && codes[ch] != 0) {
        if (ch != 92) {
          _throwUnquotedSpace(ch, "name");
        } else {
          ch = _decodeCharEscape();
          if (ch < 0) {
            this._minorState = 8;
            this._minorStateAfterSplit = 9;
            this._quadLength = qlen;
            this._pending32 = currQuad;
            this._pendingBytes = currQuadBytes;
            return this._currToken = JsonToken.NOT_AVAILABLE;
          } 
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
        continue;
      } 
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = currQuad;
      currQuad = ch;
      currQuadBytes = 1;
    } 
    if (currQuadBytes > 0) {
      if (qlen >= quads.length)
        this._quadBuffer = quads = _growNameDecodeBuffer(quads, quads.length); 
      quads[qlen++] = _padLastQuad(currQuad, currQuadBytes);
    } else if (qlen == 0) {
      return _fieldComplete("");
    } 
    String name = this._symbols.findName(quads, qlen);
    if (name == null)
      name = _addName(quads, qlen, currQuadBytes); 
    return _fieldComplete(name);
  }
  
  protected final JsonToken _finishFieldWithEscape() throws IOException {
    int ch = _decodeSplitEscaped(this._quoted32, this._quotedDigits);
    if (ch < 0) {
      this._minorState = 8;
      return JsonToken.NOT_AVAILABLE;
    } 
    if (this._quadLength >= this._quadBuffer.length)
      this._quadBuffer = _growNameDecodeBuffer(this._quadBuffer, 32); 
    int currQuad = this._pending32;
    int currQuadBytes = this._pendingBytes;
    if (ch > 127) {
      if (currQuadBytes >= 4) {
        this._quadBuffer[this._quadLength++] = currQuad;
        currQuad = 0;
        currQuadBytes = 0;
      } 
      if (ch < 2048) {
        currQuad = currQuad << 8 | 0xC0 | ch >> 6;
        currQuadBytes++;
      } else {
        currQuad = currQuad << 8 | 0xE0 | ch >> 12;
        if (++currQuadBytes >= 4) {
          this._quadBuffer[this._quadLength++] = currQuad;
          currQuad = 0;
          currQuadBytes = 0;
        } 
        currQuad = currQuad << 8 | 0x80 | ch >> 6 & 0x3F;
        currQuadBytes++;
      } 
      ch = 0x80 | ch & 0x3F;
    } 
    if (currQuadBytes < 4) {
      currQuadBytes++;
      currQuad = currQuad << 8 | ch;
    } else {
      this._quadBuffer[this._quadLength++] = currQuad;
      currQuad = ch;
      currQuadBytes = 1;
    } 
    if (this._minorStateAfterSplit == 9)
      return _finishAposName(this._quadLength, currQuad, currQuadBytes); 
    return _parseEscapedName(this._quadLength, currQuad, currQuadBytes);
  }
  
  private int _decodeSplitEscaped(int value, int bytesRead) throws IOException {
    if (this._inputPtr >= this._inputEnd) {
      this._quoted32 = value;
      this._quotedDigits = bytesRead;
      return -1;
    } 
    int c = getNextSignedByteFromBuffer();
    if (bytesRead == -1) {
      char ch;
      switch (c) {
        case 98:
          return 8;
        case 116:
          return 9;
        case 110:
          return 10;
        case 102:
          return 12;
        case 114:
          return 13;
        case 34:
        case 47:
        case 92:
          return c;
        case 117:
          break;
        default:
          ch = (char)c;
          return _handleUnrecognizedCharacterEscape(ch);
      } 
      if (this._inputPtr >= this._inputEnd) {
        this._quotedDigits = 0;
        this._quoted32 = 0;
        return -1;
      } 
      c = getNextSignedByteFromBuffer();
      bytesRead = 0;
    } 
    c &= 0xFF;
    while (true) {
      int digit = CharTypes.charToHex(c);
      if (digit < 0)
        _reportUnexpectedChar(c & 0xFF, "expected a hex-digit for character escape sequence"); 
      value = value << 4 | digit;
      if (++bytesRead == 4)
        return value; 
      if (this._inputPtr >= this._inputEnd) {
        this._quotedDigits = bytesRead;
        this._quoted32 = value;
        return -1;
      } 
      c = getNextUnsignedByteFromBuffer();
    } 
  }
  
  protected JsonToken _startString() throws IOException {
    int ptr = this._inputPtr;
    int outPtr = 0;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int[] codes = _icUTF8;
    int max = Math.min(this._inputEnd, ptr + outBuf.length);
    while (ptr < max) {
      int c = getByteFromBuffer(ptr) & 0xFF;
      if (codes[c] != 0) {
        if (c == 34) {
          this._inputPtr = ptr + 1;
          this._textBuffer.setCurrentLength(outPtr);
          return _valueComplete(JsonToken.VALUE_STRING);
        } 
        break;
      } 
      ptr++;
      outBuf[outPtr++] = (char)c;
    } 
    this._textBuffer.setCurrentLength(outPtr);
    this._inputPtr = ptr;
    return _finishRegularString();
  }
  
  private final JsonToken _finishRegularString() throws IOException {
    int[] codes = _icUTF8;
    char[] outBuf = this._textBuffer.getBufferWithoutReset();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    int ptr = this._inputPtr;
    int safeEnd = this._inputEnd - 5;
    while (true) {
      if (ptr >= this._inputEnd) {
        this._inputPtr = ptr;
        this._minorState = 40;
        this._textBuffer.setCurrentLength(outPtr);
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      int max = Math.min(this._inputEnd, ptr + outBuf.length - outPtr);
      while (ptr < max) {
        int c = getByteFromBuffer(ptr++) & 0xFF;
        if (codes[c] != 0) {
          if (c == 34) {
            this._inputPtr = ptr;
            this._textBuffer.setCurrentLength(outPtr);
            return _valueComplete(JsonToken.VALUE_STRING);
          } 
          if (ptr >= safeEnd) {
            this._inputPtr = ptr;
            this._textBuffer.setCurrentLength(outPtr);
            if (!_decodeSplitMultiByte(c, codes[c], (ptr < this._inputEnd))) {
              this._minorStateAfterSplit = 40;
              return this._currToken = JsonToken.NOT_AVAILABLE;
            } 
            outBuf = this._textBuffer.getBufferWithoutReset();
            outPtr = this._textBuffer.getCurrentSegmentSize();
            ptr = this._inputPtr;
            continue;
          } 
          switch (codes[c]) {
            case 1:
              this._inputPtr = ptr;
              c = _decodeFastCharEscape();
              ptr = this._inputPtr;
              break;
            case 2:
              c = _decodeUTF8_2(c, getByteFromBuffer(ptr++));
              break;
            case 3:
              c = _decodeUTF8_3(c, getByteFromBuffer(ptr++), getByteFromBuffer(ptr++));
              break;
            case 4:
              c = _decodeUTF8_4(c, getByteFromBuffer(ptr++), getByteFromBuffer(ptr++), 
                  getByteFromBuffer(ptr++));
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
          continue;
        } 
        outBuf[outPtr++] = (char)c;
      } 
    } 
  }
  
  protected JsonToken _startAposString() throws IOException {
    int ptr = this._inputPtr;
    int outPtr = 0;
    char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
    int[] codes = _icUTF8;
    int max = Math.min(this._inputEnd, ptr + outBuf.length);
    while (ptr < max) {
      int c = getByteFromBuffer(ptr) & 0xFF;
      if (c == 39) {
        this._inputPtr = ptr + 1;
        this._textBuffer.setCurrentLength(outPtr);
        return _valueComplete(JsonToken.VALUE_STRING);
      } 
      if (codes[c] != 0)
        break; 
      ptr++;
      outBuf[outPtr++] = (char)c;
    } 
    this._textBuffer.setCurrentLength(outPtr);
    this._inputPtr = ptr;
    return _finishAposString();
  }
  
  private final JsonToken _finishAposString() throws IOException {
    int[] codes = _icUTF8;
    char[] outBuf = this._textBuffer.getBufferWithoutReset();
    int outPtr = this._textBuffer.getCurrentSegmentSize();
    int ptr = this._inputPtr;
    int safeEnd = this._inputEnd - 5;
    while (true) {
      if (ptr >= this._inputEnd) {
        this._inputPtr = ptr;
        this._minorState = 45;
        this._textBuffer.setCurrentLength(outPtr);
        return this._currToken = JsonToken.NOT_AVAILABLE;
      } 
      if (outPtr >= outBuf.length) {
        outBuf = this._textBuffer.finishCurrentSegment();
        outPtr = 0;
      } 
      int max = Math.min(this._inputEnd, ptr + outBuf.length - outPtr);
      while (ptr < max) {
        int c = getByteFromBuffer(ptr++) & 0xFF;
        if (codes[c] != 0 && c != 34) {
          if (ptr >= safeEnd) {
            this._inputPtr = ptr;
            this._textBuffer.setCurrentLength(outPtr);
            if (!_decodeSplitMultiByte(c, codes[c], (ptr < this._inputEnd))) {
              this._minorStateAfterSplit = 45;
              return this._currToken = JsonToken.NOT_AVAILABLE;
            } 
            outBuf = this._textBuffer.getBufferWithoutReset();
            outPtr = this._textBuffer.getCurrentSegmentSize();
            ptr = this._inputPtr;
            continue;
          } 
          switch (codes[c]) {
            case 1:
              this._inputPtr = ptr;
              c = _decodeFastCharEscape();
              ptr = this._inputPtr;
              break;
            case 2:
              c = _decodeUTF8_2(c, getByteFromBuffer(ptr++));
              break;
            case 3:
              c = _decodeUTF8_3(c, getByteFromBuffer(ptr++), getByteFromBuffer(ptr++));
              break;
            case 4:
              c = _decodeUTF8_4(c, getByteFromBuffer(ptr++), getByteFromBuffer(ptr++), 
                  getByteFromBuffer(ptr++));
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
          continue;
        } 
        if (c == 39) {
          this._inputPtr = ptr;
          this._textBuffer.setCurrentLength(outPtr);
          return _valueComplete(JsonToken.VALUE_STRING);
        } 
        outBuf[outPtr++] = (char)c;
      } 
    } 
  }
  
  private final boolean _decodeSplitMultiByte(int c, int type, boolean gotNext) throws IOException {
    switch (type) {
      case 1:
        c = _decodeSplitEscaped(0, -1);
        if (c < 0) {
          this._minorState = 41;
          return false;
        } 
        this._textBuffer.append((char)c);
        return true;
      case 2:
        if (gotNext) {
          c = _decodeUTF8_2(c, getNextSignedByteFromBuffer());
          this._textBuffer.append((char)c);
          return true;
        } 
        this._minorState = 42;
        this._pending32 = c;
        return false;
      case 3:
        c &= 0xF;
        if (gotNext)
          return _decodeSplitUTF8_3(c, 1, getNextSignedByteFromBuffer()); 
        this._minorState = 43;
        this._pending32 = c;
        this._pendingBytes = 1;
        return false;
      case 4:
        c &= 0x7;
        if (gotNext)
          return _decodeSplitUTF8_4(c, 1, getNextSignedByteFromBuffer()); 
        this._pending32 = c;
        this._pendingBytes = 1;
        this._minorState = 44;
        return false;
    } 
    if (c < 32) {
      _throwUnquotedSpace(c, "string value");
    } else {
      _reportInvalidChar(c);
    } 
    this._textBuffer.append((char)c);
    return true;
  }
  
  private final boolean _decodeSplitUTF8_3(int prev, int prevCount, int next) throws IOException {
    if (prevCount == 1) {
      if ((next & 0xC0) != 128)
        _reportInvalidOther(next & 0xFF, this._inputPtr); 
      prev = prev << 6 | next & 0x3F;
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 43;
        this._pending32 = prev;
        this._pendingBytes = 2;
        return false;
      } 
      next = getNextSignedByteFromBuffer();
    } 
    if ((next & 0xC0) != 128)
      _reportInvalidOther(next & 0xFF, this._inputPtr); 
    this._textBuffer.append((char)(prev << 6 | next & 0x3F));
    return true;
  }
  
  private final boolean _decodeSplitUTF8_4(int prev, int prevCount, int next) throws IOException {
    if (prevCount == 1) {
      if ((next & 0xC0) != 128)
        _reportInvalidOther(next & 0xFF, this._inputPtr); 
      prev = prev << 6 | next & 0x3F;
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 44;
        this._pending32 = prev;
        this._pendingBytes = 2;
        return false;
      } 
      prevCount = 2;
      next = getNextSignedByteFromBuffer();
    } 
    if (prevCount == 2) {
      if ((next & 0xC0) != 128)
        _reportInvalidOther(next & 0xFF, this._inputPtr); 
      prev = prev << 6 | next & 0x3F;
      if (this._inputPtr >= this._inputEnd) {
        this._minorState = 44;
        this._pending32 = prev;
        this._pendingBytes = 3;
        return false;
      } 
      next = getNextSignedByteFromBuffer();
    } 
    if ((next & 0xC0) != 128)
      _reportInvalidOther(next & 0xFF, this._inputPtr); 
    int c = (prev << 6 | next & 0x3F) - 65536;
    this._textBuffer.append((char)(0xD800 | c >> 10));
    c = 0xDC00 | c & 0x3FF;
    this._textBuffer.append((char)c);
    return true;
  }
  
  private final int _decodeCharEscape() throws IOException {
    int left = this._inputEnd - this._inputPtr;
    if (left < 5)
      return _decodeSplitEscaped(0, -1); 
    return _decodeFastCharEscape();
  }
  
  private final int _decodeFastCharEscape() throws IOException {
    char c1;
    int c = getNextSignedByteFromBuffer();
    switch (c) {
      case 98:
        return 8;
      case 116:
        return 9;
      case 110:
        return 10;
      case 102:
        return 12;
      case 114:
        return 13;
      case 34:
      case 47:
      case 92:
        return (char)c;
      case 117:
        break;
      default:
        c1 = (char)c;
        return _handleUnrecognizedCharacterEscape(c1);
    } 
    int ch = getNextSignedByteFromBuffer();
    int digit = CharTypes.charToHex(ch);
    int result = digit;
    if (digit >= 0) {
      ch = getNextSignedByteFromBuffer();
      digit = CharTypes.charToHex(ch);
      if (digit >= 0) {
        result = result << 4 | digit;
        ch = getNextSignedByteFromBuffer();
        digit = CharTypes.charToHex(ch);
        if (digit >= 0) {
          result = result << 4 | digit;
          ch = getNextSignedByteFromBuffer();
          digit = CharTypes.charToHex(ch);
          if (digit >= 0)
            return result << 4 | digit; 
        } 
      } 
    } 
    _reportUnexpectedChar(ch & 0xFF, "expected a hex-digit for character escape sequence");
    return -1;
  }
  
  private final int _decodeUTF8_2(int c, int d) throws IOException {
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    return (c & 0x1F) << 6 | d & 0x3F;
  }
  
  private final int _decodeUTF8_3(int c, int d, int e) throws IOException {
    c &= 0xF;
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    c = c << 6 | d & 0x3F;
    if ((e & 0xC0) != 128)
      _reportInvalidOther(e & 0xFF, this._inputPtr); 
    return c << 6 | e & 0x3F;
  }
  
  private final int _decodeUTF8_4(int c, int d, int e, int f) throws IOException {
    if ((d & 0xC0) != 128)
      _reportInvalidOther(d & 0xFF, this._inputPtr); 
    c = (c & 0x7) << 6 | d & 0x3F;
    if ((e & 0xC0) != 128)
      _reportInvalidOther(e & 0xFF, this._inputPtr); 
    c = c << 6 | e & 0x3F;
    if ((f & 0xC0) != 128)
      _reportInvalidOther(f & 0xFF, this._inputPtr); 
    return (c << 6 | f & 0x3F) - 65536;
  }
}
