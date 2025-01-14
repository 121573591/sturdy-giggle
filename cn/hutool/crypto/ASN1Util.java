package cn.hutool.crypto;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IORuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.util.ASN1Dump;

public class ASN1Util {
  public static byte[] encodeDer(ASN1Encodable... elements) {
    return encode("DER", elements);
  }
  
  public static byte[] encode(String asn1Encoding, ASN1Encodable... elements) {
    FastByteArrayOutputStream out = new FastByteArrayOutputStream();
    encodeTo(asn1Encoding, (OutputStream)out, elements);
    return out.toByteArray();
  }
  
  public static void encodeTo(String asn1Encoding, OutputStream out, ASN1Encodable... elements) {
    DERSequence dERSequence;
    BERSequence bERSequence;
    DLSequence dLSequence;
    switch (asn1Encoding) {
      case "DER":
        dERSequence = new DERSequence(elements);
        break;
      case "BER":
        bERSequence = new BERSequence(elements);
        break;
      case "DL":
        dLSequence = new DLSequence(elements);
        break;
      default:
        throw new CryptoException("Unsupported ASN1 encoding: {}", new Object[] { asn1Encoding });
    } 
    try {
      dLSequence.encodeTo(out);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static ASN1Object decode(InputStream in) {
    ASN1InputStream asn1In = new ASN1InputStream(in);
    try {
      return (ASN1Object)asn1In.readObject();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static String getDumpStr(InputStream in) {
    return ASN1Dump.dumpAsString(decode(in));
  }
}
