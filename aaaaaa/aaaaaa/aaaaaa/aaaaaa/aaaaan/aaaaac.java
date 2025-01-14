package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import org.openjdk.nashorn.tools.resources.JhjbM.___;
import org.openjdk.nashorn.tools.resources.JhjbM.___.____;

class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac {
  private final Player 我草你怎么反编译我模组aaaaaa;
  
  private volatile boolean 我草你怎么反编译我模组aaaaab = false;
  
  private volatile float 我草你怎么反编译我模组aaaaac;
  
  private final String 我草你怎么反编译我模组aaaaad = UUID.randomUUID().toString();
  
  public static boolean $skidonion$698979338;
  
  public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac(File paramFile, double paramDouble) throws Exception {
    this.我草你怎么反编译我模组aaaaac = (float)Math.min(2.0D, Math.max(0.0D, Math.round(paramDouble * 10.0D) / 10.0D));
    try {
      aaaaab aaaaab = new aaaaab(this.我草你怎么反编译我模组aaaaac);
      BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(paramFile));
      this.我草你怎么反编译我模组aaaaaa = new Player(bufferedInputStream, (AudioDevice)aaaaab);
      aaaaaa.我草你怎么反编译我模组aaaaaa.info((String)aaaaae((char)-297140224), this.我草你怎么反编译我模组aaaaad, Float.valueOf(this.我草你怎么反编译我模组aaaaac));
    } catch (Exception exception) {
      aaaaaa.我草你怎么反编译我模组aaaaaa.error((String)aaaaae((char)-646774783), this.我草你怎么反编译我模组aaaaad, exception);
      throw exception;
    } 
  }
  
  public native void 你为什么要破解我的代码aaaaaa() throws Exception;
  
  public native void 你为什么要破解我的代码aaaaab();
  
  private native void 你为什么要破解我的代码aaaaac();
  
  public native void 你为什么要破解我的代码aaaaad(double paramDouble);
  
  static {
    ___.___(130, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac.class);
    ____.___130_50(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac.class);
  }
  
  private static native Object aaaaae(char paramChar);
}
