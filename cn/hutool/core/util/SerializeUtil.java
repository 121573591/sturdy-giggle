package cn.hutool.core.util;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.ValidateObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializeUtil {
  public static <T> T clone(T obj) {
    if (false == obj instanceof Serializable)
      return null; 
    return deserialize(serialize(obj), new Class[0]);
  }
  
  public static <T> byte[] serialize(T obj) {
    if (false == obj instanceof Serializable)
      return null; 
    FastByteArrayOutputStream byteOut = new FastByteArrayOutputStream();
    IoUtil.writeObjects((OutputStream)byteOut, false, new Serializable[] { (Serializable)obj });
    return byteOut.toByteArray();
  }
  
  public static <T> T deserialize(byte[] bytes, Class<?>... acceptClasses) {
    try {
      return (T)IoUtil.readObj((InputStream)new ValidateObjectInputStream(new ByteArrayInputStream(bytes), acceptClasses));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
}
