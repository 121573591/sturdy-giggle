package cn.hutool.jwt;

import java.util.Date;

public interface RegisteredPayload<T extends RegisteredPayload<T>> {
  public static final String ISSUER = "iss";
  
  public static final String SUBJECT = "sub";
  
  public static final String AUDIENCE = "aud";
  
  public static final String EXPIRES_AT = "exp";
  
  public static final String NOT_BEFORE = "nbf";
  
  public static final String ISSUED_AT = "iat";
  
  public static final String JWT_ID = "jti";
  
  default T setIssuer(String issuer) {
    return setPayload("iss", issuer);
  }
  
  default T setSubject(String subject) {
    return setPayload("sub", subject);
  }
  
  T setAudience(String... audience) {
    return setPayload("aud", audience);
  }
  
  default T setExpiresAt(Date expiresAt) {
    return setPayload("exp", expiresAt);
  }
  
  default T setNotBefore(Date notBefore) {
    return setPayload("nbf", notBefore);
  }
  
  default T setIssuedAt(Date issuedAt) {
    return setPayload("iat", issuedAt);
  }
  
  default T setJWTId(String jwtId) {
    return setPayload("jti", jwtId);
  }
  
  T setPayload(String paramString, Object paramObject);
}
