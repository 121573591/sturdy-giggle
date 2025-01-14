package org.tritonus.share.sampled.file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;
import org.tritonus.share.TDebug;

public abstract class TAudioFileReader extends AudioFileReader {
  private int m_nMarkLimit = -1;
  
  private boolean m_bRereading;
  
  protected TAudioFileReader(int nMarkLimit) {
    this(nMarkLimit, false);
  }
  
  protected TAudioFileReader(int nMarkLimit, boolean bRereading) {
    this.m_nMarkLimit = nMarkLimit;
    this.m_bRereading = bRereading;
  }
  
  private int getMarkLimit() {
    return this.m_nMarkLimit;
  }
  
  private boolean isRereading() {
    return this.m_bRereading;
  }
  
  public AudioFileFormat getAudioFileFormat(File file) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioFileFormat(File): begin (class: " + getClass().getSimpleName() + ")"); 
    long lFileLengthInBytes = file.length();
    InputStream inputStream = new FileInputStream(file);
    AudioFileFormat audioFileFormat = null;
    try {
      audioFileFormat = getAudioFileFormat(inputStream, lFileLengthInBytes);
    } finally {
      inputStream.close();
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioFileFormat(File): end"); 
    return audioFileFormat;
  }
  
  public AudioFileFormat getAudioFileFormat(URL url) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioFileFormat(URL): begin (class: " + getClass().getSimpleName() + ")"); 
    long lFileLengthInBytes = getDataLength(url);
    InputStream inputStream = url.openStream();
    AudioFileFormat audioFileFormat = null;
    try {
      audioFileFormat = getAudioFileFormat(inputStream, lFileLengthInBytes);
    } finally {
      inputStream.close();
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioFileFormat(URL): end"); 
    return audioFileFormat;
  }
  
  public AudioFileFormat getAudioFileFormat(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioFileFormat(InputStream): begin (class: " + getClass().getSimpleName() + ")"); 
    long lFileLengthInBytes = -1L;
    if (!inputStream.markSupported())
      inputStream = new BufferedInputStream(inputStream, getMarkLimit()); 
    inputStream.mark(getMarkLimit());
    AudioFileFormat audioFileFormat = null;
    try {
      audioFileFormat = getAudioFileFormat(inputStream, lFileLengthInBytes);
    } finally {
      inputStream.reset();
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioFileFormat(InputStream): end"); 
    return audioFileFormat;
  }
  
  protected abstract AudioFileFormat getAudioFileFormat(InputStream paramInputStream, long paramLong) throws UnsupportedAudioFileException, IOException;
  
  public AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(File): begin (class: " + getClass().getSimpleName() + ")"); 
    long lFileLengthInBytes = file.length();
    InputStream inputStream = new FileInputStream(file);
    AudioInputStream audioInputStream = null;
    try {
      audioInputStream = getAudioInputStream(inputStream, lFileLengthInBytes);
    } catch (UnsupportedAudioFileException e) {
      inputStream.close();
      throw e;
    } catch (IOException e) {
      inputStream.close();
      throw e;
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(File): end"); 
    return audioInputStream;
  }
  
  public AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(URL): begin (class: " + getClass().getSimpleName() + ")"); 
    long lFileLengthInBytes = getDataLength(url);
    InputStream inputStream = url.openStream();
    AudioInputStream audioInputStream = null;
    try {
      audioInputStream = getAudioInputStream(inputStream, lFileLengthInBytes);
    } catch (UnsupportedAudioFileException e) {
      inputStream.close();
      throw e;
    } catch (IOException e) {
      inputStream.close();
      throw e;
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(URL): end"); 
    return audioInputStream;
  }
  
  public AudioInputStream getAudioInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(InputStream): begin (class: " + getClass().getSimpleName() + ")"); 
    long lFileLengthInBytes = -1L;
    AudioInputStream audioInputStream = null;
    if (!inputStream.markSupported())
      inputStream = new BufferedInputStream(inputStream, getMarkLimit()); 
    inputStream.mark(getMarkLimit());
    try {
      audioInputStream = getAudioInputStream(inputStream, lFileLengthInBytes);
    } catch (UnsupportedAudioFileException e) {
      inputStream.reset();
      throw e;
    } catch (IOException e) {
      try {
        inputStream.reset();
      } catch (IOException e2) {
        if (e2.getCause() == null) {
          e2.initCause(e);
          throw e2;
        } 
      } 
      throw e;
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(InputStream): end"); 
    return audioInputStream;
  }
  
  protected AudioInputStream getAudioInputStream(InputStream inputStream, long lFileLengthInBytes) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(InputStream, long): begin (class: " + 
          getClass().getSimpleName() + ")"); 
    if (isRereading()) {
      if (!inputStream.markSupported())
        inputStream = new BufferedInputStream(inputStream, getMarkLimit()); 
      inputStream.mark(getMarkLimit());
    } 
    AudioFileFormat audioFileFormat = getAudioFileFormat(inputStream, lFileLengthInBytes);
    if (isRereading())
      inputStream.reset(); 
    AudioInputStream audioInputStream = new AudioInputStream(inputStream, audioFileFormat.getFormat(), audioFileFormat.getFrameLength());
    if (TDebug.TraceAudioFileReader)
      TDebug.out("TAudioFileReader.getAudioInputStream(InputStream, long): end"); 
    return audioInputStream;
  }
  
  protected static int calculateFrameSize(int nSampleSize, int nNumChannels) {
    return (nSampleSize + 7) / 8 * nNumChannels;
  }
  
  private static long getDataLength(URL url) throws IOException {
    long lFileLengthInBytes = -1L;
    URLConnection connection = url.openConnection();
    connection.connect();
    int nLength = connection.getContentLength();
    if (nLength > 0)
      lFileLengthInBytes = nLength; 
    return lFileLengthInBytes;
  }
  
  public static int readLittleEndianInt(InputStream is) throws IOException {
    int b0 = is.read();
    int b1 = is.read();
    int b2 = is.read();
    int b3 = is.read();
    if ((b0 | b1 | b2 | b3) < 0)
      throw new EOFException(); 
    return (b3 << 24) + (b2 << 16) + (b1 << 8) + (b0 << 0);
  }
  
  public static short readLittleEndianShort(InputStream is) throws IOException {
    int b0 = is.read();
    int b1 = is.read();
    if ((b0 | b1) < 0)
      throw new EOFException(); 
    return (short)((b1 << 8) + (b0 << 0));
  }
  
  public static double readIeeeExtended(DataInputStream dis) throws IOException {
    double f = 0.0D;
    int expon = 0;
    long hiMant = 0L;
    long loMant = 0L;
    double HUGE = 3.4028234663852886E38D;
    expon = dis.readUnsignedShort();
    long t1 = dis.readUnsignedShort();
    long t2 = dis.readUnsignedShort();
    hiMant = t1 << 16L | t2;
    t1 = dis.readUnsignedShort();
    t2 = dis.readUnsignedShort();
    loMant = t1 << 16L | t2;
    if (expon == 0 && hiMant == 0L && loMant == 0L) {
      f = 0.0D;
    } else if (expon == 32767) {
      f = HUGE;
    } else {
      expon -= 16383;
      expon -= 31;
      f = hiMant * Math.pow(2.0D, expon);
      expon -= 32;
      f += loMant * Math.pow(2.0D, expon);
    } 
    return f;
  }
}
