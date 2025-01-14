package cn.hutool.crypto.digest.mac;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;

public class BCMacEngine implements MacEngine {
  private Mac mac;
  
  public BCMacEngine(Mac mac, CipherParameters params) {
    init(mac, params);
  }
  
  public BCMacEngine init(Mac mac, CipherParameters params) {
    mac.init(params);
    this.mac = mac;
    return this;
  }
  
  public Mac getMac() {
    return this.mac;
  }
  
  public void update(byte[] in, int inOff, int len) {
    this.mac.update(in, inOff, len);
  }
  
  public byte[] doFinal() {
    byte[] result = new byte[getMacLength()];
    this.mac.doFinal(result, 0);
    return result;
  }
  
  public void reset() {
    this.mac.reset();
  }
  
  public int getMacLength() {
    return this.mac.getMacSize();
  }
  
  public String getAlgorithm() {
    return this.mac.getAlgorithmName();
  }
}
