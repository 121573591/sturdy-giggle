package cn.hutool.core.img;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Path;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class Img implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final BufferedImage srcImage;
  
  private Image targetImage;
  
  private String targetImageType;
  
  private boolean positionBaseCentre = true;
  
  private float quality = -1.0F;
  
  private Color backgroundColor;
  
  public static Img from(Path imagePath) {
    return from(imagePath.toFile());
  }
  
  public static Img from(File imageFile) {
    return new Img(ImgUtil.read(imageFile));
  }
  
  public static Img from(Resource resource) {
    return from(resource.getStream());
  }
  
  public static Img from(InputStream in) {
    return new Img(ImgUtil.read(in));
  }
  
  public static Img from(ImageInputStream imageStream) {
    return new Img(ImgUtil.read(imageStream));
  }
  
  public static Img from(URL imageUrl) {
    return new Img(ImgUtil.read(imageUrl));
  }
  
  public static Img from(Image image) {
    return new Img(ImgUtil.castToBufferedImage(image, "jpg"));
  }
  
  public Img(BufferedImage srcImage) {
    this(srcImage, null);
  }
  
  public Img(BufferedImage srcImage, String targetImageType) {
    this.srcImage = srcImage;
    if (null == targetImageType)
      if (srcImage.getType() == 2 || srcImage
        .getType() == 3 || srcImage
        .getType() == 6 || srcImage
        .getType() == 7) {
        targetImageType = "png";
      } else {
        targetImageType = "jpg";
      }  
    this.targetImageType = targetImageType;
  }
  
  public Img setTargetImageType(String imgType) {
    this.targetImageType = imgType;
    return this;
  }
  
  public Img setPositionBaseCentre(boolean positionBaseCentre) {
    this.positionBaseCentre = positionBaseCentre;
    return this;
  }
  
  public Img setQuality(double quality) {
    return setQuality((float)quality);
  }
  
  public Img setQuality(float quality) {
    if (quality > 0.0F && quality < 1.0F) {
      this.quality = quality;
    } else {
      this.quality = 1.0F;
    } 
    return this;
  }
  
  public Img setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }
  
  public Img scale(float scale) {
    if (scale < 0.0F)
      scale = -scale; 
    Image srcImg = getValidSrcImg();
    if ("png".equals(this.targetImageType)) {
      double scaleDouble = NumberUtil.toDouble(Float.valueOf(scale));
      this.targetImage = ImgUtil.transform(AffineTransform.getScaleInstance(scaleDouble, scaleDouble), 
          ImgUtil.toBufferedImage(srcImg, this.targetImageType));
    } else {
      int width = NumberUtil.mul(Integer.valueOf(srcImg.getWidth(null)), Float.valueOf(scale)).intValue();
      int height = NumberUtil.mul(Integer.valueOf(srcImg.getHeight(null)), Float.valueOf(scale)).intValue();
      scale(width, height);
    } 
    return this;
  }
  
  public Img scale(int width, int height) {
    return scale(width, height, 4);
  }
  
  public Img scale(int width, int height, int scaleType) {
    Image srcImg = getValidSrcImg();
    int srcHeight = srcImg.getHeight(null);
    int srcWidth = srcImg.getWidth(null);
    if (srcHeight == height && srcWidth == width) {
      this.targetImage = srcImg;
      return this;
    } 
    if ("png".equals(this.targetImageType)) {
      double sx = NumberUtil.div(width, srcWidth);
      double sy = NumberUtil.div(height, srcHeight);
      this.targetImage = ImgUtil.transform(AffineTransform.getScaleInstance(sx, sy), 
          ImgUtil.toBufferedImage(srcImg, this.targetImageType));
    } else {
      this.targetImage = srcImg.getScaledInstance(width, height, scaleType);
    } 
    return this;
  }
  
  public Img scale(int width, int height, Color fixedColor) {
    Image srcImage = getValidSrcImg();
    int srcHeight = srcImage.getHeight(null);
    int srcWidth = srcImage.getWidth(null);
    double heightRatio = NumberUtil.div(height, srcHeight);
    double widthRatio = NumberUtil.div(width, srcWidth);
    if (NumberUtil.equals(heightRatio, widthRatio)) {
      scale(width, height);
    } else if (widthRatio < heightRatio) {
      scale(width, (int)(srcHeight * widthRatio));
    } else {
      scale((int)(srcWidth * heightRatio), height);
    } 
    srcImage = getValidSrcImg();
    srcHeight = srcImage.getHeight(null);
    srcWidth = srcImage.getWidth(null);
    BufferedImage image = new BufferedImage(width, height, getTypeInt());
    Graphics2D g = image.createGraphics();
    if (null != fixedColor) {
      g.setBackground(fixedColor);
      g.clearRect(0, 0, width, height);
    } 
    g.drawImage(srcImage, (width - srcWidth) / 2, (height - srcHeight) / 2, srcWidth, srcHeight, fixedColor, null);
    g.dispose();
    this.targetImage = image;
    return this;
  }
  
  public Img cut(Rectangle rectangle) {
    Image srcImage = getValidSrcImg();
    fixRectangle(rectangle, srcImage.getWidth(null), srcImage.getHeight(null));
    ImageFilter cropFilter = new CropImageFilter(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    this.targetImage = ImgUtil.filter(cropFilter, srcImage);
    return this;
  }
  
  public Img cut(int x, int y) {
    return cut(x, y, -1);
  }
  
  public Img cut(int x, int y, int radius) {
    Image srcImage = getValidSrcImg();
    int width = srcImage.getWidth(null);
    int height = srcImage.getHeight(null);
    int diameter = (radius > 0) ? (radius * 2) : Math.min(width, height);
    BufferedImage targetImage = new BufferedImage(diameter, diameter, 2);
    Graphics2D g = targetImage.createGraphics();
    g.setClip(new Ellipse2D.Double(0.0D, 0.0D, diameter, diameter));
    if (this.positionBaseCentre) {
      x = x - width / 2 + diameter / 2;
      y = y - height / 2 + diameter / 2;
    } 
    g.drawImage(srcImage, x, y, (ImageObserver)null);
    g.dispose();
    this.targetImage = targetImage;
    return this;
  }
  
  public Img round(double arc) {
    Image srcImage = getValidSrcImg();
    int width = srcImage.getWidth(null);
    int height = srcImage.getHeight(null);
    arc = NumberUtil.mul(arc, Math.min(width, height));
    BufferedImage targetImage = new BufferedImage(width, height, 2);
    Graphics2D g2 = targetImage.createGraphics();
    g2.setComposite(AlphaComposite.Src);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.fill(new RoundRectangle2D.Double(0.0D, 0.0D, width, height, arc, arc));
    g2.setComposite(AlphaComposite.SrcAtop);
    g2.drawImage(srcImage, 0, 0, (ImageObserver)null);
    g2.dispose();
    this.targetImage = targetImage;
    return this;
  }
  
  public Img gray() {
    this.targetImage = ImgUtil.colorConvert(ColorSpace.getInstance(1003), getValidSrcBufferedImg());
    return this;
  }
  
  public Img binary() {
    this.targetImage = ImgUtil.copyImage(getValidSrcImg(), 12);
    return this;
  }
  
  public Img pressText(String pressText, Color color, Font font, int x, int y, float alpha) {
    return pressText(pressText, color, font, new Point(x, y), alpha);
  }
  
  public Img pressText(String pressText, Color color, Font font, Point point, float alpha) {
    BufferedImage targetImage = ImgUtil.toBufferedImage(getValidSrcImg(), this.targetImageType);
    if (null == font)
      font = FontUtil.createSansSerifFont((int)(targetImage.getHeight() * 0.75D)); 
    Graphics2D g = targetImage.createGraphics();
    g.setComposite(AlphaComposite.getInstance(10, alpha));
    if (this.positionBaseCentre) {
      GraphicsUtil.drawString(g, pressText, font, color, new Rectangle(point.x, point.y, targetImage
            .getWidth(), targetImage.getHeight()));
    } else {
      GraphicsUtil.drawString(g, pressText, font, color, point);
    } 
    g.dispose();
    this.targetImage = targetImage;
    return this;
  }
  
  public Img pressTextFull(String pressText, Color color, Font font, int lineHeight, int degree, float alpha) {
    Dimension dimension;
    BufferedImage targetImage = ImgUtil.toBufferedImage(getValidSrcImg(), this.targetImageType);
    if (null == font)
      font = FontUtil.createSansSerifFont((int)(targetImage.getHeight() * 0.75D)); 
    int targetHeight = targetImage.getHeight();
    int targetWidth = targetImage.getWidth();
    Graphics2D g = targetImage.createGraphics();
    g.setColor(color);
    g.rotate(Math.toRadians(degree), (targetWidth >> 1), (targetHeight >> 1));
    g.setComposite(AlphaComposite.getInstance(10, alpha));
    try {
      dimension = FontUtil.getDimension(g.getFontMetrics(font), pressText);
    } catch (Exception e) {
      dimension = new Dimension(targetWidth / 3, targetHeight / 3);
    } 
    int intervalHeight = dimension.height * lineHeight;
    int y = -targetHeight >> 1;
    while (y < targetHeight * 1.5D) {
      int x = -targetWidth >> 1;
      while (x < targetWidth * 1.5D) {
        GraphicsUtil.drawString(g, pressText, font, color, new Point(x, y));
        x += dimension.width;
      } 
      y += intervalHeight;
    } 
    g.dispose();
    this.targetImage = targetImage;
    return this;
  }
  
  public Img pressImage(Image pressImg, int x, int y, float alpha) {
    int pressImgWidth = pressImg.getWidth(null);
    int pressImgHeight = pressImg.getHeight(null);
    return pressImage(pressImg, new Rectangle(x, y, pressImgWidth, pressImgHeight), alpha);
  }
  
  public Img pressImage(Image pressImg, Rectangle rectangle, float alpha) {
    Image targetImg = getValidSrcImg();
    this.targetImage = draw(ImgUtil.toBufferedImage(targetImg, this.targetImageType), pressImg, rectangle, alpha);
    return this;
  }
  
  public Img rotate(int degree) {
    Image image = getValidSrcImg();
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    Rectangle rectangle = calcRotatedSize(width, height, degree);
    BufferedImage targetImg = new BufferedImage(rectangle.width, rectangle.height, getTypeInt());
    Graphics2D graphics2d = targetImg.createGraphics();
    graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics2d.translate((rectangle.width - width) / 2.0D, (rectangle.height - height) / 2.0D);
    graphics2d.rotate(Math.toRadians(degree), width / 2.0D, height / 2.0D);
    graphics2d.drawImage(image, 0, 0, (ImageObserver)null);
    graphics2d.dispose();
    this.targetImage = targetImg;
    return this;
  }
  
  public Img flip() {
    Image image = getValidSrcImg();
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    BufferedImage targetImg = new BufferedImage(width, height, getTypeInt());
    Graphics2D graphics2d = targetImg.createGraphics();
    graphics2d.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
    graphics2d.dispose();
    this.targetImage = targetImg;
    return this;
  }
  
  public Img stroke(Color color, float width) {
    return stroke(color, new BasicStroke(width));
  }
  
  public Img stroke(Color color, Stroke stroke) {
    BufferedImage image = ImgUtil.toBufferedImage(getValidSrcImg(), this.targetImageType);
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    Graphics2D g = image.createGraphics();
    g.setColor((Color)ObjectUtil.defaultIfNull(color, Color.BLACK));
    if (null != stroke)
      g.setStroke(stroke); 
    g.drawRect(0, 0, width - 1, height - 1);
    g.dispose();
    this.targetImage = image;
    return this;
  }
  
  public Image getImg() {
    return getValidSrcImg();
  }
  
  public boolean write(OutputStream out) throws IORuntimeException {
    return write(ImgUtil.getImageOutputStream(out));
  }
  
  public boolean write(ImageOutputStream targetImageStream) throws IORuntimeException {
    Assert.notBlank(this.targetImageType, "Target image type is blank !", new Object[0]);
    Assert.notNull(targetImageStream, "Target output stream is null !", new Object[0]);
    Image targetImage = (null == this.targetImage) ? this.srcImage : this.targetImage;
    Assert.notNull(targetImage, "Target image is null !", new Object[0]);
    return ImgUtil.write(targetImage, this.targetImageType, targetImageStream, this.quality, this.backgroundColor);
  }
  
  public boolean write(File targetFile) throws IORuntimeException {
    String formatName = FileUtil.extName(targetFile);
    if (StrUtil.isNotBlank(formatName))
      this.targetImageType = formatName; 
    if (targetFile.exists())
      targetFile.delete(); 
    ImageOutputStream out = null;
    try {
      out = ImgUtil.getImageOutputStream(targetFile);
      return write(out);
    } finally {
      IoUtil.close(out);
    } 
  }
  
  private BufferedImage draw(BufferedImage backgroundImg, Image img, Rectangle rectangle, float alpha) {
    Graphics2D g = backgroundImg.createGraphics();
    GraphicsUtil.setAlpha(g, alpha);
    fixRectangle(rectangle, backgroundImg.getWidth(), backgroundImg.getHeight());
    GraphicsUtil.drawImg(g, img, rectangle);
    g.dispose();
    return backgroundImg;
  }
  
  private int getTypeInt() {
    switch (this.targetImageType) {
      case "png":
        return 2;
    } 
    return 1;
  }
  
  private Image getValidSrcImg() {
    return (Image)ObjectUtil.defaultIfNull(this.targetImage, this.srcImage);
  }
  
  private BufferedImage getValidSrcBufferedImg() {
    return ImgUtil.toBufferedImage(getValidSrcImg(), this.targetImageType);
  }
  
  private Rectangle fixRectangle(Rectangle rectangle, int baseWidth, int baseHeight) {
    if (this.positionBaseCentre) {
      Point pointBaseCentre = ImgUtil.getPointBaseCentre(rectangle, baseWidth, baseHeight);
      rectangle.setLocation(pointBaseCentre.x, pointBaseCentre.y);
    } 
    return rectangle;
  }
  
  private static Rectangle calcRotatedSize(int width, int height, int degree) {
    if (degree < 0)
      degree += 360; 
    if (degree >= 90) {
      if (degree / 90 % 2 == 1) {
        int temp = height;
        height = width;
        width = temp;
      } 
      degree %= 90;
    } 
    double r = Math.sqrt((height * height + width * width)) / 2.0D;
    double len = 2.0D * Math.sin(Math.toRadians(degree) / 2.0D) * r;
    double angel_alpha = (Math.PI - Math.toRadians(degree)) / 2.0D;
    double angel_dalta_width = Math.atan(height / width);
    double angel_dalta_height = Math.atan(width / height);
    int len_dalta_width = (int)(len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
    int len_dalta_height = (int)(len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
    int des_width = width + len_dalta_width * 2;
    int des_height = height + len_dalta_height * 2;
    return new Rectangle(des_width, des_height);
  }
}
