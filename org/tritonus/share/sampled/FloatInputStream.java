package org.tritonus.share.sampled;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class FloatInputStream extends AudioInputStream implements FloatSampleInput {
  private InputStream sourceStream;
  
  private FloatSampleInput sourceInput;
  
  private boolean eofReached = false;
  
  private byte[] tempBuffer = null;
  
  private FloatSampleBuffer tempFloatBuffer;
  
  public FloatInputStream(AudioInputStream sourceStream) {
    super(sourceStream, sourceStream.getFormat(), sourceStream
        .getFrameLength());
    this.tempFloatBuffer = null;
    this.sourceStream = sourceStream;
    init();
  }
  
  public FloatInputStream(InputStream sourceStream, AudioFormat format, long frameLength) {
    super(sourceStream, format, frameLength);
    this.tempFloatBuffer = null;
    this.sourceStream = sourceStream;
    init();
  }
  
  public FloatInputStream(FloatSampleInput sourceInput, AudioFormat format, long frameLength) {
    super(new ByteArrayInputStream(new byte[0]), format, frameLength);
    this.tempFloatBuffer = null;
    this.sourceStream = null;
    this.sourceInput = sourceInput;
    init();
  }
  
  public void read(FloatSampleBuffer outBuffer) {
    read(outBuffer, 0, outBuffer.getSampleCount());
  }
  
  private void init() {
    if (this.sourceStream != null && this.sourceStream instanceof FloatSampleInput)
      this.sourceInput = (FloatSampleInput)this.sourceStream; 
    FloatSampleBuffer.checkFormatSupported(this.format);
  }
  
  public void read(FloatSampleBuffer buffer, int offset, int sampleCount) {
    if (sampleCount == 0 || isDone()) {
      buffer.setSampleCount(offset, true);
      return;
    } 
    if (buffer.getChannelCount() != getChannels())
      throw new IllegalArgumentException("read: passed buffer has different channel count"); 
    if (this.sourceInput != null) {
      this.sourceInput.read(buffer, offset, sampleCount);
    } else {
      int byteBufferSize = buffer.getSampleCount() * getFormat().getFrameSize();
      byte[] lTempBuffer = this.tempBuffer;
      if (lTempBuffer == null || byteBufferSize > lTempBuffer.length) {
        lTempBuffer = new byte[byteBufferSize];
        this.tempBuffer = lTempBuffer;
      } 
      int readSamples = 0;
      int byteOffset = 0;
      while (readSamples < sampleCount) {
        int readBytes;
        try {
          readBytes = this.sourceStream.read(lTempBuffer, byteOffset, byteBufferSize);
        } catch (IOException ioe) {
          readBytes = -1;
        } 
        if (readBytes < 0) {
          this.eofReached = true;
          readBytes = 0;
          break;
        } 
        if (readBytes == 0) {
          Thread.yield();
          continue;
        } 
        readSamples += readBytes / getFormat().getFrameSize();
        byteBufferSize -= readBytes;
        byteOffset += readBytes;
      } 
      buffer.setSampleCount(offset + readSamples, (offset > 0));
      if (readSamples > 0)
        buffer.setSamplesFromBytes(lTempBuffer, 0, getFormat(), offset, readSamples); 
    } 
  }
  
  public int getChannels() {
    return getFormat().getChannels();
  }
  
  public float getSampleRate() {
    return getFormat().getSampleRate();
  }
  
  public boolean isDone() {
    if (!this.eofReached && this.sourceInput != null)
      return this.sourceInput.isDone(); 
    return this.eofReached;
  }
  
  public int read() throws IOException {
    if (getFormat().getFrameSize() != 1)
      throw new IOException("frame size must be 1 to read a single byte"); 
    byte[] temp = new byte[1];
    int result = read(temp);
    if (result <= 0)
      return -1; 
    return temp[0] & 0xFF;
  }
  
  public int read(byte[] abData) throws IOException {
    return read(abData, 0, abData.length);
  }
  
  public int read(byte[] abData, int nOffset, int nLength) throws IOException {
    if (isDone())
      return -1; 
    if (this.sourceStream != null)
      return readBytesFromInputStream(abData, nOffset, nLength); 
    return readBytesFromFloatInput(abData, nOffset, nLength);
  }
  
  protected int readBytesFromInputStream(byte[] abData, int nOffset, int nLength) throws IOException {
    int readBytes = this.sourceStream.read(abData, nOffset, nLength);
    if (readBytes < 0)
      this.eofReached = true; 
    return readBytes;
  }
  
  protected int readBytesFromFloatInput(byte[] abData, int nOffset, int nLength) throws IOException {
    FloatSampleInput lInput = this.sourceInput;
    if (lInput.isDone())
      return -1; 
    int frameCount = nLength / getFormat().getFrameSize();
    FloatSampleBuffer lTempBuffer = this.tempFloatBuffer;
    if (lTempBuffer == null) {
      lTempBuffer = new FloatSampleBuffer(getFormat().getChannels(), frameCount, getFormat().getSampleRate());
      this.tempFloatBuffer = lTempBuffer;
    } else {
      lTempBuffer.setSampleCount(frameCount, false);
    } 
    lInput.read(lTempBuffer);
    if (lInput.isDone())
      return -1; 
    if (abData != null) {
      int writtenBytes = this.tempFloatBuffer.convertToByteArray(abData, nOffset, 
          getFormat());
      return writtenBytes;
    } 
    return frameCount * getFormat().getFrameSize();
  }
  
  public synchronized long skip(long nSkip) throws IOException {
    long skipFrames = nSkip / getFormat().getFrameSize();
    if (this.sourceStream != null)
      return this.sourceStream.skip(skipFrames * getFormat().getFrameSize()); 
    if (isDone() || skipFrames <= 0L)
      return 0L; 
    return readBytesFromFloatInput((byte[])null, 0, 
        (int)(skipFrames * getFormat().getFrameSize()));
  }
  
  public int available() throws IOException {
    if (this.sourceStream != null)
      return this.sourceStream.available(); 
    return -1;
  }
  
  public void mark(int readlimit) {
    if (this.sourceStream != null)
      this.sourceStream.mark(readlimit); 
  }
  
  public void reset() throws IOException {
    if (this.sourceStream != null)
      this.sourceStream.reset(); 
  }
  
  public boolean markSupported() {
    if (this.sourceStream != null)
      return this.sourceStream.markSupported(); 
    return false;
  }
  
  public void close() throws IOException {
    if (this.eofReached)
      return; 
    this.eofReached = true;
    if (this.sourceStream != null)
      this.sourceStream.close(); 
    this.tempBuffer = null;
    this.tempFloatBuffer = null;
  }
}
