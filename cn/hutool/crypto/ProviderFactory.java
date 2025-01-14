package cn.hutool.crypto;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ProviderFactory {
  public static Provider createBouncyCastleProvider() {
    BouncyCastleProvider bouncyCastleProvider;
    Provider provider = Security.getProvider("BC");
    if (provider == null) {
      bouncyCastleProvider = new BouncyCastleProvider();
      SecureUtil.addProvider((Provider)bouncyCastleProvider);
    } 
    return (Provider)bouncyCastleProvider;
  }
}
