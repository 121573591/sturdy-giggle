package cn.hutool.crypto;

import cn.hutool.core.io.IORuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.X509TrustedCertificateBlock;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;

public class OpensslKeyUtil {
  private static final JcaPEMKeyConverter pemKeyConverter = (new JcaPEMKeyConverter()).setProvider(GlobalBouncyCastleProvider.INSTANCE.getProvider());
  
  public static PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo) throws CryptoException {
    try {
      return pemKeyConverter.getPrivateKey(privateKeyInfo);
    } catch (PEMException e) {
      throw new CryptoException(e);
    } 
  }
  
  public static PublicKey getPublicKey(SubjectPublicKeyInfo publicKeyInfo) throws CryptoException {
    try {
      return pemKeyConverter.getPublicKey(publicKeyInfo);
    } catch (PEMException e) {
      throw new CryptoException(e);
    } 
  }
  
  public static KeyPair getKeyPair(PEMKeyPair keyPair) throws CryptoException {
    try {
      return pemKeyConverter.getKeyPair(keyPair);
    } catch (PEMException e) {
      throw new CryptoException(e);
    } 
  }
  
  public static Key readPemKey(InputStream keyStream, char[] password) {
    try (PEMParser pemParser = new PEMParser(new InputStreamReader(keyStream))) {
      return readPemKeyFromKeyObject(pemParser.readObject(), password);
    } catch (IOException e) {
      throw new CryptoException(e);
    } 
  }
  
  public static PrivateKeyInfo decrypt(PKCS8EncryptedPrivateKeyInfo pkcs8Info, char[] password) throws CryptoException {
    try {
      InputDecryptorProvider decryptProvider = (new JceOpenSSLPKCS8DecryptorProviderBuilder()).setProvider(GlobalBouncyCastleProvider.INSTANCE.getProvider()).build(password);
      return pkcs8Info.decryptPrivateKeyInfo(decryptProvider);
    } catch (OperatorCreationException|org.bouncycastle.pkcs.PKCSException e) {
      throw new CryptoException(e);
    } 
  }
  
  public static PEMKeyPair decrypt(PEMEncryptedKeyPair pemEncryptedKeyPair, char[] password) throws IORuntimeException {
    try {
      PEMDecryptorProvider decryptProvider = (new JcePEMDecryptorProviderBuilder()).setProvider(GlobalBouncyCastleProvider.INSTANCE.getProvider()).build(password);
      return pemEncryptedKeyPair.decryptKeyPair(decryptProvider);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  private static Key readPemKeyFromKeyObject(Object keyObject, char[] password) throws CryptoException {
    if (keyObject instanceof PrivateKeyInfo)
      return getPrivateKey((PrivateKeyInfo)keyObject); 
    if (keyObject instanceof PEMKeyPair)
      return getKeyPair((PEMKeyPair)keyObject).getPrivate(); 
    if (keyObject instanceof PKCS8EncryptedPrivateKeyInfo)
      return getPrivateKey(decrypt((PKCS8EncryptedPrivateKeyInfo)keyObject, password)); 
    if (keyObject instanceof PEMEncryptedKeyPair)
      return getPrivateKey(decrypt((PEMEncryptedKeyPair)keyObject, password).getPrivateKeyInfo()); 
    if (keyObject instanceof SubjectPublicKeyInfo)
      return getPublicKey((SubjectPublicKeyInfo)keyObject); 
    if (keyObject instanceof X509CertificateHolder)
      return getPublicKey(((X509CertificateHolder)keyObject).getSubjectPublicKeyInfo()); 
    if (keyObject instanceof X509TrustedCertificateBlock)
      return getPublicKey(((X509TrustedCertificateBlock)keyObject).getCertificateHolder().getSubjectPublicKeyInfo()); 
    if (keyObject instanceof PKCS10CertificationRequest)
      return getPublicKey(((PKCS10CertificationRequest)keyObject).getSubjectPublicKeyInfo()); 
    throw new CryptoException("Unsupported key object type: {}", new Object[] { keyObject.getClass() });
  }
}
