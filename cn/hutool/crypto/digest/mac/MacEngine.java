package cn.hutool.crypto.digest.mac;

import cn.hutool.crypto.CryptoException;
import java.io.IOException;
import java.io.InputStream;

public interface MacEngine {
  default void update(byte[] in) {
    update(in, 0, in.length);
  }
  
  void update(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  byte[] doFinal();
  
  void reset();
  
  default byte[] digest(InputStream data, int bufferLength) {
    byte[] result;
    if (bufferLength < 1)
      bufferLength = 8192; 
    byte[] buffer = new byte[bufferLength];
    try {
      int read = data.read(buffer, 0, bufferLength);
      while (read > -1) {
        update(buffer, 0, read);
        read = data.read(buffer, 0, bufferLength);
      } 
      result = doFinal();
    } catch (IOException e) {
      throw new CryptoException(e);
    } finally {
      reset();
    } 
    return result;
  }
  
  int getMacLength();
  
  String getAlgorithm();
}
