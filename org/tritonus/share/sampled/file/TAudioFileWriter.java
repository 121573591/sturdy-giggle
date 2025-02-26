package org.tritonus.share.sampled.file;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.spi.AudioFileWriter;
import org.tritonus.share.ArraySet;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.AudioFormats;
import org.tritonus.share.sampled.AudioUtils;
import org.tritonus.share.sampled.TConversionTool;

public abstract class TAudioFileWriter extends AudioFileWriter {
  protected static final int ALL = -1;
  
  protected static final AudioFormat.Encoding PCM_SIGNED = AudioFormat.Encoding.PCM_SIGNED;
  
  protected static final AudioFormat.Encoding PCM_UNSIGNED = AudioFormat.Encoding.PCM_UNSIGNED;
  
  private static final int BUFFER_LENGTH = 16384;
  
  protected static final AudioFileFormat.Type[] NULL_TYPE_ARRAY = new AudioFileFormat.Type[0];
  
  private Collection<AudioFileFormat.Type> m_audioFileTypes;
  
  private Collection<AudioFormat> m_audioFormats;
  
  protected TAudioFileWriter(Collection<AudioFileFormat.Type> fileTypes, Collection<AudioFormat> audioFormats) {
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("TAudioFileWriter.<init>(): begin"); 
    this.m_audioFileTypes = fileTypes;
    this.m_audioFormats = audioFormats;
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("TAudioFileWriter.<init>(): end"); 
  }
  
  public AudioFileFormat.Type[] getAudioFileTypes() {
    return this.m_audioFileTypes.<AudioFileFormat.Type>toArray(NULL_TYPE_ARRAY);
  }
  
  public boolean isFileTypeSupported(AudioFileFormat.Type fileType) {
    return this.m_audioFileTypes.contains(fileType);
  }
  
  public AudioFileFormat.Type[] getAudioFileTypes(AudioInputStream audioInputStream) {
    AudioFormat format = audioInputStream.getFormat();
    ArraySet<AudioFileFormat.Type> res = new ArraySet();
    Iterator<AudioFileFormat.Type> it = this.m_audioFileTypes.iterator();
    while (it.hasNext()) {
      AudioFileFormat.Type thisType = it.next();
      if (isAudioFormatSupportedImpl(format, thisType))
        res.add(thisType); 
    } 
    return (AudioFileFormat.Type[])res.toArray((Object[])NULL_TYPE_ARRAY);
  }
  
  public boolean isFileTypeSupported(AudioFileFormat.Type fileType, AudioInputStream audioInputStream) {
    return (isFileTypeSupported(fileType) && (
      isAudioFormatSupportedImpl(audioInputStream.getFormat(), fileType) || 
      findConvertableFormat(audioInputStream.getFormat(), fileType) != null));
  }
  
  public int write(AudioInputStream audioInputStream, AudioFileFormat.Type fileType, File file) throws IOException {
    if (TDebug.TraceAudioFileWriter) {
      TDebug.out(">TAudioFileWriter.write(.., File): called");
      TDebug.out("class: " + getClass().getName());
    } 
    if (!isFileTypeSupported(fileType)) {
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("< file type is not supported"); 
      throw new IllegalArgumentException("file type is not supported.");
    } 
    AudioFormat inputFormat = audioInputStream.getFormat();
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("input format: " + inputFormat); 
    AudioFormat outputFormat = null;
    boolean bNeedsConversion = false;
    if (isAudioFormatSupportedImpl(inputFormat, fileType)) {
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("input format is supported directely"); 
      outputFormat = inputFormat;
      bNeedsConversion = false;
    } else {
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("input format is not supported directely; trying to find a convertable format"); 
      outputFormat = findConvertableFormat(inputFormat, fileType);
      if (outputFormat != null) {
        bNeedsConversion = true;
        if (outputFormat.getSampleSizeInBits() == 8 && outputFormat
          .getEncoding().equals(inputFormat.getEncoding()))
          bNeedsConversion = false; 
      } else {
        if (TDebug.TraceAudioFileWriter)
          TDebug.out("< input format is not supported and not convertable."); 
        throw new IllegalArgumentException("format not supported and not convertable");
      } 
    } 
    long lLengthInBytes = AudioUtils.getLengthInBytes(audioInputStream);
    TDataOutputStream dataOutputStream = new TSeekableDataOutputStream(file);
    AudioOutputStream audioOutputStream = getAudioOutputStream(outputFormat, lLengthInBytes, fileType, dataOutputStream);
    int written = writeImpl(audioInputStream, audioOutputStream, bNeedsConversion);
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("< wrote " + written + " bytes."); 
    return written;
  }
  
  public int write(AudioInputStream audioInputStream, AudioFileFormat.Type fileType, OutputStream outputStream) throws IOException {
    if (!isFileTypeSupported(fileType))
      throw new IllegalArgumentException("file type is not supported."); 
    if (TDebug.TraceAudioFileWriter) {
      TDebug.out(">TAudioFileWriter.write(.., OutputStream): called");
      TDebug.out("class: " + getClass().getName());
    } 
    AudioFormat inputFormat = audioInputStream.getFormat();
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("input format: " + inputFormat); 
    AudioFormat outputFormat = null;
    boolean bNeedsConversion = false;
    if (isAudioFormatSupportedImpl(inputFormat, fileType)) {
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("input format is supported directely"); 
      outputFormat = inputFormat;
      bNeedsConversion = false;
    } else {
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("input format is not supported directely; trying to find a convertable format"); 
      outputFormat = findConvertableFormat(inputFormat, fileType);
      if (outputFormat != null) {
        bNeedsConversion = true;
        if (outputFormat.getSampleSizeInBits() == 8 && outputFormat
          .getEncoding().equals(inputFormat.getEncoding()))
          bNeedsConversion = false; 
      } else {
        if (TDebug.TraceAudioFileWriter)
          TDebug.out("< format is not supported"); 
        throw new IllegalArgumentException("format not supported and not convertable");
      } 
    } 
    long lLengthInBytes = AudioUtils.getLengthInBytes(audioInputStream);
    TDataOutputStream dataOutputStream = new TNonSeekableDataOutputStream(outputStream);
    AudioOutputStream audioOutputStream = getAudioOutputStream(outputFormat, lLengthInBytes, fileType, dataOutputStream);
    int written = writeImpl(audioInputStream, audioOutputStream, bNeedsConversion);
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("< wrote " + written + " bytes."); 
    return written;
  }
  
  protected int writeImpl(AudioInputStream audioInputStream, AudioOutputStream audioOutputStream, boolean bNeedsConversion) throws IOException {
    if (TDebug.TraceAudioFileWriter) {
      TDebug.out(">TAudioFileWriter.writeImpl(): called");
      TDebug.out("class: " + getClass().getName());
    } 
    int nTotalWritten = 0;
    AudioFormat outputFormat = audioOutputStream.getFormat();
    int nBytesPerSample = outputFormat.getFrameSize() / outputFormat.getChannels();
    int nBufferSize = 16384 / outputFormat.getFrameSize() * outputFormat.getFrameSize();
    byte[] abBuffer = new byte[nBufferSize];
    while (true) {
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("trying to read (bytes): " + abBuffer.length); 
      int nBytesRead = audioInputStream.read(abBuffer);
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("read (bytes): " + nBytesRead); 
      if (nBytesRead == -1)
        break; 
      if (bNeedsConversion)
        TConversionTool.changeOrderOrSign(abBuffer, 0, nBytesRead, nBytesPerSample); 
      int nWritten = audioOutputStream.write(abBuffer, 0, nBytesRead);
      nTotalWritten += nWritten;
    } 
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("<TAudioFileWriter.writeImpl(): after main loop. Wrote " + nTotalWritten + " bytes"); 
    audioOutputStream.close();
    return nTotalWritten;
  }
  
  protected Iterator<AudioFormat> getSupportedAudioFormats(AudioFileFormat.Type fileType) {
    return this.m_audioFormats.iterator();
  }
  
  protected boolean isAudioFormatSupportedImpl(AudioFormat audioFormat, AudioFileFormat.Type fileType) {
    if (TDebug.TraceAudioFileWriter) {
      TDebug.out("> TAudioFileWriter.isAudioFormatSupportedImpl(): format to test: " + audioFormat);
      TDebug.out("class: " + getClass().getName());
    } 
    Iterator<AudioFormat> audioFormats = getSupportedAudioFormats(fileType);
    while (audioFormats.hasNext()) {
      AudioFormat handledFormat = audioFormats.next();
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("matching against format : " + handledFormat); 
      if (AudioFormats.matches(handledFormat, audioFormat)) {
        if (TDebug.TraceAudioFileWriter)
          TDebug.out("<...succeeded."); 
        return true;
      } 
    } 
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("< ... failed"); 
    return false;
  }
  
  protected abstract AudioOutputStream getAudioOutputStream(AudioFormat paramAudioFormat, long paramLong, AudioFileFormat.Type paramType, TDataOutputStream paramTDataOutputStream) throws IOException;
  
  private AudioFormat findConvertableFormat(AudioFormat inputFormat, AudioFileFormat.Type fileType) {
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("TAudioFileWriter.findConvertableFormat(): input format: " + inputFormat); 
    if (!isFileTypeSupported(fileType)) {
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("< input file type is not supported."); 
      return null;
    } 
    AudioFormat.Encoding inputEncoding = inputFormat.getEncoding();
    if ((inputEncoding.equals(PCM_SIGNED) || inputEncoding.equals(PCM_UNSIGNED)) && inputFormat
      .getSampleSizeInBits() == 8) {
      AudioFormat outputFormat = convertFormat(inputFormat, true, false);
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("trying output format: " + outputFormat); 
      if (isAudioFormatSupportedImpl(outputFormat, fileType)) {
        if (TDebug.TraceAudioFileWriter)
          TDebug.out("< ... succeeded"); 
        return outputFormat;
      } 
      outputFormat = convertFormat(inputFormat, false, true);
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("trying output format: " + outputFormat); 
      if (isAudioFormatSupportedImpl(outputFormat, fileType)) {
        if (TDebug.TraceAudioFileWriter)
          TDebug.out("< ... succeeded"); 
        return outputFormat;
      } 
      outputFormat = convertFormat(inputFormat, true, true);
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("trying output format: " + outputFormat); 
      if (isAudioFormatSupportedImpl(outputFormat, fileType)) {
        if (TDebug.TraceAudioFileWriter)
          TDebug.out("< ... succeeded"); 
        return outputFormat;
      } 
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("< ... failed"); 
      return null;
    } 
    if (inputEncoding.equals(PCM_SIGNED) && (inputFormat
      .getSampleSizeInBits() == 16 || inputFormat
      .getSampleSizeInBits() == 24 || inputFormat
      .getSampleSizeInBits() == 32)) {
      AudioFormat outputFormat = convertFormat(inputFormat, false, true);
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("trying output format: " + outputFormat); 
      if (isAudioFormatSupportedImpl(outputFormat, fileType)) {
        if (TDebug.TraceAudioFileWriter)
          TDebug.out("< ... succeeded"); 
        return outputFormat;
      } 
      if (TDebug.TraceAudioFileWriter)
        TDebug.out("< ... failed"); 
      return null;
    } 
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("< ... failed"); 
    return null;
  }
  
  private AudioFormat convertFormat(AudioFormat format, boolean changeSign, boolean changeEndian) {
    AudioFormat.Encoding enc = PCM_SIGNED;
    if (format.getEncoding().equals(PCM_UNSIGNED) != changeSign)
      enc = PCM_UNSIGNED; 
    return new AudioFormat(enc, format
        
        .getSampleRate(), format
        .getSampleSizeInBits(), format
        .getChannels(), format
        .getFrameSize(), format
        .getFrameRate(), format
        .isBigEndian() ^ changeEndian);
  }
}
