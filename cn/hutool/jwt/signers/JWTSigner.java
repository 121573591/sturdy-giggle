package cn.hutool.jwt.signers;

public interface JWTSigner {
  String sign(String paramString1, String paramString2);
  
  boolean verify(String paramString1, String paramString2, String paramString3);
  
  String getAlgorithm();
  
  default String getAlgorithmId() {
    return AlgorithmUtil.getId(getAlgorithm());
  }
}
