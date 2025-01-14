package cn.hutool.core.codec;

import cn.hutool.core.util.StrUtil;
import java.util.Arrays;

public class Base58Codec implements Encoder<byte[], String>, Decoder<CharSequence, byte[]> {
  public static Base58Codec INSTANCE = new Base58Codec();
  
  public String encode(byte[] data) {
    return Base58Encoder.ENCODER.encode(data);
  }
  
  public byte[] decode(CharSequence encoded) throws IllegalArgumentException {
    return Base58Decoder.DECODER.decode(encoded);
  }
  
  public static class Base58Encoder implements Encoder<byte[], String> {
    private static final String DEFAULT_ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    
    public static final Base58Encoder ENCODER = new Base58Encoder("123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray());
    
    private final char[] alphabet;
    
    private final char alphabetZero;
    
    public Base58Encoder(char[] alphabet) {
      this.alphabet = alphabet;
      this.alphabetZero = alphabet[0];
    }
    
    public String encode(byte[] data) {
      if (null == data)
        return null; 
      if (data.length == 0)
        return ""; 
      int zeroCount = 0;
      while (zeroCount < data.length && data[zeroCount] == 0)
        zeroCount++; 
      data = Arrays.copyOf(data, data.length);
      char[] encoded = new char[data.length * 2];
      int outputStart = encoded.length;
      for (int inputStart = zeroCount; inputStart < data.length; ) {
        encoded[--outputStart] = this.alphabet[Base58Codec.divmod(data, inputStart, 256, 58)];
        if (data[inputStart] == 0)
          inputStart++; 
      } 
      while (outputStart < encoded.length && encoded[outputStart] == this.alphabetZero)
        outputStart++; 
      while (--zeroCount >= 0)
        encoded[--outputStart] = this.alphabetZero; 
      return new String(encoded, outputStart, encoded.length - outputStart);
    }
  }
  
  public static class Base58Decoder implements Decoder<CharSequence, byte[]> {
    public static Base58Decoder DECODER = new Base58Decoder("123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz");
    
    private final byte[] lookupTable;
    
    public Base58Decoder(String alphabet) {
      byte[] lookupTable = new byte[123];
      Arrays.fill(lookupTable, (byte)-1);
      int length = alphabet.length();
      for (int i = 0; i < length; i++)
        lookupTable[alphabet.charAt(i)] = (byte)i; 
      this.lookupTable = lookupTable;
    }
    
    public byte[] decode(CharSequence encoded) {
      if (encoded.length() == 0)
        return new byte[0]; 
      byte[] input58 = new byte[encoded.length()];
      for (int i = 0; i < encoded.length(); i++) {
        char c = encoded.charAt(i);
        int digit = (c < '') ? this.lookupTable[c] : -1;
        if (digit < 0)
          throw new IllegalArgumentException(StrUtil.format("Invalid char '{}' at [{}]", new Object[] { Character.valueOf(c), Integer.valueOf(i) })); 
        input58[i] = (byte)digit;
      } 
      int zeros = 0;
      while (zeros < input58.length && input58[zeros] == 0)
        zeros++; 
      byte[] decoded = new byte[encoded.length()];
      int outputStart = decoded.length;
      for (int inputStart = zeros; inputStart < input58.length; ) {
        decoded[--outputStart] = Base58Codec.divmod(input58, inputStart, 58, 256);
        if (input58[inputStart] == 0)
          inputStart++; 
      } 
      while (outputStart < decoded.length && decoded[outputStart] == 0)
        outputStart++; 
      return Arrays.copyOfRange(decoded, outputStart - zeros, decoded.length);
    }
  }
  
  private static byte divmod(byte[] number, int firstDigit, int base, int divisor) {
    int remainder = 0;
    for (int i = firstDigit; i < number.length; i++) {
      int digit = number[i] & 0xFF;
      int temp = remainder * base + digit;
      number[i] = (byte)(temp / divisor);
      remainder = temp % divisor;
    } 
    return (byte)remainder;
  }
}
