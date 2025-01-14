package cn.hutool.jwt.signers;

import cn.hutool.jwt.JWTException;
import java.security.Key;
import java.security.KeyPair;

public class EllipticCurveJWTSigner extends AsymmetricJWTSigner {
  public EllipticCurveJWTSigner(String algorithm, Key key) {
    super(algorithm, key);
  }
  
  public EllipticCurveJWTSigner(String algorithm, KeyPair keyPair) {
    super(algorithm, keyPair);
  }
  
  protected byte[] sign(byte[] data) {
    return derToConcat(super.sign(data), getSignatureByteArrayLength(getAlgorithm()));
  }
  
  protected boolean verify(byte[] data, byte[] signed) {
    return super.verify(data, concatToDER(signed));
  }
  
  private static int getSignatureByteArrayLength(String alg) throws JWTException {
    switch (alg) {
      case "ES256":
      case "SHA256withECDSA":
        return 64;
      case "ES384":
      case "SHA384withECDSA":
        return 96;
      case "ES512":
      case "SHA512withECDSA":
        return 132;
    } 
    throw new JWTException("Unsupported Algorithm: {}", new Object[] { alg });
  }
  
  private static byte[] derToConcat(byte[] derSignature, int outputLength) throws JWTException {
    int offset;
    if (derSignature.length < 8 || derSignature[0] != 48)
      throw new JWTException("Invalid ECDSA signature format"); 
    if (derSignature[1] > 0) {
      offset = 2;
    } else if (derSignature[1] == -127) {
      offset = 3;
    } else {
      throw new JWTException("Invalid ECDSA signature format");
    } 
    byte rLength = derSignature[offset + 1];
    int i = rLength;
    while (i > 0 && derSignature[offset + 2 + rLength - i] == 0)
      i--; 
    byte sLength = derSignature[offset + 2 + rLength + 1];
    int j = sLength;
    while (j > 0 && derSignature[offset + 2 + rLength + 2 + sLength - j] == 0)
      j--; 
    int rawLen = Math.max(i, j);
    rawLen = Math.max(rawLen, outputLength / 2);
    if ((derSignature[offset - 1] & 0xFF) != derSignature.length - offset || (derSignature[offset - 1] & 0xFF) != 2 + rLength + 2 + sLength || derSignature[offset] != 2 || derSignature[offset + 2 + rLength] != 2)
      throw new JWTException("Invalid ECDSA signature format"); 
    byte[] concatSignature = new byte[2 * rawLen];
    System.arraycopy(derSignature, offset + 2 + rLength - i, concatSignature, rawLen - i, i);
    System.arraycopy(derSignature, offset + 2 + rLength + 2 + sLength - j, concatSignature, 2 * rawLen - j, j);
    return concatSignature;
  }
  
  private static byte[] concatToDER(byte[] jwsSignature) throws ArrayIndexOutOfBoundsException {
    int offset;
    byte[] derSignature;
    int rawLen = jwsSignature.length / 2;
    int i = rawLen;
    while (i > 0 && jwsSignature[rawLen - i] == 0)
      i--; 
    int j = i;
    if (jwsSignature[rawLen - i] < 0)
      j++; 
    int k = rawLen;
    while (k > 0 && jwsSignature[2 * rawLen - k] == 0)
      k--; 
    int l = k;
    if (jwsSignature[2 * rawLen - k] < 0)
      l++; 
    int len = 2 + j + 2 + l;
    if (len > 255)
      throw new JWTException("Invalid ECDSA signature format"); 
    if (len < 128) {
      derSignature = new byte[4 + j + 2 + l];
      offset = 1;
    } else {
      derSignature = new byte[5 + j + 2 + l];
      derSignature[1] = -127;
      offset = 2;
    } 
    derSignature[0] = 48;
    derSignature[offset++] = (byte)len;
    derSignature[offset++] = 2;
    derSignature[offset++] = (byte)j;
    System.arraycopy(jwsSignature, rawLen - i, derSignature, offset + j - i, i);
    offset += j;
    derSignature[offset++] = 2;
    derSignature[offset++] = (byte)l;
    System.arraycopy(jwsSignature, 2 * rawLen - k, derSignature, offset + l - k, k);
    return derSignature;
  }
}
