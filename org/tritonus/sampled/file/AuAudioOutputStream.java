package org.tritonus.sampled.file;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.file.TAudioOutputStream;
import org.tritonus.share.sampled.file.TDataOutputStream;

public class AuAudioOutputStream extends TAudioOutputStream {
  private static String description = "Created by Tritonus";
  
  protected static void writeText(TDataOutputStream dos, String s) throws IOException {
    if (s.length() > 0) {
      dos.writeBytes(s);
      dos.writeByte(0);
      if (s.length() % 2 == 0)
        dos.writeByte(0); 
    } 
  }
  
  protected static int getTextLength(String s) {
    if (s.length() == 0)
      return 0; 
    return s.length() + 2 & 0xFFFFFFFE;
  }
  
  public AuAudioOutputStream(AudioFormat audioFormat, long lLength, TDataOutputStream dataOutputStream) {
    super(audioFormat, (lLength > 2147483647L) ? -1L : lLength, dataOutputStream, dataOutputStream
        
        .supportsSeek());
    if (AuTool.getFormatCode(audioFormat) == 0)
      throw new IllegalArgumentException("Unknown encoding/format for AU file: " + audioFormat); 
    requireSign8bit(true);
    requireEndianness(true);
    if (TDebug.TraceAudioOutputStream)
      TDebug.out("Writing AU: " + audioFormat.getSampleSizeInBits() + " bits, " + audioFormat
          .getEncoding()); 
  }
  
  protected void writeHeader() throws IOException {
    if (TDebug.TraceAudioOutputStream)
      TDebug.out("AuAudioOutputStream.writeHeader(): called."); 
    AudioFormat format = getFormat();
    long lLength = getLength();
    TDataOutputStream dos = getDataOutputStream();
    if (TDebug.TraceAudioOutputStream) {
      TDebug.out("AuAudioOutputStream.writeHeader(): AudioFormat: " + format);
      TDebug.out("AuAudioOutputStream.writeHeader(): length: " + lLength);
    } 
    dos.writeInt(779316836);
    dos.writeInt(24 + getTextLength(description));
    dos.writeInt((lLength != -1L) ? (int)lLength : -1);
    dos.writeInt(AuTool.getFormatCode(format));
    dos.writeInt((int)format.getSampleRate());
    dos.writeInt(format.getChannels());
    writeText(dos, description);
  }
  
  protected void patchHeader() throws IOException {
    TDataOutputStream tdos = getDataOutputStream();
    tdos.seek(0L);
    setLengthFromCalculatedLength();
    writeHeader();
  }
}
