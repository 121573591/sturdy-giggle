package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.core.io.UTF32Reader;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public final class ByteSourceJsonBootstrapper {
  public static final byte UTF8_BOM_1 = -17;
  
  public static final byte UTF8_BOM_2 = -69;
  
  public static final byte UTF8_BOM_3 = -65;
  
  private static final int STRING_READER_BYTE_ARRAY_LENGTH_LIMIT = 8192;
  
  private final IOContext _context;
  
  private final InputStream _in;
  
  private final byte[] _inputBuffer;
  
  private int _inputPtr;
  
  private int _inputEnd;
  
  private final boolean _bufferRecyclable;
  
  private boolean _bigEndian = true;
  
  private int _bytesPerChar;
  
  public ByteSourceJsonBootstrapper(IOContext ctxt, InputStream in) {
    this._context = ctxt;
    this._in = in;
    this._inputBuffer = ctxt.allocReadIOBuffer();
    this._inputEnd = this._inputPtr = 0;
    this._bufferRecyclable = true;
  }
  
  public ByteSourceJsonBootstrapper(IOContext ctxt, byte[] inputBuffer, int inputStart, int inputLen) {
    this._context = ctxt;
    this._in = null;
    this._inputBuffer = inputBuffer;
    this._inputPtr = inputStart;
    this._inputEnd = inputStart + inputLen;
    this._bufferRecyclable = false;
  }
  
  public JsonEncoding detectEncoding() throws IOException {
    JsonEncoding enc;
    boolean foundEncoding = false;
    if (ensureLoaded(4)) {
      int quad = this._inputBuffer[this._inputPtr] << 24 | (this._inputBuffer[this._inputPtr + 1] & 0xFF) << 16 | (this._inputBuffer[this._inputPtr + 2] & 0xFF) << 8 | this._inputBuffer[this._inputPtr + 3] & 0xFF;
      if (handleBOM(quad)) {
        foundEncoding = true;
      } else if (checkUTF32(quad)) {
        foundEncoding = true;
      } else if (checkUTF16(quad >>> 16)) {
        foundEncoding = true;
      } 
    } else if (ensureLoaded(2)) {
      int i16 = (this._inputBuffer[this._inputPtr] & 0xFF) << 8 | this._inputBuffer[this._inputPtr + 1] & 0xFF;
      if (checkUTF16(i16))
        foundEncoding = true; 
    } 
    if (!foundEncoding) {
      enc = JsonEncoding.UTF8;
    } else {
      switch (this._bytesPerChar) {
        case 1:
          enc = JsonEncoding.UTF8;
          this._context.setEncoding(enc);
          return enc;
        case 2:
          enc = this._bigEndian ? JsonEncoding.UTF16_BE : JsonEncoding.UTF16_LE;
          this._context.setEncoding(enc);
          return enc;
        case 4:
          enc = this._bigEndian ? JsonEncoding.UTF32_BE : JsonEncoding.UTF32_LE;
          this._context.setEncoding(enc);
          return enc;
      } 
      return (JsonEncoding)VersionUtil.throwInternalReturnAny();
    } 
    this._context.setEncoding(enc);
    return enc;
  }
  
  public static int skipUTF8BOM(DataInput input) throws IOException {
    int b = input.readUnsignedByte();
    if (b != 239)
      return b; 
    b = input.readUnsignedByte();
    if (b != 187)
      throw new IOException("Unexpected byte 0x" + Integer.toHexString(b) + " following 0xEF; should get 0xBB as part of UTF-8 BOM"); 
    b = input.readUnsignedByte();
    if (b != 191)
      throw new IOException("Unexpected byte 0x" + Integer.toHexString(b) + " following 0xEF 0xBB; should get 0xBF as part of UTF-8 BOM"); 
    return input.readUnsignedByte();
  }
  
  public Reader constructReader() throws IOException {
    InputStream in;
    MergedStream mergedStream;
    JsonEncoding enc = this._context.getEncoding();
    switch (enc.bits()) {
      case 8:
      case 16:
        in = this._in;
        if (in == null) {
          int length = this._inputEnd - this._inputPtr;
          if (length <= 8192)
            return new StringReader(new String(this._inputBuffer, this._inputPtr, length, enc.getJavaName())); 
          in = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
        } else if (this._inputPtr < this._inputEnd) {
          mergedStream = new MergedStream(this._context, in, this._inputBuffer, this._inputPtr, this._inputEnd);
        } 
        return new InputStreamReader((InputStream)mergedStream, enc.getJavaName());
      case 32:
        return (Reader)new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context
            .getEncoding().isBigEndian());
    } 
    return (Reader)VersionUtil.throwInternalReturnAny();
  }
  
  public JsonParser constructParser(int parserFeatures, ObjectCodec codec, ByteQuadsCanonicalizer rootByteSymbols, CharsToNameCanonicalizer rootCharSymbols, int factoryFeatures) throws IOException {
    int prevInputPtr = this._inputPtr;
    JsonEncoding enc = JsonFactory.Feature.CHARSET_DETECTION.enabledIn(factoryFeatures) ? detectEncoding() : JsonEncoding.UTF8;
    int bytesProcessed = this._inputPtr - prevInputPtr;
    if (enc == JsonEncoding.UTF8)
      if (JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(factoryFeatures)) {
        ByteQuadsCanonicalizer can = rootByteSymbols.makeChild(factoryFeatures);
        return (JsonParser)new UTF8StreamJsonParser(this._context, parserFeatures, this._in, codec, can, this._inputBuffer, this._inputPtr, this._inputEnd, bytesProcessed, this._bufferRecyclable);
      }  
    return (JsonParser)new ReaderBasedJsonParser(this._context, parserFeatures, constructReader(), codec, rootCharSymbols
        .makeChild());
  }
  
  public static MatchStrength hasJSONFormat(InputAccessor acc) throws IOException {
    if (!acc.hasMoreBytes())
      return MatchStrength.INCONCLUSIVE; 
    byte b = acc.nextByte();
    if (b == -17) {
      if (!acc.hasMoreBytes())
        return MatchStrength.INCONCLUSIVE; 
      if (acc.nextByte() != -69)
        return MatchStrength.NO_MATCH; 
      if (!acc.hasMoreBytes())
        return MatchStrength.INCONCLUSIVE; 
      if (acc.nextByte() != -65)
        return MatchStrength.NO_MATCH; 
      if (!acc.hasMoreBytes())
        return MatchStrength.INCONCLUSIVE; 
      b = acc.nextByte();
    } 
    int ch = skipSpace(acc, b);
    if (ch < 0)
      return MatchStrength.INCONCLUSIVE; 
    if (ch == 123) {
      ch = skipSpace(acc);
      if (ch < 0)
        return MatchStrength.INCONCLUSIVE; 
      if (ch == 34 || ch == 125)
        return MatchStrength.SOLID_MATCH; 
      return MatchStrength.NO_MATCH;
    } 
    if (ch == 91) {
      ch = skipSpace(acc);
      if (ch < 0)
        return MatchStrength.INCONCLUSIVE; 
      if (ch == 93 || ch == 91)
        return MatchStrength.SOLID_MATCH; 
      return MatchStrength.SOLID_MATCH;
    } 
    MatchStrength strength = MatchStrength.WEAK_MATCH;
    if (ch == 34)
      return strength; 
    if (ch <= 57 && ch >= 48)
      return strength; 
    if (ch == 45) {
      ch = skipSpace(acc);
      if (ch < 0)
        return MatchStrength.INCONCLUSIVE; 
      return (ch <= 57 && ch >= 48) ? strength : MatchStrength.NO_MATCH;
    } 
    if (ch == 110)
      return tryMatch(acc, "ull", strength); 
    if (ch == 116)
      return tryMatch(acc, "rue", strength); 
    if (ch == 102)
      return tryMatch(acc, "alse", strength); 
    return MatchStrength.NO_MATCH;
  }
  
  private static MatchStrength tryMatch(InputAccessor acc, String matchStr, MatchStrength fullMatchStrength) throws IOException {
    for (int i = 0, len = matchStr.length(); i < len; i++) {
      if (!acc.hasMoreBytes())
        return MatchStrength.INCONCLUSIVE; 
      if (acc.nextByte() != matchStr.charAt(i))
        return MatchStrength.NO_MATCH; 
    } 
    return fullMatchStrength;
  }
  
  private static int skipSpace(InputAccessor acc) throws IOException {
    if (!acc.hasMoreBytes())
      return -1; 
    return skipSpace(acc, acc.nextByte());
  }
  
  private static int skipSpace(InputAccessor acc, byte b) throws IOException {
    while (true) {
      int ch = b & 0xFF;
      if (ch != 32 && ch != 13 && ch != 10 && ch != 9)
        return ch; 
      if (!acc.hasMoreBytes())
        return -1; 
      b = acc.nextByte();
    } 
  }
  
  private boolean handleBOM(int quad) throws IOException {
    switch (quad) {
      case 65279:
        this._bigEndian = true;
        this._inputPtr += 4;
        this._bytesPerChar = 4;
        return true;
      case -131072:
        this._inputPtr += 4;
        this._bytesPerChar = 4;
        this._bigEndian = false;
        return true;
      case 65534:
        reportWeirdUCS4("2143");
        break;
      case -16842752:
        reportWeirdUCS4("3412");
        break;
    } 
    int msw = quad >>> 16;
    if (msw == 65279) {
      this._inputPtr += 2;
      this._bytesPerChar = 2;
      this._bigEndian = true;
      return true;
    } 
    if (msw == 65534) {
      this._inputPtr += 2;
      this._bytesPerChar = 2;
      this._bigEndian = false;
      return true;
    } 
    if (quad >>> 8 == 15711167) {
      this._inputPtr += 3;
      this._bytesPerChar = 1;
      this._bigEndian = true;
      return true;
    } 
    return false;
  }
  
  private boolean checkUTF32(int quad) throws IOException {
    if (quad >> 8 == 0) {
      this._bigEndian = true;
    } else if ((quad & 0xFFFFFF) == 0) {
      this._bigEndian = false;
    } else if ((quad & 0xFF00FFFF) == 0) {
      reportWeirdUCS4("3412");
    } else if ((quad & 0xFFFF00FF) == 0) {
      reportWeirdUCS4("2143");
    } else {
      return false;
    } 
    this._bytesPerChar = 4;
    return true;
  }
  
  private boolean checkUTF16(int i16) {
    if ((i16 & 0xFF00) == 0) {
      this._bigEndian = true;
    } else if ((i16 & 0xFF) == 0) {
      this._bigEndian = false;
    } else {
      return false;
    } 
    this._bytesPerChar = 2;
    return true;
  }
  
  private void reportWeirdUCS4(String type) throws IOException {
    throw new CharConversionException("Unsupported UCS-4 endianness (" + type + ") detected");
  }
  
  protected boolean ensureLoaded(int minimum) throws IOException {
    int gotten = this._inputEnd - this._inputPtr;
    while (gotten < minimum) {
      int count;
      if (this._in == null) {
        count = -1;
      } else {
        count = this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
      } 
      if (count < 1)
        return false; 
      this._inputEnd += count;
      gotten += count;
    } 
    return true;
  }
}
