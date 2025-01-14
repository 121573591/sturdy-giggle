package cn.hutool.extra.compress;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.compress.archiver.Archiver;
import cn.hutool.extra.compress.archiver.SevenZArchiver;
import cn.hutool.extra.compress.archiver.StreamArchiver;
import cn.hutool.extra.compress.extractor.Extractor;
import cn.hutool.extra.compress.extractor.SevenZExtractor;
import cn.hutool.extra.compress.extractor.StreamExtractor;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public class CompressUtil {
  public static CompressorOutputStream getOut(String compressorName, OutputStream out) {
    try {
      return (new CompressorStreamFactory()).createCompressorOutputStream(compressorName, out);
    } catch (CompressorException e) {
      throw new CompressException(e);
    } 
  }
  
  public static CompressorInputStream getIn(String compressorName, InputStream in) {
    in = IoUtil.toMarkSupportStream(in);
    try {
      if (StrUtil.isBlank(compressorName))
        compressorName = CompressorStreamFactory.detect(in); 
      return (new CompressorStreamFactory()).createCompressorInputStream(compressorName, in);
    } catch (CompressorException e) {
      throw new CompressException(e);
    } 
  }
  
  public static Archiver createArchiver(Charset charset, String archiverName, File file) {
    if ("7z".equalsIgnoreCase(archiverName))
      return (Archiver)new SevenZArchiver(file); 
    return (Archiver)StreamArchiver.create(charset, archiverName, file);
  }
  
  public static Archiver createArchiver(Charset charset, String archiverName, OutputStream out) {
    if ("7z".equalsIgnoreCase(archiverName))
      return (Archiver)new SevenZArchiver(out); 
    return (Archiver)StreamArchiver.create(charset, archiverName, out);
  }
  
  public static Extractor createExtractor(Charset charset, File file) {
    return createExtractor(charset, (String)null, file);
  }
  
  public static Extractor createExtractor(Charset charset, String archiverName, File file) {
    if ("7z".equalsIgnoreCase(archiverName))
      return (Extractor)new SevenZExtractor(file); 
    try {
      return (Extractor)new StreamExtractor(charset, archiverName, file);
    } catch (CompressException e) {
      Throwable cause = e.getCause();
      if (cause instanceof org.apache.commons.compress.archivers.StreamingNotSupportedException && cause.getMessage().contains("7z"))
        return (Extractor)new SevenZExtractor(file); 
      throw e;
    } 
  }
  
  public static Extractor createExtractor(Charset charset, InputStream in) {
    return createExtractor(charset, (String)null, in);
  }
  
  public static Extractor createExtractor(Charset charset, String archiverName, InputStream in) {
    if ("7z".equalsIgnoreCase(archiverName))
      return (Extractor)new SevenZExtractor(in); 
    try {
      return (Extractor)new StreamExtractor(charset, archiverName, in);
    } catch (CompressException e) {
      Throwable cause = e.getCause();
      if (cause instanceof org.apache.commons.compress.archivers.StreamingNotSupportedException && cause.getMessage().contains("7z"))
        return (Extractor)new SevenZExtractor(in); 
      throw e;
    } 
  }
}
