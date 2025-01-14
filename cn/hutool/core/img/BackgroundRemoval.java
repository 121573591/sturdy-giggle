package cn.hutool.core.img;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BackgroundRemoval {
  public static String[] IMAGES_TYPE = new String[] { "jpg", "png" };
  
  public static boolean backgroundRemoval(String inputPath, String outputPath, int tolerance) {
    return backgroundRemoval(new File(inputPath), new File(outputPath), tolerance);
  }
  
  public static boolean backgroundRemoval(File input, File output, int tolerance) {
    return backgroundRemoval(input, output, null, tolerance);
  }
  
  public static boolean backgroundRemoval(File input, File output, Color override, int tolerance) {
    if (fileTypeValidation(input, IMAGES_TYPE))
      return false; 
    try {
      BufferedImage bufferedImage = ImageIO.read(input);
      return ImageIO.write(backgroundRemoval(bufferedImage, override, tolerance), "png", output);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public static BufferedImage backgroundRemoval(BufferedImage bufferedImage, Color override, int tolerance) {
    tolerance = Math.min(255, Math.max(tolerance, 0));
    ImageIcon imageIcon = new ImageIcon(bufferedImage);
    BufferedImage image = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), 6);
    Graphics graphics = image.getGraphics();
    graphics.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
    String[] removeRgb = getRemoveRgb(bufferedImage);
    String mainColor = getMainColor(bufferedImage);
    int alpha = 0;
    for (int y = image.getMinY(); y < image.getHeight(); y++) {
      for (int x = image.getMinX(); x < image.getWidth(); x++) {
        int rgb = image.getRGB(x, y);
        String hex = ImgUtil.toHex((rgb & 0xFF0000) >> 16, (rgb & 0xFF00) >> 8, rgb & 0xFF);
        boolean isTrue = (ArrayUtil.contains((Object[])removeRgb, hex) || areColorsWithinTolerance(hexToRgb(mainColor), new Color(Integer.parseInt(hex.substring(1), 16)), tolerance));
        if (isTrue)
          rgb = (override == null) ? (alpha + 1 << 24 | rgb & 0xFFFFFF) : override.getRGB(); 
        image.setRGB(x, y, rgb);
      } 
    } 
    graphics.drawImage(image, 0, 0, imageIcon.getImageObserver());
    return image;
  }
  
  public static BufferedImage backgroundRemoval(ByteArrayOutputStream outputStream, Color override, int tolerance) {
    try {
      return backgroundRemoval(ImageIO.read(new ByteArrayInputStream(outputStream.toByteArray())), override, tolerance);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  private static String[] getRemoveRgb(BufferedImage image) {
    int width = image.getWidth() - 1;
    int height = image.getHeight() - 1;
    int leftUpPixel = image.getRGB(1, 1);
    String leftUp = ImgUtil.toHex((leftUpPixel & 0xFF0000) >> 16, (leftUpPixel & 0xFF00) >> 8, leftUpPixel & 0xFF);
    int upMiddlePixel = image.getRGB(width / 2, 1);
    String upMiddle = ImgUtil.toHex((upMiddlePixel & 0xFF0000) >> 16, (upMiddlePixel & 0xFF00) >> 8, upMiddlePixel & 0xFF);
    int rightUpPixel = image.getRGB(width, 1);
    String rightUp = ImgUtil.toHex((rightUpPixel & 0xFF0000) >> 16, (rightUpPixel & 0xFF00) >> 8, rightUpPixel & 0xFF);
    int rightMiddlePixel = image.getRGB(width, height / 2);
    String rightMiddle = ImgUtil.toHex((rightMiddlePixel & 0xFF0000) >> 16, (rightMiddlePixel & 0xFF00) >> 8, rightMiddlePixel & 0xFF);
    int lowerRightPixel = image.getRGB(width, height);
    String lowerRight = ImgUtil.toHex((lowerRightPixel & 0xFF0000) >> 16, (lowerRightPixel & 0xFF00) >> 8, lowerRightPixel & 0xFF);
    int lowerMiddlePixel = image.getRGB(width / 2, height);
    String lowerMiddle = ImgUtil.toHex((lowerMiddlePixel & 0xFF0000) >> 16, (lowerMiddlePixel & 0xFF00) >> 8, lowerMiddlePixel & 0xFF);
    int leftLowerPixel = image.getRGB(1, height);
    String leftLower = ImgUtil.toHex((leftLowerPixel & 0xFF0000) >> 16, (leftLowerPixel & 0xFF00) >> 8, leftLowerPixel & 0xFF);
    int leftMiddlePixel = image.getRGB(1, height / 2);
    String leftMiddle = ImgUtil.toHex((leftMiddlePixel & 0xFF0000) >> 16, (leftMiddlePixel & 0xFF00) >> 8, leftMiddlePixel & 0xFF);
    return new String[] { leftUp, upMiddle, rightUp, rightMiddle, lowerRight, lowerMiddle, leftLower, leftMiddle };
  }
  
  public static Color hexToRgb(String hex) {
    return new Color(Integer.parseInt(hex.substring(1), 16));
  }
  
  public static boolean areColorsWithinTolerance(Color color1, Color color2, int tolerance) {
    return areColorsWithinTolerance(color1, color2, new Color(tolerance, tolerance, tolerance));
  }
  
  public static boolean areColorsWithinTolerance(Color color1, Color color2, Color tolerance) {
    return (color1.getRed() - color2.getRed() < tolerance.getRed() && color1
      .getRed() - color2.getRed() > -tolerance.getRed() && color1
      .getBlue() - color2.getBlue() < tolerance
      .getBlue() && color1.getBlue() - color2.getBlue() > 
      -tolerance.getBlue() && color1
      .getGreen() - color2.getGreen() < tolerance
      .getGreen() && color1.getGreen() - color2
      .getGreen() > -tolerance.getGreen());
  }
  
  public static String getMainColor(String input) {
    return getMainColor(new File(input));
  }
  
  public static String getMainColor(File input) {
    try {
      return getMainColor(ImageIO.read(input));
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    } 
  }
  
  public static String getMainColor(BufferedImage bufferedImage) {
    if (bufferedImage == null)
      throw new IllegalArgumentException("图片流是空的"); 
    List<String> list = new ArrayList<>();
    for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
      for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
        int pixel = bufferedImage.getRGB(x, y);
        list.add(((pixel & 0xFF0000) >> 16) + "-" + ((pixel & 0xFF00) >> 8) + "-" + (pixel & 0xFF));
      } 
    } 
    Map<String, Integer> map = new HashMap<>(list.size(), 1.0F);
    for (String string : list) {
      Integer integer = map.get(string);
      if (integer == null) {
        integer = Integer.valueOf(1);
      } else {
        Integer integer1 = integer, integer2 = integer = Integer.valueOf(integer.intValue() + 1);
      } 
      map.put(string, integer);
    } 
    String max = "";
    long num = 0L;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      Integer temp = entry.getValue();
      if (StrUtil.isBlank(max) || temp.intValue() > num) {
        max = key;
        num = temp.intValue();
      } 
    } 
    String[] strings = max.split("-");
    int rgbLength = 3;
    if (strings.length == rgbLength)
      return ImgUtil.toHex(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), 
          Integer.parseInt(strings[2])); 
    return "";
  }
  
  private static boolean fileTypeValidation(File input, String[] imagesType) {
    if (!input.exists())
      throw new IllegalArgumentException("给定文件为空"); 
    String type = FileTypeUtil.getType(input);
    if (!ArrayUtil.contains((Object[])imagesType, type))
      throw new IllegalArgumentException(StrUtil.format("文件类型{}不支持", new Object[] { type })); 
    return false;
  }
}
