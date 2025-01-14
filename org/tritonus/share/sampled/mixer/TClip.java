package org.tritonus.share.sampled.mixer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import org.tritonus.share.TDebug;

public class TClip extends TDataLine implements Clip {
  public TClip(DataLine.Info info) {
    super((TMixer)null, info);
  }
  
  public TClip(DataLine.Info info, Collection<Control> controls) {
    super((TMixer)null, info, controls);
  }
  
  public void open(AudioFormat audioFormat, byte[] abData, int nOffset, int nLength) throws LineUnavailableException {
    ByteArrayInputStream bais = new ByteArrayInputStream(abData, nOffset, nLength);
    AudioInputStream audioInputStream = new AudioInputStream(bais, audioFormat, -1L);
    try {
      open(audioInputStream);
    } catch (IOException e) {
      if (TDebug.TraceAllExceptions)
        TDebug.out(e); 
      throw new LineUnavailableException("IOException occured");
    } 
  }
  
  public void open(AudioInputStream audioInputStream) throws LineUnavailableException, IOException {
    AudioFormat audioFormat = audioInputStream.getFormat();
    DataLine.Info info = new DataLine.Info(Clip.class, audioFormat, -1);
    setLineInfo(info);
  }
  
  public int getFrameLength() {
    return -1;
  }
  
  public long getMicrosecondLength() {
    return -1L;
  }
  
  public void setFramePosition(int nPosition) {}
  
  public void setMicrosecondPosition(long lPosition) {}
  
  public int getFramePosition() {
    return -1;
  }
  
  public long getMicrosecondPosition() {
    return -1L;
  }
  
  public void setLoopPoints(int nStart, int nEnd) {}
  
  public void loop(int nCount) {
    if (TDebug.TraceClip)
      TDebug.out("TClip.loop(int): called; count = " + nCount); 
    if (nCount == 0) {
      if (TDebug.TraceClip)
        TDebug.out("TClip.loop(int): starting sample (once)"); 
    } else if (TDebug.TraceClip) {
      TDebug.out("TClip.loop(int): starting sample (forever)");
    } 
  }
  
  public void flush() {}
  
  public void drain() {}
  
  public void close() {}
  
  public void open() {}
  
  public void start() {
    if (TDebug.TraceClip)
      TDebug.out("TClip.start(): called"); 
    if (TDebug.TraceClip)
      TDebug.out("TClip.start(): calling 'loop(0)' [hack]"); 
    loop(0);
  }
  
  public void stop() {}
  
  public int available() {
    return -1;
  }
}
