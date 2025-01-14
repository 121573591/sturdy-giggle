package cn.hutool.core.util;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.text.TextSimilarity;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class StrUtil extends CharSequenceUtil implements StrPool {
  public static boolean isBlankIfStr(Object obj) {
    if (null == obj)
      return true; 
    if (obj instanceof CharSequence)
      return isBlank((CharSequence)obj); 
    return false;
  }
  
  public static boolean isEmptyIfStr(Object obj) {
    if (null == obj)
      return true; 
    if (obj instanceof CharSequence)
      return (0 == ((CharSequence)obj).length()); 
    return false;
  }
  
  public static void trim(String[] strs) {
    if (null == strs)
      return; 
    for (int i = 0; i < strs.length; i++) {
      String str = strs[i];
      if (null != str)
        strs[i] = trim(str); 
    } 
  }
  
  public static String utf8Str(Object obj) {
    return str(obj, CharsetUtil.CHARSET_UTF_8);
  }
  
  @Deprecated
  public static String str(Object obj, String charsetName) {
    return str(obj, Charset.forName(charsetName));
  }
  
  public static String str(Object obj, Charset charset) {
    if (null == obj)
      return null; 
    if (obj instanceof String)
      return (String)obj; 
    if (obj instanceof byte[])
      return str((byte[])obj, charset); 
    if (obj instanceof Byte[])
      return str((Byte[])obj, charset); 
    if (obj instanceof ByteBuffer)
      return str((ByteBuffer)obj, charset); 
    if (ArrayUtil.isArray(obj))
      return ArrayUtil.toString(obj); 
    return obj.toString();
  }
  
  public static String str(byte[] bytes, String charset) {
    return str(bytes, CharsetUtil.charset(charset));
  }
  
  public static String str(byte[] data, Charset charset) {
    if (data == null)
      return null; 
    if (null == charset)
      return new String(data); 
    return new String(data, charset);
  }
  
  public static String str(Byte[] bytes, String charset) {
    return str(bytes, CharsetUtil.charset(charset));
  }
  
  public static String str(Byte[] data, Charset charset) {
    if (data == null)
      return null; 
    byte[] bytes = new byte[data.length];
    for (int i = 0; i < data.length; i++) {
      Byte dataByte = data[i];
      bytes[i] = (null == dataByte) ? -1 : dataByte.byteValue();
    } 
    return str(bytes, charset);
  }
  
  public static String str(ByteBuffer data, String charset) {
    if (data == null)
      return null; 
    return str(data, CharsetUtil.charset(charset));
  }
  
  public static String str(ByteBuffer data, Charset charset) {
    if (null == charset)
      charset = Charset.defaultCharset(); 
    return charset.decode(data).toString();
  }
  
  public static String toString(Object obj) {
    return String.valueOf(obj);
  }
  
  public static String toStringOrNull(Object obj) {
    return (null == obj) ? null : obj.toString();
  }
  
  public static StringBuilder builder() {
    return new StringBuilder();
  }
  
  public static StrBuilder strBuilder() {
    return StrBuilder.create();
  }
  
  public static StringBuilder builder(int capacity) {
    return new StringBuilder(capacity);
  }
  
  public static StrBuilder strBuilder(int capacity) {
    return StrBuilder.create(capacity);
  }
  
  public static StringReader getReader(CharSequence str) {
    if (null == str)
      return null; 
    return new StringReader(str.toString());
  }
  
  public static StringWriter getWriter() {
    return new StringWriter();
  }
  
  public static String reverse(String str) {
    return new String(ArrayUtil.reverse(str.toCharArray()));
  }
  
  public static String fillBefore(String str, char filledChar, int len) {
    return fill(str, filledChar, len, true);
  }
  
  public static String fillAfter(String str, char filledChar, int len) {
    return fill(str, filledChar, len, false);
  }
  
  public static String fill(String str, char filledChar, int len, boolean isPre) {
    int strLen = str.length();
    if (strLen > len)
      return str; 
    String filledStr = repeat(filledChar, len - strLen);
    return isPre ? filledStr.concat(str) : str.concat(filledStr);
  }
  
  public static double similar(String str1, String str2) {
    return TextSimilarity.similar(str1, str2);
  }
  
  public static String similar(String str1, String str2, int scale) {
    return TextSimilarity.similar(str1, str2, scale);
  }
  
  public static String uuid() {
    return IdUtil.randomUUID();
  }
  
  public static String format(CharSequence template, Map<?, ?> map) {
    return format(template, map, true);
  }
  
  public static String format(CharSequence template, Map<?, ?> map, boolean ignoreNull) {
    return StrFormatter.format(template, map, ignoreNull);
  }
  
  public static String truncateUtf8(String str, int maxBytes) {
    Charset charset = StandardCharsets.UTF_8;
    return truncateByByteLength(str, charset, maxBytes, 4, true);
  }
  
  public static String truncateByByteLength(String str, Charset charset, int maxBytes, int factor, boolean appendDots) {
    int limitBytes;
    if (str == null || str.length() * factor <= maxBytes)
      return str; 
    byte[] sba = str.getBytes(charset);
    if (sba.length <= maxBytes)
      return str; 
    if (appendDots) {
      limitBytes = maxBytes - ("...".getBytes(charset)).length;
    } else {
      limitBytes = maxBytes;
    } 
    ByteBuffer bb = ByteBuffer.wrap(sba, 0, limitBytes);
    CharBuffer cb = CharBuffer.allocate(limitBytes);
    CharsetDecoder decoder = charset.newDecoder();
    decoder.onMalformedInput(CodingErrorAction.IGNORE);
    decoder.decode(bb, cb, true);
    decoder.flush(cb);
    String result = new String(cb.array(), 0, cb.position());
    if (appendDots)
      return result + "..."; 
    return result;
  }
}
