package cn.hutool.jwt;

import java.util.Map;

public class JWTHeader extends Claims {
  private static final long serialVersionUID = 1L;
  
  public static String ALGORITHM = "alg";
  
  public static String TYPE = "typ";
  
  public static String CONTENT_TYPE = "cty";
  
  public static String KEY_ID = "kid";
  
  public JWTHeader setKeyId(String keyId) {
    setClaim(KEY_ID, keyId);
    return this;
  }
  
  public JWTHeader addHeaders(Map<String, ?> headerClaims) {
    putAll(headerClaims);
    return this;
  }
}
