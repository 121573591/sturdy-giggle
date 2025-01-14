package cn.hutool.core.img;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ColorUtil {
  private static final int RGB_COLOR_BOUND = 256;
  
  public static String toHex(Color color) {
    return toHex(color.getRed(), color.getGreen(), color.getBlue());
  }
  
  public static String toHex(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255)
      throw new IllegalArgumentException("RGB must be 0~255!"); 
    return String.format("#%02X%02X%02X", new Object[] { Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b) });
  }
  
  public static Color getColor(String colorName) {
    if (StrUtil.isBlank(colorName))
      return null; 
    colorName = colorName.toUpperCase();
    if ("BLACK".equals(colorName))
      return Color.BLACK; 
    if ("WHITE".equals(colorName))
      return Color.WHITE; 
    if ("LIGHTGRAY".equals(colorName) || "LIGHT_GRAY".equals(colorName))
      return Color.LIGHT_GRAY; 
    if ("GRAY".equals(colorName))
      return Color.GRAY; 
    if ("DARKGRAY".equals(colorName) || "DARK_GRAY".equals(colorName))
      return Color.DARK_GRAY; 
    if ("RED".equals(colorName))
      return Color.RED; 
    if ("PINK".equals(colorName))
      return Color.PINK; 
    if ("ORANGE".equals(colorName))
      return Color.ORANGE; 
    if ("YELLOW".equals(colorName))
      return Color.YELLOW; 
    if ("GREEN".equals(colorName))
      return Color.GREEN; 
    if ("MAGENTA".equals(colorName))
      return Color.MAGENTA; 
    if ("CYAN".equals(colorName))
      return Color.CYAN; 
    if ("BLUE".equals(colorName))
      return Color.BLUE; 
    if ("DARKGOLD".equals(colorName))
      return hexToColor("#9e7e67"); 
    if ("LIGHTGOLD".equals(colorName))
      return hexToColor("#ac9c85"); 
    if (StrUtil.startWith(colorName, '#'))
      return hexToColor(colorName); 
    if (StrUtil.startWith(colorName, '$'))
      return hexToColor("#" + colorName.substring(1)); 
    List<String> rgb = StrUtil.split(colorName, ',');
    if (3 == rgb.size()) {
      Integer r = Convert.toInt(rgb.get(0));
      Integer g = Convert.toInt(rgb.get(1));
      Integer b = Convert.toInt(rgb.get(2));
      if (false == ArrayUtil.hasNull((Object[])new Integer[] { r, g, b }))
        return new Color(r.intValue(), g.intValue(), b.intValue()); 
    } else {
      return null;
    } 
    return null;
  }
  
  public static Color getColor(int rgb) {
    return new Color(rgb);
  }
  
  public static Color hexToColor(String hex) {
    return getColor(Integer.parseInt(StrUtil.removePrefix(hex, "#"), 16));
  }
  
  public static Color add(Color color1, Color color2) {
    double r1 = color1.getRed();
    double g1 = color1.getGreen();
    double b1 = color1.getBlue();
    double a1 = color1.getAlpha();
    double r2 = color2.getRed();
    double g2 = color2.getGreen();
    double b2 = color2.getBlue();
    double a2 = color2.getAlpha();
    int r = (int)((r1 * a1 / 255.0D + r2 * a2 / 255.0D) / (a1 / 255.0D + a2 / 255.0D));
    int g = (int)((g1 * a1 / 255.0D + g2 * a2 / 255.0D) / (a1 / 255.0D + a2 / 255.0D));
    int b = (int)((b1 * a1 / 255.0D + b2 * a2 / 255.0D) / (a1 / 255.0D + a2 / 255.0D));
    return new Color(r, g, b);
  }
  
  public static Color randomColor() {
    return randomColor(null);
  }
  
  public static Color randomColor(Random random) {
    if (null == random)
      random = RandomUtil.getRandom(); 
    return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
  }
  
  public static String getMainColor(BufferedImage image, int[]... rgbFilters) {
    Map<String, Long> countMap = new HashMap<>();
    int width = image.getWidth();
    int height = image.getHeight();
    int minx = image.getMinX();
    int miny = image.getMinY();
    for (int i = minx; i < width; i++) {
      for (int j = miny; j < height; j++) {
        int pixel = image.getRGB(i, j);
        int r = (pixel & 0xFF0000) >> 16;
        int g = (pixel & 0xFF00) >> 8;
        int b = pixel & 0xFF;
        if (!matchFilters(r, g, b, rgbFilters))
          countMap.merge(r + "-" + g + "-" + b, Long.valueOf(1L), Long::sum); 
      } 
    } 
    String maxColor = null;
    long maxCount = 0L;
    for (Map.Entry<String, Long> entry : countMap.entrySet()) {
      String key = entry.getKey();
      Long count = entry.getValue();
      if (count.longValue() > maxCount) {
        maxColor = key;
        maxCount = count.longValue();
      } 
    } 
    String[] splitRgbStr = StrUtil.splitToArray(maxColor, '-');
    String rHex = Integer.toHexString(Integer.parseInt(splitRgbStr[0]));
    String gHex = Integer.toHexString(Integer.parseInt(splitRgbStr[1]));
    String bHex = Integer.toHexString(Integer.parseInt(splitRgbStr[2]));
    rHex = (rHex.length() == 1) ? ("0" + rHex) : rHex;
    gHex = (gHex.length() == 1) ? ("0" + gHex) : gHex;
    bHex = (bHex.length() == 1) ? ("0" + bHex) : bHex;
    return "#" + rHex + gHex + bHex;
  }
  
  private static boolean matchFilters(int r, int g, int b, int[]... rgbFilters) {
    if (rgbFilters != null && rgbFilters.length > 0)
      for (int[] rgbFilter : rgbFilters) {
        if (r == rgbFilter[0] && g == rgbFilter[1] && b == rgbFilter[2])
          return true; 
      }  
    return false;
  }
}
