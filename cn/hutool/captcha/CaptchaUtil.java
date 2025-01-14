package cn.hutool.captcha;

import cn.hutool.captcha.generator.CodeGenerator;

public class CaptchaUtil {
  public static LineCaptcha createLineCaptcha(int width, int height) {
    return new LineCaptcha(width, height);
  }
  
  public static LineCaptcha createLineCaptcha(int width, int height, int codeCount, int lineCount) {
    return new LineCaptcha(width, height, codeCount, lineCount);
  }
  
  public static LineCaptcha createLineCaptcha(int width, int height, CodeGenerator generator, int lineCount) {
    return new LineCaptcha(width, height, generator, lineCount);
  }
  
  public static CircleCaptcha createCircleCaptcha(int width, int height) {
    return new CircleCaptcha(width, height);
  }
  
  public static CircleCaptcha createCircleCaptcha(int width, int height, int codeCount, int circleCount) {
    return new CircleCaptcha(width, height, codeCount, circleCount);
  }
  
  public static CircleCaptcha createCircleCaptcha(int width, int height, CodeGenerator generator, int circleCount) {
    return new CircleCaptcha(width, height, generator, circleCount);
  }
  
  public static ShearCaptcha createShearCaptcha(int width, int height) {
    return new ShearCaptcha(width, height);
  }
  
  public static ShearCaptcha createShearCaptcha(int width, int height, int codeCount, int thickness) {
    return new ShearCaptcha(width, height, codeCount, thickness);
  }
  
  public static ShearCaptcha createShearCaptcha(int width, int height, CodeGenerator generator, int thickness) {
    return new ShearCaptcha(width, height, generator, thickness);
  }
  
  public static GifCaptcha createGifCaptcha(int width, int height) {
    return new GifCaptcha(width, height);
  }
  
  public static GifCaptcha createGifCaptcha(int width, int height, int codeCount) {
    return new GifCaptcha(width, height, codeCount);
  }
  
  public static GifCaptcha createGifCaptcha(int width, int height, CodeGenerator generator, int thickness) {
    return new GifCaptcha(width, height, generator, thickness);
  }
}
