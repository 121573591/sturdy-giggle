package org.tritonus.share.sampled.mixer;

import java.util.Collection;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import org.tritonus.share.TDebug;

public abstract class TDataLine extends TLine implements DataLine {
  private static final int DEFAULT_BUFFER_SIZE = 128000;
  
  private AudioFormat m_format;
  
  private int m_nBufferSize;
  
  private boolean m_bRunning;
  
  public TDataLine(TMixer mixer, DataLine.Info info) {
    super(mixer, info);
    init(info);
  }
  
  public TDataLine(TMixer mixer, DataLine.Info info, Collection<Control> controls) {
    super(mixer, info, controls);
    init(info);
  }
  
  private void init(DataLine.Info info) {
    this.m_format = null;
    this.m_nBufferSize = -1;
    setRunning(false);
  }
  
  public void start() {
    if (TDebug.TraceSourceDataLine)
      TDebug.out("TDataLine.start(): called"); 
    setRunning(true);
  }
  
  public void stop() {
    if (TDebug.TraceSourceDataLine)
      TDebug.out("TDataLine.stop(): called"); 
    setRunning(false);
  }
  
  public boolean isRunning() {
    return this.m_bRunning;
  }
  
  protected void setRunning(boolean bRunning) {
    boolean bOldValue = isRunning();
    this.m_bRunning = bRunning;
    if (bOldValue != isRunning())
      if (isRunning()) {
        startImpl();
        notifyLineEvent(LineEvent.Type.START);
      } else {
        stopImpl();
        notifyLineEvent(LineEvent.Type.STOP);
      }  
  }
  
  protected void startImpl() {}
  
  protected void stopImpl() {}
  
  public boolean isActive() {
    return isRunning();
  }
  
  public AudioFormat getFormat() {
    return this.m_format;
  }
  
  protected void setFormat(AudioFormat format) {
    if (TDebug.TraceDataLine)
      TDebug.out("TDataLine.setFormat(): setting: " + format); 
    this.m_format = format;
  }
  
  public int getBufferSize() {
    return this.m_nBufferSize;
  }
  
  protected void setBufferSize(int nBufferSize) {
    if (TDebug.TraceDataLine)
      TDebug.out("TDataLine.setBufferSize(): setting: " + nBufferSize); 
    this.m_nBufferSize = nBufferSize;
  }
  
  public int getFramePosition() {
    return -1;
  }
  
  public long getLongFramePosition() {
    return -1L;
  }
  
  public long getMicrosecondPosition() {
    return (long)(getFramePosition() * getFormat().getFrameRate() * 1000000.0F);
  }
  
  public float getLevel() {
    return -1.0F;
  }
  
  protected void checkOpen() {
    if (getFormat() == null)
      throw new IllegalStateException("format must be specified"); 
    if (getBufferSize() == -1)
      setBufferSize(getDefaultBufferSize()); 
  }
  
  protected int getDefaultBufferSize() {
    return 128000;
  }
  
  protected void notifyLineEvent(LineEvent.Type type) {
    notifyLineEvent(new LineEvent(this, type, getFramePosition()));
  }
}
