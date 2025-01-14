package org.tritonus.share.sampled.convert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.TCircularBuffer;
import org.tritonus.share.TDebug;

public abstract class TAsynchronousFilteredAudioInputStream extends TAudioInputStream implements TCircularBuffer.Trigger {
  private static final int DEFAULT_BUFFER_SIZE = 327670;
  
  private static final int DEFAULT_MIN_AVAILABLE = 4096;
  
  private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  
  protected TCircularBuffer m_circularBuffer;
  
  private int m_nMinAvailable;
  
  private byte[] m_abSingleByte;
  
  public TAsynchronousFilteredAudioInputStream(AudioFormat outputFormat, long lLength) {
    this(outputFormat, lLength, 327670, 4096);
  }
  
  public TAsynchronousFilteredAudioInputStream(AudioFormat outputFormat, long lLength, int nBufferSize, int nMinAvailable) {
    super(new ByteArrayInputStream(EMPTY_BYTE_ARRAY), outputFormat, lLength);
    if (TDebug.TraceAudioConverter)
      TDebug.out("TAsynchronousFilteredAudioInputStream.<init>(): begin"); 
    this.m_circularBuffer = new TCircularBuffer(nBufferSize, false, true, this);
    this.m_nMinAvailable = nMinAvailable;
    if (TDebug.TraceAudioConverter)
      TDebug.out("TAsynchronousFilteredAudioInputStream.<init>(): end"); 
  }
  
  protected TCircularBuffer getCircularBuffer() {
    return this.m_circularBuffer;
  }
  
  protected boolean writeMore() {
    return (getCircularBuffer().availableWrite() > this.m_nMinAvailable);
  }
  
  public int read() throws IOException {
    int nByte = -1;
    if (this.m_abSingleByte == null)
      this.m_abSingleByte = new byte[1]; 
    int nReturn = read(this.m_abSingleByte);
    if (nReturn == -1) {
      nByte = -1;
    } else {
      nByte = this.m_abSingleByte[0] & 0xFF;
    } 
    return nByte;
  }
  
  public int read(byte[] abData) throws IOException {
    if (TDebug.TraceAudioConverter)
      TDebug.out("TAsynchronousFilteredAudioInputStream.read(byte[]): begin"); 
    int nRead = read(abData, 0, abData.length);
    if (TDebug.TraceAudioConverter)
      TDebug.out("TAsynchronousFilteredAudioInputStream.read(byte[]): end"); 
    return nRead;
  }
  
  public int read(byte[] abData, int nOffset, int nLength) throws IOException {
    if (TDebug.TraceAudioConverter)
      TDebug.out("TAsynchronousFilteredAudioInputStream.read(byte[], int, int): begin"); 
    int nRead = this.m_circularBuffer.read(abData, nOffset, nLength);
    if (TDebug.TraceAudioConverter)
      TDebug.out("TAsynchronousFilteredAudioInputStream.read(byte[], int, int): end"); 
    return nRead;
  }
  
  public long skip(long lSkip) throws IOException {
    for (long lSkipped = 0L; lSkipped < lSkip; lSkipped++) {
      int nReturn = read();
      if (nReturn == -1)
        return lSkipped; 
    } 
    return lSkip;
  }
  
  public int available() throws IOException {
    return this.m_circularBuffer.availableRead();
  }
  
  public void close() throws IOException {
    this.m_circularBuffer.close();
  }
  
  public boolean markSupported() {
    return false;
  }
  
  public void mark(int nReadLimit) {}
  
  public void reset() throws IOException {
    throw new IOException("mark not supported");
  }
}
