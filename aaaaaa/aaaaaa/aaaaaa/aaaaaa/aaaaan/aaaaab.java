package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaan;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDeviceBase;
import org.openjdk.nashorn.tools.resources.JhjbM.___;
import org.openjdk.nashorn.tools.resources.JhjbM.___.____;
import org.slf4j.Logger;

public class aaaaab extends AudioDeviceBase {
  private static final Logger 我草你怎么反编译我模组aaaaaa;
  
  private SourceDataLine 我草你怎么反编译我模组aaaaab = null;
  
  private AudioFormat 我草你怎么反编译我模组aaaaac = null;
  
  private final float 我草你怎么反编译我模组aaaaad;
  
  private long 我草你怎么反编译我模组aaaaae = 0L;
  
  public static boolean $skidonion$512193837;
  
  public aaaaab(float paramFloat) {
    this.我草你怎么反编译我模组aaaaad = paramFloat;
  }
  
  protected native void openImpl() throws JavaLayerException;
  
  protected native void writeImpl(short[] paramArrayOfshort, int paramInt1, int paramInt2) throws JavaLayerException;
  
  protected native void flushImpl();
  
  protected native void closeImpl();
  
  public native int getPosition();
  
  private native byte[] 你为什么要破解我的代码aaaaaa(short[] paramArrayOfshort, int paramInt1, int paramInt2);
  
  static {
    ___.___(96, aaaaab.class);
    ____.___96_70(aaaaab.class);
  }
  
  private static native Object aaaaab(char paramChar);
}
