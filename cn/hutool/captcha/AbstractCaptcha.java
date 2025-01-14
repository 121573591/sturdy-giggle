package cn.hutool.captcha;

import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractCaptcha implements ICaptcha {
  private static final long serialVersionUID = 3180820918087507254L;
  
  protected int width;
  
  protected int height;
  
  protected int interfereCount;
  
  protected Font font;
  
  protected String code;
  
  protected byte[] imageBytes;
  
  protected CodeGenerator generator;
  
  protected Color background;
  
  protected AlphaComposite textAlpha;
  
  public AbstractCaptcha(int width, int height, int codeCount, int interfereCount) {
    this(width, height, (CodeGenerator)new RandomGenerator(codeCount), interfereCount);
  }
  
  public AbstractCaptcha(int width, int height, CodeGenerator generator, int interfereCount) {
    this.width = width;
    this.height = height;
    this.generator = generator;
    this.interfereCount = interfereCount;
    this.font = new Font("SansSerif", 0, (int)(this.height * 0.75D));
  }
  
  public void createCode() {
    generateCode();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ImgUtil.writePng(createImage(this.code), out);
    this.imageBytes = out.toByteArray();
  }
  
  protected void generateCode() {
    this.code = this.generator.generate();
  }
  
  protected abstract Image createImage(String paramString);
  
  public String getCode() {
    if (null == this.code)
      createCode(); 
    return this.code;
  }
  
  public boolean verify(String userInputCode) {
    return this.generator.verify(getCode(), userInputCode);
  }
  
  public void write(String path) throws IORuntimeException {
    write(FileUtil.touch(path));
  }
  
  public void write(File file) throws IORuntimeException {
    try (OutputStream out = FileUtil.getOutputStream(file)) {
      write(out);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public void write(OutputStream out) {
    IoUtil.write(out, false, getImageBytes());
  }
  
  public byte[] getImageBytes() {
    if (null == this.imageBytes)
      createCode(); 
    return this.imageBytes;
  }
  
  public BufferedImage getImage() {
    return ImgUtil.read(IoUtil.toStream(getImageBytes()));
  }
  
  public String getImageBase64() {
    return Base64.encode(getImageBytes());
  }
  
  public String getImageBase64Data() {
    return URLUtil.getDataUriBase64("image/png", getImageBase64());
  }
  
  public void setFont(Font font) {
    this.font = font;
  }
  
  public CodeGenerator getGenerator() {
    return this.generator;
  }
  
  public void setGenerator(CodeGenerator generator) {
    this.generator = generator;
  }
  
  public void setBackground(Color background) {
    this.background = background;
  }
  
  public void setTextAlpha(float textAlpha) {
    this.textAlpha = AlphaComposite.getInstance(3, textAlpha);
  }
}
