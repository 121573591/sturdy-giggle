package javazoom.jl.player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;

public class jlp {
  private String fFilename = null;
  
  private boolean remote = false;
  
  public static void main(String[] args) {
    int retval = 0;
    try {
      jlp player = createInstance(args);
      if (player != null)
        player.play(); 
    } catch (Exception ex) {
      System.err.println(ex);
      ex.printStackTrace(System.err);
      retval = 1;
    } 
    System.exit(retval);
  }
  
  public static jlp createInstance(String[] args) {
    jlp player = new jlp();
    if (!player.parseArgs(args))
      player = null; 
    return player;
  }
  
  private jlp() {}
  
  public jlp(String filename) {
    init(filename);
  }
  
  protected void init(String filename) {
    this.fFilename = filename;
  }
  
  protected boolean parseArgs(String[] args) {
    boolean parsed = false;
    if (args.length == 1) {
      init(args[0]);
      parsed = true;
      this.remote = false;
    } else if (args.length == 2) {
      if (!args[0].equals("-url")) {
        showUsage();
      } else {
        init(args[1]);
        parsed = true;
        this.remote = true;
      } 
    } else {
      showUsage();
    } 
    return parsed;
  }
  
  public void showUsage() {
    System.out.println("Usage: jlp [-url] <filename>");
    System.out.println("");
    System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");
    System.out.println("        java javazoom.jl.player.jlp -url http://www.server.com/remotefile.mp3");
    System.out.println("        java javazoom.jl.player.jlp -url http://www.shoutcastserver.com:8000");
  }
  
  public void play() throws JavaLayerException {
    try {
      System.out.println("playing " + this.fFilename + "...");
      InputStream in = null;
      if (this.remote == true) {
        in = getURLInputStream();
      } else {
        in = getInputStream();
      } 
      AudioDevice dev = getAudioDevice();
      Player player = new Player(in, dev);
      player.play();
    } catch (IOException ex) {
      throw new JavaLayerException("Problem playing file " + this.fFilename, ex);
    } catch (Exception ex) {
      throw new JavaLayerException("Problem playing file " + this.fFilename, ex);
    } 
  }
  
  protected InputStream getURLInputStream() throws Exception {
    URL url = new URL(this.fFilename);
    InputStream fin = url.openStream();
    BufferedInputStream bin = new BufferedInputStream(fin);
    return bin;
  }
  
  protected InputStream getInputStream() throws IOException {
    FileInputStream fin = new FileInputStream(this.fFilename);
    BufferedInputStream bin = new BufferedInputStream(fin);
    return bin;
  }
  
  protected AudioDevice getAudioDevice() throws JavaLayerException {
    return FactoryRegistry.systemRegistry().createAudioDevice();
  }
}
