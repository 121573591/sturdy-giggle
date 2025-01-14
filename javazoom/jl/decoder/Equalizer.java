package javazoom.jl.decoder;

public final class Equalizer {
  public static final float BAND_NOT_PRESENT = -InfinityF;
  
  public static final Equalizer PASS_THRU_EQ = new Equalizer();
  
  private static final int BANDS = 32;
  
  private final float[] settings = new float[32];
  
  public Equalizer() {}
  
  public Equalizer(float[] settings) {
    setFrom(settings);
  }
  
  public Equalizer(EQFunction eq) {
    setFrom(eq);
  }
  
  public void setFrom(float[] eq) {
    reset();
    int max = (eq.length > 32) ? 32 : eq.length;
    for (int i = 0; i < max; i++)
      this.settings[i] = limit(eq[i]); 
  }
  
  public void setFrom(EQFunction eq) {
    reset();
    int max = 32;
    for (int i = 0; i < max; i++)
      this.settings[i] = limit(eq.getBand(i)); 
  }
  
  public void setFrom(Equalizer eq) {
    if (eq != this)
      setFrom(eq.settings); 
  }
  
  public void reset() {
    for (int i = 0; i < 32; i++)
      this.settings[i] = 0.0F; 
  }
  
  public int getBandCount() {
    return this.settings.length;
  }
  
  public float setBand(int band, float neweq) {
    float eq = 0.0F;
    if (band >= 0 && band < 32) {
      eq = this.settings[band];
      this.settings[band] = limit(neweq);
    } 
    return eq;
  }
  
  public float getBand(int band) {
    float eq = 0.0F;
    if (band >= 0 && band < 32)
      eq = this.settings[band]; 
    return eq;
  }
  
  private float limit(float eq) {
    if (eq == Float.NEGATIVE_INFINITY)
      return eq; 
    if (eq > 1.0F)
      return 1.0F; 
    if (eq < -1.0F)
      return -1.0F; 
    return eq;
  }
  
  float[] getBandFactors() {
    float[] factors = new float[32];
    for (int i = 0, maxCount = 32; i < maxCount; i++)
      factors[i] = getBandFactor(this.settings[i]); 
    return factors;
  }
  
  float getBandFactor(float eq) {
    if (eq == Float.NEGATIVE_INFINITY)
      return 0.0F; 
    float f = (float)Math.pow(2.0D, eq);
    return f;
  }
  
  public static abstract class EQFunction {
    public float getBand(int band) {
      return 0.0F;
    }
  }
}
