package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.async.ByteBufferFeeder;
import com.fasterxml.jackson.core.async.NonBlockingInputFeeder;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

public class NonBlockingByteBufferJsonParser extends NonBlockingUtf8JsonParserBase implements ByteBufferFeeder {
  private ByteBuffer _inputBuffer = ByteBuffer.wrap(NO_BYTES);
  
  public NonBlockingByteBufferJsonParser(IOContext ctxt, int parserFeatures, ByteQuadsCanonicalizer sym) {
    super(ctxt, parserFeatures, sym);
  }
  
  public NonBlockingInputFeeder getNonBlockingInputFeeder() {
    return (NonBlockingInputFeeder)this;
  }
  
  public void feedInput(ByteBuffer byteBuffer) throws IOException {
    if (this._inputPtr < this._inputEnd)
      _reportError("Still have %d undecoded bytes, should not call 'feedInput'", Integer.valueOf(this._inputEnd - this._inputPtr)); 
    int start = byteBuffer.position();
    int end = byteBuffer.limit();
    if (end < start)
      _reportError("Input end (%d) may not be before start (%d)", Integer.valueOf(end), Integer.valueOf(start)); 
    if (this._endOfInput)
      _reportError("Already closed, can not feed more input"); 
    this._currInputProcessed += this._origBufferLen;
    streamReadConstraints().validateDocumentLength(this._currInputProcessed);
    this._currInputRowStart = start - this._inputEnd - this._currInputRowStart;
    this._currBufferStart = start;
    this._inputBuffer = byteBuffer;
    this._inputPtr = start;
    this._inputEnd = end;
    this._origBufferLen = end - start;
  }
  
  public int releaseBuffered(OutputStream out) throws IOException {
    int avail = this._inputEnd - this._inputPtr;
    if (avail > 0) {
      WritableByteChannel channel = Channels.newChannel(out);
      channel.write(this._inputBuffer);
    } 
    return avail;
  }
  
  protected byte getNextSignedByteFromBuffer() {
    return this._inputBuffer.get(this._inputPtr++);
  }
  
  protected int getNextUnsignedByteFromBuffer() {
    return this._inputBuffer.get(this._inputPtr++) & 0xFF;
  }
  
  protected byte getByteFromBuffer(int ptr) {
    return this._inputBuffer.get(ptr);
  }
}
