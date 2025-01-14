package cn.hutool.core.img;

import cn.hutool.core.lang.Assert;
import java.awt.Color;
import java.awt.color.ColorSpace;

public class LabColor {
  private static final ColorSpace XYZ_COLOR_SPACE = ColorSpace.getInstance(1001);
  
  private final double l;
  
  private final double a;
  
  private final double b;
  
  public LabColor(Integer rgb) {
    this((rgb != null) ? new Color(rgb.intValue()) : null);
  }
  
  public LabColor(Color color) {
    Assert.notNull(color, "Color must not be null", new Object[0]);
    float[] lab = fromXyz(color.getColorComponents(XYZ_COLOR_SPACE, null));
    this.l = lab[0];
    this.a = lab[1];
    this.b = lab[2];
  }
  
  public double getDistance(LabColor other) {
    double c1 = Math.sqrt(this.a * this.a + this.b * this.b);
    double deltaC = c1 - Math.sqrt(other.a * other.a + other.b * other.b);
    double deltaA = this.a - other.a;
    double deltaB = this.b - other.b;
    double deltaH = Math.sqrt(Math.max(0.0D, deltaA * deltaA + deltaB * deltaB - deltaC * deltaC));
    return Math.sqrt(Math.max(0.0D, Math.pow(this.l - other.l, 2.0D) + 
          Math.pow(deltaC / (1.0D + 0.045D * c1), 2.0D) + Math.pow(deltaH / (1.0D + 0.015D * c1), 2.0D)));
  }
  
  private float[] fromXyz(float[] xyz) {
    return fromXyz(xyz[0], xyz[1], xyz[2]);
  }
  
  private static float[] fromXyz(float x, float y, float z) {
    double l = (f(y) - 16.0D) * 116.0D;
    double a = (f(x) - f(y)) * 500.0D;
    double b = (f(y) - f(z)) * 200.0D;
    return new float[] { (float)l, (float)a, (float)b };
  }
  
  private static double f(double t) {
    return (t > 0.008856451679035631D) ? Math.cbrt(t) : (0.3333333333333333D * Math.pow(4.833333333333333D, 2.0D) * t + 0.13793103448275862D);
  }
}
