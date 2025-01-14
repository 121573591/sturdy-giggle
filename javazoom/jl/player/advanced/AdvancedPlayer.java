package javazoom.jl.player.advanced;

import java.io.InputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class AdvancedPlayer {
  private Bitstream bitstream;
  
  private Decoder decoder;
  
  private AudioDevice audio;
  
  private boolean closed = false;
  
  private boolean complete = false;
  
  private int lastPosition = 0;
  
  private PlaybackListener listener;
  
  public AdvancedPlayer(InputStream stream) throws JavaLayerException {
    this(stream, null);
  }
  
  public AdvancedPlayer(InputStream stream, AudioDevice device) throws JavaLayerException {
    this.bitstream = new Bitstream(stream);
    if (device != null) {
      this.audio = device;
    } else {
      this.audio = FactoryRegistry.systemRegistry().createAudioDevice();
    } 
    this.audio.open(this.decoder = new Decoder());
  }
  
  public void play() throws JavaLayerException {
    play(2147483647);
  }
  
  public boolean play(int frames) throws JavaLayerException {
    boolean ret = true;
    if (this.listener != null)
      this.listener.playbackStarted(createEvent(PlaybackEvent.STARTED)); 
    while (frames-- > 0 && ret)
      ret = decodeFrame(); 
    AudioDevice out = this.audio;
    if (out != null) {
      out.flush();
      synchronized (this) {
        this.complete = !this.closed;
        close();
      } 
      if (this.listener != null)
        this.listener.playbackFinished(createEvent(out, PlaybackEvent.STOPPED)); 
    } 
    return ret;
  }
  
  public synchronized void close() {
    AudioDevice out = this.audio;
    if (out != null) {
      this.closed = true;
      this.audio = null;
      out.close();
      this.lastPosition = out.getPosition();
      try {
        this.bitstream.close();
      } catch (BitstreamException ex) {}
    } 
  }
  
  protected boolean decodeFrame() throws JavaLayerException {
    try {
      AudioDevice out = this.audio;
      if (out == null)
        return false; 
      Header h = this.bitstream.readFrame();
      if (h == null)
        return false; 
      SampleBuffer output = (SampleBuffer)this.decoder.decodeFrame(h, this.bitstream);
      synchronized (this) {
        out = this.audio;
        if (out != null)
          out.write(output.getBuffer(), 0, output.getBufferLength()); 
      } 
      this.bitstream.closeFrame();
    } catch (RuntimeException ex) {
      throw new JavaLayerException("Exception decoding audio frame", ex);
    } 
    return true;
  }
  
  protected boolean skipFrame() throws JavaLayerException {
    Header h = this.bitstream.readFrame();
    if (h == null)
      return false; 
    this.bitstream.closeFrame();
    return true;
  }
  
  public boolean play(int start, int end) throws JavaLayerException {
    boolean ret = true;
    int offset = start;
    for (; offset-- > 0 && ret; ret = skipFrame());
    return play(end - start);
  }
  
  private PlaybackEvent createEvent(int id) {
    return createEvent(this.audio, id);
  }
  
  private PlaybackEvent createEvent(AudioDevice dev, int id) {
    return new PlaybackEvent(this, id, dev.getPosition());
  }
  
  public void setPlayBackListener(PlaybackListener listener) {
    this.listener = listener;
  }
  
  public PlaybackListener getPlayBackListener() {
    return this.listener;
  }
  
  public void stop() {
    this.listener.playbackFinished(createEvent(PlaybackEvent.STOPPED));
    close();
  }
}
