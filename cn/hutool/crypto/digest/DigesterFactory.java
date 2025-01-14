package cn.hutool.crypto.digest;

import cn.hutool.crypto.SecureUtil;
import java.security.MessageDigest;

public class DigesterFactory {
  private final MessageDigest prototype;
  
  private final boolean cloneSupport;
  
  public static DigesterFactory ofJdk(String algorithm) {
    return of(SecureUtil.createJdkMessageDigest(algorithm));
  }
  
  public static DigesterFactory of(String algorithm) {
    return of(SecureUtil.createMessageDigest(algorithm));
  }
  
  public static DigesterFactory of(MessageDigest messageDigest) {
    return new DigesterFactory(messageDigest);
  }
  
  private DigesterFactory(MessageDigest messageDigest) {
    this.prototype = messageDigest;
    this.cloneSupport = checkCloneSupport(messageDigest);
  }
  
  public Digester createDigester() {
    return new Digester(createMessageDigester());
  }
  
  public MessageDigest createMessageDigester() {
    if (this.cloneSupport)
      try {
        return (MessageDigest)this.prototype.clone();
      } catch (CloneNotSupportedException cloneNotSupportedException) {} 
    return SecureUtil.createJdkMessageDigest(this.prototype.getAlgorithm());
  }
  
  private static boolean checkCloneSupport(MessageDigest messageDigest) {
    try {
      messageDigest.clone();
      return true;
    } catch (CloneNotSupportedException e) {
      return false;
    } 
  }
}
