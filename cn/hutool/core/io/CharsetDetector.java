package cn.hutool.core.io;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CharsetDetector {
  private static final Charset[] DEFAULT_CHARSETS;
  
  static {
    String[] names = { "UTF-8", "GBK", "GB2312", "GB18030", "UTF-16BE", "UTF-16LE", "UTF-16", "BIG5", "UNICODE", "US-ASCII" };
    DEFAULT_CHARSETS = (Charset[])Convert.convert(Charset[].class, names);
  }
  
  public static Charset detect(File file, Charset... charsets) {
    return detect(FileUtil.getInputStream(file), charsets);
  }
  
  public static Charset detect(InputStream in, Charset... charsets) {
    return detect(32768, in, charsets);
  }
  
  public static Charset detect(int bufferSize, InputStream in, Charset... charsets) {
    if (ArrayUtil.isEmpty((Object[])charsets))
      charsets = DEFAULT_CHARSETS; 
    byte[] buffer = new byte[bufferSize];
    try {
      while (in.read(buffer) > -1) {
        for (Charset charset : charsets) {
          CharsetDecoder decoder = charset.newDecoder();
          if (identify(buffer, decoder))
            return charset; 
        } 
      } 
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      IoUtil.close(in);
    } 
    return null;
  }
  
  private static boolean identify(byte[] bytes, CharsetDecoder decoder) {
    try {
      decoder.decode(ByteBuffer.wrap(bytes));
    } catch (CharacterCodingException e) {
      return false;
    } 
    return true;
  }
}
