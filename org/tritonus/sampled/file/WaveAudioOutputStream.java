package org.tritonus.sampled.file;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.file.TAudioOutputStream;
import org.tritonus.share.sampled.file.TDataOutputStream;

public class WaveAudioOutputStream extends TAudioOutputStream {
  private static final int LENGTH_NOT_KNOWN = -1;
  
  public WaveAudioOutputStream(AudioFormat audioFormat, long lLength, TDataOutputStream dataOutputStream) {
    super(audioFormat, lLength, dataOutputStream, dataOutputStream
        
        .supportsSeek());
    if (lLength != -1L && lLength + 46L > 4294967295L) {
      if (TDebug.TraceAudioOutputStream)
        TDebug.out("WaveAudioOutputStream: Length exceeds 4GB: " + lLength + "=0x" + 
            Long.toHexString(lLength) + " with header=" + (lLength + 46L) + "=0x" + 
            
            Long.toHexString(lLength + 46L)); 
      throw new IllegalArgumentException("Wave files cannot be larger than 4GB.");
    } 
    if (WaveTool.getFormatCode(getFormat()) == 0)
      throw new IllegalArgumentException("Unknown encoding/format for WAVE file: " + audioFormat); 
    requireSign8bit(false);
    requireEndianness(false);
    if (TDebug.TraceAudioOutputStream)
      TDebug.out("Writing WAVE: " + audioFormat.getSampleSizeInBits() + " bits, " + audioFormat.getEncoding()); 
  }
  
  protected void writeHeader() throws IOException {
    if (TDebug.TraceAudioOutputStream)
      TDebug.out("WaveAudioOutputStream.writeHeader()"); 
    int formatCode = WaveTool.getFormatCode(getFormat());
    AudioFormat format = getFormat();
    long lLength = getLength();
    int formatChunkAdd = 0;
    if (formatCode == 49)
      formatChunkAdd += 2; 
    int dataOffset = 46 + formatChunkAdd;
    if (formatCode != 1)
      dataOffset += 12; 
    if (lLength != -1L && lLength + dataOffset > 4294967295L)
      lLength = 4294967295L - dataOffset; 
    long lDataChunkSize = lLength + lLength % 2L;
    if (lLength == -1L || lDataChunkSize > 4294967295L)
      lDataChunkSize = 4294967295L; 
    long RIFF_Size = lDataChunkSize + dataOffset - 8L;
    if (lLength == -1L || RIFF_Size > 4294967295L)
      RIFF_Size = 4294967295L; 
    TDataOutputStream dos = getDataOutputStream();
    dos.writeInt(1380533830);
    dos.writeLittleEndian32((int)RIFF_Size);
    dos.writeInt(1463899717);
    int formatChunkSize = 18 + formatChunkAdd;
    short sampleSizeInBits = (short)format.getSampleSizeInBits();
    int decodedSamplesPerBlock = 1;
    if (formatCode == 49) {
      if (format.getFrameSize() == 33) {
        decodedSamplesPerBlock = 320;
      } else if (format.getFrameSize() == 65) {
        decodedSamplesPerBlock = 320;
      } else {
        decodedSamplesPerBlock = (int)(format.getFrameSize() * 4.923077F);
      } 
      sampleSizeInBits = 0;
    } 
    int avgBytesPerSec = (int)format.getSampleRate() / decodedSamplesPerBlock * format.getFrameSize();
    dos.writeInt(1718449184);
    dos.writeLittleEndian32(formatChunkSize);
    dos.writeLittleEndian16((short)formatCode);
    dos.writeLittleEndian16((short)format.getChannels());
    dos.writeLittleEndian32((int)format.getSampleRate());
    dos.writeLittleEndian32(avgBytesPerSec);
    dos.writeLittleEndian16((short)format.getFrameSize());
    dos.writeLittleEndian16(sampleSizeInBits);
    dos.writeLittleEndian16((short)formatChunkAdd);
    if (formatCode == 49)
      dos.writeLittleEndian16((short)decodedSamplesPerBlock); 
    if (formatCode != 1) {
      long samples = 0L;
      if (lLength != -1L)
        samples = lLength / format.getFrameSize() * decodedSamplesPerBlock; 
      if (samples > 4294967295L)
        samples = 4294967295L / decodedSamplesPerBlock * decodedSamplesPerBlock; 
      dos.writeInt(1717658484);
      dos.writeLittleEndian32(4);
      dos.writeLittleEndian32((int)(samples & 0xFFFFFFFFFFFFFFFFL));
    } 
    dos.writeInt(1684108385);
    dos.writeLittleEndian32((lLength != -1L) ? (int)lLength : -1);
  }
  
  protected void patchHeader() throws IOException {
    TDataOutputStream tdos = getDataOutputStream();
    tdos.seek(0L);
    setLengthFromCalculatedLength();
    writeHeader();
  }
  
  public void close() throws IOException {
    long nBytesWritten = getCalculatedLength();
    if (nBytesWritten % 2L == 1L) {
      if (TDebug.TraceAudioOutputStream)
        TDebug.out("WaveOutputStream.close(): adding padding byte"); 
      TDataOutputStream tdos = getDataOutputStream();
      tdos.writeByte(0);
    } 
    super.close();
  }
}
