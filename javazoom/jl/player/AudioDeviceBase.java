package javazoom.jl.player;

import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;

public abstract class AudioDeviceBase implements AudioDevice {
  private boolean open = false;
  
  private Decoder decoder = null;
  
  public synchronized void open(Decoder decoder) throws JavaLayerException {
    if (!isOpen()) {
      this.decoder = decoder;
      openImpl();
      setOpen(true);
    } 
  }
  
  protected void openImpl() throws JavaLayerException {}
  
  protected void setOpen(boolean open) {
    this.open = open;
  }
  
  public synchronized boolean isOpen() {
    return this.open;
  }
  
  public synchronized void close() {
    if (isOpen()) {
      closeImpl();
      setOpen(false);
      this.decoder = null;
    } 
  }
  
  protected void closeImpl() {}
  
  public void write(short[] samples, int offs, int len) throws JavaLayerException {
    if (isOpen())
      writeImpl(samples, offs, len); 
  }
  
  protected void writeImpl(short[] samples, int offs, int len) throws JavaLayerException {}
  
  public void flush() {
    if (isOpen())
      flushImpl(); 
  }
  
  protected void flushImpl() {}
  
  protected Decoder getDecoder() {
    return this.decoder;
  }
}
