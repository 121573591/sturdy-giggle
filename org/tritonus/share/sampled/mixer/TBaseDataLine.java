package org.tritonus.share.sampled.mixer;

import java.util.Collection;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import org.tritonus.share.TDebug;

public abstract class TBaseDataLine extends TDataLine {
  public TBaseDataLine(TMixer mixer, DataLine.Info info) {
    super(mixer, info);
  }
  
  public TBaseDataLine(TMixer mixer, DataLine.Info info, Collection<Control> controls) {
    super(mixer, info, controls);
  }
  
  public void open(AudioFormat format, int nBufferSize) throws LineUnavailableException {
    if (TDebug.TraceDataLine)
      TDebug.out("TBaseDataLine.open(AudioFormat, int): called with buffer size: " + nBufferSize); 
    setBufferSize(nBufferSize);
    open(format);
  }
  
  public void open(AudioFormat format) throws LineUnavailableException {
    if (TDebug.TraceDataLine)
      TDebug.out("TBaseDataLine.open(AudioFormat): called"); 
    setFormat(format);
    open();
  }
  
  protected void finalize() {
    if (isOpen())
      close(); 
  }
}
