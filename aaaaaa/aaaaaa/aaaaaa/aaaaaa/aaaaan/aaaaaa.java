package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaan;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaj.aaaaab;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import org.openjdk.nashorn.tools.resources.JhjbM.___;
import org.openjdk.nashorn.tools.resources.JhjbM.___.____;
import org.slf4j.Logger;

public class aaaaaa {
  private static final Logger 我草你怎么反编译我模组aaaaaa;
  
  private static final File 我草你怎么反编译我模组aaaaab;
  
  private static final String 我草你怎么反编译我模组aaaaac;
  
  private static aaaaaa 我草你怎么反编译我模组aaaaad;
  
  private final ConcurrentHashMap<String, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac> 我草你怎么反编译我模组aaaaae = new ConcurrentHashMap<>();
  
  private final ExecutorService 我草你怎么反编译我模组aaaaaf = Executors.newCachedThreadPool();
  
  public static boolean $skidonion$904990024;
  
  private aaaaaa() {
    你为什么要破解我的代码aaaaab();
    aaaaab.你为什么要破解我的代码aaaaaa();
  }
  
  public static synchronized native aaaaaa 你为什么要破解我的代码aaaaaa();
  
  public static native void 你为什么要破解我的代码aaaaab();
  
  private static native void 你为什么要破解我的代码aaaaac() throws IOException;
  
  private static native void 你为什么要破解我的代码aaaaad(URL paramURL) throws IOException;
  
  private static native void 你为什么要破解我的代码aaaaae(URL paramURL) throws IOException;
  
  public native String 你为什么要破解我的代码aaaaaf(String paramString, double paramDouble);
  
  public native void 你为什么要破解我的代码aaaaag(String paramString);
  
  public native void 你为什么要破解我的代码aaaaah(String paramString, double paramDouble);
  
  public native boolean 你为什么要破解我的代码aaaaai(String paramString);
  
  static {
    ___.___(91, aaaaaa.class);
    ____.___91_110(aaaaaa.class);
  }
  
  private static native Object aaaaak(char paramChar);
  
  private static class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac {
    private final Player 我草你怎么反编译我模组aaaaaa;
    
    private volatile boolean 我草你怎么反编译我模组aaaaab = false;
    
    private volatile float 我草你怎么反编译我模组aaaaac;
    
    private final String 我草你怎么反编译我模组aaaaad = UUID.randomUUID().toString();
    
    public static boolean $skidonion$698979338;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac(File param1File, double param1Double) throws Exception {
      this.我草你怎么反编译我模组aaaaac = (float)Math.min(2.0D, Math.max(0.0D, Math.round(param1Double * 10.0D) / 10.0D));
      try {
        aaaaab aaaaab = new aaaaab(this.我草你怎么反编译我模组aaaaac);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(param1File));
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
    
    public native void 你为什么要破解我的代码aaaaad(double param1Double);
    
    static {
      ___.___(130, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac.class);
      ____.___130_50(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaan/aaaaac.class);
    }
    
    private static native Object aaaaae(char param1Char);
  }
}
