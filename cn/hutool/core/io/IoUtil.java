package cn.hutool.core.io;

import cn.hutool.core.collection.LineIter;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.copy.ReaderWriterCopier;
import cn.hutool.core.io.copy.StreamCopier;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

public class IoUtil extends NioUtil {
  public static long copy(Reader reader, Writer writer) throws IORuntimeException {
    return copy(reader, writer, 8192);
  }
  
  public static long copy(Reader reader, Writer writer, int bufferSize) throws IORuntimeException {
    return copy(reader, writer, bufferSize, (StreamProgress)null);
  }
  
  public static long copy(Reader reader, Writer writer, int bufferSize, StreamProgress streamProgress) throws IORuntimeException {
    return copy(reader, writer, bufferSize, -1L, streamProgress);
  }
  
  public static long copy(Reader reader, Writer writer, int bufferSize, long count, StreamProgress streamProgress) throws IORuntimeException {
    return (new ReaderWriterCopier(bufferSize, count, streamProgress)).copy(reader, writer);
  }
  
  public static long copy(InputStream in, OutputStream out) throws IORuntimeException {
    return copy(in, out, 8192);
  }
  
  public static long copy(InputStream in, OutputStream out, int bufferSize) throws IORuntimeException {
    return copy(in, out, bufferSize, (StreamProgress)null);
  }
  
  public static long copy(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) throws IORuntimeException {
    return copy(in, out, bufferSize, -1L, streamProgress);
  }
  
  public static long copy(InputStream in, OutputStream out, int bufferSize, long count, StreamProgress streamProgress) throws IORuntimeException {
    return (new StreamCopier(bufferSize, count, streamProgress)).copy(in, out);
  }
  
  public static long copy(FileInputStream in, FileOutputStream out) throws IORuntimeException {
    Assert.notNull(in, "FileInputStream is null!", new Object[0]);
    Assert.notNull(out, "FileOutputStream is null!", new Object[0]);
    FileChannel inChannel = null;
    FileChannel outChannel = null;
    try {
      inChannel = in.getChannel();
      outChannel = out.getChannel();
      return copy(inChannel, outChannel);
    } finally {
      close(outChannel);
      close(inChannel);
    } 
  }
  
  public static BufferedReader getUtf8Reader(InputStream in) {
    return getReader(in, CharsetUtil.CHARSET_UTF_8);
  }
  
  @Deprecated
  public static BufferedReader getReader(InputStream in, String charsetName) {
    return getReader(in, Charset.forName(charsetName));
  }
  
  public static BufferedReader getReader(BOMInputStream in) {
    return getReader(in, in.getCharset());
  }
  
  public static BomReader getBomReader(InputStream in) {
    return new BomReader(in);
  }
  
  public static BufferedReader getReader(InputStream in, Charset charset) {
    InputStreamReader reader;
    if (null == in)
      return null; 
    if (null == charset) {
      reader = new InputStreamReader(in);
    } else {
      reader = new InputStreamReader(in, charset);
    } 
    return new BufferedReader(reader);
  }
  
  public static BufferedReader getReader(Reader reader) {
    if (null == reader)
      return null; 
    return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
  }
  
  public static PushbackReader getPushBackReader(Reader reader, int pushBackSize) {
    return (reader instanceof PushbackReader) ? (PushbackReader)reader : new PushbackReader(reader, pushBackSize);
  }
  
  public static OutputStreamWriter getUtf8Writer(OutputStream out) {
    return getWriter(out, CharsetUtil.CHARSET_UTF_8);
  }
  
  @Deprecated
  public static OutputStreamWriter getWriter(OutputStream out, String charsetName) {
    return getWriter(out, Charset.forName(charsetName));
  }
  
  public static OutputStreamWriter getWriter(OutputStream out, Charset charset) {
    if (null == out)
      return null; 
    if (null == charset)
      return new OutputStreamWriter(out); 
    return new OutputStreamWriter(out, charset);
  }
  
  public static String readUtf8(InputStream in) throws IORuntimeException {
    return read(in, CharsetUtil.CHARSET_UTF_8);
  }
  
  @Deprecated
  public static String read(InputStream in, String charsetName) throws IORuntimeException {
    FastByteArrayOutputStream out = read(in);
    return StrUtil.isBlank(charsetName) ? out.toString() : out.toString(charsetName);
  }
  
  public static String read(InputStream in, Charset charset) throws IORuntimeException {
    return StrUtil.str(readBytes(in), charset);
  }
  
  public static FastByteArrayOutputStream read(InputStream in) throws IORuntimeException {
    return read(in, true);
  }
  
  public static FastByteArrayOutputStream read(InputStream in, boolean isClose) throws IORuntimeException {
    FastByteArrayOutputStream out;
    if (in instanceof FileInputStream) {
      try {
        out = new FastByteArrayOutputStream(in.available());
      } catch (IOException e) {
        throw new IORuntimeException(e);
      } 
    } else {
      out = new FastByteArrayOutputStream();
    } 
    try {
      copy(in, out);
    } finally {
      if (isClose)
        close(in); 
    } 
    return out;
  }
  
  public static String read(Reader reader) throws IORuntimeException {
    return read(reader, true);
  }
  
  public static String read(Reader reader, boolean isClose) throws IORuntimeException {
    StringBuilder builder = StrUtil.builder();
    CharBuffer buffer = CharBuffer.allocate(8192);
    try {
      while (-1 != reader.read(buffer))
        builder.append(buffer.flip()); 
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      if (isClose)
        close(reader); 
    } 
    return builder.toString();
  }
  
  public static byte[] readBytes(InputStream in) throws IORuntimeException {
    return readBytes(in, true);
  }
  
  public static byte[] readBytes(InputStream in, boolean isClose) throws IORuntimeException {
    return read(in, isClose).toByteArray();
  }
  
  public static byte[] readBytes(InputStream in, int length) throws IORuntimeException {
    if (null == in)
      return null; 
    if (length <= 0)
      return new byte[0]; 
    FastByteArrayOutputStream out = new FastByteArrayOutputStream(length);
    copy(in, out, 8192, length, (StreamProgress)null);
    return out.toByteArray();
  }
  
  public static String readHex(InputStream in, int length, boolean toLowerCase) throws IORuntimeException {
    return HexUtil.encodeHexStr(readBytes(in, length), toLowerCase);
  }
  
  public static String readHex64Upper(InputStream in) throws IORuntimeException {
    return readHex(in, 64, false);
  }
  
  public static String readHex8192Upper(InputStream in) throws IORuntimeException {
    try {
      int i = in.available();
      return readHex(in, Math.min(8192, in.available()), false);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static String readHex64Lower(InputStream in) throws IORuntimeException {
    return readHex(in, 64, true);
  }
  
  public static <T> T readObj(InputStream in) throws IORuntimeException, UtilException {
    return readObj(in, (Class<T>)null);
  }
  
  public static <T> T readObj(InputStream in, Class<T> clazz) throws IORuntimeException, UtilException {
    try {
      return readObj((in instanceof ValidateObjectInputStream) ? (ValidateObjectInputStream)in : new ValidateObjectInputStream(in, new Class[0]), clazz);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static <T> T readObj(ValidateObjectInputStream in, Class<T> clazz) throws IORuntimeException, UtilException {
    if (in == null)
      throw new IllegalArgumentException("The InputStream must not be null"); 
    if (null != clazz)
      in.accept(new Class[] { clazz }); 
    try {
      return (T)in.readObject();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new UtilException(e);
    } 
  }
  
  public static <T extends java.util.Collection<String>> T readUtf8Lines(InputStream in, T collection) throws IORuntimeException {
    return readLines(in, CharsetUtil.CHARSET_UTF_8, collection);
  }
  
  @Deprecated
  public static <T extends java.util.Collection<String>> T readLines(InputStream in, String charsetName, T collection) throws IORuntimeException {
    return readLines(in, CharsetUtil.charset(charsetName), collection);
  }
  
  public static <T extends java.util.Collection<String>> T readLines(InputStream in, Charset charset, T collection) throws IORuntimeException {
    return readLines(getReader(in, charset), collection);
  }
  
  public static <T extends java.util.Collection<String>> T readLines(Reader reader, T collection) throws IORuntimeException {
    readLines(reader, collection::add);
    return collection;
  }
  
  public static void readUtf8Lines(InputStream in, LineHandler lineHandler) throws IORuntimeException {
    readLines(in, CharsetUtil.CHARSET_UTF_8, lineHandler);
  }
  
  public static void readLines(InputStream in, Charset charset, LineHandler lineHandler) throws IORuntimeException {
    readLines(getReader(in, charset), lineHandler);
  }
  
  public static void readLines(Reader reader, LineHandler lineHandler) throws IORuntimeException {
    Assert.notNull(reader);
    Assert.notNull(lineHandler);
    for (String line : lineIter(reader))
      lineHandler.handle(line); 
  }
  
  @Deprecated
  public static ByteArrayInputStream toStream(String content, String charsetName) {
    return toStream(content, CharsetUtil.charset(charsetName));
  }
  
  public static ByteArrayInputStream toStream(String content, Charset charset) {
    if (content == null)
      return null; 
    return toStream(StrUtil.bytes(content, charset));
  }
  
  public static ByteArrayInputStream toUtf8Stream(String content) {
    return toStream(content, CharsetUtil.CHARSET_UTF_8);
  }
  
  public static FileInputStream toStream(File file) {
    try {
      return new FileInputStream(file);
    } catch (FileNotFoundException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static ByteArrayInputStream toStream(byte[] content) {
    if (content == null)
      return null; 
    return new ByteArrayInputStream(content);
  }
  
  public static ByteArrayInputStream toStream(ByteArrayOutputStream out) {
    if (out == null)
      return null; 
    return new ByteArrayInputStream(out.toByteArray());
  }
  
  public static BufferedInputStream toBuffered(InputStream in) {
    Assert.notNull(in, "InputStream must be not null!", new Object[0]);
    return (in instanceof BufferedInputStream) ? (BufferedInputStream)in : new BufferedInputStream(in);
  }
  
  public static BufferedInputStream toBuffered(InputStream in, int bufferSize) {
    Assert.notNull(in, "InputStream must be not null!", new Object[0]);
    return (in instanceof BufferedInputStream) ? (BufferedInputStream)in : new BufferedInputStream(in, bufferSize);
  }
  
  public static BufferedOutputStream toBuffered(OutputStream out) {
    Assert.notNull(out, "OutputStream must be not null!", new Object[0]);
    return (out instanceof BufferedOutputStream) ? (BufferedOutputStream)out : new BufferedOutputStream(out);
  }
  
  public static BufferedOutputStream toBuffered(OutputStream out, int bufferSize) {
    Assert.notNull(out, "OutputStream must be not null!", new Object[0]);
    return (out instanceof BufferedOutputStream) ? (BufferedOutputStream)out : new BufferedOutputStream(out, bufferSize);
  }
  
  public static BufferedReader toBuffered(Reader reader) {
    Assert.notNull(reader, "Reader must be not null!", new Object[0]);
    return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
  }
  
  public static BufferedReader toBuffered(Reader reader, int bufferSize) {
    Assert.notNull(reader, "Reader must be not null!", new Object[0]);
    return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader, bufferSize);
  }
  
  public static BufferedWriter toBuffered(Writer writer) {
    Assert.notNull(writer, "Writer must be not null!", new Object[0]);
    return (writer instanceof BufferedWriter) ? (BufferedWriter)writer : new BufferedWriter(writer);
  }
  
  public static BufferedWriter toBuffered(Writer writer, int bufferSize) {
    Assert.notNull(writer, "Writer must be not null!", new Object[0]);
    return (writer instanceof BufferedWriter) ? (BufferedWriter)writer : new BufferedWriter(writer, bufferSize);
  }
  
  public static InputStream toMarkSupportStream(InputStream in) {
    if (null == in)
      return null; 
    if (false == in.markSupported())
      return new BufferedInputStream(in); 
    return in;
  }
  
  public static PushbackInputStream toPushbackStream(InputStream in, int pushBackSize) {
    return (in instanceof PushbackInputStream) ? (PushbackInputStream)in : new PushbackInputStream(in, pushBackSize);
  }
  
  public static InputStream toAvailableStream(InputStream in) {
    if (in instanceof FileInputStream)
      return in; 
    PushbackInputStream pushbackInputStream = toPushbackStream(in, 1);
    try {
      int available = pushbackInputStream.available();
      if (available <= 0) {
        int b = pushbackInputStream.read();
        pushbackInputStream.unread(b);
      } 
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return pushbackInputStream;
  }
  
  public static void write(OutputStream out, boolean isCloseOut, byte[] content) throws IORuntimeException {
    try {
      out.write(content);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      if (isCloseOut)
        close(out); 
    } 
  }
  
  public static void writeUtf8(OutputStream out, boolean isCloseOut, Object... contents) throws IORuntimeException {
    write(out, CharsetUtil.CHARSET_UTF_8, isCloseOut, contents);
  }
  
  @Deprecated
  public static void write(OutputStream out, String charsetName, boolean isCloseOut, Object... contents) throws IORuntimeException {
    write(out, CharsetUtil.charset(charsetName), isCloseOut, contents);
  }
  
  public static void write(OutputStream out, Charset charset, boolean isCloseOut, Object... contents) throws IORuntimeException {
    OutputStreamWriter osw = null;
    try {
      osw = getWriter(out, charset);
      for (Object content : contents) {
        if (content != null)
          osw.write(Convert.toStr(content, "")); 
      } 
      osw.flush();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      if (isCloseOut)
        close(osw); 
    } 
  }
  
  public static void writeObj(OutputStream out, boolean isCloseOut, Serializable obj) throws IORuntimeException {
    writeObjects(out, isCloseOut, new Serializable[] { obj });
  }
  
  public static void writeObjects(OutputStream out, boolean isCloseOut, Serializable... contents) throws IORuntimeException {
    ObjectOutputStream osw = null;
    try {
      osw = (out instanceof ObjectOutputStream) ? (ObjectOutputStream)out : new ObjectOutputStream(out);
      for (Serializable content : contents) {
        if (content != null)
          osw.writeObject(content); 
      } 
      osw.flush();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      if (isCloseOut)
        close(osw); 
    } 
  }
  
  public static void flush(Flushable flushable) {
    if (null != flushable)
      try {
        flushable.flush();
      } catch (Exception exception) {} 
  }
  
  public static void close(Closeable closeable) {
    if (null != closeable)
      try {
        closeable.close();
      } catch (Exception exception) {} 
  }
  
  public static void closeIfPosible(Object obj) {
    if (obj instanceof AutoCloseable)
      close((AutoCloseable)obj); 
  }
  
  public static boolean contentEquals(InputStream input1, InputStream input2) throws IORuntimeException {
    if (false == input1 instanceof BufferedInputStream)
      input1 = new BufferedInputStream(input1); 
    if (false == input2 instanceof BufferedInputStream)
      input2 = new BufferedInputStream(input2); 
    try {
      int ch = input1.read();
      while (-1 != ch) {
        int i = input2.read();
        if (ch != i)
          return false; 
        ch = input1.read();
      } 
      int ch2 = input2.read();
      return (ch2 == -1);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static boolean contentEquals(Reader input1, Reader input2) throws IORuntimeException {
    input1 = getReader(input1);
    input2 = getReader(input2);
    try {
      int ch = input1.read();
      while (-1 != ch) {
        int i = input2.read();
        if (ch != i)
          return false; 
        ch = input1.read();
      } 
      int ch2 = input2.read();
      return (ch2 == -1);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) throws IORuntimeException {
    BufferedReader br1 = getReader(input1);
    BufferedReader br2 = getReader(input2);
    try {
      String line1 = br1.readLine();
      String line2 = br2.readLine();
      while (line1 != null && line1.equals(line2)) {
        line1 = br1.readLine();
        line2 = br2.readLine();
      } 
      return Objects.equals(line1, line2);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static long checksumCRC32(InputStream in) throws IORuntimeException {
    return checksum(in, new CRC32()).getValue();
  }
  
  public static Checksum checksum(InputStream in, Checksum checksum) throws IORuntimeException {
    Assert.notNull(in, "InputStream is null !", new Object[0]);
    if (null == checksum)
      checksum = new CRC32(); 
    try {
      in = new CheckedInputStream(in, checksum);
      copy(in, new NullOutputStream());
    } finally {
      close(in);
    } 
    return checksum;
  }
  
  public static long checksumValue(InputStream in, Checksum checksum) {
    return checksum(in, checksum).getValue();
  }
  
  public static LineIter lineIter(Reader reader) {
    return new LineIter(reader);
  }
  
  public static LineIter lineIter(InputStream in, Charset charset) {
    return new LineIter(in, charset);
  }
  
  public static String toStr(ByteArrayOutputStream out, Charset charset) {
    try {
      return out.toString(charset.name());
    } catch (UnsupportedEncodingException e) {
      throw new IORuntimeException(e);
    } 
  }
}
