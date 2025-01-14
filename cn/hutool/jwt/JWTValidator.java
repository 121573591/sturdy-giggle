package cn.hutool.jwt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.signers.JWTSigner;
import java.util.Date;

public class JWTValidator {
  private final JWT jwt;
  
  public static JWTValidator of(String token) {
    return new JWTValidator(JWT.of(token));
  }
  
  public static JWTValidator of(JWT jwt) {
    return new JWTValidator(jwt);
  }
  
  public JWTValidator(JWT jwt) {
    this.jwt = jwt;
  }
  
  public JWTValidator validateAlgorithm() throws ValidateException {
    return validateAlgorithm(null);
  }
  
  public JWTValidator validateAlgorithm(JWTSigner signer) throws ValidateException {
    validateAlgorithm(this.jwt, signer);
    return this;
  }
  
  public JWTValidator validateDate() throws ValidateException {
    return validateDate((Date)DateUtil.beginOfSecond((Date)DateUtil.date()));
  }
  
  public JWTValidator validateDate(Date dateToCheck) throws ValidateException {
    validateDate(this.jwt.getPayload(), dateToCheck, 0L);
    return this;
  }
  
  public JWTValidator validateDate(Date dateToCheck, long leeway) throws ValidateException {
    validateDate(this.jwt.getPayload(), dateToCheck, leeway);
    return this;
  }
  
  private static void validateAlgorithm(JWT jwt, JWTSigner signer) throws ValidateException {
    String algorithmId = jwt.getAlgorithm();
    if (null == signer)
      signer = jwt.getSigner(); 
    if (StrUtil.isEmpty(algorithmId)) {
      if (null == signer || signer instanceof cn.hutool.jwt.signers.NoneJWTSigner)
        return; 
      throw new ValidateException("No algorithm defined in header!");
    } 
    if (null == signer)
      throw new IllegalArgumentException("No Signer for validate algorithm!"); 
    String algorithmIdInSigner = signer.getAlgorithmId();
    if (false == StrUtil.equals(algorithmId, algorithmIdInSigner))
      throw new ValidateException("Algorithm [{}] defined in header doesn't match to [{}]!", new Object[] { algorithmId, algorithmIdInSigner }); 
    if (false == jwt.verify(signer))
      throw new ValidateException("Signature verification failed!"); 
  }
  
  private static void validateDate(JWTPayload payload, Date now, long leeway) throws ValidateException {
    DateTime dateTime;
    if (null == now) {
      dateTime = DateUtil.date();
      dateTime.setTime(dateTime.getTime() / 1000L * 1000L);
    } 
    Date notBefore = payload.getClaimsJson().getDate("nbf");
    validateNotAfter("nbf", notBefore, (Date)dateTime, leeway);
    Date expiresAt = payload.getClaimsJson().getDate("exp");
    validateNotBefore("exp", expiresAt, (Date)dateTime, leeway);
    Date issueAt = payload.getClaimsJson().getDate("iat");
    validateNotAfter("iat", issueAt, (Date)dateTime, leeway);
  }
  
  private static void validateNotAfter(String fieldName, Date dateToCheck, Date now, long leeway) throws ValidateException {
    DateTime dateTime;
    if (null == dateToCheck)
      return; 
    if (leeway > 0L)
      dateTime = DateUtil.date(now.getTime() + leeway * 1000L); 
    if (dateToCheck.after((Date)dateTime))
      throw new ValidateException("'{}':[{}] is after now:[{}]", new Object[] { fieldName, 
            DateUtil.date(dateToCheck), DateUtil.date(dateTime) }); 
  }
  
  private static void validateNotBefore(String fieldName, Date dateToCheck, Date now, long leeway) throws ValidateException {
    DateTime dateTime;
    if (null == dateToCheck)
      return; 
    if (leeway > 0L)
      dateTime = DateUtil.date(now.getTime() - leeway * 1000L); 
    if (dateToCheck.before((Date)dateTime))
      throw new ValidateException("'{}':[{}] is before now:[{}]", new Object[] { fieldName, 
            DateUtil.date(dateToCheck), DateUtil.date(dateTime) }); 
  }
}
