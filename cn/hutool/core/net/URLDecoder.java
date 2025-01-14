package cn.hutool.core.net;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

public class URLDecoder implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final byte ESCAPE_CHAR = 37;
  
  public static String decodeForPath(String str, Charset charset) {
    return decode(str, charset, false);
  }
  
  public static String decode(String str, Charset charset) {
    return decode(str, charset, true);
  }
  
  public static String decode(String str, Charset charset, boolean isPlusToSpace) {
    if (null == str || null == charset)
      return str; 
    int length = str.length();
    if (0 == length)
      return ""; 
    StringBuilder result = new StringBuilder(length / 3);
    int begin = 0;
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      if ('%' != c && !CharUtil.isHexChar(c)) {
        if (i > begin)
          result.append(decodeSub(str, begin, i, charset, isPlusToSpace)); 
        if ('+' == c && isPlusToSpace)
          c = ' '; 
        result.append(c);
        begin = i + 1;
      } 
    } 
    if (begin < length)
      result.append(decodeSub(str, begin, length, charset, isPlusToSpace)); 
    return result.toString();
  }
  
  public static byte[] decode(byte[] bytes) {
    return decode(bytes, true);
  }
  
  public static byte[] decode(byte[] bytes, boolean isPlusToSpace) {
    if (bytes == null)
      return null; 
    ByteArrayOutputStream buffer = new ByteArrayOutputStream(bytes.length);
    for (int i = 0; i < bytes.length; i++) {
      int b = bytes[i];
      if (b == 43) {
        buffer.write(isPlusToSpace ? 32 : b);
        continue;
      } 
      if (b == 37) {
        if (i + 1 < bytes.length) {
          int u = CharUtil.digit16(bytes[i + 1]);
          if (u >= 0 && i + 2 < bytes.length) {
            int l = CharUtil.digit16(bytes[i + 2]);
            if (l >= 0) {
              buffer.write((char)((u << 4) + l));
              i += 2;
              continue;
            } 
          } 
        } 
        buffer.write(b);
        continue;
      } 
      buffer.write(b);
      continue;
    } 
    return buffer.toByteArray();
  }
  
  private static String decodeSub(String str, int begin, int end, Charset charset, boolean isPlusToSpace) {
    return new String(decode(str
          
          .substring(begin, end).getBytes(CharsetUtil.CHARSET_ISO_8859_1), isPlusToSpace), charset);
  }
}
