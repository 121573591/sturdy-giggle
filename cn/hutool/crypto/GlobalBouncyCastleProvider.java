package cn.hutool.crypto;

import java.security.Provider;

public enum GlobalBouncyCastleProvider {
  INSTANCE;
  
  private static boolean useBouncyCastle;
  
  private Provider provider;
  
  static {
    useBouncyCastle = true;
  }
  
  GlobalBouncyCastleProvider() {
    try {
      this.provider = ProviderFactory.createBouncyCastleProvider();
    } catch (NoClassDefFoundError|NoSuchMethodError noClassDefFoundError) {}
  }
  
  public Provider getProvider() {
    return useBouncyCastle ? this.provider : null;
  }
  
  public static void setUseBouncyCastle(boolean isUseBouncyCastle) {
    useBouncyCastle = isUseBouncyCastle;
  }
}
