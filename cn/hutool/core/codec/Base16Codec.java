package cn.hutool.core.codec;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.StrUtil;

public class Base16Codec implements Encoder<byte[], char[]>, Decoder<CharSequence, byte[]> {
  public static final Base16Codec CODEC_LOWER = new Base16Codec(true);
  
  public static final Base16Codec CODEC_UPPER = new Base16Codec(false);
  
  private final char[] alphabets;
  
  public Base16Codec(boolean lowerCase) {
    this.alphabets = (lowerCase ? "0123456789abcdef" : "0123456789ABCDEF").toCharArray();
  }
  
  public char[] encode(byte[] data) {
    int len = data.length;
    char[] out = new char[len << 1];
    for (int i = 0, j = 0; i < len; i++) {
      out[j++] = this.alphabets[(0xF0 & data[i]) >>> 4];
      out[j++] = this.alphabets[0xF & data[i]];
    } 
    return out;
  }
  
  public byte[] decode(CharSequence encoded) {
    if (StrUtil.isEmpty(encoded))
      return null; 
    encoded = StrUtil.cleanBlank(encoded);
    int len = encoded.length();
    if ((len & 0x1) != 0) {
      encoded = "0" + encoded;
      len = encoded.length();
    } 
    byte[] out = new byte[len >> 1];
    for (int i = 0, j = 0; j < len; i++) {
      int f = toDigit(encoded.charAt(j), j) << 4;
      j++;
      f |= toDigit(encoded.charAt(j), j);
      j++;
      out[i] = (byte)(f & 0xFF);
    } 
    return out;
  }
  
  public String toUnicodeHex(char ch) {
    return "\\u" + this.alphabets[ch >> 12 & 0xF] + this.alphabets[ch >> 8 & 0xF] + this.alphabets[ch >> 4 & 0xF] + this.alphabets[ch & 0xF];
  }
  
  public void appendHex(StringBuilder builder, byte b) {
    int high = (b & 0xF0) >>> 4;
    int low = b & 0xF;
    builder.append(this.alphabets[high]);
    builder.append(this.alphabets[low]);
  }
  
  private static int toDigit(char ch, int index) {
    int digit = Character.digit(ch, 16);
    if (digit < 0)
      throw new UtilException("Illegal hexadecimal character {} at index {}", new Object[] { Character.valueOf(ch), Integer.valueOf(index) }); 
    return digit;
  }
}
