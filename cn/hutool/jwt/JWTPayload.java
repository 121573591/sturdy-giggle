package cn.hutool.jwt;

import java.util.Map;

public class JWTPayload extends Claims implements RegisteredPayload<JWTPayload> {
  private static final long serialVersionUID = 1L;
  
  public JWTPayload addPayloads(Map<String, ?> payloadClaims) {
    putAll(payloadClaims);
    return this;
  }
  
  public JWTPayload setPayload(String name, Object value) {
    setClaim(name, value);
    return this;
  }
}
