package cn.hutool.core.lang.hash;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.StrUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KetamaHash implements Hash64<String>, Hash32<String> {
  public long hash64(String key) {
    byte[] bKey = md5(key);
    return (bKey[3] & 0xFF) << 24L | (bKey[2] & 0xFF) << 16L | (bKey[1] & 0xFF) << 8L | (bKey[0] & 0xFF);
  }
  
  public int hash32(String key) {
    return (int)(hash64(key) & 0xFFFFFFFFL);
  }
  
  public Number hash(String key) {
    return Long.valueOf(hash64(key));
  }
  
  private static byte[] md5(String key) {
    MessageDigest md5;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new UtilException("MD5 algorithm not suooport!", e);
    } 
    return md5.digest(StrUtil.utf8Bytes(key));
  }
}
