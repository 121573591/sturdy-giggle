package org.tritonus.share.sampled.file;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.AudioUtils;
import org.tritonus.share.sampled.TConversionTool;

public abstract class TAudioOutputStream implements AudioOutputStream {
  private AudioFormat m_audioFormat;
  
  private long m_lLength;
  
  private long m_lCalculatedLength;
  
  private TDataOutputStream m_dataOutputStream;
  
  private boolean m_bDoBackPatching;
  
  private boolean m_bHeaderWritten;
  
  private boolean m_doSignConversion;
  
  private boolean m_doEndianConversion;
  
  protected TAudioOutputStream(AudioFormat audioFormat, long lLength, TDataOutputStream dataOutputStream, boolean bDoBackPatching) {
    this.m_audioFormat = audioFormat;
    this.m_lLength = lLength;
    this.m_lCalculatedLength = 0L;
    this.m_dataOutputStream = dataOutputStream;
    this.m_bDoBackPatching = bDoBackPatching;
    this.m_bHeaderWritten = false;
  }
  
  protected void requireSign8bit(boolean signed) {
    if (this.m_audioFormat.getSampleSizeInBits() == 8 && AudioUtils.isPCM(this.m_audioFormat)) {
      boolean si = this.m_audioFormat.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED);
      this.m_doSignConversion = (signed != si);
    } 
  }
  
  protected void requireEndianness(boolean bigEndian) {
    int ssib = this.m_audioFormat.getSampleSizeInBits();
    if ((ssib == 16 || ssib == 24 || ssib == 32) && AudioUtils.isPCM(this.m_audioFormat))
      this.m_doEndianConversion = (bigEndian != this.m_audioFormat.isBigEndian()); 
  }
  
  public AudioFormat getFormat() {
    return this.m_audioFormat;
  }
  
  public long getLength() {
    return this.m_lLength;
  }
  
  public long getCalculatedLength() {
    return this.m_lCalculatedLength;
  }
  
  protected TDataOutputStream getDataOutputStream() {
    return this.m_dataOutputStream;
  }
  
  private void handleImplicitConversions(byte[] abData, int nOffset, int nLength) {
    if (this.m_doSignConversion)
      TConversionTool.convertSign8(abData, nOffset, nLength); 
    if (this.m_doEndianConversion)
      switch (this.m_audioFormat.getSampleSizeInBits()) {
        case 16:
          TConversionTool.swapOrder16(abData, nOffset, nLength / 2);
          break;
        case 24:
          TConversionTool.swapOrder24(abData, nOffset, nLength / 3);
          break;
        case 32:
          TConversionTool.swapOrder32(abData, nOffset, nLength / 4);
          break;
      }  
  }
  
  public int write(byte[] abData, int nOffset, int nLength) throws IOException {
    if (TDebug.TraceAudioOutputStream)
      TDebug.out("TAudioOutputStream.write(): wanted length: " + nLength); 
    if (!this.m_bHeaderWritten) {
      writeHeader();
      this.m_bHeaderWritten = true;
    } 
    long lTotalLength = getLength();
    if (lTotalLength != -1L && this.m_lCalculatedLength + nLength > lTotalLength) {
      if (TDebug.TraceAudioOutputStream)
        TDebug.out("TAudioOutputStream.write(): requested more bytes to write than possible."); 
      nLength = (int)(lTotalLength - this.m_lCalculatedLength);
      if (nLength < 0)
        nLength = 0; 
    } 
    if (nLength > 0) {
      handleImplicitConversions(abData, nOffset, nLength);
      this.m_dataOutputStream.write(abData, nOffset, nLength);
      this.m_lCalculatedLength += nLength;
      handleImplicitConversions(abData, nOffset, nLength);
    } 
    if (TDebug.TraceAudioOutputStream)
      TDebug.out("TAudioOutputStream.write(): calculated (total) length: " + this.m_lCalculatedLength + " bytes = " + (this.m_lCalculatedLength / getFormat().getFrameSize()) + " frames"); 
    return nLength;
  }
  
  protected abstract void writeHeader() throws IOException;
  
  public void close() throws IOException {
    if (TDebug.TraceAudioOutputStream)
      TDebug.out("TAudioOutputStream.close(): called"); 
    if (this.m_bDoBackPatching) {
      if (TDebug.TraceAudioOutputStream)
        TDebug.out("TAudioOutputStream.close(): patching header"); 
      patchHeader();
    } 
    this.m_dataOutputStream.close();
  }
  
  protected void patchHeader() throws IOException {
    TDebug.out("TAudioOutputStream.patchHeader(): called");
  }
  
  protected void setLengthFromCalculatedLength() {
    this.m_lLength = this.m_lCalculatedLength;
  }
}
