package javazoom.jl.decoder;

public abstract class Obuffer {
  public static final int OBUFFERSIZE = 2304;
  
  public static final int MAXCHANNELS = 2;
  
  public abstract void append(int paramInt, short paramShort);
  
  public void appendSamples(int channel, float[] f) {
    for (int i = 0; i < 32; ) {
      short s = clip(f[i++]);
      append(channel, s);
    } 
  }
  
  private final short clip(float sample) {
    return (sample > 32767.0F) ? Short.MAX_VALUE : ((sample < -32768.0F) ? Short.MIN_VALUE : (short)(int)sample);
  }
  
  public abstract void write_buffer(int paramInt);
  
  public abstract void close();
  
  public abstract void clear_buffer();
  
  public abstract void set_stop_flag();
}
