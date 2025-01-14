package javazoom.jl.player;

import java.applet.Applet;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;

public class PlayerApplet extends Applet implements Runnable {
  public static final String AUDIO_PARAMETER = "audioURL";
  
  private Player player = null;
  
  private Thread playerThread = null;
  
  private String fileName = null;
  
  protected AudioDevice getAudioDevice() throws JavaLayerException {
    return FactoryRegistry.systemRegistry().createAudioDevice();
  }
  
  protected InputStream getAudioStream() {
    InputStream in = null;
    try {
      URL url = getAudioURL();
      if (url != null)
        in = url.openStream(); 
    } catch (IOException ex) {
      System.err.println(ex);
    } 
    return in;
  }
  
  protected String getAudioFileName() {
    String urlString = this.fileName;
    if (urlString == null)
      urlString = getParameter("audioURL"); 
    return urlString;
  }
  
  protected URL getAudioURL() {
    String urlString = getAudioFileName();
    URL url = null;
    if (urlString != null)
      try {
        url = new URL(getDocumentBase(), urlString);
      } catch (Exception ex) {
        System.err.println(ex);
      }  
    return url;
  }
  
  public void setFileName(String name) {
    this.fileName = name;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  protected void stopPlayer() throws JavaLayerException {
    if (this.player != null) {
      this.player.close();
      this.player = null;
      this.playerThread = null;
    } 
  }
  
  protected void play(InputStream in, AudioDevice dev) throws JavaLayerException {
    stopPlayer();
    if (in != null && dev != null) {
      this.player = new Player(in, dev);
      this.playerThread = createPlayerThread();
      this.playerThread.start();
    } 
  }
  
  protected Thread createPlayerThread() {
    return new Thread(this, "Audio player thread");
  }
  
  public void init() {}
  
  public void start() {
    String name = getAudioFileName();
    try {
      InputStream in = getAudioStream();
      AudioDevice dev = getAudioDevice();
      play(in, dev);
    } catch (JavaLayerException ex) {
      synchronized (System.err) {
        System.err.println("Unable to play " + name);
        ex.printStackTrace(System.err);
      } 
    } 
  }
  
  public void stop() {
    try {
      stopPlayer();
    } catch (JavaLayerException ex) {
      System.err.println(ex);
    } 
  }
  
  public void destroy() {}
  
  public void run() {
    if (this.player != null)
      try {
        this.player.play();
      } catch (JavaLayerException ex) {
        System.err.println("Problem playing audio: " + ex);
      }  
  }
}
