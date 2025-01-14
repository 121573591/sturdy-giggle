package cn.hutool.http;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Assert;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class HttpDownloader {
  public static String downloadString(String url, Charset customCharset, StreamProgress streamPress) {
    FastByteArrayOutputStream out = new FastByteArrayOutputStream();
    download(url, (OutputStream)out, true, streamPress);
    return (null == customCharset) ? out.toString() : out.toString(customCharset);
  }
  
  public static byte[] downloadBytes(String url) {
    return requestDownload(url, -1).bodyBytes();
  }
  
  public static long downloadFile(String url, File targetFileOrDir, int timeout, StreamProgress streamProgress) {
    return requestDownload(url, timeout).writeBody(targetFileOrDir, streamProgress);
  }
  
  public static long downloadFile(String url, File targetFileOrDir, String tempFileSuffix, int timeout, StreamProgress streamProgress) {
    return requestDownload(url, timeout).writeBody(targetFileOrDir, tempFileSuffix, streamProgress);
  }
  
  public static File downloadForFile(String url, File targetFileOrDir, int timeout, StreamProgress streamProgress) {
    return requestDownload(url, timeout).writeBodyForFile(targetFileOrDir, streamProgress);
  }
  
  public static long download(String url, OutputStream out, boolean isCloseOut, StreamProgress streamProgress) {
    Assert.notNull(out, "[out] is null !", new Object[0]);
    return requestDownload(url, -1).writeBody(out, isCloseOut, streamProgress);
  }
  
  private static HttpResponse requestDownload(String url, int timeout) {
    Assert.notBlank(url, "[url] is blank !", new Object[0]);
    HttpResponse response = HttpUtil.createGet(url, true).timeout(timeout).executeAsync();
    if (response.isOk())
      return response; 
    throw new HttpException("Server response error with status code: [{}]", new Object[] { Integer.valueOf(response.getStatus()) });
  }
}
